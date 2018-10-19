package com.acg12.service.impl;

import com.acg12.dao.SystemUserDao;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.GenericService;
import com.acg12.utils.StringUtil;
import com.framework.loippi.mybatis.dao.GenericDao;
import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.Paramap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service - 基类实现
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Transactional
public class GenericServiceImpl<T extends GenericEntity, PK extends Serializable> implements GenericService<T, PK> {
	
//	@Autowired
//	private SyOperateLogDao syOperateLogDao;
	
	@Autowired
	private SystemUserDao userDao;
	
	/** GenericDao */
	private GenericDao<T, PK> genericDao;

	public void setGenericDao(GenericDao<T, PK> genericDao) {
		this.genericDao = genericDao;
	}

	@Transactional
	public Long saveEntity(T param) {
//		SystemUserEntity user = getCurrent();
//		if(user != null){//如果是后天操作，则需要进行更新日志操作
//			String afterStr = GSONUtils.valueToString(param);
//			SyOperateLog syOperateLog = new SyOperateLog();
//			syOperateLog.setCreateDate(new Date());
//			syOperateLog.setOperateName(user.getNickname());
//			syOperateLog.setOperaterId(user.getId());
//			syOperateLog.setOperateType("新增操作");
//			syOperateLog.setAfterOperate(afterStr);
//			syOperateLogDao.insert(syOperateLog);
//		}
		return genericDao.insertEntity(param);
	}

	@Transactional
	public Long updateEntity(T param) {
//		User user = getCurrent();
//		if(user != null){//如果是后天操作，则需要进行更新日志操作
//			String afterStr = GSONUtils.valueToString(param);
//			JSONObject jsonObject = JSONObject.parseObject(afterStr);
//			Long id = jsonObject.getLong("id");
//			T beforeUpdateData = genericDao.find((PK)id);
//			String beforeStr = GSONUtils.valueToString(beforeUpdateData);
//			System.out.println("++++++++++++++"+beforeStr);
//			SyOperateLog syOperateLog = new SyOperateLog();
//			syOperateLog.setBeforOperate(beforeStr);
//			syOperateLog.setCreateDate(new Date());
//			syOperateLog.setOperateName(user.getNickname());
//			syOperateLog.setOperaterId(user.getId());
//			syOperateLog.setOperateType("更新操作");
//			syOperateLog.setAfterOperate(afterStr);
//			syOperateLogDao.insert(syOperateLog);
//		}
		return genericDao.updateEntity(param);
	}

	@Transactional
	public Long deleteEntity(T param) {
//		User user = getCurrent();
//		if(user != null){//如果是后天操作，则需要进行更新日志操作
//			String afterStr = GSONUtils.valueToString(param);
//			JSONObject jsonObject = JSONObject.parseObject(afterStr);
//			Long id = jsonObject.getLong("id");
//			T beforeUpdateData = genericDao.find((PK)id);
//			String beforeStr = GSONUtils.valueToString(beforeUpdateData);
//			SyOperateLog syOperateLog = new SyOperateLog();
//			syOperateLog.setBeforOperate(beforeStr);
//			syOperateLog.setCreateDate(new Date());
//			syOperateLog.setOperateName(user.getNickname());
//			syOperateLog.setOperaterId(user.getId());
//			syOperateLog.setOperateType("删除操作");
//			syOperateLogDao.insert(syOperateLog);
//		}
		return genericDao.deleteEntity(param);
	}

