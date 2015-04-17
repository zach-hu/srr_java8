/*
 * Created on Mar 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequestIdManager
{
    private static RequestIdManager INSTANCE = new RequestIdManager();
    private Map requests = new HashMap();

    /**
     * UserPreferenceAdminManager constructor comment.
     */
    private RequestIdManager()
    {
        super();
    }

    /**
     * @return com.tsa.puridiom.user.UserPreferenceManager
     */
    public static RequestIdManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RequestIdManager();
        }
        return INSTANCE;
    }

    public Long getNexId()
    {
        return UniqueKeyGenerator.getInstance().getUniqueKey();
    }

    public Object addId(Object id, HttpServletRequest request)
    {
        RequestParameters requestParams = new RequestParameters();
        requestParams.setRequestProcess(request);
        INSTANCE.requests.put(id, requestParams);
        return id;
    }

    public boolean exists(Object id)
    {
        if(id != null)
        {
            boolean temp = INSTANCE.requests.containsKey(id);
            if(temp)
            {
                return ((RequestParameters)INSTANCE.requests.get(id)).isDeploy();
            }

            return temp;
        }
        else
        {
            return false;
        }
    }

    public RequestParameters getRequest(Object id)
    {
        return (RequestParameters)this.requests.get(id);
    }
    
	public void close() {
    	requests.clear();
    }
}
