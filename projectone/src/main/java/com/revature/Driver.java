package com.revature;

import com.revature.controllers.ReimController;
import com.revature.controllers.UserController;
import com.revature.dao.IReimDaoJDBC;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.Reimburse;
import com.revature.models.User;
import com.revature.services.ReimService;
import com.revature.services.UserService;
import com.revature.utils.ConnectionSingleton;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.Date;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Driver {

    public static void main(String[] args ) {
        Connection c = ConnectionSingleton.getConnectionSingleton().getConnection();
        UserDaoJDBC ud = new UserDaoJDBC();
        IReimDaoJDBC rd = new IReimDaoJDBC();
        UserService us = new UserService(ud);
        ReimService rs = new ReimService(rd);
        UserController uc = new UserController(us);
        ReimController rc = new ReimController(rs);
        //Date d = new Date(2020-01-01);

        //User use = new User(1,"","","","","@", true);

        //User useOne = new User(1,"mom","mom","mom","mom","m@m", false);

        //User useTwo = new User(2,"dad","dad","dad","dad","d@d", false);

        //User useThree = new User(3,"bro","bro","bro","bro","b@o", false);

        //User useFour = new User(4,"sis","sis","sis","sis","s@s", false);

        //Reimburse r = new Reimburse(1, 2.0, null, null, "nothing", 1, 1, 2, 1);

        //ud.createUser(use);
        //ud.createUser(useOne);
        //ud.createUser(useTwo);
        //ud.createUser(useThree);
        //ud.createUser(useFour);

        //System.out.println(ud.getUserById(2).toString());

        //System.out.println(ud.readUserByEmail("m@m").toString());
        //System.out.println(us.updateUserInfo(use).toString());

        //us.deleteUser(use);

        //use.setRegistry(ud.readAllEmployees());
        //System.out.println(use.getRegistry().get(2));

        //rd.createTicket(r);

        //rs.addReimburse(1.0, "stuff", 1, 1);
        //rs.addReimburse(1.0, "stuff", 1, 4);
        //rs.addReimburse(1.0, "stuff", 2, 3);
        //rs.addReimburse(1.0, "stuff", 3, 2);
        //rs.addReimburse(1.0, "stuff", 4, 1);

        //rd.updateReimburse(r);

        //use.setHistory(rd.getById()); all list need work

        //System.out.println(rd.updateReimburse(r));

        //use.setHistory(rd.getAllApproved());

        //use.setHistory(rd.getAllPending());

        //use.setHistory(rd.getApprovedById(1));

        //use.setHistory(rd.getPendingById(1));
        //System.out.println(use.getHistory().get(0).toString());

        Javalin server = Javalin.create(config ->
        {
            config.enableCorsForAllOrigins();
        });
        //server.before(ctx -> ctx.header("Access-Control-Allow-Credentials", "true"));
        //server.before(ctx -> ctx.header("Access-Control-Expose-Headers", "*"));
        server.routes(()-> {
            path("users", () -> {
                post("/register", uc.handleRegister);
                post("/login", uc.handleLogin);
                put("/update", uc.handleUpdateUser);
                delete("/delete{id}", uc.handleDeleteUser);
                get("/registry", uc.handleAllEmployee);
                get("/logout", uc.handleLogout);
                get("/info{id}", uc.handleGetUserInfo);
            });
            path("reimburse", ()-> {
                post("/create",rc.handleCreateReim);
                get("/get{id}",rc.handleViewTickets);
                put("/edit",rc.handleUpdateReim);
                get("/own_approve{id}",rc.handleUserApprove);
                get("/own_pend{id}",rc.handleUserPend);
                get("/approve",rc.handleGetAllAprrove);
                get("/pend", rc.handlegetAllPend);

            });






            server.start(8080);

        });


    }
}

// get("/logout", uc.handlelogout); {

