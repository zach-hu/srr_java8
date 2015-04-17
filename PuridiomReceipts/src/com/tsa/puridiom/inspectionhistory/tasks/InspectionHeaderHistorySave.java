package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Map;

public class InspectionHeaderHistorySave extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

            String qtySignoff = (String) incomingRequest.get("InspectionHeader_qtySignoff") ;
            String qtyAccepted = (String) incomingRequest.get("InspectionHeader_qtyAccepted") ;
            String qtyRejected = (String) incomingRequest.get("InspectionHeader_qtyRejected") ;

            String oQtySignoff = (String) incomingRequest.get("originalQtySignoff") ;
            String oQtyAccepted = (String) incomingRequest.get("originalQtyAccepted") ;
            String oQtyRejected = (String) incomingRequest.get("originalQtyRejected") ;

            if (qtySignoff == null) qtySignoff = "0" ;
            BigDecimal bQtySignoff = new BigDecimal(qtySignoff) ;
            if (qtyAccepted == null) qtyAccepted = "0" ;
            BigDecimal bQtyAccepted = new BigDecimal(qtyAccepted) ;
            if (qtyRejected == null) qtyRejected = "0" ;
            BigDecimal bQtyRejected = new BigDecimal(qtyRejected) ;

            if (oQtySignoff == null) oQtySignoff = "0" ;
            BigDecimal boQtySignoff = new BigDecimal(oQtySignoff) ;
            if (oQtyAccepted == null) oQtyAccepted = "0" ;
            BigDecimal boQtyAccepted = new BigDecimal(oQtyAccepted) ;
            if (oQtyRejected == null) oQtyRejected = "0" ;
            BigDecimal boQtyRejected = new BigDecimal(oQtyRejected) ;

            String historyText = "" ;

            if (bQtySignoff.compareTo(boQtySignoff) != 0) {
            	historyText = historyText + "Sign-off for Quantity " + bQtySignoff  ;
            }
            if (bQtyAccepted.compareTo(boQtyAccepted) != 0) {
            	if (historyText.length() > 0) historyText = historyText + ", " ;
            	historyText = historyText + "Accepted Quantity " + bQtyAccepted ;
            }
            if (bQtyRejected.compareTo(boQtyRejected) != 0) {
            	if (historyText.length() > 0) historyText = historyText + ", " ;
            	historyText = historyText + "Rejected Quantity " + bQtyRejected ;
            }

            if (historyText.length() > 0) {
            	incomingRequest.put("inspectionComment", historyText) ;

    			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
    			PuridiomProcess process = processLoader.loadProcess("inspectionheader-history-add.xml");
    			process.executeProcess(incomingRequest);
            }

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}