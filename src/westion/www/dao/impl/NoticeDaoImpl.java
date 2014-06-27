package westion.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import westion.www.dao.NoticeDao;
import westion.www.entity.Notice;
import westion.www.exception.AddException;
import westion.www.exception.DeleteException;
import westion.www.exception.QueryException;
import westion.www.exception.UpdateException;
import westion.www.utls.JdbcUtls;

/**
 * 
 * 数据库notice表Dao类接口实现
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class NoticeDaoImpl implements NoticeDao {

	private List<Object> objects = null;
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 返回Notice中最新的一条
	 * 
	 * @return Notice
	 * @throws QueryException
	 *             不能得到实体
	 * */
	@Override
	public Notice getNew() {

		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("select * from notice where create_time=(select max(create_time) from notice)");
			rs = st.executeQuery();
			objects = JdbcUtls.GetObjects(rs, Notice.class);
			if (objects.size() == 0) {
				throw new QueryException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		finally {
			JdbcUtls.close(conn, st, rs);
		}
		return (Notice) objects.get(0);
	}

	/**
	 * 增加一条通知
	 * 
	 * @param ncontent
	 *            String 通知内容
	 * @param destination_url
	 *            String 通知的链接
	 * @param nphoto_url
	 *            String 通知图片的链接
	 * @param create_time
	 *            String 通知建立的时间
	 * @throws AddException
	 *             添加失败
	 * 
	 * */
	@Override
	public void add(String ncontent, String destination_url, String nphoto_url,
			Long create_time) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("insert into notice(ncontent,destination_url,nphoto_url,create_time)values(?,?,?,?)");
			st.setString(1, ncontent);
			st.setString(2, destination_url);
			st.setString(3, nphoto_url);
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
	 * 删除一条通知
	 * 
	 * @param id
	 *            Integer 通知主id
	 * @throws DeleteException
	 *             删除失败
	 * 
	 * */
	@Override
	public void delete(Integer id) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("delete from notice where nid=?");
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
	 * 修改一条通知
	 * 
	 * @param ncontent
	 *            String 通知内容
	 * @param destination_url
	 *            String 通知的链接
	 * @param nphoto_url
	 *            String 通知图片的链接
	 * @param create_time
	 *            String 通知建立的时间
	 * @throws AddException
	 *             添加失败
	 * 
	 * */
	@Override
	public void update(Integer id, String ncontent, String destination_url,
			String nphoto_url, Long create_time) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("update notice set ncontent=?,destination_url=?,nphoto_url=?,create_time=? where nid=?");
			st.setString(1, ncontent);
			st.setString(2, destination_url);
			st.setString(3, nphoto_url);
			st.setLong(4, create_time);
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
System.out.println();
	}

	/**
	 * 获得一条通知,通过nid
	 * 
	 * @param nid
	 *            Integer 通知主id
	 * @throws QueryException
	 *             查询失败，即查不到
	 * 
	 * */
	@Override
	public Notice findById(Integer nid) {
		Notice notice = null;
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from notice where nid=?");
			st.setInt(1, nid);
			rs = st.executeQuery();
			objects = JdbcUtls.GetObjects(rs, Notice.class);
			if (objects.size() == 0) {
				throw new QueryException();
			}
			notice = (Notice) objects.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		} finally {
			JdbcUtls.close(conn, st, rs);
		}
		return notice;

	}

}
