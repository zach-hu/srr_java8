/*
 * Created on Nov 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.poline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoLineRetrieveAll extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Object result ;
        try
        {
            Map incomingRequest = (Map)object;
            List poLines = (List)incomingRequest.get("poLineList");
            
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("polinedata-retrieve.xml");
			
			
            for(int i = 0; i < poLines.size(); i++)
            {
                PoLine poLine = (PoLine)poLines.get(i);
                Map arguments = new HashMap();
                Map updateParameters = new HashMap();
                updateParameters.put("poHeader", incomingRequest.get("poHeader"));
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("poLine", poLine);
				
				process.executeProcess(updateParameters);
				this.setStatus(process.getStatus());
				if (process.getStatus() != Status.SUCCEEDED)
				{
					throw new TsaException("Po Line save as process failed.");
				}
				else
				{
				    poLine = (PoLine)updateParameters.get("poLine");
				    poLines.set(i, poLine);
				}
			}
			result = poLines;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
    }
}
