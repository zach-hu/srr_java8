<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>
<%
	String s_requisitionType = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String	s_item_type = (String) request.getAttribute("itemType");

	List itemList = null;

	if (s_item_type.equalsIgnoreCase("INV"))
	{
		itemList = (List) request.getAttribute("invItemList");
	}
	else
	{
		itemList = (List) request.getAttribute("catalogItemList");
	}

	int listSize = 0;
	if (itemList != null)
	{
		listSize = itemList.size();
	}
%>

<script language="JavaScript1.2">
<!--
	document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/scripts/dynamicTables.js' type='text/javascript'><\/scr" + "ipt>");
//-->
</script>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_requisitionType%>"/>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "itemSelection", "Item Selection")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
		<tr>
			<td width=50%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "keywords", "Keyword(s)")%>:</b>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printer", "printer")%>&nbsp;</td>
			<td width=50% align=right>
				<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sortby", "Sort By")%>:</b>&nbsp;
				<SELECT NAME="sortcolumn" SIZE="1">
				<OPTION value="catalog_items.catalog_id" SELECTED><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogId", "Catalog Id")%></OPTION>
				<OPTION value="catalog_items.max_req_qty"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "itemNumber", "Item Number")%></OPTION>
				<OPTION value="catalog_items.min_req_qty"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "description", "Description")%></OPTION>
				<OPTION value="catalog_items.image_file"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "commodity", "Commodity")%></OPTION>
				<OPTION value="compute.break_count"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "breakCount", "Break Count")%></OPTION>
				<OPTION value="catalog_items.mfg_name"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "manufacturer", "Manufacturer")%></OPTION>
				<OPTION value="catalog_items.model_number"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "modelNumber", "Model Number")%></OPTION>
				</SELECT>
			</td>
		</tr>
		<tr>
			<td colspan=2 height=3px><img src="<%=contextPath%>/images/none.gif" height=3px /></td>
		</tr>
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
<table width=680px cellpadding=2 cellspacing=0 border=0>
<tr>
	<td valign=top width=165px>
		<!-- Sidebar Filter -->
		<br>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr>
			<td valign=top width=97% nowrap>
				<table cellSpacing=0 cellPadding=2 width=100% border=1 class=filterHdr>
				<tr valign=middle>
					<td valign=middle class=filterbg>
						<table cellSpacing=0 cellPadding=0 border=0 width=100% class=filterbg>
						<tr>
							<td valign=middle class=filterbg><FONT color="yellow"><b>FILTER</b></FONT> BY SUPPLIER</td>
							<td valign=middle align=right class=filterbg><a href='javascript:void(0);'><image valign='baseline' align='right' src='<%=contextPath%>/images/more_purpleonyellow.gif' border=0 alt="View More"></a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td id="supplierFilterOptions">
						<table border=0>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>DELL CATALOG</a><span>&nbsp;(5572)</span></td>
						</tr>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>OFFICE</a><span>&nbsp;(392)</span></td>
						</tr>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>BALCO</a><span>&nbsp;(222)</span></td>
						</tr>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>ZEP</a><span>&nbsp;(126)</span></td>
						</tr>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>OFFICEMAX</a><span>&nbsp;(40)</span></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr><td height=5px><img src="<%=contextPath%>/images/none.gif" height=5px border=0></td></tr>
		</table>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr>
			<td valign=top width=97% nowrap>
				<table cellSpacing=0 cellPadding=2 width=100% border=1 class=filterHdr>
				<tr valign=middle>
					<td valign=middle class=filterbg>
						<table cellSpacing=0 cellPadding=0 border=0 width=100% class=filterbg>
						<tr>
							<td valign=middle class=filterbg><FONT color="yellow"><b>FILTER</b></FONT> BY CATALOG</td>
							<td valign=middle align=right class=filterbg><a href='javascript:void(0);'><image valign='baseline' align='right' src='<%=contextPath%>/images/more_purpleonyellow.gif' border=0 alt="View More"></a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td id="catalogFilterOptions">
						<table border=0>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>DELL CATALOG</a><span>&nbsp;(5572)</span></td>
						</tr>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>OFFICE</a><span>&nbsp;(392)</span></td>
						</tr>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>BALCO</a><span>&nbsp;(222)</span></td>
						</tr>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>ZEP</a><span>&nbsp;(126)</span></td>
						</tr>
						<tr>
							<td valign=top align='left'>&#149;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>OFFICEMAX</a><span>&nbsp;(40)</span></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr><td height=5px><img src="<%=contextPath%>/images/none.gif" height=5px border=0></td></tr>
		</table>

		<table cellSpacing=0 cellPadding="0" width="100%" border=0>
		<tr>
			<td valign=top width=97% nowrap>
				<table cellSpacing=0 cellPadding=2 width=100% border=1 class=filterHdr>
				<tr valign=middle>
					<td valign=middle class=filterbg>
						<table cellSpacing=0 cellPadding=0 border=0 width=100% class=filterbg>
						<tr>
							<td valign=middle class=filterbg><FONT color="yellow"><b>FILTER</b></FONT> BY COMMODITY</td>
							<td valign=middle align=right class=filterbg><a href='javascript:void(0);'><image valign='baseline' align='right' src='<%=contextPath%>/images/more_purpleonyellow.gif' border=0 alt="View More"></a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border=0>
						<tr>
							<td width=4 valign=top><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>COMPUTER</a><span>&nbsp;(5572)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>OFFICE</a><span>&nbsp;(392)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>CONSUL</a><span>&nbsp;(222)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>RENT</a><span>&nbsp;(126)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>SEMINAR</a><span>&nbsp;(40)</span></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr><td height=5px><img src="<%=contextPath%>/images/none.gif" height=5px border=0></td></tr>
		</table>

		<table cellSpacing=0 cellPadding="0" width="100%" border=0>
		<tr>
			<td valign=top width=97% nowrap>
				<table cellSpacing=0 cellPadding=2 width=100% border=1 class=filterHdr>
				<tr valign=middle>
					<td valign=middle class=filterbg>
						<table cellSpacing=0 cellPadding=0 border=0 width=100% class=filterbg>
						<tr>
							<td valign=middle class=filterbg><FONT color="yellow"><b>FILTER</b></FONT> BY PRICE</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border=0>
						<tr>
							<td width=4 valign=top align='left'><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>Under $100</a><span>&nbsp;(272)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top align='left'><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>$100-$500</a><span>&nbsp;(392)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top align='left'><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>$500-$1000</a><span>&nbsp;(1222)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top align='left'><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>$1000-$2000</a><span>&nbsp;(126)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top align='left'><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>$2000-$3000</a><span>&nbsp;(126)</span></td>
						</tr>
						<tr>
							<td width=4 valign=top align='left'><IMG width=2 height=1 src='<%=contextPath%>/images/none.gif'>&#149;&nbsp;</td>
							<td align='left' width='100%'><a href='javascript:void(0);'>$3000 and above</a><span>&nbsp;(40)</span></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr><td height=5px><img src="<%=contextPath%>/images/none.gif" height=5px border=0></td></tr>
		</table>

		<table cellSpacing=0 cellPadding="0" width="100%" border=0>
		<tr>
			<td valign=top width=97% nowrap>
				<table cellSpacing=0 cellPadding=2 width=100% border=1 class=filterHdr>
				<tr valign=middle>
					<td valign=middle class=filterbg>
						<table cellSpacing=0 cellPadding=0 border=0 width=100% class=filterbg>
						<tr>
							<td valign=middle class=filterbg><FONT color="yellow"><b>FILTER</b></FONT> BY KEYWORD</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellpadding=0 cellspacing=1 width=100%>
						<tr>
							<td colspan=2 valign=middle align=left bgColor="#efefef" nowrap>
								Search within your results
							</td>
						</tr>
						<tr>
							<td valign=middle align=left bgColor="#efefef" nowrap>
								<input name="SearchWithinTextBox" type=text maxlength=25 Size=15>
							</td>
							<td valign=middle align=right bgColor="#efefef"><input type="image" class=button src="<%=contextPath%>/images/button_go_sm.gif"></td>
						</tr>
						<tr>
							<td colspan=2 valign=top align=left>
								<table cellSpacing=0 cellPadding=0 border=0>
								<tr>
									<td nowrap><INPUT id="chkIncludeDesc" type="checkbox" CHECKED value="1" name="chkIncludeDesc"></td>
									<td nowrap>Include descriptions</td>
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

		<!-- End Sidebar Filter -->
	</td>

	<td valign=top>
		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr>
			<td width=30%>Showing 1 - 25 of <b>6735</b></td>
			<td width=70% align=right>&nbsp;<B>1</B>&nbsp;
				<A HREF="javascript:pageMe(2);">2</A>&nbsp;
				<A HREF="javascript:pageMe(3);">3</A>&nbsp;
				<A HREF="javascript:pageMe(4);">4</A>&nbsp;
				<A HREF="javascript:pageMe(5);">5</A>&nbsp;
				<A HREF="javascript:pageMe(6);">6</A>&nbsp;
				<A HREF="javascript:pageMe(7);">7</A>&nbsp;
				<A HREF="javascript:pageMe(8);">8</A>&nbsp;
				<A HREF="javascript:pageMe(9);">9</A>&nbsp;
				<A HREF="javascript:pageMe(10);">10</A>&nbsp;&nbsp;
				<A HREF="javascript:moveMe('next');"> Next 10 >></A>&nbsp;
			</td>
		</tr>
		</table>

		<hr size=0>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr>
			<td width=100%>
				<table border=0 cellSpacing=0 cellPadding=2 width=100% class=browseHdrDk>
				<tr>
					<td width=18% class=browseHdrDk>Catalog</td>
					<td width=37% class=browseHdrDk>Item Number</td>
					<td width=15% class=browseHdrDk>Commodity</td>
					<td width=15% class=browseHdrDk align=right>Cost (UOM)</td>
					<td width=15% class=browseHdrDk align=center>Quantity</td>
				</tr>
				</table>
