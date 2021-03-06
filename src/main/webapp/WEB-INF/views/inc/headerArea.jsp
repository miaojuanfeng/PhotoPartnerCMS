<div class="header-area">

			<h1 class="btn btn-primary btn-md"><i class="glyphicon glyphicon-check"></i>&nbsp;&nbsp;&nbsp;JoyHong CMS</h1>
			<div class="shadow-area"></div>

			<div class="menu-area">
				<div class="container-fluid">
					<div class="row">
						<div class="menu-column-area col-lg-3 col-md-3 col-sm-6 col-xs-6 col-ms-6">
							<h3 class="corpcolor-font">Dashboard</h3>
							<ul>
								<li><a href="<c:url value="/cms/dashboard/select"></c:url>">Dashboard</a></li>
							</ul>
						</div>
						
						<div class="menu-column-area col-lg-3 col-md-3 col-sm-6 col-xs-6 col-ms-6">
							<h3 class="corpcolor-font">Order / Device</h3>
							<ul>
								<li><a href="<c:url value="/cms/category/select/sample"></c:url>">Sample</a></li>
								<li><a href="<c:url value="/cms/category/select/order"></c:url>">Order</a></li>
							</ul>
						</div>
						
						<div class="menu-column-area col-lg-3 col-md-3 col-sm-6 col-xs-6 col-ms-6">
							<h3 class="corpcolor-font">User</h3>
							<ul>
								<li><a href="<c:url value="/cms/user/select/all"></c:url>">All</a></li>
								<li><a href="<c:url value="/cms/user/select/app"></c:url>">App</a></li>
								<li><a href="<c:url value="/cms/user/select/facebook"></c:url>">Facebook</a></li>
								<li><a href="<c:url value="/cms/user/select/twitter"></c:url>">Twitter</a></li>
							</ul>
						</div>
						
						<div class="menu-column-area col-lg-3 col-md-3 col-sm-6 col-xs-6 col-ms-6">
							<h3 class="corpcolor-font">System</h3>
							<ul>
								<li><a href="<c:url value="/cms/config/update"></c:url>">Account</a></li>
								<li><a href="<c:url value="/cms/version/select"></c:url>">Version</a></li>
							</ul>
						</div>

					</div>
				</div>
			</div>

		</div> <!-- header-area -->


		<div class="btn-group show-myself">
			<a href="#" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown">
				<i class="glyphicon glyphicon-user"> ${user_nickname}</i>
			</a>
			<ul class="dropdown-menu dropdown-menu-right" role="menu">
				<li><a href="<c:url value="/cms/logout"></c:url>">Logout: ${user_nickname}</a></li>
			</ul>
		</div>