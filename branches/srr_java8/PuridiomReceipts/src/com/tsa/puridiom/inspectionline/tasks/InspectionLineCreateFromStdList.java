package com.tsa.puridiom.inspectionline.tasks;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.inspectioncrit.tasks.InspectionCritRetrieveById;
import com.tsa.puridiom.stdtable.tasks.StdTableRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InspectionLineCreateFromStdList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String oid = (String) incomingRequest.get("organizationId") ;
		String uid = (String) incomingRequest.get("userId") ;
		List inspectionStdList = (List) incomingRequest.get("inspectionStdList") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String today = Dates.today("", userTimeZone);

		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("inspectionline-add.xml");

		int seqNo = 0;
		String prevCode = "" ;

		InspectionHeader inspectionHeader = (InspectionHeader) incomingRequest.get("inspectionHeader") ;
		if (inspectionHeader != null) {
			for (int i = 0; i < inspectionStdList.size(); i++) {
				InspectionStd inspectionStd = (InspectionStd) inspectionStdList.get(i);
				Map newRequest = new HashMap() ;

				if (! prevCode.equalsIgnoreCase(inspectionStd.getInspectCode())) {
					prevCode = inspectionStd.getInspectCode() ;
					seqNo = 0;
				}

				seqNo++ ;

    			String inspCode = inspectionStd.getInspectCode() ;
    			String inspCrit = inspectionStd.getCritNo() ;
    			String critDesc = "" ;

    			if (! HiltonUtility.isEmpty(inspCode)) {
					incomingRequest.put("InspectionCrit_inspectCode", inspCode) ;
					incomingRequest.put("InspectionCrit_critNo", inspCrit) ;

					InspectionCritRetrieveById cTask = new InspectionCritRetrieveById() ;
					InspectionCrit ct = (InspectionCrit) cTask.executeTask(incomingRequest) ;
					if (ct != null) {
						critDesc = ct.getDescription() ;
					}
    			}



    			newRequest.put("dbsession", dbs) ;
				newRequest.put("organizationId", oid) ;
				newRequest.put("userId", uid) ;


				newRequest.put("InspectionLine_icInspLine", "") ;
				newRequest.put("InspectionLine_standardCode", inspectionHeader.getStandardCode()) ;
				newRequest.put("InspectionLine_icInspNo", inspectionHeader.getComp_id().getIcInspNo().toString()) ;
				newRequest.put("InspectionLine_icMsrLine", inspectionHeader.getComp_id().getIcMsrLine().toString()) ;
				newRequest.put("InspectionLine_inspectType", inspectionHeader.getInspectType()) ;
				newRequest.put("InspectionLine_inspectCode", inspCode) ;
				newRequest.put("InspectionLine_critNo", inspCrit) ;
				newRequest.put("InspectionLine_standardCode", inspectionStd.getStandardCode()) ;
				newRequest.put("InspectionLine_seqNo", Integer.toString(seqNo)) ;
				newRequest.put("InspectionLine_lockFlag","N") ;
				newRequest.put("InspectionLine_owner",uid) ;
				newRequest.put("InspectionLine_status", DocumentStatus.INSP_PENDING) ;
                newRequest.put("InspectionLine_dateEntered", today);
				newRequest.put("InspectionLine_lastChangeBy",uid) ;
                newRequest.put("InspectionLine_lastchange", today);
                newRequest.put("InspectionLine_critDescription", inspectionStd.getCritText());



                process.executeProcess(newRequest) ;
			}
		}

		this.setStatus(dbs.getStatus()) ;
		return null ;
	}

}