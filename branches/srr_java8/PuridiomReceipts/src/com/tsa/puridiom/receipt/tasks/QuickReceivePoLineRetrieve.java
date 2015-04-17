package com.tsa.puridiom.receipt.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

@SuppressWarnings(value = { "unchecked" })
public class QuickReceivePoLineRetrieve extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();
		List requirement = new ArrayList();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List poHeaderList = (List) incomingRequest.get("poHeaderList");
			if (poHeaderList == null)
				poHeaderList = new ArrayList(10);

			for (int i = 0; i < poHeaderList.size(); i++)
			{
				if (poHeaderList.get(i) != null && poHeaderList.get(i) != "" && poHeaderList.get(i) != "NE")
				{
					PoHeader poHeader = (PoHeader) poHeaderList.get(i);
					BigDecimal icPoHeader = poHeader.getIcPoHeader();

					String queryString = "from PoLine as PoLine where PoLine.icPoHeader = ?";
					List resultList = dbs.query(queryString, new Object[] {icPoHeader} , new Type[] { Hibernate.BIG_DECIMAL}) ;

					if (resultList != null && resultList.size() == 1)
					{
						queryString = "from PoLine as PoLine where PoLine.icPoHeader = ? AND PoLine.udf2Code = 'LEVEL_3' AND PoLine.shelfLifeRqd <> 'Y' AND PoLine.chemicalItemNumber = '' ";
						resultList = dbs.query(queryString, new Object[] {icPoHeader} , new Type[] { Hibernate.BIG_DECIMAL}) ;

						if (resultList != null && resultList.size() == 1)
						{
							PoLine poLine = (PoLine)resultList.get(0);

							PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
							PuridiomProcess process = processLoader.loadProcess("poline-check-inspection-required.xml");
							incomingRequest.put("poLine_icLineHistory", poLine.getIcLineHistory().toString());

							process.executeProcess(incomingRequest);

							Boolean inspectionReq = (Boolean)incomingRequest.get("poLineInspection");

							result.add(poHeader);

							if (inspectionReq) {
								requirement.add("I");
							} else if(!poLine.getAsset().equalsIgnoreCase("N") && !Utility.isEmpty(poLine.getAsset())){
								requirement.add("M");
							} else {
								requirement.add("N");
							}
						}
						else
						{
							result.add("NE");
							requirement.add("N");
						}
					}
					else
					{
						result.add("NE");
						requirement.add("N");
					}
				}
				else
				{
					if (poHeaderList.get(i) == null || poHeaderList.get(i) == "") {
						result.add("");
					}
					else if (poHeaderList.get(i) == "NE") {
						result.add("NE");
					}
					requirement.add("N");
				}
			}
			incomingRequest.put("requirement", requirement);
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