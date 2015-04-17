<%
		List accountCommodityList = (List) request.getAttribute("accountCommodityList");
		Map accountDefaultByHeader = (Map) request.getAttribute("accountDefaultByHeader");
		String accfld1 = "", accfld2 = "", accfld3 = "", accfld4 = "", accfld5 = "";
		String accfld6 = "", accfld7 = "", accfld8 = "", accfld9 = "", accfld10 = "";
		String accfld11 = "", accfld12 = "", accfld13 = "", accfld14 = "", accfld15 = "";

		String nameUdf1 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF1","");
		String nameUdf2 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF2","");
		String nameUdf3 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF3","");
		String nameUdf4 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF4","");
		String nameUdf5 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF5","");
		String dept 	= propertiesManager.getProperty("ACCT NAME UDFS", "DEPT","");
		String nameUdf6 = propertiesManager.getProperty("ACCT NAME UDFS", "COST CENTER","");
		String shipTo 	= propertiesManager.getProperty("ACCT NAME UDFS", "SHIP TO","");
		String locale 	= propertiesManager.getProperty("ACCT NAME UDFS", "LOCALE","");

		String defaultByCommodity 	= propertiesManager.getProperty("ACCOUNTS","DEFAULTBYCOMMODITY","N");
		String defaultByHeader 		= propertiesManager.getProperty("ACCOUNTS","DEFAULTBYHEADER","N");

		if ( !HiltonUtility.isEmpty(nameUdf1) )
		{
			if (nameUdf1.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf1();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf2) )
		{
			if (nameUdf2.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf2();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf3) )
		{
			if (nameUdf3.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf3();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf4) )
		{
			if (nameUdf4.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf4();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf5) )
		{
			if (nameUdf5.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf5();	}
		}
		if ( !HiltonUtility.isEmpty(dept) )
		{
			if (dept.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getDepartment();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf6) )
		{
			if (nameUdf6.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getCostCenter();	}
			if (nameUdf6.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getCostCenter();	}
		}
		if ( !HiltonUtility.isEmpty(shipTo) )
		{
			if (shipTo.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getShipToCode();	}
		}
		if ( !HiltonUtility.isEmpty(locale) )
		{
			if (locale.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getLocale();	}
		}

		if (oid.equalsIgnoreCase("ttr09p") && ( accountType.startsWith("RQ") || accountType.startsWith("PO")))
		{
			if( s_req_udf10.equalsIgnoreCase("90") || s_req_udf10.equalsIgnoreCase("91") || s_req_udf10.equalsIgnoreCase("95"))
			{
				accfld1 = "139400";
				accfld3 = "CC";
			}
		}

		if (oid.equalsIgnoreCase("hoy08p"))
		{
			List usetaxAccountList = (List) request.getAttribute("usetaxAccountList");
			if (usetaxAccountList != null && usetaxAccountList.size()>= 1)
			{
				System.out.println(usetaxAccountList);
				Account useTaxAccount = (Account) usetaxAccountList.get(0);
				if (useTaxAccount!=null)
				{

					if (!HiltonUtility.isEmpty(useTaxAccount.getFld1()))
					{
						accfld1 = useTaxAccount.getFld1();
					}
					if (!HiltonUtility.isEmpty(useTaxAccount.getFld2()))
					{
						accfld2 = useTaxAccount.getFld2();
					}
					if (!HiltonUtility.isEmpty(useTaxAccount.getFld3()))
					{
						accfld3 = useTaxAccount.getFld3();
					}
					if (!HiltonUtility.isEmpty(useTaxAccount.getFld4()))
					{
						String formType = HiltonUtility.ckNull((String) request.getAttribute("formType"));

						if (!formType.equalsIgnoreCase("IVH"))
						{
							accfld4 = useTaxAccount.getFld4();
						}
					}
					if (!HiltonUtility.isEmpty(useTaxAccount.getFld5()))
					{
						accfld5 = useTaxAccount.getFld5();
					}
					if (!HiltonUtility.isEmpty(useTaxAccount.getFld6()))
					{
						accfld6 = useTaxAccount.getFld6();
					}
				}
			}
		}
		if ( oid.equalsIgnoreCase("MSG07P") && auto_accounting_active.equals("NOCAPITAL") ) {
			accfld4 = "000000";
			accfld5 = "0000000";
			accfld6 = "0000";
		}

		if (oid.equalsIgnoreCase("bly07p"))
		{
			accfld1 = user.getDepartment();
			if (accountCommodityList != null)
			{
				if (HiltonUtility.isEmpty(accfld2) && accountCommodityList.size() > 0)
				{
					Account accountCommodity = (Account) accountCommodityList.get(0);
					accfld2 = accountCommodity.getFld2();
				}
			}
		}

		if ( oid.equalsIgnoreCase("wpc08p"))
		{
			UserProfile	userb = UserManager.getInstance().getUser(oid,s_buyer_code);
			if(!HiltonUtility.isEmpty(userb.getUserId()))
			{
				accfld1 = userb.getDepartment();
				accfld2 = userb.getNameUdf2();
			}
			else
			{
				accfld1 = user.getDepartment();
				accfld2 = user.getNameUdf2();
			}
		}

		if (!HiltonUtility.isEmpty(vendorValueDefault))
		{
			accfld3 = vendorValueDefault;
		}
		if (defaultByCommodity.equals("Y") && accountCommodityList != null && accountCommodityList.size() > 0) {
			Account accountCommodity = null;
			if (propertiesManager.getProperty("ACCOUNTS", "SELECT ACCOUNT BY FLD 1", "N").equalsIgnoreCase("Y"))
			{
				for (int ind = 0; ind < accountCommodityList.size(); ind++)
				{
					Account accountItem = (Account) accountCommodityList.get(ind);
					if (accfld1.equalsIgnoreCase(accountItem.getFld1()))
					{
						accountCommodity = accountItem;
						break;
					}
				}
			}
			else
			{
				accountCommodity = (Account) accountCommodityList.get(0);
			}

			if (accountCommodity != null) {
				if (HiltonUtility.isEmpty(accfld1)) {
					accfld1 = accountCommodity.getFld1();
				}
				if (HiltonUtility.isEmpty(accfld2)) {
					accfld2 = accountCommodity.getFld2();
				}
				if (HiltonUtility.isEmpty(accfld3)) {
					accfld3 = accountCommodity.getFld3();
				}
				if (HiltonUtility.isEmpty(accfld4)) {
					accfld4 = accountCommodity.getFld4();
				}
				if (HiltonUtility.isEmpty(accfld5)) {
					accfld5 = accountCommodity.getFld5();
				}
				if (HiltonUtility.isEmpty(accfld6)) {
					accfld6 = accountCommodity.getFld6();
				}
				if (HiltonUtility.isEmpty(accfld7)) {
					accfld7 = accountCommodity.getFld7();
				}
				if (HiltonUtility.isEmpty(accfld8)) {
					accfld8 = accountCommodity.getFld8();
				}
				if (HiltonUtility.isEmpty(accfld9)) {
					accfld9 = accountCommodity.getFld9();
				}
				if (HiltonUtility.isEmpty(accfld10)) {
					accfld10 = accountCommodity.getFld10();
				}
				if (HiltonUtility.isEmpty(accfld11)) {
					accfld11 = accountCommodity.getFld11();
				}
				if (HiltonUtility.isEmpty(accfld12)) {
					accfld12 = accountCommodity.getFld12();
				}
				if (HiltonUtility.isEmpty(accfld13)) {
					accfld13 = accountCommodity.getFld13();
				}
				if (HiltonUtility.isEmpty(accfld14)) {
					accfld14 = accountCommodity.getFld14();
				}
				if (HiltonUtility.isEmpty(accfld15)) {
					accfld15 = accountCommodity.getFld15();
				}
			}
		}

		if(defaultByHeader.equals("Y") && accountDefaultByHeader != null){
			if (accountDefaultByHeader.containsKey("Account_fld1"))	{	accfld1 = (String)accountDefaultByHeader.get("Account_fld1");	}
			if (accountDefaultByHeader.containsKey("Account_fld2"))	{	accfld2 = (String)accountDefaultByHeader.get("Account_fld2");	}
			if (accountDefaultByHeader.containsKey("Account_fld3"))	{	accfld3 = (String)accountDefaultByHeader.get("Account_fld3");	}
			if (accountDefaultByHeader.containsKey("Account_fld4"))	{	accfld4 = (String)accountDefaultByHeader.get("Account_fld4");	}
			if (accountDefaultByHeader.containsKey("Account_fld5"))	{	accfld5 = (String)accountDefaultByHeader.get("Account_fld5");	}
			if (accountDefaultByHeader.containsKey("Account_fld6"))	{	accfld6 = (String)accountDefaultByHeader.get("Account_fld6");	}
			if (accountDefaultByHeader.containsKey("Account_fld7"))	{	accfld7 = (String)accountDefaultByHeader.get("Account_fld7");	}
			if (accountDefaultByHeader.containsKey("Account_fld8"))	{	accfld8 = (String)accountDefaultByHeader.get("Account_fld8");	}
			if (accountDefaultByHeader.containsKey("Account_fld9"))	{	accfld9 = (String)accountDefaultByHeader.get("Account_fld9");	}
			if (accountDefaultByHeader.containsKey("Account_fld10")){	accfld10 = (String)accountDefaultByHeader.get("Account_fld10");	}
			if (accountDefaultByHeader.containsKey("Account_fld11")){	accfld11 = (String)accountDefaultByHeader.get("Account_fld11");	}
			if (accountDefaultByHeader.containsKey("Account_fld12")){	accfld12 = (String)accountDefaultByHeader.get("Account_fld12");	}
			if (accountDefaultByHeader.containsKey("Account_fld13")){	accfld13 = (String)accountDefaultByHeader.get("Account_fld13");	}
			if (accountDefaultByHeader.containsKey("Account_fld14")){	accfld14 = (String)accountDefaultByHeader.get("Account_fld14");	}
			if (accountDefaultByHeader.containsKey("Account_fld15")){	accfld15 = (String)accountDefaultByHeader.get("Account_fld15");	}
		}

		String isDisabledForCapital = "";
		String getCapitalAutoaccountingInfo = "";
		if ( HiltonUtility.ckNull((String)request.getAttribute("autoAccountingActive")).equals("CAPITAL"))
		{
			//XrefCombo xrefComboUniqueCPTL = (XrefCombo) request.getAttribute("xrefComboUniqueCPTL");
			//accfld1 = HiltonUtility.ckNull(xrefComboUniqueCPTL.getCode2());
			//accfld2 = HiltonUtility.ckNull(xrefComboUniqueCPTL.getCode3());
			//accfld3 = HiltonUtility.ckNull(xrefComboUniqueCPTL.getCode4());
			accfld3 = "117999";
			isDisabledForCapital = "DISABLED";
			getCapitalAutoaccountingInfo = "getCapitalAutoaccountingInfo(this)";
		}

		String resetDeptNonCapital="";
		String autoAccountingActive = HiltonUtility.ckNull((String)request.getAttribute("autoAccountingActive"));
		if ( autoAccountingActive.equals("CAPITAL") || autoAccountingActive.equals("NOCAPITAL") )
		{
			resetDeptNonCapital="resetDepartment();";
		}

		if (propertiesManager.getProperty("ACCOUNTS", "FLD3 FROM DEPARTMENTCODE", "N").equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(s_req_departmentCode))
		{
			accfld3 = s_req_departmentCode;
		}

		String replaceFldsFromAccountCommodity = propertiesManager.getProperty("ACCOUNTS", "REPLACE FDLS FROM ACCOUNT COMMODITY", "N");
		if (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && accountCommodityList != null && accountCommodityList.size() > 0)
		{
			Account accountComm = (Account) accountCommodityList.get(0);
			if (accountComm != null)
			{
				if (!HiltonUtility.isEmpty(accountComm.getFld1())) { accfld1 = accountComm.getFld1(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld2())) { accfld2 = accountComm.getFld2(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld3())) { accfld3 = accountComm.getFld3(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld4())) { accfld4 = accountComm.getFld4(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld5())) { accfld5 = accountComm.getFld5(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld6())) { accfld6 = accountComm.getFld6(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld7())) { accfld7 = accountComm.getFld7(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld8())) { accfld8 = accountComm.getFld8(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld9())) { accfld9 = accountComm.getFld9(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld10())) { accfld10 = accountComm.getFld10(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld11())) { accfld11 = accountComm.getFld11(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld12())) { accfld12 = accountComm.getFld12(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld13())) { accfld13 = accountComm.getFld13(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld14())) { accfld14 = accountComm.getFld14(); }
				if (!HiltonUtility.isEmpty(accountComm.getFld15())) { accfld15 = accountComm.getFld15(); }
			}
		}
