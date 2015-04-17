<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.ApprovalLog" %>

<%
	List routingList = (List) request.getAttribute("routingList");
	String s_po_number = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_poNumber"));
%>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
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
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="approval-notes" defaultString="Approval Notes" /></div>
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
		<span class="formType"><tsa:label labelName="order" defaultString="Order" /> </span><span class="hdr12">#<%=s_po_number%></span>
	</td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr>
	<td width="100%" align="center">
		<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 620px; height: 190px; align:center; overflow-y:visible; overflow-x:auto;">
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
			<tr><td class="browseHdrDk"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
<%			} %>
			<tr height="20px">
				<td width="100%"><b><%=approver.getDisplayName()%>&nbsp;<tsa:label labelName="wrote" defaultString="wrote" />:</b></td>
			</tr>
			<tr height="20px" valign="top">
				<td width="100%"><%=appLog.getApproverNotes()%></td>
			</tr>
<%		}
		}
		else
		{ %>
			<tr height="20px">
				<td width="100%" align="center"><br><br><b><tsa:label labelName="no-approval-notes" defaultString="There are no approval notes for this Order." /></b></td>
			</tr>
<%	} %>
			</table>
		</div>
	</td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr>
	<td width="100%" align="center"><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);"><tsa:label labelName="close" defaultString="Close" /></a></div></td>
</tr>
</table>