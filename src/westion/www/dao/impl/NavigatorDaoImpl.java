package westion.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import westion.www.dao.NavigatorDao;
import westion.www.entity.Event;
import westion.www.entity.Navigator;
import westion.www.exception.AddException;
import westion.www.exception.DeleteException;
import westion.www.exception.QueryException;
import westion.www.exception.UpdateException;
import westion.www.utls.JdbcUtls;

/**
 * 
 * 数据库navigator表Dao类接口实现
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class NavigatorDaoImpl implements NavigatorDao {

	private List<Object> objects = null;
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 返回Navigator的列表
	 * 
	 * @return List\<Navigator\> Navigator的列表
	 * @throws QueryException
	 *             不能得到实体
	 * */
	@Override
	public List<Navigator> list() {
		List<Navigator> navigators = null;
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from navigator");
			rs = st.executeQuery();

			objects = JdbcUtls.GetObjects(rs, Navigator.class);

			navigators = new ArrayList<Navigator>();

			for (int i = 0; i < objects.size(); i++) {
				navigators.add((Navigator) objects.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return navigators;
	}

	/**
	 * 增加一个导航条目
	 * 
	 * @param naname
	 *            String 导航条目内容
	 * @param destination_url
	 *            String 导航目的链接
	 * @throws AddException
	 *             添加失败
	 * 
	 * */
	@Override
	public void add(String naname, String destination_url) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("insert into navigator(naname,destination_url)values(?,?)");
			st.setString(1, naname);
			st.setString(2, destination_url);
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
	 * 删除一个导航条目
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
			st = conn.prepareStatement("delete from navigator where naid=?");
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
	 * 修改一个导航条目
	 * 
	 * @param naname
	 *            String 导航条目内容
	 * @param destination_url
	 *            String 导航目的链接
	 * @throws AddException
	 *             添加失败
	 * 
	 * */
	@Override
	public void update(Integer id, String naname, String destination_url) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("update navigator set naname=?,destination_url=? where naid=?");
			st.setString(1, naname);
			st.setString(2, destination_url);
			st.setInt(3, id);
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
	
	@Override
	public Navigator findById(Integer naid) {
		Navigator navigator = null;
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from navigator where naid=?");
			st.setInt(1, naid);
			rs = st.executeQuery();
			objects = JdbcUtls.GetObjects(rs, Navigator.class);
			if (objects.size()==0) {
				throw new QueryException();
			}
			navigator = (Navigator)objects.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		} finally {
			JdbcUtls.close(conn, st, rs);
		}
		return navigator;
	}

}
