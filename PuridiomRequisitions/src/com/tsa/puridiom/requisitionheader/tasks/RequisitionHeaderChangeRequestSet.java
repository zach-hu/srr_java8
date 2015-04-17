package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionHeaderChangeRequestSet extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			String userId = (String) incomingRequest.get("userId");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
		    RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionOld");

		    if (requisitionHeader != null) {
		    	incomingRequest.put("RequisitionHeader_requisitionerCode", requisitionHeader.getRequisitionerCode());
		    	incomingRequest.put("RequisitionHeader_udf8Code", requisitionHeader.getUdf8Code());
			    incomingRequest.put("RequisitionHeader_udf15Code", requisitionHeader.getUdf15Code());
		    } else if (poHeader != null){
		    	incomingRequest.put("RequisitionHeader_requisitionerCode", poHeader.getRequisitionerCode());
		    }
		    
		    incomingRequest.put("RequisitionHeader_owner", userId);
		    
		    incomingRequest.put("RequisitionHeader_shipAttn", poHeader.getShipToContact());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}