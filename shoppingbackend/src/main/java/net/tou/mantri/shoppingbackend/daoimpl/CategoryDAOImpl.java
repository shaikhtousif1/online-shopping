package net.tou.mantri.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.tou.mantri.shoppingbackend.dao.CategoryDAO;
import net.tou.mantri.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();

	static {

		Category category = new Category();
		// adding 1st category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is a TV");
		category.setImageURL("CAT_1.png");
		// category.setActive();
		categories.add(category);

		// adding 2nd category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is a Mobile");
		category.setImageURL("CAT_2.png");
		// category.setActive();
		categories.add(category);

		// adding 3rd category
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is a Laptop");
		category.setImageURL("CAT_3.png");
		// category.setActive();
		categories.add(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		//enhance for loop
		for(Category category:categories) {
			
			if(category.getId()==id)return category;
		}
		return null;
	}

}
