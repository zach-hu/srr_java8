<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsagate.properties.DictionaryManager" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/checkPrinting.js"></script>
<%
			Browse browse = (Browse) request.getAttribute("browse");
			BrowseObject browseObject = browse.getBrowseObject();
			BrowseColumn[] labels = browseObject.getLabels();
			List list = browse.getPageResults();
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

					sb.append("<input type=hidden name=\"colname\" value=\"" + colname + "\">");
					sb.append("<input type=hidden name=\"filter_txt\" value=\"" + filter.getValue() + "\">");
					sb.append("<input type=hidden name=\"operator\" value=\"" + filter.getOperator() + "\">");
					sb.append("<input type=hidden name=\"logicalOperator\" value=\"" + logicalOperator + "\">");
					sb.append("<input type=hidden name=\"sort\" value=\"" + filter.getSort() + "\">");

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
								sbOriginalFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
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
								sbFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
							}
						}
					}
				}
			}

			String	allowBrowse = (String) request.getAttribute("allowBrowse");
			if (allowBrowse == null) {
				allowBrowse = "true";
			}

			String filterType = (String) request.getAttribute("filterType");
			if (HiltonUtility.isEmpty(filterType)) {
				filterType = "ADVANCED";
			}

			String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
			String	s_po_type = (String) request.getAttribute("PoHeader_poType");
			if (s_req_type == null)	{	s_req_type = "";	}
			if (s_po_type == null)	{	s_po_type = "";	}

			long start = System.currentTimeMillis();

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
%>
<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="browseId" value="<%=browse.getBrowseId()%>"/>
<tsa:hidden name="newPage" value="1"/>
<tsa:hidden name="pageSize" value=""/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="poTotal" value="<%=poTotal%>"/>
<tsa:hidden name="paymentTotal" value="<%=paymentTotal%>"/>
<tsa:hidden name="balance" value="<%=balance%>"/>
<tsa:hidden name="currencyCode" value="<%=currencyCode%>"/>
<tsa:hidden name="originalRetrieve" value="N"/>
<tsa:hidden name="totalRecordCount" value="<%=totalRecordCount%>"/>
<tsa:hidden name="filterType" value="<%=filterType%>"/>
<tsa:hidden name="createdfrom" value="<%=createdFrom%>"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icReqHeader%>"/>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="SaleHeader_icSaleHeader" value="<%=icSaleHeader%>"/>
<tsa:hidden name="attributeSet" value="<%=browseObject.isAttributeSet()%>"/>
<tsa:hidden name="appMenuAction" value="<%=appMenuAction%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=browseObject.getTitle(oid, language)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>
	<td valign=bottom align=middle>
		<div id="viewReportDisplay" style="visibility:hidden; display:none;">
		</div>
		<table width=100% cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
	<td valign=bottom align=middle>
		<div id="selectPrintDisplay" style="visibility:hidden; display:none;">
		<table border=0 cellspacing=0 cellpadding=0 border=0 width="100%">
		<tr>
			<td valign=bottom align="right" nowrap><input type=checkbox name="selectAll" onclick="flagAll(this.checked);calculateTotals();">&nbsp;</td>
			<td valign=middle nowrap class="label">Select All</td>
		</tr>
		</table>
		</div>
		<table cellpadding="0" cellspacing="0" border=0 width="100%">
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
	<td valign=bottom align=right>
		<div id="filterTextDisplay">
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr><td align=right valign=top nowrap>&nbsp;<b>Original Filter:</b></td><td>&nbsp;<%=sbOriginalFilterText%></td></tr>
		<tr><td align=right valign=top nowrap>&nbsp;<b>Current Filter:</b></td><td>&nbsp;<%=sbFilterText%></td></tr>
		</table>
		</div>
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


