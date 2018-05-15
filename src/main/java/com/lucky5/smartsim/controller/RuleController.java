package com.lucky5.smartsim.controller;

import com.lucky5.smartsim.model.Rule;
import com.lucky5.smartsim.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * author : Yashpal_Rawat
 * lastModifiedDate : 4/05/2018 8:43 PM
 *
 */
@Controller
public class RuleController {
    private RuleService ruleService;

    private static final Logger log = LoggerFactory.getLogger(RuleController.class);

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public String view(Model m) {
        log.info("request received {} ", m);

        m.addAttribute("rules", ruleService.findAll());
        m.addAttribute("ruleobj", new Rule());
        return "index";
    }

    @RequestMapping(value = "/rule", method = RequestMethod.POST)
    public String add(Model m, Rule rule) {
        log.info("saving rule in db");
        ruleService.save(rule);
        return "redirect:/rules";
    }

    @RequestMapping(value = "/smart-sim/*",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public String apiRequest(HttpServletRequest request) {
        log.info("request received {} ", request);
        return ruleService.service(request);
    }
}
