package org.onlineSubmit.service;

import org.onlineSubmit.entity.Manuscript;

import org.onlineSubmit.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public User findByUserName(String username);
	public User findByAuthorName(String author_name);
	public int add(User user);
}
