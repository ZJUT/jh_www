package westion.www.entity;

import com.google.gson.annotations.Expose;



/**
 * 
 * 管理员数据库实体类
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class AdminUser {

	/**学号*/
	@Expose
	private String stu_id;
	/**用户名*/
	@Expose
	private String username;
	
	/**密码*/
	private String pwd;
	/**权限标记*/
	@Expose
	private Integer flag;
	
	
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	
	
	
}






