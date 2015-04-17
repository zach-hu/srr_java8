package com.tsa.puridiom.inspectionstd.tasks;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.InspectionStd;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InspectionStdListParse extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		List inspectionStdList = (List) incomingRequest.get("inspectionStdList");
		if (inspectionStdList == null) inspectionStdList = new ArrayList() ;
		HashMap inspMap = new HashMap() ;
		List inspectionCodeList = new ArrayList() ;
		List inspectionCritList = new ArrayList() ;

		String lastCode = "" ;
		int codeCount = 0 ;
		int critCount = 0;

		for (int i = 0; i < inspectionStdList.size(); i++) {
			InspectionStd inspLine = (InspectionStd) inspectionStdList.get(i) ;
			critCount++ ;
			inspMap.put("crit" + Integer.toString(critCount),inspLine) ;
			inspectionCodeList.add(inspLine) ;
		}

		incomingRequest.put("inspectionCodeList", inspectionCodeList) ;
		result = inspMap ;
		this.setStatus(dbs.getStatus()) ;

		return result;
	}

}
