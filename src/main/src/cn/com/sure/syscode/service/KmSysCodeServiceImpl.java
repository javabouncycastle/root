package cn.com.sure.syscode.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.common.KmConstants;
import cn.com.sure.common.KmErrorMessageConstants;
import cn.com.sure.syscode.dao.KmSysCodeDAO;
import cn.com.sure.syscode.entry.KmSysCode;
import cn.com.sure.syscode.entry.KmSysCodeType;

@Transactional(propagation = Propagation.REQUIRED)
@Service("SysCodeService")
public class KmSysCodeServiceImpl implements KmSysCodeService{
	
	private static final Log LOG = LogFactory.getLog(KmSysCodeServiceImpl.class);
	
	@Autowired
	private KmSysCodeDAO sysCodeDAO;
	

	@Override
	public int insert(KmSysCode sysCode)
			throws KmApplicationexception {
		LOG.debug("insert - start");
		KmSysCode dbSysCode = sysCodeDAO.findByName(sysCode);
		int i = 0 ;
		if(dbSysCode==null){
			i =sysCodeDAO.insert(sysCode);
		}if(dbSysCode!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.paraValueExist, new String[]{sysCode.getParaValue()});
		}
		
		LOG.debug("insert - end");
		return i;
	}

	@Override
	public int update(KmSysCode sysCode) {
		LOG.debug("update - start");
		int i = sysCodeDAO.update(sysCode);
		LOG.debug("update - end");
		return i;
	}

	@Override
	public int remove(Long id) {
		LOG.debug("remove - start");
		int i = sysCodeDAO.delete(id);
		LOG.debug("remove - end");
		return i;
	}

	@Override
	public void suspend(Long id) {
		LOG.debug("suspend - start");
		KmSysCode sysCode = sysCodeDAO.findById(id);
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_NO);
		sysCodeDAO.updateValid(sysCode);
		LOG.debug("suspend - end");
	}

	@Override
	public void activate(Long id) {
		LOG.debug("activate - start");
		KmSysCode sysCode = sysCodeDAO.findById(id);
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		sysCodeDAO.updateValid(sysCode);
		LOG.debug("activate - start");
	}

	@Override
	public List<KmSysCode> selectAll() {
		LOG.debug("selectAll - start");
		List<KmSysCode> sysCodes = sysCodeDAO.selectAll();
		LOG.debug("selectAll - end");
		return sysCodes;
	}

	@Override
	public List<KmSysCode> selectByType(KmSysCode sysCode) {
		LOG.debug("selectByType - start");
		KmSysCodeType sysCodeType = new KmSysCodeType();
		sysCodeType.setParaType(KmConstants.TYPE_ID_TASK_STATUS);
		sysCode.setParaType(sysCodeType);
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		List<KmSysCode> sysCodes = this.sysCodeDAO.findByType(sysCode);
		LOG.debug("selectByType - end");
		return sysCodes;
	}

	@Override
	public List<KmSysCode> searchByCondition(KmSysCode sysCode) {
		LOG.debug("searchByCondition - start");
		List<KmSysCode> sysCodes = sysCodeDAO.serachByContion(sysCode);
		LOG.debug("searchByCondition - end");
		return sysCodes;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#selectById(java.lang.Long)
	 */
	@Override
	public KmSysCode selectById(Long id) {
		LOG.debug("selectById - start");
		KmSysCode sysCodes = sysCodeDAO.findById(id);
		LOG.debug("selectById - end");
		return sysCodes;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#selectMin()
	 */
	@Override
	public List<KmSysCode> selectMin() {
		LOG.debug("selectMin - start");
		KmSysCodeType sysCodeType = new KmSysCodeType();
		sysCodeType.setParaType(KmConstants.KEY_NUM_MIN);
		KmSysCode sysCode = new KmSysCode();
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		sysCode.setParaType(sysCodeType);
		List<KmSysCode> listMin = sysCodeDAO.selectMin(sysCode);
		LOG.debug("selectMin - end");
		return listMin;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#selectBuffer()
	 */
	@Override
	public List<KmSysCode> selectBuffer() {
		LOG.debug("selectMin - start");
		KmSysCodeType sysCodeType = new KmSysCodeType();
		sysCodeType.setParaType(KmConstants.DB_COMMIT_BUFFER);
		KmSysCode sysCode = new KmSysCode();
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		sysCode.setParaType(sysCodeType);
		List<KmSysCode> sysList = sysCodeDAO.selectBuffer(sysCode);
		LOG.debug("selectMin - end");
		return sysList;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#selectGenKeyNum()
	 */
	@Override
	public List<KmSysCode> selectGenKeyNum() {
		LOG.debug("selectMin - start");
		KmSysCodeType sysCodeType = new KmSysCodeType();
		sysCodeType.setParaType(KmConstants.GEN_KEY_NUM);
		KmSysCode sysCode = new KmSysCode();
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		sysCode.setParaType(sysCodeType);
		List<KmSysCode> sysList = sysCodeDAO.selectBuffer(sysCode);
		LOG.debug("selectMin - end");
		return sysList;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#selectBufSize(cn.com.sure.syscode.entry.SysCode)
	 */
	@Override
	public List<KmSysCode> selectBufSize(KmSysCode sysCode) {
		LOG.debug("selectBufSize - start");
		KmSysCodeType sysCodeType = new KmSysCodeType();
		sysCodeType.setParaType(KmConstants.DB_COMMIT_BUFFER);
		sysCode.setParaType(sysCodeType);
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		List<KmSysCode> codeBufSize = sysCodeDAO.findByType(sysCode);
		LOG.debug("selectBufSize - end");
		return codeBufSize;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#getServicePort()
	 */
	@Override
	public List<KmSysCode> selectServicePort() {
		LOG.debug("getServicePort - start");
		KmSysCodeType sysCodeType = new KmSysCodeType();
		sysCodeType.setParaType(KmConstants.TYPE_ID_TASK_STATUS);
		KmSysCode sysCode = new KmSysCode();
		sysCode.setParaType(sysCodeType);
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		List<KmSysCode> sysCodes = this.sysCodeDAO.findByType(sysCode);
		LOG.debug("getServicePort - start");
		return sysCodes;
	}


}
