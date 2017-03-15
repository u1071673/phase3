package cs5530;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback {
		public Feedback()
		{}

		public String getAllOtherUserFeedback(String login, Statement stmt)
		{
			String sql="select * from Feedback where login != '"+login+"'";
			String output="";
			ResultSet rs=null;
			System.out.println("executing "+sql);
			try{
				rs=stmt.executeQuery(sql);
				System.out.println("fid\t\thid\t\tlogin\t\ttext\t\tscore\t\tfbdate");
				while (rs.next())
				{
					output+=rs.getString("fid")+"\t\t"
							+rs.getString("hid")+"\t\t"
							+rs.getString("login")+"\t\t"
							+rs.getString("text") + "\t\t"
							+rs.getString("score")+"\t\t"
							+rs.getString("fbdate")+"\n";
				}

				rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot execute the query");
			}
			finally
			{
				try{
					if (rs!=null && !rs.isClosed())
						rs.close();
				}
				catch(Exception e)
				{
					System.out.println("cannot close resultset");
				}
			}
			return output;
		}

	public String getFeedback(String fid, String hid, Statement stmt)
	{
			String sql="select * from Favorites where fid = "+fid+" and hid = '"+hid+"'";
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
					//System.out.print("cname:");
				        output+=rs.getString("fid")+"   "+rs.getString("hid")+ rs.getString("text") + "\n";
				 }
			     
			     rs.close();
   		 	}
   		 	catch(Exception e)
   		 	{
   		 		System.out.println("cannot execute the query");
   		 	}
   		 	finally
   		 	{
   		 		try{
	   		 		if (rs!=null && !rs.isClosed())
	   		 			rs.close();
   		 		}
   		 		catch(Exception e)
   		 		{
   		 			System.out.println("cannot close resultset");
   		 		}
   		 	}
		    return output;
		}

	public String getLoginFeedback(String fid, Statement stmt)
	{
		String sql="select DISTINCT login from Feedback where fid = "+fid;

		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+=rs.getString("login");
			}

			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}
	public void addFeedback(String fid, String hid, String text, String score, Date fbdate, Statement stmt)
	{

		String sql="INSERT INTO Feedback (fid, hid, text, score, fbdate) VALUES ("+fid+", "+hid+", " + text +", "+score+", "+", "+fbdate+", "+")";
		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}
}