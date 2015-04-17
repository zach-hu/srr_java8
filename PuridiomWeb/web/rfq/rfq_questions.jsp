<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionRatingMethod" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionResponseType" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	int i;
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_status"));
	String s_rfqType = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_rfqType"));
	String s_fiscalYear = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_fiscalYear"));

	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}

	String s_current_process = "HEADER_QUESTIONS";
	String s_current_page = "/rfq/rfq_questions.jsp";
	String s_current_method = "DoNothing";
	String s_current_process_method = "";
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="<%=s_rfqType%>"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="questionAction" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="rfq-Solicitation-Questions" defaultString="Solicitation Questions" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<table border=0 cellpadding=0 cellspacing=0 width="520px">
		<tsa:hidden name="RfqQuestion_icQuestion" value=""/>
		<tr>
			<td>
				<table id="questionTable" border=0 cellpadding=2 cellspacing=0 width=100%>
				<tr valign="middle">
					<td width=3% class=browseRow>&nbsp;</td>
					<td width=40% class=browseRow><b><tsa:label labelName="rfq-Question" defaultString="Question" /></b></TD>
					<td width=20% class=browseRow><b><tsa:label labelName="rfq-Response-Type" defaultString="Response Type" /></b></td>
					<td width=20% height=18px class=browseRow <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>><b><tsa:label labelName="rfq-Rating-Method" defaultString="Rating Method" /></b></td>
					<td width=12% height=18px class=browseRow <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>><b><tsa:label labelName="rfq-Max-Points" defaultString="Max Points" /></b></td>
<%	if (editMode) { %>
					<td width=5%><b><tsa:label labelName="rfq_delete" defaultString="Delete" /></b></TD>
<%	}%>
				</tr>
<%
	List rfqQuestionList = (List) request.getAttribute("rfqQuestionList");
	if (rfqQuestionList!=null)
	{
		for(i = 0;i < rfqQuestionList.size(); i++)
		{
			RfqQuestion rfqQuestion = (RfqQuestion) rfqQuestionList.get(i);
			RfqQuestionPK rfqQuestionPK = (RfqQuestionPK) rfqQuestion.getComp_id();
			BigDecimal bd_icQuestion = rfqQuestionPK.getIcQuestion();
			BigDecimal bd_sequence = rfqQuestion.getSequence();
			String s_responseType = StdQuestionResponseType.toString(rfqQuestion.getResponseType(), oid);
			String s_ratingMethod = StdQuestionRatingMethod.toString(rfqQuestion.getRatingMethod(), oid);
			if (rfqQuestion.getRatingMethod().equals(StdQuestionRatingMethod.WEIGHTED_RATING)) {
				s_ratingMethod = s_ratingMethod + " (" + rfqQuestion.getWeight() + ")";
			}
%>
				<tr>
					<td width=3% align=right valign=top>&nbsp;<%=bd_sequence%>.&nbsp;<tsa:hidden name="RfqQuestion_sequence" value="<%=bd_sequence%>"/><tsa:hidden id="icQuestion_<%=i%>" name="icQuestion_<%=i%>" value="<%=bd_icQuestion%>"/></td>
					<td width=42% valign=top><a href="javascript: editQuestion(<%=i%>); void(0);"><%=rfqQuestion.getQuestionText()%></a><tsa:hidden name="RfqQuestion_questionText" value="<%=rfqQuestion.getQuestionText()%>"/></td>
					<td width=20% valign=top nowrap><%=s_responseType%><tsa:hidden name="RfqQuestion_responseType"  value="<%=rfqQuestion.getResponseType()%>"/></td>
					<td width=20% valign=top nowrap <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>><%=s_ratingMethod%><tsa:hidden name="RfqQuestion_ratingMethod" value="<%=rfqQuestion.getRatingMethod()%>"/></td>
					<td width=12% valign=top nowrap <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>><%=rfqQuestion.getMaxPoints()%><tsa:hidden name="RfqQuestion_maxPoints" value="<%=rfqQuestion.getMaxPoints()%>"/></td>
<%		if (editMode) { %>
					<td width=5% align=center valign=top><a href="javascript: if ( verifyAction('Delete this question?') ) { deleteQuestion(<%=i%>); void(0); }"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%			}%>
				</tr>
<%		}
		}
		if (rfqQuestionList == null || rfqQuestionList.size() <= 0) { %>
				<tr>
					<td colspan=5 align=center><br>There are no questions attached to this solicitation.<br><br></td>
				</tr>
<%	} %>
				</TABLE>
			</TD>
		</TR>
		<TR VALIGN="MIDDLE">
