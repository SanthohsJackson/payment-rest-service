package com.payment.credit.service;

import com.payment.credit.data.User;
import com.payment.credit.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Primary service implementation for {@link UserService}
 *
 * @Author Santhosh Jackson
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    /**
     * @return
     */
    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }


    /**
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null");
        } else if (user.getFirstName() == null) {
            throw new IllegalArgumentException("User first name can not be null");
        }
        return userRepo.save(user);
    }
}
