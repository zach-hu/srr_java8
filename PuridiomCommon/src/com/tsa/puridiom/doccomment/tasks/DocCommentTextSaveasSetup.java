/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.doccomment.tasks;

import com.tsa.puridiom.entity.DocComment;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Kelli
 */
public class DocCommentTextSaveasSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		
		try {
			DocComment	docComment = (DocComment) incomingRequest.get("docComment") ;
			if (docComment == null) {
				this.setStatus(Status.FAILED) ;
			}
			else {
				BigDecimal bdIcText = docComment.getIcText();
				String	icText = "0" ;
				String	newIcText = (String) incomingRequest.get("newDocText_icText") ;
				
				if (bdIcText != null) {
					icText = bdIcText.toString();
				}
				if (Utility.isEmpty(newIcText)) {
					newIcText = icText;
				}

				incomingRequest.put("newDocComment_icText", newIcText) ;
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}		
		return null ;
	}
}
