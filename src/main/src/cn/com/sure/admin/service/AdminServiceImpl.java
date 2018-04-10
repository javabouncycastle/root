/**
 * 
 */
package cn.com.sure.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.admin.dao.AdminDAO;
import cn.com.sure.admin.entry.Admin;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("AdminService")
public class AdminServiceImpl implements AdminService{
	
	private static final Log LOG = LogFactory.getLog(AdminServiceImpl.class);
	
	@Autowired
	AdminDAO adminDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.commom.service.AdminService#fingByAdmId(int)
	 */
	@Override
	public Admin fingByAdmId(int adminAuthNum) {
		LOG.debug("fingByAdmId - start");
		Admin admin = adminDAO.fingByAdmId(adminAuthNum);
		LOG.debug("fingByAdmId - end");
		return admin;
	}

}
