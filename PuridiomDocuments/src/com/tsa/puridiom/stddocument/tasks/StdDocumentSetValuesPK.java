package com.tsa.puridiom.stddocument.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class StdDocumentSetValuesPK
{
	public void setValues(Map incomingRequest, StdDocument stddocument ) throws Exception
	{
		try
		{
			String fileName = (String ) incomingRequest.get("StdDocument_fileName");
			stddocument.setFileName(fileName);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}