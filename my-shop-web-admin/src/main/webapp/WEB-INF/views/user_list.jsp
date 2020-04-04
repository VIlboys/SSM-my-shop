<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>--%>
<html>
<head>
    <title>我的商城|用户管理</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<jsp:include page="../includes/nav.jsp"/>
<jsp:include page="../includes/menu.jsp"/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            用户管理
            <small>Control panel</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">控制面板</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- /.row -->
        <div class="row">
            <div class="col-xs-12">

                <%--                    高级搜索--%>
                <div class="box box-info box-info-search"  style="display: none">
                    <div class="box-header with-border">
                        <h3 class="box-title">高级搜素</h3>
                    </div>
                    <div class="box-body">

<%--                        <form:form cssClass="form-horizontal" action="/user/search" method="post" modelAttribute="tbUser">--%>

                            <div class="box-body">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group form-horizontal">
                                            <label for="username" class="col-sm-4 control-label ">姓名</label>

                                            <div class="col-sm-8">
                                                <input id="username" class="form-control " placeholder="姓名">
<%--                                                <form:input path="username" cssClass="form-control " placeholder="姓名" ></form:input>--%>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="email" class="col-sm-4 control-label ">邮箱</label>

                                            <div class="col-sm-8">
                                                <input id="email" class="form-control " placeholder="邮箱">
<%--                                                <form:input path="email" cssClass="form-control " placeholder="邮箱" ></form:input>--%>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-4 control-label ">手机</label>

                                            <div class="col-sm-8">
                                                <input id="phone" class="form-control " placeholder="手机">
<%--                                                <form:input path="phone" cssClass="form-control " placeholder="手机" ></form:input>--%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-info pull-right" onclick="search()" style="width: 150px">
                                    <i class="fa fa-search"></i>    搜索</button>
                            </div>
<%--                        </form:form>--%>
                    </div>
                    <!-- /.box-body -->
                </div>
                <%--end--%>
                <div class="box">


                    <div class="box-header">
                        <h3 class="box-title">用户管理</h3>

                        <div class="box-body">
                            <br>
                            <a href="/user/form" type="button" class="btn btn-success btn-sm">
                                <i class="fa  fa-plus"></i>    添加
                            </a>

                            <button type="button" class="btn btn-default btn-sm" onclick="App.deleteMulti('/user/delete')" >
                                <i class="fa  fa-trash"></i>    删除
                            </button>
                            <a type="button" class="btn btn-default btn-sm">
                                <i class="fa   fa-upload"></i>    导入
                            </a>
                            <a type="button" class="btn btn-default btn-sm">
                                <i class="fa  fa-trash"></i>    导出
                            </a>
                            <button type="button" class="btn btn-primary" data-toggle="modal" onclick="$('.box-info-search').css('display')== 'none' ? $('.box-info-search').show('fast'): $('.box-info-search').hide('fast')">
                                <i class="fa fa-search"></i>    高级搜索
                            </button>
                        </div>

                        <c:if test="${baseResult != null}">
                            <div class="alert alert-${baseResult.status == 200 ? "success": "danger"} alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <i class="icon fa fa-ban"></i>${baseResult.message}
                            </div>
                        </c:if>

                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive">
                        <table id="dataTable" class="table table-hover">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master" /></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>

                                <tbody>

                                </tbody>


                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<jsp:include page="../includes/copyright.jsp"/>
</div>

<%--<sys:model/>--%>
<jsp:include page="../includes/footer.jsp"/>



<script>

    var _dataTable;

    $(function () {
        var _columns = [
                {
                    "data": function ( row, type, val, meta ) {
                        return '<input id=" '+row.id+'" type="checkbox" class="minimal" />'
                    }
                },

                { "data": "id" },
                { "data": "username" },
                { "data": "phone" },
                { "data": "email" },
                { "data": "updated" },
                {
                    "data": function ( row, type, val, meta ) {
                        var detaliUrl= "/user/detail?id="+ row.id;
                        var deleteUrl = "/user/delete";
                        return '<button type="button" class="btn  btn-default btn-sm" onclick="App.showDetail(\'' + detaliUrl + '\')"><i class="fa fa-search"></i>查看</button>'
                            + '<a href="/user/form?id='+row.id+'" type="button" class="btn  btn-primary btn-sm"><i class="fa fa-wrench"></i>修改</a>'
                            + '<button type="button" class="btn btn-sm btn-danger" onclick="App.deleteSingle(\'' + deleteUrl + '\', \'' + row.id + '\')"><i class="fa fa-trash-o"></i> 删除</button>';
                    }
                },

            ];
        _dataTable = App.initDataTable("/user/page",_columns);

    })

    function search() {

        var username = $("#username").val();
        var email = $("#email").val();
        var phone = $("#phone").val();


        var param = {

            "username": username,
            "email": email,
            "phone": phone


        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }


</script>

</body>
</html>
