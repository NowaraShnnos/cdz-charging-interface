package com.pengniao.cdzcharginginterface.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassNameTaskRoute
 * @Description
 * @Author
 * @Date2020/9/17 14:23
 * @Version V1.0
 **/

@Controller
public class TaskRoute {

    @RequestMapping({"/cdz-tasks","index.html","index"})
    public String index(){
        return "index";
    }


    @RequestMapping({"/schedule-add-or-update"})
    public ModelAndView skipAddOrUpdate(Long jobId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedule-add-or-update");
        modelAndView.addObject("id",jobId.toString());
        return modelAndView;
    }

    @RequestMapping({"/schedule-log"})
    public ModelAndView skipScheduleLog(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedule-log");
        return modelAndView;
    }



}