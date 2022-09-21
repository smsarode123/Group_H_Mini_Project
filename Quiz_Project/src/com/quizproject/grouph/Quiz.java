package com.quizproject.grouph;


import java.sql.SQLException;
import java.util.Scanner;

public class Quiz {
	
	Scanner sc=new Scanner(System.in);
	/*
	 * Here we are creating one method which is use to interact with user to
	 * Login and Registration
	 */
	public void quizStart() {
		LoginAndRegistration studentlar=new LoginAndRegistration();
		System.out.println("For Login type 'LOGIN' and if you are new then please type 'REGISTER'");
		String checkNew=sc.next().toUpperCase();
		if(checkNew.equals("LOGIN")) {
			// Here we are calling studentLogin method
			studentlar.studentLogin();
		}else if(checkNew.equals("REGISTER")){
			// Here we are calling studentRegistration method
			studentlar.studentRegistration();
		}else {
			System.out.println("Please submit your answer correctly..");
			// Here we are calling quizStart method again to continue the loop
			quizStart();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		Quiz quiz=new Quiz();
		// Here we are calling quizStart method
		quiz.quizStart();
		
	}
}
