<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<footer id="colophon">

	<!-- Footer Description -->
	<div class="footer-description">
		<div class="container">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12">
					<h2>在Ororus网上购物</h2>
					<p>我们的产品范围广泛且价格合理，包括最新的电子产品和小配件，包括智能手机、平板电脑、智能手表、行动摄像头、电视盒、电视、无人机、3D打印机、汽车DVR，以及最新的酷炫玩具，如滑板车、游戏配件、玩具屋、假装游戏和高质量的生活方式产品，包括真空吸尘器。净水器、空气净化器、厨房工具、吊灯、手电筒、油漆等。</p>
				</div>
			</div>
			<!-- endo of row -->
		</div>
		<!-- end of container -->
	</div>
	<!-- end of footer-description -->

	<!-- Footer Copyright -->
	<div class="footer-copyright">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-12 col-sm-12 col-md-12 col-lg-6">
					<p class="copyright-text">
						Copyright (c) 2018 <a href="http://www.bootstrapmbhtmlsucai.com/"
							rel="nofollow">Ororus Themes</a>. All Right Reserved.
					</p>
				</div>
				<div class="col-12 col-sm-12 col-md-12 col-lg-6">
					<div class="footer-payment">
						<ul>
							<li><a href="#"><img
									src="assets/images/icons/payment-1.jpg" alt="Payment Icons"></a></li>
							<li><a href="#"><img
									src="assets/images/icons/payment-2.jpg" alt="Payment Icons"></a></li>
							<li><a href="#"><img
									src="assets/images/icons/payment-3.jpg" alt="Payment Icons"></a></li>
							<li><a href="#"><img
									src="assets/images/icons/payment-4.jpg" alt="Payment Icons"></a></li>
							<li><a href="#"><img
									src="assets/images/icons/payment-5.jpg" alt="Payment Icons"></a></li>
							<li><a href="#"><img
									src="assets/images/icons/payment-6.jpg" alt="Payment Icons"></a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- end of row -->
		</div>
		<!-- end of container -->
	</div>
	<!-- end of footer-copyright -->
</footer>

<!-- Start of Scroll to Top -->
<div id="to_top">
	<i class="ion ion-ios-arrow-forward"></i> <i
		class="ion ion-ios-arrow-forward"></i>
</div>
<!-- End of Scroll to Top -->
</div>
<!-- End of Whole Site Wrapper -->

<!-- Initializing Photoswipe -->
<div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="pswp__bg"></div>
	<div class="pswp__scroll-wrap">
		<div class="pswp__container">
			<div class="pswp__item"></div>
			<div class="pswp__item"></div>
			<div class="pswp__item"></div>
		</div>
		<div class="pswp__ui pswp__ui--hidden">
			<div class="pswp__top-bar">
				<div class="pswp__counter"></div>
				<button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
				<button class="pswp__button pswp__button--share" title="Share"></button>
				<button class="pswp__button pswp__button--fs"
					title="Toggle fullscreen"></button>
				<button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
				<div class="pswp__preloader">
					<div class="pswp__preloader__icn">
						<div class="pswp__preloader__cut">
							<div class="pswp__preloader__donut"></div>
						</div>
					</div>
				</div>
			</div>
			<div
				class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
				<div class="pswp__share-tooltip"></div>
			</div>
			<button class="pswp__button pswp__button--arrow--left"
				title="Previous (arrow left)"></button>
			<button class="pswp__button pswp__button--arrow--right"
				title="Next (arrow right)"></button>
			<div class="pswp__caption">
				<div class="pswp__caption__center"></div>
			</div>
		</div>
	</div>
</div>
<!-- End of Photoswipe -->


<!-- JS
    ============================================ -->

<!-- Popper JS -->
<script src="assets/js/popper.min.js"></script>

<!-- Bootstrap JS -->
<script src="assets/js/bootstrap.min.js"></script>

<!-- Plugins JS -->
<script src="assets/js/plugins.js"></script>

<!-- Main JS -->
<script src="assets/js/main.js"></script>
<script src="assets/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" charset="utf-8">
  $(function() {
      $("img").lazyload({effect: "fadeIn", threshold :80});
  });
</script>
<script>
	        var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-XXXXX-X' ]);
	_gaq.push([ '_trackPageview' ]);
	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
    
