package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.VendorBuyerRel;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * Class that allows Buyer Assignment using VENDOR_BUYER_REL table
 * 
 * @author Alexander Angulo
 *
 */
public class ByVendorBuyerRelTable  extends Task {
	
	/**
	 * 
	 */
    @SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception {
    	
        Object ret = null;
        
        try {
            Map incomingRequest = (Map)object;
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List lines = (List)incomingRequest.get("requisitionLineList");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);

            Log.debug(this, "Assign By VENDOR_BUYER_REL table");

            for (int i = 0; i < lines.size(); i++) {
            	
                RequisitionLine rql = (RequisitionLine)lines.get(i);
                Log.debug(this, "requisition line: " + rql.getLineNumber());

                if (!rql.getAssigned()) {
                	String lineBuyer = this.checkTypes(rql, header);
	                if (Utility.isEmpty(lineBuyer)) {
	                    lineBuyer = HiltonUtility.ckNull(byVendorBuyerRelTable(rql, incomingRequest));
	                }

	                if (("PURCHASING,UNASSIGNED").indexOf(lineBuyer) < 0  && !Utility.isEmpty(lineBuyer)) {
	                	rql.setAssignedBuyer(lineBuyer);
		                rql.setAssignedDate(d_today);
	                	rql.setAssigned(true);
	                }
                }
            }

            ret = lines;
            this.setStatus(Status.SUCCEEDED);
            
        } catch (Exception e){
            this.setStatus(Status.FAILED);
            throw new TsaException("Assign By Commodity-line Failed!");
        }

        return ret;
    }

    /**
     * 
     * @param rql
     * @param header
     * @return
     */
    private String checkTypes(RequisitionLine rql, RequisitionHeader header) {
        Log.debug(this, "By Commodity checkTypes");
        String sBuyer = "";

        if (header.getRequisitionType().equals(RequisitionType.PRICING_REQUEST) 
        		&& rql.getStatus().equals(DocumentStatus.REQ_FORWARDED)) {
            String compare = "PURCHASING,UNASSIGNED";
            if (compare.indexOf(rql.getAssignedBuyer()) < 0) {
                sBuyer = HiltonUtility.ckNull(rql.getAssignedBuyer());
            }
            
        } else if(header.getRequisitionType().equals(RequisitionType.SUPPLY_REQUEST) 
        		|| header.getRequisitionType().equals(RequisitionType.IMPRINT_REQUEST) 
        		|| header.getRequisitionType().equals(RequisitionType.TRANSFER_REQUEST)) {
            sBuyer = "INVENTORY";
        }
        
        Log.debug(this, "checkTypes buyer: " + sBuyer);
        return sBuyer;
    }

    /**
     * 
     * @param rql
     * @param incomingRequest
     * @return
     */
    @SuppressWarnings("unchecked")
	private String byVendorBuyerRelTable(RequisitionLine rql, Map incomingRequest) {
        String sBuyer = "";
        String vendorId = rql.getVendorId();
        Log.debug(this, "Vendor ID: " + vendorId);

        if (!Utility.isEmpty(vendorId)) {
        	
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from VendorBuyerRel vbr where vbr.id.vendorId = ?";
			
			try {
				List resultList = dbs.query(queryString, new Object[] {vendorId}, new Type[] {Hibernate.STRING});

				if (resultList != null && resultList.size() > 0) {
					VendorBuyerRel objVendorBuyerRel = (VendorBuyerRel) resultList.get(0);
					sBuyer = HiltonUtility.ckNull(objVendorBuyerRel.getComp_id().getUserId());
				}
				
			} catch (Exception e) {
				Log.error(this, "Vendor ID:" + vendorId + " does not have a Buyer Associated!");
                sBuyer = "";
			}
        
            Log.debug(this, "buyer: " + sBuyer);
        }

        return sBuyer;
    }
}
