/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqHeaderDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RfqHeader rfh = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfh == null)
			{
				throw new Exception("The RfqHeader entity was not found.");
			}
	
			List rfqVendorList = (List) incomingRequest.get("rfqVendorList");
			
			rfh.setDocAttachmentList((List) incomingRequest.get("docAttachmentList")) ;
			rfh.setDocCommentList((List) incomingRequest.get("docCommentList")) ;
			rfh.setRfqBidList((List) incomingRequest.get("rfqBidList")) ;
			rfh.setRfqLineList((List) incomingRequest.get("rfqLineList")) ;
			rfh.setRfqQuestionList((List) incomingRequest.get("rfqQuestionList")) ;
			rfh.setRfqVendorList(rfqVendorList) ;
			rfh.setScheduleList((List) incomingRequest.get("scheduleList")) ;
			rfh.setVendorQuestionList((List) incomingRequest.get("vendorQuestionList")) ;
			rfh.setRequisitionInfoList((List) incomingRequest.get("requisitionInfoList")) ;
			rfh.setPoInfoList((List) incomingRequest.get("poInfoList")) ;
			
			rfh.setBillToAddress((Address) incomingRequest.get("billToAddress"));
			rfh.setShipToAddress((Address) incomingRequest.get("shipToAddress"));
			rfh.setVendorAddress((Address) incomingRequest.get("vendorAddress"));
			
			String vendorId = (String) incomingRequest.get("vendorId") ;
			if (!Utility.isEmpty(vendorId) && rfqVendorList != null) {
			    for (int i=0; i < rfqVendorList.size(); i++) {
			        RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(i);
			        if (rfqVendor != null) {
			            if (rfqVendor.getComp_id().getVendorId().equals(vendorId)) {
			                incomingRequest.put("currentVendor", rfqVendor);
			                break;
			            }
			        } 
			    }
			}
			
			result = rfh;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return result  ;
	}
}
