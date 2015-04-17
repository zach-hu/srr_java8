<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_alloc_by_qty = propertiesManager.getProperty("MISC", "ALLOCBYQTY", "N");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String auto_accounting_active = HiltonUtility.ckNull((String)request.getAttribute("autoAccountingActive"));
	String	s_catalog_id = (String) request.getAttribute("Catalog_catalogId");
	String	s_amount_to_allocate = (String) request.getAttribute("amountToAllocate");
	String	s_alloc_method = "";
	String tableType = "AC";
	String	s_dept_code = "";
	String  s_buyer_code = "";
	String labViewGroup = "";
	String	s_account_action = (String) request.getAttribute("accountAction");
	BigDecimal	bd_amount_to_allocate = HiltonUtility.getFormattedDollar(new BigDecimal(0), oid);
	UserRole roleR = UserManager.getInstance().getUserRole(oid,uid);
	String accountType ="";

	if (s_amount_to_allocate != null)
	{
		bd_amount_to_allocate = HiltonUtility.getFormattedDollar(new BigDecimal(s_amount_to_allocate), oid);
	}
	String	s_set_row = "";

	List accList = (List) request.getAttribute("accountList");
	Account catAccount = null;
	boolean newAccount = false;

	if (accList == null || accList.size() <= 0)
	{
		catAccount = new Account();
		AccountPK comp_id = new AccountPK();
		comp_id.setSequence(new BigDecimal(1));
		catAccount.setComp_id(comp_id);
		catAccount.setDateEntered(d_today);
		catAccount.setDateExpires(d_today);
		catAccount.setOwner(uid);
		catAccount.setStatus("02");
		newAccount = true;
	}
	else
	{
		catAccount = (Account) accList.get(0);
		s_alloc_method = HiltonUtility.ckNull(catAccount.getAllocMethod());
	}
	AccountPK catAccountPK = catAccount.getComp_id();

	if (s_alloc_method.length() <= 0)
	{
		if ( s_alloc_by_qty.equals("Y") )
		{
			s_alloc_method = "QL";
		}
		else
		{
			s_alloc_method = "PL";
		}
	}

	if (s_account_action == null)	{	s_account_action = "";	}
%>

