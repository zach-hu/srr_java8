/*
 * Created on Oct 16, 2003 
 */
package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class InvCatalogRetrieveHandler extends CatalogHandler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		PuridiomProcess process = super.executeProcess(incomingRequest, "invcatalog-retrieve-all.xml");
		
		super.setViewPage(process, incomingRequest);

		return incomingRequest;
	}
}
