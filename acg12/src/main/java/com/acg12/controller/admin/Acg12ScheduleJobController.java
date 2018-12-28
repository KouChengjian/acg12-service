package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12ScheduleJobEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12ScheduleJobLogService;
import com.acg12.service.Acg12ScheduleJobService;
import com.acg12.service.SystemUserService;
import com.acg12.support.Message;
import com.acg12.utils.StringUtil;
import com.acg12.utils.result.Result;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.doc.GSONUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller - scheduleJob
 *
 * @author kcj
 * @version 2.0
 */
@Controller("acg12ScheduleJobController")
@RequestMapping({"/admin/schedule_job"})
public class Acg12ScheduleJobController extends GenericController {

    @Resource
    private SystemUserService userService;
    @Resource
    private Acg12ScheduleJobService acg12ScheduleJobService;
    @Resource
    private Acg12ScheduleJobLogService acg12ScheduleJobLogService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        return "/admin/schedule_job/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12ScheduleJobEntity acg12ScheduleJob, RedirectAttributes redirectAttributes) {
        SystemUserEntity user = userService.getCurrent();
        System.out.println(acg12ScheduleJob.toString());
        acg12ScheduleJob.setJobStatus("0");
        acg12ScheduleJob.setCreateTime(new Date());
        acg12ScheduleJob.setUpdateTime(new Date());
        acg12ScheduleJobService.save(acg12ScheduleJob);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12ScheduleJobEntity acg12ScheduleJobEntity = acg12ScheduleJobService.find(id);
        model.addAttribute("acg12ScheduleJobEntity", acg12ScheduleJobEntity);
        return "/admin/schedule_job/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12ScheduleJobEntity acg12ScheduleJob = acg12ScheduleJobService.find(id);
        model.addAttribute("acg12ScheduleJob", acg12ScheduleJob);
        return "/admin/schedule_job/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
    public String update(Acg12ScheduleJobEntity acg12ScheduleJobEntity, RedirectAttributes redirectAttributes) {
        acg12ScheduleJobService.update(acg12ScheduleJobEntity);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 列表查询
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequiresPermissions("admin:system:job:list")
    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {


        return "/admin/schedule_job/list";
    }

    // 新列表查询
    @RequestMapping(value = "/listNew.json", method = RequestMethod.GET)
    public @ResponseBody
    String listNew(HttpServletRequest request, Integer pageNumber, Integer pageSize,
                   ModelMap model) {

        Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
        Map map = new HashMap();
        for (String key : paramter.keySet()) {
            if (!StringUtil.isEmpty(paramter.get(key).toString())) {
                map.put(key, paramter.get(key));
            }
        }
        String filter_ids = request.getParameter("filter_ids");
        String filter_ide = request.getParameter("filter_ide");
        if (!StringUtil.isEmpty(filter_ids)) {
            map.put("filter_ids", filter_ids.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_ide)) {
            map.put("filter_ide", filter_ide.replace("-", ""));
        }
        String filter_createTimes = request.getParameter("filter_createTimes");
        String filter_createTimee = request.getParameter("filter_createTimee");
        if (!StringUtil.isEmpty(filter_createTimes)) {
            map.put("filter_createTimes", filter_createTimes.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_createTimee)) {
            map.put("filter_createTimee", filter_createTimee.replace("-", ""));
        }
        String filter_updateTimes = request.getParameter("filter_updateTimes");
        String filter_updateTimee = request.getParameter("filter_updateTimee");
        if (!StringUtil.isEmpty(filter_updateTimes)) {
            map.put("filter_updateTimes", filter_updateTimes.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_updateTimee)) {
            map.put("filter_updateTimee", filter_updateTimee.replace("-", ""));
        }

        Long total = acg12ScheduleJobService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map = this.dateAndOrderMap(map, request);

        List<Acg12ScheduleJobEntity> acg12ScheduleJobs = acg12ScheduleJobService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12ScheduleJobs);
        String str = GSONUtils.valueToString(returnMap);
        return str;

    }


    private Map dateAndOrderMap(Map map, HttpServletRequest request) {
        String sortOrder = request.getParameter("sortOrder");
        String sortName = request.getParameter("sortName");
        if (StringUtil.isEmpty(sortName)) {
            map.put("order", sortOrder.replace("+", " "));
        } else {
            map.put("order", sortName + "  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("id")) {
            map.put("order", " id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("jobName")) {
            map.put("order", " job_name  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("jobGroup")) {
            map.put("order", " job_group  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("jobStatus")) {
            map.put("order", " job_status  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("cronExpression")) {
            map.put("order", " cron_expression  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("description")) {
            map.put("order", " description  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("beanClass")) {
            map.put("order", " bean_class  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("isConcurrent")) {
            map.put("order", " is_concurrent  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("methodName")) {
            map.put("order", " method_name  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("springId")) {
            map.put("order", " spring_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("createTime")) {
            map.put("order", " create_time  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("updateTime")) {
            map.put("order", " update_time  " + sortOrder);
        }
        return map;
    }

    /**
     * 删除操作
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    Message delete(Long[] ids) {
        for (Long long1 : ids) {
            this.acg12ScheduleJobService.delete(long1);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping(value = {"/deletes"}, method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    Message deletes(HttpServletRequest request) {
        String idsStrings = request.getParameter("ids");
        idsStrings = StringUtil.delChar(idsStrings, ",");
        if (!StringUtil.isEmpty(idsStrings)) {
            String[] idss = idsStrings.split(",");
            for (int i = 0; i < idss.length; i++) {
                this.acg12ScheduleJobService.delete(Long.parseLong(idss[i]));
            }
        }
        return SUCCESS_MESSAGE;
    }


    /**
     * 修改显示隐藏列表
     *
     * @return
     */
    @RequestMapping(value = {"/updateListColumns"}, method = {RequestMethod.GET})
    public @ResponseBody
    Message updateListColumns(HttpServletRequest request, ModelMap model) {
        return SUCCESS_MESSAGE;
    }

    /**
     * 修改显示隐藏搜索字段
     *
     * @return
     */
    @RequestMapping(value = {"/updateColumnSearchLists"}, method = {RequestMethod.GET})
    public @ResponseBody
    Message updateColumnSearchLists(HttpServletRequest request, ModelMap model) {
        return SUCCESS_MESSAGE;
    }

    /**
     * 修改显示隐藏搜索字段
     *
     * @return
     */
    @RequestMapping(value = {"/changeJobStatus"}, method = {RequestMethod.POST})
    @ResponseBody
    public Result changeJobStatus(HttpServletRequest request, Long jobId, String cmd, RedirectAttributes redirectAttributes, ModelMap model) throws SchedulerException {
        acg12ScheduleJobService.changeStatus(jobId, cmd);
        return Result.ok();
    }
}
