<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ page import="com.tsa.puridiom.property.PropertiesManager"%>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_ic_line = (String) request.getParameter("PoLine_icPoLine");
	String	s_po_ic_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	s_line_number = (String) request.getAttribute("PoLine_lineNumber");
	String	s_item_number = (String) request.getAttribute("PoLine_itemNumber");
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));

	// Alt Text variables
	String source =  (String) request.getAttribute("AltText_source");
	String id = (String) request.getAttribute("AltText_id");
	String itemNumber = (String) request.getAttribute("AltText_itemNumber");
	String icLine = (String) request.getAttribute("AltText_icLine");
	List altTextList = HiltonUtility.ckNull((List) request.getAttribute("altTextList"));
	boolean bAllowEdit = true;
	int rowCount = 0;


	if (!HiltonUtility.ckNull(source).equals("POL")) {
		bAllowEdit = false;
	}
	if (altTextList!= null){
		rowCount = altTextList.size();
	}

	if (s_po_number == null)
	{
		s_po_number = (String) request.getAttribute("formNumber");
	}
	if (s_line_number == null)
	{
		s_line_number = (String) request.getAttribute("lineNumber");
	}
	if (s_po_ic_header == null)
	{
		s_po_ic_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_po_ic_line == null)
	{
		s_po_ic_line = (String) request.getAttribute("DocComment_icLine");
	}
	if (s_po_status == null)
	{
		s_po_status = (String) request.getAttribute("formStatus");
	}
	if (s_fiscal_year == null)
	{
		s_fiscal_year = (String) request.getAttribute("fiscalYear");
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_po_ic_header%>"/>
<tsa:hidden name="PoLine_icPoLine" value="<%=s_po_ic_line%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="PoLine_lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="PoLine_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="Default_referenceType" value="POL"/>
<tsa:hidden name="formNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_po_status%>"/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteall" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
			<tr>
				<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
			</tr>
			<tr>
				<td nowrap class=hdr12 valign=middle>
				<div style="margin-left: 10px; margin-right: 10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-line-item", "Line Item")%>  <%=s_line_number%> - <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-alttext-descriptions", "Alternate Language Descriptions")%></div></div>
				</td>
			</tr>
		</table>
		</td>
		<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
		<td valign=bottom align=right height=30px>
			<table border=0 cellspacing=0 cellpadding=1 width=100%>
			</table>
			<table cellpadding="0" cellspacing="0" border=0>
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
<div id="altTextRows">
<%@ include file="/base/alttext_rows.jsp"%>
</div>
<%@ include file="/system/footer.jsp"%>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
</table>


<SCRIPT value=JavaScript>
<!-- Hide script

	function thisLoad()
	{
		f_StartIt();
<%		if (!bAllowEdit) { %>
			checkInputSettings();
<%	} %>
	}

	function saveAltTextList() {
	 	doSubmit('/orders/po_item.jsp', 'AltTextUpdateByItem;PoLineRetrieve');
	}

	function returnAbort() {
		setInnerHTML("altTextRows", "");
	 	doSubmit('/orders/po_item.jsp', 'PoLineRetrieve');
	}

// End Hide script -->
</SCRIPT>
