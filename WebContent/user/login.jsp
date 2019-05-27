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
                            <span class="breadcrumb-item active">登录</span>
                        </nav>
                    </div>
                </div> <!-- end of row -->
            </div> <!-- end of container -->
        </div>
        <!-- End of Breadcrumbs -->

        <!-- Start of Main Content Wrapper -->
        <div id="content" class="main-content-wrapper">
            
            <!-- Start of Login Wrapper -->
            <div class="login-wrapper">
                <div class="container">
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            <main id="primary" class="site-main">
                                <div class="user-login">
                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-12">
                                            <div class="section-title left-aligned with-border">
                                                <h2>登录你的账号</h2>
                                            </div>
                                        </div>
                                    </div> <!-- end of row -->

                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-6 offset-lg-2 offset-xl-3">
                                            <div class="login-form mt-half">
                                                <form id="form" action="#" method="post"  accept-charset="utf-8">
                                                 <input type="hidden" name="method" value="Login">
                                                    <div class="form-group row align-items-center mb-4">
                                                        <label for="email" class="col-12 col-sm-12 col-md-4 col-form-label">邮箱</label>
                                                        <div class="col-12 col-sm-12 col-md-8">
                                                            <input type="text" name="email" class="form-control" id="email" placeholder="Email" required>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row align-items-center mb-4">
                                                        <label for="c-password" class="col-12 col-sm-12 col-md-4 col-form-label">密码</label>
                                                        <div class="col-12 col-sm-12 col-md-8">
                                                            <input type="password"name="password" class="form-control" id="c-password" placeholder="Password" required>
                                                            <!-- <button class="pass-show-btn" type="button">Show</button> -->
                                                        </div>
                                                    </div>
                                                   
                                                    <div class="login-box mt-5 text-center">
                                                        <p><a href="#">忘记密码?</a></p>
                                                        <button type="button" class="default-btn tiny-btn mt-4" id="login">登录</button>
                                                    </div>
                                                    <div class="text-center mt-half pt-half top-bordered">
                                                        <p>没有账号?<a href="register.jsp">点这里创建一个</a>.</p>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- end of user-login -->
                            </main> <!-- end of #primary -->
                        </div>
                    </div> <!-- end of row -->
                </div> <!-- end of container -->
            </div>
            <!-- End of Login Wrapper -->

            <!-- Start of Newsletter Section -->
            
            <!-- End of Newsletter Section -->
        </div>
        <!-- End of Main Content Wrapper -->
         <script type="text/javascript">
    $(function(){
    	$("#login").click(
    			function(){
    				$.post(
    						"User_User_Login", 
    						$('#form').serialize(),
    						function(result) {
    							alert(result);
    							if(result=="密码正确")
    								{
    								location.href="user/home.jsp";
    								}
    								else
    									{
    							alert(result);
    							console.log(result);
    							}
    						}
    					);
    				
    			});
    })
    </script>

            <!-- Start of Footer -->
      <%@include file="../include/footer.jsp"%>
        <!-- End of Footer -->

      