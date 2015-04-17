/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.AltText;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class RequisitionLineDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		RequisitionLine rql = (RequisitionLine) incomingRequest.get("requisitionLine") ;

		rql.setAccountList((List) incomingRequest.get("accountList")) ;
		rql.setDocCommentList((List) incomingRequest.get("docCommentList")) ;
		rql.setBillToList((List) incomingRequest.get("billToList")) ;
		rql.setBudgetInfoList((List) incomingRequest.get("budgetInfoList"));
		rql.setShipToList((List) incomingRequest.get("shipToList")) ;
		rql.setDocAttachmentList((List) incomingRequest.get("docAttachmentList")) ;
		rql.setRfqInfoList((List) incomingRequest.get("rfqInfoList"));
		rql.setPoInfoList((List) incomingRequest.get("poInfoList"));
		rql.setInspectionList((List) incomingRequest.get("inspectionList"));
        Object altText = (Object)incomingRequest.get("altText");

        rql.setAltText((AltText) altText);

		this.setStatus(Status.SUCCEEDED) ;

		return null  ;
	}
}
