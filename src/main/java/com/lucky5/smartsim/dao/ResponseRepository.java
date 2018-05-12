package com.lucky5.smartsim.dao;

import com.lucky5.smartsim.model.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * author : Yashpal_Rawat
 * lastModifiedDate : 4/05/2018 8:25 PM
 */
@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {
}
