package westion.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import westion.www.dao.EventDao;
import westion.www.dao.NoticeDao;
import westion.www.entity.Event;
import westion.www.entity.Notice;
import westion.www.exception.AddException;
import westion.www.exception.ArgumentNumException;
import westion.www.exception.DeleteException;
import westion.www.exception.QueryException;
import westion.www.exception.UpdateException;
import westion.www.utls.JdbcUtls;

/**
 * 
 * 数据库event表Dao类接口实现
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class EventDaoImpl implements EventDao {

	private List<Object> objects = null;
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 返回Event的列表
	 * 
	 * @return List\<Event\> Event的列表
	 * @throws QueryException
	 *             不能得到实体
	 * */
	@Override
	public List<Event> list() {

		List<Event> events = null;
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from event");
			rs = st.executeQuery();

			objects = JdbcUtls.GetObjects(conn, st, rs, Event.class);

			events = new ArrayList<Event>();

			for (int i = 0; i < objects.size(); i++) {
				events.add((Event) objects.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		} finally {
			JdbcUtls.close(conn, st, rs);
		}
		return events;
	}

	/**
	 * 增加一条事件
	 * 
	 * @param econtent
	 *            String 事件内容
	 * @param ephoto_url
	 *            String 事件图片
	 * @param etime
	 *            Integer 事件的时间
	 * @param create_time
	 *            Integer 事件发布时间
	 * @throws AddException
	 *             添加失败
	 * 
	 * */
	@Override
	public void add(String econtent, String ephoto_url, long etime,
			long create_time) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("insert into event(econtent,ephoto_url,etime,create_time)values(?,?,?,?)");
			st.setString(1, econtent);
			st.setString(2, ephoto_url);
			st.setLong(3, etime);
			st.setLong(4, create_time);
			int acount = st.executeUpdate();
			if (acount == 0)
				throw new AddException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e);
		} finally {
			JdbcUtls.close(conn, st, rs);
		}
	}

	/**
	 * 删除一条事件
	 * 
	 * @param Integer
	 *            id 事件主id
	 * @throws DeleteException
	 *             删除失败
	 * 
	 * */
	@Override
	public void delete(Integer id) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("delete from event where eid=?");
			st.setInt(1, id);
			int acount = st.executeUpdate();
			if (acount == 0)
				throw new DeleteException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DeleteException(e);
		} finally {
			JdbcUtls.close(conn, st, rs);
		}
	}

	/**
	 * 修改一条事件
	 * 
	 * @param Integer
	 *            id 事件主id
	 * @param econtent
	 *            String 事件内容
	 * @param ephoto_url
	 *            String 事件图片
	 * @param etime
	 *            Integer 事件的时间
	 * @param create_time
	 *            Integer 事件发布时间
	 * @throws UpdateException
	 *             更新失败
	 * 
	 * */
	@Override
	public void update(Integer id, String econtent, String ephoto_url,
			Integer etime, Integer create_time) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("update event set econtent=?,ephoto_url=?,etime=?,create_time=? where eid=?");
			st.setString(1, econtent);
			st.setString(2, ephoto_url);
			st.setInt(3, etime);
			st.setInt(4, create_time);
			st.setInt(5, id);
			int acount = st.executeUpdate();
			if (acount == 0)
				throw new UpdateException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new UpdateException(e);
		} finally {
			JdbcUtls.close(conn, st, rs);
		}
	}

}
