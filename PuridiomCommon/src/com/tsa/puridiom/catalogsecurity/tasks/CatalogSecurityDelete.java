/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.catalogsecurity.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Administrator
 */

public class CatalogSecurityDelete extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;

        try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			//BigDecimal icHeader = (BigDecimal) incomingRequest.get("CatalogSecurity_icHeader");
			Object objCatalogId = (Object) incomingRequest.get("CatalogSecurity_catalogId");
			Object objItemNumber = (Object) incomingRequest.get("CatalogSecurity_itemNumber");

			String catalogId = "";
			String itemNumber = "";

			if(objCatalogId instanceof String)
			{
				catalogId = objCatalogId.toString();
				itemNumber = objItemNumber.toString();
			}
			else
			{
				catalogId = ((String[])objCatalogId)[0].toString();
				itemNumber =((String[])objItemNumber)[0].toString();
			}
			String queryString = "from CatalogSecurity as cs where  cs.catalogId = ? and cs.itemNumber=? ";
			this.setStatus(dbs.delete(queryString, new Object[] {  catalogId, itemNumber}, new Type[] { Hibernate.STRING, Hibernate.STRING }));

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			Log.error(this, "Error delete catalogSecurity: \r\n" + e.getMessage());
			throw e;
		}

		return object;
	}

}

