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
	 * ����һ���¼�
	 * 
	 * @param econtent
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
	 * ɾ��һ���¼�
	 * 
	 * @param Integer
	 *            id �¼���id
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
	 * @param Integer
	 *            id �¼���id
	 * @param econtent
	 *            String �¼�����
	 * @param ephoto_url
	 *            String �¼�ͼƬ
	 * @param etime
	 *            Integer �¼���ʱ��
	 * @param create_time
	 *            Integer �¼�����ʱ��
	 * @throws UpdateException
	 *             ����ʧ��
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
