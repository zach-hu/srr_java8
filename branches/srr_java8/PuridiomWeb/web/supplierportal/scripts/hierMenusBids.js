/*hierMenus.js
* by Peter Belesis. v4.0.2 010109
* Copyright (c) 2001 Peter Belesis. All Rights Reserved.
* Originally published and documented at http://www.dhtmlab.com/
* You may use this code on a public Web site only if this entire
* copyright notice appears unchanged and you publicly display
* on the Web page a link to http://www.dhtmlab.com/.
*
* Contact pbel@meteor.com for all other uses.
*/

/***************************************************************************
							BROWSER DETECTION
***************************************************************************/

  DOM	= (document.getElementById) ? true : false;
  IE	= (document.all) ? true : false;
  IE4	= IE && !DOM;
  IE5	= IE && DOM;
  Mac	= (navigator.appVersion.indexOf("Mac") != -1);
  IE5M	= IE5 && Mac;
  IEW	= IE && !Mac;
  IE4W	= IE4 && IEW;
  IE5W	= IE5 && IEW;
  NS	= navigator.appName == ("Netscape");
  NS4	= (document.layers) ? true : false;
  NS6	= (navigator.vendor == ("Netscape6") || navigator.product == ("Gecko"));

  if(IE5M) { DOM = false; IE4 = true }


/********************** PLEASE DO NOT MODIFY CODE BELOW **************************
********************** UNLESS COMFORTABLE WITH SCRIPTING ************************* 
****************** The menu parameters in the a_Parameters array **************
****************** can be changed.                               ************/

/***************************************************************************
							MENU PARAMETERS
***************************************************************************/

MenuIDPrefix = "Menu";
ItemIDPrefix = "Item";
ArrayIDPrefix = "Array";

a_Parameters = [
	["MenuWidth",          150],
	["FontFamily",         "Arial,sans-serif"],
	["FontSize",           10],
	["FontBold",           false],
	["FontItalic",         false],
	["FontColor",          "black"],
	["FontColorOver",      "FFFFFF"],
	["BGColor",            "#FFFFCC"],
	["BGColorOver",        "#666699"],
	["ItemPadding",        2],
	["BorderWidth",        1],
	["BorderColor",        "#E6E6E6"],
	["BorderStyle",        "solid"],
	["SeparatorSize",      1],
	["SeparatorColor",     "#E6E6E6"],
	["ImageSrc",           ""],
	["ImageSrcLeft",       ""],
	["ImageSize",          5],
	["ImageHorizSpace",    0],
	["ImageVertSpace",     0],
	["KeepHilite",         false],
	["ClickStart",         false],
	["ClickKill",          true],
	["ChildOverlap",       20],
	["ChildOffset",        10],
	["ChildPerCentOver",   null],
	["TopMilliSecondsVisible",  1000],
	["TopSecondsVisible",  .5],
	["StatusDisplayBuild", 0],
	["StatusDisplayLink",  1],
	["UponDisplay",        null],
	["UponHide",           null],
	["RightToLeft",        false]
]

/***************************************************************************
								MAIN SCRIPT
***************************************************************************/

function f_StringTrim(){
	var TestString = this;
	var SpaceChar  = " ";
	while (TestString.charAt(0) == SpaceChar) {TestString = TestString.substr(1)};
	while (TestString.charAt(TestString.length-1) == SpaceChar) {TestString = TestString.substr(0,TestString.length-1)};
	return TestString.toString();
}

a_BadChars = [".","/"," ",",","-"];

function f_StringStrip(){
	var TestString = this;
	var BadChar;
	for(var i=0;i<a_BadChars.length;i++) {
		BadChar = a_BadChars[i];
		BadCharIndex = TestString.lastIndexOf(BadChar);
		if(BadCharIndex!=-1) TestString = TestString.substr(BadCharIndex + 1);
	}
	return TestString.toString();
}

String.prototype.trim = f_StringTrim;
String.prototype.strip = f_StringStrip;

function f_AssignParameters(paramname,defaultvalue){
	var FullParamName = "" + paramname;
	if (typeof eval("window.PG_" + paramname) == "undefined") {
		if (typeof eval("window.GL_" + paramname) == "undefined") {
			eval(FullParamName + "= defaultvalue");
		}
		else {
			eval(FullParamName + "= GL_" + paramname);
		}
	}
	else {
		eval(FullParamName + "= PG_" + paramname);
	}

	var TestString = eval(FullParamName);
	if(eval("typeof(TestString)") == "string") {
		TestString = TestString.trim();
		if(TestString.length == 0) {
			eval(FullParamName + "= null");
			return;
		}
		if(TestString.charAt(0)=="#")return;
		TestString = TestString.strip();
	}

	if (eval("typeof(" + TestString +")") != 'undefined') {
		eval(FullParamName + "= eval("+ FullParamName +")");
	}
}

for (i=0;i<a_Parameters.length;i++) {
	f_AssignParameters(a_Parameters[i][0],a_Parameters[i][1]);
}

ChildPerCentOver = (isNaN(parseFloat(ChildPerCentOver))) ? null : parseFloat(ChildPerCentOver)/100;

function f_ValidateArray(arrayname){
	return ((typeof eval("window." + arrayname) == "object") && (eval(arrayname).length > 1))
}

/*
	Check for existence of array specifying which menus should be built (a_TreesToBuild)
    If array is not defined, then use all existing menu arrays to build menus, as in Version 3.
    Unlike Version 3, menu arrays do not have to be consecutively numbered.
	Create an empty a_TreesToBuild array.
    Cycle through array names Array1 to Array100.
    Any arrays found are added to a_TreesToBuild.
*/

