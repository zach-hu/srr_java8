package com.tsa.puridiom.sendalert.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class QueueAddOrderRequest extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("SendQueue_doctype", "PO");
			incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("PoHeader_icPoHeader"));
			
			String order = 	(String) incomingRequest.get("PoHeader_poNumber") ;
			String release = (String) incomingRequest.get("PoHeader_releaseNumber") ;
			if (Utility.isEmpty(release) ) {
				release = "0" ;
			}
			BigDecimal rel = new BigDecimal(release) ;
			String owner = (String) incomingRequest.get("userId") ;
			
			StringBuffer subject = new StringBuffer("");
			subject.append("Order ");
			subject.append(order) ;
			
			if (rel.compareTo(new BigDecimal(0)) > 0) {
				subject.append(" Release ");
				subject.append(release) ;
			}
			subject.append(" submitted for purchase") ;

			incomingRequest.put("SendQueue_subject",subject.toString() );
			incomingRequest.put("SendQueue_sendfromtype", "U");
			incomingRequest.put("SendQueue_sendfrom",owner );
			incomingRequest.put("SendQueue_sendtotype", "U");
			incomingRequest.put("SendQueue_sendto", "");
			
			incomingRequest.put("SendQueue_action", "XM");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
			process.executeProcess(incomingRequest);
			this.status = process.getStatus() ;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}