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

	case 'Manage Products':
		$('#manageProducts').addClass('active');
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

		$table
				.DataTable({

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

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}
							},

							{

								data : 'name'
							},
							{

								data : 'brand'
							},
							{

								data : 'unitPrice',
								mRender : function(data, type, row) {

									return '&#8377;' + data;
								}
							},
							{

								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock</span>';
									}
									return data;
								}
							},
							{

								data : 'id',
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary">View</a>';
									if (row.quantity < 1) {

										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a>';
									} else {
										str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a>';
									}
									return str;
								}
							} ]
				})

	}
	// dismissing the alert after 3 seconds
	var $alert = $('.alert');
	if ($alert.length) {

		setTimeout(function() {

			$alert.fadeOut('slow');
		}, 3000)
	}

	// ---------------------------------------------------

	// --------------------Add data table for admin------------

	var $adminsProductsTable = $('#adminProductsTable');

	// execute below code only where we have this table

	if ($adminsProductsTable.length) {

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminsProductsTable
				.DataTable({

					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Record', '30 Record', '50 Record', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{

								data : 'code',
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImgMP"/>';
								}
							},

							{

								data : 'name'
							},
							{

								data : 'brand'
							},

							{

								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock</span>';
									}
									return data;
								}
							},
							{

								data : 'unitPrice',
								mRender : function(data, type, row) {

									return '&#8377;' + data;
								}
							},
							{

								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<label class="switch">';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '">';
									} else {
										str += '<input type="checkbox"  value="'
												+ row.id + '">';
									}
									str += '<div class="slider"></div></label>';
									return str;
								}
							},
							{

								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-warning">Edit</a>';
									return str;
								}
							}

					],
					initComplete : function() {

						var api = this.api();
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {
											var dText = (this.checked) ? 'You want to activate the Product?'
													: 'You want to de-activate the Product?';
											var checked = this.checked;
											var checkbox = $(this);
											var value = checkbox.prop('value');
											debugger;
											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation/Deactivation',
														message : dText,
														callback : function(
																confirmed) {
															if (confirmed) {

																console
																		.log(value);
																var activationUrl = window.contextRoot
																		+ '/manage/products/'
																		+ value
																		+ '/activation';
																$
																		.post(
																				activationUrl,
																				function(
																						data) {
																					bootbox
																							.confirm({

																								size : 'medium',
																								title : 'Information',
																								message : data
																							});
																				});

															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													});

										});
					}
				})

	}

	// ------------------------------------------------------
	// -----validation code for category-------

	var categoryForm = $('#categoryForm');

	if (categoryForm.length) {

		categoryForm
				.validate({

					rules : {
						name : {

							required : true,
							minlength : 2
						},
						description : {
							required : true
						}
					},
					messages : {

						name : {

							required : 'Please add the Category Name',
							minlength : 'The Category name should not be less than 2 character'
						},
						description : {
							required : 'Please add the description for this category'
						}
					},
					errorElement:'em',
					errorPlacement:function(error,element){
						//add the class of help-block
						error.addClass('help-block');
						//add the error element after the input element
						error.insertAfter(element);
						
					}

				});
	}
//---------------------------------------------------------------
});