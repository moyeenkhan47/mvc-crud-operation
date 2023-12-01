package productcrudapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
//	@Transactional
	public void deleteProduct(int pid) {
		Product p = this.hibernateTemplate.get(Product.class, pid);
		SessionFactory sf = this.hibernateTemplate.getSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.delete(p);
		t.commit();
		s.close();
	}

	// get the single product
	public Product getProduct(int pid) {
		return this.hibernateTemplate.get(Product.class, pid);
	}
	public void updateProduct(int pid) {
		Product p = this.hibernateTemplate.get(Product.class, pid);
		SessionFactory sf = this.hibernateTemplate.getSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.update(p);
		t.commit();
		s.close();
		
	}
}