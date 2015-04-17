package com.tsa.puridiom.approvals.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*		ApprovalGetRequiredAmounts
 * 		Generates a Map of requiredAmounts based on the data from the request.
 */
public class ApprovalGetRequiredAmounts extends Task {
    public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Map requiredAmounts = new HashMap();

        try {
            StringBuffer	ruleString = new StringBuffer() ;
            String	appUdfColumn[] = (String[]) incomingRequest.get("appUdfColumn") ;
            String	appUdfName[] = (String[]) incomingRequest.get("appUdfName") ;
            List dataList = (List) incomingRequest.get("dataList") ;
		    BigDecimal hdrAllocAmt = new BigDecimal(0);
		    Map accountAllocationAmts = (Map) incomingRequest.get("accountAllocationAmts");
		    Map allocatedAmts = new HashMap();
		    
	    	for (int cx = 0; cx < dataList.size(); cx++) {
	    		List rowList = (List) dataList.get(cx) ;
			    BigDecimal amt = new BigDecimal(0);
			    BigDecimal lineAmt = new BigDecimal(0);
			    BigDecimal hdrAmt = new BigDecimal(0);
			    BigDecimal allocPercent = new BigDecimal(0);
			    BigDecimal allocAmt = new BigDecimal(0);
			    BigDecimal icaccount = new BigDecimal(0);
			    boolean hasLines = false;
			    boolean hasAccounts = false;
			    boolean hasHeaderData = false;

	        	ruleString.setLength(0);
	        	
	        	if (rowList.size() > 0) {
		        	for (int ux = 0; appUdfColumn[ux] != null;ux++)  {
			    		for (int bx = 0; bx < rowList.size(); bx++) {
			    			Hashtable htColumn = (Hashtable) rowList.get(bx) ;
			
			    			String	ruleDataName = (String) htColumn.get("name") ;
			    			
			    			if (! ruleDataName.equalsIgnoreCase(appUdfColumn[ux])) {
			    				continue;
			    			}
			    			
			    			String	ruleDataLabel = (String) htColumn.get("label") ;
			    			String ruleDataValue = (String) htColumn.get("value") ;
			    			ruleDataValue = Utility.ckNull(ruleDataValue).trim();
			    			
			        		ruleString.append("[");
			        		ruleString.append(ruleDataLabel);
			        		ruleString.append("=");
			        		ruleString.append(ruleDataValue);
			    			ruleString.append("]");
			    			
					        String sectioncode = (String) htColumn.get("sectioncode");
					        if (sectioncode.equals("LIN")) {
					            lineAmt = (BigDecimal) htColumn.get("amount");
					            hasLines = true;
					        }
					        else if (sectioncode.equals("ACC")) {
					            allocPercent = (BigDecimal) htColumn.get("allocPercent");
					            allocAmt = (BigDecimal) htColumn.get("amount");
					            icaccount = (BigDecimal) htColumn.get("icaccount") ;
					            hasAccounts = true;
					        }
					        else if (sectioncode.equals("HDR")) {
					            hdrAmt = (BigDecimal) htColumn.get("amount");
					            hasHeaderData = true;
					        }
			    		}  // Next bx
		        	}  // Next ux
		        	
		        	if (hasLines) {
		        	    if (allocPercent.compareTo(new BigDecimal(0)) <= 0) {
		        	        BigDecimal totalAllocationAmt = new BigDecimal(0);
		    			    if (accountAllocationAmts.containsKey(icaccount)) {
		    			        totalAllocationAmt = (BigDecimal) accountAllocationAmts.get(icaccount);
		    			    }
		    			    try {
		    			        allocPercent = allocAmt.divide(totalAllocationAmt, 10, BigDecimal.ROUND_HALF_UP);
		    			    } catch (Exception e) {
		    			        Log.error(this, "Error calculating allocation percentage.");
		    			    }
		        	    } else {
		        	        allocPercent = allocPercent.multiply(new BigDecimal(".01"));
		        	    }
		        	    if (hasAccounts) {
		        	        amt = lineAmt.multiply(allocPercent);
		        	    } else {
		        	        amt = lineAmt;
		        	    }
		        	} else if (hasAccounts) {
		        	    amt = allocAmt;
		        	} else if (hasHeaderData) {
		        	    amt = hdrAmt;
		        	}
		        	
		        	if (hasAccounts) {
					    BigDecimal amtAllocated = new BigDecimal(0);
					    if (allocatedAmts.containsKey(icaccount)) {
					        amtAllocated = (BigDecimal) allocatedAmts.get(icaccount);
					    }
					    amtAllocated = amtAllocated.add(amt);
					    
					    BigDecimal totalAllocationAmt = new BigDecimal(0);
					    if (accountAllocationAmts.containsKey(icaccount)) {
					        totalAllocationAmt = (BigDecimal) accountAllocationAmts.get(icaccount);
					    }
					    if (amtAllocated.compareTo(totalAllocationAmt) > 0) {
					        BigDecimal amtDifference = amtAllocated.subtract(totalAllocationAmt);
					        amt = amt.subtract(amtDifference);
					        amtAllocated = amtAllocated.subtract(amtDifference);
					    }
					    allocatedAmts.put(icaccount, amtAllocated);
		        	}
	
				    if (requiredAmounts.containsKey(ruleString.toString())) {
				        BigDecimal tempAmount = (BigDecimal) requiredAmounts.get(ruleString.toString());
				        amt = tempAmount.add(amt);
				    }
				    requiredAmounts.put(ruleString.toString(), amt);
		    	}  // Next cx
	        	
		        incomingRequest.put("requiredAmounts", requiredAmounts);
	    	}
	        
			this.status = Status.SUCCEEDED;
        }
        catch (Exception e) {
			this.status = Status.FAILED;
			throw new TsaException("Routing List could not be generated", e);
        }

		return requiredAmounts ;
	}
}
