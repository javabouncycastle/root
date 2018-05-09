/**
 * 
 */
package cn.com.sure.kpgtask.service;

import java.util.List;
import java.util.Map;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.common.PagedQuery;

/**
 * @author Limin
 *
 */
public interface KpgTaskService {

	/**
	 * @return 
	 * 
	 */
	List<KpgTask> selectAll(PagedQuery pagedQuery);

	/**
	 * @param kpgTask
	 * @return 
	 */
	int insert(KpgTask kpgTask)throws Applicationexception;

	/**
	 * @param kpgTask
	 * @return 
	 */
	int update(KpgTask kpgTask);

	/**
	 * @param id
	 */
	int delete(Long id);

	/**
	 * @param id 
	 * @return 
	 * 
	 */
	KpgTask selectById(Long id);

    /**
     * 
     *  功能描述：查询所有需要执行的任务
     *  @param taskStatus
     *  @return
     */
    public List<KpgTask> findAllUnExecutedTask();

	/**
	 * @param codeIdTaskStatusWaitingForExecuting
	 * @return
	 */
	List<KpgTask> findByTaskStatus(Long codeId);

	/**
	 * @param taskId
	 * @param sliceSize
	 */
	void updateGeneratedKeyAmount(Long taskId, int sliceSize);

	/**
	 * @param kpgTask
	 * @return
	 */
	List<KpgTask> searchByCondition(KpgTask kpgTask);

	/**
	 * @param id
	 */
	Map<String,Object> startTask(Long id);

	/**
	 * @param id
	 */
	int stop(Long id);

	/**
	 * @param id
	 */
	Map<String,Object> continuation(Long id);

	int getSysCodeCount();

}
