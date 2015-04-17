<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<%pageContext.setAttribute("language", language); %>
<%
	String icReqHeader = (String)request.getAttribute("RequisitionHeader_icReqHeader");
	String reqNumber = (String)request.getAttribute("RequisitionHeader_requisitionNumber");
	String reqType = (String)request.getAttribute("RequisitionHeader_requisitionType");

%>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icReqHeader%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=reqType%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=reqNumber%>"/>
<tsa:hidden name="emailTo" value="Y"/>
<tsa:hidden name="viewNow" value="N"/>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
			<tsa:td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tsa:tr>
					<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
						<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="requisition" defaultString="Requisition"></tsa:label></div>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
			<tsa:td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
			<tsa:td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tsa:tr>
					<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td align="center">
		<span class="formType"><%=headerEncoder.encodeForHTML(RequisitionType.toString(reqType, oid))%> </span><span class="hdr12">#<%=headerEncoder.encodeForHTML(reqNumber)%></span>
	</tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td width="100%" align="center" valign="top">
		<table border="0" cellpadding="2" cellspacing="0">
		<tsa:tr>
			<tsa:td noWrap="nowrap" id="emailto" align="right"><tsa:label labelName="emailTo" defaultString="Email To"></tsa:label>:</tsa:td>
			<tsa:td><tsa:input type="text" name="email" value="" size="60"></tsa:input></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<br>
<br>
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td align="center" width="50%"><div id="pxbutton"><a href="javascript: emailPdf();"><tsa:label labelName="req-submit" defaultString="Submit"></tsa:label></a></div></tsa:td>
	<tsa:td align="center" width="50%"><div id="pxbutton"><a href="javascript: window.top.hidePopWin();"><tsa:label labelName="req-close" defaultString="Close"></tsa:label></a></div></tsa:td>
</tsa:tr>
</table>
<br>
<br>
<br>
<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	function emailPdf() {
		if (checkemail(frm.email)) {
			doSubmit('system/close_window.jsp', 'EmailReqPdf');
		}
	}

// end hiding contents -->
</SCRIPT>
