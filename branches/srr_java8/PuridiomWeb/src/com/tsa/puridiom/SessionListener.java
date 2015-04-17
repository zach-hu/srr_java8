/*
 * Created on August 1, 2005 @author Renzo / Kelli
 */
package com.tsa.puridiom;

import com.tsa.puridiom.browse.BrowseManager;
import com.tsagate.foundation.utility.Log;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private UserLogged users;

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession	userSession = se.getSession();
        Log.debug(this, "Session Created: " + userSession.getId());
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession hsSession = se.getSession();
        String	organizationId = (String) hsSession.getAttribute("organizationId");
        String	userId = (String) hsSession.getAttribute("userId");

        Log.debug(this, "Session to be destroyed for " + organizationId + " - " + userId + " - " + hsSession.getId());

        String sessionId = (String) hsSession.getId();
        BrowseManager.getInstance().cleanupBrowsesBySession(organizationId,sessionId);
        Log.debug(this, "Browse destroyed for "+ organizationId + " - " + userId + " - " + hsSession.getId());

        UserLogged.getInstance(organizationId).destroyUserLog(hsSession.getId(), userId, organizationId);
    }
}