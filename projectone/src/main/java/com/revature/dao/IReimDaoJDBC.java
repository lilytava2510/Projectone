package com.revature.dao;

import com.revature.models.Reimburse;
import com.revature.models.User;
import com.revature.utils.ConnectionSingleton;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IReimDaoJDBC {

    ConnectionSingleton cs = ConnectionSingleton.getConnectionSingleton();

    public void createTicket(Reimburse r){
        Connection c = cs.getConnection();
        String sql = "insert into reimburse(amount, submitted_date, resolve_date, description, reimburse_author, resolver, reimburse_status, reimburse_type) values(?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            c.setAutoCommit(false);
            CallableStatement call = c.prepareCall(sql);
            call.setDouble(1, r.getAmount());
            call.setDate(2, r.getSubmission());
            call.setDate(3, r.getResolution());
            call.setString(4, r.getDescription());
            call.setInt(5, r.getAuthor());
            call.setInt(6, r.getManager());
            call.setInt(7, r.getStatus());
            call.setInt(8, r.getType());
            call.execute();
            c.setAutoCommit(true);
        }catch(SQLException e){e.printStackTrace();}
    }

    public List<Reimburse> getById(int id){
        Connection c =cs.getConnection();
        String sql = "select * from reimburse where reimburse_author = ?";
        List<Reimburse> holder= new ArrayList<>();
        try{
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            if(rs.wasNull()) {
                return null;
            }else {
                while (rs.next()) {
                    Reimburse temp = new Reimburse(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(rs.getDate(3) != null){
                        Date ns = new Date(rs.getDate(3).getTime());
                        temp.setStart(dateFormat.format(ns));
                    }
                    if(rs.getDate(4) != null){
                        Date ne = new Date(rs.getDate(4).getTime());
                        temp.setEnd(dateFormat.format(ne));
                    }
                    holder.add(temp);
                }
                return holder;
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

    public Reimburse updateReimburse(Reimburse r){
        Connection c = cs.getConnection();
        String sql = "update reimburse set resolve_date = ?, resolver = ?, reimburse_status = ? where reimburse_id = ?";
        try{
            c.setAutoCommit(false);
            PreparedStatement p = c.prepareStatement(sql);
            p.setDate(1, r.getResolution());
            p.setInt(2, r.getManager());
            p.setInt(3, r.getStatus());
            p.setInt(4, r.getReimburse_id());
            p.execute();
            c.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if(r.getResolution() != null){
            Date ne = new Date(r.getResolution().getTime());
            r.setEnd(dateFormat.format(ne));
        }
        return r;
    }

    public Reimburse updateWholeReimburse(Reimburse r){
        Connection c = cs.getConnection();
        String sql = "update reimburse set amount = ?, submitted_date = ?, resolve_date = ?, description = ?, reimburse_author = ?, resolver = ?, reimburse_status = ?, reimburse_type = ? where reimburse_id = ?";
        try{
            c.setAutoCommit(false);
            PreparedStatement p = c.prepareStatement(sql);
            p.setDouble(1, r.getAmount());
            p.setDate(2, r.getSubmission());
            p.setDate(3, r.getResolution());
            p.setString(4, r.getDescription());
            p.setInt(5, r.getAuthor());
            p.setInt(6, r.getManager());
            p.setInt(7, r.getStatus());
            p.setInt(8, r.getType());
            p.setInt(9, r.getReimburse_id());
            p.execute();
            c.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return r;
    }

    public List<Reimburse> getApprovedById(int id){
        Connection c =cs.getConnection();
        String sql = "select * from reimburse where reimburse_author = ? and reimburse_status = 2";
        List<Reimburse> holder= new ArrayList<>();
        try{
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            if(rs.wasNull()) {
                return null;
            }else {
                while (rs.next()) {
                    Reimburse temp = new Reimburse(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(rs.getDate(3) != null){
                        Date ns = new Date(rs.getDate(3).getTime());
                        temp.setStart(dateFormat.format(ns));
                    }
                    if(rs.getDate(4) != null){
                        Date ne = new Date(rs.getDate(4).getTime());
                        temp.setEnd(dateFormat.format(ne));
                    }
                    holder.add(temp);
                }
                return holder;
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

    public List<Reimburse> getPendingById(int id){
        Connection c =cs.getConnection();
        String sql = "select * from reimburse where reimburse_author = ? and reimburse_status = 1";
        List<Reimburse> holder= new ArrayList<>();
        try{
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            if(rs.wasNull()) {
                return null;
            }else {
                while (rs.next()) {
                    Reimburse temp = new Reimburse(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(rs.getDate(3) != null){
                        Date ns = new Date(rs.getDate(3).getTime());
                        temp.setStart(dateFormat.format(ns));
                    }
                    if(rs.getDate(4) != null){
                        Date ne = new Date(rs.getDate(4).getTime());
                        temp.setEnd(dateFormat.format(ne));
                    }
                    holder.add(temp);
                }
                return holder;
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

//    public List<Reimburse> getPendById(int id){
//        Connection c =cs.getConnection();
//        String sql = "select * from reimburse where reimburse_author = ? and reimburse_status = 1";
//        List<Reimburse> holder= new ArrayList<>();
//        try{
//            PreparedStatement p = c.prepareStatement(sql);
//            p.setInt(1, id);
//            ResultSet rs = p.executeQuery();
//            if(rs.wasNull()) {
//                return null;
//            }else {
//                while (rs.next()) {
//                    Reimburse temp = new Reimburse(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
//
//                    holder.add(temp);
//                }
//                return holder;
//            }
//        }catch(SQLException e){e.printStackTrace();}
//        return null;
//    }

    public List<Reimburse> getAllApproved(){
        Connection c =cs.getConnection();
        String sql = "select * from reimburse where reimburse_status = 2";
        List<Reimburse> holder= new ArrayList<>();
        try{
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            if(rs.wasNull()) {
                return null;
            }else {
                while (rs.next()) {
                    Reimburse temp = new Reimburse(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(rs.getDate(3) != null){
                        Date ns = new Date(rs.getDate(3).getTime());
                        temp.setStart(dateFormat.format(ns));
                    }
                    if(rs.getDate(4) != null){
                        Date ne = new Date(rs.getDate(4).getTime());
                        temp.setEnd(dateFormat.format(ne));
                    }
                    holder.add(temp);
                }
                return holder;
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

    public List<Reimburse> getAllPending(){
        Connection c =cs.getConnection();
        String sql = "select * from reimburse where reimburse_status = 1";
        List<Reimburse> holder= new ArrayList<>();
        try{
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            if(rs.wasNull()) {
                return null;
            }else {
                while (rs.next()) {
                    Reimburse temp = new Reimburse(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(rs.getDate(3) != null){
                        Date ns = new Date(rs.getDate(3).getTime());
                        temp.setStart(dateFormat.format(ns));
                    }
                    if(rs.getDate(4) != null){
                        Date ne = new Date(rs.getDate(4).getTime());
                        temp.setEnd(dateFormat.format(ne));
                    }
                    holder.add(temp);
                }
                return holder;
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

    public Reimburse getOneById(int id){
        Connection c =cs.getConnection();
        String sql = "select * from reimburse where reimburse_id = ?";
        Reimburse temp = null;
        try{
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,id);
            ResultSet rs = p.executeQuery();
            if(rs.wasNull()) {
                return null;
            }else {
                while (rs.next()) {
                    temp = new Reimburse(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(rs.getDate(3) != null){
                        Date ns = new Date(rs.getDate(3).getTime());
                        temp.setStart(dateFormat.format(ns));
                    }
                    if(rs.getDate(4) != null){
                        Date ne = new Date(rs.getDate(4).getTime());
                        temp.setEnd(dateFormat.format(ne));
                    }
                }
                return temp;
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

cd}