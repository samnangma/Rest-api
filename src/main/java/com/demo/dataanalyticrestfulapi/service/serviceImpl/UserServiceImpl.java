package com.demo.dataanalyticrestfulapi.service.serviceImpl;

import com.demo.dataanalyticrestfulapi.Reposity.UserRepository;
import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.model.UserAccount;
import com.demo.dataanalyticrestfulapi.model.request.UserRequest;
import com.demo.dataanalyticrestfulapi.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public PageInfo<User> allUsers(int page, int size , String filterName) {
        PageHelper.startPage(page, size);
        return new PageInfo<>( userRepository.allUsers(filterName));
    }

    @Override
    public List<User> findUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserByID(id);
    }

    @Override
    public int createNewUser(UserRequest user) {
        return userRepository.createNewUser(user);
    }

    @Override
    public int updateUser(UserRequest user, int id) {
        return userRepository.updateUser(user, id);
    }

    @Override
    public int removeUSer(int id) {
        return userRepository.removeUser(id);
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userRepository.getAllUserAccounts();
    }
}
