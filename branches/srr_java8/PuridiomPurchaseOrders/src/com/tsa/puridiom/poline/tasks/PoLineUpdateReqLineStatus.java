/*
 * Created on Sep 30, 2004
 *
 * @author  * renzo / Kelli
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks.PoLineUpdateReqLineStatus.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PoLineUpdateReqLineStatus extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;

            if (incomingRequest.containsKey("poLine"))
            {
                PoLine poLine = (PoLine) incomingRequest.get("poLine");

                if (poLine != null)
                {
                    String organizationId = (String) incomingRequest.get("organizationId");
                    String userId = (String) incomingRequest.get("userId");
                    DBSession dbsession = (DBSession) incomingRequest.get("dbsession");

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("requisitionline-update-status.xml");
                    Map requestParameters = new HashMap();

                    String status = poLine.getStatus();
                    BigDecimal	icReqLine = poLine.getIcReqLine();

                    if (icReqLine != null && icReqLine.compareTo(new BigDecimal(0)) > 0)
                    {
                        if (status.equals(DocumentStatus.CANCELLED) || status.equals(DocumentStatus.DELETE_INPROGRESS))
                        {
                        	if(organizationId.equalsIgnoreCase("DTN07P"))
                        	{
                        		status = DocumentStatus.REQ_APPROVED;
                        	}
                        	else
                        	{
                        		status = DocumentStatus.CANCELLED;
                        	}
                        	if( incomingRequest.containsKey("delete_option") &&  !Utility.isEmpty( (String) incomingRequest.get("delete_option") ) )
                        	{
                        		status = (String) incomingRequest.get("delete_option");
                        	}
                        } else if (status.equals(DocumentStatus.PO_APPROVING) || status.equals(DocumentStatus.PO_REJECTED)) {
                            status = DocumentStatus.PO_INPROGRESS;
                        } else if (status.equals(DocumentStatus.CT_APPROVING) || status.equals(DocumentStatus.CT_REJECTED)) {
                            status = DocumentStatus.CT_INPROGRESS;
                        }

                        requestParameters.put("organizationId",organizationId);
                        requestParameters.put("userId",userId);
                        requestParameters.put("userTimeZone",(String) incomingRequest.get("userTimeZone"));
                        requestParameters.put("dbsession", dbsession);
                        requestParameters.put("RequisitionLine_icReqLine", icReqLine.toString());
                        requestParameters.put("RequisitionLine_icPoLine", poLine.getIcPoLine().toString());
                        requestParameters.put("RequisitionLine_status", status);

                        requestParameters.put("PoLine", poLine);
                        PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
                        if(poHeader != null)
                        {
                        	requestParameters.put("RequisitionLine_blanketOrder", poHeader.getDisplayPoNumber(false).toString());
                        }

                        Log.debug(this, "Updating Requisition Status from Po " + poLine.getPoNumber() + " line status " + status);

                        process.executeProcess(requestParameters);

                        if (process.getStatus() != Status.SUCCEEDED) {
                            throw new Exception("An error ocurred updating the requisition line status for po line #" + poLine.getLineNumber());
                        }
                    }
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
