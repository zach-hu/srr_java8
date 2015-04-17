/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.doccomment.tasks;

import com.tsa.puridiom.entity.DocComment ;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff / Kelli
 */
public class DocCommentSaveasList extends Task
{
	
	/**
	 * executeTask
	 * <p>This method takes a DocComment List coming from incoming request(docCommentList)</p>
	 * <p> and calls the Saveas task for each one of them.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List docCommentList = (List)incomingRequest.get("docCommentList");
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
		PuridiomProcess process = processLoader.loadProcess("doccomment-saveas-line.xml") ;
		
		for (int row = 0; row < docCommentList.size(); row++)
		{
			DocComment docComment = (DocComment)docCommentList.get(row);
			String	icText = "0";
			if (docComment.getIcText() != null)
			{
				icText = docComment.getIcText().toString();
			}
			incomingRequest.put("DocText_icText", icText);
			incomingRequest.put("DocComment_commentSource", docComment.getCommentSource());
			incomingRequest.put("docComment", docComment);
			
			process.executeProcess(incomingRequest);

			if (process.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("DocComment Save as process failed (doccomment-saveas-line.xml).");
			}			
			
			docComment = (DocComment) incomingRequest.get("docComment");
			docCommentList.set(row, docComment);
		}
		
		this.setStatus(Status.SUCCEEDED);
		
		return docCommentList;
	}

}
