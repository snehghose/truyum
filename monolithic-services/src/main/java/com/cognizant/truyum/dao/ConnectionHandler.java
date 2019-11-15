package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.cognizant.truyum.util.FileImportUtil;

/**
 * @author 810491
 *
 */
public class ConnectionHandler {
	public static Connection getConnection()
	{
		Connection connection=null;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("connection.properties").toURI().getPath());
			Properties properties=new Properties();
			properties.load(fileInputStream);
			Class.forName((String)properties.get("driver"));
			connection=DriverManager.getConnection((String)properties.get("connection-url"),(String)properties.get("user"),(String)properties.get("password"));
		} catch (URISyntaxException | IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static void createSchema()
	{
		Connection connection=getConnection();
		PreparedStatement preparedStatement=null;
		try
		{
			preparedStatement=connection.prepareStatement("use truyum");
			preparedStatement.executeUpdate();
			
		}
		catch(SQLException e)
		{ 
			try {
				
				preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/truYum-schema-creation.sql"));
				preparedStatement.executeUpdate();
				preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/insert_menu_item_list_admin.sql"));
				preparedStatement.executeUpdate();
				preparedStatement=connection.prepareStatement(FileImportUtil.getSQL("sql/insert_user.sql"));
				preparedStatement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}