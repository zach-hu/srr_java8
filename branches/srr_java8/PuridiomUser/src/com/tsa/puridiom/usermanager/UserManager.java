package com.tsa.puridiom.usermanager;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * Creation date: June 2004
 * @author: Kelli Knisely
 */
public class UserManager
{
    private static UserManager INSTANCE = new UserManager();
    private HashMap organizations = new HashMap();
//    private HashMap organizationGroups = new HashMap();
    private HashMap attemptedLogins = new HashMap();
    private HashMap usersLogged = new HashMap();
//    private long lastCleanup = 0;

    private UserManager()
    {
        super();
    }
    /**
     * @return com.tsa.puridiom.usermanager.UserManager
     */
    public static UserManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new UserManager();
        }
        return INSTANCE;
    }

    /**
     * @return com.tsa.puridiom.entity.UserProfile
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public UserProfile getUser(String organizationId, String userId) throws java.lang.Exception
    {
        UserProfile userProfile = null;

        try
        {
            if (Utility.isEmpty(userId) || Utility.isEmpty(organizationId))
            {
                userProfile = new UserProfile();
                return userProfile;
            }
            else if(userId.equalsIgnoreCase("AUTO"))
            {
                userProfile = new UserProfile();
                userProfile.setFirstName("System");
                userProfile.setLastName("Automation");
                return userProfile;
            }
            else
            {
                userId = userId.toUpperCase();
                organizationId = organizationId.toUpperCase();

                Map users = new HashMap();
                if (this.organizations.containsKey(organizationId))
                {
                    users = (Map) this.organizations.get(organizationId);
                }
                if (users.containsKey(userId))
                {
                    userProfile = (UserProfile) users.get(userId);
                }
                else
                {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("userId", userId);
                    processParameters.put("UserProfile_organizationId", organizationId);
                    processParameters.put("UserProfile_userId", userId);
                    processParameters.put("UserGroupRel_userId", userId);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("userprofile-detail-retrieve-by-userid.xml");

                    process.executeProcess(processParameters);

                    userProfile = (UserProfile) processParameters.get("userProfile");

                    if (userProfile != null)
                    {
                        userProfile.setRegistered(true);
                        users.put(userId, userProfile);
                    }
                    else
                    {
                        userProfile = new UserProfile();
                        userProfile.setRegistered(false);
                        userProfile.setUserId(userId);
                        userProfile.setOrganizationId(organizationId);
                    }
                    this.organizations.put(organizationId, users);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return userProfile;
        }
    }

    /**
     * @return com.tsa.puridiom.entity.UserProfile
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public AppPool getUserPool(String organizationId, String userId) throws java.lang.Exception
    {
    	AppPool userProfile = null;

        try
        {
            if (Utility.isEmpty(userId) || Utility.isEmpty(organizationId))
            {
                userProfile = new AppPool();
                return userProfile;
            }
            else
            {
                userId = userId.toUpperCase();
                organizationId = organizationId.toUpperCase();

                Map users = new HashMap();
                if (this.organizations.containsKey(organizationId))
                {
                    users = (Map) this.organizations.get(organizationId);
                }
                if (users.containsKey(userId))
                {
                    userProfile = (AppPool) users.get(userId);
                }
                else
                {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("AppPool_poolid", userId);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("app-pool-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    userProfile = (AppPool) processParameters.get("appPool");

                    if (userProfile != null)
                    {
                        users.put(userId, userProfile);
                    }
                    else
                    {
                        userProfile = new AppPool();
                        userProfile.setPoolid(userId);
                        userProfile.setPooldesc("");
                    }
                    this.organizations.put(organizationId, users);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return userProfile;
        }
    }

    /***
     *
     * @param organizationId
     * @param mailId
     * @return UserProfile object from mailId
     * @throws java.lang.Exception
     */
    public UserProfile getUserByMailId(String organizationId, String mailId) throws java.lang.Exception
    {
        UserProfile userProfile = null;

        try
        {
            if (Utility.isEmpty(mailId) || Utility.isEmpty(organizationId))
            {
                userProfile = new UserProfile();
                return userProfile;
            }
            else
            {
                mailId = mailId.toUpperCase();
                organizationId = organizationId.toUpperCase();


                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("UserProfile_organizationId", organizationId);
                    processParameters.put("UserProfile_mailId", mailId);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-by-mailid.xml");

                    process.executeProcess(processParameters);

                    if ( processParameters.get("userProfile") instanceof UserProfile ) {
                    	userProfile = (UserProfile) processParameters.get("userProfile");
                    }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return userProfile;
        }
    }
    /**
     * @param userProfile UserProfile
     * @exception java.lang.Exception
     */
    public void setUserInCache(UserProfile userProfile) throws Exception
    {
        try
        {
            if (userProfile == null) throw new Exception ("UserProfile was null.");

            String	userId = userProfile.getUserId();
            String	organizationId = userProfile.getOrganizationId();

            if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(userId))
            {
                HashMap users = new HashMap();

                organizationId = organizationId.trim().toUpperCase();
                userId = userId.trim().toUpperCase();

                if (this.organizations.containsKey(organizationId))
                {
                    users = (HashMap) this.organizations.get(organizationId);
                }

                users.put(userId, userProfile);

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
     * @return int
     * @param loginSessionId String
     * @exception java.lang.Exception The exception description.
     */
    public void setFailedLoginAttempt(String loginSessionId, String mailId) throws java.lang.Exception
    {
        int	iattempts = 0;

        try
        {
            Map loginIds = new HashMap();
            if (this.attemptedLogins.containsKey(loginSessionId))
            {
                loginIds = (Map) this.attemptedLogins.get(loginSessionId);
                if (loginIds != null && loginIds.containsKey(mailId))
                {
                    iattempts = ((Integer) loginIds.get(mailId)).intValue();
                }
            }
            iattempts++;
            loginIds.put(mailId, new Integer(iattempts));
            this.attemptedLogins.put(loginSessionId, loginIds);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     * @return com.tsa.puridiom.entity.UserProfile
     * @param organizationId java.lang.String
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public UserRole getUserRole(String organizationId, String userId) throws java.lang.Exception
    {
        UserRole userRole = new UserRole();

        try
        {
            if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
            {
                UserProfile	user = this.getUser(organizationId, userId);
                if (user != null)
                {
                    List userRoles = user.getUserRoles();
                    if (userRoles == null || userRoles.size() == 0)
                    {
                        // User Roles have not yet been set for this user
                        HashMap processParameters = new HashMap();
                        processParameters.put("organizationId", organizationId);
                        processParameters.put("userId", userId);
                        processParameters.put("UserProfile_organizationId", organizationId);
                        processParameters.put("UserProfile_userId", userId);
                        processParameters.put("UserGroupRel_userId", userId);

                        PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                        PuridiomProcess process = processLoader.loadProcess("usergrouprel-retrieve-by-userid.xml");

                        process.executeProcess(processParameters);

                        List userGroupRelList = (List) processParameters.get("userGroupRelList");

                        userRole = this.setUserRoles(organizationId, user, userGroupRelList);
                    }
                    else
                    {
                        Log.debug(this, "getUserRole for userRoles - "+ userRoles.toString());
                        userRole = UserRoleManager.getInstance().getUserRole(userRoles, user.getOrganizationId());
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return userRole;
        }
    }

    /**
     * @param organizationId String
     * @param userId String
     * @exception java.lang.Exception
     */
    public void removeUserFromCache(String organizationId, String userId) throws Exception
    {
        try
        {
            if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(userId))
            {
                HashMap users = new HashMap();

                organizationId = organizationId.trim().toUpperCase();
                userId = userId.trim().toUpperCase();

                if (this.organizations.containsKey(organizationId))
                {
                    users = (HashMap) this.organizations.get(organizationId);
                }

                users.remove(userId);

                this.organizations.put(organizationId, users);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[ClassName=com.tsa.puridiom.usermanager.UserManager]");
        return sb.toString();
    }
    /**
     * @return java.util.List
     * @param organizationId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public List getBuyerList(String organizationId) throws java.lang.Exception
    {
        List buyerList = new ArrayList();

        try
        {
            if (!Utility.isEmpty(organizationId))
            {
                HashMap processParameters = new HashMap();
                processParameters.put("organizationId", organizationId);
                processParameters.put("UserProfile_buyer", "Y");

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-by.xml");

                process.executeProcess(processParameters);

                buyerList = (List) processParameters.get("userProfileList");

                if (buyerList == null)
                {
                    buyerList = new ArrayList();
                }
            }
        }
        catch (Exception e)
        {
           Log.error(this, e.getMessage());
        }
        return buyerList;
    }
    /**
     * @return java.util.List
     * @param organizationId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public List getEngineerList(String organizationId) throws java.lang.Exception
    {
    	List inspectorList = new ArrayList();

    	try
    	{
    		if (!Utility.isEmpty(organizationId))
    		{
    			HashMap processParameters = new HashMap();
    			processParameters.put("organizationId", organizationId);
    			processParameters.put("UserProfile_engineer", "Y");

    			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
    			PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-by.xml");

    			process.executeProcess(processParameters);

    			inspectorList = (List) processParameters.get("userProfileList");

    			if (inspectorList == null)
    			{
    				inspectorList = new ArrayList();
    			}
    		}
    	}
    	catch (Exception e)
    	{
    		Log.error(this, e.getMessage());
    	}
    	return inspectorList;
    }

    /**
     * @return java.util.List
     * @param organizationId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public List getInspectorList(String organizationId) throws java.lang.Exception
    {
    	List inspectorList = new ArrayList();

    	try
    	{
    		if (!Utility.isEmpty(organizationId))
    		{
    			HashMap processParameters = new HashMap();
    			processParameters.put("organizationId", organizationId);
    			processParameters.put("UserProfile_inspector", "Y");

    			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
    			PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-by.xml");

    			process.executeProcess(processParameters);

    			inspectorList = (List) processParameters.get("userProfileList");

    			if (inspectorList == null)
    			{
    				inspectorList = new ArrayList();
    			}
    		}
    	}
    	catch (Exception e)
    	{
    		Log.error(this, e.getMessage());
    	}
    	return inspectorList;
    }

    /**
     * @param String organizationId
     * @param String userId
     * @exception java.lang.Exception
     */
    public void closeSession(String organizationId, String userId) throws Exception
    {
        try
        {
            if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(userId)) {
                HashMap processParameters = new HashMap();
                processParameters.put("organizationId", organizationId);
                processParameters.put("userId", userId);

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                PuridiomProcess process = processLoader.loadProcess("user-logoff.xml");

                process.executeProcess(processParameters);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    /*
    private void startUserSessionCleanup() {
		class MyRunner implements Runnable {
			public void run() {
				try {
					sessionCleanup();
					lastCleanup = (new Date()).getTime();
				}
				catch (Exception exception) {
				    Log.error(this, exception.getMessage());
					//no need to throw this since the process has exited at this point
				}
			}
		}
        long	currentTime = (new Date()).getTime();
	    //  The browse cleanup should be triggered every 3600000 milliseconds (1 hour)
        if (lastCleanup == 0) {
            // Server just started - first access set lastCleanup to current Time
            lastCleanup = currentTime;
        }
        if ((lastCleanup - currentTime) > 3600000) {
            new Thread(new MyRunner()).start();
        }
    }

	private void sessionCleanup() {
		try {
		    Set oidSet = this.organizations.keySet();
		    Iterator iterator = oidSet.iterator();
		    long currentTime = (new java.util.Date()).getTime();
		    Map organizationsToReset = new HashMap();

		    while (iterator.hasNext()) {
		        String	organizationId = (String) iterator.next();
		        Map userIds = (Map) this.organizations.get(organizationId);
		        Set	uidSet = userIds.keySet();
		        Iterator userIterator = uidSet.iterator();
		        List	listToReset = new ArrayList();

		        while (userIterator.hasNext()) {
			        String	userId = (String) userIterator.next();
			        UserProfile userProfile = (UserProfile) userIds.get(userId);
			        if (userProfile.isAuthenticated() && userProfile.getLastRequestTime() > 0) {
				        long	timeDif = currentTime - userProfile.getLastRequestTime();

					    //  The user authentication should be reset after 3600000 milliseconds (1 hour) of not being accessed
				        if (timeDif > 3600000) {
					        listToReset.add(userId);
				        }
			        }
		        }
		        if (listToReset.size() > 0) {
		            organizationsToReset.put(organizationId, listToReset);
		        }
			}

		    Set newOidSet = organizationsToReset.keySet();
		    Iterator	newOidIterator = newOidSet.iterator();

		    while (newOidIterator.hasNext()) {
		        String	organizationId = (String) newOidIterator.next();
		        List listToReset = (List) organizationsToReset.get(organizationId);

			    for (int i=0; i < listToReset.size(); i++) {
			        String	userId = (String) listToReset.get(i);
			        UserManager.getInstance().closeSession(organizationId, userId);
			    }
		    }
		}
		catch (Exception e) {
			Log.error(this, e.getMessage());
		}
	}
	*/
    public boolean isUserRegistered(String organizationId, String userId) {
        boolean registered = false;

        try {
            registered = this.getUser(organizationId, userId).isRegistered();
        } catch (Exception e) {
            // registered = false
        }
        return registered;
    }

    /**
     * @return com.tsa.puridiom4.usermanager.UserRole
     * @param organizationId java.lang.String
     * @param user com.tsa.puridiom.entity.UserProfile
     * @param userGroupRelList java.util.List
     * @exception java.lang.Exception The exception description.
     */
    public UserRole setUserRoles(String organizationId, UserProfile user, List userGroupRelList) throws java.lang.Exception
    {
        UserRole userRole = new UserRole();

        try
        {
            List userRoles = new ArrayList();
            if (user != null)
            {
                if (userGroupRelList != null)
                {
                    userRoles = new ArrayList();
                    for (int i = 0; i < userGroupRelList.size(); i++)
                    {
                        UserGroupRel userGroupRel = (UserGroupRel) userGroupRelList.get(i);
                        userRoles.add(userGroupRel.getComp_id().getGroupId());
                    }
                }

                user.setUserRoles(userRoles);
                this.setUserInCache(user);
            }

            userRole = UserRoleManager.getInstance().getUserRole(user.getUserRoles(), user.getOrganizationId());
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return userRole;
        }
    }

    public void logUserSession(String organizationId, String sessionId) {
        if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(sessionId)) {
            organizationId = organizationId.toUpperCase();

            Map sessions = new HashMap();
            if (usersLogged.containsKey(organizationId)) {
                // contains all valid sessions for users logged in for the organizationId specified
                sessions = (Map) usersLogged.get(organizationId);
            }
            sessions.put(sessionId, Dates.today("", ""));
            usersLogged.put(organizationId, sessions);
        }
    }

    public void removeUserSession(String organizationId, String sessionId) {
        if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(sessionId)) {
            organizationId = organizationId.toUpperCase();

            Map sessions = new HashMap();
            if (usersLogged.containsKey(organizationId)) {
                // contains all valid sessions for users logged in for the organizationId specified
                sessions = (Map) usersLogged.get(organizationId);
            }
            sessions.remove(sessionId);
            usersLogged.put(organizationId, sessions);
        }
    }

    public int getUserLogCount(String organizationId) {
        int userCount = 0;
        if (!Utility.isEmpty(organizationId)) {
            organizationId = organizationId.toUpperCase();
            Map sessions = new HashMap();
            if (usersLogged.containsKey(organizationId)) {
                try {
                    // contains all valid sessions for users logged in for the organizationId specified
                    sessions = (Map) usersLogged.get(organizationId);
                } catch (Exception ie) {
                }
            }
            try {
                userCount =sessions.keySet().size();
            }  catch (Exception ie) {
            }
        }
        return userCount;
    }

    /**
     * @return String
     * @param organizationId java.lang.String
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public String getUserDisplayName(String organizationId, String userId) throws java.lang.Exception {
        String name = userId;

        try {
            HashMap processParameters = new HashMap();
            processParameters.put("organizationId", organizationId);
            processParameters.put("userId", userId);
            processParameters.put("UserProfile_organizationId", organizationId);
            processParameters.put("UserProfile_userId", userId);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-display-name.xml");

            process.executeProcess(processParameters);

            name = (String) processParameters.get("UserProfile_displayName");
        } catch (Exception e) {
            Log.error(this, "The user display name could not be retrieved for " + organizationId + "/" + userId);
        }

        return name;
    }

    /**
     * @return int
     * @param organizationId java.lang.String
     * @param userType java.lang.String [valid values include: REQUISITIONER / BUYER]
     */
    public Integer getUserCount(String organizationId, String userType) {
    	Integer userCount = new Integer(0);
    	try {
            HashMap processParameters = new HashMap();
            processParameters.put("organizationId", organizationId);
            processParameters.put("UserGroupRel_groupId", userType);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess("usergrouprel-retrieve-count-by-groupid.xml");

            process.executeProcess(processParameters);

            userCount = new Integer(((Long) processParameters.get("userGroupRelCount")).intValue());
    	} catch (Exception e) {
            Log.error(this, "The user count could not be retrieved for " + organizationId + " / " + userType);
            Log.error(this, e.getMessage());
    	}
    	return userCount;
    }

    public String getAdministratorName(String organizationId) {
    	String administratorName = "";
    	try {
            HashMap processParameters = new HashMap();
            processParameters.put("organizationId", organizationId);
            processParameters.put("UserProfile_userType", "ADMIN");

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-by.xml");

            process.executeProcess(processParameters);

            List userProfileList = (List) processParameters.get("userProfileList");
            if (userProfileList != null && userProfileList.size() > 0) {
	            UserProfile user = (UserProfile) userProfileList.get(0);
	            if (user != null) {
	            	administratorName = user.getDisplayName();
	            }
            }
    	} catch (Exception e) {
            Log.error(this, "The administrator name could not be retrieved for " + organizationId);
            Log.error(this, e.getMessage());
    	}
    	return administratorName;
    }
}
