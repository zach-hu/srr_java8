/*
 * Created on Jan 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.systemreport.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 */
public class TopSupplierDataSource extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest =(Map)object;
            String defaultSql = (String)incomingRequest.get("defaultSql");
            String sql = (String)incomingRequest.get("sql");
            sql = "select poHeader.vendorId, sum(poHeader.total) from PoHeader as poHeader where poHeader.status = \'3030\' and poHeader.lastRevision = \'C\'" +
            		" group by poHeader.vendorId";
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            
            //Object values[] = this.getValues((String[])incomingRequest.get("columnTypes"), (String[])incomingRequest.get("values"));
            List resultList = dbs.query(sql) ;
            if(resultList == null)
            {
                resultList = new ArrayList();
            }
            ret = resultList;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }
    
    public Object[] getValues(String names[], String alias[], String types[], String values[])
    {
        List tempValuesList = new ArrayList();
        List tempTypesList = new ArrayList();
        StringBuffer where = new StringBuffer("1 = 1");
        
        for (int i = 0; i < values.length; i++)
        {
            if(!HiltonUtility.isEmpty(values[i]))
            {
                tempValuesList.add(values[i]);
                tempTypesList.add(this.getType(types[i]));
                where.append(" AND ");
                where.append(names[i]);
                where.append(" as ");
                where.append(alias[i]);
                
            }
        }
        Object tempObjects[] = new Object[] { tempTypesList.toArray(), tempTypesList.toArray()};
        
        return tempObjects;
    }
    
    public Type getType(String typeName)
    {
        Type type = Hibernate.STRING;
        
        if(typeName.equalsIgnoreCase("BigDecimal"))
        {
            type = Hibernate.BIG_DECIMAL;
        }
        else if(typeName.equalsIgnoreCase("String"))
        {
            type = Hibernate.STRING;
        }
        else if(typeName.equalsIgnoreCase("date"))
        {
            type = Hibernate.DATE;
        }
        
        return type;
    }
}
