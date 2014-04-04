package westion.www.admin.action;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import westion.www.service.EventService;
import westion.www.service.impl.EventServiceImpl;
import westion.www.utls.FileUploadTool;

public class AdminEventAction {

	/** 请求参数 */
	private Map<String, String[]> params = null;
	/** 请求用户的session */
	private HttpSession session = null;
	/** 网站全局的配置文件 */
	private Properties properties = null;
	/** 网站全局的返回状态文件 */
	private List<String> errorList = null;

	private EventService eventService = new EventServiceImpl();

	private HttpServletRequest request = null;

	/**
	 * 构造函数，从request中获取相应的属性，并对私有成员初始化
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public AdminEventAction(HttpServletRequest request) {
		super();
		this.params = (Map<String, String[]>) request.getAttribute("params");
		this.session = request.getSession();
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (List<String>) request.getAttribute("errorList");
		this.request = request;
	}

	/**
	 * 增加一条事件
	 * 
	 * @author westion 文件上传 具体步骤： 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 2） 利用
	 *         request 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同 3）对
	 *         DiskFileItemFactory 对象设置一些 属性 4）高水平的API文件上传处理
	 *         ServletFileUploadupload = new ServletFileUpload(factory);
	 *         目的是调用parseRequest（request）方法 获得 FileItem 集合list ， 5）在 FileItem
	 *         对象中 获取信息， 遍历， 判断 表单提交过来的信息 是否是 普通文本信息 另做处理 6） 第一种. 用第三方 提供的
	 *         item.write( new File(path,filename) ); 直接写到磁盘上 第二种. 手动处理
	 * 
	 * @param econtent
	 *            String 事件内容
	 * @param ephoto_url
	 *            String 事件图片
	 * @param etime
	 *            Integer 事件的时间
	 * @param create_time
	 *            Integer 事件发布时间
	 * 
	 * 
	 * */
	public void addEvent() {
		try {
			String filename = FileUploadTool.fileUpload(request, "/"
					+ properties.getProperty("eventPhotoDir"));
			eventService.add((String) request.getAttribute("econtent"),
					properties.getProperty("eventPhotoDir") + filename,
					(String) request.getAttribute("etime"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("uploadError"));
		}

	}

}
