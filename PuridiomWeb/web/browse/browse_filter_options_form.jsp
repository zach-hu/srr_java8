<script language='Javascript1.2' src="<%=contextPath%>/scripts/date_check.js"></script>

<script type="text/javascript">
	function setTypeNames(typeName) {
		document.getElementById('typeNames').innerHTML += '<input type=hidden name="typeName" value="' + typeName + '">';
	}
</script>
<div id="typeNames"></div>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=center>
		<table id="filterOptions" border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td>Column Name</td>
			<td>Operator</td>
			<td>Filter Expression(*)</td>
			<td>Logical</td>
			<td>&nbsp;</td>
			<td>Sort Option</td>
		</tr>
<%		int pt = 0 ;
		int filterRows = 5;

		if( oid.equalsIgnoreCase("BSC04P"))
		{
			filterRows = 6;
		}
		for (int ii = 0; ii < filterRows; ii++) {
			int columnCount = 0; %>
		<tr>
			<td>
			    <select name="colname" size=1 onchange="javascript: updateFilterStatus(<%=ii%>);">
<%
		    // load values from BrowseObject.browseColumns
			for (int ix=0; ix < browseColumns.length; ix++) {
				BrowseColumn column = browseColumns[ix];

		        if ((column.isHidden() && !column.getAllowFilter().equals("Y")) || column.getType().equalsIgnoreCase("checkbox") || (!column.getAllowFilter().equals("Y") && column.getType().equalsIgnoreCase("Sum"))) {
		            if (pt == ix) pt++ ;
		            continue ;
				}
		        if (pt == ix) {
%>				<option value="<%=column.getColumnName()%>" selected><%=column.getLabel(oid, language)%></option>
<%				} else { %>
					<option value="<%=column.getColumnName()%>"><%=column.getLabel(oid, language)%></option>
<%				}

		        if (ii == 0) {
	            	  %>
	            	  <script type="text/javascript">
	            	  	this.setTypeNames('<%= column.getType() %>');
	            	  </script>
	           <% }

				columnCount++;
			}
			if (ii == 0) {
				if (columnCount < filterRows) {
					filterRows = columnCount;
				}
			}
			pt++ ;
%>
	    		</select>&nbsp;
			</td>
			<td align=right>
				<tsa:hidden name="originalFilter" value="Y"/>
			    <select name="operator" size=1>
					<option value="=">=</option>
					<option value=">">></option>
					<option value="<"><</option>
					<option value=">=">>=</option>
					<option value="<="><=</option>
					<option value="<>"><></option>
			    </select>&nbsp;
			</td>
<%	String defaultValue = "";
		/*
			this if statement added for QRI on 09.11.06
			they want the user's locale to default for purchase orders and receipts
		*/
		if ( (ii == 0) && (oid.equalsIgnoreCase("qri06p")) )
		{
			if (/*browseObject.getBrowseName().indexOf("poheader") >= 0 || */ browseObject.getBrowseName().equals("receipt-order-pkg") || /*browseObject.getBrowseName().equals("rfq_createfrom_requisitionheader") || browseObject.getBrowseName().equals("receipt-return-order") ||*/ browseObject.getBrowseName().indexOf("receipt-by-item") >= 0)
			defaultValue = user.getLocale();
		}	%>
			<td>
		        <%--input name="filter_txt" size=30 type="text" value="<%=defaultValue%>" onChange="correctMe(<%=ii%>);"--%>
		        <div id="defaultOption<%=ii%>" style="float: left; padding-top: 0px;"><input name="filter_txt" size=30 type=text value="<%=defaultValue%>" onChange="correctMe(<%=ii%>);" onClick="javascript:reviewField(<%= ii %>);"></div>
        		<div id="typeOption<%=ii%>" style="visibility:hidden; display:none; float: left;"><input name="filterLabel_txt" size=30 type=text value="<%= defaultValue %>" onClick="javascript:reviewField(<%= ii %>);" readonly></div>
		    </td>
			<td>
<%		if (ii < (filterRows -1)) {%>
			    <select name="logicalOperator" size=1>
					<option value=""> </option>
					<option value="AND">AND</option>
					<option value="OR">OR</option>
			    </select>
<%		} else {%><!-- The last logical operator is not used --><tsa:hidden name="logicalOperator" value=""/>
<%		}%>
				&nbsp;
			</td>
			<td>&nbsp;</td>
			<td>
			    <select name="sort" size=1>
					<option value="N"> </option>
					<option value="ASC">Ascending</option>
					<option value="DESC">Descending</option>
			    </select>&nbsp;
			</td>
		</tr>
		<%}%>
		<tr>
			<td colspan=6 align=center>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
					<td align=center CLASS="small">(*)Enter Dates in <%=userDateFormat.toUpperCase()%>.
					<br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browse-filterWarning", "")%>
					</td>
				</tr>
				<% if ((browseObject.getBrowseName()).indexOf("admin-userlog") >= 0) { %>
				<tr>
					<td align=center CLASS="small">(*)Enter Time in <%=PropertiesManager.getInstance(oid).getProperty("MISC", "TimeFormat", "HH:mm:ss").toUpperCase()%>.</td>
				</tr>
				<% } %>
				<tr><td><br></td></tr>
				<tr>
					<td>
						<% if ((browseObject.getTitle()).indexOf("Report") < 0 &&
							(browseObject.getBrowseName()).indexOf("print-check-invoices") < 0 && (browseObject.getBrowseName()).indexOf("invoice-exported-reset") < 0 )
						{ %>
							<table border=0 cellpadding=1 cellspacing=0>
							<tr>
								<td align=right>Display</td>
								<td>
									<select name="pageSize">
										<option value="1" <% if (browseObject.getPageSize() == 1) {%>selected<%}%>>1</option>
										<option value="5" <% if (browseObject.getPageSize() == 5) {%>selected<%}%>>5</option>
										<option value="10" <% if (browseObject.getPageSize() == 10) {%>selected<%}%>>10</option>
										<option value="15" <% if (browseObject.getPageSize() == 15) {%>selected<%}%>>15</option>
										<option value="20" <% if (browseObject.getPageSize() == 20) {%>selected<%}%>>20</option>
										<option value="25" <% if (browseObject.getPageSize() == 25) {%>selected<%}%>>25</option>
										<option value="50" <% if (browseObject.getPageSize() == 50) {%>selected<%}%>>50</option>
									</select>
								</td>
								<td>Results Per Page</td>
							</tr>
							</table>

							<br>
						<%}%>
						<table border=0 cellpadding=0 cellspacing=0 width=100%>
						<tr>
				<% if ((browseObject.getTitle()).indexOf("Report") >= 0) { %>
							<td align=center>
								<table border=0 cellpadding=0 cellspacing=0 width=100%>
								<tr>
									<td align="center">
										<div id="pxbutton"><a href="javascript: generateReport(); void(0);">Print</a></div>
										<tsa:hidden name="reportName" value="<%=browseObject.getBrowseName()%>"/>
									</td>
									<td align="center">
										<div id="pxbutton"><a href="javascript: returnToList(); void(0);">Return</a></div>
									</td>
								</tr>
								</table>
							</td>
				<% } else if (browseObject.getBrowseName().equals("catalogitem-admin")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); browseItems(); void(0);">Browse Items</a></div>
							</td>
							<% if (role.getAccessRights("CATITEM") >= 3 ) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: addCatalogItem(); void(0);">Add Item</a></div>
							</td>
							<% } %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: cancelMe(); void(0);">Return</a></div>
							</td>
				<% } else if (browseObject.getBrowseName().equals("department-admin") || browseObject.getBrowseName().equals("taxcode-admin")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); browseSystemTable('<%=browseObject.getBrowseName()%>');  void(0);">Browse Items</a></div>
							</td>
				<% } else if (browseObject.getBrowseName().equals("stdtable-admin")) { %>
							<td align=center>
								<%String code = HiltonUtility.ckNull((String) request.getAttribute("tableType"));%>
								<tsa:hidden name="tableType" value="<%=code%>"/>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); browseStdTable('<%=code%>'); void(0);">Browse Items</a></div>
							</td>
				<%	} else if (browseObject.getBrowseName().equals("catalog")) { 	%>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); browse('<%=browseObject.getBrowseName()%>'); void(0);">Browse</a></div>
							</td>
							<% if (role.getAccessRights("CAT") >= 3 ) { %>
							<td width=50% align=center>
								<div id="pxbutton"><a href="javascript: addCatalog(); void(0);">Add</a></div>
							</td>
							<% } %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
							</td>
				<%	} else if (browseObject.getBrowseName().equals("supplier_mgt")) { 	%>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); setSupplierBrowse(); void(0);">Browse</a></div>
							</td>
						<%	if ((role.getAccessRights("VEND") > 2)) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: addSupplier(); void(0);">Add</a></div>
							</td>
						<%	} %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: returnAdmin(); void(0);">Return</a></div>
							</td>
				<%	} else if (browseObject.getBrowseName().equals("supplierorders")) { %>
						<%	if ( oid.equalsIgnoreCase("MSG07P")) {
								String fromMain = HiltonUtility.ckNull((String) request.getAttribute("fromMainMenu"));
				   		%>
						<td align=center>
							<div id="pxbutton"><a href="javascript: viewBrowseVendor(); void(0);">Browse</a></div>
							<tsa:hidden name="fromMainMenu" value="<%=fromMain%>"/>
						</td>
						<td align=center>
							<div id="pxbutton"><a href="javascript: setBrowseName(); resetNavCookie(12); doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById');">Browse</a></div>
						</td>
						<%	} else { %>
						<td align=center>
							<div id="pxbutton"><a href="javascript: setFilterOptions(); setOwnerFilter(); void(0);">Browse</a></div>
						</td>
						<td align=center>
							<div id="pxbutton"><a href="javascript: setBrowseName(); resetNavCookie(12); doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById');">Return</a></div>
						</td>
						<%  } %>
				<%	} else if (browseObject.getBrowseName().equals("print-check-invoices")) { %>
						<td align=center>
							<div id="pxbutton"><a href="javascript: viewBrowseCheckPrinting(); void(0);">Browse</a></div>
						</td>
				<%	} else if (browseObject.getBrowseName().equals("myopenreqs")) { 	%>
							<td align=center>
								<div id="pxbutton"><a href="javascript: viewMyOpenReqs(); void(0);">Browse</a></div>
							</td>
				<% } else if ( browseObject.getBrowseName().equals("requisitionline-account") || browseObject.getBrowseName().equals("poline-account") ) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); setFiscalYearFilter(); browse('<%=browseObject.getBrowseName()%>'); void(0);">Browse</a></div>
							</td>
				<% } else if (browseObject.getBrowseName().equals("vendor-udf1")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); setSupplierBrowseUdf1(); void(0);">Browse</a></div>
							</td>
				<% } else if ( browseObject.getBrowseName().equals("receiptheader")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); searchReceiptHeaderWitPRO(); void(0);">Browse</a></div>
							</td>
				<% } else if ( browseObject.getBrowseName().equals("receipt-package")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); searchPackageWitPRO(); void(0);">Browse</a></div>
							</td>
				<%  } else { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: setFilterOptions(); setOwnerFilter(); void(0);">Browse</a></div>
							</td>
				<%  } if ((browseObject.getBrowseName()).equals("admin-userprofile")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: addUserProfile(); void(0);">Add User</a></div>
							</td>
							<td align=center>
								<div id="pxbutton"><a href="javascript: returnAdmin(); void(0);">Return</a></div>
							</td>
				<% } else if ((browseObject.getBrowseName()).equals("admin-securitygroup")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: addSecurityGroup(); void(0);">Add</a></div>
							</td>
							<td align=center>
								<div id="pxbutton"><a href="javascript: returnAdmin(); void(0);">Return</a></div>
							</td>
				<% } else if (browseObject.getBrowseName().equals("labels")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: addLabels(); void(0);">Add</a></div>
							</td>
							<td align=center>
								<div id="pxbutton"><a href="javascript: returnAdmin(); void(0);">Return</a></div>
							</td>
				<% } else if ((browseObject.getBrowseName()).equals("app-udf1")) {
						String fromMain = HiltonUtility.ckNull((String) request.getAttribute("fromMainMenu"));

						    if (fromMain.equals("true")) { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
								<tsa:hidden name="fromMainMenu" value="<%=fromMain%>"/>
							</td>
				<%		    } else { %>
							<td align=center>
								<div id="pxbutton"><a href="javascript: returnAdmin(); void(0);">Return</a></div>
								<tsa:hidden name="fromMainMenu" value="<%=fromMain%>"/>
							</td>
				<%	  		}
					 }		    %>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<iframe id="typesFrame" name="typesFrame" src="javascript: void(0);" frameborder="0" marginheight="0" marginwidth="0" style="position: absolute; border: 1px solid #C0C0C0; display: none;"></iframe>


