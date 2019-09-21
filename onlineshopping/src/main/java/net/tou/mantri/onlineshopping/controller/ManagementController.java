package net.tou.mantri.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.tou.mantri.onlineshopping.util.FileUploadUtility;
import net.tou.mantri.onlineshopping.validator.ProductValidator;
import net.tou.mantri.shoppingbackend.dao.CategoryDAO;
import net.tou.mantri.shoppingbackend.dao.ProductDAO;
import net.tou.mantri.shoppingbackend.dto.Category;
import net.tou.mantri.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product nProduct = new Product();
		// set new product
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);

		if (operation != null) {

			if (operation.equals("product")) {
				mv.addObject("message", "Product Submitted Successfully!");
			}
			else if(operation.equals("category")){
				mv.addObject("message", "Category Submitted Successfully!");
			}
		}

		return mv;

	}

	// handling the product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {

		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		} else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}

		// check if there are any errors

		if (results.hasErrors()) {

			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation Failed for Product submission!");

			return "page";

		}

		logger.info(mProduct.toString());
		// create new product
		// use of hidden field
		if (mProduct.getId() == 0) {
			productDAO.add(mProduct);
		} else {
			// update the existing prodcut
			productDAO.update(mProduct);
		}
		if (!mProduct.getFile().getOriginalFilename().equals("")) {

			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}

	// returning categories for all the request mappings
	@ModelAttribute("categories")
	public List<Category> getCategories() {

		return categoryDAO.list();

	}

	@RequestMapping(value = "/products/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {

		Product product = productDAO.get(id);
		boolean isActive = product.isActive();

		product.setActive(!product.isActive());
		productDAO.update(product);
		return (isActive) ? "You have successfully deactivated the product" + product.getId()
				: "You have successfully activated the product" + product.getId();
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}

	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView showEditProduct(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		// fetch the product from DB
		Product nProduct = productDAO.get(id);

		// set the product fetch from database
		mv.addObject("product", nProduct);

		return mv;

	}

	@ModelAttribute("category")
	public Category getCategory() {

		return new Category();

	}

}
