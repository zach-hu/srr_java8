package com.tsa.puridiom.doccomment.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.DocText;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

@SuppressWarnings(value = { "unchecked" })
public class DocCommentWithTextRetrieveByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		
		Object result = null;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("DocComment_icHeader");
		String	organizationId = (String) incomingRequest.get("organizationId");

        String queryString = "from DocComment as c where c.id.icHeader = ? AND c.id.icLine = 0" ;

		BigDecimal bdHeader = new BigDecimal(icHeader);

		List docCommentList = dbs.query(queryString, new Object[] {bdHeader}, new Type[] {Hibernate.BIG_DECIMAL}) ;
		
		Iterator it = docCommentList.iterator() ;
		int	i = 0;
		
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
		}
		
		result = docCommentList;
		
		this.setStatus(dbs.getStatus()) ;

		return result  ;
	}
}
