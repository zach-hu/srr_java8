package com.tsa.puridiom.asset.serv.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvItemRetrieveByIdFromPoLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;
		/*
		 * @author EDSAC
		 * return an InvItem list according with the itemNumber
		 */
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			String itemNumber = poLine.getItemNumber();
			String queryString = "from InvItem InvItem where InvItem.itemNumber = ? ";
			result = dbs.query(queryString, new Object[] {itemNumber} , new Type[] {Hibernate.STRING}) ;
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