<tsa:hidden name="Catalog_catalogId" value="<%=s_catalog_id%>"/>
<tsa:hidden name="Account_icHeader" value="<%=catAccountPK.getIcHeader()%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="formType" value="CATALOG"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="newAccount" value="<%=newAccount%>"/>

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
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Catalog:</b></td>
			<td width=100px nowrap><%=s_catalog_id%></td>
		</tr>
		</table>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<table border=0 cellpadding=2 cellspacing=0 width==90%>
		<tr>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> width="125px" align="right"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationMethod", "Allocation Method")%>:</B><tsa:hidden name="as_alloc_method" value="<%=s_alloc_method%>"/></TD>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> width="75px" align="center"><INPUT TYPE="radio" value="PL" NAME="alloc_method" <% if (s_alloc_method.equals("PL")) { %>CHECKED<%}%> ONCLICK="setMethod();" VALIGN="MIDDLE"> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "percent", "Percent")%> </TD>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> width="75px" align="center"><INPUT TYPE="radio" value="AL" NAME="alloc_method" <% if (s_alloc_method.equals("AL")) { %>CHECKED<%}%> ONCLICK="setMethod();" VALIGN="MIDDLE"> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "amount", "Amount")%> </TD>
			<TD <%=HtmlWriter.isVisible(oid, "totalToAllocate")%> width="200px" align="right"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "totalToAllocate", "Total Amount to Allocate")%>: </B> </TD>
			<TD width="*" align="left" nowrap><%=bd_amount_to_allocate%></TD>
		</tr>
		</table>

		<br>

		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td valign="top">
				<table id=accounts border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr valign="middle">
					<td width=15px>&nbsp;<tsa:hidden name="as_rows" value="<%=accList.size()%>"/><tsa:hidden name="stdTable_tableType" value=""/></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC01")%> nowrap><a href="javascript: browseStd('Account_fld1', 'AC01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC01", "ACC UDF1")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC02")%> nowrap><a href="javascript: browseStd('Account_fld2', 'AC02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC02", "ACC UDF2")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC03")%> nowrap><a href="javascript: browseStd('Account_fld3', 'AC03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC03", "ACC UDF3")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC04")%> nowrap><a href="javascript: browseStd('Account_fld4', 'AC04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04", "ACC UDF4")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC05")%> nowrap><a href="javascript: browseStd('Account_fld5', 'AC05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC05", "ACC UDF5")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC06")%> nowrap><a href="javascript: browseStd('Account_fld6', 'AC06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC06", "ACC UDF6")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC07")%> nowrap><a href="javascript: browseStd('Account_fld7', 'AC07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC07", "ACC UDF7")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC08")%> nowrap><a href="javascript: browseStd('Account_fld8', 'AC08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC08", "ACC UDF8")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC09")%> nowrap><a href="javascript: browseStd('Account_fld9', 'AC09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC09", "ACC UDF9")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC10")%> nowrap><a href="javascript: browseStd('Account_fld10', 'AC10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC10", "ACC UDF10")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC11")%> nowrap><a href="javascript: browseStd('Account_fld11', 'AC11'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC11", "ACC UDF11")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC12")%> nowrap><a href="javascript: browseStd('Account_fld12', 'AC12'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC12", "ACC UDF12")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC13")%> nowrap><a href="javascript: browseStd('Account_fld13', 'AC13'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC13", "ACC UDF13")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC14")%> nowrap><a href="javascript: browseStd('Account_fld14', 'AC14'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC14", "ACC UDF14")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC15")%> nowrap><a href="javascript: browseStd('Account_fld15', 'AC15'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC15", "ACC UDF15")%></A></TD>
					<td <%=HtmlWriter.isVisible(oid, "allocationPercent")%> nowrap width=75px align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationPercent", "Percent")%></TD>
					<td <%=HtmlWriter.isVisible(oid, "allocationAmount")%> nowrap width=100px align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationAmount", "Amount")%></TD>
					<td width=10px ><IMG SRC="<%=contextPath%>/images/none.gif" border=0></TD>
				</TR>
<%
	int i_rowcount = 0;


	for (int i = 0; i < accList.size(); i++)
	{
		s_set_row = "ONFOCUS='setCurrentRow(" + i_rowcount + ");'";
		Account account = (Account) accList.get(i);
		AccountPK accountPk = (AccountPK) account.getComp_id();
		String	s_alloc_title = account.getAccountTitle();
		s_alloc_method = account.getAllocMethod();

		if (s_alloc_method.length() <= 0)
		{
			if ( s_alloc_by_qty.equals("Y") )
			{
				s_alloc_method = "QL";
			}
			else
			{
				s_alloc_method = "PL";
			}
		}
%>
				<TR>
					<td id=acc_num_<%=i%> align="right"><%=i+1%>
						<tsa:hidden name="Account_sequence" value="<%=accountPk.getSequence()%>"/>
						<tsa:hidden name="Account_accountType" value="CAT"/>
						<tsa:hidden name="Account_allocMethod" value="<%=s_alloc_method%>"/>
					</TD>
					<td <%=HtmlWriter.isVisible(oid, "AC01")%> id=acc_1 align="left"><input type="text" name="Account_fld1" SIZE="10" MAXLENGTH="18" value="<%=account.getFld1()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC02")%> id=acc_2 align="left"><input type="text" name="Account_fld2" SIZE="10" MAXLENGTH="15" value="<%=account.getFld2()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC03")%> id=acc_3 align="left"><input type="text" name="Account_fld3" SIZE="10" MAXLENGTH="15" value="<%=account.getFld3()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC04")%> id=acc_4 align="left"><input type="text" name="Account_fld4" SIZE="10" MAXLENGTH="15" value="<%=account.getFld4()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC05")%> id=acc_5 align="left"><input type="text" name="Account_fld5" SIZE="10" MAXLENGTH="15" value="<%=account.getFld5()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC06")%> id=acc_6 align="left"><input type="text" name="Account_fld6" SIZE="10" MAXLENGTH="15" value="<%=account.getFld6()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC07")%> id=acc_7 align="left"><input type="text" name="Account_fld7" SIZE="10" MAXLENGTH="15" value="<%=account.getFld7()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC08")%> id=acc_8 align="left"><input type="text" name="Account_fld8" SIZE="10" MAXLENGTH="15" value="<%=account.getFld8()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC09")%> id=acc_9 align="left"><input type="text" name="Account_fld9" SIZE="10" MAXLENGTH="15" value="<%=account.getFld9()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC10")%> id=acc_10 align="left"><input type="text" name="Account_fld10" SIZE="10" MAXLENGTH="15" value="<%=account.getFld10()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC11")%> id=acc_11 align="left"><input type="text" name="Account_fld11" SIZE="10" MAXLENGTH="15" value="<%=account.getFld11()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC12")%> id=acc_12 align="left"><input type="text" name="Account_fld12" SIZE="10" MAXLENGTH="15" value="<%=account.getFld12()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC13")%> id=acc_13 align="left"><input type="text" name="Account_fld13" SIZE="10" MAXLENGTH="15" value="<%=account.getFld13()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC14")%> id=acc_14 align="left"><input type="text" name="Account_fld14" SIZE="10" MAXLENGTH="15" value="<%=account.getFld14()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "AC15")%> id=acc_15 align="left"><input type="text" name="Account_fld15" SIZE="10" MAXLENGTH="15" value="<%=account.getFld15()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<td <%=HtmlWriter.isVisible(oid, "allocationPercent")%> id=as_perc_tot align="right"><INPUT TYPE="TEXT" id="allocPercent_<%=i%>" NAME="Account_allocPercent" SIZE="10" MAXLENGTH="25" value="<%=Utility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>" style="text-align:right" ONCHANGE="addUp(<%=i%>);"></TD>
					<td <%=HtmlWriter.isVisible(oid, "allocationAmount")%> id=as_perc_amt align="right"><INPUT TYPE="TEXT" id="allocAmount_<%=i%>" NAME="Account_allocAmount" SIZE="15" MAXLENGTH="25" value="<%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%>" style="text-align:right" ONCHANGE="addUp(<%=i%>);"></TD>
					<tsa:hidden name="Account_allocQty" value="<%=HiltonUtility.getFormattedQuantity(account.getAllocQty(), oid)%>"/>
					<td id=acc_del_<%//=i%>><a href="javascript: if (confirm('Are you sure you wish to delete this Account?')) { deleteMe(<%//=i%>); } void(0);" TABINDEX="999"><IMG SRC="<%=contextPath%>/images/delete.gif" ALT="Delete" BORDER="0"></A></TD>
				</TR>
		<%
					i_rowcount++;
				}%>
				</TABLE>

				<HR align="center" width=95%>

				<TABLE BORDER="0" CELLPADDING="2" width=100%>
				<TR>
					<td width=15px>&nbsp;</TD>
					<td align="left"><a href="javascript: addNew(); void(0);"><FONT CLASS="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></B></FONT></A></TD>
					<td width="175px" align="center"><a href="javascript: deleteEmptyRows(); browseStdAccounts(); void(0);"><FONT CLASS="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addStandardAccount", "Add standard account")%></B></FONT></A></TD>
					<td align="right"><a href="javascript: deleteAll(); void(0);"><FONT CLASS="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></FONT></A></TD>
		<%	if (!s_alloc_by_qty.equals("Y")) { %>
					<td width=75px align="right"><input type="text" name="as_perc_tot" size="10" maxlength="10" value="100.00" STYLE="text-align:right" ONFOCUS="this.blur();" disabled ></TD>
					<td width=100px align="right"><input type="text" name="as_amt_tot" SIZE="15" maxlength="15" value="<%=bd_amount_to_allocate%>" STYLE="text-align:right" ONFOCUS="this.blur();" disabled></TD>
					<td width=10px>
						<tsa:hidden name="as_tot" value="<%=bd_amount_to_allocate%>"/>
						<tsa:hidden name="as_qty_tot" value=""/>
					</TD>
		<%	} else { %>
					<td width=75px align="right"><tsa:hidden name="as_perc_tot" value="100.00"/></TD>
					<td width=100px align="right"><tsa:hidden name="as_amt_tot" value="<%=bd_amount_to_allocate%>"/></TD>
					<td width=10px>
						<tsa:hidden name="as_tot" value=""/>
						<tsa:hidden name="as_qty_tot" value=""/>
					</TD>
		<% }%>
				</TR>
				</TABLE>

				<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>
			</TD>
		</TR>
		</TABLE>
	</TD>
