package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;

public interface KeypairAlgorithmDAO {


	/**
	 * 增加密钥算法
	 * @param keypairAlgorithm
	 * @return 
	 */
	int insert(KeyPairAlgorithm keyPairAlgorithm);

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
	 */
	int update(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * 删除密钥算法
	 * @param id
	 * @return 
	 */
	int delete(Long id);

	/**
	 * 根据id查找密钥算法
	 * @param id
	 * @return
	 */
	KeyPairAlgorithm selectById(Long id);
	
	/**
	 * 根据name查找密钥算法
	 * @param keypairAlgorithm
	 * @return
	 */
	KeyPairAlgorithm selectByName(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * @return
	 * 查询已启用的数据
	 */
	List<KeyPairAlgorithm> selectOpYes(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * @return
	 */
	List<KeyPairAlgorithm> searchByCondition(KeyPairAlgorithm keyPairAlgorithm);

	

}
