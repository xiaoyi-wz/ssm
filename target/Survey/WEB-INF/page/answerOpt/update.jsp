
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaoyi
  Date: 2020/8/24
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <form class="layui-form" action="" lay-filter="demo">
            <input type="hidden" value="${answerOpt.id}" name="id"  lay-verify="required" autocomplete="off"class="layui-input">
            <div class="layui-form-item">
                <label class="layui-form-label">问卷ID</label>
                <div class="layui-input-block">
                    <input type="text" name="surveyId" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">问题ID</label>
                <div class="layui-input-block">
                    <input type="text" name="questionId" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-inline">
                    <select name="type" lay-filter="type">
                        <option value="1">单选</option>
                        <option value="2">多选</option>
                        <option value="3">单行</option>
                        <option value="4">多行</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">选项</label>
                <div class="layui-input-inline">
                    <input type="text" name="optId"  class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">参与者</label>
                <div class="layui-input-block">
                    <input type="text" name="voter"  class="layui-input" >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">创建时间</label>
                <div class="layui-input-block">
                    <input type="text" placeholder="yyyy-MM-dd"  lay-verify="date" name="createTime" id="createTime" autocomplete="off" class="layui-input">
                </div>
            </div>

            <br>
            <br>
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
    layui.use(['form','jquery','layer','laydate','upload'], function () {
        var form = layui.form,$=layui.jquery,layer = layui.layer,upload=layui.upload
        var laydate = layui.laydate
        laydate.render({
            elem: '#createTime',
            type:'date',
            trigger: 'click', // 新增这一行以解决

        });

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
        //表单初始赋值
        form.val('demo', {
            "surveyId": "${answerOpt.surveyId}" // "name": "value"
            , "questionId": "${answerOpt.questionId}"
            , "type": "${answerOpt.type}"
            , "optId": "${answerOpt.optId}"
            , "voter": "${answerOpt.voter}"
            , "createTime": '<fmt:formatDate value="${answerOpt.createTime}" pattern="yyyy-MM-dd"/>'

        })
    });

</script>

</body>
</html>
