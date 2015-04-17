<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReferenceInfo" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>

<%
	int i;
	int i_size;
	RfqLine rfqLine = (RfqLine) request.getAttribute("rfqLine");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	boolean solicitationActive = propertiesManager.getProperty("RFQ OPTIONS", "SOLICITATIONS", "N").equalsIgnoreCase("Y");

	BigDecimal bd_icRfqHeader = rfqLine.getIcRfqHeader();
	BigDecimal bd_icRfqLine = rfqLine.getIcRfqLine();
	BigDecimal bd_line_number = rfqLine.getRfqLine().setScale(0, BigDecimal.ROUND_HALF_UP);
	BigDecimal bd_umFactor = rfqLine.getUmFactor();
	String s_rfqNumber = rfqLine.getRfqNumber();
	String s_rfqAmendment = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_rfqAmendment"));
//	String s_rfqStatus = rfqLine.getStatus();
	String s_rfqStatus = (String)request.getAttribute("RfqHeader_status");
	String s_rfqType = (String) request.getAttribute("RfqHeader_rfqType");
	String s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");
	String s_line_count = (String) request.getAttribute("lineCount");
	String s_from_page = (String) request.getAttribute("frompage");
	String maxLength = "15";
	if (rfqLine.getUdf1Code().length() > 15)
	{
		maxLength = "30";
	}

	String s_return_page = "";
	String s_return_method = "";
	String s_udf6 = rfqLine.getUdf6Code();
	String s_udf7 = rfqLine.getUdf7Code();
	String s_shelfLife	= rfqLine.getShelfLifeRqd();

	String	lookupStatus = (String) request.getAttribute("lookupStatus");

	if (lookupStatus == null) {
		lookupStatus = "";
	}

	if (HiltonUtility.isEmpty(s_rfqStatus))	{	s_rfqStatus = DocumentStatus.RFQ_INPROGRESS;		}
	if (HiltonUtility.isEmpty(s_rfqType))		{	s_rfqType = "RQ";				}
	if (HiltonUtility.isEmpty(s_line_count))	{	s_line_count = "1";			}
	if (HiltonUtility.isEmpty(s_from_page))	{	s_from_page = "shopcart";	}

	if (bd_umFactor == null || bd_umFactor.compareTo(new BigDecimal(0)) <= 0)
	{
		bd_umFactor = new BigDecimal(1);
	}

	if (s_from_page.equalsIgnoreCase("shopcart"))
	{
		s_return_page = "/rfq/rfq_items.jsp";
		s_return_method = "RfqLineRetrieveByHeader;RfqHeaderRetrieveById";
	}
	else
	{
		s_return_page = "/rfq/rfq_review.jsp";
		s_return_method = "RfqRetrieve";
	}

	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}

	String showLinkGetItemInfo = (String)request.getAttribute("showLinkGetItemInfo");
	if(showLinkGetItemInfo == null)
		showLinkGetItemInfo = "N";

	String	s_use_subcommodity = propertiesManager.getProperty("MISC", "USE SUBCOMMODITY", "N");

	Encoder encoder = DefaultEncoder.getInstance();
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="<%=encoder.encodeForHTMLAttribute(s_rfqType)%>"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqBid_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqBid_icRfqLine" value="<%=bd_icRfqLine%>"/>
<tsa:hidden name="ShipTo_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="ShipTo_icLine" value="<%=bd_icRfqLine%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="<%=bd_icRfqLine%>"/>
<tsa:hidden name="RfqLine_icRfqLine" value="<%=bd_icRfqLine%>"/>
<tsa:hidden name="RfqLine_rfqLine" value="<%=bd_line_number%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="previousPage" value="i"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="as_action" value="true"/>
<tsa:hidden name="as_redirect" value="true"/>
<tsa:hidden name="formtype" value="RFQ"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="lineUpdated" value="false"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="currentVendorId" value=""/>
<tsa:hidden name="returnPage" value="/rfq/rfq_item.jsp"/>
<tsa:hidden name="returnMethod" value="RfqLineRetrieveById"/>
<tsa:hidden name="showLinkGetItemInfo" value="<%=showLinkGetItemInfo%>"/>
<tsa:hidden name="duplicateItem" value="FALSE"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Line Item <%=bd_line_number%> of <%=headerEncoder.encodeForHTML(s_line_count)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
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
<%@ include file="/rfq/rfq_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top width=520px>
		<br>
		<table border=0 cellpadding=0 cellspacing=0 height=100%>
		<tr>
			<td>
<%
	String s_itemNumber = rfqLine.getItemNumber();
	String s_asset = rfqLine.getAsset();
	if (HiltonUtility.isEmpty(s_asset)) { s_asset="N"; }
	String s_taxable = rfqLine.getTaxable();
	if (HiltonUtility.isEmpty(s_taxable)) { s_taxable="N"; }
	BigDecimal b_quantity = rfqLine.getQuantity().setScale(Integer.valueOf(s_quantity_decimals).intValue(), BigDecimal.ROUND_HALF_UP);;
	String s_commodity = rfqLine.getCommodity();
	String s_uom = rfqLine.getUmCode();
	String s_modelNumber = rfqLine.getModelNumber();
	String s_itemLocation = rfqLine.getItemLocation();
	String s_catalogId = rfqLine.getCatalogId();
	String s_mfg = rfqLine.getMfgName();
	String s_udf1 = rfqLine.getUdf1Code();
	String s_udf2 = rfqLine.getUdf2Code();
	String s_udf3 = rfqLine.getUdf3Code();
	String s_udf4 = rfqLine.getUdf4Code();
	String s_udf5 = rfqLine.getUdf5Code();

