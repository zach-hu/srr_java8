package com.tsagate.properties.tests;

import com.tsagate.properties.DictionaryManager;

public class DictionaryManagerIsVisibleTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String	oid = "PURIDIOM";
		    String	labelProperty = "req-acc-fld1";
		    String	defaultLabel = "DEFAULT";
		    String language = "";
		    boolean	isVisible = DictionaryManager.getLabelsInstance(oid, language).isVisible(oid, labelProperty);

		    System.out.println(oid + " Visible Property for Label " + labelProperty + " = " + isVisible);
			System.out.println("DictionaryManagerIsVisibleTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}