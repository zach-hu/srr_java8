package com.tsa.puridiom.browse.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.BrowseUtility;
import com.tsa.puridiom.browse.ComputedColumn;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class BrowseColumnHQLArgumentsRetrieve extends Task
{

    public Object executeTask(Object object) throws Exception
    {
    	Map incomingRequest = (Map) object;
        Object result = null;
        try
        {
            DBSession dbs = (DBSession) incomingRequest.get("dbsession");
            BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");

            BrowseColumn[] browseColumnArray = b.getBrowseColumns();
            
            for (int i = 0; i < browseColumnArray.length; i++) {
                BrowseColumn browseColumn = browseColumnArray[i];
                if(browseColumn.getClassName().equals("d")){
                	String query = browseColumn.getSelecthql();
                	
                	List list = dbs.query(query, new Object[] {});
                    
                    ArrayList columnsToUse = new ArrayList();
                    ComputedColumn computedColumn = new ComputedColumn();
        			computedColumn.setColumnType("");
        			computedColumn.setValue("none");
        			columnsToUse.add(computedColumn);
        			
                    String key = null;
                    String value = null;
                    
                    for (int j = 0; j < list.size(); j++) {
						Object obj = list.get(j);
						Object entity = BrowseUtility.getEntityObject(obj, browseColumn);
						
						if (entity != null) {
							key = (String)BrowseUtility.getArgumentValue(browseColumn.getMethodOptionKey(), entity);
							value = (String)BrowseUtility.getArgumentValue(browseColumn.getMethodOptionValue(), entity);
						}
						
						computedColumn = new ComputedColumn();

						computedColumn.setColumnType(key);
						computedColumn.setValue(value);
						columnsToUse.add(computedColumn);
					}
                    
                    browseColumnArray[i].setArgumentColumns(columnsToUse);
                }
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            e.getMessage();
            throw new TsaException(this.getName(), e);
        }
        return result;
    }

}
