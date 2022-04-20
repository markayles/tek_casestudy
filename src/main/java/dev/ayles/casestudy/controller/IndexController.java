package dev.ayles.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();



        response.setViewName("index");
        return response;
    }

}
