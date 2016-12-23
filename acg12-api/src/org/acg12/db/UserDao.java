package org.acg12.db;

import org.acg12.bean.User;

public interface UserDao {

	int insert(User user);

	int update(User user);

	int delete(User user);

	User find(int id);

	User find(String username);
}
