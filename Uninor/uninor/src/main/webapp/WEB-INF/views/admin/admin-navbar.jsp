<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 22-07-2024
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <div class="navbar-main-container">
        <div class="navbar-inner-container">
            <div class="menu-logo-flex">
                <img src="<c:url value='/resources/icons/menu-animate.svg' />" class="menu-icon menu-logo-left  d-none" />
                <div class="logo-name-flex"><img
                        src="<c:url value='/resources/images/white-logo-removebg-preview.png' />" class="nav-logo"/>
                    <span class="nav-company-name">Uninor</span></div>
            </div>

            <div class="bell-profile-logout-flex">
                <div class="pic-name-flex">
                    <a class="nav-profile-image" href=""><img src="<c:url value='/resources/icons/person-filled-animated.svg'/>"
                                                                                                                      class="profile-pic"/></a>
                </div>
                <img src="<c:url value='/resources/icons/logout-animate.svg' />" class="menu-icon"/>
            </div>
        </div>
    </div>
</header>
