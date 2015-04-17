/**
 *
 */
package com.tsa.puridiom.xrefcombo.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Richard
 */
public class XrefComboRetrieveFromCatalogItem extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String wareHouse = HiltonUtility.ckNull((String) incomingRequest.get("warehouse"));
			String itemNumber = HiltonUtility.ckNull((String) incomingRequest.get("CatalogItem_itemNumber"));

			if (!HiltonUtility.isEmpty(wareHouse) && !HiltonUtility.isEmpty(itemNumber))
			{
				String queryString = "from XrefCombo xrefCombo where xrefCombo.xrefType = 'MXPW' AND xrefCombo.code1 = ? AND xrefCombo.code2 = ? ";
				List resultList = dbs.query(queryString, new Object[] { wareHouse, itemNumber }, new Type[] { Hibernate.STRING, Hibernate.STRING });

				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
			}

			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "XrefComboRetrieveFromCatalogItem error " + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

}