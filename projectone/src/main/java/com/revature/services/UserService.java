package com.revature.services;

import com.revature.models.User;
import com.revature.dao.IReimDaoJDBC;
import com.revature.dao.UserDaoJDBC;

import java.util.List;

public class UserService {

    private UserDaoJDBC ud;
    private IReimDaoJDBC rd;

    public UserService(UserDaoJDBC ud) {
        this.ud = ud;
    }

    public UserService(UserDaoJDBC ud, IReimDaoJDBC rd) {
        this.ud = ud;
        this.rd = rd;

    }

    public void registerUser(String first, String last, String username, String email, String password) {
        User register = new User(0, first, last, username, email, password);
        ud.createUser(register);
    }
    public void registerUser(String first, String last, String user, String email, String pass, boolean trust){
        User register = new User(user, pass, first, last, email, trust);
        ud.createUser(register);
    }


    public User loginUser(String email, String password) {
        return ud.login(email, password);
//        User u = ud.readUserByEmail(email);
//
//        if(u != null){
//            if(password.equals(u.getPassword())){
//
//                return u;
//            } else {
//
//                return null;
//            }
//        }

        //  public User logout(String email, String password) {

        //  User u = ud.loginUser(email,password);


        //return null;
    }


    public User updateUserInfo(User u) {
        return ud.updateUser(u);
    }

    public void deleteUser(int id) {
        ud.deleteUser(id);
    }

    public List<User> readAllEmployee(){
        return ud.readAllEmployees();
    }

}