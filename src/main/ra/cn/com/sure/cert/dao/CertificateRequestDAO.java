/**
 * 
 */
package cn.com.sure.cert.dao;

import java.util.List;

import cn.com.sure.cert.entry.CertificateRequest;

/**
 * @author Limin
 *
 */
public interface CertificateRequestDAO {

	/**
	 * @param raCertificateRequest
	 * @return
	 */
	int insert(CertificateRequest raCertificateRequest);

	/**
	 * @param id
	 * @return
	 */
	CertificateRequest findById(String id);

	/**
	 * @param raCertificateRequest
	 * @return
	 */
	List<CertificateRequest> searchByCondition(CertificateRequest raCertificateRequest);

	List<CertificateRequest> selectAll();

}
