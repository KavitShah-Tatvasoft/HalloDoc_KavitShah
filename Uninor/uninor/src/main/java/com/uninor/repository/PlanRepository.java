package com.uninor.repository;

import com.uninor.dto.RechargePlanFilter;
import com.uninor.dto.RechargePlanFilter;
import com.uninor.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlanRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;


    public List<Plan> getActivePlan(){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Plan WHERE isDeleted=false ORDER BY categoryId.planId ASC, rechargeAmount ASC";
        Query<Plan> q = s.createQuery(queryString);
        List<Plan> list = q.list();
        s.close();
        return list;
    }

    public List<PlanActivation> getExpiringPlans(LocalDate now){
        LocalDate inSevenDays = now.plusDays(7);
        Session s = this.sessionFactory.openSession();
        String query = "FROM PlanActivation p WHERE p.expirationDate BETWEEN :now AND :inSevenDays AND p.isExpired=false";
        Query<PlanActivation> q = s.createQuery(query, PlanActivation.class);
        q.setParameter("now", now);
        q.setParameter("inSevenDays", inSevenDays);
        List<PlanActivation> list = q.list();
        s.close();
        return list;
    }

    public List<PlanActivation> getExpiredPlans(){
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime startOfDay = yesterday.atStartOfDay();
        LocalDateTime endOfDay = yesterday.atTime(LocalTime.MAX);
        Session s = this.sessionFactory.openSession();
        String query = "FROM PlanActivation p WHERE p.expirationDate BETWEEN :startOfDay AND :endOfDay";
        Query<PlanActivation> q = s.createQuery(query, PlanActivation.class);
        q.setParameter("startOfDay", startOfDay);
        q.setParameter("endOfDay", endOfDay);
        List<PlanActivation> list = q.list();
        s.close();
        return list;
    }

    public List<Plan> getActiveFilteredPlan(RechargePlanFilter filters){
        Session s = this.sessionFactory.openSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Plan> cr = cb.createQuery(Plan.class);
        Root<Plan> root = cr.from(Plan.class);

        Join<Plan, PlanCategories> categoriesJoin = root.join("categoryId");

        Predicate predicate;

        List<Predicate> predicates = new ArrayList<Predicate>();

        String[] refreshingData = filters.getRefreshingPlanFilters().split(",");

        int refreshingDataFilterCount = 0;
        for (String str : refreshingData) {
            refreshingDataFilterCount++;
            if(Integer.parseInt(str) == 1){
                predicate = cb.equal(categoriesJoin.get("planId"),refreshingDataFilterCount);
                predicates.add(predicate);
            }
        }

        String[] fixedData = filters.getFixedDataPlanFilters().split(",");

        int fixedDataCount = 0;
        for (String str : fixedData) {
            fixedDataCount++;

            switch (fixedDataCount){
                case 1:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),6),cb.between(root.get("dataAllowance"), 0, 200000));
                        predicates.add(predicate);
                    }
                    break;

                case 2:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),6),cb.between(root.get("dataAllowance"), 200000, 300000));
                        predicates.add(predicate);
                    }
                    break;
                case 3:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),6),cb.greaterThanOrEqualTo(root.get("dataAllowance"), 300000));
                        predicates.add(predicate);
                    }
                    break;
                default:
                    break;
            }

        }

        String[] addOnData = filters.getAddOnDataFilters().split(",");

        int addOnDataCount = 0;
        for (String str : addOnData) {
            addOnDataCount++;

            switch (addOnDataCount){
                case 1:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),7),cb.between(root.get("dataAllowance"), 0, 25000));
                        predicates.add(predicate);
                    }
                    break;

                case 2:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),7),cb.between(root.get("dataAllowance"), 25000, 50000));
                        predicates.add(predicate);
                    }
                    break;
                case 3:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),7),cb.between(root.get("dataAllowance"), 50000, 100000));
                        predicates.add(predicate);
                    }
                    break;
                case 4:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),7),cb.greaterThanOrEqualTo(root.get("dataAllowance"), 100000));
                        predicates.add(predicate);
                    }
                    break;
                default:
                    break;
            }

        }

        String[] roamingData = filters.getRoamingDataFilters().split(",");

        int roamingDataCount = 0;
        for (String str : roamingData) {
            roamingDataCount++;

            switch (roamingDataCount){
                case 1:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),9),cb.equal(root.get("voiceAllowance"), "None"));
                        predicates.add(predicate);
                    }
                    break;

                case 2:
                    if(Integer.parseInt(str) == 1){
                        predicate = cb.and(cb.equal(categoriesJoin.get("planId"),9),cb.notEqual(root.get("voiceAllowance"), "None"), cb.notEqual(root.get("voiceAllowance"), "Unlimited"));
                        predicates.add(predicate);
                    }
                    break;
                default:
                    break;
            }

        }

