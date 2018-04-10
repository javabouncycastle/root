package cn.com.sure.log.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.log.dao.AuditOpLogDAO;
import cn.com.sure.log.entry.AuditOpLog;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value="auditOpLogService")
public class AuditOpLogServiceImpl implements AuditOpLogService{
	
	private static final Log LOG = LogFactory.getLog(AuditOpLogServiceImpl.class);
	
	@Autowired
	private AuditOpLogDAO auditOpLogDAO;


	/* (non-Javadoc)
	 * @see cn.com.sure.log.service.AuditOpLogService#selectAll()
	 */
	@Override
	public List<AuditOpLog> selectAll() {
		LOG.debug("selectAll - start");
		List<AuditOpLog> auditOpLogs = auditOpLogDAO.selectAll();
		LOG.debug("selectAll - end");
		return auditOpLogs;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.log.service.AuditOpLogService#searchByCondition(cn.com.sure.log.entry.AuditOpLog)
	 */
	@Override
	public List<AuditOpLog> searchByCondition(AuditOpLog auditOpLog) {
		LOG.debug("searchByCondition - start");
		List<AuditOpLog> auditOpLogs = auditOpLogDAO.searchByCondition(auditOpLog);
		LOG.debug("searchByCondition - start");
		return auditOpLogs;
	}



	/**
	 * 插入日志信息
	 */
	@Override
	public void insert(long type, String action, String actionExt1,
			String actionExt2, String actionExt3, String actionExt4,
			String message, Date timestamp, String ip, String operator,
			Integer isOpSucc) {
		LOG.debug("insert - start");
		AuditOpLog auditOpLog = new AuditOpLog();
		auditOpLog.setType(type);
		auditOpLog.setAction(action);
		auditOpLog.setActionExt1(actionExt1);
		auditOpLog.setActionExt2(actionExt2);
		auditOpLog.setActionExt3(actionExt3);
		auditOpLog.setActionExt4(actionExt4);
		auditOpLog.setMessage(message);
		auditOpLog.setTimestamp(timestamp);
		auditOpLog.setIp(ip);
		auditOpLog.setOperator(operator);
		auditOpLog.setIsOpSucc(isOpSucc);
		
		auditOpLogDAO.insert(auditOpLog);
		
		LOG.debug("insert - end");
	}
	


	/* (non-Javadoc)
	 * @see cn.com.sure.log.service.AuditOpLogService#exportExcel(java.lang.String[], javax.servlet.ServletOutputStream)
	 */
	@Override
	public void exportExcel(String[] titles, ServletOutputStream out,AuditOpLog auditOpLog) throws Exception {
		LOG.debug("exportExcel - start");
		// 第一步，创建一个workbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet hssfSheet = workbook.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow hssfRow = hssfSheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
		//居中样式
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell hssfCell = null;
		for (int i = 0; i < titles.length; i++) {
		hssfCell = hssfRow.createCell(i);//列索引从0开始
		hssfCell.setCellValue(titles[i]);//列名1
		hssfCell.setCellStyle(hssfCellStyle);//列居中显示                
			}
		
		  // 第五步，写入实体数据 
		 List <AuditOpLog> auditOpLogs = auditOpLogDAO.searchByCondition(auditOpLog);
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 if(auditOpLogs != null && !auditOpLogs.isEmpty()){
		     for (int i = 0; i < auditOpLogs.size(); i++) {
		         hssfRow = hssfSheet.createRow(i+1);                
		         AuditOpLog opLog = auditOpLogs.get(i);
		      // 第六步，创建单元格，并设置值
		      String id = opLog.getId();
		      hssfRow.createCell(0).setCellValue(id);
		      
		      String action = opLog.getAction();
		      hssfRow.createCell(1).setCellValue(action);
		      
		      String actioneExt1 = opLog.getActionExt1();
		      hssfRow.createCell(2).setCellValue(actioneExt1);
		      
		      String actioneExt2 = opLog.getActionExt2();
		      hssfRow.createCell(3).setCellValue(actioneExt2);
		      
		      String actioneExt3 = opLog.getActionExt3();
		      hssfRow.createCell(4).setCellValue(actioneExt3);
		      
		      String actioneExt4 = opLog.getActionExt4();
		      hssfRow.createCell(5).setCellValue(actioneExt4);
		      
		      String message = opLog.getMessage();
		      hssfRow.createCell(6).setCellValue(message);
		      
		      Date timestamp = opLog.getTimestamp();
		      hssfRow.createCell(7).setCellValue(sdf.format(timestamp));
		      
		      String ip = opLog.getIp();
		      hssfRow.createCell(8).setCellValue(ip);
		      
		      String operator = opLog.getOperator();
		      hssfRow.createCell(9).setCellValue(operator); 
		      
		      Integer isOpSucc = opLog.getIsOpSucc();
		      hssfRow.createCell(9).setCellValue(isOpSucc); 
		      
		     }
		     
		 }
		      
		      try {
		    	  workbook.write(out);
		    	  out.flush();
		    	  out.close();
		    	  
		    	      } catch (Exception e) {
		    	          e.printStackTrace();
		    	          throw new Exception("");
		    	      }
		    	  
		
		LOG.debug("exportExcel - end");
		 }	     
	 }
