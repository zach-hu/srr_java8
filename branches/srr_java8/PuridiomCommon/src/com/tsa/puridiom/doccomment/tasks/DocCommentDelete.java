package com.tsa.puridiom.doccomment.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class DocCommentDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		DocComment docComment = (DocComment)incomingRequest.get("docComment");
		if(docComment == null)
		{
			docComment = new DocComment();
		}
		DocCommentSetValuesPK setValues = new DocCommentSetValuesPK();
		setValues.setValues(incomingRequest, docComment);
		dbs.delete(docComment);
		this.setStatus(dbs.getStatus()) ;
		return docComment ;
	}

}