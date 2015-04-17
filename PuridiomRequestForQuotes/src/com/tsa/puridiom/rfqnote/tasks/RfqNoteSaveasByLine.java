package com.tsa.puridiom.rfqnote.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class RfqNoteSaveasByLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqNote */
			RfqNotePK comp_id = new RfqNotePK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RfqNote	originalRfqNote = (RfqNote) incomingRequest.get("rfqNote");
			RfqNote	rfqNote = new RfqNote();

			comp_id.setIcHeader(new BigDecimal((String) incomingRequest.get("newRfqNote_icHeader")));
			comp_id.setIcLine(new BigDecimal((String) incomingRequest.get("newRfqNote_icLine")));
			comp_id.setVendorId(originalRfqNote.getComp_id().getVendorId());
			rfqNote.setNotesText(originalRfqNote.getNotesText());
			
			rfqNote.setComp_id(comp_id);

			incomingRequest.put("rfqNote", rfqNote);

			RfqNoteAdd addTask = new RfqNoteAdd();
			rfqNote = (RfqNote) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = rfqNote;
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