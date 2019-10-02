package net.tou.mantri.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.tou.mantri.onlineshopping.model.UserModel;
import net.tou.mantri.shoppingbackend.dao.UserDAO;
import net.tou.mantri.shoppingbackend.dto.User;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		
		if(session.getAttribute("userModel")==null) {
			//add the user model
			
			Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
			User user =userDAO.getByEmail(authentication.getName());
			if(user!=null) {
				
				//create a new user model object to pass the user object
				userModel=new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullname(user.getFirstName()+" "+user.getLastName());
				
				if(userModel.getRole().equals("USER")) {
					//set the cart only if it is a buyer
					userModel.setCart(user.getCart());
				}
				//set the usermodel in session
				session.setAttribute("userModel", userModel);
				return userModel;
				
			}
		}
		return(UserModel)session.getAttribute("userModel");
	}

}
