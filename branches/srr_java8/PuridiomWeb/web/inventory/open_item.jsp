<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String s_requisitionType = (String) request.getAttribute("requisitionType");
	
%>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></SCRIPT>

<tsa:hidden name="requisitionType" value="<%=s_requisitionType%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inventory Items Search</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
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
<br>

<table border=1 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
					<TABLE CELLPADDING=2 CELLSPACING=0 BORDER=0 WIDTH=650px>
						<TR>
							<TD align='left' WIDTH="33%" nowrap><b>Item Name</b></TD>
<!--						<TD align='left' WIDTH="33%" nowrap><b>Operator</b></TD>	-->
							<TD align='left' WIDTH="33%" nowrap><b>Filter</b></TD>
						</TR>
						<TR>
							<TD align='left' nowrap>Item Number</TD>
<!--							<TD>
							<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
-->							<TD><INPUT TYPE="TEXT" Name="filterValue" ID="InvItem_itemNumber" SIZE=15 MAXLENGTH=30 value="" ></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Item Description</TD>
<!--							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
-->							<TD><INPUT TYPE="TEXT" Name="filterValue" ID="InvItem_description" SIZE=15 MAXLENGTH=30 value=""></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Commodity</TD>
<!--							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
-->								<TD><INPUT TYPE="TEXT" Name="filterValue" ID="InvItem_commodity" SIZE=15 MAXLENGTH=30 value="" ></TD>	
						</TR>

						<TR>
							<TD align='left' nowrap>Inventory Location</TD>
<!--						<TD>
							<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
-->							<TD><INPUT TYPE="TEXT" Name="filterValue" ID="InvItem_cost" SIZE=15 MAXLENGTH=30 value="" ></TD>	
						</TR>
						</TABLE>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><!--'ListItems"-->
		<a href="javascript: createRequest(); void(0);"><img class=button src="<%=contextPath%>/images/button_search.gif" tabindex=16 border=0 alt="Search"></a>
	</td>
	<td width=50% align=center>
		<a href="javascript: doSubmit('inventory/inv_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" tabindex=17 border=0 alt="Cancel"></a>
	</td>
</tr>
</table>

<br>
 
<hr size=0 color=black width=680px align=left>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td Valign=bottom align=left class=copyright>
		<br>&nbsp;Copyright &copy; 2000-2003 <a href="http://www.tsagate.com">Technical Services Associates Inc. All rights Reserved</a>
		<br>&nbsp;Release 1.00.00
	</td>
</tr>
</table>
<TABLE ID="filterValues">
</TABLE>
</FORM>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	function createRequest(){
		var size = (document.getElementsByName("filterValue")).length;
		var myTable = document.getElementById("filterValues");
		for(i=0;i<size;i++){
			if(!(isEmpty(frm.filterValue[i].value))){
				name = frm.filterValue[i].id;
				myRow = myTable.insertRow();
				myCell = myRow.insertCell();
				myCell.innerHTML = "<INPUT TYPE=\"HIDDEN\" NAME=\"" + name + "\" value=\"" + frm.filterValue[i].value + "\">";
			}
		}
		doSubmit('inventory/inv_items.jsp', 'InvItemRetrieveBy'); void(0);
	}
// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>
