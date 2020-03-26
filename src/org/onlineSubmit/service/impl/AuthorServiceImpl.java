package org.onlineSubmit.service.impl;
import java.util.List;
import java.util.Map;

import org.onlineSubmit.dao.AuthorDao;
import org.onlineSubmit.dao.UserDao;
import org.onlineSubmit.entity.Manuscript;
import org.onlineSubmit.entity.User;
import org.onlineSubmit.service.AuthorService;
import org.onlineSubmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	private AuthorDao authorDao;
	public int add(Manuscript manuscript) {
		// TODO Auto-generated method stub
		return authorDao.add(manuscript);
	}
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return authorDao.getTotal(queryMap);
	}
	public User queryOwnInfo(int id) {
		// TODO Auto-generated method stub
		return authorDao.queryOwnInfo(id);
	}
	public int editOwnInfo(User user) {
		// TODO Auto-generated method stub
		return authorDao.editOwnInfo(user);
	}
	public List<Manuscript> findListById(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return authorDao.findListById(queryMap);
	}
}
