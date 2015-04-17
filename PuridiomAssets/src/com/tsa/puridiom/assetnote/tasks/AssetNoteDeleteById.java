package com.tsa.puridiom.assetnote.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AssetNoteDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AssetNote assetNote = (AssetNote)incomingRequest.get("assetNote");
		if(assetNote == null)
		{
			assetNote = new AssetNote();
		}
		AssetNoteSetValuesPK setValues = new AssetNoteSetValuesPK();
		setValues.setValues(incomingRequest, assetNote);
		dbs.delete(assetNote);
		this.setStatus(dbs.getStatus()) ;
		return assetNote ;
	}

}