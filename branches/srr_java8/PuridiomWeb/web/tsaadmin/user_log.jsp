<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="com.tsa.puridiom.UserLogged" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>User Log</td>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
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

<%	int userCount = UserLogged.getInstance(oid).getUserCount();	%>


<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr><td align=center><b>There are currently <%=userCount%> <%=oid%> users logged in.</b></td></tr>
<tr><td align=center><br></td></tr>
<tr>
	<td width=100% align=center valign=top>
		<table border=0 cellspacing=1 cellpadding=0 width=675px class=browseHdrDk>
		<tr>
			<td width=20% height=18px class=browseHdrDk>&nbsp;User Id</td>
			<td width=35% height=18px class=browseHdrDk>Login Id</td>
			<td width=45% height=18px class=browseHdrDk>User Name</td>
		</tr>
		</table>

		<div id="scrollbox" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width:675px; align:left; overflow-y:auto">
		<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
<%
	List userLog = UserLogged.getInstance(oid).getLoggedUsers();

	if (userLog != null) {
		String rowClass = "browseRow";
		long	currentTime = new Date().getTime();
		for (int i=0; i < userLog.size(); i++) {
			String userId = (String) userLog.get(i);
			UserProfile loggedUser = UserManager.getInstance().getUser(oid, userId);
%>
		<tr>
			<td width=20% height=18px class=<%=rowClass%>><%=userId%></td>
			<td width=35% height=18px class=<%=rowClass%>><%=loggedUser.getMailId()%></td>
			<td width=45% height=18px class=<%=rowClass%>><%=loggedUser.getDisplayName()%><tsa:hidden name="loggedUserName" value="<%=loggedUser.getDisplayName()%>"/></td>
		</tr>
<%		if (rowClass.equals("browseRow")) {
				rowClass = "summary";
			} else {
				rowClass = "browseRow";
			}
		}
	}%>
		</table>
		</div>
	</td>
</tr>
</table>


<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td align=center><a href="javascript: doSubmit('/tsaadmin/tsaadmin_menu.jsp'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button alt="Return to Tsa Admin Menu"></a></td></tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
  
	document.title = "User Log";
  
// -->
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
