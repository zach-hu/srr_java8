<%!
BigDecimal	bd_amount_to_allocate = new BigDecimal(0.00);
String	s_quantity_decimals = null;
String	s_account_action=null;
 %>
<%
	String	s_alloc_by_qty = propertiesManager.getProperty("MISC", "ALLOCBYQTY", "N");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_browse_xref_combo = propertiesManager.getProperty("ACCOUNTS", "ACCOUNT_BROWSE_XREF_COMBO", "Y");
	String	s_account_default_fld4 = propertiesManager.getProperty("ACCOUNTS","DEFAULT FLD4","N");
	String	s_req_type_account = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionType"));
	boolean restrictGlAccount = (s_req_type_account.equalsIgnoreCase("H") && accountType.startsWith("RQ")) ? true : false;
	s_account_action = HiltonUtility.ckNull((String) request.getAttribute("accountAction"));
	String	s_amount_to_allocate = (String) request.getAttribute("amountToAllocate");
	String	s_alloc_method = "";
	pageContext.setAttribute("labelPrefix", labelPrefix);

	List accountList = (List) request.getAttribute("accountList");
//	boolean	budgetActive = propertiesManager.isModuleActive("Account Budget") ;
	boolean	budgetView = propertiesManager.getProperty("BUDGET", "BUDGET_VIEW","N").equals("Y") ;
	boolean codaEnabled = propertiesManager.getProperty("CODA","Enabled","N").equals("Y") ;

    String		s_accsep = propertiesManager.getProperty("MISC", "AccountSeparator", "-");

    boolean	budgetCommodity = false ;
    for (int ix = 1; ix <= 15; ix++) {
    	String	temp = propertiesManager.getProperty("BUDGET", "BUDGET UDF" + ix, "");
    	if (temp == null) break ;
    	if (temp.trim().length() == 0) break;
    	if (temp.startsWith("Comm")) {
    		budgetCommodity = true ;
    		break ;
    	}
    }

	if (s_amount_to_allocate != null) {
		bd_amount_to_allocate = new BigDecimal(s_amount_to_allocate).setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	}
	if (accountList == null) {
		accountList = new ArrayList();
	}
	if (accountList.size() > 0) {
		Account account = (Account) accountList.get(0);
		s_alloc_method = account.getAllocMethod();
		if (HiltonUtility.isEmpty(s_alloc_method)) {
			s_alloc_method = "PL";
		}
	}

	if (s_alloc_method.length() <= 0) {
		if ( s_alloc_by_qty.equals("Y") ) {
			s_alloc_method = "Q" + accountType.substring(accountType.length() - 1);
			if (accountType.equals("IVT") || accountType.equals("IVS") || accountType.equals("IVO") || accountType.equals("IVU"))
			{
				s_alloc_method = "QH";
			}
		}
		else {
			s_alloc_method = "P" + accountType.substring(accountType.length() - 1);
			if (accountType.equals("IVT") || accountType.equals("IVS") || accountType.equals("IVO") || accountType.equals("IVU"))
			{
				s_alloc_method = "PH";
			}
		}
	}

	String auto_accounting_active = HiltonUtility.ckNull((String)request.getAttribute("autoAccountingActive"));
	String	s_commodidty_code = "";
	Object	o_commodidty_code = (Object) request.getAttribute("RequisitionLine_commodityCode");
	if (o_commodidty_code instanceof String[]) {
		s_commodidty_code = HiltonUtility.ckNull(((String[])o_commodidty_code)[0]);
	} else {
		s_commodidty_code = HiltonUtility.ckNull((String)o_commodidty_code);
	}
	String	s_division = (String) HiltonUtility.ckNull((String)user.getDepartment());

	String local_s_item_number = null;
	if(!HiltonUtility.isEmpty((String)request.getAttribute("PoLine_itemNumber"))){
		local_s_item_number = (String)request.getAttribute("PoLine_itemNumber");
	}else{
		if(!HiltonUtility.isEmpty((String)request.getAttribute("RequisitionLine_itemNumber"))){
			local_s_item_number = (String)request.getAttribute("RequisitionLine_itemNumber");
		}
	}

	String	divisionCapital = "";
	if (request.getAttribute("requisitionHeader") != null)
	{
		divisionCapital = ((RequisitionHeader) request.getAttribute("requisitionHeader")).getDepartmentCode();
	}

//	if (!(local_s_item_number.startsWith("*") || HiltonUtility.isEmpty(local_s_item_number) || HiltonUtility.isEmpty(account.getFld2())) ) {
	if ( oid.equalsIgnoreCase("BLY07P") && local_s_item_number != null && !(local_s_item_number.startsWith("*") || HiltonUtility.isEmpty(local_s_item_number)) ) {
		request.setAttribute("GLAccount_ROnly","1");
	}

	List groupList = user.getUserRoles();
	for (Iterator it = groupList.iterator(); it.hasNext(); )
    {
		if (((String)it.next()).indexOf("LAB VIEW") >= 0)
		{
			labViewGroup = "disabled";
			break;
		}
	}

	String ac01Type = propertiesManager.getProperty("ACCOUNTS", "AC01 TYPE", "AC01");
	String ac02Type = propertiesManager.getProperty("ACCOUNTS", "AC02 TYPE", "AC02");
	String ac03Type = propertiesManager.getProperty("ACCOUNTS", "AC03 TYPE", "AC03");
	String ac04Type = propertiesManager.getProperty("ACCOUNTS", "AC04 TYPE", "AC04");
	String ac05Type = propertiesManager.getProperty("ACCOUNTS", "AC05 TYPE", "AC05");
	if (user.getLocale().equalsIgnoreCase("US")) {
		ac01Type = "AC01";
		ac02Type = "AC02";
		ac03Type = "AC03";
		ac04Type = "AC04";
		ac05Type = "AC05";
	}
