package westion.www.entity;

import com.google.gson.annotations.Expose;


/**
 * 
 * ֪ͨ�����ݿ�ʵ����
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class Notice {

	/**���(����)*/
	@Expose
	private Integer nid;
	/**֪ͨ����*/
	@Expose
	private String ncontent;
	/**֪ͨ������*/
	@Expose
	private String destination_url;
	/**֪ͨ��ͼƬ*/
	@Expose
	private String nphoto_url;
	/**��ʽ������֪ͨ������ʱ��*/
	@Expose
	private String formatTime;
	
	
	/**����1970��ʽ��֪ͨ������ʱ��*/
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
