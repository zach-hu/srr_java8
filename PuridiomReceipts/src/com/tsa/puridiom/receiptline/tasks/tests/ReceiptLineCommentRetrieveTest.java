package com.tsa.puridiom.receiptline.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineCommentRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("doccomment-retrieve-by-line.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("DocComment_icHeader", "1119590600000");
			incomingRequest.put("DocComment_icLine", "1");
			
			process.executeProcess(incomingRequest);
			
			List list = (List) incomingRequest.get("docCommentList");
			for (int i=0; i < list.size(); i++)
			{
				DocComment docComment = (DocComment) list.get(i);
				System.out.println(docComment.getComp_id().getCommentOrder() + " - " + docComment.getCommentTitle());
			}
			
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}