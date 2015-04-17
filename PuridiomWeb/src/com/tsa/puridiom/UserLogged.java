/*
 * Created on August 1, 2005 @author Renzo / Kelli
 */
package com.tsa.puridiom;

import com.tsa.puridiom.browse.BrowseManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

public final class UserLogged {

    /** This is the default instance used for this singleton. */
    protected int userCount = 0;
    protected int maxCount = 0;
    protected String organizationId = new String();
    protected Hashtable users = new Hashtable();

    private static HashMap userLogged = new HashMap();

	public static UserLogged getInstance(String o) {
		if (UserLogged.userLogged.get(o) == null) {
		    UserLogged.userLogged.put(o, new UserLogged(o));
		}
		return (UserLogged) UserLogged.userLogged.get(o);
	}

	private UserLogged(String o) {
		this.userCount = 0;
		this.maxCount = 0;
		this.users = new Hashtable();
		this.organizationId = o;
	}
    /**
     * Constructor of the object. <br>
     * This constructor should remain private
     */
    private UserLogged() {
    }

    public int getUserCount() {
        return userCount;
    }

    public void logUser(String sessionId, String userId, String organizationId) {
        this.users.put(sessionId, userId);
        userCount++;
        Log.debug(this, "Hilton User " + userId + " logged in... Current Count " + Integer.toString(this.userCount) + "/" + Integer.toString(this.maxCount));
        UserManager.getInstance().logUserSession(organizationId, sessionId);
    }

    /**
     * Method destroyUserLog
    * @param sessionId
     * @param userId
     */
    public void destroyUserLog(String sessionId, String userId, String organizationId) {
        this.users.remove(sessionId);
        userCount--;

        Log.debug(this, "User " + userId + " is logged out... Current Count " + Integer.toString(this.userCount) + "/" + Integer.toString(this.maxCount));

        //BrowseManager.getInstance().cleanupBrowsesBySession(organizationId, sessionId);
        UserManager.getInstance().removeUserSession(organizationId, sessionId);
    }

    public List getLoggedUsers() {
        Set	sessionIds = this.users.keySet();
        Iterator iterator = sessionIds.iterator();
        List	userList = new ArrayList();

        while (iterator.hasNext()) {
            String	sessionId = (String) iterator.next();
            String	userId = (String) this.users.get(sessionId);

            userList.add(userId);
        }
        return userList;
    }

    public String getSessionUserId(String sessionId) {
        String	userId = (String) this.users.get(sessionId);
        if (Utility.isEmpty(userId)) {
            userId = "UNKNOWN";
        }
        return userId;
    }
}