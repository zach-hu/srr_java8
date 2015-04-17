<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.entity.RfqHeader" %>

<script language='Javascript1.2' src="<%=contextPath%>/supplierportal/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/supplierportal/scripts/dynamicTables.js"></script>
<%
		String browseName = (String) request.getAttribute("browseName");
		BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
		BrowseColumn[] labels = browseObject.getLabels();
		List list = (List) request.getAttribute("browseList");
		int	ipageSize = browseObject.getPageSize();
		int	ipageCount = 1;
		int	ibegin = 0;
		int	iend = 0;
		int	irows = 0;

		if (browseName.indexOf("rfq-bidboard-posts") >= 0) {
			//Remove duplicate Rfq records
			int totalRows = list.size();
			Map existingRows = new HashMap();

			for (int il = totalRows - 1; il >= 0; il--) {
				Object object = list.get(il);
				if (object instanceof Object[]) {
					Object objArray[] = (Object[]) object;
					for (int ioa=0; ioa < objArray.length; ioa++) {
						if (objArray[ioa] instanceof RfqHeader) {
							RfqHeader rfqHeader = (RfqHeader) objArray[ioa];
							if (existingRows.containsKey(rfqHeader.getRfqNumber())) {
								list.remove(il);
							} else {
								existingRows.put(rfqHeader.getRfqNumber(), String.valueOf(il));
							}
							break;
						}
					}
				}

/*				for (int i=0; i < labels.length; i++) {
					BrowseColumn column = (BrowseColumn) displayList.get(i);

					if (column.getColumnName().equals("RfqHeader_rfqNumber")) {
						if (existingRows.containsKey(column.getValue())) {
							list.remove(il);
						} else {
							existingRows.put(column.getValue(), String.valueOf(il));
						}
						break;
					}
				}
*/
			}
		}

		if (ipageSize == 0) {
			ipageSize = 10;
		}
		if (list != null) {
			irows = list.size();
		}
		if (irows > 0) {
			ibegin = 1;
			iend = irows;
		}
		if (irows > ipageSize) {
			iend = ipageSize;
		}
		if (ipageSize > 0) {
			ipageCount = ((irows - 1) / browseObject.getPageSize()) + 1;
		}

		StringBuffer sbHiddenFields = new StringBuffer();
		StringBuffer sbOriginalFilterFields = new StringBuffer();
		StringBuffer sbFilterFields = new StringBuffer();
		StringBuffer sbOriginalFilterText = new StringBuffer();
		StringBuffer sbFilterText = new StringBuffer();
		StringBuffer sbSortOptions = new StringBuffer();
		StringBuffer sbFilterOptions = new StringBuffer();
		String	sortedColumn = "";
		List filters = browseObject.getBrowseFilters();
		Map labelsMap = new HashMap();
		BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
		boolean	lbFirst = true;

		for (int i=0; i < labels.length; i++) {
			BrowseColumn column = labels[i];

			if (column.getLabel() != null && column.getLabel().length() > 0 && !column.getType().equals("Input") && !column.getClassName().equals("Input") && !column.getType().equals("Checkbox")) {
				labelsMap.put(column.getColumnName(), column.getLabel());

				if (column.getColumnName().equals(sortedColumn)) {
					sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + " selected" + ">" + column.getLabel() + "</option>");
				} else {
					sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + ">" + column.getLabel() + "</option>");
				}
				if (lbFirst) {
					lbFirst = false ;
					sbFilterOptions.append("<option value=" + '"' + column.getColumnName() + '"' + " selected" + ">" + column.getLabel() + "</option>");
				} else {
					sbFilterOptions.append("<option value=" + '"' + column.getColumnName() + '"' + ">" + column.getLabel() + "</option>");
				}
			}
		}

		if (filters != null) {
			for (int ix=0; ix < filters.size(); ix++) {
				BrowseFilter filter = (BrowseFilter) filters.get(ix);
				String	colname = filter.getColumnName().replace('.', '_');
				String	sort = filter.getSort();
				StringBuffer sb = new StringBuffer("");

				sb.append("<input type=hidden name=" + '"' + "colname" + '"' + " value=" + '"' + colname + '"' + ">");
				sb.append("<input type=hidden name=" + '"' + "filter_txt" + '"' + " value=" + '"' + filter.getValue() + '"' + ">");
				sb.append("<input type=hidden name=" + '"' + "operator" + '"' + " value=" + '"' + filter.getOperator() + '"' + ">");
				sb.append("<input type=hidden name=" + '"' + "logicalOperator" + '"' + " value=" + '"' + filter.getLogicalOperator() + '"' + ">");
				sb.append("<input type=hidden name=" + '"' + "sort" + '"' + " value=" + '"' + filter.getSort() + '"' + ">");

				if (sort != null && !sort.equalsIgnoreCase("N") && sortedColumn.length() == 0) {
					sortedColumn = colname;
				}
				if (filter.isOriginalFilter()) {
					sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "Y" + '"' + ">");

					sbOriginalFilterFields.append(sb);

					if (filter.getValue() != null && filter.getValue().length() > 0) {
						if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
							if (sbOriginalFilterText.length() > 0) {
								sbOriginalFilterText.append(filter.getLogicalOperator() + " ");
							}
							sbOriginalFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
						} else if (browseName.indexOf("rfq-bidboard-posts") >= 0 && colname.equals("RfqVendor_id_vendorId") && filter.getValue().equalsIgnoreCase(user.getVendorId())) {
							if (sbFilterText.length() > 0) {
								sbFilterText.append(filter.getLogicalOperator() + " ");
							}
							sbOriginalFilterText.append("Solicitations I have bid on.");
						}
					}
				} else {
					sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "N" + '"' + ">");

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

		String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
		String	s_po_type = (String) request.getAttribute("PoHeader_poType");
		if (s_req_type == null)	{	s_req_type = "";	}
		if (s_po_type == null)	{	s_po_type = "";	}
		String	s_today = HiltonUtility.getFormattedDate(new Date(), oid,  "MM-dd-yyyy  HH:mm:ss");
