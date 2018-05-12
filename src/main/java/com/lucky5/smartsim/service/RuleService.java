package com.lucky5.smartsim.service;

import com.lucky5.smartsim.dao.RuleRepository;
import com.lucky5.smartsim.model.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * author : Yashpal_Rawat
 * lastModifiedDate : 7/05/2018 6:03 PM
 */
@Service
public class RuleService {

    private RuleRepository ruleRepository;
    private static final Logger log = LoggerFactory.getLogger(RuleService.class);

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    // Retrieves key from rule definition
    public String getKey(Rule rule) {
        return null;
    }

    public Rule getRule(final String pattern) {
        return ruleRepository.findByRequestPattern(pattern);
    }

    public List<Rule> findAll() {
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        return rules;
    }

    public void save(Rule rule) {
        ruleRepository.save(rule);
    }

    public String service(HttpServletRequest request) {
        log.info("getRequestURI => {} ", request.getRequestURI());
        log.info("context path => {} ", request.getContextPath());
        log.info("path info => {} ", request.getPathInfo());
        log.info("query string => {} ", request.getQueryString());
        return "";
    }
}
