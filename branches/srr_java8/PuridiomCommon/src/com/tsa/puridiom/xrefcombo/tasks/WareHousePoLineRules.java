package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class WareHousePoLineRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String failed = "N";
		String statusLine = "succeeded";

		try
        {
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	    	List poLineList = (List) incomingRequest.get("poLineList");

	    	if (poLineList != null || poLineList.size() > 0)
			{

				for (Iterator it = poLineList.iterator(); it.hasNext();)
				{
					PoLine poLine = (PoLine) it.next();

					if (!poLine.getItemNumber().equalsIgnoreCase("") && !poLine.getItemNumber().startsWith("*"))
					{
						String itemNumber = poLine.getItemNumber();
						String warehouse = poLine.getUdf5Code();

						String queryString = "";
						List resultList = null;

						// itemNumber, warehouse should be valid on CatalogItem
						queryString = "from CatalogItem catalogItem where catalogItem.id.itemNumber = ? AND catalogItem.status = '02'";
						resultList = dbs.query(queryString, new Object[] { itemNumber }, new Type[] { Hibernate.STRING });

						if (resultList == null || resultList.size() < 1)
						{
							statusLine = "failed";
							break;
						}

						// // itemNumber, warehouse should be valid on XrefCombo
						queryString = "from XrefCombo xrefCombo where xrefCombo.xrefType = 'MXPW' AND xrefCombo.code1 = ? AND xrefCombo.code2 = ? AND xrefCombo.status = '02'";
						resultList = dbs.query(queryString, new Object[] { warehouse, itemNumber }, new Type[] { Hibernate.STRING, Hibernate.STRING });
						if (resultList == null || resultList.size() < 1)
						{
							statusLine = "failed";
							break;
						}
					}

				}
			}

	        incomingRequest.put("wareHouseLine", statusLine);

	        this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "n Error occurred at WareHousePoLineRules" + e);
            throw new TsaException("An Error occurred at WareHousePoLineRules", e);
		}
		return failed;
    }
}