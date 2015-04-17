package com.tsa.puridiom.workorder.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.SrrWorkorderView;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SrrWorkorderPopulateElementData extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String wo = (String ) incomingRequest.get("srrWorkorderNo");

			List  srrWorkorderList = (List) incomingRequest.get("srrWorkorderList") ;


			if (srrWorkorderList != null  && srrWorkorderList.size() > 0) {
				SrrWorkorderView srrWorkorder = (SrrWorkorderView) srrWorkorderList.get(0) ;
				incomingRequest.put("area", HiltonUtility.ckNull(srrWorkorder.getArea()));
				incomingRequest.put("componentNo", HiltonUtility.ckNull(srrWorkorder.getComponentNo())) ;
				incomingRequest.put("corpType",HiltonUtility.ckNull(srrWorkorder.getCorpType())) ;
				incomingRequest.put("equipNo", HiltonUtility.ckNull(srrWorkorder.getEquipNo())) ;
				incomingRequest.put("equipType", HiltonUtility.ckNull(srrWorkorder.getEquipType())) ;
				incomingRequest.put("facility", HiltonUtility.ckNull(srrWorkorder.getFacility())) ;
				incomingRequest.put("idpNo",HiltonUtility.ckNull(srrWorkorder.getIdpNo())) ;
				incomingRequest.put("manufacturerCode", HiltonUtility.ckNull(srrWorkorder.getManufacturerCode())) ;
				incomingRequest.put("modelNo", HiltonUtility.ckNull(srrWorkorder.getModelNo())) ;
				incomingRequest.put("serialNo", HiltonUtility.ckNull(srrWorkorder.getSerialNo())) ;
				incomingRequest.put("systemType", HiltonUtility.ckNull(srrWorkorder.getSystemType())) ;
				incomingRequest.put("taskNo", HiltonUtility.ckNull(srrWorkorder.getTaskNo()));
				incomingRequest.put("unit", HiltonUtility.ckNull(srrWorkorder.getUnit())) ;
				incomingRequest.put("utczNo", HiltonUtility.ckNull(srrWorkorder.getUtczNo())) ;
				incomingRequest.put("waCode", HiltonUtility.ckNull(srrWorkorder.getWaCode())) ;
				incomingRequest.put("workorderClosed", HiltonUtility.ckNull(srrWorkorder.getWorkorderClosed())) ;
				incomingRequest.put("workorderNo", HiltonUtility.ckNull(srrWorkorder.getWorkorderNo())) ;

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("element-data-save.xml");
				process.executeProcess(incomingRequest);

			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}