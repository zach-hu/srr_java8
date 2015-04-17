<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.rule.ui.*" %>

<%List rulesList = (List)request.getAttribute("rulesList");
if(rulesList == null){		rulesList = new ArrayList();		}

String formType = (String)request.getAttribute("formType");
if(formType == null){	formType = "req";	}

String sOid = (String)request.getAttribute("as_organizationId");
if(sOid == null){	sOid = "TEST";	}

String ruleFileName = (String)request.getAttribute("ruleFileName");
if(ruleFileName == null){	ruleFileName = "";	}
%>
<table border="0" cellpadding="3" cellspacing="0" width="680px">
  <tr>
		<th colspan="2" class="formtype" nowrap valign="top" align="center">Validation Rules</th>
	</tr>
	<tr>
		<td valign="top" width="20%">Select Process.</td>
		<td>
			<select name="form_option" size="4" onchange="checkOption();">
                        <option <% if (formType.equals("req")) {%> SELECTED <%}%> value="req">Requisition</option>
                        <option <% if (formType.equals("po")) {%> SELECTED <%}%> value="po">Orders</option>
                        <option <% if (formType.equals("rfq")) {%> SELECTED <%}%> value="rfq">RFQs</option>
                    </select>
		</td>
		<td>Enter Organization Id:</td>
		<td><input type="text" name="as_organizationId" value="<%=sOid%>" onchange="upperCase(this);"></input></td>
  </tr>
</table>
<tsa:hidden name="formType" value="<%=formType%>"/>
<tsa:hidden name="ruleFileName" value="<%=ruleFileName%>"/>
<br>
<table border="0" cellpadding="3" cellspacing="0" align="center">
<tr>
  <td align="center"><a href="javascript: submitThis(); void(0);"><img src="<%=contextPath%>/images/button_ok.gif" border="0" class="button"></a></td>
  <td align="center"><a href="javascript: updateRules(); void(0);"><img src="<%=contextPath%>/images/button_save.gif" border="0" class="button"></a></td>
</tr>
<%for (int i = 0; i < rulesList.size(); i++)
{
	RuleElement rule = (RuleElement)rulesList.get(i);%>
	<tr>
	  <td colspan="2" align="left"><%=rule.getAlias()%>
	  	 <tsa:hidden name="alias" value="<%=rule.getAlias()%>"/>
	  	<table border="1">
	  		<tr>
	  			<td width="20%">File Name:</td>
	  			<td><input type="text" name="filename" value="<%=rule.getFileName()%>" size="30"></td>
	  		</tr>
	  		<tr>
	  			<td width="20%">Mesage:</td>
	  			<td><textarea name="msg" cols="80" rows="3">${esapi:encodeForHTML(rule.msg)}</textarea></td>
	  		</tr>
	  		<tr>
	  			<td width="20%">Severity:</td>
	  			<td><select name="severity" size="1">
                        <option <% if (rule.getSeverity().equals("E")) {%> SELECTED <%}%> value="E">Error</option>
                        <option <% if (rule.getSeverity().equals("W")) {%> SELECTED <%}%> value="W">Warning</option>
                    </select>
	  			<!--<tsa:hidden name="severity" value="<%=rule.getSeverity()%>"/></td>-->
	  		</tr>
	  		<tr>
	  			<td width="20%">Order:</td>
	  			<td><input type="text" name="order" value="<%=rule.getOrder()%>"></td>
	  		</tr>
	  		<tr>
	  			<td width="20%">Enabled:</td>
	  			<td><select name="enabled" size="1">
                        <option <% if (rule.isEnabled()) {%> SELECTED <%}%> value="Y">Yes</option>
                        <option <% if (!rule.isEnabled()) {%> SELECTED <%}%> value="N">No</option>
                    </select>
                    <!--<tsa:hidden name="enabled" value="<%=rule.isEnabled()%>"/></td>-->
                </td>
	  		</tr>
	  	</table>
	  </td>
	</tr>
	<tr><td colspan="2"><br></td></tr>
<%}%>
<%if(rulesList.size() > 2)
{%>
	<tr>
		<td colspan="2" align="center">
		  	<table border="0">
		  		<tr>
			  		<td colspan="2" align="center"><a href="javascript: submitThis(); void(0);"><img src="<%=contextPath%>/images/button_ok.gif" border="0" class="button"></a></td>
			  		<td align="center"><a href="javascript: updateRules(); void(0);"><img src="<%=contextPath%>/images/button_save.gif" border="0" class="button"></a></td>
		  		</tr>
		  	</table>
	  	</td>
	</tr>
<%}%>
</table>

<br><br><br><br>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
  var frm = document.purchaseform;
  
  	function checkOption()
    {
    	frm.organizationId.value = frm.as_organizationId.value;
    	frm.formType.value = frm.form_option.value;
    }
    
    function updateRules() 
  {
    doSubmit('tsaadmin/validation-rules-list.jsp', 'RuleListerUpdater;RuleLister');
  }

  function submitThis() 
  {
    doSubmit('tsaadmin/validation-rules-list.jsp', 'RuleLister');
  }
//-->
</script>