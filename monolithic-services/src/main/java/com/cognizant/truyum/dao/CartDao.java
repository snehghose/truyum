package com.cognizant.truyum.dao;

import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.model.Cart;

/**
 * @author 810491
 *
 */
public interface CartDao {

	public void addCartItem(String user, long menuItemId);

	public Cart getAllCartItems(String user) throws CartEmptyException;

	public void removeCartItem(String user, long menuItemId);

}
