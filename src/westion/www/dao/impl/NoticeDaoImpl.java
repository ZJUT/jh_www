package westion.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import westion.www.dao.NoticeDao;
import westion.www.entity.Notice;
import westion.www.exception.QueryException;
import westion.www.utls.JdbcUtls;

/**
 * 
 * ���ݿ�notice��Dao��ӿ�ʵ��
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
	 * ����Notice�����µ�һ��
	 * 
	 * @return Notice
	 * @throws QueryException
	 *             ���ܵõ�ʵ��
	 * */
	@Override
	public Notice getNew() {

		try {
			conn = JdbcUtls.getConnection();
			st = conn
					.prepareStatement("select * from notice where create_time=(select max(create_time) from notice)");
			rs = st.executeQuery();
			objects = JdbcUtls.GetObjects(conn, st, rs, Notice.class);
			if (objects.size() == 0) {
				throw new QueryException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return (Notice) objects.get(0);
	}

}
