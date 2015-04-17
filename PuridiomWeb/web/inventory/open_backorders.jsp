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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Backorder Search</div>
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
							<TD align='left' WIDTH="33%" nowrap>Operator</TD>
							<TD align='left' WIDTH="33%" nowrap>Filter</TD>
						</TR>
						<TR>
							<TD align='left' nowrap>Requisition Number</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="InvItem_itemNumber" SIZE=15 MAXLENGTH=30 value="" ></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Requisition Date</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_description" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Requisition Status</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="status" SIZE=15 MAXLENGTH=30 value="9020" disabled ></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Inventory Location</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_unitOfOrder" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Item Number</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_cost" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Item Description</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_taxable" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Unit of Measure</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_abcCode" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Quantity Requested</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_abcCode" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Quantity Backordered</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_abcCode" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Quantity On Hand</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_abcCode" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Commodity</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_abcCode" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap>Requisitoned By</TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_abcCode" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						<TR>
							<TD align='left' nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%></TD>
							<TD>
								<SELECT NAME="as_catalog" ONCHANGE="setValues();">
									<OPTION value="=">Equal To</OPTION>
									<OPTION value="<">Greater Than</OPTION>
									<OPTION value="<=">Greater Than/Equal To</OPTION>
									<OPTION value=">">Less Than</OPTION>
									<OPTION value=">=">Less Than/Eqaul To</OPTION>
									<OPTION value="!=">Not Equal</OPTION>
								</SELECT>
							</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_InvItem_abcCode" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>	
						</TR>
						</TABLE>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><!--'ListItems"-->
		<a href="javascript: doSubmit('inventory/req_backorders.jsp', 'RequisitionHeaderRetrieveByStatus'); void(0);"><img class=button src="<%=contextPath%>/images/button_search.gif" tabindex=16 border=0 alt="Search"></a>
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

</FORM>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;


// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>
