//Navigation Script
function prepareWizard() {
	var wizard = $("#wizard_tabs");

	// setup ul.tabs to work as tabs for each div directly under div.panes
	// enable tabs that are contained within the wizard
	$("#tab-navigation", wizard).tabs("div.panes > div", function(event, index) {

		/* now we are inside the onBeforeClick event */
		var n = $("input:checked").length;
		if (index > 0 && n < 1) {
			console.log("No solution sets selected");
			// could put an error message here and block advance
			// when false is returned, user cant advance to next tab
			// return false;
		}
		// else everything is ok. we would remove error here
	});	
	
	$("#tab-navigation2", wizard).tabs("div.panes > div", function(event, index) {

		/* now we are inside the onBeforeClick event */
		var n = $("input:checked").length;
		if (index > 0 && n < 1) {
			console.log("No solution sets selected");
			// could put an error message here and block advance
			// when false is returned, user cant advance to next tab
			// return false;
		}
		// else everything is ok. we would remove error here
	});	

	var tabList = $("#tab-navigation", wizard).data("tabs");
	var breadcrList=$("#tab-navigation2", wizard).data("tabs");
	
	// "next tab" button
	$("button.next", wizard).click(function() {
		tabList.next();
		breadcrList.next();
		var ctab= breadcrList.getCurrentTab();
		prepBreadCrumbs(ctab);
		hideTabDescription();
		tabList.getCurrentTab().next('p').show();
	});

	// "back tab" button
	$("button.prev", wizard).click(function() {
		tabList.prev();
		breadcrList.prev();
		var ctab= breadcrList.getCurrentTab();
		prepBreadCrumbs(ctab);
		hideTabDescription();
		tabList.getCurrentTab().next('p').show();
	});
	
	
	function prepBreadCrumbs(ctab){
		$('li, #tab-navigation2').removeClass('e-current');
		
		if ($(ctab).attr('class') == "current") {
			$(ctab).parent().addClass('e-current');
		}
		
		$('#bread-crumb-tab-description').empty();
		
		var breadCrumbTabDescription = $(ctab).attr('title');
		$('#bread-crumb-tab-description').append(breadCrumbTabDescription);
		
	}
}