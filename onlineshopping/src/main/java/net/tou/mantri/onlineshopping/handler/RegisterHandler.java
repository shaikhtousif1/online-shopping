package net.tou.mantri.onlineshopping.handler;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.tou.mantri.onlineshopping.model.RegisterModel;
import net.tou.mantri.shoppingbackend.dao.UserDAO;
import net.tou.mantri.shoppingbackend.dto.Address;
import net.tou.mantri.shoppingbackend.dto.Cart;
import net.tou.mantri.shoppingbackend.dto.User;

@Component
public class RegisterHandler {
	
@Autowired
private UserDAO userDAO;

@Autowired
private BCryptPasswordEncoder passwordencoder;

	public RegisterModel init() {
		
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel,User user) {
		
		registerModel.setUser(user);
	}
	public void addBilling(RegisterModel registerModel,Address billing) {
		
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model) {
		String transitionValue="success";
		
		//fetch user
		
		User user=model.getUser();
		if(user.getRole().equals("USER")) {
			
			Cart cart= new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//encode the password
		
		user.setPassword(passwordencoder.encode(user.getPassword()));
		
		//save the user
		userDAO.add(user);
		
		//get the address
		
		Address billing=model.getBilling();
		
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		//set the address
		userDAO.addAddress(billing);
		
		
		return transitionValue;
	}

	
	public String validateUser(User user,MessageContext error) {
		String transitionValue="success";
		
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match the confirm password !").build());
			transitionValue="failure";
			
		}
		
		if(userDAO.getByEmail(user.getEmail())!=null) {
			error.addMessage(new MessageBuilder().error().source("email")
					.defaultText("Email address already exits!").build());
			transitionValue="failure";
			
			
			
		}
		
		return transitionValue;
	}
}
