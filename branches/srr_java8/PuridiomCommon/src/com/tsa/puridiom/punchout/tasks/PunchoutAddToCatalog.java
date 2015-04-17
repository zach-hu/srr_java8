package com.tsa.puridiom.punchout.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.tsa.puridiom.entity.Punchout;
import com.tsa.puridiom.entity.Catalog;

public class PunchoutAddToCatalog extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
		public Object  executeTask (Object object) throws Exception
		{
			Map incomingRequest = (Map)object;
			Object result = null;

			try
			{
				DBSession dbs = (DBSession)incomingRequest.get("dbsession");
				String catalogId = (String ) incomingRequest.get("Catalog_catalogId");

				String queryString = "from Catalog as Catalog where Catalog.catalogId = ? ";
				List resultList = dbs.query(queryString, new Object[] {catalogId} , new Type[] { Hibernate.STRING}) ;

				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
				this.setStatus(dbs.getStatus()) ;

				try{
					Object obj = resultList.get(0);
					Catalog catalog = (Catalog) obj;
					Punchout punchout = (Punchout) incomingRequest.get("punchout");

					BigDecimal ic	= punchout.getIcPunchout();
					catalog.setIcPunchout(ic);

					incomingRequest.put("punchout", punchout);
					incomingRequest.put("catalog", catalog);

					dbs.update(catalog);
				}
				catch (Exception e){
					incomingRequest.put("punchout", null);
					incomingRequest.put("catalog", null);
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