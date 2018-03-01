/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.keypair.dao.KeypairInuseDAO;
import cn.com.sure.keypair.entry.KeypairArchive;
import cn.com.sure.keypair.entry.KeypairInuse;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("keypairInuseService")
public class KeypairInUseServiceImpl implements KeypairInuseService{
	
	private static final Log LOG = LogFactory.getLog(KeypairInuseService.class);
	
	@Autowired
	private KeypairInuseDAO keyPairInUseDAO;
	@Autowired
	private KeypairArchiveService archiveService;

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeyPairInUseService#selectAll()
	 */
	@Override
	public List<KeypairInuse> selectAll() {
		LOG.debug("selectAll - start");
		List<KeypairInuse> keyPairInUses = keyPairInUseDAO.selectAll();
		LOG.debug("selectAll - start");
		return keyPairInUses;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairInuseService#insert(cn.com.sure.kpg.entry.KeypairInuse)
	 */
	@Override
	public void insert(KeypairInuse inuse) {
		LOG.debug("insert - start");
		keyPairInUseDAO.insert(inuse);
		LOG.debug("insert - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairInuseService#findBySn(cn.com.sure.kpg.entry.KeypairInuse)
	 */
	@Override
	public KeypairInuse findBySn(KeypairInuse kpInuse) {
		LOG.debug("findBySn - start");
		KeypairInuse inuse = keyPairInUseDAO.findBySn(kpInuse);
		LOG.debug("findBySn - end");
		return inuse;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairInuseService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		LOG.debug("delete - start");
		keyPairInUseDAO.delete(id);
		LOG.debug("delete - end");
	}



	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairInuseService#seleExpireKpg(com.mysql.fabric.xmlrpc.base.Data)
	 */
	@Override
	public List<KeypairInuse> seleExpireKpg(Date date) {
		LOG.debug("seleExpireKpg - start");
		KeypairInuse inuse = new KeypairInuse();
		inuse.setEndTime(date);
		List<KeypairInuse> keyPairInUses = keyPairInUseDAO.seleExpireKpg(inuse);
		LOG.debug("seleExpireKpg - end");
		return keyPairInUses;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairInuseService#executeAutoArchKpg()
	 */
	@Override
	public void executeAutoArchKpg() {
		LOG.debug("executeAutoArchKpg - start");
		//1.查询出来到期密钥
			List<KeypairInuse> list = seleExpireKpg(new Date());
			for(KeypairInuse inuse:list){
				
				//1.1将查询出来的在用密钥复制到历史密钥中
				KeypairArchive archive = new KeypairArchive();
				archive.setPriKey(inuse.getPriKey());
				archive.setPubKey(inuse.getPubKey());
				archive.setKeyPairAlgorithm(inuse.getKeyPairAlgorithm());
				archive.setKpgTask(inuse.getKpgTask());
				archive.setGenTime(inuse.getGenTime());
				archive.setArchTime(new Date());
				archive.setCertDn(inuse.getCertDn());
				archive.setCertSn(inuse.getCertSn());
				archive.setEndTime(inuse.getEndTime());
				archive.setStartTime(inuse.getStartTime());
				
				//1.2插入历史密钥中
				archiveService.insert(archive);
				
				//1.3删除在用表中的那条信息
				delete(inuse.getId());
				
			}
		LOG.debug("executeAutoArchKpg - end");		
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairInuseService#searchByCondition(cn.com.sure.kpg.entry.KeypairInuse)
	 */
	@Override
	public List<KeypairInuse> searchByCondition(KeypairInuse keypairInuse) {
		LOG.debug("searchByCondition - start");
		List<KeypairInuse> keypairInuses = keyPairInUseDAO.searchByCondition(keypairInuse);
		LOG.debug("searchByCondition - end");
		return null;
	}

}