%>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%>><input type=text name="RfqLine_itemNumber" tabindex=1 size=20 maxlength=30 value="<%=s_itemNumber%>" onchange="upperCase(this); <% if (showLinkGetItemInfo.equalsIgnoreCase("N")) { %> getItemInfo(); <% } %> updated(); void(0);"></td>
				<% if (showLinkGetItemInfo.equalsIgnoreCase("Y")) { %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> nowrap align=right><a href="javascript: getItemInfo(); void(0);" title="Click here to Get Item Info">Get Item # Info</a></td>
				<% } %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rfq-description")%>>
					<td nowrap align=right valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-description", "Description", true)%>:&nbsp;</td>
					<td colspan=4><textarea wrap="virtual" name="RfqLine_description" tabindex=2 rows=5 cols=60 onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000);"><%=HiltonUtility.encodeHtml(rfqLine.getDescription())%></textarea></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">
<%
		String s_align="center";
		if (oid.equalsIgnoreCase("vse06p"))
		{
			s_align = "left";
		}
%>
						<table border="0" cellpadding="0" cellspacing="2" width="100%">
<% if (!oid.equals("SRR10P")) { %>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-asset")%> align="<%=s_align%>">
								<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-asset", "Asset")%>:<input type=checkbox name="c_checkbox" tabindex=3 <% if (s_asset.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.RfqLine_asset,0); updated();">&nbsp;
								<tsa:hidden name="RfqLine_asset" value="<%=s_asset%>"/>
							</td>
<% } %>
							<td <%=HtmlWriter.isVisible(oid, "rfq-taxable")%> align="<%=s_align%>">
								<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-taxable", "Taxable")%>:<input type=checkbox name="c_checkbox" tabindex=4 <% if (s_taxable.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.RfqLine_taxable, 1); updated();">
								<tsa:hidden name="RfqLine_taxable" value="<%=s_taxable%>"/>
							</td>
<%	boolean shelfLife	= false;
	if (oid.equalsIgnoreCase("vse06p")) {	%>
							<td <%=HtmlWriter.isVisible(oid, "rfq-LN01")%> align="<%=s_align%>">
								<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN01", "Line UDF1")%>:
								<input type=checkbox name="c_checkbox" tabindex=4 <% if (s_udf1.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(RfqLine_udf1Code, 2); updated();">
								<tsa:hidden name="RfqLine_udf1Code" value="<%=s_udf1%>"/>
							</td>
<%	shelfLife = true;}	%>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantity", "Quantity", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%>><input type=text name="RfqLine_quantity" tabindex=5 size=15 maxlength=15 value="<%=b_quantity%>" style="text-align:right" onchange="setQty(); updated();"></td>

					<td <%=HtmlWriter.isVisible(oid, "rfq-catalog")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-catalog", "Catalog", false)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-catalog")%>><input type=text name="RfqLine_catalogId" size=15 value="<%=rfqLine.getCatalogId()%>" READONLY DISABLED></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> align=right nowrap><a href="javascript: browseLookup('RfqLine_umCode','unitofmeasure'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM")%> for this item or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM", true)%></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%>>
						<input type=text name="RfqLine_umCode" tabindex=7 size=15 maxlength=15 value="<%=s_uom%>" onchange="upperCase(this); updateUMFactor(); updated();">
						<tsa:hidden name="RfqLine_umFactor" value="<%=bd_umFactor%>"/>
					</td>
<%	if ( !oid.equalsIgnoreCase("vse06p") ) { %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN01")%> align=right nowrap>
					<% if ( oid.equalsIgnoreCase("QRI06P") ) { %>
							<a href="javascript: browseCommodity('RfqLine_udf1Code','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');"  title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN01", "Line UDF1")%> for this item or enter the value in box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN01", "Line UDF1", true)%></a>:&nbsp;
					<%} else {%>
							<a href="javascript: browseStd('RfqLine_udf1Code','LN01');"  title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN01", "Line UDF1")%> for this item or enter the value in box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN01", "Line UDF1", true)%></a>:&nbsp;
					<%} %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN01")%>><input type=text name="RfqLine_udf1Code" tabindex=15 size="30" maxlength="30" value="<%=s_udf1%>" onchange="upperCase(this); updated();"></td>
