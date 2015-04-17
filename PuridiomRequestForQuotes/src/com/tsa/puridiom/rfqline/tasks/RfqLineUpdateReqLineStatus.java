/*
 * Created on Sep 30, 2004
 *
 * @author  * Kelli
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks.PoLineUpdateReqStatus.java
 *
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RfqType;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RfqLineUpdateReqLineStatus extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
		{
            Map incomingRequest = (Map)object;

            RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");

            if (incomingRequest.containsKey("rfqLine"))
            {
                RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
                if (rfqLine != null)
                {
					String	icReqLine = "";

					if (rfqLine.getIcReqLine().compareTo(new BigDecimal(0)) > 0) {
					    icReqLine = String.valueOf(rfqLine.getIcReqLine());
					}

					if (!Utility.isEmpty(icReqLine)) {
	                    String organizationId = (String) incomingRequest.get("organizationId");
	                    String userId = (String) incomingRequest.get("userId");
	                    DBSession dbsession = (DBSession) incomingRequest.get("dbsession");

	                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("requisitionline-update-status.xml");
						Map requestParameters = new HashMap();

						String status = rfqLine.getStatus();
						String	rfqType = rfqHeader.getRfqType();

						if (status == DocumentStatus.CANCELLED || status == DocumentStatus.DELETE_INPROGRESS) {
						    if (rfqType.equals(RfqType.PRICING)) {
						        status = DocumentStatus.REQ_FORWARDED;
						    } else {
						        status = DocumentStatus.REQ_APPROVED;
						    }
						}

						requestParameters.put("organizationId",organizationId);
						requestParameters.put("userId",userId);
						requestParameters.put("dbsession", dbsession);
						requestParameters.put("RequisitionLine_icReqLine", String.valueOf(icReqLine));
						requestParameters.put("RequisitionLine_status", status);

						if (incomingRequest.containsKey("poLineList")) {
		                	PoLine poLine = getPoLine(rfqLine.getIcRfqLine(), (List) incomingRequest.get("poLineList"));
		                	if (poLine != null) {
		                		requestParameters.put("PoLine", poLine);
		                	}
		                }

						process.executeProcess(requestParameters);

						if (process.getStatus() != Status.SUCCEEDED) {
						    throw new Exception("An error ocurred updating the requisition line status for rfq line #" + rfqLine.getRfqLine());
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

    public PoLine getPoLine (BigDecimal icRfqLine, List poLineList) throws Exception
	{
		PoLine result = null;
		try
		{
			for(int i=0;i<poLineList.size();i++){

				PoLine poLine = (PoLine) poLineList.get(i);
				if (poLine.getIcRfqLine()!= null && poLine.getIcRfqLine().compareTo(icRfqLine) == 0) {
					result = poLine;
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
