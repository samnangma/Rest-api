package com.demo.dataanalyticrestfulapi.service;

import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.model.UserAccount;
import com.demo.dataanalyticrestfulapi.model.request.UserRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    PageInfo <User> allUsers(int page, int size ,String filterName);
    List <User> findUserByName(String username);
    User findUserById(int id);

    int createNewUser(UserRequest user);
    int updateUser(UserRequest user, int id);
    int removeUSer(int id);

    List<UserAccount> getAllUserAccounts();
}