<%	}	%>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-commodity")%> align=right nowrap><a href="javascript: <% if (s_use_subcommodity.equalsIgnoreCase("Y")) {%>browseCommodity('RfqLine_commodity','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');<% } else {%>browseCommodityByType(); <%}%>void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-commodity", "Commodity")%> for this item or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-commodity", "Commodity")%> in box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-commodity", "Commodity", true)%></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-commodity")%>><input type=text name="RfqLine_commodity" id="RfqLine_commodity" tabindex=9 size=15 maxlength=15 value="<%=s_commodity%>" onchange="upperCase(this); updated(); getNewInfo('commodity', this);"></td>
<%	if (oid.equalsIgnoreCase("vse06p")) { %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN02")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN02", "Line UDF2", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN02")%> >
						<select tabindex=23 name="RfqLine_udf2Code" size=1>
<%
			if (HiltonUtility.isEmpty(s_udf2))
			{
				rfqLine.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
			}
			Map inspectionLevels = new TreeMap(DictionaryManager.getInstance("inspection-level", oid).getPropertyMap());
			Iterator inspectionIterator = inspectionLevels.keySet().iterator();
			String	inspectionLevelCode = "";
			String inspectionLevelName = "";
			while (inspectionIterator.hasNext())
			{
				inspectionLevelCode = (String) inspectionIterator.next();
				inspectionLevelName = (String) inspectionLevels.get(inspectionLevelCode);

				if (inspectionLevelCode.equals("DEFAULT"))
				{
					continue;
				}
%>
						<option value="<%=inspectionLevelCode%>" <%if (rfqLine.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></value>
<%		}	%>
						</select>
					</td>
<%	} else { %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN02")%> align=right nowrap><a href="javascript: browseStd('RfqLine_udf2Code','LN02');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN02", "Line UDF2")%> for this item or enter the value in box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN02", "Line UDF2", true)%></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN02")%>><input type=text name="RfqLine_udf2Code" tabindex=17 size=15 maxlength=15 value="<%=s_udf2%>" onchange="upperCase(this); updated();"></td>
<%	}	%>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-commodityName")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-commodityName", "Commodity Name")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-commodityName")%>>
					<%if (oid.equalsIgnoreCase("dtn07p")){%>
					<input type=text name="as_commodityName" size=70 value="<%=CommodityManager.getInstance().getCommodityDescription(oid, rfqLine.getCommodity())%>" onfocus="this.blur();" disabled></td>
					<% }else{%>
					<input type=text name="as_commodityName" size=25 value="<%=CommodityManager.getInstance().getCommodityDescription(oid, rfqLine.getCommodity())%>" onfocus="this.blur();" disabled></td>
					<%}%>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN03")%> align=right nowrap><a href="javascript: browseStd('RfqLine_udf3Code','LN03');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN03", "Line UDF3")%> for this item or Enter the value in box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN03", "Line UDF3", true)%></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN03")%>><input type=text name="RfqLine_udf3Code" tabindex=19 size=15 maxlength=15 value="<%=s_udf3%>" onchange=" upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-modelNumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-modelNumber", "Model Number", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-modelNumber")%>><input type=text name="RfqLine_modelNumber" tabindex=11 size=15 maxlength=20 value="<%=s_modelNumber%>" onchange="upperCase(this); updated();"></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN04")%> align=right nowrap><a href="javascript: browseStd('RfqLine_udf4Code','LN04');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN04", "Line UDF4")%> for this item or enter the value in box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN04", "Line UDF4", true)%></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN04")%>><input type=text name="RfqLine_udf4Code" tabindex=21 size=15 maxlength=15 value="<%=s_udf4%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-manufacturer")%> align=right nowrap>
<%//	if (oid.equalsIgnoreCase("vse06p")) {	%>
						<!--<a href="javascript: browseLookup('RfqLine_mfgName', 'rfqline-manufacturer'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-manufacturer", "Manufacturer", true)%>:</a>&nbsp;-->
<%//	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-manufacturer", "Manufacturer", true)%>:&nbsp;
<%//	}	%>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-manufacturer")%>><input type=text name="RfqLine_mfgName" tabindex=13 size=15 maxlength=25 value="<%=s_mfg%>" onchange="upperCase(this); updated();"></td>
				<%if (!oid.equalsIgnoreCase("vse06p")) { %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN05")%> align=right nowrap><a href="javascript: browseStd('RfqLine_udf5Code','LN05');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN05", "Line UDF5")%> for this item or enter the value in box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN05", "Line UDF5", true)%></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN05")%>><input type=text name="RfqLine_udf5Code" tabindex=23 size=15 maxlength=15 value="<%=s_udf5%>" onchange="upperCase(this); updated();"></td>
				<% } %>
				</tr>
<%		List requisitionLineInfoList = (List) rfqLine.getRequisitionLineInfoList();
			if (requisitionLineInfoList != null)
			{
				for (int ix = 0; ix < requisitionLineInfoList.size(); ix++)
				{
					ReferenceInfo requisitionInfo = (ReferenceInfo) requisitionLineInfoList.get(ix);
%>
				<tr <%=HtmlWriter.isVisible(oid, "rfq-requisitionNumber")%>>
					<td nowrap align=right height=18px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requisitionNumber", "Requisition #")%>:&nbsp;</td>
					<td nowrap height=18px><a href="javascript: reqPreview('<%=requisitionInfo.getIcHeader()%>'); void(0);" title="View Requisition"><%=requisitionInfo.getFormNumber()%></a></td>
				</tr>
<%			}
			}

			List poLineInfoList = (List) rfqLine.getPoLineInfoList();
			if (poLineInfoList != null)
			{
				for (int ix = 0; ix < poLineInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poLineInfoList.get(ix);
%>
				<tr <%=HtmlWriter.isVisible(oid, "rfq-poNumber")%>>
					<td nowrap align=right height=18px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-poNumber", "Order #")%>:&nbsp;</td>
					<td nowrap  height=18px>
						<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if ((poInfo.getReleaseNumber()).compareTo(new BigDecimal(0)) > 0) { %>
						&nbsp;/&nbsp;<%=poInfo.getReleaseNumber()%>
<%				} %>
				</tr>
<%				}
			}
%>
<% if (oid.equals("SRR10P")) { %>
				<tr>
					<td width=175px <%=HtmlWriter.isVisible(oid, "rfq-asset")%> nowrap align=right><tsa:label labelName="rfq-asset" defaultString="Asset" />:&nbsp;</td>
						<tsa:td field="req-asset" >
						<tsa:input type="mintext" name="RfqLine_asset" tabIndex="3" maxLength="15" value="<%=rfqLine.getAsset()%>" labelName="rfq-Asset" onchange="upperCase(this); updated();"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td field="rfq-rfqShelfLifeRqd" noWrap="nowrap" align="right"><tsa:label labelName="rfq-rfqShelfLifeRqd" defaultString="Shelf Life Required" checkRequired="false"/>:&nbsp;</tsa:td>
					<tsa:td field="rfq-rfqShelfLifeRqd">
						<input type=checkbox name="c_checkbox" tabindex="11" <% if (s_shelfLife.equals("Y")) { %> CHECKED <% } %> value="Y" <% if (shelfLife) { %> onclick="setCheckbox(RfqLine_shelfLifeRqd, 2); updated();" <% } else { %>onclick="setCheckbox(RfqLine_shelfLifeRqd, 1); updated();" <%}%>>
						<tsa:hidden name="RfqLine_shelfLifeRqd" value="<%=s_shelfLife%>"/>
					</tsa:td>
				</tr>
<% } %>
				</table>
			</td>
		</tr>
		</table>
	</td>
	<td align=right width=225px valign=top>
		<br>
		<br>
