<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<tsa:hidden name="UserProfile_organizationId" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Puridiom Xpress Registration</td>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top width=100% id="systemSetupFrame" height=350px>
		<div id="SystemProcessing" style="visibility: visible; position:absolute; left:25px; height:500px;">
			<table border=0 cellspacing=0 cellpadding=0 width=100%>
			<tr>
				<td width=100% align=center valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "processing", "Processing")%>... <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleaseWait", "Please wait")%>.</b><br>
			  <br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
			</tr>
			</table>
		</div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	frm.userId.value = "";


	function thisLoad() {
		var newInputField = "<input type='hidden' name='PackagePricing_packageType' value='G'>";
		setHiddenFields(newInputField);
		doSubmit('user/package_selection.jsp', 'PackagePricingRetrieveBy');
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>