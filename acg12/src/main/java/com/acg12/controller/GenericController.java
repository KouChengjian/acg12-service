//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//                佛祖保佑                  永不宕机
//                心外无法                  法外无心
package com.acg12.controller;

import com.acg12.support.DateEditor;
import com.acg12.support.Message;
import com.acg12.template.directive.FlashMessageDirective;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.web.SpringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Controlelr - GENERIC
 *
 * @author Loippi Team
 * @version 1.0
 */
public class GenericController {
//    /**
//     * 错误视图
//     */
//    protected static final String ERROR_VIEW = "/admin/common/error";
//
//    /**
//     * 错误消息
//     */
//    protected static final Message ERROR_MESSAGE = Message.error("admin.message.error");
//
//    /**
//     * 成功消息
//     */
//    protected static final Message SUCCESS_MESSAGE = Message.success("admin.message.success");
//
////	@Resource
////	private JedisCache jedisCache;
//
//    public static final Integer QCLOUD_USER_SIG_REDIS_CACHE_SECONDS = 3600 * 24 * 170;
//
//    /**
//     * 获取国际化消息
//     *
//     * @param code 代码
//     * @param args 参数
//     * @return 国际化消息
//     */
//    protected String message(String code, Object... args) {
//        return SpringUtils.getMessage(code, args);
//    }
//
//
//    /**
//     * 数据绑定
//     *
//     * @param binder WebDataBinder
//     */
////    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false)); //空字符串不转换为null
//        binder.registerCustomEditor(Date.class, new DateEditor(true));
//    }

    /**
     * 添加瞬时消息
     *
     * @param redirectAttributes RedirectAttributes
     * @param message            消息
     */
    protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
        if (redirectAttributes != null && message != null) {
            redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
        }
    }

//    protected void processQueryConditions(Pageable pageable, HttpServletRequest request) {
//        Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
//        pageable.setParameter(paramter);
//    }


    /**
     * 获取IM,直播 adminKey
     */

//	public String getRedisQcloudAdminSig(String userAccount) {
//		JedisStringCache jedisStringCache = jedisCache.getJedisStringCache("QcloudAdminSig="+userAccount);
//		if (jedisStringCache == null||jedisStringCache.get()==null) {
//			String sign = SignatureUtils.sign(userAccount);
//			jedisStringCache = jedisCache.getJedisStringCache("QcloudAdminSig="+userAccount);
//			jedisStringCache.set(sign, this.QCLOUD_USER_SIG_REDIS_CACHE_SECONDS);
//			return sign;
//		}else{
//			return jedisStringCache.get();
//		}
//	}

}
