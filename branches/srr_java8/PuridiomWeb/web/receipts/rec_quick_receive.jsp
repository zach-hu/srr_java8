<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	default_length = propertiesManager.getProperty("REC OPTIONS", "QUICKRECEIVINGLENGTH", "10");
%>

<input type="hidden" name="totalOrders" value="">

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Quick Receiving</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<table width=<%=formEntryWidth%> border=0 cellspacing=0 cellpadding=2>
	<tr>
		<td>
			<table width="70%" border=0 cellspacing=0 cellpadding=2>
			<!--Headings-->
			<tr>
				<td style="font:13" height=30px align="center">
					<b>Enter PO Numbers:</b>
				</td>
			</tr>
			</table>
			<table ID="quick_receive" width="70%" border=0 cellspacing=0 cellpadding=2>
			<tr>
				<td align="center">
					1:&nbsp;&nbsp;
					<input name="PoNumber1" type="text" size="20" maxlength="20" value="" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					2:&nbsp;&nbsp;
					<input name="PoNumber2" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					3:&nbsp;&nbsp;
					<input name="PoNumber3" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					4:&nbsp;&nbsp;
					<input name="PoNumber4" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					5:&nbsp;&nbsp;
					<input name="PoNumber5" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					6:&nbsp;&nbsp;
					<input name="PoNumber6" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					7:&nbsp;&nbsp;
					<input name="PoNumber7" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					8:&nbsp;&nbsp;
					<input name="PoNumber8" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					9:&nbsp;&nbsp;
					<input name="PoNumber9" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			<tr>
				<td align="center">
					10:&nbsp;&nbsp;
					<input name="PoNumber10" type="text" size="20" maxlength="20" onKeyUp="return autoTab(this, <%=default_length%>, event);" onchange="upperCase(this)"></input>
				</td>
			</tr>
			</table>
		</td>
		<td>
			<table width="30%" border=0 cellspacing=0 cellpadding=2>
			<tr>
				<td>
					Auto-Tab:&nbsp;&nbsp;
					<input name="auto_tab" type="checkbox" checked></input>
				</td>
			</tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr>
				<td valign=middle align=left nowrap>
				Enter More:&nbsp;
				<input type="text" size="5" maxLength="3" name="addNewNumTimes" value="1"/>&nbsp;
				<div id="pxsmallbutton" style="display:inline"><a href="javascript: addNewMultiple(); void(0);">Go</a></div>
				</td>
			</tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr>
				<td><div id="pxbutton"><a href="javascript: checkPo();">Enter</a></div></td>
			</tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr>
				<td><div id="pxbutton"><a href="javascript: returnMe();">Return</a></div></td>
			</tr>
			</table>
		</td>
	</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/receipts/rec_quick_receive.jsp", "DoNothing", "Quick Receiving");
	var myTable = document.getElementById("quick_receive");
	var maxRows = myTable.rows.length;

	function autoTab(input,len, e) {
	  var keyCode = e.keyCode;
	  var filter = [0,8,9,16,17,18,37,38,39,40,46];
	  if (frm.auto_tab.checked) {
		  if (input.value.length >= len && !containsElement(filter,keyCode)) {
		    input.form[(getIndex(input)+1) % input.form.length].focus();
		  }
	  }

	  function containsElement(arr, ele) {
	    var found = false, index = 0;
	    while(!found && index < arr.length)
	    if(arr[index] == ele)
	    found = true;
	    else
	    index++;
	    return found;
	  }

	  function getIndex(input) {
	    var index = -1, i = 0, found = false;
	    while (i < input.form.length && index == -1)
	    if (input.form[i] == input)index = i;
	    else i++;
	    return index;
	  }
	  return true;
	}

	function returnMe() {
		doSubmit('menu/main_menu.jsp', 'DoNothing');
	}

	function checkPo() {
		frm.totalOrders.value = maxRows;
		doSubmit('receipts/rec_quick_receive_checked.jsp', 'QuickReceiveCheck');
	}

	function upperCase (formField) {
		  var x = formField.value;
		  formField.value = x.toUpperCase();
	}

	function addNew(){
		myTable = document.getElementById("quick_receive");
		count = maxRows;
		maxRows++;

		myRow = createRow(myTable);

		myCell = createCell(myRow);
		myCell.align = "center";
		myCell.innerHTML = maxRows + ":&nbsp;&nbsp;<input type=text name=\"PoNumber" + maxRows + "\" size=20 maxlength=20 value=\"\" onKeyUp=\"return autoTab(this, <%=default_length%>, event);\"  onchange=\"upperCase(this);\"></input>";
	}

	function addNewMultiple(){
		if(frm.addNewNumTimes != null && frm.addNewNumTimes.value != "" ){
			var times = frm.addNewNumTimes.value;
			for(var i = 0; i < times; i++){
				addNew();
			}
		}
	}

/*
	$(function(){
	    $('input[name*="PoNumber1"]').focusout(function(){
		    alert("this.value is " + this.value);
	    	 frm.PoHeader_poNumber.value = this.value;
	        sendValue($(this));
	    });
	});

	function sendValue(type)
	{
	  var url = contextPath + "/ajaxServlet";

	  $.post(url, { QuickReceiving: "yes", PoHeader_poNumber: frm.PoHeader_poNumber.value}, function(data) {
			if (data != null)
			{
	      		alert("this is " + data.value);
			}
	      $(type).html( data );
		 });
	}
	*/

// End Hide script -->
</SCRIPT>