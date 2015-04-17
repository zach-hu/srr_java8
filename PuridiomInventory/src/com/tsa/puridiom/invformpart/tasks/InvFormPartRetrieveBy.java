package com.tsa.puridiom.invformpart.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
public class InvFormPartRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvFormPart as invformpart where 1=1 ");
		if(incomingRequest.containsKey("InvFormPart_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvFormPart_itemNumber");
			queryString.append(" AND invformpart.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_formPart"))
		{			
			String formPart = (String) incomingRequest.get("InvFormPart_formPart");
			queryString.append(" AND invformpart.id.formPart = '" + formPart + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_measureBy"))
		{			
			String measureBy = (String) incomingRequest.get("InvFormPart_measureBy");
			queryString.append(" AND invformpart.measureBy = '" + measureBy + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_overallWidth"))
		{			
			String overallWidth = (String) incomingRequest.get("InvFormPart_overallWidth");
			queryString.append(" AND invformpart.overallWidth = '" + overallWidth + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_overallLen"))
		{			
			String overallLen = (String) incomingRequest.get("InvFormPart_overallLen");
			queryString.append(" AND invformpart.overallLen = '" + overallLen + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_detachWidth"))
		{			
			String detachWidth = (String) incomingRequest.get("InvFormPart_detachWidth");
			queryString.append(" AND invformpart.detachWidth = '" + detachWidth + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_detachLen"))
		{			
			String detachLen = (String) incomingRequest.get("InvFormPart_detachLen");
			queryString.append(" AND invformpart.detachLen = '" + detachLen + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_sizeUdf"))
		{			
			String sizeUdf = (String) incomingRequest.get("InvFormPart_sizeUdf");
			queryString.append(" AND invformpart.sizeUdf = '" + sizeUdf + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_printSides"))
		{			
			String printSides = (String) incomingRequest.get("InvFormPart_printSides");
			queryString.append(" AND invformpart.printSides = '" + printSides + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_printCopies"))
		{			
			String printCopies = (String) incomingRequest.get("InvFormPart_printCopies");
			queryString.append(" AND invformpart.printCopies = '" + printCopies + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_marginWords"))
		{			
			String marginWords = (String) incomingRequest.get("InvFormPart_marginWords");
			queryString.append(" AND invformpart.marginWords = '" + marginWords + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_addchgdel"))
		{			
			String addchgdel = (String) incomingRequest.get("InvFormPart_addchgdel");
			queryString.append(" AND invformpart.addchgdel = '" + addchgdel + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_blockout"))
		{			
			String blockout = (String) incomingRequest.get("InvFormPart_blockout");
			queryString.append(" AND invformpart.blockout = '" + blockout + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_copiesUdf"))
		{			
			String copiesUdf = (String) incomingRequest.get("InvFormPart_copiesUdf");
			queryString.append(" AND invformpart.copiesUdf = '" + copiesUdf + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_paperColor"))
		{			
			String paperColor = (String) incomingRequest.get("InvFormPart_paperColor");
			queryString.append(" AND invformpart.paperColor = '" + paperColor + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_paperWeight"))
		{			
			String paperWeight = (String) incomingRequest.get("InvFormPart_paperWeight");
			queryString.append(" AND invformpart.paperWeight = '" + paperWeight + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_paperGrade"))
		{			
			String paperGrade = (String) incomingRequest.get("InvFormPart_paperGrade");
			queryString.append(" AND invformpart.paperGrade = '" + paperGrade + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_inkFront"))
		{			
			String inkFront = (String) incomingRequest.get("InvFormPart_inkFront");
			queryString.append(" AND invformpart.inkFront = '" + inkFront + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_inkBack"))
		{			
			String inkBack = (String) incomingRequest.get("InvFormPart_inkBack");
			queryString.append(" AND invformpart.inkBack = '" + inkBack + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_paperUdf"))
		{			
			String paperUdf = (String) incomingRequest.get("InvFormPart_paperUdf");
			queryString.append(" AND invformpart.paperUdf = '" + paperUdf + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_inkUdf"))
		{			
			String inkUdf = (String) incomingRequest.get("InvFormPart_inkUdf");
			queryString.append(" AND invformpart.inkUdf = '" + inkUdf + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_carbonWeight"))
		{			
			String carbonWeight = (String) incomingRequest.get("InvFormPart_carbonWeight");
			queryString.append(" AND invformpart.carbonWeight = '" + carbonWeight + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_carbonGrade"))
		{			
			String carbonGrade = (String) incomingRequest.get("InvFormPart_carbonGrade");
			queryString.append(" AND invformpart.carbonGrade = '" + carbonGrade + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_carbonColor"))
		{			
			String carbonColor = (String) incomingRequest.get("InvFormPart_carbonColor");
			queryString.append(" AND invformpart.carbonColor = '" + carbonColor + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_carbonSize"))
		{			
			String carbonSize = (String) incomingRequest.get("InvFormPart_carbonSize");
			queryString.append(" AND invformpart.carbonSize = '" + carbonSize + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_carbonSpot"))
		{			
			String carbonSpot = (String) incomingRequest.get("InvFormPart_carbonSpot");
			queryString.append(" AND invformpart.carbonSpot = '" + carbonSpot + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_carbonStrip"))
		{			
			String carbonStrip = (String) incomingRequest.get("InvFormPart_carbonStrip");
			queryString.append(" AND invformpart.carbonStrip = '" + carbonStrip + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_carbonUdf"))
		{			
			String carbonUdf = (String) incomingRequest.get("InvFormPart_carbonUdf");
			queryString.append(" AND invformpart.carbonUdf = '" + carbonUdf + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_punchHoles"))
		{			
			String punchHoles = (String) incomingRequest.get("InvFormPart_punchHoles");
			queryString.append(" AND invformpart.punchHoles = '" + punchHoles + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_punchSize"))
		{			
			String punchSize = (String) incomingRequest.get("InvFormPart_punchSize");
			queryString.append(" AND invformpart.punchSize = '" + punchSize + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_punchCenter"))
		{			
			String punchCenter = (String) incomingRequest.get("InvFormPart_punchCenter");
			queryString.append(" AND invformpart.punchCenter = '" + punchCenter + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_punchPos"))
		{			
			String punchPos = (String) incomingRequest.get("InvFormPart_punchPos");
			queryString.append(" AND invformpart.punchPos = '" + punchPos + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_mperfLeft"))
		{			
			String mperfLeft = (String) incomingRequest.get("InvFormPart_mperfLeft");
			queryString.append(" AND invformpart.mperfLeft = '" + mperfLeft + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_mperfRight"))
		{			
			String mperfRight = (String) incomingRequest.get("InvFormPart_mperfRight");
			queryString.append(" AND invformpart.mperfRight = '" + mperfRight + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_operfLeft"))
		{			
			String operfLeft = (String) incomingRequest.get("InvFormPart_operfLeft");
			queryString.append(" AND invformpart.operfLeft = '" + operfLeft + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_operfRight"))
		{			
			String operfRight = (String) incomingRequest.get("InvFormPart_operfRight");
			queryString.append(" AND invformpart.operfRight = '" + operfRight + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_perfType"))
		{			
			String perfType = (String) incomingRequest.get("InvFormPart_perfType");
			queryString.append(" AND invformpart.perfType = '" + perfType + "'");
		}
		if(incomingRequest.containsKey("InvFormPart_turnType"))
		{			
			String turnType = (String) incomingRequest.get("InvFormPart_turnType");
			queryString.append(" AND invformpart.turnType = '" + turnType + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}