/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class RequisitionHeaderDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;

		rqh.setRequisitionLineList((List) incomingRequest.get("requisitionLineList")) ;
		rqh.setAccountList((List) incomingRequest.get("accountList")) ;
		rqh.setDocCommentList((List) incomingRequest.get("docCommentList")) ;
		rqh.setDocAttachmentList((List) incomingRequest.get("docAttachmentList")) ;
		rqh.setScheduleList((List) incomingRequest.get("scheduleList")) ;
		rqh.setRfqInfoList((List) incomingRequest.get("rfqInfoList"));
		rqh.setPoInfoList((List) incomingRequest.get("poInfoList"));

		rqh.setBillToAddress((Address) incomingRequest.get("billToAddress"));
		rqh.setShipToAddress((Address) incomingRequest.get("shipToAddress"));
		rqh.setVendorAddress((Address) incomingRequest.get("vendorAddress"));


		this.setStatus(Status.SUCCEEDED) ;

		return null  ;
	}
}
