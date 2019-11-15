package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.MenuItem;

/**
 * @author 810491
 *
 */
@SuppressWarnings("unchecked")
@Component
public class MenuItemDaoCollectionImpl implements MenuItemDao {
	//@Autowired
	private static List<MenuItem> menuItemList;

	public MenuItemDaoCollectionImpl() {
		super();
		ApplicationContext context=new ClassPathXmlApplicationContext("menu-items.xml");
		menuItemList=context.getBean("menuItemList",java.util.ArrayList.class);
		((ConfigurableApplicationContext)context).close();
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemListCustomer = new ArrayList<MenuItem>();
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getDateOfLaunch().after(new Date()))
				continue;
			if (menuItem.getIsActive())
				menuItemListCustomer.add(menuItem);
		}
		return menuItemListCustomer;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		for(int i=0;i<menuItemList.size();i++) {
			if(menuItemList.get(i).equals(menuItem)) {
				menuItemList.set(i, menuItem);
				System.out.println(menuItem);
				return;
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == menuItemId) {
				
				return menuItem;
			}
		}
		
		return null;
	}

}
