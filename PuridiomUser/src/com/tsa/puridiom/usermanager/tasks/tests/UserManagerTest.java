package com.tsa.puridiom.usermanager.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.usermanager.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Creation date: (6/22/2004)
 * @author: Kelli
 */
public class UserManagerTest
{
	private String sessionId = "000000111111";
	private String mailId = "kelli@tsagate.com";
	private String userId = "KELLI";
	private String organizationId = "PURIDIOM";
	private String password = "test";
	
	/**
	 * TestUserAdminManager constructor comment.
	 */
	public UserManagerTest()
	{
		super();
	}
	
	public static void main(String args[])
	{
		UserManagerTest test = new UserManagerTest();
		
		try
		{
			if (!test.testSetFailedLoginAttempt())
			{
				System.out.println("First SetFailedLoginAttempt FAILED.");
			}
			else if (!test.testSetFailedLoginAttempt())
			{
				System.out.println("Second SetFailedLoginAttempt FAILED.");
			}
			else if (!test.testGetFailedLoginAttemptsByLoginId())
			{
				System.out.println("GetFailedLoginAttemptsByLoginId FAILED.");
			}
			else if (!test.testGetFailedLoginAttemptsBySessionId())
			{
				System.out.println("GetFailedLoginAttemptsBySessionId FAILED.");
			}
			else if (!test.testResetFailedLoginAttempts())
			{
				System.out.println("ResetFailedLoginAttempts FAILED.");
			}
			else if (!test.testSetUserInCache())
			{
				System.out.println("SetUserInCache FAILED.");
			}
			else if (!test.testGetUser())
			{
				System.out.println("getUser FAILED.");
			}
			else if (!test.testGetUserRole())
			{
				System.out.println("getUserRole FAILED.");
			}
			else if (!test.testIsUserRegistered())
			{
				System.out.println("isUserRegistered FAILED.");
			}
			else
			{
				System.out.println("TEST WAS SUCCESSFUL!");
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION ERROR: " + e.getMessage());
		}
	}
	
	/**
	 * @return boolean
	 * @exception java.lang.Exception The exception description.
	 */
	public boolean testSetFailedLoginAttempt() throws java.lang.Exception
	{
		System.out.println("TEST SetFailedLoginAttempt BEGIN...");
		
		boolean success = false;
		
		try
		{
			UserManager userManager = UserManager.getInstance();
			
			userManager.setFailedLoginAttempt(this.sessionId, this.mailId);
			
			success = true;
			
			System.out.println("TEST SetFailedLoginAttempt COMPLETE!");
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION ERROR in TEST SetFailedLoginAttempt: " + e.getMessage());
			throw e;
		}
		finally
		{
			return success;
		}
	}
	
	/**
	 * @return boolean
	 * @exception java.lang.Exception The exception description.
	 */
	public boolean testGetFailedLoginAttemptsByLoginId() throws java.lang.Exception
	{
		System.out.println("TEST GetFailedLoginAttemptsByLoginId BEGIN...");
		boolean success = false;
		
		try
		{
			UserManager userManager = UserManager.getInstance();						

			int attempts = userManager.getFailedLoginAttempts(this.sessionId, this.mailId);
			
			System.out.println("TEST GetFailedLoginAttemptsByLoginId attempts = " + attempts);
			success = true;
			
			System.out.println("TEST GetFailedLoginAttemptsByLoginId COMPLETE!");
		}
		catch (Throwable throwable)
		{
			throw throwable;
		}
		finally
		{
			return success;
		}
	}
	
	/**
	 * @return boolean
	 * @exception java.lang.Exception The exception description.
	 */
	public boolean testGetFailedLoginAttemptsBySessionId() throws java.lang.Exception
	{
		System.out.println("TEST GetFailedLoginAttemptsBySessionId BEGIN...");
		boolean success = false;
		
		try
		{
			UserManager userManager = UserManager.getInstance();						

			int attempts = userManager.getFailedLoginAttempts(this.sessionId);
			
			System.out.println("TEST GetFailedLoginAttemptsBySessionId attempts = " + attempts);
			success = true;
			
			System.out.println("TEST GetFailedLoginAttemptsBySessionId COMPLETE!");
		}
		catch (Throwable throwable)
		{
			throw throwable;
		}
		finally
		{
			return success;
		}
	}
	/**
	 * @return boolean
	 * @exception java.lang.Exception
	 */
	public boolean testGetUser() throws java.lang.Exception
	{
		System.out.println("TEST getUser() BEGIN...");
		UserProfile userProfile = null;
		boolean success = false;
		
		try
		{
			UserManager userManager = UserManager.getInstance();
			userProfile = userManager.getUser(this.organizationId, this.userId);
			
			if (userProfile == null)
			{
				System.out.println("getUser failed and returned a null UserProfile object.");
			}
			else
			{
				success = true;
			}
			
			System.out.println("TEST getUser() COMPLETE!");
		}
		catch (Throwable throwable)
		{
			throw throwable;
		}
		finally
		{
			System.out.println("Returning User: " + userProfile.toString());
			return success;
		}
	}
	
	/**
	 * @return boolean
	 * @exception java.lang.Exception The exception description.
	 */
	public boolean testSetUserInCache() throws java.lang.Exception
	{
		System.out.println("TEST SetUserInCache BEGIN...");
		boolean success = false;
		
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
			Calendar rightNow = Calendar.getInstance();
			Date	today = rightNow.getTime();
			
			UserManager userManager = UserManager.getInstance();						
			UserProfile userProfile = new UserProfile();
			userProfile.setMailId(this.mailId);
			userProfile.setUserId(this.userId);
			userProfile.setOrganizationId(this.organizationId);

			userManager.setUserInCache(userProfile);
			
			success = true;
			
			System.out.println("TEST setUserInCache COMPLETE!");
		}
		catch (Throwable throwable)
		{
			throw throwable;
		}
		finally
		{
			return success;
		}
	}
	
