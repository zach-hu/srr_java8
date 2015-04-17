/**
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Johnny
 */
public class PoHeaderGetLinesWithQuantity extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			List poLineList = new ArrayList();

			for (Iterator iterator = poHeader.getPoLineList().iterator(); iterator.hasNext();)
			{
				PoLine poLine = (PoLine) iterator.next();

				if (poLine.getQuantity().compareTo(new BigDecimal(0)) != 0)
				{
					poLineList.add(poLine);
				}
			}

			poHeader.setPoLineList(poLineList);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return null;
	}
}
