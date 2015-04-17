/*
 * Created on Aug 19, 2003
 * Modifed Feb 10, 2005 -KK 
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.poline.exceptions.PoLineNotFoundException;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineDeleteByHeader extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		try
		{

			List	poLineList = (List)incomingRequest.get("poLineList");
			
			if (poLineList != null)
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("poline-delete.xml");
					
				for (int i=0; i < poLineList.size(); i++) 
				{
					PoLine poLine = (PoLine) poLineList.get(i);
		
					incomingRequest.put("poLine", poLine);
					incomingRequest.put("PoLine_icPoHeader", String.valueOf(poLine.getIcPoHeader()));
					incomingRequest.put("PoLine_icPoLine", String.valueOf(poLine.getIcPoLine()));
					incomingRequest.put("deleteAction", "BY-HEADER");
					
					process.executeProcess(incomingRequest);
					
					if (process.getStatus() < Status.SUCCEEDED) 
					{
						throw new Exception("Po Line save as process failed.");
					}
				}
			}
		
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
			//throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
