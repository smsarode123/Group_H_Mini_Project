package com.quizproject.grouph;

import java.sql.*;
import java.util.*;

public class QuestionAnswerDetails {
	PreparedStatement ps=null;
	ResultSet rs=null;
	Connection con=null;
	List<String> ansList=new ArrayList<String>();
	int ansCount=0;
	String grade=null;
	
	/*
	 * By using getQuestionDetails method we are getting question details
	 */
	public void getQuestionDetails() throws SQLException {
		try {
			GetConnection connection=new GetConnection();
			con=connection.getConnectionDetails();
			ps=con.prepareStatement("select * from quiz_questions");
			rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println("Q "+rs.getString(1)+") "+rs.getString(2));
				System.out.println("A)"+rs.getString(3));
				System.out.println("B)"+rs.getString(4));
				System.out.println("C)"+rs.getString(5));
				System.out.println("D)"+rs.getString(6));
				System.out.println("==============================================");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * with the help of getUserInput method we are taking input from the user 
	 * to the question answer
	 */
	public void getUserInput() throws SQLException {
	      	Scanner sc = null ;
		try {
		    sc=new Scanner(System.in);
			System.out.println("");
			System.out.println("Please submit your answer as 'A' 'B' 'C' and 'D'");
			String ans=sc.next().toUpperCase();
			if(ans.equals(rs.getString(7))) {
				System.out.println("Answer is correct....");
				ansList.add("1");
			}else {
				ansList.add("0");
				System.out.println("not correct....");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * selectQuestion method shows particular Question to the user
	 */
	public void selectQuestion(String value) {
		try {
			rs=ps.executeQuery();
			while(rs.next()) {
				if(value.equals(rs.getString(1))) {
					System.out.println("Q "+rs.getString(1)+") "+rs.getString(2));
					System.out.println("A)"+rs.getString(3));
					System.out.println("B)"+rs.getString(4));
					System.out.println("C)"+rs.getString(5));
					System.out.println("D)"+rs.getString(6));
					getUserInput();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * addScore method is used to insert marks inside the database
	 */
	public void addScore(List<String> queList, String rollNumber) {
		LoginAndRegistration slar=new LoginAndRegistration();
		int flag=slar.getSingleStudentScore(rollNumber);
		if(flag==0) {
		try {
			
			ps=con.prepareStatement("insert into student_record (one,two,three,four,five,six,seven,eight,nine,ten,total_marks,grade,student_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			Iterator<String> ansitr = ansList.iterator();
			System.out.println("Your attempt Questions are = "+queList);
			System.out.println("Your Question wise answers are = "+ansList);
			while(ansitr.hasNext()) {
				if(ansitr.next().equals("1")) {
					ansCount++;
				}
			}
		
			if(ansCount>=8) {
				grade="Grade A";
			}else if(ansCount>=6 && ansCount<=7) {
				grade="Grade B";
			}else if(ansCount==5) {
				grade="Grade C";
			}else {
				grade="Fail";
			}
			System.out.println("Total score "+ansCount+"/10");

			System.out.println("Student grade="+grade);

			for(int i=1;i<=10;i++) {
				String q=Integer.toString(i);
				if(queList.contains(q)) {
					int queIndex=queList.indexOf(q);
					String queValue=queList.get(queIndex);
					int q1=Integer.parseInt(queValue);
					String ansValue=ansList.get(queIndex);
					ps.setString(q1,ansValue);
					
				}else {
					ps.setString(i,"0");
				}
			}
			ps.setInt(11,ansCount);
			ps.setString(12,grade);
			ps.setString(13,rollNumber);
			ps.executeUpdate();
		   }catch(Exception e) {
			e.printStackTrace();
		   }}
		    else {
			  try {
				
				ps=con.prepareStatement("update student_record set one=?,two=?,three=?,four=?,five=?,six=?,seven=?,eight=?,nine=?,ten=?,total_marks=?,grade=? where student_id=?");
				
				Iterator<String> ansitr = ansList.iterator();
				System.out.println("Your attempted Questions are = "+queList);
				System.out.println("Your Question wise answers are = "+ansList);
				while(ansitr.hasNext()) {
					if(ansitr.next().equals("1")) {
						ansCount++;
					}
				}
			
				if(ansCount>=8) {
					grade="Grade A";
				}else if(ansCount>=6 && ansCount<=7) {
					grade="Grade B";
				}else if(ansCount==5) {
					grade="Grade C";
				}else {
					grade="Fail";
				}
				System.out.println("Total Score "+ansCount+"/10");
				System.out.println("Student grade="+grade);

				for(int i=1;i<=10;i++) {
					String q=Integer.toString(i);
					if(queList.contains(q)) {
						int queIndex=queList.indexOf(q);
						String queValue=queList.get(queIndex);
						int q1=Integer.parseInt(queValue);
						String ansValue=ansList.get(queIndex);
						ps.setString(q1,ansValue);
						
					}else {
						ps.setString(i,"0");
					}
				}
				    ps.setInt(11,ansCount);
				    ps.setString(12,grade);
				    ps.setString(13,rollNumber);
				    ps.executeUpdate();
		  	}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * getScore method show marksheet 
	 */
	public void getScore(String rollNumber) {
	
		try {
			GetConnection connection=new GetConnection();
			con=connection.getConnectionDetails();
			ps=con.prepareStatement("select student.studentRollNumber, student.studentName, student_record.total_marks, student_record.grade from student INNER JOIN student_record ON student.studentRollNumber=student_record.student_id where student_record.student_id=?");
			ps.setString(1, rollNumber);
			rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println("Roll Number = "+rs.getString(1));
				System.out.println("Name        = "+rs.getString(2));
				System.out.println("Total Score = "+rs.getString(3));
				System.out.println("Grade       = "+rs.getString(4));
				System.out.println("==============================================");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Here getAllScoreDetails method is used to print all student score
	 * on console
	 */
	public void getAllScoreDetails() {
		try {
			GetConnection connection=new GetConnection();
			con=connection.getConnectionDetails();
			ps=con.prepareStatement("select student.studentRollNumber, student.studentName, student_record.total_marks, student_record.grade from student INNER JOIN student_record ON student.studentRollNumber=student_record.student_id");
			rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println("Roll Number = "+rs.getString(1));
				System.out.println("Name        = "+rs.getString(2));
				System.out.println("Total Score = "+rs.getString(3));
				System.out.println("Grade       = "+rs.getString(4));
				System.out.println("==============================================");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}