	/**
	 * @return boolean
	 * @exception java.lang.Exception
	 */
	public boolean testResetFailedLoginAttempts() throws java.lang.Exception
	{
		System.out.println("TEST ResetFailedLoginAttempts BEGIN...");
		
		try 
		{
			UserManager userManager = UserManager.getInstance();
		
			userManager.resetFailedLoginAttempts(this.sessionId);
		}
		catch(Exception e)
		{
			System.out.println("FAILURE: " + e.getMessage());
			return false;
		}
	
		System.out.println("TEST deleteUser() COMPLETE!");
		return true;
	}

	/**
	 * @return boolean
	 * @exception java.lang.Exception
	 */
	public boolean testGetUserRole() throws java.lang.Exception
	{
		System.out.println("TEST getUserRole() BEGIN...");
		UserRole userRole = null;
		boolean success = false;
		
		try
		{
			UserManager userManager = UserManager.getInstance();
			userRole = userManager.getUserRole(this.organizationId, this.userId);
			
			if (userRole == null)
			{
				System.out.println("getUserRole failed and returned a null UserRole object.");
			}
			else
			{
				success = true;
			}
			
			System.out.println("TEST getUserRole() COMPLETE!");
		}
		catch (Throwable throwable)
		{
			throw throwable;
		}
		finally
		{
			System.out.println("Returning UserRole: " + userRole.toString());
			return success;
		}
	}
	
	/**
	 * @return boolean
	 * @exception java.lang.Exception
	 */
	public boolean testIsUserRegistered() 
	{
		System.out.println("TEST isUserRegistered() BEGIN...");
		boolean registered = false;
		boolean success = false;
		
		try
		{
			UserManager userManager = UserManager.getInstance();
			registered = userManager.isUserRegistered(this.organizationId, this.userId);
			
			success = true;
			
			System.out.println("TEST isUserRegistered() COMPLETE!");
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		    success = false;
		}
		catch (Throwable throwable)
		{
		    throwable.printStackTrace();
		    success = false;
		}

		System.out.println("Returning: " + String.valueOf(registered));
		return success;
	}
	
	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.usermanager.tests.TestUserManager]");
		return sb.toString();
	}
}

