/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.handlers;

import com.tsagate.foundation.processengine.PuridiomProcess;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqAmendmentHandler extends Handler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		PuridiomProcess process = super.executeProcess(incomingRequest, "rfq-create-amendment.xml");
		
		super.setViewPage(process, incomingRequest);

		return incomingRequest;
	}
}
