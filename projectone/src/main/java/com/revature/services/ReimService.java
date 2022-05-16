package com.revature.services;


import java.sql.Date;
import java.time.Instant;
import java.util.List;

import com.revature.dao.IReimDaoJDBC;
import com.revature.models.Reimburse;

public class ReimService {
    private IReimDaoJDBC rd;

    public ReimService(IReimDaoJDBC rd) {
        this.rd = rd;

    }

    public void addReimburse(double amount, String description, int author, int type) {

        Date d = new Date(Instant.now().toEpochMilli());

        Reimburse r = new Reimburse( amount, d, description, author, 1, type);
        rd.createTicket(r);
    }


    public List<Reimburse> ReadReimburse(int id) {
        return rd.getById(id);
    }

    public Reimburse updateReim(int id, int resolver, int status) {
        Date d = new Date(Instant.now().toEpochMilli());

        Reimburse r = new Reimburse();
        r.setReimburse_id(id);
        r.setManager(resolver);
        r.setStatus(status);
        r.setResolution(d);
        return rd.updateReimburse(r);
    }

    public List<Reimburse> getApprovedId(int id) {
        return rd.getApprovedById(id);
    }

    public List<Reimburse> getPendingId(int id) {
        return rd.getPendingById(id);

    }

    public List<Reimburse> getAllApprove() {

        return rd.getAllApproved();

    }

    public List<Reimburse> getAllPend(){
        return rd.getAllPending();
    }
}//package com.revature.services;
//
//
//import java.sql.Date;
//import java.time.Instant;
//import com.revature.dao.IReimDaoJDBC;
//import com.revature.models.Reimburse;
//
//public class ReimService {
//
//     private IReimDaoJDBC rd;
//
//    public ReimService(IReimDaoJDBC rd){
//        this.rd=rd;}
//
//    public void addReimburse(double amount, String description, int author, int type){
//
//        Date d = new Date(Instant.now().toEpochMilli());
//
//        Reimburse r = new Reimburse( amount, d, description, author, 1, type);
//        r.setManager(1);
//        rd.createTicket(r);
//    }
//
//
//}