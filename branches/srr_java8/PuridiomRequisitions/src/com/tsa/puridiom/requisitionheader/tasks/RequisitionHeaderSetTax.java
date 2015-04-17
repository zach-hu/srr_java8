/**
 * 
 * Created on Setp. 3, 2004
 * @author Kelli
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisitionheader.tasks.RequisitionHeaderSetTax.java
 * 
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.TaxCode;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RequisitionHeaderSetTax extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		TaxCode taxCode = (TaxCode)incomingRequest.get("taxCode");
		if (taxCode != null) {
		    //requisitionHeader.setTaxCode(taxCode.getTaxCode());
		    requisitionHeader.setTaxPercent(taxCode.getTaxRate());
		}
		
		return requisitionHeader;
	}

}
