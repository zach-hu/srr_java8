package com.tsa.puridiom.rfqnote.tasks;

import com.tsa.puridiom.entity.RfqNote;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class RfqNoteSaveasListByLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqNoteList */
			List	rfqNoteList = (List) incomingRequest.get("rfqNoteList");
			
			for (int i=0; i < rfqNoteList.size(); i++)
			{
				incomingRequest.put("rfqNote", (RfqNote) rfqNoteList.get(i));
	
				RfqNoteSaveasByLine saveasTask = new RfqNoteSaveasByLine();
				RfqNote	rfqNote = (RfqNote) saveasTask.executeTask(incomingRequest);
				
				if (saveasTask.getStatus() != Status.SUCCEEDED)
				{
					throw new Exception("RfqNoteSaveas failed.");
				}
				incomingRequest.remove("rfqNote");
				rfqNoteList.set(i, rfqNote);
			}
			
			result = rfqNoteList;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}