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
	String	s_itemnumber = (String) request.getAttribute("InvLocation_itemNumber");
	String	s_itemlocation = (String) request.getAttribute("InvLocation_itemLocation");
	String	s_ic_inv_account = HiltonUtility.ckNull((String) request.getAttribute("InvLocation_icInvAccount"));
	String	s_amount_to_allocate = (String) request.getAttribute("amountToAllocate");
	String	s_alloc_method = "";
	String	s_account_action = (String) request.getAttribute("accountAction");
	String tableType = "AC";
	String s_dept_code = "";
	String  s_buyer_code="";
	String labViewGroup = "";
	String accountType = "INV";
	BigDecimal	bd_amount_to_allocate = HiltonUtility.getFormattedDollar(new BigDecimal(0), oid);

	if (s_amount_to_allocate != null)
	{
		bd_amount_to_allocate = HiltonUtility.getFormattedDollar(new BigDecimal(s_amount_to_allocate), oid);
	}
	String	s_set_row = "";

	List accList = (List) request.getAttribute("accountList");
	Account invAccount = null;
	boolean newAccount = false;

	if (accList == null || accList.size() <= 0)
	{
		invAccount = new Account();
		AccountPK comp_id = new AccountPK();
		comp_id.setSequence(new BigDecimal(1));
		invAccount.setComp_id(comp_id);
		invAccount.setDateEntered(d_today);
		invAccount.setDateExpires(d_today);
		invAccount.setOwner(uid);
		invAccount.setStatus("02");
		newAccount = true;
	}
	else
	{
		invAccount = (Account) accList.get(0);
		s_alloc_method = HiltonUtility.ckNull(invAccount.getAllocMethod());
	}
	AccountPK invAccountPK = invAccount.getComp_id();

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

