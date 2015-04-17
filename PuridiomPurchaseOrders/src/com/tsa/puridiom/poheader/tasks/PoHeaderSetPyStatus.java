/**
 *
 * Created on April 20, 2007
 * @author Kathleen
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderSetPaymentStatus.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.poline.exceptions.PoLineNotFoundException;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.messaging.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderSetPyStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			String oid = (String)incomingRequest.get("organizationId");
			List poLines = (List)incomingRequest.get("poLines");
			
			String poNumber = poHeader.getPoNumber();
			BigDecimal icPoHeader = poHeader.getIcPoHeader();			
			if(poLines == null)
			{
			    poLines = (List)incomingRequest.get("poLineList");
			}
			if(poLines == null)
			{
			    this.setStatus(Status.FAILED);
			    throw new PoLineNotFoundException(this.getName() + "- List of Po Lines was not found");
			}
			boolean flagNoInvoiced = false;
			boolean flagPartiallInvoiced = false;
			boolean flagFullyInvoiced = false;
			PoLine poLine = (PoLine) poLines.get(0);
			for (int i=0; i < poLines.size(); i++) 
			{
			    poLine = (PoLine) poLines.get(i);
				String poLinePystatus = poLine.getPyStatus();
				if(poLinePystatus.equalsIgnoreCase("6050"))
				{					//underPyStatusD = poLinePystatusD;
					flagNoInvoiced = true;
				}
				if(poLinePystatus.equalsIgnoreCase("6055"))
				{					//underPyStatusD = poLinePystatusD;
					flagPartiallInvoiced = true;
				}
				if(poLinePystatus.equalsIgnoreCase("6060"))
				{					//underPyStatusD = poLinePystatusD;
					flagFullyInvoiced = true;
				}				
			}
			if(!flagPartiallInvoiced && !flagFullyInvoiced)
				poHeader.setPyStatus("6050");
			else if ((flagNoInvoiced&& flagNoInvoiced) || flagPartiallInvoiced)
				poHeader.setPyStatus("6055");
			else if (!flagPartiallInvoiced && !flagNoInvoiced)
				poHeader.setPyStatus("6060");			
			
			result = poHeader;
	    	/*String queryString = "from PoHeader as po where po.poNumber = ? and po.icPoHeader <> ?";
		    List poHeaderList = dbs.query(queryString,	new Object[] {poNumber,icPoHeader}, new Type[] { Hibernate.STRING,Hibernate.BIG_DECIMAL});
		    if (poHeaderList != null)
			{
		    	PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("poheader-update-norecalc.xml");
				for (Iterator it = poHeaderList .iterator(); it.hasNext(); ) 
				{
					PoHeader poHeaderNew = (PoHeader) it.next();
					poHeaderNew.setPyStatus(poHeader.getPyStatus());
					incomingRequest.put("poHeader", poHeaderNew);
					process.executeProcess(incomingRequest);
					if (process.getStatus() < Status.SUCCEEDED)			
						throw new Exception("Po Line save as process failed.");
				}
			}*/
		    
		    
		    this.setStatus(Status.SUCCEEDED) ;

		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}