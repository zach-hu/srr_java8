<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String tableType = "AC";
	String s_dept_code = "";
	String  s_buyer_code="";
	boolean allowEdit = true;
	String	labelPrefix = "ivc";
	String	accountType = "IVH";
	boolean newAccount = false;

	String	s_alloc_by_qty = propertiesManager.getProperty("MISC", "ALLOCBYQTY", "N");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_action = HiltonUtility.ckNull((String) request.getAttribute("accountAction"));
	String	s_alloc_method = "";
	BigDecimal	bd_amount_to_allocate = new BigDecimal(0.00);
	List accountList = (List) request.getAttribute("useTaxAccountList");
	if (accountList == null) {
		accountList = new ArrayList();
	}
	if (accountList.size() > 0) {
		Account account = (Account) accountList.get(0);
		s_alloc_method = account.getAllocMethod();
	}
	else
	{
		newAccount = true;
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
%>

<tsa:hidden name="as_alloc_method" value="<%=s_alloc_method%>"/>
<tsa:hidden name="alloc_method" value="PH" <% if (s_alloc_method.substring(0, 1).equals("P")) { %>CHECKED<%}%> />
<tsa:hidden name="alloc_method" value="AH" <% if (s_alloc_method.substring(0, 1).equals("A")) { %>CHECKED<%}%> />
<tsa:hidden name="as_perc_tot" value="100.00"/>
<tsa:hidden name="newAccount" value="<%=newAccount%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteall" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "account_information", "Account Information")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>

		<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" WIDTH="500px" align="center">
		<TR>
			<TD VALIGN="TOP">
				<TABLE ID=accounts BORDER="0" CELLPADDING="2" WIDTH=100%>
				<TR VALIGN="MIDDLE">
					<TD WIDTH=15px>&nbsp;<tsa:hidden name="as_rows" value="<%=accountList.size()%>"/><tsa:hidden name="stdTable_tableType" value=""/></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC01")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC01")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld1', 'AC01'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01", "ACC UDF1", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01", "ACC UDF1", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC01", "ACC UDF1", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC02")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC02")) {	%>
					<A HREF="javascript: browseAccountFld('Account_fld2', 'AC02'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02", "ACC UDF2", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02", "ACC UDF2", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC02", "ACC UDF2", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC03")%> NOWRAP>
<%	String fld3Label = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03", "ACC UDF3");
		if (DictionaryManager.isLink(oid, labelPrefix + "-AC03")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld3', 'AC03'); void(0);" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC03instructions", "Click here to choose the value for " + fld3Label + " or enter the value in the box below.")%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03", "ACC UDF3", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC03", "ACC UDF3", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC04")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC04")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld4', 'AC04'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC04", "ACC UDF4", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC05")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC05")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld5', 'AC05'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05", "ACC UDF5", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05", "ACC UDF5", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC05", "ACC UDF5", true)%>
