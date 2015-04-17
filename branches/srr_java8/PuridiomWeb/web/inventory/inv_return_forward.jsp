<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.po.tasks.PoErrors" %>
<%@ page import="com.tsa.puridiom.poheader.tasks.UserErrors" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.util.*" %>

<%
	String itemNumber = "";
	String itemLocation = "";
	BigDecimal qtyOnHand = new BigDecimal(0);
	InvLocation invLocation = (InvLocation)request.getAttribute("invLocation");
	if (invLocation != null) {
		itemNumber = invLocation.getComp_id().getItemNumber();
		itemLocation = invLocation.getComp_id().getItemLocation();
		qtyOnHand = invLocation.getQtyOnHand();
	}
	qtyOnHand = HiltonUtility.getFormattedQuantity(qtyOnHand, oid);
%>
<br>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center>Item&nbsp;<font class=hdr12><%=itemNumber%></font>&nbsp;
		returned to location&nbsp;<font class=hdr12><%=itemLocation%></font>.&nbsp;
		<tsa:label labelName="inv-quantityOnHand" defaultString="Qty on Hand"/>:&nbsp;<%=qtyOnHand%>
	</td>
</tr>
</table>
<br>
<br>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td>
		<table width="400px" align="center">
		<tr>
			<td align="center"><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

// End Hide script -->
</SCRIPT>