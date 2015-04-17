package com.tsa.puridiom.browse;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * Creation date: May 2005
 * @author: Kelli Knisely
 */
public class BrowseManager {
    private static BrowseManager INSTANCE = new BrowseManager();
    private HashMap browses = new HashMap();
    private HashMap organizations = new HashMap();
    private long lastCleanup = 0;
    
    private BrowseManager() {
        super();
    }
    /**
     * @return com.tsa.puridiom.browse.BrowseManager
     */
    public static BrowseManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BrowseManager();
        }
        return INSTANCE;
    }
    
    public Browse getBrowse(String	browseId) {
        Browse browse = (Browse) browses.get(browseId);
        browse.setLastAccessed((new Date()).getTime());
        return browse;
    }
    
    /**
     * @param browse Browse
     * @exception java.lang.Exception
     */
    public void setBrowseInCache(Browse browse, String organizationId, String sessionId) throws Exception {
        try {
            if (browse == null) throw new Exception ("Browse was null.");

            String	browseId = browse.getBrowseId();
            boolean newBrowse = true;

            if (!Utility.isEmpty(browseId)) {
                browse.setLastAccessed((new Date()).getTime());
                if (browses.containsKey(browseId)) {
                    newBrowse = false;
                }
                browses.put(browseId, browse);
                
                if (newBrowse) {
	                Map sessionBrowses = (HashMap) organizations.get(organizationId);
	                if (sessionBrowses == null) {
	                    sessionBrowses = new HashMap();
	                }
	                List sessionBrowseList = (List) sessionBrowses.get(sessionId);
	                if (sessionBrowseList == null) {
	                    sessionBrowseList = new ArrayList();
	                }
	                sessionBrowseList.add(browseId);
	                
	                sessionBrowses.put(sessionId, sessionBrowseList);
	                organizations.put(organizationId, sessionBrowses);
                }
            }
            
            checkBrowseCleanup();
        }
        catch (Exception e) {
            throw e;
        }
    }
    /**
     * @exception java.lang.Exception
     */
    public Set getAvailableBrowseIds() throws Exception {
        Set result = null;
        try {
        	result = browses.keySet();
        }
        catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     * @exception java.lang.Exception
     */
    public Map getOrganizationBrowses() throws Exception {
        Map result = new HashMap();
        try {
        	result = this.organizations;
        }
        catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     * @param browseId String
     * @exception java.lang.Exception
     */
    public void destroyBrowse(String browseId) throws Exception {
        try {
            if (!Utility.isEmpty(browseId)) {                
                browses.remove(browseId);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
    /**
     * @param browseId String
     * @exception java.lang.Exception
     */
    public void destroyBrowseBySession(String organizationId, String sessionId) throws Exception {
        try {
            if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(sessionId)) {
                Map sessionBrowses = (HashMap) organizations.get(organizationId);
                if (sessionBrowses != null) {
	                List sessionBrowseList = (List) sessionBrowses.get(sessionId);
	                if (sessionBrowseList != null) {
	                    for (int i=0; i < sessionBrowseList.size(); i++) {
	                        String	browseId = (String) sessionBrowseList.get(i);
	                        BrowseManager.getInstance().destroyBrowse(browseId);
	                    }
	                }
	                sessionBrowses.remove(sessionId);
	                organizations.put(organizationId, sessionBrowses);
                }
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    public void cleanupBrowsesBySession(String organizationId, String sessionId) {
		final String oid = organizationId;
		final String sid = sessionId;
		class MyRunner implements Runnable {						
			public void run() {
				try {
					destroyBrowseBySession(oid, sid);
				}
				catch (Exception exception) {
				    Log.error(this, exception.getMessage());
					//no need to throw this since the process has exited at this point
				}						
			}
		}
        new Thread(new MyRunner()).start();
    }
    
    private void checkBrowseCleanup() {
		class MyRunner implements Runnable {						
			public void run() {
				try {
					browseCleanup();
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

	private void browseCleanup() {
		try {
		    Set browseIds = BrowseManager.getInstance().getAvailableBrowseIds();
		    Iterator iterator = browseIds.iterator();
		    long currentTime = (new java.util.Date()).getTime();
		    List	listToDestroy = new ArrayList();
		    
		    while (iterator.hasNext()) {
		        String	browseId = (String) iterator.next();
		        Browse browse = (Browse) browses.get(browseId);
		        long	timeDif = 0;
		        
		        if (browse.getLastAccessed() > 0) {
		            timeDif = currentTime - browse.getLastAccessed();
		        }

			    //  The browse objects should be destroyed after 3600000 milliseconds (1 hour) of not being accessed
		        if (timeDif > 3600000) {
			        listToDestroy.add(browseId);
		        }
			}
		    for (int i=0; i < listToDestroy.size(); i++) {
		        String	browseId = (String) listToDestroy.get(i);
		        BrowseManager.getInstance().destroyBrowse(browseId);
		    }
		}
		catch (Exception e) {
			Log.error(this, e.getMessage());
		}		
	}
	
    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[ClassName=com.tsa.puridiom.browse.BrowseManager]");
        return sb.toString();
    }

}
