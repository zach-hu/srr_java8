package com.tsa.puridiom.stdcomment.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.StdComment;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class StdCommentSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain stdComment */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			StdComment	originalStdComment = (StdComment) incomingRequest.get("stdComment");
			StdComment	stdComment = new StdComment();

			stdComment.setCommentId(originalStdComment.getCommentId());
			stdComment.setCommentTitle(originalStdComment.getCommentTitle());
			stdComment.setCommentPrint(originalStdComment.getCommentPrint());
			stdComment.setIcText(originalStdComment.getIcText());
			stdComment.setDateEntered(originalStdComment.getDateEntered());
			stdComment.setDateExpires(originalStdComment.getDateExpires());
			stdComment.setOwner(originalStdComment.getOwner());
			stdComment.setStatus(originalStdComment.getStatus());
			stdComment.setCommentBold(originalStdComment.getCommentBold());
			stdComment.setCommentPublic(originalStdComment.getCommentPublic());

			incomingRequest.put("stdComment", stdComment);

			StdCommentAdd addTask = new StdCommentAdd();
			stdComment = (StdComment) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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