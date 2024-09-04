<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 11-06-2024
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <div class="navbar-main-container">
        <div class="navbar-inner-container">
            <div class="menu-logo-flex">
                <img src="<c:url value='/resources/icons/menu-animate.svg' />" class="menu-icon menu-logo-left  d-none" />
                <a href="${pageContext.request.contextPath}/client/dashboard"><div class="logo-name-flex"><img
                        src="<c:url value='/resources/images/white-logo-removebg-preview.png' />" class="nav-logo"/>
                    <span class="nav-company-name">Uninor</span></div></a>
            </div>

            <div class="bell-profile-logout-flex">
                <div class="bell-new-notification-flex">
                    <div class="count-span-noti red-noti-background"></div>
                    <img src="<c:url value='/resources/icons/bell-animate.svg' />" class="bell-menu-icon" data-bs-toggle="offcanvas" data-bs-target="#notification-tab-id" aria-controls="offcanvasRight"/>
                </div>
                <div class="pic-name-flex">
                    <a class="nav-profile-image" href="${pageContext.request.contextPath}/client/client-profile"><img src="<c:url value='/resources/icons/person-filled-animated.svg'/>"
                                                        class="profile-pic"/></a>
                </div>
                <img src="<c:url value='/resources/icons/logout-animate.svg' />" class="logout-icon" onclick="logoutUser()" />
            </div>
        </div>
    </div>
</header>