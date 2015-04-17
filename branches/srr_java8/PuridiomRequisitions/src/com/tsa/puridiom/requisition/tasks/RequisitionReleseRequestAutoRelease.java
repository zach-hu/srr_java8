/*
 * Created on Dec 1, 2004
 */
package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
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
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class RequisitionReleseRequestAutoRelease extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        boolean createRelease = false;
        boolean createReleaseAssignedToMe = false;
        Object ret = null;
        try
        {

            Map incomingRequest = (Map)object;
            String orgId = (String)incomingRequest.get("organizationId");
            PropertiesManager propertiesManger = PropertiesManager.getInstance(orgId);
            RequisitionHeader header = this.getRequisitionHeader(incomingRequest);
            if (incomingRequest.containsKey("createReleaseFromReqAssignedToMe")) {
            	createReleaseAssignedToMe = ((String)incomingRequest.get("createReleaseFromReqAssignedToMe")).equals("true");
            }

            PoHeader blanket = null;

            if(!header.getStatus().equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
            {
                createRelease = false;
                this.setStatus(Status.SUCCEEDED);
                return null;
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
            }
            else if(lineList.size() < 1)
            {
                Log.error(this.getName(), this.getName() + " is not Approved!" + "(" + header.getStatus() + ")");
                createRelease = false;
            }
            else
            {
                blanket = this.getBlanket(dbs, header);
                createRelease = (blanket != null);
                if(createRelease)
                {
                    if(!blanket.getStatus().equals(DocumentStatus.PO_AWARDED))
                    {
                        createRelease = false;
                        Log.error(this.getName(), this.getName() +" Blanket " + blanket.getPoNumber() + " had a Revision that is not Approved!");
                    }

                }

                if(createRelease)
                {
                    createRelease = this.checkDates(blanket, header);
                }
                if(createRelease)
                {
                    createRelease = this.checkBlanketLimits(blanket, header, incomingRequest);
                }
            }
            if(createRelease || createReleaseAssignedToMe)
            {
            	if (propertiesManger.getProperty("PO OPTIONS", "RELEASEASSIGNEDBUYER", "N").equalsIgnoreCase("Y"))
            	{
            		header.setAssignedBuyer(header.getAssignedBuyer());
            	}
            	else
            	{
            		header.setAssignedBuyer("AUTORELEASE");
            	}
                header.setAssignedDate(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
                header.setStatus(DocumentStatus.REQ_APPROVED);
                incomingRequest.put("blanket", blanket);
            }
            incomingRequest.put("createReleaseFromReq", String.valueOf(createRelease));

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
        return (RequisitionHeader)incomingRequest.get("requisitionHeader");
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
        for(int i = 0; i < lineList.size(); i++)
        {
            RequisitionLine reqLine = (RequisitionLine)lineList.get(i);
            if(reqLine.getStatus().equals(DocumentStatus.REQ_APPROVED))
            {
                lines.add(reqLine);
            }
            else
            {
                Log.debug(this, "line " + reqLine.getLineNumber() + " item" + reqLine.getItemNumber() + " can not be placed on Order");
            }
        }
        return lines;
    }

    public PoHeader getBlanket(DBSession dbs, RequisitionHeader rqh)
    {
        Log.debug(this, this.getName() + ".getBlanket(" + rqh.getRequisitionNumber() + ")");
        PoHeader result = null;
        BigDecimal icPoHeader = rqh.getIcRevisedOrder();
        if(!Utility.isObjectEmpty(icPoHeader))
        {
            try
            {
                Log.debug(this, "icPoHeader: " + icPoHeader);
                String queryString = "from PoHeader as PoHeader where PoHeader.id = ? ";
                List resultList = dbs.query(queryString, new Object[] {icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;

                if (resultList != null && resultList.size() > 0)
                {
                    result = (PoHeader)resultList.get(0);
                    if(!result.getLastRevision().equals("C"))
                    {
                        queryString = "from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.lastRevision = 'C'";
                        List tempList = dbs.query(queryString, new Object[] {result.getPoNumber() } , new Type[] { Hibernate.STRING}) ;
                        if (tempList != null && tempList.size() > 0)
                        {
                            result = (PoHeader)tempList.get(0);
                        }
                        else
                        {
                            result = null;
                        }
                    }
                }

            }
            catch (Exception e)
            {
                Log.error(this.getName(), "Blanket for Release Request " + rqh.getRequisitionNumber() + " was not found");
                return null;
            }
        }
        else
        {
            return null;
        }
        return result;
    }

    public boolean checkDates(PoHeader blanket, RequisitionHeader reqHeader)
    {
        Log.debug(this, "checkDates");
        boolean createRelease = false;

        Date reqDate = reqHeader.getRequisitionDate();
        if(reqDate == null)
        {
            reqDate = Dates.getCurrentDate(reqHeader.getTimeZone());
        }
        if(!(reqDate.compareTo(blanket.getEffectiveDate()) > -1 && reqDate.compareTo(blanket.getExpirationDate()) < 1))
        {
            Log.error(this.getName(), "Effective Date or Expiration Date is not valid. (Req Date: " + reqDate.toString()
                    + " expiration date: " + blanket.getExpirationDate().toString()
                    + " effective date: " + blanket.getEffectiveDate().toString());
            createRelease = false;
        }
        else
        {
            createRelease = true;
        }
        Log.debug(this, "checkDates returned: " + createRelease);

        return createRelease;
    }

    public boolean checkBlanketLimits(PoHeader blanket, RequisitionHeader rqh, Map incomingRequest)
    {
        Log.debug(this, "checkBlanketLimits");
        boolean createRelease = false;
        String check = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("PO SETUP", "checkrellimits", "Y");
        if(check.equalsIgnoreCase("Y"))
        {

            BigDecimal reqTotal = rqh.getTotal();
            if(reqTotal.compareTo(blanket.getReleaseLimit()) == 1)
            {
                createRelease = false;
                Log.error(this.getName(), "Release Request #" + rqh.getRequisitionNumber() + " Exceeds Release Limt of " + blanket.getReleaseLimit().toString());
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
                    Log.error(this.getName(), RequisitionType.RELEASE_REQUEST + "#" + rqh.getRequisitionNumber() + " Exceeds Blanket Limt!");
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
        Log.debug(this, "checkBlanketLimits returned: " + createRelease);
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
