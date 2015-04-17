package com.tsa.puridiom.assetnote.serv.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class AssetNoteDeleteByTagNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

		List items = (List) incomingRequest.get("assetNoteList");
		if (items.size()>0)
		{
			for (int i=0; i < items.size(); i++)
			{
				AssetNote assetNote = (AssetNote) items.get(i);
				dbs.delete(assetNote);
			}
		}
		this.setStatus(dbs.getStatus()) ;
		return null;
	}

}