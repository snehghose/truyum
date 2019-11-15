package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

/**
 * @author 810491
 *
 */
@Component
public class CartDaoCollectionImpl implements CartDao {
	private static HashMap<String, Cart> userCarts;

	public CartDaoCollectionImpl() {
		super();
		if (userCarts == null)
			userCarts = new HashMap<String, Cart>();
	}

	@Override
	public void addCartItem(String user, long menuItemId) {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		if (userCarts.containsKey(user)) {
			Cart cart = userCarts.get(user);
			List<MenuItem> menuItemList = cart.getMenuItemList();
			for(int i=0;i<menuItemList.size();i++)
			{
				if(menuItemList.get(i).equals(menuItem))
				{
					MenuItem menuItemNew=menuItemList.get(i);
					menuItemNew.setQuantity(menuItemNew.getQuantity()+1);
					menuItemList.set(i, menuItemNew);
					cart.setMenuItemList(menuItemList);
					cart.setTotal(cart.getTotal() + menuItem.getPrice());
					userCarts.put(user, cart);
					return;
				}
			}
			/*if (menuItemList.contains(menuItem)) {
				menuItemList.put(menuItem, menuItemList.get(menuItem) + 1);
			} else {
				menuItemList.put(menuItem, 1);
			}*/
			menuItem.setQuantity(1);
			menuItemList.add(menuItem);
			cart.setMenuItemList(menuItemList);
			cart.setTotal(cart.getTotal() + menuItem.getPrice());
			userCarts.put(user, cart);
		} else {
			List<MenuItem> menuItemList = new ArrayList<MenuItem>();
			menuItem.setQuantity(1);
			menuItemList.add(menuItem);
			Cart cart = new Cart(menuItemList, menuItem.getPrice());
			userCarts.put(user, cart);
		}
	}

	@Override
	public void removeCartItem(String user, long menuItemId) {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		Cart cart = userCarts.get(user);
		List<MenuItem> menuItemList = cart.getMenuItemList();
		int flag=0;
		for(int i=0;i<menuItemList.size();i++)
		{
			if(menuItemList.get(i).equals(menuItem))
			{
				MenuItem menuItemNew=menuItemList.get(i);
				if(menuItemNew.getQuantity()>1)
				{
					menuItemNew.setQuantity(menuItemNew.getQuantity()-1);
					menuItemList.set(i, menuItemNew);
					flag=1;
					break;
				}
			}
		}
		if(flag==0)
			menuItemList.remove(menuItem);
		cart.setTotal(cart.getTotal() - menuItem.getPrice());
		cart.setMenuItemList(menuItemList);
		userCarts.put(user, cart);
	}

	@Override
	public Cart getAllCartItems(String user) throws CartEmptyException {
		Cart cart = userCarts.get(user);
		try {
			List<MenuItem> menuItemList = cart.getMenuItemList();
			if (menuItemList.isEmpty())
				throw new CartEmptyException();
			System.out.println(userCarts.get(user));
			return userCarts.get(user);
		} catch (NullPointerException e) {
			throw new CartEmptyException();
		}
	}

}
