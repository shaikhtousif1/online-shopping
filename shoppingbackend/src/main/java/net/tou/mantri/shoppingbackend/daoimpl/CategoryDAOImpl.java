package net.tou.mantri.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tou.mantri.shoppingbackend.dao.CategoryDAO;
import net.tou.mantri.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private static List<Category> categories = new ArrayList<>();

	@Override
	public List<Category> list() {
		
		String selectActiveCategory="FROM Category WHERE active = :active";
		
		Query query =sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		
		
		return query.getResultList();
	}

	/*
	 * gettung single category based on ID
	 */
	@Override
	public Category get(int id) {
		// enhance for loop
		/*
		 * for(Category category:categories) {
		 * 
		 * if(category.getId()==id)return category; }
		 */
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Category category) {
		try {

			// add the category to DB
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}

		catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	
	//This methid is for updating the category
	@Override
	public boolean update(Category category) {
		try {

			// add the category to DB
			sessionFactory.getCurrentSession().update(category);
			return true;
		}

		catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		try {

			category.setActive(false);
			// add the category to DB
			sessionFactory.getCurrentSession().update(category);
			return true;
		}

		catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

}
