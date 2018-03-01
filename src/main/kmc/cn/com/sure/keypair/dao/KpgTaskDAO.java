/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.kpgtask.entry.KpgTask;

/**
 * @author Limin
 *
 */
public interface KpgTaskDAO {

	/**
	 * @return
	 */
	List<KpgTask> selectAll();

	/**
	 * @param name
	 * @return
	 */
	KpgTask findByName(String name);

	/**
	 * @param kpgTask
	 * @return 
	 */
	int update(KpgTask kpgTask);

	/**
	 * @param id
	 */
	void delete(Long id);

	/**
	 * @param kpgTask
	 * @return 
	 */
	int insert(KpgTask kpgTask);

	/**
	 * 
	 */
	KpgTask findById(Long id);

	/**
	 * @return
	 */
	List<KpgTask> findAllUnExecutedTask(KpgTask kpgTask);

	/**
	 * @param codeId
	 * @return
	 */
	List<KpgTask> findByTaskStatus(Long codeId);

	/**
	 * @param kpgTask
	 * @return
	 */
	List<KpgTask> searchByCondition(KpgTask kpgTask);

	/**
	 * @param kpgTask
	 */
	void start(KpgTask kpgTask);

	/**
	 * @param id
	 */
	void suspend(Long id);

	/**
	 * @param id
	 */
	void stop(Long id);

	/**
	 * @param id
	 */
	void continuation(Long id);

}
