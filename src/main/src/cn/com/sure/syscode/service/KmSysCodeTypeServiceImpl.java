/**
 * 
 */
package cn.com.sure.syscode.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.common.KmErrorMessageConstants;
import cn.com.sure.syscode.dao.KmSysCodeTypeDAO;
import cn.com.sure.syscode.entry.KmSysCodeType;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("SysCodeTypeService")
public class KmSysCodeTypeServiceImpl implements KmSysCodeTypeService {
	
	private static final Log LOG = LogFactory.getLog(KmSysCodeTypeServiceImpl.class);
	
	@Autowired
	private KmSysCodeTypeDAO sysCodeTypeDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#insert(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public int insert(KmSysCodeType sysCodeType) throws KmApplicationexception {
		LOG.debug("insert - start");
		KmSysCodeType dbSysCodeType = sysCodeTypeDAO.findByType(sysCodeType.getParaType());
		int i = 0;
		if(dbSysCodeType==null){
			i = sysCodeTypeDAO.insert(sysCodeType);
		}if(dbSysCodeType!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.paraTypeValueExist, new String[]{sysCodeType.getParaType()});
		}
		LOG.debug("insert - start");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#update(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public int update(KmSysCodeType sysCodeType) {
		LOG.debug("update - start");
		int i = sysCodeTypeDAO.update(sysCodeType);
		LOG.debug("update - start");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id) {
		LOG.debug("delete - start");
		int i = sysCodeTypeDAO.delete(id);
		LOG.debug("delete - start");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#selectAll(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public List<KmSysCodeType> selectAll(KmSysCodeType sysCodeType) {
		LOG.debug("selectAll - start");
		List<KmSysCodeType> sysCodeTypes = sysCodeTypeDAO.selectAll(sysCodeType);
		LOG.debug("selectAll - end");
		return sysCodeTypes;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#searchByCondition(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public List<KmSysCodeType> searchByCondition(KmSysCodeType sysCodeType) {
		LOG.debug("searchByCondition - start");
		List<KmSysCodeType> sysCodeTypes = sysCodeTypeDAO.searchByCondition(sysCodeType);
		LOG.debug("searchByCondition - end");
		return sysCodeTypes;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#selectById(java.lang.Long)
	 */
	@Override
	public KmSysCodeType selectById(Long id) {
		LOG.debug("selectById - start");
		KmSysCodeType sysCodeTypes = sysCodeTypeDAO.selectById(id);
		LOG.debug("selectById - end");
		return sysCodeTypes;
	}

}
