<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/filter.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/DisbOptionArrays.js"></script>

<%@ include file="/system/header.jsp" %>

<%
	String	s_account_separator = PropertiesManager.getInstance(oid).getProperty("MISC", "AccountSeparator", "-");
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

	String s_disbDate = "";
	Date d_disbDate = disbHeader.getDisbDate();
	if (d_disbDate != null)
	{
		s_disbDate = HiltonUtility.getFormattedDate(d_disbDate, oid, userDateFormat);
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
%>

<script language='Javascript1.2' type="text/javascript">
<!--
	disbNumber = "<%=s_dsb_number%>";
	disbStatus = "<%=s_dsb_status%>";
	disbursed = "<%=DocumentStatus.INV_DISBURSED%>";
	backorder = "<%=DocumentStatus.INV_BACKORDERED%>";

	Array91 = createDisbOptionsMenu(Array91[0]);

// -->
</SCRIPT>

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
<tsa:hidden name="formtype" value="DSB"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Disbursement Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Disbursement #:</b></td>
			<td width=100px><%=s_dsb_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_dsb_status, oid)%></td>
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

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) {
			if (!s_dsb_type.equals("O")) { %>
	<td align=right nowrap><a href="javascript: doSubmit('/inventory/dsb_forward.jsp', 'DisbForward'); void(0);">Forward</a></td>
	<td align=right nowrap><a href="javascript: unavailable(); void(0);">Cancel</a></td>
<%		} else { %>
	<td align=right nowrap><a href="javascript: doSubmit('/inventory/dsb_forward.jsp', 'OtcSave'); void(0);">Forward</a></td>
<%		}
		} %>
	<td align=right nowrap>&nbsp;</td>
	<td width="200px" align="right" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=BrowseHdr>
				<tr>
					<td class=browseHdr width=2% height=18px nowrap><a href="javascript: doSubmit('/inventory/dsb_general_info.jsp', 'DisbHeaderRetrieveById'); void(0)"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a>
					<td class=browseHdr width=98% height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=browseRow>
				<table border=0 cellspacing=0 cellpadding=2 width=100%>
				<tr>
					<td nowrap align=right width=25%><b>Disbursement Date:&nbsp;</b></td>
					<td nowrap width=25%><%=HiltonUtility.getFormattedDate(disbHeader.getDisbDate(), oid, userDateFormat)%></td>
					<td nowrap align=right width=25%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%>:&nbsp;</b></td>
					<td nowrap width=25%><%=disbHeader.getRequisitionerCode()%></td>
				</tr>
				<tr>
					<td nowrap align=right width=25%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%>:&nbsp;</b></td>
					<td nowrap width=25%>
						<%=disbHeader.getFiscalYear()%>
						<tsa:hidden name="DisbHeader_fiscalYear" value="<%=disbHeader.getFiscalYear()%>"/>
					</td>
					<td nowrap align=right width=25%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionerName", "Requisitioner Name")%>:&nbsp;</b></td>
					<td nowrap width=25%><%=requisitioner.getDisplayName()%></td>
				</tr>
				<tr>
					<td nowrap align=right width=25%><b>Inventory Location:&nbsp;</b></td>
					<td nowrap width=25%><%=disbHeader.getItemLocation()%></td>
<%	if ( !s_dsb_type.equals("O") ) {%>
					<td nowrap align=right width=25%><b>Requisition Number:&nbsp;</b></td>
					<td nowrap width=25%><%=headerEncoder.encodeForHTML(disbHeader.getRequisitionNumber())%></td>
<%	} %>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=100%>
				<tr>
					<td valign=middle>&nbsp;
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height =18px class=browseHdr><a href="javascript: doSubmit('/inventory/dsb_accounts.jsp', 'AccountRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a>
					<td width=68% height=18px class=browseHdr>&nbsp;<b>Account</b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>

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
							<td width=70% class=browseRow><%=s_account%></td>
							<td width=15% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>%</td>
							<td width=15% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></td>
						</tr>
						</table>
<%			}
		}%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td class=browseRow>&nbsp;</td>
							<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
						</tr>
						<tr>
							<td width=70% class=browseRow>&nbsp;</td>
							<td width=15% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(bd_total_perc, 2)%>%</td>
							<td width=15% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></td>
						</tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td valign=middle>&nbsp;
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

