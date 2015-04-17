<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.currcode.CurrencyManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	List retrieveTracking = (List) request.getAttribute("retrieveTracking");

	String trackingNumber = HiltonUtility.ckNull((String) request.getAttribute("trackingNumber"));
	String receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));

	String viewTrackingNumber = HiltonUtility.ckNull((String)request.getAttribute("viewTrackingNumber"));

	String s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	String bd_revision_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_revisionNumber"));
	String bd_release_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_releaseNumber"));
	String PoLine_icPoHeader = HiltonUtility.ckNull((String)request.getAttribute("PoLine_icPoHeader"));
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="TrackingData_icHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="UserProfile_userId" value="<%=uid%>"/>
<tsa:hidden name="UserProfile_organizationId" value="<%= oid %>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=PoLine_icPoHeader%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=PoLine_icPoHeader%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Tracking Information Receipt</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
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

<table border=0 cellpadding=0 cellspacing=0>
<tr>
	<td width=10px>&nbsp;</td>
	<td>
		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2>
						<tr>
							<%--To set up a BROWSE use the Label Manager!--%>
							<td height=1px width=200px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "trackingNumber", "Tracking Number", false, true, "TrackingData_trackingNumber")%></td>
							<td height=18px width=250px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "trackingDescription", "Tracking Description", false, true, "TrackingData_trackingDesc")%></td>
							<td height=18px width=200px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner", false, true, "TrackingData_owner")%></td>
							<td width=10px><img src="<%=contextPath%>/images/none.gif" border=0 width=9px height=1px></td>
						</tr>
						</table>

						<table id="tracking" border=0 cellspacing=0 cellpadding=2 width=100%>
<%	if (retrieveTracking != null && retrieveTracking.size() > 0)
		{
			for (int i = 0; i < retrieveTracking.size(); i++)
			{
				TrackingData trackingData = (TrackingData) retrieveTracking.get(i);
				TrackingDataPK trackingDataPK = trackingData.getComp_id();
				String owner = HiltonUtility.ckNull((String) trackingData.getOwner());
				if(HiltonUtility.isEmpty(owner))
				{
					owner = uid;
				}
%>
						<tr>
							<td class=browseRow width=100px><input type="text" name="TrackingData_trackingNumber" value="<%=trackingData.getTrackingNumber()%>" size="30" maxlength="30" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td class=browseRow width=100px><input type="text" name="TrackingData_trackingDesc" value="<%=trackingData.getTrackingDesc()%>" size="50" maxlength="255" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td class=browseRow width=100px><input type="text" name="TrackingData_owner" value="<%=owner%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);" disabled="disabled"></td>
							<td id=delete_<%=i%> class=browseRow width=10px><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
						</tr>
<%		}
		} %>
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

<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td width=50% align=center nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></B></font></a></td>
	<td>&nbsp;</td>
	<td width=50% align=center nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></font></a>&nbsp;</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveTrackingNumber(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/receipts/rec_general_info.jsp', 'ReceiptLineRetrieveByHeader;ReceiptHeaderRetrieveById'); void(0);">Cancel</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/receipts/rec_tracking_info.jsp", "ReceiptHeaderRetrieveById", "Tracking Number");

	var myTable = document.getElementById("tracking");
	var count = myTable.rows.length;
	var viewTrackingNumber = '<%=headerEncoder.encodeForJavaScript(viewTrackingNumber)%>';

	if(viewTrackingNumber=='Y') {
		addNew();
	}
	currentRow = 0;

	function addNew()
	{
		myRow = createRow(myTable);

		myCell = createCell(myRow);
		id = "trackingNumber";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"TrackingData_trackingNumber\" size=30 maxlength=30 value=\"<%=trackingNumber%>\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";

		myCell = createCell(myRow);
		id = "trackingDesc";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"TrackingData_trackingDesc\" size=50 maxlength=255 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";


		myCell = createCell(myRow);
		id = "owner";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"TrackingData_owner\" size=15 maxlength=20 value=\"<%=uid%>\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");' disabled=\"disabled\">";

		myCell = createCell(myRow);
		id = "delete_" + count;
		myCell.id = id;
		myCell.width = "10px";
		myCell.innerHTML = "<a href=\"javascript:deleteMe(" + count + "); void(0);\"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";





		currentRow = count;
		count++;
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function saveTrackingNumber()
	{
		if(validTrackingNumber()) {
			var handlers = 'TrackingDataUpdate;ReceiptLineRetrieveByHeader;ReceiptHeaderRetrieveById';
			doSubmit('/receipts/rec_general_info.jsp', handlers);
		}
		else
		{
			alert('Enter the tracking number.');
		}

	}


	function deleteMe(row)
	{
		if (confirm("Are you sure you wish to delete this rule?"))
		{
			var currentRows = myTable.rows.length;

			if (currentRows == 0)
			{
				return;
			}
			else if (currentRows > 1)
			{
				for (var i = row; i < (currentRows - 1); i++)
				{

					frm.TrackingData_trackingNumber[i].value = frm.TrackingData_trackingNumber[i + 1].value;
					frm.TrackingData_trackingDesc[i].value = frm.TrackingData_trackingDesc[i + 1].value;
					frm.TrackingData_owner[i].value = frm.TrackingData_owner[i + 1].value;

				}
			}

			myTable = document.getElementById("tracking");
			myTable.deleteRow(currentRows - 1);

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
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all rules for this Approver?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("tracking");
			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();
		}
	}

	function validTrackingNumber(){

		var currentRows = myTable.rows.length;

		if (currentRows > 1)
		{
			for (var i = 0; i < currentRows; i++)
			{
				if(isEmpty(frm.TrackingData_trackingNumber[i].value)) {
					return false;
				}
			}
		}
		else if(currentRows == 1)
		{
			if(isEmpty(frm.TrackingData_trackingNumber.value)) {
				return false;
			}
		}

		return true;
	}

// End Hide script -->
</SCRIPT>