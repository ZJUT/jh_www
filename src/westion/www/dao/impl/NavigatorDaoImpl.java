package westion.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import westion.www.dao.NavigatorDao;
import westion.www.entity.Navigator;
import westion.www.exception.QueryException;
import westion.www.utls.JdbcUtls;

/**
 * 
 * ���ݿ�navigator��Dao��ӿ�ʵ��
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
	 * ����Navigator���б�
	 * @return List\<Navigator\> Navigator���б�
	 * @throws QueryException ���ܵõ�ʵ��
	 * */
	@Override
	public List<Navigator> list() {
		List<Navigator> navigators = null;
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from navigator");
			rs = st.executeQuery();

			objects = JdbcUtls.GetObjects(conn, st, rs, Navigator.class);

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

}
