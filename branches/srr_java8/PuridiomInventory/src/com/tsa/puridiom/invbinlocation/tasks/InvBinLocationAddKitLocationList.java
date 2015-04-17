package com.tsa.puridiom.invbinlocation.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class InvBinLocationAddKitLocationList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map)object;
			DBSession dbSesion = (DBSession)incomingRequest.get("dbsession") ;
            String organizationId = (String)incomingRequest.get("organizationId");
            Object itemNumberObj = incomingRequest.get("InvBinLocation_itemNumber");
            Object itemLocationObj = incomingRequest.get("InvBinLocation_itemLocation");
            Object tempIcObj = incomingRequest.get("InvBinLocation_tempIc");
            Object aisleObj = incomingRequest.get("InvBinLocation_aisle");
            Object locrowObj = incomingRequest.get("InvBinLocation_aisle");
            Object tierObj = incomingRequest.get("InvBinLocation_aisle");
            Object binObj = incomingRequest.get("InvBinLocation_aisle");
            Object lotObj = incomingRequest.get("InvBinLocation_lot");
            
            Object icReqHeader = incomingRequest.get("RequisitionHeader_icReqHeader");
		
            String[] itemNumberArray;
            String[] itemLocationArray;
            String[] tempIcArray;
            String[] aisleArray;
            String[] locrowArray;
            String[] tierArray;
            String[] binArray;
            String[] lotArray;
            
        	if (itemNumberObj instanceof String[]) {
        	    itemNumberArray = (String[]) itemNumberObj;  
        	} else {
        		itemNumberArray = new String[1];
        		itemNumberArray[0] = (String)itemNumberObj;
        	}
        	if (itemLocationObj instanceof String[]) {
        	    itemLocationArray = (String[]) itemLocationObj;  
        	} else {
        		itemLocationArray = new String[1];
        		itemLocationArray[0] = (String)itemLocationObj;
        	}
            if (tempIcObj instanceof String[]) {
        	   tempIcArray = (String[]) tempIcObj;  
        	} else {
        		tempIcArray = new String[1];
        		tempIcArray[0] = (String)tempIcObj;
        	}
            if (aisleObj instanceof String[]) {
            	aisleArray = (String[]) aisleObj;  
            } else {
            	aisleArray = new String[1];
            	aisleArray[0] = (String)aisleObj;
            }
            if (locrowObj instanceof String[]) {
            	locrowArray = (String[]) locrowObj;  
            } else {
            	locrowArray = new String[1];
            	locrowArray[0] = (String)locrowObj;
            }
            if (tierObj instanceof String[]) {
            	tierArray = (String[]) tierObj;  
            } else {
            	tierArray = new String[1];
            	tierArray[0] = (String)tierObj;
            }
            if (binObj instanceof String[]) {
            	binArray = (String[]) binObj;  
            } else {
            	binArray = new String[1];
            	binArray[0] = (String)binObj;
            }
            if (lotObj instanceof String[]) {
            	lotArray = (String[]) lotObj;  
            } else {
            	lotArray = new String[1];
            	lotArray[0] = (String)lotObj;
            }
    		
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invbinlocation-add-kit-location.xml");
			
			for (int i = 0; i < lotArray.length; i++) {
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", dbSesion);
				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("InvBinLocation_itemNumber", itemNumberArray[i]);
				newIncomingRequest.put("InvBinLocation_itemLocation", itemLocationArray[i]);
				newIncomingRequest.put("InvBinLocation_tempIc", tempIcArray[i]);
				newIncomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
				newIncomingRequest.put("InvBinLocation_aisle", aisleArray[i]);
				newIncomingRequest.put("InvBinLocation_locrow", locrowArray[i]);
				newIncomingRequest.put("InvBinLocation_tier", tierArray[i]);
				newIncomingRequest.put("InvBinLocation_bin", binArray[i]);
				newIncomingRequest.put("InvBinLocation_lot", lotArray[i]);
				newIncomingRequest.put("InvBinLocation_udf3Code", icReqHeader);
				process.executeProcess(newIncomingRequest);
			}

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}
