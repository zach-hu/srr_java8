package com.tsa.puridiom.handlers;

import java.util.*;

/**
 * @author Kelli Knisely
 */
public class DoNothingHandler implements IHandler
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		//Do Nothing except return the original request values and set the viewPage = the successPage
		incomingRequest.put("viewPage", incomingRequest.get("successPage"));

		return incomingRequest;
	}
}
