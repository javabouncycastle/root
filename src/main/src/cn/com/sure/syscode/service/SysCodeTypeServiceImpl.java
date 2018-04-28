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

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.ErrorMessageConstants;
import cn.com.sure.syscode.dao.SysCodeDAO;
import cn.com.sure.syscode.dao.SysCodeTypeDAO;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.entry.SysCodeType;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("SysCodeTypeService")
public class SysCodeTypeServiceImpl implements SysCodeTypeService {
	
	private static final Log LOG = LogFactory.getLog(SysCodeTypeServiceImpl.class);
	
	@Autowired
	private SysCodeTypeDAO sysCodeTypeDAO;
	
	@Autowired
	private SysCodeDAO sysCodeDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#insert(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public int insert(SysCodeType sysCodeType) throws Applicationexception {
		LOG.debug("insert - start");
		SysCodeType dbSysCodeType = sysCodeTypeDAO.findByType(sysCodeType.getParaType());
		int i = 0;
		if(dbSysCodeType==null){
			i = sysCodeTypeDAO.insert(sysCodeType);
		}if(dbSysCodeType!=null){
			Applicationexception.throwException(ErrorMessageConstants.paraTypeValueExist, new String[]{sysCodeType.getParaType()});
		}
		LOG.debug("insert - start");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#update(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public int update(SysCodeType sysCodeType) throws Applicationexception {
		LOG.debug("update - start");
		SysCodeType codeType = new SysCodeType();
		codeType.setId(sysCodeType.getId());
		codeType.setParaType(sysCodeType.getParaType());
		
		List<SysCodeType> sysCodeTypes = sysCodeTypeDAO.searchByCondition(codeType);
		int i = 0;
		if((sysCodeTypes.size()==0)) {
			i = sysCodeTypeDAO.update(sysCodeType);
		}else {
			Applicationexception.throwException(ErrorMessageConstants.paraTypeValueExist, new String[]{sysCodeType.getParaType()});
		}
		
		
		LOG.debug("update - start");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id) throws Applicationexception{
		LOG.debug("delete - start");
		//删除之前先查询一下这个数据字典类型是否在用，如果在用，则不允许删除
		SysCodeType codeType = new SysCodeType();
		codeType.setId(id);
		SysCode sysCode = new SysCode();
		sysCode.setParaType(codeType);
		List<SysCode> syscodes = sysCodeDAO.searchByCondition(sysCode);
		int i =0;
		if(syscodes.size() != 0) {
			Applicationexception.throwException(ErrorMessageConstants.sysCodeTypeInuse, new String[]{id.toString()});
		}else {
			i = sysCodeTypeDAO.delete(id);
		}
		LOG.debug("delete - start");
		return i;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#selectAll(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public List<SysCodeType> selectAll() {
		LOG.debug("selectAll - start");
		List<SysCodeType> sysCodeTypes = sysCodeTypeDAO.selectAll();
		LOG.debug("selectAll - end");
		return sysCodeTypes;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#searchByCondition(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public List<SysCodeType> searchByCondition(SysCodeType sysCodeType) {
		LOG.debug("searchByCondition - start");
		List<SysCodeType> sysCodeTypes = sysCodeTypeDAO.searchByCondition(sysCodeType);
		LOG.debug("searchByCondition - end");
		return sysCodeTypes;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#selectById(java.lang.Long)
	 */
	@Override
	public SysCodeType selectById(Long id) {
		LOG.debug("selectById - start");
		SysCodeType sysCodeTypes = sysCodeTypeDAO.selectById(id);
		LOG.debug("selectById - end");
		return sysCodeTypes;
	}

}
