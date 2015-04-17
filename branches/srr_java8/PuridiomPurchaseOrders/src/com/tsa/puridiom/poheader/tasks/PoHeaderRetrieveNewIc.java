/**
 * @author kathleen
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderRetrieveNewIc extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		String ret = "";
		try 
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			if(poHeader == null)
			{
				throw new TsaException("New Po entity was not found!");
			}
			ret = poHeader.getIcPoHeader().toString();
			this.setStatus(Status.SUCCEEDED);
		} 
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}

}
