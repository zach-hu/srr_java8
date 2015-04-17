package com.tsa.puridiom.tests;

import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.entity.RfqBidPK;
import com.tsagate.foundation.rule.operator.OperatorTypes;
import com.tsagate.foundation.utility.FilterList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kelli
 */
public class FilterListTest
{
	public FilterListTest ()
	{
		super();
	}
	
	public static void main(String args[])
	{
		try
		{
		    List list = new ArrayList();
		    RfqBid bid1= new RfqBid();
		    RfqBidPK pk1= new RfqBidPK();
		    
		    pk1.setIcRfqHeader(new BigDecimal("11111"));
		    pk1.setIcRfqLine(new BigDecimal("22111"));
		    pk1.setVendorId("DELL");
		    bid1.setComp_id(pk1);
		    
		    list.add(bid1);
		    
		    RfqBid bid2= new RfqBid();
		    RfqBidPK pk2= new RfqBidPK();
		    
		    pk2.setIcRfqHeader(new BigDecimal("11111"));
		    pk2.setIcRfqLine(new BigDecimal("22222"));
		    pk2.setVendorId("DELL");
		    bid2.setComp_id(pk2);
		    
		    list.add(bid2);
		    
		    RfqBid bid3= new RfqBid();
		    RfqBidPK pk3= new RfqBidPK();
		    
		    pk3.setIcRfqHeader(new BigDecimal("11111"));
		    pk3.setIcRfqLine(new BigDecimal("22333"));
		    pk3.setVendorId("DELL");
		    bid3.setComp_id(pk3);
		    
		    list.add(bid3);
		    
			FilterList filterList = new FilterList(list);
//			filterList.addFilter("icRfqHeader", OperatorTypes.equal, new BigDecimal("11111"));
//			filterList.addLogicValue("AND");
			filterList.addFilter("icRfqLine", OperatorTypes.equal, new BigDecimal("22111"));
//			filterList.addLogicValue("AND");
//			filterList.addFilter("vendorId", OperatorTypes.equal, "DELL");
			List filteredList = filterList.filter();
			
			if (filteredList != null) {
			    for (int i = 0; i < filteredList.size(); i++) {
			        RfqBid bid = (RfqBid) filteredList.get(i);
			        System.out.println(i + " - " + bid.toString());
			    }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("COMPLETE");
	}
}
