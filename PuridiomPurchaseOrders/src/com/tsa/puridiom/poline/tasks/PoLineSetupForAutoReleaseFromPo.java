/**
 *
 * Created on Jan 26, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineFromReqSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class PoLineSetupForAutoReleaseFromPo extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
    		String organizationId = (String)incomingRequest.get("organizationId") ;
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

            if (HiltonUtility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }

            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            if(poHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Purchase Order was not found!");
            }
            /*RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            if(requisitionHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Requisition was not found!");
            }*/
            RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
            if(reqLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("RequisitionLine can not be Empty!");
            }
            UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            ret = ukg.getUniqueKey().toString();
            incomingRequest.put("PoLine_icPoLine", ret);
//            incomingRequest.put("PoLine_icRelKey", ret);
            incomingRequest.put("PoLine_icLineKey", ret);
            incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
            incomingRequest.put("PoLine_poNumber", poHeader.getPoNumber().toString());
            incomingRequest.put("PoLine_icReqLine", reqLine.getIcReqLine().toString());
            incomingRequest.put("PoLine_lineNumber", incomingRequest.get("lineNumber"));
            incomingRequest.put("PoLine_discountSource", " ");
            incomingRequest.put("PoLine_itemNumber", reqLine.getItemNumber());
            incomingRequest.put("PoLine_catalogId", reqLine.getCatalogId());
            incomingRequest.put("PoLine_itemSource", reqLine.getItemSource());
            incomingRequest.put("PoLine_requisitionerCode", reqLine.getRequisitionerCode());
            incomingRequest.put("PoLine_receiptRequired", reqLine.getReceiptRequired());
            incomingRequest.put("PoLine_umCode", reqLine.getUmCode());
            incomingRequest.put("PoLine_umFactor", reqLine.getUmFactor().toString());
            incomingRequest.put("PoLine_otherOvr", reqLine.getOtherOvr());
            incomingRequest.put("PoLine_shipOvr", reqLine.getShipOvr());
            incomingRequest.put("PoLine_discOvr", reqLine.getDiscOvr());
            incomingRequest.put("PoLine_taxOvr", reqLine.getTaxOvr());
            incomingRequest.put("PoLine_taxPercent", reqLine.getTaxPercent().toString());
            incomingRequest.put("PoLine_taxAmount", reqLine.getTaxAmount().toString());
            incomingRequest.put("PoLine_shippingTax", reqLine.getShippingTaxAmt().toString());
            incomingRequest.put("PoLine_otherTax", reqLine.getOtherTaxAmount().toString());
            incomingRequest.put("PoLine_quantity", reqLine.getQuantity().toString());
            incomingRequest.put("PoLine_unitPrice", reqLine.getUnitPrice().toString());
            incomingRequest.put("PoLine_commodity", reqLine.getCommodityCode());
            incomingRequest.put("PoLine_taxable", reqLine.getTaxable());
            incomingRequest.put("PoLine_asset", reqLine.getAsset());
            incomingRequest.put("PoLine_discountSource", reqLine.getDiscountSource());
            incomingRequest.put("PoLine_discountPercent", reqLine.getDiscountPercent().toString());
            incomingRequest.put("PoLine_discountAmount", reqLine.getDiscountAmount().toString());
            incomingRequest.put("PoLine_shippingCharges", reqLine.getShippingCharges().toString());
            incomingRequest.put("PoLine_shippingTaxable", reqLine.getTaxShipping().toString());
            incomingRequest.put("PoLine_otherCharges", reqLine.getOtherCharges().toString());
            incomingRequest.put("PoLine_otherDescription", reqLine.getOtherDescription());
            incomingRequest.put("PoLine_otherTaxable", reqLine.getTaxOther());
            incomingRequest.put("PoLine_splits", reqLine.getSplits());
            incomingRequest.put("PoLine_commentFlag", reqLine.getCommentFlag());
            incomingRequest.put("PoLine_mfgName", reqLine.getMfgName());
            incomingRequest.put("PoLine_modelNumber", reqLine.getModelNumber());
            incomingRequest.put("PoLine_vendorId", reqLine.getVendorId());
            incomingRequest.put("PoLine_vendorName", reqLine.getVendorName());
            incomingRequest.put("PoLine_vendContactCode", reqLine.getVendContactCode());
            incomingRequest.put("PoLine_udf1Code", reqLine.getUdf1Code());
            incomingRequest.put("PoLine_udf2Code", reqLine.getUdf2Code());
            incomingRequest.put("PoLine_udf3Code", reqLine.getUdf3Code());
            incomingRequest.put("PoLine_udf4Code", reqLine.getUdf4Code());
            incomingRequest.put("PoLine_udf5Code", reqLine.getUdf5Code());
			incomingRequest.put("PoLine_udf6Code", reqLine.getUdf6Code());
			incomingRequest.put("PoLine_udf7Code", reqLine.getUdf7Code());
			incomingRequest.put("PoLine_udf8Code", reqLine.getUdf8Code());
			incomingRequest.put("PoLine_udf9Code", reqLine.getUdf9Code());
			incomingRequest.put("PoLine_udf10Code", reqLine.getUdf10Code());
			incomingRequest.put("PoLine_memoLine", reqLine.getMemoLine());


            incomingRequest.put("PoLine_icLineHistory", reqLine.getIcLineHistory().toString());
            incomingRequest.put("PoLine_itemLocation", reqLine.getItemLocation());
            incomingRequest.put("PoLine_routing", reqLine.getRouting());
            incomingRequest.put("PoLine_description", reqLine.getDescription());
            incomingRequest.put("PoLine_lineRevNo", "0");
            incomingRequest.put("PoLine_releaseNumber", poHeader.getReleaseNumber().toString());
            if( poHeader.getRequiredDate() != null )
            {
            	incomingRequest.put("PoLine_poPromised", HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), organizationId, userDateFormat));
            }
            incomingRequest.put("PoLine_requisitionNumber",reqLine.getRequisitionNumber()) ;
            incomingRequest.put("PoLine_departmentCode",reqLine.getDepartmentCode()) ;
            incomingRequest.put("PoLine_dateEntered", Dates.today(userDateFormat,  poHeader.getTimeZone()));
            incomingRequest.put("PoLine_lastChgDate", Dates.today(userDateFormat,  poHeader.getTimeZone()));

            incomingRequest.put("createdfrom", "REQ");

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }

}
