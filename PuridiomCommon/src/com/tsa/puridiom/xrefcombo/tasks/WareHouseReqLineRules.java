package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class WareHouseReqLineRules extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String failed = "N";
		String itemNumberCatalogItem = "succeeded";
		String itemNumberWarehouseXrefCombo = "succeeded";

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			List requisitionLineList = (List) incomingRequest.get("requisitionLineList");

			if (requisitionLineList != null && requisitionLineList.size() > 0)
			{

				for (Iterator it = requisitionLineList.iterator(); it.hasNext();)
				{
					RequisitionLine requisitionLine = (RequisitionLine) it.next();
					List resultList = null;
					String queryString = "";
					String itemNumber = requisitionLine.getItemNumber();
					String warehouse = requisitionLine.getUdf5Code();
					String catalogId = requisitionLine.getCatalogId();

					Date today = new Date();

					if (!HiltonUtility.isEmpty(itemNumber))
					{
						if (!itemNumber.startsWith("*") && itemNumberCatalogItem.equalsIgnoreCase("succeeded"))
						{
							// itemNumber, warehouse should be valid on
							// CatalogItem
							queryString = "from CatalogItem catalogItem where catalogItem.id.catalogId = ? AND catalogItem.id.itemNumber = ? AND " +
									"( catalogItem.status = '02' OR (catalogItem.status = '01' AND catalogItem.dateExpires >= ?) ) ";
							resultList = dbs.query(queryString, new Object[] { catalogId, itemNumber, today }, new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.DATE });

							if ( resultList.size() < 1)
							{
								itemNumberCatalogItem="failed";
							}
						}

						if (itemNumberWarehouseXrefCombo.equalsIgnoreCase("succeeded"))
						{
							// // itemNumber, warehouse should be valid on XrefCombo
							queryString = "from XrefCombo xrefCombo where xrefCombo.xrefType = 'MXPW' AND xrefCombo.code1 = ? AND xrefCombo.code2 = ? AND " +
									"( xrefCombo.status = '02' OR (xrefCombo.status = '01' AND xrefCombo.dateExpires >= ?) )";
							resultList = dbs.query(queryString, new Object[] { warehouse, itemNumber, today }, new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.DATE });

							if (resultList.size() < 1)
							{
								itemNumberWarehouseXrefCombo = "failed";
							}
						}
					}
				}
			}

			incomingRequest.put("itemNumberCatalogItem", itemNumberCatalogItem);
			incomingRequest.put("itemNumberWarehouseXrefCombo", itemNumberWarehouseXrefCombo);
			//incomingRequest.put("wareHouseLine", statusLine);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at WareHouseReqLineRules" + e);
			e.printStackTrace();
			throw e;
		}
		return failed;
	}
}