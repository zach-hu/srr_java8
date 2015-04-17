<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DisbursementType" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	DisbHeader disbHeader = (DisbHeader) request.getAttribute("disbHeader");
	BigDecimal b_icReqHeader = disbHeader.getIcReqHeader();
	BigDecimal b_icDisbHeader = disbHeader.getIcDsbHeader();
	String s_dsb_status = disbHeader.getStatus();
	String s_dsb_type = disbHeader.getDisbType();
	String s_dsb_number = disbHeader.getDisbNumber();

	if (s_dsb_number == null || s_dsb_number.length() <= 0)
	{
		s_dsb_number = "N/A";
	}

	String s_fiscalYear = disbHeader.getFiscalYear();
	String s_requisitioner = disbHeader.getRequisitionerCode();
	String s_itemLocation = disbHeader.getItemLocation();
	String s_intComments = disbHeader.getInternalComments();
	BigDecimal b_subTotal = disbHeader.getSubtotal();

	UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner);

	int linecount = 0;
	List disbList = (List) request.getAttribute("disbLineList");
	if (disbList != null)
	{
		linecount = disbList.size();
	}

	int iBefore = 0;
	int iAfter = 0;
	int iBeforeItem = 0;
	int iAfterItem = 0;
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="DisbLine_icDsbHeader" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="DisbLine_icDsbLine" value=""/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=disbHeader.getDisbNumber()%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_dsb_status%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_icReqHeader%>"/>
<tsa:hidden name="DisbHeader_icAccount" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="Account_icHeader" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="DBH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="lineCount" value="<%=linecount%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=660px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td nowrap class=formtype valign=middle>
				&nbsp;&nbsp;<%=DisbursementType.toString(s_dsb_type, oid)%> Information
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionNumber", "Requisition #")%>:</b></td>
			<td width=100px><%=s_dsb_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><%=DocumentStatus.toString(disbHeader.getStatus())%></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td width=660px align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=browseRow>
				<table border=0 cellspacing=0 cellpadding=2 width=100%>
				<tr>
					<td>
						<table>
						<tr>
							<td nowrap align=right width=35%><b>Disbursement Date:&nbsp;</b></td>
							<td nowrap width=65%><%=HiltonUtility.getFormattedDate(disbHeader.getDisbDate(), oid, userDateFormat)%></td>
						</tr>
						<tr>
							<td nowrap align=right width=35%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%>:&nbsp;</b></td>
							<td nowrap width=65%>
								<%=disbHeader.getFiscalYear()%>
								<tsa:hidden name="DisbHeader_fiscalYear" value="<%=disbHeader.getFiscalYear()%>"/>
							</td>
						</tr>
						<tr>
							<td nowrap align=right width=35%><b>Inventory Location:&nbsp;</b></td>
							<td nowrap width=65%><%=disbHeader.getItemLocation()%></td>
						</tr>
						</table>
					</td>
					<td>
						<table>
		<%	if ( !s_dsb_type.equals("O") ) {%>
						<tr>
							<td nowrap align=right width=35%><b>Requisition Number:&nbsp;</b></td>
							<td nowrap width=65%><%=headerEncoder.encodeForHTML(disbHeader.getRequisitionNumber())%></td>
						</tr>
		<%	} %>
						<tr>
							<td nowrap align=right width=35%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%>:&nbsp;</b></td>
							<td nowrap width=65%><%=disbHeader.getRequisitionerCode()%></td>
						</tr>
						<tr>
							<td nowrap align=right width=35%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionerName", "Requisitioner Name")%>:&nbsp;</b></td>
							<td nowrap width=65%><%=requisitioner.getDisplayName()%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
	<td rowspan=3 align=right valign=top>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
						<tr>
							<td width=60% height=18px class=browseHdr>&nbsp;<b>Account</b></td>
							<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
							<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>

<%
		BigDecimal bd_total_perc = new BigDecimal(0.00);
		BigDecimal bd_total_amt = new BigDecimal(0.00);
		List accList = (List) request.getAttribute("accountList");
		if (accList != null)
		{
				for (int i = 0; i < accList.size(); i++)
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
					} %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=60% class=browseRow><%=s_account%></td>
							<td width=20% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>%</td>
							<td width=20% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></td>
						</tr>
						</table>
<%			}
		} %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td class=browseRow>&nbsp;</td>
							<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
						</tr>
						<tr>
							<td width=60% class=browseRow>&nbsp;</td>
							<td width=20% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(bd_total_perc, 2)%>%</td>
							<td width=20% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<div id="commentBefore" style="display:none;">
