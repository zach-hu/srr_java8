<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.po.tasks.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");

	String icPoHeader = (String) request.getAttribute("PoHeader_icPoHeader");
	String poNumber = (String) request.getAttribute("PoHeader_poNumber");
	String poStatus = (String) request.getAttribute("PoHeader_status");
	String revNumber = (String) request.getAttribute("PoHeader_revisionNumber");
	String relNumber = (String) request.getAttribute("PoHeader_releaseNumber");
	String buyerCode = (String) request.getAttribute("PoHeader_buyerCode");
	String	vendorId = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));
	BigDecimal bd_zero = new BigDecimal(0);
	BigDecimal bd_revision = new BigDecimal(revNumber);
	BigDecimal bd_release = new BigDecimal(relNumber);

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	Date dateEntered = d_today;
	Date dateChanged = d_today;

	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_po_number = poHeader.getPoNumber();
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	String	s_po_type = poHeader.getPoType();
	String	s_po_status = poHeader.getStatus();
	String	s_fiscal_year = poHeader.getFiscalYear();
	String	s_buyer_code = poHeader.getBuyerCode();
	String	s_requisitioner_code = poHeader.getRequisitionerCode();
	String	s_authorized_by_code = poHeader.getAuthorizationCode();

	boolean bAllowEdit= true;
	if (role.getAccessRights("PO") < 2)
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(buyerCode))
	{
		bAllowEdit = false;
	}

	String s_returnPage = "";
	if (s_view.equalsIgnoreCase("CLASSIC"))
	{
		s_returnPage = "/orders/po_summary.jsp";
	}
	else
	{
		s_returnPage = "/orders/po_review.jsp";
	}

	String	s_current_process = "BLANKET_SECURITY";
	String	s_current_page = "/orders/po_security.jsp";
	String	s_current_method = "PoSecurityUpdateByPoNumber";
	String	s_current_process_method = "";
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=poNumber%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poNumber%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=poHeader.getReleaseNumber()%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=poHeader.getRevisionNumber()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="Account_icHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=bd_ic_po_header%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="blanketSecurity" defaultString="Blanket Security" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b><tsa:label labelName="order-number" defaultString="Order #" />:&nbsp;</b></td>
			<td width=100px><%=headerEncoder.encodeForHTML(poNumber)%></td>