%>

		<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2" WIDTH=500px>
		<tr>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> WIDTH="125px" ALIGN="RIGHT"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationMethod", "Allocation Method")%>:</B><tsa:hidden name="as_alloc_method" value="<%=s_alloc_method%>"/></TD>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> WIDTH="75px" ALIGN="CENTER"><INPUT TYPE="radio" value="PH" NAME="alloc_method" <% if (s_alloc_method.substring(0, 1).equals("P")) { %>CHECKED<%}%> ONCLICK="setMethod();" VALIGN="MIDDLE"> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationPercent", "Percent")%> </TD>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> WIDTH="75px" ALIGN="CENTER"><INPUT TYPE="radio" value="AH" NAME="alloc_method" <% if (s_alloc_method.substring(0, 1).equals("A")) { %>CHECKED<%}%> ONCLICK="setMethod();" VALIGN="MIDDLE"> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "amount", "Amount")%> </TD>
			<TD <%=HtmlWriter.isVisible(oid, "totalToAllocate")%> WIDTH="200px" ALIGN="RIGHT"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "totalToAllocate", "Total Amount to Allocate")%>: </B> </TD>
			<TD WIDTH="*" ALIGN="LEFT" NOWRAP><%=bd_amount_to_allocate%></TD>
		</tr>
		</TABLE>

		<BR>

		<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0">
		<TR>
			<TD VALIGN="TOP">
				<TABLE ID=accounts BORDER="0" CELLPADDING="2" WIDTH=100%>
				<TR VALIGN="MIDDLE">
					<TD WIDTH=15px>&nbsp;<tsa:hidden name="as_rows" value="<%=accountList.size()%>"/><tsa:hidden name="stdTable_tableType" value=""/></TD>
<% if ( auto_accounting_active.equals("CAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC01capital")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC01capital")) {	%>
						<A HREF="javascript: browseGLAccount('Account_fld1', '<%=ac01Type%>'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01capital", "Entity", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01capital", "ACC UDF1", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01capital", "ACC UDF1", true)%>
		<%	}	%>
					</TD>
<% }  else  if ( auto_accounting_active.equals("NOCAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC01nocapital")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC01nocapital")) {	%>
						<A HREF="javascript: browseGLAccount('Account_fld1', '<%=ac01Type%>'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01nocapital", "Entity", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01nocapital", "ACC UDF1", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01nocapital", "ACC UDF1", true)%>
		<%	}	%>
					</TD>
<% 	} 	else { %>
					<tsa:td field="AC01">
						<tsa:label labelName="AC01" fieldName="Account_fld1"/>
					</tsa:td>
<%	}	%>

<% if ( auto_accounting_active.equals("CAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC02capital")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC02capital") && !(HiltonUtility.ckNull( (String) request.getAttribute("GLAccount_ROnly"))).equals("1")) {	%>
					<A HREF="javascript: browseENDP(); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02capital", "ACC UDF2", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02capital", "ACC UDF2", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02capital", "ACC UDF2", true)%>
		<%	}	%>
					</TD>
<% }  else if ( auto_accounting_active.equals("NOCAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC02nocapital")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC02nocapital")) {	%>
					<A HREF="javascript: browseENDP(); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02nocapital", "ACC UDF2", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02nocapital", "ACC UDF2", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02nocapital", "ACC UDF2", true)%>
		<%	}	%>
					</TD>
<% 	} else { %>
					<tsa:td field="AC02">
						<tsa:label labelName="AC02" fieldName="Account_fld2"/>
					</tsa:td>
<%	}	%>

<% if ( auto_accounting_active.equals("CAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC03capital")%> NOWRAP>
		<%	String fld3Label = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03capital", "ACC UDF3");
		if (DictionaryManager.isLink(oid, labelPrefix + "-AC03capital")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld3', '<%=ac03Type%>'); void(0);" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC03instructions", "Click here to choose the value for " + fld3Label + " or enter the value in the box below.")%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03capital", "ACC UDF3", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03capital", "ACC UDF3", true)%>
		<%	}	%>
					</TD>
<% } else if ( auto_accounting_active.equals("NOCAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC03capital")%> NOWRAP>
		<%	String fld3Label = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03nocapital", "ACC UDF3");
		if (DictionaryManager.isLink(oid, labelPrefix + "-AC03nocapital")) {	%>
						<A HREF="javascript: browseCMOD(); void(0);" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC03instructions", "Click here to choose the value for " + fld3Label + " or enter the value in the box below.")%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03nocapital", "ACC UDF3", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03nocapital", "ACC UDF3", true)%>
		<%	}	%>
					</TD>
<% } 	else { %>
					<tsa:td field="AC03">
						<tsa:label labelName="${labelPrefix}-AC03" fieldName="Account_fld3"/>
					</tsa:td>
<% } %>

