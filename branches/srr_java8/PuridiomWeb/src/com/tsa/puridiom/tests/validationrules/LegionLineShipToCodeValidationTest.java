/*
 * Created on Oct 11, 2005
 */
package com.tsa.puridiom.tests.validationrules;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ShipTo;
import com.tsa.puridiom.entity.ShipToPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.rule.RuleManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


/**
 * @author Kelli
 */
public class LegionLineShipToCodeValidationTest {
    
	public boolean rule(String name) {
	    boolean result = false;
	    
	    try {
			RuleManager rules = RuleManager.getInstance();
			Map map = new HashMap();
			
			BigDecimal ic = new BigDecimal(0);
			List shipToList1 = new ArrayList();
			List shipToList2 = new ArrayList();
			List shipToList3 = new ArrayList();
	
			ShipTo shipTo1 = new ShipTo();
			ShipToPK shipToPK1 = new ShipToPK();
			ShipTo shipTo2 = new ShipTo();
			ShipToPK shipToPK2 = new ShipToPK();
			ShipTo shipTo3 = new ShipTo();
			ShipToPK shipToPK3 = new ShipToPK();
			
			shipToPK1.setIcHeader(ic);
			shipToPK1.setIcLine(ic);
			shipToPK1.setShipToCode("SOUTH");
			shipToPK1.setShipToPriority("1");
			shipTo1.setComp_id(shipToPK1);
			
			shipToPK2.setIcHeader(ic);
			shipToPK2.setIcLine(ic);
			shipToPK2.setShipToCode("TSA");
			shipToPK2.setShipToPriority("1");
			shipTo2.setComp_id(shipToPK2);
			
			shipToPK3.setIcHeader(ic);
			shipToPK3.setIcLine(ic);
			shipToPK3.setShipToCode("NORTH");
			shipToPK3.setShipToPriority("1");
			shipTo3.setComp_id(shipToPK3);
			
			shipToList1.add(shipTo1);
			shipToList1.add(shipTo2);
			shipToList1.add(shipTo3);
			
			shipToList2.add(shipTo3);
			
			// test w/ no ship to records
			//shipToList3.add(shipTo3);
			
			RequisitionLine requisitionLine1 = new RequisitionLine();
			RequisitionLine requisitionLine2 = new RequisitionLine();
			RequisitionLine requisitionLine3 = new RequisitionLine();
			
			requisitionLine1.setShipToList(shipToList1);
			requisitionLine2.setShipToList(shipToList2);
			requisitionLine3.setShipToList(shipToList3);
			
			List lineItemList = new ArrayList();
			
			lineItemList.add(requisitionLine1);
			lineItemList.add(requisitionLine2);
			lineItemList.add(requisitionLine3);
			
			String	organizationId = "LEGION";
			
			DBSession dbsession = new DBSession(organizationId);
			
			map.put("lineitems", lineItemList);
			map.put("isValidationRule", "true");
			map.put("newRules", "true");
			map.put("organizationId", organizationId);
			map.put("dbsession", dbsession);
		
			result = rules.evaluate(name, map);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}
	
	public static void main(String[] args) {
		LegionLineShipToCodeValidationTest pr = new LegionLineShipToCodeValidationTest();
		
		System.out.println(pr.rule("line-shiptocode-v.xml"));
	}
}
