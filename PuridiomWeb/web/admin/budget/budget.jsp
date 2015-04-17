<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>

<%
	PropertiesManager	propertiesManager	= PropertiesManager.getInstance(oid);

	// Labels to the User Defined Fields (1-15)
	String	labeludf[]	= new String[15];

	// User Defined Fields (1-15)
	String	budgetudf[]	= new String[15];

			for (int x = 0; x < 15; x++) {
				labeludf[x]	= propertiesManager.getProperty("BUDGET","LABEL UDF" + (x + 1),"");
				budgetudf[x] =	propertiesManager.getProperty("BUDGET","BUDGET UDF" + (x + 1),"");
			}


	String	authorityudf	= propertiesManager.getProperty("BUDGET","AUTHORITY_UDF","");
	String	authority		= propertiesManager.getProperty("BUDGET","AUTHORITY","");
	String	budgetyear		= propertiesManager.getProperty("BUDGET","BUDGETYEAR","");
	String	checkbudget		= propertiesManager.getProperty("BUDGET","CHECKBUDGET","");
	String	preencumberence	= propertiesManager.getProperty("BUDGET","PREENCUMBERENCE","");
	String	encumberence	= propertiesManager.getProperty("BUDGET","ENCUMBERENCE","");
	String	expense			= propertiesManager.getProperty("BUDGET","EXPENSE","");
	String	autoexpense		= propertiesManager.getProperty("BUDGET","AUTOEXPENSE","");
	String	tolerance		= propertiesManager.getProperty("BUDGET","TOLERANCE","");
	String	checkerrorbudget= propertiesManager.getProperty("BUDGET","CHECKBUDERRORGET","");
	String	existserr	= propertiesManager.getProperty("BUDGET","BUDGET_EXISTSERR","");
	String	overerr		= propertiesManager.getProperty("BUDGET","BUDGET_OVERERR","");
	String	autherr		= propertiesManager.getProperty("BUDGET","BUDGET_AUTHERR","");
	String	tolerr		= propertiesManager.getProperty("BUDGET","BUDGET_TOLERR","");
%>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="qtyShown" value="2"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<!--In this part we have a function to attach a document-->
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budget-setup", "Budget Setup")%></div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="600px">
    <tr>
      <td align="center" valign="top">
      	<%@ include file="/admin/budget/budget_setup.jsp" %>
      </td>
	</tr>
	</table>
  </td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=2 width="680px">
<tr>
		<td align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align="center">
				<a href="javascript: setupBudgetCenter(); void(0);">
				<img class=button src="<%=contextPath%>/images/button_save.gif" border=0>
				</a>
			</td>
			<td align="center">
				<a href="javascript: returnToBudgetMenu(); void(0);">
				<img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0>
				</a>
			</td>
		</tr>
		</table>
		</td>
</tr>
</table>
<%@ include file="/system/footer.jsp" %>

<script value=JavaScript>
<!-- Hide script

  var frm = document.purchaseform;

setNavCookie("/admin/budget/budget.jsp", "DoNothing", "Budget Setup");

function returnToBudgetMenu() {
	doSubmit('admin/budget/budget_menu.jsp','DoNothing');
}

function changeLabelState(pos,est) {
	frm.label[pos].style.border = est;
}

function editLabel(pos) {
	changeLabelState(pos,'inset');
	frm.label[pos].readOnly=false;
	frm.label[pos].focus();
	displayArea('labelSaveUDF'+pos);
	hideArea('labelEditUDF'+pos);
}

function saveLabel(pos) {
	changeLabelState(pos,'none');
	frm.label[pos].readOnly=true;
	frm.label[pos].blur();
	displayArea('labelEditUDF'+pos);
	hideArea('labelSaveUDF'+pos);
	if(frm.label[pos].value!="")
		frm.UDF[pos].disabled = false;
	else
		frm.UDF[pos].disabled = true;
}

function showOneHiddenMore() {
	var qty = frm.qtyShown.value;
	if (qty < 15)
		qty++;
	displayArea('dropUp');
	if (qty < 15)
		displayArea('dropDown');
	else
		hideArea('dropDown');
	displayArea('udffull'+(eval(qty)-1));
	frm.qtyShown.value = qty;
}

