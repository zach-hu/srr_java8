/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.doccomment.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.doctext.tasks.DocTextDeleteById;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class DocCommentDeleteByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		
		this.setStatus(Status.SUCCEEDED);
		
		// Assumes comment rows have already been retrieved
		List  docCommentList = (List)incomingRequest.get("docCommentList") ;
		
		Iterator it = docCommentList.iterator() ;
		while (it.hasNext()) {
			DocComment docComment = (DocComment)it.next() ;
			incomingRequest.put("DocComment_commentOrder",docComment.getComp_id().getCommentOrder().toString()) ;
			String commentSource = docComment.getCommentSource() ;
			if (commentSource == null) {
				commentSource = "" ;
			}
			if (! commentSource.equalsIgnoreCase("STD")) {
				// Delete Comment Text
				BigDecimal icText = docComment.getIcText() ;
				incomingRequest.put("DocText_icText", icText.toString()) ;
				incomingRequest.put("docText",docComment.getDocText()) ;
				DocTextDeleteById docTextDelete = new DocTextDeleteById();
				docTextDelete.executeTask(incomingRequest);
				this.setStatus(docTextDelete.getStatus()) ;
			}
			
			if (this.getStatus() == Status.SUCCEEDED) {
				DocCommentDeleteById docCommentDelete = new DocCommentDeleteById() ;
				incomingRequest.put("docComment",docComment) ;
				docCommentDelete.executeTask(incomingRequest) ;
				this.setStatus(docCommentDelete.getStatus()) ;
			}
			if (this.getStatus() != Status.SUCCEEDED) {
				break ;
			}
		}
			
		return object  ;
	}
}
