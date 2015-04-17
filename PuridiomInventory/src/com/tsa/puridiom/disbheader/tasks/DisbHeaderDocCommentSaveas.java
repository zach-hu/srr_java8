/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.disbheader.tasks;

import com.tsa.puridiom.doccomment.tasks.DocCommentSaveas;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

/**
 * @author Jeff / Kelli
 */
public class DisbHeaderDocCommentSaveas extends Task
{
	
	/**
	 * executeTask
	 * <p>This method takes a list of comments coming from incoming request(docCommentList)</p>
	 * <p> and calls DocCommentSaveas for each one of them.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List docCommentList = (List)incomingRequest.get("docCommentList");
		DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");

		for (int i = 0; i < docCommentList.size(); i++)
		{
			DocComment docComment = (DocComment) docCommentList.get(i);

			incomingRequest.put("docComment", docComment);
			incomingRequest.put("newDocComment_icHeader", disbHeader.getIcDsbHeader().toString());
			incomingRequest.put("newDocComment_icLine", "0");
			
			DocCommentSaveas saveas = new DocCommentSaveas();
			saveas.executeTask(incomingRequest);
			
			if (saveas.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("DocComment save as failed.");
			}
			
			docCommentList.set(i, docComment);
			this.setStatus(Status.SUCCEEDED);
		}
		if (docCommentList.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return docCommentList;
	}

}
