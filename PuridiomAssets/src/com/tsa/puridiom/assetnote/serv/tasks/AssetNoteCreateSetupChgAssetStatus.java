/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.assetnote.serv.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetNoteCreateSetupChgAssetStatus extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			AssetNote assetNote = new AssetNote();
			AssetNotePK assetNotePK = new AssetNotePK();
			String userId = (String)incomingRequest.get("userId");

			assetNotePK.setTagNumber((String)incomingRequest.get("AssetNote_tagNumber"));
			assetNotePK.setSequenceNo(new BigDecimal((String)incomingRequest.get("AssetNote_sequenceNo")));
			assetNote.setDateChanged(new Date());
			assetNote.setDateEntered(new Date());
			assetNote.setLastChgBy(userId);
			assetNote.setStdText((String)incomingRequest.get("AssetNote_stdText"));
			assetNote.setComp_id(assetNotePK);
			ret = assetNote;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