<% if ( auto_accounting_active.equals("CAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC04capital")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC04")) {	%>
						<A HREF="javascript: browseCARP(); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04capital", "ACC UDF4", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04capital", "ACC UDF4", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04capital", "ACC UDF4", true)%>
		<%	}	%>
					</TD>
<% } else if ( auto_accounting_active.equals("NOCAPITAL") ) {
%>
					<TD <%=HtmlWriter.isVisible(oid, "-AC04nocapital")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC04nocapital")) {	%>
						<A HREF="javascript: browseDIVS('Account_fld4', 'autotable_divs_event'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04nocapital", "ACC UDF4", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04nocapital", "ACC UDF4", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04nocapital", "ACC UDF4", true)%>
		<%	}	%>
					</TD>
<% }  else { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC04")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC04")) {	%>
		<%		if (codaEnabled) { %>
						<A HREF="javascript: browseCodaElements('Account_fld4', 'coda-finder-element'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", true)%></A>
		<% } else { %>
						<%--A HREF="javascript: browseAccountFld('Account_fld4', '<%=ac04Type%>'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", true)%></A--%>
						<A HREF="javascript: browseFld4Xref(); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", true)%></A>
		<% }
		} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", true)%>
		<%	}	%>
					</TD>
<% } %>

<% if ( auto_accounting_active.equals("CAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC05capital")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC05capital")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld5', '<%=ac05Type%>'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05capital", "ACC UDF5", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05capital", "ACC UDF5", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05capital", "ACC UDF5", true)%>
		<%	}	%>
					</TD>
<% }  else if ( auto_accounting_active.equals("NOCAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC05nocapital")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC05nocapital")) {	%>
						<A HREF="javascript: browseDIVS('Account_fld5', 'autotable_divs_performance'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05nocapital", "ACC UDF5", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05nocapital", "ACC UDF5", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05nocapital", "ACC UDF5", true)%>
		<%	}	%>
					</TD>
<% }  else { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC05")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC05")) {	%>
						<A HREF="javascript: browseSOS(); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05", "ACC UDF5", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05", "ACC UDF5", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05", "ACC UDF5", true)%>
		<%	}	%>
					</TD>
<% } %>

<% if ( auto_accounting_active.equals("CAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC06")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC06capital")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld6', 'AC06'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06capital", "ACC UDF6", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", true)%>
		<%	}	%>
					</TD>
<% } else if ( auto_accounting_active.equals("NOCAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC06")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC06nocapital")) {	%>
						<A HREF="javascript: browseDIVS('Account_fld6', 'autotable_divs_program'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06nocapital", "ACC UDF6", true)%></A>
		<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", true)%>
		<%	}	%>
					</TD>
<% } else { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC06")%> NOWRAP>
		<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC06")) {	%>
		<%		if (codaEnabled) { %>
						<A HREF="javascript: browseCodaElements('Account_fld6', 'coda-finder-element'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", true)%></A>
		<% } else { %>
						<A HREF="javascript: browseAccountFld('Account_fld6', 'AC06'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", true)%></A>
		<% }
		} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", true)%>
		<%	}	%>
					</TD>
<% } %>

					<TD <%=HtmlWriter.isVisible(oid, "AC07")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC07")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld7', 'AC07'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC07", "ACC UDF7", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC07", "ACC UDF7", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC07", "ACC UDF7", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC08")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC08")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld8', 'AC08'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC08", "ACC UDF8", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC08", "ACC UDF8", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC08", "ACC UDF8", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC09")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC09")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld9', 'AC09'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC09", "ACC UDF9", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC09", "ACC UDF9", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC09", "ACC UDF9", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC10")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC10")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld10', 'AC10'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC10", "ACC UDF10", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC10", "ACC UDF10", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC10", "ACC UDF10", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC11")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC11")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld11', 'AC11'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC11", "ACC UDF11", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC11", "ACC UDF11", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC11", "ACC UDF11", true)%>
<%	}	%>					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC12")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC12")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld12', 'AC12'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC12", "ACC UDF12", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC12", "ACC UDF12", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC12", "ACC UDF12", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC13")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC13")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld13', 'AC13'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC13", "ACC UDF13", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC13", "ACC UDF13", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC13", "ACC UDF13", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC14")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC14")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld14', 'AC14'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC14", "ACC UDF14", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC14", "ACC UDF14", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC14", "ACC UDF14", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC15")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC15")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld15', 'AC15'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC15", "ACC UDF15", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC15", "ACC UDF15", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC15", "ACC UDF15", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "statusAccount")%> NOWRAP WIDTH=10px ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "statusAccount", "  ")%></TD>
					<TD <%=HtmlWriter.isVisible(oid, "allocationPercent")%> NOWRAP WIDTH=75px ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationPercent", "Percent")%></TD>
					<TD <%=HtmlWriter.isVisible(oid, "allocationAmount")%> NOWRAP WIDTH=100px ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationAmount", "Amount")%></TD>
					<TD WIDTH=10px ><IMG SRC="<%=contextPath%>/images/none.gif" border=0></TD>
				</TR>
