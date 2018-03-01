package cn.com.sure.algorthm.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.common.KmApplicationexception;
import cn.com.sure.common.KmConstants;
import cn.com.sure.log.service.KmAuditOpLogService;

@Controller
@RequestMapping(value="algorithm")
public class KeypairAlgorithmController {

	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmController.class);
	
	@Autowired
	private KeypairAlgorithmService keyPairAlgorithmService;
	
	@Autowired
	private KmAuditOpLogService auditOpLogService;
	
	
	/**
	 * UC-ALG02-01 新增密钥算法
	 */
	@RequestMapping(value="insert")
	public String insert(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try{
			//执行insert操作
			int i = keyPairAlgorithmService.insert(keyPairAlgorithm);
			int result;
			if(i==-1){
				result = KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD;
			}else{
				result = KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
			}
			//添加审计日志
			auditOpLogService.insert(KmConstants.OPERATION_TYPE_INSERT, "增加", "数据字典类别", null,
					keyPairAlgorithm.getAlgorithmName(), null, null, new Date(), getIp(request), (String)request.getSession().getAttribute(KmConstants.SESSION_ADMIN_NAME), 
					result);
		}catch(KmApplicationexception e){
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("keyPairAlgorithm",keyPairAlgorithm);
			return "redirect:/algorithm/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+keyPairAlgorithm.getName()+"】成功");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 * UC- ALG02-06查询所有密钥算法
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		System.out.println(request.getRemoteAddr());
		LOG.debug("selectAll - start");
		List <KeyPairAlgorithm> keyPairAlgorithms = keyPairAlgorithmService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keyPairAlgList").addObject("keyPairAlgorithms", keyPairAlgorithms);
		
	}
	
	/**
	 * UC- ALG02-02修改密钥算法
	 * @throws KmApplicationexception 
	 */
	@RequestMapping(value="update")
	public String update(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request) throws KmApplicationexception{
		LOG.debug("update - start");
		String str = compare(keyPairAlgorithm);
		int i = keyPairAlgorithmService.update(keyPairAlgorithm);
		int result;
		if(i==-1){
			result = KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD;
		}else{
			result = KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
		}
		auditOpLogService.insert(KmConstants.OPERATION_TYPE_UPDATE, "更新", "数据字典类别", keyPairAlgorithm.getId().toString(), null, null, 
				str, new Date(), getIp(request), (String)request.getSession().getAttribute(KmConstants.SESSION_ADMIN_NAME), 
				result);
		
		LOG.debug("update - end");
		attr.addFlashAttribute("updateSuccess","true");
		attr.addFlashAttribute("message","修改主键为【"+keyPairAlgorithm.getId()+"】的信息成功！");
		return "redirect:/algorithm/selectAll.do";
	}
	
	
	/**
	 *  UC- ALG02-03删除密钥算法
	 */
	@RequestMapping(value="remove")
	public String delete(
			@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("delete - start");
		int i = keyPairAlgorithmService.delete(id);
		int result;
		if(i==-1){
			result = KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD;
		}else{
			result = KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS;
		}
		auditOpLogService.insert(KmConstants.OPERATION_TYPE_DELETE, "删除", "数据字典类别", id.toString(), null, null, null, 
				new Date(),getIp(request),  (String)request.getSession().getAttribute(KmConstants.SESSION_ADMIN_NAME), result);
		LOG.debug("delete - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","删除主键为【"+id+"】成功！");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	
	/**
	* UC- ALG02-04停用密钥算法
	* @return "redirect:/list"
	*/
	@RequestMapping(value = "suspend")
	public String suspend(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("suspend - start!");
		this.keyPairAlgorithmService.suspend(id);
        attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","停用主键为【"+id+"】成功");
    	LOG.debug("suspend - end!");
        return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 *  UC- ALG02-05启用密钥算法
	 * @return "redirect:/list"
	 */
	
	
	@RequestMapping(value = "activate")
	public String activate(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("activate - start!");
		this.keyPairAlgorithmService.activate(id);
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","启用主键为【"+id+"】成功");
    	LOG.debug("activate - end!");		
        return "redirect:/algorithm/selectAll.do";
		
	}
	

	/**
	 * UC- ALG02-07按条件查询密钥算法
	 * @param keypairAlgorithm
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchByCondition")
	public ModelAndView searchByCOndition(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCOndition - start");
		List<KeyPairAlgorithm> keyPairAlgorithms = this.keyPairAlgorithmService.searchByCondition(keyPairAlgorithm);
		LOG.debug("searchByCOndition - end");
		return new ModelAndView("algorithm/keyPairAlgList").addObject("keyPairAlgorithms", keyPairAlgorithms);
		
	}
	
	
	

	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
    public  String getIp(HttpServletRequest request) {
           String ip = request.getHeader("X-Forwarded-For");
           if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
               //多次反向代理后会有多个ip值，第一个ip才是真实ip
               int index = ip.indexOf(",");
               if(index != -1){
                   return ip.substring(0,index);
               }else{
                   return ip;
                }
            }
            ip = request.getHeader("X-Real-IP");
            if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
                return ip;
            }
            return request.getRemoteAddr();
       }
    
    
    //比较更新了那些字段
    public String compare(KeyPairAlgorithm keyPairAlgorithmNew){
    	String resultString="";
		//查询数据库中未更新前的数据
    	if(keyPairAlgorithmNew!=null&&!"".equals(keyPairAlgorithmNew)&&keyPairAlgorithmNew.getId()!=null){
    		KeyPairAlgorithm keyPairAlgorithmDB = keyPairAlgorithmService.selectById(keyPairAlgorithmNew.getId());
    		if(StringUtils.isNotBlank(keyPairAlgorithmNew.getAlgorithmName())&&StringUtils.isNotBlank(keyPairAlgorithmDB.getAlgorithmName())){
    			if(!keyPairAlgorithmNew.getAlgorithmName().equals(keyPairAlgorithmDB.getAlgorithmName())){
    				resultString+="算法英文缩写由"+keyPairAlgorithmDB.getAlgorithmName()+"变更为"+"keyPairAlgorithmNew.getAlgorithmName()";
    			}
    		}if(StringUtils.isNotBlank(keyPairAlgorithmNew.getName())&&StringUtils.isNoneBlank(keyPairAlgorithmDB.getName())){
    			if(!keyPairAlgorithmDB.getName().equals(keyPairAlgorithmNew.getName())){
    				resultString+="别名由"+keyPairAlgorithmDB.getName()+"变更为"+keyPairAlgorithmNew.getName();
    			}
    		}if(StringUtils.isNotBlank(keyPairAlgorithmNew.getAlgorithmOid())&&StringUtils.isNotBlank(keyPairAlgorithmDB.getAlgorithmOid())){
    			if(!keyPairAlgorithmDB.getAlgorithmOid().equals(keyPairAlgorithmNew.getAlgorithmOid())){
    				resultString+="算法OID由"+keyPairAlgorithmDB.getAlgorithmOid()+"变更为"+keyPairAlgorithmNew.getAlgorithmOid();
    			}
    		}if(keyPairAlgorithmDB.getKeysize()!=null&&keyPairAlgorithmNew.getKeysize()!=null){
    			if(!keyPairAlgorithmDB.getKeysize().equals(keyPairAlgorithmNew.getKeysize())){
    				resultString+="密钥长度由"+keyPairAlgorithmDB.getKeysize()+"变更为"+keyPairAlgorithmNew.getKeysize();
    			}
    		}
    	}
		
    	
		return resultString;
    	
    }

	
}
