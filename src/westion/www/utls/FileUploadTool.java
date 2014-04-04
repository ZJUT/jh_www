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

public class FileUploadTool {

	public static String fileUpload(HttpServletRequest request, String dir)
			throws Exception {
		String filename = null;
		try {
			request.setCharacterEncoding("utf-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String path = request.getServletContext().getRealPath(dir);

			factory.setRepository(new File(path));
			// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
			factory.setSizeThreshold(1024 * 1024);

			// 高水平的API文件上传处理
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 可以上传多个文件
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

			for (FileItem item : list) {
				// 获取表单的属性名字
				String name = item.getFieldName();

				// 如果获取的 表单信息是普通的 文本 信息
				if (item.isFormField()) {
					// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = item.getString();

					request.setAttribute(name, value);
				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else {
					String value = item.getName();
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

					System.out.println("获取上传文件的总共的容量：" + item.getSize());

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
			throw e;
		}
		return filename;

	}
}