<div id="instructions" style="visibility:hidden; display:none; align:center;"></div>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%>>
<tr>
	<td nowrap>&nbsp;&nbsp;<b>Showing Records <span id="beginRecord"><%=browse.getRowStart()%></span> - <span id="endRecord"><%=browse.getRowEnd()%></span> of <%=browse.getRowCount()%></b></td>
	<td nowrap>
		<div id="goToPage" name="goToPage" style="border:solid 0px; padding: 2px; align:center; display:none; visibility:hidden;">
		<table border=0 cellpadding=2 cellspacing=1 align=center>
		<tr>
			<td align=right><b>Go To Page: </b><input type=text name="go_to_pg" size=5 value=""> <b>of <%=ipageCount%></b></td>
			<td><a href="javascript: if (!isEmpty(nfilter(frm.go_to_pg))) { pageMe(nfilter(frm.go_to_pg)); } void(0);"><img class=button src="<%=contextPath%>/images/button_go_sm.gif" border=0 valign=bottom></a></td>
		</tr>
		</table>
		</div>
	</td>
</tr>
</table>
<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%>>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=670px class=browseHdrDk align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=<%=formWidth%>>
		<tr>
		<% if (browseObject.getBrowseName().equals("print-check-invoices")) { %>
			<td class="browseHdrDk" nowrap="" height="18" align="left" width="2%">Pay</td>
			<td class="browseHdrDk" nowrap="" height="18" align="left" width="2%">Disc</td>
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
			if (labels[i].getColumnName().equalsIgnoreCase(sortedColumn))
			{
				className="sortedColumn";
			}
			else
			{
				className="browseHdrDk";
			} %>
			<td nowrap height=18px class=<%=className%> width=<%=iwidth%>% align=<%=alignment%>>
	<%	if (!labels[i].getAllowFilter().equals("Y") && !labels[i].isSqlPart()) {%>
				<%=labels[i].getLabel(oid, language)%>&nbsp;
	<%	} else {%>
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
		<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 990px; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=<%=formWidth%> class=browseRow>
		<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
		</table>
		</div>
		<div id="noAttributeSet" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=<%=formWidth%> class=browseRow>
		<tr><td><b><%=browseObject.getNoAttributeErrorMsg()%></b></td></tr>
		</table>
		</div>

		<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=<%=formWidth%> class=browseRow>
<%		String rowClass = "browseRow";
		for (int il = 0; il < list.size(); il++) {
				Object object = list.get(il);
				List	detailList = new ArrayList();
				int	itd = 0;
%>
		<tr>
			<td class=<%=rowClass%>>
				<table border=0 cellspacing=0 cellpadding=1 width=<%=formWidth%> class=<%=rowClass%>>
				<tr class="<%=rowClass%>">
				<% if (browseObject.getBrowseName().equals("print-check-invoices")) { %>
					<td class="browseRow" height="18" align="left" width="2%" valign="top">
						<input type="checkbox" value="Y" name="c_checkbox" onclick="calculateTotals();" />
						<tsa:hidden value="" name="Input_checkbox"/>
					</td>
					<td class="browseRow" height="18" align="left" width="2%" valign="top">
						<input type="checkbox" value="Y" name="c_checkbox2" checked onclick="takeDiscount(<%=il%>)" />
						<tsa:hidden value="Y" name="Input_checkbox2"/>
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
						if (il == (list.size() - 1))
						{
							sbHiddenFields.append( "<input type=hidden name=\\" + '"' + column.getColumnName() + "\\\" value=\\\"\\\">");
						}
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
<%					if (column.isTextInput()) {%>
						<input type=text name="<%=column.getColumnName()%>" value="<%=result%>" style="border:none;text-align:right;background-color:transparent" size="8" readonly />
<%					} else if (column.getLink() != null && column.getLink().length() > 0 && allowBrowse.equalsIgnoreCase("true")) {%>
						<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>"  onclick="javascript: rowSelect='<%=il%>';" onMouseOver="<% if (!browseObject.isDetailVisible()) {%>showDetails(<%=il%>);<%}%>highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);<% if (!browseObject.isDetailVisible()) {%>hideDetails(<%=il%>);<%}%>">
<%						if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && resultString.toUpperCase().indexOf(findString) >= 0) {
								findString = "";%>
						<font class=selectedRow><%=result%></font>
<%						} else {%>
						<%=result%>
<%						}%>
						</a>
<%					} else {
							if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && resultString.toUpperCase().indexOf(findString) >= 0) {
								findString = "";%>
						<font class=selectedRow><%=result%></font>
<%						} else {%>
						<%=result%>
<%						}
						}%>
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
<%				}%>
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
<%					} %>
						&nbsp;<%=column.getValue()%>
					</td>