<script value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var currentTypeOptions = '';
	var currentIndex = '';
	var currentColumnValue = '';
	var filterStatus = new Array();

	var bReset = true;
	var filterFields = "";

	<% if ( browseObject.getBrowseName().indexOf("admin") >= 0 ||
		browseObject.getBrowseName().equals("approver") ||
		browseObject.getBrowseName().equals("supplier_mgt") ||
		browseObject.getBrowseName().equals("vendor-udf1") ||
		browseObject.getBrowseName().equals("supplierorders") ||
		browseObject.getBrowseName().equals("catalog") ||
		browseObject.getBrowseName().equals("app-udf1") ||
		browseObject.getBrowseName().equals("budget_center") ||
		browseObject.getBrowseName().indexOf("invoicecreate") >= 0) { %>
            bReset = false;
	<% }%>

	<% if ( (browseObject.getTitle()).indexOf("Report") < 0 ){	%>
			setNavCookie("/browse/browse_filter_options.jsp", "BrowseGetOptions", "<%=browseObject.getTitle()%> Options", bReset);
			document.cookie = "browseName=" + "<%=HiltonUtility.ckNull((String) request.getAttribute("browseName"))%>";
	<% }%>
	function isType(typeValue) {
		return (typeValue.indexOf('-TYPE') >= 0) || (typeValue == 'STATUS') || (typeValue == 'DATE') || (typeValue == 'GENERALSTATUS') || (typeValue == 'TIMEZONE');
	}

	function updateFilterStatus(ix) {
		var typeValue = frm.typeName[frm.colname[ix].selectedIndex].value;

		setDefaultVisibility(ix);

		if (isType(typeValue)) {
			filterStatus[ix] = 'IP';
		} else {
			filterStatus[ix] = 'NR';
		}
	}

	function thisLoad() {
		f_StartIt();

		document.onkeypress=enterKeyHandler;

		if (frm.action.value == 'old') {
			for (var i = 0; i < frm.colname.length; i++) {
				if (isType(frm.typeName[frm.colname[i].selectedIndex].value)) {
					if (!isEmpty(frm.filterLabel_txt[i].value)) {
						displayArea('typeOption' + i);
						hideArea('defaultOption' + i);
						filterStatus[i] = 'IP';
					} else if (!isEmpty(frm.filter_txt[i].value)) {
						filterStatus[i] = 'OP';
					}
				}
			}
		}

	}

	function enterKeyHandler(evt) {
  		var evt = (evt) ? evt : ((event) ? event : null);
  		if ((evt.keyCode == 13))  {
  			<% if ((browseObject.getTitle()).indexOf("Report") >= 0) { %>
				generateReport(); void(0);
			<% } else if (browseObject.getBrowseName().equals("catalogitem-admin")) { %>
				setFilterOptions(); browseItems(); void(0);
			<% } else if (browseObject.getBrowseName().equals("department-admin") || browseObject.getBrowseName().equals("taxcode-admin")) { %>
				setFilterOptions(); browseSystemTable('<%=browseObject.getBrowseName()%>'); void(0);
			<% } else if (browseObject.getBrowseName().equals("stdtable-admin")) { %>
				<%String code = HiltonUtility.ckNull((String) request.getAttribute("tableType"));%>
				setFilterOptions(); browseStdTable('<%=code%>'); void(0);
			<%	} else if (browseObject.getBrowseName().equals("catalog")) { 	%>
				setFilterOptions(); browse('<%=browseObject.getBrowseName()%>'); void(0);
			<%	} else if (browseObject.getBrowseName().equals("supplier_mgt")) { 	%>
				setFilterOptions(); setSupplierBrowse(); void(0);
			<%	} else if (browseObject.getBrowseName().equals("supplierorders")) { %>
				<%	if ( oid.equalsIgnoreCase("MSG07P")) { %>
					viewBrowseVendor(); void(0);
				<%	} else { %>
					setFilterOptions(); setOwnerFilter(); void(0);
				<% } %>
			<%	} else if (browseObject.getBrowseName().equals("print-check-invoices")) { %>
				viewBrowseCheckPrinting(); void(0);
			<%	} else if (browseObject.getBrowseName().equals("myopenreqs")) { 	%>
				viewMyOpenReqs(); void(0);
			<% } else if ( browseObject.getBrowseName().equals("requisitionline-account") || browseObject.getBrowseName().equals("poline-account") ) { %>
				setFilterOptions(); setFiscalYearFilter(); browse('<%=browseObject.getBrowseName()%>'); void(0);
			<% } else if (browseObject.getBrowseName().equals("vendor-udf1")) { %>
				setFilterOptions(); setSupplierBrowseUdf1(); void(0);
			<%  } else { %>
				setFilterOptions(); setOwnerFilter(); void(0);
			<% } %>
		}
	}

	function reviewField(ix) {
		var colName = frm.colname[ix];
		var	columnValue = colName.value;
		var	typeValue = frm.typeName[colName.selectedIndex].value;

		if (filterStatus[ix] == 'IP' || (isType(typeValue) && filterStatus[ix] == null)) {

			if (currentTypeOptions != typeValue || currentColumnValue != columnValue) {
				loadTypesFrame(typeValue, columnValue);
			} else {
				self.typesFrame.unloadValue();
			}
			displayArea('typeOption' + ix);
			hideArea('defaultOption' + ix);
			showTypesFrame(ix);
		} else {
			hideArea('typesFrame');
		}
	}

	function loadTypesFrame(type, columnValue) {
		if (!isEmpty(type)) {
			var typeClass = type;
			popupUrl = '/browse/type_options.jsp';
			popupHandler = 'TypesListRetrieve';
			popupUserId = '${esapi:encodeForJavaScript(userid)}';
			popupMailId = '<%=HiltonUtility.encode(request.getParameter("mailId").toString()) %>';
			popupOrganizationId = '<%= oid %>';
			currentTypeOptions = type;
			currentColumnValue = columnValue;

			if (typeClass.indexOf('-TYPE') > 0) {
				typeClass = 'TYPE';
			}

			popupParameters = 'type=' + type + ';typeClass=' + typeClass + ';columnValue=' + columnValue;
			document.getElementById('typesFrame').src = '<%= contextPath %>/system/iframe_html.jsp';
		}
	}

	function showTypesFrame(ix) {
		var inputBox = frm.filterLabel_txt[ix];
		var typesFrame = document.getElementById('typesFrame');
		currentIndex = ix;

		typesFrame.style.left = componentPosition_getPageOffsetLeft(inputBox) + 'px';
        typesFrame.style.top = componentPosition_getPageOffsetTop(inputBox) + 'px';
        displayArea('typesFrame');
	}

	function hideTypesFrame() {
		//filterStatus[currentIndex] = 'OP';
		setDefaultVisibility(currentIndex);
		frm.filter_txt[currentIndex].focus();
	}

	function assignValue(optionValue, optionText) {
		frm.filter_txt[currentIndex].value = optionValue;
        frm.filterLabel_txt[currentIndex].value = optionText;
		hideArea('typesFrame');
	}

	function setDefaultVisibility(ix) {
		hideArea('typesFrame');
		displayArea('defaultOption' + ix);
		hideArea('typeOption' + ix);
		frm.filter_txt[ix].value = '';
		frm.filterLabel_txt[ix].value = '';
	}

	function correctMe ( ix ) {

		frmcolname = selectValue(frm.colname[ix]);
		frmFilter = frm.filter_txt[ix];

		if ( frmcolname.indexOf("rqh_requisition_number") >= 0 || frmcolname.indexOf("poh_po_number") >= 0 || frmcolname.indexOf("rfh_rfq_number") >= 0) {
			if (frm.filter_txt[ix].value.indexOf("%") < 0) {
				nfilter( frmFilter );
				frmFilter = frm.filter_txt[ix].value;
				var r = "000000";

				if ( (frmFilter.length < 6) && (frmFilter.length > 0)) {
					frm.filter_txt[ix].value = r.substr(frmFilter.length) + frmFilter;
				}
			}
		}
		else if ( frmcolname.indexOf("rqh_requisition_status") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rqh_fiscal_year") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rqh_total") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rqh_estimated_cost") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rqh_requisition_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rqh_date_entered") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rqd_required_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rfh_rfq_status") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_fiscal_year") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_total") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_estimated_cost") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_rfq_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rfh_date_entered") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rfh_due_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("poh_status") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("poh_fiscal_year") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("poh_total") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("poh_po_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("poh_date_entered") >= 0 )
			checkDate( frmFilter );
		else
			frm.filter_txt[ix].value = frmFilter.value.toUpperCase();
	}

	function selectValue ( lstObject ) {
		var list = lstObject;
		var frmcolname = list.options[list.selectedIndex].value;
		return frmcolname;
	}

	function setFilterOptions() {
		var myTable = document.getElementById("filterOptions");
		var previousLogicalOperator = "AND";
		var tempFilterFields = "";

		for (var i=0; i < myTable.rows.length - 2; i++) {
			var filterColumn = frm.colname[i].value;
			var filterTxt = trim(frm.filter_txt[i]);
			var sort = frm.sort[i].value;

			if (!isEmpty(filterTxt) || sort != "N") {
				var operator = frm.operator[i].value;
				var logicalOperator = frm.logicalOperator[i].value;

				if (!isEmpty(filterTxt) && frm.typeName[frm.colname[i].selectedIndex].value == "DATE" && !chkdate(frm.filter_txt[i]) && frm.filter_txt[i].value.indexOf(":") < 0) {
					alert("Enter dates in <%=userDateFormat.toUpperCase()%> format.");
					return false;
				}

				if (filterTxt.indexOf("%") >= 0 || filterColumn == "CatalogItem_description") {
					if (operator == "<>") {
						frm.operator[i][frm.operator[i].selectedIndex].value = "NOT LIKE";
					} else {
						frm.operator[i][frm.operator[i].selectedIndex].value = "LIKE";
					}
				}

				tempFilterFields = tempFilterFields + "<input type=hidden name=\"" + filterColumn + "\" value=\"" + filterTxt + "\">";
				tempFilterFields = tempFilterFields + "<input type=hidden name=\"" + filterColumn + "_operator\" value=\"" + operator + "\">";
				tempFilterFields = tempFilterFields + "<input type=hidden name=\"" + filterColumn + "_logic\" value=\"" + previousLogicalOperator + "\">";
				tempFilterFields = tempFilterFields + "<input type=hidden name=\"" + filterColumn + "_original\" value=\"Y\">";
				tempFilterFields = tempFilterFields + "<input type=hidden name=\"" + filterColumn + "_sort\" value=\"" + sort + "\">";

				previousLogicalOperator = logicalOperator;

			}
		}

		<%if (browseObject.getBrowseName().indexOf("receipt")>=0 && oid.equalsIgnoreCase("hoy08p") && role.getAccessRights("RCVBYITEM") > 1 && role.getAccessRights("RCVBYITEM") < 99) { %>
			setOriginalFilter("PoHeader_departmentCode", "=", "<%=user.getDepartment()%>");
		<% }%>

		<%if (browseObject.getBrowseName().equals("supplierorders")) { %>
			setOriginalFilter("PoHeader_vendorId", "=", "<%=sVendor_Id%>");
		<% }%>

		filterFields = filterFields + tempFilterFields;

		return filterFields;
	}

	function addSupplier(){
		 var newInputField = "<input type='hidden' name='save_suplier' value='true'>";
		 doSubmit('admin/supplier/supplier_info.jsp', 'DoNothing')
	}

	function addUserProfile(){
		<% if (oid.equalsIgnoreCase("vse06p") && oid.equalsIgnoreCase("qri06p") ){ %>
		doSubmit('admin/user/user_profile_add.jsp', 'UserProfileValidateRetrieve')
		<% } else { %>
		doSubmit('admin/user/user_profile_add.jsp', 'UserProfileCreate');
		<% } %>
	}

	function addSecurityGroup(){
		doSubmit('admin/user/user_group_profile.jsp', 'DoNothing')
	}

	function browseItems()
	{
<%	String catId = HiltonUtility.ckNull((String) request.getAttribute("Catalog_catalogId"));%>
		var newInputField = "<input type='hidden' name='Catalog_catalogId' value='<%=catId%>'>";
		setHiddenFields(newInputField);
		setOriginalFilter("CatalogItem_id_catalogId", "=", "<%=catId%>");
		browse('catalogitem-admin');
	}

	function generateReport()
	{
		var filterFields = setFilterOptions();
		frm.browseName.value = '<%=browseObject.getBrowseName()%>';
		if(frm.browseName.value == 'requisition-line')
		{
			setOriginalFilter("RequisitionHeader_owner", "=", "${esapi:encodeForJavaScript(userId)}");
		}
		doSubmit('reports/report_confirmation.jsp', 'ReportSave');
	}
	/* please do not delete */
	/*function generateReport()
	{
		var filterFields = setFilterOptions();
		frm.browseName.value = '<%=browseObject.getBrowseName()%>';
		popupParameters = "browseName=<%=browseObject.getBrowseName()%>";
		popupParameters = popupParameters + ";reportName=<%=browseObject.getBrowseName()%>";

		if(frm.browseName.value == 'requisition-line')
		{
			//setOriginalFilter("RequisitionHeader_owner", "=", "${esapi:encodeForJavaScript(userId)}");
			popupParameters = popupParameters + ";colname=RequisitionHeader_owner;operator==;filter_txt=${esapi:encodeForJavaScript(userId)};logicalOperator=AND;originalFilter=Y;sort=N;"
		}
		//doSubmit('', 'ReportExecute');
		doSubmitToPopup('', 'ReportExecute');
	}*/

	function browseStdTable(code)
	{
		frm.tableType.value = code;
		setOriginalFilter("StdTable_id_tableType", "=", code);
		frm.browseName.value = 'stdtable-admin';
		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function browseSystemTable(x)
	{
		frm.browseName.value = x;
		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function addCatalog()
	{
		var catId = "<%=catId%>";
		var newInputField = "<input type='hidden' name='CatalogItem_catalogId' value='" + catId + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/catalog/catalog.jsp', 'DoNothing');
	}

	function addCatalogItem()
	{
		var catId = "<%=catId%>";
		var newInputField = "<input type='hidden' name='Catalog_catalogId' value='" + catId + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemCreate');
	}

	function addLabels(){
		doSubmit('/admin/labels/labels.jsp', 'LabelsRetrieveAll');
	}

	function cancelMe()
	{
		var catId = "<%=catId%>";
		var newInputField = "<input type='hidden' name='CatalogItem_catalogId' value='" + catId + "'>";
		setHiddenFields(newInputField);
		viewCatalog(catId);
	}

	function viewCatalog(catalog)
	{
		var myCell = document.getElementById("hiddenFields");
		var fromPage = frm.OriginalFromPage.value;
		myCell.innerHTML = "";
		var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"Catalog_catalogId\" value=\"" + catalog + "\" ><INPUT TYPE=\"HIDDEN\" NAME=\"CatalogItem_catalogId\" value=\"" + catalog + "\" >";
		myCell.innerHTML = newInputField;
		if(fromPage.indexOf("main_menu.jsp") > 0) {
			doSubmit('/menu/main_menu.jsp', 'DoNothing');
		}
		else
		{
			doSubmit('/admin/catalog/catalog.jsp', 'CatalogRetrieveById;CatalogItemRetrieveCountByCatalog');
		}
	}

	function setOwnerFilter()
	{
<% if (browseObject.getBrowseName().equals("print-check-invoices")) {  %>
		if (!isCriteriaEntered()) {
			alert("Please specify at least one search criteria.");
			return false;
		}
<%  }  %>
<%	if ( !propertiesManager.getProperty("MISC","OwnerOnly","Y").equalsIgnoreCase("N") )
		{
			if (role.getAccessRights("REQBROWSE") < 99 || propertiesManager.getProperty("MISC","OwnerOnly","Y").equalsIgnoreCase("Y"))
			{
				if (role.getAccessRights("REQBROWSE") > 2)		/*  maintain/create access  */
				{
					String operator = "=";
					String department = user.getDepartment();
					String locale = user.getLocale();
					boolean isBuyer = user.isABuyer();
					boolean isAnAdminBuyer = user.isAnAdminBuyer();

					if ( oid.equalsIgnoreCase("vse06p") && !HiltonUtility.isEmpty(department) )
					{
						if ( department.length() > 4)
						{
							operator = "LIKE";
							department = department.substring(0, 4);
							department = department + "%";
						}
					}

					String applyLocaleFilter = propertiesManager.getProperty("BROWSE", "APPLYLOCALEFILTER", "N");
					if (browseObject.getBrowseName().equals("requisitionheader")) { %>
						<%	if (role.getAccessRights("REQBROWSE") < 99) { %>
							setOriginalFilter("RequisitionHeader_departmentCode", "<%=operator%>", "<%=department%>");
						<%	} %>
<%					} else if (browseObject.getBrowseName().equals("rfqheader")) { %>
						<%	if (!isAnAdminBuyer) { %>
							<%	if (!isBuyer) { %>
								setOriginalFilter("RfqHeader_departmentCode", "<%=operator%>", "<%=department%>");
							<%	} %>
							<%	if (applyLocaleFilter.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(locale)) { %>
								setOriginalFilter("RfqHeader_udf1Code", "<%=operator%>", "<%=locale%>");
							<%	} %>
						<%	} %>
<%					} else if (browseObject.getBrowseName().equals("poheader"))	{	%>
						<%	if (!isAnAdminBuyer) { %>
							<%	if (!isBuyer) { %>
								setOriginalFilter("PoHeader_departmentCode", "<%=operator%>", "<%=department%>");
							<%	} %>
							<%	if (applyLocaleFilter.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(locale)) { %>
								setOriginalFilter("PoHeader_udf1Code", "<%=operator%>", "<%=locale%>");
							<%	} %>
						<%	} %>
<%					}
				}
				else		/*  view access only  */
				{
					if (browseObject.getBrowseName().equals("requisitionheader")) {	%>
							browse("requisitionheader_byowner");
							return;
<%				} else if (browseObject.getBrowseName().equals("rfqheader")) {	%>
							browse("rfqheader_byowner");
							return;
<%				} else if (browseObject.getBrowseName().equals("poheader"))	 {	%>
						browse("poheader_byowner");
						return;
<%				}
				}
			}
		}	%>

		<%
		if (propertiesManager.getProperty("REC DEFAULTS","RECEIVERNOTEQUALREQUISITIONER","N").equalsIgnoreCase("Y") && browseObject.getBrowseName().startsWith("receipt-by-item-"))	{	%>
			setOriginalFilter("PoHeader_requisitionerCode", "<%= "<>" %>", "${esapi:encodeForJavaScript(userId)}");
		<% } %>

		browse('<%=browseObject.getBrowseName()%>');
	}

	function setSupplierBrowse()
	{
		if (filterFields.indexOf("_commodity") > 0)
		{
			browse("supplier_mgt_commodity");
		}
		else
		{
			browse("supplier_mgt");
		}
	}

	function setSupplierBrowseUdf1()
	{
		browse("vendor-udf1");
	}

	function viewMyOpenReqs()
	{
		var userId = "${esapi:encodeForJavaScript(userId)}";
		var status = "<%=DocumentStatus.REQ_APPROVED%>";
		setFilter("RequisitionHeader_status", "<", status);
		//setFilter("RequisitionHeader_requisitionerCode", "=", userId);
    	//setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
    	browse("myopenreqs");
	}

	function setFiscalYearFilter()
	{
		if ( filterFields.length <= 0 )
		{
<%		String fybegin = propertiesManager.getProperty("Misc", "fybegin", "1");
			String fiscalyear = Dates.getFiscalYear(Integer.parseInt(fybegin), userTimeZone);
			if ( browseObject.getBrowseName().equals("requisitionline-account") ) {	%>
				setOriginalFilter("RequisitionHeader_fiscalYear", "=", "<%=fiscalyear%>");
<%		} else if ( browseObject.getBrowseName().equals("poline-account") ) {	%>
				setOriginalFilter("PoHeader_fiscalYear", "=", "<%=fiscalyear%>");
<%		}	%>
		}
	}

	function resizeTypeOptions(optionsSize) {
		document.getElementById('typesFrame').style.height = (optionsSize + 4) + 'em';
	}

	function componentPosition_getPageOffsetLeft(el){
	    var ol=el.offsetLeft;
	    while((el=el.offsetParent) != null){
	        ol += el.offsetLeft;
	    }
	    return ol;
	}

	function componentPosition_getPageOffsetTop(el){
	    var ot=el.offsetTop;
	    while((el=el.offsetParent) != null){
	        ot += el.offsetTop;
	    }
	    return ot;
	}

	function viewBrowseVendor() {
		var newInputField = "<input type='hidden' name='PurchaseHistory_vendorId' value='${esapi:encodeForJavaScript(Vendor_vendorId)}'>";
		setHiddenFields(newInputField);
	    frm.browseName.value = 'supplierorders';
		doSubmit('browse/browse.jsp', 'BrowseRetrieve;VendorSummaryByOrder');
	}

	function viewBrowseCheckPrinting() {
		if (!isCriteriaEntered()) {
			alert("Please specify at least one search criteria.");
			return false;
		}
	    frm.browseName.value = 'print-check-invoices';
		doSubmit('browse/browse_check_printing.jsp', 'BrowseRetrieve');
	}

	function isCriteriaEntered()
	{
		var myTable = document.getElementById("filterOptions");

		for (var i=0; i < myTable.rows.length - 2; i++)
		{
			var filterTxt = trim(frm.filter_txt[i]);

			if (!isEmpty(filterTxt))
			{
				return true;
			}
		}
		return false;
	}

	function returnAdmin() {
		var fromPage = frm.OriginalFromPage.value;

		if (!isEmpty(fromPage)) {
			if (fromPage == '/browse/browse.jsp') {
				doSubmit('menu/main_menu.jsp', 'DoNothing')
			} else {
				doSubmit(fromPage, 'DoNothing')
			}
		} else {
			doSubmit('admin/admin_menu.jsp', 'DoNothing')
		}
	}

	function searchPackageWitPRO()
	{
		if (filterFields.indexOf("_trackingNumber") > 0)
		{
			browse("receipt-package-pro");
		}
		else
		{
			browse("receipt-package");
		}
	}

	function searchReceiptHeaderWitPRO()
	{
		if (filterFields.indexOf("_trackingNumber") > 0)
		{
			browse("receiptheader-pro");
		}
		else
		{
			browse("receiptheader");
		}
	}

// End Hide script -->
</script>