if(!window.a_TreesToBuild) {
	a_TreesToBuild = [];
	for(i=1; i<100; i++){
		if(f_ValidateArray(ArrayIDPrefix + i)) a_TreesToBuild[a_TreesToBuild.length] = i;
	}
}

CurrentArray = null;
CurrentTree  = null;
CurrentMenu  = null;

a_TopMenus = [];

ZIndex = 5000;

AreLoaded = false;
AreCreated = false;
BeingCreated = false;

UserOverMenu = false;

HideAllTimer = null;
TotalTrees = 0; 

function f_Initialize() {
    if(AreCreated) {
		for(var i=0; i<TotalTrees; i++) {
			var TopMenu = a_TopMenus[i];
			clearTimeout(TopMenu.hideTimer);
			TopMenu.hideTimer = null;
        }
        clearTimeout(HideAllTimer);
    }
	AreCreated = false;
	BeingCreated = false;
	UserOverMenu = false;
	CurrentMenu = null;
	HideAllTimer = null;
	TotalTrees = 0;
	a_TopMenus = [];
}

function propertyTransfer(){
	this.obj = eval(this.id + "Obj");
	for (temp in this.obj) {this[temp] = this.obj[temp]}
}

if(NS4) {
	NS_OrigWidth  = window.innerWidth;
	NS_OrigHeight = window.innerHeight;
	window.onresize = function (){
	    if (window.innerWidth == NS_OrigWidth && window.innerHeight == NS_OrigHeight) return;
	    f_Initialize();
	    window.location.reload();
	}
	Layer.prototype.propertyTransfer = propertyTransfer;
}

function f_StartIt() {
	if(DOM && ((typeof(document.body) == "undefined") || (document.body == null))) return;
	AreLoaded = true;
	if (ClickKill) {
		f_OtherMouseDown = (document.onmousedown) ? document.onmousedown :  new Function;
  		if(NS4) document.captureEvents(Event.MOUSEDOWN);
    	document.onmousedown = function(){f_PageClick();f_OtherMouseDown()}
    }
	else {
		TopMilliSecondsVisible = TopSecondsVisible * 1000;
	}
    f_MakeTrees();
}

function f_AssignTreeParameters(arrayvalue,defaultvalue){
	var ValueIsString = (typeof arrayvalue == "string");
	if (ValueIsString) arrayvalue = arrayvalue.trim();
	var ValueIsNull = ((arrayvalue == null) || (typeof arrayvalue == "undefined") || (ValueIsString && arrayvalue.length == 0));
	if(ValueIsNull) return defaultvalue;
	var TestString = arrayvalue;
	if(eval("typeof(TestString)") == "string") {
		if(TestString.charAt(0)=="#")return arrayvalue;
		TestString = TestString.strip()
	}
	if (eval("typeof("+ TestString+" )") != 'undefined') {
		eval("arrayvalue = eval(arrayvalue)");
	}
	return arrayvalue;
}

function f_MakeTrees(){
    BeingCreated = true;
	var TreeParams = null;
	var TreeHasChildren = false;
	var ItemArray = null;

	for(var t=0; t<a_TreesToBuild.length; t++) {
		if(!f_ValidateArray(ArrayIDPrefix + a_TreesToBuild[t])) continue;
		CurrentArray = eval(ArrayIDPrefix + a_TreesToBuild[t]);

		TreeParams = CurrentArray[0];
		TreeHasChildren = false;

		for(var i=1; i<CurrentArray.length; i++) {
			ItemArray = CurrentArray[i];
			if(ItemArray[ItemArray.length-1]) {TreeHasChildren = true; break}
		}

		CurrentTree = {
			MenuWidth        : MenuWidth = f_AssignTreeParameters(TreeParams[0],MenuWidth),
			MenuLeft         : MenuLeft = f_AssignTreeParameters(TreeParams[1],null),
			MenuTop          : MenuTop = f_AssignTreeParameters(TreeParams[2],null),
			ItemWidth        : ItemWidth = MenuWidth - (BorderWidth*2),
			ItemTextWidth    : TreeHasChildren ? (ItemWidth - (ImageSize + ImageHorizSpace + ItemPadding)) : ItemWidth,
			HorizOffsetRight : HorizOffsetRight = (parseInt((ChildPerCentOver != null) ? (ChildPerCentOver  * ItemWidth) : ChildOverlap)) - (NS4 ? ItemPadding : 0),
			HorizOffsetLeft  : (MenuWidth - HorizOffsetRight) - (NS4 ? (BorderWidth*2) : 0),
			FontColor        : f_AssignTreeParameters(TreeParams[3],FontColor),
			FontColorOver    : f_AssignTreeParameters(TreeParams[4],FontColorOver),
			BGColor          : f_AssignTreeParameters(TreeParams[5],BGColor),
			BGColorOver      : f_AssignTreeParameters(TreeParams[6],BGColorOver),
			BorderColor      : f_AssignTreeParameters(TreeParams[7],BorderColor),
			SeparatorColor   : f_AssignTreeParameters(TreeParams[8],SeparatorColor),
			TopIsPermanent   : ((MenuLeft == null) || (MenuTop == null)) ? false : f_AssignTreeParameters(TreeParams[9],false),
			TopIsHorizontal  : TopIsHorizontal = f_AssignTreeParameters(TreeParams[10],false),
			TreeIsHorizontal : TreeHasChildren ? f_AssignTreeParameters(TreeParams[11],false) : false,
			PositionUnder    : (!TopIsHorizontal || !TreeHasChildren) ? false : f_AssignTreeParameters(TreeParams[12],false),
			TopImageShow     : TreeHasChildren ? f_AssignTreeParameters(TreeParams[13],true)  : false,
			TreeImageShow    : TreeHasChildren ? f_AssignTreeParameters(TreeParams[14],true)  : false,
			UponDisplay      : f_AssignTreeParameters(TreeParams[15],UponDisplay),
			UponHide         : f_AssignTreeParameters(TreeParams[16],UponHide)
		}

		CurrentMenu = null;
		f_MakeMenu(a_TreesToBuild[t]);
		a_TopMenus[TotalTrees] = CurrentTree.treeParent;
		TotalTrees++;

		if(CurrentTree.TopIsPermanent){
			CurrentTree.treeParent.moveTo(CurrentTree.MenuLeft,CurrentTree.MenuTop);
			if(NS4) {
				CurrentTree.treeParent.zIndex = 5000;
				CurrentTree.treeParent.visibility = "show";
			}
			else {
				CurrentTree.treeParent.style.zIndex = 5000;
				CurrentTree.treeParent.style.visibility = "visible";
			}
		}
    }

	if(StatusDisplayBuild) status = TotalTrees + " Hierarchical Menu Trees Created";
    AreCreated = true;
    BeingCreated = false;
}

