/*
 * Created on Dec 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BuyerGetGraphData
{
    private Map data;
    private BuyerGetGraphData()
    {
        //hiding the method from use
    }
    public BuyerGetGraphData(String organizationId, String userId)
    {
        this.getBuyerData(organizationId, userId);
    }
    
    private Object getBuyerData(String organizationId, String userId) 
    {
        Object ret = null;
        try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("buyer-graph-data.xml") ;
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("userId", userId);
			
			process.executeProcess(incomingRequest);
			
			this.setData(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
		}
		
		return ret;
    }

    public void setData(Map data)
    {
        this.data = data;
    }
    public Map getData()
    {
        return data;
    }
    
    public List getPoCount()
    {
        return (List)this.getData().get("poCount");
    }
    public List getReqCount()
    {
        return (List)this.getData().get("reqCount");
    }
    public List getBuyerCount()
    {
        return (List)this.getData().get("buyerCount");
    }
}
