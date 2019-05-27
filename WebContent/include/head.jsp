
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>购物网站</title>
<meta name="description" content="">
<meta name="robots" content="noindex, follow" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- favicon
    ============================================ -->
<link rel="shortcut icon" type="image/x-icon"
	href="assets/images/favicon.ico">


<!-- CSS files
    ============================================ -->

<!-- Boostrap stylesheet -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">

<!-- Icon Font CSS -->
<link rel="stylesheet" href="assets/css/ionicons.min.css">
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/pe-icon-7-stroke.css">

<!-- Plugins stylesheet -->
<link rel="stylesheet" href="assets/css/plugins.css">

<!-- Master stylesheet -->
<link rel="stylesheet" href="assets/css/style.css">

<!-- Responsive stylesheet -->
<link rel="stylesheet" href="assets/css/responsive.css">

<!-- modernizr JS
    ============================================ -->
<script src="assets/js/modernizr-2.8.3.min.js"></script>
<!-- jQuery JS -->
<script src="assets/js/jquery.1.12.4.min.js"></script>
</head>

<body>



	<!--[if lt IE 9]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please upgrade your browser to improve your experience.</p>
    <![endif]-->

	<!-- Start of Whole Site Wrapper with mobile menu support -->
	<div id="whole" class="whole-site-wrapper color-scheme-one">
		<header class="header bgc-white inner-header" id="header">
			<div class="header-area">
				<div class="container">
					<div class="row">
						<div class="col-12 col-sm-12 col-md-12 col-lg-3 align-self-center">
							<div class="logo">
								<a href="user/home.jsp"><img src="assets/images/logo.png"
									alt="Logo" class="img-fluid"></a>
							</div>
						</div>
						<div class="col-12 col-sm-12 col-md-12 col-lg-9">
							<div class="top-header">
								<div class="row align-items-center">
									<div
										class="col-12 col-sm-12 col-md-7 col-lg-6 order-md-2 order-lg-1">
										<div class="top-search">
											<p>
												你可能想搜索: <a href="User_Productslist_list?page.type=手机">手机</a>,
												<a href="User_Productslist_list?page.type=电脑">电脑</a>, <a
													href="User_Productslist_list?page.type=耳机">耳机</a>...
											</p>
										</div>
									</div>
									<div
										class="col-12 col-sm-12 col-md-12 col-lg-6 order-md-1 order-lg-2">
										<ul class="list-inline">
											<li class="top-links list-inline-item">
												<div class="btn-group">
													<button class="btn-link dropdown-toggle" id="user">
														<i class="fa fa-angle-down"></i>
													</button>
													<div class="dropdown-menu">
														<ul id="account">
															<li><a href="user/login.jsp">登录</a></li>
															<li><a href="user/register.jsp">注册</a></li>
														</ul>
													</div>
												</div>
											</li>
											<li class="language list-inline-item">
												<div class="btn-group">
													<button class="btn-link dropdown-toggle">
														<img src="assets/images/icons/en-gb.png" alt="Icons">
														English <i class="fa fa-angle-down"></i>
													</button>
													<div class="dropdown-menu">
														<ul>
															<li><a href="#"><img
																	src="assets/images/icons/en-gb.png" alt="Icons">
																	English</a></li>
															<li><a href="#"><img
																	src="assets/images/icons/fr-fr.png" alt="Icons">
																	French</a></li>
														</ul>
													</div>
												</div>
											</li>
											<li class="currency list-inline-item">
												<div class="btn-group">
													<button class="btn-link dropdown-toggle">
														USD <i class="fa fa-angle-down"></i>
													</button>
													<div class="dropdown-menu">
														<ul>
															<li><a href="#">EUR Euro</a></li>
															<li><a href="#">lb Pound Sterling</a></li>
															<li><a href="#">$ US Dollar</a></li>
														</ul>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- end of top-header -->

							<div class="bottom-header">
								<div class="row align-items-center">
									<div class="col-12 col-sm-12 col-md-7 col-lg-7">
										<div class="header-search-area">
											<div class="search-categories">
												<form action="products" method="post">
													<div class="search-form-input">
														<select id="select" name="select" class="nice-select">
															<option>所有商品</option>
															<option>电脑</option>
															<option>手机</option>
															<option>耳机</option>
															<option>摄像</option>
															<option>家电</option>
															<option>路由器</option>
															<option>智能手表/手环</option>
														</select>
														<div class="search-wrapper">
															<input name="key" type="text" placeholder="搜索">
															<button class="header-search-btn" type="submit">
																<i class="ion ion-ios-search"></i>
															</button>
														</div>
													</div>
												</form>
											</div>
										</div>
										<!-- end of header-search-area -->
									</div>
									<div class="col-12 col-sm-12 col-md-5 col-lg-5">
										<div class="header-cart-area">
											<div class="header-cart">
												<div class="btn-group">
													<button class="btn-link dropdown-toggle icon-cart"
														id="minicart">
														<i class="pe-7s-cart"></i> <span <c:if test="${cartlist.size()==0}">style="display:none"</c:if> class="count-style">${cartlist.size()}</span>
													</button>
													<div class="dropdown-menu">
														<div class="shopping-cart-content">
															<ul class="list-unstyled" id="headcart">

															<c:forEach items="${cartlist}" var="c" varStatus="st">
																<li class="single-cart-item media">
																	<div class="shopping-cart-img mr-4">
																		<a href="#"> <img class="img-fluid"
																			alt="Cart Item" src="${c.product.productImgs[0].imgurl}">
																			<span class="product-quantity">${c.number}x</span>
																		</a>
																	</div>
																	<div class="shopping-cart-title media-body">
																		<h4>
																			<a href="#">${c.product.pname}</a>
																		</h4>
																		<p class="cart-price">$${c.product.pnewprice}</p>
																		<div class="product-attr">
																			<span>Size: S</span> <span>Color: Black</span>
																		</div>
																	</div>
																	<div class="shopping-cart-delete">
																		<a href="javascript:void(0)" id="remove" oiid="${c.oiid}"><i class="ion ion-md-close"></i></a>
																	</div>
																</li>
  </c:forEach>

															</ul>
															<div class="shopping-cart-total" id="head-cart-sum">
																<h4>
																	商品价格 :$ <span id="psum">${cartsum}</span>
																</h4>
																<h4>
																	订单价格 :$ <span id="osum">${cartsum}</span>
																</h4>
															</div>
															<div class="shopping-cart-btn">
																<a class="default-btn" href="User_CartList_list">查看购物车</a>

															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="header-contact media">
												<div class="header-contact-icon mr-4">
													<i class="pe-7s-headphones"></i>
												</div>
												<div class="header-contact-content media-body">
													<p>
														<span>联系我们:</span> <br> <a href="#">+88
															123.456.789</a>
													</p>
												</div>
											</div>
										</div>
										<!-- end of header-cart-area -->
									</div>
								</div>
							</div>
							<!-- end of bottom-header -->
						</div>
					</div>
					<!-- end of row -->
				</div>
				<!-- end of container -->
			</div>
			<!-- end of header-area -->

			<!-- Start of Main and Mobile Navigation -->
			<div class="main-nav-area transparent-nav floating-nav">
				<div class="container">
					<nav id="main_nav" class="stellarnav">
						<ul class="text-left">
							<li><a href="user/home.jsp"><span>主页</span></a></li>
							<li class="mega" data-columns="3"><a href="#"><span>页面</span></a>
								<ul class="mega-container">
									<li><a href="my-account.html" class="mega-menu-title"><h3>账户</h3></a>
										<ul id="pagenav">
											<li><a href="user/login.jsp">登录</a></li>
											<li><a href="user/register.jsp">注册</a></li>
										</ul></li>

									<li><a href="#" class="mega-menu-title"><h3>功能</h3></a>
										<ul>
											<li><a href="User_Productslist_list">所有商品</a>
											<li>
											
										</ul></li>

								</ul></li>
							<!--<li><a href="shop-grid.html"><span>购物页面 </span></a>
                                <ul>
                                    <li><a href="shop-grid.html">Shop Grid</a></li>
                                    <li><a href="shop-list.html">Shop List</a></li>
                                    <li><a href="shop-right-sidebar.html">Shop Right Sidebar</a></li>
                                    <li><a href="shop-fullwidth.html">Shop Full Width</a></li>
                                    <li><a href="single-product.html">Single Product Page</a></li>
                                </ul>
                            </li>-->
							<!--<li><a href="blog.html"><span>Blog Pages</span></a>
                                <ul>
                                    <li><a href="blog.html">Blog FullWidth</a></li>
                                    <li><a href="blog-left-sidebar.html">Blog Left Sidebar</a></li>
                                    <li><a href="blog-right-sidebar.html">Blog Right Sidebar</a></li>
                                    <li><a href="blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>-->
							<li><a href="user/about.jsp"><span>关于我们</span></a></li>
							<li><a href="user/contact.jsp"><span>联系我们</span></a></li>
						</ul>
					</nav>
				</div>
				<!-- end of container -->
			</div>
			<!-- End of Main and Mobile Navigation -->
		</header>
		<script>
			var unlogin = " <li ><a href='"+"user/login.jsp"+"'>登录</a></li>"
					+ " <li ><a href='"+"user/register.jsp"+"'>注册</a></li>";
			var login = " <li ><a href='"+"user/my-account.jsp"+"'>我的账户</a></li>"
					+ " <li ><a href='"+"User_CartList_list"+"'>购物车</a></li>"
					+ " <li ><a href='"+"user/register.jsp"+"'>注册</a></li>";

			if ("${user.name}" == "") {
				user.innerHTML = "请登录<i class='"+"fa fa-angle-down"+"'></i>";
				minicart.style.display = "none";
				pagenav.innerHTML=unlogin;
			} else {
				user.innerHTML = "${user.name}<i class='"+"fa fa-angle-down"+"'></i>";
				account.innerHTML = login;
				pagenav.innerHTML=login;
			}
			if (window.location.pathname == "/mycart/home.jsp")
				header.className = "header bgc-white";
			
			$("#headcart").on("click","#remove",function(){
				$(this).parents("li").fadeOut("slow");
				removeCartItem($(this).attr("oiid")); 
				
				
				/* $(this).parents("li").find("#psum").text("$"+sum);
				$(this).parents("li").find("#osum").text("$"+sum); */
			})

			/* 
			for(var i=0;i< $(".top-search > p > a").length;i++)
			$(".top-search > p > a").eq(i).attr("href","User_Productslist_list?page.type="+encodeURI(encodeURI($(".top-search > p > a").eq(i).text()))); */
		</script>