<%
	int linecount = Integer.valueOf(s_line_count).intValue();
	if (linecount > 1)
	{ %>
		View Item:
<%	for (i = 1; i <= linecount; i++)
		{
			if (i == bd_line_number.intValue())
			{ %>
				&nbsp;<%=i%>
<%		}
			else
			{ %>
				&nbsp;<a href="javascript: retrieveLine(<%=i%>);"><%=i%></a>
<%		}
		}
	} %>
		<br>
		<table border=1 cellpadding=0 cellspacing=0>
		<tr><td class=browseHdr>Item Options</td></tr>
		<tr>
			<td class=browseRow>
				<table border=0 cellpadding=0 cellspacing=0 width=225px>
<%	if (editMode) {%>
				<tr height=15px>
					<td valign=middle colspan=3 align=center>
<%		if (s_view.equalsIgnoreCase("WIZARD")) { %>
						<a href="javascript:if (verifyAction('Delete this item?')) { doSubmit('rfq/rfq_items.jsp','RfqLineDelete;RfqLineRetrieveByHeader'); } void(0);">
<%		} else { %>
						<a href="javascript:if (verifyAction('Delete this item?')) { doSubmit('rfq/rfq_summary.jsp','RfqLineDelete;RfqRetrieve'); } void(0);">
<%		} %>
						<img valign='baseline' src='<%=contextPath%>/images/delete.gif' border=0 alt="Delete">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteItem", "Delete Item")%></a>
					</td>
				</tr>
<%	}
		else if (s_rfqStatus.compareTo(DocumentStatus.RFQ_PURCHASING) < 0 && role.getAccessRights("RFQ") >= 2) { %>
				<tr height=15px>
					<td valign=middle colspan=3 align=center>
						<a href="javascript:if (verifyAction('Cancel this item?')) { doSubmit('rfq/rfq_item.jsp','RfqLineCancel;RfqLineRetrieveById'); } void(0);">Cancel Item</a>
					</td>
				</tr>
<%	}
		if (!s_rfqNumber.equals("N/A")) { %>
				<tr height=15px>
					<td nowrap align=center>&nbsp;<A HREF="javascript: viewItemHistory(); void(0);">Item History</A></TD>
				</tr>
<%	} %>
				</table>
<%	if (editMode) { %>
				<table border=0 cellpadding=0 cellspacing=0 width=225px>
				<tr height=15px>
					<td nowrap align=center>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td align=right><a href="javascript: addItem(); void(0);"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="Add Non-Standard Item"></a></td>
							<td><a href="javascript: addItem(); void(0);"><font style="text-decoration: none;">&nbsp;</font>Add Non-Standard Item</a></td>
						</tr>
						<tr>
							<td nowrap colspan="2" align="center">&nbsp;<a href="javascript: addItem('duplicate'); void(0);" title="Click here to add a duplicate of this item"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-duplicateItem", "Duplicate Item", false)%></a></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				<table width=100%>
				<tr height=15px>
					<td nowrap align=center>&nbsp;<a href="javascript: toggleDetailsDisplay('addItem', 'addItemOptions'); void(0);">Item Search Options</a></td>
				</tr>
				</table>

				<div id="addItem" style="display:none;">
				<table border=0 width=225px cellpadding=0>
				<tr>
					<td align=right>
						<tsa:hidden name="createAction" value="SAVE"/>
						<table border=0 cellspacing=0 cellpadding=1>
						<tr <%=HtmlWriter.isVisible(oid, "req-itemNumber")%>>
							<td valign=middle align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #")%>:</b></td>
							<td valign=middle><input type=text name=as_itemNumber value="" size=15 tabindex=20></td>
						</tr>
						<tr>
							<td valign=middle align=right nowrap><b>Keyword(s):</b></td>
							<td valign=middle><input type=text name="as_keywords" value="" size=17 tabindex=21></td>
							<td valign=middle><div id="pxmediumbutton"><a href="javascript: itemSearch(); void(0);">Search</a></div></td>
						</tr>
						</table>
<%	if (propertiesManager.isModuleActive("Extended Inventory") || propertiesManager.isModuleActive("Standard Inventory")) {%>
						<div id="itemtype" style="visibility:visible; display:block;">
						<table border=0 cellspacing=0 cellpadding=2>
						<tr>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=23 value="CAT" CHECKED></td>
							<td valign=middle nowrap>Catalog</td>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=24 value="INV"></td>
							<td valign=middle nowrap>Inventory</td>
						</tr>
						</table>
						</div>
<%	} else {%>
						<tsa:hidden name="as_item_type" value="CAT"/>
<%	}%>
					</td>
				</tr>
				<tr>
					<td align=center>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
						<td valign="middle"><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" alt="Advanced Item Search" border=0><font style="text-decoration:none;">&nbsp;</font></a></td>
							<td valign=middle><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);">Advanced Item Search</a></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				</div>

<%	} %>

			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%
