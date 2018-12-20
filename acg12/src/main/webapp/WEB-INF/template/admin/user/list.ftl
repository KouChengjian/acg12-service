<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 主页</title>
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
                <form id="listForm" action="list.jhtml" method="get">
                    <div class="ibox-title">
                        <h5>管理员列表 </h5>
                        <div class="ibox-tools">
                            <a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.html"><i
                                    class="fa fa-plus"></i> 新增</a>
                            <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i
                                    class="fa fa-trash"></i> 删除</a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入姓名" name="filter_nicknamelike"
                                       value="${(paramter.nicknamelike)!''}" class="input-sm form-control">
                            </div>

                            <div class="col-sm-1">
                                <div class="input-group">
                                    <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                <tr>

                                    <th><input type="checkbox" class="i-checks" name="checkAll"></th>
                                    <th>姓名</th>
                                    <th>用户名</th>
                                    <th>联系电话</th>
                                    <th>角色</th>
                                    <th>状态</th>
                                    <th>锁定状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as user]
                                     <tr>
                                         <td>
                                             <input type="checkbox" class="i-checks" name="ids" value="${user.id}">
                                         </td>
                                         <td>${user.nickname}</td>
                                         <td>${user.username}</td>
                                         <td>${user.phone}</td>
                                         <td>${user.role.name}</td>
                                         <td> [#if user.isEnabled == '1']启用[/#if]
						                        [#if user.isEnabled == '0']禁用[/#if]</td>
                                         <td> [#if user.isLocked == '1']正常[/#if]
						                        [#if user.isLocked == '0']锁定[/#if]</td>
                                         <td>
                                             <a class="btn btn-info btn-xs btn-edit-loippi" data-id="${user.id}"
                                                href="edit.html?id=${user.id}"><i class="fa fa-paste"></i> 编辑</a>
                                             <a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${user.id}"><i
                                                     class="fa fa-trash"></i> 删除</a>
                                         </td>
                                     </tr>
                                     [/#list]
                                </tbody>
                            </table>
                       		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
                                [#include "/admin/include/pagination.ftl"]
                            [/@pagination]
                        </div>
                    </div>
                </form>
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
    });
</script>
</body>

</html>
