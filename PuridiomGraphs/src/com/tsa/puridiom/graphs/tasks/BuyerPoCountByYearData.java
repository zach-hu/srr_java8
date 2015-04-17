/*
 * Created on Dec 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs.tasks;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.math.BigDecimal ;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility ;
import com.tsa.puridiom.graphs.exceptions.ProcessException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import java.util.Calendar;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BuyerPoCountByYearData extends Task
{


    public Object executeTask(Object object) throws Exception
    {
        List ret = null;
		List newList = new ArrayList() ;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
            Calendar cal = Calendar.getInstance() ;

            int fromYear = cal.get(cal.YEAR) - 1;
            int toYear = cal.get(cal.YEAR) ;
            String chartFromYear = Integer.toString(fromYear) ;
            String chartToYear = Integer.toString(toYear) ;

            String sql = "select sum(us.statCount), us.id.statYear, us.id.statMonth from UserStatistic as us where us.id.statType = 'PO-TYPE' and us.id.statYear >= ? and us.id.statYear <= ? AND us.id.userId = ? group by us.id.statYear, us.id.statMonth order by us.id.statMonth, us.id.statYear";
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String userId = (String) incomingRequest.get("userId");

			ret = dbs.query(sql, new Object[] {chartFromYear, chartToYear, userId} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;

			// Build new list from sorted list
			int cur = 0;
			for (int mn = 1; mn <= 12; mn++) {
				String itMonth = Integer.toString(mn) ;
				String monthText = HiltonUtility.getFormattedMonth(mn, organizationId, "MMM") ;
				if (itMonth.length() < 2) itMonth = "0" + itMonth ;
				for (int yr = fromYear; yr <= toYear; yr++) {
					String itYear = Integer.toString(yr) ;
					if (cur < ret.size()) {
						Object vf[] = (Object[])ret.get(cur);
						if ((((String)vf[1]).equals(itYear) && ((String)vf[2]).equals(itMonth))) {
							vf[2] = monthText ;
							newList.add(vf) ;
							cur++ ;
						} else {
							Object vt[] = {new BigDecimal(0), itYear, monthText} ;
							newList.add(vt) ;
						}
					} else {
						Object vt[] = {new BigDecimal(0), itYear, monthText} ;
						newList.add(vt) ;
					}
				}
			}
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new ProcessException("BuyerPoCountData" + e.toString());
        }

        return newList;
    }
}
