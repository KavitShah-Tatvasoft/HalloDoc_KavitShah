<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 16-07-2024
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="offcanvas offcanvas-end" tabindex="-1" id="notification-tab-id" aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
        <div class="notification-text-logo-flex">
            <img src="<c:url value='/resources/icons/bell-animate.svg'/>" class="bell-icon-notification">
            <h5 class="offcanvas-title" id="offcanvasRightLabel">Notifications</h5>
            <div class="new-notifications-count"><span class="count-span-noti">5</span>&nbsp;unread</div>
        </div>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>

    <div class="single-notification-body clone-notification-class d-none">
        <div class="close-header-flex">
            <div class="img-noti-header-flex">
                <img src="<c:url value='/resources/icons/bell-animate.svg' /> " class="noti-icon">
                <div class="noti-header-text">Daily Data Expired</div>
            </div>
            <img src="<c:url value='/resources/icons/close.svg' />" class="close-btn-noti">
        </div>
        <div class="noti-description-text"></div>
        <div class="noti-time">12.53 AM</div>
    </div>

    <div class="offcanvas-body no-notification d-none">
        <div class="no-single-notification">No new notification to show</div>
    </div>

    <div class="offcanvas-body noti-main-body">

    </div>
    <div class="clear-all-btn-flex">
        <span class="clear-all-btn" onclick="clearAllNotification()">Clear All</span>
    </div>
</div>

