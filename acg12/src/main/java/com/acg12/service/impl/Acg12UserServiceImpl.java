package com.acg12.service.impl;

import com.acg12.dao.Acg12UserDao;
import com.acg12.entity.po.Acg12UserEntity;
import com.acg12.service.Acg12UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE - Acg12UserEntity(用户表)
 *
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12UserServiceImpl extends GenericServiceImpl<Acg12UserEntity, Long> implements Acg12UserService {

    @Autowired
    private Acg12UserDao acg12UserDao;

    @Autowired
    public void setGenericDao() {
        super.setGenericDao(acg12UserDao);
    }

    public List<Acg12UserEntity> findListByPage(Object parameter) {
        return acg12UserDao.findListByPage(parameter);
    }

    public List<Acg12UserEntity> findListNewByPage(Object parameter) {
        return acg12UserDao.findListNewByPage(parameter);
    }

    public Long deletes(Object parameter) {
        return acg12UserDao.deletes(parameter);
    }

    @Override
    public Acg12UserEntity findUserByPhone(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", phone);
        map.put("password", password);
        List<Acg12UserEntity> list = acg12UserDao.findByParams(map);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Acg12UserEntity findUserByEmail(String email, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        List<Acg12UserEntity> list = acg12UserDao.findByParams(map);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }


}
