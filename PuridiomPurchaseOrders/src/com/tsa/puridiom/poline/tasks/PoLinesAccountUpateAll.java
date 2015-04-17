/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoLinesAccountUpateAll extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            List lines = (List)incomingRequest.get("poLineList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("polines-update-accounts.xml");

			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String oid = (String)incomingRequest.get("organizationId");
			boolean closeLine = false;
			if ((oid.equalsIgnoreCase("DTN07P")) && (poHeader != null) && (poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0))
				closeLine = true;

            for(int i = 0; i < lines.size(); i++)
            {
                PoLine line = (PoLine)lines.get(i);

                if (line.getStatus().equals(DocumentStatus.CANCELLED) || (closeLine && line.getStatus().equals(DocumentStatus.CLOSED)))
				{
					incomingRequest.put("icLineCancelled", line.getIcPoLine());
				}

				incomingRequest.put("poLine", line);

				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED)
				{
				    this.setStatus(Status.FAILED);
					throw new Exception("Po Line Account Update process failed.");
				}
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);

        }
        return super.executeTask(object);
    }
}
