package com.tsa.puridiom.docattachment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class DocAttachmentRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from DocAttachment as docattachment where 1=1 ");
		if(incomingRequest.containsKey("DocAttachment_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("DocAttachment_icHeader");
			queryString.append(" AND docattachment.id.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("DocAttachment_docIc"))
		{			
			String docIc = (String) incomingRequest.get("DocAttachment_docIc");
			queryString.append(" AND docattachment.id.docIc = '" + docIc + "'");
		}
		if(incomingRequest.containsKey("DocAttachment_docSource"))
		{			
			String docSource = (String) incomingRequest.get("DocAttachment_docSource");
			queryString.append(" AND docattachment.docSource = '" + docSource + "'");
		}
		if(incomingRequest.containsKey("DocAttachment_docTitle"))
		{			
			String docTitle = (String) incomingRequest.get("DocAttachment_docTitle");
			queryString.append(" AND docattachment.docTitle = '" + docTitle + "'");
		}
		if(incomingRequest.containsKey("DocAttachment_docFilename"))
		{			
			String docFilename = (String) incomingRequest.get("DocAttachment_docFilename");
			queryString.append(" AND docattachment.docFilename = '" + docFilename + "'");
		}
		if(incomingRequest.containsKey("DocAttachment_docPrint"))
		{			
			String docPrint = (String) incomingRequest.get("DocAttachment_docPrint");
			queryString.append(" AND docattachment.docPrint = '" + docPrint + "'");
		}
		if(incomingRequest.containsKey("DocAttachment_docPost"))
		{			
			String docPost = (String) incomingRequest.get("DocAttachment_docPost");
			queryString.append(" AND docattachment.docPost = '" + docPost + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}