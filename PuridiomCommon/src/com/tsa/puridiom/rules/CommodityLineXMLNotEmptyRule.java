package com.tsa.puridiom.rules;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class CommodityLineXMLNotEmptyRule extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
        {
	    	String formType = (String)incomingRequest.get("formType");
	    	String poXmlCommodity = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("PO OPTIONS", "XML COMMODITY", "N");
	    	String reqXmlCommodity = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("REQ OPTIONS", "XML COMMODITY", "N");
	    	
	    	if (formType.equals("REQ") && reqXmlCommodity.equalsIgnoreCase("Y"))
  	    	{
	    		List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
	    		for (Iterator it = requisitionLineList.iterator(); it.hasNext(); )
		        {
		        	RequisitionLine requisitionLine = (RequisitionLine) it.next();
		        	String commodity = requisitionLine.getCommodityCode();

					if ( requisitionLine.getItemSource().equalsIgnoreCase("XML") && HiltonUtility.isEmpty(commodity))
					{
						incomingRequest.put("commodity-line-xml-not-empty", "failed");
					}
		        }
  	    	}
	    	else if (formType.equals("PO") && poXmlCommodity.equalsIgnoreCase("Y"))
  	    	{
	    		List poLineList = (List) incomingRequest.get("poLineList");
	    		for (Iterator it = poLineList.iterator(); it.hasNext(); )
		        {
		        	PoLine poLine = (PoLine) it.next();
		        	String commodity = poLine.getCommodity();

					if ( poLine.getItemSource().equalsIgnoreCase("XML") && HiltonUtility.isEmpty(commodity) )
					{
						incomingRequest.put("commodity-line-xml-not-empty", "failed");
					}
		        }
  	    	}
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at CommodityLineXMLNotEmptyRule", e);
		}
		return result;
    }
}