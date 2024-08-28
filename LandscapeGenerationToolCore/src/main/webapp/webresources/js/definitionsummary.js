/***Definition Summary Functions***/
function deleteParent() {
	$('button.e-close').click(function() {
		var currentParent = $(this);
		
		tableChecker =  currentParent.parents('div').attr('class');
		if(tableChecker == "scrollsection2"){
			currentCheckboxClass = "solutionsetcheckbx";
			summaryTableMatcher = $("."+currentCheckboxClass).parents('table').parent('div').attr('id');
			tableChecker = summaryTableMatcher;
		}
		else if(tableChecker == "products-summary-table"){
			currentCheckboxClass = "productscheckbx";
			summaryTableMatcher = $("."+currentCheckboxClass).parents('table').parent('div').attr('id');
			tableChecker = summaryTableMatcher;	
		}
		
		//Remove Parent
		var parentId = currentParent.siblings('label').attr('id');
		$('#'+tableChecker+ ' input[id='+parentId+']').attr('checked', false);
		
		//Ensures SolutionSets are removed from their respective Table when the Product is deleted from the SummaryTable
		if(tableChecker == "productstable")
			tableChecker = "solutionsettable";
		
		//Gets All Dependency Rows from the All Summary Tables Except the Product Summary Table
		var dependencyRows = getAllSummaryTableDependencyRows(currentParent);
		
		//Checks or Uncheck Dependent SolutionSets based on the Parent Product or SolutionSet
		enableOrDisableChildrenRows(currentParent, dependencyRows, tableChecker);
		
		//Remove from Summary Table
		updateSummaryTable(getSelectedCheckBoxList());
	});
}

function getAllSummaryTableDependencyRows(currentObject){
	var tableIdentifier = currentObject.parent().find('label').text();
	var allSummaryTables = $('.summarytable');
	var dependencyRows = [];

	allSummaryTables.each(function(){
		var firstRow = $('tr:first label', this).text();
		var exceptFirstRow = $('tr:not(:first) label', this);
		
		//Do not add Parent Tables to Dependencies
		if(firstRow !== tableIdentifier){
			exceptFirstRow.each(function(){
				dependencyRows.push(this.id);
			});
		}
	});
	return dependencyRows;
}

function enableOrDisableChildrenRows(currentObject, dependencyRowsCollection, tableChecker){
	//Get all the rows for the current Summary Table
	var rows = $('tr:gt(0)', currentObject.parents('tbody'));
	for(var i=0; i<rows.length; i++){	
		var item = rows[i];
		var dependencyID = $('td label', item).attr('id');
		var checkboxField = $('#'+tableChecker+ ' input[id='+dependencyID+']');
		
		//False, enable the solutionSet not dependent in any other table
		if(dependencyRowsCollection.length>0){
			if($.inArray(dependencyID, dependencyRowsCollection) == -1){
				checkboxField	.attr('checked', false)
								.attr('disabled', false)
								.removeClass('productSolutionSet')
								.next('label').css({opacity : 1});
			}
			else if(currentCheckboxClass == "productscheckbx"){
				//If it is available in the summary table then only remove the class
				checkboxField	.removeClass('productSolutionSet');
			}
		}
		else{
			checkboxField	.attr('checked', false)
							.attr('disabled', false)
							.removeClass('productSolutionSet')
							.next('label').css({opacity : 1});
		}
		
	}
}

//Returns all of the dependencies for a parent
function getDependenciesList(index) {
	var SelectedCheckBoxes = getSelectedCheckBoxList();
    if (ulParentsDependency.length > 0 && SelectedCheckBoxes[index].value == 0) {
        for (var i = 0; i < ulParentsDependency.length; i++) {
            dependenciesCollection.push(ulParentsDependency[i][0]);
        }
        return dependenciesCollection;
    }
    return false;
}

function checkParentsDependency(index) {
	var SelectedCheckBoxes = getSelectedCheckBoxList();
	var currentChild = SelectedCheckBoxes[index].value;
	var nextChild = "";
	var newRow = $('<tr>');
	var newCell = $('<td>');
	var label = $('<label>');
	
	//Gets next nextChild based on the currentChilds value
	for(var i=0; i<SelectedCheckBoxes.length; i++){
		if(($.inArray(currentChild, SelectedCheckBoxes[i].id)!= -1)){
			nextChild = SelectedCheckBoxes[i].value;
			currentChild = i;
		}		
	}
	
	//Set attributes here as they can be re-used by single / multiple child
	label		.attr('id', SelectedCheckBoxes[currentChild].id)
				.attr('value', SelectedCheckBoxes[currentChild].value)
				.css('text-indent','50px')
				.text(SelectedCheckBoxes[currentChild].name);

	newCell		.append(label);

	newRow		.append(newCell)
				.css({'display':'none'});
	
	
	if (nextChild != 0 || nextChild == 0 && continueCheck == true) {
        
        ulParentsDependency.push(newRow);
   
        //Recursive Check, if there are still more dependencies, 
        //checkParentsDependency is called until there are no more children.
        var listChildren = getDependenciesList(currentChild);
        if (listChildren === false) {
        	continueCheck = true;
            listChildren = checkParentsDependency(currentChild);
        }
        //Clear Array and Dependencies for Re-use
        ulParentsDependency = [];
        dependenciesCollection = [];
        continueCheck = false;
        
        //Returns an array of all the children related to the parent
        return listChildren;
    }
    else if (nextChild == 0 && dependenciesCollection.length == 0) {
    	
    	//Returns single child only related to the parent
        return newRow;
    }
}

