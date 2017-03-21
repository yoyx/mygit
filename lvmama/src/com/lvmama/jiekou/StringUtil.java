package com.lvmama.jiekou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 对字符串进行检查或操作的工具类
 */
public final class StringUtil {
	
	public static boolean notEmpty( String s ) {
		/*
		if( s != null && s.trim().length() != 0 ) {
			return true ;
		}
		return false ;
		*/
		return s != null && s.trim().length() != 0 ;
	}
	
	/**
	 * 判断一个字符串是否是 空 或 空串(剔除首尾空白后)
	 * @param s 需要判断的 字符串
	 * @return 如果 字符串 是 空 或 空串 返回 true ，否则返回 false
	 */
	public static boolean isEmpty ( String s ) {
		/*
		if( s == null ) {
			return true ;
		}
		
		if( s.trim().isEmpty() ){
			return true ;
		}
		
		return false ;
		*/
		return s == null || s.trim().isEmpty() ;
	}
	
	/**
	 * 随机产生一个 UUID 字符串 ( UUID : 通用唯一标识符 )
	 * @return
	 */
	public static String uuid(){
		
		UUID u = UUID.randomUUID();
		
		String s = u.toString();
		
		s = s.replace( "-" , "" );
		
		s = s.toUpperCase() ;
		
		return s ;
		
	}
	
	/**
	 * MD5加密
	 * 
	 * @param str需要加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 */
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

	/**
	 * 随机生成12位数字验证码
	 * 
	 * @return 生成后的验证码
	 */
	public static String getRandomString(int c) {
		char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c; i++) {
			sb.append(chars[random.nextInt(chars.length)]);
		}
		return sb.toString();
	}
	
	/**
	 * 接受请求
	 * @author yoyx@foxmail.com
	 * @date 2017年3月20日上午8:32:45
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String receivePost(HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("UTF-8");

		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		System.out.println("读取到的数据为：" + sb.toString());
		return sb.toString();
	}

}
