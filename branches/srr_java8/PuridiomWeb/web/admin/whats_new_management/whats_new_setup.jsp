<%@page language="java" errorPage="/system/error.jsp" %>
<%@include file="/system/prevent_caching.jsp" %>
<%@include file="/system/context_path.jsp" %>
<%@include file="/system/header.jsp"%>
<%@page import="com.tsa.puridiom.property.PropertiesManager"%>
<%@page import="com.tsa.puridiom.entity.News"%>
<%@page import="com.tsa.puridiom.menu.MenuManager"%>
<%@page import="java.io.File"%>

<%
  List whatsNew = MenuManager.getNews(oid);
  String riaYahoo = contextPath + File.separator + "ria"
      + File.separator + "yahoo";
%>


<script type="text/javascript" src="<%=contextPath%>/scripts/yui/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/event/event-min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/dom/dom-min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/animation/animation-min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/dragdrop/DDList.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/container/container-min.js"></script>

<script type="text/javascript">

YAHOO.example.DDApp = function() {
    var Dom = YAHOO.util.Dom;
    var DDM = YAHOO.util.DragDropMgr;
    return {
        init: function() {
      <%
        int	newsCount = whatsNew.size();
        for (int i=0; i < newsCount; i++) {
          News news = (News) whatsNew.get(i);
        %>
        new YAHOO.example.DDList("news_<%=i%>");
      <%}%>
            DDM.mode = 1; 		// 0: point   1: intersect
        }
    };
} ();

YAHOO.util.Event.addListener(window, "load", YAHOO.example.DDApp.init);
</script>

<style>
  ul {
    list-style: none;
    clear: both;
  }
  li {
	margin: 5px 0px 5px 0px;
    padding: 5px 0px 5px 0px;
	clear: both;
    cursor: move;
    min-height: 2.5em;
  }
  .itemNumber {
	float: left;
	width: 2em;
	height: 2em;
	padding-top: 3px;
	text-align: center;
	background-color: #F5F5F5;
	border: 1px solid #C0C0C0;
  }
  .itemNumber span {
	font-weight: bold;
	font-size: 1.1em
  }
  .news {
    padding-left: 0.5em;
    float:left;
  	height: 2.5em;
    width: 93%;
    background-color: #FCFCFC;
  }
  .delete {
  	float:right;
  	height: 2.5em;
	cursor: pointer;
  }
</style>
<br><br>
<table border=0 cellspacing=0 cellpadding=0 width=680px>
  <tr>
    <td align="center"><b> Welcome to the Whats New Management!</b><br><br></td>
  </tr>
</table>

