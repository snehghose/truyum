package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.FileImportUtil;

public class CartDaoSqlImpl implements CartDao {

	@Override
	public void addCartItem(long userID, long menuItemId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionHandler.getConnection();
			preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/insert_cart.sql"));
			preparedStatement.setLong(1, userID);
			preparedStatement.setLong(2, menuItemId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<MenuItem> getAllCartItem(long userID) throws CartEmptyException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cart cart = new Cart(new ArrayList<MenuItem>(), 0);
		try {
			connection = ConnectionHandler.getConnection();
			preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/view_cart.sql"));
			preparedStatement.setLong(1, userID);
			resultSet = preparedStatement.executeQuery();
			List<MenuItem> menuItemList = cart.getMenuItemList();
			if (resultSet.next()) {
				do
					menuItemList.add(new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3),
							resultSet.getBoolean(4), resultSet.getDate(5), resultSet.getString(6),
							resultSet.getBoolean(7)));
				while (resultSet.next());
				cart.setMenuItemList(menuItemList);
				preparedStatement.close();
				resultSet.close();
				preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/view_total_price.sql"));
				preparedStatement.setLong(1, userID);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				double d=Double.parseDouble(new DecimalFormat("###.##").format(resultSet.getDouble(1)));
				cart.setTotal(d);
				System.out.println(d);
			} else {
				throw new CartEmptyException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cart.getMenuItemList();
	}

	@Override
	public void removeCartItem(long userID, long menuItemId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionHandler.getConnection();
			preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/remove_item_from_cart.sql"));
			preparedStatement.setLong(1, userID);
			preparedStatement.setLong(2, menuItemId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
