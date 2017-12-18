<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="t" %>

<t:template>
	<jsp:attribute name="title">
		Assista aos filmes
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<style type="text/css">
			.carousel-showmanymoveone .carousel-control {
			  width: 4%;
			  background-image: none;
			}
			.carousel-showmanymoveone .carousel-control.left {
			  margin-left: 15px;
			}
			.carousel-showmanymoveone .carousel-control.right {
			  margin-right: 15px;
			}
			.carousel-showmanymoveone .cloneditem-1,
			.carousel-showmanymoveone .cloneditem-2,
			.carousel-showmanymoveone .cloneditem-3 {
			  display: none;
			}
			@media all and (min-width: 768px) {
			  .carousel-showmanymoveone .carousel-inner > .active.left,
			  .carousel-showmanymoveone .carousel-inner > .prev {
			    left: -50%;
			  }
			  .carousel-showmanymoveone .carousel-inner > .active.right,
			  .carousel-showmanymoveone .carousel-inner > .next {
			    left: 50%;
			  }
			  .carousel-showmanymoveone .carousel-inner > .left,
			  .carousel-showmanymoveone .carousel-inner > .prev.right,
			  .carousel-showmanymoveone .carousel-inner > .active {
			    left: 0;
			  }
			  .carousel-showmanymoveone .carousel-inner .cloneditem-1 {
			    display: block;
			  }
			}
			@media all and (min-width: 768px) and (transform-3d), all and (min-width: 768px) and (-webkit-transform-3d) {
			  .carousel-showmanymoveone .carousel-inner > .item.active.right,
			  .carousel-showmanymoveone .carousel-inner > .item.next {
			    -webkit-transform: translate3d(50%, 0, 0);
			            transform: translate3d(50%, 0, 0);
			    left: 0;
			  }
			  .carousel-showmanymoveone .carousel-inner > .item.active.left,
			  .carousel-showmanymoveone .carousel-inner > .item.prev {
			    -webkit-transform: translate3d(-50%, 0, 0);
			            transform: translate3d(-50%, 0, 0);
			    left: 0;
			  }
			  .carousel-showmanymoveone .carousel-inner > .item.left,
			  .carousel-showmanymoveone .carousel-inner > .item.prev.right,
			  .carousel-showmanymoveone .carousel-inner > .item.active {
			    -webkit-transform: translate3d(0, 0, 0);
			            transform: translate3d(0, 0, 0);
			    left: 0;
			  }
			}
			@media all and (min-width: 992px) {
			  .carousel-showmanymoveone .carousel-inner > .active.left,
			  .carousel-showmanymoveone .carousel-inner > .prev {
			    left: -25%;
			  }
			  .carousel-showmanymoveone .carousel-inner > .active.right,
			  .carousel-showmanymoveone .carousel-inner > .next {
			    left: 25%;
			  }
			  .carousel-showmanymoveone .carousel-inner > .left,
			  .carousel-showmanymoveone .carousel-inner > .prev.right,
			  .carousel-showmanymoveone .carousel-inner > .active {
			    left: 0;
			  }
			  .carousel-showmanymoveone .carousel-inner .cloneditem-2,
			  .carousel-showmanymoveone .carousel-inner .cloneditem-3 {
			    display: block;
			  }
			}
			@media all and (min-width: 992px) and (transform-3d), all and (min-width: 992px) and (-webkit-transform-3d) {
			  .carousel-showmanymoveone .carousel-inner > .item.active.right,
			  .carousel-showmanymoveone .carousel-inner > .item.next {
			    -webkit-transform: translate3d(25%, 0, 0);
			            transform: translate3d(25%, 0, 0);
			    left: 0;
			  }
			  .carousel-showmanymoveone .carousel-inner > .item.active.left,
			  .carousel-showmanymoveone .carousel-inner > .item.prev {
			    -webkit-transform: translate3d(-25%, 0, 0);
			            transform: translate3d(-25%, 0, 0);
			    left: 0;
			  }
			  .carousel-showmanymoveone .carousel-inner > .item.left,
			  .carousel-showmanymoveone .carousel-inner > .item.prev.right,
			  .carousel-showmanymoveone .carousel-inner > .item.active {
			    -webkit-transform: translate3d(0, 0, 0);
			            transform: translate3d(0, 0, 0);
			    left: 0;
			  }
			}
			li.btn-signin {
				display: none;
			}
			.ng-car {
				width: 270px;
			}
			h3 {
				color: White;
			}
			body {
				background-image: url(<c:url value="/resources/images/bg_content.jpg" />);
				background-repeat: no-repeat;
				background-size: cover;
			}
		</style>
	</jsp:attribute>
	
	<jsp:body>
		<div class="container"><!-- page container -->
		
			<!-- Carousel items -->

		<c:forEach var="cat" items="${ grupos }">
		 <c:if test="${ fn:length(cat.filmes) gt 0 }">
		  <h3>${ cat.categoria.categoria }</h3>
			<c:set var="act" value="true" scope="page" />
			<div class="row"><!-- row -->
			    <div class="col-md-12">
			      <div class="carousel carousel-showmanymoveone slide" id="carousel${ cat.categoria.id }">
			        <div class="carousel-inner">
			        	<c:forEach var="fil" items="${ cat.filmes }">
			        		<c:choose>
							    <c:when test="${pageScope.act == 'true'}">
							        <div class="item active">
							    </c:when>
							    <c:otherwise>
							        <div class="item">
							    </c:otherwise>
							</c:choose>
							<c:set var="act" value="false" scope="page" />
				            	<div class="col-xs-12 col-sm-6 col-md-3">
				            		<a href="<c:url value="/watch?id=${ fil.id }" />" title="${ fil.titulo }"><img src="<c:url value="${ fil.previewImagePath }" />" class="img-responsive"></a>
			            		</div>
				          	</div>
						</c:forEach>  
			        </div>
			        <a class="left carousel-control" href="#carousel${ cat.categoria.id }" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
			        <a class="right carousel-control" href="#carousel${ cat.categoria.id }" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
			      	
			      </div>
			    </div>
			  </div><!-- /row -->
		  </c:if>
		</c:forEach>	
		
			<!--/carousel-items-->
			
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>

		</div><!-- /page container -->

		
		<script type="text/javascript">
		(function(){
			  // setup your carousels as you normally would using JS
			  // or via data attributes according to the documentation
			  // http://getbootstrap.com/javascript/#carousel
			  $('#carousel1').carousel({ interval: null });
			  $('#carousel2').carousel({ interval: null });
			  $('#carousel3').carousel({ interval: null });
			}());

			(function(){
			  $('.carousel-showmanymoveone .item').each(function(){
			    var itemToClone = $(this);

			    for (var i=1;i<4;i++) {
			      itemToClone = itemToClone.next();

			      // wrap around if at end of item collection
			      if (!itemToClone.length) {
			        itemToClone = $(this).siblings(':first');
			      }

			      // grab item, clone, add marker class, add to collection
			      itemToClone.children(':first-child').clone()
			        .addClass("cloneditem-"+(i))
			        .appendTo($(this));
			    }
			  });
			}());
		</script>
	</jsp:body>

</t:template>
