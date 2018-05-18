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
    @RequestMapping(value = "/view/animat")
    public ModelAndView homeAnimat() {
        return new ModelAndView("/client/home-animat.html");
    }

    @ResponseBody
    @RequestMapping(value = "/view/caricature")
    public ModelAndView homeCaricature() {
        return new ModelAndView("/client/home-caricature.html");
    }

    @ResponseBody
    @RequestMapping(value = "/view/game")
    public ModelAndView homeGame() {
        return new ModelAndView("/client/home-game.html");
    }

    @ResponseBody
    @RequestMapping(value = "/view/novel")
    public ModelAndView homeNovel() {
        return new ModelAndView("/client/home-novel.html");
    }

    @ResponseBody
    @RequestMapping(value = "/view/person")
    public ModelAndView homePerson() {
        return new ModelAndView("/client/home-person.html");
    }

    @ResponseBody
    @RequestMapping(value = "/view/character")
    public ModelAndView homeCharacter() {
        return new ModelAndView("/client/home-character.html");
    }

    @ResponseBody
    @RequestMapping(value = "/view/person/{personId}")
    public ModelAndView homePersonInfo(@PathVariable String personId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/jsp/person/home-person-info.jsp");
        modelAndView.addObject("personId" ,personId);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/view/character/{characterId}")
    public ModelAndView homeCharacterInfo(@PathVariable String characterId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/jsp/character/home-character-info.jsp");
        modelAndView.addObject("characterId" ,characterId);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/view/subject/{sId}")
    public ModelAndView homeSubjectInfo(@PathVariable String sId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/jsp/subject/home-subject-info.jsp");
        modelAndView.addObject("sId" ,sId);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/view/subject/{sId}/images")
    public ModelAndView homeSubjectImage(@PathVariable String sId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/jsp/subject/home-subject-image.jsp");
        modelAndView.addObject("sId" ,sId);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/view/subject/{sId}/boards")
    public ModelAndView homeSubjectBoards(@PathVariable String sId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/jsp/subject/home-subject-image.jsp");
        modelAndView.addObject("sId" ,sId);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/view/search")
    public ModelAndView search() {
        return new ModelAndView("/client/search.html");
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
