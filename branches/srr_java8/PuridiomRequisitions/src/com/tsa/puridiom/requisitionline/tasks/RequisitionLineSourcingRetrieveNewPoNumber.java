/*
 * Created on July 8, 2011
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Matthew
 */
@SuppressWarnings("unchecked")
public class RequisitionLineSourcingRetrieveNewPoNumber extends Task {
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

	    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	    List	reqLineList = (List) incomingRequest.get("requisitionLineList");
	    int	i_linecount = 0;

		if (reqLineList != null)
		{
			i_linecount = reqLineList.size();
		}
		
		List   lineResult = new ArrayList();
        String queryString = "from PoLine as b where b.icLineHistory = ? order by b.lineNumber ASC";

		for (int i = 0; i < i_linecount; i++)
		{
			RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
			BigDecimal icLineHistory = reqLine.getIcLineHistory();
			List result;
			result = dbs.query(queryString,	new Object[] {icLineHistory},	new Type[] {Hibernate.BIG_DECIMAL} ) ;
			if (result != null && result.size() > 0)
			{
				lineResult.add(result);
			} else {
				lineResult.add(new ArrayList());
			}
		}
	    
		int	j_linecount = 0;

		if (lineResult != null)
		{
			j_linecount = lineResult.size();
		}

		List result = new ArrayList();
		queryString = "from PoHeader as PoHeader where PoHeader.icPoHeader = ? AND PoHeader.poNumber NOT LIKE 'N/A'";

		for (int j = 0; j < j_linecount; j++)
		{
			List resultList = (List) lineResult.get(j);
			if(resultList.size() > 0){
				PoLine poLine = (PoLine) resultList.get(0);
				BigDecimal icPoHeader = poLine.getIcPoHeader();

				result.add(dbs.query(queryString, new Object[] {icPoHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ) ;
			} else {
				result.add(new ArrayList());
			}
		}

		this.setStatus(dbs.getStatus()) ;
		
		return result ;
	}

}