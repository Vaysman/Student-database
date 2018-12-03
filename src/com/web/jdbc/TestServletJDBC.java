package com.web.jdbc;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Servlet implementation class TestServletJDBC
 */
@WebServlet("/TestServletJDBC")
public class TestServletJDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//difine datasource/connection pool for Resource Injection
    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //set up the print wrighter
        PrintWriter out = response.getWriter(); //send data back to the browser
        response.setContentType("text/plain");
            // get connection to the database
        Connection myConn = null;
        Statement myStatment = null;
        ResultSet myRs = null;
        try {                                         //valid connection to database
            myConn = dataSource.getConnection();
            // create sql statment
            String sql = "select *from student";
            myStatment = myConn.createStatement();
            // execute sql query
            myRs = myStatment.executeQuery(sql);
            // process the result set
            while (myRs.next()){
            String email = myRs.getString("email");
            out.println(email);
        }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
