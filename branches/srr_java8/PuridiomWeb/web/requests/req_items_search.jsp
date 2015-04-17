<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String s_requisitionType = (String) request.getAttribute("requisitionType");
	
%>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>

<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_requisitionType%>"/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="150px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="requisitionItemsSearch" defaultString="Requisition Items Search" /></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" align="right">
		<table cellpadding="0" cellspacing="0" border="0">
			<tsa:tr>
				<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
			</tsa:tr>
			<tsa:tr>
				<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
			</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="680px" align="center">
					<TABLE CELLPADDING="2" CELLSPACING="0" BORDER="0" WIDTH="650px">
						<TR>
							<TD align='right' WIDTH="100" nowrap><tsa:label labelName="keyword" defaultString="Keyword(s)" /> </TD>
							<TD>
								<tsa:input type="text" name="as_keywords" size="35" maxLength="100" tabIndex="1" value="" onchange="upperCase(this);" />
								<tsa:hidden name="as_phrases" value=""/><tsa:hidden name="as_words" value=""/>
							</TD>
							<TD ALIGN="RIGHT"><tsa:input type="checkbox" name="as_catalog_items" tabIndex="10" value="Y" onclick="setValues(); setChecked(this);" checked="true" /></TD>
							<TD NOWRAP>&nbsp;<tsa:label labelName="catalogItems" defaultString="Catalog Items" /></TD>
						</TR>
						<TR>
							<TD align='right' WIDTH="100" nowrap><tsa:label labelName="priceRange" defaultString="Price Range" /></TD>
							<TD nowrap>
								<tsa:input type="text" name="as_minprice" size="5" maxLength="15" tabIndex="2" value="" onchange="formatPrice('min')" />
								&nbsp;&nbsp; <tsa:label labelName="to" defaultString="to" />&nbsp;&nbsp;
								<tsa:input type="text" name="as_maxprice" size="5" maxLength="15" tabIndex="3" value="" onchange="formatPrice('max')" />
							</TD>
							<TD ALIGN="RIGHT"><tsa:input type="checkbox" name="as_stock_items" tabIndex="11" value="Y" onclick="setValues(); setChecked(this);" /></TD>
							<TD NOWRAP>&nbsp;<tsa:label labelName="stockItems" defaultString="Stock Items" /></TD>

						</TR>
						<TR>
							<TD align='right' WIDTH="100" nowrap><tsa:label labelName="commodity" defaultString="Commodity" /></TD>
							<TD><tsa:input type="text" name="as_commodity" size="15" maxLength="30" tabIndex="4" value="" onchange="upperCase(this);" /></TD>
							<TD ALIGN="RIGHT"><tsa:input type="checkbox" name="as_inventory_items" tabIndex="12" value="Y" onclick="setChecked(this);" /></TD>
							<TD NOWRAP>&nbsp;<tsa:label labelName="inventoryItems" defaultString="Inventory Items" /></TD>

						</TR>
						<TR>
							<TD align='right' WIDTH="100" nowrap><tsa:label labelName="catalog" defaultString="Catalog" /></TD>
							<TD>
								<SELECT NAME="as_catalog" TABINDEX="5" ONCHANGE="setValues();">
									<OPTION value=""></OPTION>

									<OPTION value="5 FL REPRO ROOM" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-repro-room" defaultString="5 FL REPRO ROOM" /></OPTION>

									<OPTION value="DIRECT RESPONSE" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-direct-response" defaultString="DIRECT RESPONSE" /></OPTION>

									<OPTION value="DISK DUPLICATE" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-disk-duplicate" defaultString="DISK DUPLICATE" /></OPTION>

									<OPTION value="ENVELOPES" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-envelopes" defaultString="ENVELOPES" /></OPTION>

									<OPTION value="FLOFFFURN" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-flofffurn" defaultString="FLOFFFURN" /></OPTION>

									<OPTION value="FOLDERS" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-folders" defaultString="FOLDERS" /></OPTION>

									<OPTION value="OFFICE" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-office" defaultString="OFFICE" /></OPTION>

									<OPTION value="PC HARDWARE" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-pc-hardware" defaultString="PC HARDWARE" /></OPTION>

									<OPTION value="PRINTING" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-printing" defaultString="PRINTING" /></OPTION>

									<OPTION value="PRINTING - MISC" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-printing-misc" defaultString="PRINTING - MISC" /></OPTION>

									<OPTION value="T.CIACCIOFILING" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-t-ciacciofiling" defaultString="T.CIACCIOFILING" /></OPTION>

									<OPTION value="VIDEO" ONFOCUS="checkBlur('catalog');"><tsa:label labelName="req-video" defaultString="VIDEO" /></OPTION>
								</SELECT>
							</TD>
							<TD colspan="2">&nbsp;</TD>						
						</TR>
						<TR>
							<TD align='right' WIDTH="100" nowrap><tsa:label labelName="manufacturer" defaultString="Manufacturer" /></TD>
							<TD><tsa:input type="mintext" name="as_manufacturer" maxLength="30" tabIndex="6" value="" onfocus="checkBlur('mfg');" onchange="upperCase(this)" /></TD>
							<TD align='right' WIDTH="75" nowrap><tsa:label labelName="sortBy" defaultString="Sort By" /></TD>
							<TD NOWRAP>
								<SELECT NAME="as_sort" TABINDEX="13" ONCHANGE="setValues();">
									<OPTION value=""></OPTION>
									<OPTION value="cti_item_number" SELECTED><tsa:label labelName="req-item-number" defaultString="Item Number" /></OPTION>
									<OPTION value="cti_cost"><tsa:label labelName="req-price" defaultString="Price" /></OPTION>
									<OPTION value="cti_commodity"><tsa:label labelName="req-commodity" defaultString="Commodity" /></OPTION>
									<OPTION value="cti_catalog_id"><tsa:label labelName="req-catalog" defaultString="Catalog" /></OPTION>
									<OPTION value="cti_mfg_name"><tsa:label labelName="req-manufacturer" defaultString="Manufacturer" /></OPTION>
									<OPTION value="cti_model_number"><tsa:label labelName="req-model-number" defaultString="Model Number" /></OPTION>
								</SELECT>
								<SELECT NAME="as_sort_opt" TABINDEX="14">
									<OPTION value=""></OPTION>
									<OPTION value="ASC" SELECTED>A</OPTION>
									<OPTION value="DESC">D</OPTION>
								</SELECT>
							</TD>
						</TR>
						<TR>
							<TD align='right' nowrap><tsa:label labelName="modelNro" defaultString="Model No" />.</TD>
							<TD><INPUT TYPE="TEXT" NAME="as_modelno" SIZE="15" MAXLENGTH="30" TABINDEX="7" value="" ONFOCUS="checkBlur('modelno');" ONCHANGE="upperCase(this);"></TD>
							<TD align='right' nowrap><tsa:label labelName="groupBy" defaultString="Group By" /></TD>
							<TD>
								<SELECT NAME="as_groupby" TABINDEX="15" ONCHANGE="setValues();">
									<OPTION value=""></OPTION>
									<OPTION value="cti_commodity"><tsa:label labelName="req-commodity" defaultString="Commodity" /></OPTION>
									<OPTION value="cti_catalog_id"><tsa:label labelName="req-catalog" defaultString="Catalog" /></OPTION>
									<OPTION value="cti_mfg_name"><tsa:label labelName="req-manufacturer" defaultString="Manufacturer" /></OPTION>
								</SELECT>
							</TD>

						</TR>
						<TR>
							<TD COLSPAN="4"><IMG WIDTH="400" HEIGHT="5" SRC="/xchange/images/none.gif">
								<tsa:hidden name="cat_id" value=""/>
								<tsa:hidden name="as_type" value="REQ"/>
								<tsa:hidden name="ds_sort" value=""/>
								<tsa:hidden name="sortc" value=""/>
								<tsa:hidden name="sortm" value=""/>
								<tsa:hidden name="cat_where" value=""/>
								<tsa:hidden name="inv_where" value=""/>
								<tsa:hidden name="req_where" value=""/>
								<tsa:hidden name="where" value=""/>
								<tsa:hidden name="wheredel" value=""/>
								<tsa:hidden name="as_cancel_page" value="/puridiom/xchange/shopcart/items_search.jsp"/>
								<tsa:hidden name="as_redirect" value=""/>
								<tsa:hidden name="as_action" value="REDIRECT"/>
								<tsa:hidden name="as_dbms_type" value="SYBASE"/>
							</TD>
						</TR>
						</TABLE>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="50%" align="center"><!--'ListItems"-->
		<a href="javascript: doSubmit('catalog/item_search_results.jsp', 'InvItemRetrieveAll'); void(0);"><img class="button" src="<%=contextPath%>/images/button_search.gif" tabindex="16" border="0" alt="Search"></a>
	</td>
	<td width="50%" align="center">
		<a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionCreate'); void(0);"><img class="button" src="<%=contextPath%>/images/button_cancel.gif" tabindex="17" border="0" alt="Cancel"></a>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;


// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>