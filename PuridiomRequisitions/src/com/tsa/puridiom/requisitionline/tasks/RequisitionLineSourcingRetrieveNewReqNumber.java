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

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.*;

/**
 * @author Matthew
 */
public class RequisitionLineSourcingRetrieveNewReqNumber extends Task {
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
        String queryString = "from RequisitionLine as b where b.icLineHistory = ? and b.reqType != 'M' order by b.vendorId, b.udf7Code, b.udf10Code ASC";

		for (int i = 0; i < i_linecount; i++)
		{
			RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
			BigDecimal icLineHistory = reqLine.getIcLineHistory();
			List result;
			result = dbs.query(queryString,	new Object[] {icLineHistory},	new Type[] {Hibernate.BIG_DECIMAL} ) ;
			if (result != null && result.size() > 0)
			{
				lineResult.add(result);
			}
		}

		int	j_linecount = 0;

		if (lineResult != null)
		{
			j_linecount = lineResult.size();
		}

		List result = new ArrayList();
		queryString = "from RequisitionHeader as RequisitionHeader where RequisitionHeader.icReqHeader = ? ";

		for (int j = 0; j < j_linecount; j++)
		{
			List resultList = (List) lineResult.get(j);
			RequisitionLine reqLine = (RequisitionLine) resultList.get(0);
			BigDecimal icReqHeader = reqLine.getIcReqHeader();

			result.add(dbs.query(queryString, new Object[] {icReqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ) ;
		}

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}