package net.tou.mantri.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.tou.mantri.onlineshopping.exception.ProductNotFoundException;
import net.tou.mantri.shoppingbackend.dao.CategoryDAO;
import net.tou.mantri.shoppingbackend.dao.ProductDAO;
import net.tou.mantri.shoppingbackend.dto.Category;
import net.tou.mantri.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger= LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("page");
		//mv.addObject("greeting", "Welcome to SPr");
		mv.addObject("title", "Home");
		
		logger.info("Inside PageController index method -Info");
		logger.debug("Inside PageController index method - DEBUG");
		
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickHome", true);
		return mv;
	}
	
	@RequestMapping(value = { "/about" })
	public ModelAndView about() {

		ModelAndView mv = new ModelAndView("page");
		//mv.addObject("greeting", "Welcome to SPr");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {

		ModelAndView mv = new ModelAndView("page");
		//mv.addObject("greeting", "Welcome to SPr");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
//Methods to load all the products
	

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProduct() {

		ModelAndView mv = new ModelAndView("page");
		//mv.addObject("greeting", "Welcome to SPr");
		mv.addObject("title", "All Products");
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProduct(@PathVariable("id")int id) {

		ModelAndView mv = new ModelAndView("page");
		
		//category Dao to fetch a single category
		Category category=null;
		category=categoryDAO.get(id);
		mv.addObject("title", category.getName());
		mv.addObject("categories",categoryDAO.list());
		//passing the single category object
		mv.addObject("category",category);
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	/*
	to view product in single view
	*/
	
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProductPage(@PathVariable("id")int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");
		Product product =productDAO.get(id);
		
		if(product==null) throw new ProductNotFoundException();
		
		//update the view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct",true);
		
		return mv;
	}
}
