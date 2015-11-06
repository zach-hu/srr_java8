<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsagate.properties.DictionaryManager" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/checkPrinting.js"></script>
<%
			Browse browse = (Browse) request.getAttribute("browse");
			BrowseObject browseObject = browse.getBrowseObject();
			BrowseColumn[] labels = browseObject.getLabels();

			String s_multiple_browse = (String)request.getAttribute("multipleBrowse");
			if(s_multiple_browse == null) s_multiple_browse = "N";
			
			String modeRetrieveAllRegisters = null;
			if(request.getAttribute("modeRetrieveAllRegisters") != null){
				modeRetrieveAllRegisters = (String)request.getAttribute("modeRetrieveAllRegisters");
			}

			List list = null;

			if(modeRetrieveAllRegisters != null && modeRetrieveAllRegisters.endsWith("Y")){
				list = browse.getPageResultsFromAllRegisters();
			}else{
				list = browse.getPageResults();
			}

			int	ipageCount = browse.getPageCount();
			int	ipageSize = browse.getPageSize();
			int	irows = browse.getRowCount();

			StringBuffer sbHiddenFields = new StringBuffer();
			StringBuffer sbOriginalFilterFields = new StringBuffer();
			StringBuffer sbFilterFields = new StringBuffer();
			StringBuffer sbOriginalFilterText = new StringBuffer();
			StringBuffer sbFilterText = new StringBuffer();
			StringBuffer sbSortOptions = new StringBuffer();
			StringBuffer sbFilterOptions = new StringBuffer();
			String	sortedColumn = "";
			String	sortedOrder = "";
			String	findString = (String) request.getAttribute("finder");
			List filters = browseObject.getBrowseFilters();
			Map labelsMap = browseObject.getColumnLabels();
			BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
			boolean	lbFirst = true;
			boolean allowEdit = true;
