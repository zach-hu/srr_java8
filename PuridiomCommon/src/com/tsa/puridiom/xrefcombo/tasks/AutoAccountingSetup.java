package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AutoAccountingSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
        {
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        String organizationId = (String) incomingRequest.get("organizationId");
	        RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
	    	String	autoAccountingActive = PropertiesManager.getInstance(organizationId).getProperty("ACCOUNTS", "AUTOACCOUNTING", "N");

	    	if ( autoAccountingActive.equals("Y"))
	    	{
	    		if (requisitionHeader!= null)
	    		{
	    			if (requisitionHeader.getUdf1Code().equals("Y"))
	    			{
	    				incomingRequest.put("autoAccountingActive", "CAPITAL");

	    				String queryString = "from XrefCombo as XrefCombo where XrefCombo.xrefType = 'CPTL' and XrefCombo.status = '02' ";
	        			List resultList = dbs.query(queryString);
	        			Object xrefComboUniqueCPTL = null;
	        			if (resultList != null && resultList.size() > 0)
	        			{
	        				xrefComboUniqueCPTL = resultList.get(0);
	        			}
	        			this.setStatus(dbs.getStatus());
	        			incomingRequest.put("xrefComboUniqueCPTL", xrefComboUniqueCPTL);
	    			}
	    			else if (requisitionHeader.getUdf1Code().equals("N"))
	    			{
	    				incomingRequest.put("autoAccountingActive", "NOCAPITAL");
	    			}
	    		}
	    	}
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at AutoAccountingSetup", e);
		}
		return result;
    }
}