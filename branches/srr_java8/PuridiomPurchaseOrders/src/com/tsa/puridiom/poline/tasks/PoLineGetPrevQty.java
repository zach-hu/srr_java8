/**
 * Created on Mar 4, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineGetPrevQty.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoLineGetPrevQty extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object oldQty = null;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			
			BigDecimal revision = poHeader.getRevisionNumber();
			if(revision.compareTo(new BigDecimal(0)) > 0)
			{
				revision = revision.subtract(new BigDecimal(1));
				BigDecimal release = poHeader.getReleaseNumber();
				String poNumber = poHeader.getPoNumber();
				String item = poLine.getItemNumber();
				if(Utility.isEmpty(item))
				{
					throw new TsaException("Item Number:" + item + "not found! ");
				}
				String sql = "Select poLine.quantity FROM po_header as poHeader, po_line as poLine" +  
								"WHERE ( poHeader.icPoHeader = poLine.icPoHeader ) and " +
								"( ( poHeader.poNumber = ? ) AND " +  
								"( poHeader.releaseNumber = ? ) AND " +  
								"( poHeader.revisionNumber = ? ) AND " +  
								"( poLine.itemNumber = ? ) )" ;
				DBSession dbs = (DBSession)incomingRequest.get("dbsession");
				List resultList = dbs.query(sql, new Object[] {poNumber, release, revision, item } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING});
				if(resultList.size() > 0 )
				{
					oldQty = resultList.get(0);
				}
			}
			else
			{
				oldQty = poLine.getQuantity();
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return oldQty;
	}
}
