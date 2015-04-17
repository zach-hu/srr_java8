package com.tsa.puridiom.supplierportal;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

/**
 * Creation date: March 2004
 * @author: Kelli Knisely
 */
public class BidBoardUserManager
{
	private static BidBoardUserManager INSTANCE = new BidBoardUserManager();
	private HashMap organizations = new HashMap();
	private HashMap attemptedLogins = new HashMap();

	private BidBoardUserManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.supplierportal.UserManager
	 */
	public static BidBoardUserManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new BidBoardUserManager();
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
	 * @return BidBoardUser
	 */
	public BidBoardUser getUserInCache(String organizationId, String userId)
	{
	    if (Utility.isEmpty(userId)) {
	        return null;
	    }
		HashMap users = new HashMap();
		if (this.organizations.containsKey(organizationId)) {
			users = (HashMap) this.organizations.get(organizationId);
		}
		return (BidBoardUser) users.get(userId.toUpperCase());
	}
	/**
	 * @param user BidBoardUser
	 * @exception java.lang.Exception The exception description.
	 */
	public void setUserInCache(BidBoardUser user) throws Exception
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
     * @return int
     * @param loginSessionId String
     * @exception java.lang.Exception The exception description.
     */
    public int getFailedLoginAttempts(String loginSessionId) throws java.lang.Exception
    {
        int	iattempts = 0;

        try
        {
            if (this.attemptedLogins.containsKey(loginSessionId))
            {
                Map loginIds = (Map) this.attemptedLogins.get(loginSessionId);
                if (loginIds != null)
                {
                    Iterator loginIterator = loginIds.keySet().iterator();
                    while (loginIterator.hasNext())
                    {
                        iattempts = iattempts + ((Integer) loginIds.get((String) loginIterator.next())).intValue();
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return iattempts;
    }

    /**
     * @return int
     * @param loginSessionId String
     * @exception java.lang.Exception The exception description.
     */
    public int getFailedLoginAttempts(String loginSessionId, String mailId) throws java.lang.Exception
    {
        int	iattempts = 0;

        try
        {
            if (this.attemptedLogins.containsKey(loginSessionId))
            {
                Map loginIds = (Map) this.attemptedLogins.get(loginSessionId);
                if (loginIds != null && loginIds.containsKey(mailId))
                {
                    iattempts = ((Integer) loginIds.get(mailId)).intValue();
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return iattempts;
    }

    /**
     * @param loginSessionId String
     * @exception java.lang.Exception The exception description.
     */
    public void resetFailedLoginAttempts(String loginSessionId) throws java.lang.Exception
    {
        try
        {
            if (this.attemptedLogins.containsKey(loginSessionId))
            {
                this.attemptedLogins.remove(loginSessionId);
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
	public BidBoardUser setFailedLoginAttempt(Map incomingRequest) throws java.lang.Exception
	{
		BidBoardUser user = null;

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
						user = (BidBoardUser) users.get(userId);
					}
				}
				if (user == null)
				{
					user = new BidBoardUser();
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
				BidBoardUser user = (BidBoardUser) users.get((String) iterator.next());

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
		sb.append("[ClassName=com.tsa.puridiom.supplierportal.UserManager]");
		return sb.toString();
	}
}
