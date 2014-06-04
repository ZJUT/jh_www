package westion.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import westion.www.dao.EventDao;
import westion.www.entity.Event;
import westion.www.exception.AddException;
import westion.www.exception.DeleteException;
import westion.www.exception.QueryException;
import westion.www.exception.UpdateException;
import westion.www.utls.JdbcUtls;

/**
 * 
 * ���ݿ�event��Dao��ӿ�ʵ��
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
	 * ����Event���б�
	 * 
	 * @return List\<Event\> Event���б�
	 * @throws QueryException
	 *             ���ܵõ�ʵ��
	 * */
	@Override
	public List<Event> list() {

		List<Event> events = null;
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from event order by etime desc");
			rs = st.executeQuery();

			objects = JdbcUtls.GetObjects(rs, Event.class);

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
	 * ����һ���¼�
	 * 
	 * @param econtent
	 *            String �¼�����
	 * @param etitle
	 *            String �¼�����
	 * @param ephoto_url
	 *            String �¼�ͼƬ
	 * @param etime
	 *            Integer �¼���ʱ��
	 * @param create_time
	 *            Integer �¼�����ʱ��
	 * @throws AddException
	 *             ���ʧ��
	 * 
	 * */
	@Override
	public void add(String econtent, String etitle, String ephoto_url,
			Long etime, Long create_time) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("insert into event(econtent,etitle,ephoto_url,etime,create_time)values(?,?,?,?,?)");
			st.setString(1, econtent);
			st.setString(2, etitle);
			st.setString(3, ephoto_url);
			st.setLong(4, etime);
			st.setLong(5, create_time);
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
	 * ɾ��һ���¼�
	 * 
	 * @param id
	 *            Integer �¼���id
	 * @throws DeleteException
	 *             ɾ��ʧ��
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
	 * �޸�һ���¼�
	 * 
	 * @param id
	 *            Integer �¼���id
	 * @param econtent
	 *            String �¼�����
	 * @param ephoto_url
	 *            String �¼�ͼƬ
	 * @param etime
	 *            Long �¼���ʱ��
	 * @param create_time
	 *            Long �¼�����ʱ��
	 * @throws UpdateException
	 *             ����ʧ��
	 * 
	 * */
	@Override
	public void update(Integer id, String econtent, String etitle,
			String ephoto_url, Long etime, Long create_time) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("update event set econtent=?,etitle=?,ephoto_url=?,etime=?,create_time=? where eid=?");
			st.setString(1, econtent);
			st.setString(2, etitle);
			st.setString(3, ephoto_url);
			st.setLong(4, etime);
			st.setLong(5, create_time);
			st.setInt(6, id);
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

	/**
	 * ����һ���¼���ͨ��eid
	 * 
	 * @param eid
	 *            Integer �¼���id
	 * @throws QueryException
	 *             ��ѯʧ�ܣ����鲻��
	 * 
	 * */
	@Override
	public Event findById(Integer eid) {
		Event event = null;
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from event where eid=?");
			st.setInt(1, eid);
			rs = st.executeQuery();
			objects = JdbcUtls.GetObjects(rs, Event.class);
			if (objects.size() == 0) {
				throw new QueryException();
			}
			event = (Event) objects.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		} finally {
			JdbcUtls.close(conn, st, rs);
		}
		return event;
	}

}
