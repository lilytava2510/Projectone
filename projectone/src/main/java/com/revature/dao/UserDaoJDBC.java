package com.revature.dao;

import com.revature.models.Reimburse;
import com.revature.models.User;
import com.revature.utils.ConnectionSingleton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC {

    ConnectionSingleton cs = ConnectionSingleton.getConnectionSingleton();

    public void createUser(User user){
        Connection c = cs.getConnection();
        String sql = "call create_user(?,?,?,?,?,?)";
        int x = 0;
        try{
            if(user.isPrivilege()){ x=1;}else{x=2;}
            c.setAutoCommit(false);
            CallableStatement call = c.prepareCall(sql);
            call.setString(1, user.getUsername());
            call.setString(2, user.getPassword());
            call.setString(3, user.getFirstName());
            call.setString(4, user.getLastName());
            call.setString(5, user.getEmail());
            call.setInt(6, x);
            call.execute();
            c.setAutoCommit(true);
        }catch(SQLException e){e.printStackTrace();}
    }

    public User getUserById(int id){
        Connection c = cs.getConnection();
        String sql = "select * from users where user_id = ?";
        User user = new User();
        try{
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while(rs.next()){
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                if(rs.getInt(7) == 1){
                    user.setPrivilege(true);
                }else{user.setPrivilege(false);}
            }
        }catch(SQLException e){e.printStackTrace();}
        return user;
    }

    public User readUserByEmail(String email){
        Connection c = cs.getConnection();
        String sql = "select * from users where email = ?";
        User user = new User();
        try{
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, email);
            ResultSet rs = p.executeQuery();
            while(rs.next()){
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                if(rs.getInt(7) == 1){
                    user.setPrivilege(true);
                }else{user.setPrivilege(false);}
            }
        }catch(SQLException e){e.printStackTrace();}
        return user;
    }

    public User updateUser(User user){
        Connection c = cs.getConnection();
        String sql = "update users set username = ?, password = ?, first_name = ?, last_name = ?, email = ?, role_ = ? where user_id = ?";
        int x = 0;
        try{
            if(user.isPrivilege()){ x=1;}else{x=2;}
            c.setAutoCommit(false);
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, user.getUsername());
            p.setString(2, user.getPassword());
            p.setString(3, user.getFirstName());
            p.setString(4, user.getLastName());
            p.setString(5, user.getEmail());
            p.setInt(6, x);
            p.setInt(7, user.getUserId());
            p.execute();
            c.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public void deleteUser(int id){
        Connection c = cs.getConnection();
        String sql = "delete from users where user_id = ?";
        try{
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            p.execute();
        }catch(SQLException e){e.printStackTrace();}
    }

    public User login(String users, String password){
        Connection c = cs.getConnection();
        String sql = "select * from users where email = ? and password = ?";
        User user = new User();
        try{
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, users);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            while(rs.next()){
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                if(rs.getInt(7) == 1){
                    user.setPrivilege(true);
                }else{user.setPrivilege(false);}
            }
        }catch(SQLException e){e.printStackTrace();}
        if(user.getUsername() == null){
            user = null;
        }
        return user;
    }

    public List<User> readAllEmployees() {
        Connection c =cs.getConnection();
        String sql = "select * from users where role_ = 2";
        List<User> holder= new ArrayList<>();
        try{
            Statement p = c.createStatement();
            ResultSet rs = p.executeQuery(sql);
            if(rs.wasNull()) {
                return null;
            }else {
                while (rs.next()) {
                    boolean x = false;
                    //if(rs.getInt(7) == 1){x = true;}else{x = false;}
                    User temp = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), x);
                    holder.add(temp);
                }
                return holder;
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }


}







