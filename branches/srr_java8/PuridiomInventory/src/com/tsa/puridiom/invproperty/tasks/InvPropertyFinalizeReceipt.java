/*
 * Created on Sep 30, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks.PoLineUpdateRfqLineStatus.java
 *
 */
package com.tsa.puridiom.invproperty.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class InvPropertyFinalizeReceipt extends Task
{

    public Object executeTask(Object object) throws Exception
    {
	    try
		{
		  Map incomingRequest = (Map)object;

		  String icRecLine = (String)incomingRequest.get("ReceiptLine_icRecLine");

		  if(!Utility.isEmpty(icRecLine) && !icRecLine.equals("0"))
		  {
		      DBSession dbsession = (DBSession)incomingRequest.get("dbsession");
		      String updateSql = "UPDATE INV_PROPERTY SET STATUS = '02' where IC_REC_LINE = ? AND STATUS = '00'" ;
		      Object [] args = new Object[1];
		      Integer [] types = new Integer[1];
		      args[0] = icRecLine;
		      types[0] = Types.VARCHAR;
		      
		      dbsession.sqlUpdate(updateSql, args, types);
		      this.setStatus(dbsession.getStatus());
		  }
		  else
		  {
		      this.setStatus(Status.SUCCEEDED);
		  }
		}
		catch (Exception e)
		{
		    this.setStatus(Status.FAILED);
		    throw new TsaException(this.getName(), e);
		}
        return super.executeTask(object);
    }
}
