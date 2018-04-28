package cn.com.sure.algorthm.service;

import java.util.List;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.common.Applicationexception;

public interface KeypairAlgorithmService {
	
	/**
	 * 增加密钥算法
	 * @param keypairAlgorithm
	 * @return 
	 * @throws Applicationexception
	 */
	int insert(KeyPairAlgorithm keyPairAlgorithm) throws  Applicationexception;

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
	 * @throws Applicationexception 
	 */
	int update(KeyPairAlgorithm keyPairAlgorithm) throws Applicationexception;
	
	/**
	 * 删除密钥算法
	 * @param id
	 * @return 
	 */
	int delete(Long id);

	/**
	 * 停用密钥算法
	 * @param id
	 * @return 
	 */
	int suspend(Long id);

	/**
	 * 启用密钥算法
	 * @param id
	 */
	int activate(Long id);

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
