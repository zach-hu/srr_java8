package com.tsa.puridiom.doccomment.tasks.tests;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.doccomment.tasks.DocCommentRetrieveByLine;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.DocCommentPK;
import com.tsagate.foundation.database.DBSession;

public class DocCommentRetrieveByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    DocCommentRetrieveByLine task = new DocCommentRetrieveByLine();
			Map incomingRequest = new HashMap();
			
			DocComment docComment = new DocComment();
			DocCommentPK pk = new DocCommentPK();
			DBSession dbs = new DBSession("PURIDIOM");
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocComment_icHeader", "472290800000");
			incomingRequest.put("DocComment_icLine", "0");
			
						
			Object result = task.executeTask(incomingRequest);
			
			System.out.println("RESULT: " + result.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}