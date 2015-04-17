package com.tsa.puridiom.doctext.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class DocTextSetValuesPK
{
	public void setValues(Map incomingRequest, DocText doctext ) throws Exception
	{
		try
		{
			String icTextString = (String) incomingRequest.get("DocText_icText");
			BigDecimal icText = new BigDecimal ( icTextString );
			doctext.setIcText(icText);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}