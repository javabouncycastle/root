/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.com.sure.keypair.entry.KeypairArchive;

/**
 * @author Limin
 *
 */
public interface KeypairArchiveDAO {

	/**
	 * @return 
	 * 
	 */
	public List<KeypairArchive> selectAll();

	/**
	 * @param kpArchive
	 */
	public void insert(KeypairArchive kpArchive);

	/**
	 * @param certSn
	 * @return 
	 */
	public KeypairArchive findBySn(String certSn);

	/**
	 * @param data
	 * @return
	 */
	public List<KeypairArchive> seleExpireKp(Data data);


}
