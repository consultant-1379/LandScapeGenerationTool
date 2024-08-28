//Quicksearch, filters out solution sets
function quickSearch() {
	$(".kwd_search").keyup(function(){
		// When value of the input is not blank
		if($(this).attr('placeholder')=="Search for a Solution Set"){
			if( $(this).val() != "")
			{
				// Show only matching TR, hide rest of them
				$("#tablesolutionset tbody>tr").hide();
				$("#tablesolutionset td fieldset:contains-ci('" + $(this).val() + "')").parents("tr").show();
			}
			else
			{
				// When there is no input or clean again, show everything back
				$("#tablesolutionset tbody>tr").show();
			}
		}
		if($(this).attr('placeholder')=="Search for a Product"){
			if( $(this).val() != "")
			{
				// Show only matching TR, hide rest of them
				$("#tableproducts tbody>tr").hide();
				$("#tableproducts td fieldset:contains-ci('" + $(this).val() + "')").parents("tr").show();
			}
			else
			{
				// When there is no input or clean again, show everything back
				$("#tableproducts tbody>tr").show();
			}
		}
	});
}
//jQuery expression for case-insensitive filter 
//Must be declared outside quicksearch function
//Otherwise the first search on page load will not function properly
$.extend($.expr[":"], 
		{
	"contains-ci": function(elem, i, match, array) 
	{
		return (elem.textContent || elem.innerText || $(elem).text() || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
	}
});