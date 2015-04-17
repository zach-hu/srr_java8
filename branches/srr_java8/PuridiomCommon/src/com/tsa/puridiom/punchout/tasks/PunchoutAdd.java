package com.tsa.puridiom.punchout.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.catalog.tasks.CatalogSetValues;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.Punchout;

public class PunchoutAdd extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
		public Object  executeTask (Object object) throws Exception
		{
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");

			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BigDecimal newIC	= new BigDecimal(ukg.getUniqueKey());
			String IcPunchout	= newIC.toString();

			Punchout punchout = new Punchout(newIC);
			incomingRequest.put("punchoutId", IcPunchout);

			PunchoutSetValues punchoutValues = new PunchoutSetValues();
			incomingRequest.put("punchout", punchout);
			punchout = (Punchout) punchoutValues.executeTask(incomingRequest);

			dbs.add(punchout);
			this.setStatus(dbs.getStatus());

			return punchout;
		}

	}