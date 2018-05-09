package cn.com.sure.cert.dao;

import java.util.List;

import cn.com.sure.cert.entry.Certificate;

public interface CertificateDAO {

	List<Certificate> searchByCondition(Certificate certificate);
	

}
