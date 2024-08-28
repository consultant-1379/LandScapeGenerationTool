//Loads JSON object data into the solutions sets table
function createAndPopulateTable(){
	$.getJSON('SolutionsetList.json', function(data) {
		 solutionSetList = data;

		//console.log("Data Loaded: ", data);
		var table = $('<table class="table" id="tablesolutionset"></table>').appendTo($('#solutionsettable'));

		for(var i=0; i<data.length; i++){
			var item = data[i];
			var newInput = $('<input id="'+item.solutionSetId+'" type="checkbox" class="solutionsetcheckbx" value="'+item.solutionSetDependentId+'">');
			var newLabel = $('<label for="'+item.name+'" value="'+item.solutionSetId+'"id=label'+item.solutionSetId+'>'+item.name+ '</label><button class="e-icon e-history e-next" ></button>');
			var descriptionLabel = $('<label for="'+item.description+'"id=description'+item.solutionSetId+' class="description">'+item.description+'</label><span style="display:none !important;" id="version">'+item.version+'</span>');
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