function f_GetItemHtmlStr(arraystring){
	var TempString = arraystring;
	if (FontBold) TempString = TempString.bold();
	if (FontItalic) TempString = TempString.italics();
	TempString = "<FONT FACE='" + FontFamily + "' SIZE=" + FontSize + "px>" + TempString + "</FONT>";
	var TempStringOver = TempString.fontcolor(CurrentTree.FontColorOver);
	TempString = TempString.fontcolor(CurrentTree.FontColor);
	return [TempString,TempStringOver];
}

function f_GetItemLyrStr(itemid,htmlstrings,hasmore){
	var TempString = "<LAYER ID=" + itemid + " WIDTH="+ (CurrentTree.ItemWidth - (ItemPadding*2)) + ">"
				+  "<LAYER WIDTH=" + (CurrentTree.ItemTextWidth - (ItemPadding*2)) + ">" + htmlstrings[0] +"</LAYER>"
				+  "<LAYER WIDTH=" + (CurrentTree.ItemTextWidth - (ItemPadding*2)) + ">" + htmlstrings[1] +"</LAYER>"
				+  "<LAYER></LAYER>"
	if(hasmore && CurrentMenu.showImage) {
		var ImgSrc = RightToLeft ? ImageSrcLeft : ImageSrc;
		TempString += "<LAYER WIDTH="+ ImageSize + "><IMG SRC='" + ImgSrc + "' WIDTH=" + ImageSize + " VSPACE=0 HSPACE=0 BORDER=0></LAYER>";
	}
	TempString += "</LAYER>";
	return TempString;
}

function f_GetItemDivStr(itemid,disptext,hasmore){
	var WidthValue = CurrentMenu.isHorizontal ? (ItemElement.isLastItem) ? (CurrentTree.MenuWidth - BorderWidth - SeparatorSize) : (CurrentTree.MenuWidth - BorderWidth) : CurrentTree.ItemWidth;
	var TempString = "<DIV ID=" + itemid + " STYLE='position:absolute;width:" + WidthValue + "px'>";
	if(CurrentMenu.showImage) {
		var FullPadding  = (ItemPadding*2) + ImageSize + ImageHorizSpace;
	}
    if(hasmore && CurrentMenu.showImage) {
		var ImgPosition = RightToLeft ? "absolute;" : "relative;";
		var ImgSrc      = RightToLeft ? ImageSrcLeft : ImageSrc;
		var ImgHSpace   = (RightToLeft || IE5M) ? 0 : ItemPadding;
		var ImgStyle    = RightToLeft ? ("left:"+ (ItemPadding + ImageHorizSpace) + "px;top:"+ (ItemPadding + ImageVertSpace) + "px;") : ("float:right;margin-right:"+ (IE5M ? -(ImageSize + ItemPadding) : (-FullPadding)) +"px;margin-top:"+ ImageVertSpace + "px;width:"+ ImageSize + "px;");
		var ImgString   = "<IMG STYLE='position:"+ ImgPosition + ImgStyle +"' SRC='" + ImgSrc + "' HSPACE="+ ImgHSpace +" VSPACE=0 BORDER=0>";
		TempString += ImgString;
	}
 	TempString += disptext + "</DIV>";
	return TempString;
}

function f_SetItemProperties(itemid,itemidsuffix) {
	this.tree        = CurrentTree;
	this.itemsetup   = f_ItemSetup;
	this.index       = CurrentMenu.itemCount - 1;
	this.tree        = CurrentTree;
	this.isLastItem  = (CurrentMenu.itemCount == CurrentMenu.maxItems);
	this.array	     = CurrentMenu.array[CurrentMenu.itemCount];
	this.dispText    = this.array[0];
	this.linkText    = this.array[1];
	this.hasRollover = this.array[2];
	this.permHilite  = (!this.hasRollover && this.array[3]);
	this.hasMore	 = this.array[4];
	this.childID	 = this.hasMore ? (MenuIDPrefix + itemidsuffix) : null;
	this.child	     = null;
    this.onmouseover = f_ItemOver;
    this.onmouseout  = f_ItemOut;
	if(NS4) {
		var HtmlStrings  = f_GetItemHtmlStr(this.dispText);
		this.htmStr	     = HtmlStrings[0];
		this.htmStrOver  = HtmlStrings[1];
		this.itemStr	 = f_GetItemLyrStr(itemid,HtmlStrings,this.hasMore);
	}
	else {
		this.setItemStyle = f_SetItemStyle;
		if(IE4) {
			this.itemStr	 = f_GetItemDivStr(itemid,this.dispText,this.hasMore);
		}
	}
}

