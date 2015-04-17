package com.tsa.puridiom.doccomment.tasks;
/*
 * Created on Aug 19, 2003 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.DocText;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class DocCommentTextRetrieveByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		ArrayList docCommentList = (ArrayList)incomingRequest.get("docCommentList") ;
        ArrayList docTextList = new ArrayList() ;
		
		Iterator it = docCommentList.iterator() ;
		int	i = 0;
		String	organizationId = (String) incomingRequest.get("organizationId");
		
		while (it.hasNext()) {
			HashMap requestParameters = new HashMap();
			
			requestParameters.put("organizationId", organizationId);
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			processLoader.setApplicationName(this.getApplicationName());
			PuridiomProcess process = processLoader.loadProcess("doctext-retrieve-by-id.xml");
			
			DocComment  docComment = (DocComment) it.next() ;
						 
			requestParameters.put("DocText_icText", docComment.getIcText().toString()) ;
			process.executeProcess(requestParameters);
			this.setStatus(process.getStatus()) ;
			if (this.getStatus() == Status.FAILED) {
				break ;
			}
			docComment.setDocText((DocText) requestParameters.get("docText"));
			docCommentList.set(i, docComment);
			i++;
			docTextList.add((DocText) requestParameters.get("docText")) ;
		}
		this.setStatus(Status.SUCCEEDED) ;			
		return docTextList  ;
	}
}
