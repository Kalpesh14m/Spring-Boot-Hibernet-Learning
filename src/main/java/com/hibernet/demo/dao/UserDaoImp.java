package com.hibernet.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernet.demo.model.User;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public User findById(Long id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUser() {
		return sessionFactory.getCurrentSession().createQuery("FROM User").list();
	}

	public User update(User val, Long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		user.setCity(val.getCity());
		user.setFirstName(val.getFirstName());
		session.update(user);
		return user;
	}

	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = findById(id);
		session.delete(user);
	}
//	    @Override
//	    public User updateCountry(User val, int id){
//	        Session session = sessionFactory.getCurrentSession();
//	        User user = (User)session.load(User.class, id);
//	        user.setCountry(val.getCountry());
//	        return user;
//	    }

}