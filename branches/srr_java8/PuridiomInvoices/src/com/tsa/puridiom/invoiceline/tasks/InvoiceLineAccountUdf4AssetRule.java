package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class InvoiceLineAccountUdf4AssetRule extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
        {
	    	List invoiceLineList = (List) incomingRequest.get("lineitems");

	    	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

	        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); )
	        {
	        	InvoiceLine invoiceLine = (InvoiceLine) it.next();

		        String queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine = ?";
				List accountList = dbs.query(queryString, new Object[] {invoiceLine.getIcIvcHeader(), invoiceLine.getIcIvcLine()}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;

				if ( (accountList != null && accountList.size() > 0) )
				{
		        	for (Iterator it2 = accountList.iterator(); it2.hasNext(); )
				    {
		        		Account account = (Account) it2.next();
			        	String fld4 = account.getFld4(); //		HOYA 	GL Account

			        	if ( invoiceLine.getAsset().equals("Y") && fld4.length() >= 12 )
			       		{
				       		incomingRequest.put("InvoiceLineAccountUdf4AssetRule", "failed");
		        		}
		        		if ( !invoiceLine.getAsset().equals("Y") )
			        	{
			        		queryString = "";
							List resultList = null;

							queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC04' " +
							"AND StdTable.id.tableKey = ? AND StdTable.status = '02' ";
							resultList = dbs.query(queryString, new Object[] {fld4} , new Type[] { Hibernate.STRING}) ;
							if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
							{
								incomingRequest.put("InvoiceLineAccountUdf4AssetRule", "failed");
							}
			        	}
			        }
				}
	        }
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at InvoiceLineAccountUdf4AssetRule", e);
		}
		return result;
    }
}