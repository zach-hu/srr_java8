package com.tsa.puridiom.vendordocument.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class VendorDocumentUpdateByVendor extends Task {

	public Object executeTask(Object object) throws Exception {
		Object ret = null;

		try {
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			Object docIcObj = incomingRequest.get("VendorDocument_docIc");

			if (docIcObj instanceof String[]) {
				int	arraySize = ((String[]) docIcObj).length;
				Set keySet = incomingRequest.keySet();

				for(int i = 0; i < arraySize; i++) {
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					processLoader.setApplicationName(this.getApplicationName());
					PuridiomProcess process = processLoader.loadProcess("vendordocument-update-by-id.xml");

					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));

					Iterator iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						if (key.indexOf("VendorDocument_") == 0) {
							Object obj =  incomingRequest.get(key);
							if (obj instanceof String[]) {
								String arrayObj[] = (String[]) obj;
								updateParameters.put(key, arrayObj[i]);
							} else {
							    updateParameters.put(key, obj);
							}
						}
					}
					process.executeProcess(updateParameters);

					if (process.getStatus() != Status.SUCCEEDED) {
						throw new TsaException("Error occurred updating  vendor documents!");
					}
				}
			}
			else if (!Utility.isObjectEmpty(docIcObj)) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("vendordocument-update-by-id.xml");

				process.executeProcess(incomingRequest);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
