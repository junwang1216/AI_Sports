<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.sports.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sports.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/passport/login.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card-group mb-0">
                    <div class="card p-4">
                        <div class="card-block">
                            <h1>Login</h1>
                            <p class="text-muted">Sign In to your account</p>
                            <div class="input-group mb-3">
                                <span class="input-group-addon"><i class="icon-user"></i>
                                </span>
                                <input type="text" class="form-control" placeholder="Username">
                            </div>
                            <div class="input-group mb-4">
                                <span class="input-group-addon"><i class="icon-lock"></i>
                                </span>
                                <input type="password" class="form-control" placeholder="Password">
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <button type="button" class="btn btn-primary px-4">Login</button>
                                </div>
                                <div class="col-6 text-right">
                                    <button type="button" class="btn btn-link px-0">Forgot password?</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card card-inverse card-primary py-5 d-md-down-none" style="width:44%">
                        <div class="card-block text-center">
                            <div>
                                <h2>Sign up</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                <button type="button" class="btn btn-primary active mt-3">Register Now!</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</layout:override>

<c:import url="../Shared/PassportLayout.jsp">
    <c:param name="nav" value="setting"/>
</c:import>
