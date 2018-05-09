/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.keypair.entry.KeypairStandby;


/**
 * @author Limin
 *
 */
public interface KeyPairStandbyDAO {


	/**
	 * @param keyPairStandby
	 */
	void insert(KeypairStandby keyPairStandby);


	/**
	 * @return
	 */
	List<KeypairStandby> selectAll();


	/**
	 * @return
	 */
	int countNum(KeyPairAlgorithm kpa);


	/**
	 * @return
	 */
	KeypairStandby obtKpStandby(KeyPairAlgorithm kpAlg);


	/**
	 * @param id
	 */
	void delete(String id);


	/**
	 * @param keypairStandby
	 * @return
	 */
	List<KeypairStandby> searchByCondition(KeypairStandby keypairStandby);

}