<%				}
				} else {%>
					<td height=18px class=<%=rowClass%> width=<%=iwidth%>% valign=top>
<%				if (column.getLink() != null && column.getLink().length() > 0) {%>
<%					if (column.getLinkImage()!= null && column.getLinkImage().length() > 0) { 	%>
						<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>"> <img src="<%=contextPath%>/images/<%=column.getLinkImage()%>" border="0" alt="<%=column.getLabel(oid, language)%>"></a>
<!-- 					<a href="<%=column.getLink()%>"> <img src="<%=contextPath%>/images/<%=column.getLinkImage()%>" border="0" alt="<%=column.getLabel(oid, language)%>"></a> -->
<%					} else { %>
						<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>"><%=column.getLabel(oid, language)%></a>
<!-- 					<a href="<%=column.getLink()%>"><%=column.getLabel(oid, language)%></a> -->
<%					} %>
<%				} else {%>
<%					if (!HiltonUtility.isEmpty(column.getLabel(oid, language))) { %>
						<b><%=column.getLabel(oid, language)%>:</b>
<%					} if(column.getLabel(oid, language).equals("Invoice Amt.")){%>
						<!-- &nbsp;<%=column.getValue()%> -->
						&nbsp;<%=tmp_amount%>*
<%						 }else{%>
							&nbsp;<%=column.getValue()%>
						<%} %>
				<%}%>
					</td>
<%			}
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
	<td width=5px>&nbsp;</td>
</tr>
</table>
<%	if (browseObject.getBrowseName().equals("print-check-invoices")) {
//		totalRecordCount = new Integer((String) request.getAttribute("totalCheckRecords")).intValue();
%>
<table width=<%=formWidth%> border=0>
<tr>
	<td align=right width=8%>&nbsp;</td>
	<td align=right width=8%>&nbsp;</td>
	<td align=right width=8%>&nbsp;</td>
	<td align=right width=8%>&nbsp;</td>
	<td align=right width=8%>&nbsp;</td>
	<td align=right width=8%><b>Totals:</b></td>
	<td align=right width=8%>
		<input type="text" readonly="" size="8" style="border: medium none ; text-align: right; background-color: transparent;" value="0" name="totalAmount"/>
	</td>
	<td align=right width=8%>
		<input type="text" readonly="" size="8" style="border: medium none ; text-align: right; background-color: transparent;" value="0" name="totalDiscount"/>
	</td>
	<td align=right width=8%>
		<input type="text" readonly="" size="8" style="border: medium none ; text-align: right; background-color: transparent;" value="0" name="totalAdjustment"/>
	</td>
	<td align=right width=8%>
		<input type="text" readonly="" size="8" style="border: medium none ; text-align: right; background-color: transparent;" value="0" name="totalPaid"/>
	</td>
	<td align=right width=8%>&nbsp;</td>
	<td align=right width=8%>&nbsp;</td>
</tr>
</table>
<%	}%>

<input type=text name="InvoiceHeader_invoiceTotal" value="" style="border:none;background-color:transparent" />
<input type=text name="InvoiceHeader_invoiceDiscount" value="" style="border:none;background-color:transparent" />
<input type=text name="InvoiceHeader_invoiceAdjustment" value="" style="border:none;background-color:transparent" />
<input type=text name="InvoiceHeader_invoicePaid" value="" style="border:none;background-color:transparent" />

<div id="originalFilterFields"><%=sbOriginalFilterFields%></div>
<div id="currentFilterFields"><%=sbFilterFields%></div>

<table border=0 width=<%=formWidth%>>
<tr>
	<td align=center><%@ include file="/browse/browse_pageform.jsp" %></td>
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
	var sortedColumn = "<%=sortedColumn%>";
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

	function showDetails(row) {
		displayArea("details" + row);
	}
	function hideDetails(row) {
		hideArea("details" + row);
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
   	if(frm.c_checkbox2.length)
   	{
		for (var i = 0; i < frm.c_checkbox2.length; i++)
    	{
   			takeDiscount(i);
    	}
   	}
   	else
   	{
		takeDiscount(0);
   	}
<% } %>

// End Hide script -->
</SCRIPT>
