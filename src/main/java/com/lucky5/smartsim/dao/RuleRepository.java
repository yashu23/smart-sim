package com.lucky5.smartsim.dao;

import com.lucky5.smartsim.model.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * author : Yashpal_Rawat
 * lastModifiedDate : 4/05/2018 8:24 PM
 */
@Repository
public interface RuleRepository extends CrudRepository<Rule, Long> {
    public Rule findByRequestPattern(String requestPattern);
}
