package com.tsa.puridiom.po.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.inspectionheader.tasks.InspectionHeaderRetrieveByIcMsrLine;
import com.tsa.puridiom.inspectionline.tasks.InspectionLineRetrieveByIcInspNo;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
/**
 *
 * @author mdanz
 *
 */
@SuppressWarnings("unchecked")
public class ValidInspectionCriteria extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			List poLines = (List)incomingRequest.get("poLineList");

			this.validInspectionCriteriaForLineItem(poHeader, poLines, incomingRequest);
			this.validProcurementLevel3Item(poHeader, poLines, incomingRequest);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "Problem occured validating po line");
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public void validInspectionCriteriaForLineItem(PoHeader poHeader, List poLines, Map incomingRequest) throws Exception
	{
		boolean validInspectionCriteria = true;
		boolean validRequestCat = this.validRequestCat(poHeader);

		for (int i = 0; i < poLines.size(); i++)
		{
			PoLine poLine = (PoLine)poLines.get(i);
			String receiptOption = poLine.getReceiptRequired();

			if (receiptOption.equalsIgnoreCase("R") && validRequestCat && (poLine.getUdf2Code().equalsIgnoreCase("LEVEL_1") || poLine.getUdf2Code().equalsIgnoreCase("LEVEL_2")))
			{
				InspectionHeaderRetrieveByIcMsrLine inspectionHeaderRetrieve = new InspectionHeaderRetrieveByIcMsrLine();

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("InspectionHeader_icMsrLine", poLine.getIcLineHistory().toString());

				Object inspectionHeaderObject = inspectionHeaderRetrieve.executeTask(newIncomingRequest);
				List inspectionHeaderList = new ArrayList();
				if(inspectionHeaderObject instanceof InspectionHeader){
					inspectionHeaderList.add(inspectionHeaderObject);
				} else if(inspectionHeaderObject instanceof List){
					inspectionHeaderList = (List) inspectionHeaderObject;
				} else {
					validInspectionCriteria = false;
				}

				for (int j = 0; j < inspectionHeaderList.size(); j++) {
					InspectionHeader inspectionHeader = (InspectionHeader) inspectionHeaderList.get(j);

					InspectionLineRetrieveByIcInspNo inspectionLineRetrieve = new InspectionLineRetrieveByIcInspNo();
					newIncomingRequest.put("InspectionLine_icInspNo", inspectionHeader.getComp_id().getIcInspNo());

					List inspectionLineList = (List)inspectionLineRetrieve.executeTask(newIncomingRequest);

					for (int k = 0; k < inspectionLineList.size(); k++) {
						InspectionLine inspectionLine = (InspectionLine)inspectionLineList.get(k);
						if(HiltonUtility.isEmpty(inspectionLine.getCritNo())){
							validInspectionCriteria = false;
							break;
						}
					}

					if(inspectionLineList.size() == 0){
						validInspectionCriteria = false;
					}

					if(!validInspectionCriteria){
						break;
					}
				}

				if(!validInspectionCriteria){
					break;
				}

			}

		}

		incomingRequest.put("validInspectionCriteria", String.valueOf(validInspectionCriteria));
	}

	public void validProcurementLevel3Item(PoHeader poHeader, List poLines, Map incomingRequest) throws Exception
	{
		boolean validProcurementLevel3Item = true;

		for (int i = 0; i < poLines.size(); i++)
		{
			PoLine poLine = (PoLine)poLines.get(i);
			if (poLine.getUdf2Code().equalsIgnoreCase("LEVEL_3"))
			{
				InspectionHeaderRetrieveByIcMsrLine inspectionHeaderRetrieve = new InspectionHeaderRetrieveByIcMsrLine();

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("InspectionHeader_icMsrLine", poLine.getIcLineHistory().toString());

				Object inspectionHeader = inspectionHeaderRetrieve.executeTask(newIncomingRequest);
				if (inspectionHeader != null) {
					validProcurementLevel3Item = false;
					break;
				}
			}
		}

		incomingRequest.put("validProcurementLevel3Item", String.valueOf(validProcurementLevel3Item));

	}

	public boolean validRequestCat(PoHeader poHeader)
	{
		boolean validRequestCat = false;
		if (poHeader.getRequestCat().equalsIgnoreCase("M"))
		{
			validRequestCat = true;
		}

		return validRequestCat;
	}
}
