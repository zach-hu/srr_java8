<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	int i;
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_status"));
	String s_rfqType = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_rfqType"));
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="VendorQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="<%=s_rfqType%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="VendorQuestion_icVendorQuestion" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Vendor Questions</div>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=660px>
		<tr>
			<td>
				<table id="questionTable" border=0 cellpadding=2 cellspacing=0 width=100%>
				<tr valign=middle>
					<td width=3% class=browseRow>&nbsp;</td>
					<td width=62% class=browseRow>&nbsp;</td>
					<td width=20% class=browseRow><b>Submitted By</b></td>
					<td width=15% class=browseRow><b>Date Submitted</b></td>
				</tr>
<%
	List vendorQuestionList = (List) request.getAttribute("vendorQuestionList");
	if (vendorQuestionList!=null)
	{
		for(i = 0;i < vendorQuestionList.size(); i++)
		{
			VendorQuestion vendorQuestion = (VendorQuestion) vendorQuestionList.get(i);
			String	responseText = vendorQuestion.getResponseText();
%>
				<tr>
					<td align=right valign=top>&nbsp;<b><%=i + 1%>.</b>&nbsp;<tsa:hidden name="icQuestion_<%=i%>" value="<%=vendorQuestion.getIcVendorQuestion()%>"/></td>
					<td valign=top>
						<b><%=vendorQuestion.getQuestionText()%></b>
						<br>
<%		if (HiltonUtility.isEmpty(responseText) && s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) ==0) {%>
						<a href="javascript: enterResponse(<%=i%>); void(0);">Click here to respond.</a>
<%		} else {%>
						<%=responseText%>
<%		}%>
					</td>
					<td valign=top nowrap><%=vendorQuestion.getVendorId()%></td>
					<td valign=top nowrap><%=HiltonUtility.getFormattedDate(vendorQuestion.getDatePosted() + " " + vendorQuestion.getTimePosted(), oid, userDateFormat + " hh:mm")%></td>
				</tr>
				<tr><td colspan=4><br></td></tr>
<%		}
		}
		if (vendorQuestionList == null || vendorQuestionList.size() <= 0) { %>
				<tr>
					<td colspan=4 align=center><br>There are no questions yet posted for this solicitation.<br><br></td>
				</tr>
<%	} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var rfqnumber = "<%=s_rfqNumber%>";

	function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function enterResponse(row){
		var num = document.getElementById("icQuestion_" + row);
		frm.VendorQuestion_icVendorQuestion.value = num.value;
		doSubmit('/rfq/rfq_vendor_question.jsp', 'VendorQuestionRetrieveById');
	}

	function returnMe() {
		var pg = "/rfq/rfq_review.jsp";

		if (frm.viewType.value == "CLASSIC") {
			pg = "/rfq/rfq_summary.jsp";
		}
		doSubmit(pg, 'RfqRetrieve');
	}

// End Hide script -->
</SCRIPT>