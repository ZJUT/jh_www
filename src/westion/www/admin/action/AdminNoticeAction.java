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
 * 通知请求类（管理员）
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.NoticeService
 */
public class AdminNoticeAction {

	/** 请求参数 */
	private Map<String, String[]> params = null;

	/** 网站全局的配置文件 */
	private Properties properties = null;
	/** 网站全局的返回状态文件 */
	private List<String> errorList = null;

	private NoticeService noticeService = new NoticeServiceImpl();

	private HttpServletRequest request = null;

	/**
	 * 构造函数，从request中获取相应的属性，并对私有成员初始化
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
	 * 增加一条通知
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
	 * 删除一条通知，并要删除图片
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
	 * 修改一条通知
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