%>
<SCRIPT value=JavaScript>
<!-- Hide script

	function addNew()
	{
		if (TotalRows == 0) {
			frm.as_perc_tot.value = "0.00";
			frm.as_amt_tot.value = "0.0000";
		}

		frm.deleteall.value = "FALSE";
		myTable = document.getElementById("accounts");
		TotalRows = TotalRows+1;

		myRow = createRow(myTable);
		maxRows++;

		myCell = createCell(myRow);
		id = "acc_num_" + (maxRows - 1);
		myCell.id = id;
		myCell.align = "RIGHT";
		myCell.innerHTML = maxRows + "<INPUT TYPE=\"HIDDEN\" NAME=\"Account_sequence\" value=" + maxRows + ">" +
									 "<INPUT TYPE=\"HIDDEN\" NAME=\"Account_accountType\" value=\"<%=accountType%>\">" +
									 "<INPUT TYPE=\"HIDDEN\" NAME=\"Account_allocMethod\" value=" + allocMethod + ">";

		myCell = createCell(myRow);
		id = "acc_1";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld1\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld1%>\" ONCHANGE=\"upperCase(this);<%=resetDeptNonCapital%>; setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' <%=labViewGroup %>>";
		<%=HtmlWriter.cellVisibility(oid, "AC01")%>
		<%=HtmlWriter.cellDisplay(oid, "AC01")%>

<%		String size = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC02-FIELDLENGTH", "10", false);
		if (size == null || "null".equals(size)) size = "10";
		System.out.println("size: " + size);%>

		myCell = createCell(myRow);
		id = "acc_2";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld2\" SIZE=\"<%=size%>\" MAXLENGTH=\"15\" value=\"<%=accfld2%>\" ONCHANGE=\"upperCase(this); getQxrefInfo(" + (maxRows - 1) + ");setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' >";
		<%=HtmlWriter.cellVisibility(oid, "AC02")%>
		<%=HtmlWriter.cellDisplay(oid, "AC02")%>

<%		String maxLength = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC03-FIELDLENGTH", "10", false);
		if (maxLength == null || "null".equals(maxLength)) maxLength = "15"; %>

		myCell = createCell(myRow);
		id = "acc_3";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld3\" SIZE=\"10\" MAXLENGTH=\"<%=maxLength%>\" value=\"<%=accfld3%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' <%=isDisabledForCapital %>>";
		<%=HtmlWriter.cellVisibility(oid, "AC03")%>
		<%=HtmlWriter.cellDisplay(oid, "AC03")%>

<%		size = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04-FIELDLENGTH", "10", false);
		if (size == null || "null".equals(size)) size = "10"; %>

		myCell = createCell(myRow);
		id = "acc_4";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld4\" SIZE=\"<%=size%>\" MAXLENGTH=\"15\" value=\"<%=accfld4%>\" ONCHANGE=\"upperCase(this); setProjectCode(this.value);<%=getCapitalAutoaccountingInfo%>;setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC04")%>
		<%=HtmlWriter.cellDisplay(oid, "AC04")%>

		myCell = createCell(myRow);
		id = "acc_5";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld5\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld5%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' <%=isDisabledForCapital %>>";
		<%=HtmlWriter.cellVisibility(oid, "AC05")%>
		<%=HtmlWriter.cellDisplay(oid, "AC05")%>

		myCell = createCell(myRow);
		id = "acc_6";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld6\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld6%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' <%=isDisabledForCapital %>>";
		<%=HtmlWriter.cellVisibility(oid, "AC06")%>
		<%=HtmlWriter.cellDisplay(oid, "AC06")%>

		myCell = createCell(myRow);
		id = "acc_7";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld7\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld7%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC07")%>
		<%=HtmlWriter.cellDisplay(oid, "AC07")%>

		myCell = createCell(myRow);
		id = "acc_8";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld8\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld8%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC08")%>
		<%=HtmlWriter.cellDisplay(oid, "AC08")%>

		myCell = createCell(myRow);
		id = "acc_9";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld9\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld9%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC09")%>
		<%=HtmlWriter.cellDisplay(oid, "AC09")%>

		myCell = createCell(myRow);
		id = "acc_10";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld10\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld10%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC10")%>
		<%=HtmlWriter.cellDisplay(oid, "AC10")%>

		myCell = createCell(myRow);
		id = "acc_11";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld11\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld11%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC11")%>
		<%=HtmlWriter.cellDisplay(oid, "AC11")%>

		myCell = createCell(myRow);
		id = "acc_12";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld12\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld12%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC12")%>
		<%=HtmlWriter.cellDisplay(oid, "AC12")%>

		myCell = createCell(myRow);
		id = "acc_13";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld13\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld13%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC13")%>
		<%=HtmlWriter.cellDisplay(oid, "AC13")%>

		myCell = createCell(myRow);
		id = "acc_14";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld14\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld14%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC14")%>
		<%=HtmlWriter.cellDisplay(oid, "AC14")%>

		myCell = createCell(myRow);
		id = "acc_15";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld15\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"<%=accfld15%>\" ONCHANGE=\"upperCase(this);setStatus();\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC15")%>
		<%=HtmlWriter.cellDisplay(oid, "AC15")%>

		myCell = createCell(myRow);
		id = "acc_";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"HIDDEN\" NAME=\"Account_status\" SIZE=\"2\" MAXLENGTH=\"2\" value=\"<%="01"%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "accountStatus")%>
		<%=HtmlWriter.cellDisplay(oid, "accountStatus")%>

		myCell = createCell(myRow);
		myCell.align = "right";
		myCell.innerHTML = "<DIV ID=percent" + (maxRows - 1) + "><INPUT TYPE=\"TEXT\" NAME=\"Account_allocPercent\" ID=allocPercent_" + (maxRows - 1) + " SIZE=\"10\" MAXLENGTH=\"25\" value=\"\" STYLE=\"text-align:right\"  ONCHANGE=\"addUp(" + (maxRows - 1) + "); \"></DIV>"
		+ "<DIV ID=percentAmt" + (maxRows - 1) + "><INPUT TYPE=\"HIDDEN\" NAME=\"Account_amountLine\" ID=allocAmountP_" + (maxRows - 1) + " SIZE=\"10\" MAXLENGTH=\"25\" value=\"\" STYLE=\"text-align:right\" ONCHANGE=\"\" disabled></DIV>";
		<%=HtmlWriter.cellVisibility(oid, "allocationPercent")%>
		<%=HtmlWriter.cellDisplay(oid, "allocationPercent")%>

		myCell = createCell(myRow);
		myCell.align = "right";
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_allocAmount\" ID=allocAmount_" + (maxRows - 1) + " SIZE=\"15\" MAXLENGTH=\"25\" value=\"\" STYLE=\"text-align:right\" ONCHANGE=\"addUp(" + (maxRows - 1) + ");\"><INPUT TYPE=\"HIDDEN\" NAME=\"Account_allocQty\" value=\"\">";
		<%=HtmlWriter.cellVisibility(oid, "allocationAmount")%>
		<%=HtmlWriter.cellDisplay(oid, "allocationAmount")%>

