package com.tsa.puridiom.browse.tasks;

import java.util.Map;

import com.tsa.puridiom.browse.Browse;
import com.tsagate.foundation.processengine.Task;

public class BrowseObjectSetCurrentPage extends Task{
	public Object  executeTask (Object object) throws Exception {

	    Object result = null;
	    Map incomingRequest = (Map)object;
	    Browse browse = (Browse)incomingRequest.get("browse");

		int	newPage = Integer.valueOf((String) incomingRequest.get("newPage")).intValue();
	    browse.getBrowseObject().setCurrentPage(newPage);

		result = browse.getBrowseObject();

		return result;
	}
}
