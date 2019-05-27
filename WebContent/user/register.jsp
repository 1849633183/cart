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
                            <span class="breadcrumb-item active">注册</span>
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
                                                <h2>创建你的账号</h2>
                                            </div>
                                        </div>
                                    </div> <!-- end of row -->

                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-8 offset-xl-2">
                                            <div class="registration-form login-form mt-half">
                                                <form id="form" action="#" method="post"  accept-charset="utf-8">
                                                <input type="hidden" name="method" value="Register">
                                                    <div class="login-info mb-half">
                                                        <p>已有账号? <a href="login.jsp">点此登录!</a></p>
                                                    </div>
                                                   
                                                    <div class="form-group row">
                                                        <label for="f-name"  class="col-12 col-sm-12 col-md-4 col-form-label">昵称</label>
                                                        <div class="col-12 col-sm-12 col-md-8 col-lg-8">
                                                            <input type="text" name="name" class="form-control" id="f-name" required="">
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group row">
                                                        <label for="email" class="col-12 col-sm-12 col-md-4 col-form-label">邮箱地址</label>
                                                        <div class="col-12 col-sm-12 col-md-8 col-lg-8">
                                                            <input type="text"  name="email" class="form-control" id="email" required="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="inputpassword" class="col-12 col-sm-12 col-md-4 col-form-label">密码</label>
                                                        <div class="col-12 col-sm-12 col-md-8 col-lg-8">
                                                            <input type="password" id="apassword" name="password" class="form-control" id="inputpassword" required="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="inputpassword" class="col-12 col-sm-12 col-md-4 col-form-label">重复密码</label>
                                                        <div class="col-12 col-sm-12 col-md-8 col-lg-8">
                                                            <input type="password" id="bpassword" class="form-control" id="inputpassword" required="">
                                                        </div>
                                                    </div>
                                                    
                                                    
                                                    
                                                    <div class="register-box d-flex justify-content-end mt-half">
                                                        <button type="button" id="register" class="default-btn tiny-btn">注册</button>
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
    	$("#register").click(
    			function(){
    				if($("#apassword").val()==''||$("#bpassword").val()==''||$("#f-name").val()==''||$("#email").val()=='')
    					{
    					alert("请填写好信息");
    					}
    				else if($("#apassword").val()!=$("#bpassword").val())
    					{alert("两次密码不一致");}
    				else
    					{
    				$.post(
    						"User_User_Register", 
    						$('#form').serialize(),
    						function(result) {
    							if("注册成功"==result)
    								{
    							alert(result);
    								location.href="user/login.jsp";
    								}
    								else
    									{
    							alert(result+":用户已存在");
    							console.log(result);
    							}
    						}
    					);}
    				
    			});
    })
    </script>

    <!-- Start of Footer -->
      <%@include file="../include/footer.jsp"%>
        <!-- End of Footer -->
