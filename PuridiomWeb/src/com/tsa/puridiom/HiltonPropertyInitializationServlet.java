/*
 * Created on Nov 9, 2003
 */
package com.tsa.puridiom;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.security.tasks.RetrieveOrganizationList;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import java.util.*;

/**
 * @author Kelli
 */
public class HiltonPropertyInitializationServlet extends HttpServlet {

	public void init() throws ServletException {
		try {
			super.init();
			
			//load the properties for all organizations
			this.loadProperties();
		}
		catch (Exception exception) {
			throw new ServletException(exception.toString());
		}
	}
	public void loadProperties() {
		RetrieveOrganizationList retrieveOrganizations = new RetrieveOrganizationList();

		try {
			List organizations = (List) retrieveOrganizations.executeTask(new HashMap());
			
			//load the properties
			Iterator iterator = organizations.iterator();
			String	organization = "";

			while (iterator.hasNext()) {
				organization = (String) iterator.next();
				PropertiesManager properties = PropertiesManager.getInstance(organization);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