function f_Make4ItemElement(menucount) {
	var ItemIDSuffix = menucount + "_" + CurrentMenu.itemCount;
	var LayerID  = ItemIDPrefix + ItemIDSuffix;
	var ObjectID = LayerID + "Obj";
 	eval(ObjectID + " = new Object()");
	ItemElement = eval(ObjectID);
	ItemElement.setItemProperties = f_SetItemProperties;
	ItemElement.setItemProperties(LayerID,ItemIDSuffix);
	return ItemElement;
}

function f_MakeElement(menuid) {
	var MenuObject;
	if (DOM) {
		MenuObject = document.createElement("DIV");
		with(MenuObject){
			id = menuid;
			with(style) {
				position = "absolute";
				visibility = "hidden";
				width = (NS6 ? CurrentTree.ItemWidth : CurrentTree.MenuWidth) + "px";
			}
		}
		document.body.appendChild(MenuObject);
	}
	else {
		var LayerID  = menuid;
		var ObjectID = LayerID + "Obj";
		eval(ObjectID + " = new Object()"); 
		MenuObject = eval(ObjectID);
	}
	return MenuObject;
}

function f_MakeMenu(menucount) {
	if(!f_ValidateArray(ArrayIDPrefix + menucount)) return false;
	CurrentArray = eval(ArrayIDPrefix + menucount);
	
	NewMenu = f_MakeElement(MenuIDPrefix + menucount);
	NewMenu.array = CurrentArray;
	NewMenu.tree  = CurrentTree;

	if(CurrentMenu) {
		NewMenu.parentMenu = CurrentMenu;
		NewMenu.parentItem = CurrentMenu.itemElement;
		NewMenu.parentItem.child = NewMenu;
		NewMenu.hasParent = true;
		NewMenu.isHorizontal = CurrentTree.TreeIsHorizontal;
		NewMenu.showImage = CurrentTree.TreeImageShow;
	}
	else {
		NewMenu.isHorizontal = CurrentTree.TopIsHorizontal;
		NewMenu.showImage = CurrentTree.TopImageShow;
	}

	NewMenu.itemCount = 0;
	NewMenu.maxItems = NewMenu.array.length - 1;
	NewMenu.zIndex = ++ZIndex;
	NewMenu.showIt = f_ShowIt;
	NewMenu.keepInWindow = f_KeepInWindow;
	NewMenu.onmouseover = f_MenuOver;
	NewMenu.onmouseout = f_MenuOut;
	NewMenu.hideTree = f_HideTree
	NewMenu.hideParents = f_HideParents;
	NewMenu.hideChildren = f_HideChildren;
	NewMenu.hideTop = f_HideTop;
	NewMenu.hideSelf        = f_HideSelf;

	NewMenu.hasChildVisible = false;
	NewMenu.isOn = false;
	NewMenu.hideTimer = null;
	NewMenu.currentItem = null;
	NewMenu.setMenuStyle = f_SetMenuStyle;

	if(IE) NewMenu.onselectstart = f_CancelSelect;
	if(!NS4) NewMenu.moveTo = f_MoveTo;
	
	if(NS4) NewMenu.htmlString = "<LAYER ID='" + MenuIDPrefix + menucount +"' VISIBILITY=HIDE WIDTH="+ CurrentTree.MenuWidth +">";
	if(IE4) NewMenu.htmlString = "<DIV   ID='" + MenuIDPrefix + menucount +"' STYLE='position:absolute;visibility:hidden;width:"+ CurrentTree.MenuWidth +"'>";

	CurrentMenu = NewMenu;

	if(DOM) NewMenu.setMenuStyle();

	while (NewMenu.itemCount < NewMenu.maxItems) {
		NewMenu.itemCount++;
		if(StatusDisplayBuild) status = "Creating Hierarchical Menus: " + menucount + " / " + NewMenu.itemCount;
		NewMenu.itemElement = (NS4 || IE4) ? f_Make4ItemElement(menucount) : f_MakeItemElement(menucount);

		if(!DOM) NewMenu.htmlString += NewMenu.itemElement.itemStr;
		if(NewMenu.itemElement.hasMore){
	        MenuCreated = f_MakeMenu(menucount + "_" + NewMenu.itemCount);
		if(MenuCreated) {
			CurrentMenu = NewMenu = NewMenu.parentMenu;
		}
		else {
			NewMenu.itemElement.hasMore = false;
		}
        }
    }
	if(IE4) {
		document.write(NewMenu.htmlString + "</DIV>");
		menuLyr = document.all[MenuIDPrefix + menucount];
		menuLyr.propertyTransfer = propertyTransfer;
		menuLyr.propertyTransfer();
		NewMenu = menuLyr;
		NewMenu.setMenuStyle();
	    if(!IE5M) NewMenu.childNodes = NewMenu.children;
		NewMenu.lastItem = NewMenu.childNodes[NewMenu.childNodes.length-1];
	    for(var i=0; i<NewMenu.childNodes.length; i++) {
	        it = NewMenu.childNodes[i];
			it.siblingBelow = i>0 ? NewMenu.childNodes[i-1] : null;
			it.propertyTransfer = propertyTransfer;
			it.propertyTransfer();
			it.itemsetup(i+1);
	    }
	}
    if(NS4) {
		document.write(NewMenu.htmlString + "</LAYER>");
		menuLyr = document.layers[document.layers.length-1];
		menuLyr.propertyTransfer();
		eval(menuLyr.id + "= menuLyr");
		NewMenu = menuLyr;
    	NewMenu.childNodes = NewMenu.document.layers;
		NewMenu.lastItem = NewMenu.childNodes[NewMenu.childNodes.length-1];
	    for(var i=0; i<NewMenu.childNodes.length; i++) {
	        it = NewMenu.childNodes[i];
			it.propertyTransfer();
			it.itemsetup(i+1);
	    }
	    NewMenu.fullHeight = NewMenu.lastItem.top + NewMenu.lastItem.clip.bottom + BorderWidth;
		NewMenu.bgColor = CurrentTree.BorderColor;
	
	    NewMenu.clip.top = 0;
		if (NewMenu.isHorizontal) {
		    NewMenu.clip.right = NewMenu.lastItem.left + NewMenu.lastItem.clip.right + BorderWidth;
		}
		else {
		    NewMenu.clip.right = CurrentTree.MenuWidth;
		}
	
	    NewMenu.clip.bottom = NewMenu.fullHeight;
	}
	if(DOM || IE4) {
		if(NewMenu.isHorizontal){
			var ChildHeight = parseInt(NewMenu.style.height);
			ChildHeight -= (NS6) ? (ItemPadding * 2) : (BorderWidth * 2);
			for(i=0; i<NewMenu.childNodes.length; i++){
				NewMenu.childNodes[i].style.height = ChildHeight + "px";
			}
		}
	}
	NewMenu.moveTo(0,0);
	CurrentTree.treeParent = NewMenu.tree.startChild = NewMenu;
	return true;
}

