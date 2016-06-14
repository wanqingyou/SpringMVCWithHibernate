package com.spring.vo;

import java.io.Serializable;

public class ProductToBeItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2437085215704732161L;
	private int productId;
	private String name;
	private double unitPrice;
	private int quantity;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