<%
	for(int i=0;i<itemList.size();i++)
	{
		InvItem invItem = (InvItem) itemList.get(i);
		String s_item_number = invItem.getItemNumber();
		String s_item_description = invItem.getDescription();
		String s_item_commodity = invItem.getCommodity();
		String s_item_umcode = invItem.getUnitOfOrder();
		BigDecimal bd_item_cost = invItem.getCost();
		String s_item_primeuser = invItem.getOwner();
		String s_item_budgetcode = invItem.getBuyerCode();
		String s_item_status = invItem.getStatus();

		if (s_item_number == null)		{	s_item_number = "";			}
		if (s_item_description == null)	{	s_item_description = "";	}
		if (s_item_commodity == null)	{	s_item_commodity = "";	}
		if (s_item_umcode == null)		{	s_item_umcode = "";		}


		if (bd_item_cost == null)			{	bd_item_cost = new BigDecimal(0.00);	}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow id=itemTable<%=i%>>
				<tr>
					<td width=18% height=18px valign=top class=browseRow><%=s_item_commodity%></td>
					<td width=37% height=18px valign=top class=browseRow>
						<%=s_item_number%>&nbsp;<br>
						<div id="detailLink<%=i%>">
						<a href="javascript: viewItemDetails(<%=i%>); void(0);">View Details</a>
						</div>
					</td>
					<td width=15% height=18px valign=top class=browseRow><%=s_item_commodity%></td>
					<td width=15% height=18px valign=top class=browseRow align=right><%=bd_item_cost%> (<%=s_item_umcode%>)</td>
					<td width=15% height=18px valign=top class=browseRow valign=bottom align=center>
						<tsa:hidden name="min_qty" value=" "/>
						<tsa:hidden name="max_qty" value=" "/>
						<tsa:hidden name="itm_image" value=""/>
						<INPUT TYPE="TEXT" NAME="quantity" SIZE="10" TABINDEX="1" value="" ONCHANGE="formatQuantity(0); checkQty(0);">
					</td>
				</tr>
				</table>
				<hr size=2 class=browseHR>
<% } %>

			</td>
		</tr>
		</table>

		<hr size=0>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr>
			<td width=30%>Showing 1 - 25 of <b><%=listSize%></b></td>
			<td width=70% align=right>&nbsp;<B>1</B>&nbsp;
				<A HREF="javascript:pageMe(2);">2</A>&nbsp;
				<A HREF="javascript:pageMe(3);">3</A>&nbsp;
				<A HREF="javascript:pageMe(4);">4</A>&nbsp;
				<A HREF="javascript:pageMe(5);">5</A>&nbsp;
				<A HREF="javascript:pageMe(6);">6</A>&nbsp;
				<A HREF="javascript:pageMe(7);">7</A>&nbsp;
				<A HREF="javascript:pageMe(8);">8</A>&nbsp;
				<A HREF="javascript:pageMe(9);">9</A>&nbsp;
				<A HREF="javascript:pageMe(10);">10</A>&nbsp;&nbsp;
				<A HREF="javascript:moveMe('next');"> Next 10 >></A>&nbsp;
			</td>
		</tr>
		</table>

		<br>
		<br>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr>
			<td align=center><a href="javascript: returnItems(); void(0);" border=0 tabIndex=900><img class=button src="<%=contextPath%>/images/button_ok.gif" border=0></a></td>
			<td align=center><a href="javascript: returnAbort(); void(0);" border=0 tabIndex=910><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<hr size=0 color=black width=680px align=left>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td Valign=bottom align=left class=copyright>
		<br>&nbsp;Copyright &copy; 2000-2003 <a href="http://www.tsagate.com">Technical Services Associates Inc. All rights Reserved</a>
		<br>&nbsp;Release 1.00.00
	</td>
