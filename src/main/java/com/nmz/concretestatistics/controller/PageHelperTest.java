package com.nmz.concretestatistics.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class PageHelperTest {
    @RequestMapping("/agentList")
    public ModelAndView agentList(HttpServletRequest request, Integer pageNum, Integer
            pageSize) throws Exception {

        ModelAndView mv = new ModelAndView("myAgent");

        User user = (User) request.getSession().getAttribute("user");
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        //使用分页插件
        PageHelper.startPage(pageNum , pageSize);

        List<Map> list = agentService.agentList(user.getId()); //获取后台数据列表（根据自己系统修改）

        //根据查询的数据列表，得到分页的结果对象
        PageInfo<Map> pageList = new PageInfo<Map>(list);
        List<Map> datas = pageList.getList();
        mv.addObject("datas", datas);
        return mv;
    }

}
