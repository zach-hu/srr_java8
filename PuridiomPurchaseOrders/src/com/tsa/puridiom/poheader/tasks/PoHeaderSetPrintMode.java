package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.PrintMode;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class PoHeaderSetPrintMode extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			Log.debug(this, "Printing mode for po number: " + poHeader.getPoNumber());
			incomingRequest.put("PoHeader_datePrinted", Dates.today("", userTimeZone));
			if ((poHeader.getStatus().compareTo(DocumentStatus.PO_AWARDED))<0){
				incomingRequest.put("PoHeader_printMode", PrintMode.DRAFT_ONLY_NOT_AN_ORDER);
			}
			else if (poHeader.getStatus().equalsIgnoreCase(DocumentStatus.PO_AWARDED) && (poHeader.getPrintMode().equalsIgnoreCase(PrintMode.DRAFT_ONLY_NOT_AN_ORDER))) {
				PrintMode.toString(organizationId, PrintMode.ORIGINAL);
				incomingRequest.put("PoHeader_printMode", PrintMode.ORIGINAL);
			}else{
				PrintMode.toString(organizationId, PrintMode.DUPLICATE_ORDER);
				incomingRequest.put("PoHeader_printMode", PrintMode.DUPLICATE_ORDER);
			}
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetSetupPoEntities error " + e.getMessage());

			throw e;
		}

		return result;
	}
}
