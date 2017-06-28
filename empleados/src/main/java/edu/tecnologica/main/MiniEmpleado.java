package edu.tecnologica.main;


public class MiniEmpleado {

	private int employeeId;
	private String firstName;
	
	public MiniEmpleado(int employeeId, String firstName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
}
