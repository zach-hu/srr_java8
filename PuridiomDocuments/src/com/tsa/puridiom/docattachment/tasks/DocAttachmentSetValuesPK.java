package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class DocAttachmentSetValuesPK
{
	public void setValues(Map incomingRequest, DocAttachment docattachment ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("DocAttachment_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String docIcString = (String) incomingRequest.get("DocAttachment_docIc");
			BigDecimal docIc = new BigDecimal ( docIcString );
			DocAttachmentPK comp_id = new DocAttachmentPK();
			comp_id.setDocIc(docIc);
			comp_id.setIcHeader(icHeader);
			docattachment.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}