if (oid.equalsIgnoreCase("vse06p"))
{
%>
<br>
<table <%=HtmlWriter.isVisible(oid, "rfq-bav-details")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<td>
	&nbsp;&nbsp;<a href="javascript: toggleDetailsDisplay('bavDetailInfo', 'bavDetailOptions'); void(0);" tabIndex="-1">BAV (Only) Details</a>
</td>
</tr>
</table>

<div id="bavDetailInfo" name="bavDetailInfo" style="visibility:hidden; display:none">
<br>
<table <%=HtmlWriter.isVisible(oid, "rfq-additional-info")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<!--
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px>
		<tr>
		<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=68% height=18px class=browseHdr>&nbsp;<b>BAV Details (Only)</b></td>
				</tr>
				</table>
		</td>
		</tr>
		</table>
	</td>
</tr>
-->
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px>
		<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="2" width="50%">
				<tr <%=HtmlWriter.isVisible(oid, "rfq-LNMEMO")%>>
					<td nowrap align=left valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LNMEMO", "Notes", true)%>:</td>
				</tr>

				<tr <%=HtmlWriter.isVisible(oid, "rfq-LNMEMO")%>>
					<td colspan=4><textarea wrap="virtual" name="RfqLine_memoLine" tabindex="48" rows=4 cols=60 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);" onchange="upperCase(this); textCounter(this,255); updated();"><%=HiltonUtility.encodeHtml(rfqLine.getMemoLine())%></textarea></td>
				</tr>
			</table>
		</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="2" width="50%">
			<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN06")%> align=right nowrap>
					<% if (DictionaryManager.isLink(oid, "rfq-LN06")) { %>
						<a href="javascript: browseStd('RfqLine_udf6Code', 'LN06'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN06", "Line UDF 6")%> for this item or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN06", "Line UDF 6", true)%></a>:
					<% } else { %>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN06", "Line UDF 6", true)%>:
					<% } %>
					</td>

					<td <%=HtmlWriter.isVisible(oid, "rfq-LN06")%>>
						<select tabindex="49" name="RfqLine_udf6Code" size=1>
							<option <% if (s_udf6.equals("N")) {%> SELECTED <%}%> value=""></option>
							<option <% if (s_udf6.equals("O")) { %> selected <% } %> value="O">OUTFITTING</option>
							<option <% if (s_udf6.equals("I")) { %> selected <% } %> value="I">INDUSTRIAL</option>
						</select>
					</td>
			</tr>
			<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN07")%> align=right nowrap>
					<% if (DictionaryManager.isLink(oid, "rfq-LN07")) { %>
						<a href="javascript: browseStd('RfqLine_udf7Code', 'LN07'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN07", "Line UDF 7")%> for this item or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN07", "Line UDF 7", true)%></a>:
					<% } else { %>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-LN07", "Line UDF 7", true)%>:
					<% } %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-LN07")%>><input type=text name="RfqLine_udf7Code" tabindex="50" size=15 maxlength=15 value="<%=rfqLine.getUdf7Code()%>" onchange="upperCase(this); updated();"></td>		</tr>
			</tr>
			</table>
		</td>
		</table>
	</td>
</tr>
</table>
</div>
<% } %>

<br>
<br>
<%List shipToList = (List) rfqLine.getShipToList();%>
<%List bidList = (List) rfqLine.getRfqBidList();%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center valign=top>
		<table <%=HtmlWriter.isVisible(oid, "rfq-itemShippingSchedule")%> border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=4% class=browseHdr height=18px nowrap><a href="javascript: if (validateItem()) { doSubmit('/rfq/rfq_shipping_schedule.jsp', 'RfqLineUpdateById;ShipToRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=93% class=browseHdr height=18px nowrap>&nbsp;Item Shipping Schedule</td>
					<%	if (shipToList != null && shipToList.size() > 0) { %>
					<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('shippingDetails', 'Details'); void(0);"><img id='shippingDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
					<%	} %>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
					<td class=browseRow>
<%
	if (shipToList != null)
	{
		for (i = 0;i < shipToList.size(); i++)
		{
			ShipTo shipTo = (ShipTo) shipToList.get(i);
			String s_shp_attn = shipTo.getAttention();
			BigDecimal b_shp_qty = shipTo.getQuantity();
			if (b_shp_qty == null)	{	b_shp_qty = new BigDecimal(0);	}
			if (s_shp_attn == null)	{	s_shp_attn = "";							}

			ShipToPK shipToPK = shipTo.getComp_id();
			String s_shp_code = shipToPK.getShipToCode();

			String	s_ship_address_line_1 = "";
			String	s_ship_address_line_2 = "";
			String	s_ship_address_line_3 = "";
			String	s_ship_address_line_4 = "";
			String	s_ship_city = "";
			String	s_ship_state = "";
			String	s_ship_postal_code = "";
			String	s_ship_country = "";

			Address shipToAddress = (Address) shipTo.getShipToAddress();
			if (shipToAddress != null)
			{
				s_ship_address_line_1 = shipToAddress.getAddressLine1();
				s_ship_address_line_2 = shipToAddress.getAddressLine2();
				s_ship_address_line_3 = shipToAddress.getAddressLine3();
				s_ship_address_line_4 = shipToAddress.getAddressLine4();
				s_ship_city = shipToAddress.getCity();
				s_ship_state = shipToAddress.getState();
				s_ship_postal_code = shipToAddress.getPostalCode();
				s_ship_country = shipToAddress.getCountry();
			}
%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td class=browseRow height=12px nowrap><b><%=s_shp_code%></b></td>
							<td class=browseRow width=70px nowrap><b>QTY:</b>&nbsp;<%=HiltonUtility.getFormattedQuantity(shipTo.getQuantity(), oid)%></td>
							<td class=browseRow width=135px align=right nowrap><b>Required By:</b> <%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), oid, userDateFormat)%></td>
						</tr>
						</table>

						<div id="shippingDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_ship_address_line_1))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_address_line_1%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_2))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_address_line_2%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_3))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_address_line_3%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_4))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_address_line_4%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_city + s_ship_state + s_ship_postal_code))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_country))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_country%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_shp_attn))
		{ %>
						<tr><td class=browseRow height=12px nowrap>Attn: <%=s_shp_attn%></td></tr>
<%	}%>
						</table>
						</div>
