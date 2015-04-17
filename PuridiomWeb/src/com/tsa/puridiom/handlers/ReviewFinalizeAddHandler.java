package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;


public class ReviewFinalizeAddHandler extends Handler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("reviewfinalize-add.xml");
		process.executeProcess(incomingRequest);

		super.setViewPage(process, incomingRequest);

		return incomingRequest;
	}
}
