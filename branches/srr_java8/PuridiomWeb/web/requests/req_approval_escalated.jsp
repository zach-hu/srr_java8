<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% pageContext.setAttribute("oid", oid);
   pageContext.setAttribute("language", language);%>
<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	String backupApprover = (String) request.getAttribute("backupApprover");
	String errorMsg = (String) request.getAttribute("errorMsg");
	String	requisitionNumberText = "";

	backupApprover = HiltonUtility.ckNull(backupApprover);
	if (requisitionHeader != null) {
		requisitionNumberText = " for Requisition # <span class=hdr12>" + headerEncoder.encodeForHTML(requisitionHeader.getRequisitionNumber()) + "</span>";
	}
	pageContext.setAttribute("oid", oid);
%>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<%	if (!HiltonUtility.isEmpty(backupApprover)) {%>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<tsa:tr><tsa:td align="center" cssClass="label">
<tsa:label labelName="approval-escalated" defaultString="Approval escalated to " /><span class=hdr12><%=UserManager.getInstance().getUser(oid, backupApprover).getDisplayName()%></span><%=requisitionNumberText%>.</tsa:td></tsa:tr>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<%	} else if (!HiltonUtility.isEmpty(errorMsg)) {%>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<tsa:tr><tsa:td align="center" cssClass="label"><%=errorMsg%></tsa:td></tsa:tr>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<%	} else {%>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<tsa:tr><tsa:td align="center" cssClass="label">
<tsa:label labelName="backupapprover-notfound" defaultString="Backup approver was not found" />.</tsa:td></tsa:tr>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<%	} %>
<tsa:tr><tsa:td align="center"><div id="pxbutton"><a href="javascript: window.top.hidePopWin();; void(0);"><tsa:label labelName="req-close" defaultString="Close"/></a></div></tsa:td></tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

// End Hide script -->
</SCRIPT>