package com.tsagate.properties.tests;

import com.tsagate.properties.DictionaryManager;

public class DictionaryManagerGetLabelTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String	oid = "PURIDIOM";
		    String	labelProperty = "fld1";
		    String	defaultLabel = "DEFAULT";
		    String language = "es";
		    String	labelValue = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelProperty, defaultLabel);

		    System.out.println(oid + " Label for " + labelProperty + " = " + labelValue);
			System.out.println("DictionaryManagerGetLabelTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}