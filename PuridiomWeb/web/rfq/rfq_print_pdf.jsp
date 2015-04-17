<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<%
	String icRfqHeader = (String)request.getAttribute("RfqHeader_icRfqHeader");
	String s_form_number = (String)request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqType = (String)request.getAttribute("RfqHeader_rfqType");
	String printtype = (String)request.getAttribute("printtype");
	if(HiltonUtility.isEmpty(printtype))
	{
		printtype = "Rfq";
	}
	
	Encoder encoder = DefaultEncoder.getInstance();
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=icRfqHeader%>" />
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}" />
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_form_number%>" />
<tsa:hidden name="allowBrowse" value="true" />
<tsa:hidden name="emailTo" value="N" />
<tsa:hidden name="printtype" value="<%=printtype %>" />

<table border="0" cellpadding="0" cellspacing="0" width="505px">
<tr><td><br></td></tr>
<tr>
	<td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tr>
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" valign="middle">
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitation", "Solicitation")%></div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
				</tr>
				<tr>
					<td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align="center">
		<font class="formType"><%=encoder.encodeForHTML(RfqType.toString(s_rfqType, oid))%> </font><font class="hdr12">#<%=s_form_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<%@ include file="/print/print_options_general.jsp" %>
</table>
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="455px">
	<TR>
<!--  		<td align="center"><a href="javascript: pdfOptions();"><img class="button" src="<%=contextPath%>/images/button_print.gif" border="0" alt="Print" ></a></td>-->
		<TD align="CENTER"><a href="javascript: doSubmit('rfq/rfq_review.jsp', 'DoNothing;RfqRetrieve');"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0></a></TD>
	</TR>
</TABLE>
<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	function pdfOptions()
	{
		popupParameters = "RfqHeader_icRfqHeader=<%=icRfqHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.print_option[0].checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToLookup('', 'Print<%=printtype%>Pdf', 'width=775px', 'height=850px');
		}
		else if(frm.print_option[1].checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('rfq/rfq_review.jsp', 'Email<%=printtype%>Pdf;RfqRetrieve');
			}
		}
	}

	function popupParameters()
	{
		popupParameters = "RfqHeader_icRfqHeader=<%=icRfqHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
	}

	function emailPdf()
	{
		if(checkemail(frm.email))
		{
			frm.viewNow.value = 'N';
			frm.emailTo.value = 'Y';
			doSubmit('rfq/rfq_review.jsp', 'Email<%=printtype%>Pdf;RfqRetrieve');
		}
	}

	function viewNow()
	{
		popupParameters();
		frm.emailTo.value = 'N';
		popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
		doSubmitToLookup('', 'Print<%=printtype%>Pdf', 'width=775px', 'height=850px');
	}

// end hiding contents -->
</SCRIPT>
