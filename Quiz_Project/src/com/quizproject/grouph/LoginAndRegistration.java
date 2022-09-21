package com.quizproject.grouph;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginAndRegistration {
	Scanner sc=new Scanner(System.in);
	String number=null;
	String choice;
	int count=0;
	Quiz qs = new Quiz();
	List<String> questionList=new ArrayList<String>();
	//creating Object of QuestionAnswerDetails class
	QuestionAnswerDetails getDetails=new QuestionAnswerDetails();
	PreparedStatement ps=null;
	ResultSet rs=null;
	Connection con=null;
	
	/*
	 * Here we are validating userName and Password
	 */
	public void studentLogin(){
		System.out.println("Please enter your Roll number");
		String rollNumber=sc.next();
		System.out.println("Please enter your Name");
		String studentName=sc.next();
		try {
			GetConnection connection=new GetConnection();
			con=connection.getConnectionDetails();
			ps=con.prepareStatement("select * from student");
			rs=ps.executeQuery();
			while(rs.next()) {
				if(rollNumber.equals(rs.getString(1)) && studentName.equals(rs.getString(2))){
					count++;
					/*
					 * Taking input from the user to check user's choice
					 */
					System.out.println("To see your score please type 'Score'");
					System.out.println("and to play the Quiz please type 'Quiz'");
					System.out.println("To see all score please type 'AllScores'");
					choice=sc.next().toUpperCase();
					//Here getScoreOrQuiz method calling
					getScoreOrQuiz(choice,rollNumber);
				}
			}
			if(count==0) {
				System.out.println("Please enter valid rollNumber and userName");
				//Here studentLogin method calling
				studentLogin();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	public void getScoreOrQuiz(String studentIntrest,String rollNumber){
		try {
			if(choice.equals("SCORE")) {
				//Here getScore method calling
				getDetails.getScore(rollNumber);
				System.out.println("Thanks You...");
				//Here quizStart method calling
				qs.quizStart();
			}else if(choice.equals("ALLSCORES")) {
				//Here getAllScoreDetails method calling
				getDetails.getAllScoreDetails();
				qs.quizStart();
			}else if(choice.equals("QUIZ")) {
				//Here getQuestionDetails method calling
				getDetails.getQuestionDetails();
				
	         	//Here we are asking to user if he or she interested to play
		      for(int i=1;i<=10;i++) {
					System.out.println("");
					System.out.println("If you want to continue to play Quiz type 'YES' for not 'NO'");
					String ans=sc.next().toUpperCase();
					if(ans.equals("YES")) {
						System.out.println("Enter the Question number");
			     		number=sc.next();
						questionList.add(number);
						getDetails.selectQuestion(number);
					}else {
						System.out.println("Thank you....");
						break;
					}	
				}
				//Here addScore method calling
				getDetails.addScore(questionList, rollNumber);
				qs.quizStart();
			}else {
				System.out.println("Please type correct input");
				System.out.println("You are loged out now, please login again");
				//Here studentLogin method calling
				studentLogin();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * studentRegistration method is for new student registration
	 */
	public void studentRegistration() {
		StudentDetails sd=new StudentDetails();
		System.out.println("Plese enter your Roll number");
		String studentRollNumber=sc.next();
		sd.setRollNumber(studentRollNumber);
		
		System.out.println("Plese enter your Name");
		String studentName=sc.next();
		sd.setName(studentName);
		
		try {
			GetConnection connection=new GetConnection();
			con=connection.getConnectionDetails();
			ps=con.prepareStatement("insert into student (studentRollNumber,studentName) values(?,?)");
			ps.setString(1, sd.getRollNumber());
			ps.setString(2, sd.getName());
			ps.executeUpdate();
			System.out.println("You have successfully registered yourself.");
			System.out.println("Now Please login yourself.");
			//Here studentLogin method calling
			studentLogin();
		}catch(Exception e) {
			System.out.println("Please Enter Another Roll Number");
			//Here we are calling again studentRegistration method
			studentRegistration();
		
		}
	}       
	        public int getSingleStudentScore(String rollNumber) {
	        	try {GetConnection connection=new GetConnection();
				con=connection.getConnectionDetails();
				
	        ps=	con.prepareStatement("select*from student_record where student_id=?");
	        	ps.setString(1, rollNumber);
	        ResultSet rs=	ps.executeQuery();
	        rs.next();
	             String stutablerecord=    rs.getString("student_id");
	        if(stutablerecord.equals(rollNumber)) {
	        	return 1;
	        }
	        else {
	        	return 0;
	        }
	        	}catch (Exception e) {
					return 0;
				}
	        	
	        }
}



