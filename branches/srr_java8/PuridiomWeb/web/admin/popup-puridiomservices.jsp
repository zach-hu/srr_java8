
<!--<link rel="stylesheet" type="text/css" href="../../assets/yui.css" >-->

<style>
/*Supplemental CSS for the YUI distribution*/

<% String contextPathYahoo="/puridiom/scripts/yui";%>

</style>
	<link rel="stylesheet" type="text/css" href="<%=contextPathYahoo%>/assets/dpSyntaxHighlighter.css">
	<link rel="stylesheet" type="text/css" href="<%=contextPathYahoo%>/container/assets/skins/sam/container.css" />
	<script type="text/javascript" src="<%=contextPathYahoo%>/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="<%=contextPathYahoo%>/event/event-min.js"></script>
	<script type="text/javascript" src="<%=contextPathYahoo%>/dom/dom-min.js"></script>
	<script type="text/javascript" src="<%=contextPathYahoo%>/container/container-min.js"></script>
	<script type="text/javascript" src="<%=contextPathYahoo%>/yahoo/yahoo.js"></script>
	<script type="text/javascript" src="<%=contextPathYahoo%>/event/event.js"></script>
	<script type="text/javascript" src="<%=contextPathYahoo%>/connection/connection.js"></script>


<div id="yahoo-com" class=" yui-skin-sam">
	<div id="custom-doc" class="yui-t2">

	<style>
	#example {height:30em;}
	label { display:block;float:left;width:45%;clear:left; }
	.clear { clear:both; }
	#resp { font-family:courier;margin:10px;padding:5px;border:1px solid #ccc;background:#fff;}
	</style>

	<script>


	YAHOO.namespace("example.container");

	function init() {

		// Define various event handlers for Dialog
		var handleSubmit = function() {
			this.submit();
		};
		var handleCancel = function() {
			//delete information
			this.cancel();
		};
		var handleSuccess = function(o) {
			var response = o.responseText;
			response = response.split("<!")[0];
			document.getElementById("resp").innerHTML = response;
			eval(response);
		};
		var handleFailure = function(o) {
			alert("Submission failed: " + o.status);
		};

		// Instantiate the Dialog
		YAHOO.example.container.dialog1 = new YAHOO.widget.Dialog("dialog1",
			{	width : "260px",
			 	fixedcenter : true,
			 	visible : false,
			 	constraintoviewport : true
			 	//buttons : [
						//	 { text:"Submit", handler:handleSubmit, isDefault:true },
						//	 { text:"Cancel", handler:handleCancel }
				//		  ]
        	 } );



		// Wire up the success and failure handlers
		YAHOO.example.container.dialog1.callback = { success: handleSuccess,
													 failure: handleFailure };

		// Render the Dialog
		YAHOO.example.container.dialog1.render();

		YAHOO.util.Event.addListener("show", "click", YAHOO.example.container.dialog1.show, YAHOO.example.container.dialog1, true);
		YAHOO.util.Event.addListener("hide", "click", YAHOO.example.container.dialog1.hide, YAHOO.example.container.dialog1, true);
	}

		YAHOO.util.Event.onDOMReady(init);
	</script>

<div id="show">
</div>

<div id="dialog1">
	<div class="hd">Status of the PuridiomServices</div>
		<div id="divpopup" class="bd">

	</div>
</div>
