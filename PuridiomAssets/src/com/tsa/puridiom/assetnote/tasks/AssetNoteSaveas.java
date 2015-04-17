package com.tsa.puridiom.assetnote.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AssetNoteSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain assetNote */
			AssetNotePK comp_id = new AssetNotePK();
			AssetNote	originalAssetNote = (AssetNote) incomingRequest.get("assetNote");
			AssetNote	assetNote = new AssetNote();

			comp_id.setTagNumber(originalAssetNote.getComp_id().getTagNumber());
			comp_id.setSequenceNo(originalAssetNote.getComp_id().getSequenceNo());
			assetNote.setDateEntered(originalAssetNote.getDateEntered());
			assetNote.setDateChanged(originalAssetNote.getDateChanged());
			assetNote.setUserId(originalAssetNote.getUserId());
			assetNote.setLastChgBy(originalAssetNote.getLastChgBy());
			assetNote.setStdText(originalAssetNote.getStdText());
			assetNote.setComp_id(comp_id);

			incomingRequest.put("assetNote", assetNote);

			AssetNoteAdd addTask = new AssetNoteAdd();
			assetNote = (AssetNote) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = assetNote;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}