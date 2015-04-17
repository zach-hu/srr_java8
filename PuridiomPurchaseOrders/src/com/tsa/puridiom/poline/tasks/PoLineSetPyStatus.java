package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.poline.exceptions.PoLineNotFoundException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.messaging.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineSetPyStatus extends Task 
{
	public Object  executeTask (Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try 
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			List	poLines = (List)incomingRequest.get("poLines");
			
			
			if(poLines == null)
			{
			    poLines = (List)incomingRequest.get("poLineList");
			}
			if(poLines == null)
			{
			    this.setStatus(Status.FAILED);
			    throw new PoLineNotFoundException(this.getName() + "- List of Po Lines was not found");
			}
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("poline-update-norecalc.xml");
			String organizationId = (String) incomingRequest.get("organizationId");
			String qtyInvCalculateOption = PropertiesManager.getInstance(organizationId).getProperty("IVC OPTIONS", "DESACTIVATE ABS QTY INV CALCULATE", "N") ;
			
			for (int i=0; i < poLines.size(); i++) 
			{
				
				PoLine poLine = (PoLine) poLines.get(i);
				BigDecimal quantity;
				BigDecimal qtyInvoiced;
				if(qtyInvCalculateOption.equals("Y"))
				{
					quantity = poLine.getQuantity();
					qtyInvoiced = poLine.getQtyInvoiced();
				}else{
					quantity = poLine.getQuantity().abs();
					qtyInvoiced = poLine.getQtyInvoiced().abs();
					}
				
				if(qtyInvoiced.compareTo(new BigDecimal(0))<=0)
				{
					poLine.setPyStatus(DocumentStatus.PY_NOTINVOICED );
				}
				else if(quantity.compareTo(qtyInvoiced)<=0)
				{
					poLine.setPyStatus(DocumentStatus.PY_FULLYINVOICED);
				}
				else
				{
					poLine.setPyStatus(DocumentStatus.PY_PARTIALLYINVOICED);
				}
				
				incomingRequest.put("poLine", poLine);
										
				process.executeProcess(incomingRequest);
				if (process.getStatus() < Status.SUCCEEDED) 
				{
					throw new Exception("Po Line save as process failed.");
				}
				//update Polines
				
				BigDecimal icLineKey = poLine.getIcLineKey(); 
				BigDecimal icPoLine = poLine.getIcPoLine();
				
				if(icLineKey.compareTo(new BigDecimal(0))>0 && icPoLine.compareTo(new BigDecimal(0))>0){
					String sql = "from PoLine as po where po.icLineKey = ? and po.icPoLine <> ?";
					List poLineList = dbs.query(sql,new Object[]{icLineKey,icPoLine}, new Type[] {Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL});
					if (poLineList != null)
					{
						for (Iterator it = poLineList .iterator(); it.hasNext(); ) 
						{
							PoLine poLineNew = (PoLine) it.next();
							poLineNew.setPyStatus(poLine.getPyStatus());
							poLineNew.setQtyInvoiced(qtyInvoiced);
							incomingRequest.put("poLine", poLine);
							process.executeProcess(incomingRequest);
							if (process.getStatus() < Status.SUCCEEDED)			
								throw new Exception("Po Line save as process failed.");
						}
					}
				}
				
			}
		
			result = poLines;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}