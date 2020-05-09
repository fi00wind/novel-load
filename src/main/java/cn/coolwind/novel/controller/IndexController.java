package cn.coolwind.novel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/web/**")
    public ModelAndView web() {
        return new ModelAndView("/index.html");
    }

    @GetMapping("/")
    public ModelAndView root() {
        return new ModelAndView("forward:web");
    }
}
