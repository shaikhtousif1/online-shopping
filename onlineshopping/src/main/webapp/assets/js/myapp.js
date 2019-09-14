$(function() {

	// solving the active menu problem

	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;

	case 'Contact Us':
		$('#contact').addClass('active');
		break;

	case 'All Products':
		$('#listProducts').addClass('active');
		break;

	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for jquery

	var $table = $('#productListTable');

	// execute below code only where we have this table

	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId != '') {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';

		} else {

			jsonUrl = window.contextRoot + '/json/data/all/products';

		}

		$table.DataTable({

			lengthMenu : [ [ 3, 5, 10, -1 ],
					[ '3 Record', '5 Record', '10 Record', 'ALL' ] ],
			pageLength : 5,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [ 
				{

					data : 'code',
					mRender : function(data, type, row) {
						
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				
				{

				data : 'name'
			}, {

				data : 'brand'
			}, {

				data : 'unitPrice',
				mRender : function(data, type, row) {

					return '&#8377;' + data;
				}
			}, {

				data : 'quantity'
			}, {

				data : 'id',
				mRender : function(data, type, row) {
					
					var str='';
					str+= '<a href="/'+window.contextRoot+'/show/'+data+'/product">View</a> &#160';
					str+= '<a href="/'+window.contextRoot+'/cart/add/'+data+'/product">Add to Cart</a>';
					return str;
				}
			} ]
		})	

	}
});