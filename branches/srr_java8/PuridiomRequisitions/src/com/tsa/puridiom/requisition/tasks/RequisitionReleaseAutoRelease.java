/*
 * Created on Mar 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionReleaseAutoRelease extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            boolean createReleaseFromReq = ((String)incomingRequest.get("createReleaseFromReq")).equals(String.valueOf(true));
            if (incomingRequest.containsKey("createReleaseFromReqAssignedToMe")) {
            	createReleaseFromReq = ((String)incomingRequest.get("createReleaseFromReqAssignedToMe")).equals("true");
            }

            List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
            List msgList = new ArrayList();

            if(requisitionLineList.size() > 0)
            {//items to create release for
                String recalculate = (String)incomingRequest.get("recalculate");
                if(recalculate == null){		recalculate = "N";		}
                List newReleases = new ArrayList();
                RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
                PoHeader blanket = (PoHeader)incomingRequest.get("blanket");
                Log.debug(this, "Release Request AutoRelease for req: " + rqh.getRequisitionNumber());
                Log.debug(this, "Release Request AutoRelease for blanket: " + blanket.getPoNumber());
                Log.debug(this, "AutoRelease recalculate: " + recalculate);

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("blanket", blanket);
                newIncomingRequest.put("requisitionHeader", rqh);
                newIncomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

                newIncomingRequest.put("PoHeader_icPoHeader", blanket.getIcPoHeader().toString());

                newIncomingRequest.put("requisitionLineList", requisitionLineList);
                newIncomingRequest.put("createReleaseFromReq", "true");
                newIncomingRequest.put("Vendor_vendorId", blanket.getVendorId());
                newIncomingRequest.put("recalculate", recalculate);
                newIncomingRequest.put("createdfrom", this.createdFrom());

                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("createReleaseFromReq", new Boolean(createReleaseFromReq));
                newIncomingRequest.put("forwardallowed", new Boolean(createReleaseFromReq));
                newIncomingRequest.put("PoHeader_poType", incomingRequest.get("PoHeader_poType"));

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();

                PuridiomProcess process = processLoader.loadProcess("po-create-release-partial.xml");
                process.executeProcess(newIncomingRequest);
                if(process.getStatus() != Status.SUCCEEDED)
                {
                    msgList.add("Items " + requisitionLineList + " could not be placed on Order! " );
                }
                else
                {
                    PoHeader poHeader = (PoHeader)newIncomingRequest.get("poHeader");
                    //msgList.add("Items " + requisitionLineList + " where Released on " + OrderType.RELEASE_ORDER +
                       //                 "# " + poHeader.getPoNumber() + "-" + poHeader.getReleaseNumber().toString());
                    newReleases.add(poHeader);
                    AutoReleasedItemsSummary autoReleasedItems = new AutoReleasedItemsSummary(rqh, requisitionLineList, poHeader);
                    autoReleasedItems.setPoNumber(poHeader.getPoNumber());
                    autoReleasedItems.setReleaseNumber(poHeader.getReleaseNumber());
                    //autoReleasedItems.setRqSubType(rqh.getRqSubType());
                    autoReleasedItems.setRequisitionNumber(rqh.getRequisitionNumber());
                    autoReleasedItems.setReleaseOrder(poHeader);
                    msgList.add(autoReleasedItems);
                    incomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());
                }

                incomingRequest.put("releaseMessages", msgList);
                incomingRequest.put("poFromRelease", "Y");
                incomingRequest.put("releases", newReleases);

            }
            else
            {
                //rest of items should go through normal process.?????????
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return super.executeTask(object);
    }

    public String createdFrom()
    {
        return "REQ";
    }
}
