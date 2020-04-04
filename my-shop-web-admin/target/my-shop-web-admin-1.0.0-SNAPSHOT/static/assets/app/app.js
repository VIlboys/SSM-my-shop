var App = function () {

    var _masterCheckbox;
    var _checkbox;

    //用于存放id得数组
    var _idArray;

    //默认的Dropzone参数
    var defaultDropzoneOpts ={
        url: "",
        paramName: "dropFile",
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

    var handerlCheckbox =function () {
        //激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        })
        //控制顶部
        _masterCheckbox=$('input[type="checkbox"].minimal.icheck_master');
        //获取全部
        _checkbox=$('input[type="checkbox"].minimal');

    };

    //全选功能
    var handlerCheckboxAll=function(){
        _masterCheckbox.on("ifClicked",function (e) {
            if (e.target.checked){
                _checkbox.iCheck("uncheck");
            }else {
                _checkbox.iCheck("check");
            }
        })
    };


    /**
     * 删除单笔记录
     * @param url 删除链接
     * @param id 需要删除数据的 ID
     */
    var handlerDeleteSingle = function (url, id, msg) {
        // 可选参数
        if (!msg) msg = null;

        // 将 ID 放入数组中，以便和批量删除通用
        _idArray = new Array();
        _idArray.push(id);

        $("#model-message").html(msg == null ? "您确定删除数据项吗？" : msg);
        $("#modal-default").modal("show");
        // 绑定删除事件
        $("#btnModelOk").one("click",function () {
            handlerDeleteData(url);
        });
    };

    //删除功能
    var handlerDeleteMulti=function(url) {

        _idArray = new Array();

        //将选中的元素放入数组
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        //判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#model-message").html("您还没有选择任何的数据，至少选择一项");
        } else {
            $("#model-message").html("您确定选择的删除数据吗");
        }
        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");

        //如果用户选择了数据项则调用删除方法
        $("#btnModelOk").one("click", function () {
            handlerDeleteData(url);
        });
    };
        /**
         * 当前私有函数的私有函数，删除数据
         */
        var handlerDeleteData = function (url) {
            $("#modal-default").modal("hide");
            //如果没有选择数据线关闭模态框
            if(_idArray.length > 0 ) {

            //删除操作
                setTimeout(function () {
                    $.ajax({
                        "url": url,
                        "type": "POST",
                        //设置为同步执行
                        //"async":false,
                        "data": {"ids":_idArray.toString()},
                        "dataType": "JSON",
                        "success": function (data) {
                            //请求成功后，无论是成功或者失败都需要弹出模态框进行提示，所以这里需要先解绑原来的click事件
                            $("#btnModelOk").one("click");
                            //请求成功
                            if(data.status === 200){
                                //刷新页面
                                $("#btnModelOk").one("click",function () {
                                    window.location.reload();
                                });

                            }
                            //请求失败
                            else{
                                //确定按钮的事件改为隐藏模态框
                                $("#btnModelOk").one("click",function () {
                                    $("#modal-default").modal("hide");
                                });
                            }
                            // 因为无论如何都需要提示信息，所以这里的模态框是必须调用的
                            $("#model-message").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    });
                },600); // 延时600毫秒提升用户体验
        }
    };

    /**
     * 初始化 DataTable
     */
    var handlerInitDataTable =function (url,columns) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,

            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                handerlCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };
    /**
     * 初始化ZTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree = function (url, autoParam, callback) {

        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam
            }
        };
        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModelOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();
            //未选择
            if(nodes.length == 0){
                alert("请先选择一个节点");
            }
            //已选择
            else {
                callback(nodes);
            }
        })

    };

    /**
     * 初始化Dropzone
     * @param opts
     */
    var handlerInitDropzone =function (opts) {
        //关闭Dropzone自动发现功能
        Dropzone.autoDiscover = false;
        var options = $.extend(defaultDropzoneOpts,opts);
        new Dropzone(options.id, options);

        //     init: function () {
        //         this.on("success", function (file, data) {
        //             defaultDropzoneOpts.success(file,data);
        //         });
        //     }
        // });
    };


    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail =function (url) {
        //这里是通过ajax请求Html的方式 将 jsp 装载进模态框中
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#model-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        })
    }



    return{
        /**
         *         初始化
         */
        initCheckbox:function(){


            handerlCheckbox();
            handlerCheckboxAll();
        },
        deleteSingle:function(url, id, msg){
            handlerDeleteSingle(url, id, msg);
        },
        /**
         * 批量删除
         * @param url
         */
        deleteMulti:function (url) {
            handlerDeleteMulti(url);
        },
        /**
         * 初始化 DataTable
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initDataTable:function (url, columns) {
            return handlerInitDataTable(url,columns);
        },
        /**
         * 初始化ZTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree:function(url, autoParam, callback){
            handlerInitZTree(url, autoParam, callback);
        },
        initDropzone:function(opts){
            handlerInitDropzone(opts);
        },
        /**
         * 显示详情
         * @param url
         */
        showDetail:function (url) {
            handlerShowDetail(url);
        }
    }
}();

$(document).ready(function () {
    App.initCheckbox();
})