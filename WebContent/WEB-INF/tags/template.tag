<%@attribute name="title" fragment="true" required="false"%>
<%@attribute name="header" fragment="true" required="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-BR">
<head>
<title><jsp:invoke fragment="title" /> - NETGIFX</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="<c:url value="/resources/stylesheets/common.css" />" />
<jsp:invoke fragment="header" />
</head>
<body>
	<div id="header" class="container">

		<div class="navbar navbar-inverse opaque-navbar">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navMain">
						<span class="glyphicon glyphicon-chevron-right"
							style="color: white;"></span>
					</button>
					
					<a class="navbar-brand" href="<c:url value="/" />"><img
						src="<c:url value="/resources/images/netgifs_logo.png" />" /></a>
						
				</div>
				<div class="collapse navbar-collapse" id="navMain">

					<ul class="nav navbar-nav pull-left" style="margin-top: 10px;">
						<li class="afterlogin no-hover" style="margin-left: 25px;"><a
							href="<c:url value="/content" />"
							style="color: Red; font-weight: bolder; font-size: 1.3em;">Browse
								movies</a></li>
					</ul>

					<ul class="nav navbar-nav pull-right">
						
						<li class="btn-signin" style="margin-top:20px;">
							<a class="btn" style="color: white; line-height: 0.4; background-color: #CC0000;
							 width: 80px; height: 35px; font-size: 1.2em;" href="<c:url value="/login" />">Entrar</a>
						</li>

						<li class="afterlogin"><img
							src="<c:url value="/resources/images/ico_usuario.gif" />"
							width="25" style="margin-top: 20px;" /></li>

						<li class="dropdown nav-user afterlogin" style="font-size: 1.3em; padding-top:10px;">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false"
							style="color: White;">usuário <span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item"
									href="<c:url value="/profile" />" style="font-weight: bolder;">Minha
										conta</a></li>
								<li><a class="dropdown-item"
									href="<c:url value="/logout" />" style="font-weight: bolder;">Sair</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>



	</div>
	<div id="content">
		<jsp:doBody />
	</div>


</body>
</html>