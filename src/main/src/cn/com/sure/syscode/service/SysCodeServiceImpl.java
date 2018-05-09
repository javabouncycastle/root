package cn.com.sure.syscode.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.Constants;
import cn.com.sure.common.ErrorMessageConstants;
import cn.com.sure.common.PagedQuery;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.syscode.dao.SysCodeDAO;
import cn.com.sure.syscode.entry.SysCode;

@Transactional(propagation = Propagation.REQUIRED)
@Service("SysCodeService")
public class SysCodeServiceImpl implements SysCodeService{
	
	private static final Log LOG = LogFactory.getLog(SysCodeServiceImpl.class);
	
	@Autowired
	private SysCodeDAO sysCodeDAO;
	
	@Autowired
	private KpgTaskService kpgTaskService;

	@Override
	public int insert(SysCode sysCode)
			throws Applicationexception {
		LOG.debug("insert - start");
		SysCode dbSysCode = sysCodeDAO.findByName(sysCode);
		int i = 0 ;
		if(dbSysCode==null){
			i =sysCodeDAO.insert(sysCode);
		}if(dbSysCode!=null){
			Applicationexception.throwException(ErrorMessageConstants.paraValueExist, new String[]{sysCode.getParaValue()});
		}
		
		LOG.debug("insert - end");
		return i;
	}

	@Override
	public int update(SysCode sysCode) throws  Applicationexception{
		LOG.debug("update - start");
		//判断表里边原来是否有一个这样名字的数据
		SysCode sysCodes = new SysCode();
		sysCodes.setParaCode(sysCode.getParaCode());
		int i = 0;
		List<SysCode> dbSysCode= sysCodeDAO.searchByCondition(sysCodes);
		if(sysCode.getId()==dbSysCode.get(0).getId()) {
			i =sysCodeDAO.update(sysCode);
		}else {
			Applicationexception.throwException(ErrorMessageConstants.paraValueExist, new String[]{sysCode.getParaValue()});
		}
		
		LOG.debug("update - end");
		return i;
	}

	@Override
	public int remove(Long id)  throws Applicationexception{
		LOG.debug("remove - start");
		//1判断这个数据字典是都在用，如果没有在用可以删除，如果有，则不允许删除
		//1.2判断密钥任务中任务状态这个字段是否在用
		//这里有bug
		KpgTask kpgTask = new KpgTask();
		SysCode sysCodes = sysCodeDAO.findById(id);
		kpgTask.setTaskStatus(sysCodes);
		List<KpgTask> kpgTsaks= kpgTaskService.searchByCondition(kpgTask);
		if(kpgTsaks.size()!=0) {
			Applicationexception.throwException(ErrorMessageConstants.sysCodeInuseIntaskStatus, new String[]{id.toString()});
		}
		//1.3判断密钥任务中缓存数量这个字段是否在用
		KpgTask kpgtasks = new KpgTask();
		kpgtasks.setDbCommitBufsize(sysCodes);
		List<KpgTask> dbKpgTsaks= kpgTaskService.searchByCondition(kpgTask);
		if(dbKpgTsaks.size()!=0) {
			Applicationexception.throwException(ErrorMessageConstants.sysCodeInuseIntaskBuff, new String[]{id.toString()});
		}
		//执行删除方法
		int i = sysCodeDAO.delete(id);
		
		LOG.debug("remove - end");
		return i;
	}

	@Override
	public int suspend(Long id) {
		LOG.debug("suspend - start");
		SysCode sysCode = sysCodeDAO.findById(id);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_NO);
		int i = sysCodeDAO.updateValid(sysCode);
		LOG.debug("suspend - end");
		return i;
	}

	@Override
	public int activate(Long id) {
		LOG.debug("activate - start");
		SysCode sysCode = sysCodeDAO.findById(id);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		int i = sysCodeDAO.updateValid(sysCode);
		LOG.debug("activate - start");
		return i;
	}

	@Override
	public List<SysCode> selectAll(PagedQuery pageVo) {
		LOG.debug("selectAll - start");
		List<SysCode> sysCodes = sysCodeDAO.selectAll(pageVo);
		LOG.debug("selectAll - end");
		return sysCodes;
	}

	@Override
	public List<SysCode> selectByType(SysCode sysCode) {
		LOG.debug("selectByType - start");
		List<SysCode> sysCodes = this.sysCodeDAO.searchByCondition(sysCode);
		LOG.debug("selectByType - end");
		return sysCodes;
	}

	@Override
	public List<SysCode> searchByCondition(SysCode sysCode) {
		LOG.debug("searchByCondition - start");
		List<SysCode> sysCodes = sysCodeDAO.searchByCondition(sysCode);
		LOG.debug("searchByCondition - end");
		
		return sysCodes;
		
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#selectById(java.lang.Long)
	 */
	@Override
	public SysCode selectById(Long id) {
		LOG.debug("selectById - start");
		SysCode sysCodes = sysCodeDAO.findById(id);
		LOG.debug("selectById - end");
		return sysCodes;
	}


	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#getServicePort()
	 */
/*	@Override
	public List<SysCode> selectServicePort() {
		LOG.debug("getServicePort - start");
		SysCodeType sysCodeType = new SysCodeType();
		sysCodeType.setParaType(Constants.TYPE_ID_TASK_STATUS);
		SysCode sysCode = new SysCode();
		sysCode.setParaType(sysCodeType);
		sysCode.setIsValid(Constants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodes = this.sysCodeDAO.findByType(sysCode);
		LOG.debug("getServicePort - start");
		return sysCodes;
	}*/

	@Override
	public int getSysCodeCount() {
		LOG.debug("getSysCodeCount - start");
		int count = sysCodeDAO.getSysCodeCount();
		LOG.debug("getSysCodeCount - end");
		return count;
	}

}
