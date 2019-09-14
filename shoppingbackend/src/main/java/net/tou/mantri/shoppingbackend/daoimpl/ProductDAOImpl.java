package net.tou.mantri.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tou.mantri.shoppingbackend.dao.ProductDAO;
import net.tou.mantri.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private static List<Product> categories = new ArrayList<>();

	@Override
	public Product get(int productid) {
		return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productid));
	}

	@Override
	public List<Product> list() {
		String selectActiveproduct = "FROM Product";

		return sessionFactory.getCurrentSession().createQuery(selectActiveproduct, Product.class).getResultList();

	}

	@Override
	public boolean add(Product product) {
		try {

			// add the product to DB
			sessionFactory.getCurrentSession().persist(product);
			return true;
		}

		catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Product product) {
		try {

			// add the product to DB
			sessionFactory.getCurrentSession().update(product);
			return true;
		}

		catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		try {

			product.setActive(false);
			// add the product to DB
			sessionFactory.getCurrentSession().update(product);
			return true;
		}

		catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProduct = "FROM Product WHERE active = :active";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProduct);

		query.setParameter("active", true);

		return query.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryid) {
		String selectActiveProductByCategory = "FROM Product WHERE active = :active AND categoryId= :categoryId";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProductByCategory);

		query.setParameter("active", true);
		query.setParameter("categoryId", categoryid);

		return query.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectActiveProductByCategory = "FROM Product WHERE active = :active ORDER BY id";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProductByCategory,Product.class);

		query.setParameter("active", true).setFirstResult(0).setMaxResults(count);
		

		return query.getResultList();
	}

}
