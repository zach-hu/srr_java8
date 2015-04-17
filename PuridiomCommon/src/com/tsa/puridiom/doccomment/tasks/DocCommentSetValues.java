package com.tsa.puridiom.doccomment.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class DocCommentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DocCommentPK comp_id = new DocCommentPK();
			DocComment docComment = (DocComment) incomingRequest.get("docComment");
			if (docComment == null)
			{
				docComment = new DocComment();
			}

			if (incomingRequest.containsKey("DocComment_referenceType"))
			{
				String referenceType = (String ) incomingRequest.get("DocComment_referenceType");
				docComment.setReferenceType(referenceType);
			}
			if (incomingRequest.containsKey("DocComment_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("DocComment_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("DocComment_icLine"))
			{
				String icLineString = (String) incomingRequest.get("DocComment_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("DocComment_commentOrder"))
			{
				String commentOrderString = (String) incomingRequest.get("DocComment_commentOrder");
				if (Utility.isEmpty(commentOrderString))
				{
					commentOrderString = "0";
				}
				BigDecimal commentOrder = new BigDecimal ( commentOrderString );
				comp_id.setCommentOrder(commentOrder);
			}
			if (incomingRequest.containsKey("DocComment_commentId"))
			{
				String commentId = (String ) incomingRequest.get("DocComment_commentId");
				docComment.setCommentId(commentId);
			}
			if (incomingRequest.containsKey("DocComment_commentTitle"))
			{
				String commentTitle = (String ) incomingRequest.get("DocComment_commentTitle");
				docComment.setCommentTitle(commentTitle);
			}
			if (incomingRequest.containsKey("DocComment_icText"))
			{
				String icTextString = (String) incomingRequest.get("DocComment_icText");
				if (Utility.isEmpty(icTextString))
				{
					icTextString = "0";
				}
				BigDecimal icText = new BigDecimal ( icTextString );
				docComment.setIcText(icText);
			}
			if (incomingRequest.containsKey("DocComment_commentPrint"))
			{
				String commentPrint = (String ) incomingRequest.get("DocComment_commentPrint");
				docComment.setCommentPrint(commentPrint);
			}
			if (incomingRequest.containsKey("DocComment_commentPlace"))
			{
				String commentPlace = (String ) incomingRequest.get("DocComment_commentPlace");
				docComment.setCommentPlace(commentPlace);
			}
			if (incomingRequest.containsKey("DocComment_commentSource"))
			{
				String commentSource = (String ) incomingRequest.get("DocComment_commentSource");
				docComment.setCommentSource(commentSource);
			}
			if (incomingRequest.containsKey("DocComment_commentBold"))
			{
				String commentBold = (String ) incomingRequest.get("DocComment_commentBold");
				docComment.setCommentBold(commentBold);
			}
			if (incomingRequest.containsKey("DocComment_commentPublic"))
			{
				String commentPublic = (String ) incomingRequest.get("DocComment_commentPublic");
				docComment.setCommentPublic(commentPublic);
			}
			if (incomingRequest.containsKey("DocComment_commentWebpost"))
			{
				String commentWebpost = (String ) incomingRequest.get("DocComment_commentWebpost");
				docComment.setCommentWebpost(commentWebpost);
			}
			docComment.setComp_id(comp_id);

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