/*
			for (int i=0; i < labels.length; i++) {
				BrowseColumn column = labels[i];
				if (!HiltonUtility.isEmpty(column.getLabel(oid, language))) {
//					labelsMap.put(column.getColumnName(), column.getLabel(oid, language));
					if (!column.getType().equals("Input") && !column.getClassName().equals("Input") && !column.getType().equals("Checkbox"))  {
						if (column.getColumnName().equals(sortedColumn)) {
							sbSortOptions.append("<option value=\"" + column.getColumnName() +  "\" selected>" + column.getLabel(oid, language) + "</option>");
						} else {
							sbSortOptions.append("<option value=\"" + column.getColumnName() + "\">" + column.getLabel(oid, language) + "</option>");
						}
						if (lbFirst) {
							lbFirst = false ;
							sbFilterOptions.append("<option value=\"" + column.getColumnName() +  "\" selected>" + column.getLabel(oid, language) + "</option>");
						} else {
							sbFilterOptions.append("<option value=\"" + column.getColumnName() + "\">" + column.getLabel(oid, language) + "</option>");
						}
					}
				}
			}
*/

		    // load values from BrowseObject.browseColumns
			for (int ix = 0; ix < browseColumns.length; ix++) {
				BrowseColumn column = browseColumns[ix];

		        if (!column.getAllowFilter().equals("Y") && (column.isHidden() || HiltonUtility.isEmpty(column.getLabel()))) {
		            continue ;
				} else if (!column.getAllowFilter().equals("Y") && !column.isSqlPart()) {
					continue;
				}
				if (column.getType().equalsIgnoreCase("checkbox"))
				{
					continue;
				}
//		        if (lbFirst) {
		        if (column.isFilterDefault()) {
					lbFirst = false ;
					sbFilterOptions.append("<option value=\"" + column.getColumnName() +  "\" selected>" + column.getLabel(oid, language) + "</option>");
				} else {
					sbFilterOptions.append("<option value=\"" + column.getColumnName() + "\">" + column.getLabel(oid, language) + "</option>");
				}
			}

			if (filters != null) {
				for (int ix=0; ix < filters.size(); ix++) {
					BrowseFilter filter = (BrowseFilter) filters.get(ix);
					String	colname = filter.getColumnName().replace('.', '_');
					String	sort = filter.getSort();
					StringBuffer sb = new StringBuffer("");
					String	logicalOperator = "AND";

					if ((ix + 1) < filters.size()) {
						BrowseFilter nextFilter = (BrowseFilter) filters.get(ix + 1);
						logicalOperator = nextFilter.getLogicalOperator();
					}

					sb.append("<input type=hidden name=\"colname\" value=\"" + headerEncoder.encodeForHTMLAttribute(colname) + "\">");
					sb.append("<input type=hidden name=\"filter_txt\" value=\"" + headerEncoder.encodeForHTMLAttribute(filter.getValue()) + "\">");
					sb.append("<input type=hidden name=\"operator\" value=\"" + headerEncoder.encodeForHTMLAttribute(filter.getOperator()) + "\">");
					sb.append("<input type=hidden name=\"logicalOperator\" value=\"" + headerEncoder.encodeForHTMLAttribute(logicalOperator) + "\">");
					sb.append("<input type=hidden name=\"sort\" value=\"" + headerEncoder.encodeForHTMLAttribute(filter.getSort()) + "\">");

					if (sort != null && !sort.equalsIgnoreCase("N") && sortedColumn.length() == 0) {
						sortedColumn = colname;
						sortedOrder = sort;
					}
					if (filter.isOriginalFilter()) {
						sb.append("<input type=hidden name=\"originalFilter\" value=\"Y\">");

						sbOriginalFilterFields.append(sb);

						if (filter.getValue() != null && filter.getValue().length() > 0) {
							if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
								if (sbOriginalFilterText.length() > 0) {
									sbOriginalFilterText.append(filter.getLogicalOperator() + " ");
								}
								sbOriginalFilterText.append(DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, (String) labelsMap.get(colname), "") + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
							}
						}
					} else {
						sb.append("<input type=hidden name=\"originalFilter\" value=\"N\">");
						sbFilterFields.append(sb);

						if (filter.getValue() != null && filter.getValue().length() > 0) {
							if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
								if (sbFilterText.length() > 0) {
									sbFilterText.append(filter.getLogicalOperator() + " ");
								}
								sbFilterText.append(DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, (String) labelsMap.get(colname), "") + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
							}
						}
					}
				}
			}

			String	allowBrowse = "";
			if(request.getAttribute("allowBrowse") != null) {
				if(request.getAttribute("allowBrowse") instanceof String)
					allowBrowse = (String) request.getAttribute("allowBrowse");
				else allowBrowse = "true";
			}
			else {
				allowBrowse = "true";
			}

			//if ( browseObject.getBrowseName().equalsIgnoreCase("autotable_cmod") && irows > 1) {
			//	allowBrowse = "false";
			//}

			String filterType = (String) request.getAttribute("filterType");
			if (HiltonUtility.isEmpty(filterType)) {
				filterType = "ADVANCED";
			}


			String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
			String	s_po_type = (String) request.getAttribute("PoHeader_poType");
			if (s_req_type == null)	{	s_req_type = "";	}
			if (s_po_type == null)	{	s_po_type = "";	}

			long start = System.currentTimeMillis();

			/*	added for System Table */
			String tableType = HiltonUtility.ckNull((String) request.getAttribute("tableType"));
			String tableTypeMod = null;
			String title = null;
			if (!HiltonUtility.isEmpty(tableType) && browseObject.getBrowseName().indexOf("stdtable") >= 0)
			{
				if (tableType.indexOf("RQ") >= 0)
				{
					tableTypeMod = "req-" + tableType;
				}
				else if (tableType.indexOf("PO") >= 0)
				{
					tableTypeMod = "po-" + tableType;
				}
				if (!HiltonUtility.isEmpty(tableTypeMod))
				{
					title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, tableTypeMod, "",s_po_type);
				}
				else
				{
					title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, tableType, "",s_po_type);
				}
				if (!HiltonUtility.isEmpty(title))
				{
					browseObject.setTitle(title + " Browse");

				}
			}
			else if(browseObject.getBrowseName().indexOf("item") >= 0){
				browseObject.setTitle("Item " + tableType + " Browse");
			}
			if (browseObject.getBrowseName().equals("catalogitem-admin"))
			{
				String catId = HiltonUtility.ckNull((String) request.getAttribute("Catalog_catalogId"));
				browseObject.setTitle(catId + " Catalog Browse");
			}

	String createdFrom = (String) request.getAttribute("createdfrom");
	String	icReqHeader = "";

	if (request.getAttribute("RequisitionHeader_icReqHeader") instanceof String)
	{
		icReqHeader = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	}

	String	icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String	icSaleHeader = (String) request.getAttribute("SaleHeader_icSaleHeader");
	String	vendorId = (String) request.getAttribute("RfqVendor_vendorId");
	String appMenuAction = HiltonUtility.ckNull((String) request.getAttribute("appMenuAction"));
	String originalRetrieve = (String) request.getAttribute("originalRetrieve");
	String currencyCode = (String) request.getAttribute("currencyCode");
	int totalRecordCount = 0;
	BigDecimal poTotal = new BigDecimal(0);
	BigDecimal paymentTotal = new BigDecimal(0);
	BigDecimal balance = new BigDecimal(1);

	try {
		poTotal = new BigDecimal((String) request.getAttribute("poTotal"));
	} catch(Exception bde) {
	}
	try {
		if (originalRetrieve != null && originalRetrieve.equals("Y")) {
			totalRecordCount = irows;
		} else {
			totalRecordCount = new Integer((String) request.getAttribute("totalRecordCount")).intValue();
		}
	} catch(Exception bde) {
	}
	try {
		Object paymentTotalObj = request.getAttribute("paymentTotal");
		if (paymentTotalObj instanceof BigDecimal) {
			paymentTotal = (BigDecimal) paymentTotalObj;
		} else {
			paymentTotal = new BigDecimal((String) paymentTotalObj);
		}
	} catch(Exception bde) {
	}
	try {
		balance = poTotal.subtract(paymentTotal);
	} catch (Exception bde) {
	}

	createdFrom = HiltonUtility.ckNull(createdFrom);
	icReqHeader = HiltonUtility.ckNull(icReqHeader);
	icRfqHeader = HiltonUtility.ckNull(icRfqHeader);
	icSaleHeader = HiltonUtility.ckNull(icSaleHeader);
	vendorId = HiltonUtility.ckNull(vendorId);

	String	s_refresh = (String) request.getAttribute("refreshOpener");
	if (HiltonUtility.isEmpty(s_refresh))		{	s_refresh = "N";			}

	String editFieldsApprover = HiltonUtility.ckNull((String)request.getAttribute("editFieldsApprover"));
	String formFieldApprover = HiltonUtility.ckNull((String) request.getAttribute("formField"));
	boolean allowBrowseApprover = false;
	if (!HiltonUtility.isEmpty(editFieldsApprover) && !HiltonUtility.isEmpty(formFieldApprover) && editFieldsApprover.indexOf(formFieldApprover) >= 0) {
		allowBrowseApprover = true;
	}
