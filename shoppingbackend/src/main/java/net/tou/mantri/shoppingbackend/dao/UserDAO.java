package net.tou.mantri.shoppingbackend.dao;

import java.util.List;

import net.tou.mantri.shoppingbackend.dto.Address;
import net.tou.mantri.shoppingbackend.dto.Cart;
import net.tou.mantri.shoppingbackend.dto.User;

public interface UserDAO {

	// user related operation
	User getByEmail(String email);
	User get(int id);

	boolean add(User user);
	boolean updateCart(Cart cart);
	
	// adding and updating a new address
	Address getAddress(int addressId);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	Address getBillingAddress(int userId);
	List<Address> listShippingAddresses(int userId);
	

	
}
