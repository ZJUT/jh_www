package westion.www.admin.action;

import java.util.List;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import westion.www.entity.Notice;
import westion.www.service.NoticeService;
import westion.www.service.impl.NoticeServiceImpl;
import westion.www.utls.FileTool;

/**
 * 
 * ֪ͨ�����ࣨ����Ա��
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.NoticeService
 */
public class AdminNoticeAction {

	/** ������� */
	private Map<String, String[]> params = null;

	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;
	/** ��վȫ�ֵķ���״̬�ļ� */
	private List<String> errorList = null;

	private NoticeService noticeService = new NoticeServiceImpl();

	private HttpServletRequest request = null;

	/**
	 * ���캯������request�л�ȡ��Ӧ�����ԣ�����˽�г�Ա��ʼ��
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public AdminNoticeAction(HttpServletRequest request) {
		super();
		this.params = (Map<String, String[]>) request.getAttribute("params");
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (List<String>) request.getAttribute("errorList");
		this.request = request;
	}

	/**
	 * ����һ��֪ͨ
	 * */
	public void addNotice() {
		String filename = null;
		try {
			filename = FileTool.fileUpload(request,
					"/" + properties.getProperty("noticePhotoDir"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("uploadError"));
			return;
		}
		try {
			String address = null;
			if (filename != null)
				address = properties.getProperty("noticePhotoDir") + filename;
			noticeService.add((String) request.getAttribute("ncontent"),
					(String) request.getAttribute("destination_url"), address,
					null);
		} catch (Exception e) {
			e.printStackTrace();
			if (filename != null)
				FileTool.deleteFile(this.request.getServletContext()
						.getRealPath(
								"/" + properties.getProperty("noticePhotoDir")
										+ filename));
			errorList.add(properties.getProperty("uploadError"));
		}
	}

	/**
	 * ɾ��һ��֪ͨ����Ҫɾ��ͼƬ
	 * */
	public void deleteNotice() {
		Notice notice = null;
		try {
			notice = noticeService
					.findById(Integer.parseInt(params.get("nid")[0]));
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			noticeService.delete(notice.getNid());

		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			String filename = notice.getNphoto_url();
			if (filename != null)
				FileTool.deleteFile(this.request.getServletContext()
						.getRealPath("/" + filename));
		} catch (Exception e) {
			e.printStackTrace();
			noticeService.add(notice.getNcontent(),
					notice.getDestination_url(), notice.getNphoto_url(),
					notice.getCreate_time());
			errorList.add(properties.getProperty("sqlError"));
		}

	}

	/**
	 * �޸�һ��֪ͨ
	 * */
	public void updateNotice() {
		Notice notice = null;
		String filename = null;
		try {
			notice = noticeService
					.findById(Integer.parseInt(params.get("nid")[0]));
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			filename = notice.getNphoto_url();
			if (filename != null)
				FileTool.deleteFile(this.request.getServletContext()
						.getRealPath("/" + filename));
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			filename = FileTool.fileUpload(request,
					"/" + properties.getProperty("noticePhotoDir"));
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			String address = null;
			if (filename != null)
				address = properties.getProperty("noticePhotoDir") + filename;
			noticeService.update(notice.getNid(),
					(String) request.getAttribute("ncontent"),
					(String) request.getAttribute("destination_url"), address,
					null);
		} catch (Exception e) {
			e.printStackTrace();
			if (filename != null)
				FileTool.deleteFile(this.request.getServletContext()
						.getRealPath(
								"/" + properties.getProperty("noticePhotoDir")
										+ filename));
			errorList.add(properties.getProperty("sqlError"));
		}

	}

}
