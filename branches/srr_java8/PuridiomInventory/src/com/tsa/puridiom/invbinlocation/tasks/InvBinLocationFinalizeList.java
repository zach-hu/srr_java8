/*
 * Created on August 30, 2005
 */
package com.tsa.puridiom.invbinlocation.tasks;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine ;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Kelli
 */
public class InvBinLocationFinalizeList extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List invBinLocationList = (List) incomingRequest.get("invBinLocationList");
		ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
		PoLine poLine = (PoLine) incomingRequest.get("poLine");
		RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine") ;
		InvItem invItem = (InvItem) incomingRequest.get("invItem");

		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("invbinlocation-finalize.xml");

		for (int i = 0; i < invBinLocationList.size(); i++)
		{
			InvBinLocation invBinLocation = (InvBinLocation) invBinLocationList.get(i);

			Map updateParameters = new HashMap();
			updateParameters.put("userId", incomingRequest.get("userId"));
			updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
			updateParameters.put("organizationId", incomingRequest.get("organizationId"));
			updateParameters.put("dbsession", incomingRequest.get("dbsession"));
			updateParameters.put("receiptLine", receiptLine);
			updateParameters.put("poLine", poLine);
			updateParameters.put("requisitionLine", requisitionLine) ;
			updateParameters.put("invBinLocation", invBinLocation);
			updateParameters.put("invItem", invItem);

			process.executeProcess(updateParameters);

			if (process.getStatus() < Status.SUCCEEDED) {
				throw new Exception("invbinlocation-finalize process failed.");
			}

			invBinLocationList.set(i, updateParameters.get("invBinLocation"));

			if(process.getStatus() != Status.SUCCEEDED)
			{
				i = invBinLocationList.size();
			}
			this.setStatus(process.getStatus());
		}

		// Remove temp ic and permanentOnly for inv location quantity update
		incomingRequest.remove("InvBinLocation_tempIc");
		incomingRequest.remove("permanentOnly");

		return invBinLocationList;
	}

}
