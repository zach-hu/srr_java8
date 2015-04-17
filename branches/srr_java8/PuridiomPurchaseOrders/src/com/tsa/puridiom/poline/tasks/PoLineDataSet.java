/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.poline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AltText;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class PoLineDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		PoLine pol = (PoLine) incomingRequest.get("poLine") ;
		Object altText = (Object)incomingRequest.get("altText");

		if (pol != null)
		{
			pol.setAccountList((List) incomingRequest.get("accountList")) ;
			pol.setDocCommentList((List) incomingRequest.get("docCommentList")) ;
			pol.setBillToList((List) incomingRequest.get("billToList")) ;
			pol.setBudgetInfoList((List) incomingRequest.get("budgetInfoList"));
			pol.setShipToList((List) incomingRequest.get("shipToList")) ;
			pol.setDocAttachmentList((List) incomingRequest.get("docAttachmentList")) ;
			pol.setInspectionList((List) incomingRequest.get("inspectionList"));
			pol.setReceiptLineList((List) incomingRequest.get("receiptLineList"));
			pol.setAltText((AltText) altText);

			String imageFile = (String) incomingRequest.get("imageFile");
			if ( !HiltonUtility.isEmpty(imageFile))
			{
				pol.setImageFile(imageFile);
			}
		}

		this.setStatus(Status.SUCCEEDED) ;

		return pol;
	}
}