<%	if ( !s_dsb_type.equals("O") ) {%>
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=100% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>

<%
	List cmtList = (List) disbHeader.getDocCommentList();
	int size = 0;
	if (cmtList != null)
	{
		size = cmtList.size();
		for(int i = 0; i < size; i++)
		{
			DocComment docComment = (DocComment) cmtList.get(i);
			DocText docText = docComment.getDocText();

			String s_cmt_title = docComment.getCommentTitle();
			String s_cmt_print = docComment.getCommentPrint();
			String s_cmt_bold = docComment.getCommentBold();
			String s_cmt_place = docComment.getCommentPlace();
			String s_cmt_text = docText.getStdText();

			if (s_cmt_place.equals("A"))
			{
				continue;
			}
			if (s_cmt_print.equals("N"))
			{
				continue;
			}
			iBefore++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=100%  colspan=2 class=browseRow>
<%				if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
<%				if (s_cmt_bold.equals("Y")) 	{ %>	<b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
<% 	}
	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>
</div>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td width=6% class=browseHdr nowrap>&nbsp;Line #</td>
							<td width=15% class=browseHdr nowrap align="center">&nbsp;Item/Part #</td>
							<td width=10% class=browseHdr nowrap align="left" >&nbsp;Item Loc.</td>
							<td width=5% class=browseHdr nowrap>&nbsp;Aisle</td>
							<td width=5% class=browseHdr nowrap>&nbsp;Row</td>
							<td width=5% class=browseHdr nowrap>&nbsp;Tier</td>
							<td width=5% class=browseHdr nowrap>&nbsp;Bin</td>
							<td width=7% class=browseHdr nowrap>&nbsp;UI</td>
							<td width=10% class=browseHdr nowrap align=right>&nbsp;Quantity</td>
							<td width=10% class=browseHdr nowrap align=right>&nbsp;Price</td>
							<td width=10% class=browseHdr nowrap align=right>&nbsp;Line Total</td>
							<td width=12% class=browseHdr nowrap align=right>&nbsp;Line Status</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table id=itemRows border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<%	for (int i = 0; i < linecount; i++)
		{
			DisbLine disbLine = (DisbLine) disbList.get(i);
%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td width=6% class=browseRow nowrap ><a href="javascript: viewItem(<%=i%>); void(0);"><%=i+1%></a></td>
							<td width=16% class=browseRow nowrap><%=disbLine.getItemNumber()%><tsa:hidden id="icDsbLine_<%=i%>" name="icDsbLine_<%=i%>" value="<%=disbLine.getIcDsbLine()%>"/></td>
							<td width=9% class=browseRow nowrap><%=disbLine.getItemLocation()%></td>
							<td width=5% class=browseRow nowrap><%=disbLine.getAisle()%></td>
							<td width=5% class=browseRow nowrap><%=disbLine.getLocrow()%></td>
							<td width=5% class=browseRow nowrap><%=disbLine.getTier()%></td>
							<td width=5% class=browseRow nowrap><%=disbLine.getBin()%></td>
							<td width=7% class=browseRow nowrap><%=disbLine.getUmCode()%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(disbLine.getQuantity(), oid)%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedPrice(disbLine.getUnitPrice(), oid)%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbLine.getLineTotal(), oid)%></td>
							<td width=12% class=browseRow nowrap align=right><%=DocumentStatus.toString(disbLine.getStatus())%></td>
						</tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=10 class=browseRow><%=disbLine.getDescription()%></td>
						</tr>
						</table>
<%		} %>
					</td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td>&nbsp;</td><td class=browseRow width=30% align=right><hr size=0></td></tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=78% class=browseRow nowrap align=right><b>Total:</b></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbHeader.getSubtotal(), oid)%></td>
							<td width=12%>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<div id="commentAfter" style="display:none;">
<%	if ( !s_dsb_type.equals("O") ) {%>
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=100% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>

<%
	List cmtList = (List) disbHeader.getDocCommentList();
	int size = 0;
	if (cmtList != null)
	{
		size = cmtList.size();
		for (int i = 0; i < size; i++)
		{
			DocComment docComment = (DocComment) cmtList.get(i);
			DocText docText = docComment.getDocText();

			String s_cmt_title = docComment.getCommentTitle();
			String s_cmt_print = docComment.getCommentPrint();
			String s_cmt_bold = docComment.getCommentBold();
			String s_cmt_place = docComment.getCommentPlace();
			String s_cmt_text = docText.getStdText();

			if (s_cmt_place.equals("B"))
			{
				continue;
			}
			if (s_cmt_print.equals("N"))
			{
				continue;
			}
			iAfter++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=100%  colspan=2 class=browseRow>
<%				if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
<%				if (s_cmt_bold.equals("Y")) 	{ %>	<b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
<% 	}
	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>
</div>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: printMe(); void(0);">Print</a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: closeMe(); void(0);">Close</a></div></tsa:td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	var netscape  = "";
	var ver = navigator.appVersion;
	var len = ver.length;

	for(iln = 0; iln < len; iln++) {
		if (ver.charAt(iln) == "(") break;
	}
	netscape = (ver.charAt(iln+1).toUpperCase() != "C");

	window.onfocus = displayButtons; // displayButtons on window.onfocus

	if (netscape) window.captureEvents(window.onfocus);

<%	if (iBefore > 0) { %>
			displayArea('commentBefore');
<%	}
		if (iAfter > 0) { %>
			displayArea('commentAfter');
<%	} %>

	function printMe() {
		hideArea("buttons");

		this.print();
	}

	function closeMe() {
		window.top.hidePopWin();
	}

	function displayButtons() {
		displayArea("buttons");
	}

// End Hide script -->
</SCRIPT>