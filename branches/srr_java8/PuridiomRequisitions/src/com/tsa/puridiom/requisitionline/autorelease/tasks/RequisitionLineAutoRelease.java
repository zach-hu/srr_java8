/*
 * Created on Mar 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.common.autorelease.AutoReleaseObjectComparator;
import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
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
public class RequisitionLineAutoRelease extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
            Map incomingRequest = (Map)object;
            Map groupByOrder = (Map)incomingRequest.get("groupByOrder");
            Map blanketOrdersList = (Map)incomingRequest.get("blanketOrdersList");
            List autoLines = new ArrayList();

            if(groupByOrder.size() > 0)
            {
            	//items to create release for
                Set keys = groupByOrder.keySet();
                System.out.println("<<<<<<<blankets: " + keys.size()+ " >>>>>>>>>>>>>>>");
                for (Iterator iter = keys.iterator(); iter.hasNext();)
                {
                    String blanketNumber = (String) iter.next();
                    System.out.println("<<<<<<<blanket " + blanketNumber + " >>>>>>>>>>>>>>>");
                    PoHeader blanket = (PoHeader)blanketOrdersList.get(blanketNumber);
                    Map groupByShipTo = (Map)groupByOrder.get(blanketNumber);
                    Set shipTokeys = groupByShipTo.keySet();
                    System.out.println("<<<<<<<ShipTos: " + shipTokeys.size()+ " >>>>>>>>>>>>>>>");

                    for (Iterator iterShipTos = shipTokeys.iterator(); iterShipTos.hasNext();)
                    {
                        String shipToCode = (String) iterShipTos.next();
                        System.out.println("<<<<<<<shipToCode " + shipToCode + " >>>>>>>>>>>>>>>");
                        Log.debug(this, "AutoRelease for items from shipToCode: " + shipToCode);
                        List requisitionLineAutoReleaseList = (List)groupByShipTo.get(shipToCode);
                        Collections.sort(requisitionLineAutoReleaseList, new AutoReleaseObjectComparator());
                        System.out.println("<<<<<<<shipToCode " + shipToCode + " after comparator >>>>>>>>>>>>>>>");

                        //retrieve requisition information/billto/comments/accounts.
                        List requisitionLineList = this.getRequisitionLineList(requisitionLineAutoReleaseList, incomingRequest);
                        System.out.println("<<<<<<<shipToCode " + shipToCode + " got RequisitionLIne Data >>>>>>>>>>>>>>>");
                        this.getReleaseOrder(requisitionLineList, blanket, shipToCode, incomingRequest, autoLines);
                        System.out.println("<<<<<<<shipToCode " + shipToCode + " created order >>>>>>>>>>>>>>>");
                    }
                }
            }
            else
            {
            	Log.debug(this, "No items to autorelease!");
            }

            Log.debug(this, "AUTORELEASE PART DONE!");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Release Order could not be created!", e);
        }
        return ret;
    }


    private void getReleaseOrder(List requisitionLineList, PoHeader blanket, String shipToCode, Map incomingRequest, List autoLines)
    {
    	System.out.println("<<<<<<<shipToCode " + shipToCode + " create Release >>>>>>>>>>>>>>>");
    	Map newIncomingRequest = new HashMap();
        newIncomingRequest.put("blanket", blanket);
        //newIncomingRequest.put("requisitionHeader", rqh);
        //newIncomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

        newIncomingRequest.put("PoHeader_icPoHeader", blanket.getIcPoHeader().toString());

        newIncomingRequest.put("requisitionLineList", requisitionLineList);
        newIncomingRequest.put("Vendor_vendorId", blanket.getVendorId());
        //newIncomingRequest.put("PoLine_catalogId", reqLine);
        newIncomingRequest.put("recalculate", "Y");
        newIncomingRequest.put("createdfrom", this.createdFrom());

        newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
        newIncomingRequest.put("userId", incomingRequest.get("userId"));
        newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
        newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
        newIncomingRequest.put("createReleaseFromReq", Boolean.TRUE);
        newIncomingRequest.put("forwardallowed", "Y");

          if(requisitionLineList.size() > 0)
          {
        	  RequisitionLineAutoReleaseObject tmpReqLineAutoObject = (RequisitionLineAutoReleaseObject)requisitionLineList.get(0);
        	  RequisitionLine tmpReqLine = tmpReqLineAutoObject.getRequistionLine();
        	  List reqLineshipToList = tmpReqLine.getShipToList();
        	  if(reqLineshipToList != null && reqLineshipToList.size() > 0)
              {
        		  shipToCode = ((ShipTo)reqLineshipToList.get(0)).getShipToCode();
              }
          }

        newIncomingRequest.put("shipToCode", shipToCode);
        newIncomingRequest.put("autoLines", autoLines);
        newIncomingRequest.put("userId", "AUTORELEASE");
        newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));

        try
        {
        	System.out.println("<<<<<<<linelist " + requisitionLineList + " >>>>>>>>>>>>>>>");
	        PuridiomProcessLoader processLoader = new PuridiomProcessLoader();

	        PuridiomProcess process = processLoader.loadProcess("po-auto-release.xml");
	        process.executeProcess(newIncomingRequest);

            String userTimeZone = (String) incomingRequest.get("userTimeZone");

            if(process.getStatus() != Status.SUCCEEDED)
	        {
	            //msgList.add("Items " + requisitionLineList + " could not be placed on Order! " );
	            this.setExtraReqLineData(requisitionLineList, blanket.getBuyerCode(), blanket.getDisplayPoNumber().toString(), userTimeZone);
	            //PoHeader_releaseNumber
	        }
	        else
	        {
	            PoHeader poHeader = (PoHeader)newIncomingRequest.get("poHeader");
	            Log.error(this, "ReleaseOrder Created." + poHeader.getDisplayPoNumber());
	            incomingRequest.put("autoLines", newIncomingRequest.get("autoLines"));
	            //msgList.add("Items " + requisitionLineList + " where Released on " + OrderType.RELEASE_ORDER +
	               //                 "# " + poHeader.getPoNumber() + "-" + poHeader.getReleaseNumber().toString());
	            //newReleases.add(poHeader);
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

	            this.setExtraReqLineData(requisitionLineList, "AUTORELEASE", poHeader.getDisplayPoNumber().toString(), userTimeZone);
	        }
        }
        catch (Exception e)
        {
			Log.error(this, "Release Order could not be created for: " + blanket.getPoNumber());
		}
	}


	public List getRequisitionLineList(List requisitionLineAutoReleaseList, Map incomingRequest)
    {
    	List requisitionLineList = new ArrayList();
        for(int i = 0; i < requisitionLineAutoReleaseList.size(); i++)
        {
            Map newIncomingRequest = new HashMap();
            try
            {
	            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
	            newIncomingRequest.put("userId", incomingRequest.get("userId"));
	            newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
	            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
	            RequisitionLineAutoReleaseObject requisitionLineAutoReleaseObject = (RequisitionLineAutoReleaseObject)requisitionLineAutoReleaseList.get(i);
	            newIncomingRequest.put("autoReleaseObject", requisitionLineAutoReleaseObject);

	            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	            PuridiomProcess process = processLoader.loadProcess("requisitionlinedata-retrieve-autorelease.xml");
	            process.executeProcess(newIncomingRequest);
	            if(process.getStatus() != Status.SUCCEEDED)
	            {
	            	Log.error(this, "requisition data was not found.");
	            }
	            else
	            {
	            	requisitionLineAutoReleaseObject.setRequistionLine((RequisitionLine)newIncomingRequest.get("requisitionLine"));
	            	requisitionLineList.add(requisitionLineAutoReleaseObject);
	            }
            }
            catch (Exception e)
            {
				Log.error(this, e.getMessage());
			}
        }
        return requisitionLineList;
    }

    public String createdFrom()
    {
        return "REQ";
    }

    /**
     *  Sets Assigned Buyer.
     *  Sets Release Number.
     * @param reqLines
     * @param buyer
     * @return
     */
    public List setExtraReqLineData(List reqLines, String buyer, String displayNumber, String userTimeZone)
    {

        for(int i = 0; i < reqLines.size(); i++)
        {
            RequisitionLine reqLine = (RequisitionLine)reqLines.get(i);
            Log.debug(this, "Setting assigned Buyer for line: " + reqLine.getLineNumber().toString() + ", buyer: " + buyer);
            reqLine.setAssignedBuyer(buyer);
            reqLine.setAssignedDate(Dates.getCurrentDate(userTimeZone));
            reqLine.setBlanketOrder(displayNumber);
            reqLines.set(i, reqLine);
        }

        return reqLines;
    }
}
