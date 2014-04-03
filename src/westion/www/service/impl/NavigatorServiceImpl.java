package westion.www.service.impl;

import java.util.List;

import westion.www.dao.NavigatorDao;
import westion.www.dao.impl.NavigatorDaoImpl;
import westion.www.entity.Navigator;
import westion.www.service.NavigatorService;

public class NavigatorServiceImpl implements NavigatorService {

	/** Dao���Ӧ����ʵ�� {@link NavigatorDaoImpl#NavigatorDaoImpl()} */
	private NavigatorDao navigatorDao = new NavigatorDaoImpl();
	
	@Override
	public List<Navigator> list() {
		return navigatorDao.list();
	}

}
