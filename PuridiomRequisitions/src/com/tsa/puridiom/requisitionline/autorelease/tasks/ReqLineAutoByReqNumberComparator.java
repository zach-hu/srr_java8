/*
 * Created on Jul 18, 2003
 */
package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.Comparator;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.RequisitionLine;

/**
 * @author Administrator
 */
public class ReqLineAutoByReqNumberComparator implements Comparator
{
	public int compare(Object objectOne, Object objectTwo)
	{
		RequisitionLineAutoReleaseObject obj1 = (RequisitionLineAutoReleaseObject) objectOne ;
		RequisitionLineAutoReleaseObject obj2 = (RequisitionLineAutoReleaseObject) objectTwo ;

		if (obj1 == null && obj2 == null)
		{
			return 0;
		}
		else if (obj1 == null || obj2 == null)
		{
			return -1;
		}
		else
		{
			RequisitionLine reqLine1 = obj1.getRequistionLine();
			RequisitionLine reqLine2 = obj2.getRequistionLine();
			String reqNumber1 = reqLine1.getRequisitionNumber();
			String reqNumber2 = reqLine2.getRequisitionNumber();

			return reqNumber1.compareTo(reqNumber2);
		}
	}
}
