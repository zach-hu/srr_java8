package com.tsa.puridiom.catalog.tasks;

import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class CatalogUpdateStatusByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String catalogStatus = (String) incomingRequest.get("Catalog_status");
			String vendorId      = (String) incomingRequest.get("Vendor_vendorId");

			if (!HiltonUtility.isEmpty(vendorId) && !HiltonUtility.isEmpty(catalogStatus))
			{
				if (catalogStatus.equals("01") || catalogStatus.equals("02") || catalogStatus.equals("03"))
				{
					String sqlUpdate = "update catalog set status = ? where vendor_id = ?";
					DBSession dbs = (DBSession)incomingRequest.get("dbsession");
					Object [] args = new Object[2];
					Integer [] types = new Integer[2];
					args[0] = catalogStatus;
					types[0] = Types.VARCHAR;
					args[1] = vendorId;
					types[1] = Types.VARCHAR;
					dbs.sqlUpdate(sqlUpdate, args, types);
					this.setStatus(dbs.getStatus());

					String queryString = "Select Catalog.catalogId from Catalog as Catalog where Catalog.vendorId=?";
					args = new Object[1];
					Type[] hTypes = new Type[1];
					args[0] = vendorId;
					hTypes[0] = Hibernate.STRING;
					List resultList = dbs.query(queryString, args, hTypes);
					if ( resultList != null && resultList.size() > 0  )
					{
						for (Iterator it = resultList.iterator(); it.hasNext(); )
					    {
							String catalogId = (String)it.next();
							sqlUpdate = "update catalog_item set status = ? where catalog_id = ?";
							args = new Object[2];
							types = new Integer[2];
							args[0] = catalogStatus;
							types[0] = Types.VARCHAR;
							args[1] = catalogId;
							types[1] = Types.VARCHAR;
							dbs.sqlUpdate(sqlUpdate, args, types);
							this.setStatus(dbs.getStatus());
					    }
					}
					this.setStatus(dbs.getStatus());
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}