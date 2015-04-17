package com.tsa.puridiom.doccomment.tasks;

import com.tsa.puridiom.entity.StdComment;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.Map;

public class DocCommentSetValuesFromStdComment extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			StdComment stdComment = (StdComment) incomingRequest.get("stdComment");
			String	commentPublic = stdComment.getCommentPublic();
			String	icText = "";
			BigDecimal	bdText = stdComment.getIcText();
			if (bdText == null)
			{
				icText = "0";
			}
			else
			{
				icText = bdText.toString();
			}
			
			if (commentPublic.equalsIgnoreCase("Y"))
			{ 
				UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
				icText = ukg.getUniqueKey().toString();
				
				incomingRequest.put("DocComment_commentSource", "ST*");
			}
			else
			{
				incomingRequest.put("DocComment_commentSource", "STD");
			}
						
			incomingRequest.put("DocComment_icText", icText);
			incomingRequest.put("DocComment_referenceType", incomingRequest.get("Default_referenceType"));
			incomingRequest.put("DocComment_commentPlace", incomingRequest.get("Default_commentPlace"));
			incomingRequest.put("DocComment_icHeader", incomingRequest.get("DocComment_icHeader"));
			incomingRequest.put("DocComment_icLine", incomingRequest.get("DocComment_icLine"));
			incomingRequest.put("DocComment_commentOrder", "0");
			incomingRequest.put("DocComment_commentId", stdComment.getCommentId());
			incomingRequest.put("DocComment_commentTitle", stdComment.getCommentTitle());
			//incomingRequest.put("DocComment_IcText", stdComment.getIcText());
			incomingRequest.put("DocComment_commentPrint", stdComment.getCommentPrint());
			//incomingRequest.put("DocComment_commentPlace", stdComment.getCommentPlace());
			
			incomingRequest.put("DocComment_commentBold", stdComment.getCommentBold());
			incomingRequest.put("DocComment_commentPublic", stdComment.getCommentPublic());
			//incomingRequest.put("DocComment_commentWebpost", stdComment.getCommentWebpost());
			
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}