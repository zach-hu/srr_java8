package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
//import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;


public class InvoiceApproverListVsrrProj extends Task
{
    public Object executeTask(Object object) throws Exception
    {
    	Map incomingRequest = (Map)object;
    	List result = null;
        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
        	BigDecimal icPoHeader = new BigDecimal(0);
        	String icPoHeaderString = "";
        	if(invoiceHeader == null)
        	{
        		invoiceHeader = new InvoiceHeader();
        	}
        	else
        	{
        		icPoHeader = HiltonUtility.ckNull(invoiceHeader.getIcPoHeader());
        	}
        	
        	String query = "select distinct VSRRProject.udf2Code from VSRRProject as VSRRProject where VSRRProject.id.tableKey in " +
     		"(select account.fld3 from Account as account where account.id.icHeader = ?)";
        	result = dbs.query(query, new Object[] { icPoHeader },new Type[] { Hibernate.BIG_DECIMAL });

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            throw e; 
            //throw new TsaException("An Error ocurred submitting the current Invoice ", e);
        }
        return result;
    }
}
