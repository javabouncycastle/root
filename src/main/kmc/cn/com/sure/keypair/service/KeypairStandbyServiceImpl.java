/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.keypair.dao.KeyPairStandbyDAO;
import cn.com.sure.keypair.entry.KeypairStandby;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("keyPairStandbyService")
public class KeypairStandbyServiceImpl implements KeypairStandbyService{
	
	private static final Log LOG = LogFactory.getLog(KeypairStandbyServiceImpl.class);
	
	@Autowired
	private KeyPairStandbyDAO keyPairStandbyDAO;
	


	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairStandbyService#insert()    
	 */
	@Override
	public void insert(KeypairStandby keyPairStandby) {
		LOG.debug("insert - start");
		this.keyPairStandbyDAO.insert(keyPairStandby);
		LOG.debug("insert - end");
	}



	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeyPairStandbyService#selectAll()
	 */
	@Override
	public List<KeypairStandby> selectAll() {
		LOG.debug("selectAll - start");
		List<KeypairStandby> keyPairStandbys = keyPairStandbyDAO.selectAll();
		LOG.debug("selectAll - end");
		return keyPairStandbys;
	}




	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairStandbyService#countNum(java.lang.String)
	 */
	@Override
	public int countNum(String algorithmName) {
		LOG.debug("countNum - start");
		int keyNum = keyPairStandbyDAO.countNum(algorithmName);
		LOG.debug("countNum - end");
		return keyNum;
	}



	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairStandbyService#obtKpStandby()
	 */
	@Override
	public KeypairStandby obtKpStandby(KeyPairAlgorithm kpAlg) {
		LOG.debug("obtKpStandby - start");
		KeypairStandby kpStandby = keyPairStandbyDAO.obtKpStandby(kpAlg);
		LOG.debug("obtKpStandby - end");
		return kpStandby;
	}



	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairStandbyService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		LOG.debug("obtKpStandby - start");
		keyPairStandbyDAO.delete(id);
		LOG.debug("obtKpStandby - end");
	}



	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairStandbyService#backups(java.lang.Long)
	 */
	@Override
	public void backups(KeypairStandby keypairStandby,ServletOutputStream out) {
		LOG.debug("backups - start");
		
		LOG.debug("backups - start");
		
	}



	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairStandbyService#searchByCondition(cn.com.sure.kpg.entry.KeypairStandby)
	 */
	@Override
	public List<KeypairStandby> searchByCondition(KeypairStandby keypairStandby) {
		LOG.debug("searchByCondition - start");
		List<KeypairStandby> keypairStandbys = keyPairStandbyDAO.searchByCondition(keypairStandby);
		LOG.debug("searchByCondition - end");
		return keypairStandbys;
	}

}
