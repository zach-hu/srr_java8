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
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.*;

/**
 * @author Matthew
 */
public class RequisitionLineSourcingRetrieveNewRecNumber extends Task {
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

	    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	    List	reqHeaderList = (List) incomingRequest.get("requisitionLineHistoryReqList");
	    List	poHeaderList = (List) incomingRequest.get("requisitionLineHistoryPoList");
		int	i_linecount = 0;

		if (poHeaderList != null)
		{
			i_linecount = poHeaderList.size();
		}

		List   result = new ArrayList();

		for (int i = 0; i < i_linecount; i++)
		{
			List resultList = (List) poHeaderList.get(i);
			try {
					PoHeader poHeader = (PoHeader) resultList.get(0);
					BigDecimal icPoHeader = poHeader.getIcPoHeader();
					String queryString = "from ReceiptHeader as ReceiptHeader where ReceiptHeader.icPoHeader = ? AND ReceiptHeader.receiptNumber NOT LIKE '' order by ReceiptHeader.receiptNumber DESC";
					result.add(dbs.query(queryString, new Object[] {icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL}));
			} catch (Exception e) {
				try {
					resultList = (List) reqHeaderList.get(i);
					RequisitionHeader reqHeader = (RequisitionHeader) resultList.get(0);
					BigDecimal icReqHeader = reqHeader.getIcReqHeader();
					String queryString = "from ReceiptHeader as ReceiptHeader where ReceiptHeader.icReqHeader = ? AND ReceiptHeader.icReqHeader NOT LIKE '0' AND ReceiptHeader.receiptNumber NOT LIKE '' order by ReceiptHeader.receiptNumber DESC";
					result.add(dbs.query(queryString, new Object[] {icReqHeader } , new Type[] { Hibernate.BIG_DECIMAL}));
				} catch (Exception e2){
					result.add("");
				}
			}
		}
		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}