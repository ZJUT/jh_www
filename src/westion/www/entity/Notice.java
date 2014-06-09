package westion.www.entity;

import com.google.gson.annotations.Expose;


/**
 * 
 * 通知的数据库实体类
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class Notice {

	/**编号(主键)*/
	@Expose
	private Integer nid;
	/**通知内容*/
	@Expose
	private String ncontent;
	/**通知的链接*/
	@Expose
	private String destination_url;
	/**通知的图片*/
	@Expose
	private String nphoto_url;
	/**格式化过的通知创建的时间*/
	@Expose
	private String formatTime;
	
	
	/**距离1970格式的通知创建的时间*/
	private Long create_time;
	
	
	
	
	
	
	
	
	
	
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getDestination_url() {
		return destination_url;
	}
	public void setDestination_url(String destination_url) {
		this.destination_url = destination_url;
	}
	public Long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
	public String getNphoto_url() {
		return nphoto_url;
	}
	public void setNphoto_url(String nphoto_url) {
		this.nphoto_url = nphoto_url;
	}
	
	
	
}
