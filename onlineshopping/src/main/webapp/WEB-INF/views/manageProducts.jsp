<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">

	<div class="row">
		<c:if test="${not empty message}">

			<div class="col-md-offset-2 col-md-12">
				<div class="alert alert-success alert-dismissible">

					<button type="button" class="close" data-dismiss="alert">&times;</button>

					${message}
				</div>

			</div>
		</c:if>

		<div class="col-md-offset-2 col-md-12">
			<div class="card bg-primary text-white text-center">
				<div class="card-body">Product Management</div>

			</div>

			<div class="card">

				<!-- FORM elements here -->
				<sf:form class="form-horizontal" modelAttribute="product"
					action="${contextRoot}/manage/products" method="POST"
					enctype="multipart/form-data">
					<div class="form-group">
						<label class="control-label col-md-4" for="name">Enter
							Product name</label>
						<div class="col-md-12">
							<sf:input type="text" path="name" id="name"
								placeholder="Product Name" class="form-control" />
							<sf:errors path="name" cssClass="help-block" element="em" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4" for="name">Enter
							Brand name</label>
						<div class="col-md-12">
							<sf:input type="text" path="brand" id="brand"
								placeholder="Brand Name" class="form-control" />
							<sf:errors path="brand" cssClass="help-block" element="em" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4" for="description">Enter
							Description</label>
						<div class="col-md-12">
							<sf:textarea path="description" id="description"
								placeholder="Write a description" class="form-control"></sf:textarea>
							<sf:errors path="description" cssClass="help-block" element="em" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4" for="unitPrice">Enter
							Unit Price</label>
						<div class="col-md-12">
							<sf:input type="number" path="unitPrice" id="unitPrice"
								placeholder="Unit Price in &#8377" class="form-control" />
							<sf:errors path="unitPrice" cssClass="help-block" element="em" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4" for="quantity">Enter
							Quantity</label>
						<div class="col-md-12">
							<sf:input type="number" path="quantity" id="quantity"
								placeholder="Quantity Available" class="form-control" />

						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4" for="file">Select an
							Image:</label>
						<div class="col-md-12">
							<sf:input type="file" path="file" id="file" class="form-control" />
							<sf:errors path="file" cssClass="help-block" element="em"></sf:errors>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4" for="categoryId">Select
							Category</label>
						<div class="col-md-12">

							<sf:select class="form-control" id="categoryId" path="categoryId"
								items="${categories}" itemLabel="name" itemValue="id" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 col-md-12">
							<input type="submit" name="submit" id="submit" value="Submit"
								class="btn btn-primary" />

						</div>

						<!-- Hidden field -->
						<sf:hidden path="id"></sf:hidden>
						<sf:hidden path="code"></sf:hidden>
						<sf:hidden path="supplierId"></sf:hidden>
						<sf:hidden path="active"></sf:hidden>
						<sf:hidden path="purchases"></sf:hidden>
						<sf:hidden path="views"></sf:hidden>
					</div>

				</sf:form>

			</div>
		</div>

	</div>

	<div class="row">

		<div class="col-md-offset-2 col-md-12">
			<h3>Available Product</h3>
			</hr>
		</div>
		<div class="col-md-offset-2 col-md-12">
			<div style="overflow: auto">

				<table id="adminProductsTable"
					class="table table-stripped table-bordered">

					<thead>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Qty. Avail</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>4</td>
							<td><img class="dataTableImgMP"
								src="${contextRoot}/resources/images/1234.jpg" /></td>
							<td>Xiaomi</td>
							<td>5</td>
							<td>&#8377; 10000.00</td>
							<td><label class="switch"> <input type="checkbox"
									checked="checked" value="4">
									<div class="slider"></div>

							</label></td>
							<td>
							<a href="${contextRoot}/manage/4/product" class="btn btn-warning">Edit</a>
							
							</td>

						</tr>
					</tbody>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Qty. Avail</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</tfoot>


				</table>

			</div>

		</div>


	</div>


</div>