package com.tsa.puridiom.organization.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.organization.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class FormatOrganizationIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			OrganizationRegisterNew test = new OrganizationRegisterNew();

			System.out.println("Organization Id: " + test.formatOrganizationId("2"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}