/**
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoLineDeleteBudgetServiceSetup extends Task
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		try
		{
			PoHeader poh = (PoHeader) incomingRequest.get("poHeader");
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			String deleteAction = HiltonUtility.ckNull((String) incomingRequest.get("deleteAction"));
			String deleteOption = "";
			BigDecimal icReqLine = poLine.getIcReqLine();
			String budgetSubAction = "";
			List poLineList = new ArrayList();

			poLineList.add(poLine);

			incomingRequest.put("header", poh);
			incomingRequest.put("lineItems", poLineList);
			incomingRequest.put("formtype", "PO");

			if (incomingRequest.containsKey("delete_option") && !HiltonUtility.isEmpty((String) incomingRequest.get("delete_option")))
			{
				deleteOption = HiltonUtility.ckNull((String) incomingRequest.get("delete_option"));

				if (deleteOption.equals(DocumentStatus.CANCELLED))
				{
					budgetSubAction = "CANCEL";

				} else if (deleteOption.equals(DocumentStatus.CLOSED))
				{
					budgetSubAction = "CLOSE";

				} else
				{
					this.setPostAction("exitActivity");
				}

				incomingRequest.put("budgetServiceSubAction", budgetSubAction);
			}

			if (deleteAction.equalsIgnoreCase("BY-HEADER") || (!((icReqLine.compareTo(new BigDecimal(0)) > 0) || (poh.getRevisionNumber().compareTo(new BigDecimal(0)) > 0))))
			{
				this.setPostAction("exitActivity");
			}

			incomingRequest.put("budgetServiceAction", "DELETE");

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoLineDeleteBudgetServiceSetup error " + e.getMessage());

			throw e;
		}

		return result;
	}
}