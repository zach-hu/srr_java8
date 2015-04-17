/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.entity.RfqNote;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqLineDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqLine rfl = (RfqLine) incomingRequest.get("rfqLine") ;
			if (rfl == null)
			{
				throw new Exception ("The RfqLine entity was not found.");
			}

			rfl.setDocCommentList((List) incomingRequest.get("docCommentList")) ;
			rfl.setBillToList((List) incomingRequest.get("billToList")) ;
			rfl.setShipToList((List) incomingRequest.get("shipToList")) ;
			rfl.setRfqBidList((List) incomingRequest.get("rfqBidList"));
			rfl.setRequisitionLineInfoList((List) incomingRequest.get("requisitionLineInfoList"));
			rfl.setPoLineInfoList((List) incomingRequest.get("poLineInfoList"));
			rfl.setDocAttachmentList((List) incomingRequest.get("docAttachmentList")) ;
			rfl.setInspectionList((List) incomingRequest.get("inspectionList"));
			RfqNote rfqNote = (RfqNote) incomingRequest.get("rfqNote");
			if (rfqNote != null) {
			    rfl.setRfqNote(rfqNote.getNotesText());
			}

			result = rfl;
			this.setStatus(Status.SUCCEEDED) ;
		}
        catch (Exception e)
        {
        	this.setStatus(Status.FAILED);
        	throw e;
        }
		return result;
	}
}