%>
<tsa:hidden name="refreshOpener" value="<%=s_refresh%>"/>
<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="browseId" value="<%=browse.getBrowseId()%>"/>
<tsa:hidden name="newPage" value="1"/>
<tsa:hidden name="pageSize" value=""/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="poTotal" value="<%=poTotal.toString()%>"/>
<tsa:hidden name="paymentTotal" value="<%=paymentTotal.toString()%>"/>
<tsa:hidden name="balance" value="<%=balance.toString()%>"/>
<tsa:hidden name="currencyCode" value="<%=currencyCode%>"/>
<tsa:hidden name="originalRetrieve" value="N"/>
<tsa:hidden name="totalRecordCount" value="<%= \"\" + totalRecordCount%>"/>
<tsa:hidden name="filterType" value="<%=filterType%>"/>
<tsa:hidden name="createdfrom" value="<%=createdFrom%>"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icReqHeader%>"/>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="SaleHeader_icSaleHeader" value="<%=icSaleHeader%>"/>
<tsa:hidden name="attributeSet" value="<%= \"\" + browseObject.isAttributeSet()%>"/>
<tsa:hidden name="appMenuAction" value="<%=appMenuAction%>"/>
<tsa:hidden name="rowschanged" value="N"/>
<tsa:hidden name="multipleBrowse" value="<%=s_multiple_browse%>"/>

<table cellpadding=0 cellspacing=0 border=0 width=<%=formWidth%> id="browseTab">
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width="150px">
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><%=headerEncoder.encodeForHTML(browseObject.getTitle(oid, language))%></div></td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>
	<td valign=bottom align=middle>
		<div id="viewReportDisplay" style="visibility:hidden; display:none; width:100%">
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td valign=top nowrap><a href="javascript: viewReport('<%=headerEncoder.encodeForJavaScript(tableType)%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border="0"></a>&nbsp;</td>
			<td valign=top nowrap><a href="javascript: viewReport('<%=headerEncoder.encodeForJavaScript(tableType)%>'); void(0);">View Report</a></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=1 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
		</div>
	</td>
	<td valign=bottom align=middle width=150px>
		<div id="selectPrintDisplay" style="visibility:hidden; display:none;">
		<table border=0 cellspacing=0 cellpadding=0 border=0 width="150px">
		<tr>
			<td valign=bottom align="right" nowrap><input type=checkbox name="selectAll" onclick="flagAll(this.checked);">&nbsp;</td>
			<td valign=middle nowrap class="label">Select All</td>
		</tr>
		</table>
		</div>
		<table cellpadding="0" cellspacing="0" border=0 width="150px">
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
	<td valign=bottom align=right width="100%">
		<div id="filterTextDisplay">
<%	if (isXpress && browseObject.getBrowseName().equals("admin-userprofile")) {%>
		<%@ include file="/admin/user/user_available_licenses.jsp" %></div>
<%	} else {%>
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr><td align=right valign=top nowrap>&nbsp;<b>Original Filter:</b></td><td>&nbsp;<%=headerEncoder.encodeForHTML(sbOriginalFilterText.toString())%></td></tr>
		<tr><td align=right valign=top nowrap>&nbsp;<b>Current Filter:</b></td><td>&nbsp;<%=headerEncoder.encodeForHTML(sbFilterText.toString())%></td></tr>
		</table>
<%	}%>
		</div>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<div id="instructions" style="visibility:hidden; display:none; align:center;"></div>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%>>
