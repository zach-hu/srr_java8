package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.Map;

public class RfqRequisitionMap extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");

			String f1 = propertiesManager.getProperty("REQ TO RFQ","req_udf01","") ;
			String f2 = propertiesManager.getProperty("REQ TO RFQ","req_udf02","") ;
			String f3 = propertiesManager.getProperty("REQ TO RFQ","req_udf03","") ;
			String f4 = propertiesManager.getProperty("REQ TO RFQ","req_udf04","") ;
			String f5 = propertiesManager.getProperty("REQ TO RFQ","req_udf05","") ;
			String f6 = propertiesManager.getProperty("REQ TO RFQ","req_udf06","") ;
			String f7 = propertiesManager.getProperty("REQ TO RFQ","req_udf07","") ;
			String f8 = propertiesManager.getProperty("REQ TO RFQ","req_udf08","") ;
			String f9 = propertiesManager.getProperty("REQ TO RFQ","req_udf09","") ;
			String f10 = propertiesManager.getProperty("REQ TO RFQ","req_udf10","") ;
			String f11 = propertiesManager.getProperty("REQ TO RFQ", "req_udf11", "");

			if (! Utility.isEmpty(f1)) {
				f1 = "RfqHeader_" + f1;
				incomingRequest.put(f1, reqHeader.getUdf1Code()) ;
			}
			if (! Utility.isEmpty(f2)) {
				f2 = "RfqHeader_" + f2;
				incomingRequest.put(f2, reqHeader.getUdf2Code()) ;
			}
			if (! Utility.isEmpty(f3)) {
				f3 = "RfqHeader_" + f3;
				incomingRequest.put(f3, reqHeader.getUdf3Code()) ;
			}
			if (! Utility.isEmpty(f4)) {
				f4 = "RfqHeader_" + f4;
				incomingRequest.put(f4, reqHeader.getUdf4Code()) ;
			}
			if (! Utility.isEmpty(f5)) {
				f5 = "RfqHeader_" + f5;
				incomingRequest.put(f5, reqHeader.getUdf5Code()) ;
			}
			if (! Utility.isEmpty(f6)) {
				f6 = "RfqHeader_" + f6;
				incomingRequest.put(f6, reqHeader.getUdf6Code()) ;
			}
			if (! Utility.isEmpty(f7)) {
				f7 = "RfqHeader_" + f7;
				incomingRequest.put(f7, reqHeader.getUdf7Code()) ;
			}
			if (! Utility.isEmpty(f8)) {
				f8 = "RfqHeader_" + f8;
				incomingRequest.put(f8, reqHeader.getUdf8Code()) ;
			}
			if (! Utility.isEmpty(f9)) {
				f9 = "RfqHeader_" + f9;
				incomingRequest.put(f9, reqHeader.getUdf9Code()) ;
			}
			if (! Utility.isEmpty(f10)) {
				f10 = "RfqHeader_" + f10;
				incomingRequest.put(f10, reqHeader.getUdf10Code()) ;
			}

			if (! Utility.isEmpty(f11)) {
				f11 = "RfqHeader_" + f11;
				incomingRequest.put(f11, reqHeader.getUdf11Code());
			}

		    this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}

		return null ;
	}

}