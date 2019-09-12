package net.tou.mantri.shoppingbackend.dao;

import java.util.List;

import net.tou.mantri.shoppingbackend.dto.Category;

public interface CategoryDAO {

	
	List<Category> list();
	Category get(int id);
}
