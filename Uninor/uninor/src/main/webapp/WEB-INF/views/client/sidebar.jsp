<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 11-06-2024
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="sidebar">

    <div class="side-inner">

        <div class="nav-menu">
            <ul class="sidebar-list">
                <li><a href="${pageContext.request.contextPath}/client/dashboard" class="sidebar-single-flex active-dashboard active-common-class active-link-sidebar" ><img src="<c:url value='/resources/icons/home.svg' />" class="margin-right"> <span>Dashboard</span></a></li>
                <li><a href="${pageContext.request.contextPath}/client/recharge-tab" class="sidebar-single-flex active-recharge active-common-class"><img src="<c:url value='/resources/icons/recharge.svg' />" class="margin-right extra-size-icon"> <span>Recharge</span></a></li>
                <li><a href="${pageContext.request.contextPath}/client/client-profile" class="sidebar-single-flex active-profile active-common-class"><img src="<c:url value='/resources/icons/profile-card.svg' />" class="margin-right extra-size-icon"> <span>User Profile</span></a></li>
                <li><a href="${pageContext.request.contextPath}/client/sim-details" class="sidebar-single-flex active-sim active-common-class"><img src="<c:url value='/resources/icons/sim.svg' />" class="margin-right"> <span>Sim Details</span></a></li>
                <li><a href="${pageContext.request.contextPath}/client/redeem-rewards" class="sidebar-single-flex active-rewards active-common-class"><img src="<c:url value='/resources/icons/gift.svg' />" class="margin-right "> <span>Rewards</span></a></li>
                <li><a href="${pageContext.request.contextPath}/client/recharge-history" class="sidebar-single-flex active-history active-common-class"><img src="<c:url value='/resources/icons/recharge-history.svg' />" class="margin-right extra-size-icon"> <span>Recharge History</span></a></li>
            </ul>
        </div>
    </div>

</aside>
<div class="custom-overlay"></div>
