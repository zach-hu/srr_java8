/**
 *
 */
package com.tsa.puridiom.common.autorelease;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ShipTo;

/**
 * @author renzo
 *
 */
public class AutoReleaseObjectComparator implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		if( o1 != null && o2 != null )
		{
			if (o1 instanceof RequisitionLineAutoReleaseObject && o2 instanceof RequisitionLineAutoReleaseObject)
	        {
				RequisitionLineAutoReleaseObject autoRelease1 = (RequisitionLineAutoReleaseObject)o1;
				RequisitionLineAutoReleaseObject autoRelease2 = (RequisitionLineAutoReleaseObject)o2;
				BigDecimal bd1 = autoRelease1.getIcReqHeader().add(autoRelease1.getRequistionLine().getLineNumber());
				BigDecimal bd2 = autoRelease2.getIcReqHeader().add(autoRelease2.getRequistionLine().getLineNumber());

				return bd1.compareTo(bd2);
	        }
		}
		return 0;
	}
	public static void main(String[] args)
	{
		Object data[] = new Object[3];
		RequisitionHeader r1 = new RequisitionHeader();
		r1.setIcReqHeader(new BigDecimal("11608264300060"));
		data[0] = r1;
		RequisitionLine rl1 = new RequisitionLine();
		rl1.setIcReqLine(new BigDecimal("11608264400070"));
		rl1.setLineNumber(new BigDecimal(1));
		data[1] = rl1;
		data[2] = new ShipTo();
		RequisitionLineAutoReleaseObject a1 = new RequisitionLineAutoReleaseObject(data);

		Object data1[] = new Object[3];
		RequisitionHeader r12 = new RequisitionHeader();
		r12.setIcReqHeader(new BigDecimal("11608264300060"));
		RequisitionLine rl12 = new RequisitionLine();
		rl12.setIcReqLine(new BigDecimal("11608264500076"));
		rl12.setLineNumber(new BigDecimal(2));
		data1[0] = r12;
		data1[1] = rl12;
		data1[2] = new ShipTo();
		RequisitionLineAutoReleaseObject a12 = new RequisitionLineAutoReleaseObject(data1);

		Object data13[] = new Object[3];
		RequisitionHeader r123 = new RequisitionHeader();
		r123.setIcReqHeader(new BigDecimal("11609082500025"));
		RequisitionLine rl123 = new RequisitionLine();
		rl123.setIcReqLine(new BigDecimal("11608110900009"));
		rl123.setLineNumber(new BigDecimal(1));
		data13[0] = r123;
		data13[1] = rl123;
		data13[2] = new ShipTo();
		RequisitionLineAutoReleaseObject a123 = new RequisitionLineAutoReleaseObject(data13);

		List list1 = new ArrayList();
		list1.add(a1);
		list1.add(a123);
		list1.add(a12);


		Collections.sort(list1, new AutoReleaseObjectComparator());

		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			RequisitionLineAutoReleaseObject aro = (RequisitionLineAutoReleaseObject) iterator.next();
			System.out.println(aro.getIcReqHeader() + "-line-" + aro.getRequistionLine().getLineNumber());
		}


		/*
		 * 11608264300060 11608264400070 11608264500076 11608264500082
11608082500025 11608110900009


		 */

	}
}
