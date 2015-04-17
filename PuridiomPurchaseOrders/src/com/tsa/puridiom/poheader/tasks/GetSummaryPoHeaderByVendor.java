package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class GetSummaryPoHeaderByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		List poheaderList = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			BigDecimal total = new BigDecimal("0");
			BigDecimal totalRow = new BigDecimal("0");
			BigDecimal subTotalRow = new BigDecimal("0");

			poheaderList  = (List) incomingRequest.get("browseList");
			BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");

			int ind = 0;
			BrowseColumn browseColumns[] = b.getBrowseColumns();

			for(int index=0 ; index < browseColumns.length ; index ++ )
			{
				String columnName = browseColumns[index].getColumnName().toString();
				if(columnName.indexOf("PoHeader_total") == 0){
					ind = index;
					break;
				}
			}

			if (poheaderList != null && poheaderList.size() > 0)
			{

				for (int ii = 0; ii < poheaderList.size(); ii++)
					{
						Object[] po = (Object[]) poheaderList.get(ii);

						subTotalRow = new BigDecimal(po[ind].toString());
						total = total.add(subTotalRow);
						totalRow = totalRow.add(new BigDecimal("1"));
				}
			}

			incomingRequest.put("sumTotal", total.toString());
			incomingRequest.put("rowTotal", totalRow.toString());
			incomingRequest.put("PurchaseHistory_vendorId", HiltonUtility.ckNull((String) incomingRequest.get("PurchaseHistory_vendorId")) );

			result = poheaderList;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);

		}
		return result;

	}
}