function displayHiddens() {
	var qty = frm.qtyShown.value;
	if (qty > 2)
		qty--;
	if (qty > 2)
		displayArea('dropUp');
	else
		hideArea('dropUp');
	displayArea('dropDown');
	hideArea('udffull'+(eval(qty)));
	frm.qtyShown.value = qty;
}

function setupBudgetCenter() {
	var newInputField = "<input type=hidden name='Property_section' value='BUDGET'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF1'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF2'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF3'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF4'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF5'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF6'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF7'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF8'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF9'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF10'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF11'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF12'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF13'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF14'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='LABEL UDF15'>";

	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF1'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF2'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF3'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF4'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF5'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF6'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF7'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF8'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF9'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF10'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF11'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF12'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF13'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF14'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET UDF15'>";

	newInputField = newInputField + "<input type=hidden name='Property_property' value='AUTHORITY'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGETYEAR'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='CHECKBUDGET'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='PREENCUMBERENCE'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='ENCUMBERENCE'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='EXPENSE'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='AUTOEXPENSE'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='TOLERANCE'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='AUTHORITY_UDF'>";

	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET_EXISTSERR'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET_OVERERR'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET_AUTHERR'>";
	newInputField = newInputField + "<input type=hidden name='Property_property' value='BUDGET_TOLERR'>";

	<% for(int x=0; x<15; x++) {
    		if(Utility.isEmpty(labeludf[x])) {  %>
    			frm.label[<%=x%>].value = "" ;
    			frm.UDF[<%=x%>].value = "" ;
<%    	}
    	}
    	%>

	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[0].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[1].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[2].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[3].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[4].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[5].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[6].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[7].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[8].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[9].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[10].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[11].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[12].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[13].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.label[14].value+"'>";

	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[0].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[1].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[2].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[3].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[4].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[5].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[6].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[7].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[8].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[9].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[10].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[11].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[12].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[13].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.UDF[14].value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.authority.value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.budgetYear.value+"'>";
	var checkOptionValue = "";
	if(frm.checkbudget[0].checked) checkOptionValue = frm.checkbudget[0].value;
	if(frm.checkbudget[1].checked) checkOptionValue = frm.checkbudget[1].value;
	if(frm.checkbudget[2].checked) checkOptionValue = frm.checkbudget[2].value;
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+checkOptionValue+"'>";
	if(!frm.preencumberence.checked) frm.preencumberence.value="N";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.preencumberence.value+"'>";
	if(!frm.encumberence.checked) frm.encumberence.value="N";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.encumberence.value+"'>";
	if(!frm.expense.checked) frm.expense.value="N";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.expense.value+"'>";
	if(!frm.autoexpense.checked) frm.autoexpense.value="N";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.autoexpense.value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.tolerance.value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.authorityudf.value+"'>";

	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.existserr.value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.overerr.value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.autherr.value+"'>";
	newInputField = newInputField + "<input type=hidden name='Property_value' value='"+frm.tolerr.value+"'>";

	setHiddenFields(newInputField);
	doSubmit('admin/budget/budget_menu.jsp', 'PropertyUpdate');
}

function intFilter(objectFld) {
    var cmp = "0123456789";
    var x = objectFld.value;
    var w = "";

    for ( var i = 0; i < x.length; i++) {
      tst = x.substring(i,i+1);
      if (cmp.indexOf(tst) >= 0) { w += tst; }
    }
    if ( w.length != x.length)
      objectFld.value = w;
    if ( w.length == 0 )
      w = "0";
    return w;
}

function yearFilter(objectFld) {
	var dToday = new Date;
	var yToday = dToday.getYear();
	var y = intFilter(objectFld);
	if (eval(y)<eval(yToday))
		objectFld.value = yToday;
	else
		objectFld.value = y;
}

function activeAccount(obj) {
	frm.authorityudf.style.visibility = "hidden";
	if (obj.value=="Account UDF")
		frm.authorityudf.style.visibility = "visible";
}


// End Hide script -->
</script>