<tr>
	<td nowrap>&nbsp;&nbsp;<b>Showing Records <span id="beginRecord"><%=browse.getRowStart()%></span> - <span id="endRecord"><%=browse.getRowEnd()%></span> of <%=browse.getRowCount()%></b></td>
	<td nowrap>
		<div id="goToPage" name="goToPage" style="border:solid 0px; padding: 2px; align:center; display:none; visibility:hidden;">
		<table border=0 cellpadding=2 cellspacing=1 align=center>
		<tr>
			<td align=right><b>Go To Page: </b><input type=text name="go_to_pg" size=5 value=""> <b>of <%=ipageCount%></b></td>
			<%if(modeRetrieveAllRegisters != null  && modeRetrieveAllRegisters.equalsIgnoreCase("Y")){%>
			<td><div id="pxmediumbutton"><a href="javascript: if (!isEmpty(nfilter(frm.go_to_pg))) { pageRetrieveAllRegistersMode(nfilter(frm.go_to_pg)); } void(0);">Go</a></div></td>
			<%}else{%>
			<td><div id="pxmediumbutton"><a href="javascript: if (!isEmpty(nfilter(frm.go_to_pg))) { pageMe(nfilter(frm.go_to_pg)); } void(0);">Go</a></div></td>
			<%}%>
		</tr>
		</table>
		</div>
	</td>
</tr>
</table>


<!-- start rounded corners -->
<div id="container" style="width: <%=formWidth%>; align:left; margin:5;">
<b class="rtop">
  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
</b>
<table cellpadding=0 cellspacing=0 border=0 class=browseHdr width=100%>
<tr>
	<td>
		<table border=0 cellpadding=2 cellspacing=0 class=browseHdr width=100%>
		<tr>
		<% if (browseObject.getBrowseName().equals("print-check-invoices")) { %>
			<td class="browseHdr" nowrap="" height="18" align="left" width="2%">Pay</td>
			<td class="browseHdr" nowrap="" height="18" align="left" width="2%">Disc</td>
		<% } %>
<%	String className = "";
	for (int i=0; i < labels.length; i++) {
			String alignment = "left";
			int	iwidth = 10;

			if (labels[i].getType().indexOf("Decimal") >= 0) {	alignment = "right";	}

			if (labels[i].getSize() > 0) {	iwidth = labels[i].getSize();	}
			if (HiltonUtility.isEmpty(sortedColumn))
			{
				if (labels[i].getSort().length() > 0)
				{
					sortedColumn = labels[i].getColumnName();
					if (labels[i].getSort().equalsIgnoreCase("A"))
					{
						sortedOrder = "ASC";
					}
					else
					{
						sortedOrder = "DESC";
					}
				}
			}
			className="browseHdr";
			%>
			<td nowrap height=18px class=<%=className%> width=<%=iwidth%>% align=<%=alignment%>>
				&nbsp;
	<%	if (!labels[i].getAllowFilter().equals("Y") && !labels[i].isSqlPart()) {%>
				<%=labels[i].getLabel(oid, language)%>&nbsp;
	<%	} if(s_multiple_browse.equalsIgnoreCase("Y")){ %>
			<%=labels[i].getLabel(oid, language)%>&nbsp;
	<%	}else {%>
				<a href="javascript: sortMe('<%=labels[i].getColumnName()%>'); void(0);" class=<%=className%>>
				<%=labels[i].getLabel(oid, language)%>

				<% if (labels[i].getColumnName().equalsIgnoreCase(sortedColumn)) {
						if (sortedOrder.equalsIgnoreCase("ASC")) { %>
					<img src="<%=contextPath%>/images/arrow_up.gif" border=0>
					<% } else { %>
					<img src="<%=contextPath%>/images/arrow_down.gif" border=0>
					<% }
				} %>
				</a>&nbsp;
	<%	}%>
			</td>
<%	}%>
			<tsa:hidden name="sortedOrder" value="<%=sortedOrder%>"/>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdr style="border:solid 2px; background: #FFFFFF; padding: 2px; width: <%=formWidth%>; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
		</table>
		</div>
		<div id="noAttributeSet" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		<tr><td><b><%=headerEncoder.encodeForHTML(browseObject.getNoAttributeErrorMsg())%></b></td></tr>
		</table>
		</div>

		<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
