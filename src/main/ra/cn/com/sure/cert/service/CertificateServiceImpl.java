package cn.com.sure.cert.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.cert.entry.Certificate;

@Transactional(propagation = Propagation.REQUIRED)
@Service("RaCertService")
public class CertificateServiceImpl implements CertificateService{

	@Override
	public void insert(Certificate raCert) {
		// TODO Auto-generated method stub
		
	}

}
