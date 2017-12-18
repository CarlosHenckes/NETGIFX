<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="t" %>

<t:template>
	<jsp:attribute name="title">Seja Bem Vindo</jsp:attribute>
    
    <jsp:attribute name="header">
      <style type="text/css">
		body {
			background-image: url('<c:url value="/resources/images/bg_homepage.jpg" />');
			background-repeat: no-repeat;
			background-size: 1700px 601px;
		}
		
		.headerwrapper {
			height: 480px;
		}
		li.afterlogin {
			display: none;
		}
		.btn-signin {
			display: block;
		}
		</style>
    </jsp:attribute>
    <jsp:body>
    	<div class="container headerwrapper" style="padding-top: 120px;">
			<h1 style="font-size: 5em; text-shadow: 4px 4px rgba(0, 0, 0, 0.4); color: white;">O
				que vem a seguir...</h1>
			<p style="font-size: 1.2em; color: white;">EM QUALQUER LUGAR.
				CANCELE QUANDO QUISER.</p>
			<p>
				<a href="<c:url value="/register" />" class="nav-link btn btn-sm" 
				style="color: white; background-color: #CC0000; padding: 10px 30px; max-width:250px;">ASSISTA GRATIS POR UM MÊS</a>

			</p>
		</div>
		<div style="background-color: #141414; border-bottom: 2px solid #3d3d3d; padding: 35px 0;">
			<div class="row">
				<div class="col-md-4 text-center"
					style="color: white; font-size: 1.1em; font-weight: bold;">
					<img src="<c:url value="/resources/images/door.png" />" /><br /> Cancele quando quiser
				</div>
				<div class="col-md-4 text-center"
					style="color: white; font-size: 1.1em; font-weight: bold;">
					<img src="<c:url value="/resources/images/sets.png" />" /><br /> Assista em qualquer lugar
				</div>
				<div class="col-md-4 text-center"
					style="color: white; font-size: 1.1em; font-weight: bold;">
					<img src="<c:url value="/resources/images/ticket.png" />" /><br /> Faça seu
					preço
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-6"
					style="padding-left: 50px; padding: 100px 0;">
					<h3 style="color: white; font-size: 1.4em;">
						Se você desidir que NETGIFX não é para
						você - <br /> sem problemas. Sem compromisso. <br />
						Cancele online a qualquer momento.
					</h3>
					<a href="<c:url value="/register" />" class="nav-link btn btn-sm" 
				style="color: white; background-color: #CC0000; padding: 10px 30px; max-width:250px;">ASSISTA GRATIS POR UM MÊS</a>
				</div>
			</div>
		</div>
    </jsp:body>
</t:template>