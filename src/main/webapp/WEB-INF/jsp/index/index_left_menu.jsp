<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <!-- 左侧菜单 -->
  <aside class="main-sidebar">
    <section class="sidebar">
   	 <ul class="sidebar-menu" data-widget="tree" id="side-menu">
	 	<li class="header">功能菜单</li>  
	 	<li>
       	 	<a href="javascript:void(0)"><i class="fa fa-sitemap fa-fw"></i><span >系统参数</span><span class="fa arrow pull-right-container"></span></a>
        	<ul class="nav-second-level treeview-menu collapse">
            	<li mid="system_syscode" funurl="${ctx}/syscode/toSelectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">数据字典列表</a>
            	</li>
            	<li mid="system_syscodetype" funurl="${ctx}/syscodetype/selectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">数据字典类型列表</a>
            	</li>  	  	
       		</ul>
    	</li>
   		<li>
       	 	<a href="javascript:void(0)"><i class="fa fa-tasks"></i><span >任务管理</span><span class="fa arrow pull-right-container"></span></a>
        	<ul class="nav-second-level treeview-menu collapse">
            	<li mid="key_kpgTask" funurl="${ctx}/kpgTask/toSelectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)"><i class="fa fa-list-ul"></i>秘钥任务列表</a>
            	</li>  	
       		</ul>
    	</li>
    	<li>
       	 	<a href="javascript:void(0)"><i class="fa fa-sitemap fa-fw"></i><span >模板管理</span><span class="fa arrow pull-right-container"></span></a>
        	<ul class="nav-second-level treeview-menu collapse">
            	<li mid="template_keypairStandby" funurl="${ctx}/certificateTemplate/toSelectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">证书模板列表</a>
            	</li>  	
       		</ul>
    	</li>
    	<li>
       	 	<a href="javascript:void(0)"><i class="fa fa-key"></i><span >密钥管理</span><span class="fa arrow pull-right-container"></span></a>
        	<ul class="nav-second-level treeview-menu collapse">
            	<li mid="key_keypairStandby" funurl="${ctx}/keypairStandby/toSelectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">备用密钥列表</a>
            	</li>
            	<li mid="key_keypairInuse" funurl="${ctx}/keypairInuse/toSelectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">在用密钥列表列表</a>
            	</li>
            	<li mid="key_kpArchive" funurl="${ctx}/kpArchive/toSelectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">历史密钥列表列表</a>
            	</li>  	  	
       		</ul>
    	</li>

    	<li>
       	 	<a href="javascript:void(0)"><i class="fa fa-sitemap fa-fw"></i><span >算法管理</span><span class="fa arrow pull-right-container"></span></a>
        	<ul class="nav-second-level treeview-menu collapse">
            	<li mid="template_keypairStandby" funurl="${ctx}/algorithm/selectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">算法列表</a>
            	</li>  	
       		</ul>
    	</li>
						
    	<li>
       	 	<a href="javascript:void(0)"><i class="fa fa-sitemap fa-fw"></i><span >证书管理</span><span class="fa arrow pull-right-container"></span></a>
        	<ul class="nav-second-level treeview-menu collapse">
            	<li mid="certificate_certificateRequestList" funurl="${ctx}/certificateRequest/selectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">证书申请列表</a>
            	</li>
            	<li mid="certificate_certificateRequest" funurl="${ctx}/certificateRequest/forwardInsert.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">证书申请</a>
            	</li>  	  	
       		</ul>
    	</li>
    	<li>
       	 	<a href="javascript:void(0)"><i class="fa fa-sitemap fa-fw"></i><span >日志管理</span><span class="fa arrow pull-right-container"></span></a>
        	<ul class="nav-second-level treeview-menu collapse">
            	<li mid="log_auditOpLog" funurl="${ctx}/auditOpLog/selectAll.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">日志列表</a>
            	</li>	  	
       		</ul>
    	</li>
    	<li>
       	 	<a href="javascript:void(0)"><i class="fa fa-sitemap fa-fw"></i><span >管理员管理</span><span class="fa arrow pull-right-container"></span></a>
        	<ul class="nav-second-level treeview-menu collapse">
            	<li mid="admin_conf" funurl="${ctx}/conf/syscode/list.do" onclick="addTabs(this)">
                	<a href="javascript:void(0)">管理员列表</a>
            	</li>	  	
       		</ul>
    	</li>									
      </ul>
    </section>
  </aside>
  