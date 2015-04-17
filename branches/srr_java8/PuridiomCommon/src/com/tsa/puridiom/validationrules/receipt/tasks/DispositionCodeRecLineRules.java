package com.tsa.puridiom.validationrules.receipt.tasks;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class DispositionCodeRecLineRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String statusLine = "true";

		try
        {
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        Object objQtyRejected = (Object) incomingRequest.get("ReceiptLine_qtyRejected");
	        Object objDispositionCode = (Object) incomingRequest.get("ReceiptLine_dispositionCode");

	        if (objQtyRejected != null && objDispositionCode != null)
	        {
	        	if( objQtyRejected instanceof String && objDispositionCode instanceof String )
	        	{
	        		String qtyRejected = (String)objQtyRejected;
	        		String dispositionCode    = (String)objDispositionCode;

	        		if(HiltonUtility.isEmpty(qtyRejected))
	        		{
	        			qtyRejected = "0";
	        		}
	        		if(HiltonUtility.isEmpty(dispositionCode))
	        		{
	        			dispositionCode = "";
	        		}

	        		BigDecimal bigdecimalQtyRejected = new BigDecimal(qtyRejected);

	        		if ( !( bigdecimalQtyRejected.compareTo((new BigDecimal(0))) == 0 ) )
	        		{
	        			String queryString = "";
	        			List resultList = null;

	        			// dispositionCode valid
	        			queryString = "from StdTable stdTable where stdTable.id.tableType = 'DISP' AND stdTable.id.tableKey = ? AND (stdTable.status = '02' OR (stdTable.status = '01' AND stdTable.dateExpires >= ?))";
	        			resultList = dbs.query(queryString, new Object[] { dispositionCode, Calendar.getInstance().getTime() }, new Type[] { Hibernate.STRING, Hibernate.DATE });

	        			if (resultList == null || resultList.size() < 1)
	        			{
	        				statusLine = "false";
	        			}
	        		}
	        	}
	        	else if( objQtyRejected instanceof String[] && objDispositionCode instanceof String[] )
	        	{
	        		String[] arrayQtyRejected = (String[])   objQtyRejected;
	        		String[] arrayDispositionCode = (String[]) objDispositionCode;

	        		for (int i=0; i< arrayQtyRejected.length; i++)
	        		{
	        			if(HiltonUtility.isEmpty(arrayQtyRejected[i]))
	        			{
	        				arrayQtyRejected[i] = "0";
	        			}
	        			if(HiltonUtility.isEmpty(arrayDispositionCode[i]))
	        			{
	        				arrayDispositionCode[i] = "";
	        			}
	        			BigDecimal bigdecimalQtyRejected = new BigDecimal(arrayQtyRejected[i]);

	        			if ( !( bigdecimalQtyRejected.compareTo((new BigDecimal(0))) == 0 ) )
	        			{
	        				String queryString = "";
	        				List resultList = null;


	        				queryString = "from StdTable stdTable where stdTable.id.tableType = 'DISP' AND stdTable.id.tableKey = ? AND (stdTable.status = '02' OR (stdTable.status = '01' AND stdTable.dateExpires >= ?))";
	        				resultList = dbs.query(queryString, new Object[] { arrayDispositionCode[i], Calendar.getInstance().getTime() }, new Type[] { Hibernate.STRING, Hibernate.DATE });

	        				if (resultList == null || resultList.size() < 1)
	        				{
	        					statusLine = "false";
	        					break;
	        				}

	        			}

	        		}
	        	}
	        }
	        incomingRequest.put("isDispositionCodeValid", statusLine);

	        this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at DispositionCodeRecLineRules" + e);
            throw new TsaException("An Error occurred at DispositionCodeRecLineRules", e);
		}
		return statusLine;
    }
}