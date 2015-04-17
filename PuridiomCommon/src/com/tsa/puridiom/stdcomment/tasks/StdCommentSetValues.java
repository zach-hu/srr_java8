package com.tsa.puridiom.stdcomment.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.StdComment;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class StdCommentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			StdComment stdComment = (StdComment) incomingRequest.get("stdComment");
			if (stdComment == null)
			{
				stdComment = new StdComment();
			}

			if (incomingRequest.containsKey("StdComment_commentId"))
			{
				String commentId = (String ) incomingRequest.get("StdComment_commentId");
				stdComment.setCommentId(commentId);
			}
			if (incomingRequest.containsKey("StdComment_commentTitle"))
			{
				String commentTitle = (String ) incomingRequest.get("StdComment_commentTitle");
				stdComment.setCommentTitle(commentTitle);
			}
			if (incomingRequest.containsKey("StdComment_commentPrint"))
			{
				String commentPrint = (String ) incomingRequest.get("StdComment_commentPrint");
				if (Utility.isEmpty(commentPrint)) {
				    commentPrint = "N";
				}
				stdComment.setCommentPrint(commentPrint);
			}
			if (incomingRequest.containsKey("StdComment_icText"))
			{
				String icTextString = (String) incomingRequest.get("StdComment_icText");
				if (Utility.isEmpty(icTextString))
				{
					icTextString = "0";
				}
				BigDecimal icText = new BigDecimal ( icTextString );
				stdComment.setIcText(icText);
			}
			if (incomingRequest.containsKey("StdComment_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("StdComment_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				stdComment.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("StdComment_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("StdComment_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				stdComment.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("StdComment_owner"))
			{
				String owner = (String ) incomingRequest.get("StdComment_owner");
				stdComment.setOwner(owner);
			}
			if (incomingRequest.containsKey("StdComment_status"))
			{
				String status = (String ) incomingRequest.get("StdComment_status");
				stdComment.setStatus(status);
			}
			if (incomingRequest.containsKey("StdComment_commentBold"))
			{
				String commentBold = (String ) incomingRequest.get("StdComment_commentBold");
				if (Utility.isEmpty(commentBold)) {
				    commentBold = "N";
				}
				stdComment.setCommentBold(commentBold);
			}
			if (incomingRequest.containsKey("StdComment_commentPublic"))
			{
				String commentPublic = (String ) incomingRequest.get("StdComment_commentPublic");
				if (Utility.isEmpty(commentPublic)) {
				    commentPublic = "N";
				}
				stdComment.setCommentPublic(commentPublic);
			}

			result = stdComment;
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