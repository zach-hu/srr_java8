package com.tsa.puridiom.alttext.tasks;

import com.tsa.puridiom.entity.AltText;
import com.tsa.puridiom.entity.DocText;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AltTextRetrieveByItemId extends Task{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;
        String catalogId = "";
        String itemNumber = "";
        String source = "";

        try
        {
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

            String queryString = "from AltText as AltText, DocText as DocText where AltText.id.source = ? and AltText.id.id = ? and AltText.id.itemNumber = ?  and AltText.id.language = ? and DocText.icText = AltText.icText";
            catalogId = (String) incomingRequest.get("AltText_id");
            itemNumber = (String) incomingRequest.get("AltText_itemNumber");
            source = (String) incomingRequest.get("AltText_source");
            String language = (String ) incomingRequest.get("AltText_language");

            if (Utility.isEmpty(catalogId)) {
                catalogId = (String) incomingRequest.get("CatalogItem_catalogId");
            }
            if (Utility.isEmpty(itemNumber)) {
                itemNumber = (String) incomingRequest.get("CatalogItem_itemNumber");
            }
            if (Utility.isEmpty(itemNumber)) {
                itemNumber = " ";
            }
            if (Utility.isEmpty(source)) {
                source = "CAT";
            }

            List resultList = dbs.query(queryString, new Object[] {source, catalogId, itemNumber, language} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING , Hibernate.STRING }) ;

            List altTextList = new ArrayList();

            if (resultList != null && resultList.size() > 0)
            {
                Object objArray[] = (Object[]) resultList.get(0);
                AltText altText = (AltText) objArray[0];
                DocText docText = (DocText) objArray[1];

                altText.setDocText(docText);
                result = altText;
            }
            this.setStatus(dbs.getStatus());
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            Log.error(this, "Error retrieving AltText for source = [" + source + "] and catalogId = [" + catalogId + "] and itemNumber = [" + itemNumber + "]: \r\n" + e.getMessage());
            throw e;
        }
        return result;
    }
}