</script>
<script type="text/javascript">
	function cartlistsession2html() {
		$.ajax({
			url : "User_CartList_ajaxlist",
			type : "post",
			data : {},
			dataType : "json",
			success : function(data) {
				console.log(data);
				console.log("ajax调用成功");
				loadheadcart(data);

			}
		});
	}
	/* function getcartsum() {
		var sum=0;
		$.ajax({
			url : "User_CartList_ajaxlist",
			type : "post",
			data : {},
			dataType : "json",
			success : function(data) {
				
				for ( var i in data) {
					sum+=data[i].product.pnewprice*data[i].number;
				}
				
			}
		});
		console.log(sum+"--------------");
		return sum;
	} */

	function cartlist2session() {
		$.ajax({
			url : "User_CartList_list2session",
			type : "post",
			data : {},
			dataType : "text",
			success : function(data) {
				console.log(data);
				console.log("ajax调用成功");
				cartlistsession2html();
			}
		});
	}

	function cartadd(number, product) {
		$.ajax({
			url : "User_CartList_add",
			type : "post",
			data : {
				"number" : number,
				"product" : JSON.stringify(product)
			},
			dataType : "text",
			success : function(result) {
				console.log(result);
				if(result=="无用户")
					window.location.href="user/login.jsp";
				cartlistsession2html();
				

			}
		});
	}
	

	function loadheadcart(cart) {
		var html="";
		var size=0;
		var sum=0;
		for ( var i in cart) {
			html += '<li class="single-cart-item media">'
					+ '<div class="shopping-cart-img mr-4">'
					+ '<a href="#">'
					+ ' <img class="img-fluid" alt="Cart Item" src="'+cart[i].product.productImgs[0].imgurl+'">'
					+ '<span class="product-quantity">'+cart[i].number+'x</span>'
					+ '</a>' + '</div>'
					+ '<div class="shopping-cart-title media-body">'
					+ '<h4><a href="#">'+cart[i].product.pname+'</a></h4>'
					+ '<p class="cart-price">$'+cart[i].product.pnewprice+'</p>'
					+ '<div class="product-attr"><span>Size: S</span>'
					+ ' <span>Color: Black</span>' + '</div>' + '</div>'
					+ '<div class="shopping-cart-delete">'
					+ '<a href="javascript:void(0)" id="remove" oiid='+cart[i].oiid+'><i class="ion ion-md-close"></i></a>'
					+ '</div>' + '</li>';
					size++;
					sum+=cart[i].product.pnewprice*cart[i].number;
		}
	 if(size==0)
		$(".count-style").fadeOut(1);
	else 
		{$(".count-style").fadeIn(1);
		$(".count-style").text(size);}
		$("#headcart").html(html);		
		$("#head-cart-sum span").each(function(){$(this).text(sum);});
	}
	function removeCartItem(oiid){
		$.ajax({
			url : "User_CartList_delete",
			type : "post",
			data : {
				
				"oiid" : oiid
			},
			dataType : "text",
			success : function(result) {
				console.log(result);
				setTimeout(function(){cartlistsession2html()},600);
				

			}
		});}
	function updateCartItem(oiid,number){
		if(number>0)
		$.ajax({
			url : "User_CartList_update",
			type : "post",
			data : {
				
				"oiid" : oiid,
				"number":number
			},
			dataType : "text",
			success : function(result) {
				console.log(result);
				cartlistsession2html();
				alert(result);
				

			}
		});
		else
			alert("非法输入！");
		}
</script>
<!-- <script>  
//判断浏览器  
var Browser=new Object();  
Browser.userAgent=window.navigator.userAgent.toLowerCase();  
Browser.ie=/msie/.test(Browser.userAgent);  
Browser.Moz=/gecko/.test(Browser.userAgent);  

//判断是否加载完成  
function Imagess(url,imgid,callback){      
    var val=url;  
    var img=new Image();  
    if(Browser.ie){  
        img.onreadystatechange =function(){    
            if(img.readyState=="complete"||img.readyState=="loaded"){  
                callback(img,imgid);  
            }  
        }          
    }else if(Browser.Moz){  
        img.onload=function(){  
            if(img.complete==true){  
                callback(img,imgid);  
            }  
        }          
    }      
    //如果因为网络或图片的原因发生异常，则显示该图片  
    img.onerror=function(){img.src="assets/images/load.jpg"}  
    img.src=val;  
}  

//显示图片  
function checkimg(obj,imgid){  
document.getElementById(imgid).style.cssText="";  
document.getElementById(imgid).src=obj.src;  
}  
//初始化需要显示的图片，并且指定显示的位置  
window.onload=function(){  
var imglist=document.getElementById("imagelist").getElementsByTagName('img');  
for(i=0;i<imglist.length;i++)  
{  
    imglist[i].id="img0"+i;  
    //imglist[i].src="http://www.86y.org/images/loading.gif";  
    imglist[i].style.cssText="background:url(assets/images/load.jpg) no-repeat center center;"  
    Imagess(imglist[i].getAttribute("data"),imglist[i].id,checkimg);  
}  
}  
</script>   -->


</body>
</html>