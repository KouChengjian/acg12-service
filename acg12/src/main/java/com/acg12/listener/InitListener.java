package com.acg12.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.logging.Logger;

/**
 * Listener - 初始化
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent> {


	/** logger */
	private static final Logger logger = Logger.getLogger(InitListener.class.getName());

	/** servletContext */
	private ServletContext servletContext;

	@Value("${system.version}")
	private String systemVersion;
	
//	@Resource(name = "cacheServiceImpl")
//	private CacheService cacheService;
//	@Resource
//	private StaticService staticService;

	

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
		if (servletContext != null && contextRefreshedEvent.getApplicationContext().getParent() == null) {
			String info = "Initializing shangyizhijia!" + systemVersion;
			logger.info(info);
//			//清除缓存
//			cacheService.clear();
//			//生成后台JS文件
//			staticService.buildOther();
		}
	}
}