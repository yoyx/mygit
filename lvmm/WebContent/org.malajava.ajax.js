

/**
 * 根据不同的主流浏览器返回相应的 XMLHttpRequest 实例
 */
function initRequest() {
	
	var $http ; // 函数内部的局部变量
	
	try {
		if( window.XMLHttpRequest ){
			$http = new XMLHttpRequest();
		}
	} catch ( e ) {
		console.log( e.message );
	}
	
	if( $http ){
		
	} else {
		
		try{
			// 如果 window.ActiveXObject 存在，说明是 IE 内核的 浏览器
			if( window.ActiveXObject ) {
				// 按照 IE 的方式 创建 $http 实例
				$http = new ActiveXObject( "Microsoft.XMLHTTP" );
			} 
		} catch ( e ) {
			// 如果发生异常，就捕获
			console.log( e.message ) ;
		}
		
	}
	
	return $http ; // 返回 创建好的 XMLHttpRequest 对象的 实例
}
