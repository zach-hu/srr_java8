/**
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Johnny
 *
 */
public class PoSetupCreatedFromReq extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			List poLineList = (List) incomingRequest.get("poLineList");
			String createdfrom = null;

			if ((!HiltonUtility.isEmpty(poHeader.getRequisitionNumber())) && (poHeader.getIcReqHeader().compareTo(new BigDecimal(0)) > 0)) {

				incomingRequest.put("RequisitionHeader_icReqHeader", poHeader.getIcReqHeader().toString());
				createdfrom = "REQ";
			} else {

				for (Iterator iterator = poLineList.iterator(); iterator.hasNext();)
				{
					PoLine poLine = (PoLine) iterator.next();

					if ((!HiltonUtility.isEmpty(poLine.getRequisitionNumber())) && (poLine.getIcReqLine().compareTo(new BigDecimal(0)) > 0)) {

						incomingRequest.put("RequisitionHeader_icReqLine", poLine.getIcReqLine().toString());
						incomingRequest.put("RequisitionLine_icReqLine", poLine.getIcReqLine().toString());
						createdfrom = "REQ";

						break;
					}
				}
			}

			incomingRequest.put("createdfrom", createdfrom);

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("RequisiLast Approval Log record could not be retrieved.", e);
		}
		return ret ;
	}
}