<%      if(s_account_cds_description.equalsIgnoreCase("Y")) { %>
			myNewRow = createRow(myTable);

			myCell = createCell(myNewRow);
			myCell.align = "center";
			myCell.colSpan = "2";
			myCell.innerHTML = "CDS Description: ";
			<%=HtmlWriter.cellVisibility(oid, "ACTITLE")%>
			<%=HtmlWriter.cellDisplay(oid, "ACTITLE")%>

			myCell = createCell(myNewRow);
			myCell.align = "left";
			myCell.colSpan = "8";
			myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_accountTitle\" ID=acctitle_" + (maxRows - 1) + " SIZE=\"100\" MAXLENGTH=\"20\" value=\"\" STYLE=\"text-align:left\" ONCHANGE=\"addUp(" + (maxRows - 1) + ");\">";
			<%=HtmlWriter.cellVisibility(oid, "ACTITLE")%>
			<%=HtmlWriter.cellDisplay(oid, "ACTITLE")%>

	<%  } %>

		myCell = createCell(myRow);
		id = "acc_del_" + (maxRows - 1);
		myCell.id = id;
		myCell.innerHTML = "<A href=\"javascript: if (confirm('Are you sure you wish to delete this Account?')) { deleteMe(" + (maxRows - 1) + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

		distributeRemaining((maxRows - 1));

		setCurrentRow(maxRows - 1);

		if (frm.alloc_method[1].checked)
		{
			disablePercentFields();
		}

		total();

		if ("<%=propertiesManager.getProperty("ACCOUNTS", "UPDATE FLD4 FROM FLD2", "N")%>" == "Y")
		{
			getQxrefInfo(maxRows - 1);
		}
	}

	function getQxrefInfo(row)
	{
<%	if (oid.equalsIgnoreCase("qri06p")) {	%>
			var submit = false;
			var location = "<%=tableType%>";
			var afe = "";

			if (maxRows > 1)
			{
					if (! isEmpty(frm.Account_fld2[row].value))
					{
						afe = frm.Account_fld2[row].value.trim();
						submit = true;
					}
					else
					{
						frm.Account_fld4[row].value = "";
						frm.Account_fld5[row].value = "";
						frm.Account_fld4[row].disabled = false;
						frm.Account_fld5[row].disabled = false;
					}
			}
			else
			{
					if (! isEmpty(frm.Account_fld2.value))
					{
						afe = frm.Account_fld2.value.trim();
						submit = true;
					}
					else
					{
						frm.Account_fld4.value = "";
						frm.Account_fld5.value = "";
						frm.Account_fld4.disabled = false;
						frm.Account_fld5.disabled = false;
					}
			}

			if (submit)
			{
				popupParameters = "AccountQxref_location=" + location + ";AccountQxref_afe=" + afe + ";as_row=" + row;

				setLookupParameters('/base/get_qxref_info.jsp', 'AccountQxrefRetrieveById');
				displayArea('getInfoFrame');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';

				submit = false;
			}
<%	} else if (oid.equalsIgnoreCase("vse06p")) { %>
		var submit = false;
		var proj = "";
		var org = "" ;

		if (maxRows > 1)
		{
			if (! isEmpty(frm.Account_fld2[row].value))
			{
				org = frm.Account_fld1[row].value.trim();
				proj = frm.Account_fld2[row].value.trim();
				submit = true;
			}
			else
			{
				frm.Account_fld3[row].value = "";
				frm.Account_fld4[row].value = "";
				frm.Account_fld5[row].value = "";
			}
		}
		else
		{
			if (! isEmpty(frm.Account_fld2.value))
			{
				org = frm.Account_fld1.value.trim();
				proj = frm.Account_fld2.value.trim();
				submit = true;
			}
			else
			{
				frm.Account_fld3.value = "";
				frm.Account_fld4.value = "";
				frm.Account_fld5.value = "";
			}
		}

		if (submit)
		{
			popupParameters = "AccountVse_org=" + org + ";AccountVse_proj=" + proj + ";as_row=" + row;

			setLookupParameters('/base/get_vseproj_info.jsp', 'AccountVseRetrieveByOrgProj');
			displayArea('getInfoFrame');
			document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';

			submit = false;
		}
<%	} %>
	}

	function getXrefCode4Info(row)
	{
		if ("<%=propertiesManager.getProperty("ACCOUNTS", "UPDATE FLD4 FROM FLD2", "N")%>" == "Y")
		{
			var submit = false;

			if (maxRows > 1)
			{
				if (frm.Account_fld1[row] && frm.Account_fld1[row].value != "" &&
					frm.Account_fld2[row] && frm.Account_fld2[row].value != "" &&
					frm.Account_fld3[row] && frm.Account_fld3[row].value != "")
				{
					popupParameters =
						"XrefCombo_code1=" + frm.Account_fld1[row].value +
						";XrefCombo_code2=" + frm.Account_fld2[row].value +
						";XrefCombo_code3=" + frm.Account_fld3[row].value +
						";as_row=" + row;

					submit = true;
				}
				else if (frm.Account_fld4[row])
				{
					frm.Account_fld4[row].value = "";
				}
			}
			else
			{
				if (frm.Account_fld1 && frm.Account_fld1.value != "" &&
					frm.Account_fld2 && frm.Account_fld2.value != "" &&
					frm.Account_fld3 && frm.Account_fld3.value != "")
				{
					popupParameters =
						"XrefCombo_code1=" + frm.Account_fld1.value +
						";XrefCombo_code2=" + frm.Account_fld2.value +
						";XrefCombo_code3=" + frm.Account_fld3.value +
						";as_row=" + row;

					submit = true;
				}
				else if (frm.Account_fld4)
				{
					frm.Account_fld4.value = "";
				}
			}

			if (submit)
			{
				setLookupParameters('/base/get_xref_info.jsp', 'XrefComboRetrieveBy');
				displayArea('getInfoFrame');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';

				submit = false;
			}
		}
	}

// End Hide script -->
</SCRIPT>