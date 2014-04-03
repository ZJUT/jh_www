package westion.www.entity;

import com.google.gson.annotations.Expose;


/**
 * 
 * 导航内容数据库实体类
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class Navigator {
	
	/**编号(主键)*/
	@Expose
	private Integer naid;
	/**导航名称*/
	@Expose
	private String naname;
	/**导航对应链接*/
	@Expose
	private String destination_url;
	
	
	
	public Integer getNaid() {
		return naid;
	}
	public void setNaid(Integer naid) {
		this.naid = naid;
	}
	public String getNaname() {
		return naname;
	}
	public void setNaname(String naname) {
		this.naname = naname;
	}
	public String getDestination_url() {
		return destination_url;
	}
	public void setDestination_url(String destination_url) {
		this.destination_url = destination_url;
	}
	
	
}
