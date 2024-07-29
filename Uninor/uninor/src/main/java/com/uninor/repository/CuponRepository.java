package com.uninor.repository;

import com.uninor.dto.CouponsDetailsFilterDto;
import com.uninor.dto.CreateNewCouponDto;
import com.uninor.model.ClientCupons;
import com.uninor.model.ClientRequest;
import com.uninor.model.Cupon;
import com.uninor.model.CuponCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CuponRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<ClientCupons> getClientCupons(int clientId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientCupons WHERE client.clientId = :clientId AND isExpired=false AND isUsed=false";
        Query<ClientCupons> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        List<ClientCupons> list = q.list();
        s.close();
        return list;
    }

    public List<ClientCupons> getClientCuponByCuponCode(int clientId, String cuponCode){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientCupons WHERE client.clientId =:clientId AND cuponCode =:cuponCode";
        Query<ClientCupons> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.setParameter("cuponCode", cuponCode);
        List<ClientCupons> list = q.list();
        s.close();
        return list;
    }

    @Transactional
    public void updateClientCupons(ClientCupons cupon){
        this.hibernateTemplate.update(cupon);
    }

    public List<Cupon> getAllAvailableCoupons(){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Cupon c WHERE  c.isAvailable =true";
        Query<Cupon> q = s.createQuery(queryString);
        List<Cupon> list = q.list();
        s.close();
        return list;
    }

    @Transactional
    public int saveClientCupons(ClientCupons cupon){
        return (Integer) this.hibernateTemplate.save(cupon);
    }

    public Page<Cupon> getFilteredCoupons(Pageable pageable, CouponsDetailsFilterDto couponsDetailsFilterDto){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Cupon c WHERE  c.cuponCategory.cuponCategoryId =: couponType";
        Query<Cupon> q = s.createQuery(queryString);
        q.setParameter("couponType", couponsDetailsFilterDto.getCouponType());
        q.setMaxResults(pageable.getPageSize());
        q.setFirstResult((int) pageable.getOffset());
        List<Cupon> list = q.getResultList();

        Query<Cupon> query = s.createQuery(queryString);
        query.setParameter("couponType", couponsDetailsFilterDto.getCouponType());
        List<Cupon> countList = query.getResultList();
        int count = countList.size();
        s.close();
        return new PageImpl<>(list, pageable, count);
    }

    public List<CuponCategory> getAllCuponCategories(){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM CuponCategory c WHERE c.cuponCategoryId<>4";
        Query<CuponCategory> q = s.createQuery(queryString);
        List<CuponCategory> list = q.list();
        s.close();
        return list;
    }

    public Cupon checkAlreadyExistingCoupon(CreateNewCouponDto createNewCouponDto){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Cupon c WHERE c.cuponCategory.cuponCategoryId =:categoryId AND c.validityPeriod =:validityPeriod AND c.maxRewardAmount =:maxRewardAmount AND c.rewardAmount =:rewardAmount";
        Query<Cupon> q = s.createQuery(queryString);
        q.setParameter("categoryId",createNewCouponDto.getCouponType());
        q.setParameter("validityPeriod",createNewCouponDto.getValidity());
        if(createNewCouponDto.getCouponType() == 3){
            q.setParameter("rewardAmount",createNewCouponDto.getRewardMB()+" MB");
            q.setParameter("maxRewardAmount",0);
        }else {
            q.setParameter("maxRewardAmount",createNewCouponDto.getMaxReward());
            q.setParameter("rewardAmount",createNewCouponDto.getRewardPercentage()+"%");
        }

        List<Cupon> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    public CuponCategory getCouponCategoryById(int couponId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM CuponCategory c WHERE c.cuponCategoryId=:couponId";
        Query<CuponCategory> q = s.createQuery(queryString);
        q.setParameter("couponId", couponId);
        List<CuponCategory> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional
    public int addNewCoupon(Cupon cupon){
        return (Integer)this.hibernateTemplate.save(cupon);
    }

    public Cupon getCouponDetailsById(int couponId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Cupon c WHERE c.cuponId=:couponId";
        Query<Cupon> q = s.createQuery(queryString);
        q.setParameter("couponId", couponId);
        List<Cupon> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional
    public void updateCouponDetails(Cupon coupon){
        this.hibernateTemplate.update(coupon);
    }

    public boolean checkCouponAvailability(int categoryId, int validityPeriod, int rewardAmountMB, int rewardAmountPercentage, int maxRewardAmount, int couponId ){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Cupon c WHERE c.cuponCategory.cuponCategoryId =:categoryId AND c.validityPeriod =:validityPeriod AND c.maxRewardAmount =:maxRewardAmount AND c.rewardAmount =:rewardAmount AND c.cuponId !=:couponId";
        Query<Cupon> q = s.createQuery(queryString);
        q.setParameter("categoryId",categoryId);
        q.setParameter("validityPeriod",validityPeriod);
        q.setParameter("couponId",couponId);
        if(categoryId == 3){
            q.setParameter("rewardAmount",rewardAmountMB+" MB");
            q.setParameter("maxRewardAmount",0);
        }else {
            q.setParameter("maxRewardAmount",maxRewardAmount);
            q.setParameter("rewardAmount",rewardAmountPercentage+"%");
        }

        List<Cupon> list = q.list();
        s.close();
        return !list.isEmpty();
    }

}
