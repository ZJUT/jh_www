package westion.www.utls;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import westion.www.exception.DataSourceInitError;
import westion.www.exception.GetObjectException;

/**
 * 
 * 数据库通用操作的封装的类，被上层调用
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public final class JdbcUtls {

	/** 数据源 */
	private static DataSource myDataSource = null;

	/**
	 * 数据源的初始化，从配置文件读取信息。网站发布后配置文件的路径是在WEB-INF里的lib
	 * 
	 * @throws DataSourceInitError
	 *             数据源初始化错误
	 * */
	static {
		InputStream in = null;
		try {
			in = JdbcUtls.class.getResourceAsStream("/dbconfig.properties");
			Properties properties = new Properties();
			properties.load(in);
			myDataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataSourceInitError(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/** 私有化构造方法，不让在别的地方私有化 */
	private JdbcUtls() {
	}

	/**
	 * 通过数据源获取数据库的链接
	 * 
	 * @return Connection
	 * */
	public static Connection getConnection() throws SQLException {
		return myDataSource.getConnection();
	}

	/**
	 * 通过数据源关闭数据连接(其实数据源是将连接返回连接池),并关闭Statement和ResultSet流
	 * 
	 * @param conn
	 *            Connection
	 * @param st
	 *            Statement
	 * @param rs
	 *            ResultSet
	 * */
	public static void close(Connection conn, Statement st, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (st != null)
					try {
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						if (conn != null)
							try {
								conn.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
			}
	}

	/**
	 * 将查询获得结果封装为对象，并关闭一次查询的流 通过数据表的属性名与实体类的成员名进行内省生成对象
	 * 
	 * @param conn
	 *            Connection
	 * @param st
	 *            Statement
	 * @param rs
	 *            ResultSet
	 * @param clz
	 *            Class 用于反射对象提供类型
	 * @return List<Object> 查询结果封装成Object列表
	 * @throws GetObjectException
	 *             不能从数据库封装到对象
	 * */
	public static List<Object> GetObjects(Connection connection, Statement st,
			ResultSet rs, Class<?> clz) throws Exception {

		// 存封装的对象
		List<Object> objects = new ArrayList<Object>();
		// 存放反射的方法
		Vector<Method> methods = new Vector<Method>();
		try {
			// 结果的元数据，主要是数据表的列名
			ResultSetMetaData pmd = rs.getMetaData();
			int count = pmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				PropertyDescriptor pd = new PropertyDescriptor(
						pmd.getColumnName(i), clz);
				methods.add(pd.getWriteMethod());
			}
			while (rs.next()) {
				Object object = clz.newInstance();
				for (int i = 1; i <= count; i++) {
					methods.get(i - 1).invoke(object, rs.getObject(i));
				}
				objects.add(object);
			}
			return objects;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GetObjectException(e);
		}
	}
}
