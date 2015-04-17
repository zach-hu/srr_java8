<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%@page import="com.tsa.puridiom.common.utility.HiltonUtility"%><tsa:hidden name="fromPage" value="rec_item"/>

<%
	//String s_bin_item_number = (String) request.getAttribute("InvBinLocation_itemNumber");
	String[] s_bin_item_location = new String[1];
	boolean binLocationString = false;
	if(request.getAttribute("InvBinLocation_itemLocation") instanceof String[]){
		s_bin_item_location = (String[]) request.getAttribute("InvBinLocation_itemLocation");
	} else {
		s_bin_item_location[0] = (String) request.getAttribute("InvBinLocation_itemLocation");
	}
	 
	String s_inv_item_number = (String) request.getAttribute("InvLocation_itemNumber");
	String s_inv_item_location = (String) request.getAttribute("InvLocation_itemLocation");
	String s_icRecLine = (String) request.getAttribute("ReceiptLine_icRecLine");
	String s_title = "";
	String qtyReceived = (String) request.getAttribute("qtyReceived");
	String s_poNumber = (String) request.getAttribute("poNumber");
	String s_recNumber = (String) request.getAttribute("recNumber");
	
	//String invItemBinNumber = (String) request.getAttribute("InvBinLocation_itemNumber");
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");
	String s_bin_HdrIc = (String) request.getAttribute("InvBinLocation_hdrIc");
	String s_bin_IcRc = (String) request.getAttribute("InvBinLocation_icRc");
	String fromPage = (String) request.getAttribute("fromPage");
	String s_icPoLine = (String) request.getAttribute("PoLine_icPoLine") ;
	List propertyList = (List)request.getAttribute("invPropertyList");
	if(propertyList == null){
		propertyList = new ArrayList();
	}
	if(HiltonUtility.isEmpty(s_recNumber)){
		s_recNumber = receiptLine.getReceiptNumber();
	}
%>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px align="center">
<tr>
<td>
	<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
	<td height="160px" >&nbsp;</td>	
	<td width=680px align=center>
		<table border=1 id=invPropertyListTable cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td class=browseHdr height=18px nowrap>&nbsp;Receipt Serial Numbers</td>
				<tsa:hidden name="binAction" value=""/>
				<tsa:hidden name="propertyListSize" value="<%= propertyList.size() %>"/>
				<tsa:hidden name="enteredPropertyListFrom" value="${fromPage}"/>
				<tsa:hidden name="as_rows" value="<%=propertyList.size()%>"/>
			</tr>
			<tr>
			<td class=browseRow>
				<table border=0	cellpadding=1 cellspacing=0 width=680px>
				<tr>
<!--
				<td <%=HtmlWriter.isVisible(oid, "invprop-tagnumber")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-tagnumber", "GPIN")%></b></td>
-->
				<td <%=HtmlWriter.isVisible(oid, "invprop-serialnumber")%> align=center width=20%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-datein")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-datein", "Date In")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-owner")%> align=center width=20%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-owner", "Recorded By") %></b></td>
<!--
				<td <%=HtmlWriter.isVisible(oid, "invprop-dateout")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-dateout", "Date Out")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-ponumber")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-ponumber", "Order Number")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-contractnumber")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-contractnumber", "Contract")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-receiptnumber")%> align=center width=6%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-receiptnumber", "Receipt")%></b></td>
-->
				</tr>
				</table>

			<table id="invPropertyTable" border=0 cellspacing=0 cellpadding=1 width=680px>
