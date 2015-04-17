<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String s_requisitionType = (String) request.getAttribute("requisitionType");
	String s_reqType = "";
	if (s_requisitionType.equals("S")){
		s_reqType = "Supply";
	}
	else if (s_requisitionType.equals("T")){
		s_reqType = "Transfer";
	}
%>
<script language='Javascript1.2' src="/scripts/browse.js"></SCRIPT>

<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_requisitionType%>"/>
<tsa:hidden name="RequisitionHeader_approved" value="Y"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_reqType%> Requisition Search</div>
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
							<TD align='right' WIDTH="100" nowrap>Keyword(s)</TD>
							<TD>
								<INPUT TYPE="TEXT" NAME="as_keywords" SIZE=35 MAXLENGTH=100 TABINDEX="1" value="" ONCHANGE="upperCase(this);">
								<tsa:hidden name="as_phrases" value=""/><tsa:hidden name="as_words" value=""/>
							</TD>
						</TR>
						<TR>
							<TD COLSPAN=3><BR></TD>
						<TR>
							<TD align='left' WIDTH="33%" nowrap>Item Name</TD>
<!--						<TD align='left' WIDTH="33%" nowrap>Operator</TD>	-->
							<TD align='left' WIDTH="33%" nowrap>Filter</TD>
						</TR>
						<TR>
							<TD align='left' nowrap>Requisition Number</TD>
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
							<TD align='left' nowrap>Type</TD>
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
							<TD align='left' nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></TD>
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
						</TABLE>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><!--'ListItems"-->
		<a href="javascript: searchReqs(); void(0);"><img class=button src="<%=contextPath%>/images/button_search.gif" tabindex=16 border=0 alt="Search"></a>
	</td>
	<td width=50% align=center>
		<a href="javascript: doSubmit('/inventory/dsb_select_type.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" tabindex=17 border=0 alt="Cancel"></a>
	</td>
</tr>
</table>

<br>
 <%@ include file="/system/footer.jsp" %>
</FORM>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function searchReqs(){
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
		doSubmit('/inventory/dsb_reqs.jsp', 'RequisitionHeaderRetrieveBy'); void(0);
	}
// End Hide script -->
</SCRIPT>