<%	}	%>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC06")%> NOWRAP>
<%	if (DictionaryManager.isLink(oid, labelPrefix + "-AC06")) {	%>
						<A HREF="javascript: browseAccountFld('Account_fld6', 'AC06'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", false)%> or enter the value in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", true)%></A>
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelPrefix + "-AC06", "ACC UDF6", true)%>
<%	}	%>
					</TD>
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
		if (i == 0)
		{
%>
			<tsa:hidden name="Account_icHeader" value="<%=accountPk.getIcHeader()%>"/>
			<tsa:hidden name="Account_icLine" value="0"/>
<%	}	%>
				<TR>
					<TD ID=acc_num_<%=i%> ALIGN="right"><%=i+1%>
						<tsa:hidden name="Account_sequence" value="<%=accountPk.getSequence()%>"/>
						<tsa:hidden name="Account_accountType" value="<%=accountType%>"/>
						<tsa:hidden name="Account_allocMethod" value="<%=s_alloc_method%>"/>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC01")%> ID=acc_1 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld1" SIZE="10" MAXLENGTH="15" value="<%=account.getFld1()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC02")%> ID=acc_2 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld2" SIZE="10" MAXLENGTH="15" value="<%=account.getFld2()%>" ONCHANGE="upperCase(this); getQxrefInfo(<%=i%>);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC03")%> ID=acc_3 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld3" SIZE="10" MAXLENGTH="15" value="<%=account.getFld3()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC04")%> ID=acc_4 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld4" SIZE="10" MAXLENGTH="15" value="<%=account.getFld4()%>" ONCHANGE="upperCase(this); setProjectCode(this.value);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC05")%> ID=acc_5 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld5" SIZE="10" MAXLENGTH="15" value="<%=account.getFld5()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC06")%> ID=acc_6 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld6" SIZE="10" MAXLENGTH="15" value="<%=account.getFld6()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC07")%> ID=acc_7 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld7" SIZE="10" MAXLENGTH="15" value="<%=account.getFld7()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC08")%> ID=acc_8 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld8" SIZE="10" MAXLENGTH="15" value="<%=account.getFld8()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC09")%> ID=acc_9 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld9" SIZE="10" MAXLENGTH="15" value="<%=account.getFld9()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC10")%> ID=acc_10 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld10" SIZE="10" MAXLENGTH="15" value="<%=account.getFld10()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC11")%> ID=acc_11 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld11" SIZE="10" MAXLENGTH="15" value="<%=account.getFld11()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC12")%> ID=acc_12 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld12" SIZE="10" MAXLENGTH="15" value="<%=account.getFld12()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC13")%> ID=acc_13 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld13" SIZE="10" MAXLENGTH="15" value="<%=account.getFld13()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC14")%> ID=acc_14 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld14" SIZE="10" MAXLENGTH="15" value="<%=account.getFld14()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC15")%> ID=acc_15 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld15" SIZE="10" MAXLENGTH="15" value="<%=account.getFld15()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<tsa:hidden name="Account_allocQty" value="<%=HiltonUtility.getFormattedQuantity(account.getAllocQty(), oid)%>"/>
					<tsa:hidden name="Account_accountTitle" value="<%=account.getAccountTitle()%>"/>
		<%		if (allowEdit) { %>
					<TD ID=acc_del_<%=i%>><A href="javascript: if (confirm('Are you sure you wish to delete this Account?')) { deleteMe(<%=i%>); } void(0);" TABINDEX="999"><IMG SRC="<%=contextPath%>/images/delete.gif" ALT="Delete" BORDER="0"></A></TD>
		<%		} else { %>
					<td><img src="<%=contextPath%>/images/none.gif" width=10px height=1px></td>
		<%		} %>
				</TR>
		<%		i_rowcount++;
				}%>
				</TABLE>

				<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>
			</TD>
		</TR>
		</TABLE>
	</td>
</tr>
</table>

<br>
<br>

<div id="hiddenFields" style="visibility:hidden; display:none;"></div>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/system_setup.jsp', 'UseTaxAccountUpdate;AutoGenRetrieveByGenYear'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/system_setup.jsp', 'AutoGenRetrieveByGenYear'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>


