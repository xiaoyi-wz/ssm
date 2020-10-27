<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

        <form class="layui-form" action="" lay-filter="demo">
            <input type="hidden" value="${questionOpt.id}" name="id"  lay-verify="required" autocomplete="off"class="layui-input">
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
                    <input type="text" name="opt" id="opt"  class="layui-input" >
                </div>
                <label class="layui-form-label">排序</label>
                <div class="layui-input-inline">

                    <select name="orderby" lay-filter="orderby">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">答案</label>
                <div class="layui-input-block">
                    <input type="checkbox"  name="answer" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" value="1">
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


        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        //监听提交
        form.on('submit(demo1)', function (data) {

            console.log(data)
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
            "surveyId": "${questionOpt.surveyId}" // "name": "value"
            , "questionId": "${questionOpt.questionId}"
            , "opt": "${questionOpt.opt}"
            , "orderby": "${questionOpt.orderby}"
            , "type": "${questionOpt.type}"
            ,
            <c:if test="${questionOpt.answer eq 'yes'}" >
            "answer": "${questionOpt.answer}"
            </c:if>

        })
    });
</script>

</body>
</html>
