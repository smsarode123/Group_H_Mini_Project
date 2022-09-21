package com.quizproject.grouph;


public class StudentDetails {
	private String studentRollNumber;
	private String studentName;
	
//  Here we are providing public getter and setter method for private variable
	
	public String getRollNumber() {
		return studentRollNumber;
	}
	public void setRollNumber(String studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}
	public String getName() {
		return studentName;
	}
	public void setName(String studentName) {
		this.studentName = studentName;
	}	
}

