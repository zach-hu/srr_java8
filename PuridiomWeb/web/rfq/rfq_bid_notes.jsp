<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<form name="purchaseform" target="_parent" action="/procure" method="POST">

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12>Solicitation Bid Note</div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=left>
		<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" WIDTH="600px">

		<TR>
			<TD>
				<TABLE ID="rfq_bid_notes" BORDER=0 CELLPADDING="1" CELLSPACING="0">
<%
	RfqNote rfqNote = (RfqNote) request.getAttribute("rfqNote");
	if (rfqNote != null)
	{
		RfqNotePK rfqNotePK = (RfqNotePK) rfqNote.getComp_id();
		String s_vendorId = rfqNotePK.getVendorId();
		String s_notesText = rfqNote.getNotesText();
		if (s_notesText==null){s_notesText = "";}
%>
				<TR>
					<TD WIDTH=15%>&nbsp;</TD>
					<TD ALIGN="LEFT" WIDTH=25%><%=s_vendorId%>:</TD>
				</TR>
				<TR>
					<TD WIDTH=15%>&nbsp;</TD>
					<TD ALIGN="LEFT" WIDTH=25%><%=s_notesText%></TD>
				</TR>
<%	}
	else { %>
				<TR>
					<TD WIDTH=15%>&nbsp;</TD>
					<TD WIDTH=15%>&nbsp;</TD>
				</TR>
				<TR>
					<TD WIDTH=15%>&nbsp;</TD>
					<TD ALIGN="LEFT" WIDTH=25%>No notes entered for this bid.</TD>
				</TR>
<%	}	%>
				</TABLE>
			</TD>
		</TR>
		</TABLE>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);">Close</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var Rows = 0;

	myTable = document.getElementById("rfq_bid_notes");
	var TotalRows = myTable.rows.length;

/*	setTableHeights();

	function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
	}

	function viewItem(row) {
		doSubmit('requests/req_item.jsp', 'ViewRequisition');
	}
*/


	function highlightRow(row) {
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "selectedRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row) {
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "browseRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "browseRow");
	}

// End Hide script -->
</SCRIPT>