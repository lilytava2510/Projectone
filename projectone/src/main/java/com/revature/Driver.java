package com.revature;

import com.revature.controllers.ReimController;
import com.revature.controllers.UserController;

import com.revature.dao.UserDaoJDBC;
import com.revature.services.ReimService;
import com.revature.services.UserService;
import com.revature.utils.ConnectionSingleton;
import io.javalin.Javalin;
import com.revature.dao.IReimDaoJDBC;
import java.sql.Connection;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class Driver {

public static void main(String[] args ) {
    Connection c = ConnectionSingleton.getConnectionSingleton().getConnection();
    UserDaoJDBC ud = new UserDaoJDBC();
    IReimDaoJDBC rd = new IReimDaoJDBC();
    UserService us = new UserService(ud);
    UserController uc = new UserController(us);
    ReimService rs = new ReimService(rd);
    ReimController rc = new ReimController(rs);
   // us.registerUser("charly", "charles", "tt", "@gmail", "passwprd");
    Javalin server = Javalin.create(config ->
    {

        config.enableCorsForAllOrigins();
    });
    server.before(ctx -> ctx.header("Access-Control-Allow-Credentials", "true"));
    server.before(ctx -> ctx.header("Access-Control_Expose-Headers","*"));

    server.routes(()-> {
        path("users", () -> {
            post("/register", uc.handleRegister);
            post("/login", uc.handleLogin);
            put("/update", uc.handleUpdateUser);
            delete("/id", uc.handleDeleteUser);
            get("/registry", uc.handleAllEmployee);
            get("/logout", uc.handleLogout);
            get("/info/", uc.handleGetUserInfo);

        });
        path("reimburse", ()-> {
            post("/create",rc.handleCreateReim);
            get("/own_ledger",rc.handleViewTickets);
            put("/edit",rc.handleUpdateReim);
            get("/own_approve",rc.handleUserApprove);
            get("/own_pend",rc.handleUserPend);
            get("/approve",rc.handleGetAllAprrove);
            get("/pend", rc.handlegetAllPend);

        });







//    server.routes(()-> {
//        path("users", () -> {
//            post("/register", uc.handleRegister);
//            post("/login", uc.handleLogin);
//            put("/", uc.handleUpdateUser);
//            delete("/{id}", uc.handleDeleteUser);
//        });
//        path("reimburse", ()-> {
//            post("/",rc.handleCreateReim);
//           get("/",rc.handleViewTickets);
//           // put("/",rc.handleUpdateReim);
//           // get("/",rc.handleUserApprove);
//          //  get("/",rc.handleUserPend);
//         //   put("/",rc.handleGetAllAprrove);
//         //   put("/", rc.handlegetAllPend);
//
//        });
//
//
//
//


        server.start(8080);

    });

}}


// get("/logout", uc.handlelogout); {

