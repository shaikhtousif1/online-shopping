package net.tou.mantri.shoppingbackend.dao;

import java.util.List;

import net.tou.mantri.shoppingbackend.dto.Product;

public interface ProductDAO {
	Product get(int id);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	
	//bussiness method
	
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryid);
	List<Product> getLatestActiveProducts(int count);
}
