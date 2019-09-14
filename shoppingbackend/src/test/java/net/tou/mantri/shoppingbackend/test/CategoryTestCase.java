package net.tou.mantri.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.tou.mantri.shoppingbackend.dao.CategoryDAO;
import net.tou.mantri.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.tou.mantri.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/*
	 * @Test
	 * 
	 * public void testAddCategory() {
	 * 
	 * 
	 * category = new Category();
	 * 
	 * category.setName("Mobile"); category.setDescription("This is a Mobile");
	 * category.setImageURL("CAT_107.png"); // category.setActive();
	 * assertEquals("Successfully added a category in the table",true,categoryDAO.
	 * add(category)); }
	 * 
	 */
	/*
	 * @Test
	 * 
	 * public void testGetCategory() {
	 * 
	 * category = categoryDAO.get(1);
	 * 
	 * assertEquals("Successfully fetched from table","Television",category.getName(
	 * )); }
	 */
	/*
	 * @Test
	 * 
	 * public void testUpdateCategory() {
	 * 
	 * category = categoryDAO.get(1);
	 * 
	 * category.setName("TV");
	 * 
	 * assertEquals("Successfully updated in the table",
	 * true,categoryDAO.update(category)); }
	 */

	/*
	 * @Test
	 * 
	 * public void testDeleteCategory() {
	 * 
	 * category = categoryDAO.get(1);
	 * 
	 * 
	 * assertEquals("Successfully updated in the table",
	 * true,categoryDAO.delete(category)); }
	 */

	/*
	 * @Test
	 * 
	 * public void testListCategory() {
	 * 
	 * assertEquals("Successfully fetched list of categories from the table",2,
	 * categoryDAO.list().size()); }
	 */

	@Test

	public void testCRUDOfCategory() {

		category = new Category();

		category.setName("Mobile");
		category.setDescription("This is a Mobile");
		category.setImageURL("CAT_1.png"); 
		assertEquals("Successfully added a category in the table", true, categoryDAO.add(category));

		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is a Televison");
		category.setImageURL("CAT_2.png"); 
		assertEquals("Successfully added a category in the table", true, categoryDAO.add(category));

		// fetching and updating
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Successfully updated in the table", true, categoryDAO.update(category));

		//deactivate the category
		
		assertEquals("Successfully updated in the table", true, categoryDAO.delete(category));
		
		//list
		assertEquals("Successfully fetched list of categories from the table", 1, categoryDAO.list().size());
	}
}
