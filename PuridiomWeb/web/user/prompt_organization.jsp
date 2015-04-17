<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String errorMsg = 	(String)request.getAttribute("errorMsg");
	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}
%>

<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="registrationFailurePage" value="/user/prompt_organization.jsp"/>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Prompt Organization</td>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%>>
<tr>
	<td width=100% valign=top>
		<table width=<%=formEntryWidth%> align=center border=0>
		<tr class=mrow>
			<td align="center" colspan="2">
				<table width="100%">
				<tr><td colspan=2 class=error align=center><br><%=errorMsg%></td></tr>
				<tr>
					<td width=10% align=center valign=top><img src="<%=contextPath%>/images/step1off.gif" border="0"></td>
					<td>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(DictionaryManager.getInstance("host", null).getProperty("instance-oid-installed", ""), "promptoid", "Enter your organization ID used by Puridiom.  If you do not know this ID,  it's available on the Procurement website.")%>
					</td>
				</tr>
				</table>
				<br>
				<br>
			</td>
		</tr>
		<tr class=mrow>
			<td align="right" width="45%"><b>Organization ID:</b>&nbsp;</td>
			<td><input type=password name="organizationIdRegister" autocomplete="off"  maxLength=15 size=20 onchange="upperCase(this);"></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>
<br>
<table border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%>>
<tr>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: register(); void(0);">Continue</a></div>
	</td>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: doSubmit('index.jsp', 'DoNothing'); void(0);">Cancel</a></div>
	</td>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function register() {
		doSubmit('user/self_registration.jsp', 'ValidateOrganizationId');
	}

	function setFirstFocus() {
		frm.organizationIdRegister.focus();
	}
//-->
</SCRIPT>