<%
	String	s_set_row = "";
	int i_rowcount = 0;

	for (int i = 0; i < accountList.size(); i++) {
		Account account = (Account) accountList.get(i);
		AccountPK accountPk = (AccountPK) account.getComp_id();
		String	s_alloc_title = account.getAccountTitle();
		s_alloc_method = account.getAllocMethod();
		s_set_row = "ONFOCUS='setCurrentRow(" + i_rowcount + ");'";

		if (s_alloc_method.length() <= 0) {
			if ( s_alloc_by_qty.equals("Y") ) {
				s_alloc_method = "Q" + accountType.substring(accountType.length() - 1);
				if (accountType.equals("IVT") || accountType.equals("IVS") || accountType.equals("IVO") || accountType.equals("IVU"))
				{
					s_alloc_method = "QH";
				}
			}
			else {
				s_alloc_method = "P" + accountType.substring(accountType.length() - 1);
				if (accountType.equals("IVT") || accountType.equals("IVS") || accountType.equals("IVO") || accountType.equals("IVU"))
				{
					s_alloc_method = "PH";
				}
			}
		}

		String isDisabledForCapital = "";
		String getCapitalAutoaccountingInfo = "";
		if ( HiltonUtility.ckNull((String)request.getAttribute("autoAccountingActive")).equals("CAPITAL"))
		{
			isDisabledForCapital = "DISABLED";
			getCapitalAutoaccountingInfo = "getCapitalAutoaccountingInfo(this)";
		}

%>
				<TR>
					<TD ID=acc_num_<%=i%> ALIGN="right"><%=i+1%>
						<tsa:hidden name="Account_sequence" value="<%=accountPk.getSequence()%>"/>
						<tsa:hidden name="Account_accountType" value="<%=accountType%>"/>
						<tsa:hidden name="Account_allocMethod" value="<%=s_alloc_method%>"/>
					</TD>
					<%if ( auto_accounting_active.equals("CAPITAL") || auto_accounting_active.equals("NOCAPITAL") ) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC01")%> ID=acc_1 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld1" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld1())%>" ONCHANGE="upperCase(this);setStatus(); resetDepartment(); " <%=s_set_row%> ></TD>
					<% } else { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC01")%> ID=acc_1 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld1" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld1())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%> <%=labViewGroup%> ></TD>
					<% } %>
					<% if (oid.equalsIgnoreCase("bly07p")) { %>
					<TD <%=HtmlWriter.isVisible(oid, "AC02")%> ID=acc_2 ALIGN="LEFT">
						<INPUT TYPE="TEXT" NAME="Account_fld2" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld2())%>" ONCHANGE="upperCase(this); getQxrefInfo(<%=i%>);" <%=s_set_row%> <% if ( HiltonUtility.ckNull(((String)request.getAttribute("GLAccount_ROnly"))).equals("1")){ %>READONLY<%}%>>
					</TD>
					<% } else {
						String size = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02-FIELDLENGTH", "10", false);
						if (size == null || "null".equals(size)) size = "10"; %>
					<TD <%=HtmlWriter.isVisible(oid, "AC02")%> ID=acc_2 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld2" SIZE="<%=size %>" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld2())%>" ONCHANGE="upperCase(this); setStatus(); getQxrefInfo(<%=i%>);" <%=s_set_row%> ></TD>
					<% }
					String maxLength = DictionaryManager.getLabelsInstance(oid,language).getLabel(oid, labelPrefix + "-AC03-FIELDLENGTH", "15", false);
					if (maxLength == null || "null".equals(maxLength)) maxLength = "15";%>
					<TD <%=HtmlWriter.isVisible(oid, "AC03")%> ID=acc_3 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld3" SIZE="10" MAXLENGTH="<%=maxLength %>" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld3())%>" ONCHANGE="upperCase(this);setStatus(); getXrefCode4Info(<%=i%>);" <%=s_set_row%> <%=isDisabledForCapital %>></TD>
