package com.tsa.puridiom.xrefcombo.tasks;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.ReceiptLine;
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
		String failed = "N";
		String statusLine = "succeeded";

		try
        {
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	    	List lineitems = (List) incomingRequest.get("lineitems");

	    	if (lineitems != null || lineitems.size() > 0)
			{

				for (Iterator it = lineitems.iterator(); it.hasNext();)
				{
					ReceiptLine receiptLine = (ReceiptLine) it.next();

					if ( ! ( receiptLine.getQtyRejected().intValue() < 1 ) )
					{
						String dispositionCode = receiptLine.getDispositionCode();

						String queryString = "";
						List resultList = null;

						// dispositionCode valid on CatalogItem
						queryString = "from StdTable stdTable where stdTable.id.tableType = 'DISP' AND stdTable.id.tableKey = ? AND (stdTable.status = '02' OR (stdTable.status = '01' AND stdTable.dateExpires >= ?))";
						resultList = dbs.query(queryString, new Object[] { dispositionCode, Calendar.getInstance().getTime() }, new Type[] { Hibernate.STRING, Hibernate.DATE });

						if (resultList == null || resultList.size() < 1)
						{
							statusLine = "failed";
							break;
						}

					}

				}
	    	}
	        incomingRequest.put("dispositionCodeLine", statusLine);

	        this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "n Error occurred at DispositionCodeRecLineRules" + e);
            throw new TsaException("An Error occurred at DispositionCodeRecLineRules", e);
		}
		return failed;
    }
}