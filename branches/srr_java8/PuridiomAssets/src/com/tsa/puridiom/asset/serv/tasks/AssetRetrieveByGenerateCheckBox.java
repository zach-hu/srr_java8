/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetRetrieveByGenerateCheckBox extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return an Asset_tagNumber list
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List assetList = new ArrayList();
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			Object tagNumber = incomingRequest.get("Asset_tagNumberList");
			String Asset_tagNumber[];
			if (tagNumber instanceof String[])
			{
			    Asset_tagNumber = (String[]) incomingRequest.get("Asset_tagNumberList");
			}
			else
			{
			    Asset_tagNumber = new String[1];
			    Asset_tagNumber[0] = (String) tagNumber;
			}
			for (int i=0; i<Asset_tagNumber.length; i++)
			{
				String generateValue = (String) incomingRequest.get("Asset_generateTag"+Asset_tagNumber[i]);
				if (generateValue.equalsIgnoreCase("1"))
				{
					String queryString = "from Asset Asset where Asset.id = ? ";
					List resultList = dbs.query(queryString, new Object[] {Asset_tagNumber[i]} , new Type[] { Hibernate.STRING});
					if (resultList != null && resultList.size() > 0)
					{
						Asset asset = (Asset) resultList.get(0);
						assetList.add(asset);
					}
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return assetList;
	}
}
