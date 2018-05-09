package cn.com.sure.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.sure.common.Constants;
import cn.com.sure.log.entry.AuditOpLog;
import cn.com.sure.log.test.service.AuditOpLogService;

@Aspect
@Component
public class AuditLogAspect {
	
	AuditOpLog auditOpLog = new AuditOpLog();
	
	@Autowired
	private AuditOpLogService auditOpLogService;
	
	 @Pointcut("execution(* cn.com.sure.*.service.*.insert(..))")
	 public void insert() {}
	 
	 @Pointcut("execution(* cn.com.sure.*.service.*.update(..))")
	 public void update() {}
	 
	 @Pointcut("execution(* cn.com.sure.*.service.*.remove(..))")
	 public void remove() {}
	 
	 @Around("insert()")
	 public Object insert(ProceedingJoinPoint pjp)throws Throwable {

		 MethodSignature signature = (MethodSignature) pjp.getSignature();
		 String[] str = signature.getParameterNames();
		 //获取操作内容  
	     String opContent = adminOptionContent(pjp.getArgs(), pjp.getSignature().getName());  
	     
	     //在session中获取管理员信息
	     HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
         HttpSession session =request.getSession(); 
         
         auditOpLog.setType(Constants.OPERATION_TYPE_INSERT);
         auditOpLog.setAction(pjp.getSignature().getName());
         auditOpLog.setActionExt1(Arrays.toString(str));//被操作的对象名称
         auditOpLog.setActionExt2(opContent);//对象的所有参数值的集合
         auditOpLog.setActionExt3("");
         auditOpLog.setActionExt4("");
         auditOpLog.setMessage("");
         auditOpLog.setTimestamp(new Date());
         auditOpLog.setIp(getIp(request));
         auditOpLog.setOperator((String)session.getAttribute(Constants.SESSION_ADMIN_NAME));
         Object obj=pjp.proceed();
    	 int ifSuccess=1;
		 if(obj!=null&&!"".equals(obj)){
		 	 ifSuccess=Integer.parseInt(obj.toString());
		 }
		 if(ifSuccess==1){
		 	auditOpLog.setIsOpSucc(1);
		 }else{
		 	auditOpLog.setIsOpSucc(0);
		 }
         
         auditOpLogService.insert(auditOpLog);
         
		 return ifSuccess;
	 }
	 
	 @Around("update()")
	 public Object update(ProceedingJoinPoint pjp)throws Throwable {
		 
		 try {
			MethodSignature signature = (MethodSignature) pjp.getSignature();
			 String[] str = signature.getParameterNames();
			 //获取操作内容  
			 String opContent = adminOptionContent(pjp.getArgs(), pjp.getSignature().getName());  
			 
			 //在session中获取管理员信息
			 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
			 HttpSession session =request.getSession(); 
			 
			 Object obj=pjp.proceed(pjp.getArgs());
			 
			 auditOpLog.setType(Constants.OPERATION_TYPE_UPDATE);
			 auditOpLog.setAction(pjp.getSignature().getName());
			 auditOpLog.setActionExt1(Arrays.toString(str));//对象名称
			 auditOpLog.setActionExt2(opContent);//对象的所有参数值的集合
			 auditOpLog.setActionExt3("");
			 auditOpLog.setActionExt4("");
			 auditOpLog.setMessage("");
			 auditOpLog.setTimestamp(new Date());
			 auditOpLog.setIp(getIp(request));
			 auditOpLog.setOperator((String)session.getAttribute(Constants.SESSION_ADMIN_NAME));
			 
			 int ifSuccess=1;
			 if(obj!=null&&!"".equals(obj)){
			 	 ifSuccess=Integer.parseInt(obj.toString());
			 }
			 if(ifSuccess==1){
			 	auditOpLog.setIsOpSucc(1);
			 }else{
			 	auditOpLog.setIsOpSucc(0);
			 }
			 
			 auditOpLogService.insert(auditOpLog);
			 
			 return ifSuccess;
		} catch (Exception e) {
			e.printStackTrace();
			pjp.proceed();
			return 1;
			
		}
	 }   
	 
	 
	 
	 public String adminOptionContent(Object[] args, String mName) throws Exception{  
   	  
	        if (args == null) {  
	            return null;  
	        }  
	          
	        StringBuffer rs = new StringBuffer();  
	        rs.append(mName);  
	        String className = null;  
	        int index = 1;  
	        // 遍历参数对象  
	        for (Object info : args) {  
	              
	            //获取对象类型  
	            className = info.getClass().getName();  
	            className = className.substring(className.lastIndexOf(".") + 1);  
	            rs.append(index + className);  
	              
	            // 获取对象的所有方法  
	            Method[] methods = info.getClass().getDeclaredMethods();  
	              
	            // 遍历方法，判断get方法  
	            for (Method method : methods) {  
	                  
	                String methodName = method.getName();  
	                // 判断是不是get方法  
	                if (methodName.indexOf("get") == -1) {// 不是get方法  
	                    continue;// 不处理  
	                }  
	                  
	                Object rsValue = null;  
	                try {  
	                      
	                    // 调用get方法，获取返回值  
	                    rsValue = method.invoke(info);  
	                      
	                    if (rsValue == null) {//没有返回值  
	                        continue;  
	                    }  
	                      
	                } catch (Exception e) {  
	                    continue;  
	                }  
	                  
	                //将值加入内容中  
	                rs.append("(" + methodName + " : " + rsValue + ")");  
	            }  
	              
	            rs.append("]");  
	              
	            index++;  
	        }  
	          
	        return rs.toString();  
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
}