</tr>
</table>

</form>

</body>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;

	function hideItemDetails(x) {
		var myDiv = document.getElementById("detailLink" + x);
		myDiv.innerHTML = "<a href='javascript: viewItemDetails(" + x + "); void(0);'>View Details</a>";

		var myTable = document.getElementById("itemTable" + x);

		if ((browser == "NS" || browser == "NS6") && myTable.hasChildNodes()) {
			var row;

			for (var i = myTable.childNodes.length - 1; i >= 1 ; i--) {
				if (myTable.childNodes[i].nodeName == 'TR') {
					row = myTable.childNodes[i];
					myTable.removeChild(row);
				}
			}
		}
		else {
			for (var i = myTable.rows.length - 1; i >= 1; i--) {
				myTable.deleteRow(i);
			}
		}
	}

	function viewItemDetails(x) {
		var myDescHTML = "<hr size=0 width=90%><table id='itemDescTable" + x + "' border=0 cellspacing=0 cellpadding=0 width=90%></table><br>";
		var myDetailHTML = "<table id='itemDetailTable" + x + "' border=0 cellspacing=0 cellpadding=0 width=90%></table>";
		var myTable = document.getElementById("itemTable" + x);
		var myRow;
		var myCell;

		var myDiv = document.getElementById("detailLink" + x);
		myDiv.innerHTML = "<a href='javascript: hideItemDetails(" + x + "); void(0);'>Hide Details</a>";

		if (browser == "NS6") {
			myRow = document.createElement("TR");
			myTable.appendChild(myRow);

			myRow.className = "browseRow";

			myCell = document.createElement("TD");
			myRow.appendChild(myCell);

			myCell.style.colspan = 5;
			myCell.className = "browseRow";
			myCell.innerHTML = getItemDetails(x);
		}
		else {
			myRow = myTable.insertRow();
			myRow.className = "browseRow";

			myCell = myRow.insertCell();
			myCell.colSpan = 5;
			myCell.className = "browseRow";
			myCell.align = "center";
			myCell.innerHTML = myDescHTML + myDetailHTML;

			setItemDetails(x);
		}
	}

	function setItemDetails(x) {
		var detailHTML = "";
		var myTable = document.getElementById("itemDescTable" + x);
		var myRow;
		var myCell;

		myRow = createRow(myTable);
		myRow.className = "browseRow";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.innerHTML = "#10 TINTED 24LB BRIGHT WHITE STRATHMORE RECYCLED INK BLK 2 SIDED";

		myTable = document.getElementById("itemDetailTable" + x);

		myRow = createRow(myTable);
		myRow.className = "browseRow";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.style.width = "45%";
		myCell.innerHTML = "<b>Manufacturer:</b>&nbsp;Dell";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.style.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<input type=checkbox name=kitItem value=Y checked>";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.style.width = "20%";
		myCell.innerHTML = "<b>Kit Item</b>";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.style.width = "25%";
		myCell.innerHTML = "<a href='javascript: viewKitItems(" + x + "); void(0);'>View Kit Items</a>";

		// Row 2
		myRow = createRow(myTable);
		myRow.className = "browseRow";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.innerHTML = "<b>Model Number:</b>&nbsp;AZY-2398034";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.innerHTML = "<input type=checkbox name=taxableItem value=Y checked>";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.innerHTML = "<b>Taxable</b>";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.innerHTML = "<a href='javascript: viewPriceBreak(" + x + "); void(0);'>View Price Break</a>";

		return;
	}

	function returnItems()
	{
		doSubmit('/requests/req_summary.jsp', 'RequisitionCreate');
	}

	function returnAbort() {
		doSubmit('/menu/main_menu.jsp', 'DoNothing');
	}
-->
</script>
</html>