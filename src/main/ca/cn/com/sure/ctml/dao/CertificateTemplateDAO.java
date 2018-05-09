package cn.com.sure.ctml.dao;


import java.util.List;

import cn.com.sure.common.PagedQuery;
import cn.com.sure.ctml.entry.CertificateTemplate;



/**
 * @author Limin
 *
 */
public interface CertificateTemplateDAO {

	/**
	 * @return 
	 * 
	 */
	public List<CertificateTemplate> selectAll(PagedQuery pageQuery);

	/**
	 * @param certTemplate
	 * @return
	 */
	public int insert(CertificateTemplate certTemplate);

	/**
	 * @param certTemplate
	 * @return
	 */
	public int update(CertificateTemplate certTemplate);


	/**
	 * @param id
	 * @return
	 */
	public int remove(String id);

	/**
	 * @param id
	 * @return
	 */
	public CertificateTemplate findById(String id);

	/**
	 * @param certTemplate
	 * @return
	 */
	public int updateValid(CertificateTemplate certTemplate);

	/**
	 * @param caCertTemplate 
	 * @return
	 */
	public List<CertificateTemplate> selectStandby(CertificateTemplate caCertTemplate);

	public CertificateTemplate findByName(String ctmlName);

	public List<CertificateTemplate> searchByCondition(CertificateTemplate certificateTemplate);

	public int count();


}
