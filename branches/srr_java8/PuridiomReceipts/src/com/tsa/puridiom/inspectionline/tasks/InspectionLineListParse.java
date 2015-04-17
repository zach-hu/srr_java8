package com.tsa.puridiom.inspectionline.tasks;

import com.tsa.puridiom.entity.InspectionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InspectionLineListParse extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		List inspectionLineList = (List) incomingRequest.get("inspectionLineList");
		if (inspectionLineList == null) inspectionLineList = new ArrayList() ;
		HashMap inspMap = new HashMap() ;
		List inspectionCodeList = new ArrayList() ;
		List inspectionCritList = new ArrayList() ;

		String lastCode = "" ;
		int codeCount = 0 ;
		int critCount = 0;

		for (int i = 0; i < inspectionLineList.size(); i++) {
			InspectionLine inspLine = (InspectionLine) inspectionLineList.get(i) ;
			if (inspLine.getInspectCode().compareTo(lastCode) != 0) {
				lastCode = inspLine.getInspectCode() ;
				codeCount++ ;
				critCount = 0 ;
				inspMap.put("code" + Integer.toString(codeCount), inspLine) ;
			}
			critCount++ ;
			inspMap.put("crit" + Integer.toString(codeCount)  + Integer.toString(critCount),inspLine) ;
			inspectionCodeList.add(inspLine) ;
		}

		incomingRequest.put("inspectionCodeList", inspectionCodeList) ;
		result = inspMap ;
		this.setStatus(dbs.getStatus()) ;

		return result;
	}

}