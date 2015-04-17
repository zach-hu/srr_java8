<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsagate.properties.DictionaryManager" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.Dates" %>
<%@ page import="com.tsagate.html.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	String	oid = (String) request.getAttribute("organizationId");
	String	language = (String)request.getAttribute("language");
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("language", language);
%>
<div name="approve_link" style="visibility: visible;">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
  <tsa:td align="center" width="680px">
  	<br>
  	<br>
    <table border="0" cellspacing="0" cellpadding="0" width="680px" id="approverButtons" style="visibility: hidden; display: none;">
	    <tsa:tr>
	    	<tsa:td align="center" width="6%">&nbsp;</tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: doSubmitApprove('N'); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: rejectMe(); void(0);"><img src="<%=contextPath%>/images/reject_black.gif" border="0" alt="Reject"></a></tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: rerouteMe(); void(0);"><img src="<%=contextPath%>/images/reroute_black.gif" border="0" alt="Reroute"></a></tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: deferMe(); void(0);"><img src="<%=contextPath%>/images/defer_black.gif" border="0" alt="Defer"></a></tsa:td>
			<tsa:td align="center" width="6%">&nbsp;</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="center" width="6%">&nbsp;</tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: doSubmitApprove('N'); void(0);">
				<tsa:label labelName="clickhere-approve" defaultString="Click here to approve." />.</a></tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: rejectMe(); void(0);">
				<tsa:label labelName="clickhere-reject" defaultString="Click here to reject" />.</a></tsa:td>
			<tsa:td align="center" width="22%">&nbsp;<a href="javascript: rerouteMe(); void(0);">
				<tsa:label labelName="clickhere-reroute" defaultString="Click here to reroute" />.</a></tsa:td>
			<tsa:td align="center" width="22%">&nbsp;<a href="javascript: deferMe(); void(0);">
				<tsa:label labelName="clickhere-defer" defaultString="Click here to defer" />.</a></tsa:td>
			<tsa:td align="center" width="6%">&nbsp;</tsa:td>
		</tsa:tr>
    </table>
    <table border="0" cellspacing="0" cellpadding="0" width="680px" id="adminApproverButtons" style="visibility: hidden; display: none;">
	    <tsa:tr>
			<tsa:td align="center" width="20%"><a href="javascript: doSubmitApprove('N'); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approve", "Approve")%>"></a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: doSubmitApprove('Y'); void(0);"><img src="<%=contextPath%>/images/approve_for_all.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approve", "Approve")%>"></a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: rejectMe(); void(0);"><img src="<%=contextPath%>/images/reject_black.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reject", "Reject")%>"></a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: rerouteMe(); void(0);"><img src="<%=contextPath%>/images/reroute_black.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reroute", "Reroute")%>"></a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: deferMe(); void(0);"><img src="<%=contextPath%>/images/defer_black.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "defer", "Defer")%>"></a></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="center" width="20%"><a href="javascript: doSubmitApprove('N'); void(0);">
			<tsa:label labelName="clickhere-approveon" defaultString="Click here to approve on" /><br>
			<tsa:label labelName="behalf-currentapprover" defaultString="behalf of the current approver" />.</a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: doSubmitApprove('Y'); void(0);">
			<tsa:label labelName="clickhere-approveon" defaultString="Click here to approve on" /><br>
			<tsa:label labelName="behalf-allapprovers" defaultString="behalf of all approvers" />.</a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: rejectMe(); void(0);">
			<tsa:label labelName="clickhere-reject" defaultString="Click here to reject" />.</a></tsa:td>
			<tsa:td align="center" width="20%">&nbsp;<a href="javascript: rerouteMe(); void(0);">
			<tsa:label labelName="clickhere-reroute" defaultString="Click here to reroute" />.</a></tsa:td>
			<tsa:td align="center" width="20%">&nbsp;<a href="javascript: deferMe(); void(0);">
			<tsa:label labelName="clickhere-defer" defaultString="Click here to defer" />.</a></tsa:td>
		</tsa:tr>
    </table>
    <table border="0" cellspacing="0" cellpadding="0" width="680px" id="advisorButtons" style="visibility: hidden; display: none;">
	    <tsa:tr>
	    	<tsa:td align="center" width="6%">&nbsp;</tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: recommendApproval(); void(0);"><img src="<%=contextPath%>/images/for.gif" border="0" alt="For"></a></tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: opposeApproval(); void(0);"><img src="<%=contextPath%>/images/against.gif" border="0" alt="Against"></a></tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: rerouteMe(); void(0);"><img src="<%=contextPath%>/images/reroute_black.gif" border="0" alt="Reroute"></a></tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: deferMe(); void(0);"><img src="<%=contextPath%>/images/defer_black.gif" border="0" alt="Defer"></a></tsa:td>
			<tsa:td align="center" width="6%">&nbsp;</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="center" width="6%">&nbsp;</tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: recommendApproval(); void(0);">
<tsa:label labelName="clickhere-recommend" defaultString="Click here to recommend" />.</a></tsa:td>
			<tsa:td align="center" width="22%"><a href="javascript: opposeApproval(); void(0);">
<tsa:label labelName="clickhere-oppose" defaultString="Click here to oppose" />.</a></tsa:td>
			<tsa:td align="center" width="22%">&nbsp;<a href="javascript: rerouteMe(); void(0);">
<tsa:label labelName="clickhere-reroute" defaultString="Click here to reroute" />.</a></tsa:td>
			<tsa:td align="center" width="22%">&nbsp;<a href="javascript: deferMe(); void(0);">
<tsa:label labelName="clickhere-defer" defaultString="Click here to defer" />.</a></tsa:td>
			<tsa:td align="center" width="6%">&nbsp;</tsa:td>
		</tsa:tr>
    </table>
  </tsa:td>
</tsa:tr>
</table>
</div>