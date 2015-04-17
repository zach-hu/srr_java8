/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.common.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

/**
 * @author Kelli
 */
public class FormatDocNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String	documentType = (String)incomingRequest.get("AutoGen_documentType");
			String  type = HiltonUtility.ckNull((String)incomingRequest.get("AutoGen_Type"));
			String	fiscalYear = (String)incomingRequest.get("AutoGen_genYear") ;
			String	docNumber = (String)incomingRequest.get("documentNumber");

			String	o = (String) incomingRequest.get("organizationId");
			PropertiesManager properties = PropertiesManager.getInstance(o);
			String	enableFormatType = properties.getProperty("AUTONUMBER", "AUTOREQ" + type, "N").toUpperCase();
			String	format = properties.getProperty("AUTONUMBER", documentType + type + "Format", "").toUpperCase();
			if(HiltonUtility.isEmpty(format) || enableFormatType.equalsIgnoreCase("N"))
			{
				format = properties.getProperty("AUTONUMBER", documentType + "Format", "NNNNNN").toUpperCase();
			}
			String	prefixField = properties.getProperty("AUTONUMBER", documentType + "Prefix", "");
			String	separator = properties.getProperty("AUTONUMBER","docsep","");
			String	formatStr = "00000000000000000000";
			String	formatedNumber = "";
			String	prefix = "" ;
			boolean excludeSeparator = true;
			int len = format.length();

			if (prefixField == null) prefixField = "" ;
			if (prefixField.equalsIgnoreCase("locale")) {
				UserProfile up = (UserProfile) incomingRequest.get("userProfile") ;
				if (! (up == null)) {
					prefix = up.getLocale() ;
					if (prefix == null) prefix = "" ;
				}
			}
			
			if (o.equalsIgnoreCase("MSG07P"))
			{
				format = format.replaceFirst("YN", "Y" + separator + "N");
				format = format.replaceFirst("NY", "N" + separator + "Y");
				excludeSeparator = false;
			}

			int yrPos = format.indexOf("Y");
			int yrLast = format.lastIndexOf("Y") ;
			int numPos = format.indexOf("N");
			int numLast = format.lastIndexOf("N") ;
			String		yr = "" ;
			String		yrFmt = "" ;
			String		num = "" ;

			if (yrPos > -1) {
				int	l = yrLast  - yrPos + 1;
				if (l < 4) {
					yr = fiscalYear.substring(4 - l) ;
				} else {
					yr = fiscalYear ;
				}
			}

			if (numPos > -1) {
				int	l = (numLast + 1) - numPos ;
				int	fl = formatStr.length() ;
				if (l < fl) {
					String fmt = formatStr.substring(formatStr.length() - l) ;
					num = Utility.formatString(fmt, docNumber);
				} else {
					num = docNumber ;
				}
			}	else {
				num = docNumber ;
			}

			if (separator.length() > 0 && yr.length() > 0 && excludeSeparator) {
				//formatedNumber = num + separator + yr ;
				formatedNumber = format ;
				if (yrPos > -1) {
					formatedNumber = formatedNumber.replaceAll(replicate("Y",yr.length()),yr) ;
				}
				if (numPos > -1) {
					formatedNumber = formatedNumber.replaceAll(replicate("N",num.length()),num) ;
				} else {
					formatedNumber = num ;
				}
			} else {
				formatedNumber = format ;
				if (yrPos > -1) {
					formatedNumber = formatedNumber.replaceAll(replicate("Y",yr.length()),yr) ;
				}
				if (numPos > -1) {
					formatedNumber = formatedNumber.replaceAll(replicate("N",num.length()),num) ;
				} else {
					formatedNumber = num ;
				}
			}

			result = prefix + formatedNumber;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}

	private String replicate(String st, int cnt) {
		StringBuffer result = new StringBuffer("") ;
		for (int i = 1; i <= cnt; i++) {
			result.append(st) ;
		}
		return result.toString() ;
	}

}
