/*
 * Created on Mar 17, 2005
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 */
public class NonReleasedItemsEmailTxt extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            Map itemsMap = (Map)incomingRequest.get("nonReleaseItems");
            List msg = new ArrayList();
            Map buyerMap = new HashMap();
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            if(itemsMap != null)
            {
                //group by buyer
                Set keySet = itemsMap.keySet();
                for (Iterator iter = keySet.iterator(); iter.hasNext();)
                {
                    Object key = (Object) iter.next();
                    Object nonReleasedItem[] = (Object[])itemsMap.get(key);
                    /*
                     * itemsWithReason[0] = itemsByBuyer; 			//itemlist
                            itemsWithReason[1] = nonReleasedItem[1]; //reason
                     */
                    List itemsList = (List)nonReleasedItem[0];
                    //List itemsList = (List)itemsMap.get(key);
                    for(int i = 0; i < itemsList.size(); i++)
                    {
                        RequisitionLine rql = (RequisitionLine)itemsList.get(i);
                        String assignedBuyer = rql.getAssignedBuyer();
                        if(buyerMap.containsKey(assignedBuyer))
                        {
                        	Map itemsWithReasonByBuyer = (Map)buyerMap.get(assignedBuyer);
                        	if(itemsWithReasonByBuyer != null)
                        	{
                        		List itemsByReason;
                        		if(itemsWithReasonByBuyer.containsKey(nonReleasedItem[1]))
                        		{
                        			itemsByReason = (List)itemsWithReasonByBuyer.get(nonReleasedItem[1]);
                        		}
                        		else
                        		{
                        			itemsByReason = new ArrayList();
                        		}
                        		itemsByReason.add(rql);
                    			itemsWithReasonByBuyer.put(nonReleasedItem[1], itemsByReason);
                        	}

                            buyerMap.put(assignedBuyer, itemsWithReasonByBuyer);
                        }
                        else
                        {
                        	Map itemsWithReasonByBuyer = new HashMap();
                        	List itemsByReason = new ArrayList();
                        	itemsByReason.add(rql);
                        	itemsWithReasonByBuyer.put(nonReleasedItem[1], itemsByReason);
                            buyerMap.put(assignedBuyer, itemsWithReasonByBuyer);
                        }
                    }
                }
                //create list by buyer
                Set buyerSet = buyerMap.keySet();
                for (Iterator iter = buyerSet.iterator(); iter.hasNext();)
                {
                    Object key = (Object)iter.next();
                    //List itemsByBuyer = (List)buyerMap.get(key);
                    Map itemsByBuyerWithReason = (Map)buyerMap.get(key);
                    Set keySetByReason = itemsByBuyerWithReason.keySet();
                    //List itemsWithReason = new ArrayList();
                    for (Iterator iterByReason = keySetByReason.iterator(); iterByReason.hasNext();)
                    {
                    	Object keyReason = (Object) iterByReason.next();
                        List itemsByReason = (List)itemsByBuyerWithReason.get(keyReason);
                        //itemsWithReason.add(new Object[]{itemsByReason, keyReason});
                        NonReleasedItemsSummary nonReleased = new NonReleasedItemsSummary(rqh, new Object[]{itemsByReason, keyReason}, (String)key);
                        msg.add(nonReleased);
                    }


                }
            }

            ret = msg;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }

        return ret;
    }
}