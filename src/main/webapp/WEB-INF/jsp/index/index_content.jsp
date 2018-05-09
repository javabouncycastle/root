<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div class="col-xs-12">
	<div class="content">
		<h1 style="text-align: center;">欢迎使用!</h1>
	</div>
</div>
<div class="col-xs-12">
	<div class=" input-group col-sm-12">
        <span class="input-group-addon">开始时间：</span>
        <input type="date" id="date_start" name="date_start" class="date-picker form-control" placeholder="开始时间">
    </div>
</div>
</body>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        //日期选择器
    	$('.date-picker').datepicker({
            language: 'zh-CN',
            autoclose: true,
            todayHighlight: true,
    	    format: 'yyyy-mm-dd',
        });
    } );
</script>
</html>