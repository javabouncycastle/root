package cn.com.sure.algorthm.service;

import java.util.List;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.common.KmApplicationexception;

public interface KeypairAlgorithmService {
	
	/**
	 * 增加密钥算法
	 * @param keypairAlgorithm
	 * @return 
	 * @throws KmApplicationexception
	 */
	int insert(KeyPairAlgorithm keyPairAlgorithm) throws  KmApplicationexception;

	/**
	 * 查询全部密钥算法
	 * @param keypairAlgorithm
	 * @return
	 */
	List<KeyPairAlgorithm> selectAll();

	/**
	 * 更新密钥算法
	 * @param keypairAlgorithm
	 * @return 
	 * @throws KmApplicationexception 
	 */
	int update(KeyPairAlgorithm keyPairAlgorithm) throws KmApplicationexception;
	
	/**
	 * 删除密钥算法
	 * @param id
	 * @return 
	 */
	int delete(Long id);

	/**
	 * 停用密钥算法
	 * @param id
	 */
	void suspend(Long id);

	/**
	 * 启用密钥算法
	 * @param id
	 */
	void activate(Long id);

	/**
	 * @param kpgTask 
	 * @param object
	 * @return
	 * 查询启用的数据
	 */
	List<KeyPairAlgorithm> selectOpYes(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * @param keypairAlgorithm
	 * @return
	 */
	List<KeyPairAlgorithm> searchByCondition(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * @param id
	 * @return
	 */
	KeyPairAlgorithm selectById(Long id);


}