<tsa:hidden name="InvLocation_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvLocation_itemLocation" value="<%=s_itemlocation%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invAccountPK.getIcHeader()%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="formType" value="INV"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="binAction" value="UPDATE"/>
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
			<td align=right><b>Item #:</b></td>
			<td width=100px><%=s_itemnumber%></td>
		</tr>
		<tr>
			<td align=right><b>Location:</b></td>
			<td width=100px><%=s_itemlocation%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="2" WIDTH=500px>
		<tr>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> WIDTH="125px" ALIGN="RIGHT"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationMethod", "Allocation Method")%>:</B><tsa:hidden name="as_alloc_method" value="<%=s_alloc_method%>"/></TD>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> WIDTH="75px" ALIGN="CENTER"><INPUT TYPE="radio" value="PL" NAME="alloc_method" <% if (s_alloc_method.equals("PL")) { %>CHECKED<%}%> ONCLICK="setMethod();" VALIGN="MIDDLE"> Percent </TD>
			<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> WIDTH="75px" ALIGN="CENTER"><INPUT TYPE="radio" value="AL" NAME="alloc_method" <% if (s_alloc_method.equals("AL")) { %>CHECKED<%}%> ONCLICK="setMethod();" VALIGN="MIDDLE"> Amount </TD>
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
					<TD WIDTH=15px>&nbsp;<tsa:hidden name="as_rows" value="<%=accList.size()%>"/><tsa:hidden name="stdTable_tableType" value=""/></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC01")%> NOWRAP><A HREF="javascript: browseStd('Account_fld1', 'AC01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC01", "ACC UDF1")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC02")%> NOWRAP><A HREF="javascript: browseStd('Account_fld2', 'AC02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC02", "ACC UDF2")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC03")%> NOWRAP><A HREF="javascript: browseStd('Account_fld3', 'AC03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC03", "ACC UDF3")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC04")%> NOWRAP><A HREF="javascript: browseStd('Account_fld4', 'AC04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04", "ACC UDF4")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC05")%> NOWRAP><A HREF="javascript: browseStd('Account_fld5', 'AC05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC05", "ACC UDF5")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC06")%> NOWRAP><A HREF="javascript: browseStd('Account_fld6', 'AC06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC06", "ACC UDF6")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC07")%> NOWRAP><A HREF="javascript: browseStd('Account_fld7', 'AC07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC07", "ACC UDF7")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC08")%> NOWRAP><A HREF="javascript: browseStd('Account_fld8', 'AC08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC08", "ACC UDF8")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC09")%> NOWRAP><A HREF="javascript: browseStd('Account_fld9', 'AC09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC09", "ACC UDF9")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC10")%> NOWRAP><A HREF="javascript: browseStd('Account_fld10', 'AC10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC10", "ACC UDF10")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC11")%> NOWRAP><A HREF="javascript: browseStd('Account_fld11', 'AC11'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC11", "ACC UDF11")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC12")%> NOWRAP><A HREF="javascript: browseStd('Account_fld12', 'AC12'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC12", "ACC UDF12")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC13")%> NOWRAP><A HREF="javascript: browseStd('Account_fld13', 'AC13'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC13", "ACC UDF13")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC14")%> NOWRAP><A HREF="javascript: browseStd('Account_fld14', 'AC14'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC14", "ACC UDF14")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC15")%> NOWRAP><A HREF="javascript: browseStd('Account_fld15', 'AC15'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC15", "ACC UDF15")%></A></TD>
					<TD <%=HtmlWriter.isVisible(oid, "allocationPercent")%> NOWRAP WIDTH=75px ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationPercent", "Percent")%></TD>
					<TD <%=HtmlWriter.isVisible(oid, "allocationAmount")%> NOWRAP WIDTH=100px ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationAmount", "Amount")%></TD>
					<TD WIDTH=10px ><IMG SRC="<%=contextPath%>/images/none.gif" border=0></TD>
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
					<TD ID=acc_num_<%=i%> ALIGN="right"><%=i+1%>
						<tsa:hidden name="Account_sequence" value="<%=accountPk.getSequence()%>"/>
						<tsa:hidden name="Account_accountType" value="INV"/>
						<tsa:hidden name="Account_allocMethod" value="<%=s_alloc_method%>"/>
					</TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC01")%> ID=acc_1 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld1" SIZE="10" MAXLENGTH="18" value="<%=account.getFld1()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC02")%> ID=acc_2 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld2" SIZE="10" MAXLENGTH="15" value="<%=account.getFld2()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC03")%> ID=acc_3 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld3" SIZE="10" MAXLENGTH="15" value="<%=account.getFld3()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
					<TD <%=HtmlWriter.isVisible(oid, "AC04")%> ID=acc_4 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="Account_fld4" SIZE="10" MAXLENGTH="15" value="<%=account.getFld4()%>" ONCHANGE="upperCase(this);" <%=s_set_row%>></TD>
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
					<TD <%=HtmlWriter.isVisible(oid, "allocationPercent")%> ID=as_perc_tot ALIGN="RIGHT"><INPUT TYPE="TEXT" ID="allocPercent_<%=i%>" NAME="Account_allocPercent" SIZE="10" MAXLENGTH="25" value="<%=Utility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>" style="text-align:right" ONCHANGE="addUp(<%=i%>);"></TD>
					<TD <%=HtmlWriter.isVisible(oid, "allocationAmount")%> ID=as_perc_amt ALIGN="RIGHT"><INPUT TYPE="TEXT" ID="allocAmount_<%=i%>" NAME="Account_allocAmount" SIZE="15" MAXLENGTH="25" value="<%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%>" style="text-align:right" ONCHANGE="addUp(<%=i%>);"></TD>
					<tsa:hidden name="Account_allocQty" value="<%=HiltonUtility.getFormattedQuantity(account.getAllocQty(), oid)%>"/>
					<TD ID=acc_del_<%//=i%>><A href="javascript: if (confirm('Are you sure you wish to delete this Account?')) { deleteMe(<%//=i%>); } void(0);" TABINDEX="999"><IMG SRC="<%=contextPath%>/images/delete.gif" ALT="Delete" BORDER="0"></A></TD>
				</TR>
		<%
					i_rowcount++;
				}%>
				</TABLE>

				<HR ALIGN="CENTER" WIDTH=95%>

				<TABLE BORDER="0" CELLPADDING="2" WIDTH=100%>
				<TR>
					<TD WIDTH=15px>&nbsp;</TD>
					<TD ALIGN="LEFT"><A HREF="javascript: addNew(); void(0);"><FONT CLASS="reset"><B>Add new</B></FONT></A></TD>
					<TD WIDTH="175px" ALIGN="CENTER"><A HREF="javascript: deleteEmptyRows(); browseStdAccounts(); void(0);"><FONT CLASS="reset"><B>Add standard account</B></FONT></A></TD>
					<TD ALIGN="RIGHT"><A HREF="javascript: deleteAll(); void(0);"><FONT CLASS="reset"><B>Delete all</B></FONT></A></TD>
		<%	if (!s_alloc_by_qty.equals("Y")) { %>
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
	<td id="saveMe" width=50% align=center><a href="javascript: doSubmit('/inventory/inv_location.jsp', 'ItemAccountUpdate;InvLocationRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_location.jsp', 'InvLocationRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<%@ include file="/base/account_rows.jsp" %>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/inv_item_accounts.jsp", "AccountRetrieveByLine", "Account Information");
	frm = document.purchaseform;

	var myTable = document.getElementById("accounts");
	var TotalRows = myTable.rows.length - 1;

	var accRows = frm.as_rows.value;
	var maxRows = frm.as_maxrows.value;

	var allocMethod = frm.as_alloc_method.value;
	//var allocMethod = "PL";
	var qtyDec  = <%=s_quantity_decimals%>;
	var type = "LIN";  /*used for setting allocMethod in accounts.js*/
	var accountType = "INV";
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
		var invAccess = <%=role.getAccessRights("INV")%>;
		f_StartIt();
		if (invAccess <= 1)
		{
			$('#saveMe').hide();
			checkInputSettings();
		}
	}

// End Hide script -->
</SCRIPT>
