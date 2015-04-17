package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineGroupByReleaseLimit extends Task
{

	private static final BigDecimal LIMIT_DIFFERENCE = new BigDecimal(100);
	private static final BigDecimal LIMIT = new BigDecimal(1000);

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;

		try
		{
			Map groupByOrder = (Map) incomingRequest.get("groupByOrder");
			Map blanketOrdersList = (Map) incomingRequest.get("blanketOrdersList");
			Map groupByShipTo;
			PoHeader blanket;
			List reqLines;
			BigDecimal tmpTotal;
			List tmpReqLines;
			List keysToRemove = new ArrayList();
			String groupByShipToKey;
			Set keysGroupByShipTo;
			int groupNum;
			boolean hasBranch;

			RequisitionLineAutoReleaseObject requisitionLineAutoReleaseObject;

			for (Iterator iter = blanketOrdersList.keySet().iterator(); iter.hasNext();)
			{
				System.out.println(" ************************************************************* ");

				blanket = (PoHeader) blanketOrdersList.get((String) iter.next());

				groupByShipTo = (Map) groupByOrder.get(blanket.getPoNumber());

				System.out.println("PoNumber " + blanket.getPoNumber() + " ReleaseLimit " + blanket.getReleaseLimit());

				keysGroupByShipTo = new HashSet(groupByShipTo.keySet());
				keysToRemove = new ArrayList();

				for (Iterator iterator = keysGroupByShipTo.iterator(); iterator.hasNext();)
				{
					groupByShipToKey = (String) iterator.next();

					reqLines = (List) groupByShipTo.get(groupByShipToKey);

					groupNum = 0;

					tmpTotal = new BigDecimal(0);
					tmpReqLines = new ArrayList();

					hasBranch = false;

					System.out.println(">> ShipTo " + groupByShipToKey);

					Collections.sort(reqLines, new ReqLineAutoByReqLineTotalComparator()); //Sort Req Lines by line total

					for (int i = 0; i < reqLines.size(); i++)
					{
						requisitionLineAutoReleaseObject = (RequisitionLineAutoReleaseObject) reqLines.get(i);

						tmpTotal = tmpTotal.add(requisitionLineAutoReleaseObject.getRequistionLine().getLineTotal());

						System.out.println(">>>> RegHeader " + requisitionLineAutoReleaseObject.getRequisitionHeader().getRequisitionNumber() + " ReqLine "
								+ requisitionLineAutoReleaseObject.getRequistionLine().getLineNumber() + " Total " + requisitionLineAutoReleaseObject.getRequistionLine().getLineTotal());

						System.out.println(">>>>>> TmpTotal " + tmpTotal);

						if ((tmpTotal.compareTo(LIMIT) > 0) && (i > 0))
//						if ((blanket.getReleaseLimit().subtract(tmpTotal).compareTo(LIMIT_DIFFERENCE) < 1) && (i > 0))
						{
							groupByShipTo.put(groupByShipToKey + "_" + (++groupNum), tmpReqLines);
							tmpTotal = requisitionLineAutoReleaseObject.getRequistionLine().getLineTotal();
							tmpReqLines = new ArrayList();

							hasBranch = true;

							System.out.println(">>>>>> NewShipTo Map " + groupByShipToKey + "_" + (groupNum) + "  with TmpTotal " + tmpTotal);
						}

						tmpReqLines.add(requisitionLineAutoReleaseObject);

						if (hasBranch && ((i + 1) == reqLines.size()))
						{
							groupByShipTo.put(groupByShipToKey + "_" + (++groupNum), tmpReqLines);

							System.out.println(">>>>>> Last element Map " + groupByShipToKey + "_" + (groupNum));
						}
					}

					if (hasBranch)
					{
						keysToRemove.add(groupByShipToKey);
					}
				}

				this.removeElementsFromGroupByShipTo(keysToRemove, groupByShipTo);
			}

			ret = groupByOrder;

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("RequisitionLineGroupByReleaseLimit failed to group line items: " + e.getMessage(), e);
		}

		return ret;
	}

	private void removeElementsFromGroupByShipTo(List keysToRemove, Map groupByShipTo)
	{
		String keyToRemoveShipTo = "";

		for (Iterator iter = keysToRemove.iterator(); iter.hasNext();)
		{
			keyToRemoveShipTo = (String) iter.next();

			groupByShipTo.remove(keyToRemoveShipTo);

			System.out.println("KeyShipTo to remove " + keyToRemoveShipTo);
		}
	}

}
