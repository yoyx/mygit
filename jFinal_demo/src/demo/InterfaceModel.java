package demo;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author yao
 * @date 创建时间：2017年3月16日上午9:04:43
 * @version 1.0
 */
public class InterfaceModel extends Model {

	public List<Object> getCountByTime(String startTime, String endTime, String userId) {
		InterfaceModel dao = new InterfaceModel();
		String sqlList = "select recommendedUser , count(recommendedUser) AS count, max(time) AS nearTime  "
				+ "from tyjt_weixin_user where  status = 1 ";

		List<Object> params = new ArrayList<Object>();

		if (userId != null) {
			sqlList += " and user_id = ?";
			params.add(userId);
		}
		if (startTime != null) {
			sqlList += " and time >= ?";
			params.add(startTime);
		}
		if (endTime != null) {
			sqlList += " and time <= ?";
			params.add(endTime);
		}
		if (userId == null && startTime == null && endTime == null) {
			sqlList += " and 1=2";
		}
		
		
		return null;

	}

}
