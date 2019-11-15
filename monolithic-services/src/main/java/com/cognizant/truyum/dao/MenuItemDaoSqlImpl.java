package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.FileImportUtil;

public class MenuItemDaoSqlImpl implements MenuItemDao {

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MenuItem> menuItemList = null;
		try {
			menuItemList = new ArrayList<>();
			connection = ConnectionHandler.getConnection();
			ConnectionHandler.createSchema();
			preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/view_menu_item_list_admin.sql"));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
				menuItemList.add(new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3),
						resultSet.getBoolean(4), resultSet.getDate(5), resultSet.getString(6),
						resultSet.getBoolean(7)));
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
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MenuItem> menuItemList = null;
		try {
			menuItemList = new ArrayList<>();
			connection = ConnectionHandler.getConnection();
			ConnectionHandler.createSchema();
			preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/view_menu_item_list_customer.sql"));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
				menuItemList.add(new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3),
						resultSet.getBoolean(4), resultSet.getDate(5), resultSet.getString(6),
						resultSet.getBoolean(7)));
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
		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionHandler.getConnection();
			preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/update_menu_item.sql"));
			preparedStatement.setString(1, menuItem.getName());
			preparedStatement.setFloat(2, menuItem.getPrice());
			preparedStatement.setString(3, (menuItem.isActive() ? "Yes" : "No"));
			preparedStatement.setDate(4, new java.sql.Date(menuItem.getDateOfLaunch().getTime()));
			preparedStatement.setString(5, menuItem.getCategory());
			preparedStatement.setString(6, menuItem.isFreeDelivery() ? "Yes" : "No");
			preparedStatement.setLong(7, menuItem.getId());
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
	public MenuItem getMenuItem(long menuItemId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MenuItem menuItem = null;
		try {
			connection = ConnectionHandler.getConnection();
			preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/get_menu_item.sql"));
			preparedStatement.setLong(1, menuItemId);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			menuItem = new MenuItem(resultSet.getLong(1), resultSet.getString(2), resultSet.getFloat(3),
					resultSet.getBoolean(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getBoolean(7));
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
		return menuItem;
	}

}
