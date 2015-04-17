package com.tsa.puridiom.poheader.autoawardrelease.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * Class that retrieve list of Blanket Orders(BO) with:
 * [ 
 * 	 PO_Header.PO_TYPE= BO, DO, SB
 *   PO_Header.Auto_Release = Y
 *   PO_header.Last_Release = C
 *   PO_Header.Status > 3020 < 90000
 *   PO_header.Effective_Date <= Today
 *   PO_Header.Expiration_date > Today
 * ]
 * 
 * That have a Delivery Scheduled for that day
 *	[ 
 *		a. Schedule.Schedule_date = TODAY
 *  	b. PO_HEADER linked to SCHEDULE by IC_HEADER
 *  	c. Delivery Schedules are coded with SCHED_TYPE = D
 *  	d. Schedule.completionDate must be null 
 * 	]
 * 
 *
 * @author Alexander Richard Angulo Kcana
 *
 */
public class PoHeaderByAutoRelease extends Task
{
	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List<PoHeader> result = new ArrayList<PoHeader>();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from PoHeader PoHeader WHERE " +
			"(PoHeader.poType = 'BO' OR PoHeader.poType = 'DO' OR PoHeader.poType = 'SB') AND " +
				"PoHeader.autoRelease = 'Y' AND " +
				"PoHeader.lastRevision = 'C' AND " +
				"PoHeader.status > '3020' AND PoHeader.status < '9000' AND " +
				"PoHeader.effectiveDate <= ? AND " +
				"PoHeader.expirationDate > ?";

			List resultList = dbs.query(queryString, new Object[] {new Date(), new Date()}, new Type[] {Hibernate.DATE, Hibernate.DATE});
			
			if (resultList != null && resultList.size() > 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				Date date = calendar.getTime();
				
				Iterator<PoHeader> iterator = resultList.iterator();
				while (iterator.hasNext()) {
					PoHeader poHeader = iterator.next();
					String query = "from Schedule schedule WHERE " + 
						"schedule.scheduleDate = ? AND " +
						"schedule.id.icHeader = ? AND " +
						"schedule.id.scheduleType = 'P' AND " + 
						"schedule.completionDate IS NULL ";

					List list = dbs.query(query, new Object[] {date, poHeader.getIcPoHeader()}, new Type[] {Hibernate.DATE, Hibernate.BIG_DECIMAL});
					if (list != null && list.size() > 0) {
						result.add(poHeader);
					}
				}
			}
			
			Log.debug(this, "BOs to AutoAward: " + result.size());
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, "PoHeaderByAutoRelease failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
			throw new TsaException("PoHeaderByAutoRelease failed to retrieve POs: " + e.getMessage(), e);
		}
		return result;
	}
}
