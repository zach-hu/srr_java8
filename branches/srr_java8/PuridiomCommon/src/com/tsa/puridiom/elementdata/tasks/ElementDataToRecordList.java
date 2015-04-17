package com.tsa.puridiom.elementdata.tasks;

import com.tsa.puridiom.entity.ElementData;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ElementDataToRecordList extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			// Data list is sorted by icHeader icLine, icSequence, elementId
			List dataList = (List) incomingRequest.get("formDataList") ;
			List recordList = new ArrayList() ;

			if (dataList.size() > 0) {
				String firstElement = getFirstElement(dataList) ;
				Iterator it = dataList.iterator() ;
				ElementData ed = (ElementData) it.next();
				while (it.hasNext()) {
					if (ed.getComp_id().getElementId().equals(firstElement)) {
						HashMap mp = new HashMap() ;
						while (true) {
							String element = ed.getComp_id().getElementId() ;
							mp.put(element, ed.getElementValue()) ;
							if (! it.hasNext()) break ;
							ed = (ElementData) it.next();
							if (ed.getComp_id().getElementId().equals(firstElement)) break ;
						}
						recordList.add(mp) ;
					}
				}
			}

			result = recordList ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}


	private int recordCount(List dataList) {
		int count = 0;
		if (dataList.size() > 0) {
			String firstElement  = getFirstElement(dataList) ;
			for (int i = 0; i < dataList.size(); i++) {
				ElementData ed = (ElementData) dataList.get(i) ;
				if (firstElement.compareTo(ed.getComp_id().getElementId()) == 0) {
					count++;
				}
			}
		}
		return count  ;
	}

	private String getFirstElement(List dataList) {
		ElementData ed = (ElementData) dataList.get(0) ;
		return ed.getComp_id().getElementId() ;
	}

}