/**
 * 
 */
package com.tsa.puridiom.xrefcombo.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class XrefComboRetrieveFromLineItem extends Task
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
			String type = HiltonUtility.ckNull((String) incomingRequest.get("Account_accountType"));
			Object lineItem = incomingRequest.get("lineItem");
			String wareHouse = "";
			String itemNumber = "";

			if (lineItem != null)
			{
				if (type.equalsIgnoreCase("RQL"))
				{
					RequisitionLine reqLine = (RequisitionLine) lineItem;

					itemNumber = reqLine.getItemNumber();
					wareHouse = reqLine.getUdf5Code();

				} else if (type.equalsIgnoreCase("POL"))
				{
					PoLine poLine = (PoLine) lineItem;

					itemNumber = poLine.getItemNumber();
					wareHouse = poLine.getUdf5Code();
				}

				if (!HiltonUtility.isEmpty(wareHouse) && !HiltonUtility.isEmpty(itemNumber))
				{
					String queryString = "from XrefCombo xrefCombo where xrefCombo.xrefType = 'MXPW' AND xrefCombo.code1 = ? AND xrefCombo.code2 = ? ";
					List resultList = dbs.query(queryString, new Object[] { wareHouse, itemNumber }, new Type[] { Hibernate.STRING, Hibernate.STRING });

					if (resultList != null && resultList.size() > 0)
					{
						result = resultList.get(0);
					}
				}
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "XrefComboRetrieveFromLineItem error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}