package com.tsa.puridiom.inspection.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class InspectionDetailRetrieveSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;

		InspectionHeader ih = (InspectionHeader) incomingRequest.get("inspectionHeader") ;
		if (ih != null) {
			incomingRequest.put("InspectionLine_icInspNo", ih.getComp_id().getIcInspNo()) ;
			incomingRequest.put("DocComment_icHeader", ih.getComp_id().getIcInspNo().toString());
			incomingRequest.put("DocAttachment_icHeader", ih.getComp_id().getIcInspNo().toString());
			incomingRequest.put("InspectionStd_inspectType", ih.getInspectType()) ;
			incomingRequest.put("InspectionStd_standardCode", ih.getStandardCode()) ;
			incomingRequest.put("InspectionMfr_icInspNo", ih.getComp_id().getIcInspNo().toString()) ;
			incomingRequest.put("InspectionMte_icInspNo", ih.getComp_id().getIcInspNo().toString()) ;
			incomingRequest.put("InspectionDispos_icInspNo", ih.getComp_id().getIcInspNo().toString()) ;
			incomingRequest.put("InspectionDiscrep_icInspNo", ih.getComp_id().getIcInspNo().toString()) ;

		} else {
			// Not created yet
			incomingRequest.put("InspectionLine_icInspNo", new BigDecimal(-1)) ;
			incomingRequest.put("DocComment_icHeader",  new BigDecimal(-1).toString());
			incomingRequest.put("DocAttachment_icHeader",  new BigDecimal(-1).toString());
			incomingRequest.put("InspectionMfr_icInspNo", new BigDecimal(-1).toString()) ;
			incomingRequest.put("InspectionMte_icInspNo", new BigDecimal(-1).toString()) ;
			incomingRequest.put("InspectionDispos_icInspNo", new BigDecimal(-1).toString()) ;
			incomingRequest.put("InspectionDiscrep_icInspNo", new BigDecimal(-1).toString()) ;
		}

		return result ;
	}
}