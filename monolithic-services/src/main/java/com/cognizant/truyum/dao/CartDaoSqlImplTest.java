package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

/**
 * @author 810491
 *
 */
public class CartDaoSqlImplTest {

	public static void testAddCartItem() throws ParseException, CartEmptyException {
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem(1, 3);
		List<MenuItem> menuItemList = cartDao.getAllCartItem(1);
		int flag = 0;
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == 3) {
				System.out.println(menuItem.toString());
				flag = 1;
			}
		}
		if (flag == 0)
			System.out.println("Cart item not added");
	}

	public static void testGetAllCartItems() throws CartEmptyException {
		CartDao cartDao = new CartDaoSqlImpl();
		for (MenuItem menuItem : cartDao.getAllCartItem(1))
			System.out.println(menuItem);
	}

	public static void testRemoveCartItem() {
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.removeCartItem(1, 3);
	}

	public static void main(String[] args) throws ParseException, CartEmptyException {
		// testAddCartItem();
		// testRemoveCartItem();
		testGetAllCartItems();
	}

}
