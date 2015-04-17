<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRule" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRules" %>
<%@ page import="java.math.BigDecimal" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	String fromSave =  HiltonUtility.ckNull((String) request.getAttribute("fromSave"));
	String catalogId = HiltonUtility.ckNull((String) request.getAttribute("CatalogItem_catalogId"));
	String itemNumber = HiltonUtility.ckNull((String) request.getAttribute("CatalogItem_itemNumber"));
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>

<tsa:hidden name="Catalog_catalogId" value="<%=catalogId%>"/>
<tsa:hidden name="CatalogItem_catalogId" value="<%=catalogId%>"/>
<tsa:hidden name="CatalogItem_itemNumber" value="<%=itemNumber%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Catalog Item Validation Results</div>
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

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td width="100%" align="center" valign="top">
    <%@ include file="/base/validation_rules.jsp" %>
  </td>
</tr>
</table>

<br>
<br>

<table width=655px cellpadding=0 cellspacing=0 border=0 valign=bottom>
<tr>
	<td align=center>
	
<%	if (rules.getResult() == 1) { %>
		<div id="novalidationrules" style="display:none;">
			<table align=center>
			<tr>
				<td valign=middle><img src="<%=contextPath%>/images/alert.gif" valign=middle border=0></td>
				<td valign=middle class="basic"><b>The Catalog Item information has passed validation.</b></td>
			 </tr>
			</table>
			
		</div>
		
<%	}%>
	

	<div id="pxbutton"><a tabindex=50 href="javascript: returnToCatalogItemInformation(); void(0);">Return</a></div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	
<%	if (rules.getResult() == 1) { %>
	displayArea('novalidationrules');
	<%	if (fromSave.equalsIgnoreCase("Y")) { %>
	setOriginalFilter("CatalogItem_id_catalogId", "=", "<%=catalogId%>");
	browse('catalogitem-admin');
	<%	} %>
<%	} else { %>
	displayArea('validationrules');
<%	} %>

	function returnToCatalogItemInformation() {
		doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById');
	}

// end hiding contents -->
</SCRIPT>