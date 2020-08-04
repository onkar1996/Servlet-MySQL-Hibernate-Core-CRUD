package co.demo.service;

import org.json.simple.JSONObject;

import co.demo.dao.DataAccess;
import co.demo.model.Employee;

public class DBLayer {

	public static JSONObject addEmployee(Employee emp) {
		JSONObject json = new JSONObject();
		json.put("success", 1);
		json.put("EmployeeId", DataAccess.save(emp));
		return json;
	}

	public static JSONObject getEmployee(String employeeID) {
		JSONObject json = new JSONObject();
		Employee emp = DataAccess.get(Employee.class, Integer.parseInt(employeeID));
		json.put("success", 1);
		json.put("id", emp.getId());
		json.put("name", emp.getName());
		json.put("mobile", emp.getMobile());
		json.put("createdAt", emp.getCreatedAt());
		return json;
	}

	public static JSONObject deleteEmployee(String employeeID) {
		JSONObject json = new JSONObject();
		Employee emp = DataAccess.get(Employee.class, Integer.parseInt(employeeID));
		DataAccess.delete(emp);
		json.put("success", 1);
		json.put("message", "Employee Deleted");
		return json;
	}

	public static JSONObject updateEmployee(Employee emp) {
		JSONObject json = new JSONObject();
		json.put("success", 0);
		if (DataAccess.saveOrUpdate(emp) == 1) {
			json.put("success", 1);
		}
		return json;
	}

}
