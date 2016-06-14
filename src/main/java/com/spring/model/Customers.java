package com.spring.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author WYou
 * @since 16.6.0
 */
@Entity
@Table(name = "Customers")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_ID")
	private int customerID;
	@Column(name = "name", length = 30)
	private String customerName;
	@Column(name = "contact", length = 30)
	private String contactInformation;
	@Column(name = "Address", length = 100)
	private String address;
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Orders.class)
	@JoinColumn(name = "customer_ID", referencedColumnName = "customer_ID")
	private Set<Orders> order;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public Set<Orders> getOrder() {
		return order;
	}

	public void setOrder(Set<Orders> order) {
		this.order = order;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return String.format("ID=%s, Name=%s, Contact=%s, Address=%s", this.customerID, this.customerName, this.contactInformation, this.address);
	}
}