<%	if ( !s_dsb_type.equals("O") ) {%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height =18px class=browseHdr><a href="javascript: doSubmit('/inventory/dsb_notes.jsp', 'DocCommentRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a>
					<td width=73% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
					<td width=8% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Bold</b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Placement</b></td>
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

			String s_cmt_title = docComment.getCommentTitle();
			String s_cmt_print = docComment.getCommentPrint();
			String s_cmt_bold = docComment.getCommentBold();
			String s_cmt_place = docComment.getCommentPlace();

			if (s_cmt_place.equals("A"))
			{
				s_cmt_place = "After";
			}
			else if (s_cmt_place.equals("B"))
			{
				s_cmt_place = "Before";
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=75% class=browseRow>&nbsp;<%=s_cmt_title%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_cmt_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_bold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_cmt_place%></td>
				</tr>
				</table>
<% 	}
	} %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td valign=middle>&nbsp;
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=2% class=browseHdr nowrap><a href="javascript: doSubmit('/inventory/dsb_items.jsp', 'DoNothing;DisbLineRetrieveByHeader'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit Items"></a></td>
					<td width=15% class=browseHdr nowrap align="center">&nbsp;Item/Part #</td>
					<td width=10% class=browseHdr nowrap align="left" >&nbsp;Item Loc.</td>
					<td width=5% class=browseHdr nowrap align=center>&nbsp;Aisle</td>
					<td width=5% class=browseHdr nowrap align=center>&nbsp;Row</td>
					<td width=5% class=browseHdr nowrap align=center>&nbsp;Tier</td>
					<td width=5% class=browseHdr nowrap align=center>&nbsp;Bin</td>
					<td width=7% class=browseHdr nowrap align=center>&nbsp;UI</td>
					<td width=10% class=browseHdr nowrap align=centert>&nbsp;Quantity</td>
					<td width=10% class=browseHdr nowrap align=center>&nbsp;Price</td>
					<td width=10% class=browseHdr nowrap align=center>&nbsp;Line Total</td>
					<td width=13% class=browseHdr nowrap align=center>&nbsp;Line Status</td>
					<%	if (linecount > 1) {%>
					<td width=3% class=browseHdr nowrap align=right><a href="javascript: toggleDetailsDisplay('itemDetails', 'Items'); void(0);"><img id='itemDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Items"></a></td>
					<%	}%>

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

			if (i == 1)
			{
%>
						<div id="itemDetails" style="display:none;">
<%		} %>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td width=6% class=browseRow nowrap align=right><a href="javascript: viewItem(<%=i%>); void(0);"><%=i+1%></a></td>
							<td width=15% class=browseRow nowrap>
								<%=disbLine.getItemNumber()%>
								<tsa:hidden id="icDsbLine_<%=i%>" name="icDsbLine_<%=i%>" value="<%=disbLine.getIcDsbLine()%>"/>
							</td>
							<td width=10% class=browseRow nowrap><%=disbLine.getItemLocation()%></td>
							<td width=5% class=browseRow nowrap><%=disbLine.getAisle()%></td>
							<td width=5% class=browseRow nowrap><%=disbLine.getLocrow()%></td>
							<td width=5% class=browseRow nowrap><%=disbLine.getTier()%></td>
							<td width=5% class=browseRow nowrap><%=disbLine.getBin()%></td>
							<td width=7% class=browseRow nowrap><%=disbLine.getUmCode()%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(disbLine.getQuantity(), oid)%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbLine.getUnitPrice(), oid)%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbLine.getLineTotal(), oid)%></td>
							<td width=12% class=browseRow nowrap align=right><%=DocumentStatus.toString(disbLine.getStatus())%></td>
						</tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=10 class=browseRow><%=disbLine.getDescription()%></td>
						</tr>
						</table>
<%		}
			if (linecount > 1) { %>
						</div>
<%		}%>
					</td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
<%	if (s_dsb_type.equals("O") && s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) ==0) { %>
				<td width=20% id="filterFields">&nbsp;</td>
				<td width=50% valign=middle align=left>
					<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
					<tr>
						<td valign=middle align=right nowrap>Keyword(s):</td>
						<td valign=middle align=center><input type=text name="as_keywords" value="" size=18></td>
						<td valign=middle><a href="javascript: itemSearch(); void(0);"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0" ALT="Item Search"></a></td>
						<td valign=middle>
							<tsa:hidden name="as_item_type" value="INV"/></td>
						</td>
					</tr>
					</table>
				</td>
<%	} %>
					<td width=30% valign=middle align=right>
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
							<td width=80% class=browseRow nowrap align=right><b>Total:</b></td>
							<td width=20% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbHeader.getSubtotal(), oid)%></td>
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

<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ( s_dsb_type.equals("O") ) {%>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_forward.jsp', 'OtcSave'); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></td>
<%	} else { %>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_forward.jsp', 'DisbForward'); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></td>
<%	} %>
	<td width=50% align=center><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Main Menu"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var itemLocation = "<%=disbHeader.getItemLocation()%>";

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myText = document.getElementById(areaName + "Text");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (hideArea) {
			myImg.src = "<%=contextPath%>/images/arrows_down.gif";
			myImg.alt = "View" + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/arrows_up.gif";
			myImg.alt = "Hide " + type;
		}
	}

	function viewItem(row) {
		var num = document.getElementById("icDsbLine_" + row);
		frm.DisbLine_icDsbLine.value = num.value;
		doSubmit('/inventory/dsb_item.jsp','DisbLineRetrieve');
	}

	function highlightRow(row)
	{
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "selectedRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row)
	{
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "browseRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "browseRow");
	}

	function switchView()
	{
		frm.viewType.value = "WIZARD";
		doSubmit('/inventory/dsb_review.jsp', 'DisbSetProperty;DisbursementRetrieve');
	}

	function itemSearch()
	{
		var itemType = "INV";
		frm.browseName.value = "invitem-invbinlocation";
		setItemKeywordFilterOptions(itemType);
		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
	}

	function viewHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=disbHeader.getIcHeaderHistory()%>;formtype=DSB;DisbHeader_icDsbHeader=<%=b_icDisbHeader%>";
		doSubmitToPopup('/inventory/dsb_history.jsp', 'HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
	}

	function printPreview()
	{
		popupParameters = "DisbHeader_icDsbHeader=<%=b_icDisbHeader%>";
		doSubmitToPopup('/inventory/dsb_preview.jsp', 'DisbursementRetrieve', 'WIDTH=680px', 'HEIGHT=540px');
	}

// End Hide script -->
</SCRIPT>