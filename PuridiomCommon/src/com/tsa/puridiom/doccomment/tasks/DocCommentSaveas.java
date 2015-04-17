package com.tsa.puridiom.doccomment.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.math.BigDecimal;
import java.util.*;

public class DocCommentSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain docComment, newDocComment_icHeader, 
			 * 	newDocComment_icLine, and newDocComment_icText */
			DocCommentPK comp_id = new DocCommentPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			DocComment	originalDocComment = (DocComment) incomingRequest.get("docComment");
			DocComment	docComment = new DocComment();
			String	icHeader = (String) incomingRequest.get("newDocComment_icHeader");
			String	icLine = (String) incomingRequest.get("newDocComment_icLine");
			String	icText = (String) incomingRequest.get("newDocComment_icText");
			String	referenceType = (String) incomingRequest.get("newDocComment_referenceType");

			if (!Utility.isEmpty(referenceType))
			{
				docComment.setReferenceType(referenceType);
			}
			else
			{
				docComment.setReferenceType(originalDocComment.getReferenceType());
			}
			comp_id.setIcHeader(new BigDecimal(icHeader));
			comp_id.setIcLine(new BigDecimal(icLine));
			comp_id.setCommentOrder(originalDocComment.getComp_id().getCommentOrder());
			docComment.setCommentId(originalDocComment.getCommentId());
			docComment.setCommentTitle(originalDocComment.getCommentTitle());
			docComment.setIcText(new BigDecimal(icText));
			docComment.setCommentPrint(originalDocComment.getCommentPrint());
			docComment.setCommentPlace(originalDocComment.getCommentPlace());
			docComment.setCommentSource(originalDocComment.getCommentSource());
			docComment.setCommentBold(originalDocComment.getCommentBold());
			docComment.setCommentPublic(originalDocComment.getCommentPublic());
			docComment.setCommentWebpost(originalDocComment.getCommentWebpost());
			docComment.setComp_id(comp_id);

			incomingRequest.put("docComment", docComment);

			DocCommentAdd addTask = new DocCommentAdd();
			docComment = (DocComment) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = docComment;
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