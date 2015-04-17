package com.tsa.puridiom.sungard.extract.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.common.documents.AccountRollup;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.sungard.extract.ExtractTemplate;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SungardInvoiceExtractCreateFile extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
		ExtractTemplate extractTemplate = (ExtractTemplate) incomingRequest.get("extractTemplate");

		String	outDir = extractTemplate.getExtractDirectory();
		String	extractTimestamp = Dates.today("yyyy-MM-dd hh:mm:ssss", "");
		String	year = extractTimestamp.substring(0, 4);
		String	month = extractTimestamp.substring(5, 7);
		String	day = extractTimestamp.substring(8, 10);
		String	hour = extractTimestamp.substring(11, 13);
		String	minute = extractTimestamp.substring(14, 16);
		String	seconds = extractTimestamp.substring(17, 21);
		String	outFileName = extractTemplate.getFilePrefix() + year + month + day + hour + minute + seconds + "." + extractTemplate.getFileExtension();
		boolean	appendFile = false ;
		List accountRollupList = (List) incomingRequest.get("accountRollupList") ;

		if (! HiltonUtility.isEmpty(extractTemplate.getFileName())) {
			String filename = extractTemplate.getFileName() ;
			if (accountRollupList.size() > 0) {
				AccountRollup ac = (AccountRollup) accountRollupList.get(0) ;
				String company = ac.getFld1() ;
				if (! HiltonUtility.isEmpty(company)) {
					filename = filename +"-" + company ;
				}
			}
			outFileName = extractTemplate.getFilePrefix() + "-" + filename  + "." + extractTemplate.getFileExtension() ;
			appendFile = true ;
		}

		incomingRequest.put("InvoiceHeader_lastExtract", extractTimestamp);

		 if (Utility.isEmpty(outDir)) {
     		throw new Exception("The Voucher Extract Directory was not specified.");
     	}

         File dir = new File(outDir);
         if (!dir.isDirectory()) {
             Log.error(this, "The Extract Directory [" + dir + "] is not a valid directory.");
             throw new IOException("The Voucher Extract Directory is not a valid directory.");
         }
         if (!dir.canWrite()) {
             Log.error(this, "The Extract Directory [" + dir + "] does not have write access.");
             throw new IOException("The batch file cannot be created because the application does not have write access for the Voucher Extract Directory.");
         }

         if (outDir.charAt(outDir.length() - 1) != File.separatorChar) {
             outDir = outDir + File.separator;
         }

 		File extractFile = new File(outDir + outFileName);
 		if (! extractFile.exists() || appendFile == false ) {
 			if (!extractFile.createNewFile()) {
 				throw new IOException("The extract file could not be created.");
 			}
 		}

		this.setStatus(Status.SUCCEEDED);
		return extractFile;
	}
}