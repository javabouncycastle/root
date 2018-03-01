/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.Date;
import java.util.List;

import cn.com.sure.keypair.entry.KeypairInuse;

/**
 * @author Limin
 *
 */
public interface KeypairInuseService {

	/**
	 * @return
	 */
	List<KeypairInuse> selectAll();


	/**
	 * @param inuse
	 */
	void insert(KeypairInuse inuse);

	/**
	 * @param kpInuse
	 * @return
	 */
	KeypairInuse findBySn(KeypairInuse kpInuse);

	/**
	 * @param id
	 */
	void delete(String id);


	/**
	 * @param data
	 * @return
	 */
	List<KeypairInuse> seleExpireKpg(Date date);


	/**
	 * 
	 */
	void executeAutoArchKpg();


	/**
	 * @param keypairInuse
	 * @return
	 */
	List<KeypairInuse> searchByCondition(
			KeypairInuse keypairInuse);

}
