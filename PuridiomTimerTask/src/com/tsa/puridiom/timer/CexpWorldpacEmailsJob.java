/**
 * 
 */
package com.tsa.puridiom.timer;

/**
 * @author Johnny
 */
public class CexpWorldpacEmailsJob extends ReadInboxJob
{

	public void onStart()
	{
		this.setJobType("cexpworldpac");
	}

}