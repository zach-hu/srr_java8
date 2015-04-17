package com.tsa.puridiom.docnote.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class DocNoteSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DocNote docNote = (DocNote) incomingRequest.get("docNote");
			if (docNote == null)
			{
				docNote = new DocNote();
			}

			if (incomingRequest.containsKey("DocNote_icNotes"))
			{
				String icNotesString = (String) incomingRequest.get("DocNote_icNotes");
				if (Utility.isEmpty(icNotesString))
				{
					icNotesString = "0";
				}
				BigDecimal icNotes = new BigDecimal ( icNotesString );
				docNote.setIcNotes(icNotes);
			}
			if (incomingRequest.containsKey("DocNote_notes"))
			{
				String notes = (String ) incomingRequest.get("DocNote_notes");
				docNote.setNotes(notes);
			}
			if (incomingRequest.containsKey("DocNote_referenceType"))
			{
				String referenceType = (String ) incomingRequest.get("DocNote_referenceType");
				docNote.setReferenceType(referenceType);
			}
			if (incomingRequest.containsKey("DocNote_idReference"))
			{
				String idReference = (String ) incomingRequest.get("DocNote_idReference");
				docNote.setIdReference(idReference);
			}

			result = docNote;
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