package com.tsagate.properties.tests;

import com.tsagate.properties.DictionaryManager;

public class DictionaryManagerIsRequiredTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String	oid = "PURIDIOM";
		    String	labelProperty = "req-acc-fld1";
		    String	defaultLabel = "DEFAULT";
		    String language = "";
		    boolean	isRequired = DictionaryManager.getLabelsInstance(oid, language).isRequired(oid, labelProperty);

		    System.out.println(oid + " Required Property for Label " + labelProperty + " = " + isRequired);
			System.out.println("DictionaryManagerIsVisibleTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}