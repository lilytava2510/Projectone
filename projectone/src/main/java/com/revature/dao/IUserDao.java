package com.revature.dao;

import com.revature.models.User;

import java.util.List;
import java.util.Set;

public interface IUserDao {

    //Start out with the most basic crud


    public void createUser(User user);


    public List<User> readAllUsers();


    public User readUserByEmail(String email);


    public User updateUser(User u);


    public void deleteUser(int id);
    
    public User login (String email, String password);


    List<User> readAllEmployees();
}
