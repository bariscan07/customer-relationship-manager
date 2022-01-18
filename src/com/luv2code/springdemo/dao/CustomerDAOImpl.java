package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Inject the hibernate session factory dependency
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSesssion = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = 
				currentSesssion.createQuery("from Customer order by lastName",
												Customer.class);
		
		// execute query and get the result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the result.
		return customers;

	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get the current hibernate session
		Session currentSesssion = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSesssion.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currentSesssion = sessionFactory.getCurrentSession();
		
		// now retrieve from database using the primary key
		Customer theCustomer = currentSesssion.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSesssion = sessionFactory.getCurrentSession();
		
		// delete the customer with primary key
		Query theQuery = 
				currentSesssion.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get the current hibernate session
		Session currentSesssion = sessionFactory.getCurrentSession();
		
		// transform the search keyword into lower case.
		theSearchName = theSearchName.toLowerCase();
		
		// create the HQL to search the given keyword in first or last name columns
		Query theQuery = 
				currentSesssion.createQuery("from Customer where lower(firstName) like :searchName"
												+ " or lower(lastName) like :searchName", Customer.class);
		
		// set the search keyword parameter
		theQuery.setParameter("searchName", "%" + theSearchName + "%");
		
		// execute the query and return to list
		List<Customer> theCustomers = theQuery.getResultList();
		
		return theCustomers;
	}

}
