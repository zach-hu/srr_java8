/*
 * Created on March 15, 2005 
 */
package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;

/**
 * @author Kelli
 */
public class CatalogRetrieveActiveHandler extends CatalogHandler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		PuridiomProcess process = super.executeProcess(incomingRequest, "catalog-retrieve-active.xml");
		
		super.setViewPage(process, incomingRequest);

		return incomingRequest;
	}
}
