/**
 * 
 */
package cn.com.sure.ctml.service;

import java.util.List;

import cn.com.sure.common.Applicationexception;
import cn.com.sure.common.PagedQuery;
import cn.com.sure.ctml.entry.CertificateTemplate;


/**
 * @author Limin
 *
 */
public interface CertificateTemplateService {

	/**
	 * @param pageQuery 
	 * @return 
	 * 
	 */
	public List<CertificateTemplate> selectAll(PagedQuery pageQuery);

	/**
	 * @param certTemplate
	 * @return
	 */
	public int insert(CertificateTemplate certTemplate)throws  Applicationexception;

	/**
	 * @param certTemplate
	 * @return
	 */
	public int update(CertificateTemplate certTemplate)throws  Applicationexception;

	/**
	 * @param id
	 * @return
	 */
	public int remove(String id);

	/**
	 * @param id
	 * @return
	 */
	public int activite(String id);

	/**
	 * @param id
	 * @return
	 */
	public int suspend(String id);

	/**
	 * @return
	 */
	public List<CertificateTemplate> selectStandby();

	public List<CertificateTemplate> searchByCondition(CertificateTemplate ctml);

	public int count();

	public CertificateTemplate findById(String id);
}
