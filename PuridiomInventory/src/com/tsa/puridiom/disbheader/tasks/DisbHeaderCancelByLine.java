/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.disbheader.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.math.BigDecimal;

/**
 * @author Administrator
 */
public class DisbHeaderCancelByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		PuridiomProcess process;
		try {
			if (incomingRequest.containsKey("DisbLine_icDsbHeader")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				process = processLoader.loadProcess("disbursementline-standard-cancel-byline.xml");
				//Object objectIcDisbLine = incomingRequest.get("icDisbLine");

				String arraySizeString = (String)  incomingRequest.get("numberDisbLines");

				if(arraySizeString == null)
				{
					arraySizeString = "0";
				}

				int	arraySize = (new BigDecimal(arraySizeString)).intValue();

				if (arraySize>1) {


					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("canceltype", incomingRequest.get("canceltype"));
						updateParameters.put("DisbLine_icDsbHeader",incomingRequest.get("DisbLine_icDsbHeader"));
						Object[] icDsbLineArray = (Object[])  incomingRequest.get("DisbLine_icDsbLine");
						Object[] statusArray = (Object[])  incomingRequest.get("RequisitionLine_status");

						updateParameters.put("DisbLine_icDsbLine", icDsbLineArray[i]) ;
						updateParameters.put("RequisitionLine_status",statusArray[i]) ;

						process.executeProcess(updateParameters);
					}
				}
				else {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("canceltype", incomingRequest.get("canceltype"));
						updateParameters.put("DisbLine_icDsbHeader",incomingRequest.get("DisbLine_icDsbHeader")) ;
						updateParameters.put("DisbLine_icDsbLine",incomingRequest.get("DisbLine_icDsbLine")) ;
						updateParameters.put("RequisitionLine_status",incomingRequest.get("RequisitionLine_status")) ;
						process.executeProcess(updateParameters);
				}
			}
			else {
				throw new Exception("The value for DisbHeader_icHeader was not found.");
			}	
			

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.debug(this, e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
