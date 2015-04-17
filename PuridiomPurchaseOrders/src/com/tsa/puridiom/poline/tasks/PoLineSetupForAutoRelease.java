/**
 *
 * Created on Jan 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class PoLineSetupForAutoRelease extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String organizationId = (String)incomingRequest.get("organizationId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
            String noApprovalNeedAutoAward = HiltonUtility.ckNull((String) incomingRequest.get("NoApprovalNeedAutoAward"));
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
            RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
            String poType = (String)incomingRequest.get("PoHeader_poType");
            String statusAwardChangeReq = PropertiesManager.getInstance(organizationId).getProperty("REQ DEFAULTS", "AWARDCHANGEREQ", "N");
            String autoAwardReleaseRequest = propertiesManager.getProperty("AUTOAWARD", "AUTOAWARDRELEASEREQUEST", "N");
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			String newStatus = HiltonUtility.ckNull((String) incomingRequest.get("newStatus"));
			String poStatus = "";
			if(reqHeader == null)
			{
				reqHeader = new RequisitionHeader();
			}
			else
			{
				 if(statusAwardChangeReq.equalsIgnoreCase("Y"))
		    		{
		    			if (reqHeader.getRequisitionType().equalsIgnoreCase("C") && newStatus.equalsIgnoreCase("1035"))
		    			{
		    				poStatus = DocumentStatus.PO_AWARDED;
		    			}
		            }
			}
            if(Utility.isEmpty(poType))
            {
                poType = "PO";
            }

            incomingRequest.put("PoLine_icPoHeader", "0");
            incomingRequest.put("PoLine_icPoLine", "0");
            incomingRequest.put("PoLine_lineNumber", "0");
            incomingRequest.put("PoLine_poNumber", "");
            incomingRequest.put("PoLine_releaseNumber", "0");
            incomingRequest.put("PoLine_itemNumber", "");
            incomingRequest.put("PoLine_itemSource", "");
            incomingRequest.put("PoLine_umCode", "");
            //incomingRequest.put("PoLine_quantity", "0");
            incomingRequest.put("PoLine_unitPrice", "0");
            incomingRequest.put("PoLine_commodity", "");
            incomingRequest.put("PoLine_taxable", "Y");
            incomingRequest.put("PoLine_taxPercent", "0");
            incomingRequest.put("PoLine_taxAmount", "0");
            incomingRequest.put("PoLine_discountSource", "");
            incomingRequest.put("PoLine_discountPercent", "0");
            incomingRequest.put("PoLine_discountAmount", "0");
            incomingRequest.put("PoLine_shippingCharges", "0");
            incomingRequest.put("PoLine_shippingTaxable", "");
            incomingRequest.put("PoLine_shippingTax", "0");
            incomingRequest.put("PoLine_otherCharges", "0");
            incomingRequest.put("PoLine_otherDescription", "");
            incomingRequest.put("PoLine_otherTaxable", "");
            incomingRequest.put("PoLine_otherTax", "0");
            incomingRequest.put("PoLine_icReqLine", "0");
            incomingRequest.put("PoLine_asset", "N");
            incomingRequest.put("PoLine_splits", "N");
            incomingRequest.put("PoLine_commentFlag", "N");
            if(noApprovalNeedAutoAward.equalsIgnoreCase("Y"))
	        {
				if (reqLine != null && !reqLine.getStatus().equals(DocumentStatus.CANCELLED)) 
				{
					poStatus = DocumentStatus.PO_AWARDED;
				}  
				else 
				{
					poStatus = DocumentStatus.CANCELLED;
				}
	        }
            else if(autoAwardReleaseRequest.equalsIgnoreCase("Y") && reqHeader.getRequisitionType().equalsIgnoreCase("E"))
            {
            	poStatus = DocumentStatus.PO_AWARDED;
            }
			else
			{
				if (reqLine != null && !reqLine.getStatus().equals(DocumentStatus.CANCELLED)) 
				{
					if(HiltonUtility.isEmpty(poStatus))
		            {
			            if (poType.equals(OrderType.CONTRACT) && propertiesManager.getProperty("PO OPTIONS", "CONTRACTSAVEASPO", "N").equals("Y")) {
			            	poStatus = DocumentStatus.CT_INPROGRESS;
			            } else {
			            	poStatus = DocumentStatus.PO_INPROGRESS;
			            }
		            }
				}  
				else 
				{
					poStatus = DocumentStatus.CANCELLED;
				}
			}
            incomingRequest.put("PoLine_status", poStatus);
            incomingRequest.put("PoLine_icRfqLine", "0");
            incomingRequest.put("PoLine_umFactor", "0");
            incomingRequest.put("PoLine_catalogId", "");
            //incomingRequest.put("PoLine_lineTotal", "0");
            incomingRequest.put("PoLine_taxOvr", "");
            incomingRequest.put("PoLine_discOvr", "");
            incomingRequest.put("PoLine_shipOvr", "");
            incomingRequest.put("PoLine_otherOvr", "");
            incomingRequest.put("PoLine_shiptoFlag", "N");
            incomingRequest.put("PoLine_receiptRequired", "");
            incomingRequest.put("PoLine_icLineKey", "0");
            incomingRequest.put("PoLine_modelNumber", "");
            incomingRequest.put("PoLine_vendorId", "");
            incomingRequest.put("PoLine_vendorName", "");
            incomingRequest.put("PoLine_vendContactCode", "");
            incomingRequest.put("PoLine_udf1Code", "");
            incomingRequest.put("PoLine_udf2Code", "");
            incomingRequest.put("PoLine_udf3Code", "");
            incomingRequest.put("PoLine_udf4Code", "");
            incomingRequest.put("PoLine_udf5Code", "");
            incomingRequest.put("PoLine_udf6Code", "");
            incomingRequest.put("PoLine_udf7Code", "");
            incomingRequest.put("PoLine_udf8Code", "");
            incomingRequest.put("PoLine_udf9Code", "");
            incomingRequest.put("PoLine_udf10Code", "");
            incomingRequest.put("PoLine_memoLine", "");

            incomingRequest.put("PoLine_mfgName", "");

            incomingRequest.put("PoLine_lineRevNo", "");
            incomingRequest.put("PoLine_actionInd", "");
            incomingRequest.put("PoLine_icRelKey", "0");
            incomingRequest.put("PoLine_chgPromCnt", "0");
            //incomingRequest.put("PoLine_chgPromDate", Dates.today(""));
            //incomingRequest.put("PoLine_poPromised", Dates.today(""));
            incomingRequest.put("PoLine_roFlag", "");
            incomingRequest.put("PoLine_icLineHistory", "0");
            incomingRequest.put("PoLine_itemLocation", "");
            incomingRequest.put("PoLine_description", "");
            incomingRequest.put("PoLine_routing", "");
            incomingRequest.put("PoLine_qtyReceived", "0");
            incomingRequest.put("PoLine_requisitionerCode", "");
            incomingRequest.put("PoLine_icAccount","0") ;
            incomingRequest.put("PoLine_requisitionNumber","") ;
            incomingRequest.put("PoLine_departmentCode","") ;
            incomingRequest.put("PoLine_dateEntered", Dates.today("", userTimeZone));
            incomingRequest.put("PoLine_lastChgDate", Dates.today("", userTimeZone));

            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw new TsaException(this.getName(), e);
        }
        return result;
    }
}