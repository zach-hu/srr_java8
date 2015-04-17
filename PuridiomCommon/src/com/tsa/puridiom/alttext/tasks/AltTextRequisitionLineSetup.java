package com.tsa.puridiom.alttext.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class AltTextRequisitionLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String id = "";
            String itemNumber = "";
            String source = "";

            RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
            if (requisitionLine != null) {
                itemNumber = requisitionLine.getItemNumber();
                source = requisitionLine.getItemSource();

                if (Utility.isEmpty(source)) {
                    source = "RQL";
                }

                if (source.equals("INV")) {
                    id = requisitionLine.getItemLocation();
                } else if (source.equals("CAT" ) || source.equals("XML")) {
                    id = requisitionLine.getCatalogId();
                } else if (source.equals("RQL")) {
                    id = String.valueOf(requisitionLine.getIcReqLine());
                }
            } else {
                itemNumber = (String) incomingRequest.get("RequisitionLine_itemNumber");
                source = (String) incomingRequest.get("RequisitionLine_itemSource");

                if (Utility.isEmpty(source)) {
                    source = "RQL";
                }

                if (source.equals("INV")) {
                    id = (String) incomingRequest.get("RequisitionLine_itemLocation");
                } else if (source.equals("CAT" ) || source.equals("XML")) {
                    id = (String) incomingRequest.get("RequisitionLine_catalogId");
                } else if (source.equals("RQL")) {
                    id = (String) incomingRequest.get("RequisitionLine_icReqLine");
                }
            }

            if (Utility.isEmpty(id)) {
                id = "RQL";
            }
            if (Utility.isEmpty(itemNumber)) {
                itemNumber = " ";
            }

			incomingRequest.put("AltText_source", source);
            incomingRequest.put("AltText_id", id);
            incomingRequest.put("AltText_itemNumber", itemNumber);

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