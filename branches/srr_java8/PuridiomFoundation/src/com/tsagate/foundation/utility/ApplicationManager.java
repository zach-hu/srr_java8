/**
 *
 */
package com.tsagate.foundation.utility;

/**
 * @author renzo
 *
 */
public class ApplicationManager
{
	private static ApplicationManager instance = null;
	private boolean web;

	public static ApplicationManager getInstance()
	{
		if(ApplicationManager.instance == null)
		{
			ApplicationManager.instance = new ApplicationManager();
		}
		return ApplicationManager.instance;
	}

	public boolean isWeb() {
		return web;
	}
	public void setWeb(boolean web) {
		this.web = web;
	}

}
