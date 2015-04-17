/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formhtml.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
/**
 * @author Kelli
 */
public class UserEmailActivation extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
        	 Map incomingRequest = (Map)object;
             String organizationId = (String)incomingRequest.get("organizationId");
             String userId = (String)incomingRequest.get("UserProfile_userId");
             UserProfile userProfile = UserManager.getInstance().getUser(organizationId, userId);

             Map parameters = new HashMap();
             EntityDataSource ds = new EntityDataSource(userProfile);
             parameters.put("datasource", ds);
             parameters.put("organizationId", organizationId);
             parameters.put("oid", organizationId);
             parameters.put("entity", userProfile);

             parameters.put("formType", "user-email-activation.jasper");
             parameters.put("uid", incomingRequest.get("userId"));
             parameters.put("mid", incomingRequest.get("mid"));

             String defaultSiteUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl");
             String siteUrl = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "URL", defaultSiteUrl);

             parameters.put("GCS_SITE_URL", siteUrl);
             String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
             parameters.put("imgUrl", imgUrl);
             Log.debug(this, "imgUrl "+ imgUrl);

             String webreport = (String)incomingRequest.get("webreport");
             if(Utility.isEmpty(webreport)){		webreport = "Y";	}
             parameters.put("webreport", webreport);
             parameters.put("format", "html");
             ret = JasperReportsHelper.poEmailApp(parameters);

             this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