function f_SetMenuStyle(){
	with(this.style) {
		borderWidth = BorderWidth + "px";
		borderColor = CurrentTree.BorderColor;
		borderStyle = BorderStyle;
		zIndex      = --ZIndex;
		overflow    = "hidden";
		cursor      = "default";
		fontStyle   = (FontItalic) ? "italic" : "normal";
		font        = ((FontBold) ? "bold " : "normal ") + FontSize + "px " + FontFamily;
	}
}

function f_MakeItemElement() {
	var ItemElement = document.createElement("DIV");
	ItemElement.style.position = "absolute";
	ItemElement.style.visibility = "inherit";
	 CurrentMenu.appendChild(ItemElement);
	ItemElement.setItemProperties =  f_SetItemProperties;
	ItemElement.setItemProperties();
	ItemElement.siblingBelow = ItemElement.previousSibling;

	if(ItemElement.linkText) ItemElement.onclick =  f_LinkIt;
	ItemElement.menu =  CurrentMenu;
	var FullPadding  = ( ItemPadding*2) +  ImageSize +  ImageHorizSpace;
    if(ItemElement.hasMore &&  CurrentMenu.showImage) {
		var ImageElement = document.createElement("IMG");
		with(ImageElement){
			src =  RightToLeft ?  ImageSrcLeft :  ImageSrc;
			hspace = (! RightToLeft &&  IE5W) ?  ItemPadding : 0;
			vspace = 0;
			with(style) {
				if( RightToLeft) {
					position = "absolute";
					top = ( ItemPadding +  ImageVertSpace) + "px";
					left = ( ItemPadding +  ImageHorizSpace) + "px";
				}
				else {
					position = "relative";
					marginTop =  ImageVertSpace + "px";
					if( IE5W) {
						marginRight = -FullPadding + "px";
					}
					else marginRight = -( ImageSize +  ItemPadding) +"px";
					if( NS6) cssFloat = "right";
					else styleFloat = "right";
				}	
				width =  ImageSize + "px";
			}
		}
	}
	ItemElement.innerHTML = ItemElement.dispText;
	if(ImageElement) ItemElement.insertBefore(ImageElement,ItemElement.firstChild);
	ItemElement.setItemStyle();
	return ItemElement;
}

function  f_SetItemStyle() {
	with(this.style){
		backgroundColor   = (this.permHilite) ?  CurrentTree.BGColorOver :  CurrentTree.BGColor;
		color             = (this.permHilite) ?  CurrentTree.FontColorOver :  CurrentTree.FontColor;
		padding           =  ItemPadding +"px";
		if( CurrentMenu.showImage)	{
			var FullPadding  = ( ItemPadding*2) +  ImageSize +  ImageHorizSpace;
			if ( RightToLeft) paddingLeft = FullPadding + "px";
			else paddingRight = FullPadding + "px";
		}
		if(!this.isLastItem) {
			var SeparatorString =  SeparatorSize + "px solid " + this.tree.SeparatorColor;
			if (this.menu.isHorizontal) borderRight = SeparatorString;
			else borderBottom = SeparatorString;
		}

		if( IE) width =  CurrentTree.ItemWidth + "px";
		else width = ( CurrentTree.ItemWidth - (parseInt(paddingLeft) + parseInt(paddingRight))) + "px";

		if(this.menu.isHorizontal){
			if( IE){
				if(this.isLastItem) width = ( CurrentTree.MenuWidth -  BorderWidth -  SeparatorSize) + "px"
				else width = ( CurrentTree.MenuWidth -  BorderWidth) + "px"
			}
			if( NS6) width = ( CurrentTree.MenuWidth -  BorderWidth - parseInt(paddingLeft) - parseInt(paddingRight) -  SeparatorSize) + "px";
			top = "0px";
			if( IE) left = (this.index * ( CurrentTree.MenuWidth -  BorderWidth)) + "px";
			if( NS6) left = ((this.index * parseInt(width)) + (( SeparatorSize * this.index)))  + ((parseInt(paddingLeft) + parseInt(paddingRight)) * this.index) + "px";
			var LeftAndWidth = parseInt(left) + parseInt(width);
			this.menu.style.width = LeftAndWidth + ( IE ? ( BorderWidth * 2) : (parseInt(paddingLeft) + parseInt(paddingRight))) + "px"
		    if (this.index) {
				var SiblingHeight = ( IE4W) ? (this.siblingBelow.scrollHeight) : this.siblingBelow.offsetHeight;
				this.menu.style.height = Math.max(parseInt(this.menu.style.height),SiblingHeight+( NS6 ? 0 :  BorderWidth * 2)) + "px";
			}
	       	else{
				var SiblingHeight = ( IE4W) ? (this.scrollHeight) : this.offsetHeight;
				this.menu.style.height = SiblingHeight + ( NS6 ? 0 :  BorderWidth * 2) + "px";
			}
		}
		else {
			left = "0px";
		    if (this.index) {
				var SiblingHeight = ( IE4W) ? (this.siblingBelow.scrollHeight +  SeparatorSize) : this.siblingBelow.offsetHeight;
				top = parseInt(this.siblingBelow.style.top) + SiblingHeight + "px";
			}
			else top = "0px";
			this.menu.style.height = parseInt(top) + ( IEW ? this.scrollHeight : this.offsetHeight) + ( NS6 ? 0 : ( BorderWidth * 2)) + "px";
		}
	}
}

