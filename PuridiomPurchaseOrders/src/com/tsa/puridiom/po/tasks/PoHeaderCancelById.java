/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.pp.tasks.PoCancelCheck.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.po.exceptions.PoCancelException;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderCancelById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poFrom = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_poFrom"));
			String poTo = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_poTo"));
			String poNumber = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_poNumber"));
			String poEnt = HiltonUtility.ckNull((String) incomingRequest.get("Account_fld1"));
			String poDept = HiltonUtility.ckNull((String) incomingRequest.get("Account_fld2"));
			Date poDateFrom = Dates.getDate(poFrom);
			Date poDateTo = Dates.getDate(poTo);
			String queryString = "select PoHeader from PoHeader as PoHeader, Account as Account, PoLine as PoLine where PoHeader.icPoHeader = PoLine.icPoHeader and PoLine.icPoHeader = Account.id.icHeader and PoHeader.status <> 9010 and PoHeader.fiscalYear <> 2008 ";
			if(poFrom != "") {
				queryString += "and PoHeader.poDate >= '" + poDateFrom + "' ";
			}
			if(poTo != "") {
				queryString += "and PoHeader.poDate <= '" + poDateTo + "' ";
			}
			if(poNumber != "") {
				queryString += "and PoHeader.poNumber LIKE '" + poNumber + "' ";
			}
			if(poFrom != "") {
				queryString += "and Account.fld1 = '" + poEnt + "' " ;
			}
			if(poFrom != "") {
				queryString += "and Account.fld2 = '" + poDept + "' ";
			}
			List resultList = dbs.query(queryString) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
			}
			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "Error Message: " + e.getMessage());
			throw e;
		}
		return result;
	}

}