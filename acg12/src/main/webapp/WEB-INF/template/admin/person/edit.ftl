<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑person</title>
    <meta name="keywords" content="${setting.siteName}">
  	<meta name="description" content="${setting.siteName}">
  	<!-- BEGIN HEADER -->
		[#include "/admin/include/style.ftl"]
	<!-- END HEADER -->
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    	 <h5>编辑person</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/acg12_person/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/acg12_person/update.jhtml" method="post">
                  		<input type="hidden" value="${acg12Person.id}" name="id"/>
                       
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">pId</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="pId" class="form-control" maxlength="200" value="${(acg12Person.pId)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">名称</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" class="form-control" maxlength="200" value="${(acg12Person.name)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">中文名称</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="nameCn" class="form-control" maxlength="200" value="${(acg12Person.nameCn)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">图片</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="image" class="form-control" maxlength="200" value="${(acg12Person.image)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">简介</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="summary" class="form-control" maxlength="200" value="${(acg12Person.summary)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">身高</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="height" class="form-control" maxlength="200" value="${(acg12Person.height)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">体重</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="weight" class="form-control" maxlength="200" value="${(acg12Person.weight)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">别名</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="alias" class="form-control" maxlength="200" value="${(acg12Person.alias)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"> 1、声优 2、漫画家 3、制作人 4、音乐人 5、 6、演员 7、绘师 8、作家</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="type" class="form-control" maxlength="200" value="${(acg12Person.type)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">// 1、男 2、女</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="gender" class="form-control" maxlength="200" value="${(acg12Person.gender)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">血型  1、A   2、B   3、AB   4、O</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="bloodtype" class="form-control" maxlength="200" value="${(acg12Person.bloodtype)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">生日</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="birthday" class="form-control" maxlength="200" value="${(acg12Person.birthday)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="createtime" class="form-control" maxlength="200" value="${(acg12Person.createtime)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="updatetime" class="form-control" maxlength="200" value="${(acg12Person.updatetime)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">创建时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="createTime" class="form-control" maxlength="200" value="${(acg12Person.createTime?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">更新时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="updateTime" class="form-control" maxlength="200" value="${(acg12Person.updateTime?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                       		
                       
                         <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                            <button class="btn btn-success" type="submit">保存内容</button>
	                        </div>
	                    </div>
	                    </form>
                  	</div>
                </div>
            </div>
        </div>
    </div>
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    <script>
        $(document).ready(function () {
        	[@flash_message /]
        	$(".chosen-select").chosen({width:"100%"}); 
        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        	
        	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
        	
    		$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
        	$("#inputForm").validate({
               rules: {
                },
                messages: {
				}
            });
        });
    </script>
</body>
</html>
