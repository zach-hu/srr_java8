/*
 * Created on Oct 17, 2003 
 */
package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CatalogHandler implements IHandler
{

	/* (non-Javadoc)
	 * @see com.tsa.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		return null;
	}
	
	public PuridiomProcess executeProcess(Map incomingRequest, String xml) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
		PuridiomProcess process = processLoader.loadProcess(xml) ;
		process.executeProcess(incomingRequest);
		return process;
	}
	
	public void setViewPage(PuridiomProcess process, Map incomingRequest)
	{
		if (process.getStatus() != Status.SUCCEEDED)
		{
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
		}
		else
		{
			incomingRequest.put("viewPage", incomingRequest.get("successPage"));
		}
	}
}
