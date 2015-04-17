package com.tsa.puridiom.requisitionline.rules;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class RequisitionLineAddToCatalogRule extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
        {
	    	List requisitionLineList = (List) incomingRequest.get("requisitionLineList");

	        for (Iterator it = requisitionLineList.iterator(); it.hasNext(); )
	        {
	        	RequisitionLine requisitionLine = (RequisitionLine) it.next();

	        	if ( requisitionLine.getUdf1Code().equals("Y"))	// HOYA udf1Code is for Add To Catalog flag
	        	{
	        		if (HiltonUtility.isEmpty(requisitionLine.getItemNumber()))
	        		{
	        			incomingRequest.put("RequisitionLineAddToCatalogRule", "failed");
	        		}
	        	}
	        }
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at RequisitionLineAddToCatalogRule", e);
		}
		return result;
    }
}