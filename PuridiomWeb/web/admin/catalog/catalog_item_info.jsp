<%--@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" --%>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String imageFile = HiltonUtility.ckNull((String)request.getAttribute("CatalogItem_imageFile"));
	String itemNumber = HiltonUtility.ckNull((String)request.getAttribute("CatalogItem_id_itemNumber"));

	if (HiltonUtility.isEmpty(imageFile)) {
		String oid = (String) request.getAttribute("organizationId");
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
		imageFile = propertiesManager.getProperty("MISC", "CATALOGITEMURL", "");
	}

	String catalogItemUrlInfo = "";
	if (HiltonUtility.isEmpty(imageFile) && HiltonUtility.isEmpty(itemNumber)) {
		catalogItemUrlInfo = imageFile + itemNumber;
	}

	if (!HiltonUtility.isEmpty(catalogItemUrlInfo)) {
%>
<iframe id="myframe" name="myframe" src="<%=catalogItemUrlInfo%>"
	style="height: 100%; width: 100%; margin: 0 0 0 0; border: 0px;">
</iframe>
<%	} %>