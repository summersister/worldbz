package com.dear.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 路由API
 */
@RestController
public class RouteApi {

    /**
     * 网站主页
     * @return
     */
    @RequestMapping({"/", "index.html"})
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * 商品详情页
     * @return
     */
    @RequestMapping({"/p/d"})
    public ModelAndView productDetails(){
        return new ModelAndView("productDetails");
    }

    /**
     * 后台管理主页
     * @return
     */
    @RequestMapping({"/a"})
    public ModelAndView admin(){
        return new ModelAndView("admin/admin");
    }

    /**
     * 后台管理登录
     * @return
     */
    @RequestMapping({"/a/l"})
    public ModelAndView login(){
        return new ModelAndView("admin/user/login");
    }
}
