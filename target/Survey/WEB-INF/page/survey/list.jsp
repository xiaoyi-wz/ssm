<%--
  Created by IntelliJ IDEA.
  User: xiaoyi
  Date: 2020/8/23
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/public.css" media="all">
</head>
<body>
<style type="text/css">

    .layui-table-cell{

        text-align:center;

        height: auto;

        white-space: normal;

    }

    .layui-table img{

        max-width:300px

    }
</style>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">创建人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="creator" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">问卷标题</label>
                            <div class="layui-input-inline">
                                <input type="text" name="title" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="createTime" autocomplete="off" class="layui-input"  placeholder="yyyy-MM-dd">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
<%--                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>--%>
<%--                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>--%>
<%--                <button class="layui-btn layui-btn-sm layui-btn-warm data-edit-btn" lay-event="edit"> 编辑 </button>--%>
    <div class="layui-btn-group">
        <button type="button" class="layui-btn layui-btn-sm" lay-event="add">
            <i class="layui-icon">&#xe654;</i>
        </button>
        <button type="button" class="layui-btn layui-btn-sm" lay-event="edit">
            <i class="layui-icon">&#xe642;</i>
        </button>
        <button type="button" class="layui-btn layui-btn-sm" lay-event="delete">
            <i class="layui-icon">&#xe640;</i>
        </button>
    </div>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

    </div>
</div>

<script src="../static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table','laytpl'], function () {

        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            laytpl = layui.laytpl


        table.render({

            elem: '#currentTableId',
            url: 'query',
            toolbar: '#toolbarDemo',
            contentType: "application/json",
            method: 'post',
            defaultToolbar: ['filter', 'exports', 'print'],

            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID' ,sort: true},
                {field: 'title', title: '问卷标题'},
                {field: 'logo',id:"logo1", title: '问卷图标', templet:"#img"},
                {field: 'startTime', width: 150, title: '开始时间'},
                {field: 'endTime', width: 150, title: '结束时间'},
                {field: 'state', title: '状态', width: 100},
                {field: 'creator', title: '创建人', width: 100},
                {field: 'createTime', title: '创建时间', width: 150}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line'
        });


        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {



            var result = JSON.stringify(data.field);
            console.log(result)
            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                },
                contentType: "application/json"
                , where: data.field
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加用户',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: 'create',
                    end: function () {
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr = [];
                for (index in data) {
                    arr.push(data[index].id)
                }
                $.ajax({
                    url: 'delete',
                    type: 'POST',
                    dataType: 'json',
                    data: 'ids=' + arr.join(","),
                    success: function (data) {
                        layer.msg(data.msg,
                            {time: 500},
                            function () {
                                table.reload('currentTableId')
                            })

                    }
                })
            } else if (obj.event === 'edit') {
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr = [];
                for (index in data) {
                    arr.push(data[index].id)
                }
                if(arr.length != 1){
                    layer.msg("请选择一行数据修改")
                    return;
                }
                var index = layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: 'detail?id='+arr[0],
                    end:function () {
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        })

    })


</script>
<script type="text/html" id="img">
    <img src="{{d.logo}}"/>
</script>
</body>
</html>
