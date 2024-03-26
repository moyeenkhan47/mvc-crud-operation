package productcrudapp.user;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import productcrudapp.model.Product;
@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private HibernateTemplate hibernateTemplate;

   
	public int createUser(User user) {
		return this.userDao.saveUser(user);
		
	}

	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
	    if (this.hibernateTemplate == null) {
	        // Handle the situation where hibernateTemplate is null
	        return null;
	    }

	    List<User> users = (List<User>) this.hibernateTemplate.findByCriteria(
	        DetachedCriteria.forClass(User.class)
	            .add(Restrictions.eq("username", username))
	    );

	    return users.isEmpty() ? null : users.get(0);
	}
}
