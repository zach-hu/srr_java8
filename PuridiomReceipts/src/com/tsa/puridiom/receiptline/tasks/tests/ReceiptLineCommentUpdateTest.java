package com.tsa.puridiom.receiptline.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineCommentUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("receiptline-comment-update.xml");
			Map incomingRequest = new HashMap();
			
			String	commentOrder[] = {"1","2","3"} ;
			String	commentId[] = {"C-1","",""};
			String	commentTitle[] = {"Receiving Instructions - update","General Requirements - update","Return Policy"} ;
			String	commentPrint[] = {"Y","N","Y"} ;
			String	commentPlace[] = {"A","A","A"} ;
			String	commentSource[] = {"STD","ST*","ST*"} ;
			String	commentBold[] = {"N","N","N"} ;
			String	commentPublic[] = {"Y","Y","Y"} ;
			String	commentWebpost[] = {"N","N","N"} ;
			String	commentIcText[] = {"","",""} ;
			String 	textStdText[] = {"this is a test", "Comment text", "Return policy comment text"} ;

			incomingRequest.put("DocComment_icHeader", "1119590600000");
			incomingRequest.put("DocComment_icLine", "2");
			incomingRequest.put("DocComment_commentOrder",commentOrder) ;
			incomingRequest.put("DocComment_commentId",commentId) ;
			incomingRequest.put("DocComment_commentTitle",commentTitle) ;
			incomingRequest.put("DocComment_commentPrint",commentPrint) ;
			incomingRequest.put("DocComment_commentBold",commentBold) ;
			incomingRequest.put("DocComment_commentPublic",commentPublic) ;
			incomingRequest.put("DocComment_commentPlace",commentPlace) ;
			incomingRequest.put("DocComment_commentWebpost",commentWebpost) ;
			incomingRequest.put("DocComment_commentSource",commentSource) ;
			incomingRequest.put("DocComment_icText",commentIcText) ;
			incomingRequest.put("DocText_stdText",textStdText) ;
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}