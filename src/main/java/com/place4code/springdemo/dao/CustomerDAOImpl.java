package com.place4code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.place4code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//inject sessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		return sessionFactory.getCurrentSession().createQuery("from Customer order by lastName", Customer.class).getResultList();
	}

	@Override
	public void saveCustomers(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		return sessionFactory.getCurrentSession().get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		sessionFactory
		.getCurrentSession()
		.createQuery("delete from Customer where id=:id")
		.setParameter("id", id)
		.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> query = null;
		
		if(theSearchName != null && theSearchName.trim().length() > 0) {
			query = session.createQuery("FROM Customer WHERE lower(firstName) LIKE :theName OR lower(lastName) LIKE :theName", Customer.class);
			query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("FROM Customer", Customer.class);
		}
		
		return query.getResultList();
	}

}
