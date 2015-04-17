package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class XrefComboSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			XrefCombo xrefCombo = (XrefCombo) incomingRequest.get("xrefCombo");
			if (xrefCombo == null)
			{
				xrefCombo = new XrefCombo();
			}

			if (incomingRequest.containsKey("XrefCombo_icXref"))
			{
				String icXrefString = (String) incomingRequest.get("XrefCombo_icXref");
				if (Utility.isEmpty(icXrefString))
				{
					icXrefString = "0";
				}
				BigDecimal icXref = new BigDecimal ( icXrefString );
				xrefCombo.setIcXref(icXref);
			}
			if (incomingRequest.containsKey("XrefCombo_xrefType"))
			{
				String xrefType = (String) incomingRequest.get("XrefCombo_xrefType");
				xrefCombo.setXrefType(xrefType);
			}
			if (incomingRequest.containsKey("XrefCombo_code1"))
			{
				String code1 = (String) incomingRequest.get("XrefCombo_code1");
				xrefCombo.setCode1(code1);
			}
			if (incomingRequest.containsKey("XrefCombo_code2"))
			{
				String code2 = (String) incomingRequest.get("XrefCombo_code2");
				xrefCombo.setCode2(code2);
			}
			if (incomingRequest.containsKey("XrefCombo_code3"))
			{
				String code3 = (String) incomingRequest.get("XrefCombo_code3");
				xrefCombo.setCode3(code3);
			}
			if (incomingRequest.containsKey("XrefCombo_code4"))
			{
				String code4 = (String) incomingRequest.get("XrefCombo_code4");
				xrefCombo.setCode4(code4);
			}
			if (incomingRequest.containsKey("XrefCombo_code5"))
			{
				String code5 = (String) incomingRequest.get("XrefCombo_code5");
				xrefCombo.setCode5(code5);
			}
			if (incomingRequest.containsKey("XrefCombo_code6"))
			{
				String code6 = (String) incomingRequest.get("XrefCombo_code6");
				xrefCombo.setCode6(code6);
			}
			if (incomingRequest.containsKey("XrefCombo_code7"))
			{
				String code7 = (String) incomingRequest.get("XrefCombo_code7");
				xrefCombo.setCode7(code7);
			}
			if (incomingRequest.containsKey("XrefCombo_code8"))
			{
				String code8 = (String) incomingRequest.get("XrefCombo_code8");
				xrefCombo.setCode8(code8);
			}
			if (incomingRequest.containsKey("XrefCombo_code9"))
			{
				String code9 = (String) incomingRequest.get("XrefCombo_code9");
				xrefCombo.setCode9(code9);
			}
			if (incomingRequest.containsKey("XrefCombo_code10"))
			{
				String code10 = (String) incomingRequest.get("XrefCombo_code10");
				xrefCombo.setCode10(code10);
			}
			if (incomingRequest.containsKey("XrefCombo_code11"))
			{
				String code11 = (String) incomingRequest.get("XrefCombo_code11");
				xrefCombo.setCode11(code11);
			}
			if (incomingRequest.containsKey("XrefCombo_code12"))
			{
				String code12 = (String) incomingRequest.get("XrefCombo_code12");
				xrefCombo.setCode12(code12);
			}
			if (incomingRequest.containsKey("XrefCombo_code13"))
			{
				String code13 = (String) incomingRequest.get("XrefCombo_code13");
				xrefCombo.setCode13(code13);
			}
			if (incomingRequest.containsKey("XrefCombo_code14"))
			{
				String code14 = (String) incomingRequest.get("XrefCombo_code14");
				xrefCombo.setCode12(code14);
			}
			if (incomingRequest.containsKey("XrefCombo_code15"))
			{
				String code15 = (String) incomingRequest.get("XrefCombo_code15");
				xrefCombo.setCode15(code15);
			}
			if (incomingRequest.containsKey("XrefCombo_xrefInd"))
			{
				String xrefIndString = (String) incomingRequest.get("XrefCombo_xrefInd");
				if (Utility.isEmpty(xrefIndString))
				{
					xrefIndString = "0";
				}
				BigDecimal xrefInd = new BigDecimal ( xrefIndString );
				xrefCombo.setXrefInd(xrefInd);
			}
			if (incomingRequest.containsKey("XrefCombo_xrefAmt"))
			{
				String xrefAmtString = (String) incomingRequest.get("XrefCombo_xrefAmt");
				if (Utility.isEmpty(xrefAmtString))
				{
					xrefAmtString = "0";
				}
				BigDecimal xrefAmt = new BigDecimal ( xrefAmtString );
				xrefCombo.setXrefAmt(xrefAmt);
			}

			if (incomingRequest.containsKey("XrefCombo_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("XrefCombo_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				xrefCombo.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("XrefCombo_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("XrefCombo_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				xrefCombo.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("XrefCombo_status"))
			{
				String status = (String) incomingRequest.get("XrefCombo_status");
				xrefCombo.setStatus(status);
			}
			if (incomingRequest.containsKey("XrefCombo_owner"))
			{
				String owner = (String) incomingRequest.get("XrefCombo_owner");
				xrefCombo.setOwner(owner);
			}

			result = xrefCombo;
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