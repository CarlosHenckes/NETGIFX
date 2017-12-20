<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:template>
	<jsp:attribute name="title">Login</jsp:attribute>
	<jsp:attribute name="header">
		<style type="text/css">
			body {
				background-image: url(<c:url value="/resources/images/bg_login.jpg" />);
				background-repeat: no-repeat;
				background-size: cover;
			}
			
			.headerwrapper {
				height: 550px;
			}
			
			.btn-signin {
				display: none;
			}
			
			li.afterlogin {
				display: none;
			}
		</style>
	</jsp:attribute>
	<jsp:body>
	<div class="container headerwrapper">
			<div class="row">

				<div style="width: 35%; background-color: #F3F3F3; padding: 25px 25px; margin: 0 auto;">
					
					<form action="<c:url value="/login" />" method="post">
						<span style="font-size: 2em; font-family: Calibri;">Login</span>
						
						<c:choose>
							<c:when test="${ not empty errMsg }">
								<div class="alert alert-danger">
									${errMsg}
								</div>
							</c:when>
						</c:choose>
						
						<c:choose>
							<c:when test="${ not empty msg }">
								<div class="alert alert-success">
									${msg}
								</div>
							</c:when>
						</c:choose>
						
						<div class="form-group">
							<label style="margin: 0;" for="mail">Email</label> <input type="text"
								class="form-control" id="mail" name="email" required="required" />
						</div>
						<div class="form-group">
							<label style="margin: 0;" for="pass">Password</label> <input type="password"
								class="form-control" id="pass" name="senha" required="required" />
						</div>

						<div class="form-group">
							<button class="btn"
								style="width: 99%; padding: 10px 0; background-color: #CC0000; color: white; font-size: 1.2em;">Sign
								in</button>
						</div>
						<div class="form-group form-inline">
							<input type="checkbox" class="form-control"
								style="height: 30px; width: 30px;" name="persistir" /><label
								style="padding-left: 20px;">lembrar-se</label>
							<hr class="divider" />
						</div>
						<img src="<c:url value="/resources/images/fb_logo.png" />" height="25"
							style="margin: 0 10px 10px 0;" /> <span
							style="font-family: Calibri;">Logar-se com o Facebook</span>
						<p>
							<a href="<c:url value="/register" />">Primeira visita? Assine agora.</a>
						</p>
					</form>
				</div>

			</div>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</jsp:body>

</t:template>
