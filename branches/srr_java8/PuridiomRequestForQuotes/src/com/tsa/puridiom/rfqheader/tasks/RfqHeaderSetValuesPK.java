package com.tsa.puridiom.rfqheader.tasks;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import com.tsa.puridiom.entity.*;

public class RfqHeaderSetValuesPK
{
	public void setValues(Map incomingRequest, RfqHeader rfqheader )
	{
		String icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
		BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
		rfqheader.setIcRfqHeader(icRfqHeader);
	}
}