<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var myTable = document.getElementById("accounts");
	var TotalRows = myTable.rows.length - 1;
	var accRows = frm.as_rows.value;
	var maxRows = frm.as_maxrows.value;
	var allocMethod = frm.as_alloc_method.value;
	var qtyDec  = <%=s_quantity_decimals%>;
	var type = "HDR";  /*used for setting allocMethod in accounts.js*/
	var accountType = "<%=accountType%>";

	if (TotalRows == 0)
	{
		addNew();
	}

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


	function addNew()
	{

<%
		String accfld1 = "", accfld2 = "", accfld3 = "", accfld4 = "", accfld5 = "";
		String accfld6 = "", accfld7 = "", accfld8 = "", accfld9 = "", accfld10 = "";
		String accfld11 = "", accfld12 = "", accfld13 = "", accfld14 = "", accfld15 = "";

		String nameUdf1 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF1","");
		String nameUdf2 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF2","");
		String nameUdf3 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF3","");
		String nameUdf4 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF4","");
		String nameUdf5 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF5","");
		String dept = propertiesManager.getProperty("ACCT NAME UDFS", "DEPT","");

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

		if (oid.equalsIgnoreCase("vse06p"))
		{
			accfld1 = s_dept_code;
		}
		if (oid.equalsIgnoreCase("bsc04p"))
		{
			accfld3 = "000";
		}
%>

		frm.deleteall.value = "FALSE";
		myTable = document.getElementById("accounts");
		TotalRows = TotalRows+1;

		myRow = createRow(myTable);
		maxRows++;

		myCell = createCell(myRow);
		id = "acc_num_" + (maxRows - 1);
		myCell.id = id;
		myCell.align = "RIGHT";
		myCell.innerHTML = maxRows + "<INPUT TYPE=\"HIDDEN\" NAME=\"Account_sequence\" value=" + maxRows + "><INPUT TYPE=\"HIDDEN\" NAME=\"Account_accountType\" value=" + accountType + "><INPUT TYPE=\"HIDDEN\" NAME=\"Account_allocMethod\" value="+ allocMethod + "><INPUT TYPE=\"HIDDEN\" NAME=\"Account_accountTitle\" value=\"\">";

		myCell = createCell(myRow);
		id = "acc_1";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld1\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld1%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC01")%>
		<%=HtmlWriter.cellDisplay(oid, "AC01")%>

		myCell = createCell(myRow);
		id = "acc_2";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld2\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld2%>\" ONCHANGE=\"upperCase(this); getQxrefInfo(" + (maxRows - 1) + ");\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC02")%>
		<%=HtmlWriter.cellDisplay(oid, "AC02")%>

		myCell = createCell(myRow);
		id = "acc_3";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld3\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld3%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC03")%>
		<%=HtmlWriter.cellDisplay(oid, "AC03")%>

		myCell = createCell(myRow);
		id = "acc_4";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld4\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld4%>\" ONCHANGE=\"upperCase(this); setProjectCode(this.value);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC04")%>
		<%=HtmlWriter.cellDisplay(oid, "AC04")%>

		myCell = createCell(myRow);
		id = "acc_5";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld5\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld5%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC05")%>
		<%=HtmlWriter.cellDisplay(oid, "AC05")%>

		myCell = createCell(myRow);
		id = "acc_6";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld6\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld6%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC06")%>
		<%=HtmlWriter.cellDisplay(oid, "AC06")%>

		myCell = createCell(myRow);
		id = "acc_7";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld7\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld7%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC07")%>
		<%=HtmlWriter.cellDisplay(oid, "AC07")%>

		myCell = createCell(myRow);
		id = "acc_8";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld8\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld8%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC08")%>
		<%=HtmlWriter.cellDisplay(oid, "AC08")%>

		myCell = createCell(myRow);
		id = "acc_9";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld9\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld9%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC09")%>
		<%=HtmlWriter.cellDisplay(oid, "AC09")%>

		myCell = createCell(myRow);
		id = "acc_10";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld10\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld10%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC10")%>
		<%=HtmlWriter.cellDisplay(oid, "AC10")%>

		myCell = createCell(myRow);
		id = "acc_11";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld11\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld11%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC11")%>
		<%=HtmlWriter.cellDisplay(oid, "AC11")%>

		myCell = createCell(myRow);
		id = "acc_12";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld12\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld12%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC12")%>
		<%=HtmlWriter.cellDisplay(oid, "AC12")%>

		myCell = createCell(myRow);
		id = "acc_13";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld13\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld13%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC13")%>
		<%=HtmlWriter.cellDisplay(oid, "AC13")%>

		myCell = createCell(myRow);
		id = "acc_14";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld14\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld14%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC14")%>
		<%=HtmlWriter.cellDisplay(oid, "AC14")%>

		myCell = createCell(myRow);
		id = "acc_15";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld15\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"<%=accfld15%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC15")%>
		<%=HtmlWriter.cellDisplay(oid, "AC15")%>

		myCell = createCell(myRow);
		id = "acc_del_" + (maxRows - 1);
		myCell.id = id;
		myCell.innerHTML = "<A href=\"javascript: if (confirm('Are you sure you wish to delete this Account?')) { deleteMe(" + (maxRows - 1) + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

		setCurrentRow(maxRows - 1);
	}

	function deleteMe(row)
	{
			var accRows = maxRows;

			myTable = document.getElementById("accounts");

			if (accRows == 0)
			{
				return;
			}

			deleteRow(myTable, accRows);

			accRows = accRows - 1;

			if (accRows <= 0)
			{
				frm.deleteall.value = "TRUE";
			}

			maxRows = maxRows - 1;	//needs to be set so that when validateForm is called it has the appropriate row count
	}

	function validateForm()
	{
		return true;
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

// End Hide script -->
</SCRIPT>
