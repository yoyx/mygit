package com.lvmama.jiekou;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * @author yao
 * @date ：2017年3月17日下午2:01:02
 * @version 1.0
 */
@WebServlet("/recive/receive.do")
public class LvMaMaServlet extends HttpServlet {

	private static final long serialVersionUID = -4505707260876050808L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson() ; //创建好解析Json数据的对象
		LvMaMa lmm = new LvMaMa() ;
		String sql = "insert into t_lvmm (uid , password, timestamp , supplierGoodsId , num , serialNo , sign , idNum, idType , name , mobile  , authCode , settlePrice) "
				+ " values (?, ?, ? , ? , ? , ? , ? , ?, ? , ? , ? , ? , ? )" ;
		try {
			String lvMaDate = StringUtil.receivePost(request) ; //接受读取道德数据
			JSONObject jb = new JSONObject(lvMaDate) ;
			PreparedStatement ps = DBUtil.prepare(sql, false) ;
			if(jb.has("uid") && jb.has("password") && jb.has("timestamp") && jb.has("visitTime") 
								   && jb.has("supplierGoodsId") && jb.has("settlePrice") && jb.has("num")
								   && jb.has("serialNo") && jb.has("sign") && jb.has("idNum") && jb.has("idType")
								   && jb.has("mobile") && jb.has("name")){
				
				lmm = gson.fromJson(lvMaDate, LvMaMa.class) ; //解析json数据，并将值封装到对象
				System.out.println( lmm);
				String sign = lmm.getSign() ; //获得闯过来的签名
				if(StringUtil.notEmpty( sign )){ //签名不为空，在比较
					//num+password+serialNo+settlePrice+supplierGoodsId+timestamp+uid+visitTime
					String endSign = lmm.getIdNum() + lmm.getPassword() + lmm.getSerialNo() + lmm.getSettlePrice() 
											 + lmm.getSupplierGoodsId() + lmm.getTimestamp() + lmm.getVisitTime() ;
					
					String signKey = "5772ec54" ;
					endSign = StringUtil.Md5Encode(endSign) + signKey ;
					endSign = StringUtil.Md5Encode(endSign) ; //在加密
					
//					if(sign.equals(endSign) ){ //签名一致，在判断是不是产品
						if( StringUtil.notEmpty(lmm.getUid()) && lmm.getUid().equals("112233")){ //说明是铲平了
							if(StringUtil.notEmpty(lmm.getPassword() ) 
									&& StringUtil.notEmpty(lmm.getVisitTime())
									&& StringUtil.notEmpty(lmm.getSupplierGoodsId() ) 
									&& StringUtil.notEmpty(lmm.getSettlePrice()) 
									&& StringUtil.notEmpty(lmm.getNum()) 
									&& StringUtil.notEmpty(lmm.getSerialNo()) 
									&& StringUtil.notEmpty(lmm.getSign()) 
									&& StringUtil.notEmpty(lmm.getIdNum())
									&& StringUtil.notEmpty(lmm.getIdType()) 
									&& StringUtil.notEmpty(lmm.getMobile())){
								//以上都不为空
								Integer orderId = Integer.parseInt( lmm.getNum() ) ;
								ps.setString(1, lmm.getUid() );
								ps.setString(2, lmm.getPassword() );
								ps.setString(3, lmm.getTimestamp() );
								ps.setString(4, lmm.getSupplierGoodsId());
								
								ps.setString(6, lmm.getSerialNo());
								ps.setString(7, lmm.getSign());
								ps.setString(8,  lmm.getIdNum());
								ps.setString(9, lmm.getIdType());
								ps.setString(10, lmm.getName());
								ps.setString(11, lmm.getMobile());
								ps.setString(13 , lmm.getSettlePrice());
								StringBuilder sb = new StringBuilder() ;
								for( int i = 0 ; i < orderId ; i++){
									String authCode = StringUtil.getRandomString(12) ;
									if(i == orderId - 1){
										sb.append(authCode) ;
									}else{
										sb.append( authCode +",") ;
									}
									ps.setString(5, "1");
									ps.setString(12, authCode);
									ps.execute() ; //执行保存操作
									
								}//循环结束
								//发送响应数据
								JSONObject json = this.successJson(sb.toString()) ;
								response.getWriter().print(json );;
							}else{
								response.getWriter().println( this.returnErrorMsg() ); //某一列是空的值
							}
						}else{
							response.getWriter().println( this.returnErrorMsg() ); //产品不符合
						}
//					}else{
//						response.getWriter().println( this.returnErrorMsg() ); //签名不一样
//					}
				}else{
					response.getWriter().println( this.returnErrorMsg() ); //签名是空的
				}
			}else{
				response.getWriter().println( this.returnErrorMsg() ); //没有那个字段
			}
			
		} catch (Exception e) {
			System.err.println( e.getMessage() );
		}
	}
	
	
	
	/**
	 * 返回接受成功后正确的数据
	 * @author yoyx@foxmail.com
	 * @date 2017年3月17日下午6:15:28
	 * @return
	 */
	public JSONObject successJson( String str){
		
		Map<String , String> jsonMap = new HashMap<>() ;
//		String authCode = str ;// 接受传如的验证码
		String orderId = StringUtil.getRandomString(10) ;//生存十位的订单号
		String status = "1" ; //状态吗
		String codeURL = "http://www.lvmm.com" ; //二维码超链接
		
		jsonMap.put("authCode", str ) ;
		jsonMap.put("orderId", orderId ) ;
		jsonMap.put("status", status ) ;
		jsonMap.put("codeURL", codeURL ) ;
		
		JSONObject jsonObject = new JSONObject(jsonMap) ;
		return jsonObject ;
	}
	
	public JSONObject returnErrorMsg(){
		Map<String , String> jsonMap = new HashMap<>() ;
		jsonMap.put("msg", "传值有问题") ;
		JSONObject jsonObject = new JSONObject(jsonMap) ;
		return jsonObject ;
	}

}