<%		String rowClass = "browseRow";
		for (int il = 0; il < list.size(); il++) {
				Object object = list.get(il);
				List	detailList = new ArrayList();
				int	itd = 0;

				sbHiddenFields.append("<input type=hidden name=as_rowstatus value=\\\"\\\">");
%>
		<tr>
			<td class=<%=rowClass%>>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=<%=rowClass%>>
				<tr class="<%=rowClass%>">
				<% if (browseObject.getBrowseName().equals("print-check-invoices")) { %>
					<td class="browseRow" height="18" align="left" width="2%" valign="top">
						<input type="checkbox" value="Y" name="c_checkbox" onclick="setRowStatus(<%=il%>, 'U');"/>
						<tsa:hidden value="" name="Input_checkbox"/>
					</td>
					<td class="browseRow" height="18" align="left" width="2%" valign="top">
						<input type="checkbox" value="Y" name="c_checkbox2" checked onclick="takeDiscount(<%=il%>);setRowStatus(<%=il%>, 'U');"/>
						<tsa:hidden value="Y" name="Input_checkbox2"/>
					</td>
				<% }else if(browseObject.getBrowseName().equals("myopenreqs")){ %>
					<td height="18" align="left" width="2%" valign="top">
						<input type="checkbox" value="Y" name="c_checkbox" onclick="setRowStatus(<%=il%>, 'U');"/>
						<tsa:hidden value="" name="Input_checkbox"/>
					</td>
				<% } %>
<%		String tmp_amount = "";
		for (int i = 0; i < browseColumns.length; i++) {
			BrowseColumn column = browseColumns[i];
					Object result = null;
					String	resultString = "";
					String alignment = "left";
					int	iwidth = 10;

					result = BrowseUtility.getTestColumnValue(column, object, oid, browseColumns, browse.getBrowseId(), uid);

					if (column.isHiddenInput()) {
						String resultStr = String.valueOf(result);
						resultStr = resultStr.replace((char)10 ,' ');
						resultStr = resultStr.replace((char)13 ,' ');
						resultStr = HiltonUtility.encodeHtml(resultStr);

						sbHiddenFields.append( "<input type=hidden name=\\" + '"' + column.getColumnName() + "\\\" value=\\\"" + resultStr + "\\\">");
						//if (il == (list.size() - 1))
						//{
						//	sbHiddenFields.append( "<tsa:hidden name=\\" + '"' + column.getColumnName() + "\\\" value=\\\"\\\">");
						//}
					}
					if (column.isDetail()) {
						BrowseColumn detailColumn = BrowseUtility.getBrowseColumnCopy(column);
						detailColumn.setValue(result);
						detailList.add(detailColumn);
					}

					if (result instanceof String) {
						resultString = (String) result;
					} else if (result instanceof BigDecimal) {
						resultString = ((BigDecimal) result).toString();
					}

					if(i==1){ //temp source
						tmp_amount = resultString; //temp source
					}//temp source
					if (!column.isHidden() && !column.isDetail()) {%>
					<td height=18px class="<%=rowClass%>" align=<%=column.getAlignment()%> width=<%=column.getIWidth()%>% valign=top>
<%					if (column.getType().equals("ConditionalLink")) {%>
						<div id="browse_<%=column.getColumnName()%>">
<%					}
					if (column.isTextInput()) {
						if (allowEdit) {%>
						<input type=text name="<%=column.getColumnName()%>" value="<%=result%>" size="<%=column.getInputSize()%>" onchange="setRowStatus(<%=il%>, 'U');"/>
<%						} else {%>
						<input type=text name="<%=column.getColumnName()%>" value="<%=result%>" style="border:none;text-align:right;background-color:transparent" size="<%=column.getInputSize()%>" readonly />
<%						}
					} else if (column.isCheckbox()) {
						if (allowEdit) {%>
						<input type=checkbox name="c_<%=column.getColumnName()%>" value="<%=result%>" onclick="setFlagFromCkbox(this, frm.<%=column.getColumnName()%>, <%=il%>);setRowStatus(<%=il%>, 'U');"<% if (result.equals("Y")) {%> checked<%}%>/>
<%						} else {%>
						<input type=checkbox name="c_<%=column.getColumnName()%>" value="<%=result%>" style="border:none;text-align:right;background-color:transparent"<% if (result.equals("Y")) {%> checked<%}%> readonly />
<%						}
					} else if (column.getLink() != null && column.getLink().length() > 0 && (allowBrowse.equalsIgnoreCase("true") || allowBrowseApprover)) {
						if (column.getLinkImage() != null && column.getLinkImage().length() > 0) { 	%>
						<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>" onclick="javascript: rowSelect='<%=il%>';"><img id="img_<%=column.getColumnName()%>_<%=il%>" src="<%=contextPath%>/images/<%=column.getLinkImage()%>" border="0" alt="<%=column.getLabel(oid, language)%>"></a>
<%						} else {
							if (resultString.startsWith("#") && "invitem-admin".equals(browseObject.getBrowseName())) {
								String numberLink = BrowseUtility.testPopulateLinkParameters(column, object, browseColumns);
								int index = numberLink.indexOf("(");
								String numberLinkFinal = numberLink.substring(0, index + 2) + resultString + numberLink.substring(index + 2);
							%>
								<a href="<%=numberLinkFinal%>" onclick="javascript: rowSelect='<%=il%>';" onMouseOver="<% if (!browseObject.isDetailVisible()) {%>showDetails(<%=il%>);<%}%>highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);<% if (!browseObject.isDetailVisible()) {%>hideDetails(<%=il%>);<%}%>">
							<% } else { %>
								<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>" onclick="javascript: rowSelect='<%=il%>';" onMouseOver="<% if (!browseObject.isDetailVisible()) {%>showDetails(<%=il%>);<%}%>highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);<% if (!browseObject.isDetailVisible()) {%>hideDetails(<%=il%>);<%}%>">
<%							}
							if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && resultString.toUpperCase().indexOf(findString) >= 0) {
								findString = "";%>
						<font class=selectedRow><%=result != null ? result.toString() : ""%></font>
<%							} else {%>
							<%=result != null ? result.toString() : ""%>
<%							}%>
						</a>
<%						}
					} else if (column.isSelectInput() && allowEdit) {%>
						<%=BrowseUtility.getSelectColumnValue(column, object, browseColumns, il)%>
<%					} else {
							if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && resultString.toUpperCase().indexOf(findString) >= 0) {
								findString = "";%>
						<font class=selectedRow><%=result != null ? headerEncoder.encodeForHTML(result.toString()) : ""%></font>
<%						} else {%>
						<div id="test"></div>
						<%=result != null ? result.toString() : ""%>
<%						}
					}
					if (column.getType().equals("ConditionalLink")) {%>
						</div>
<%					}%>
					</td>
