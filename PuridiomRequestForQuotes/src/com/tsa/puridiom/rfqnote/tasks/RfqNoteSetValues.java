package com.tsa.puridiom.rfqnote.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RfqNoteSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqNotePK comp_id = new RfqNotePK();
			RfqNote rfqNote = (RfqNote) incomingRequest.get("rfqNote");
			if (rfqNote == null)
			{
				rfqNote = new RfqNote();
			}

			if (incomingRequest.containsKey("RfqNote_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("RfqNote_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("RfqNote_icLine"))
			{
				String icLineString = (String) incomingRequest.get("RfqNote_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("RfqNote_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("RfqNote_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("RfqNote_notesText"))
			{
				String notesText = (String ) incomingRequest.get("RfqNote_notesText");
				rfqNote.setNotesText(notesText);
			}
			rfqNote.setComp_id(comp_id);

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