//        Predicate categoryPredicates = cb.or(predicates.toArray(new Predicate[0]));

        String[] amountFilters = filters.getAmountFilters().split(",");
        List<Predicate> amountPredicates = new ArrayList<>();
        int amountFilterCount = 0;
        for (String str : amountFilters) {
            amountFilterCount++;
            switch (amountFilterCount) {
                case 1:
                    if(Integer.parseInt(str) == 1){
                        amountPredicates.add(cb.between(root.get("rechargeAmount"), 20, 300)); // Example range
                    }
                    break;
                case 2:
                    if(Integer.parseInt(str) == 1){
                        amountPredicates.add(cb.between(root.get("rechargeAmount"), 301, 600)); // Example range
                    }
                    break;
                case 3:
                    if(Integer.parseInt(str) == 1){
                        amountPredicates.add(cb.between(root.get("rechargeAmount"), 601, 900)); // Example range
                    }
                    break;
                case 4:
                    if(Integer.parseInt(str) == 1){
                        amountPredicates.add(cb.greaterThanOrEqualTo(root.get("rechargeAmount"), 900)); // Example value
                    }
                    break;
                default:
                    break;
            }
        }
//        Predicate amountPredicate = cb.or(amountPredicates.toArray(new Predicate[0]));

        String[] validityFilters = filters.getValidityFilters().split(",");
        List<Predicate> validityPredicates = new ArrayList<>();
        int validityFilterCount = 0;
        for (String str : validityFilters) {
            validityFilterCount++;
            switch (validityFilterCount) {
                case 1:
                    if(Integer.parseInt(str) == 1){
                        validityPredicates.add(cb.lessThanOrEqualTo(root.get("validity"), 30)); // Example range
                    }
                    break;
                case 2:
                    if(Integer.parseInt(str) == 1){
                        validityPredicates.add(cb.between(root.get("validity"), 31, 60)); // Example range
                    }
                    break;
                case 3:
                    if(Integer.parseInt(str) == 1){
                        validityPredicates.add(cb.greaterThanOrEqualTo(root.get("validity"), 61)); // Example range
                    }
                    break;
                case 4:
                    if(Integer.parseInt(str) == 1){
                        validityPredicates.add(cb.greaterThanOrEqualTo(root.get("validity"), 300)); // Example value
                    }
                    break;
                default:
                    break;
            }
        }
//        Predicate validityPredicate = cb.or(validityPredicates.toArray(new Predicate[0]));

        Predicate categoryPredicate = predicates.isEmpty() ? null : cb.or(predicates.toArray(new Predicate[0]));

        Predicate amountPredicate = amountPredicates.isEmpty() ? null : cb.or(amountPredicates.toArray(new Predicate[0]));

        Predicate validityPredicate = validityPredicates.isEmpty() ? null : cb.or(validityPredicates.toArray(new Predicate[0]));

        Predicate activePrdicate = cb.equal(root.get("isDeleted"), false);

        List<Predicate> finalPredicates = new ArrayList<>();

        finalPredicates.add(activePrdicate);

        if (categoryPredicate != null) {
            finalPredicates.add(categoryPredicate);
        }
        if (amountPredicate != null){
            finalPredicates.add(amountPredicate);
        }
        if (validityPredicate != null){
            finalPredicates.add(validityPredicate);
        }



        Predicate finalPredicate = cb.and(finalPredicates.toArray(new Predicate[0]));

        cr.where(finalPredicate);
        Query<Plan> query = s.createQuery(cr);

        List<Plan> list = query.getResultList();
        s.close();
        return list;
    }

    public List<Plan> getPopularActivePlans(){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Plan WHERE isDeleted=false AND categoryId.isAddOn = false ORDER BY boughtCount DESC";
        Query<Plan> q = s.createQuery(queryString);
        q.setMaxResults(6);
        List<Plan> list = q.list();
        s.close();
        return list;
    }

    public Plan getPlanById(int planId){
        return this.hibernateTemplate.get(Plan.class, planId);
    }

    @Transactional
    public void updatePlanBoughtCount(Plan plan){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hqlUpdate = "UPDATE Plan p set p.boughtCount = p.boughtCount + 1 where p.planId = :planId";
        Query<Plan> q = s.createQuery(hqlUpdate);
        q.setParameter("planId", plan.getPlanId());
        q.executeUpdate();
        tx.commit();
        s.close();
    }

}