function updateSummaryTable(SelectedCheckBoxes) {
	for ( var i = 0; i < SelectedCheckBoxes.length; i++) {
		var table = $('<table class="summarytable">');
		var newRow = $('<tr>');
		var newCell = $('<td>');
		var buttonClose = $('<button>');
		var buttonOpen = $('<button>');
		var label = $('<label>');
		
		//Check to only allow Parent Nodes
		if($.inArray(SelectedCheckBoxes[i].id, getSelectedCheckboxDependencyIDs()) === -1){
			//Single Parent with No Dependencies, "else if" Parent has one or more dependencies
			if (SelectedCheckBoxes[i].value == 0) {
				
	            if(SelectedCheckBoxes[i].classname.indexOf("productscheckbx") == -1 && SelectedCheckBoxes[i].classname.indexOf("productSolutionSet") == -1){
	            	
	            	label		.attr('id', SelectedCheckBoxes[i].id)
	            				.attr('value', SelectedCheckBoxes[i].value)
	            				.css('text-indent','40px')
	            				.text(SelectedCheckBoxes[i].name);
	            	
	            	buttonClose	.addClass('e-icon e-window-control e-close')
	            				.attr('id', 'close-button');
	            	
	            	newCell		.append(label)
	            				.append(buttonClose);
	            	
	            	newRow		.append(newCell);
	            	
	            	table.append(newRow);
	            	tableCollection.push(table);
	            }
				//Check if it happens to be a Product, then add Dependent SolutionsSets to it
	            if(SelectedCheckBoxes[i].classname.indexOf("productscheckbx") != -1){
	            	var productId = SelectedCheckBoxes[i].id - 1;
	            	var prodSolsetAssocs = productList[productId].productSolutionsetAssociations; 
	            	
	            	
	            	label		.attr('id', SelectedCheckBoxes[i].id)
	            				.attr('value', SelectedCheckBoxes[i].value)
	            				.css('text-indent','40px')
	            				.text(SelectedCheckBoxes[i].name);
	            	
	            	buttonClose	.addClass('e-icon e-window-control e-close')
	            				.attr('id', 'close-button');
	            	
	            	buttonOpen	.addClass('e-icon e-history e-next')
        						.attr('id', 'open-button');
	            	
	            	newCell		.append(label)
	            				.append(buttonOpen)
	            				.append(buttonClose);
	            	
	            	newRow		.append(newCell);
	            	
	            	table		.append(newRow);	    
	            	
	            	$.each(prodSolsetAssocs, function(){
	            		if($('fieldset label','#solutionsettable').text().indexOf(this.solutionSetDTO.name) != -1){
	            			
	            			var newRowObj = $('<tr>');
	    	            	var newCellObj = $('<td>');
	            			var labelObj = $('<label>');
	            			
	            			labelObj	.attr('id', this.solutionSetDTO.solutionSetId)
            							.attr('value', this.solutionSetDTO.solutionSetDependentId)
            							.css('text-indent','50px')
            							.text(this.solutionSetDTO.name);
	            			
	            			newCellObj 	.append(labelObj);
	            			
	            			newRowObj	.append(newCellObj)
	            						.css({'display':'none'});
	            			
	            			table		.append(newRowObj);	  
	            		}
	            	});
	            	tableCollection.push(table);
	            }
			}
			else if (SelectedCheckBoxes[i].value != 0) {
				label		.attr('id', SelectedCheckBoxes[i].id)
							.attr('value', SelectedCheckBoxes[i].value)
							.css('text-indent','40px')
							.text(SelectedCheckBoxes[i].name);
	
				buttonClose	.addClass('e-icon e-window-control e-close')
							.attr('id', 'close-button');
				
				buttonOpen	.addClass('e-icon e-history e-next')
							.attr('id', 'open-button');
				
				newCell		.append(label)
							.append(buttonOpen)
							.append(buttonClose);
				
				newRow		.append(newCell)
							.addClass('first');
				
				table		.append(newRow);
				//Add Children to Parent Table
				table.append(checkParentsDependency(i));
	            tableCollection.push(table);
			}
		}
	}
	pushSummaryTableChanges();
}

function pushSummaryTableChanges(){
	//Check which table is to be populated
	if(summaryTableMatcher == "solutionsettable"){
		$('.scrollsection2').empty();
		if (tableCollection.length > 0) {
			//Add all parents table(s) to the div
			for (var j = 0; j < tableCollection.length; j++) {
				$('.scrollsection2').append(tableCollection[j]);
			}
		}
		$('.scrollsection2 .e-next').click(toggleDependentSolSetsInSummaryTable);
	}
	else{
		$('.products-summary-table').empty();
		//Find all SolutionSets that have products as Parents and Remove them from the SolutionSetTable if they're already there.
		if($('label', '.scrollsection2')){
			var productSolutionSet = $('.productSolutionSet');
			for(var i=0; i<productSolutionSet.length; i++){
				var item = productSolutionSet[i];
				$('.scrollsection2').find('label:[id='+item.id+']').parents('tbody').children().eq(0).find('label:[id='+item.id+']').parents('table').remove();
			}
		}
		if (tableCollection.length > 0) {
			//Add all parents table(s) to the div
			for (var j = 0; j < tableCollection.length; j++) {
				$('.products-summary-table').append(tableCollection[j]);
			}
			$('.products-summary-table').prev('h4').css('border-bottom','1px solid #D2D2D2');
			$('.products-summary-table').css('min-height',' 0px');
			$('.products-summary-table').css('min-height',function(){
				return $(this).height() + 1;
			});
			
		}
		else{
			$('.products-summary-table').prev('h4').css('border-bottom','0px solid #D2D2D2');
			$('.products-summary-table').css('min-height',' 0px');
		}
		$('.products-summary-table .e-next').click(toggleDependentSolSetsInSummaryTable);
	}
	//Clear array
	tableCollection = [];
	deleteParent();
}