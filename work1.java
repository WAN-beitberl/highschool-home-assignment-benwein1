import java.sql.*;
import java.util.Scanner;
public class work1 {
	
	public static void main(String[] args) 
	{
	    Scanner reader = new Scanner(System.in);
		int option = 0;
		while(option!=8)
		{
		System.out.println("welcome miss sima! in this automatic report you will easily get all the information you need to know about your students!");
		System.out.println("please choose one of the options:");
		System.out.println("option 1 - grade average of the entire school");
		System.out.println("option 2 - grade average of the boys");
		System.out.println("option 3 - grade average of the girls");
		System.out.println("option 4 - average hight of students above 2 meters who have a purple car");
		System.out.println("option 5 - insert id of a student , and get the id of his freinds and the id of their freinds");
		System.out.println("option 6 - get the precent of the popular students, the regulars and the lonely ones");
		System.out.println("option 7 - insert id of a student and get his grade average");
		System.out.println("option 8 - exit the program:)");
		System.out.print("your option: ");
        option = reader.nextInt();
		
        //
		if(option==1) 
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/talbar","root","Benben9611!");
				
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select AVG(grade_avg) as school_avg from highschool");
				
				rs.next();
			    
				double school_avg = rs.getDouble("school_avg");

				System.out.println("school grade average: "+school_avg);

				st.close();
				con.close();
				}catch(Exception e) {System.out.println(e);}
		}
		//
		if(option==2) 
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/talbar","root","Benben9611!");
				
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select AVG(grade_avg) as boys_avg from highschool where gender = 'Male' ");
				
				rs.next();
			    
				double boys_avg = rs.getDouble("boys_avg");

				System.out.println("boys grade average: "+boys_avg);

				st.close();
				con.close();
				}catch(Exception e) {System.out.println(e);}
		}
		//
		if(option==3) 
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/talbar","root","Benben9611!");
				
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select AVG(grade_avg) as girls_avg from highschool where gender = 'Female' ");
				
				rs.next();
			    
				double girls_avg = rs.getDouble("girls_avg");

				System.out.println("girls grade average: "+girls_avg);

				st.close();
				con.close();
				}catch(Exception e) {System.out.println(e);}
		}
		//
		if(option==4) 
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/talbar","root","Benben9611!");
				
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select AVG(cm_height) as hight_avg from highschool where cm_height>=200 and car_color = 'purple' ");
				
				rs.next();
			    
				double hight_avg = rs.getDouble("hight_avg");

				System.out.println("the average is: "+hight_avg);

				st.close();
				con.close();
				}catch(Exception e) {System.out.println(e);}
		}
		
		if(option==5) 
		{
			System.out.print("enter student id: ");
			int student_id = reader.nextInt();
			
			try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/talbar","root","Benben9611!");

		    // Find all friends of the given student Id
		    String sql = "SELECT DISTINCT friend_id, other_friend_id FROM highschool_friendships WHERE friend_id = ?";
		    try (PreparedStatement stmt = con.prepareStatement(sql)) {
		        stmt.setInt(1, student_id);
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		            System.out.println("Friend: " + rs.getInt("friend_id") + ", Other friend: " + rs.getInt("other_friend_id"));
		        }
		    }

		    // Find all friends of the friends of the given friendId
		    sql = "SELECT DISTINCT friend_id, other_friend_id FROM highschool_friendships "
		            + "WHERE friend_id IN (SELECT DISTINCT other_friend_id "
		            + "FROM highschool_friendships WHERE friend_id = ?) AND other_friend_id != ?";
		    try (PreparedStatement stmt = con.prepareStatement(sql)) {
		        stmt.setInt(1, student_id);
		        stmt.setInt(2, 26);
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		            System.out.println("Friend of friend: " + rs.getInt("friend_id") + ", Other friend of friend: " + rs.getInt("other_friend_id"));
		        }
		    }
			}catch(Exception e) {System.out.println(e);}

		}
		
		if(option==6)
		{
			double countLonely = 0;
			double countPopular = 0;
			double countRegular = 0;
			try 
			{
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/talbar","root","Benben9611!");
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select id, friend_id, other_friend_id from highschool_friendships");
				
				while(rs.next())
				{
					if(rs.getInt("friend_id") > 0 && rs.getInt("other_friend_id") > 0 && rs.getInt("friend_id") < 1001 && rs.getInt("other_friend_id") < 1001)
						countPopular++;
					else
					{
						if((rs.getInt("friend_id") > 0 && rs.getInt("other_friend_id") > 0) || (rs.getInt("friend_id") < 1001 && rs.getInt("other_friend_id") < 1001))
							countRegular++;
						else
							countLonely++;
					}
				}
				double pLonely = (countLonely / 1000) * 100;
				double pRegular = (countRegular / 1000) * 100;
				double pPopular = (countPopular / 1000) * 100;
				
				System.out.println("The percentage of popular students: " + pPopular + " regular students: " + pRegular + " lonely students:" + pLonely);
				
				st.close();
				con.close();
			}
			
			catch(Exception e) 
			{
				System.out.println(e);
			}
}
		if(option==7) 
		{
			System.out.print("enter student id:");
			int student_id = reader.nextInt();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/talbar","root","Benben9611!");
				
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(" select grade_avg from talbar.studentgrades where id = "+student_id);
				
				rs.next();
			    
				double grade_avg = rs.getDouble("grade_avg");

				System.out.println("the average grade of: "+student_id+" is: "+grade_avg);

				st.close();
				con.close();
				}catch(Exception e) {System.out.println(e);}
		}
		
		if(option==8) 
		{
			System.out.println("bye bye!!:)");
			break;
		}
		}
		
	}
}




