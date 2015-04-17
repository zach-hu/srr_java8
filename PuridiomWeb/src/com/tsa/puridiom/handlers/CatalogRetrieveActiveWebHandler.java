package com.tsa.puridiom.handlers;

import java.util.Map;
import com.tsagate.foundation.processengine.PuridiomProcess;

public class CatalogRetrieveActiveWebHandler extends CatalogHandler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		PuridiomProcess process = super.executeProcess(incomingRequest, "catalog-retrieve-active-web.xml");

		super.setViewPage(process, incomingRequest);

		return incomingRequest;
	}
}