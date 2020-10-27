<%--
  Created by IntelliJ IDEA.
  User: xiaoyi
  Date: 2020/8/24
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <form class="layui-form" action="" lay-filter="demo" >
            <input type="hidden" value="${survey.id}" name="id"  lay-verify="required" autocomplete="off"class="layui-input">
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">说明</label>
                <div class="layui-input-block">
                    <textarea name="remark" class="layui-textarea" ></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">限制</label>
                <div class="layui-input-inline">
                    <select name="bounds" lay-filter="bounds">
                        <option value="0">不限制</option>
                        <option value="1">IP限制</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">规则</label>
                <div class="layui-input-inline">
                    <select name="rules" lay-filter="rules">
                        <option value="0">公开访问</option>
                        <option value="1">密码访问</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="password" id="password" placeholder="请设置访问密码" autocomplete="off" style="display: none" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">开始时间</label>
                    <div class="layui-input-block">
                        <input type="text"  id="startTime" autocomplete="off" name="startTime" class="layui-input" placeholder="yyyy-MM-dd"  lay-verify="date">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">结束时间</label>
                    <div class="layui-input-block">
                        <input type="text" placeholder="yyyy-MM-dd"  lay-verify="date" name="endTime" id="endTime" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">匿名</label>
                <div class="layui-input-block">
                    <input type="checkbox"  name="anon" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" value="0">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                </div>
            </div>
        </form>

    </div>
</div>

<script src="../static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form','jquery','layer','laydate'], function () {
        var form = layui.form,$=layui.jquery,layer = layui.layer
        var laydate = layui.laydate
        laydate.render({
            elem: '#startTime',
            type:'date',
            trigger: 'click', // 新增这一行以解决

        });
        laydate.render({
            elem: '#endTime',
            type:'date',
            trigger: 'click', // 解决闪退 新增这一行以解决

        })

        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        //监听提交
        form.on('submit(demo1)', function (data) {
            $.ajax({
                url: 'update',
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data.field),
                success: function (data) {
                    layer.msg(data.msg,
                        {time:500},
                        function () {
                            parent.layer.close(index);//再执行关闭
                        })

                }
            })
            return false;
        });
        form.on('select(rules)',function (data) {
            if (data.value === "1"){
                $('#password').show()
            }else {
                $('#password').hide()
            }
        })
        if("${survey.rules}===1"){
            $("#password").show()
        }
        //表单初始赋值
        form.val('demo', {
            "title": "${survey.title}" // "name": "value"
            , "remark": "${survey.remark}"
            , "bounds": "${survey.bounds}"
            , "startTime": '<fmt:formatDate value="${survey.startTime}" pattern="yyyy-MM-dd"/>'
            , "endTime": '<fmt:formatDate value="${survey.endTime}" pattern="yyyy-MM-dd"/>'
            , "rules": "${survey.rules}"
            , "password": "${survey.password}"
            ,
            <c:if test="${survey.anon eq 0}" >
            "anon": "${survey.anon}"
            </c:if>

        })
    });
</script>

</body>
</html>
