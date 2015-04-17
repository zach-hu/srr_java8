/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.assetnote.serv.tasks;

import java.util.Map;
import java.util.List;
import java.math.BigDecimal;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;


import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class AssetNoteLastSequence extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DBSession dbs =(DBSession)incomingRequest.get("dbsession");
			String AssetNote_tagNumber = (String)incomingRequest.get("AssetNote_tagNumber");

			if (Utility.isEmpty(AssetNote_tagNumber))
			{
				AssetNote_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			}

			String sql = "Select MAX(AssetNote.id.sequenceNo) from AssetNote AssetNote Where AssetNote.id.tagNumber = ?";
			List resultList = dbs.query(sql, new Object[] {AssetNote_tagNumber} , new Type[] { Hibernate.STRING});

			if(resultList != null && resultList.size() > 0)
            {
                ret = resultList.get(0);
                if(ret==null)
                {
                	ret = new BigDecimal(0);
                }
            }
            else
            {
            	ret = new BigDecimal(0);
            }
            this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
