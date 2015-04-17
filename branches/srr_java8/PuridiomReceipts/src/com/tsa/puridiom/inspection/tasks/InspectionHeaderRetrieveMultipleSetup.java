package com.tsa.puridiom.inspection.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InspectionHeaderRetrieveMultipleSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;
		List lineInspections = new ArrayList();
		List inspections = new ArrayList();

		List lineList = (List) incomingRequest.get("requisitionLineList") ;

		if (lineList != null && lineList.size() > 0)
		{
			for (int i = 0; i < lineList.size(); i++)
			{
				RequisitionLine reqLine = (RequisitionLine) lineList.get(i);

				List inspList = reqLine.getInspectionList();

				if (inspList != null && !inspList.isEmpty())
				{
					if (inspList.size() > 1)
					{
						for (int ix = 0; ix < inspList.size(); ix++)
						{
							InspectionHeader insp = (InspectionHeader) inspList.get(ix);

							incomingRequest.put("RequisitionLine_icReqLine", reqLine.getIcReqLine().toString());
							incomingRequest.put("InspectionHeader_icInspNo", insp.getComp_id().getIcInspNo().toString());
							incomingRequest.put("InspectionHeader_icMsrLine", insp.getComp_id().getIcMsrLine().toString());

							DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
					        this.setStatus(Status.SUCCEEDED) ;

							PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
							PuridiomProcess process = processLoader.loadProcess("inspection-detail-retrieve.xml");

							process.executeProcess(incomingRequest);

							inspections.add(incomingRequest.get("inspectionLineList"));

							this.setStatus(process.getStatus()) ;
							if (process.getStatus() == Status.FAILED) {
								break ;
							}
						}
						lineInspections.add(inspections);
						inspections = new ArrayList();
					}
					else
					{
						InspectionHeader insp = (InspectionHeader) inspList.get(0);

						incomingRequest.put("RequisitionLine_icReqLine", reqLine.getIcReqLine().toString());
						incomingRequest.put("InspectionHeader_icInspNo", insp.getComp_id().getIcInspNo().toString());
						incomingRequest.put("InspectionHeader_icMsrLine", insp.getComp_id().getIcMsrLine().toString());

						DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
				        this.setStatus(Status.SUCCEEDED) ;

						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("inspection-detail-retrieve.xml");

						process.executeProcess(incomingRequest);

						lineInspections.add(incomingRequest.get("inspectionLineList"));

						this.setStatus(process.getStatus()) ;
						if (process.getStatus() == Status.FAILED) {
							break ;
						}
					}
				}
				else {
					lineInspections.add(null);
				}
			}
		}
		result = lineInspections;
		return result ;
	}
}