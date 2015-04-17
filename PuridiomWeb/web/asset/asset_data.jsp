<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table border="0" cellspacing="0" cellpadding="2">
  <tsa:tr>
  	<!-- In this part we show the Tag # for the asset -->
  	<tsa:td field="asset-tagnumber" align="right" noWrap="nowrap" >
  		<tsa:label labelName="asset-tagnumber" defaultString="Tag #" checkRequired="true" />
  	</tsa:td>
  	<% String tagNumber = (String)request.getAttribute("Asset_tagNumber");%>
  	<tsa:td field="asset-tagnumber">
  		<% if(!action.equalsIgnoreCase("itemnew")) { %>
	    <tsa:input type="text" size="30" name="Asset_tagNumber" maxLength="30" value="<%=tagNumber%>" disabled="Y"/>
	    <%}else{ %>
	    <tsa:input type="text" size="30" name="Asset_tagNumber" maxLength="30" value="<%=tagNumber%>"/>
	    <%} %>
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
  	<!--In this part we show the ItemNumber attribute of asset-->
    <tsa:td field="asset-itemnumber" align="right" noWrap="nowrap" >
    	<tsa:label labelName="asset-itemnumber" defaultString="Item Number" />
    </tsa:td>
    <tsa:td>
	    <% if(!action.equalsIgnoreCase("itemnew")) { %>
	    <tsa:input type="text" size="30" name="Asset_itemNumber" maxLength="30" value="<%=Asset_itemNumber%>" disabled="true"/>
	    <%}else{ %>
	    <tsa:input type="text" size="30" name="Asset_itemNumber" maxLength="30" value="<%=Asset_itemNumber%>"/>
	    <%} %>
    </tsa:td>
    <tsa:td colspan="3">&nbsp;</tsa:td>
  </tsa:tr>
  <tsa:tr>
  	<!--In this part we show the description of asset-->
    <tsa:td field="asset-description" align="right" noWrap="nowrap" >
    <tsa:label labelName="asset-description" defaultString="Description" />
    </tsa:td>
    <tsa:td colspan="4" noWrap="nowrap">
    <table border="0" cellspacing="0" cellpadding="0"><tsa:tr>
    <tsa:td noWrap="nowrap">
    <% if(!action.equalsIgnoreCase("itemnew")) { %>
	    <tsa:input type="textarea" name="Asset_descriptionLocal" cols="43" rows="6" disabled="true"><%
	    	if(action.equalsIgnoreCase("new")) {
	    		%>${esapi:encodeForHTML(asset.description)}<%
	    	} else {
	    		%>${esapi:encodeForHTML(Asset_description)}<%
	    	}
	    %></tsa:input>
	<%}else{ %>
	    <tsa:input type="textarea" name="Asset_descriptionLocal" cols="43" rows="6"><%
	    	if(action.equalsIgnoreCase("new")) {
	    		%>${esapi:encodeForHTML(asset.description)}<%
	    	} else {
	    		%>${esapi:encodeForHTML(Asset_description)}<%
	    	}
	    %></tsa:input>
	<% }%>
   </tsa:td>
    <tsa:td>
    <div id="AssetNote" style="visibility: hidden; display: none;">
      <table border="0" cellspacing="0" cellpadding="0"><tsa:tr>
      <tsa:td field="asset-reason" align="right" noWrap="nowrap" >
       &nbsp;<tsa:label labelName="asset-reason" defaultString="Reason" />&nbsp;
       </tsa:td>
      <tsa:td>
      <tsa:input type="textarea" name="AssetNote_stdText" cols="29" rows="6">
      </tsa:input>
      </tsa:td>
      </tsa:tr></table>
    </div>
    </tsa:td>
    </tsa:tr></table>
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
    <tsa:td colspan="6">&nbsp;</tsa:td>
  </tsa:tr>
  <tsa:tr>
    <tsa:td field="asset-assetclass" align="right" >
    <tsa:label labelName="asset-assetclass" defaultString="Asset Class" />
    </tsa:td>
    <%String opAssetClass = asset.getAssetClass();%>
    <tsa:td noWrap="nowrap">
	 <!--In this part we show diferents types that an asset could be-->
      <select name="Asset_assetClass" style="width: 100px">
          <option value="Tangible" <% if (opAssetClass.equalsIgnoreCase("Tangible")) {%> selected<%}%>> <tsa:label labelName="ass-tangible" defaultString="Tangible"></tsa:label></option>
          <option value="Intangible" <% if (opAssetClass.equalsIgnoreCase("Intangible")) {%> selected<%}%>> <tsa:label labelName="ass-intangible" defaultString="Intangible"></tsa:label></option>
          <option value="Hardware" <% if (opAssetClass.equalsIgnoreCase("Hardware")) {%> selected<%}%>> <tsa:label labelName="ass-hardware" defaultString="Hardware"></tsa:label></option>
          <option value="Software" <% if (opAssetClass.equalsIgnoreCase("Software")) {%> selected<%}%>> <tsa:label labelName="ass-software" defaultString="Software"></tsa:label></option>
          <option value="Furniture" <% if (opAssetClass.equalsIgnoreCase("Furniture")) {%> selected<%}%>> <tsa:label labelName="ass-furniture" defaultString="Furniture"></tsa:label></option>
          </select>
        </tsa:td>
        <tsa:td>&nbsp;</tsa:td>
    <tsa:td field="asset-status" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-status" defaultString="Status" />
    </tsa:td>
    <%String opAssetStatus = asset.getAssetStatus();%>
    <tsa:td>
    <!--In this part we show diferents status that an asset could take-->
      <select name="Asset_assetStatus" style="width: 100px" <% if(action.equalsIgnoreCase("new")) { %>onChange="javascript: showHideAssetNote();"<% } %>>
          <option value="Active" <% if (opAssetStatus.equalsIgnoreCase("Active")) {%> selected<%}%>> <tsa:label labelName="ass-active" defaultString="Active"></tsa:label></option>
          <option value="Inactive" <% if (opAssetStatus.equalsIgnoreCase("Inactive")) {%> selected<%}%>> <tsa:label labelName="ass-inactive" defaultString="Inactive"></tsa:label></option>
          <option value="Disposed" <% if (opAssetStatus.equalsIgnoreCase("Disposed")) {%> selected<%}%>> <tsa:label labelName="ass-disposed" defaultString="Disposed"></tsa:label></option>
          <option value="Damaged" <% if (opAssetStatus.equalsIgnoreCase("Damaged")) {%> selected<%}%>> <tsa:label labelName="ass-damaged" defaultString="Damaged"></tsa:label></option>
          <option value="Scrapped" <% if (opAssetStatus.equalsIgnoreCase("Scrapped")) {%> selected<%}%>> <tsa:label labelName="ass-scrapped" defaultString="Scrapped"></tsa:label></option>
          <option value="Stolen" <% if (opAssetStatus.equalsIgnoreCase("Stolen")) {%> selected<%}%>> <tsa:label labelName="ass-stolen" defaultString="Stolen"></tsa:label></option>
          <option value="Sold" <% if (opAssetStatus.equalsIgnoreCase("Sold")) {%> selected<%}%>> <tsa:label labelName="ass-sold" defaultString="Sold"></tsa:label></option>
          <option value="Lost" <% if (opAssetStatus.equalsIgnoreCase("Lost")) {%> selected<%}%>> <tsa:label labelName="ass-lost" defaultString="Lost"></tsa:label></option>
          <option value="Inventory" <% if (opAssetStatus.equalsIgnoreCase("Inventory")) {%> selected<%}%>> <tsa:label labelName="ass-inventory" defaultString="Inventory"></tsa:label></option>
          <option value="Cancelled" <% if (opAssetStatus.equalsIgnoreCase("Cancelled")) {%> selected<%}%>> <tsa:label labelName="ass-cancelled" defaultString="Cancelled"></tsa:label></option>
      </select>
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
    <!--In this part we show the commodity and Cost of asset-->
     <tsa:td field="asset-commodity" align="right" noWrap="nowrap">
    <a href="javascript: browseLookup('Asset_commodity', 'commodity'); void(0);" title="Click here to choose the value for <tsa:label labelName="asset-commodity" defaultString="Commodity" /> for this item or enter the value in the box on the right.">
    <tsa:label labelName="asset-commodity" defaultString="Commodity" />
    </a>
    </tsa:td>
    <tsa:td>
    <tsa:input type="text" size="30" name="Asset_commodity" maxLength="256" value="<%=asset.getCommodity()%>"/>
    </tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <tsa:td field="asset-assetcost" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-assetcost" defaultString="Asset Cost" />
    </tsa:td>
    <tsa:td>
    <tsa:input type="text" size="30" name="Asset_assetCost" maxLength="256" value="<%=HiltonUtility.getFormattedDollar(asset.getAssetCost(), oid)%>" disabled="true" onchange="formatPrice(this)" style="text-align:right"/>
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
    <!--In this part if the access and permissions are right we show the owner and purchaseOrder of asset-->
    <% if (role.getAccessRights("ASTO")>=0) { %>
     <tsa:td field="asset-ownerentity" align="right" noWrap="nowrap">
    <% if (role.getAccessRights("ASTO")>0) { %>
    <a href="javascript: browseLookup('Asset_owner', 'user'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-ownerentity" defaultString="Owner Entity" /> for this item or enter the value in the box on the right.'>
   <tsa:label labelName="asset-ownerentity" defaultString="Owner Entity" /></a>
    <% } else { %>
    <tsa:label labelName="asset-ownerentity" defaultString="Owner Entity" />
    <% } %>
  	</tsa:td>
    <tsa:td>
    <tsa:input type="text" name="Asset_owner" size="30" maxLength="256" value="<%=asset.getOwner()%>" disabled="<%=accessASTO %>" onchange="upperCase(this); getNewInfo('user', this);"></tsa:input>
    </tsa:td>
    <% } %>
	<% if (role.getAccessRights("ASTO")<0) { %>
		<tsa:td colspan="3">&nbsp;</tsa:td>
	<%}else {%>
		<tsa:td>&nbsp;</tsa:td>
	<%}%>
    <tsa:td field="asset-purchaseorder" align="right">
    <a href="javascript: browseOrders(); void(0);">
    <tsa:label labelName="asset-purchaseorder" defaultString="Purchase Order" /></a>
    </tsa:td>
    <tsa:td>
    <tsa:input type="text" name="Asset_purchaseOrder" size="30" maxLength="15" value="<%=asset.getPurchaseOrder()%>"/>
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
	<!--In this part if the access and permissions are right we show the owner name of asset-->
    <% if (role.getAccessRights("ASTO")>=0) { %>
    <tsa:td field="asset-ownerentity" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-ownerentityname" defaultString="Owner Name" />
    </tsa:td>
    <tsa:td><tsa:input type="text" name="as_ownerName" size="30" maxLength="256" value="<%=owner.getDisplayName()%>" disabled="true"/></tsa:td>
    <% } %>
  </tsa:tr>
  <tsa:tr>
  	<!--In this part we show the manufacturer of asset-->
    <tsa:td field="asset-manufacturer" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-manufacturer" defaultString="Manufacturer" />
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_manufacturer" size="30" maxLength="20" value="<%=asset.getManufacturer()%>" /></tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <% if (role.getAccessRights("ASTP")>=0) { %>
    <tsa:td field="asset-purchasevendor" align="right" noWrap="nowrap">
    <% if (role.getAccessRights("ASTP")>0) { %>
    <a href="javascript: browseStd('Asset_poVendor', 'ASTP'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-purchasevendor" defaultString="Purchase Vendor" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-purchasevendor" defaultString="Purchase Vendor" /></a>
    <% } else { %>
    <tsa:label labelName="asset-purchasevendor" defaultString="Purchase Vendor" /><% } %></tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_poVendor" size="30" maxLength="256" value="<%=asset.getPoVendor()%>" disabled="<%=accessASTP%>"></tsa:input></tsa:td>
    <% } %>
  </tsa:tr>
  <tsa:tr>
    <!--In this part we show the model and the contractService of asset but this parameters has to follow some rules before access-->
    <tsa:td field="asset-model" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-model" defaultString="Model" />
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_model" size="30" maxLength="20" value="<%=asset.getModel()%>"/></tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <% if (role.getAccessRights("ASTC")>=0) { %>
    <tsa:td field="asset-servicecontractno" align="right" noWrap="nowrap">
    <%if (role.getAccessRights("ASTC")>0) { %>
    <a href="javascript: browseStd('Asset_contractService', 'ASTC'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-servicecontractno" defaultString="Service Contract No" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-servicecontractno" defaultString="Service Contract No" /></a>
    <% } else { %>
    <tsa:label labelName="asset-servicecontractno" defaultString="Service Contract No" />
    <% } %>
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_contractService" size="30" maxLength="15" value="<%=asset.getContractService()%>" disabled="<%=accessASTC%>"></tsa:input> </tsa:td>
    <% } %>
  </tsa:tr>
  <tsa:tr>
	<!--In this part we show the serialNumber and the contractorName of asset-->
    <tsa:td field="asset-serialnumber" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-serialnumber" defaultString="Serial Number" />
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_serialNumber" size="30" maxLength="25" value="<%=asset.getSerialNumber()%>"/></tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <tsa:td field="asset-contractorname" align="right" noWrap="nowrap">
    <a href="javascript: browseStd('Asset_contractorName', 'ASTN'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-contractorname" defaultString="Contractor Name" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-contractorname" defaultString="Contractor Name" /></a>
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_contractorName" size="30" maxLength="15" value="<%=asset.getContractorName()%>" /></tsa:td>
  </tsa:tr>
  <tsa:tr>
	<!--In this part if the access and permissions are right we show the deprecMethod and upgradeReqs of asset-->
    <% if (role.getAccessRights("ASTD")>=0) { %>
    <tsa:td field="asset-depmethod" align="right" noWrap="nowrap">
    <% if (role.getAccessRights("ASTD")>0) { %>
    <a href="javascript: browseStd('Asset_deprecMethod', 'ASTD'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-depmethod" defaultString="Dep Method" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-depmethod" defaultString="Dep Method" /></a>
    <% } else { %>
    <tsa:label labelName="asset-depmethod" defaultString="Dep Method" />
    <% } %>
    </tsa:td>
    <tsa:td field="asset-depmethod"><tsa:input type="text" name="Asset_deprecMethod" size="30" maxLength="256" value="<%=asset.getDeprecMethod()%>" disabled="<%=accessASTD%>"></tsa:input></tsa:td>
    <% } %>
	<% if (role.getAccessRights("ASTD")<0) { %>
		<tsa:td colspan="3">&nbsp;</tsa:td>
	<%}else {%>
		<tsa:td>&nbsp;</tsa:td>
	<%}%>
	<tsa:td field="asset-dummySpace" colspan="2">&nbsp;</tsa:td>
    <% if (role.getAccessRights("ASTU")>=0) { %>
    <tsa:td field="asset-upgradereqs" align="right" noWrap="nowrap">
    <% if (role.getAccessRights("ASTU")>0) { %><a href="javascript: browseStd('Asset_upgradeReqs', 'ASTU'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-upgradereqs" defaultString="Upgrade Reqs" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-upgradereqs" defaultString="Upgrade Reqs" /></a>
    <% } else { %>
    <tsa:label labelName="asset-upgradereqs" defaultString="Upgrade Reqs" />
    <% } %>
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_upgradeReqs" size="30" maxLength="256" value="<%=asset.getUpgradeReqs()%>" disabled="<%=accessASTU%>"></tsa:input></tsa:td>
    <% } %>
  </tsa:tr>
  <tsa:tr>
	<!--In this part if the access and permissions are right we show the deprecTerm and the securityCode of asset-->
    <tsa:td field="asset-depterm" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-depterm" defaultString="Dep Term" />
     </tsa:td>
    <tsa:td field="asset-depterm">
    <tsa:input type="text" name="Asset_deprecTerm" size="30" maxLength="256" value="<%=HiltonUtility.getFormattedDollar(asset.getDeprecTerm(), oid)%>" onchange="formatPrice(this)" style="text-align:right"/>
    </tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <% if (role.getAccessRights("ASTS")>=0) { %>
    <tsa:td field="asset-securitycode" align="right" noWrap="nowrap">
    <% if (role.getAccessRights("ASTS")>0) { %>
    <a href="javascript: browseStd('Asset_securityCode', 'ASTS'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-securitycode" defaultString="Security Code" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-securitycode" defaultString="Security Code" /></a>
    <% } else { %>
    <tsa:label labelName="asset-securitycode" defaultString="Security Code" />
    <% } %>
    </tsa:td>
    <tsa:td field="asset-securitycode" ><tsa:input type="text" name="Asset_securityCode" size="30" maxLength="256" value="<%=asset.getSecurityCode()%>" disabled="<%=accessASTS%>"></tsa:input></tsa:td>
    <% } %>
  </tsa:tr>
  <tsa:tr>
    <tsa:td colspan="6">&nbsp;</tsa:td>
  </tsa:tr>
  <tsa:tr>
	<!--In this part we show and enter the value for Asset_lease-->
    <tsa:td field="asset-leased" align="left" noWrap="nowrap" colspan="6">
    <% if (asset.getLease().equalsIgnoreCase("L")) { %>
    <tsa:input type="radio" name="Asset_lease" value="L"  onclick="displayArea('AssetLeased');" />
    <%}else{ %>
    <tsa:input type="radio" name="Asset_lease" value="L" checked="true" onclick="displayArea('AssetLeased');" />
    <%} %>
      <tsa:label labelName="asset-leased" defaultString="Leased" />
     <% if (asset.getLease().equalsIgnoreCase("L")) { %>
    <tsa:input type="radio" name="Asset_lease" value="P" onclick="hideArea('AssetLeased');"/>
    <%}else{ %>
    <tsa:input type="radio" name="Asset_lease" value="P" checked="true" onclick="hideArea('AssetLeased');" />
    <%} %>
      <tsa:label labelName="asset-purchased" defaultString="Purchased" />
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
    <tsa:td colspan="6">
    <!--In this part we show the diferents kind of Asset_lease-->
    <div id="AssetLeased" style="visibility: hidden; display: none;">
      <table border="0" cellspacing="0" cellpadding="2" bgcolor="#F5F5F5">
      <tsa:tr bgcolor="#F5F5F5">
        <tsa:td field="asset-leasetype" align="right" noWrap="nowrap">
        <tsa:label labelName="asset-leasetype" defaultString="Type Of Lease" />
        </tsa:td>
        <tsa:td><tsa:input type="text" name="Asset_leaseType" size="30" maxLength="1" value="<%=asset.getLeaseType()%>" />
        </tsa:td>
        <tsa:td>&nbsp;</tsa:td>
        <tsa:td field="asset-leaseterm" align="right" noWrap="nowrap">
         <tsa:label labelName="asset-leaseterm" defaultString="Lease Term" />
        </tsa:td>
        <tsa:td>
        <tsa:input type="text" name="Asset_leaseTerm" size="30" maxLength="25" value="<%=asset.getLeaseTerm()%>" />
        </tsa:td>

      </tsa:tr>
      <tsa:tr bgcolor="#F5F5F5">
        <tsa:td field="asset-financing" align="right" noWrap="nowrap">
        <tsa:label labelName="asset-financing" defaultString="Financing" />
      	</tsa:td>
        <tsa:td>
        <tsa:input type="text" name="Asset_financing" size="30" maxLength="25" value="<%=HiltonUtility.getFormattedDollar(asset.getFinancing(), oid)%>" onchange="formatPrice(this)" style="text-align:right" />
        </tsa:td>
        <tsa:td>&nbsp;</tsa:td>
        <tsa:td field="asset-leasingcompany" align="right" noWrap="nowrap">
        &nbsp;&nbsp;<tsa:label labelName="asset-leasingcompany" defaultString="Leasing Company" />
         </tsa:td>
        <tsa:td>
        <tsa:input type="text" name="Asset_leasingCompany" size="30" maxLength="25" value="<%=HiltonUtility.getFormattedDollar(asset.getLeasingCompany(), oid)%>" onchange="formatPrice(this)" style="text-align:right"/>
        </tsa:td>
      </tsa:tr>
      <tsa:tr bgcolor="#F5F5F5">
        <tsa:td field="asset-renewaldate" align="right" noWrap="nowrap">
        <tsa:label labelName="asset-renewaldate" defaultString="Renewal Date" />
        </tsa:td>
        <tsa:td>
        <tsa:input type="text"  name="Asset_renewalDate" size="23" maxLength="10" value='<%= (action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(asset.getMonthlyPayment(),oid, userDateFormat) : \"\" %>' />
        <a href="javascript: show_calendar('Asset_renewalDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
        </tsa:td>
        <tsa:td>&nbsp;</tsa:td>
        <tsa:td field="asset-monthlypayment" align="right" noWrap="nowrap">
        <tsa:label labelName="asset-monthlypayment" defaultString="Monthly Payment" />
        </tsa:td>
        <tsa:td><tsa:input type="text" name="Asset_monthlyPayment" size="23" maxLength="10" value='<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(asset.getMonthlyPayment(),oid, userDateFormat): \"\"%>' />
        <a href="javascript: show_calendar('Asset_monthlyPayment', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
        </tsa:td>
      </tsa:tr>
      </table>
    </div>
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
     <tsa:td colspan="6">&nbsp;</tsa:td>
  </tsa:tr>
  <tsa:tr>
    <!--In this part we show the date in service of asset in a respective format-->
    <tsa:td field="asset-dateinservice" align="right" noWrap="nowrap">
     <tsa:label labelName="asset-dateinservice" defaultString="Date in Service" />
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_dateInService" size="23" maxLength="10" value='<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(asset.getDateInService(),oid, userDateFormat): \"\" %>' />
    <a href="javascript: show_calendar('Asset_dateInService', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
    </tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <!--In this part we show the date when the warranty start -->
    <tsa:td field="asset-warrantystart" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-warrantystart" defaultString="Warranty Start" />
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_warrantyStart" size="23" maxLength="10" value='<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(asset.getWarrantyStart(),oid, userDateFormat): \"\" %>'/>
    <a href="javascript: show_calendar('Asset_warrantyStart', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
	<!--In this part we show the date when the asset has been receivedt -->
    <tsa:td field="asset-datereceived" align="right" noWrap="nowrap">
     <tsa:label labelName="asset-datereceived" defaultString="Date Received" />
     </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_dateReceived" size="23" maxLength="10" value='<%=(action.equalsIgnoreCase(\"new\"))? HiltonUtility.getFormattedDate(asset.getDateReceived(),oid, userDateFormat):\"\" %>' />
    <a href="javascript: show_calendar('Asset_dateReceived', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
    </tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <!--In this part we show the date when the warranty finish -->
    <tsa:td field="asset-warrantyend" align="right" noWrap="nowrap">
    <tsa:label labelName="asset-warrantyend" defaultString="Warranty End" />
    </tsa:td>
    <tsa:td><tsa:input type="text" name="Asset_warrantyEnd" size="23" maxLength="10" value='<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(asset.getWarrantyEnd(),oid, userDateFormat) : \"\" %>'/>
    <a href="javascript: show_calendar('Asset_warrantyEnd', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
    </tsa:td>
  </tsa:tr>
  <tsa:tr>
    <tsa:td colspan="6">&nbsp;</tsa:td>
  </tsa:tr>
  <% if (role.getAccessRights("ASTUDFS")>=0) { %>
  <tsa:tr>
    <tsa:td field="asset-udf1" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf1', 'AUD1'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf1" defaultString="UDF 1" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf1" defaultString="UDF 1" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf1" defaultString="UDF 1" checkRequired="true" noinstance="yes" />
    <% } %>
    </tsa:td>
    <tsa:td field="asset-udf1"><tsa:input type="text" name="Asset_assetUdf1" size="30" maxLength="256" value="<%=asset.getAssetUdf1()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <tsa:td field="asset-udf2" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf2', 'AUD2'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf2" defaultString="UDF 2" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf2" defaultString="UDF 2" /></a>
    <% } else { %>
   	<tsa:label labelName="asset-udf2" defaultString="UDF 2" />
   	<% } %>
    </tsa:td>
    <tsa:td field="asset-udf2"><tsa:input type="text" name="Asset_assetUdf2" size="30" maxLength="256" value="<%=asset.getAssetUdf2()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
  </tsa:tr>
  <tsa:tr>
    <tsa:td field="asset-udf3" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %><a href="javascript: browseStd('Asset_assetUdf3', 'AUD3'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf3" defaultString="UDF 3" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf3" defaultString="UDF 3" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf3" defaultString="UDF 3" />
    <% } %>
    </tsa:td>
    <tsa:td field="asset-udf3"><tsa:input type="text" name="Asset_assetUdf3" size="30" maxLength="256" value="<%=asset.getAssetUdf3()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <tsa:td field="asset-udf4" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf4', 'AUD4'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf4" defaultString="UDF 4" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf4" defaultString="UDF 4" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf4" defaultString="UDF 4" />
    <% } %>
    </tsa:td>
    <tsa:td field="asset-udf4"><tsa:input type="text" name="Asset_assetUdf4" size="30" maxLength="256" value="<%=asset.getAssetUdf4()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
  </tsa:tr>
  <tsa:tr>
    <tsa:td field="asset-udf5" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf5', 'AUD5'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf5" defaultString="UDF 5" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf5" defaultString="UDF 5" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf5" defaultString="UDF 5" /><% } %>
    </tsa:td>
    <tsa:td field="asset-udf5"><tsa:input type="text" name="Asset_assetUdf5" size="30" maxLength="256" value="<%=asset.getAssetUdf5()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <tsa:td field="asset-udf6" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf6', 'AUD6'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf6" defaultString="UDF 6" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf6" defaultString="UDF 6" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf6" defaultString="UDF 6" /><% } %>
    </tsa:td>
    <tsa:td field="asset-udf6"><tsa:input type="text" name="Asset_assetUdf6" size="30" maxLength="256" value="<%=asset.getAssetUdf6()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
  </tsa:tr>
  <tsa:tr>
    <tsa:td field="asset-udf7" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf7', 'AUD7'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf7" defaultString="UDF 7" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf7" defaultString="UDF 7" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf7" defaultString="UDF 7" />
    <% } %>
    </tsa:td>
    <tsa:td field="asset-udf7"><tsa:input type="text" name="Asset_assetUdf7" size="30" maxLength="256" value="<%=asset.getAssetUdf7()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <tsa:td field="asset-udf8" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf8', 'AUD8'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf8" defaultString="UDF 8" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf8" defaultString="UDF 8" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf8" defaultString="UDF 8" /><% } %>
    </tsa:td>
    <tsa:td field="asset-udf8"><tsa:input type="text" name="Asset_assetUdf8" size="30" maxLength="256" value="<%=asset.getAssetUdf8()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
  </tsa:tr>
  <tsa:tr>
    <tsa:td field="asset-udf9" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf9', 'AUD9'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf9" defaultString="UDF 9" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf9" defaultString="UDF 9" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf9" defaultString="UDF 9" />
    <% } %>
    </tsa:td>
    <tsa:td field="asset-udf9"><tsa:input type="text" name="Asset_assetUdf9" size="30" maxLength="256" value="<%=asset.getAssetUdf9()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
    <tsa:td>&nbsp;</tsa:td>
    <tsa:td field="asset-udf10" align="right">
    <% if (role.getAccessRights("ASTUDFS")>0) { %>
    <a href="javascript: browseStd('Asset_assetUdf10', 'AUDA'); void(0);" title='Click here to choose the value for <tsa:label labelName="asset-udf10" defaultString="UDF 10" /> for this item or enter the value in the box on the right.'>
    <tsa:label labelName="asset-udf10" defaultString="UDF 10" /></a>
    <% } else { %>
    <tsa:label labelName="asset-udf10" defaultString="UDF 10" /><% } %>
    </tsa:td>
    <tsa:td field="asset-udf10"><tsa:input type="text" name="Asset_assetUdf10" size="30" maxLength="256" value="<%=asset.getAssetUdf10()%>" disabled="<%=accessASTUDFS%>"></tsa:input></tsa:td>
  </tsa:tr>
  <% } %>
</table>

<tsa:hidden name="Asset_dateEntered" value=""/>
<tsa:hidden name="Asset_totalCost" value=""/>
<tsa:hidden name="Asset_icText" value=""/>
<tsa:hidden name="Asset_imageFile" value=""/>
<tsa:hidden name="Asset_icReceipt" value=""/>
<tsa:hidden name="Asset_icLineKey" value=""/>
<tsa:hidden name="Asset_itemLocation" value=""/>
<tsa:hidden name="Asset_icAccount" value=""/>
<tsa:hidden name="Asset_icDsbHeader" value=""/>
<tsa:hidden name="Asset_icDsbLine" value=""/>
<tsa:hidden name="Asset_localCurrencyPrice" value=""/>
<tsa:hidden name="Asset_originalCost" value=""/>
<tsa:hidden name="Asset_exitValue" value=""/>
<tsa:hidden name="Asset_dateInactive" value=""/>
<tsa:hidden name="Asset_lastChgBy" value=""/>
<tsa:hidden name="Asset_dateChanged" value=""/>
<tsa:hidden name="Asset_statusOriginal" value=""/>
<tsa:hidden name="Asset_printed" value="<%=asset.getPrinted()%>"/>
<tsa:hidden name="Asset_fiscalYear" value=""/>
