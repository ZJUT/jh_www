package westion.www.test;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import westion.www.service.NoticeService;
import westion.www.service.impl.NoticeServiceImpl;

/**
 * 
 * ≤‚ ‘¿‡
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class Test1 {

	@Test
	public void test() throws SQLException {

	Date a = new Date(9223372036854775807L);
	
	System.out.println(Long.toString(a.getTime()));
		
	}

}
