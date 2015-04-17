<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<!--SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT-->
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_alloc_by_qty = propertiesManager.getProperty("MISC", "ALLOCBYQTY", "N");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String auto_accounting_active = HiltonUtility.ckNull((String)request.getAttribute("autoAccountingActive"));
	String	s_ivc_ic_header = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_icIvcHeader"));
	String	s_ivc_number = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_invoiceNumber"));
	String	s_ivc_status = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_status"));
	String	s_amount_to_allocate = (String) request.getAttribute("amountToAllocate");
	String	s_fiscal_year = (String) request.getAttribute("InvoiceHeader_fiscalYear");
	String	s_alloc_method = "";
	String	s_account_action = (String) request.getAttribute("accountAction");
	String labViewGroup = "";

	BigDecimal	bd_amount_to_allocate = new BigDecimal(0.00);
	if (s_amount_to_allocate != null)
	{
		bd_amount_to_allocate = new BigDecimal(s_amount_to_allocate).setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	}
	String	s_set_row = "";
	List accList = (List) request.getAttribute("accountList");
	if (accList == null)
	{
		accList = new ArrayList();
	}
	if (accList.size() > 0)
	{
		Account account = (Account) accList.get(0);
		s_alloc_method = HiltonUtility.ckNull(account.getAllocMethod());
	}

	if (s_alloc_method.length() <= 0)
	{
		if ( s_alloc_by_qty.equals("Y") )
		{
			s_alloc_method = "QH";
		}
		else
		{
			s_alloc_method = "PH";
		}
	}
	if (s_account_action == null)	{	s_account_action = "";	}
	String tableType = "AC";
	String s_dept_code = "";
	String  s_buyer_code="";

	String	s_current_process = "ACCOUNTS";
	String	s_current_page = "/invoice/iv_accounts.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	String	accountType = "IVH";
	String	labelPrefix = "ivc";
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=s_ivc_status%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Account Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=125px><%=s_ivc_number%></td>
		</tr>
		<tr>
			<td align=right><b>Status:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_ivc_status, oid)%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align="center">
		<table border=1 cellspacing=0 cellpadding=0 width=515px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: retrieveAccounts('header'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
					<td width=58% height=18px class=browseHdr>&nbsp;<b>General Account</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
				</tr>
				</table>
			</td>
    	</tr>
		<tr>
			<td>
<%
	BigDecimal bd_total_perc = new BigDecimal(0.00);
	BigDecimal bd_total_amt = new BigDecimal(0.00);

	if (accList != null)
	{
	    for (int i = 0;i < accList.size(); i++)
	    {
			Account account = (Account) accList.get(i);

			BigDecimal bd_alloc_perc = account.getAllocPercent();
			BigDecimal bd_alloc_amt = account.getAllocAmount();

			if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
			if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);

			String	s_account = "";
			String	s_accArray[] = new String[15];

			s_accArray[0] = account.getFld1();
			s_accArray[1] = account.getFld2();
			s_accArray[2] = account.getFld3();
			s_accArray[3] = account.getFld4();
			s_accArray[4] = account.getFld5();
			s_accArray[5] = account.getFld6();
			s_accArray[6] = account.getFld7();
			s_accArray[7] = account.getFld8();
			s_accArray[8] = account.getFld9();
			s_accArray[9] = account.getFld10();
			s_accArray[10] = account.getFld11();
			s_accArray[11] = account.getFld12();
			s_accArray[12] = account.getFld13();
			s_accArray[13] = account.getFld14();
			s_accArray[14] = account.getFld15();

			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
					}
					else
					{
						s_account = s_accArray[j];
					}
				}
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=60% class=browseRow><%=s_account%></td>
					<td width=20% class=browseRow align=right><%=account.getAllocPercent().setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=account.getAllocAmount().setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
				</tr>
				</table>