<%	if (bd_release.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b><tsa:label labelName="release-number" defaultString="Release #" />:&nbsp;</b></td>
			<td width=100px><%=bd_release%></td>
<%	}
		if (bd_revision.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b><tsa:label labelName="revision-number" defaultString="Revision #" />:&nbsp;</b></td>
			<td width=100px><%=bd_revision%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b>Status:&nbsp;</b></td>
			<td width=100px><%=DocumentStatus.toString(poStatus, oid)%></td>
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

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
	<td align="center" valign="top">
		<table border="0" cellspacing="0" cellpadding="0" width="500px">
		<tr>
			<td>
				<table border="0" cellspacing="0" cellpadding="0" width="500px">
				<tr>
					<td width="100%" align="center" valign="top">
						<table border="0" cellspacing="0" cellpadding=4 width="500px">
						<tr>
							<tsa:td field="securityType" width="98px" noWrap="nowrap">&nbsp;<tsa:label labelName="securityType" defaultString="Type" /></tsa:td>
							<tsa:td field="accessID" width="144px" noWrap="nowrap">&nbsp;<a href="javascript: browseAccess(); void(0);"><tsa:label labelName="accessID" defaultString="Access ID" /></a></tsa:td>
							<tsa:td field="po-dateChanged" width="96px" noWrap="nowrap">&nbsp;<tsa:label labelName="po-dateChanged" defaultString="Date Changed" /></tsa:td>
							<tsa:td field="po-lastChangedBy" width="114px" noWrap="nowrap">&nbsp;<tsa:label labelName="po-lastChangedBy" defaultString="Last Changed By" /></tsa:td>
							<td width="15px" nowrap>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="100%" align="center" valign="top">
						<table id="security" border="0" cellspacing="0" cellpadding="0" width="500px" class="browseRow">
<%
	List poSecurityList = (List) request.getAttribute("poSecurityList");
	if (poSecurityList != null && poSecurityList.size() > 0)
	{
		for (int i = 0; i < poSecurityList.size(); i++)
		{
			PoSecurity poSecurity = (PoSecurity) poSecurityList.get(i);
			PoSecurityPK poSecurityPK = poSecurity.getComp_id();

			UserProfile owner = UserManager.getInstance().getUser(oid, poSecurity.getOwner());
			UserProfile lastChangedBy = UserManager.getInstance().getUser(oid, poSecurity.getLastChangedBy());
			String	accessName = "";

			if (poSecurityPK.getAccessType().equals("U")) {
				accessName = UserManager.getInstance().getUser(oid, poSecurityPK.getAccessId()).getDisplayName();
			} else {
				accessName = poSecurityPK.getAccessId();
			}
			pageContext.setAttribute("i", i);
%>
						<tr>
							<tsa:td field="securityType">
								<select name="PoSecurity_accessType" onfocus="setCurrentRow(<%=i%>);">
									<option value="U" <% if (poSecurityPK.getAccessType().equals("U")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-user", "User")%></option>
									<option value="D" <% if (poSecurityPK.getAccessType().equals("D")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-department", "Department")%></option>
								</select>
							</tsa:td>
							<tsa:td field="accessID">
								<tsa:hidden name="PoSecurity_accessId" value="<%=poSecurityPK.getAccessId()%>"/>
								<tsa:input type="text" name="asAccessName" value="<%=accessName%>" onchange="changeAccessId();upperCase(this);" onfocus="setCurrentRow(${i});" />
							</tsa:td>
							<tsa:td field="po-dateChanged"><tsa:input type="mintext" name="PoSecurity_dateChanged" value="<%=HiltonUtility.getFormattedDate(poSecurity.getDateChanged(), oid, userDateFormat)%>" disabled="true" /></tsa:td>
							<tsa:td field="po-lastChangedBy">
								<tsa:input type="midtext" name="as_lastChangedByName" value="<%=lastChangedBy.getDisplayName()%>" />
								<tsa:hidden name="PoSecurity_lastChangedBy" value="<%=poSecurity.getLastChangedBy()%>"/>
							</tsa:td>
<%		if (bAllowEdit) { %>
							<td id=delete_<%=i%> width="15px"><a href="javascript: if (confirmDelete()) { deleteMe(<%=i%>); void(0); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
							<td id=delete_<%=i%> width="15px"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></td>
<%		} %>
						</tr>
						<tr>
							<tsa:td field="po-ownerSecurity">&nbsp;</tsa:td>
							<tsa:td field="po-dateEnteredSecurity"><tsa:label labelName="po-dateEnteredSecurity" defaultString="Date Entered" /> by <tsa:label labelName="po-ownerSecurity" defaultString="Owner" /></tsa:td>
							<tsa:td field="po-dateEnteredSecurity"><tsa:input type="mintext" name="PoSecurity_dateEntered" value="<%=HiltonUtility.getFormattedDate(poSecurity.getDateEntered(), oid, userDateFormat)%>" disabled="true" /></tsa:td>
							<tsa:td field="po-ownerSecurity">
								<tsa:input type="midtext" name="as_ownerName" value="<%=owner.getDisplayName()%>" />
								<tsa:hidden name="PoSecurity_owner" value="<%=poSecurity.getOwner()%>"/>
							</tsa:td>
						</tr>
<%
		}
	}
%>
						</table>
					</td>
				</tr>
<%	if (bAllowEdit) { %>
				<tr>
					<td colspan="6">
						<br>
						<table border=0 cellspacing=0 cellpadding=2 width=500px align=center>
						<tr>
							<td nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><tsa:label labelName="add-new" defaultString="Add new" /></B></font></a></td>
							<td>&nbsp;</td>
							<td align=right nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><tsa:label labelName="deleteAll" defaultString="delete all" /></B></font></a>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
<%	} %>
				</table>
			</td>
		</tr>

		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
		<td rowspan=2 align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%--table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ( bAllowEdit && !s_view.equals("WIZARD")) { %>
	<td width=50% align=center><a href="javascript: submitThis(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('<%=s_returnPage%>', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	} else { %>
	<td width=100% align=center><a href="javascript: doSubmit('<%=s_returnPage%>', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	} %>
</tr>
</table--%>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%=headerEncoder.encodeForJavaScript(s_po_number)%>";

	var currentRow = 0;
	var myTable = document.getElementById("security");
	var count = 0;
	if (myTable.rows.length > 0) {
		count = myTable.rows.length / 2;
	}

<%	if (bAllowEdit) { %>
			if (count <= 0)
			{
				addNew();
			}
<%	} %>

	function addNew()
	{
		myRow = myTable.insertRow(count*2);

		myCell = myRow.insertCell(-1);
		id = "accessType";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML = "<select name=\"PoSecurity_accessType\" onfocus='setCurrentRow(" + count + ");'><option value=\"U\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-user", "User")%></option><option value=\"D\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-department", "Department")%></option></select>";
		<%=HtmlWriter.cellVisibility(oid, "securityType")%>
		<%=HtmlWriter.cellDisplay(oid, "securityType")%>

		myCell = myRow.insertCell(-1);
		id = "accessId";
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=hidden name=\"PoSecurity_accessId\" value=\"\"><input type=text name=\"asAccessName\" size=24 value=\"\" onChange=\"changeAccessId();upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "accessID")%>
		<%=HtmlWriter.cellDisplay(oid, "accessID")%>

		myCell = myRow.insertCell(-1);
		id = "dateChanged";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"PoSecurity_dateChanged\" size=15 value=\"<%=Dates.today(userDateFormat, userTimeZone)%>\" disabled>";
		<%=HtmlWriter.cellVisibility(oid, "po-dateChanged")%>
		<%=HtmlWriter.cellDisplay(oid, "po-dateChanged")%>

		myCell = myRow.insertCell(-1);
		id = "lastChangedBy";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"as_lastChangedByName\" size=20 value=\"<%=user.getDisplayName()%>\" disabled><input id=\"lastchangedby\" type=hidden name=\"PoSecurity_lastChangedBy\">";
		var userid = '${esapi:encodeForJavaScript(userId)}';
		$('#lastchangedby').val(userid);
		<%=HtmlWriter.cellVisibility(oid, "po-lastChangedBy")%>
		<%=HtmlWriter.cellDisplay(oid, "po-lastChangedBy")%>

		myCell = myRow.insertCell(-1);
		id = "delete_" + count;
		myCell.id = id;
		//myCell.width = "25px";
		myCell.innerHTML = "<a href=\"javascript: if (confirmDelete()) { deleteMe(" + count + "); void(0); } \"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		myRow = myTable.insertRow(count*2+1);

		myCell = myRow.insertCell(-1);
		id = "emptylabel";
		myCell.id = id;
		//myCell.width = "125px";
		myCell.innerHTML = "&nbsp;";
		<%=HtmlWriter.cellVisibility(oid, "po-ownerSecurity")%>
		<%=HtmlWriter.cellDisplay(oid, "po-ownerSecurity")%>

		myCell = myRow.insertCell(-1);
		id = "emptylabel";
		myCell.id = id;
		//myCell.width = "125px";
		myCell.innerHTML = "<tsa:label labelName="po-dateEntered" defaultString="Date Entered" /> by <tsa:label labelName="po-owner" defaultString="Owner" />";
		<%=HtmlWriter.cellVisibility(oid, "po-ownerSecurity")%>
		<%=HtmlWriter.cellDisplay(oid, "po-ownerSecurity")%>

		myCell = myRow.insertCell(-1);
		id = "dateEntered";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"PoSecurity_dateEntered\" size=15 value=\"<%=Dates.today(userDateFormat, userTimeZone)%>\" disabled>";
		<%=HtmlWriter.cellVisibility(oid, "po-dateEnteredSecurity")%>
		<%=HtmlWriter.cellDisplay(oid, "po-dateEnteredSecurity")%>

		myCell = myRow.insertCell(-1);
		id = "owner";
		myCell.id = id;
		//myCell.width = "125px";
		myCell.innerHTML = "<input type=text name=\"as_ownerName\" size=20 value=\"<%=user.getDisplayName()%>\" disabled><input type=hidden name=\"PoSecurity_owner\" value=\"${esapi:encodeForJavaScript(userId)}\">";
		<%=HtmlWriter.cellVisibility(oid, "po-ownerSecurity")%>
		<%=HtmlWriter.cellDisplay(oid, "po-ownerSecurity")%>

		if (count > 0)
		{
			frm.PoSecurity_accessType[count].focus();
			if (navigator.appName == "Microsoft Internet Explorer")
			frm.PoSecurity_accessType[count].fireEvent("onfocus");
		}
		/*else
		{
			frm.PoSecurity_accessType.focus();
			frm.PoSecurity_accessType.fireEvent("onfocus");
		}*/

		currentRow = count;
		count++;
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function browseAccess()
	{
		count = 0;
		if (myTable.rows.length > 0) {
			count = myTable.rows.length / 2;
		}
		if (count > 1)
		{
			var type = frm.PoSecurity_accessType[currentRow].value;
			if (type == "U")
			{
				browseSchedule('PoSecurity_accessId', 'user'); void(0);
			}
			else
			{
				browseSchedule('PoSecurity_accessId', 'department'); void(0);
			}
		}
		else
		{
			var type = frm.PoSecurity_accessType.value;
			//browseSchedule('PoSecurity_accessId', 'user'); void(0);
			if (type == "U")
			{
				browseSchedule('PoSecurity_accessId', 'user'); void(0);
			}
			else
			{
				browseSchedule('PoSecurity_accessId', 'department'); void(0);
			}
		}
	}

	function confirmDelete() {
		return confirm('Are you sure you want to delete this security access?');
	}

	function deleteMe(row)
	{
		//var currentRows = myTable.rows.length;
		var currentRows = 0;
		if (myTable.rows.length > 0) {
			currentRows = myTable.rows.length / 2;
		}

		if (currentRows == 0)
		{
			return;
		}
		/*else if (currentRows > 1)
		{
			for (var i = row; i < (currentRows - 1); i++)
			{
				var accessType = frm.PoSecurity_accessType[i + 1].value;
				var accessId = frm.PoSecurity_accessId[i + 1].value;
				var owner = frm.PoSecurity_owner[i + 1].value;
				var dateEntered = frm.PoSecurity_dateEntered[i + 1].value;
				var dateChanged = frm.PoSecurity_dateChanged[i + 1].value;
				var lastChangedBy = frm.PoSecurity_lastChangedBy[i + 1].value;

				frm.PoSecurity_accessType[i].value = accessType;
				frm.PoSecurity_accessId[i].value = accessId;
				frm.PoSecurity_owner[i].value = owner;
				frm.PoSecurity_dateEntered[i].value = dateEntered;
				frm.PoSecurity_dateChanged[i].value = dateChanged;
				frm.PoSecurity_lastChangedBy[i].value = lastChangedBy;
			}
		}*/

		myTable = document.getElementById("security");
		myTable.deleteRow(row * 2 + 1);
		myTable.deleteRow(row * 2);

		if (currentRows <= 1)
		{
			frm.deleteall.value = "TRUE";
		}
		count--;

		if (count == 0)
		{
			addNew();
		}
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all security access for this Order?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("security");
			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();
		}
	}

	function thisLoad()
	{
		f_StartIt();
<%	if ( !bAllowEdit ) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function changeAccessId()
	{
		count = 0;
		if (myTable.rows.length > 0) {
			count = myTable.rows.length / 2;
		}

		if (count > 1) {
			frm.PoSecurity_accessId[currentRow].value = "";
		}
		else {
			frm.PoSecurity_accessId.value = "";
		}
	}

	function validation()
	{
		var msg = "";
		var currentRows = 0;
		if (myTable.rows.length > 0) {
			currentRows = myTable.rows.length / 2;
		}
		var validate = true;

		if (frm.PoSecurity_poNumber.value != "N/A") {
			if (currentRows > 1)
			{
				for (var i=0; i<currentRows; i++)
				{
					if (isEmpty(frm.PoSecurity_accessId[i].value))
					{
						validate = false;
						msg = "You must enter a valid Access ID on the row " + (i + 1);
						break;
					}
				}
			}
			else if(!isEmpty(frm.asAccessName.value))
			{
				if (isEmpty(frm.PoSecurity_accessId.value))
				{
					validate = false;
					msg = "You must enter a valid Access ID on the row 1";
				}
			}
			else
			{
				frm.deleteall.value = "TRUE";
				myTable = document.getElementById("security");
				count = myTable.rows.length;
				for (i = 0;i < count; i++)
				{
					myTable.deleteRow(0);
				}
				count = 0;
				addNew();
			}
		}
		else {
			if (currentRows > 1) {
				alert(" The order must be saved prior to setting up Blanket Security!  Please save the Order and return to the Blanket Security Page.");
			}
			else if(!isEmpty(frm.asAccessName.value)) {
				alert("The order must be saved prior to setting up Blanket Security!  Please save the Order and return to the Blanket Security Page.");
			}
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("security");
			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();

			if (currentRows > 1) {
				return false;
			}
		}

		if(!validate)
			alert(msg);

		return validate;
	}

	function validationInSave()
	{
		var msg = "";
		var currentRows = 0;
		if (myTable.rows.length > 0) {
			currentRows = myTable.rows.length / 2;
		}
		var validate = true;

		if (frm.PoSecurity_poNumber.value != "N/A") {
			if (currentRows > 1)
			{
				for (var i=0; i<currentRows; i++)
				{
					if (isEmpty(frm.PoSecurity_accessId[i].value))
					{
						validate = false;
						msg = "You must enter a valid Access ID on the row " + (i + 1);
						break;
					}
				}
			}
			else if(!isEmpty(frm.asAccessName.value))
			{
				if (isEmpty(frm.PoSecurity_accessId.value))
				{
					validate = false;
					msg = "You must enter a valid Access ID on the row 1";
				}
			}
			else
			{
				frm.deleteall.value = "TRUE";
				myTable = document.getElementById("security");
				count = myTable.rows.length;
				for (i = 0;i < count; i++)
				{
					myTable.deleteRow(0);
				}
				count = 0;
				addNew();
			}
		}
		else {
			if (currentRows > 1) {
				alert(" The order must be saved prior to setting up Blanket Security!  Please save the Order and return to the Blanket Security Page.");
			}
			else if(!isEmpty(frm.asAccessName.value)) {
				alert("The order must be saved prior to setting up Blanket Security!  Please save the Order and return to the Blanket Security Page.");
			}
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("security");
			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();
		}

		if(!validate)
			alert(msg);

		return validate;
	}
	
	function submitThis()
	{
		if (validationInSave())
			<%if (!poNumber.equals("N/A")) { %>
				doSubmit("/orders/po_security.jsp", 'PoSecurityUpdateByPoNumber;PoSecurityRetrieveBy');
			<%} else { %>
				doSubmit('<%=s_returnPage%>', 'PoSecurityUpdateByPoNumber;PoRetrieve');
			<%} %>
	}

	function updateLastChangedBy(row)
	{
		if (row)
		{
			frm.as_lastChangedByName[row].value = '<%=headerEncoder.encodeForJavaScript(user.getDisplayName())%>';
			frm.PoSecurity_dateChanged[row].value = '<%=HiltonUtility.getFormattedDate(d_today, oid, userDateFormat)%>';
		}
		else
		{
			frm.as_lastChangedByName.value = '<%=user.getDisplayName()%>';
			frm.PoSecurity_dateChanged.value = '<%=HiltonUtility.getFormattedDate(d_today, oid, userDateFormat)%>';
		}
	}

// End Hide script -->
</SCRIPT>