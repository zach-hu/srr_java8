package com.tsa.puridiom.asset.serv.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvItemRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		/*
		 * @author EDSAC
		 * return an InvItem according with his itemNumber
		 */
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			String itemNumber = poLine.getItemNumber();
			String queryString = "from InvItem InvItem where InvItem.itemNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {itemNumber} , new Type[] {Hibernate.STRING}) ;
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}