<%				}
			} %>
					<% if(browseObject.getBrowseName().equalsIgnoreCase("print-check-invoices")) { %>
					<td height="18" align="left" width="2%" valign="top">
						<a name="deleteLink" href="javascript: deleteMe(<%=il%>);">
						<IMG SRC="<%=contextPath%>/images/delete.gif" ALT="Delete" BORDER="0">
						</a>
					</td>
					<% } %>
				</tr>
				</table>
<%		if (browseObject.isDetailVisible()) {%>
				<div  id="details<%=il%>" style="visibility:visible;display:block;" class=<%=rowClass%>>
<%		} else {%>
				<div  id="details<%=il%>" style="visibility:hidden;display:none;" class=<%=rowClass%>>
<%		}%>
				<table  id="detailRows" border=0 cellspacing=0 cellpadding=0 class=<%=rowClass%> width=95% align=right>
				<tr class=<%=rowClass%>>
					<td class=<%=rowClass%>>
						<table border=0 cellspacing=0 cellpadding=0 class=<%=rowClass%> width=100%>
						<tr>
<%		int itotalWidth = 0;
			for (int i=0; i < detailList.size(); i++) {
				BrowseColumn column = (BrowseColumn) detailList.get(i);
				int	iwidth = column.getIWidth();
				itotalWidth = itotalWidth + iwidth;

				if (iwidth >= 100 || itotalWidth > 100) {
					if (i > 0) {
%>
						</tr>
						</table>
					</td>
				</tr>
				<tr class=<%=rowClass%>>
					<td class=<%=rowClass%>>
						<table border=0 cellspacing=0 cellpadding=0 class=<%=rowClass%> width=100%>
						<tr>
<%					}
				}%>
					<td colspan=<%=detailList.size()%> height=18px class=<%=rowClass%> width=<%=iwidth%>% valign=top>
<%				if (column.getLink() != null && column.getLink().length() > 0) {
					if (column.getLinkImage() != null && column.getLinkImage().length() > 0) { 	%>
						<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>"> <img src="<%=contextPath%>/images/<%=column.getLinkImage()%>" border="0" alt="<%=column.getLabel(oid, language)%>"></a>
<%					} else { %>
						<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>"><%=column.getLabel(oid, language)%></a>
<%					} %>
<%				} else {
					if (!HiltonUtility.isEmpty(column.getLabel(oid, language))) { %>
						<b><%=column.getLabel(oid, language)%>:</b>
<%					}
					if (column.isTextInput()) {
						if (allowEdit) {%>
						<input type=text name="<%=column.getColumnName()%>" value="<%=column.getValue()%>" size="<%=column.getInputSize()%>" onchange="setRowStatus(<%=il%>, 'U');"/>
<%						} else {%>
						<input type=text name="<%=column.getColumnName()%>" value="<%=column.getValue()%>" style="border:none;text-align:right;background-color:transparent" size="<%=column.getInputSize()%>" readonly />
<%						}
					} else if (column.isCheckbox()) {
						if (allowEdit) {%>
						<input type=checkbox name="c_<%=column.getColumnName()%>" value="<%=column.getValue()%>" onclick="setFlagFromCkbox(this, frm.<%=column.getColumnName()%>, <%=il%>);setRowStatus(<%=il%>, 'U');"<% if (column.getValue().equals("Y")) {%> checked<%}%>/>
<%						} else {%>
						<input type=checkbox name="c_<%=column.getColumnName()%>" value="<%=column.getValue()%>" style="border:none;text-align:right;background-color:transparent"<% if (column.getValue().equals("Y")) {%> checked<%}%> readonly />
<%						}
					} else if (column.isSelectInput() && allowEdit) {%>
						<%=BrowseUtility.getSelectColumnValue(column, object, browseColumns, il)%>
<%					}  else {%>
						&nbsp;<%=column.getValue()%>
<%					}%>
					</td>
<%				}
				if (itotalWidth >= 100 && (i+1) < detailList.size()) {
					itotalWidth = 0;
%>
						</tr>
						</table>
					</td>
				</tr>
				<tr class=<%=rowClass%>>
					<td class=<%=rowClass%>>
						<table border=0 cellspacing=0 cellpadding=0 class=<%=rowClass%> width=100%>
						<tr>
<%			}
			} %>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				</div>
			</td>
		</tr>
