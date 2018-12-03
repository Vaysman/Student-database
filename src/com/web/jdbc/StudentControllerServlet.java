package com.web.jdbc;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
       
	
	private StudentDbUtil studentDbUtil;
	@Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;
	
	


	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		//create student db util...and pass in the datasource
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch(Exception exc) {
			throw new ServletException(exc);
			
		}
	
	}


	/**
     * @see HttpServlet#HttpServlet()
     */
    public StudentControllerServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
	//list student in MVC 
		try {
			//read the "command" parameter 
			String getTheCommand = request.getParameter("command");
			String theCommand = Optional.ofNullable(getTheCommand).orElse("LIST");
			
			//route to the appropriate method 
			switch(theCommand) {
			case"LIST":
				//list the student in MVC
				listStudent(request, response);
				break;
				
			case "ADD":
			addStudent(request, response);
			
			case "LOAD":
				loadStudent(request, response);
				break;
				
			case "UPDATE":
				updateStudent(request, response);
				break;
				
			case "DELETE":
				deleteStudent(request, response);
				break;
				
			case "SEARCH":
                searchStudents(request, response);
                break;
                
			default:
				listStudent(request, response);
				break;
			}
			
		} catch (Exception exc) {
		
			throw new ServletException (exc);
		}
		
	}

	private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<Student> students = studentDbUtil.searchStudents(theSearchName);
        
        // add students to the request
        request.setAttribute("STUDENT_LIST", students);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }


	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		String theStudentId = request.getParameter("studentId");
		//delete student from database
		studentDbUtil.deleteStudent(theStudentId);
		//send them back "list student" page
		listStudent(request, response);
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
				int id = Integer.parseInt(request.getParameter("studentId"));
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				
				// create a new student object
				Student theStudent = new Student(id, firstName, lastName, email);
				
				// perform update on database
				studentDbUtil.updateStudent(theStudent);
				
				// send them back to the "list students" page
				listStudent(request, response);
				
			}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student form from data form
		
				String theStudentId=request.getParameter("studentId");
				
		//get the student from the database(db util)
				Student theStudent = studentDbUtil.getStudent(theStudentId);
		//place the student in the request attribute  
				request.setAttribute("THE_STUDENT", theStudent);
		//send to the jsp page: update-studentForm.jsp 
				RequestDispatcher dispatcher = request.getRequestDispatcher("/update-studentForm.jsp");
				 dispatcher.forward(request,response);
		
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student form from data form
		
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		
		//create a new student object
		Student theStudent = new Student(firstName, lastName, email);
		
		//add the student to the database
		studentDbUtil.addStudent(theStudent);
		//sent back to the main page (the student list)
		listStudent(request, response);
	}


	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get student from db util
		List<Student> students =studentDbUtil.getStudent();
		
		//add students to request
		request.setAttribute("STUDENT_LIST", students);
		//send to the jsp page
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		 dispatcher.forward(request,response);
		
	}


	

}
