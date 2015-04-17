/**
 *
 * Created on Jan 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCreateSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;


public class PoLineCreateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		String icPoLine = ukg.getUniqueKey().toString();
		incomingRequest.put("PoLine_icPoLine", icPoLine);
		incomingRequest.put("PoLine_icLineKey", icPoLine);
		incomingRequest.put("PoLine_icRelKey", icPoLine);
		incomingRequest.put("PoLine_icLineHistory", icPoLine);
		incomingRequest.put("PoLine_icAccount","0") ;


		PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
        if (poHeader.getStatus().equals(DocumentStatus.CT_INPROGRESS)) {
            incomingRequest.put("PoLine_status", DocumentStatus.CT_INPROGRESS);
        } else {
            incomingRequest.put("PoLine_status", DocumentStatus.PO_INPROGRESS);
        }
		incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
		incomingRequest.put("PoLine_taxCode", poHeader.getTaxCode());
		incomingRequest.put("PoLine_taxPercent", poHeader.getTaxPercent().toString());
		incomingRequest.put("PoLine_discountPercent", poHeader.getDiscountPercent().toString());
		incomingRequest.put("PoLine_poNumber", poHeader.getPoNumber());
		if (HiltonUtility.isEmpty((String) incomingRequest.get("PoLine_receiptRequired")))
		{
			incomingRequest.put("PoLine_receiptRequired", poHeader.getReceiptRequired());
		}
		incomingRequest.put("PoLine_releaseNumber", poHeader.getReleaseNumber().toString());
		incomingRequest.put("PoLine_requisitionerCode", poHeader.getRequisitionerCode());
		incomingRequest.put("PoLine_departmentCode", poHeader.getDepartmentCode());
		incomingRequest.put("PoLine_taxCode", poHeader.getTaxCode());
		incomingRequest.put("PoLine_taxPercent", poHeader.getTaxPercent().toString());
		if (HiltonUtility.isEmpty((String) incomingRequest.get("PoLine_inspectionReqd")))
		{
			incomingRequest.put("PoLine_inspectionReqd", poHeader.getInspectionReqd());
		}
		if (HiltonUtility.isEmpty((String) incomingRequest.get("PoLine_commodity"))) {
			if(!HiltonUtility.isEmpty(poHeader.getVendorId()))
			{
				String vendorUdf1 = HiltonUtility.ckNull(((Vendor)VendorManager.getInstance().getVendor(organizationId, poHeader.getVendorId())).getVendUdf1());
				incomingRequest.put("PoLine_commodity", vendorUdf1);
				incomingRequest.put("Commodity_commodity", vendorUdf1);
			}
		}
		this.setStatus(Status.SUCCEEDED);
		return null;
	}

}
