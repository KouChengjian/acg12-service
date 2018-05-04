package com.acg12.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/3/21.
 */

//@RequestMapping(value = "/view")
@Controller
public class ClientViewController {

    @ResponseBody
    @RequestMapping(value = "/view")
    public ModelAndView index(HttpServletRequest request) {
//        System.out.println(getIpAddr(request));
        return new ModelAndView("/client/index.html");
    }

    @ResponseBody
    @RequestMapping(value = "/view/person")
    public ModelAndView homePerson() {
        return new ModelAndView("/client/home-person.html");
    }

    @ResponseBody
    @RequestMapping(value = "/view/person/{pId}")
    public ModelAndView homePersonInfo(@PathVariable String pId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/jsp/subject/home-subject-info.jsp");
        modelAndView.addObject("pId" ,pId);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/view/search")
    public ModelAndView search() {
        return new ModelAndView("/client/search.html");
    }

    @ResponseBody
    @RequestMapping(value = "/subject")
    public ModelAndView subjectInfo() {
        return new ModelAndView("/client/subject-info.jsp");
    }

    @ResponseBody
    @RequestMapping(value = "/subject/{sId}")
    public ModelAndView homeSubjectInfo(@PathVariable String sId) {
        return new ModelAndView("/client/home-person-info.html?sId=" + sId);
    }

    @ResponseBody
    @RequestMapping(value = "/search1")
    public ModelAndView index1() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/client/search.jsp");
        return mav;
    }

    /**
     * @param @return
     * @return String
     * @throws
     * @Title: getIpAddr
     * @author kaka  www.zuidaima.com
     * @Description: 获取客户端IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("1 = " + ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        System.out.println("2 = " + ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        System.out.println("3 = " + ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("6 = " + ip);
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
                System.out.println("7 = " + ip);
            }
        }
        System.out.println("4 = " + ip);
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        System.out.println("5 = " + ip);
        return ip;
    }
}
