package westion.www.test;



import java.sql.SQLException;

import org.junit.Test;

import westion.www.entity.Event;
import westion.www.service.EventService;
import westion.www.service.impl.EventServiceImpl;



/**
 * 
 * ≤‚ ‘¿‡
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class Test1 {

	@Test
	public void test() throws SQLException {
		
		EventService eventService = new EventServiceImpl();	
		for(Event s:eventService.list())
		{
			System.out.println(s.getEphoto_url());
		}
	}

}
