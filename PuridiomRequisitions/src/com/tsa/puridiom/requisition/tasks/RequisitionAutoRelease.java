/*
 * Created on Dec 1, 2004
 */
package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class RequisitionAutoRelease extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            boolean createRelease = true;
            Map incomingRequest = (Map)object;
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            Log.debug(this, "AUTORELEASE PROCESS starting for " + header.getRequisitionNumber());

            BigDecimal icPoHeader = new BigDecimal(0);

            if(!header.getStatus().equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
            {
                createRelease = false;
                incomingRequest.put("createReleaseFromReq", String.valueOf(createRelease));
                this.setStatus(Status.SUCCEEDED);

                Log.debug(this, "Requisition " + header.getRequisitionNumber() + " is not Approved");

                return ret;
            }
            List lineList = (List)incomingRequest.get("requisitionLineList");

            if(!header.getStatus().equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
            {
                incomingRequest.put("createReleaseFromReq", String.valueOf(createRelease));
                createRelease = false;
            }
            else if(lineList.size() < 1)
            {
                createRelease = false;
                incomingRequest.put("createReleaseFromReq", String.valueOf(createRelease));
            }
            else
            {
                createRelease = this.getReleaseItems(lineList, incomingRequest);
            }

            incomingRequest.put("createReleaseFromReq", String.valueOf(createRelease));

            ret = icPoHeader.toString();
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName() + ", " + e.getMessage() , e);
        }
        return ret;
    }

    /**
     * Find all items that are to be autoreleased.
     * if createRelease == true then there is an autorelease
     * if releasedItems.size > 1 there is multiple autoreleases.
     * @param lineList
     */
    private boolean getReleaseItems(List lineList, Map incomingRequest)
    {
        boolean createRelease = false;
        Map releasedItems = new HashMap();
        Map nonReleaseItems = new HashMap();
        StringBuffer buyersAssigned = new StringBuffer();
        String NonReleaseMsg = "";

        //find items to be released
        for(int i = 0; i < lineList.size(); i++)
        {
            RequisitionLine reqLine = (RequisitionLine)lineList.get(i);
            Object temp[] = this.getIcPoHeader(reqLine.getCatalogId(), (DBSession)incomingRequest.get("dbsession"));
            BigDecimal icPoHeader = (BigDecimal)temp[0];
            boolean allowReleases = (((String)temp[2]).equals("Y"));

            boolean noReleaseNeeded = true;

            if(allowReleases && icPoHeader.compareTo(new BigDecimal(0)) > 0)
            {
                createRelease = true;
                noReleaseNeeded = false;
                if(releasedItems.containsKey(reqLine.getCatalogId()))
                {
                    List items = (List)releasedItems.get(reqLine.getCatalogId());
                    if(items == null)
                    {
                        items = new ArrayList();
                    }
                    items.add(reqLine);

                    releasedItems.put(reqLine.getCatalogId(), items);
                }
                else
                {
                    List items = new ArrayList();
                    items.add(reqLine);
                    releasedItems.put(reqLine.getCatalogId(), items);
                }
            }
            else
            {//Catalog does not allow releases.
                //List nonReleaseItemsList = (List)nonReleaseItems.get(icPoHeader);
            	Log.debug(this, "Catalog" + reqLine.getCatalogId() + " does not allow Releases.");

            	Object nonReleasedItem[] = (Object[])nonReleaseItems.get(icPoHeader);
                if(nonReleasedItem == null)
                {
                    nonReleasedItem = new Object[2];
                    nonReleasedItem[0] = new ArrayList();
                    nonReleasedItem[1] = NoAutoReleaseReasons.CATALOG_NO_RELEASES;
                }
                ((List)nonReleasedItem[0]).add(reqLine);

                //nonReleaseItems.put(icPoHeader, nonReleaseItemsList);
                nonReleaseItems.put(icPoHeader, nonReleasedItem);
                buyersAssigned.append(reqLine.getAssignedBuyer() + ";");
            }
        }

        //check if items meet criteria.
        RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
        Set keySet = releasedItems.keySet();
        boolean tempCreateRelease = false;
        Map tempReleasedItems = new HashMap();
        DBSession dbs = (DBSession)incomingRequest.get("dbsession");
        for (Iterator iter = keySet.iterator(); iter.hasNext();)
        {
            String catalogId = (String) iter.next();

            Log.debug(this, "Searching AutoRelease Items, Catalog: " + catalogId);

            List itemsTemp = (List)releasedItems.get(catalogId);
            Object temp[] = this.getIcPoHeader(catalogId, dbs);
            PoHeader blanket = null;
            BigDecimal icPoHeader = (BigDecimal)temp[0];

            //find blanket
            String sql = "From PoHeader poHeader Where poHeader.icPoHeader = " + icPoHeader +
                               " AND poHeader.lastRevision = 'C' ";
            List tempList;
            try
            {
                tempList = dbs.query(sql);
            }
            catch (Exception e)
            {
                tempCreateRelease = false;
                tempList = new ArrayList();
                Log.error(this, "Blanket Order for ic: " + icPoHeader.toString() + "was not Found!");
            }

            /*
             * do not autorelease the items if:
             *      the blanket order has expired
                �   the blanket order has been superceded
                �   the release order exceeds the release limit
                �   the total of all release orders exceeds the blanket limit
                �   the supplier is no longer active (should we have to check this here?)
                �   there is an incomplete revision of the blanket order.
             */
            if(tempList.size() > 0)
            {
                blanket = (PoHeader)tempList.get(0);
                //incomplete revision
                String tempMessage = this.findIncompleteRevision(blanket);
                if( tempMessage != "1")
            	{
            		NonReleaseMsg = tempMessage;
                    tempCreateRelease = false;
                    Log.warn(this, "Blanket Order has an Incomplete Revision");
            	}
                else
                {
                	tempCreateRelease = true;
                }

                //blanket order expired
                Date reqDate = reqHeader.getRequisitionDate();
                if(reqDate == null)
               {
                    String userTimeZone = (String) incomingRequest.get("userTimeZone");
                    reqDate = Dates.getCurrentDate(userTimeZone);
               }
                if(tempCreateRelease)
                {
	                if(!this.isDatesValid(blanket, reqDate))
	                {
	                    NonReleaseMsg = NoAutoReleaseReasons.ORDER_EXPIRED;
	                    tempCreateRelease = false;
	                    Log.error(this, "Blanket Order has Expired or is not yet Effective");
	                }
	                else
	                {
	                    tempCreateRelease = true;
	                }
                }

                if(tempCreateRelease)
                {
                    String check = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("PO SETUP", "checkrellimits", "Y");
                    if(check.equalsIgnoreCase("Y"))
                    {
                        BigDecimal totalTemp = this.totalAutoRelease(itemsTemp);
                        if(totalTemp.compareTo(blanket.getReleaseLimit()) == 1)
                        {
                            NonReleaseMsg = NoAutoReleaseReasons.EX_RELEASE_LIMIT;
                            tempCreateRelease = false;
                            Log.warn(this.getName(), "Purchase Request #" + reqHeader.getRequisitionNumber() + " ,Items for Catalog:  " + catalogId + " Exceeds Release Limt.");
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
                                NonReleaseMsg = "-3";
                                tempCreateRelease = false;
                                Log.warn(this, "Couldn't calculate total released for " + reqHeader.getRequisitionNumber() + ", Catalog " + catalogId) ;
                            }
                            releasedTotal = releasedTotal.add(totalTemp);
                            if(releasedTotal.compareTo(blanket.getBlanketLimit()) == 1)
                            {
                                NonReleaseMsg = NoAutoReleaseReasons.EX_BLANKET_LIMIT;
                                tempCreateRelease = false;
                                Log.warn(this.getName(), "Purchase Request #" + reqHeader.getRequisitionNumber() + " ,Items for Catalog:  " + catalogId + " Exceeds Blanket Limt!");
                            }
                        }
                    }
                }
            }
            else
            {
            	Log.warn(this, "Searching for autorelease items. No items were selected!");
                tempCreateRelease = false;
            }
            if(!tempCreateRelease)
            {
                //assign req items to buyer from blanket if release is not to be created.
                if(blanket != null)
                {
                    for(int i = 0; i < itemsTemp.size(); i++)
                    {
                        RequisitionLine rql = (RequisitionLine)itemsTemp.get(i);
                        rql.setAssignedBuyer(blanket.getBuyerCode());
                        Log.warn(this, "Items are not AUTORELEASED assigning to " + blanket.getBuyerCode());
                    }
                }
                else
                {
                	Log.warn(this, "Items are not AUTORELEASED assigning to PURCHASING");
                }
                Object nonReleasedItem[] = new Object[3];
                nonReleasedItem[0] = itemsTemp;
                nonReleasedItem[1] = NonReleaseMsg;
                nonReleaseItems.put(catalogId, nonReleasedItem);
            }
            else
            {
                Object tempArray[] = new Object[3];
                /* lineitems */
                tempArray[0] = itemsTemp;
                /* blanket order */
                tempArray[1] = blanket;
                /* vendorId */
                tempArray[2] = temp[1];
                tempReleasedItems.put(catalogId, tempArray);
                buyersAssigned.append(blanket.getBuyerCode() + ";");
            }
        }
        releasedItems = tempReleasedItems;

        incomingRequest.put("releaseItems", releasedItems);
        incomingRequest.put("nonReleaseItems", nonReleaseItems);
        incomingRequest.put("buyersAssigned", buyersAssigned);

        return createRelease;
    }

    /**
     * @return
     */
    private boolean isDatesValid(PoHeader blanket, Date reqDate)
    {
        if(reqDate != null && blanket.getEffectiveDate() != null && blanket.getExpirationDate() != null)
        {
            if ((reqDate.compareTo(blanket.getEffectiveDate()) > -1 && reqDate.compareTo(blanket.getExpirationDate()) < 1))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            Log.debug(this, "Blanket expiration Date : " + blanket.getExpirationDate() + ", or Blanket effective Date: " + blanket.getEffectiveDate() +" is not valid.");
            return false;
        }
    }

    public String findIncompleteRevision(PoHeader blanket)
    {
        String ret = "1";

        try
        {
            if(!blanket.getStatus().equalsIgnoreCase(DocumentStatus.PO_AWARDED))
            {
            	if(blanket.getRevisionNumber().compareTo(new BigDecimal(0)) > 0)
            	{
            		ret = NoAutoReleaseReasons.INCOMPLETE_REVISION;
            	}
            	else
            	{
            		ret = NoAutoReleaseReasons.ORDER_NOT_AWARDED;
            	}
            }
        }
        catch (Exception e)
        {
            Log.error(this.getName(), "Couldn't find revision for blanket " + blanket.getPoNumber());
        }
        Log.debug(this, "Latest Release of " + blanket.getPoNumber() + " has " + ret + " is incomplete");

        return ret;

    }

    public Object[] getIcPoHeader(String catalogId, DBSession dbs)
    {
        Object ret[] = new Object[3];
        BigDecimal icNext = new BigDecimal(0);
        String vendorid = "";
        String allowReleases = "N";
        try
        {
            String sql = "Select catalog.poNumber, catalog.vendorId, catalog.allowReleases from Catalog catalog where catalog.catalogId = '" + catalogId + "'";
            List icList = dbs.query(sql);

            if(icList.size() > 0)
            {
                Object arrayTemp[] = (Object[])icList.get(0);
                String	poNumber = (String)arrayTemp[0];
                vendorid = (String)arrayTemp[1];
                allowReleases = (String)arrayTemp[2];
                sql = "Select po.icPoHeader from PoHeader po where po.poNumber = '" + poNumber + "' and " +
                        "(po.poType = 'BO' or po.poType = 'SB' or po.poType = 'DO') and po.lastRevision = 'C' and " +
                        "(po.status >= 3000 and po.status < 9000)";
                List poList = dbs.query(sql);
                if (poList.size() > 0) {
                    icNext = (BigDecimal)poList.get(0);
                }
            }
            else
            {
                icNext = new BigDecimal(0);
                vendorid = "staples";
            }
        }
        catch (Exception e)
        {
            Log.error(this.getName(), "Catalog: " + catalogId + " could not be found!");
        }
        ret[0] = icNext;
        ret[1] = vendorid;
        ret[2] = allowReleases;

        return ret;
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
