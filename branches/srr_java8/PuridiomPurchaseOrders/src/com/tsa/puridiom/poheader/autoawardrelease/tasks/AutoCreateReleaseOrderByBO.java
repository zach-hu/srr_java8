package com.tsa.puridiom.poheader.autoawardrelease.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * 
 * Class responsible for creating "Release Order" from the list 
 * provided Blanket Order
 * 
 * @author Alexander Richard Angulo Kcana
 *
 */
public class AutoCreateReleaseOrderByBO extends Task
{
	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;

	    try
	    {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
	        PuridiomProcess process = null;
			Map incomingRequest = (Map) object;
			String organizationId = (String)incomingRequest.get("organizationId");
	    	List pohList = (List) incomingRequest.get("poHeaderList");
	    	Log.debug(this, "organizationId: " + organizationId);
	    	
	    	if (pohList != null && pohList.size() > 0)
	        {
	    		Log.debug(this, "List size: " + pohList.size());
	    		Iterator<PoHeader> iterator = pohList.iterator();
	    		while (iterator.hasNext()) {
	    			PoHeader poHeader = iterator.next();
	    			
	    			Map newIncomingRequest = new HashMap();
	                newIncomingRequest.put("userId", incomingRequest.get("userId"));
	                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
	                newIncomingRequest.put("organizationId", organizationId);
	                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
	                
	                newIncomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());
	                newIncomingRequest.put("PoHeader_poNumber", poHeader.getPoNumber());
	                
	                String poType = poHeader.getPoType();
	                if (poType.equals(OrderType.BLANKET_ORDER)) {
	                	newIncomingRequest.put("PoHeader_poType", OrderType.RELEASE_ORDER);
	                } else if (poType.equals(OrderType.SERVICE_BLANKET)) {
	                	newIncomingRequest.put("PoHeader_poType", OrderType.SERVICE_RELEASE);
	                } else if (poType.equals(OrderType.DELIVERY_ORDER)) {
	                	newIncomingRequest.put("PoHeader_poType", OrderType.DELIVERY_RELEASE);
	                }
	                
	                newIncomingRequest.put("autoCreateRelease", Boolean.TRUE);
	    			process = processLoader.loadProcess("po-create-release.xml") ;
	    			process.executeProcess(newIncomingRequest);
	    		}
	        }
	    	else
	        {
	    		Log.debug(this, "No BO to auto-create release!");
	        }
    	
	    	Log.debug(this, "AUTO-CREATE RELEASE ORDER BY BO WORKING!");
	    	this.setStatus(Status.SUCCEEDED);
	    }
	    catch (Exception e)
	    {	
	    	Log.error(this, "AutoCreateReleaseOrderByBO failed: " + e.getMessage());
	    	this.setStatus(Status.FAILED);
	    	throw e;
	    }
	    return ret;
	}
}