<%	}
	}
	if (accList == null || accList.size() == 0) {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr><td class=browseRow>There are no accounts associated with this invoice.</td></tr>
		        </table>
<%} else {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
		        </tr>
		        <tr>
					<td width=60% class=browseRow>&nbsp;</td>
					<td width=20% class=browseRow align=right><%=bd_total_perc.setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=bd_total_amt.setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
		        </tr>
		        </table>
<%}%>
			</td>
		</tr>
		</table>

		<br>

		<table border=1 cellspacing=0 cellpadding=0 width=515px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: retrieveAccounts('tax'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
					<td width=58% height=18px class=browseHdr>&nbsp;<b>Tax Account</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
				</tr>
				</table>
			</td>
    	</tr>
		<tr>
			<td>
<%
	bd_total_perc = new BigDecimal(0.00);
	bd_total_amt = new BigDecimal(0.00);
	accList = (List) request.getAttribute("taxAccountList");
	if (accList != null)
	{
	    for (int i = 0;i < accList.size(); i++)
	    {
			Account account = (Account) accList.get(i);

			BigDecimal bd_alloc_perc = account.getAllocPercent();
			BigDecimal bd_alloc_amt = account.getAllocAmount();

			if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
			if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);

			String	s_account = "";
			String	s_accArray[] = new String[15];

			s_accArray[0] = account.getFld1();
			s_accArray[1] = account.getFld2();
			s_accArray[2] = account.getFld3();
			s_accArray[3] = account.getFld4();
			s_accArray[4] = account.getFld5();
			s_accArray[5] = account.getFld6();
			s_accArray[6] = account.getFld7();
			s_accArray[7] = account.getFld8();
			s_accArray[8] = account.getFld9();
			s_accArray[9] = account.getFld10();
			s_accArray[10] = account.getFld11();
			s_accArray[11] = account.getFld12();
			s_accArray[12] = account.getFld13();
			s_accArray[13] = account.getFld14();
			s_accArray[14] = account.getFld15();

			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
					}
					else
					{
						s_account = s_accArray[j];
					}
				}
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=60% class=browseRow><%=s_account%></td>
					<td width=20% class=browseRow align=right><%=account.getAllocPercent().setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=account.getAllocAmount().setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
				</tr>
				</table>
<%	}
	}
	if (accList == null || accList.size() == 0) {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr><td class=browseRow>There are no tax accounts associated with this invoice.</td></tr>
		        </table>
<%} else {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
		        </tr>
		        <tr>
					<td width=60% class=browseRow>&nbsp;</td>
					<td width=20% class=browseRow align=right><%=bd_total_perc.setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=bd_total_amt.setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
		        </tr>
		        </table>
<%}%>
			</td>
		</tr>
		</table>

		<br>

				<table border=1 cellspacing=0 cellpadding=0 width=515px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: retrieveAccounts('usetax'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
					<td width=58% height=18px class=browseHdr>&nbsp;<b>Use Tax Account</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
				</tr>
				</table>
			</td>
    	</tr>
		<tr>
			<td>
<%
	bd_total_perc = new BigDecimal(0.00);
	bd_total_amt = new BigDecimal(0.00);
	accList = (List) request.getAttribute("usetaxAccountList");
	if (accList != null)
	{
	    for (int i = 0;i < accList.size(); i++)
	    {
			Account account = (Account) accList.get(i);

			BigDecimal bd_alloc_perc = account.getAllocPercent();
			BigDecimal bd_alloc_amt = account.getAllocAmount();

			if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
			if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);

			String	s_account = "";
			String	s_accArray[] = new String[15];

			s_accArray[0] = account.getFld1();
			s_accArray[1] = account.getFld2();
			s_accArray[2] = account.getFld3();
			s_accArray[3] = account.getFld4();
			s_accArray[4] = account.getFld5();
			s_accArray[5] = account.getFld6();
			s_accArray[6] = account.getFld7();
			s_accArray[7] = account.getFld8();
			s_accArray[8] = account.getFld9();
			s_accArray[9] = account.getFld10();
			s_accArray[10] = account.getFld11();
			s_accArray[11] = account.getFld12();
			s_accArray[12] = account.getFld13();
			s_accArray[13] = account.getFld14();
			s_accArray[14] = account.getFld15();

			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
					}
					else
					{
						s_account = s_accArray[j];
					}
				}
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=60% class=browseRow><%=s_account%></td>
					<td width=20% class=browseRow align=right><%=account.getAllocPercent().setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=account.getAllocAmount().setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
				</tr>
				</table>
<%	}
	}
	if (accList == null || accList.size() == 0) {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr><td class=browseRow>There are no use tax accounts associated with this invoice.</td></tr>
		        </table>
<%} else {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
		        </tr>
		        <tr>
					<td width=60% class=browseRow>&nbsp;</td>
					<td width=20% class=browseRow align=right><%=bd_total_perc.setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=bd_total_amt.setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
		        </tr>
		        </table>
<%}%>
			</td>
		</tr>
		</table>

		<br>

		<table border=1 cellspacing=0 cellpadding=0 width=515px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: retrieveAccounts('shipping'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
					<td width=58% height=18px class=browseHdr>&nbsp;<b>Shipping Account</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
				</tr>
				</table>
			</td>
    	</tr>
		<tr>
			<td>
<%
	bd_total_perc = new BigDecimal(0.00);
	bd_total_amt = new BigDecimal(0.00);

	accList = (List) request.getAttribute("shippingAccountList");
	if (accList != null)
	{
	    for (int i = 0;i < accList.size(); i++)
	    {
			Account account = (Account) accList.get(i);

			BigDecimal bd_alloc_perc = account.getAllocPercent();
			BigDecimal bd_alloc_amt = account.getAllocAmount();

			if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
			if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);

			String	s_account = "";
			String	s_accArray[] = new String[15];

			s_accArray[0] = account.getFld1();
			s_accArray[1] = account.getFld2();
			s_accArray[2] = account.getFld3();
			s_accArray[3] = account.getFld4();
			s_accArray[4] = account.getFld5();
			s_accArray[5] = account.getFld6();
			s_accArray[6] = account.getFld7();
			s_accArray[7] = account.getFld8();
			s_accArray[8] = account.getFld9();
			s_accArray[9] = account.getFld10();
			s_accArray[10] = account.getFld11();
			s_accArray[11] = account.getFld12();
			s_accArray[12] = account.getFld13();
			s_accArray[13] = account.getFld14();
			s_accArray[14] = account.getFld15();

			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
					}
					else
					{
						s_account = s_accArray[j];
					}
				}
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=60% class=browseRow><%=s_account%></td>
					<td width=20% class=browseRow align=right><%=account.getAllocPercent().setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=account.getAllocAmount().setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
				</tr>
				</table>
