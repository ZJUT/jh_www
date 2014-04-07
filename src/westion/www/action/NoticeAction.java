package westion.www.action;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import westion.www.entity.Navigator;
import westion.www.entity.Notice;
import westion.www.exception.QueryException;
import westion.www.service.NoticeService;
import westion.www.service.impl.NoticeServiceImpl;

/**
 * 
 * ֪ͨ������
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.service.NoticeService
 */
public class NoticeAction {

	/** ������� */
	private Map<String, String[]> params = null;
	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;
	/** ��վȫ�ֽ���ļ� */
	private ArrayList<String> errorList = null;
	
	private NoticeService noticeService = new NoticeServiceImpl();

	/**
	 * ���캯������request�л�ȡ��Ӧ�����ԣ�����˽�г�Ա��ʼ��
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public NoticeAction(HttpServletRequest request) {
		super();
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (ArrayList<String>) (request.getAttribute("errorList"));
		this.params = request.getParameterMap();
	}

	/**
	 * ��ȡ���µ�֪ͨ
	 * 
	 * @return Notice
	 */
	public Notice getNew() {
		Notice notice = null;
		try {
			notice = noticeService.getNew();
		} catch (QueryException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
		return notice;
	}
	
	/**
	 * ���һ��ָ��֪ͨ
	 * @return event Notice
	 * */
	public Notice getNotice() {
		Notice notice = null;
		try {
			notice = noticeService.findById(Integer.parseInt(params.get("nid")[0]));
		} catch (QueryException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
		return notice;
	}
}
