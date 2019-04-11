package com.place4code.springdemo.dao;

import java.util.List;

import com.place4code.springdemo.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();

	public void saveCustomers(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomer(String theSearchName);
}