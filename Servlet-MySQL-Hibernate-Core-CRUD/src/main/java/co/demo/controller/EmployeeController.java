package co.demo.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import co.demo.config.HibernateConfig;
import co.demo.model.Employee;
import co.demo.service.DBLayer;

@WebServlet(urlPatterns = "/Employee")
@MultipartConfig
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		JSONObject responseObject = new JSONObject();
		responseObject.put("success", 0);
		String employeeID = request.getParameter("employeeId");
		responseObject = DBLayer.getEmployee(employeeID);
		response(response, responseObject.toString());

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		JSONObject responseObject = new JSONObject();
		responseObject.put("success", 0);
		Employee emp = new Employee(request.getParameter("name"), request.getParameter("mobile"), LocalDateTime.now());
		responseObject = DBLayer.addEmployee(emp);
		response(response, responseObject.toString());
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		JSONObject responseObject = new JSONObject();
		responseObject.put("success", 0);
		Employee emp = new Employee(request.getParameter("name"), request.getParameter("mobile"), LocalDateTime.now());
		responseObject = DBLayer.updateEmployee(emp);
		response(response, responseObject.toString());
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		JSONObject responseObject = new JSONObject();
		responseObject.put("success", 0);
		String employeeID = request.getParameter("employeeId");
		responseObject = DBLayer.deleteEmployee(employeeID);
		response(response, responseObject.toString());
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		HibernateConfig.getSessionFactory();
		super.init(config);
	}

	private void response(HttpServletResponse resp, String msg) {
		try {
			new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"), true).println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	private void setAccessControlHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	}

}