//    public void createUser(User user){
//        Connection c = cs.getConnection();
//        String sql = "call create_user(?,?,?,?,?,?)";
//        int x = 0;
//        try{
//            if(user.isPrivilege()){ x=1;}else{x=2;}
//            c.setAutoCommit(false);
//            CallableStatement call = c.prepareCall(sql);
//            call.setString(1, user.getUsername());
//            call.setString(2, user.getPassword());
//            call.setString(3, user.getFirstName());
//            call.setString(4, user.getLastName());
//            call.setString(5, user.getEmail());
//            call.setInt(6, x);
//            call.execute();
//            c.setAutoCommit(true);
//        }catch(SQLException e){e.printStackTrace();}
//    }
//
//    @Override
//    public List<User> readAllUsers() {
//        return null;
//    }
//
//
//    public User getUserById(int id){
//        Connection c = cs.getConnection();
//        String sql = "select * from users where user_id = ?";
//        User user = new User();
//        try{
//            PreparedStatement p = c.prepareStatement(sql);
//            p.setInt(1, id);
//            ResultSet rs = p.executeQuery();
//            while(rs.next()){
//                user.setUserId(rs.getInt(1));
//                user.setUsername(rs.getString(2));
//                user.setPassword(rs.getString(3));
//                user.setFirstName(rs.getString(4));
//                user.setLastName(rs.getString(5));
//                user.setEmail(rs.getString(6));
//                if(rs.getInt(7) == 1){
//                    user.setPrivilege(true);
//                }else{user.setPrivilege(false);}
//            }
//        }catch(SQLException e){e.printStackTrace();}
//        return user;
//    }
//
//    @Override
//    public User readUserByEmail(String email) {
//        Connection c = cs.getConnection();
//        String sql = "SELECT * FROM users WHERE email = ?";
//
//        try {
//            PreparedStatement ps = c.prepareStatement(sql);
//            ps.setString(1, email);
//            ResultSet rs = ps.executeQuery();
//
//            User loggedIn = null;
//            while (rs.next()) {
//
//                loggedIn = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7));
//            }
//
//            return loggedIn;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }}
//
////
////    //public User readUserByEmail(String email){
////        Connection c = cs.getConnection();
////        String sql = "select * from users where email = ?";
////        User user = new User();
////        try{
////            PreparedStatement p = c.prepareStatement(sql);
////            p.setString(1, email);
////            ResultSet rs = p.executeQuery();
////            while(rs.next()){
////                user.setUserId(rs.getInt(1));
////                user.setUsername(rs.getString(2));
////                user.setPassword(rs.getString(3));
////                user.setFirstName(rs.getString(4));
////                user.setLastName(rs.getString(5));
////                user.setEmail(rs.getString(6));
////                if(rs.getInt(7) == 1){
////                    user.setPrivilege(true);
////                }else{user.setPrivilege(false);}
////            }
////        }catch(SQLException e){e.printStackTrace();}
////        return user;
////    }
//
//    public User updateUser(User user){
//        Connection c = cs.getConnection();
//        String sql = "update users set username = ?, password = ?, first_name = ?, last_name = ?, email = ?, role_ = ? where user_id = ?";
//        int x = 0;
//        try{
//            if(user.isPrivilege()){ x=1;}else{x=2;}
//            c.setAutoCommit(false);
//            PreparedStatement p = c.prepareStatement(sql);
//            p.setString(1, user.getUsername());
//            p.setString(2, user.getPassword());
//            p.setString(3, user.getFirstName());
//            p.setString(4, user.getLastName());
//            p.setString(5, user.getEmail());
//            p.setInt(6, x);
//            p.setInt(7, user.getUserId());
//            p.execute();
//            c.setAutoCommit(true);
//        }catch(SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//
//        return user;
//    }
//
//    @Override
//    public void deleteUser(int id){
//        Connection c = cs.getConnection();
//        String sql = "delete from users where user_id = ?";
//        try{
//            PreparedStatement p = c.prepareStatement(sql);
//            p.setInt(1, id);
//            p.execute();
//        }catch(SQLException e){e.printStackTrace();}
//    }
//
//
//
//    public User loginUser(String email, String password){
//        Connection c = cs.getConnection();
//        String sql = "select * from users where username = ? and password = ?";
//        User user = new User();
//        try{
//            PreparedStatement p = c.prepareStatement(sql);
//            p.setString(1, email);
//            p.setString(2, password);
//            ResultSet rs = p.executeQuery();
//            while(rs.next()){
//                user.setUserId(rs.getInt(1));
//                user.setUsername(rs.getString(2));
//                user.setPassword(rs.getString(3));
//                user.setFirstName(rs.getString(4));
//                user.setLastName(rs.getString(5));
//                user.setEmail(rs.getString(6));
//                if(rs.getBoolean(7)){
//                    user.setPrivilege(true);
//                }else{user.setPrivilege(false);}
//            }
//        }catch(SQLException e){e.printStackTrace();}
//        return user;
//    }
//
//    public List<User> readAllEmployees() {
//        Connection c =cs.getConnection();
//        String sql = "select * from users where role_ = 2";
//        List<User> holder= new ArrayList<>();
//        try{
//            Statement p = c.createStatement();
//            ResultSet rs = p.executeQuery(sql);
//            if(rs.wasNull()) {
//                return null;
//            }else {
//                while (rs.next()) {
//                    boolean x = false;
//                    //if(rs.getInt(7) == 1){x = true;}else{x = false;}
//                    User temp = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), x);
//                    holder.add(temp);
//                }
//                return holder;
//            }
//        }catch(SQLException e){e.printStackTrace();}
//        return null;
//    }
//
//
//}


//    public void deleteUser(int id){
//        Connection c = cs.getConnection();
//        String sql = "delete from users where user_id = ?";
//        try{
//            PreparedStatement p = c.prepareStatement(sql);
//            p.setInt(1, id);
//            p.execute();
//        }catch(SQLException e){e.printStackTrace();}
//    }

//    public User loginUser(String users, String password){
//        Connection c = cs.getConnection();
//        String sql = "select * from users where username = ? and password = ?";
//        User user = new User();
//        try{
//            PreparedStatement p = c.prepareStatement(sql);
//            p.setString(1, users);
//            p.setString(2, password);
//            ResultSet rs = p.executeQuery();
//            while(rs.next()){
//                user.setUserId(rs.getInt(1));
//                user.setUsername(rs.getString(2));
//                user.setPassword(rs.getString(3));
//                user.setFirstName(rs.getString(4));
//                user.setLastName(rs.getString(5));
//                user.setEmail(rs.getString(6));
//                if(rs.getBoolean(7)){
//                    user.setPrivilege(true);
//                }else{user.setPrivilege(false);}
//            }
//        }catch(SQLException e){e.printStackTrace();}
//        return user;
//    }
//
//}
//
