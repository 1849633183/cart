<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


  <!-- Start of Header -->
 <%@include file="../include/head.jsp"%>
        <!-- End of Header -->

        <div class="fixed-header-space"></div> <!-- empty placeholder div for Fixed Menu bar height-->

        <!-- Start of Breadcrumbs -->
        <div class="breadcrumb-section">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-sm-12 col-md-12">
                        <nav class="breadcrumb">
                            <a class="breadcrumb-item" href="home.jsp">主页</a>
                            <span class="breadcrumb-item active">购物车</span>
                        </nav>
                    </div>
                </div> <!-- end of row -->
            </div> <!-- end of container -->
        </div>
        <!-- End of Breadcrumbs -->

        <!-- Start of Main Content Wrapper -->
        <div id="content" class="main-content-wrapper">
            
            <!-- Start of Shopping Cart Wrapper -->
            <div class="shopping-cart-wrapper">
                <div class="container">
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            <main id="primary" class="site-main">
                                <div class="shopping-cart">
                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="section-title left-aligned with-border">
                                                <h2>购物车</h2>
                                            </div>

                                            <form action="#">
                                                <div class="table-responsive">
                                                    <table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <td>图片</td>
                                                                <td>商品名</td>
                                                                <td>详细信息</td>
                                                                <td>数量</td>
                                                                <td>单价</td>
                                                                <td>总计</td>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="maincart">
                                                        
                                                         <c:forEach items="${cartlist}" var="c" varStatus="st">
                                                            <tr oiid="${c.oiid}">
                                                                <td>
                                                                    <a href="single-product.html"><img src="${c.product.productImgs[0].imgurl}" alt="Cart Product Image" title="Cas Meque Metus" class="img-thumbnail"></a>
                                                                </td>
                                                                <td>
                                                                    <a href="single-product.html">${c.product.pname}</a>
                                                                </td>
                                                                <td> <span>Delivery Date: 2018-04-22</span>
                                                                    <span>Color: Blue</span>
                                                                    <span>Reward Points: 300</span></td>
                                                                <td>
                                                                    <div class="input-group btn-block">
                                                                        <input type="text" name="quantity" value="${c.number}" size="1" class="form-control">
                                                                        <span class="input-group-btn">
                                                                            <button type="button" id="update" data-toggle="tooltip" data-direction="top" class="btn btn-primary" data-original-title="Update"><i class="fa fa-refresh"></i></button>
                                                                            <button type="button" id="remove" data-toggle="tooltip" data-direction="top" class="btn btn-danger pull-right" data-original-title="Remove"><i class="fa fa-times-circle"></i></button>
                                                                        </span>
                                                                    </div>
                                                                </td>
                                                                <td>$${c.product.pnewprice}</td>
                                                                <td>$${c.product.pnewprice}</td>
                                                            </tr>
                                                                   </c:forEach>

                                                        
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </form>

                                           <!--  <div class="cart-accordion-wrapper mt-full">
                                                <h2>What would you like to do next?</h2>
                                                <p>Choose if you have a discount code or reward points you want to use or would like to estimate your delivery cost.</p>
                                                <div id="cart_accordion" class="mt-4" role="tablist">
                                                    <div class="card">
                                                        <div class="card-header" role="tab" id="headingCoupon">
                                                            <h4 class="mb-0">
                                                                <a data-toggle="collapse" href="#collapseCoupon" aria-expanded="false" aria-controls="collapseCoupon">Use Coupon Code<i class="ion ion-ios-arrow-down"></i></a>
                                                            </h4>
                                                        </div>
                                                        <div id="collapseCoupon" class="collapse" role="tabpanel" aria-labelledby="headingCoupon" data-parent="#cart_accordion">
                                                            <div class="card-body">
                                                                <div class="input-group">
                                                                    <label class="col-12 col-sm-12 col-md-3" for="input-coupon">Enter your coupon here</label>
                                                                    <div class="col-12 col-sm-12 col-md-9">
                                                                        <div class="input-group">
                                                                        <input type="text" name="coupon" value="" placeholder="Enter your coupon here" id="input-coupon" class="form-control">
                                                                        <input type="button" value="Apply Coupon" id="button-coupon" class="btn btn-secondary">
                                                                    </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card">
                                                        <div class="card-header" role="tab" id="headingTax">
                                                            <h4 class="mb-0">
                                                                <a class="collapsed" data-toggle="collapse" href="#collapseTax" aria-expanded="false" aria-controls="collapseTax">Estimate Shipping &amp; Taxes<i class="ion ion-ios-arrow-down"></i></a>
                                                              </h4>
                                                        </div>
                                                        <div id="collapseTax" class="collapse" role="tabpanel" aria-labelledby="headingTax" data-parent="#cart_accordion">
                                                            <div class="card-body">
                                                                <p>Enter your destination to get a shipping estimate.</p>
                                                                <div class="input-group form-group">
                                                                    <label class="col-12 col-sm-12 col-md-3" for="input-country"><span class="text-danger">*</span> Country</label>
                                                                    <div class="col-12 col-sm-12 col-md-9">
                                                                        <select name="country_id" id="input-country" class="form-control nice-select">
                                                                            <option value=""> --- Please Select --- </option>
                                                                            <option value="">Argentina</option>
                                                                            <option value="">Bangladesh</option>
                                                                            <option value="">Belgium</option>
                                                                            <option value="">Brazil</option>
                                                                            <option value="">Germany</option>
                                                                            <option value="">India</option>
                                                                            <option value="">United Kingdom</option>
                                                                            <option value="">United States</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="input-group form-group">
                                                                    <label class="col-12 col-sm-12 col-md-3" for="input-zone"><span class="text-danger">*</span> Region / State</label>
                                                                    <div class="col-12 col-sm-12 col-md-9">
                                                                        <select name="zone_id" id="input-zone" class="form-control nice-select">
                                                                            <option value=""> --- Please Select --- </option>
                                                                            <option value="">Alabama</option>
                                                                            <option value="">Arizona</option>
                                                                            <option value="">California</option>
                                                                            <option value="">Florida</option>
                                                                            <option value="">Newyork</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="input-group form-group mb-5">
                                                                    <label class="col-12 col-sm-12 col-md-3" for="input-postcode"><span class="text-danger">*</span> Post Code</label>
                                                                    <div class="col-12 col-sm-12 col-md-9">
                                                                        <input type="text" name="postcode" value="" placeholder="Post Code" id="input-postcode" class="form-control mb-0">
                                                                    </div>
                                                                </div>
                                                                <button type="button" id="button-quote" class="btn btn-secondary">Get Quotes</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card">
                                                        <div class="card-header" role="tab" id="headingGift">
                                                            <h4 class="mb-0">
                                                                <a class="collapsed" data-toggle="collapse" href="#collapseGift" aria-expanded="false" aria-controls="collapseGift">Use Gift Certificate<i class="ion ion-ios-arrow-down"></i></a>
                                                              </h4>
                                                        </div>
                                                        <div id="collapseGift" class="collapse" role="tabpanel" aria-labelledby="headingGift" data-parent="#cart_accordion">
                                                            <div class="card-body">
                                                                <div class="input-group">
                                                                    <label class="col-12 col-sm-12 col-md-3" for="input-voucher">Enter your gift certificate code here</label>
                                                                    <div class="col-12 col-sm-12 col-md-9">
                                                                        <div class="input-group">
                                                                            <input type="text" name="voucher" value="" placeholder="Enter your gift certificate code here" id="input-voucher" class="form-control">
                                                                            <input type="button" value="Apply Gift Certificate" id="button-boucher" class="btn btn-secondary">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> -->

                                            <div class="cart-amount-wrapper">
                                                <div class="row">
                                                    <div class="col-12 col-sm-12 col-md-4 offset-md-8">
                                                        <table class="table table-bordered">
                                                            <tbody>
                                                                <tr>
                                                                    <td><strong>商品总价:</strong></td>
                                                                    <td>$${cartsum}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td><strong>订单总价:</strong></td>
                                                                    <td><span class="primary-color">$${cartsum}</span></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="cart-button-wrapper d-flex justify-content-between mt-4">
                                                <a href="user/home.jsp" class="btn btn-secondary">继续购物</a>
                                                <a href="checkout.html" class="btn btn-secondary align-self-end">结账</a>
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- end of shopping-cart -->
                            </main> <!-- end of #primary -->
                        </div>
                    </div> <!-- end of row -->
                </div> <!-- end of container -->
            </div>
            <!-- End of Shopping Cart Wrapper -->

         
        </div>
        <!-- End of Main Content Wrapper -->
        <script type="text/javascript">
$("#maincart").on("click","#remove",function(){
	removeCartItem($(this).parents("tr").attr("oiid"));
	cartlistsession2html();
	$(this).parents("tr").fadeOut("slow");
});
$("#maincart").on("click","#update",function(){
	
	updateCartItem($(this).parents("tr").attr("oiid"),$(this).parents("td").find("input").val()); 
});
</script>

       <!-- Start of Footer -->
      <%@include file="../include/footer.jsp"%>
        <!-- End of Footer -->
