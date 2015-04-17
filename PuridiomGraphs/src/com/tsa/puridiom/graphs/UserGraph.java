/*
 * Created on Jan 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserGraph
{
    private Map graphList = new HashMap();
    private String userId = "";
    private String organizationId = "";
    
    public String getGraph(String userType, String graphName)
    {
        String ret = "";
        Map graphMap = (Map)this.getGraphList().get(userType);
        if(graphMap == null)
        {
            ret = "not_found.jpg";
        }
        else
        {
            ret = (String)graphMap.get(graphName);
            if(ret == null)
            {
                ret = "not_found.jpg";
            }
        }
         
        return ret;
    }
    
    public void setGraphList(String userType, Map graphList)
    {
        this.getGraphList().put(userType, graphList);
    }
    
    public int size(String userType)
    {
        int ret = 0;
        
        Map graphMap = (Map)this.getGraphList().get(userType);
        if(graphMap == null)
        {
            ret = 0;
        }
        else
        {
            ret = graphMap.size();
        }
        
        return ret;
    }
    
    public Map getGraphList()
    {
        return graphList;
    }
    
    public String getOrganizationId()
    {
        return organizationId;
    }
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
