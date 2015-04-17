<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_alloc_by_qty = propertiesManager.getProperty("MISC", "ALLOCBYQTY", "N");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_date_format = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
	String	s_catalog_id = (String) request.getAttribute("CatalogItem_id_catalogId");
	String	s_item_number = (String) request.getAttribute("CatalogItem_id_itemNumber");
	String	s_price_brk = HiltonUtility.ckNull((String) request.getAttribute("CatalogItem_priceBrk"));
	String	s_unit_measure = (String) request.getAttribute("CatalogItem_unitMeasure");
	UserRole roleR = UserManager.getInstance().getUserRole(oid,uid);

	String s_break_type = "Q";  //  break type can be "Q":quantity, or "D":date
	if(oid.equalsIgnoreCase("bly07p"))
	{
		 s_break_type = "D";
	}

	List priceBrkList = (List) request.getAttribute("priceBreakList");

	CatalogPriceBrk catPriceBrk = null;
	boolean newBreakLine = false;

	if (priceBrkList == null || priceBrkList.size() <= 0)
	{
		catPriceBrk = new CatalogPriceBrk();
		CatalogPriceBrkPK comp_id = new CatalogPriceBrkPK();
		comp_id.setSequence(new BigDecimal(1));
		catPriceBrk.setComp_id(comp_id);
		catPriceBrk.setStatus("02");
	/*	catPriceBrk.setDateEntered(d_today);
		catPriceBrk.setDateExpires(d_today);
		catPriceBrk.setOwner(uid);	*/
		newBreakLine = true;
	}
	else
	{
		catPriceBrk = (CatalogPriceBrk) priceBrkList.get(0);
		//s_break_type = HiltonUtility.ckNull(catPriceBrk.getBreakType());
System.out.println("break type from db: "+ s_break_type);
	}
	//CatalogPriceBrkPK catPriceBrkPK = catAccount.getComp_id();

	if (s_break_type.length() <= 0)
	{
System.out.println("price_break_exists: "+s_price_brk);
		if ( s_price_brk.equals("Y") )
		{
			//s_break_type = HiltonUtility.ckNull(catPriceBrk.getBreakType());
			//s_break_type = "Q";	//break type: quantity
System.out.println("break typeB: "+ s_break_type);
		}
	}

%>

<tsa:hidden name="CatalogPriceBrk_catalogId" value="<%=s_catalog_id%>"/>
<tsa:hidden name="CatalogPriceBrk_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="CatalogItem_catalogId" value="<%=s_catalog_id%>"/>
<tsa:hidden name="CatalogItem_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="CatalogItem_priceBrk" value=""/>
<tsa:hidden name="formType" value="CAT"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "priceBreakInformation", "Price Break Information")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "item", "Item")%> #:</b></td>
			<td width=200px><%=s_item_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalog", "Catalog")%>:</b></td>
			<td width=200px nowrap><%=s_catalog_id%></td>
		</tr>
		</table>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>

		<br>
		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=465px align=center>
		<tr>
			<td align=center width=465px>
				<table border=0 cellspacing=0 cellpadding=0 width=465px class=browseHdr>
				<!--
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=35% height=18px class=browseHdr align=center>Break According to: </td>
							<td width=5% height=18px class=browseHdr align=right><b><input type=radio name="as_break_type" tabindex=100 value="Q" onclick="doNothing();" <% if (s_break_type.equals("Q")){ %>checked<%}%>></b></td>
							<td width=15% height=18px class=browseHdr align=left><b>Quantity </b></td>
							<td width=5% height=18px class=browseHdr align=right><b><input type=radio name="as_break_type" tabindex=100 value="D" onclick="doNothing();" <% if (s_break_type.equals("D")) {%>checked<%}%>></b></td>
							<td width=40% height=18px class=browseHdr align=left><b>Date </b></td>
						</tr>
						</table>
					</td>
				</tr>
				-->
				<tr>
					<td>
						<table border=0 cellpadding=0 cellspacing=2 width=465px align=center>
						<tr>
							<td>
								<table border=0 cellpadding=0 cellspacing=2 width=465px align=center>
									<tr>
										<td nowrap width=55px>&nbsp;</td>
										<td nowrap width=120px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "from", "From")%></b></td>
										<td nowrap width=130px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "to", "To")%></b></td>
										<td nowrap width=80px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "unitPrice", "Unit Price")%></b></td>
										<td nowrap width=40px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "um", "U/M")%></b></td>
										<td nowrap width=25px>&nbsp;&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan=4>
								<table id="pricebreaklines" name="priceBreaklines" border=0 cellspacing=0 cellpadding=2 width=465px align=center>