<%
	int i_rowcount = 0;
	if (propertyList != null)
	{
		// Force to int
		int     qty = (int) (HiltonUtility.ckNull(receiptLine.getQtyStep2Received())).intValue();
		
		for(int i = 0; i < qty; i++)
		{
			BigDecimal icRc = new BigDecimal(0) ;
			BigDecimal icProperty = new BigDecimal(0) ;
			String	tagNumber = "" ;
			String	contractNumber = "" ;
			String	poNumber = s_poNumber ;
			String	serialNumber = "" ;
			String	shipNumber = "" ;
			String	dateIn = Dates.today(userDateFormat, userTimeZone) ;
			String	dateOut = "" ;
			String	receiptNumber = s_recNumber ;
			String	remarks = "" ;
			String	itemNumber = s_inv_item_number ;
			String	cblOutNumber = "" ;
			String	armyNumber = "" ;
			String  assetNumber = "" ;

			String owner = uid ;
			UserProfile ownerRec = UserManager.getInstance().getUser(oid, owner);
			if (i < propertyList.size()) {
				InvProperty invProperty = (InvProperty) propertyList.get(i);
				
				contractNumber = invProperty.getContract() ;
				poNumber = invProperty.getPoNumber() ;
				tagNumber = invProperty.getTagNumber() ;
				receiptNumber = invProperty.getReceiptNumber() ;
				serialNumber = invProperty.getSerialNumber() ;
				remarks = invProperty.getRemarks() ;
				icRc = invProperty.getIcRc() ;
				icProperty = invProperty.getIcProperty() ;
				dateOut = HiltonUtility.getFormattedDate(invProperty.getDateOut(), oid, userDateFormat) ;
				dateIn = HiltonUtility.getFormattedDate(invProperty.getDateIn(), oid, userDateFormat) ;
				if (poNumber==null){poNumber = "";}
				if (remarks == null) remarks = "" ;
				if (contractNumber==null){contractNumber = "";}
				if (tagNumber==null){tagNumber = "";}
				if (receiptNumber==null){receiptNumber = "";}
				if (serialNumber==null){serialNumber = "";}
				if (icRc==null){icRc = new BigDecimal(0); }
				if (icProperty==null){icProperty = new BigDecimal(0); }
			}
			pageContext.setAttribute("i", i);
%>
						<tr>
							<tsa:hidden name="gpin_${i}" value="<%=tagNumber%>"/>
							<tsa:hidden name="InvProperty_itemNumber" value="<%=invItemNumber%>"/>
							<tsa:hidden name="InvProperty_icRc" value="<%=s_bin_IcRc%>"/>
							<tsa:hidden name="InvProperty_icRecLine" value="<%=s_icRecLine%>"/>
							<tsa:hidden name="InvProperty_icPoLine" value="<%=s_icPoLine%>"/>
							<tsa:hidden name="InvProperty_itemLocation" value="<%=s_bin_item_location[0] %>"/>
							<tsa:hidden name="InvProperty_icProperty" value=""/>
							<tsa:hidden name="InvProperty_source" value="REC"/>
							<tsa:hidden name="InvProperty_status" value="00"/>
							<tsa:hidden name="InvProperty_owner" value="${userId}"/>
							<tsa:hidden name="InvProperty_dateIn" value="<%=dateIn%>"/>
							<tsa:hidden name="InvProperty_dateOut" value="<%=dateOut%>"/>
							<tsa:hidden name="InvProperty_poNumber" value="<%=poNumber%>"/>
							<tsa:hidden name="InvProperty_receiptNumber" value="<%=receiptNumber%>"/>
							<tsa:hidden name="InvProperty_contract" value="<%=contractNumber%>"/>
							<tsa:hidden name="InvProperty_tagNumber" value="<%=tagNumber%>"/>
							<tsa:hidden name="InvProperty_armyNumber" value="<%=armyNumber%>"/>
							<tsa:hidden name="InvProperty_assetNumber" value="<%=assetNumber%>"/>
							<tsa:hidden name="InvProperty_cblOutNumber" value="<%=cblOutNumber%>"/>
							<tsa:hidden name="InvProperty_shipNumber" value="<%=shipNumber%>"/>
							<tsa:hidden name="InvProperty_remarks" value="<%=remarks%>"/>


<!--
							<td <%=HtmlWriter.isVisible(oid, "invprop-tagnumber")%> width=10% align=center><%=tagNumber%></td>
-->
							<td <%=HtmlWriter.isVisible(oid, "invprop-serialnumber")%> width=20% align=center><input type=text name="InvProperty_serialNumber" size=50 maxlength=200 tabindex=6 value="<%=serialNumber%>" onchange="upperCase(this);"></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-datein")%> width=10% align=center><%=dateIn %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-owner")%> width=20% align=center><%=HiltonUtility.ckNull(ownerRec.getDisplayName())%></td>
<!--
							<td <%=HtmlWriter.isVisible(oid, "invprop-dateout")%> width=10% align=center><%=dateOut %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-ponumber")%> width=10% align=center><%=poNumber %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-contractnumber")%> width=10% align=center><%=contractNumber %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-receiptnumber")%> width=6% align=center><%=receiptNumber %></td>
-->
						</tr>
