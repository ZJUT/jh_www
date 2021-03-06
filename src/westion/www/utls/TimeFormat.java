package westion.www.utls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import westion.www.exception.TimeFormatException;

/**
 * 时间格式化工具类
 * 
 * @version 1.0, 2014-4-4
 * @author westion
 * @since JDK1.7
 */
public class TimeFormat {
	
	
	/**
	 * 反格式化时间生成Date对象
	 * 
	 * @param etime
	 *            String
	 * @return Date
	 */
	public static Date changeToLongTime(String etime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(etime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new TimeFormatException(e);
		}
		return date;
	}
}
