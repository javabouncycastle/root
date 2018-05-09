/**
 * 
 */
package cn.com.sure.cert.service;

import java.util.List;

import cn.com.sure.cert.entry.CertificateRequest;
import cn.com.sure.common.Applicationexception;

/**
 * @author Limin
 *
 */
public interface CertificateRequestService {

	/**
	 * @param raCertificateRequest
	 * @return
	 */
	int insert(CertificateRequest raCertificateRequest)throws Applicationexception;

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

	void check(CertificateRequest raCertificateRequest);

	List<CertificateRequest> findByCondition();

}
