//Everything inside it will load as soon as the DOM is loaded and before the page contents are loaded.
$(document).ready(function() {
	tableCollection = [];
    ulParentsDependency = [];
    dependenciesCollection = [];
    continueCheck = false;
    currentCheckboxClass = "";
    summaryTableMatcher = "";
    
	prepareWizard();
	hideTabDescription();
	quickSearch();
	createAndPopulateTable();
	createAndPopulateProductsTable();
	CreateAndPopulateNotificationTable();
	setInterval("CreateAndPopulateNotificationTable();",10000);
	
	pressDownBreadCrumbTabs();
	
});

/***Check or Uncheck dependent SolutionSets***/
function setCheckBoxSelection() {
	var checkboxElement = $(this);
	var test = checkboxElement.parents('table');
	if(test.find('input').attr('class') == checkboxElement.attr('class')){
		
		//IMPORTANT. Used to set which Type is selected, SolSets or Products. 
		currentCheckboxClass = checkboxElement.attr('class');
		
		// gets the check box dependent solution id
		var dependentSolSetId = checkboxElement.val();

		/* If checked and its dependent is valid id, then the box is disabled and
		   text is grayed out. Or if it is unchecked and a dependent solution
		   set exist in the list as a selected checkbox because it dependent of another solution set,
		   it also remains unchecked and disabled */
		var dependentParents = $("."+currentCheckboxClass).parent().find('#'+dependentSolSetId+'');
		summaryTableMatcher = $("."+currentCheckboxClass).parents('table').parent('div').attr('id');
		var dependentSolSetObj = dependentParents;
		if (checkboxElement.prop("checked") 
				|| (checkboxElement.prop("checked") != true 
						&& isDependentSolutionSetInList(dependentSolSetObj) > -1)) {
			dependentSolSetObj.prop('disabled', true);
			dependentSolSetObj.next('label').css({opacity : 0.5});

			// fires an event when a checkbox is selected.
			dependentSolSetObj.prop('checked', true).click();

		} else {
			// otherwise enable and reset the label opacity back to normal
			var productsDependencyIds = $('label', '.products-summary-table');
			var tempArray = [];
			for(var i=0; i<productsDependencyIds.length; i++){
				var item = productsDependencyIds[i];
				tempArray.push(item.id);
			}
			//Add class to SS if it was dependent on a selected Product
			if($.inArray(dependentSolSetId, tempArray) > -1){
				dependentSolSetObj.addClass('productSolutionSet');
			}
			else{
				dependentSolSetObj.prop('disabled', false);
				dependentSolSetObj.next('label').css({opacity : 1});
			}
		}
		//Check which function to call depending if it's a Product or a SolutionSet
		if(summaryTableMatcher != "productstable"){
			updateSummaryTable(getSelectedCheckBoxList());
		}
		else{
			setSolutionSetEnabledBasedOnProduct(checkboxElement);
		}
	}
}


/***Functions to return specific information for Products or a SolutionSets ***/
//returns an index, if a dependent solutionset id is in the array otherwise it will return -1. 
function isDependentSolutionSetInList(dependentSolSetId){
	var currentId = dependentSolSetId.attr('id');
	return $.inArray(currentId, getSelectedCheckboxDependencyIDs());
}

//returns SolSets or Products dependency Ids
function getSelectedCheckboxDependencyIDs() {
	var SelectedCheckBoxes = $("."+currentCheckboxClass+":checkbox:checked").map(function() {
		return $(this).val();
	}).get();
	return SelectedCheckBoxes;
}

//returns SolSets or Products Ids
function getSelectedCheckboxIds() {
	var SelectedCheckBoxesIds = $("."+currentCheckboxClass+":checkbox:checked").map(function() {
		return $(this).attr('id');
	}).get();
	return SelectedCheckBoxesIds;
}

//returns SolSets or Products names
function getSelectedCheckBoxNames() {
	var SelectedCheckBoxes = $("."+currentCheckboxClass+":checkbox:checked").map(function() {
		return $(this).next().text();
	}).get();
	return SelectedCheckBoxes;
}

//returns a JSON like object that contains all of the details for SolSets or Products
function getSelectedCheckBoxList() {
	var SelectedCheckBoxes = $("."+currentCheckboxClass+":checkbox:checked").map(function() {
		var temp = [];
		temp.push({"id" : $(this).attr('id'), "value" : $(this).val() , "name" : $(this).next().text(), "description" : $(this).parent().next('p').text(), "classname" : $(this).attr('class')});
		return temp;
	}).get();
	return SelectedCheckBoxes;
}

/****Show/Hide Descriptions for Tabs and Tables*****/
//show/hide solution set descriptions
function showHideSolutionSetDescriptions() {
	$(this).next('button').toggleClass("tranformArrow");
	$(this).closest('button').toggleClass("tranformArrow");
	$(this).closest('td').toggleClass("shade");
	$(this).parent().next('.accordion').toggle();	
}

//show/hide tab descriptions
function hideTabDescription() {
	$('ul#tab-navigation li p').hide();
}
$('#tab-navigation a').click(function showTabDescription() {
	hideTabDescription();
	$(this).next('p').show();
});

//show/hide Solution Set Dependents in summary table.
function toggleDependentSolSetsInSummaryTable() {
	$(this).parents('tbody').children('tr:not(:first)').toggle();
	$(this).toggleClass("tranformArrow");
}


$('#login').click(function() {
	$('#LoginArea').toggle();
});

function pressDownBreadCrumbTabs() {
	
	$('#bread-crumb-tab-description').append($('#tab-navigation2 > li > a > p:first-child'));
	
	$('#tab-navigation2 > li:first-child').addClass('e-current');
	
	$('#tab-navigation2 > li > a').click(function() {
			
		$('li','#tab-navigation2').removeClass('e-current');
		
		if ($(this).attr('class') == "current") {
			$(this).parent().addClass('e-current');
		}
		
		$('#bread-crumb-tab-description').empty();
		
		var breadCrumbTabDescription = $(this).attr('title');
		$('#bread-crumb-tab-description').append(breadCrumbTabDescription);
	});
	
	
}



