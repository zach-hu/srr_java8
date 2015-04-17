/**
 *
 * Created on Jan 26, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.RfqLine.tasks.RfqLineFromReqSetup.java
 *
 */
package com.tsa.puridiom.xrefcombo.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.XrefCombo;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class XrefComboFromReqLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
	    Object result = null;

	    try
	    {
	    	DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			if(reqLine != null)
			{
				String accountFld2FromXrefCombo = "";
				String wareHouse = reqLine.getUdf5Code();
				String itemNumber = reqLine.getItemNumber();
				if( !HiltonUtility.isEmpty(wareHouse) && ! HiltonUtility.isEmpty(itemNumber) )
				{
					String queryString = "from XrefCombo xrefCombo where xrefCombo.xrefType = 'MXPW' AND xrefCombo.code1 = ? AND xrefCombo.code2 = ? ";
					List resultList = dbs.query(queryString, new Object[] { wareHouse, itemNumber } , new Type[] { Hibernate.STRING, Hibernate.STRING } );
						
					if (resultList != null && resultList.size() > 0)
					{
						XrefCombo xrefCombo = (XrefCombo)resultList.get(0);
						accountFld2FromXrefCombo = xrefCombo.getCode4();
					}
				}	
				
				result = accountFld2FromXrefCombo;
				this.setStatus(Status.SUCCEEDED);
			}
			
	    }
	    catch (Exception e)
	    {
            this.setStatus(Status.FAILED);
            Log.error(this, "Error on XrefComboFromReqLineSetup");
            e.printStackTrace();
            throw e;
        }
		return result;
	}

}
