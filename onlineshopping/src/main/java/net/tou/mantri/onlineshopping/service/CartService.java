package net.tou.mantri.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tou.mantri.onlineshopping.model.UserModel;
import net.tou.mantri.shoppingbackend.dao.CartLineDAO;
import net.tou.mantri.shoppingbackend.dao.ProductDAO;
import net.tou.mantri.shoppingbackend.dto.Cart;
import net.tou.mantri.shoppingbackend.dto.CartLine;
import net.tou.mantri.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private HttpSession session;

	// returns the cart
	private Cart getCart() {

		return ((UserModel) session.getAttribute("userModel")).getCart();
	}
	
	//return the entire cart line
	public List<CartLine> getCartLines() {
		
		return cartLineDAO.list(this.getCart().getId());
	}

	public String manageCartLine(int cartLineId, int count) {
		// fetch the cartline
		
		CartLine cartLine=cartLineDAO.get(cartLineId);
		if(cartLine==null) {
			return "result=error";
		}
		
		else {
			
			Product product	=cartLine.getProduct();
			double oldTotal=cartLine.getTotal();
			
			//check if product is available
			if(product.getQuantity()<=count) {
				return"result=unavailable";
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			cartLineDAO.update(cartLine);
			
			Cart cart =this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-oldTotal+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
		
	}

	public String deleteCartLine(int cartLineId) {
		// fetch the cart line
		CartLine cartLine=cartLineDAO.get(cartLineId);
		if(cartLine==null) {
			return "result=error";
		}
		
		else {
			
			//update the cart
			Cart cart=this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-cartLine.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			
			cartLineDAO.updateCart(cart);
			//remove the cart line
			
			cartLineDAO.remove(cartLine);
			
			return "result=deleted";
		}
		
		
		
		
	}

	public String addCartLine(int productId) {
		// TODO Auto-generated method stub
		
		String response=null;
		
		Cart cart=this.getCart();
		CartLine cartLine=cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine ==null) {
			//add a new cartLine
			cartLine =new CartLine();
			
			//fetch the product
			Product product=productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response= "result=added";
		}
		else {
			
			//checking if cartline has reached the maximum count(3)
			if(cartLine.getProductCount()<3) {
				//update the productcount for that cartline
				response=this.manageCartLine(cartLine.getCartId(), cartLine.getProductCount()+1);
			}
			else {
				response= "result=maximum";
			}
		}
		return response;
	}

}
