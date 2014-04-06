package westion.www.utls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * 具体步骤： 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 2） 利用 request 获取 真实路径 ，供临时文件存储，和
 * 最终文件存储 ，这两个存储位置可不同，也可相同 3）对 DiskFileItemFactory 对象设置一些 属性 4）高水平的API文件上传处理
 * ServletFileUploadupload = new ServletFileUpload(factory);
 * 目的是调用parseRequest（request）方法 获得 FileItem 集合list ， 5）在 FileItem 对象中 获取信息， 遍历，
 * 判断 表单提交过来的信息 是否是 普通文本信息 另做处理 6） 第一种. 用第三方 提供的 item.write( new
 * File(path,filename) ); 直接写到磁盘上 第二种. 手动处理
 */
public class FileTool {

	/**
	 * @param request
	 *            HttpServletRequest
	 * @param dir
	 *            String 上传文件存放的位置
	 * @return filename String 上传文件的地址，相对根目录
	 * */
	public static String fileUpload(HttpServletRequest request, String dir)
			throws Exception {
		String filename = null;

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String path = request.getServletContext().getRealPath(dir);
			factory.setRepository(new File(path));
			// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
			factory.setSizeThreshold(1024 * 1024);

			// 高水平的API文件上传处理
			ServletFileUpload upload = new ServletFileUpload(factory);
			// upload.setHeaderEncoding("UTF-8");
			// 可以上传多个文件
			System.out.println(upload.getHeaderEncoding());
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

			for (FileItem item : list) {
				// 获取表单的属性名字
				String name = item.getFieldName();

				// 如果获取的 表单信息是普通的 文本 信息
				if (item.isFormField()) {
					// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = item.getString("UTF-8");
					System.out.println(value);
					request.setAttribute(name, value);
				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else {
					String value = item.getName();
					// 不带文件
					if (value == null || value.equals("")) {
						continue;
					}
					// 索引到最后一个反斜杠
					int start = value.lastIndexOf(".");
					String type = value.substring(start); // 带一个点

					// 用时间和session编码，保证唯一性
					String encodeToString = Base64.encodeBase64String((request
							.getSession().getId() + new Date().getTime())
							.getBytes());
					filename = encodeToString + type;
					// 真正写到磁盘上
					// 它抛出的异常 用exception 捕捉
					// item.write( new File(path,filename) );//第三方提供的
					// 手动写的
					File file = new File(path, filename);
					OutputStream out = new FileOutputStream(file);
					InputStream in = item.getInputStream();

					int length = 0;
					byte[] buf = new byte[1024];

					// in.read(buf) 每次读到的数据存放在 buf 数组中
					while ((length = in.read(buf)) != -1) {
						// 在 buf 数组中 取出数据 写到 （输出流）磁盘上
						out.write(buf, 0, length);

					}
					in.close();
					out.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return filename;

	}

	/**
	 * @param filePath
	 *            String 文件的路径
	 * @return boolean 文件删除是否成功
	 * */
	public static void deleteFile(String filePath) {
		try {
			File file = new File(filePath);
			file.delete();
		} catch (Exception e) {
			throw e;
		}

	}
}
