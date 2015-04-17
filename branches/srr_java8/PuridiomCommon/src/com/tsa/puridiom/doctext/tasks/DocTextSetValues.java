package com.tsa.puridiom.doctext.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class DocTextSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DocText docText = (DocText) incomingRequest.get("docText");
			if (docText == null)
			{
				docText = new DocText();
			}

			if (incomingRequest.containsKey("DocText_icText"))
			{
				String icTextString = (String) incomingRequest.get("DocText_icText");
				if (Utility.isEmpty(icTextString))
				{
					icTextString = "0";
				}
				BigDecimal icText = new BigDecimal ( icTextString );
				docText.setIcText(icText);
			}
			if (incomingRequest.containsKey("DocText_stdText"))
			{
				String stdText = (String ) incomingRequest.get("DocText_stdText");
				docText.setStdText(stdText);
			}
			if (incomingRequest.containsKey("DocText_referenceType"))
			{
				String referenceType = (String ) incomingRequest.get("DocText_referenceType");
				docText.setReferenceType(referenceType);
			}
			if (incomingRequest.containsKey("DocText_idReference"))
			{
				String idReference = (String ) incomingRequest.get("DocText_idReference");
				docText.setIdReference(idReference);
			}

			result = docText;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}