<%					String size = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04-FIELDLENGTH", "10", false);
					if (size == null || "null".equals(size)) size = "10"; %>

					<% if(HiltonUtility.isEmpty(account.getFld4()) && !s_account_default_fld4.equalsIgnoreCase("N")) { %>
						<TD <%=HtmlWriter.isVisible(oid, "AC04")%> ID=acc_4 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld4" SIZE="<%=size %>" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(s_account_default_fld4)%>" ONCHANGE="upperCase(this);setStatus(); setProjectCode(this.value);<%=getCapitalAutoaccountingInfo%>;" <%=s_set_row%>></TD>
					<% } else { %>
						<TD <%=HtmlWriter.isVisible(oid, "AC04")%> ID=acc_4 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld4" SIZE="<%= size %>" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld4())%>" ONCHANGE="upperCase(this);setStatus(); setProjectCode(this.value);<%=getCapitalAutoaccountingInfo%>;" <%=s_set_row%>></TD>
					<% } %>
					<TD <%=HtmlWriter.isVisible(oid, "AC05")%> ID=acc_5 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld5" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld5())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%> <%=isDisabledForCapital %>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC06")%> ID=acc_6 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld6" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld6())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%> <%=isDisabledForCapital %>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC07")%> ID=acc_7 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld7" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld7())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC08")%> ID=acc_8 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld8" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld8())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC09")%> ID=acc_9 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld9" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld9())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC10")%> ID=acc_10 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld10" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld10())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC11")%> ID=acc_11 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld11" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld11())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC12")%> ID=acc_12 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld12" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld12())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC13")%> ID=acc_13 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld13" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld13())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC14")%> ID=acc_14 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld14" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld14())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC15")%> ID=acc_15 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld15" SIZE="10" MAXLENGTH="15" value="<%=headerEncoder.encodeForHTMLAttribute(account.getFld15())%>" ONCHANGE="upperCase(this);setStatus();" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "statusAccount")%> ID=acc_status ALIGN="LEFT" width=10px><tsa:hidden name="Account_status" value="<%=account.getStatus()%>"/></TD>
					<TD <%=HtmlWriter.isVisible(oid, "allocationPercent")%> ID=as_perc_tot ALIGN="RIGHT"><DIV ID="percent<%=i%>"><INPUT TYPE="TEXT" ID="allocPercent_<%=i%>" NAME="Account_allocPercent" SIZE="10" MAXLENGTH="25" value="<%=HiltonUtility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>" style="text-align:right" ONCHANGE="addUp(<%=i%>); "></DIV>
					<DIV ID="percentAmt<%=i%>"><tsa:hidden id="allocAmountP_<%=i%>" name="Account_amountLine" value="<%=account.getAmountLine()%>"/></DIV></TD>
					<TD <%=HtmlWriter.isVisible(oid, "allocationAmount")%> ID=as_perc_amt ALIGN="RIGHT"><INPUT TYPE="TEXT" ID="allocAmount_<%=i%>" NAME="Account_allocAmount" SIZE="15" MAXLENGTH="25" value="<%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%>" style="text-align:right" ONCHANGE="addUp(<%=i%>);"></TD>
					<tsa:hidden name="Account_allocQty" value="<%=HiltonUtility.getFormattedPrice(account.getAllocQty(), oid)%>"/>
		<%		if (allowEdit) {
					String s_allow_accounts = propertiesManager.getProperty(labelPrefix.toUpperCase() + " OPTIONS", "SINGLE ACCOUNT ALLOCATION", "N");
					if(s_allow_accounts.equalsIgnoreCase("N")) { %>
					<TD ID=acc_del_<%=i%>><A href="javascript: if (confirm('Are you sure you wish to delete this Account?')) { deleteMe(<%=i%>); } void(0);" TABINDEX="999"><IMG SRC="<%=contextPath%>/images/delete.gif" ALT="Delete" BORDER="0"></A></TD>
		<%			} else { %>
					<td><img src="<%=contextPath%>/images/none.gif" width=10px height=1px></td>
		<%			}
				} else { %>
					<td><img src="<%=contextPath%>/images/none.gif" width=10px height=1px></td>
		<%		}
					if (budgetActive && budgetView && ((s_commodity.trim().length() > 0 && budgetCommodity) || (budgetCommodity == false))) { %>
					<TD ID=acc_bgt_<%=i%>><A href="javascript: showBudget(<%=i%>);  void(0);" TABINDEX="999"><IMG SRC="<%=contextPath%>/images/budget_view.gif" ALT="View Budget" BORDER="0"></A></TD>
		<%		}  %>

		<%      if(s_account_cds_description.equalsIgnoreCase("Y")) { %>
				</TR>
					<TR>
						<TD <%=HtmlWriter.isVisible(oid, "ACTITLE")%> Colspan=2 WIDTH=100px ALIGN="Center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ACTITLE", "CDS Description")%>:</TD>
						<TD <%=HtmlWriter.isVisible(oid, "ACTITLE")%> Colspan=8 ID=acc_title ALIGN="LEFT"><INPUT TYPE="TEXT" ID="acctitle_<%=i%>" NAME="Account_accountTitle" SIZE="100" MAXLENGTH="20" value="<%=HiltonUtility.ckNull(account.getAccountTitle())%>" style="text-align:left" ONCHANGE="addUp(<%=i%>);" ></TD>
					</TR>
		<%		} else { %>

					<tsa:hidden name="Account_accountTitle" value="<%=account.getAccountTitle()%>"/>
				</tr>
		<% 		}
				i_rowcount++;
				}%>
				</TABLE>

				<HR ALIGN="CENTER" WIDTH=95%>

				<TABLE BORDER="0" CELLPADDING="2" WIDTH=100%>
				<TR>
					<TD WIDTH=15px>&nbsp;</TD>
		<%	if (allowEdit) {
				String s_allow_accounts = propertiesManager.getProperty(labelPrefix.toUpperCase() + " OPTIONS", "SINGLE ACCOUNT ALLOCATION", "N");
				if(s_allow_accounts.equalsIgnoreCase("N")) { %>
					<TD ALIGN="LEFT"><A HREF="javascript: addNew(); void(0);"><FONT CLASS="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnew", "Add new")%></B></FONT></A></TD>
					<TD WIDTH="175px" ALIGN="CENTER"><A HREF="javascript: deleteEmptyRows(); browseStdAccounts(); void(0);"><FONT CLASS="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addstdaccount", "Add standard account")%></B></FONT></A></TD>
					<TD ALIGN="RIGHT"><A HREF="javascript: deleteAll(); void(0);"><FONT CLASS="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delall", "Delete all")%></B></FONT></A></TD>
		<%		} else { %>
					<TD>&nbsp;</TD>
		<%		}
			} else {%>
					<TD>&nbsp;</TD>
		<%	}
				if (!s_alloc_by_qty.equals("Y")) { %>
					<TD WIDTH=75px ALIGN="RIGHT"><INPUT TYPE="TEXT" NAME="as_perc_tot" SIZE="10" MAXLENGTH="10" value="100.00" STYLE="text-align:right" ONFOCUS="this.blur();" disabled ></TD>
					<TD WIDTH=100px ALIGN="RIGHT"><INPUT TYPE="TEXT" NAME="as_amt_tot" SIZE="15" MAXLENGTH="15" value="<%=bd_amount_to_allocate%>" STYLE="text-align:right" ONFOCUS="this.blur();" disabled></TD>
					<TD WIDTH=10px>
						<tsa:hidden name="as_tot" value="<%=bd_amount_to_allocate%>"/>
						<tsa:hidden name="as_qty_tot" value=""/>
					</TD>
		<%	} else { %>
					<TD WIDTH=75px ALIGN="RIGHT"><tsa:hidden name="as_perc_tot" value="100.00"/></TD>
					<TD WIDTH=100px ALIGN="RIGHT"><tsa:hidden name="as_amt_tot" value="<%=bd_amount_to_allocate%>"/></TD>
					<TD WIDTH=10px>
						<tsa:hidden name="as_tot" value=""/>
						<tsa:hidden name="as_qty_tot" value=""/>
					</TD>
		<%	}%>
				</TR>
				</TABLE>
				<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>
			</TD>
		</TR>
		</TABLE>


