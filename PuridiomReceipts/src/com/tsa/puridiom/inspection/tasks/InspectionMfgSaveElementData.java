package com.tsa.puridiom.inspection.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ElementData;
import com.tsa.puridiom.entity.ElementDataPK;
import com.tsa.puridiom.entity.SrrWorkorderView;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionMfgSaveElementData extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			Object mfgNameObj = incomingRequest.get("mfgName");
			Object mfgNoObj =  incomingRequest.get("mfgNo");
			Object mfgHeatLotObj = incomingRequest.get("mfgHeatLot");

			if (mfgNameObj != null) {
				String mfgNameArray[] = this.getStringArray(mfgNameObj) ;
				String mfgNoArray[] = this.getStringArray(mfgNoObj) ;
				String mfgHeatLotArray[] = this.getStringArray(mfgHeatLotObj) ;

				List elementDataList = new ArrayList() ;

				String icHeader = (String) incomingRequest.get("InspectionHeader_icInspNo") ;
				String icLine = (String) incomingRequest.get("InspectionHeader_icMsrLine") ;
				BigDecimal icElementHeader = new BigDecimal(icHeader) ;
				BigDecimal icElementLine = new BigDecimal(icLine) ;

				incomingRequest.put("ElementData_formId", "MFG") ;
				incomingRequest.put("ElementData_icHeader", icHeader) ;
				incomingRequest.put("ElementData_icLine", icLine) ;

				int seq = 0;

				for (int i = 0; i < mfgNameArray.length; i++)
				{
					if (HiltonUtility.isEmpty(mfgNameArray[i]) && HiltonUtility.isEmpty(mfgNoArray[i]) && HiltonUtility.isEmpty(mfgHeatLotArray[i])) continue ;
					BigDecimal icSequence = new BigDecimal(++seq) ;
					elementDataList.add(elementAdd("MFG", icElementHeader, icElementLine, icSequence, "mfgName", HiltonUtility.ckNull(mfgNameArray[i]))) ;
					elementDataList.add(elementAdd("MFG", icElementHeader, icElementLine, icSequence, "mfgNo", HiltonUtility.ckNull(mfgNoArray[i]))) ;
					elementDataList.add(elementAdd("MFG", icElementHeader, icElementLine, icSequence, "mfgHeatLot", HiltonUtility.ckNull(mfgHeatLotArray[i]))) ;
				}
				incomingRequest.put("elementDataSaveList", elementDataList) ;

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("element-data-save-list.xml");
				process.executeProcess(incomingRequest);

			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	private ElementData elementAdd(String formId, BigDecimal icHeader, BigDecimal icLine, BigDecimal icSequence, String elementId, String value) {

		ElementData ed = new ElementData() ;
		ElementDataPK compId = new ElementDataPK() ;
		compId.setFormId(formId) ;
		compId.setIcHeader(icHeader) ;
		compId.setIcLine(icLine) ;
		compId.setIcSequence(icSequence) ;
		compId.setElementId(elementId) ;

		ed.setElementValue(value) ;
		ed.setComp_id(compId) ;

		return ed ;
	}

	private String[]  getStringArray(Object obj)
	{

		if (obj instanceof String[]) {
			String array[] = (String[]) obj;
			return array ;
		} else {
			String array[] = { (String) obj } ;
			return  array ;
		}

	}

}