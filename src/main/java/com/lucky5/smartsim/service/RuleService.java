package com.lucky5.smartsim.service;

import com.lucky5.smartsim.dao.RuleRepository;
import com.lucky5.smartsim.model.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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

    /**
     * Save rule in the database.
     *
     * @param rule
     */
    public void save(Rule rule) {
        log.info("trying to save rule in database");
        ruleRepository.save(rule);
    }

    public String service(HttpServletRequest httpRequest) {
        log.info("getRequestURI => {} ", httpRequest.getRequestURI());
        log.info("context path => {} ", httpRequest.getContextPath());
        log.info("path info => {} ", httpRequest.getPathInfo());
        log.info("query string => {} ", httpRequest.getQueryString());

        List<Rule> rules = findAll().stream().filter((rule) -> {
            Pattern pattern = Pattern.compile(rule.getRequestPattern());
            log.info("finding pattern {} in {}", rule.getRequestPattern(), httpRequest.getRequestURI());
            Matcher matcher = pattern.matcher(httpRequest.getRequestURI());
            return matcher.matches();
        }).collect(Collectors.toList());

        Rule rule = rules.get(0);
        return getResponse(rule.getKeyPattern(), httpRequest);
    }

    /**
     * Extract output file name based on key pattern configured with the rule.
     *
     *
     * @param keyPattern  - Key Pattern
     * @param httpRequest - {@link HttpServletRequest} Servlet request
     * @return
     */
    private String getResponse(final String keyPattern, HttpServletRequest httpRequest) {
        String outputFile = "default.json";

        
        // Output file name is derived from request path
        if (keyPattern.startsWith("request#")) {
            String actualPattern = keyPattern.substring("request#".length());
            Pattern pattern = Pattern.compile(actualPattern);

            // Output file name is derived from header
            Matcher matcher = pattern.matcher(httpRequest.getRequestURI());
            if (matcher.matches()) {
                outputFile = matcher.group(1);
            }

        } else if (keyPattern.startsWith("header#")) {
            String actualPattern = keyPattern.substring("header#".length());
            Pattern pattern = Pattern.compile(actualPattern);

            // Output file name is derived from header
            Enumeration<String> headers = httpRequest.getHeaderNames();
            while(headers.hasMoreElements()) {
                Matcher matcher = pattern.matcher(httpRequest.getHeader(headers.nextElement()));
                if (matcher.matches()) {
                   outputFile = matcher.group(0);
                   break;
                }
            }
        } else if (keyPattern.startsWith("body#")) {
            // Output file name is derived from request body

        }
        return readContentFromFile(this.getClass().getResourceAsStream(outputFile));
    }

    /**
     * Read file
     *
     * @param outputFile
     * @return
     */
    private String readContentFromFile(final InputStream in) {
        try {
            InputStreamReader isr =  new InputStreamReader(in,"utf-8");
            BufferedReader br = new BufferedReader(isr);

            // From now on, the right way of moving from bytes to utf-8 characters:
            int b;
            StringBuilder buf = new StringBuilder(512);
            while ((b = br.read()) != -1) {
                buf.append((char) b);
            }
            br.close();
            isr.close();
            return buf.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
