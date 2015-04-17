package com.tsa.puridiom.stdcomment.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
public class StdCommentRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from StdComment as stdcomment where 1=1 ");
		if(incomingRequest.containsKey("StdComment_commentId"))
		{			
			String commentId = (String) incomingRequest.get("StdComment_commentId");
			queryString.append(" AND stdcomment.id.commentId = '" + commentId + "'");
		}
		if(incomingRequest.containsKey("StdComment_commentTitle"))
		{			
			String commentTitle = (String) incomingRequest.get("StdComment_commentTitle");
			queryString.append(" AND stdcomment.commentTitle = '" + commentTitle + "'");
		}
		if(incomingRequest.containsKey("StdComment_commentPrint"))
		{			
			String commentPrint = (String) incomingRequest.get("StdComment_commentPrint");
			queryString.append(" AND stdcomment.commentPrint = '" + commentPrint + "'");
		}
		if(incomingRequest.containsKey("StdComment_icText"))
		{			
			String icText = (String) incomingRequest.get("StdComment_icText");
			queryString.append(" AND stdcomment.icText = '" + icText + "'");
		}
		if(incomingRequest.containsKey("StdComment_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("StdComment_dateEntered");
			queryString.append(" AND stdcomment.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("StdComment_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("StdComment_dateExpires");
			queryString.append(" AND stdcomment.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("StdComment_owner"))
		{			
			String owner = (String) incomingRequest.get("StdComment_owner");
			queryString.append(" AND stdcomment.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("StdComment_status"))
		{			
			String status = (String) incomingRequest.get("StdComment_status");
			queryString.append(" AND stdcomment.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("StdComment_commentBold"))
		{			
			String commentBold = (String) incomingRequest.get("StdComment_commentBold");
			queryString.append(" AND stdcomment.commentBold = '" + commentBold + "'");
		}
		if(incomingRequest.containsKey("StdComment_commentPublic"))
		{			
			String commentPublic = (String) incomingRequest.get("StdComment_commentPublic");
			queryString.append(" AND stdcomment.commentPublic = '" + commentPublic + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}