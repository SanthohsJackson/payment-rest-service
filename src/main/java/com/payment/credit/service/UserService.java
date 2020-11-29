package com.payment.credit.service;

import com.payment.credit.data.User;

import java.util.List;

/**
 * Services for {@link User} functionality
 *
 * @Author Santhosh Jackson
 **/
public interface UserService {
    List<User> getUsers();

    User saveUser(User user);
}
