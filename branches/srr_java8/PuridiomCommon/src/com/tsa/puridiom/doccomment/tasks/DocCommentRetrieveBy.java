package com.tsa.puridiom.doccomment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class DocCommentRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from DocComment as doccomment where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		
		if(incomingRequest.containsKey("DocComment_referenceType"))
		{			
			String referenceType = (String) incomingRequest.get("DocComment_referenceType");
			args.add(referenceType);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.referenceType = ?");
		}
		if(incomingRequest.containsKey("DocComment_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("DocComment_icHeader");
			args.add(icHeader);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.id.icHeader = ?");
		}
		if(incomingRequest.containsKey("DocComment_icLine"))
		{			
			String icLine = (String) incomingRequest.get("DocComment_icLine");
			args.add(icLine);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.id.icLine = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentOrder"))
		{			
			String commentOrder = (String) incomingRequest.get("DocComment_commentOrder");
			args.add(commentOrder);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.id.commentOrder = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentId"))
		{			
			String commentId = (String) incomingRequest.get("DocComment_commentId");
			args.add(commentId);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.commentId = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentTitle"))
		{			
			String commentTitle = (String) incomingRequest.get("DocComment_commentTitle");
			args.add(commentTitle);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.commentTitle = ?");
		}
		if(incomingRequest.containsKey("DocComment_icText"))
		{			
			String icText = (String) incomingRequest.get("DocComment_icText");
			args.add(icText);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.icText = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentPrint"))
		{			
			String commentPrint = (String) incomingRequest.get("DocComment_commentPrint");
			args.add(commentPrint);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.commentPrint = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentPlace"))
		{			
			String commentPlace = (String) incomingRequest.get("DocComment_commentPlace");
			args.add(commentPlace);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.commentPlace = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentSource"))
		{			
			String commentSource = (String) incomingRequest.get("DocComment_commentSource");
			args.add(commentSource);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.commentSource = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentBold"))
		{			
			String commentBold = (String) incomingRequest.get("DocComment_commentBold");
			args.add(commentBold);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.commentBold = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentPublic"))
		{			
			String commentPublic = (String) incomingRequest.get("DocComment_commentPublic");
			args.add(commentPublic);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.commentPublic = ?");
		}
		if(incomingRequest.containsKey("DocComment_commentWebpost"))
		{			
			String commentWebpost = (String) incomingRequest.get("DocComment_commentWebpost");
			args.add(commentWebpost);
			types.add(Hibernate.STRING);
			queryString.append(" AND doccomment.commentWebpost = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}