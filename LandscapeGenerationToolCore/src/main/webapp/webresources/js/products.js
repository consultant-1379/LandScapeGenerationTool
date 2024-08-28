//Loads JSON object data into the solutions sets table
function createAndPopulateProductsTable(){
	$.getJSON('products.json', function(data) {
		productList = data;

		//console.log("Data Loaded: ", data);
		var table = $('<table class="table" id="tableproducts"></table>').appendTo($('#productstable'));
		for(var i=0; i<data.length; i++){
			var item = data[i];
			
			var newInput = $('<input id="'+item.productId+'" type="checkbox" class="productscheckbx"  value="0">');
			var newLabel = $('<label for="'+item.name+'" value="'+item.productId+'"id=label'+item.productId+'>'+item.name+ '</label><button class="e-icon e-history e-next" ></button>');
			var descriptionLabel = $('<label for="'+item.description+'"id=description'+item.productId+' class="description">'+item.description+'</label>');
			// add click listeners
			newInput.click(setCheckBoxSelection);
			newLabel.click(showHideSolutionSetDescriptions);

			var newFieldset = $('<fieldset class="e-formButton">');
			newFieldset.append(newInput);
			newFieldset.append(newLabel);

			var descriptionFieldset = $('<p class="accordion"></p>');
			descriptionFieldset.append(descriptionLabel);

			var newCell = $('<td></td>');
			newCell.append(newFieldset);
			newCell.append(descriptionFieldset);

			var newRow = $('<tr class="heading"></tr>');
			newRow.append(newCell);

			table.append(newRow);
		}
	});
}

//Return the list of SolutionsSets related to Products
function getSelectedProductsCheckboxDependencyIDs() {
	var SelectedCheckBoxes = $(".solutionsetcheckbx:checkbox:checked").map(function() {
		return parseInt($(this).val());
	}).get();
	return SelectedCheckBoxes;
}

//For each Product selected the related Solutions are checked in the SolutionSet Table
function setSolutionSetEnabledBasedOnProduct(checkboxElement){
	var checkCheckboxIsChecked = checkboxElement;
	var currentCheckBoxName = checkboxElement.next('label').text();
	var currentProduct = checkboxElement.attr('id') - 1;
	
	var multipleTables = $('.summarytable');
	var multipleDependencies = [];

	//Return Dependenices that occur twice
	for(var i=0; i<multipleTables.length; i++){
		var item = multipleTables[i];
		var product = $('tr:first label', item).text();
		var solutionSet = $('tr:not(:first) label', item);
		if(product !== currentCheckBoxName){
			solutionSet.each(function(){
				multipleDependencies.push(parseInt(this.id));
			});
		}
	}
	
	
	//Get associated SolutionSets from the Product 
	var prodSolsetAssocs = productList[currentProduct].productSolutionsetAssociations;
	
	//For each product enable/disable solution set
	for(var i=0; i<prodSolsetAssocs.length; i++){
		var item = prodSolsetAssocs[i];
		
		var currentSolutionSetID = item.solutionSetDTO.solutionSetId;
		//Search for solutionSet in the SS-Table with corresponding ID
		var solutionSet = $('input[id='+currentSolutionSetID+']','#solutionsettable');
		
		//If the current Solution Set is not already independently selected from the Products 
		if(checkCheckboxIsChecked.prop("checked")){
			//then apply the checkbox:checked and disable it
			solutionSet	.prop('checked', true)
						.prop('disabled',true)
						.addClass('productSolutionSet')
						.next('label').css({opacity : 0.5});
		}
		else if($.inArray(currentSolutionSetID, multipleDependencies) == -1 || !(multipleDependencies.length>0)){
			//then apply the checkbox:unchecked and enable it
			//if the Product shares a dependency with another then don't modify it
			solutionSet	.prop('checked', false)
			 			.prop('disabled',false)
			 			.removeClass('productSolutionSet')
			 			.next('label').css({opacity : 1});
		}
		else{
			/*productSolutionSet is used to determine whether the 
			solutionSet was originally enabled/disabled when clicking the Product*/
			solutionSet.removeClass('productSolutionSet');
		}
	}
	
	//Once complete the Summary Table is updated
	updateSummaryTable(getSelectedCheckBoxList());
}