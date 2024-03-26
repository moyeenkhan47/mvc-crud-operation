package productcrudapp.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import productcrudapp.model.Product;

@Component
public class ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	// create
	@Transactional
	public void createProduct(Product product) {

		this.hibernateTemplate.save(product);

	}

	// get all products
	public List<Product> getProducts() {
		List<Product> products = this.hibernateTemplate.loadAll(Product.class);
		return products;
	}

	// delete the single product
	@Transactional
	public void deleteProduct(int pid) {
		Product p = this.hibernateTemplate.get(Product.class, pid);
		SessionFactory sf = this.hibernateTemplate.getSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.delete(p);
		t.commit();
		s.close();
	}

	// update product
	public Product updateData(int pid,String name,String description,Double price) {
		
		SessionFactory sf = this.hibernateTemplate.getSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
try {
		Product product = this.hibernateTemplate.get(Product.class, pid);
		
		 if (product == null) {
	            throw new IllegalArgumentException("Product with ID " + pid + " not found");
	        }

		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);

		s.saveOrUpdate(product);
		System.out.println("after update");
		t.commit();
		s.close();

		return product;
	}
catch (Exception e) {
    // Handle exceptions, log the error, and roll back the transaction
    if (t != null) {
        t.rollback();
    }
    e.printStackTrace(); // Log the exception or use a logging framework
    throw new RuntimeException("Error updating product details", e);
} 

	}
	// get the single product
	public Product getProduct(int pid) {
		return this.hibernateTemplate.get(Product.class, pid);
	}
// view product
	@Transactional
	public Product viewProduct(int pid) {
		Product p = this.hibernateTemplate.get(Product.class, pid);

		return p;

	}

	// search product
	@Transactional
	public Product searchProduct(int pid) {
		Product p = this.hibernateTemplate.get(Product.class, pid);
		
		return p;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Product getProductByName(String name) {

		List<Product> products = (List<Product>) this.hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Product.class).add(Restrictions.eq("name", name)));

		return products.isEmpty() ? null : products.get(0);
	}

	@SuppressWarnings("unchecked")
	public Product getProductsByPrice(Double price) {
		List<Product> products = (List<Product>) this.hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Product.class).add(Restrictions.eq("price", price)));

		return products.isEmpty() ? null : products.get(0);
	}

	

	
}
