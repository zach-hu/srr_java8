/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.doccomment.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DocComment;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Administrator 
 */
public class DocCommentFilterStdComments extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        List docCommentList = (List) incomingRequest.get("docCommentList") ;
		List stdComentList = new ArrayList();
        if(docCommentList != null && docCommentList.size() > 0)
        {
        	for(int dc = 0 ; dc < docCommentList.size(); dc++)
        	{
        		DocComment docComment = (DocComment)docCommentList.get(dc);
        		String commentId = HiltonUtility.ckNull(docComment.getCommentId());
        		String commentTitle = HiltonUtility.ckNull(docComment.getCommentTitle());
        		String queryString = "from StdComment as StdComment where StdComment.commentId = ? and StdComment.commentTitle = ? ";
        		List result = dbs.query(queryString, new Object[] {commentId, commentTitle}, new Type[] {Hibernate.STRING,Hibernate.STRING}) ;
        		if(result != null && result.size() > 0)
        		{
        			stdComentList.add(docComment);
        		}
        		this.setStatus(dbs.getStatus()) ;
        	}
        }
					
		return stdComentList  ;
	}
}
