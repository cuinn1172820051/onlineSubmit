package org.onlineSubmit.service.impl;
import org.onlineSubmit.dao.UserDao;
import org.onlineSubmit.entity.User;
import org.onlineSubmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}
	public int add(User user) {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}
	public User findByAuthorName(String author_name) {
		// TODO Auto-generated method stub
		return userDao.findByAuthorName(author_name);
	}
}
