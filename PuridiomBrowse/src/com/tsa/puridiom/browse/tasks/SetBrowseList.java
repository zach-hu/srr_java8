package com.tsa.puridiom.browse.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.Browse;
import com.tsagate.foundation.processengine.Task;

public class SetBrowseList  extends Task{
	public Object  executeTask (Object object) throws Exception {

	    Object result = null;
	    Map incomingRequest = (Map)object;
	    Browse browse = (Browse)incomingRequest.get("browse");

	    List browseList = (List)incomingRequest.get("browseList");

	    browse.setBrowseList(browseList);

		result = browse;

		return result;
	}
}
