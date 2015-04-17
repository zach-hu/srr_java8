package com.tsa.puridiom.doctext.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class DocTextSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain docText */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			DocText	originalDocText = (DocText) incomingRequest.get("docText");
			String referenceType = (String) incomingRequest.get("newDocText_referenceType");
			DocText	docText = new DocText();

			docText.setIcText(new BigDecimal(ukg.getUniqueKey().toString()));
			if (originalDocText != null) {
				docText.setStdText(originalDocText.getStdText());
				docText.setReferenceType(originalDocText.getReferenceType());
				docText.setIdReference(originalDocText.getIdReference());
			}
		
			if(!HiltonUtility.isEmpty(referenceType)){
				docText.setReferenceType(referenceType);
			}

			incomingRequest.put("docText", docText);

			DocTextAdd addTask = new DocTextAdd();
			docText = (DocText) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			incomingRequest.put("newDocText_icText", docText.getIcText().toString());

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