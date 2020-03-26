package org.onlineSubmit.dao;

import java.util.List;
import java.util.Map;

import org.onlineSubmit.entity.Manuscript;
import org.onlineSubmit.entity.User;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthorDao {
	public int add(Manuscript munscript);
	public int getTotal(Map<String,Object> queryMap);
	public User queryOwnInfo(int id);
	public int editOwnInfo(User user);
	public List<Manuscript> findListById(Map<String,Object> queryMap);
}
