package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class XrefComboSetValuesPK
{
	public void setValues(Map incomingRequest, XrefCombo xrefCombo ) throws Exception
	{
		try
		{
			String icXrefString = (String) incomingRequest.get("XrefCombo_icXref");
			BigDecimal icXref = new BigDecimal ( icXrefString );
			xrefCombo.setIcXref(icXref);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}