<%
			if (rowClass.equals("browseRow"))
			{
				rowClass = "summary";
			} else {
				rowClass = "browseRow";
			}
		}
%>
		</table>
		</div>
	</td>
</tr>
</table>
<b class="rbottom">
  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
</b>
</div>
<!-- end rounded corners -->

<%	if (browseObject.getBrowseName().equals("paymentaccount")) {

		totalRecordCount = new Integer((String) request.getAttribute("totalCheckRecords")).intValue();
%>
<table width=<%=formWidth%> border=0>
<tr>
	<td align=right>
		<table border=1 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right class=label>Total # of Payments</td>
			<td align=right><%=totalRecordCount%>&nbsp;</td>
		</tr>
		<tr>
			<td align=right class=label>Total # of Invoices</td>
			<td align=right><%=irows%>&nbsp;</td>
		</tr>
		<tr>
			<td align=right class=label>Total Order Amount</td>
			<td align=right><%=HiltonUtility.getFormattedCurrency(poTotal, currencyCode, oid)%>&nbsp;</td>
		</tr>
		<tr>
			<td align=right class=label>Total Invoice Amount</td>
			<td align=right><%=HiltonUtility.getFormattedCurrency(paymentTotal, currencyCode, oid)%>&nbsp;</td>
		</tr>
		<tr>
			<td align=right class=label>Balance</td>
			<td align=right> <% if (balance.compareTo(new BigDecimal(0)) < 0) {%><font style="color:red"><%=HiltonUtility.getFormattedCurrency(balance, currencyCode, oid)%></font><%} else {%><%=HiltonUtility.getFormattedCurrency(balance, currencyCode, oid)%>&nbsp;<%}%></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>

<%	if (browseObject.getBrowseName().equals("ext_inv_move")) { %>
<table id="addBinLocationTable" width=<%=formWidth%> border=0 style="display: none;">
<tr>
	<td align=left>
		<div id="pxbutton"><a href="javascript: addBinLocation(); void(0);">Add Bin Location</a></div>
	</td>
</tr>
</table>
<%	} %>

<div id="originalFilterFields"><%=sbOriginalFilterFields.toString()%></div>
<div id="currentFilterFields"><%=sbFilterFields.toString()%></div>

<table border=0 width=<%=formWidth%>>
<% if(!s_multiple_browse.equalsIgnoreCase("Y")){%>
<tr>
	<td align=center><%@ include file="/browse/browse_pageform.jsp" %></td>
</tr>
<%} %>
<tr>
	<td align=center><%@ include file="/browse/browse_page_nav.jsp" %></td>
</tr>
</table>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var browseName = "${esapi:encodeForJavaScript(browse.browseName)}";
	var browseId = "<%=browse.getBrowseId()%>";
	var totalRows = <%=irows%>;
	var pageSize = <%=ipageSize%>;
	var pageCount = <%=ipageCount%>;
	var sortedColumn = "<%=headerEncoder.encodeForJavaScript(sortedColumn)%>";
<%	if (sbFilterText != null && sbFilterText.length() > 0) {%>
	var filterSet = true;
<%	} else {%>
	var filterSet = false;
<%	}
	if (sbOriginalFilterText != null && sbOriginalFilterText.length() > 0) {%>
	var originalFilterSet = true;
<%	} else {%>
	var originalFilterSet = false;
<%	}%>

	currentPage = <%=browse.getCurrentPage()%>;

	setHiddenFields("<%=sbHiddenFields%>");

	if (document.getElementById("filterFields") != null && document.getElementById("filterFields") != undefined) {
		document.getElementById("filterFields").innerHTML = document.getElementById("currentFilterFields").innerHTML;
		document.getElementById("currentFilterFields").innerHTML = "";
	} else {
		document.getElementById("currentFilterFields").id = "filterFields";
	}

	if (<%=browse.getRowEnd()%> == 0) {
		rowCount = 0;
	} else {
		rowCount = <%=browse.getRowEnd()%> - <%=browse.getRowStart()%> + 1;
	}

	browseDisplaySetup();

	if (window.parent.frm.showAddBinLocation && window.parent.frm.showAddBinLocation.value == "Y" && document.getElementById("addBinLocationTable"))
	{
		document.getElementById("addBinLocationTable").style.display = "";
	}

	function showDetails(row) {
		displayArea("details" + row);
	}
	function hideDetails(row) {
		hideArea("details" + row);
	}

// Report Queue functions

	function addUsers() {
		for (var i=0; i<<%=list.size()%>; i++) {
			if (frm.c_checkbox[i].checked == true && mails.indexOf(frm.UserProfile_mailId[i].value) < 0) {
				if (mails == "") {
					mails = frm.UserProfile_mailId[i].value;
				} else  {
					mails = mails + "," + frm.UserProfile_mailId[i].value;
				}
			} else if (frm.c_checkbox[i].checked == false && mails.indexOf(frm.UserProfile_mailId[i].value) >= 0) {
				var pos1 = mails.indexOf(frm.UserProfile_mailId[i].value)-1;
				if (pos1 < 0) pos1 = 0;
				var pos2 = mails.indexOf(",",pos1+1);
				var pos3 = mails.length;
				if (pos2 < pos1) pos2 = pos3;
				mails = mails.substring(0,pos1)+mails.substring(pos2,pos3);
				if (mails.substring(0,1)==",")
					mails = mails.substring(1);
			}
		}
		frm.mails.value = mails;
	}

	function chgSendTo() {
		addUsers();
		var mailArray = mails.split(",");
		mails = mailArray[0];
		for (x=1;x<mailArray.length;x++) mails = mails + ";" + mailArray[x];
		window.parent.frm.ReportQueue_sendTo.value = mails;
		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

	function selectUsers() {
		var mailArray = mails.split(",");
		for (var i=0; i<<%=list.size()%>; i++)
			for (var j=0; j<mailArray.length; j++)
				if (frm.UserProfile_mailId[i].value == mailArray[j])
					frm.c_checkbox[i].click();
	}

	function flagAll(checkAll) {
			var checkboxes = document.all("c_checkbox");

			if (checkboxes != null) {
				var myObject = document.all("browseRows");

				var myTable;

				if (myObject.length > 1) {
					myTable = myObject(currentPage - 1);
				} else {
					myTable = myObject;
				}
				var pageRows = myTable.rows.length;
				//var startingRow = eval((currentPage - 1) * pageSize);
				var startingRow = eval(0);
				var endingRow = eval(startingRow + pageSize);

				if (pageRows < pageSize) {
					endingRow = eval(startingRow + pageRows);
				}

				if (checkboxes.length > 1) {
					for (i = startingRow; i < endingRow; i++) {
						var cbox = checkboxes[i];
						cbox.checked = checkAll;
					}
				} else {
					var cbox = checkboxes;
					cbox.checked = checkAll;
				}
			}
		}

<% if (browseObject.getBrowseName().equals("print-check-invoices")) { %>
   if(frm.c_checkbox2)
   {
	for (var i = 0; i < frm.c_checkbox2.length; i++)
    {
   		takeDiscount(i);
    }
   }
<% } %>

<% if ( browseObject.getBrowseName().equals("req-assigned-by-line") && oid.equalsIgnoreCase("bly07p") ) { %>
	Array77= createAutoawardMenu(Array77[0]);
<% } %>

	function setRowStatus(row, status) {
		frm.rowschanged.value = "Y";
		var rowstatusFlds = document.all("as_rowstatus");
		if (frm.as_rowstatus[row]) {
			frm.as_rowstatus[row].value = status;
		}
		else if (frm.as_rowstatus && row == 0) {
			frm.as_rowstatus.value = status;
		}
	}

	function addBinLocation()
	{
		var itemNumber = "";
		var itemLocation = "";
		var unitCost = "";

		if (window.parent.frm.InvBinLocation_itemNumber) {
			itemNumber = window.parent.frm.InvBinLocation_itemNumber.value;
		}
		if (window.parent.frm.InvBinLocation_itemLocation) {
			itemLocation = window.parent.frm.InvBinLocation_itemLocation.value;
		}
		if (window.parent.frm.unitCost) {
			unitCost = window.parent.frm.unitCost.value;
		}
		var newInputField = "<input type='hidden' name='InvBinLocation_itemNumber' value='" + itemNumber + "'>";
		newInputField = newInputField + "<input type='hidden' name='InvBinLocation_itemLocation' value='" + itemLocation + "'>";
		newInputField = newInputField + "<input type='hidden' name='unitCost' value='" + unitCost + "'>";
		setHiddenFields(newInputField);

		doSubmit('inventory/inv_return_bin_locations.jsp','InvBinLocationRetrieveByItem');
	}

// End Hide script -->
</SCRIPT>