	@Transactional
	public Long save(T param) {
//		User user = getCurrent();
//		if(user != null){//如果是后天操作，则需要进行更新日志操作
//			String afterStr = GSONUtils.valueToString(param);
//			SyOperateLog syOperateLog = new SyOperateLog();
//			syOperateLog.setCreateDate(new Date());
//			syOperateLog.setOperateName(user.getNickname());
//			syOperateLog.setOperaterId(user.getId());
//			syOperateLog.setOperateType("新增操作");
//			syOperateLog.setAfterOperate(afterStr);
//			syOperateLogDao.insert(syOperateLog);
//		}
		return genericDao.insert(param);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Long update(T param) {
//		User user = getCurrent();
//		if(user != null){//如果是后天操作，则需要进行更新日志操作
//			String afterStr = GSONUtils.valueToString(param);
//			JSONObject jsonObject = JSONObject.parseObject(afterStr);
//			Long id = jsonObject.getLong("id");
//			T beforeUpdateData = genericDao.find((PK)id);
//			String beforeStr = GSONUtils.valueToString(beforeUpdateData);
//			System.out.println("++++++++++++++"+beforeStr);
//			SyOperateLog syOperateLog = new SyOperateLog();
//			syOperateLog.setBeforOperate(beforeStr);
//			syOperateLog.setCreateDate(new Date());
//			syOperateLog.setOperateName(user.getNickname());
//			syOperateLog.setOperaterId(user.getId());
//			syOperateLog.setOperateType("更新操作");
//			syOperateLog.setAfterOperate(afterStr);
//			syOperateLogDao.insert(syOperateLog);
//		}
		
		return genericDao.update(param);
	}

	@Transactional
	public Long delete(PK pk) {
//		User user = getCurrent();
//		if(user != null){//如果是后天操作，则需要进行更新日志操作
//			T beforeUpdateData = genericDao.find(pk);
//			String beforeStr = GSONUtils.valueToString(beforeUpdateData);
//			SyOperateLog syOperateLog = new SyOperateLog();
//			syOperateLog.setBeforOperate(beforeStr);
//			syOperateLog.setCreateDate(new Date());
//			syOperateLog.setOperateName(user.getNickname());
//			syOperateLog.setOperaterId(user.getId());
//			syOperateLog.setOperateType("删除操作");
//			syOperateLogDao.insert(syOperateLog);
//		}
		return genericDao.delete(pk);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Long deleteAll(Long ... ids) {
//		User user = getCurrent();
//		if(user != null){//如果是后天操作，则需要进行更新日志操作
//			for (Long long1 : ids) {
//				T beforeUpdateData = genericDao.find((PK)long1);
//				String beforeStr = GSONUtils.valueToString(beforeUpdateData);
//				SyOperateLog syOperateLog = new SyOperateLog();
//				syOperateLog.setBeforOperate(beforeStr);
//				syOperateLog.setCreateDate(new Date());
//				syOperateLog.setOperateName(user.getNickname());
//				syOperateLog.setOperaterId(user.getId());
//				syOperateLog.setOperateType("删除操作");
//				syOperateLogDao.insert(syOperateLog);
//			}
//
//		}
		return genericDao.deleteAll(Paramap.create().put("ids", ids));
	}

	@Transactional(readOnly = true)
	public Long count() {
		return genericDao.count();
	}

	@Transactional(readOnly = true)
	public Long count(Map<String, Object> params) {
		return genericDao.count(params);
	}

	@Transactional(readOnly = true)
	public T find(PK pk) {
		return genericDao.find(pk);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return genericDao.findAll();
	}


	@Transactional(readOnly = true)
	public Page<T> findByPage(Pageable pageable) {

	    Map map=	(Map<String, Object>) pageable.getParameter();
	    
	    if(!StringUtil.isEmpty(pageable.getOrderProperty())){
	    map.put("order", pageable.getOrderProperty()+" "+pageable.getOrderDirection());
	    }
	    map.put("pageNumber", (pageable.getPageNumber() - 1) * pageable.getPageSize());
		map.put("pageSize", pageable.getPageSize());
	    
		
		List<T> result =  genericDao.findByParams(map);
		long TotalCount=genericDao.count((Map<String, Object>) pageable.getParameter());
		return new Page<T>(result, TotalCount, pageable);
	}

	@Transactional(readOnly = true)
	public T find(String propertyName, Object value) {
		List<T> results = genericDao.findByParams(Paramap.create().put(propertyName, value));
		if(CollectionUtils.isNotEmpty(results)){
			return results.get(0);
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<T> findList(String propertyName, Object value) {
		List<T> results = genericDao.findByParams(Paramap.create().put(propertyName, value));
		return results;
	}

	@Transactional(readOnly = true)
	public List<T> findList(Map<String, Object> params) {
		return genericDao.findByParams(params);
	}
	
//	public SystemUserEntity getCurrent() {
//		Subject subject = SecurityUtils.getSubject();
//		if (subject != null) {
//			Principal principal = (Principal) subject.getPrincipal();
//			if (principal != null && principal.getId() != null) {
//				return userDao.find(principal.getId());
//			}
//		}
//		return null;
//	}

}
