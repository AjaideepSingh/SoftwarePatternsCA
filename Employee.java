public class Employee {
	private int employeeId;
	private String pps, surname, firstName, department;
	private char gender;
	private double salary;
	private boolean fullTime;

	public Employee(int employeeId, String pps, String surname, String firstName, char gender, String department, double salary,
					boolean fullTime) {
		this.employeeId = employeeId;
		this.pps = pps;
		this.surname = surname;
		this.firstName = firstName;
		this.gender = gender;
		this.department = department;
		this.salary = salary;
		this.fullTime = fullTime;
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public String getPps() {
		return pps;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public char getGender() {
		return this.gender;
	}

	public String getDepartment() {
		return this.department;
	}

	public double getSalary() {
		return this.salary;
	}

	public boolean getFullTime() {
		return this.fullTime;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setPps(String pps) {
		this.pps = pps;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}

	public String toString() {
		String bool;
		if (fullTime)
			bool = "Yes";
		else
			bool = "No";

		return "Employee ID: " + this.employeeId + "\nPPS Number: " + this.pps + "\nSurname: " + this.surname
				+ "\nFirst Name: " + this.firstName + "\nGender: " + this.gender + "\nDepartment: " + this.department + "\nSalary: " + this.salary
				+ "\nFull Time: " + bool;
	}
}