<%	}
	}
	if (accList == null || accList.size() == 0) {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr><td class=browseRow>There are no shipping accounts associated with this invoice.</td></tr>
		        </table>
<%} else {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
		        </tr>
		        <tr>
					<td width=60% class=browseRow>&nbsp;</td>
					<td width=20% class=browseRow align=right><%=bd_total_perc.setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=bd_total_amt.setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
		        </tr>
		        </table>
<%}%>
			</td>
		</tr>
		</table>

		<br>

		<table border=1 cellspacing=0 cellpadding=0 width=515px class=browseHdr <%=HtmlWriter.isVisible(oid, "ivc-otherCharges")%>>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: retrieveAccounts('other'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
					<td width=58% height=18px class=browseHdr>&nbsp;<b>Other Account</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
				</tr>
				</table>
			</td>
    	</tr>
		<tr>
			<td>
<%
	bd_total_perc = new BigDecimal(0.00);
	bd_total_amt = new BigDecimal(0.00);
	accList = (List) request.getAttribute("otherAccountList");
	if (accList != null)
	{
	    for (int i = 0;i < accList.size(); i++)
	    {
			Account account = (Account) accList.get(i);

			BigDecimal bd_alloc_perc = account.getAllocPercent();
			BigDecimal bd_alloc_amt = account.getAllocAmount();

			if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
			if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);

			String	s_account = "";
			String	s_accArray[] = new String[15];

			s_accArray[0] = account.getFld1();
			s_accArray[1] = account.getFld2();
			s_accArray[2] = account.getFld3();
			s_accArray[3] = account.getFld4();
			s_accArray[4] = account.getFld5();
			s_accArray[5] = account.getFld6();
			s_accArray[6] = account.getFld7();
			s_accArray[7] = account.getFld8();
			s_accArray[8] = account.getFld9();
			s_accArray[9] = account.getFld10();
			s_accArray[10] = account.getFld11();
			s_accArray[11] = account.getFld12();
			s_accArray[12] = account.getFld13();
			s_accArray[13] = account.getFld14();
			s_accArray[14] = account.getFld15();

			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
					}
					else
					{
						s_account = s_accArray[j];
					}
				}
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=60% class=browseRow><%=s_account%></td>
					<td width=20% class=browseRow align=right><%=account.getAllocPercent().setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=account.getAllocAmount().setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
				</tr>
				</table>
<%	}
	}
	if (accList == null || accList.size() == 0) {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr><td class=browseRow>There are no other accounts associated with this invoice.</td></tr>
		        </table>
<%} else {%>
		        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		        <tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
		        </tr>
		        <tr>
					<td width=60% class=browseRow>&nbsp;</td>
					<td width=20% class=browseRow align=right><%=bd_total_perc.setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>
					<td width=20% class=browseRow align=right><%=bd_total_amt.setScale(Integer.valueOf(s_price_decimals).intValue(), BigDecimal.ROUND_HALF_UP)%></td>
		        </tr>
		        </table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
	<td rowspan=2 align=right valign=top><%@ include file="/invoice/iv_sidebar.jsp" %></td>
</tr>
</table>

<br>
<br>

<div id="hiddenFields" style="visibility:hidden; display:none;"></div>

<%@ include file="/base/account_rows.jsp" %>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	<%
	if (s_ivc_status.compareTo(DocumentStatus.IVC_APPROVING) > 0 && !s_ivc_status.equals(DocumentStatus.IVC_REJECTED)){
		%>
		checkInputSettings();
		allowEdit = false;
		<%
	}%>

	function retrieveAccounts(type)
	{
		if (type == 'header')
		{
			frm.formType.value = 'IVH';
		}
		else if (type == 'tax')
		{
			frm.formType.value = 'IVT';
		}
		else if (type == 'usetax')
		{
			frm.formType.value = 'IVU';
		}
		else if (type == 'shipping')
		{
			frm.formType.value = 'IVS';
		}
		else if (type == 'other')
		{
			frm.formType.value = 'IVO';
		}
		doSubmit('/invoice/iv_accounts_edit.jsp', 'AccountRetrieveByLine');
	}

// End Hide script -->
</SCRIPT>