%>

<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="fromBrowse" value="<%=browseName%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12 id="browseTitle"><%=browseObject.getTitle()%></div></td></tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right>
		<div id="filterTextDisplay">
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr><td>&nbsp;<b>Original Filter:</b> <%=sbOriginalFilterText%></td></tr>
		<tr><td>&nbsp;<b>Current Filter:</b>  <%=sbFilterText%></td></tr>
		</table>
		</div>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr><td nowrap>&nbsp;&nbsp;<b>Showing Records <span id="beginRecord"><%=ibegin%></span> - <span id="endRecord"><%=iend%></span> of <%=list.size()%></b></td></tr>
</table>

<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=670px class=browseHdrDk align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=665px>
		<tr>
<%	for (int i=0; i < labels.length; i++) {
			int	iwidth = 10;
			if (labels[i].getSize() > 0) {	iwidth = labels[i].getSize();	}
%>
			<td nowrap height=18px class=browseHdrDk width=<%=iwidth%>%><a href="javascript: sortMe('<%=labels[i].getColumnName()%>'); void(0);" class=browseHdrDk><%=labels[i].getLabel()%></a></td>
<%	}%>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdrDk style="border:solid #7C8BCC 2px; background: #FFFFFF; padding: 2px; width: 675px; height: <%=((ipageSize) * 18) + 20%>px; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
		<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
		</table>
		</div>
