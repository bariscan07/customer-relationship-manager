package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Inject the hibernate session factory dependency
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSesssion = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = 
				currentSesssion.createQuery("from Customer", Customer.class);
		
		// execute query and get the result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the result.
		return customers;

	}

}