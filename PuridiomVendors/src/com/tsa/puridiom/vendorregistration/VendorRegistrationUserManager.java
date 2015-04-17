/*
 * Created on July 25, 2006
 */
package com.tsa.puridiom.vendorregistration;

import com.tsagate.foundation.utility.*;
import java.util.*;

/**
 * @author Kelli
 */
public class VendorRegistrationUserManager
{
	private static VendorRegistrationUserManager INSTANCE = new VendorRegistrationUserManager();
	private HashMap organizations = new HashMap();

	private VendorRegistrationUserManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager
	 */
	public static VendorRegistrationUserManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new VendorRegistrationUserManager();
		}
		return INSTANCE;
	}
	/**
	 * @return HashMap
	 */
	public HashMap getUserCache(String organizationId)
	{
		return (HashMap) this.organizations.get(organizationId);
	}
	/**
	 * @return VendorRegistrationUser
	 */
	public RegisterUser getUserInCache(String organizationId, String userId)
	{
	    if (Utility.isEmpty(userId)) {
	        return null;
	    }
		HashMap users = new HashMap();
		if (this.organizations.containsKey(organizationId)) {
			users = (HashMap) this.organizations.get(organizationId);
		}
		return (RegisterUser) users.get(userId.toUpperCase());
	}
	/**
	 * @param user VendorRegistrationUser
	 * @exception java.lang.Exception The exception description.
	 */
	public void setUserInCache(RegisterUser user) throws Exception
	{
		try
		{
			if (user == null) throw new Exception ("User object was null.");
			
			String	userId = user.getEmailAddress().toUpperCase();
			String	organizationId = user.getOrganizationId();
			
			if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(userId))
			{
				HashMap users = new HashMap();
				
				if (this.organizations.containsKey(organizationId))
				{
					users = (HashMap) this.organizations.get(organizationId);
				}
				
				users.put(userId, user);
				
				this.organizations.put(organizationId, users);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	/**
	 * @return HashMap
	 * @param incomingRequest Map
	 * @exception java.lang.Exception The exception description.
	 */
	public RegisterUser setFailedLoginAttempt(Map incomingRequest) throws java.lang.Exception
	{
		RegisterUser user = null;
		
		try
		{
			String	userId = (String) incomingRequest.get("userId");
			String	organizationId = (String) incomingRequest.get("organizationId");
			
			if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(userId))
			{
				HashMap users = new HashMap();
				
				if (this.organizations.containsKey(organizationId)) 
				{
					users = (HashMap) organizations.get(organizationId);
					if (users.containsKey(userId))
					{
						user = (RegisterUser) users.get(userId);
					}
				}
				if (user == null)
				{
					user = new RegisterUser();
					user.setEmailAddress(userId);
				}
			
				user.setAuthenticated(false);
				user.setLoginAttempts(user.getLoginAttempts() + 1);
				
				users.put(userId, user);
				this.organizations.put(organizationId, users);
			}
		}
		catch (Throwable throwable)
		{
			throw throwable;
		}
		finally
		{
			return user;
		}
	}
	/**
	 * @return HashMap
	 * @param incomingRequest Map
	 * @exception java.lang.Exception The exception description.
	 */
	public List getUserLog(String organizationId) throws java.lang.Exception
	{
		List	userList = new ArrayList();
		
		try
		{
			HashMap users = new HashMap();
			if (this.organizations.containsKey(organizationId))
			{
				//get the users for the specified organization
				users = (HashMap) this.organizations.get(organizationId);
			}
			Iterator iterator = users.keySet().iterator();
			
			while (iterator.hasNext())
			{
				RegisterUser user = (RegisterUser) users.get((String) iterator.next());
				
				if (user != null && user.isAuthenticated())
				{
					userList.add(user);
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return userList;
	}	
	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager]");
		return sb.toString();
	}
}
