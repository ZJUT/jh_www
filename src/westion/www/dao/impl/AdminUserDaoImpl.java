package westion.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import westion.www.dao.AdminUserDao;
import westion.www.entity.AdminUser;
import westion.www.exception.QueryException;
import westion.www.utls.JdbcUtls;

/**
 * 
 * 数据库user表Dao类接口实现
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class AdminUserDaoImpl implements AdminUserDao {

	private List<Object> objects = null;
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	/**
	 * 返回AdminUser的列表
	 * 
	 * @return List\<AdminUser\> AdminUser的列表
	 * @throws QueryException
	 *             不能得到实体
	 * */
	@Override
	public List<AdminUser> list() {
		List<AdminUser> AdminUsers = null;
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from user");
			rs = st.executeQuery();

			objects = JdbcUtls.GetObjects(rs, AdminUser.class);

			AdminUsers = new ArrayList<AdminUser>();

			for (int i = 0; i < objects.size(); i++) {
				AdminUsers.add((AdminUser) objects.get(i));
			}
		} catch (Exception e) {
			throw new QueryException(e);
		}
		return AdminUsers;
	}

	/**
	 * 通过学号返回单个AdminUser对象
	 * 
	 * @param stuId
	 *            String 学号
	 * @return List\<AdminUser\> AdminUser的列表
	 * @throws QueryException
	 *             查询失败
	 * */
	@Override
	public AdminUser findByStuId(String stuId) {
		try {
			conn = JdbcUtls.getConnection();
			st = conn.prepareStatement("select * from user where stu_id=?");
			st.setString(1, stuId);
			rs = st.executeQuery();
			objects = JdbcUtls.GetObjects(rs, AdminUser.class);
			if (objects.size() == 0) {
				throw new QueryException();
			}
		} catch (Exception e) {
			throw new QueryException(e);
		}
		return (AdminUser) objects.get(0);
	}

}
