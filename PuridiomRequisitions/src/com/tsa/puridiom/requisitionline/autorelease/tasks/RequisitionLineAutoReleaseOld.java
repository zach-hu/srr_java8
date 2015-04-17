/*
 * Created on Mar 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionLineAutoReleaseOld extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;

            Map groupByOrder = (Map)incomingRequest.get("groupByOrder");
            Map blanketOrdersList = (Map)incomingRequest.get("blanketOrdersList");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            List msgList = new ArrayList();

            if(groupByOrder.size() > 0)
            {
            	//items to create release for
                Set keys = groupByOrder.keySet();
                List newReleases = new ArrayList();
                String recalculate = "Y";

                for (Iterator iter = keys.iterator(); iter.hasNext();)
                {
                    String blanketNumber = (String) iter.next();
                    PoHeader blanket = (PoHeader)blanketOrdersList.get(blanketNumber);
                    Map groupByShipTo = (Map)groupByOrder.get(blanketNumber);
                    Set shipTokeys = groupByShipTo.keySet();

                    for (Iterator iterShipTos = shipTokeys.iterator(); iterShipTos.hasNext();)
                    {
                        String shipToCode = (String) iterShipTos.next();
                        Log.debug(this, "AutoRelease for items from shipToCode: " + shipToCode);
                        List dataArray = (List)groupByShipTo.get(shipToCode);

                        List requisitionLineList = this.getLineList(dataArray);

                        Map newIncomingRequest = new HashMap();
                        newIncomingRequest.put("blanket", blanket);
                        //newIncomingRequest.put("requisitionHeader", rqh);
                        //newIncomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

                        newIncomingRequest.put("PoHeader_icPoHeader", blanket.getIcPoHeader().toString());

                        newIncomingRequest.put("requisitionLineList", requisitionLineList);
                        newIncomingRequest.put("Vendor_vendorId", blanket.getVendorId());
                        //newIncomingRequest.put("PoLine_catalogId", reqLine);
                        newIncomingRequest.put("recalculate", recalculate);
                        newIncomingRequest.put("createdfrom", this.createdFrom());

                        newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                        newIncomingRequest.put("userId", incomingRequest.get("userId"));
                        newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                        newIncomingRequest.put("createReleaseFromReq", Boolean.TRUE);
                        newIncomingRequest.put("forwardallowed", "Y");

                        PuridiomProcessLoader processLoader = new PuridiomProcessLoader();

                        PuridiomProcess process = processLoader.loadProcess("po-create-release-partial.xml");
                        process.executeProcess(newIncomingRequest);
                        if(process.getStatus() != Status.SUCCEEDED)
                        {
                            msgList.add("Items " + requisitionLineList + " could not be placed on Order! " );
                            this.setAssignedBuyer(requisitionLineList, blanket.getBuyerCode(), userTimeZone);
                        }
                        else
                        {
                            PoHeader poHeader = (PoHeader)newIncomingRequest.get("poHeader");
                            Log.debug(this, "ReleaseOrder Created." + poHeader.getDisplayPoNumber());
                            //msgList.add("Items " + requisitionLineList + " where Released on " + OrderType.RELEASE_ORDER +
                               //                 "# " + poHeader.getPoNumber() + "-" + poHeader.getReleaseNumber().toString());
                            newReleases.add(poHeader);
                            /*AutoReleasedItemsSummary autoReleasedItems = new AutoReleasedItemsSummary(rqh, requisitionLineList, poHeader);
                            autoReleasedItems.setPoNumber(poHeader.getPoNumber());
                            autoReleasedItems.setReleaseNumber(poHeader.getReleaseNumber());
                            //autoReleasedItems.setRqSubType(rqh.getRqSubType());
                            autoReleasedItems.setRequisitionNumber(rqh.getRequisitionNumber());
                            autoReleasedItems.setIcReqHeader(rqh.getIcReqHeader());
                            autoReleasedItems.setRequisitioner(rqh.getRequisitionerCode());
                            autoReleasedItems.setReleaseOrder(poHeader);
                            Log.debug(this, "Items: " +autoReleasedItems.toString());
                            msgList.add(autoReleasedItems);
                            */
                            this.setAssignedBuyer(requisitionLineList, "AUTORELEASE", userTimeZone);
                        }
                    }



                }
                incomingRequest.put("releaseMessages", msgList);
                incomingRequest.put("poFromRelease", "Y");
                incomingRequest.put("releases", newReleases);
            }
            else
            {
                //rest of items should go through normal process.?????????
            	Log.error(this, "No items to autorelease!");
            }

            Log.error(this, "AUTORELEASE PART DONE!");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Release Order could not be created!", e);
        }
        return super.executeTask(object);
    }

    public List getLineList(List data)
    {
    	List lines = new ArrayList();
    	for(int i = 0; i < data.size(); i++)
    	{
    		Object linesData[] = (Object[])data.get(i);
    		RequisitionLine reqLine = (RequisitionLine)linesData[1];
    		ShipTo shipTo = (ShipTo)linesData[2];
    		if(shipTo != null)
    		{
    			reqLine.setQuantity(shipTo.getQuantity());
    		}
    		lines.add(reqLine);
    	}
    	return lines;
    }

    public String createdFrom()
    {
        return "REQ";
    }

    public List setAssignedBuyer(List reqLines, String buyer, String userTimeZone)
    {

        for(int i = 0; i < reqLines.size(); i++)
        {
            RequisitionLine reqLine = (RequisitionLine)reqLines.get(i);
            Log.debug(this, "Setting assigned Buyer for line: " +
                                reqLine.getLineNumber().toString() + ", buyer: " + buyer);
            reqLine.setAssignedBuyer(buyer);
            reqLine.setAssignedDate(Dates.getCurrentDate(userTimeZone));
            reqLines.set(i, reqLine);
        }

        return reqLines;
    }
}