function  f_ItemSetup(whichItem) {
    this.menu = ( NS4) ? this.parentLayer : this.parentElement;

    if (this.hasMore) {
        this.child = eval(this.childID);
        this.child.parentMenu = this.menu;
        this.child.parentItem = this;
    }

    if (this.linkText) {
        if( NS4) {
			this.captureEvents(Event.MOUSEUP)
        	this.onmouseup =  f_LinkIt;
		}
		else {
        	this.onclick =  f_LinkIt;
		}
    }
	if( IE4) this.setItemStyle();
	if( NS4) {
		if (this.menu.isHorizontal) {
	    	if (this.index != 0) this.left = this.siblingBelow.left + this.siblingBelow.clip.width +  SeparatorSize;
			else this.left = ( BorderWidth +  ItemPadding);
			this.top = ( BorderWidth +  ItemPadding);
		}
		else {
			this.left = ( BorderWidth +  ItemPadding);
		    if (this.index != 0) this.top = this.siblingBelow.top + this.siblingBelow.clip.height +  SeparatorSize;
	    	else this.top = ( BorderWidth +  ItemPadding)
		}
	    this.clip.top = this.clip.left = - ItemPadding;
	    this.clip.right = this.tree.ItemWidth -  ItemPadding;

		this.bgColor = this.permHilite ? this.tree.BGColorOver : this.tree.BGColor;
	
		this.txtLyrOff = this.document.layers[0];
		with(this.txtLyrOff) {
			if ( RightToLeft && this.menu.showImage) left =  ItemPadding +  ImageSize +  ImageHorizSpace;
			visibility = this.permHilite ? "hide" : "inherit";
		}
	
		this.txtLyrOn = this.document.layers[1];
		with(this.txtLyrOn) {
			if ( RightToLeft && this.menu.showImage) left =  ItemPadding +  ImageSize +  ImageHorizSpace;
			visibility = this.permHilite ? "inherit" : "hide";
		}
	
		this.dummyLyr = this.document.layers[2];
		with(this.dummyLyr) {
			left = top = - ItemPadding;
			clip.width = this.clip.width;
			clip.height = this.clip.height;
		}
	
		if(this.document.layers.length>3) {
			this.imgLyr = this.document.layers[3];
			with(this.imgLyr) {
				moveBelow(this.txtLyrOff);
				left = ( RightToLeft) ?  ImageHorizSpace : this.tree.ItemWidth - ( ItemPadding * 2) -  ImageSize -  ImageHorizSpace;
				top =  ImageVertSpace;
			}
		}
	
		this.fullClip = this.txtLyrOff.document.height + ( ItemPadding * 2);
	
		if(this.menu.isHorizontal) {
			if(this.index) this.fullClip = Math.max(this.siblingBelow.fullClip,this.fullClip);
		}
		this.clip.height = this.fullClip;
	}
}

function  f_PopUp(menuname,e) {
	if( IE) e = event;
	if (! AreLoaded) return;
	menuname = menuname.replace("elMenu", MenuIDPrefix);
	CurrentMenu = ( NS4) ? document.layers[menuname] : ( DOM) ? document.getElementById(menuname) : document.all(menuname);
	if(! CurrentMenu)return;
	if ( ClickStart) {
		var ClickElement = ( IE) ? e.srcElement : e.target;
		if( NS6) {
			while(ClickElement.tagName==null){
				ClickElement = ClickElement.parentNode;
			}
		}
		ClickElement.onclick =  f_PopMenu;
    }
	else  f_PopMenu(e);
}

function  f_PopMenu(e){
	if( IE) e = event;
	if (! AreLoaded || ! AreCreated) return true;
	if ( ClickStart && e.type != "click") return true;
	f_HideAll();
	CurrentMenu.hasParent = false;
	CurrentMenu.tree.startChild =  CurrentMenu;
	var EventX = ( IE) ? (e.clientX + document.body.scrollLeft) : e.pageX;
	var EventY = ( IE) ? (e.clientY + document.body.scrollTop)  : e.pageY;

	if (eval(CurrentMenu.tree.MenuLeft) < 0) {
		CurrentMenu.tree.MenuLeft = eval(0 - CurrentMenu.tree.MenuLeft);
		CurrentMenu.tree.MenuLeft = eval(EventX - CurrentMenu.tree.MenuLeft);
	}

	CurrentMenu.xPos = ( CurrentMenu.tree.MenuLeft) ?  CurrentMenu.tree.MenuLeft : EventX;
   	CurrentMenu.yPos = ( CurrentMenu.tree.MenuTop)  ?  CurrentMenu.tree.MenuTop  : EventY;
	CurrentMenu.keepInWindow();

	if (NS4) CurrentMenu.xPos = CurrentMenu.xPos + CurrentTree.MenuWidth + 60;

	CurrentMenu.moveTo( CurrentMenu.xPos, CurrentMenu.yPos);
	CurrentMenu.isOn = false;
	CurrentMenu.showIt(true);
	return false;
}

