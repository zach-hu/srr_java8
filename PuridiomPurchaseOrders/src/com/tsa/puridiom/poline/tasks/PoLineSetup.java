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
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.UnitOfMeasure;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.unitofmeasure.tasks.UnitOfMeasureRetrieveById;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class PoLineSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        String	organizationId = (String) incomingRequest.get("organizationId");
        String  userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

		String taxable = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","TAXABLE","Y");
		String commodityCode = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","COMMODITYCODE","");
		String uom = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","UMCODE","EA");
		String umFactor = "1";
		
		if (!Utility.isEmpty(uom)) {
			try {
				UnitOfMeasureRetrieveById uomRetrieve = new UnitOfMeasureRetrieveById();
				Map params = this.getDefaultUpdateParameters(incomingRequest);
				UnitOfMeasure unitOfMeasure = (UnitOfMeasure) uomRetrieve.executeTask(params);
				if (unitOfMeasure != null) {
					umFactor = String.valueOf(unitOfMeasure.getFactor());
				}
			} catch (Exception e1) {
				umFactor = "1";
			}
		}
		
        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
        Object result = null;

        try
        {
            incomingRequest.put("PoLine_icPoHeader", "0");
            incomingRequest.put("PoLine_icPoLine", "0");
            incomingRequest.put("PoLine_lineNumber", "0");
            incomingRequest.put("PoLine_poNumber", "");
            incomingRequest.put("PoLine_releaseNumber", "0");
            incomingRequest.put("PoLine_itemNumber", "");
            incomingRequest.put("PoLine_itemSource", "");
            incomingRequest.put("PoLine_umCode", uom);
            incomingRequest.put("PoLine_quantity", "0");
            incomingRequest.put("PoLine_unitPrice", "0");
            incomingRequest.put("PoLine_commodity", commodityCode);
            incomingRequest.put("PoLine_taxable", taxable);
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
            incomingRequest.put("PoLine_status", DocumentStatus.PO_INPROGRESS);
            incomingRequest.put("PoLine_icRfqLine", "0");
            incomingRequest.put("PoLine_umFactor", umFactor);
            incomingRequest.put("PoLine_catalogId", "");
            incomingRequest.put("PoLine_lineTotal", "0");
            incomingRequest.put("PoLine_taxOvr", "");
            incomingRequest.put("PoLine_discOvr", "");
            incomingRequest.put("PoLine_shipOvr", "");
            incomingRequest.put("PoLine_otherOvr", "");
            incomingRequest.put("PoLine_shiptoFlag", "N");
            incomingRequest.put("PoLine_receiptRequired", "");
            incomingRequest.put("PoLine_icLineKey", "0");
            incomingRequest.put("PoLine_modelNumber", "");
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
            incomingRequest.put("PoLine_duomQtyReceived", "0");
            incomingRequest.put("PoLine_requisitionerCode", "");
            incomingRequest.put("PoLine_icAccount","0") ;
            incomingRequest.put("PoLine_requisitionNumber","") ;
            incomingRequest.put("PoLine_departmentCode","") ;
            incomingRequest.put("PoLine_dateEntered", Dates.today(userDateFormat, userTimeZone));
            incomingRequest.put("PoLine_altItemNumber", "");
            incomingRequest.put("PoLine_expensed", "0");
            incomingRequest.put("PoLine_assetNumber", "");
            incomingRequest.put("PoLine_chemicalItemNumber", "");

            PoHeader  poHeader = (PoHeader) incomingRequest.get("poHeader") ;
    		if (poHeader != null) {
    			// Defaults from header
    			if ( poHeader.getRequiredDate()!=null )
    			{
    					incomingRequest.put("PoLine_requiredDate", HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), organizationId, userDateFormat));
    			}
    			incomingRequest.put("PoLine_vendorId", poHeader.getVendorId());
    			incomingRequest.put("PoLine_vendContactCode", poHeader.getVendContactCode());
    		}

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