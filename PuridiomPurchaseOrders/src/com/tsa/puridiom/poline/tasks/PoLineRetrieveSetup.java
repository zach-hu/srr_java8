/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Administrator
 */
public class PoLineRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		//DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		this.setStatus(Status.SUCCEEDED) ;

		PoHeader poheader = (PoHeader) incomingRequest.get("poHeader");
		PoLine pol = (PoLine) incomingRequest.get("poLine") ;
		
		if (pol == null) {
			this.setStatus(Status.FAILED) ;
		} else {
			String icHeader = pol.getIcPoHeader().toString() ;
			String icLine = pol.getIcPoLine().toString() ;
			String catalogId = pol.getCatalogId();
			String	itemNumber = pol.getItemNumber();

			if (Utility.isEmpty(icHeader)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("PoLine_icPoLine",icLine) ;
				incomingRequest.put("PoLine_icPoHeader",icHeader) ;
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
				
                if (poheader != null)
				{
					String language = poheader.getLanguage();
					
					if (!HiltonUtility.isEmpty(language) && !language.equals("DEFAULT"))
					{
						incomingRequest.put("AltText_language", language);
					}
				}
			}
		}

		return null ;
	}
}
