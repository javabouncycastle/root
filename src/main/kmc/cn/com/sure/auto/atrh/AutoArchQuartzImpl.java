/**
 * 
 */
package cn.com.sure.auto.atrh;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.AutoArchThread;
import cn.com.sure.keypair.dao.KeypairInuseDAO;
import cn.com.sure.keypair.entry.KeypairInuse;
import cn.com.sure.keypair.service.KeypairInuseService;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("autoArchQuartz")
public class AutoArchQuartzImpl implements AutoArchQuartz{
	
	private static final Log LOG = LogFactory.getLog(AutoArchQuartzImpl.class);
	
	
	@Autowired
	private KeypairInuseDAO inuseDAO;
	@Autowired
	private KeypairInuseService inuseService;
	
	@Override
	public int countArchNum() {
		LOG.debug("countArchNum - start");
		KeypairInuse inuse = new KeypairInuse();
		inuse.setEndTime(new Date());
		int num = inuseDAO.countArchNum(inuse);
		if(num!=0){
			new Thread(new AutoArchThread(inuseService)).start();
		}
		LOG.debug("countArchNum - end");
		return num;
	}
	
}
