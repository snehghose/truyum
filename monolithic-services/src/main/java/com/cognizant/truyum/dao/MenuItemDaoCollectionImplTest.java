package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

/**
 * @author 810491
 *
 */

public class MenuItemDaoCollectionImplTest {

	public static void testGetMenuItemListAdmin() throws ParseException {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		List<MenuItem> menuItemList = menuItemDao.getMenuItemListAdmin();
		System.out.println("Admin menu list");
		for (MenuItem menuItem : menuItemList)
			System.out.println(menuItem.toString());

	}

	public static void testGetMenuItemListCustomer() throws ParseException {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		List<MenuItem> menuItemList = menuItemDao.getMenuItemListCustomer();
		System.out.println("Customer menu list");
		for (MenuItem menuItem : menuItemList)
			System.out.println(menuItem.toString());
	}

	public static void testModifyMenuItem() throws ParseException {
		MenuItem menuItem = new MenuItem(2, "Chocolate Avalanche", 150, true, DateUtil.convertToDate("03/02/2020"),
				"Dessert", true);

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		menuItemDao.modifyMenuItem(menuItem);
		System.out.println(menuItemDao.getMenuItem(2).toString());
	}

	public static void main(String[] args) throws ParseException {
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
	}

}
