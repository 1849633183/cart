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
                            <span class="breadcrumb-item active">我的账户</span>
                        </nav>
                    </div>
                </div> <!-- end of row -->
            </div> <!-- end of container -->
        </div>
        <!-- End of Breadcrumbs -->

        <!-- Start of Main Content Wrapper -->
        <div id="content" class="main-content-wrapper">
            
            <!-- Start of My Account Wrapper -->
            <div class="my-account-wrapper">
                <div class="container">
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            <main id="primary" class="site-main">
                                <div class="user-dashboard">
                                    <div class="user-info">
                                        <div class="row align-items-center no-gutters">
                                           <div class="col-12 col-sm-12 col-md-6 col-lg-3 col-xl-3">
                                               <div class="single-info">
                                                   <p class="user-name">你好 <span> ${SPRING_SECURITY_CONTEXT.authentication.principal.username} </span> <br>(不是  ${SPRING_SECURITY_CONTEXT.authentication.principal.username} ? <a id="logout2" class="log-out" href="user/userlogout.do">注销</a>)</p>
                                               </div>
                                           </div>
                                           <div class="col-12 col-sm-12 col-md-6 col-lg-4 col-xl-3">
                                               <div class="single-info">
                                                   <p>需要帮助吗? 在这里获取客户服务 <a href="#">admin@example.com</a></p>
                                               </div>
                                           </div>
                                           <div class="col-12 col-sm-12 col-md-6 col-lg-3 col-xl-3">
                                               <div class="single-info">
                                                   <p>邮箱地址 <a href="#">support@example.com</a></p>
                                               </div>
                                           </div>
                                           <div class="col-12 col-sm-12 col-md-6 col-lg-2 col-xl-3">
                                               <div class="single-info text-lg-center">
                                                   <a class="btn btn-secondary" href="User_CartLiat_list">查看购物车</a>
                                               </div>
                                           </div>
                                       </div> <!-- end of row -->
                                    </div> <!-- end of user-info -->

                                    <div class="main-dashboard">
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-2">
                                                <ul class="nav flex-column dashboard-list" role="tablist">
                                                  <!--   <li><a class="nav-link active" data-toggle="tab" href="#dashboard">Dashboard</a></li> -->
                                                    <li> <a class="nav-link" data-toggle="tab" href="#orders">订单</a></li>
                                                    <li><a class="nav-link" data-toggle="tab" href="#address">收货地址</a></li>
                                                    <li><a class="nav-link" data-toggle="tab" href="#account-details">账户详细信息</a></li>
                                                    <li id="logout1"><a class="nav-link">注销</a></li>
                                                </ul> <!-- end of dashboard-list -->
                                            </div>

                                            <div class="col-12 col-sm-12 col-md-12 col-lg-10">
                                                <!-- Tab panes -->
                                                <div class="tab-content dashboard-content">
                                                    <!--<div id="dashboard" class="tab-pane fade show active">
                                                        <h3>Dashboard </h3>
                                                        <p>From your account dashboard. you can easily check &amp; view your <a href="#">recent orders</a>, manage your <a href="#">shipping and billing addresses</a> and <a href="#">edit your password and account details.</a></p>
                                                    </div> --> <!-- end of tab-pane -->

                                                    <div id="orders" class="tab-pane fade show active">
                                                        <h3>Orders</h3>
                                                        <div class="table-responsive">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th>订单</th>
                                                                        <th>日期</th>
                                                                        <th>状态</th>
                                                                        <th>总计</th>
                                                                        <th>操作</th>                
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>1</td>
                                                                        <td>September 10, 2018</td>
                                                                        <td>Processing</td>
                                                                        <td>$25.00 for 1 item </td>
                                                                        <td><a class="btn btn-secondary" href="cart.html">查看</a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>2</td>
                                                                        <td>October 4, 2018</td>
                                                                        <td>Processing</td>
                                                                        <td>$17.00 for 1 item </td>
                                                                        <td><a class="btn btn-secondary" href="cart.html">view</a></td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div> <!-- end of tab-pane -->

                                                    <div id="downloads" class="tab-pane fade">
                                                        <h3>Downloads</h3>
                                                        <div class="table-responsive">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Product</th>
                                                                        <th>Downloads</th>
                                                                        <th>Expires</th>
                                                                        <th>Download</th>               
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>Volga - Ecommerce Bootstrap Template</td>
                                                                        <td>August 10, 2018</td>
                                                                        <td>Never</td>
                                                                        <td><a class="btn btn-secondary" href="#">Download File</a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Gatcomart - Ecommerce HTML Template</td>
                                                                        <td>September 11, 2018</td>
                                                                        <td>Never</td>
                                                                        <td><a class="btn btn-secondary" href="#">Download File</a></td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div> <!-- end of tab-pane -->

                                                    <div id="address" class="tab-pane">
                                                       <p>The following addresses will be used on the checkout page by default.</p>
                                                        <h4 class="billing-address">Billing address</h4>
                                                        <a class="btn btn-secondary my-4" href="#">edit</a>
                                                        <p>Ororus Themes</p>
                                                        <p>Bangladesh</p>   
                                                    </div> <!-- end of tab-pane -->

                                                    <div id="account-details" class="tab-pane fade">
                                                        <h3>Account details </h3>
                                                        <div class="login-form">
                                                            <form action="#">
                                                                <div class="form-group row align-items-center">
                                                                    <label class="col-12 col-sm-12 col-md-4 col-lg-3 col-form-label">Title</label>
                                                                    <div class="col-12 col-sm-12 col-md-8 col-lg-6">
                                                                        <div class="form-row">
                                                                            <div class="col-6 col-sm-3">
                                                                                <div class="custom-radio">
                                                                                    <input class="form-check-input" type="radio" name="gender" id="male">
                                                                                    <span class="checkmark"></span>
                                                                                    <label class="form-check-label" for="male">Mr.</label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-6 col-sm-3">
                                                                                <div class="custom-radio">
                                                                                    <input class="form-check-input" type="radio" name="gender" id="female">
                                                                                    <span class="checkmark"></span>
                                                                                    <label class="form-check-label" for="female">Mrs.</label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <label for="f-name" class="col-12 col-sm-12 col-md-4 col-lg-3 col-form-label">First Name</label>
                                                                    <div class="col-12 col-sm-12 col-md-8 col-lg-6">
                                                                        <input type="text" class="form-control" id="f-name" required>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <label for="l-name" class="col-12 col-sm-12 col-md-4 col-lg-3 col-form-label">Last Name</label>
                                                                    <div class="col-12 col-sm-12 col-md-8 col-lg-6">
                                                                        <input type="text" class="form-control" id="l-name" required>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <label for="email" class="col-12 col-sm-12 col-md-4 col-lg-3 col-form-label">Email Address</label>
                                                                    <div class="col-12 col-sm-12 col-md-8 col-lg-6">
                                                                        <input type="text" class="form-control" id="email" required>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <label for="inputpassword" class="col-12 col-sm-12 col-md-4 col-lg-3 col-form-label">Current Password</label>
                                                                    <div class="col-12 col-sm-12 col-md-8 col-lg-6">
                                                                        <input type="password" class="form-control" id="inputpassword" required>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <label for="newpassword" class="col-12 col-sm-12 col-md-4 col-lg-3 col-form-label">New Password</label>
                                                                    <div class="col-12 col-sm-12 col-md-8 col-lg-6">
                                                                        <input type="password" class="form-control" id="newpassword" required>
                                                                        <button class="pass-show-btn" type="button">Show</button>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <label for="c-password" class="col-12 col-sm-12 col-md-4 col-lg-3 col-form-label">Confirm Password</label>
                                                                    <div class="col-12 col-sm-12 col-md-8 col-lg-6">
                                                                        <input type="password" class="form-control" id="c-password" required>
                                                                        <button class="pass-show-btn" type="button">Show</button>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <label for="birth" class="col-12 col-sm-12 col-md-4 col-lg-3 col-form-label">Birthdate (Optional)</label>
                                                                    <div class="col-12 col-sm-12 col-md-8 col-lg-6">
                                                                        <input type="text" class="form-control" id="birth" placeholder="MM / DD / YYYY" required>
                                                                    </div>
                                                                </div>
                                                                <div class="form-check row p-0 mt-5">
                                                                    <div class="col-12 col-sm-12 col-md-8 offset-md-4 col-lg-6 offset-lg-3">
                                                                        <div class="custom-checkbox">
                                                                            <input class="form-check-input" type="checkbox" id="offer">
                                                                            <span class="checkmark"></span>
                                                                            <label class="form-check-label" for="offer">Receive offers from our partners</label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-check row p-0 mt-4">
                                                                    <div class="col-12 col-sm-12 col-md-8 offset-md-4 col-lg-6 offset-lg-3">
                                                                        <div class="custom-checkbox">
                                                                            <input class="form-check-input" type="checkbox" id="subscribe" required>
                                                                            <span class="checkmark"></span>
                                                                            <label class="form-check-label" for="subscribe">Sign up for our newsletter<br>Subscribe to our newsletters now and stay up-to-date with new collections, the latest lookbooks and exclusive offers..</label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="register-box d-flex justify-content-end mt-half">
                                                                    <button type="submit" class="btn btn-secondary">Save</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div> <!-- end of tab-pane -->                                        
                                                </div>
                                            </div>
                                        </div> <!-- end of row -->
                                    </div> <!-- end of main-dashboard -->
                                </div> <!-- end of user-dashboard -->
                            </main> <!-- end of #primary -->
                        </div>
                    </div> <!-- end of row -->
                </div> <!-- end of container -->
            </div>
            <!-- End of My Account Wrapper -->

           
        </div>
        <!-- End of Main Content Wrapper -->
            <script type="text/javascript">
   
    		function logout(){
    			$.post(
						"User_User_Logout", 							
						{"msg":"注销"},
						function(result) {
							alert(result);
						if(result=="已注销")
							location.href="login.jsp";
						else
							alert("注销失败");
						}
					);
    		}
    		/* logout1.onclick=logout;
    		logout2.onclick=logout; */
 
    </script>

           <!-- Start of Footer -->
      <%@include file="../include/footer.jsp"%>
        <!-- End of Footer -->