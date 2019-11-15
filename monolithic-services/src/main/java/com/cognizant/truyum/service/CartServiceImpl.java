package com.cognizant.truyum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.model.Cart;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;
	
	public CartServiceImpl() {
		super();
	}

	public CartServiceImpl(CartDao cartDao) {
		super();
		this.cartDao = cartDao;
	}

	@Override
	public void addCartItem(String user, long menuItemId) {
		cartDao.addCartItem(user, menuItemId);
	}
	
	@Override
	public Cart getAllCartItems(String user) throws CartEmptyException{
		return cartDao.getAllCartItems(user);
	}

	@Override
	public void removeCartItem(String user, long menuItemId) {
		cartDao.removeCartItem(user, menuItemId);
	}
}
