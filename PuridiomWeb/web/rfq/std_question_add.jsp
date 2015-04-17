<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<%
	int i;
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_status = (String) request.getAttribute("RfqHeader_status");
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Add Standard Questions</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Solicitation #:</b></td>
			<td width=100px><tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/><%=s_rfqNumber%></td>
			<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
			<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><tsa:hidden name="RfqHeader_status" value="<%=s_status%>"/><%=DocumentStatus.toString(s_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
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
		<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" WIDTH="600px">

		<TR>
			<TD>
				<TABLE BORDER=0 CELLPADDING="1" CELLSPACING="0">
				<TR VALIGN="MIDDLE">
					<TD WIDTH=5%>&nbsp;</TD>
					<TD WIDTH=25% class=browseRow>&nbsp;<b>Title</b></td>
					<TD WIDTH=50% class=browseRow>&nbsp;<b>Question</b></TD>
					<TD WIDTH=15% class=browseRow>&nbsp;<b>Response Type</b></td>
					<TD WIDTH=5%><b>Select</b></TD>
				</TR>
<%
	List stdQuestionList = (List) request.getAttribute("stdQuestionList");
	int rows = 0;
	if(stdQuestionList!=null){
		for(i=0;i<stdQuestionList.size();i++)
		{
			StdQuestion stdQuestion = (StdQuestion) stdQuestionList.get(i);
			BigDecimal b_icQuestion = stdQuestion.getIcQuestion();
			String s_questionTitle = stdQuestion.getQuestionTitle();
			String s_questionText = stdQuestion.getQuestionText();
			String s_responseType = stdQuestion.getResponseType();
			rows++;
%>
				<TR>
					<TD WIDTH=5%>&nbsp;<tsa:hidden name="icQuestion_<%=i%>" value="<%=b_icQuestion%>"/></TD>
					<TD WIDTH=25%>&nbsp;<%=s_questionTitle%><tsa:hidden name="questionTitle" value="<%=s_questionTitle%>"/></TD>
					<TD WIDTH=50%><%=s_questionText%><tsa:hidden name="questionText" value="<%=s_questionText%>"/></TD>
					<TD WIDTH=15% ALIGN="CENTER"><%=s_responseType%><tsa:hidden name="responseType" value="<%=s_responseType%>"/></TD>
					<TD WIDTH=5% ALIGN="CENTER"><INPUT TYPE="checkbox" NAME="c_checkbox_<%=i%>"></TD>
				</TR>
<%	} } %>
				</TABLE>
				<TABLE>
					<TR ID="StdQuestion"></TR>
				</TABLE>
			</TD>
		</TR>
		<TR>
			<TD ALIGN="RIGHT">
				<HR>
			</TD>
		</TR>
		</TABLE>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: addStdQuestions(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_questions.jsp', 'RfqQuestionRetrieveByHeader'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();

	setTableHeights();

	function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
/*		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
*/	}

	function addStdQuestions(){
		var count = <%=rows%>;
		for(i=0;i<count;i++){
			var cbox = document.getElementById("c_checkbox_" + i);
			if (cbox.checked==true){
				var ic = document.getElementById("icQuestion_" + i);
				myRow = document.getElementById("StdQuestion");
				myCell = myRow.insertCell();
				myCell.innerHTML = "<INPUT TYPE=\"HIDDEN\" NAME=\"StdQuestion_icQuestion\" value=\"" + ic.value + "\">";
			}
		}
		doSubmit('/rfq/rfq_questions.jsp', 'RfqQuestionAddStandard;RfqQuestionRetrieveByHeader');
	}

// End Hide script -->
</SCRIPT>