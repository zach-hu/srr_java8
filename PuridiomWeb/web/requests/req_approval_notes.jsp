<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.ApprovalLog" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<%pageContext.setAttribute("language", language); %>
<%
	List routingList = (List) request.getAttribute("routingList");
	RequisitionHeader reqHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
%>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
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
						<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="approvalNotes" defaultString="Approval Notes"></tsa:label></div>
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
		<span class="formType"><tsa:label labelName="requisition" defaultString="Requisition "></tsa:label></span><span class="hdr12">#<%=headerEncoder.encodeForHTML(reqHeader.getRequisitionNumber())%></span>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tsa:tr>
	<tsa:td width="100%" align="center">
		<div id="browseBorder" class="browseHdrDk" style="border: solid 2px; background: #FFFFFF; padding: 2px; width: 620px; height: 190px; align: center; overflow-y: visible; overflow-x: auto;">
			<table border="0" cellpadding="0" cellspacing="0" width="610px">
<%	if (routingList != null && routingList.size() > 0)
		{
			for (int i = 0; i < routingList.size(); i++)
			{
				ApprovalLog appLog = (ApprovalLog) routingList.get(i);
				String	s_approver = appLog.getComp_id().getUserId();
				UserProfile approver = UserManager.getInstance().getUser(oid, s_approver);
				if (i > 0)
				{
%>
			<tsa:tr><tsa:td cssClass="browseHdrDk"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td></tsa:tr>
<%			} %>
			<tsa:tr height="20px">
				<tsa:td width="100%"><b><%=approver.getDisplayName()%>&nbsp;<tsa:label labelName="req-wrote" defaultString="wrote"></tsa:label>:</b></tsa:td>
			</tsa:tr>
			<tsa:tr height="20px" valign="top">
				<tsa:td width="100%"><%=appLog.getApproverNotes()%></tsa:td>
			</tsa:tr>
<%		}
		}
		else
		{ %>
			<tsa:tr height="20px">
				<tsa:td width="100%" align="center"><br><br><b><tsa:label labelName="thereare-no-approval-notes-requisition" defaultString="There are no approval notes for this Requisition"></tsa:label></b></tsa:td>
			</tsa:tr>
<%	} %>
			</table>
		</div>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tsa:tr>
	<tsa:td width="100%" align="center"><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);"><tsa:label labelName="req-close" defaultString="Close"></tsa:label></a></div></tsa:td>
</tsa:tr>
</table>