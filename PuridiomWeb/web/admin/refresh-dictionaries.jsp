<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="com.tsagate.foundation.rule.RuleManager" %>
<table border=0 cellspacing=0 cellpadding=0 width=680px height=350px>
<tr>
	<td align=center valign=top width=500px id="systemSetupFrame">
		<div id="SystemProcessing" style="visibility: visible; position:absolute; top:150px; left:25px; height:400px;">
			<table border=0 cellspacing=0 cellpadding=0 width=100%>
			<tr>
				<td width=100% align=center valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Refreshing Labels")%>... <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleaseWait", "Please wait")%>.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
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
	function thisLoad() {
		<%DictionaryManager.getInstance(true);
		RuleManager.getInstance(true);%>
		doSubmit('admin/admin_menu.jsp', 'DoNothing');
		
		//hideArea("SystemProcessing");
	}
	
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>