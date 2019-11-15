package com.cognizant.truyum.model;

import java.util.Date;

/**
 * @author 810491
 *
 */
public class MenuItem {
	private int id;
	private String itemName;
	private float price;
	private boolean isActive;
	private Date dateOfLaunch;
	private String category;
	private boolean freeDelivery;
	private String imagePath;
	private int quantity;
	

	public MenuItem() {
		super();
	}

	

	public MenuItem(int id, String itemName, float price, boolean isActive, Date dateOfLaunch, String category,
			boolean freeDelivery, String imagePath, int quantity) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.isActive = isActive;
		this.dateOfLaunch = dateOfLaunch;
		this.category = category;
		this.freeDelivery = freeDelivery;
		this.imagePath = imagePath;
		this.quantity = quantity;
	}
	
	

	public int getQuantity() {
		return quantity;
	}

	

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", itemName=" + itemName + ", price=" + price + ", isActive=" + isActive
				+ ", dateOfLaunch=" + dateOfLaunch + ", category=" + category + ", freeDelivery=" + freeDelivery
				+ ", imagePath=" + imagePath + ", quantity=" + quantity + "]";
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}

	public void setDateOfLaunch(Date dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isFreeDelivery() {
		return freeDelivery;
	}

	public void setFreeDelivery(boolean freeDelivery) {
		this.freeDelivery = freeDelivery;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

}
