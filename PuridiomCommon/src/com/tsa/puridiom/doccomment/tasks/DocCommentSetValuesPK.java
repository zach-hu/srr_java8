package com.tsa.puridiom.doccomment.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class DocCommentSetValuesPK
{
	public void setValues(Map incomingRequest, DocComment doccomment ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("DocComment_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("DocComment_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String commentOrderString = (String) incomingRequest.get("DocComment_commentOrder");
			BigDecimal commentOrder = new BigDecimal ( commentOrderString );
			DocCommentPK comp_id = new DocCommentPK();
			comp_id.setCommentOrder(commentOrder);
			comp_id.setIcHeader(icHeader);
			comp_id.setIcLine(icLine);
			doccomment.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}