<%
		int icurrentRow = 0;
		int icurrentPage = 1;

		while (icurrentPage <= ipageCount) {
			int inextEnd = icurrentRow + ipageSize;
			if (inextEnd > irows) {
				inextEnd = irows;
			}
%>
		<div id="page<%=icurrentPage%>" style="visibility: hidden; display: none;">
			<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>
<%		String rowClass = "browseRow";
			for (int il = icurrentRow; il < inextEnd; il++) {
				Object object = list.get(il);
				List	detailList = new ArrayList();
				int	ipageRow = ipageSize-((icurrentPage*ipageSize) - il);
				int	itd = 0;
%>
			<tr>
				<td class=<%=rowClass%>>
					<table border=0 cellspacing=0 cellpadding=1 width=665px class=<%=rowClass%>>
					<tr>

<%			for (int i = 0; i < browseColumns.length; i++) {
					BrowseColumn column = browseColumns[i];
					Object entity = BrowseUtility.getEntityObject(object, column);
					Object originalValue = null;
					Object result = null;
					String alignment = "left";
					int	iwidth = 10;

					if (entity != null) {
						result = BrowseUtility.getColumnValue(column, entity);
					} else {
						result = "";
					}

					originalValue = result;		//set originalValue before formatting result

					if (column.getType().length() >= 0 && !column.getType().equals("String")) {
						result = BrowseUtility.formatBrowseColumnValue(result, column, oid, userDateFormat);
					}

					if (column.getType().indexOf("Decimal") >= 0) {	alignment = "right";	}
					if (column.getSize() > 0) {	iwidth = column.getSize();	}

					if (column.isHiddenInput()) {
						sbHiddenFields.append( "<input type=hidden name=\\" + '"' + column.getColumnName() + "\\\" value=\\\"" + result + "\\\">");
					}
					if (column.isDetail()) {
						BrowseColumn detailColumn = BrowseUtility.getBrowseColumnCopy(column);
						detailColumn.setValue(result);
						detailList.add(detailColumn);
					}
					if (!column.isHidden() && !column.isDetail()) {
%>
						<td height=18px class=<%=rowClass%> align=<%=alignment%> width=<%=iwidth%>% vAlign=top>
<%					if (column.getType().equals("Checkbox")) {%>
							<input type="checkbox" name="c_checkbox">&nbsp;
<%					}
						if (column.getLink() != null && column.getLink().length() > 0 && allowBrowse.equalsIgnoreCase("true")) {%>
							<a href="<%=BrowseUtility.populateLinkParameters(column, entity)%>"  onclick="javascript: rowSelect='<%=il%>';" onMouseOver="showDetails(<%=ipageRow%>);highlightRow(<%=ipageRow%>);" onMouseOut="removeHighlight(<%=ipageRow%>);hideDetails(<%=ipageRow%>);">
<%						if (column.getColumnName().equals(sortedColumn)) {%>
							<span id="sortedValue<%=icurrentPage%>-<%=ipageRow%>"><%=result%></span>
<%						} else {%>
							<%=result%>
<%						}%>
							</a>
<%					} else {
							if (column.getColumnName().equals(sortedColumn)) {%>
							<span id="sortedValue<%=icurrentPage%>-<%=ipageRow%>"><%=result%></span>
<%						} else {%>
							<%=result%>
<%						}
						}
						if ((browseName.indexOf("rfq-bidboard-posts") >= 0 && column.getColumnName().equals("RfqHeader_dueDate")) || (browseName.equals("sale-auctions")&& column.getColumnName().equals("SaleHeader_auctionEndDate"))) {%>
							<tsa:hidden name="due_date" value="<%=HiltonUtility.getFormattedDate(originalValue, oid, \"MM-dd-yyyy\")%>"/>
<%					}%>
						</td>
<%				}
					if ((browseName.indexOf("rfq-bidboard-posts") >= 0 && column.getColumnName().equals("RfqHeader_bidDueTime")) || (browseName.equals("sale-auctions")&& column.getColumnName().equals("SaleHeader_auctionEndTime"))) {%>
							<tsa:hidden name="due_time" value="<%=originalValue%>"/>
<%				}
				}%>
					</tr>
					</table>
					<div  id="details<%=icurrentRow%>" style="visibility:hidden;display:none;" class=<%=rowClass%>>
					<table  id="detailRows" border=0 cellspacing=0 cellpadding=0 class=<%=rowClass%> width=95% align=right>
					<tr class=<%=rowClass%>>
<%			int itotalWidth = 0;
				for (int i=0; i < detailList.size(); i++) {
					BrowseColumn column = (BrowseColumn) detailList.get(i);
					int	iwidth = 10;
					if (column.getSize() > 0) {	iwidth = column.getSize();	}
					itotalWidth = itotalWidth + iwidth;

					if (iwidth >= 100 || itotalWidth > 100) {
						if (i > 0) {
%>
					</tr>
					<tr class=<%=rowClass%>>
<%					}%>
						<td colspan=<%=detailList.size()%> height=18px width=<%=iwidth%>% valign=top class=<%=rowClass%>>
						<b><%=column.getLabel()%>:</b><%=column.getValue()%>
						</td>
<%				} else {%>
						<td height=18px  class=<%=rowClass%> width=<%=iwidth%>% valign=top>
<%					if (column.getLink() != null && column.getLink().length() > 0) {%>
							<a href="<%=column.getLink()%>"><%=column.getLabel()%></a>
<%					} else {%>
							<b><%=column.getLabel()%>:</b>&nbsp;<%=column.getValue()%>
<%					}%>
						</td>
<%				}
					if (itotalWidth >= 100 && (i+1) < detailList.size()) {
						itotalWidth = 0;
%>
					</tr>
					<tr class=<%=rowClass%>>
<%				}
				}%>
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

				icurrentRow++;
			}
			inextEnd = icurrentRow + ipageSize;
			if (inextEnd > irows) {
				inextEnd = irows;
			}
			icurrentPage++;
%>
		</table>
		</div>
<%	}%>
		</div>
	</td>
	<td width=5px>&nbsp;</td>
