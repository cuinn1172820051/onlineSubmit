package org.onlineSubmit.dao;
import org.onlineSubmit.entity.Manuscript;
import org.onlineSubmit.entity.User;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao {
	public User findByUserName(String username);
	public int add(User user);
	public User findByAuthorName(String author_name);
}