<ul style="width:60%" id="newsList">
  <%
      for (int i = 0; i < newsCount; i++) {
      News news = (News) whatsNew.get(i);
  %>
  <li id="news_<%=i%>">
    <div class="itemNumber">
      <a href="javascript: handleDiv('newsAtributes_<%=news.getIcNews()%>');"><%=i + 1%></a>
    </div>
    <div class="news">
      <span class="<%=news.getNewsFont()%>"><%=news.getNewsText()%></span>
    </div>
    <div name="newsAtributes_<%=news.getIcNews()%>" id="newsAtributes_<%=news.getIcNews()%>" style="clear: both; display: none;">
      <table border=0 cellspacing=0 cellpadding=0 width="98%" style="margin-top: 5px">
        <tr>
          <td align="right" width=10%>Text: </td>
          <td align="left" width=90%>
            <input type=text name="whatsNewText_<%=news.getIcNews()%>_<%=i%>" class='<%=news.getNewsFont()%>' value="<%=news.getNewsText()%>" size=80 maxLength=150 onclick="javascript:enable(this)"></td>
        </tr>
        <tr>
          <td align="right" width=10%>Font: </td>
          <td align="left" width=90%>
            <select name="whatsNewFont_<%=news.getIcNews()%>_<%=i%>" class='<%=news.getNewsFont()%>' onclick="javascript:enable(this)">
				<option value="browseHdr" <% if (news.getNewsFont().indexOf("browseHdr") >= 0){ out.println("selected"); } %>>browseHdr</option>
				<option value="error" <% if (news.getNewsFont().indexOf("error") >= 0){ out.println("selected"); } %>>error</option>
				<option value="menuDate" <% if (news.getNewsFont().indexOf("menuDate") >= 0){ out.println("selected"); } %>>menuDate</option>
				<option value="processOff" <% if (news.getNewsFont().indexOf("processOff") >= 0){ out.println("selected"); } %>>processOff</option>
			</select>
          </td>
        </tr>
        <tr>
          <td align="right" width=10%>Link: </td>
          <td align="left" width=90%>
            <input type=text name="whatsNewLink_<%=news.getIcNews()%>_<%=i%>" class='' value="<%=news.getNewsLink()%>" size=80 maxLength=150 onclick="javascript:enable(this)"></td>
        </tr>
        <tr>
          <td align="right" width=10%>Image: </td>
          <td align="left" width=90%>
            <select name="whatsNewImage_<%=news.getIcNews()%>_<%=i%>" class='' onclick="javascript:enable(this)">
				<option value="bullet_red_onwhite.gif" <% if (news.getNewsImage().indexOf("bullet_red_onwhite.gif") >= 0){ out.println("selected"); } %>>bullet_red_onwhite.gif</option>
				<option value="news.gif" <% if (news.getNewsImage().indexOf("news.gif") >= 0){ out.println("selected"); } %>>news.gif</option>
				<option value="none.gif" <% if (news.getNewsImage().indexOf("none.gif") >= 0){ out.println("selected"); } %>>none.gif</option>
			</select>
          </td>
        </tr>
        <tr>
          <td align="right" width=10%>Alt Tag: </td>
          <td align="left" width=90%><input type=text name="whatsNewAltTag_<%=news.getIcNews()%>_<%=i%>" class='' value="<%=news.getNewsAltTag()%>" size=80 maxLength=150 onclick="javascript:enable(this)"></td>
        </tr>
      </table>
    </div>
    <% if (role.getAccessRights("WHATSNEW") >= 3 ) { %>
    <div class="delete">
    	<a href="javascript:deleteNews(<%=news.getIcNews()%>, <%=i%>)" title="Delete this news (<%=i+1%>)"><img src="<%=contextPath%>/images/delete.gif" border=0></a>
    </div>
    <% } %>
  </li>
  <%
  }
  %>
</ul>
<br><br>
<table border=0 cellpadding=0 cellspacing=0 width="680px" style="clear: both;">
  <tr>
	<% if (role.getAccessRights("WHATSNEW") >= 3 ) { %>
    <td width=33% align=center>
    	<a href="javascript: addNews(); void(0);"><img class=button src="<%=contextPath%>/images/button_add.gif" border=0></a></td>
   	<td width=33% align=center>
    	<a href="javascript: saveNews(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<% } %>
    <td width=33% align=center>
    	<a href="javascript: returnToAdminMenu(); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
  </tr>
</table>

<tsa:hidden name="itemList" value=""/>
<tsa:hidden name="deletedList" value=""/>

<tsa:hidden name="fromPage" value="/admin/admin_menu.jsp"/>
<tsa:hidden name="currentPage" value="/admin/whats_new_management/whats_new_setup.jsp"/>

<%@ include file="/system/footer.jsp"%>

<script value=JavaScript>

  frm = document.purchaseform;
  var deleted = '';

  function handleDiv(divId){
  	if(document.getElementById(divId).style["display"] != "none"){
      document.getElementById(divId).style["display"] = "none";
    } else {
    document.getElementById(divId).style["display"] = "";
    }
  }

  function addNews() {
	doSubmit('admin/whats_new_management/whats_new_add.jsp','DoNothing');
  }

  function saveNews(){
	var itemList = '';
	for(var i = 0; i < document.getElementById('newsList').getElementsByTagName('LI').length; i++) {
		itemList += document.getElementById('newsList').getElementsByTagName('LI')[i].getAttribute('id') + ';';
	}
	frm.itemList.value = itemList;
	if(itemList == '')
	{
		doSubmit('admin/admin_menu.jsp','NewsDelete');
	}
	else
	{
		doSubmit('admin/admin_menu.jsp','NewsUpdate;NewsDelete');
	}
  }

  function deleteNews(id, position) {
  	var labelId = 'news_' + position;
	var element = document.getElementById(labelId);
	var parent = element.parentNode;
	var children = parent.removeChild(element);
	deleted += id + ";";
	frm.deletedList.value = deleted;
  }

  function returnToAdminMenu(){
    doSubmit('admin/admin_menu.jsp','DoNothing');
  }

  function enable(objectThis)
  {
    objectThis.readonly = false;
    objectThis.focus();
  }

</script>

<%@ include file="/system/prevent_caching.jsp"%>