</tr>
<tr>
	<td id="originalFilterFields"><%=sbOriginalFilterFields%></td>
	<td id="currentFilterFields"><%=sbFilterFields%></td>
	<td id="hiddenFields"></td>
	<tsa:hidden name="today" value="<%=s_today%>"/>
</tr>
</table>

<%@ include file="/supplierportal/browse/browse_pageform.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var rowSelect = 0;
	var browser = browserCheck();
	var browseName = "<%=browseName%>";
	var totalRows = <%=irows%>;
	var pageSize = <%=ipageSize%>;
	var pageCount = <%=ipageCount%>;
	var sortedColumn = "<%=sortedColumn%>";
<%	if (sbFilterFields != null && sbFilterFields.length() > 0) {%>
	var filterSet = true;
<%	} else {%>
	var filterSet = false;
<%	}
	if (sbOriginalFilterFields != null && sbOriginalFilterFields.length() > 0) {%>
	var originalFilterSet = true;
<%	} else {%>
	var originalFilterSet = false;
<%	}%>

	setHiddenFields("<%=sbHiddenFields%>");
<%	if (browseName.equals("poheader") || browseName.equals("invoiceheader") || browseName.equals("sale-auctions")) {%>
			hideArea('resetOriginalButton');
<%	} %>

	if (document.getElementById("filterFields") != null && document.getElementById("filterFields") != undefined && document.getElementById("filterFields").length > 0) {
		document.getElementById("filterFields").innerHTML = document.getElementById("currentFilterFields").innerHTML;
		document.getElementById("currentFilterFields").innerHTML = "";
	} else {
		document.getElementById("currentFilterFields").id = "filterFields";
	}

	browseDisplaySetup();
