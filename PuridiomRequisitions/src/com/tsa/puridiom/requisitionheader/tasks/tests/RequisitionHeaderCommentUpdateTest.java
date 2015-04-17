package com.tsa.puridiom.requisitionheader.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionHeaderCommentUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionheader-comment-update.xml");
			Map incomingRequest = new HashMap();
			
			String	commentId[] = {"C-1","","C-3"};
			String	commentTitle[] = {"Receiving Instructions","General Requirements","No Dogs Allowed"} ;
			String	commentPrint[] = {"Y","N","Y"} ;
			String	commentPlace[] = {"A","A","B"} ;
			String	commentSource[] = {"STD","ST*",""} ;
			String	commentBold[] = {"N","N","Y"} ;
			String	commentPublic[] = {"Y","Y","Y"} ;
			String	commentWebpost[] = {"N","N","Y"} ;
			String	commentIcText[] = {"55","56","0"} ;
			String 	textStdText[] = {"this is a test", "Coment text","Please no dogs"} ;
			
			incomingRequest.put("DocComment_icHeader", "549101601000");
			incomingRequest.put("DocComment_icLine", "0");
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