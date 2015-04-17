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
import com.tsa.puridiom.entity.InspectionStd;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.inspectionheader.tasks.InspectionHeaderRetrieveByIcMsrLine;
import com.tsa.puridiom.inspectionline.tasks.InspectionLineRetrieveByIcInspNo;
import com.tsa.puridiom.inspectionstd.tasks.InspectionStdRetrieveByCode;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class RequisitionValidInspectonStdCode extends Task
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
			this.validInspectionStdForRequisitionLine(header, lineItems, incomingRequest);
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionValidAccounts error " + e.getMessage());

			throw e;
		}

		return result;
	}

	public void validInspectionStdForRequisitionLine(RequisitionHeader header, List lineItems, Map incomingRequest)throws Exception {
		boolean validInspectionStdCode = true;
		for (int i = 0; i < lineItems.size(); i++) {
			RequisitionLine requisitionLine = (RequisitionLine) lineItems.get(i);
			InspectionHeaderRetrieveByIcMsrLine inspectionHeaderRetrieve = new InspectionHeaderRetrieveByIcMsrLine();
			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("dbsession",incomingRequest.get("dbsession"));
			newIncomingRequest.put("InspectionHeader_icMsrLine",HiltonUtility.ckNull(requisitionLine.getIcLineHistory()).toString());

			Object inspectionHeaderObject = inspectionHeaderRetrieve.executeTask(newIncomingRequest);
			List inspectionHeaderList = new ArrayList();
			if (inspectionHeaderObject instanceof InspectionHeader) {
				inspectionHeaderList.add(inspectionHeaderObject);
			} else if (inspectionHeaderObject instanceof List) {
				inspectionHeaderList = (List) inspectionHeaderObject;
			} else {
				break;
			}

			for (int j = 0; j < inspectionHeaderList.size(); j++) {
				InspectionHeader inspectionHeader = (InspectionHeader) inspectionHeaderList.get(j);
				InspectionStdRetrieveByCode inspectionStdRetrieve = new InspectionStdRetrieveByCode();
				newIncomingRequest.put("InspectionStd_standardCode",HiltonUtility.ckNull(inspectionHeader.getStandardCode()));
				newIncomingRequest.put("InspectionStd_inspectType",HiltonUtility.ckNull(inspectionHeader.getInspectType()));
				List inspectionStdList = (List) inspectionStdRetrieve.executeTask(newIncomingRequest);
				for (int k = 0; k < inspectionStdList.size(); k++) {
					InspectionStd inspectionStd = (InspectionStd) inspectionStdList.get(k);
					if (!HiltonUtility.ckNull(inspectionStd.getStatus()).equalsIgnoreCase("02")) {
						validInspectionStdCode = false;
						break;
					}
				}
				if (!validInspectionStdCode) {
					break;
				}
			}

			if (!validInspectionStdCode) {
				break;
			}
		}
		incomingRequest.put("validInspectionStdCode",String.valueOf(validInspectionStdCode));
	}

}