<%	if (browseName.indexOf("rfq-bidboard-posts") >= 0 || browseName.equals("sale-auctions")) {%>
	start();
<%	}%>

	function showDetails(row) {
		displayArea("details" + (row+(pageSize*currentPage)-pageSize));
	}
	function hideDetails(row) {
		hideArea("details" + (row+(pageSize*currentPage)-pageSize));
	}

	function timeRemainingSetup() {
		if (totalRows > 1) {
			for (var i=0; i < totalRows; i++) {
				frm.Input_time[i].style.textAlign = "right";

				var dueDate = frm.due_date[i].value;
				var dueTime = frm.due_time[i].value;

				if ( dueTime.length > 0 ) {
					if (dueTime == "24:00") {
						dueDate = dueDate + "  00:00";
					}
					else {
						dueDate = dueDate + "  " + dueTime;
					}
				} else {
					dueDate = dueDate + "  00:00";
				}
				frm.due_date[i].value = dueDate;
			}
		} else if (totalRows == 1) {
			frm.Input_time.style.textAlign = "right";

			var dueDate = frm.due_date.value;
			var dueTime = frm.due_time.value

			if ( dueTime.length > 0 ) {
				if (dueTime == "24:00") {
					dueDate = dueDate + "  00:00";
				}
				else {
					dueDate = dueDate + "  " + dueTime;
				}
			} else {
				dueDate = dueDate + "  00:00";
			}
			frm.due_date.value = dueDate;
		}
	}

	function start()
	{

		timeRemainingSetup();

		if (totalRows > 1) {
			for(var i = 0; i < totalRows; i++) {
				setup(frm.due_date[i].value, i);
				repeat(i)
			}
		} else if (totalRows == 1) {
			setup(frm.due_date.value, i);
			repeat(i)
		}
	}

	function repeat(row)
	{
			down(row);
			setTimeout("repeat(" + row + ")",1000);
	}

	function setup(day, row)
	{
		if ( (browserCheck() == "NS6") || (browserCheck() == "NS") ) {
			today	= (getDateNS(frm.today.value)).getTime();
			the_day = (getDateNS(day)).getTime();
		}
		else {
			today	= (new Date(frm.today.value)).getTime();
			the_day = (new Date(day)).getTime();
		}

		time = (the_day - today);
		if (totalRows > 1) {
			frm.due_date[row].value=time;
		} else if (totalRows == 1) {
			frm.due_date.value=time;
		}
	}

	function down(row)
	{
		if (totalRows > 1) {
			if (frm.Input_time[row].value != "Closed") {
				frm.due_date[row].value = frm.due_date[row].value - 1000;
				time = frm.due_date[row].value;
				days = (time - (time % 86400000)) / 86400000;
				time = time - (days * 86400000);
				hours = (time - (time % 3600000)) / 3600000;
				time = time - (hours * 3600000);
				mins = (time - (time % 60000)) / 60000;
				time = time - (mins * 60000);
				secs = (time - (time % 1000)) / 1000;
				if(days==1) out = "1 day, ";
				else out = days+" days, ";
				if(hours < 10) out = out+"0";
				out = out+hours+":";
				if(mins < 10) out = out+"0";
				out = out+mins+":";
				if(secs < 10) out = out+"0";
				out = out+secs;
				if(days+hours+mins+secs> 0)
				{
					frm.Input_time[row].value = out;
				}
				else
				{
					frm.Input_time[row].value = "Closed";
				}
			}
		} else if (totalRows == 1) {
			if (frm.Input_time.value != "Closed") {
				frm.due_date.value = frm.due_date.value - 1000;
				time = frm.due_date.value;
				days = (time - (time % 86400000)) / 86400000;
				time = time - (days * 86400000);
				hours = (time - (time % 3600000)) / 3600000;
				time = time - (hours * 3600000);
				mins = (time - (time % 60000)) / 60000;
				time = time - (mins * 60000);
				secs = (time - (time % 1000)) / 1000;
				if(days==1) out = "1 day, ";
				else out = days+" days, ";
				if(hours < 10) out = out+"0";
				out = out+hours+":";
				if(mins < 10) out = out+"0";
				out = out+mins+":";
				if(secs < 10) out = out+"0";
				out = out+secs;
				if(days+hours+mins+secs> 0)
				{
					frm.Input_time.value = out;
				}
				else
				{
					frm.Input_time.value = "Closed";
				}
			}
		}
	}

	function viewRfq(ic) {
		var closed = false;

<%	if (HiltonUtility.ckNull(browseName).indexOf("rfq-bidboard-posts") >= 0) {%>
		if (totalRows > 1) {
			if (frm.Input_time[rowSelect].value == "Closed") {
				closed = true;
			}
		} else if (totalRows == 1) {
			if (frm.Input_time.value == "Closed") {
				closed = true;
			}
		}

		var myCell = document.getElementById("hiddenFields");
		var newInputField = "<input type='hidden' name='RfqHeader_icRfqHeader' value='" + ic + "'>";
		newInputField = newInputField +  "<input type='hidden' name='VendorQuestion_icRfqHeader' value='" + ic + "'>";
		newInputField = newInputField +  "<input type='hidden' name='RfqBid_vendorId' value='<%=user.getVendorId()%>'>";

		myCell.innerHTML = newInputField;

		if (closed) {
			doSubmit('/rfq/rfq_summary_postauction.jsp','RfqRetrieve');
		} else {
			doSubmit('','RfqRetrieve;SetEventPage');
		}
<%	}%>
	}

	function viewSaleItem(icHeader, icLine) {
		var myCell = document.getElementById("hiddenFields");
		var newInputField = "<input type='hidden' name='SaleHeader_icSaleHeader' value='" + icHeader + "'>";

		newInputField = newInputField + "<input type='hidden' name='SaleLine_icSaleHeader' value='" + icHeader + "'>";
		newInputField = newInputField + "<input type='hidden' name='SaleLine_icSaleLine' value='" + icLine + "'>";

		var pg = "/sales/sale_item_summary.jsp";
		if (totalRows > 1) {
			if (frm.Input_time[rowSelect].value == "Closed") {
				pg = "/sales/sale_item_postauction.jsp";
			}
		} else if (totalRows == 1) {
			if (frm.Input_time.value == "Closed") {
				pg = "/sales/sale_item_postauction.jsp";
			}
		}

		myCell.innerHTML = newInputField;
		doSubmit(pg,'RfqRetrieve;RfqLineRetrieveById');
	}

	function createInvoiceFromOrder(ic) {
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";

		myCell.innerHTML = newInputField;
		doSubmit('/invoice/iv_general_info.jsp', 'InvoiceCreateFromOrder');
	}

	function viewInvoice(ic) {
		//frm.viewType.value = "WIZARD";
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField = "<input type='hidden' name='InvoiceHeader_icIvcHeader' value='" + ic + "'>";

		myCell.innerHTML = newInputField;
		doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
	}

// End Hide script -->
</SCRIPT>