<SCRIPT value=JavaScript>
<!-- Hide script

	var accountFld1Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld1", "systable")%>";
	var accountFld2Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld2", "systable")%>";
	var accountFld3Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld3", "systable")%>";
	var accountFld4Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld4", "systable")%>";
	var accountFld5Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld5", "systable")%>";
	var accountFld6Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld6", "systable")%>";
	var accountFld7Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld7", "systable")%>";
	var accountFld8Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld8", "systable")%>";
	var accountFld9Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld9", "systable")%>";
	var accountFld10Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld10", "systable")%>";
	var accountFld11Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld11", "systable")%>";
	var accountFld12Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld12", "systable")%>";
	var accountFld13Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld13", "systable")%>";
	var accountFld14Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld14", "systable")%>";
	var accountFld15Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld15", "systable")%>";

	var tableType = "<%=tableType%>";

	function showBudget(row) {
		var burl = "/budget/budget_view.jsp" ;
		var sep = "<%=s_accsep%>" ;
		var fy = "<%=headerEncoder.encodeForJavaScript(s_fiscal_year)%>" ;
		var commodity = "<%=s_commodity%>" ;
		var sz = <%=accountList.size()%> ;
		if (sz > 1) {
		popupParameters = "accountString=" +
				frm.Account_fld1[row].value + sep +
				 frm.Account_fld2[row].value +  sep +
				 frm.Account_fld3[row].value +  sep +
				 frm.Account_fld4[row].value +  sep +
				 frm.Account_fld5[row].value +  sep +
				 frm.Account_fld6[row].value +  sep +
				 frm.Account_fld7[row].value +  sep +
				 frm.Account_fld8[row].value +  sep +
				 frm.Account_fld9[row].value +  sep +
				 frm.Account_fld10[row].value +  sep +
				 frm.Account_fld11[row].value +  sep +
				 frm.Account_fld12[row].value +  sep +
				 frm.Account_fld13[row].value +  sep +
				 frm.Account_fld14[row].value +  sep +
				 frm.Account_fld15[row].value +  sep +
				 ";fiscalYear=" + fy +
				 ";commodity=" + commodity ;


		} else {
		popupParameters = "accountString=" +
				frm.Account_fld1.value + sep +
				 frm.Account_fld2.value +  sep +
				 frm.Account_fld3.value +  sep +
				 frm.Account_fld4.value +  sep +
				 frm.Account_fld5.value +  sep +
				 frm.Account_fld6.value +  sep +
				 frm.Account_fld7.value +  sep +
				 frm.Account_fld8.value +  sep +
				 frm.Account_fld9.value +  sep +
				 frm.Account_fld10.value +  sep +
				 frm.Account_fld11.value +  sep +
				 frm.Account_fld12.value +  sep +
				 frm.Account_fld13.value +  sep +
				 frm.Account_fld14.value +  sep +
				 frm.Account_fld15.value +  sep +
				 ";fiscalYear=" + fy +
				 ";commodity=" + commodity ;
		}
		doSubmitToPopup(burl, "BudgetCenterRetrieveView", 'WIDTH=550', 'HEIGHT=300') ;
	}

	function browseGlAccountFld(frmField, udf) {
		<% if (restrictGlAccount) { %>
			popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + udf + ";logicalOperator=AND;originalFilter=Y;sort=N;"
			popupParameters = popupParameters + "tableType=" + udf + ";";
			browseLookup(frmField, 'stdtable-it-account');
		<% } else { %>
			browseAccountFld(frmField, udf);
		<% } %>
	}

	function browseCARP() {
		popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + "<%=divisionCapital%>" + "';logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup('Account_fld4', 'car_project');
	}

 function browseCMOD() {
	//popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + "<%=s_division%>" + "';logicalOperator=AND;originalFilter=N;sort=N;";
	<% if (s_commodidty_code==null || s_commodidty_code.equals("")){ %>
		browseAccountFld('Account_fld3', '<%=ac03Type%>')
		return;
	<% } %>
	popupParameters = popupParameters + "colname=XrefCombo_code3;operator==;filter_txt='" + "<%=s_commodidty_code%>" + "';logicalOperator=AND;originalFilter=N;sort=N;";
	if (maxRows == 1) {
		popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld1.value + "';logicalOperator=AND;originalFilter=N;sort=N;";
	} else {
		popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld1[currentRow].value + "';logicalOperator=AND;originalFilter=N;sort=N;";
	}
	browseLookup('Account_fld3', 'autotable_cmod');
   }
   function browseENDP() {
	//popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + "<%=s_division%>" + "';logicalOperator=AND;originalFilter=N;sort=N;";
	if (maxRows == 1) {
		popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld1.value + "';logicalOperator=AND;originalFilter=N;sort=N;";
	} else {
			popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld1[currentRow].value + "';logicalOperator=AND;originalFilter=N;sort=N;";
	}
	browseLookup('Account_fld2', 'autotable_endp');
   }

   function browseGLAccount() {
		<%if (s_account_browse_xref_combo.equals("Y")) { %>
			if (maxRows == 1) {
				if (frm.Account_fld1 != null && frm.Account_fld1.value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld1.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
				}

				if (frm.Account_fld2 != null && frm.Account_fld2.value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld2.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
				}

				if (frm.Account_fld3 != null && frm.Account_fld3.value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code3;operator==;filter_txt='" + frm.Account_fld3.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
				}
			} else {
				if (frm.Account_fld1[currentRow] != null && frm.Account_fld1[currentRow].value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld1[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
				}

				if (frm.Account_fld2[currentRow] != null && frm.Account_fld2[currentRow].value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld2[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
				}

				if (frm.Account_fld3[currentRow] != null && frm.Account_fld3[currentRow].value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code3;operator==;filter_txt='" + frm.Account_fld3[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
				}
			}
			browseLookup('Account_fld1', 'glaccount_xref');
		<% } else { %>
			var tableType = "<%=ac01Type%>";
			if (maxRows == 1) {
				if(frm.Account_fld5 != null && frm.Account_fld5.value != ""){
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld5.value + "';logicalOperator=AND;originalFilter=N;sort=N;";
				}
				else{
					/*popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + tableType + ";logicalOperator=AND;originalFilter=Y;sort=N;"*/
					browseAccountFld('Account_fld1', '<%=ac01Type%>');
					return;
				}
			}

			else{
				if(frm.Account_fld5[currentRow] != null && frm.Account_fld5[currentRow].value != ""){
						popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld5[currentRow].value + "';logicalOperator=AND;originalFilter=N;sort=N;";
				}
				else{
					/*popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + tableType + ";logicalOperator=AND;originalFilter=Y;sort=N;"*/
					browseAccountFld('Account_fld1', '<%=ac01Type%>');
					return;
				}
			}
			browseLookup('Account_fld1', 'glaccount_sos');
		<% } %>
	}

   function browseLookupLocationXref(fieldName, browseName, func) {
	   <%if (s_account_browse_xref_combo.equals("Y")) { %>
		   if (maxRows == 1) {
				if (frm.Account_fld1 != null && frm.Account_fld1.value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld1.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					browseLookup(fieldName, browseName);
				} else {
					if (func == 'browseLookup') {
						browseLookup(fieldName, 'ship_to');
					} else {
						browseAccountFld(fieldName, '<%=ac02Type%>');
					}
				}
			} else {
				if (frm.Account_fld1[currentRow] != null && frm.Account_fld1[currentRow].value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld1[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					browseLookup(fieldName, browseName);
				} else {
					if (func == 'browseLookup') {
						browseLookup(fieldName, 'ship_to');
					} else {
						browseAccountFld(fieldName, '<%=ac02Type%>');
					}
				}
			}
	   <% } else { %>
		   	if (func == 'browseLookup') {
				browseLookup(fieldName, 'ship_to');
			} else {
				browseAccountFld(fieldName, '<%=ac02Type%>');
			}
	   <% } %>
	}

	function browseDivDeptXref() {
		<%if (s_account_browse_xref_combo.equals("Y")) { %>
			if (maxRows == 1) {
				if (frm.Account_fld1 != null && frm.Account_fld1.value != ""
						&& frm.Account_fld2 != null && frm.Account_fld2.value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld1.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld2.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					browseLookup('Account_fld3', 'divdept_xref');
				} else {
					browseAccountFld('Account_fld3', '<%=ac03Type%>');
				}
			} else {
				if (frm.Account_fld1[currentRow] != null && frm.Account_fld1[currentRow].value != ""
						&&frm.Account_fld2[currentRow] != null && frm.Account_fld2[currentRow].value != "") {
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld1[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld2[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					browseLookup('Account_fld3', 'divdept_xref');
				} else {
					browseAccountFld('Account_fld3', '<%=ac03Type%>');
				}
			}
		<% } else { %>
			browseAccountFld('Account_fld3', '<%=ac03Type%>');
   <% } %>
	}

	function browseFld4Xref() {
		<%if (s_account_browse_xref_combo.equals("Y")) { %>
			if (maxRows == 1) {
				if (frm.Account_fld1 != null && frm.Account_fld1.value != ""
						&& frm.Account_fld2 != null && frm.Account_fld2.value != ""
						&& frm.Account_fld3 != null && frm.Account_fld3.value != ""
						&& "<%=propertiesManager.getProperty("ACCOUNTS", "ACCOUNT_BROWSE_FROM_AC04", "N")%>" == "N") {
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld1.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld2.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					popupParameters = popupParameters + "colname=XrefCombo_code3;operator==;filter_txt='" + frm.Account_fld3.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					browseLookup('Account_fld4', 'fld4_xref');
				} else {
					browseAccountFld('Account_fld4', '<%=ac04Type%>');
				}
			} else {
				if (frm.Account_fld1[currentRow] != null && frm.Account_fld1[currentRow].value != ""
						&& frm.Account_fld2[currentRow] != null && frm.Account_fld2[currentRow].value != ""
						&& frm.Account_fld3[currentRow] != null && frm.Account_fld3[currentRow].value != ""
						&& "<%=propertiesManager.getProperty("ACCOUNTS", "ACCOUNT_BROWSE_FROM_AC04", "N")%>" == "N") {
					popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.Account_fld1[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.Account_fld2[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					popupParameters = popupParameters + "colname=XrefCombo_code3;operator==;filter_txt='" + frm.Account_fld3[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
					browseLookup('Account_fld4', 'fld4_xref');
				} else {
					browseAccountFld('Account_fld4', '<%=ac04Type%>');
				}
			}
		<% } else { %>
			browseAccountFld('Account_fld4', '<%=ac04Type%>');
   <% } %>
	}

	function browseSOS() {
		<%if (s_account_browse_xref_combo.equals("Y")) { %>
			if (maxRows == 1) {
				if(frm.Account_fld1 != null && frm.Account_fld1.value != "")
					popupParameters = popupParameters + "colname=XrefCombo_code8;operator==;filter_txt='" + frm.Account_fld1.value + "';logicalOperator=AND;originalFilter=N;sort=N;";
			}
			else{
				if(frm.Account_fld1[currentRow] != null && frm.Account_fld1[currentRow].value != "")
						popupParameters = popupParameters + "colname=XrefCombo_code8;operator==;filter_txt='" + frm.Account_fld1[currentRow].value + "';logicalOperator=AND;originalFilter=N;sort=N;";
			}

			browseLookup('Account_fld5', 'sos_glaccount');
		<% } else { %>
			browseAccountFld('Account_fld5', '<%=ac05Type%>');
		<% } %>
	}

function browseDIVS( fldName, browseName ) {
	if (maxRows == 1) {
		popupParameters = popupParameters + "Account_fld1="+frm.Account_fld1.value+";";
		popupParameters = popupParameters + "Account_fld2="+frm.Account_fld2.value+";";
		popupParameters = popupParameters + "Account_fld3="+frm.Account_fld3.value+";";
		popupParameters = popupParameters + "Account_fld4="+frm.Account_fld4.value+";";
		popupParameters = popupParameters + "Account_fld5="+frm.Account_fld5.value+";";
	} else {
		popupParameters = popupParameters + "Account_fld1="+frm.Account_fld1[currentRow].value+";";
		popupParameters = popupParameters + "Account_fld2="+frm.Account_fld2[currentRow].value+";";
		popupParameters = popupParameters + "Account_fld3="+frm.Account_fld3[currentRow].value+";";
		popupParameters = popupParameters + "Account_fld4="+frm.Account_fld4[currentRow].value+";";
		popupParameters = popupParameters + "Account_fld5="+frm.Account_fld5[currentRow].value+";";
	}
	browseLookup( fldName, browseName );
}

function browseCodaElements(fldName, browseName) {
	len = fldName.length;

	popupParameters = popupParameters + "codaLevel="+ fldName.substring(11)+";";
	browseLookup( fldName, browseName ) ;
}

   function resetDepartment(){
   	if (maxRows == 1) {
   		frm.Account_fld2.value = "";
   		//frm.Account_fld3.value = "";
   	}
   	else
   	{
   		frm.Account_fld2[currentRow].value = "";
   		//frm.Account_fld3[currentRow].value = "";
   	}
   }

   function getCapitalAutoaccountingInfo(fld){
		popupParameters = "XrefCombo_code3=" + fld.value + ";XrefCombo_code1=<%=divisionCapital %>" ;

		setLookupParameters('/base/get_autoaccountingcapital_info.jsp', 'XrefComboRetrieveBy');
		displayArea('getInfoFrame');
		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
   }

// End Hide script -->
</SCRIPT>

<%@ include file="/base/account_rows.jsp"%>