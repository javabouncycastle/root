/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.com.sure.keypair.entry.KeypairArchive;

/**
 * @author Limin
 *
 */
public interface KeypairArchiveService {

	/**
	 * 
	 */
	List <KeypairArchive> selectAll();

	/**
	 * @param kpArchive
	 */
	void insert(KeypairArchive kpArchive);

	/**
	 * @param certSn
	 * @return 
	 */
	KeypairArchive findBySn(String certSn);

	/**
	 * @param data
	 * @return
	 */
	List<KeypairArchive> seleExpireKp(Data data);

}
