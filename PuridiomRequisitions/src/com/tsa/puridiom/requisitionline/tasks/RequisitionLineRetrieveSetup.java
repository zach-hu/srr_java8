/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Administrator
 */
public class RequisitionLineRetrieveSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;

		RequisitionLine rql = (RequisitionLine) incomingRequest.get("requisitionLine") ;
		if (rql == null) {
			this.setStatus(Status.FAILED) ;
		} else {
			String icHeader = rql.getIcReqHeader().toString();
			String icLine = rql.getIcReqLine().toString();
			String catalogId = rql.getCatalogId();
			String itemNumber = rql.getItemNumber();

			if (Utility.isEmpty(icHeader)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("RequisitionLine_icReqLine", icLine);
				incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;
				incomingRequest.put("BillTo_icHeader",icHeader) ;
				incomingRequest.put("BillTo_icLine",icLine) ;
				incomingRequest.put("ShipTo_icHeader",icHeader) ;
				incomingRequest.put("ShipTo_icLine",icLine) ;
				incomingRequest.put("Account_icHeader",icHeader) ;
				incomingRequest.put("Account_icLine",icLine) ;
				incomingRequest.put("DocComment_icHeader",icHeader) ;
				incomingRequest.put("DocComment_icLine",icLine) ;
				incomingRequest.put("DocAttachment_icHeader",icHeader) ;
				incomingRequest.put("DocAttachment_icLine",icLine) ;
				incomingRequest.put("CatalogPriceBrk_catalogId", catalogId);
				incomingRequest.put("CatalogPriceBrk_itemNumber", itemNumber);
				incomingRequest.put("CatalogPriceBrk_breakType", "Q");
				incomingRequest.put("CatalogItem_catalogId", catalogId);
				incomingRequest.put("CatalogItem_itemNumber", itemNumber);

                boolean retrieveAltText = false;
                RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
                if (rqh != null) {
                    String language = rqh.getLanguage();
                    if (!Utility.isEmpty(language) && !language.equals("DEFAULT")) {
                        retrieveAltText = true;

                        incomingRequest.put("AltText_language", language);
                    }
                }

                incomingRequest.put("retrieveAltText", String.valueOf(retrieveAltText));
			}
		}

		return null ;
	}
}
