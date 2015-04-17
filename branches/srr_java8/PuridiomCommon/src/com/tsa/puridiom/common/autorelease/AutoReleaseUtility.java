package com.tsa.puridiom.common.autorelease;

import java.util.ArrayList;
import java.util.List;

public class AutoReleaseUtility
{
	public static List getRequisitionLineList(List autoListObject)
	{
		List reqLineList = new ArrayList();
		for(int i =0; i < autoListObject.size(); i++)
		{
			RequisitionLineAutoReleaseObject rlaro = (RequisitionLineAutoReleaseObject)autoListObject.get(i);
			reqLineList.add(rlaro.getRequistionLine());
		}

		return reqLineList;
	}

}
