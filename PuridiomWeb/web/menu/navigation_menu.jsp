<%
String requestURI = request.getRequestURI();
%>
<table id="navTable" border="0" height=28px>

</table>

<SCRIPT>
<!-- Hide script

	var myPage = "<%=requestURI%>";

	var secure = "";

	<% if (true == request.isSecure()) { %>
		secure = "; secure=true";
	<% } %>

	function checkHiddenMenu()
	{
//		if ((myPage.indexOf("/user/") > 0 && myPage.indexOf("/admin/") < 0) || myPage.indexOf("/receipts/") > 0 || myPage.indexOf("/inventory/") > 0 || myPage.indexOf("/catalog/") > 0)
//		if ((myPage.indexOf("/user/") > 0 && myPage.indexOf("/admin/") < 0) || myPage.indexOf("/receipts/") > 0 || myPage.indexOf("/catalog/") > 0)
		if ((myPage.indexOf("/user/") > 0 && myPage.indexOf("/admin/") < 0) || myPage.indexOf("/receipts/") > 0)
		{
			if (!(orgId == "VSE06P" && myPage.indexOf("/receipts/") > 0))
			{
				hideArea("navTable");
				displayArea("menuBarSpacer");
			}
		}
	}

	function displayNavMenu ()
	{
		<%--var myTable = document.getElementById("navTable");
		deleteRow(myTable, 0);

		var myRow = createRow(myTable);
		myRow.height = "25px";
		var myCell;

		var navArray = getNavCookie("navArray");

		if (navArray != undefined)
		{
			var tempArray = navArray.split(",");
			for (var i = 0; i < tempArray.length; i++)
			{
				if (i > 0)
				{
					myCell = createCell(myRow);
					myCell.innerHTML = "<font class='raquo'>&raquo;</font>";
				}
				myCell = createCell(myRow);
				if (i == (tempArray.length-3))	//if this is the last in the array do not display a link
				{
					myCell.innerHTML = tempArray[i+2];
				}
				else
				{
					tempArray[i+1] = tempArray[i+1].replace(":",";");
					myCell.innerHTML = "<a href=\"javascript: setBrowseName(); resetNavCookie(" + i + "); doSubmit('" + tempArray[i] + "', '" + tempArray[i+1] + "');\"> " + tempArray[i+2] + " </a>";
				}
				i+=2;
			}
			hideArea("menuBarSpacer");
		}--%>
	}

	function setNavCookie(page, method, label, bReset)
	{
		var count = -1;
		var navArray = new Array();
		var origNavArray = getNavCookie("navArray");
		var myPage = "<%=requestURI%>";
		var itembrowse = 0;

		if (bReset == null)
		{
			bReset = false;
		}

		if (bReset)
		{
			navArray[0] = "/menu/main_menu.jsp";
			navArray[1] = "DoNothing";
			navArray[2] = "Menu";
			count = 2;
		}
		else if (origNavArray != undefined)
		{
			var tempArray = origNavArray.split(",");
			for (var i = 0; i < tempArray.length; i++)
			{
				if (myPage.indexOf(tempArray[i]) >= 0)
				{
					var browseName = "${esapi:encodeForJavaScript(browseName)}";
					/*
						this browseName and itembrowse count are used in the catalog administration -
						the first time one is browsing items for a particular catalog, the navigation menu not reset
						however, if one returns to the catalog items browse, it should not keeping adding to the navigation menu,
						it should reset and supplierorders
					*/
					if ((browseName != "catalogitem-admin" && browseName != "supplierorders") || itembrowse > 0)
					{
						resetNavCookie(i);
						break;
					}
					else
					{
						itembrowse++;
					}
				}
				navArray[i] = tempArray[i];
				count++;
			}
		}

		navArray[count + 1] = page;
		navArray[count + 2] = method;
		navArray[count + 3] = label;

		<%--document.cookie = "navArray=" + navArray + secure; --%>

		displayNavMenu();
	}

	function getNavCookie( cookieName )
	{
		<%--var cookieJar = document.cookie.split( "; " );
		for( var i = 0; i < cookieJar.length; i++ )
		{
			var myCookie = cookieJar[i].split( "=" );
			if( myCookie[0] == escape( cookieName ) )
			{
				return unescape( myCookie[1] );
			}
		}--%>
		return '';
	}

	function deleteNavCookie( cookieName )
	{
		var cookieDate = new Date ( );
		cookieDate.setTime ( cookieDate.getTime() - 1 );
		<%--document.cookie = cookieName += "=; expires=" + cookieDate.toGMTString();--%>
	}

	function setBrowseCookie( browseName )
	{
		<%--document.cookie = "browseName=" + browseName + secure;
		document.cookie = "browseId=" + browseId + secure;--%>
	}

	function setBrowseName()
	{
		var browseName = getNavCookie("browseName");
		if (browseName != undefined)
		{
			frm.browseName.value = browseName;

			if (!frm.allowBrowse)
			{
				var myTable = document.getElementById("navTable");
				var myRow = createRow(myTable);
				var myCell = createCell(myRow);
				myCell.innerHTML = "<input type=\"hidden\" name=\"allowBrowse\" value=\"\"'>";
			}
			frm.allowBrowse.value = "true";
		}
		var browseId = getNavCookie("browseId");
		if (browseName != undefined)
		{
			if (!frm.browseId)
			{
				var myTable = document.getElementById("navTable");
				var myRow = createRow(myTable);
				var myCell = createCell(myRow);
				myCell.innerHTML = "<input type=\"hidden\" name=\"browseId\" value=\"\"'>";
			}
			frm.browseId.value = browseId;
		}
	}

	/*
		if a user clicks on one of the links, this will reset the navigation menu
	*/
	function resetNavCookie(index)
	{
		if (index == 0)
		{
			// if the user clicked on the very first navigation link, it should delete the menu links
			deleteNavCookie("navArray");
		}
		else
		{
			var navArray = new Array();
			var origNavArray = getNavCookie("navArray");

			if (origNavArray != undefined)
			{
				var tempArray = origNavArray.split(",");
				for (var i = 0; i < index; i++)
				{
					navArray[i] = tempArray[i];
				}
			}

			<%--document.cookie = "navArray=" + navArray + secure;--%>
		}
	}

// End Hide script -->
</SCRIPT>