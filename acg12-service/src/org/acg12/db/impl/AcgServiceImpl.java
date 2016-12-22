package org.acg12.db.impl;

import org.acg12.bean.User;
import org.acg12.db.UserDao;
import org.acg12.utils.DaoFactory;

public class AcgServiceImpl implements AcgService{

	private UserDao userDao = DaoFactory.getInstance().createDao("org.acg12.impl.UserDaoImpl", UserDao.class);
	
	@Override
	public User login(User user) {
		return userDao.find(user .getUsername());
	}

}
