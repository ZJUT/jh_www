package westion.www.dao;

import java.util.List;

import westion.www.entity.AdminUser;

/**
 * 
 * ���ݿ�user��Dao��ӿ�
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * 
 */
public interface AdminUserDao {

	List<AdminUser> list();

	AdminUser findByStuId(String stuId);
}
