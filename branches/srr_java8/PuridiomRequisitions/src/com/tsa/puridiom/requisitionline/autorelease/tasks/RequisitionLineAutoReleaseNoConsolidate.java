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

import com.tsa.puridiom.common.autorelease.AutoReleaseUtility;
import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
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
public class RequisitionLineAutoReleaseNoConsolidate extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
            Map incomingRequest = (Map)object;
            boolean createReleaseFromReq = true;

            Map releaseItems = this.getGroup(incomingRequest);
            Map blanketOrdersList = (Map)incomingRequest.get("blanketOrdersList");
            List autoLines = new ArrayList();

            if(releaseItems.size() > 0)
            {//items to create release for
                Set keys = releaseItems.keySet();
                String recalculate = "Y";
                if(releaseItems.size() > 1){		recalculate = "Y";		}

                for (Iterator iter = keys.iterator(); iter.hasNext();)
                {
                	String blanketNumber = (String) iter.next();
                    System.out.println("<<<<<<<blanket " + blanketNumber + " >>>>>>>>>>>>>>>");
                    PoHeader blanket = (PoHeader)blanketOrdersList.get(blanketNumber);
                    if(blanket != null)
                    {
	                    Map groupByReq = (Map)releaseItems.get(blanketNumber);
	                    Set reqNumberkeys = groupByReq.keySet();

                    	for (Iterator iterShipTos = reqNumberkeys.iterator(); iterShipTos.hasNext();)
	                    {
	                    	String reqNumber = (String) iterShipTos.next();
	                        System.out.println("<<<<<<<Requisition Number " + reqNumber + " >>>>>>>>>>>>>>>");
	                        Log.debug(this, "AutoRelease for items from requisition: " + reqNumber);
	                        List requisitionLineList = (List)groupByReq.get(reqNumber);
	                        RequisitionLineAutoReleaseObject rlao = (RequisitionLineAutoReleaseObject)requisitionLineList.get(0);
	                        Map newIncomingRequest = new HashMap();
	                        newIncomingRequest.put("blanket", blanket);
	                        newIncomingRequest.put("autoLines", autoLines);
	                        RequisitionHeader rqh = rlao.getRequisitionHeader();
	                        newIncomingRequest.put("requisitionHeader", rqh);
	                        newIncomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

	                        newIncomingRequest.put("PoHeader_icPoHeader", blanket.getIcPoHeader().toString());
	                        List reqLineList = AutoReleaseUtility.getRequisitionLineList(requisitionLineList);
	                        newIncomingRequest.put("requisitionLineList", reqLineList);
	                        newIncomingRequest.put("autoLineList", requisitionLineList);
	                        newIncomingRequest.put("Vendor_vendorId", blanket.getVendorId());
	                        newIncomingRequest.put("PoLine_catalogId", rlao.getRequistionLine().getCatalogId());
	                        newIncomingRequest.put("recalculate", recalculate);
	                        newIncomingRequest.put("createdfrom", "REQ");

	                        newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
	                        newIncomingRequest.put("userId", incomingRequest.get("userId"));
	                        newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
	                        newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
	                        newIncomingRequest.put("createReleaseFromReq", new Boolean(createReleaseFromReq));
	                        newIncomingRequest.put("forwardallowed", "Y");
	                        newIncomingRequest.put("fromAutorelease", "Y");

                            String userTimeZone = (String) incomingRequest.get("userTimeZone");

	                        PuridiomProcessLoader processLoader = new PuridiomProcessLoader();

	                        PuridiomProcess process = processLoader.loadProcess("po-create-release-partial.xml");
	                        process.executeProcess(newIncomingRequest);
	                        if(process.getStatus() != Status.SUCCEEDED)
	            	        {
	            	            //msgList.add("Items " + requisitionLineList + " could not be placed on Order! " );
	            	            this.setExtraReqLineData(reqLineList, blanket.getBuyerCode(), blanket.getDisplayPoNumber().toString(), userTimeZone);
	            	        }
	            	        else
	            	        {
	            	            PoHeader poHeader = (PoHeader)newIncomingRequest.get("poHeader");
	            	            Log.error(this, "ReleaseOrder Created." + poHeader.getDisplayPoNumber());
	            	            incomingRequest.put("autoLines", newIncomingRequest.get("autoLines"));

	            	            this.setExtraReqLineData(reqLineList, "AUTORELEASE", poHeader.getDisplayPoNumber().toString(), userTimeZone);
	            	        }
	                    }
                    }
                }
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
        return ret;
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

    public Map getGroup(Map incomingRequest)
	{
		return (Map) incomingRequest.get("groupByReq");
	}
}
