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
 * ���ݿ�ͨ�ò����ķ�װ���࣬���ϲ����
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public final class JdbcUtls {

	/** ����Դ */
	private static DataSource myDataSource = null;

	/**
	 * ����Դ�ĳ�ʼ�����������ļ���ȡ��Ϣ����վ�����������ļ���·������WEB-INF���lib
	 * 
	 * @throws DataSourceInitError
	 *             ����Դ��ʼ������
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

	/** ˽�л����췽���������ڱ�ĵط�˽�л� */
	private JdbcUtls() {
	}

	/**
	 * ͨ������Դ��ȡ���ݿ������
	 * 
	 * @return Connection
	 * */
	public static Connection getConnection() throws SQLException {
		return myDataSource.getConnection();
	}

	/**
	 * ͨ������Դ�ر���������(��ʵ����Դ�ǽ����ӷ������ӳ�),���ر�Statement��ResultSet��
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
	 * ����ѯ��ý����װΪ���󣬲��ر�һ�β�ѯ���� ͨ�����ݱ����������ʵ����ĳ�Ա��������ʡ���ɶ���
	 * 
	 * @param conn
	 *            Connection
	 * @param st
	 *            Statement
	 * @param rs
	 *            ResultSet
	 * @param clz
	 *            Class ���ڷ�������ṩ����
	 * @return List<Object> ��ѯ�����װ��Object�б�
	 * @throws GetObjectException
	 *             ���ܴ����ݿ��װ������
	 * */
	public static List<Object> GetObjects(Connection connection, Statement st,
			ResultSet rs, Class<?> clz) throws Exception {

		// ���װ�Ķ���
		List<Object> objects = new ArrayList<Object>();
		// ��ŷ���ķ���
		Vector<Method> methods = new Vector<Method>();
		try {
			// �����Ԫ���ݣ���Ҫ�����ݱ������
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
