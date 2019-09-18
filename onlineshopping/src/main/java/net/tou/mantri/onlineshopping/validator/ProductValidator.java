package net.tou.mantri.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.tou.mantri.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;

		if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select an image file to upload !");
		}
		if (!
				product.getFile().getContentType().equals("image/jpeg")	|| 
				product.getFile().getContentType().equals("image/png") ||
				product.getFile().getContentType().equals("image/gif")
			) {
			errors.rejectValue("file", null, "Please use only image file to upload !");
		}
	}

}