</tr>
</table>

<br>
<br>

<div id="hiddenFields" style="visibility:hidden; display:none;"></div>

<table border=0 cellpadding=0 cellspacing=0 width=500px>
<tr>
	<td width=50% align=center>
	<% if (roleR.getAccessRights("CAT") >= 3) { %>
		<div id="pxbutton"><a href="javascript: doSubmit('/admin/catalog/catalog.jsp', 'ItemAccountUpdate;CatalogRetrieveById;CatalogItemRetrieveCountByCatalog'); void(0);">Save</a></div>
	<% } %>
	</td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/catalog/catalog.jsp', 'CatalogRetrieveById;CatalogItemRetrieveCountByCatalog'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/base/account_rows.jsp" %>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/catalog/catalog_accounts.jsp", "AccountRetrieveByLine", "Accounts");
	//hideArea("navTable");
	displayArea("menuBarSpacer");

	var myTable = document.getElementById("accounts");
	var TotalRows = myTable.rows.length - 1;

	var accRows = frm.as_rows.value;
	var maxRows = frm.as_maxrows.value;

	var allocMethod = frm.as_alloc_method.value;
	//var allocMethod = "PL";
	var qtyDec  = <%=s_quantity_decimals%>;
	var type = "LIN";  /*used for setting allocMethod in accounts.js*/
	var accountType = "CAT";
	var amtToAllocate = <%=bd_amount_to_allocate%>;


	if (TotalRows == 0)
	{
		addNew();
	}
	if (frm.alloc_method[1].checked == true)
	{
		setMethod();
	}

<%	//if (s_account_action.equalsIgnoreCase("returnAllocation")) { %>
			//total();
<%//	} %>

	function thisLoad()
	{
		f_StartIt();
	}

// End Hide script -->
</SCRIPT>
