<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% pageContext.setAttribute("oid", oid);
   pageContext.setAttribute("language", language);%>
<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	String forwardedTo = (String) request.getAttribute("forwardedTo");

	forwardedTo = HiltonUtility.ckNull(forwardedTo);
	pageContext.setAttribute("oid", oid);
%>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<%	if (!HiltonUtility.isEmpty(forwardedTo)) {%>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<tsa:tr><tsa:td align="center" cssClass="label">
<tsa:label labelName="approvalNotificationResent" defaultString="An approval email notification has been resent to" /><span class=hdr12><%=UserManager.getInstance().getUser(oid, forwardedTo).getDisplayName()%></span> 
<tsa:label labelName="for-requisition" defaultString="for Requisition" /> #<span class=hdr12><%=headerEncoder.encodeForHTML(requisitionHeader.getRequisitionNumber())%></span>.</tsa:td></tsa:tr>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<%	}%>
<tsa:tr><tsa:td align="center"><div id="pxbutton"><a href="javascript: this.close(); void(0);"><tsa:label labelName="req-close" defaultString="Close" /></a></div></tsa:td></tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

// End Hide script -->
</SCRIPT>