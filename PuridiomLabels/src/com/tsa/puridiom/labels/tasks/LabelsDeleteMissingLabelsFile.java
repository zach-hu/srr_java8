/*
 * Created on May 29, 2009
 */
package com.tsa.puridiom.labels.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.entity.Labels;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
import com.tsagate.properties.LabelsDictionary;

/**
 * @author Kelli
 */
public class LabelsDeleteMissingLabelsFile extends Task {
	List mainLabelProperties = new ArrayList();

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try {
			String userId = (String) incomingRequest.get("userId");
            String language = Utility.ckNull((String) incomingRequest.get("Labels_language"));
            String organizationId = (String) incomingRequest.get("organizationId");

            DictionaryManager.getLabelsInstance(organizationId, language).deleteMissingLabelsFile();
            
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}}
