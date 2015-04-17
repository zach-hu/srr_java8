<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ page import="com.tsa.puridiom.property.PropertiesManager"%>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_req_ic_line = (String) request.getParameter("RequisitionLine_icReqLine");
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_line_number = (String) request.getAttribute("RequisitionLine_lineNumber");
	String	s_item_number = (String) request.getAttribute("RequisitionLine_itemNumber");
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));

	// Alt Text variables
	String source =  (String) request.getAttribute("AltText_source");
	String id = (String) request.getAttribute("AltText_id");
	String itemNumber = (String) request.getAttribute("AltText_itemNumber");
	String icLine = (String) request.getAttribute("AltText_icLine");
	List altTextList = HiltonUtility.ckNull((List) request.getAttribute("altTextList"));
	boolean bAllowEdit = true;
	int rowCount = 0;


	if (!HiltonUtility.ckNull(source).equals("RQL")) {
		bAllowEdit = false;
	}
	if (altTextList!= null){
		rowCount = altTextList.size();
	}

	if (s_req_number == null)
	{
		s_req_number = (String) request.getAttribute("formNumber");
	}
	if (s_line_number == null)
	{
		s_line_number = (String) request.getAttribute("lineNumber");
	}
	if (s_req_ic_header == null)
	{
		s_req_ic_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_req_ic_line == null)
	{
		s_req_ic_line = (String) request.getAttribute("DocComment_icLine");
	}
	if (s_req_status == null)
	{
		s_req_status = (String) request.getAttribute("formStatus");
	}
	if (s_fiscal_year == null)
	{
		s_fiscal_year = (String) request.getAttribute("fiscalYear");
	}
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="RequisitionLine_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="Default_referenceType" value="RQL"/>
<tsa:hidden name="formNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_req_status%>"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteall" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tsa:tr>
		<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
			<tsa:tr>
				<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
			</tsa:tr>
			<tsa:tr>
				<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-line-item" defaultString="Line Item"/>  <%=headerEncoder.encodeForHTML(s_line_number)%> - <tsa:label labelName="req-alttext-descriptions" defaultString="Alternate Language Descriptions"/></div></div>
				</tsa:td>
			</tsa:tr>
		</table>
		</tsa:td>
		<tsa:td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
		<tsa:td valign="bottom" align="right" height="30px">
			<table border=0 cellspacing=0 cellpadding=1 width=100%>
			</table>
			<table cellpadding="0" cellspacing="0" border=0>
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
	 	doSubmit('/requests/req_item.jsp', 'AltTextUpdateByItem;RequisitionLineRetrieve');
	}

	function returnAbort() {
		setInnerHTML("altTextRows", "");
	 	doSubmit('/requests/req_item.jsp', 'RequisitionLineRetrieve');
	}

// End Hide script -->
</SCRIPT>
