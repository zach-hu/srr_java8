/*
 * Created on Sep 30, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks.PoLineUpdateRfqLineStatus.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class PoLineUpdateRfqLineStatus extends Task
{

    public Object executeTask(Object object) throws Exception
    {
	    try
		{
		  Map incomingRequest = (Map)object;
		  String icRfqLine = (String)incomingRequest.get("PoLine_icRfqLine");

		  if (incomingRequest.containsKey("poLine") && icRfqLine == null  )
          {
              PoLine poLine = (PoLine) incomingRequest.get("poLine");
              if (poLine != null)
              {
            	  BigDecimal bd_icRfqLine = poLine.getIcRfqLine();
            	  icRfqLine = bd_icRfqLine.toString();
              }
          }
		  if(!Utility.isEmpty(icRfqLine) && !icRfqLine.equals("0"))
		  {
			  String status = (String) incomingRequest.get("delete_option");
		      DBSession dbsession = (DBSession)incomingRequest.get("dbsession");
		      String updateSql = "Update Rfq_Line set status = ? where ic_rfq_line = ?";
		      Object [] args = {status, icRfqLine};
		      Integer [] types = {Types.VARCHAR, Types.VARCHAR};
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
