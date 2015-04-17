package com.tsa.puridiom.invformpart.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvFormPartSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvFormPart_itemNumber", (String) incomingRequest.get("InvFormPart_itemNumber"));
			incomingRequest.put("InvFormPart_formPart", (String) incomingRequest.get("InvFormPart_formPart"));
			incomingRequest.put("InvFormPart_measureBy", "");
			incomingRequest.put("InvFormPart_overallWidth", "0");
			incomingRequest.put("InvFormPart_overallLen", "0");
			incomingRequest.put("InvFormPart_detachWidth", "0");
			incomingRequest.put("InvFormPart_detachLen", "0");
			incomingRequest.put("InvFormPart_sizeUdf", "");
			incomingRequest.put("InvFormPart_printSides", "0");
			incomingRequest.put("InvFormPart_printCopies", "0");
			incomingRequest.put("InvFormPart_marginWords", "");
			incomingRequest.put("InvFormPart_addchgdel", "");
			incomingRequest.put("InvFormPart_blockout", "");
			incomingRequest.put("InvFormPart_copiesUdf", "");
			incomingRequest.put("InvFormPart_paperColor", "");
			incomingRequest.put("InvFormPart_paperWeight", "0");
			incomingRequest.put("InvFormPart_paperGrade", "");
			incomingRequest.put("InvFormPart_inkFront", "");
			incomingRequest.put("InvFormPart_inkBack", "");
			incomingRequest.put("InvFormPart_paperUdf", "");
			incomingRequest.put("InvFormPart_inkUdf", "");
			incomingRequest.put("InvFormPart_carbonWeight", "0");
			incomingRequest.put("InvFormPart_carbonGrade", "");
			incomingRequest.put("InvFormPart_carbonColor", "");
			incomingRequest.put("InvFormPart_carbonSize", "0");
			incomingRequest.put("InvFormPart_carbonSpot", "");
			incomingRequest.put("InvFormPart_carbonStrip", "");
			incomingRequest.put("InvFormPart_carbonUdf", "");
			incomingRequest.put("InvFormPart_punchHoles", "0");
			incomingRequest.put("InvFormPart_punchSize", "0");
			incomingRequest.put("InvFormPart_punchCenter", "0");
			incomingRequest.put("InvFormPart_punchPos", "");
			incomingRequest.put("InvFormPart_mperfLeft", "0");
			incomingRequest.put("InvFormPart_mperfRight", "0");
			incomingRequest.put("InvFormPart_operfLeft", "0");
			incomingRequest.put("InvFormPart_operfRight", "0");
			incomingRequest.put("InvFormPart_perfType", "");
			incomingRequest.put("InvFormPart_turnType", "");

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