package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RequisitionRetrieveHandler test = new RequisitionRetrieveHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionHeader_icReqHeader", "478808700000");
			incomingRequest.put("successPage", "SUCCESS PAGE");
			incomingRequest.put("failurePage", "FAILURE PAGE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RequisitionRetrieve - SUCCESS");
			}
			if (incomingRequest.containsKey("requisitionHeader"))
			{
				RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
				System.out.println(reqHeader.toString());
				if (reqHeader != null)
				{
					List comments = reqHeader.getDocCommentList();
					if (comments != null)
					{
						for (int i = 0; i < comments.size(); i++)
						{
							DocComment comment = (DocComment) comments.get(i);
							DocText text = comment.getDocText();
							System.out.println(comment.getCommentTitle() + " - " + text.getStdText());
						}
					}
					List list = reqHeader.getRequisitionLineList();
					if (list != null)
					{
						for (int i = 0; i < list.size(); i++)
						{
							RequisitionLine reqLine = (RequisitionLine) list.get(i);
							System.out.println(reqLine.toString());
							comments = reqLine.getDocCommentList();
							if (comments != null)
							{
								for (int ic = 0; ic < comments.size(); ic++)
								{
									DocComment comment = (DocComment) comments.get(ic);
									DocText text = comment.getDocText();
									System.out.println(comment.getCommentTitle() + " - " + text.getStdText());
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}