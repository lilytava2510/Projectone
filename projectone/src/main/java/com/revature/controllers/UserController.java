package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginObject;
import com.revature.models.RegisterObject;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Handler;

public class UserController {

    private UserService us;

    private ObjectMapper om;

    public UserController(UserService us) {
        this.us = us;
        this.om = new ObjectMapper();
    }


    public Handler handleRegister = (ctx) -> {
        RegisterObject ro = om.readValue(ctx.body(), RegisterObject.class);

        System.out.println(ro);


        us.registerUser(ro.username, ro.password, ro.first, ro.last, ro.email);

        ctx.result("User was created");
    };

    public Handler handleLogin = (ctx) -> {
        ObjectMapper mapper = new ObjectMapper();
        LoginObject lo = mapper.readValue(ctx.body(), LoginObject.class);
        User u = us.loginUser( lo.email, lo.password);
//        if(u == null){
//            ctx.status(403);
//            ctx.result("Username or password was incorrect");
//        } else {
        ctx.req.getSession().setAttribute("loggedIn", u.getEmail());
        ctx.req.getSession().setAttribute("id", "" + u.getUserId());
        ctx.result(om.writeValueAsString(u));


        ctx.result("logged");
        //  }
    };

    public Handler handleUpdateUser = (ctx) -> {
        RegisterObject r = om.readValue(ctx.body(), RegisterObject.class);

        User u = new User(r.id, r.username, r.password, r.first, r.last, r.email, r.trust);

        ctx.result(om.writeValueAsString(us.updateUserInfo(u)));
    };

    public Handler handleDeleteUser = (ctx)-> {
        //int id = Integer.parseInt(ctx.pathParam("id"));
        RegisterObject ro = om.readValue(ctx.body(), RegisterObject.class);
        us.deleteUser(ro.id);
        ctx.result("User was deleted");
    };

    public Handler handleAllEmployee = (ctx) -> {
        ctx.result(om.writeValueAsString(us.readAllEmployee()));
    };

}