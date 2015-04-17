package com.tsa.puridiom.doccomment.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.doccomment.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.math.BigDecimal;
import java.util.*;

public class DocCommentAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DocCommentAdd task = new DocCommentAdd();
			Map incomingRequest = new HashMap();
			
			DocComment docComment = new DocComment();
			DocCommentPK pk = new DocCommentPK();
			DBSession dbs = new DBSession("PURIDIOM");
			
			dbs.startTransaction();
			
			pk.setIcHeader(new BigDecimal("478808700000")) ;
			pk.setIcLine(new BigDecimal("0"));
			pk.setCommentOrder(new BigDecimal("1"));
			docComment.setComp_id(pk);
			docComment.setReferenceType("RQH") ;
			docComment.setCommentTitle("COMMENT TEST") ;
			docComment.setIcText(new BigDecimal("1"));
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("docComment", docComment);
						
			Object result = task.executeTask(incomingRequest);
			
			if (task.getStatus() == Status.SUCCEEDED)
			{
				dbs = (DBSession) incomingRequest.get("dbsession");
				
				dbs.commit();
				dbs.close();				
			}
			
			System.out.println("RESULT: " + result.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}