<%	i_rowcount++;
		}%>
	<%} %>
					</table>
				</td>
			</tr>
			<tr><td><tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/></td></tr>
		</table>
	</td>
</tr>
</table>

</td>
</tr>
</table>


<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var qtyReceived = <%=qtyReceived%>;

	var myTable = document.getElementById("invPropertyTable");
	var TotalRows = myTable.rows.length;
	var maxRows = frm.as_maxrows.value;
	var accRows = frm.as_rows.value;
	<% UserProfile owner = UserManager.getInstance().getUser(oid, uid); %>

	function addProperty()
	{
		myTable = document.getElementById("invPropertyTable");
		TotalRows = TotalRows+1;
		var count = myTable.rows.length;

		NewTotalRows = eval(nfilter(frm.ReceiptLine_qtyStep2Received));
		
		if(count > NewTotalRows)
		{
			for (var i=count; (NewTotalRows)<i; i--){
				myTable.deleteRow(i-1);
			}
		}

		if(count < NewTotalRows)
		{
			newRows = NewTotalRows - count;
			for (var i=0; i<newRows; i++){
				myRow = myTable.insertRow(count);
				maxRows++;
				count++ ;

				myCell = myRow.insertCell();
				id = "spacer1";
				myCell.id = id;
				myCell.valign = "middle";
				myCell.align = "center";
				myCell.width = "20%" ;
				myCell.innerHTML = "<input type=\"text\" name=\"InvProperty_serialNumber\" size=50 maxlength=200 tabindex=6 value=\"\" onchange=\"upperCase(this);\" >"+
									"<INPUT TYPE=\"hidden\" NAME=\"gpin_" + (count + 1) +"\" VALUE=\"\">"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_itemNumber\" value=\"<%=invItemNumber%>\">"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_icRc\" value=\"<%=s_bin_IcRc%>\">"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_icRecLine\" value=\"<%=s_icRecLine%>\">"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_icPoLine\" value=\"<%=s_icPoLine%>\">"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_itemLocation\" value=\"<%=s_bin_item_location[0]%>\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_icProperty\" value=\"\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_source\" value=\"REC\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_status\" value=\"00\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_owner\" value=\"<%=uid%>\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_dateIn\" value=\"<%=Dates.today(userDateFormat, userTimeZone)%>\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_dateOut\" value=\"\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_poNumber\" value=\"<%=s_poNumber%>\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_receiptNumber\" value=\"<%=s_recNumber%>\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_contract\" value=\"\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_tagNumber\" value=\"\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_armyNumber\" value=\"\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_assetNumber\" value=\"\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_cblOutNumber\" value=\"\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_shipNumber\" value=\"\"/>"+
									"<INPUT TYPE=\"hidden\" name=\"InvProperty_remarks\" value=\"\"/>";
	
				myCell = myRow.insertCell();
				id = "spacer1";
				myCell.id = id;
				myCell.valign = "middle";
				myCell.align = "center";
				myCell.width = "10%" ;
				myCell.innerHTML = "<%=Dates.today(userDateFormat, user.getTimeZone())%>";
	
				myCell = myRow.insertCell();
				id = "spacer1";
				myCell.id = id;
				myCell.valign = "middle";
				myCell.align = "center";
				myCell.width = "20%" ;
				myCell.innerHTML = "<%=HiltonUtility.ckNull(owner.getDisplayName())%>";
			}
		}
		
	}
	
	function submitThis()
	{
		if (qtyReceived > 0)
		{
			if (qtyReceived == 1)
			{
				if (frm.InvProperty_serialNumber && trim(frm.InvProperty_serialNumber) == "")
				{
					alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%> is required");
					frm.InvProperty_serialNumber.focus();
					return false;
				}
			}
			else
			{
				for (var i = 0; i < qtyReceived; i++)
				{
					if (frm.InvProperty_serialNumber[i] && trim(frm.InvProperty_serialNumber[i]) == "")
					{
						alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%> is required");
						frm.InvProperty_serialNumber[i].focus();
						return false;
					}
				}
			}
		}

		doSubmit('receipts/rec_set_icrc_property.jsp', 'InvPropertyDeleteByIcRecLine;ReceiptTempPropertyUpdate');
	}

	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("InvPropertyUpdate") >= 0 || handler.indexOf("InvPropertyAdd") >= 0) {
	  }

	  return true;
    }

// End Hide script -->
</SCRIPT>