package com.tsa.puridiom.stdcomment.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class StdCommentSetValuesPK
{
	public void setValues(Map incomingRequest, StdComment stdcomment ) throws Exception
	{
		try
		{
			String commentId = (String ) incomingRequest.get("StdComment_commentId");
			stdcomment.setCommentId(commentId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}