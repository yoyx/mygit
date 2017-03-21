package lvmama;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


/**
 * @author yao
 * @date 创建时间：2017年3月20日上午9,40,11
 * @version 1.0
 */
public class Test {

	/**
	 * @author yoyx@foxmail.com
	 * @date 2017年3月20日上午9,40,11
	 * @param args
	 */
	public static void main(String[] args) {

//		try {
//			post() ;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		test();
	}
	
	public static String Md5Encode(String str) {
		StringBuilder sign = new StringBuilder();

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(str.getBytes());

			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(bytes[i] & 0xFF);
				if (hex.length() == 1) {
					sign.append("0");
				}
				sign.append(hex.toUpperCase());
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sign.toString();
	}
	
	//请求
	public static String get(String url, String json){  
			//实例化httpclient  
			CloseableHttpClient httpclient = HttpClients.createDefault();  
			//实例化get方法  
			HttpGet httpget = new HttpGet(url);   
			//请求结果  
			CloseableHttpResponse response = null;  
			String content ="";  
			try {  
				//执行get请求  
				response = httpclient.execute(httpget);  
				if(response.getStatusLine().getStatusCode()==200){  
					content = EntityUtils.toString(response.getEntity(),"utf-8");  
					System.out.println(content);  
				}  
			} catch (ClientProtocolException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
			return content;  
		} 
	
	/**
	 * 
	 * @author yoyx@foxmail.com
	 * @date 2017年3月20日下午6:35:23
	 * @return 返回数据为响应结束后返回的json数据
	 * @throws Exception
	 */
	public static String post( ) throws Exception{
		String url = "http://127.0.0.1:8080/lvmama/recive/receive.do" ;
		HttpPost httpPost = new HttpPost(url) ;
		
		Map<String , String> jsonMap = new HashMap<>() ;
		jsonMap.put("uid" ,"112233" ) ;
		jsonMap.put("password" , "123456") ;
		jsonMap.put("timestamp" , "20161211130143") ;
		jsonMap.put("visitTime" , "20161211130140") ;
		jsonMap.put("supplierGoodsId" , "0001") ;
		jsonMap.put("settlePrice" , "126") ;
		jsonMap.put("num", "2") ;
		jsonMap.put("serialNo" , "20170101") ;
		jsonMap.put("sign" , "B75F13D86D935F00B9728CA04888BE40") ;
		jsonMap.put("idNum" , "622223198906168899") ;
		jsonMap.put("idType" , "身份证") ;
		jsonMap.put("mobile" , "13109311234") ;
		jsonMap.put("name" , "张三丰") ;
		
		JSONObject jsonObject = new JSONObject(jsonMap) ; //封装一个json对象
		
		
		
		StringEntity se = new StringEntity(jsonObject.toString()) ; //绑定到请求中
		se.setContentEncoding("UTF-8");
		se.setContentType("application/json");
		httpPost.setEntity(se);
		//实例化htpClient
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		//执行请求
		CloseableHttpResponse response = httpclient.execute(httpPost) ;
		//接受返回的json数据
		String content = "" ;
		if(response.getStatusLine().getStatusCode()==200){  
			content = EntityUtils.toString(response.getEntity(),"utf-8");  
			System.out.println(content);  
		}  
		return content ;
	}
	
	public static void test(){
		String json="{\"name\":\"aa\" , \"pwd\" : \"123456\"}";
		JSONObject jb = new JSONObject(json) ;
		System.err.println("name: " + jb.get("name"));
	}

}
