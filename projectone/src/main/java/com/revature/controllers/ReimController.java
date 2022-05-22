package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.RegisterObject;
import com.revature.models.ReimObject;
import com.revature.models.Reimburse;
import com.revature.models.User;
import com.revature.services.ReimService;
import io.javalin.http.Handler;


public class ReimController {


    private ReimService rs;
    private ObjectMapper om;

    public ReimController(ReimService rs) {
        this.rs = rs;
        this.om = new ObjectMapper();
    }

    public Handler handleCreateReim = (ctx) -> {
        if (ctx.req.getSession().getAttribute("id") == null) {
            ctx.status(401);
            ctx.result("You must log in to request a reimbursement");
        } else {
            int reimburserId = Integer.parseInt((String) ctx.req.getSession().getAttribute("id"));
            //User u = new User();
            ReimObject r = om.readValue(ctx.body(), ReimObject.class);
            //u.setUserId(r.author);
            rs.addReimburse(r.amount, r.description, reimburserId, r.type);
        }

    };
    public Handler handleViewTickets = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (id == 0) {
            ctx.status(402);
            ctx.result("Please log in to view tickets.");
        } else {

            //RegisterObject ro = om.readValue(ctx.body(), RegisterObject.class);

            ctx.result(om.writeValueAsString(rs.readReimburse(id)));
        }

    };
    public Handler handleUpdateReim = (ctx) -> {
        //int id = Integer.parseInt(ctx.pathParam("id"));
//        if (id == 0) {
//            ctx.status(409);
//            ctx.result("Please log in to view alter tickets.");
//        } else {

        ReimObject ro = om.readValue(ctx.body(), ReimObject.class);
        ctx.result(om.writeValueAsString(rs.updateReim(ro.id, ro.author, ro.status)));
        //}
    };


    public Handler handleUserApprove = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (id == 0) {
            ctx.status(403);
            ctx.result("Please log in to view approved tickets.");
        } else {

            //RegisterObject ro = om.readValue(ctx.body(), RegisterObject.class);
            ctx.result(om.writeValueAsString(rs.getApprovedId(id)));


        }
    };


    public Handler handleUserPend = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (id == 0) {
            ctx.status(404);
            ctx.result("Log in in order to view the status of your tickets.");
        } else {

            //RegisterObject ro = om.readValue(ctx.body(), RegisterObject.class);

            ctx.result(om.writeValueAsString(rs.getPendingId(id)));
        }

    };

    public Handler handleGetAllAprrove = (ctx) -> {
        if (ctx.req.getSession().getAttribute("id") == null) {
            ctx.status(405);
            ctx.result("Log in in order to change the status of user tickets.");
        } else {

            ctx.result(om.writeValueAsString(rs.getAllApprove()));
        }


    };
    public Handler handlegetAllPend = (ctx) -> {
        if (ctx.req.getSession().getAttribute("id") == null) {
            ctx.status(406);
            ctx.result("Log in in order to change the status of user tickets.");
        } else {

            ctx.result(om.writeValueAsString(rs.getAllPend()));
        }


    };

}
//package com.revature.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.models.ReimObject;
//import com.revature.services.ReimService;
//import io.javalin.http.Handler;
//
//public class ReimController {
//
//    private ReimService rs;
//    private ObjectMapper om;
//
//    public ReimController(ReimService rs){
//        this.rs =rs;
//        this.om = new ObjectMapper();
//
//    }
//
//
//    public Handler handleCreateReim = (ctx) -> {
//
//            if (ctx.req.getSession().getAttribute("id") == null) {
//                ctx.status(401);
//                ctx.result("You must log in to request a reimbursement");
//            } else {
//                int reimburserId = Integer.parseInt((String) ctx.req.getSession().getAttribute("id"));
//
//
//
//                ReimObject r = om.readValue(ctx.body(), ReimObject.class);
//                rs.addReimburse(r.amount, r.description, r.author, r.type);
//            }
//    };
//
//
//
//
//
//   }
//package com.revature.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.models.ReimObject;
//import com.revature.services.ReimService;
//import io.javalin.http.Handler;
//
//public class ReimController {
//
//    private ReimService rs;
//    private ObjectMapper om;
//
//    public ReimController(ReimService rs){
//        this.rs =rs;
//        this.om = new ObjectMapper();
//
//    }
//
//
//    public Handler handleCreateReim = (ctx) -> {
//
//            if (ctx.req.getSession().getAttribute("id") == null) {
//                ctx.status(401);
//                ctx.result("You must log in to request a reimbursement");
//            } else {
//                int reimburserId = Integer.parseInt((String) ctx.req.getSession().getAttribute("id"));
//
//
//
//                ReimObject r = om.readValue(ctx.body(), ReimObject.class);
//                rs.addReimburse(r.amount, r.description, r.author, r.type);
//            }
//    };
//
//
//
//
//
//   }
