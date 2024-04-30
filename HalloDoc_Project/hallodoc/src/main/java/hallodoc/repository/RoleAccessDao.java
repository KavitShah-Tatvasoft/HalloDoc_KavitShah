package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Transaction;

import hallodoc.model.AspNetRoles;
import hallodoc.model.Menu;
import hallodoc.model.Role;

@Repository
public class RoleAccessDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public List<Menu> getRoleRelatedMenus(Integer roleId) {

		Session s = this.sessionFactory.openSession();

		String queryString = "FROM Menu m where m.accountType=:roleId";
		Query q = s.createQuery(queryString);
		q.setParameter("roleId", roleId);
		List<Menu> list = q.list();
		s.close();
		return list;
	}

	public List<Menu> getMenuObList(List<Integer> menuList) {

		Session s = this.sessionFactory.openSession();

		String queryString = "FROM Menu m where m.menuId IN (:menuList)";
		Query q = s.createQuery(queryString);
		q.setParameter("menuList", menuList);
		List<Menu> list = q.list();
		s.close();
		return list;

	}

	@Transactional
	public int saveRole(Role role) {
		return (Integer) this.hibernateTemplate.save(role);
	}

	public List<Role> getAllActiveRoles() {

		Session s = this.sessionFactory.openSession();

		String queryString = "FROM Role r where r.isDeleted = false";
		Query q = s.createQuery(queryString);
		List<Role> list = q.list();
		s.close();
		return list;

	}

	public Role getRoleOb(Integer roleId) {
		return this.hibernateTemplate.get(Role.class, roleId);
	}

	public List<Menu> getAllMenuForRoleType(Integer accType) {

		Session s = this.sessionFactory.openSession();

		String queryString = "FROM Menu m WHERE m.accountType =: accType";
		Query q = s.createQuery(queryString);
		q.setParameter("accType", accType);
		List<Menu> list = q.list();
		s.close();
		return list;

	}
	
	
	@Transactional
	public void deleteAllRoleMenuById(Integer roleId) {
		
		Session s = this.sessionFactory.openSession();
//		Transaction tx = s.beginTransaction();
		String queryString = "DELETE FROM role_menu WHERE role_id =:roleId";
		Query q = s.createNativeQuery(queryString);
		q.setParameter("roleId", roleId);
		q.executeUpdate();
//		tx.commit();
		s.close();

	}
	
	@Transactional
	public void updateRole(Role role) {
		this.hibernateTemplate.update(role);
	}

	@Transactional
	public void deleteRoleById(Integer roleId) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		String queryString = "UPDATE Role rl SET rl.isDeleted = true WHERE rl.roleId =:roleId" ;
		Query q = s.createQuery(queryString);
		q.setParameter("roleId", roleId);
		q.executeUpdate();
		tx.commit();
		s.close();
	}
}