function  f_MenuOver(e) {
	if(!this.tree.startChild){this.tree.startChild = this}
	if(this.tree.startChild == this)  f_HideAll(this)
    this.isOn = true;
     UserOverMenu = false;
     CurrentMenu = this;
    if (this.hideTimer) clearTimeout(this.hideTimer);
}

function  f_MenuOut() {
	if( IE5 && event.srcElement.contains(event.toElement)) return;
    this.isOn = false;
     UserOverMenu = false;
    if( StatusDisplayLink) status = "";
    if(! ClickKill)  HideAllTimer = setTimeout(" CurrentMenu.hideTree()",10);  
}

function  f_ItemOver(){
    if ( KeepHilite) {
        if (this.menu.currentItem && this.menu.currentItem != this && this.menu.currentItem.hasRollover) {
            if ( NS4) {
	            with(this.menu.currentItem){
					bgColor = this.tree.BGColor;
        	    	txtLyrOff.visibility = "inherit";
					txtLyrOn.visibility = "hide";
				}
			}
			else {
				with(this.menu.currentItem.style){
					backgroundColor = this.tree.BGColor;
	            	color = this.tree.FontColor
				}
			}
        }
    }
	if( IE5 && event.srcElement.tagName == "IMG") return;
	if(this.hasRollover) {
		if ( NS4) {
		    this.bgColor = this.tree.BGColorOver;
			this.txtLyrOff.visibility = "hide";
			this.txtLyrOn.visibility = "inherit";
		}
		else {
		    this.style.backgroundColor = this.tree.BGColorOver;
		    this.style.color = this.tree.FontColorOver;
		}
	}

    if( StatusDisplayLink) status = this.linkText;
    this.menu.currentItem = this;
	if (this.menu.hasChildVisible && (this.menu.visibleChild != this.child)) {
        this.menu.hideChildren(this);
    }

    if (this.hasMore) {
		if (this.tree.PositionUnder && (this.menu == this.tree.treeParent)) {
			if ( NS4) {
				this.child.xPos = this.pageX + this.clip.left -  BorderWidth;
				this.child.yPos = this.menu.top + this.menu.clip.height -  BorderWidth;
			}
			else {
				this.child.xPos = parseInt(this.menu.style.left) + parseInt(this.style.left);
				this.child.yPos = parseInt(this.menu.style.top)  + this.menu.offsetHeight - ( BorderWidth);
			}
		}
		else {
			if( NS4) {
				this.oL = this.pageX + this.clip.left;
				this.child.offsetWidth = this.child.clip.width;
				this.oT = this.pageY + this.clip.top -  BorderWidth;
			}
			else {
				if( IE5M) {
					this.oL = parseInt(this.menu.style.left) -  BorderWidth;
					this.oL += this.offsetLeft;
					this.oT =  parseInt(this.menu.style.top)  - BorderWidth;
					this.oT += this.offsetTop;
				}
				else {
					this.oL = ( IE) ? parseInt(this.menu.style.left) : - BorderWidth;
					this.oL += this.offsetLeft;
					this.oT = ( IE) ? parseInt(this.menu.style.top) : - BorderWidth;
					this.oT += this.offsetTop;
				}
			}

			if( RightToLeft) {
				this.child.xPos = this.oL + (this.tree.HorizOffsetRight - this.child.offsetWidth);
			}
			else {		
				this.child.xPos = this.oL + this.tree.HorizOffsetLeft;
			}
			this.child.yPos = this.oT +  ChildOffset +  BorderWidth;
		}
        if(!this.tree.PositionUnder) this.child.keepInWindow();
		this.child.moveTo(this.child.xPos,this.child.yPos);
        this.menu.hasChildVisible = true;
        this.menu.visibleChild = this.child;
        this.child.showIt(true);
    }
}

function  f_ItemOut() {
	if ( IE5 && (event.srcElement.contains(event.toElement)
	  || (event.fromElement.tagName=="IMG" && event.toElement.contains(event.fromElement))))
		  return;
    if ( (! KeepHilite || ((this.tree.TopIsPermanent && (this.tree.treeParent==this)) && !this.menu.hasChildVisible)) && this.hasRollover) {
        if( NS4) {
			with(this){
				bgColor = this.tree.BGColor;
				txtLyrOff.visibility = "inherit";
				txtLyrOn.visibility = "hide";
			}
		}
		else {
			with(this.style) {
				backgroundColor = this.tree.BGColor;
           		color = this.tree.FontColor
			}
		}
    }
	if( NS4 && ! ClickKill && ! UserOverMenu) {
         HideAllTimer = setTimeout(" CurrentMenu.hideTree()",10);  
    }
}

function  f_MoveTo(xPos,yPos) {
	this.style.left = xPos + "px";
	this.style.top = yPos + "px";
}

function  f_ShowIt(on) {
	if (!(this.tree.TopIsPermanent && (this.tree.treeParent==this))) {
		if(!this.hasParent || (this.hasParent && this.tree.TopIsPermanent)) {
			var IsVisible = ( NS4) ? this.visibility == "show" : this.style.visibility == "visible";
			if ((on && !IsVisible) || (!on && IsVisible))
				eval(on ? this.tree.UponDisplay : this.tree.UponHide)
		}
		if( NS4) {this.visibility = (on) ? "show" : "hide"}
		else {this.style.visibility = (on) ? "visible" : "hidden"}
	}
    if ( KeepHilite && this.currentItem && this.currentItem.hasRollover) {
        if( NS4) {
	        with(this.currentItem){
				bgColor = this.tree.BGColor;
				txtLyrOff.visibility = "inherit";
				txtLyrOn.visibility = "hide";
			}
		}
		else {
			with(this.currentItem.style){
				backgroundColor = this.tree.BGColor;
				color = this.tree.FontColor;
			}
		}
    }
    this.currentItem = null;
}