<%
		int i_rowcount = 0;

		List priceBreakList = (List) request.getAttribute("priceBreakList");
		if (priceBreakList != null)
		{
			if (s_break_type.equals("Q"))
			{
				for (int i = 0; i < priceBreakList.size(); i++)
				{
					CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk) priceBreakList.get(i);
					CatalogPriceBrkPK catalogPriceBrkPk = (CatalogPriceBrkPK) catalogPriceBrk.getComp_id();
	%>
								<div id="byQuantity">
									<tr>
										<td id=sequence align="right" width="5px"><%=catalogPriceBrk.getComp_id().getSequence()%>.&nbsp;
											<tsa:hidden name="CatalogPriceBrk_sequence" value="<%=catalogPriceBrk.getComp_id().getSequence()%>"/>
											<tsa:hidden name="CatalogPriceBrk_breakType" value="Q"/>
											<tsa:hidden name="CatalogPriceBrk_status" value="02"/>
										</td>
										<td id=note_<%=i%> class=browseRow width=20px valign="bottom"><a href="javascript: viewNotes('<%=catalogPriceBrk.getComp_id().getSequence()%>');" title="Click here to view notes for this price break line."> <img src="<%=contextPath%>/images/notes_line.gif" border="0"></a>&nbsp;<input type="hidden" name="CatalogPriceBrk_breakNote" value="<%=catalogPriceBrk.getBreakNote()%>"></td>
										<td id=breakfrom class=browseRow width=125px><input type=text name="CatalogPriceBrk_qtyFrom" value="<%=HiltonUtility.getFormattedDollar(catalogPriceBrk.getQtyFrom(), oid)%>" size=20 maxlength=15 onchange="this.value=nformat(eval(nfilter(this)), <%=s_quantity_decimals%>);"></td>
										<td id=breakto class=browseRow width=125px><input type=text name="CatalogPriceBrk_qtyTo" value="<%=HiltonUtility.getFormattedDollar(catalogPriceBrk.getQtyTo(), oid)%>" size=20 onchange="this.value=nformat(eval(nfilter(this)), <%=s_quantity_decimals%>);"></td>
										<td id=unitcost class=browseRow width=100px><input type=text name="CatalogPriceBrk_unitPrice" value="<%=HiltonUtility.getFormattedDollar(catalogPriceBrk.getUnitPrice(), oid)%>" size=15 onchange="this.value=nformat(eval(nfilter(this)), <%=s_dollar_decimals%>);"></td>
										<td id=unitmeasure class=browseRow width=50px><input type="text" name="CatalogItem_umCode" size=6 maxlength=15 tabindex=8 value="<%=s_unit_measure%>" onchange="upperCase(this);" disabled></td>
										<td id=delete_<%=i%> class=browseRow width=20px><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a></td>
										</tr>
								</div>

<%
					i_rowcount++;
				}
			}
			if (s_break_type.equals("D"))
			{
				for (int i = 0; i < priceBreakList.size(); i++)
				{
					CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk) priceBreakList.get(i);
					CatalogPriceBrkPK catalogPriceBrkPk = (CatalogPriceBrkPK) catalogPriceBrk.getComp_id();
	%>

								<div id="byDate">
									<tr> <!-- **Browse-Type: Date**  -->
										<td id=note class=browseRow width = 25px><a href="javascript: viewNotes();" title="Click here to view notes for this price break line."> <img src="<%=contextPath%>/images/notes_line.gif" border=0></a>&nbsp;</td>
										<td id=breakfrom class=browseRow width=130px <%=HtmlWriter.isVisible(oid, "pbDateFrom")%> >
											<input type=text title="Enter date price break starts" name="CatalogPriceBrk_dateFrom" tabindex=5 size=15 value="<%=HiltonUtility.getFormattedDate(catalogPriceBrk.getDateFrom(), oid)%>">
											<a href="javascript: show_calendar('CatalogPriceBrk_dateFrom', '<%=s_date_format%>');void();"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
										</td>
										<td id=breakto class=browseRow width=130px <%=HtmlWriter.isVisible(oid, "pbDateTo")%> >
											<input type=text title="Enter date price break ends" name="CatalogPriceBrk_dateTo" tabindex=5 size=15 value="<%=HiltonUtility.getFormattedDate(catalogPriceBrk.getDateTo(), oid)%>">
											<a href="javascript: show_calendar('CatalogPriceBrk_dateTo', '<%=s_date_format%>');void();"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
										</td>
										<td id=unitcost class=browseRow width=80px><input type=text name="CatalogPriceBrk_unitPrice" value="<%=HiltonUtility.getFormattedDollar(catalogPriceBrk.getUnitPrice(), oid)%>" size=15 onchange="formatMe(<%=i%>);"></td>
										<td id=unitmeasure class=browseRow width=40px><input type="text" name="CatalogItem_umCode" size=6 maxlength=15 tabindex=8 value="<%=s_unit_measure%>" onchange="upperCase(this);" disabled></td>
										<td id=delete_<%=i%> class=browseRow width=25px><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
									</tr>
								</div>
<%
					i_rowcount++;
				}
			}
		}
