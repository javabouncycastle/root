/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.keypair.entry.KeypairInuse;

/**
 * @author Limin
 *
 */
public interface KeypairInuseDAO {

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
	 * @return
	 */
	int countArchNum(KeypairInuse inuse);

	/**
	 * @param data
	 * @return
	 */
	List<KeypairInuse> seleExpireKpg(KeypairInuse inuse);

	/**
	 * @param keypairInuse
	 * @return
	 */
	List<KeypairInuse> searchByCondition(KeypairInuse keypairInuse);

}
