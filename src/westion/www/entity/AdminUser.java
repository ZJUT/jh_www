package westion.www.entity;

import com.google.gson.annotations.Expose;



/**
 * 
 * ����Ա���ݿ�ʵ����
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class AdminUser {

	/**ѧ��*/
	@Expose
	private String stu_id;
	/**�û���*/
	@Expose
	private String username;
	
	/**����*/
	private String pwd;
	/**Ȩ�ޱ��*/
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






