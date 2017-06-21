package sspku.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public DateUtil() {
	}

	public static Date getdate(int i) // //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
	{
		Date dd = new Date();
		Calendar cd = Calendar.getInstance();
		cd.setTime(dd);
		cd.add(Calendar.DATE, i);
		return cd.getTime();
	}

}
