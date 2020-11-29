package com.payment.credit.repo;


import com.payment.credit.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Santhosh Jackson
 **/
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findAll();
}