%>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan=4>
								<br>
								<% if(!s_break_type.equalsIgnoreCase("D")) { %>
								<table border=0 cellspacing=0 cellpadding=2 width=450px align=center>
								<tr>
									<td nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></B></font></a></td>
									<td>&nbsp;</td>
									<td align=right nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></font></a>&nbsp;</td>
								</tr>
								</table>
								<% } %>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>

		<br>

			</TD>
		</TR>
		</TABLE>
	</TD>
</tr>
</table>

<br>
<br>

<div id="hiddenFields" style="visibility:hidden; display:none;"></div>

<table border=0 cellpadding=0 cellspacing=0 width=650px>
<tr>
	<td width=50% align=center>
	<% if (roleR.getAccessRights("CATITEM") >= 3) { %>
		<div id="pxbutton"><a href="javascript: if (validatePriceBreaks()) { doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogPriceBrkUpdateByItem;CatalogItemRetrieveById'); } void(0);">Save</a></div>
	<% } %>
	</td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;

	hideArea("navTable");
	displayArea("menuBarSpacer");

	var currentRow = 0;
	var myTable = document.getElementById("pricebreaklines");
	var count = myTable.rows.length;
	var totalRows = myTable.rows.length;
	var maxRows = frm.as_maxrows.value;
	var breakType = "<%=s_break_type%>";

	var rowSelect = 0;

	if (totalRows <= 0)
	{
		addNew();
	}

	function addNew()
	{
		frm.deleteall.value = "FALSE";
		maxRows++;
		myRow = myTable.insertRow(count);

		myCell = createCell(myRow);
		myCell.id = "sequence";
		myCell.width = "5px";
		myCell.align = "right";
		myCell.innerHTML = (count + 1) + ".&nbsp;<input type=hidden name=\"CatalogPriceBrk_sequence\" value=" + (count + 1) + "><input type=hidden name=\"CatalogPriceBrk_breakType\" value=" + breakType + "><input type=hidden name=\"CatalogPriceBrk_status\" value=\"02\">";

	  	myCell = createCell(myRow);
		id = "note";
		myCell.id = id;
		myCell.width = "20px";
		myCell.innerHTML = "<a href=\"javascript: viewNotes('" +(count + 1) + "');\" title=\"Click here to view notes for this price break line.\"> <img src=\"<%=contextPath%>/images/notes_line.gif\" border=0></a>&nbsp;<input type=\"hidden\" name=\"CatalogPriceBrk_breakNote\" value=\"\">";

	  if (breakType == "Q"){
		myCell = createCell(myRow);
		id = "breakfrom";
		myCell.id = id;
		myCell.width = "125px";
		myCell.innerHTML = "<input type=text name=\"CatalogPriceBrk_qtyFrom\" size=20 value=\"0.00\" onchange=\"this.value=nformat(eval(nfilter(this)), <%=s_quantity_decimals%>);\">";

		myCell = createCell(myRow);
		id = "breakto";
		myCell.width = "125px";
		myCell.innerHTML = "<input type=text name=\"CatalogPriceBrk_qtyTo\" size=20 value=\"0.00\" onchange=\"this.value=nformat(eval(nfilter(this)), <%=s_quantity_decimals%>);\">";
	  }
	  else{
		myCell = createCell(myRow);
		id = "breakfrom";
		myCell.id = id;
		myCell.width = "130px";
		myCell.innerHTML = "<input type=text title='Enter date price break starts' name='CatalogPriceBrk_dateFrom' size=15 value='<%=HiltonUtility.getFormattedDate("", oid)%>'>&nbsp;<a href=\"javascript: show_calendar('CatalogPriceBrk_dateFrom', '<%=s_date_format%>');\"><img src=\"<%=contextPath%>/images/calendar.gif\" alt=\"Click here to choose a date or enter a date in the box on the left.\" valign=bottom border=0></a>";

		myCell = createCell(myRow);
		id = "breakto";
		myCell.width = "130px";
		myCell.innerHTML = "<input type=text title='Enter date price break ends' name='CatalogPriceBrk_dateTo' size=15 value='<%=HiltonUtility.getFormattedDate("", oid)%>'>&nbsp;<a href=\"javascript: show_calendar('CatalogPriceBrk_dateTo', '<%=s_date_format%>');\"><img src=\"<%=contextPath%>/images/calendar.gif\" alt=\"Click here to choose a date or enter a date in the box on the left.\" valign=bottom border=0></a>";
	  }

		myCell = createCell(myRow);
		id = "unitcost";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"CatalogPriceBrk_unitPrice\" size=15 value=\"0.00\" onchange=\"this.value=nformat(eval(nfilter(this)), <%=s_dollar_decimals%>);\">";

		myCell = createCell(myRow);
		id = "unitmeasure";
		myCell.id = id;
		myCell.width = "50px";
		myCell.innerHTML = "<input type=text name=\"CatalogItem_umCode\" size=6 value=\"<%=s_unit_measure%>\" disabled>";

		myCell = createCell(myRow);
		id = "delete_" + count;
		myCell.id = id;
		myCell.width = "20px";
		myCell.innerHTML = "<a href=\"javascript: deleteMe(" + count + "); void(0); \"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		currentRow = count;
		count++;
	}

	function deleteMe(row)
	{
		if (confirm("Are you sure you wish to delete this price break amount?"))
		{
			myTable = document.getElementById("pricebreaklines");
			var currentRows = myTable.rows.length;

			if (currentRows == 0)
			{
				return;
			}
			else if (currentRows > 1)
			{
				for (var i = row; i < (currentRows - 1); i++)
				{

					var prbrFrom = frm.CatalogPriceBrk_qtyFrom[i + 1].value;
					var prbrTo = frm.CatalogPriceBrk_qtyTo[i + 1].value;
					var unitPrice = frm.CatalogPriceBrk_unitPrice[i + 1].value;
					var unitMeasure = frm.CatalogItem_umCode[i + 1].value;

					frm.CatalogPriceBrk_qtyFrom[i].value = prbrFrom;
					frm.CatalogPriceBrk_qtyTo[i].value = prbrTo;
					frm.CatalogPriceBrk_unitPrice[i].value = unitPrice;
					frm.CatalogItem_umCode[i].value = unitMeasure;
				}
			}

			myTable.deleteRow(currentRows - 1);

			if (currentRows <= 1)
			{
				frm.deleteall.value = "TRUE";
			}
			count--;

			if (count == 0)
			{
				addNew();
			}
		}
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all Price Break rules for this item?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("pricebreaklines");
			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();
		}
	}

	function viewNotes(sequence)
	{
		var breakNote = "";

		count = myTable.rows.length;
		if (count == 1) {
			if (frm.CatalogPriceBrk_breakNote) {
				breakNote = frm.CatalogPriceBrk_breakNote.value;
			}
		} else {
			if (frm.CatalogPriceBrk_breakNote[sequence - 1]) {
				breakNote = frm.CatalogPriceBrk_breakNote[sequence - 1].value;
			}
		}

		popupParameters = "CatalogPriceBrk_catalogId=" + frm.CatalogItem_catalogId.value + ";CatalogPriceBrk_itemNumber=" + frm.CatalogItem_itemNumber.value + ";CatalogPriceBrk_sequence=" + sequence +
			";CatalogPriceBrk_breakNote=" + breakNote;
		
		doSubmitToPopup('/admin/catalog/cat_item_pricebreak_notes.jsp', 'CatalogPriceBrkRetrieveById', 'width=350px', 'height=200px');
	}

	function validatePriceBreaks()
	{
		myTable = document.getElementById("pricebreaklines");
		var currentRows = myTable.rows.length;
		var bEmpty = true;

		if (currentRows == 1)
		{
			var qtyfrom = frm.CatalogPriceBrk_qtyFrom.value;
			var qtyto = frm.CatalogPriceBrk_qtyTo.value;
			var price = frm.CatalogPriceBrk_unitPrice.value;

			if ( isEmpty(qtyfrom) == false ) bEmpty = false;
			if ( isEmpty(qtyto) == false ) bEmpty = false;
			if ( isEmpty(price) == false ) bEmpty = false;

			if (bEmpty == false)
			{
				if ( isEmpty(qtyfrom)  )
				{
					alert("Please enter a valid FROM quantity!");
					return false;
				}
				if ( isEmpty(qtyto) )
				{
					alert("Please enter a valid TO quantity!");
					return false;
				}
				if ( isEmpty(price) )
				{
					alert("Please enter a valid unit price!");
					return false;
				}
			}
		}
		else if (currentRows > 1)
		{
			for (var i = 0; i < (currentRows - 1); i++)
			{
				var qtyfrom = frm.CatalogPriceBrk_qtyFrom[i].value;
				var qtyto = frm.CatalogPriceBrk_qtyTo[i].value;
				var price = frm.CatalogPriceBrk_unitPrice[i].value;

				if ( isEmpty(qtyfrom) == false ) bEmpty = false;
				if ( isEmpty(qtyto) == false ) bEmpty = false;
				if ( isEmpty(price) == false ) bEmpty = false;

				if (bEmpty == false)
				{
					if ( isEmpty(qtyfrom)  )
					{
						alert("Please enter a valid FROM quantity on line " + (i+1) + "!");
						return false;
					}
					if ( isEmpty(qtyto) )
					{
						alert("Please enter a valid TO quantity on line " + (i+1) + "!");
						return false;
					}
					if ( isEmpty(price) )
					{
						alert("Please enter a valid unit price on line " + (i+1) + "!");
						return false;
					}
				}
			}
		}

		if (bEmpty == true)
		{
			frm.deleteall.value = "TRUE";
			frm.CatalogItem_priceBrk.value = "N";
		}
		else
		{
			frm.CatalogItem_priceBrk.value = "Y";
		}
		return true;
	}


// End Hide script -->
</SCRIPT>
