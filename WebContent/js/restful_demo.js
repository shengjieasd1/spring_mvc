$(function() {
	$(editMember).on("click",function(){
		$.ajax({
			url : "member?mid=1009&name=阿伦&salary=19777.77&hiredate=1977-10-10" ,	// 处理的请求路径
			type : "put" ,		// 此处发送的是POST请求
			dataType : "json" ,	// 返回的数据类型为json类型
			// data : {
			// 	mid : 1007 ,
			// 	name : "阿伦" ,
			// 	salary : 19777.77 ,
			// 	hiredate : "1977-10-10"
			// } ,
			success : function(data) {
				$(showDiv).append("<p>修改处理结果：" + data.flag + "</p>") ;
			} ,
			error : function(data) {
				$(showDiv).append("<p>对不起，出错啦！</p>") ;
			}
		}) ;
	}) ;
	$(addMember).on("click",function(){ 
		$.ajax({
			url : "member" ,	// 处理的请求路径
			type : "post" ,		// 此处发送的是POST请求
			dataType : "json" ,	// 返回的数据类型为json类型
			data : {
				mid : 1001 ,
				name : "史密斯" ,
				salary : 9888.12 ,
				hiredate : "1981-10-10" 
			} ,
			success : function(data) {
				$(showDiv).append("<p>增加处理结果：" + data.flag + "</p>") ;
			} ,
			error : function(data) {
				$(showDiv).append("<p>对不起，出错啦！</p>") ;
			} 
		}) ;
	}) ;
})