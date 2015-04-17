package com.tsa.puridiom.doctext.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.*;

public class DocTextDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			DocText docText = (DocText)incomingRequest.get("docText");
			if(docText == null)
			{
				//this.setStatus(Status.FAILED);
				//throw new TsaException("Comment Entity was not found!");
				docText = new DocText();
			}
			DocTextSetValuesPK setValues = new DocTextSetValuesPK();
			setValues.setValues(incomingRequest, docText);
			dbs.delete(docText);
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}