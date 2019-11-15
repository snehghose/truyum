package com.cognizant.truyum.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.truyum.TruyumConstants;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemServiceImpl;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

	public static Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

	@Autowired
	InMemoryUserDetailsManager inMemoryUserDetailsManager;

	@Autowired
	private MenuItemServiceImpl menuItemService;

	@GetMapping
	public List<MenuItem> getAllMenuItems() {
		LOGGER.debug("Inside getAllMenuItems");
		List<MenuItem> menuItemList;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		LOGGER.debug("Username " + user);
		if (user != "anonymousUser") {
			UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(user);
			LOGGER.debug("Userdetails " + userDetails);
			String role = userDetails.getAuthorities().toArray()[0].toString();
			TruyumConstants.LOGGER.debug("Role " + role);
			if (role.equals("ROLE_ADMIN")) {
				LOGGER.debug("Inside MenuItemListAdmin get");
				menuItemList = menuItemService.getMenuItemListAdmin();
			} else {
				LOGGER.debug("Inside MenuItemListCustomer get");
				menuItemList = menuItemService.getMenuItemListCustomer();
			}
		} else {
			LOGGER.debug("Inside MenuItemListCustomer get");
			menuItemList = menuItemService.getMenuItemListCustomer();
		}
		return menuItemList;
	}

	@GetMapping("/{id}")
	public MenuItem getMenuItem(@PathVariable("id") long id) {
		return menuItemService.getMenuItem(id);
	}

	@PutMapping
	public void modifyMenuItem(@RequestBody MenuItem menuItem) {
		System.out.println("fdsfjs");
		menuItemService.modifyMenuItem(menuItem);
	}
}
