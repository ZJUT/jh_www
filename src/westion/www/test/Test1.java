package westion.www.test;

import java.sql.SQLException;

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

		NoticeService noticeService = new NoticeServiceImpl();
		System.out.println(noticeService.getNew().getDestination_url());
		
	}

}
