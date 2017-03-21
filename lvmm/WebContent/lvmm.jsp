<%@ page language="java"  pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/org.malajava.ajax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-3.1.1.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("a").click(function() {
				var data = {
						"uid" : "112233" ,
						"password" : "123456",
						"timestamp" : "20161211130143" , 
						"visitTime" : "20161211130140" , 
						"supplierGoodsId" : "0001" , 
						"settlePrice" : "126" , 
						"num": "2" ,
						"serialNo" : "20170101" , 
						"sign" : "B75F13D86D935F00B9728CA04888BE40" ,
						"idNum" : "622223198906168899" , 
						"idType" : "身份证" ,
						"mobile" : "13109311234" , 
						"name" : "张三丰"
				}
				var options = {
						"type" : "POST" , 
						"url" : "${pageContext.request.contextPath}/recive/receive" , 
						"dataType" : "json" , 
						"data" : data , 
						"success" : function(o){
							console.log(o);
						} , 
						"error" : function(e){
							console.log("error data ：" + e);
							var fn = function( key , value ) {
								console.log(key + " : " + value);
							};
							 $.each( e , fn );
						}
				};
				$.ajax( options );
			});
			
		});
	</script>
	<body>
		<a href="javascript:;">点击测试</a>
	</body>
</html>