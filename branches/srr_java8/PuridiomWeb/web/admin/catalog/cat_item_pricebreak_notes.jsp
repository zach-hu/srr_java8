<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	CatalogPriceBrk pricebreak = (CatalogPriceBrk) request.getAttribute("catalogPriceBrk");
	String catalogId = HiltonUtility.ckNull((String) request.getAttribute("CatalogPriceBrk_catalogId"));
	String itemNumber = HiltonUtility.ckNull((String) request.getAttribute("CatalogPriceBrk_itemNumber"));
	String sequence = HiltonUtility.ckNull((String) request.getAttribute("CatalogPriceBrk_sequence"));
	String breakNote = HiltonUtility.ckNull((String) request.getAttribute("CatalogPriceBrk_breakNote"));

	if (pricebreak == null)
	{
		pricebreak = new CatalogPriceBrk();
		CatalogPriceBrkPK pk = new CatalogPriceBrkPK();
		pk.setCatalogId(catalogId);
		pk.setItemNumber(itemNumber);
		pk.setSequence(new BigDecimal(sequence));
		pricebreak.setComp_id(pk);
	}
%>

<br>

<table cellpadding=0 cellspacing=0 border=0 align=center width=100%>
<tr>
	<td align=center valign=center>
		<table cellpadding=0 cellspacing=0 border=0 align=center width=200px>
		<tr>
			<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "price-break-notes", "Price-break Notes")%></b></td>
		</tr>
		<tr>
			<td align=center>
				<textarea name="CatalogPriceBrk_breakNote" wrap="virtual" rows=8 cols=55><%=breakNote%></textarea>
				<tsa:hidden name="CatalogPriceBrk_catalogId" value="<%=pricebreak.getComp_id().getCatalogId()%>"/>
				<tsa:hidden name="CatalogPriceBrk_itemNumber" value="<%=pricebreak.getComp_id().getItemNumber()%>"/>
				<tsa:hidden name="CatalogPriceBrk_sequence" value="<%=pricebreak.getComp_id().getSequence()%>"/>
			</td>
		</tr>
		</table>
		<br>
		<br>
		<table cellpadding=0 cellspacing=0 border=0 align=center width=200px>
		<tr>
			<td align=center><div id="pxbutton"><a href="javascript: saveNotes(); void(0);">Save</a></div></td>
			<td align=center><div id="pxbutton"><a href="javascript: closeThis(); void(0);">Close</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	self.focus();

	frm = document.purchaseform;

	function closeThis() {
		window.top.hidePopWin();
	}

	function saveNotes() {
		var sequence = "<%=sequence%>";

		if (window.parent.maxRows == 1)
		{
			window.parent.frm.CatalogPriceBrk_breakNote.value = frm.CatalogPriceBrk_breakNote.value;
		}
		else
		{
			window.parent.frm.CatalogPriceBrk_breakNote[sequence-1].value = frm.CatalogPriceBrk_breakNote.value;
		}
		doSubmit("/system/close_window.jsp", "DoNothing");
		//doSubmit("/system/close_window.jsp", "CatalogPriceBrkUpdateById");
	}

// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>

<%@ include file="/system/prevent_caching.jsp" %>