package com.demo.dataanalyticrestfulapi.service;

import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.model.UserAccount;
import com.demo.dataanalyticrestfulapi.model.request.UserRequest;

import java.util.List;

public interface UserService {
    List <User> allUsers();
    List <User> findUserByName();
    User findUserById(int id);

    int createNewUser(UserRequest user);
    int updateUser(UserRequest user, int id);
    int removeUSer(int id);

    List<UserAccount> getAllUserAccounts();
}
