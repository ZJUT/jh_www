package westion.www.service.impl;

import java.util.List;

import westion.www.dao.NavigatorDao;
import westion.www.dao.impl.NavigatorDaoImpl;
import westion.www.entity.Navigator;
import westion.www.service.NavigatorService;

public class NavigatorServiceImpl implements NavigatorService {

	/** Dao层对应操作实例 {@link NavigatorDaoImpl#NavigatorDaoImpl()} */
	private NavigatorDao navigatorDao = new NavigatorDaoImpl();
	
	
	/**
	 * 获取所有导航条目
	 * 
	 * @return navigators List\<Navigator\>
	 */
	@Override
	public List<Navigator> list() {
		return navigatorDao.list();
	}

	/**
	 * 增加一条导航条目
	 * 
	 * @param naname String
	 * @param destination_url String
	 */
	@Override
	public void add(String naname, String destination_url) {
		navigatorDao.add(naname, destination_url);
		
	}

	/**
	 * 删除一条导航条目
	 * 
	 * @param id Integer
	 * 
	 */
	@Override
	public void delete(Integer id) {
		navigatorDao.delete(id);
	}

	/**
	 * 更新一条导航条目
	 * 
	 * @param id Integer
	 * @param naname String
	 * @param destination_url String
	 */
	@Override
	public void update(Integer id, String naname, String destination_url) {
		navigatorDao.update(id, naname, destination_url);
	}
	
	/**
	 * 获取一条导航条目,通过id
	 * 
	 * @param id Integer
	 */
	@Override
	public Navigator findById(Integer id) {
		Navigator navigator = navigatorDao.findById(id);
		return navigator;
	}

}
