package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqHeaderCommentUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqHeaderCommentUpdateHandler test = new RfqHeaderCommentUpdateHandler();
			Map incomingRequest = new HashMap();
			
			String	commentOrder[] = {"1","2"} ;
			String	commentId[] = {"C-1",""};
			String	commentTitle[] = {"Receiving Instructions - test","General Requirements - test"} ;
			String	commentPrint[] = {"Y","N"} ;
			String	commentPlace[] = {"A","A"} ;
			String	commentSource[] = {"STD","ST*"} ;
			String	commentBold[] = {"N","N"} ;
			String	commentPublic[] = {"Y","Y"} ;
			String	commentWebpost[] = {"N","N"} ;
			String	commentIcText[] = {"784040400000","784040400001"} ;
			String 	textStdText[] = {"this is a test", "Coment text"} ;
		
			incomingRequest.put("organizationId","PURIDIOM");;
			incomingRequest.put("userId","KELLI");	
			incomingRequest.put("DocComment_icHeader", "756864200000");
			incomingRequest.put("DocComment_icLine", "0");
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
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqCommentUpdateHeader - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}