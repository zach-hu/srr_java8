package com.tsa.puridiom.requisition.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.inspectionheader.tasks.InspectionHeaderRetrieveByIcMsrLine;
import com.tsa.puridiom.inspectionline.tasks.InspectionLineRetrieveByIcInspNo;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class RequisitionValidInspectonCriteria extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");

			this.validInspectionCreateriaForRequisitionLine(header, lineItems, incomingRequest);
			this.validateProcurementLevel3Items(header, lineItems, incomingRequest);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionValidAccounts error " + e.getMessage());

			throw e;
		}

		return result;
	}

	public void validInspectionCreateriaForRequisitionLine(RequisitionHeader header, List lineItems, Map incomingRequest) throws Exception
	{

		boolean validInspectionCriteria = true;
		boolean requestCat = false;
		requestCat = requestCatValid(header);

		if (!("T").equalsIgnoreCase(header.getRequisitionType()) && !("K").equalsIgnoreCase(header.getRequisitionType()))
		{
			for (int i = 0; i < lineItems.size(); i++)
			{
				RequisitionLine requisitionLine = (RequisitionLine) lineItems.get(i);
				if (requestCat && (requisitionLine.getUdf2Code().equals("LEVEL_1") || requisitionLine.getUdf2Code().equals("LEVEL_2")) && !"INV".equalsIgnoreCase(requisitionLine.getItemSource()))
				{
					InspectionHeaderRetrieveByIcMsrLine inspectionHeaderRetrieve = new InspectionHeaderRetrieveByIcMsrLine();

					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
					newIncomingRequest.put("InspectionHeader_icMsrLine", requisitionLine.getIcLineHistory().toString());

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

		}


		incomingRequest.put("validInspectionCriteria", String.valueOf(validInspectionCriteria));
	}

	public void validateProcurementLevel3Items(RequisitionHeader requisitionHeader, List reqLines, Map incomingRequest) throws Exception
	{
		boolean validProcurementLevel3Item = true;

		for (int i = 0; i < reqLines.size(); i++)
		{
			RequisitionLine reqLine = (RequisitionLine)reqLines.get(i);
			if (reqLine.getUdf2Code().equalsIgnoreCase("LEVEL_3"))
			{
				InspectionHeaderRetrieveByIcMsrLine inspectionHeaderRetrieve = new InspectionHeaderRetrieveByIcMsrLine();

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("InspectionHeader_icMsrLine", reqLine.getIcLineHistory().toString());

				Object inspectionHeader = inspectionHeaderRetrieve.executeTask(newIncomingRequest);
				if (inspectionHeader != null) {
					validProcurementLevel3Item = false;
					break;
				}
			}
		}

		incomingRequest.put("validProcurementLevel3Item", String.valueOf(validProcurementLevel3Item));

	}

	public boolean requestCatValid(RequisitionHeader header)
	{
		String requestCat = "";
		boolean validate = false;
		requestCat = (HiltonUtility.ckNull(header.getRequestCat()));

		if (!requestCat.equalsIgnoreCase("M"))
		{
			return validate;
		}
		else
		{
			validate = true;
			return validate;
		}
	}
}