function  f_KeepInWindow() {
    var ExtraSpace     = 10;
	var WindowLeftEdge = ( IE) ? document.body.scrollLeft   : window.pageXOffset;
	var WindowTopEdge  = ( IE) ? document.body.scrollTop    : window.pageYOffset;
	var WindowWidth    = ( IE) ? document.body.clientWidth  : window.innerWidth;
	var WindowHeight   = ( IE) ? document.body.clientHeight : window.innerHeight;
	var WindowRightEdge  = (WindowLeftEdge + WindowWidth) - ExtraSpace;
	var WindowBottomEdge = (WindowTopEdge + WindowHeight) - ExtraSpace;

	var MenuLeftEdge = this.xPos;
	var MenuRightEdge = MenuLeftEdge + (( NS4) ? this.clip.width : this.offsetWidth);
	var MenuBottomEdge = this.yPos + (( NS4) ? this.clip.height : this.offsetHeight);

	if (this.hasParent) {
		var ParentLeftEdge = ( NS4) ? this.parentMenu.pageX : parseInt(this.parentMenu.style.left);
		if( NS4) this.offsetWidth = this.clip.width;
	}
	if (MenuRightEdge > WindowRightEdge) {
		if (this.hasParent) {
			this.xPos = ParentLeftEdge + this.tree.HorizOffsetRight - this.offsetWidth;	
		}
		else {
			dif = MenuRightEdge - WindowRightEdge;
			this.xPos -= dif;
		}
	}

	if (MenuBottomEdge > WindowBottomEdge) {
		dif = MenuBottomEdge - WindowBottomEdge;
		this.yPos -= dif;
	}

	if (MenuLeftEdge < WindowLeftEdge) {
		if (this.hasParent) {
			this.xPos = ParentLeftEdge + this.tree.HorizOffsetLeft;
		}
		else {this.xPos = 5}
	}       
}

function  f_LinkIt() {
	 f_HideAll();
	if (this.linkText.indexOf("javascript")!=-1) eval(this.linkText)
//    else location.href = this.linkText;
	else { setRedirect(this.linkText); submitThis(); }
}


function  f_PopDown(menuName){
	if (! AreLoaded || ! AreCreated) {
		return;
	}
	else {
		ClickKill = false;
	}
	menuName = menuName.replace("elMenu", MenuIDPrefix);
    var MenuToHide = ( NS4) ? document.layers[menuName] : ( DOM) ? document.getElementById(menuName) : document.all(menuName);
	MenuToHide.isOn = false;
	if (!ClickKill) MenuToHide.hideTop();
}

function  popDownImg(menuName, imgName, imgSrc){
	if (! AreLoaded || ! AreCreated) {
		return;
	}
	else {
		ClickKill = false;
	}
    var MenuToHide = ( NS4) ? document.layers[menuName] : ( DOM) ? document.getElementById(menuName) : document.all(menuName);
	MenuToHide.isOn = false;
	if (!ClickKill) {
		if (imgName == "additem") { frm.img_additem.src = imgSrc; }
		if (imgName == "insertitem") { frm.img_insertitem.src = imgSrc; }
		if (imgName == "replaceitem") { frm.img_replaceitem.src = imgSrc; }
	};

}


function  f_HideAll(callingmenu) {
	for(var i=0; i< TotalTrees; i++) {
        var TopMenu =  a_TopMenus[i].tree.startChild;
		if(TopMenu == callingmenu)continue
        TopMenu.isOn = false;
        if (TopMenu.hasChildVisible) TopMenu.hideChildren();
        TopMenu.showIt(false);
    }    
}

function  f_HideTree() { 
     HideAllTimer = null;
    if ( UserOverMenu) return;
    if (this.hasChildVisible) this.hideChildren();
    this.hideParents();
}

function  f_HideTop() {
	TopMenuToHide = this;
    ( ClickKill) ? TopMenuToHide.hideSelf() : (this.hideTimer = setTimeout("TopMenuToHide.hideSelf()", TopMilliSecondsVisible));
}

function  f_HideSelf() {
    this.hideTimer = null;
    if (!this.isOn && ! UserOverMenu) this.showIt(false);
}

function  f_HideParents() {
    var TempMenu = this;
    while(TempMenu.hasParent) {
        TempMenu.showIt(false);
        TempMenu.parentMenu.isOn = false;        
        TempMenu = TempMenu.parentMenu;
    }
    TempMenu.hideTop();
}

function  f_HideChildren(callingitem) {
    var TempMenu = this.visibleChild;
    while(TempMenu.hasChildVisible) {
        TempMenu.visibleChild.showIt(false);
        TempMenu.hasChildVisible = false;
        TempMenu = TempMenu.visibleChild;
    }

    if (!this.isOn || !callingitem.hasMore || this.visibleChild != this.child) {
        this.visibleChild.showIt(false);
        this.hasChildVisible = false;
    }
}

function  f_CancelSelect(){return false}

function  f_PageClick() {
    if (! UserOverMenu &&  CurrentMenu!=null && ! CurrentMenu.isOn)  f_HideAll();
}

popUp =  f_PopUp;
popDown =  f_PopDown;

//f_StartIt();

//end