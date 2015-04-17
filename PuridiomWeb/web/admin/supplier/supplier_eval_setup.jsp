<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);%>
<tsa:hidden name="allowBrowse" value="true"/>

<table width="685px" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td valign="top" width="135px" height="30px">
			<table cellpadding="0" cellspacing="0" border="0" height=100% width=100%>
				<tr>
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" class="darkShadow">
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierevalsetup", "Supplier Evaluation Setup")%></td>
					</td>
				</tr>
			</table>
		</td>
		<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
		<td valign="bottom" align="right" height="30px">
			<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="1000px" height="1px" class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
			</tr>
			<tr>
				<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
			</tr>
			</table>
		</td>
	</tr>
</table>

<br>
<br>
<%String evalQty = propertiesManager.getProperty("SUPPLIER EVAL", "QTY","10");
int qty = Integer.parseInt(evalQty);
String evalType = propertiesManager.getProperty("SUPPLIER EVAL", "TYPE","W");
%>
<table border="0" cellspacing="0" cellpadding="0" width="690px" height="390px">
<tr>
	<td align="center" valign="top" width="510px" id="systemSetupFrame">
		<div id="Supplier" style="visibility: visible; position:absolute; left:25px; height:300px;">
			<table border="0" cellspacing="0" cellpadding="2" width="100"%>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="center">
						<table width="100%">
							<tr>
								<th width="40%" nowrap>Evaluation Type:&nbsp;</th>
								<td width="30%" nowrap><input type="radio" name="ratingtype" <%if(evalType.equalsIgnoreCase("A")) {%>checked<%} %> onclick="checkOption();"> Average</td>
								<td width="30%" nowrap><input type="radio" name="ratingtype" <%if(evalType.equalsIgnoreCase("W")) {%>checked<%} %> onclick="checkOption();"> Weight</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
						<td>&nbsp;</td>
						<th align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "evaluationname", "Evaluation Name")%>&nbsp;</th>
					   <th>&nbsp;<div id="weight" style="visibility: visible;"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "evaluationweight", "Weight")%></div>&nbsp;</th>
				</tr>
				<%int totalWeight = 0;
				for(int i = 0;i < qty; i++)
					{%>
						<tr>
							<td align="right"><%=i + 1%>.&nbsp;</td>
							<td>
								<tsa:hidden name="Property_section" value="SUPPLIER EVAL"/>
								<tsa:hidden name="Property_property" value="<%=i %>"/>
								<input type="text" name="Property_value" size="50" value="<%=propertiesManager.getProperty("SUPPLIER EVAL", String.valueOf(i),"")%>" onchange="upperCase(this);">
							</td>
							<td>
								<div id="weight" style="visibility: visible;">
									<tsa:hidden name="Property_section" value="SUPPLIER EVAL W"/>
									<tsa:hidden name="Property_property" value="<%=i %>"/>
									<input type="text" name="Property_value" size="5" value="<%=propertiesManager.getProperty("SUPPLIER EVAL W", String.valueOf(i),"")%>" style="text-align:right" onKeyPress="return numbersonly(this, event)" onchange="addUp(this.value, '<%=i%>');">
									<tsa:hidden name="eval_weight" value="<%=propertiesManager.getProperty("SUPPLIER EVAL W", String.valueOf(i),"")%>"/>
									<%String weightS = propertiesManager.getProperty("SUPPLIER EVAL W", String.valueOf(i),"0");
									if(HiltonUtility.isEmpty(weightS)){		weightS = "0"; }
									int tmpW = Integer.parseInt(weightS);
									totalWeight = totalWeight + tmpW; %>
								</div>
							</td>
						</tr>
					<%} %>
					<tr><td colspan="4"><br></td></tr>
					<tr>
						<td>&nbsp;</td>
						<th align="right"><div id="weight" style="visibility: visible;"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "weightequals100", "Combined Weight must equal 100")%></div></th>
						<td><div id="weight" style="visibility: visible;"><input type="text" size="5" name="total" value="<%=totalWeight %>"></div></td>
					</tr>
			</table>
		</div>
	</td>
</tr>
</table>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="480px">
<tr>
	<td>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		<%	if (role.getAccessRights("SUPPLIEREVAL") >= 3 ) { %>
			<td width="50%" align="center"><a href="javascript: checkTotal(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border="0"></a></td>
		<% } %>
			<td width="50%" align="center"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border="0"></a></td>
		</tr>

		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/supplier/supplier_eval_setup.jsp", "DoNothing", "Supplier Evaluation Setup");

	function checkOption()
	{
		if(frm.ratingtype[1].checked)
		{//average
			displayArea('weight');
		}
		else if(frm.ratingtype[0].checked)
		{//rated
			hideArea('weight');
		}
	}


	function checkTotal()
	{
		if(frm.total.value == 100)
		{
			doSubmit('admin/admin_menu.jsp', 'PropertyUpdate');
		}
		else
		{
			alert("Combined Weight must equals 100");
		}
	}

	function addUp(evalValue, index)
	{
	 	var runTotal = 0;
		frm.eval_weight[index].value = evalValue;

		for(var i=0; i < 9; i++)
		{
			if (frm.eval_weight[i].value.length > 0)
			{
				runTotal = (eval(frm.eval_weight[i].value) + eval(runTotal));
			}
		}
		frm.total.value = runTotal;
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>