package com.tsa.puridiom.inspectionstd.tasks;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InspectionCrit;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.InspectionStd;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.StdTable;
import com.tsa.puridiom.inspectioncrit.tasks.InspectionCritRetrieveById;
import com.tsa.puridiom.stdtable.tasks.StdTableRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class InspectionStdListSetDescriptions extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;
		this.setStatus(Status.SUCCEEDED) ;

        String	organizationId = (String)incomingRequest.get("organizationId");
		List inspectionStdList = (List) incomingRequest.get("inspectionStdList") ;
		if (inspectionStdList == null) inspectionStdList = new ArrayList() ;

		for (int i = 0; i < inspectionStdList.size(); i++) {
			InspectionStd inspLine = (InspectionStd) inspectionStdList.get(i) ;
			String inspCode = inspLine.getInspectCode() ;
			String inspCrit = inspLine.getCritNo();

			inspLine.setCritDesc("") ;


			if (! HiltonUtility.isEmpty(inspCode)) {
				incomingRequest.put("StdTable_tableType", "INSP") ;
				incomingRequest.put("StdTable_tableKey", inspCode) ;
				StdTableRetrieveById s = new StdTableRetrieveById() ;
				StdTable st = (StdTable) s.executeTask(incomingRequest) ;

				if (! HiltonUtility.isEmpty(inspCode)) {
					incomingRequest.put("InspectionCrit_inspectCode", inspCode) ;
					incomingRequest.put("InspectionCrit_critNo", inspCrit) ;

					InspectionCritRetrieveById cTask = new InspectionCritRetrieveById() ;
					InspectionCrit ct = (InspectionCrit) cTask.executeTask(incomingRequest) ;
					if (ct != null) {
						inspLine.setCritDesc(ct.getDescription()) ;
					} else {
						inspLine.setCritDesc("") ;
					}
				}
			}

			inspectionStdList.set(i, inspLine) ;
		}

		return inspectionStdList  ;
	}
}
