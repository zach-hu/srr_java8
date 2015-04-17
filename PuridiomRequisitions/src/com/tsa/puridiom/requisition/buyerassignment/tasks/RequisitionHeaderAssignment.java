/*
 * Created on Apr 19, 2005
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.VendorBuyerRel;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class RequisitionHeaderAssignment extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List lineList = (List)incomingRequest.get("requisitionLineList");
            RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
            PropertiesManager pm = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
            String assignedOnly = pm.getProperty("ASSIGNMENT", "ASSIGNEDONLY", "N");
            String assignedByVendor = pm.getProperty("ASSIGNMENT","ASSIGNBYVENDORBUYERREL","N");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            boolean assignAll = true;
            String  buyerAssigned = "";

            Log.debug(this, "Requisition Header Assignment");
            if (lineList != null && lineList.size() > 0)
            {
                for(int i = 0; i < lineList.size(); i++)
                {
                    RequisitionLine rql = (RequisitionLine)lineList.get(i);
                    if(!rql.getAssignedBuyer().equalsIgnoreCase("AUTORELEASE") && !rql.getAssignedBuyer().equalsIgnoreCase("PURCHASING"))
                    {
                    	buyerAssigned = rql.getAssignedBuyer();
                        reqHeader.setAssignedBuyer(buyerAssigned);
                        reqHeader.setAssignedDate(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
                        Log.debug(this, "Requisition: " + reqHeader.getRequisitionNumber() + " assigned to: " + reqHeader.getAssignedBuyer());
                        i = lineList.size();
                        assignAll = false;
                    }
                }
                incomingRequest.put("forwardedTo", reqHeader.getAssignedBuyer());
                if(assignAll)
                {
                    this.assignAll(reqHeader, lineList, userTimeZone);
                }
            }
            else if (requisitionLine != null)
            {
            	buyerAssigned = requisitionLine.getAssignedBuyer();
                if(!buyerAssigned.equalsIgnoreCase("AUTORELEASE") && !buyerAssigned.equalsIgnoreCase("PURCHASING") && !buyerAssigned.equalsIgnoreCase("UNASSIGNED"))
                {
                    reqHeader.setAssignedBuyer(buyerAssigned);
                    reqHeader.setAssignedDate(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
                    Log.debug(this, "Requisition: " + reqHeader.getRequisitionNumber() + " assigned to: " + reqHeader.getAssignedBuyer());
                }
            }
            if(reqHeader.getRequisitionType().equalsIgnoreCase("N"))
            {
            	buyerAssigned = reqHeader.getAssignedBuyer();
            	reqHeader.setAssignedBuyer(buyerAssigned);
                reqHeader.setAssignedDate(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
            }
            if(assignedOnly.equalsIgnoreCase("Y") || reqHeader.getRqSubType().equalsIgnoreCase("PO"))
            {
            	buyerAssigned = reqHeader.getBuyer();
            	reqHeader.setAssignedBuyer(buyerAssigned);
                reqHeader.setAssignedDate(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
                this.assignOnly(lineList, buyerAssigned, userTimeZone);
            }
            if(assignedByVendor.equalsIgnoreCase("Y"))
            {
            	buyerAssigned = HiltonUtility.ckNull(byVendorBuyerRelTable(reqHeader, incomingRequest));
            	if(!HiltonUtility.isEmpty(buyerAssigned))
            	{
            		reqHeader.setAssignedBuyer(buyerAssigned);
                    reqHeader.setAssignedDate(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
                    this.assignOnly(lineList, buyerAssigned, userTimeZone);
            	}         	
            }
            if(HiltonUtility.isEmpty(reqHeader.getBuyer()))
            {
            	reqHeader.setBuyer(reqHeader.getAssignedBuyer());
            }
            if(HiltonUtility.isEmpty(buyerAssigned))
            {
            	buyerAssigned = "UNASSIGNED";
            	reqHeader.setAssignedBuyer(buyerAssigned);
            }
            ret = reqHeader;
            this.setStatus(Status.SUCCEEDED);

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            Log.error(this, "Exception[" + e.getMessage() + "]");
            throw e;
        }

        return ret;
    }

    public void assignAll(RequisitionHeader reqHeader, List lineList, String userTimeZone)
    {
        String assignedBuyer = "";
        String headerBuyer = reqHeader.getAssignedBuyer();
        if(headerBuyer.equals("PURCHASING") || headerBuyer.equals("AUTORELEASE"))
        {
            Log.debug(this, "Header Assigned Buyer: " + headerBuyer);
        }
        else
        {
            for(int i = 0; i < lineList.size(); i++)
            {
                RequisitionLine rql = (RequisitionLine)lineList.get(i);
                String lineAssigned = rql.getAssignedBuyer();
                if(Utility.isEmpty(assignedBuyer) || lineAssigned.equals(assignedBuyer))
                {
                    assignedBuyer = lineAssigned;
                }
                else
                {
                    //leave it alone.
                    headerBuyer = "";
                }
            }
            reqHeader.setAssignedBuyer(assignedBuyer);
            reqHeader.setAssignedDate(Dates.getCurrentDate(userTimeZone));

            Log.debug(this, "Header Assigned Buyer: " + assignedBuyer);
        }
    }
    
    public void assignOnly(List lineList,String buyer, String userTimeZone)
    {
        String assignedBuyer = "";
        if (lineList != null && lineList.size() > 0) {
        	for(int i = 0; i < lineList.size(); i++)
            {
            	RequisitionLine rql = (RequisitionLine)lineList.get(i);
                String lineAssigned = rql.getAssignedBuyer();
                if(!Utility.isEmpty(buyer))
                {
                	lineAssigned = buyer;
                }
                rql.setAssignedBuyer(lineAssigned);
                rql.setAssignedDate(Dates.getCurrentDate(userTimeZone));

                Log.debug(this, "Header Assigned Buyer: " + assignedBuyer);
            }
        }
    }
    private String byVendorBuyerRelTable(RequisitionHeader rh, Map incomingRequest) {
        String sBuyer = "";
        String vendorId = rh.getVendorId();
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