<%	}
	}
	if (shipToList == null || shipToList.size() == 0) {%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr><td><b>There is no shipping schedule for this item.</b></td></tr>
						</table>
<%}%>
					</td>
				</tr>
				</table>
			</td>
	<td width=50% align=center valign=top>
		<table <%=HtmlWriter.isVisible(oid, "rfq-itemBillingSchedule")%> border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
		<tr>
			<td width=100%>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=4% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('rfq/rfq_line_bids.jsp','RfqLineUpdateById;RfqLineRetrieve'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=31% height=18px class=browseHdr>&nbsp;<b>Vendor Id</b></td>
					<td width=30% height=18px class=browseHdr align=center>&nbsp;<b>Item Bid</b></td>
					<td width=29% height=18px class=browseHdr align=center>&nbsp;<b>Extended Cost</b></td>
					<%	if (bidList != null && bidList.size() > 1)
					{%>
					<td width=3% height=18px class=browseHdr align=right><a href="javascript: toggleDetailsDisplay('bidDetails', 'Bids'); void(0);"><img id='bidDetailsImg' valign='baseline' class='processOnImg' src='<%=contextPath%>/images/arrows_down.gif' border=0 alt="View Details"></a>&nbsp;</td>
					<%		} %>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=3>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
		int i_bidSize = 0;
		if (bidList != null)
		{
			i_bidSize = bidList.size();
		}
		if (i_bidSize <= 0)
		{ %>
				<tr><td><b>There are currently no bids for this item.</b><br></td></tr>
				</table>
<%	}
		else
		{
			for (i = 0; i < i_bidSize; i++)
			{
				RfqBid rfqBid = (RfqBid) bidList.get(i);
				RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
				String s_bidVendorId = rfqBidPK.getVendorId();
				String s_bidUmCode = rfqBid.getUmCode();
				if (s_bidUmCode==null){s_bidUmCode = "EA";}
				BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedPrice(rfqBid.getUnitPrice(), oid);
				String s_bidUnitPrice = bd_bidUnitPrice.toString();
				String s_bidCode = rfqBid.getBidCode();
				if (s_bidCode.equalsIgnoreCase("NC")) {
					s_bidUnitPrice = "No Charge";
				}
				else if (s_bidCode.equalsIgnoreCase("NSP")) {
					s_bidUnitPrice = "Not Separately Priced";
				}
				else if (s_bidCode.equalsIgnoreCase("SN")) {
					s_bidUnitPrice = "See Notes";
				}
				else if (s_bidCode.equalsIgnoreCase("NB")) {
					s_bidUnitPrice = "No Bid";
				}
				else if (s_bidCode.equalsIgnoreCase("NE")) {
					s_bidUnitPrice = "None Entered";
				}

				BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
				bd_bidExtendedPrice = (bd_quantity.multiply(bd_bidUnitPrice)).multiply(bd_umFactor);
				bd_bidExtendedPrice = HiltonUtility.getFormattedDollar(bd_bidExtendedPrice, oid);

				if (i==1)
				{
%>
				</table>
				<div id="bidDetails" style="display:none;">
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<% 			} %>
				<tr>
					<td width=4% class=browseRow>&nbsp;</td>
					<td width=31% class=browseRow>&nbsp;&nbsp;<a href="javascript: viewSupplier('<%=rfqBidPK.getVendorId()%>'); void(0);"><%=rfqBidPK.getVendorId()%></a></td>
					<td width=30% class=browseRow align=center valign=top>&nbsp;<%=s_bidUnitPrice%></td>
					<td width=29% class=browseRow align=center valign=top>&nbsp;<%=bd_bidExtendedPrice%></td>
					<td width=3% class=browseRow>&nbsp;</td>
				</tr>
<%		} %>
				</table>
				</div>
<%	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%	List commentList = (List) rfqLine.getDocCommentList();%>
<table <%=HtmlWriter.isVisible(oid, "rfq-itemComments")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100%>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/rfq/rfq_notes_ln.jsp', 'RfqLineUpdateById;DocCommentRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=45% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
<%		if (solicitationActive) {%>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Post</b></td>
<%		} else {%>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
<%		}%>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Bold</b></td>
					<td width=20% height=18px class=browseHdr align=center>&nbsp;<b>Placement</b></td>
					<%	if (commentList != null && commentList.size() > 0)
					{%>
					<td width=3% height=18px class=browseHdr align=center><a href="javascript: toggleDetailsDisplay('commentDetails', 'Details'); void(0);"><img id='commentDetailsImg' valign='baseline' class='processOnImg' src='<%=contextPath%>/images/arrows_down.gif' border=0 alt="View Comments"></a></td>
					<%	} %>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<% 	//Comments
	String s_placeString = "";
	if(commentList!=null)
	{
		for(i=0;i<commentList.size();i++)
		{
		DocComment docComment = (DocComment) commentList.get(i);
		DocCommentPK docCommentPK = docComment.getComp_id();
		DocText docText = docComment.getDocText();
		BigDecimal b_icCmtHeader = docCommentPK.getIcHeader();
		BigDecimal b_icCmtLine = docCommentPK.getIcLine();
		String s_cmtPost = docComment.getCommentWebpost();
		String s_cmtPrint = docComment.getCommentPrint();
		String s_cmtBold = docComment.getCommentBold();
		String s_cmtPlace = docComment.getCommentPlace();
		String s_cmtText = docText.getStdText();

		if (s_cmtPost==null){s_cmtPost = "N";}
		if (s_cmtPrint==null){s_cmtPrint = "N";}
		if (s_cmtBold==null){s_cmtBold = "N";}
		if (s_cmtPlace==null){s_cmtPlace = "A";}
		if (s_cmtPlace.equals("A")){
			s_placeString = "After";
		}
		else if (s_cmtPlace.equals("B")){
			s_placeString = "Before";
		}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=2% class=browseRow>&nbsp;</td>
					<td width=45% class=browseRow>&nbsp;<%=docComment.getCommentTitle()%></td>
<%	if (solicitationActive) {%>
					<td width=10% class=browseRow align=center valign=top>&nbsp;<%=s_cmtPost%></td>
<%	} else {%>
					<td width=10% class=browseRow>&nbsp;</td>
<%	} %>
					<td width=10% class=browseRow align=center valign=top>&nbsp;<%=s_cmtPrint%></td>
					<td width=10% class=browseRow align=center valign=top>&nbsp;<%=s_cmtBold%></td>
					<td width=20% class=browseRow align=center valign=top>&nbsp;<%=s_placeString%></td>
					<td width=3% class=browseRow align=center valign=top>&nbsp;</td>
				</tr>
				</table>
				<div id="commentDetails" name="commentDetails" style="display:none;">
				<table>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%>
<%				if (s_cmtBold.equals("Y")) 	{ %>	<b><% } %><%=s_cmtText%><% if (s_cmtBold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
				</div>
<%	}
	}
	if (commentList == null || commentList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b>There are no comments for this item.</b></td></tr>
				</table>
<%}%>
				</table>
				</div>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>

	</td>
</tr>
</table>

<br>



<!-- Desde aqui es el add para el Attachment -->
<%
List attachmentList = (List) request.getAttribute("docAttachmentList");
%>

<table <%=HtmlWriter.isVisible(oid, "po-itemAttachments")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
<!--					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/orders/po_item_attachment_new.jsp', 'PoLineUpdate'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>-->
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/rfq/rfq_line_attachments.jsp', 'RfqLineUpdateById;DocAttachmentRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=64% height=18px class=browseHdr>&nbsp;<b>Attachment</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	i_size = 0;
	if (attachmentList != null)
	{
		i_size = attachmentList.size();
		for (i = 0;i < attachmentList.size(); i++)
		{
			DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
			String	filename = docAttachment.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=5% align=right id="doc_num_<%=i%>"> &nbsp;<%=i+1%>.&nbsp;	</td>
						<td width=65%>

						<input type="hidden" name="docTitle" value = "<%=docAttachment.getDocFilename()%>" maxLength=60 size=60>
						<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachment.getDocTitle()%></a>
						</td>
						<td width=5% valign=middle align=center>
							<input type=hidden name="docFilename" value = "<%=filename%>">
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
				</tr>
				</table>
				</div>
<% 	}
	}
	if (attachmentList == null || attachmentList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b>There are no Attachments for this item.</b></td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<!-- AUn no tiene labels  -->
<!-- Hasta aqui es el add para el Attachment -->

<br>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (s_view.equalsIgnoreCase("CLASSIC")) {
			if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', '<%=s_return_method%>'); void(0);">Return</a></div></td>

	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', 'RfqLineUpdateById;RfqRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', 'RfqRetrieve'); void(0);">Return</a></div></td>
<%		} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', 'RfqRetrieve'); void(0);">Return</a></div></td>
<%		}
		}
		else if (s_view.equalsIgnoreCase("WIZARD")) {
			if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', 'RfqLineUpdateById;<%=s_return_method%>'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', '<%=s_return_method%>'); void(0);">Return</a></div></td>
<%		} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', 'RfqRetrieve'); void(0);">Return</a></div></td>
<%		}
		}%>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var info_window = null;
	var populated = false;
	var uomArray = new Array();
	var itemLocation = "<%=s_itemLocation%>";
	var lookupStatus = "<%= headerEncoder.encodeForJavaScript(lookupStatus) %>";

	function thisLoad()
	{
		f_StartIt();
<%	if (!editMode) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setFieldFocus() {
		if (lookupStatus == "NOTFOUND" && frm.RfqLine_description) {
			frm.RfqLine_description.focus();
		} else if (lookupStatus == "FOUND" && frm.RfqLine_quantity) {
			frm.RfqLine_quantity.focus();
		} else {
			setFirstFocus();
		}
	}

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


			if (myArea.style.display == "none") {
				myArea.style.display = "block";
				if (type == "bavDetailOptions") {
					myArea.style.visibility = "visible" ;
				}
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
				if (type == "bavDetailOptions") {
					myArea.style.visibility = "hidden" ;
				}
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (type != "addItemOptions" && type != "bavDetailOptions")
		{
			/*	don't want to do this for the add item options	*/
			if (hideArea) {
				myImg.src = "<%=contextPath%>/images/arrows_down.gif";
				myImg.alt = "View" + type;

			}
			else {
				myImg.src = "<%=contextPath%>/images/arrows_up.gif";
				myImg.alt = "Hide " + type;

			}
		}
	}


	function addAttachment() {
		doSubmit('/rfq/rfq_item_attachment_new.jsp', 'DoNothing');
	}



	function addItem(createtype)
	{

		if (createtype == "duplicate")
		{
			frm.duplicateItem.value = "TRUE";
		}
		else
		{
			frm.duplicateItem.value = "FALSE";
		}

		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;

		doSubmit('/rfq/rfq_item.jsp', 'RfqLineUpdateById;RfqLineCreate');
	}

	function populateUOM(uoma)
	{
		var e = 0;

		for (var i = 0; i < uoma.length; i++) {
			uomArray[e] = new Array(uoma[i][0], uoma[i][1]);
			e++;
		}

		populated = true;
	}

	function updateUMFactor()
	{
		var open = true;
		var browser = browserTest();
		var factor = "";
		var code = "";

		frm.RfqLine_umCode.value = trim(frm.RfqLine_umCode);

		if (info_window != null) {
			if (browser == "Netscape") {
				if (info_window.closed == false) {
					info_window.setUomCode("RfqLine_");
					open = false;
				}
			}
			else {
				info_window.setUomCode("RfqLine_");
				open = false;
			}
		}

		if (open == true)
		{
			if (uomArray.length > 0 || populated)
			{
				code = frm.RfqLine_umCode.value;
				for (var i = 0; i < uomArray.length; i++)
				{
					if (code == (uomArray[i][0]).toString())
					{
						factor = uomArray[i][1];
						break;
					}
				}

				setUmFactor(factor)
			}
			else
			{
				popupParameters = "as_prefix=RfqLine_";

				setLookupParameters('/base/get_uom_info.jsp', 'UnitOfMeasureRetrieveAll');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			}
		}
	}

	function setUmFactor(factor) {
		frm.RfqLine_umFactor.value = factor;
//		addUp();
	}

	function retrieveLine(linenumber)
	{
		frm.lineToRetrieve.value = linenumber;
		doSubmit('/rfq/rfq_item.jsp', 'RfqLineUpdateById;RfqLineRetrieveByLineNumber');
	}

	function setQty()
	{
		var qty_dec = <%=s_quantity_decimals%>;
		var qty = nformat(eval(nfilter(frm.RfqLine_quantity)),qty_dec);
		frm.RfqLine_quantity.value = qty;
	}

	function viewItemHistory()
	{
		popupParameters = "HistoryLog_icLineHistory=<%=rfqLine.getIcLineHistory()%>;formtype=RFQ;RfqLine_icRfqLine=<%=rfqLine.getIcRfqLine()%>;rfqNumber=<%=rfqLine.getRfqNumber()%>";
		doSubmitToPopup('/rfq/rfq_history.jsp', 'HistoryLogRetrieveByHistoryLine', 'width=675px', 'height=350px');
	}


	function itemSearch() {
		itemSearchWithUpdate('RfqLineUpdateById');
	}

	function getItemInfo() {
		// No need to call the server if the item number is empty
		if (!isEmpty(frm.RfqLine_itemNumber.value)) {
			doSubmit('/rfq/rfq_item.jsp', 'RfqLineItemLookup');
		}
	}

	function validateItem() {
		if (isEmpty(frm.RfqLine_itemNumber.value) && isEmpty(frm.RfqLine_description.value)) {
			alert("You must enter either an item number or description for this item.");
			return false;
		}
		return true;
	}

	function validateForm() {
		var deleteItem = false;
		var handlers = frm.handler.value;

		if (isEmpty(frm.RfqLine_itemNumber.value) && isEmpty(frm.RfqLine_description.value) && handlers.indexOf("RfqLineDelete") < 0) {
			// no need to do this check if we are already deleting the line item or if changes are going to be discarded and an item number or description was previously entered
			if (frm.lineUpdated.value == "true") {
				if (handlers == "RfqLineRetrieveByHeaderHandler;") {
<%				if ( !HiltonUtility.isEmpty(rfqLine.getItemNumber()) || !HiltonUtility.isEmpty(rfqLine.getDescription()) ) {	%>
						// no need to delete item if changes are going to be discarded and an item number or description was previously entered
						return true;
<%				} else {	%>
						deleteItem = true;
<%				}	%>
				}
				if (!deleteItem) {
					if (confirm("This is not a valid item because it has no item number and no description!  Would you like to discard changes?")) {
						deleteItem = true;
					} else {
						if (handlers.indexOf("RfqLineCreate") >= 0) {
							frm.lineCount.value = frm.lineCount.value - 1;
						}
						return false;
					}
				}
			} else {
				deleteItem = true;
			}

			if (deleteItem) {
				var ind = handlers.indexOf("RfqLineUpdateByIdHandler;");
				if (ind >= 0) {
					handlers = handlers.substring(0, ind) + handlers.substring(ind + 25, handlers.length);
				} else {
					ind = handlers.indexOf("RfqLineUpdateByIdHandler");
					if (ind >= 0) {
						handlers = handlers.substring(0, ind) + handlers.substring(ind + 24, handlers.length);
					} else {
						ind = handlers.indexOf("RfqLineUpdateById;");
						if (ind >= 0) {
							handlers = handlers.substring(0, ind) + handlers.substring(ind + 18, handlers.length);
						} else {
							ind = handlers.indexOf("RfqLineUpdateById");
							if (ind >= 0) {
								handlers = handlers.substring(0, ind) + handlers.substring(ind + 17, handlers.length);
							}
						}
					}
				}
				frm.lineCount.value = frm.lineCount.value - 1;

				if (frm.lineToRetrieve.value > frm.RfqLine_rfqLine.value) {
					frm.lineToRetrieve.value = frm.lineToRetrieve.value - 1;
				}

				frm.handler.value = "RfqLineDeleteHandler;" + handlers;
				return true;
			}
			else {
				if (frm.handlers.indexOf("RfqLineCreate") >= 0) {
					frm.lineCount.value = frm.lineCount.value - 1;
				}
				return false;
			}
		} else {
			return true;
		}
	}

	function reqPreview(icReqHeader) {
		popupParameters = "RequisitionHeader_icReqHeader=" + icReqHeader;
		doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

    function orderPreview(icPoHeader) {
		popupParameters = "PoHeader_icPoHeader=" + icPoHeader;
		doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function viewSupplier(vendorId){
		frm.currentVendorId.value = vendorId;

		doSubmit('rfq/rfq_supplier_summary.jsp', 'RfqRetrieve');
	}

	function browseCommodityByType()
	{
		<% if (oid.equalsIgnoreCase("msg07p")) { %>
		popupParameters = "browseName=commodity";
		popupParameters = popupParameters + ";formField=RfqLine_commodity;allowBrowse=" + frm.allowBrowse.value;
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=BO;logicalOperator=OR;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=SP;logicalOperator=AND;originalFilter=Y;sort=N";
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		<% } else { %>
		browseCommodity('RfqLine_commodity', 'commodity', '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
		<% } %>
	}

// End Hide script -->
</SCRIPT>