<%	if (editMode) { %>
			<TD>
				<br>
				<TABLE WIDTH=100%>
				<TR>
					<TD WIDTH=8%>&nbsp;</TD>
					<TD WIDTH=30% ALIGN="CENTER">
						<A HREF="javascript: addNewQuestion(); void(0);" ><FONT CLASS="reset"><B><tsa:label labelName="rfq-Add-NewQuestion" defaultString="Add new question" /></B></FONT></A>&nbsp;
					</TD>
					<TD WIDTH=30% ALIGN="CENTER">
						<A HREF="javascript: browseStdQuestions(); void(0);" ><FONT CLASS="reset"><B><tsa:label labelName="rfq-Add-StandardQuestion" defaultString="Add standard question" /></B></FONT></A>&nbsp;
					</TD>
					<TD WIDTH=30% ALIGN="CENTER">
<%		if ( rfqQuestionList.size() > 1) { %>
						<A HREF="javascript:  if ( verifyAction('Delete all questions?') ) { doSubmit('/rfq/rfq_questions.jsp', 'RfqQuestionDeleteByHeader;RfqRetrieve'); } void(0);"><FONT CLASS="reset"><B>Delete all</B></FONT></A>&nbsp;
<%		} %>
					</TD>
				</TR>
				</TABLE>
			</TD>
<%	} %>
		</TR>
		</TABLE>
	</td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<td rowspan=2 valign=top><%@ include file="/rfq/rfq_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%		if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Save" defaultString="Save" /></a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%		} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%		}%>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var rfqnumber = "<%= headerEncoder.encodeForJavaScript(s_rfqNumber) %>";
	var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscalYear) %>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	var browser = browserCheck();

	setTableHeights();

	function thisLoad()
	{
		f_StartIt();
<%	if (!editMode) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function editQuestion(row){
		var num = document.getElementById("icQuestion_" + row);
		frm.RfqQuestion_icQuestion.value = num.value;
		frm.questionAction.value = "Edit";
		doSubmit('/rfq/rfq_question_edit.jsp', 'RfqQuestionRetrieveById');
	}

	function addNewQuestion() {
		frm.questionAction.value = "Add";
		doSubmit('/rfq/rfq_question_edit.jsp', 'RfqQuestionCreate');
	}

	function deleteQuestion(row)
	{
		var num = document.getElementById("icQuestion_" + row);
		frm.RfqQuestion_icQuestion.value = num.value;
		doSubmit('/rfq/rfq_questions.jsp', 'RfqQuestionDelete;RfqRetrieve');
	}

	function deleteAllQuestions(){
		var myTable = document.getElementById("questionTable");
		var rows = myTable.rows.length;
		rows = rows-1;
		for(i=0;i<rows;i++){
			alert(i);
			var num = document.getElementById("icQuestion_" + i);
			frm.RfqQuestion_icQuestion.value = num.value;
			doSubmit('/rfq/rfq_questions.jsp', 'RfqQuestionDelete;RfqRetrieve');
		}
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
/*		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
*/	}

	function browseStdQuestions()
	{
		frm.browseName.value = "stdquestion";
		doSubmit('/browse/browse_std_question.jsp', 'BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>