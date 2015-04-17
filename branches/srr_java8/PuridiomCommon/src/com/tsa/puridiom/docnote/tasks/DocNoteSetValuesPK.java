package com.tsa.puridiom.docnote.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class DocNoteSetValuesPK
{
	public void setValues(Map incomingRequest, DocNote docnote ) throws Exception
	{
		try
		{
			String icNotesString = (String) incomingRequest.get("DocNote_icNotes");
			BigDecimal icNotes = new BigDecimal ( icNotesString );
			docnote.setIcNotes(icNotes);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}