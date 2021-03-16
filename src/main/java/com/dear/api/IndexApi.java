package com.dear.api;

import com.dear.api.community.service.ICommunityService;
import com.dear.common.bean.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexApi {

    @Autowired
    private ICommunityService service;

    @RequestMapping({"/","index.html"})
    public ModelAndView index(Model model){

        ResultJson communityList = this.service.getCommunityList(1, 30);

        model.addAttribute("communityList", communityList);
        model.addAttribute("title", "包子");

        return new ModelAndView("index");
    }
}
