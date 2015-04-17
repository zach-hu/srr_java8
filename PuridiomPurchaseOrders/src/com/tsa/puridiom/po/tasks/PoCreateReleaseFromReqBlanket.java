/*
 * Created on Dec 1, 2004
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class PoCreateReleaseFromReqBlanket extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        boolean createRelease = false;
        Object ret = null;
        List errorList = new ArrayList();

        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader header = this.getRequisitionHeader(incomingRequest);
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

            PoHeader blanket = null;

            if(!header.getStatus().equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
            {
                createRelease = false;
                errorList.add("Requisition is not Approved.");
            }
            else
            {
                createRelease = true;
            }

            List lineList = this.checkLineItems(incomingRequest);
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");

            if(!header.getStatus().equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
            {
                createRelease = false;
                errorList.add("Requisition is not Approved.");
            }
            else if(lineList.size() < 1)
            {
                Log.error(this.getName(), this.getName() + " no items to be placed on Order were found!");
                errorList.add("No items where found to be placed on Release.");
                createRelease = false;
            }
            else
            {
                blanket = this.getBlanket(incomingRequest);
                createRelease = (blanket != null);
                if(createRelease)
                {
                    if(!blanket.getStatus().equals(DocumentStatus.PO_AWARDED))
                    {
                        createRelease = false;
                        Log.error(this.getName(), this.getName() +" Blanket " + blanket.getPoNumber() + " had a Revision that is not Approved!");
                        errorList.add("Blanket :" + blanket.getPoNumber() + " has a Revision that has not been Approved.");
                    }
                }

                if(createRelease)
                {
                    createRelease = this.checkDates(blanket, header, errorList, userTimeZone);
                }
                if(createRelease)
                {
                    createRelease = this.checkBlanketLimits(blanket, header, incomingRequest, errorList);
                }
            }
            if(createRelease)
            {
                header.setAssignedBuyer("AUTORELEASE");
                header.setAssignedDate(Dates.getCurrentDate(userTimeZone));
                header.setStatus(DocumentStatus.REQ_APPROVED);
                incomingRequest.put("blanket", blanket);
                incomingRequest.put("FORWARD", "N");
            }
            incomingRequest.put("createReleaseFromReq", String.valueOf(createRelease));
            incomingRequest.put("errorList", errorList);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return new Boolean(createRelease);
    }

    /**
     * @param incomingRequest
     * @return
     */
    private RequisitionHeader getRequisitionHeader(Map incomingRequest)
    {
        RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
        Log.debug(this, "RequisitionHeader: " + rqh.getRequisitionNumber());
        return rqh;

    }

    /**
     * @param lineList
     * @return
     */
    private List checkLineItems(Map incomingRequest)
    {
        Log.debug(this, "checkLine Items");
        List lineList = (List)incomingRequest.get("requisitionLineList");
        List lines = new ArrayList();
        String   	assignedOnly = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("ASSIGNMENT","ASSIGNEDONLY", "N");

        for(int i = 0; i < lineList.size(); i++)
        {
        	boolean addLine = true;

            RequisitionLine reqLine = (RequisitionLine)lineList.get(i);
            Log.debug(this, "line: " + reqLine.getLineNumber().toString() + " item " + reqLine.getItemNumber());
            if(assignedOnly.equalsIgnoreCase("Y"))
            {
                String reqAssignedBuyer = reqLine.getAssignedBuyer();
                String userId = (String) incomingRequest.get("userId");
                if(!reqAssignedBuyer.equalsIgnoreCase(userId))
                {
                	addLine = false;
                }
            }
            if(addLine && reqLine.getStatus().equals(DocumentStatus.REQ_APPROVED))
            {
                lines.add(reqLine);
            }
            else
            {
                Log.debug(this, "line " + reqLine.getLineNumber() + " item" + reqLine.getItemNumber() + " can not be placed on Order");
            }
        }
        incomingRequest.put("requisitionLineList", lines);
        return lines;
    }

    public PoHeader getBlanket(Map incomingRequest)
    {
        Log.debug(this, "getBalnket");
        PoHeader result = (PoHeader)incomingRequest.get("blanket");
        if(result != null)
        {
            Log.debug(this, "getBalnket: " + result.getPoNumber() + "-" + result.getRevisionNumber() +
                    ", Release " + result.getReleaseNumber() );
        }
        else
        {
            Log.debug(this, "no blanket found");
        }
        return result;
    }

    public boolean checkDates(PoHeader blanket, RequisitionHeader reqHeader, List errorList, String userTimeZone)
    {
        Log.debug(this, "checkDates");
        boolean createRelease = false;

        Date reqDate = reqHeader.getRequisitionDate();
        if(reqDate == null)
        {
            reqDate = Dates.getCurrentDate(userTimeZone);
        }
        if(!(reqDate.compareTo(blanket.getEffectiveDate()) > -1 && reqDate.compareTo(blanket.getExpirationDate()) < 1))
        {
            createRelease = false;
            String error = "Effective Date or Expiration Date is not valid. (Req Date: " + reqDate.toString()
            + " expiration date: " + blanket.getExpirationDate().toString()
            + " effective date: " + blanket.getEffectiveDate().toString();
            errorList.add(error);
             Log.error(this.getName(), error);
        }
        else
        {
            createRelease = true;
        }
        Log.debug(this, "checkDates returned: " + createRelease);

        return createRelease;
    }

    public boolean checkBlanketLimits(PoHeader blanket, RequisitionHeader rqh, Map incomingRequest, List errorList)
    {
        Log.debug(this, "checkBlanketLimits");
        boolean createRelease = true;
        /*
        boolean createRelease = false;
        String check = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("PO SETUP", "checkrellimits", "Y");
        if(check.equalsIgnoreCase("Y"))
        {

            BigDecimal reqTotal = rqh.getTotal();
            if(reqTotal.compareTo(blanket.getReleaseLimit()) == 1)
            {
                createRelease = false;
                String error = "Request #" + rqh.getRequisitionNumber() + "(Total: "+ reqTotal.toString() + "). Exceeds Release Limt of " + blanket.getReleaseLimit().toString();
                Log.error(this.getName(), error);
                errorList.add(error);
            }
            else
            {//now check that all releases are not over the limit!
                Map tempRequest = new HashMap();
                tempRequest.put("PoHeader_poNumber", blanket.getPoNumber());
                tempRequest.put("dbsession", incomingRequest.get("dbsession"));
                TotalReleasedToDate tRD = new TotalReleasedToDate();
                BigDecimal releasedTotal = new BigDecimal(0);
                try
                {
                    releasedTotal = (BigDecimal)tRD.executeTask(tempRequest);
                }
                catch (Exception e1)
                {
                    createRelease = false;
                    Log.error(this, "Couldn't calculate total released for " + rqh.getRequisitionNumber()) ;
                }
                releasedTotal = releasedTotal.add(reqTotal);
                if(releasedTotal.compareTo(blanket.getBlanketLimit()) == 1)
                {
                    createRelease = false;
                    String error = "Creating a Release Order for Request #" + rqh.getRequisitionNumber() + "(" + releasedTotal.toString() +") will exceed Blanket Limt of :" + blanket.getBlanketLimit().toString();
                    Log.error(this.getName(), error);
                    errorList.add(error);
                }
                else
                {
                    createRelease = true;
                }
            }

        }
        else
        {
            createRelease =  true;
        }
*/        Log.debug(this, "checkBlanketLimits returned: " + createRelease);
        return createRelease;
    }

    private BigDecimal totalAutoRelease(List items)
    {
        BigDecimal total = new BigDecimal(0);
        for(int i = 0; i < items.size(); i++)
        {
            RequisitionLine line = (RequisitionLine)items.get(i);
            total = total.add(line.getLineTotal());
        }
        return total;
    }
}
