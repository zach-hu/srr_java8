package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.*;

public class DocAttachmentSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain docAttachment, newDocAttachment_icHeader, and newDocAttachment_docFilename */
			DocAttachmentPK comp_id = new DocAttachmentPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			DocAttachment	originalDocAttachment = (DocAttachment) incomingRequest.get("docAttachment");
			DocAttachment	docAttachment = new DocAttachment();
			String	icHeader = (String) incomingRequest.get("newDocAttachment_icHeader");
			String	icLine = (String) incomingRequest.get("newDocAttachment_icLine");
			if(HiltonUtility.isEmpty(icLine)){
				icLine = "0";
			}
			String	docFilename = (String) incomingRequest.get("newDocAttachment_docFilename");
			String	docSource = (String) incomingRequest.get("newDocAttachment_docSource");
			
			if (Utility.isEmpty(docSource)) {
			    docSource = originalDocAttachment.getDocSource();
			}
			
			comp_id.setIcHeader(new BigDecimal(icHeader));
			comp_id.setIcLine(new BigDecimal(icLine));
			comp_id.setDocIc(new BigDecimal(ukg.getUniqueKey().toString()));
			docAttachment.setDocSource(docSource);
			docAttachment.setDocTitle(originalDocAttachment.getDocTitle());
			docAttachment.setDocFilename(docFilename);
			docAttachment.setDocPrint(originalDocAttachment.getDocPrint());
			docAttachment.setDocPost(originalDocAttachment.getDocPost());
			docAttachment.setComp_id(comp_id);

			incomingRequest.put("docAttachment", docAttachment);

			DocAttachmentAdd addTask = new DocAttachmentAdd();
			docAttachment = (DocAttachment) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = docAttachment;
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