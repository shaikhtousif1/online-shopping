
<!-- Navigation -->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">

				<li class="nav-item" id="about"><a class="nav-link"
					href="${contextRoot}/about">About</a></li>

				<li class="nav-item" id="contact"><a class="nav-link"
					href="${contextRoot}/contact">Contact</a></li>

				<li class="nav-item" id="listProducts"><a class="nav-link"
					href="${contextRoot}/show/all/products">View Products</a></li>

				<li class="nav-item" id="manageProducts"><a class="nav-link"
					href="${contextRoot}/manage/products">Manage Products</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="nav-item" id="register"><a class="nav-link"
					href="${contextRoot}/register">Sign Up</a></li>
				<li class="nav-item" id="login"><a class="nav-link"
					href="${contextRoot}/login">Login</a></li>
				<li class="dropdown"><a href="javascript:void(0)"
					class="btn btn-default dropdown-toggle" id="dropdownMenu1"
					data-toggle="dropdown"> Full Name <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${contextRoot}/cart"> <span
								class="glyphicon glyphicon-shopping-cart"></span> <span
								class="badge">0</span> - &#8377; 0.0
						</a></li>
						<li class="divider" role="separator"></li>
						<li><a href="${contextRoot}/logout">Logout</a></li>

					</ul></li>

			</ul>
		</div>
	</div>
</nav>

