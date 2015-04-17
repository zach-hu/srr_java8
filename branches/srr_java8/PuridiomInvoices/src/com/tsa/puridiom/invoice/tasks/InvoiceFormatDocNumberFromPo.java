/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.invoice.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

/**
 * @author Kelli
 */
public class InvoiceFormatDocNumberFromPo extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String	documentType = (String)incomingRequest.get("AutoGen_documentType");
			String	poNumber = (String)incomingRequest.get("PoHeader_poNumber");
			String  invoiceNumber = (String)incomingRequest.get("InvoiceHeader_invoiceNumber");
			PoHeader  poHeader = (PoHeader)incomingRequest.get("poHeader");

			String	oid = (String) incomingRequest.get("organizationId");
			String userTimeZone = (String)incomingRequest.get("userTimeZone");
			PropertiesManager properties = PropertiesManager.getInstance(oid);
			String	format = properties.getProperty("AUTONUMBER", documentType + "Format", "NNNNNN").toUpperCase();
			String	poformat = properties.getProperty("AUTONUMBER", "PoFormat", "NNNNNN").toUpperCase();
			String begin = properties.getProperty("Misc", "fybegin", "1");

			if(HiltonUtility.isEmpty(userTimeZone))	userTimeZone = properties.getProperty("USER DEFAULTS", "TIMEZONE", "EST");
			
			String	formatedNumber = "";

			int poYrPos 	= poformat.indexOf("Y");
			int poYrLast 	= poformat.lastIndexOf("Y") ;
			int poNumPos 	= poformat.indexOf("N");
			int poNumLast 	= poformat.lastIndexOf("N") ;
			int hyphen 		= poformat.indexOf("-");
			int numPoPos 	= format.indexOf("O");
			int numPoLast 	= format.lastIndexOf("O") ;
			int numPos 		= format.indexOf("N");
			int numLast 	= format.lastIndexOf("N") ;
			String poYr 	= "" ;
			String poNum 	= "" ;
			String poNumberString = "" ;
			String invNumberString = "" ;

			int poNumberHyphen = poNumber.indexOf("-");

			if(hyphen == poNumberHyphen){
				if (poYrPos > -1) {
					poYr = poNumber.substring(poYrPos, poYrLast+1);
				}
				if (poNumPos > -1) {
					poNum = poNumber.substring(poNumPos, poNumLast+1);
				}
			} else {
				if(!HiltonUtility.isEmpty(poHeader.getFiscalYear())){
					poYr = poHeader.getFiscalYear().substring(2);
				} else {
					poYr = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone).substring(2);
				}

				if(poNumberHyphen <= poNumber.length()-4){
					poNum = poNumber.substring(poNumber.length()-4);
				} else if(poNumberHyphen-4 >= 0){
					poNum = poNumber.substring(poNumberHyphen-4, poNumberHyphen);
				}
			}

			formatedNumber = format ;
			if (numPoPos > -1) {
				poNumberString = poNum + poYr;
				formatedNumber = formatedNumber.replaceAll(replicate("O",poNumberString.length()),poNumberString) ;
			}
			if (numPos > -1) {
				invNumberString = invoiceNumber.substring(numPos, numLast+1);
				formatedNumber = formatedNumber.replaceAll(replicate("N",invNumberString.length()),invNumberString) ;
			} else {
				formatedNumber = invNumberString ;
			}

			result = formatedNumber;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
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
