package com.tsa.puridiom.timer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimeZone;

import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.properties.DictionaryManager;

public class ConnectionJob extends ScheduledJob
{
	public static void main(String[] args) {
		ConnectionJob cj = new ConnectionJob();
		cj.sendEmail(cj.testConnection());
	}
	public void execute()
	{
		this.sendEmail(this.testConnection());
		System.out.println("job done");
		System.out.println(this.toString());
	}

	protected void sendEmail(boolean connected)
	{

		if(!connected)
		{
			StringBuffer messageText = new StringBuffer("Database Connection Failure: " );
			messageText.append(this.getOrganizationId());
			messageText.append("\r\n");
			messageText.append("url = " + DictionaryManager.getInstance("dbconnection", this.getOrganizationId()).getProperty("url", "jdbc:oracle:thin:@216.37.169.78:1521:hilton1p"));
			messageText.append("userId = " + DictionaryManager.getInstance("dbconnection", this.getOrganizationId()).getProperty("userid",  "hiltontestuser"));
			messageText.append("\r\n");
			messageText.append("DateTime: ");
			messageText.append(Dates.today("", ""));
			messageText.append(Dates.getNow("", ""));
            messageText.append(TimeZone.getDefault().getID());
			messageText.append("\r\n");

			try
			{
				String to = DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.to", "test1@puridiom.com");
				String from = DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.from", "support@puridiom.com");
				EmailManager.getInstance().sendEmail(from, to, null, "Database Connection Failure", messageText.toString(), null, this.getOrganizationId());
			} catch (EmailsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected boolean testConnection()
	{
		Connection conn = null;
		boolean ret = false;
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			String url = DictionaryManager.getInstance("dbconnection", this.getOrganizationId()).getProperty("url", "jdbc:oracle:thin:@216.37.169.78:1521:hilton1p");
			String userId = DictionaryManager.getInstance("dbconnection", this.getOrganizationId()).getProperty("userid",  "hiltontestuser");
			String pass = DictionaryManager.getInstance("dbconnection", this.getOrganizationId()).getProperty("password",  "hiltontestuserp");
			conn = DriverManager.getConnection(url, userId, pass);
			this.executeStatement(conn);
			ret = true;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	protected void executeStatement(Connection conn)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * from send_queue where status = '00'");
			rs = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
				}
				if(ps != null)
				{
					ps.close();
				}
			}
			catch (SQLException e) {
				// TODO: handle exception
			}

		}
	}
}
