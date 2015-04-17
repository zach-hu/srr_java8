/*
 * Created on Sep 7, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoSetReleasetInfo.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoSetReleasetInfo extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String poNumber = (String)incomingRequest.get("PoHeader_poNumber");
            String blanketType = (String)incomingRequest.get("blanketType");
			PoBlanketInfo blanketInfo =(PoBlanketInfo)incomingRequest.get("blanketInfo");

//          **** Locate Blanket Order Header for Blanket Info
			String queryString = "	SELECT poHeader.effectiveDate, poHeader.expirationDate, poHeader.blanketLimit, poHeader.releaseLimit, poHeader.icPoHeader " +
					"FROM PoHeader as poHeader " +
					"WHERE ( poHeader.poNumber = ? ) AND ( poHeader.poType = ? ) AND ( poHeader.lastRevision = 'C' )";

			List resultList = dbs.query(queryString, new Object[] {poNumber,  blanketType} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

			if(resultList != null && resultList.size() > 0)
			{
			    Object releaseInfo[] = (Object[])resultList.get(0);

				blanketInfo.setPoNumber(poNumber);
				blanketInfo.setPoEffective((java.sql.Timestamp)releaseInfo[0]);
				blanketInfo.setPoExpires((java.sql.Timestamp)releaseInfo[1]);
				blanketInfo.setPoBlanket((BigDecimal)releaseInfo[2]);
				blanketInfo.setPoReleaseLimit((BigDecimal)releaseInfo[3]);
				incomingRequest.put("blanketIc", (BigDecimal)releaseInfo[4]);

				this.setStatus(Status.SUCCEEDED);

				ret = blanketInfo;
			}
			else
			{
			    this.setStatus(Status.FAILED);
			    throw new TsaException(this.getName() + ", Parent order cannot be located!");
			}

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
