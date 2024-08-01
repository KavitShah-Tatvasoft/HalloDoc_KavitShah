<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 11-06-2024
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>Sign Up</title>
    <link rel="icon" href="<c:url value="/resources/images/white-logo.png" />" type="image/icon type">
    <%--    font link--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/resources/css/navbar.css' />">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro:400,600&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value='/resources/fonts/icomoon/style.css' />">

    <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.min.css' />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">


    <!-- Style -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body class="show-sidebar body">
<%@include file="navbar.jsp"%>


<div class="outermost-contianer ">
    <%@include file="sidebar.jsp"%>
    <div class="custom-background"></div>
    <main>
        <div class="container-fluid recharge-container">  <!-- Change inside content and class-->
            <div class="section_our_solution">
                <div class="row recharge-cards-row">
                    <div class="col-12 col-sm-6 col-md-4">
                        <div class="our_solution_category">
                            <div class="solution_cards_box">
                                <div class="solution_card">
                                    <div class="hover_color_bubble"></div>
                                    <div class="solu_title">
                                        <div class="amount-view-btn-flex">
                                            <h3 class="rs-amount-flex">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                <span class="amount-span">666</span>
                                            </h3>

                                            <span class="view-plan-text">View Plan</span>
                                        </div>
                                    </div>
                                    <div class="solu_description">
                                        <div class="validity-data-flex">
                                            <div class="validity-flex">
                                                <span class="label-text">VALIDITY</span>
                                                <span class="label-value">364 days</span>
                                            </div>

                                            <div class="validity-flex">
                                                <span class="label-text">DATA</span>
                                                <span class="label-value">1.5 GB/ Day</span>
                                            </div>
                                        </div>
                                        <div class="center-recharge-button">
                                            <button type="button" class="read_more_btn">Recharge</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-sm-6 col-md-4">
                        <div class="our_solution_category">
                            <div class="solution_cards_box">
                                <div class="solution_card">
                                    <div class="hover_color_bubble"></div>
                                    <div class="solu_title">
                                        <div class="amount-view-btn-flex">
                                            <h3 class="rs-amount-flex">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                <span class="amount-span">666</span>
                                            </h3>

                                            <span class="view-plan-text">View Plan</span>
                                        </div>
                                    </div>
                                    <div class="solu_description">
                                        <div class="validity-data-flex">
                                            <div class="validity-flex">
                                                <span class="label-text">VALIDITY</span>
                                                <span class="label-value">364 days</span>
                                            </div>

                                            <div class="validity-flex">
                                                <span class="label-text">DATA</span>
                                                <span class="label-value">1.5 GB/ Day</span>
                                            </div>
                                        </div>
                                        <div class="center-recharge-button">
                                            <button type="button" class="read_more_btn">Recharge</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 col-md-4">
                        <div class="our_solution_category">
                            <div class="solution_cards_box">
                                <div class="solution_card">
                                    <div class="hover_color_bubble"></div>
                                    <div class="solu_title">
                                        <div class="amount-view-btn-flex">
                                            <h3 class="rs-amount-flex">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                <span class="amount-span">666</span>
                                            </h3>

                                            <span class="view-plan-text">View Plan</span>
                                        </div>
                                    </div>
                                    <div class="solu_description">
                                        <div class="validity-data-flex">
                                            <div class="validity-flex">
                                                <span class="label-text">VALIDITY</span>
                                                <span class="label-value">364 days</span>
                                            </div>

                                            <div class="validity-flex">
                                                <span class="label-text">DATA</span>
                                                <span class="label-value">1.5 GB/ Day</span>
                                            </div>
                                        </div>
                                        <div class="center-recharge-button">
                                            <button type="button" class="read_more_btn">Recharge</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="<c:url value='/resources/js/main.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
</body>

<style>

    .view-plan-text:hover{
        color: #00005c;
        transform: scale(1.1);
        transition: 0.7s ease-in-out;
    }

    .view-btn{
        border: 0;
        border-radius: 15px;
        background: linear-gradient( 140deg, #42c3ca 0%, #42c3ca 50%, #42c3cac7 75% ) !important;
        color: #fff;
        font-weight: 500;
        font-size: 1rem;
        padding: 5px 16px;
    }

    .amount-view-btn-flex{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .recharge-container{
        width: 75%;
    }

    .rs-amount-flex{
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .validity-data-flex{
        display: flex;
        justify-content: flex-start;
        gap: 30%;
        padding-left: 5px;
        margin-bottom: 25px;
        margin-top: 15px;
    }

    .validity-flex{
        display: flex;
        flex-direction: column;

    }

    .center-recharge-button{
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .section_our_solution .row {
        align-items: center;
    }

    .our_solution_category {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }
    .our_solution_category .solution_cards_box {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .solution_cards_box{
        height: 100%;
    }
    .solution_cards_box .solution_card {
        flex: 0 50%;
        background: #fff;
        box-shadow: 0 2px 4px 0 rgba(136, 144, 195, 0.2),
        0 5px 15px 0 rgba(37, 44, 97, 0.15);
        border-radius: 15px;
        margin: 8px;
        padding: 10px 15px;
        position: relative;
        z-index: 1;
        overflow: hidden;
        height: 100%;
        transition: 0.7s;
    }

    .solution_cards_box .solution_card:hover {
        background: #309df0;
        color: #fff;
        transform: scale(1.1);
        z-index: 9;

    }

    .solution_cards_box .solution_card:hover .rs-amount-flex img{
        filter: invert(97%) sepia(97%) saturate(10%) hue-rotate(153deg) brightness(102%) contrast(103%);
    }

    .solution_cards_box .solution_card:hover::before {
        background: rgb(85 108 214 / 10%);
    }

    .solution_cards_box .solution_card:hover .solu_title h3,
    .solution_cards_box .solution_card:hover .solu_description p {
        color: #fff;
    }

    .solution_cards_box .solution_card:before {
        content: "";
        position: absolute;
        background: rgb(85 108 214 / 5%);
        width: 170px;
        height: 400px;
        z-index: -1;
        transform: rotate(42deg);
        right: -56px;
        top: -23px;
        border-radius: 35px;
    }

    .solution_cards_box .solution_card:hover .solu_description button {
        background: #fff !important;
        color: #309df0;
    }

    .solution_card .so_top_icon {
    }

    .solution_card .solu_title h3 {
        color: #212121;
        font-size: 1.3rem;
        margin-top: 13px;
        margin-bottom: 13px;
    }

    .solution_card .solu_description p {
        font-size: 15px;
        margin-bottom: 15px;
    }

    .solution_card .solu_description button {
        border: 0;
        border-radius: 15px;
        background: #3750eb;
        color: #fff;
        font-weight: 500;
        font-size: 1rem;
        padding: 5px 16px;
    }

    .our_solution_content h1 {
        text-transform: capitalize;
        margin-bottom: 1rem;
        font-size: 2.5rem;
    }
    .our_solution_content p {
    }

    .hover_color_bubble {
        position: absolute;
        background: rgb(54 81 207 / 15%);
        width: 100rem;
        height: 100rem;
        left: 0;
        right: 0;
        z-index: -1;
        top: 4rem;
        border-radius: 50%;
        transform: rotate(-36deg);
        left: -18rem;
        transition: 0.7s;
    }

    .solution_cards_box .solution_card:hover .hover_color_bubble {
        top: 0rem;
    }

    .solution_cards_box .solution_card .so_top_icon {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        background: #fff;
        overflow: hidden;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .solution_cards_box .solution_card .so_top_icon img {
        width: 40px;
        height: 50px;
        object-fit: contain;
    }

    /*start media query*/

    @media screen and (max-width: 1504px){
        .validity-data-flex{
            gap: 30px;
        }

        .recharge-container{
            width: 90%;
        }
    }

    @media screen and (max-width: 1146px){
        .validity-data-flex{
            gap: 20px;
        }

        .recharge-container{
            width: 100%;
        }
    }

    @media screen and (max-width: 1024px) {
        .sol_card_top_3 {
            position: relative;
            top: -3rem;
        }
        .our_solution_category {
            margin: 0 auto;
        }
    }

    @media screen and (max-width: 768px) {
        .our_solution_category .solution_cards_box {
            flex: 1;
        }

        .recharge-container{
            width: 100%;
        }

        .validity-data-flex{
            gap: 29px;
        }

        .recharge-cards-row .col-12{
            margin-top: 10px;
        }
    }

    @media only screen and (min-width: 768px) and (max-width: 768px){
        .recharge-cards-row .col-12{
            padding-left: 0px;
            padding-right: 0px;
        }
    }



    @media screen and (max-width: 575px) {
        .validity-data-flex{
            gap: 30%
        }
    }

    @media screen and (min-width: 320px) {
        .sol_card_top_3 {
            position: relative;
            top: 0;
        }

        .our_solution_category {
            width: 100%;
            margin: 0 auto;
        }

        .our_solution_category .solution_cards_box {
            flex: auto;
        }
    }

</style>


</html>

<------------------------------- recharge tab design complete --------------------->

<%--
  Created by IntelliJ IDEA.
  User: pca25
  Date: 11-06-2024
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>Sign Up</title>
    <link rel="icon" href="<c:url value="/resources/images/white-logo.png" />" type="image/icon type">
    <%--    font link--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/resources/css/navbar.css' />">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro:400,600&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value='/resources/fonts/icomoon/style.css' />">

    <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.min.css' />">

    <!-- Swiper CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/freeps2/a7rarpress@main/swiper-bundle.min.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">


    <!-- Style -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/recharge-tab.css' />">
</head>
<body class="show-sidebar body">
<%@include file="navbar.jsp"%>


<div class="outermost-contianer ">
    <%@include file="sidebar.jsp"%>
    <div class="custom-background"></div>
    <main>


        <div class="slide-container swiper">
            <div class="slide-content">
                <div class="recharge-text">Popular Plans</div>
                <div type="button" data-bs-toggle="modal" data-bs-target="#filterModal" class="filter-btn">
                    <div class="filter-count">
                        <span class="filter-applied-count">4</span>
                    </div>
                    <div class="filter-icon-flex">
                        <span class="filter-text">Filter</span>
                        <img src="<c:url value='/resources/icons/filter-animated.svg'/>" class="filter-icon">
                    </div>
                </div>
                <div class="card-wrapper swiper-wrapper">
                    <div class="card swiper-slide">
                        <div class="col-12">
                            <div class="our_solution_category">
                                <div class="solution_cards_box">
                                    <div class="solution_card">
                                        <div class="hover_color_bubble"></div>
                                        <div class="solu_title">
                                            <div class="amount-view-btn-flex">
                                                <h3 class="rs-amount-flex">
                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                    <span class="amount-span">666</span>
                                                </h3>

                                                <span class="view-plan-text">View Plan</span>
                                            </div>
                                        </div>
                                        <div class="solu_description">
                                            <div class="validity-data-flex">
                                                <div class="validity-flex">
                                                    <span class="label-text">VALIDITY</span>
                                                    <span class="label-value">364 days</span>
                                                </div>

                                                <div class="validity-flex">
                                                    <span class="label-text">DATA</span>
                                                    <span class="label-value">1.5 GB/ Day</span>
                                                </div>
                                            </div>
                                            <div class="center-recharge-button">
                                                <button type="button" class="read_more_btn">Recharge</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="col-12">
                            <div class="our_solution_category">
                                <div class="solution_cards_box">
                                    <div class="solution_card">
                                        <div class="hover_color_bubble"></div>
                                        <div class="solu_title">
                                            <div class="amount-view-btn-flex">
                                                <h3 class="rs-amount-flex">
                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                    <span class="amount-span">666</span>
                                                </h3>

                                                <span class="view-plan-text">View Plan</span>
                                            </div>
                                        </div>
                                        <div class="solu_description">
                                            <div class="validity-data-flex">
                                                <div class="validity-flex">
                                                    <span class="label-text">VALIDITY</span>
                                                    <span class="label-value">364 days</span>
                                                </div>

                                                <div class="validity-flex">
                                                    <span class="label-text">DATA</span>
                                                    <span class="label-value">1.5 GB/ Day</span>
                                                </div>
                                            </div>
                                            <div class="center-recharge-button">
                                                <button type="button" class="read_more_btn">Recharge</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="col-12">
                            <div class="our_solution_category">
                                <div class="solution_cards_box">
                                    <div class="solution_card">
                                        <div class="hover_color_bubble"></div>
                                        <div class="solu_title">
                                            <div class="amount-view-btn-flex">
                                                <h3 class="rs-amount-flex">
                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                    <span class="amount-span">666</span>
                                                </h3>

                                                <span class="view-plan-text">View Plan</span>
                                            </div>
                                        </div>
                                        <div class="solu_description">
                                            <div class="validity-data-flex">
                                                <div class="validity-flex">
                                                    <span class="label-text">VALIDITY</span>
                                                    <span class="label-value">364 days</span>
                                                </div>

                                                <div class="validity-flex">
                                                    <span class="label-text">DATA</span>
                                                    <span class="label-value">1.5 GB/ Day</span>
                                                </div>
                                            </div>
                                            <div class="center-recharge-button">
                                                <button type="button" class="read_more_btn">Recharge</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="col-12">
                            <div class="our_solution_category">
                                <div class="solution_cards_box">
                                    <div class="solution_card">
                                        <div class="hover_color_bubble"></div>
                                        <div class="solu_title">
                                            <div class="amount-view-btn-flex">
                                                <h3 class="rs-amount-flex">
                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                    <span class="amount-span">666</span>
                                                </h3>

                                                <span class="view-plan-text">View Plan</span>
                                            </div>
                                        </div>
                                        <div class="solu_description">
                                            <div class="validity-data-flex">
                                                <div class="validity-flex">
                                                    <span class="label-text">VALIDITY</span>
                                                    <span class="label-value">364 days</span>
                                                </div>

                                                <div class="validity-flex">
                                                    <span class="label-text">DATA</span>
                                                    <span class="label-value">1.5 GB/ Day</span>
                                                </div>
                                            </div>
                                            <div class="center-recharge-button">
                                                <button type="button" class="read_more_btn">Recharge</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="swiper-button-next swiper-navBtn"></div>
            <div class="swiper-button-prev swiper-navBtn"></div>
            <div class="swiper-pagination"></div>
        </div>
        <div class="container-fluid recharge-container">  <!-- Change inside content and class-->

            <div class="accordion accordion-item-container always-open  accordion-flush mt-3" id="accordionFlushExample">
                <div class="accordion-item plan-data-items">
                    <h2 class="accordion-header">
                        <div class="accordion-button accordion-button-custom collapsed accordion-btn" type="button" onclick="getId(this)" data-bs-toggle="collapse"  aria-expanded="false" aria-controls="card-body-1">
                            <div class="category-name-view-flex">
                                <div class="category-name-card">Daily Refreshing Plans</div>
                                <div class="view-more-icon-flex">
                                    <div class="view-all-card-text">View More</div>
                                    <img src="<c:url value='/resources/icons/chevron-down.svg'/>" class="chevron-arrows">
                                </div>
                            </div>
                        </div>
                    </h2>
                    <div id="card-body-1" class="accordion-collapse collapse show" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <div class="section_our_solution">
                                <div class="row recharge-cards-row">
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="accordion-item plan-data-items">
                    <h2 class="accordion-header">
                        <div class="accordion-button accordion-button-custom collapsed accordion-btn" type="button" onclick="getId(this)" data-bs-toggle="collapse"  aria-expanded="false" aria-controls="card-body-2">
                            <div class="category-name-view-flex">
                                <div class="category-name-card">Daily Refreshing Plans</div>
                                <div class="view-more-icon-flex">
                                    <div class="view-all-card-text">View More</div>
                                    <img src="<c:url value='/resources/icons/chevron-down.svg'/>" class="chevron-arrows">
                                </div>
                            </div>
                        </div>
                    </h2>
                    <div id="card-body-2" class="accordion-collapse collapse show" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <div class="section_our_solution">
                                <div class="row recharge-cards-row">
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="accordion-item plan-data-items">
                    <h2 class="accordion-header">
                        <div class="accordion-button accordion-button-custom collapsed accordion-btn" type="button" onclick="getId(this)" data-bs-toggle="collapse"  aria-expanded="false" aria-controls="card-body-3">
                            <div class="category-name-view-flex">
                                <div class="category-name-card">Daily Refreshing Plans</div>
                                <div class="view-more-icon-flex">
                                    <div class="view-all-card-text">View More</div>
                                    <img src="<c:url value='/resources/icons/chevron-down.svg'/>" class="chevron-arrows">
                                </div>
                            </div>
                        </div>
                    </h2>
                    <div id="card-body-3" class="accordion-collapse collapse show" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <div class="section_our_solution">
                                <div class="row recharge-cards-row">
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="accordion-item plan-data-items">
                    <h2 class="accordion-header">
                        <div class="accordion-button accordion-button-custom collapsed accordion-btn" type="button" onclick="getId(this)" data-bs-toggle="collapse"  aria-expanded="false" aria-controls="card-body-4">
                            <div class="category-name-view-flex">
                                <div class="category-name-card">Daily Refreshing Plans</div>
                                <div class="view-more-icon-flex">
                                    <div class="view-all-card-text">View More</div>
                                    <img src="<c:url value='/resources/icons/chevron-down.svg'/>" class="chevron-arrows">
                                </div>
                            </div>
                        </div>
                    </h2>
                    <div id="card-body-4" class="accordion-collapse collapse show" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <div class="section_our_solution">
                                <div class="row recharge-cards-row">
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-recharge col-12 col-sm-6 col-md-4">
                                        <div class="our_solution_category">
                                            <div class="solution_cards_box">
                                                <div class="solution_card">
                                                    <div class="hover_color_bubble"></div>
                                                    <div class="solu_title">
                                                        <div class="amount-view-btn-flex">
                                                            <h3 class="rs-amount-flex">
                                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">
                                                                <span class="amount-span">666</span>
                                                            </h3>

                                                            <span class="view-plan-text">View Plan</span>
                                                        </div>
                                                    </div>
                                                    <div class="solu_description responsive-card">
                                                        <div class="validity-data-flex">
                                                            <div class="validity-flex">
                                                                <span class="label-text">VALIDITY</span>
                                                                <span class="label-value">364 days</span>
                                                            </div>

                                                            <div class="validity-flex">
                                                                <span class="label-text">DATA</span>
                                                                <span class="label-value">1.5 GB/ Day</span>
                                                            </div>
                                                        </div>
                                                        <div class="center-recharge-button">
                                                            <button type="button" class="read_more_btn">Recharge</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



            </div>



            <%--            <div class="section_our_solution">--%>
            <%--                <div class="row recharge-cards-row">--%>
            <%--                    <div class="col-12 col-sm-6 col-md-4">--%>
            <%--                        <div class="our_solution_category">--%>
            <%--                            <div class="solution_cards_box">--%>
            <%--                                <div class="solution_card">--%>
            <%--                                    <div class="hover_color_bubble"></div>--%>
            <%--                                    <div class="solu_title">--%>
            <%--                                        <div class="amount-view-btn-flex">--%>
            <%--                                            <h3 class="rs-amount-flex">--%>
            <%--                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">--%>
            <%--                                                <span class="amount-span">666</span>--%>
            <%--                                            </h3>--%>

            <%--                                            <span class="view-plan-text">View Plan</span>--%>
            <%--                                        </div>--%>
            <%--                                    </div>--%>
            <%--                                    <div class="solu_description responsive-card">--%>
            <%--                                        <div class="validity-data-flex">--%>
            <%--                                            <div class="validity-flex">--%>
            <%--                                                <span class="label-text">VALIDITY</span>--%>
            <%--                                                <span class="label-value">364 days</span>--%>
            <%--                                            </div>--%>

            <%--                                            <div class="validity-flex">--%>
            <%--                                                <span class="label-text">DATA</span>--%>
            <%--                                                <span class="label-value">1.5 GB/ Day</span>--%>
            <%--                                            </div>--%>
            <%--                                        </div>--%>
            <%--                                        <div class="center-recharge-button">--%>
            <%--                                            <button type="button" class="read_more_btn">Recharge</button>--%>
            <%--                                        </div>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>

            <%--                    <div class="col-12 col-sm-6 col-md-4">--%>
            <%--                        <div class="our_solution_category">--%>
            <%--                            <div class="solution_cards_box">--%>
            <%--                                <div class="solution_card">--%>
            <%--                                    <div class="hover_color_bubble"></div>--%>
            <%--                                    <div class="solu_title">--%>
            <%--                                        <div class="amount-view-btn-flex">--%>
            <%--                                            <h3 class="rs-amount-flex">--%>
            <%--                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">--%>
            <%--                                                <span class="amount-span">666</span>--%>
            <%--                                            </h3>--%>

            <%--                                            <span class="view-plan-text">View Plan</span>--%>
            <%--                                        </div>--%>
            <%--                                    </div>--%>
            <%--                                    <div class="solu_description responsive-card">--%>
            <%--                                        <div class="validity-data-flex">--%>
            <%--                                            <div class="validity-flex">--%>
            <%--                                                <span class="label-text">VALIDITY</span>--%>
            <%--                                                <span class="label-value">364 days</span>--%>
            <%--                                            </div>--%>

            <%--                                            <div class="validity-flex">--%>
            <%--                                                <span class="label-text">DATA</span>--%>
            <%--                                                <span class="label-value">1.5 GB/ Day</span>--%>
            <%--                                            </div>--%>
            <%--                                        </div>--%>
            <%--                                        <div class="center-recharge-button">--%>
            <%--                                            <button type="button" class="read_more_btn">Recharge</button>--%>
            <%--                                        </div>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                    <div class="col-12 col-sm-6 col-md-4">--%>
            <%--                        <div class="our_solution_category">--%>
            <%--                            <div class="solution_cards_box">--%>
            <%--                                <div class="solution_card">--%>
            <%--                                    <div class="hover_color_bubble"></div>--%>
            <%--                                    <div class="solu_title">--%>
            <%--                                        <div class="amount-view-btn-flex">--%>
            <%--                                            <h3 class="rs-amount-flex">--%>
            <%--                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="person-filled-icon">--%>
            <%--                                                <span class="amount-span">666</span>--%>
            <%--                                            </h3>--%>

            <%--                                            <span class="view-plan-text">View Plan</span>--%>
            <%--                                        </div>--%>
            <%--                                    </div>--%>
            <%--                                    <div class="solu_description responsive-card">--%>
            <%--                                        <div class="validity-data-flex">--%>
            <%--                                            <div class="validity-flex">--%>
            <%--                                                <span class="label-text">VALIDITY</span>--%>
            <%--                                                <span class="label-value">364 days</span>--%>
            <%--                                            </div>--%>

            <%--                                            <div class="validity-flex">--%>
            <%--                                                <span class="label-text">DATA</span>--%>
            <%--                                                <span class="label-value">1.5 GB/ Day</span>--%>
            <%--                                            </div>--%>
            <%--                                        </div>--%>
            <%--                                        <div class="center-recharge-button">--%>
            <%--                                            <button type="button" class="read_more_btn">Recharge</button>--%>
            <%--                                        </div>--%>
            <%--                                    </div>--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </div>--%>

        </div>

        <!-- Modal -->
        <div class="modal fade" id="filterModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">

                    <div class="modal-body">
                        <div class="main-modal-container">
                            <div class="modal-filter-icon-flex">
                                <img src="<c:url value='/resources/icons/filter-filled-animated.svg'/>" class="modal-filter-icon">
                                <span class="modal-filter-plans">Filter Plans</span>
                            </div>

                            <div class="accordion accordion-flush mt-4" id="accordionFlushExample1">
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                            Daily Refreshing Plans
                                        </button>
                                    </h2>
                                    <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample1">
                                        <div class="accordion-body">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="1" name="ckb-1">
                                                            <span></span>
                                                            1 GB/Day
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="2" name="ckb-2">
                                                            <span></span>
                                                            1.5 GB/Day
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            2 GB/Day
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            2.5 GB/Day
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            3 GB/Day
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFour" aria-expanded="false" aria-controls="flush-collapseFour">
                                            Fixed Data Plans
                                        </button>
                                    </h2>
                                    <div id="flush-collapseFour" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample1">
                                        <div class="accordion-body">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="justify-col col-12 col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="1" name="ckb-1">
                                                            <span></span>
                                                            100 - 200 GB Data
                                                        </label>
                                                    </div>
                                                    <div class="justify-col col-12 col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="2" name="ckb-2">
                                                            <span></span>
                                                            200 - 300 GB Data
                                                        </label>
                                                    </div>
                                                    <div class="justify-col col-12 col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            300 GB Data & More
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                                            Charges
                                        </button>
                                    </h2>
                                    <div id="flush-collapseTwo" class="accordion-collapse collapse charge-row" data-bs-parent="#accordionFlushExample1">
                                        <div class="accordion-body">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="1" name="ckb-1">
                                                            <span></span>
                                                            <div class="only-amount-flex">
                                                                <div class="amount-rs-icon-flex">
                                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                    <div>149</div>
                                                                </div> <div>&nbsp-&nbsp</div>
                                                                <div class="amount-rs-icon-flex">
                                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                    <div>300</div>
                                                                </div>
                                                            </div>

                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="2" name="ckb-2">
                                                            <span></span>
                                                            <div class="only-amount-flex">
                                                                <div class="amount-rs-icon-flex">
                                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                    <div>301</div>
                                                                </div> <div>&nbsp-&nbsp</div>
                                                                <div class="amount-rs-icon-flex">
                                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                    <div>600</div>
                                                                </div>
                                                            </div>
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            <div class="only-amount-flex">
                                                                <div class="amount-rs-icon-flex">
                                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                    <div>601</div>
                                                                </div> <div>&nbsp-&nbsp</div>
                                                                <div class="amount-rs-icon-flex">
                                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                    <div>900</div>
                                                                </div>
                                                            </div>
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            <div class="only-amount-flex">
                                                                <div class="amount-rs-icon-flex">
                                                                    <img src="<c:url value='/resources/icons/rupees.svg'/>" class="">
                                                                    <div>900</div>
                                                                </div> <div>&nbsp&&nbsp</div>
                                                                <div class="amount-rs-icon-flex">
                                                                    <div>above</div>
                                                                </div>
                                                            </div>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                                            Validity
                                        </button>
                                    </h2>
                                    <div id="flush-collapseThree" class="accordion-collapse collapse validity-row"  data-bs-parent="#accordionFlushExample1">
                                        <div class="accordion-body">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="1" name="ckb-1">
                                                            <span></span>
                                                            10 - 30 Days
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="2" name="ckb-2">
                                                            <span></span>
                                                            31 - 60 Days
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            61 - 90 Days
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            Annual Plans
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed custom-icon" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFive" aria-expanded="false" aria-controls="flush-collapseFive">
                                            Add On Data Packs
                                        </button>
                                    </h2>
                                    <div id="flush-collapseFive" class="accordion-collapse collapse validity-row"  data-bs-parent="#accordionFlushExample1">
                                        <div class="accordion-body">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="1" name="ckb-1">
                                                            <span></span>
                                                            1 - 25 GB
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" value="2" name="ckb-2">
                                                            <span></span>
                                                            25 - 50 GB
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            50 - 100 GB
                                                        </label>
                                                    </div>
                                                    <div class="col-xs col-sm-6">
                                                        <label class="checkbox-btn">
                                                            <input type="checkbox" >
                                                            <span></span>
                                                            100 GB & more
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary apply-filter-btn" data-bs-dismiss="modal">Apply Filter</button>
                        <button type="button" class="btn btn-primary clear-filter-btn">Clear Filter</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#view-plan-modal">
            View Plan
        </button>

        <!-- Modal -->
        <div class="modal fade" id="view-plan-modal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="view-plan-header modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">
                            <div class="modal-amount-icon-flex">
                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="modal-rs-icon">
                                <span>666</span>
                            </div>
                        </h1>

                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body view-plan-body">
                        <div class="plan-detials-modal-text">Plan Details</div>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <td>Pack Validity</td>
                                    <td>84 days</td>
                                </tr>
                                <tr>
                                    <td>Total Data</td>
                                    <td>126 GB</td>
                                </tr>
                                <tr>
                                    <td><span class="relative-class">Data at high speed<span class="astreik-class">*</span></span></td>
                                    <td >1.5 GB/Day</td>
                                </tr>
                                <tr>
                                    <td>Voice Call</td>
                                    <td>Unlimited</td>
                                </tr>
                                <tr>
                                    <td>SMS</td>
                                    <td>100 SMS/Day</td>
                                </tr>
                                <tr>
                                    <td><span class="relative-class">Additional Data<span class="astreik-class">*</span></span></td>
                                    <td>10 GB</td>
                                </tr>
                                </tbody>
                            </table>

                            <div class="notes-text-modal">- Notes</div>
                            <div class="notes-flex">
                                <span class="notes-view-modal">- Post which unlimited @ 64 Kbps</span>
                                <span class="notes-view-modal">- Unlimited 5G data for eligible subscribers</span>
                                <span class="notes-view-modal">- Additional Data will be valid till the base plan is not expired.</span>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer view-plan-modal">
                        <button class="modal-recharge-button">Recharge</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#recharge-plan">
            Recharge Now
        </button>

        <!-- Modal -->
        <div class="modal recharge-plan-modal fade fade-scale in" id="recharge-plan" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog recharge-plan-modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content recharge-plan-modal-content">
                    <div class="modal-header recharge-plan-modal-header">
                        <h1 class="modal-title recharge-plan-modal-title fs-5" id="staticBackdropLabel">Recharge Details</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body recharge-plan-modal-body">

                        <div class="accordion accordion-flush" id="accordion-plan-details">
                            <div class="accordion-item">
                                <h2 class="accordion-header">
                                    <button class="accordion-button plan-details-view-btn custom-icon collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#recharge-modal-plan-details" aria-expanded="false" aria-controls="flush-collapseOne">
                                        Plan Details
                                    </button>
                                </h2>
                                <div id="recharge-modal-plan-details" class="accordion-collapse collapse" data-bs-parent="#accordion-plan-details">
                                    <div class="accordion-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tbody>
                                                <tr>
                                                    <td>Pack Validity</td>
                                                    <td>84 days</td>
                                                </tr>
                                                <tr>
                                                    <td>Total Data</td>
                                                    <td>126 GB</td>
                                                </tr>
                                                <tr>
                                                    <td><span class="relative-class">Data at high speed<span class="astreik-class">*</span></span></td>
                                                    <td >1.5 GB/Day</td>
                                                </tr>
                                                <tr>
                                                    <td>Voice Call</td>
                                                    <td>Unlimited</td>
                                                </tr>
                                                <tr>
                                                    <td>SMS</td>
                                                    <td>100 SMS/Day</td>
                                                </tr>
                                                <tr>
                                                    <td><span class="relative-class">Additional Data<span class="astreik-class">*</span></span></td>
                                                    <td>10 GB</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item">
                                <h2 class="accordion-header">
                                    <button class="accordion-button plan-details-view-btn custom-icon collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#cupon-code" aria-expanded="false" aria-controls="flush-collapseOne">
                                        Add Cupon Code <span class="applied-btn">Applied</span>
                                    </button>
                                </h2>
                                <div id="cupon-code" class="accordion-collapse collapse" data-bs-parent="#accordion-plan-details">
                                    <div class="accordion-body">
                                        <div class="cupon-text-code-flex">
                                            <div class="add-cupon-text">Cupon Code :</div>
                                            <input type="text" class="cupon-code-input">
                                            <span class="verify-code" onclick="verifyCuponCode(true)">Apply</span>
                                            <span class="d-none remove-cupon" onclick="verifyCuponCode(false)">Remove</span>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="recharge-summary-flex">
                            <%--                            <div class="recharge-summary-top">--%>
                            <%--                                <div class="recharge-summary-title">Recharge Summary</div>--%>
                            <%--                            </div>--%>
                            <div class="summary-details">
                                <div class="summary-row-flex">
                                    <div class="summary-title">Plan Amount</div>
                                    <div class="summary-value-flex">
                                        <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                        <div class="summary-value">569.00</div>
                                    </div>
                                </div>

                                <%--                                <div class="summary-row-flex">--%>
                                <%--                                    <div class="summary-title">Wallet Money Used</div>--%>
                                <%--                                    <div class="summary-value-flex">--%>
                                <%--                                        <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">--%>
                                <%--                                        <div class="summary-value">100.00</div>--%>
                                <%--                                    </div>--%>
                                <%--                                </div>--%>

                                <div class="summary-row-flex">
                                    <div class="summary-title">Discount Applied</div>
                                    <div class="summary-value-flex">
                                        <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                        <div class="summary-value">25.05</div>
                                    </div>
                                </div>

                                <div class="summary-row-flex">
                                    <div class="summary-title">Tax Amount</div>
                                    <div class="summary-value-flex">
                                        <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                        <div class="summary-value">38.50</div>
                                    </div>
                                </div>

                                <hr class="summary-recharge-hr">

                                <div class="summary-row-flex final-price-flex">
                                    <div class="summary-title">Total Amount</div>
                                    <div class="summary-value-flex ">
                                        <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                        <div class="summary-value">630.50</div>
                                    </div>
                                </div>


                                <div class="wallet-card">
                                    <div class="wallet-details-flex">
                                        <div class="wallet-left-side">
                                            <img src="<c:url value='/resources/icons/wallet.svg'/>" class="wallet-wallet-icon">
                                            <div class="wallet-balance-flex">
                                                <span class="avaiable-amount-text">Amount</span>
                                                <span class="wallet-total-amount-span">
                                                <img src="<c:url value='/resources/icons/rupees.svg'/>" class="wallet-rs-icon">
                                                <span class="wallet-amount">500</span>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="wallet-right-side">
                                            <img src="<c:url value='/resources/icons/rupees.svg'/>" class="input-rs-icon">
                                            <input type="text" class="amount-input" placeholder="Amount"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="summary-row-flex final-price-div">
                                    <div class="summary-title">Payable Amount</div>
                                    <div class="summary-value-flex">
                                        <img src="<c:url value='/resources/icons/rupees.svg'/>" class="summary-rs-icon">
                                        <div class="summary-value">38.50</div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>


                    <div class="modal-footer recharge-plan-modal-footer">
                        <button type="button" class="pay-now" data-bs-dismiss="modal">Pay <span>666.00</span></button>
                    </div>
                </div>
            </div>
        </div>

    </main>
</div>

<!-- Swiper JS -->
<script src="//cdn.jsdelivr.net/gh/freeps2/a7rarpress@main/swiper-bundle.min.js"></script>

<!-- JavaScript -->
<!--Uncomment this line-->
<script src="<c:url value='/resources/js/recharge-tab.js' />"></script>
<script src="//cdn.jsdelivr.net/gh/freeps2/a7rarpress@main/script.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="<c:url value='/resources/js/main.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
</body>

<style>


    /*.fade-scale {*/
    /*    transform: scale(0);*/
    /*    opacity: 0;*/
    /*    -webkit-transition: all .25s linear;*/
    /*    -o-transition: all .25s linear;*/
    /*    transition: all .25s linear;*/
    /*}*/
    /*.fade-scale.in {*/
    /*    opacity: 1;*/
    /*    transform: scale(1);*/
    /*}*/
    /*.fade-scale .modal-dialog {*/
    /*    position: absolute;*/
    /*    left: 0;*/
    /*    right: 0;*/
    /*    top: 50%;*/
    /*    transform: translateY(-50%) !important;*/
    /*}*/

    /*.applied-btn{*/
    /*    color: white;*/
    /*    background-color: red;*/
    /*    padding: 6px 12px;*/
    /*    border: 0px;*/
    /*    width: 80px;*/
    /*    border-radius: 25px;*/
    /*    transform: scale(0.62) translateX(-10px);*/
    /*}*/
    /*.verify-code, .remove-cupon{*/
    /*    color: #0d336a;*/
    /*    font-size: 13px;*/
    /*    position: absolute;*/
    /*    top: 2px;*/
    /*    right: 0px;*/
    /*}*/

    /*.add-cupon-text{*/
    /*    text-wrap: nowrap;*/
    /*}*/

    /*.cupon-text-code-flex{*/
    /*    position: relative;*/
    /*    display: flex;*/
    /*    justify-content: flex-start;*/
    /*    align-items: center;*/
    /*    gap: 15px;*/
    /*}*/

    /*.accordion-button:not(.collapsed) {*/
    /*    border: none;*/
    /*    box-shadow: none;*/
    /*}*/

    /*.cupon-code-input{*/
    /*    outline: none;*/
    /*    width: 100%;*/
    /*    border: 0px;*/
    /*    border-bottom: 1px solid lightgrey ;*/
    /*}*/

    /*.recharge-plan-modal-body .accordion-item{*/
    /*    border: none;*/
    /*}*/

    /*.pay-now{*/
    /*    border: 1px solid #3750eb;*/
    /*    border-radius: 25px;*/
    /*    padding: 6px 12px;*/
    /*    width: 125px;*/
    /*    color: white;*/
    /*    background-color: #3750eb;*/
    /*}*/

    /*.final-price-div{*/
    /*    margin-top: 20px;*/
    /*}*/

    /*.final-price-div .summary-title{*/
    /*    font-size: 20px;*/
    /*}*/

    /*.final-price-div .summary-value-flex{*/
    /*    transform: scale(1.24);*/
    /*}*/

    /*.wallet-right-side{*/
    /*    display: flex;*/
    /*    justify-content: flex-end;*/
    /*    align-items: center;*/
    /*}*/

    /*.amount-input {*/
    /*    box-sizing: border-box;*/
    /*    text-align: right; !* Aligns the input text to the right *!*/
    /*    !*direction: rtl; !* Reverses the text direction *!*!*/
    /*}*/

    /* Reverse the text alignment for the actual input text */
    /* .amount-input::placeholder {*/
    /*     text-align: right;*/
    /*     padding-left: 10px;*/

    /* }*/

    /* .amount-input{*/
    /*     overflow: none;*/
    /*     width: 80px;*/
    /*     border: 0px;*/
    /*     outline: none;*/
    /*     border-bottom:  1px solid lightgray;*/
    /* }*/

    /* .amount-input:focus-visible {*/
    /*     border-bottom:  1px solid lightgray;*/
    /* }*/

    /* .wallet-total-amount-span{*/
    /*     display: flex;*/
    /*     justify-content: flex-start;*/
    /*     margin-left: -5px;*/
    /* }*/

    /* .wallet-balance-flex{*/
    /*     display: flex;*/
    /*     position: relative;*/
    /*     text-wrap: nowrap;*/
    /* }*/

    /* .avaiable-amount-text{*/
    /*     width: 100%;*/
    /*     position: absolute;*/
    /*     top: -15px;*/
    /*     left: 0px;*/
    /*     font-size: 13px;*/
    /* }*/

    /* .wallet-wallet-icon{*/
    /*     height: 33px;*/
    /*     width: auto;*/
    /* }*/

    /* .wallet-left-side{*/
    /*     display: flex;*/
    /*     justify-content: flex-start;*/
    /*     gap: 5px;*/
    /*     align-items: end;*/
    /* }*/


    /* .wallet-card{*/
    /*     margin-top: 15px;*/
    /*     width: 100%;*/
    /* }*/

    /* .wallet-details-flex{*/
    /*     width: 100%;*/
    /*     display: flex;*/
    /*     justify-content: space-between;*/
    /*     align-items: center;*/
    /* }*/

    /* .final-price-flex{*/
    /*     margin-top: -15px;*/
    /* }*/

    /* .summary-recharge-hr{*/
    /*     width: 100%;*/
    /*     margin-top: 0px;*/
    /*     border: 1px solid black;*/
    /* }*/

    /* .recharge-summary-top{*/
    /*     display: flex;*/
    /*     justify-content: center;*/
    /* }*/

    /* .summary-value-flex{*/
    /*     display: flex;*/
    /*     justify-content: flex-start;*/
    /*     align-items: center;*/
    /* }*/

    /* .summary-details{*/
    /*     display: flex;*/
    /*     flex-direction: column;*/
    /*     align-items: flex-start;*/
    /*     row-gap: 10px;*/
    /*     width: 100%;*/
    /* }*/

    /* .summary-row-flex{*/
    /*     display: flex;*/
    /*     justify-content: space-between;*/
    /*     align-items: center;*/
    /*     width: 100%;*/
    /*     row-gap: 10px;*/
    /* }*/

    /* .recharge-plan-modal-header .btn-close{*/
    /*     padding-top: 20px;*/
    /* }*/

    /* .recharge-summary-flex{*/
    /*     display: flex;*/
    /*     flex-direction: column;*/
    /*     justify-content: center;*/
    /*     align-items: flex-start;*/
    /*     row-gap: 10px;*/
    /*     padding: 0px 20px 0px 20px;*/
    /* }*/

    /* .plan-details-view-btn{*/
    /*     font-size: 16px;*/
    /*     padding: 6px 20px;*/
    /* }*/

    /* .recharge-plan-modal .btn-close{*/
    /*     filter: invert(99%) sepia(1%) saturate(3%) hue-rotate(193deg) brightness(200%) contrast(100%);*/
    /* }*/

    /* .recharge-plan-modal-header{*/
    /*     background-color: #3750eb;*/
    /*     color: white;*/
    /* }*/

    /* .modal-footer view-plan-modal{*/
    /*     display: flex;*/
    /*     justify-content: center;*/
    /*     align-items: center;*/
    /* }*/

    /* .modal-recharge-button{*/
    /*     padding: 6px 12px;*/
    /*     border-radius: 25px;*/
    /*     color: white;*/
    /*     background-color: #3750eb;*/
    /*     border: 1px solid #3750eb;*/
    /*     width: 150px;*/
    /*     font-size: 18px;*/
    /* }*/

    /* .notes-text-modal{*/
    /*     font-size: 15px;*/
    /*     margin-bottom: 4px;*/
    /* }*/

    /* .notes-flex{*/
    /*     display: flex;*/
    /*     flex-direction: column;*/
    /*     row-gap: 5px;*/
    /*     margin-bottom: 15px;*/
    /* }*/

    /* .notes-flex span{*/
    /*     font-size: 11px;*/
    /*     color: #7d7d7d;*/
    /* }*/

    /* .relative-class{*/
    /*     position: relative;*/
    /* }*/

    /* .astreik-class{*/
    /*     position: absolute;*/
    /*     top: -7px;*/
    /*     right: -10px;*/
    /* }*/

    /* .plan-detials-modal-text{*/
    /*     font-size: 20px;*/
    /*     padding-bottom: 10px;*/
    /* }*/

    /* .view-plan-body{*/
    /*     padding: 37px;*/
    /*     padding-bottom: 0px;*/
    /* }*/


    /* */
    /* .view-plan-header{*/
    /*     border: 0px;*/
    /*     padding: 25px 25px 0px 25px;*/
    /* }*/

    /* .modal-amount-icon-flex{*/
    /*     padding-top: 25px;*/
    /*     padding-left: 20px;*/
    /*     display: flex;*/
    /*     justify-content: flex-start;*/
    /*     align-items: center;*/
    /*     transform: scale(2);*/
    /* }*/

    /* .always-open .accordion-collapse {*/
    /*     display: block;*/
    /* }*/

    /* .accordion-item-container .accordion-item{*/
    /*     border: 0px;*/
    /* }*/

    /* .accordion-button-custom{*/
    /*     padding-left: 12px;*/
    /*     padding-right: 0px;*/
    /* }*/

    /* .chevron-arrows{*/
    /*     transition: transform 0.5s ease-in-out;*/
    /*     height: 22px;*/
    /*     width: auto;*/
    /* }*/

    /* .chevron-arrows.rotated {*/
    /*     transform: rotate(180deg);*/
    /* }*/

    /* .view-more-icon-flex{*/
    /*     display: flex;*/
    /*     justify-content: flex-start;*/
    /*     align-items: center;*/
    /*     gap: 10px;*/
    /* }*/

    /* .category-name-card{*/
    /*     font-size: 28px;*/
    /*     font-weight: 600;*/
    /* }*/

    /* .category-name-view-flex{*/
    /*     width: 100%;*/
    /*     display: flex;*/
    /*     justify-content: space-between;*/
    /*     align-items: center;*/
    /* }*/

    /* .recharge-cards-row >:nth-child(n+4) {*/
    /*     display: none;*/
    /* }*/

    /* .accordion-collapse {*/
    /*     transition: height 0.3s ease-in-out, opacity 0.3s ease-in-out;*/
    /* }*/

    /* .accordion-collapse.collapse {*/
    /*     height: 0;*/
    /*     opacity: 0;*/
    /* }*/

    /* .accordion-collapse.show {*/
    /*     height: auto;*/
    /*     opacity: 1;*/
    /* }*/

    /* .plan-data-items .accordion-btn{*/
    /*     background-color: transparent;*/
    /* }*/

    /* .plan-data-items .accordion-body{*/
    /*     padding-left: 0;*/
    /*     padding-right: 0;*/
    /* }*/

    /* .plan-data-items{*/
    /*     background-color: transparent;*/
    /*     !*margin-bottom: 7px;*!*/
    /*     !*box-shadow: rgba(0, 0, 0, 0.15) 0px 15px 25px, rgba(0, 0, 0, 0.05) 0px 5px 10px; *!*/

    /* }*/


    /* .filter-count {*/
    /*     display: flex;*/
    /*     justify-content: center;*/
    /*     align-items: center;*/
    /*     height: 20px;*/
    /*     width: 20px;*/
    /*     border-radius: 15px;*/
    /*     background-color: #f24b4b;*/
    /*     color: white;*/
    /*     position: absolute;*/
    /*     top: -4px;*/
    /*     right: -4px;*/
    /* }*/
    /* .apply-filter-btn{*/
    /*     background-color: #3750eb;*/
    /* }*/

    /* .apply-filter-btn:hover{*/
    /*     background-color: #0e2ad3;*/
    /* }*/

    /* .clear-filter-btn{*/
    /*     border: 1px solid #3750eb;*/
    /*     background-color: transparent;*/
    /*     color: #3750eb;*/
    /* }*/

    /* .clear-filter-btn:hover{*/
    /*     border: 1px solid #3750eb;*/
    /*     background-color: #3750eb;*/
    /*     color: white;*/
    /* }*/

    /* .modal-footer{*/
    /*     justify-content: center;*/
    /* }*/

    /* .validity-row .accordion-body, .charge-row .accordion-body{*/
    /*     padding-left: 0px;*/
    /*     padding-right: 0px;*/
    /* }*/

    /* .amount-rs-icon-flex{*/
    /*     display: flex;*/
    /*     align-items: center;*/
    /* }*/

    /* .only-amount-flex{*/
    /*     display: flex;*/
    /*     justify-content: flex-start;*/
    /*     align-items: center;*/
    /* }*/
    /* .justify-col{*/
    /*     display: flex;*/
    /*     justify-content: flex-start;*/
    /* }*/

    /* @media screen and (max-width: 575px) {*/
    /*     .col-xs{*/
    /*         flex: 0 0 50%;*/
    /*         max-width: 50%;*/
    /*     }*/
    /* }*/

    /* @media screen and (max-width: 410px) {*/
    /*     .col-xs{*/
    /*         display: flex;*/
    /*         justify-content: flex-start;*/
    /*         flex: 0 0 100%;*/
    /*         max-width: 100%;*/
    /*     }*/
    /* }*/

    /* .accordion-button::after, .accordion-button:not(.collapsed)::after {*/
    /*     background-image: none; !* Reset the default icon *!*/
    /* }*/

    /* .accordion-button.custom-icon::after {*/
    /*     content: url("/uninor/resources/icons/minus-animated.svg"); !* Custom down arrow icon *!*/
    /*     transform: scale(1.2);*/
    /*     filter: invert(16%) sepia(52%) saturate(2131%) hue-rotate(198deg) brightness(90%) contrast(106%);*/
    /* }*/

    /* .accordion-button.custom-icon.collapsed::after {*/
    /*     content: url("/uninor/resources/icons/plus-animated.svg");*/
    /*     filter: invert(0%) sepia(100%) saturate(7500%) hue-rotate(17deg) brightness(113%) contrast(114%);*/
    /*     transform: scale(1.2);*/
    /* }*/

    /* .accordion-button:focus {*/
    /*     box-shadow: none;*/
    /* }*/

    /* .accordion-button:not(.collapsed) {*/
    /*     color: var(--bs-accordion-active-color);*/
    /*     background-color: transparent;*/
    /*     box-shadow: inset 0 calc(-1 * var(--bs-accordion-border-width)) 0 var(--bs-accordion-border-color);*/
    /* }*/

    /* .modal-filter-icon{*/
    /*     height: 30px;*/
    /*     width: auto;*/
    /* }*/

    /* .modal-filter-plans{*/
    /*     font-size: 22px;*/
    /* }*/

    /* .main-modal-container{*/
    /*     padding: 15px;*/
    /* }*/

    /* .modal-filter-icon-flex{*/
    /*     display: flex;*/
    /*     justify-content: flex-start;*/
    /*     align-items: center;*/
    /*     justify-content: center;*/
    /* }*/

    /* .modal-content {*/
    /*     border-radius: 20px;*/
    /* }*/

    /* !* Checkbox Input *!*/
    /* .checkbox-btn span {*/
    /*     display: flex;*/
    /*     align-items: center;*/
    /*     justify-content: center;*/
    /*     width: 20px;*/
    /*     height: 20px;*/
    /*     background-color: #ddd;*/
    /*     transition: 0.3s;*/
    /*     border-radius: 4px;*/
    /* }*/

    /* .checkbox-btn span::before {*/
    /*     content: '';*/
    /*     display: inline-block;*/
    /*     width: 24px;*/
    /*     height: 12px;*/
    /*     border-bottom: 3px solid #fff;*/
    /*     border-left: 3px solid #fff;*/
    /*     transform: scale(0) rotate(-45deg);*/
    /*     position: relative;*/
    /*     bottom: 4px;*/
    /*     transition: 0.6s;*/
    /* }*/

    /* .checkbox-btn input {*/
    /*     display: none;*/
    /* }*/

    /* .checkbox-btn input:checked ~ span {*/
    /*     background-color: #3750eb;*/
    /* }*/

    /* .checkbox-btn input:checked ~ span::before {*/
    /*     transform: scale(0.65) rotate(-45deg);*/
    /* }*/

    /*.checkbox-btn {*/
    /*    display: flex;*/
    /*    flex-direction: row;*/
    /*    align-items: center;*/
    /*    !*justify-content: center;*!*/
    /*    gap: 10px*/
    /* }*/

</style>

<script>

    // function verifyCuponCode(bool){
    //     if(bool){
    //         $(".verify-code").addClass('d-none')
    //         $(".remove-cupon").removeClass('d-none')
    //     }else {
    //         $(".remove-cupon").addClass('d-none')
    //         $(".verify-code").removeClass('d-none')
    //     }
    //
    // }
    //
    // let toggleState = true;
    // function getId(element){
    //
    //     var ariaControls = element.getAttribute('aria-controls');
    //     var mainItem = $("#" + ariaControls)
    //     var textChange = $(element).find('.view-all-card-text');
    //     if(toggleState){
    //         mainItem.find('.recharge-cards-row').children().css('display', 'block');
    //         textChange.html("View Less")
    //     }
    //     else{
    //         mainItem.find('.recharge-cards-row').children().slice(3).css('display', 'none');
    //         textChange.html("View More")
    //     }
    //     toggleState = !toggleState;
    //     const chevronDownArrow = $(element).find('.chevron-arrows');
    //     chevronDownArrow.toggleClass('rotated');
    // }
    //
    // var swiper = new Swiper(".slide-content", {
    //     slidesPerView: 3,
    //     spaceBetween: 25,
    //     loop: true,
    //     centerSlide: 'true',
    //     fade: 'true',
    //     grabCursor: 'true',
    //     pagination: {
    //         el: ".swiper-pagination",
    //         clickable: true,
    //         dynamicBullets: true,
    //     },
    //     navigation: {
    //         nextEl: ".swiper-button-next",
    //         prevEl: ".swiper-button-prev",
    //     },
    //     autoplay: {
    //         delay: 5000, // delay between slides in milliseconds
    //         disableOnInteraction: true, // autoplay will not be disabled after user interaction
    //     },
    //     breakpoints:{
    //         0: {
    //             slidesPerView: 1,
    //         },
    //         520: {
    //             slidesPerView: 2,
    //         },
    //         950: {
    //             slidesPerView: 3,
    //         },
    //     },
    // });
    //
    // document.querySelectorAll('.solution_card').forEach(card => {
    //     card.addEventListener('mouseover', () => {
    //         swiper.autoplay.stop();
    //     });
    //     card.addEventListener('mouseout', () => {
    //         swiper.autoplay.start();
    //     });
    // });

</script>

<style>

    /*.filter-icon-flex{*/
    /*    position: relative;*/
    /*    display: flex;*/
    /*    align-items: center;*/
    /*    gap: 5px;*/
    /*    color: #242424;*/
    /*}*/

    /*.filter-btn{*/
    /*    position: absolute;*/
    /*    top: 3px;*/
    /*    right: 69px;*/
    /*    !*border: 1px solid #9daaf9;*!*/
    /*    border: 1px solid #242424;*/
    /*    border-radius: 25px;*/
    /*    padding: 6px 14px 3px 16px;*/
    /*}*/

    /*.filter-btn:hover{*/
    /*    background-color: #3750eb;*/
    /*    border: 1px solid #3750eb;*/
    /*}*/



    /*.filter-btn:hover .filter-text{*/
    /*    color: white;*/
    /*}*/

    /*.filter-btn:hover img{*/
    /*    filter: invert(97%) sepia(97%) saturate(10%) hue-rotate(153deg) brightness(102%) contrast(103%);*/
    /*}*/


    /*.filter-text{*/
    /*    font-size: 18px;*/
    /*}*/

    /*.filter-icon{*/
    /*    height: 22px;*/
    /*    width: auto;*/
    /*}*/

    /*.recharge-text{*/
    /*    position: absolute;*/
    /*    top: 3px;*/
    /*    left: 63px;*/
    /*    font-size: 30px;*/
    /*    font-weight: 600;*/
    /*    color: #242424;*/
    /*}*/

    /*.swiper-navBtn{*/
    /*    display: none;*/
    /*}*/

    /*.swiper-wrapper{*/
    /*    height:auto;*/
    /*}*/

    /*.swiper-wrapper .swiper-slide{*/
    /*    background-color: transparent;*/
    /*    border: 0px;*/
    /*}*/

    /*.slide-container{*/
    /*    !*max-width: 1120px;*!*/
    /*    position: relative;*/
    /*    width: 80%;*/
    /*    padding: 60px 0px 40px 0px;*/
    /*}*/
    /*.slide-content{*/
    /*    margin: 0 40px;*/
    /*    overflow: hidden;*/
    /*    border-radius: 25px;*/
    /*}*/
    /*.image-content,*/
    /*.card-content{*/
    /*    display: flex;*/
    /*    flex-direction: column;*/
    /*    align-items: center;*/
    /*    padding: 10px 14px;*/
    /*}*/
    /*.image-content{*/
    /*    position: relative;*/
    /*    row-gap: 5px;*/
    /*    padding: 25px 0;*/
    /*}*/
    /*.overlay{*/
    /*    position: absolute;*/
    /*    left: 0;*/
    /*    top: 0;*/
    /*    height: 100%;*/
    /*    width: 100%;*/
    /*    background-color: #4070F4;*/
    /*    border-radius: 25px 25px 0 25px;*/
    /*}*/
    /*.overlay::before,*/
    /*.overlay::after{*/
    /*    content: '';*/
    /*    position: absolute;*/
    /*    right: 0;*/
    /*    bottom: -40px;*/
    /*    height: 40px;*/
    /*    width: 40px;*/
    /*    background-color: #4070F4;*/
    /*}*/
    /*.overlay::after{*/
    /*    border-radius: 0 25px 0 0;*/
    /*    background-color: #FFF;*/
    /*}*/

    /*.swiper-navBtn{*/
    /*    color: #6E93f7;*/
    /*    transition: color 0.3s ease;*/
    /*}*/
    /*.swiper-navBtn:hover{*/
    /*    color: #4070F4;*/
    /*}*/
    /*.swiper-navBtn::before,*/
    /*.swiper-navBtn::after{*/
    /*    font-size: 38px;*/
    /*}*/
    /*.swiper-button-next{*/
    /*    right: 0;*/
    /*}*/
    /*.swiper-button-prev{*/
    /*    left: 0;*/
    /*}*/
    /*.swiper-pagination-bullet{*/
    /*    background-color: #6E93f7;*/
    /*    opacity: 1;*/
    /*}*/
    /*.swiper-pagination-bullet-active{*/
    /*    background-color: #4070F4;*/
    /*}*/

    /*@media screen and (max-width: 767px) {*/

    /*    .accordion-button-custom {*/
    /*        padding-left: 12px;*/
    /*    }*/
    /*}*/

    /*@media screen and (max-width: 768px) {*/

    /*    .accordion-button-custom{*/
    /*        padding-left: 0px;*/
    /*    }*/

    /*    .slide-content{*/
    /*        margin: 0 10px;*/
    /*    }*/
    /*    .swiper-navBtn{*/
    /*        display: none;*/
    /*    }*/
    /*    .slide-content{*/
    /*        overflow: visible;*/
    /*    }*/

    /*}*/

    /*@media screen and (max-width: 607px) {*/
    /*    .card .col-12{*/
    /*        transform: scale(0.95);*/
    /*    }*/
    /*}*/

</style>

<style>

    /*.swiper-slide .validity-data-flex{*/
    /*    gap: 20%;*/
    /*}*/

    /*.view-plan-text:hover{*/
    /*    color: #00005c;*/
    /*    transform: scale(1.1);*/
    /*    transition: 0.7s ease-in-out;*/
    /*}*/

    /*.view-btn{*/
    /*    border: 0;*/
    /*    border-radius: 15px;*/
    /*    background: linear-gradient( 140deg, #42c3ca 0%, #42c3ca 50%, #42c3cac7 75% ) !important;*/
    /*    color: #fff;*/
    /*    font-weight: 500;*/
    /*    font-size: 1rem;*/
    /*    padding: 5px 16px;*/
    /*}*/

    /*.amount-view-btn-flex{*/
    /*    display: flex;*/
    /*    justify-content: space-between;*/
    /*    align-items: center;*/
    /*}*/

    /*.recharge-container{*/
    /*    width: 75%;*/
    /*}*/

    /*.rs-amount-flex{*/
    /*    display: flex;*/
    /*    justify-content: flex-start;*/
    /*    align-items: center;*/
    /*}*/

    /*.validity-data-flex{*/
    /*    display: flex;*/
    /*    justify-content: flex-start;*/
    /*    gap: 30%;*/
    /*    padding-left: 5px;*/
    /*    margin-bottom: 25px;*/
    /*    margin-top: 15px;*/
    /*}*/

    /*.validity-flex{*/
    /*    display: flex;*/
    /*    flex-direction: column;*/

    /*}*/

    /*.center-recharge-button{*/
    /*    display: flex;*/
    /*    justify-content: flex-start;*/
    /*    align-items: center;*/
    /*}*/

    /*.section_our_solution .row {*/
    /*    align-items: center;*/
    /*}*/

    /*.our_solution_category {*/
    /*    display: flex;*/
    /*    flex-direction: row;*/
    /*    flex-wrap: wrap;*/
    /*}*/
    /*.our_solution_category .solution_cards_box {*/
    /*    display: flex;*/
    /*    flex-direction: column;*/
    /*    justify-content: center;*/
    /*}*/

    /*.solution_cards_box{*/
    /*    height: 100%;*/
    /*}*/
    /*.solution_cards_box .solution_card {*/
    /*    flex: 0 50%;*/
    /*    background: #fff;*/
    /*    box-shadow: 0 2px 4px 0 rgba(136, 144, 195, 0.2),*/
    /*    0 5px 15px 0 rgba(37, 44, 97, 0.15);*/
    /*    border-radius: 15px;*/
    /*    margin: 8px;*/
    /*    padding: 10px 15px;*/
    /*    position: relative;*/
    /*    z-index: 1;*/
    /*    overflow: hidden;*/
    /*    height: 100%;*/
    /*    transition: 0.7s;*/
    /*}*/

    /*.solution_cards_box .solution_card:hover {*/
    /*    background: #309df0;*/
    /*    color: #fff;*/
    /*    transform: scale(1.1);*/
    /*    z-index: 9;*/

    /*}*/

    /*.solution_cards_box .solution_card:hover .rs-amount-flex img{*/
    /*    filter: invert(97%) sepia(97%) saturate(10%) hue-rotate(153deg) brightness(102%) contrast(103%);*/
    /*}*/

    /*.solution_cards_box .solution_card:hover::before {*/
    /*    background: rgb(85 108 214 / 10%);*/
    /*}*/

    /*.solution_cards_box .solution_card:hover .solu_title h3,*/
    /*.solution_cards_box .solution_card:hover .solu_description p {*/
    /*    color: #fff;*/
    /*}*/

    /*.solution_cards_box .solution_card:before {*/
    /*    content: "";*/
    /*    position: absolute;*/
    /*    background: rgb(85 108 214 / 5%);*/
    /*    width: 170px;*/
    /*    height: 400px;*/
    /*    z-index: -1;*/
    /*    transform: rotate(42deg);*/
    /*    right: -56px;*/
    /*    top: -23px;*/
    /*    border-radius: 35px;*/
    /*}*/

    /*.solution_cards_box .solution_card:hover .solu_description button {*/
    /*    background: #fff !important;*/
    /*    color: #309df0;*/
    /*}*/

    /*.solution_card .so_top_icon {*/
    /*}*/

    /*.solution_card .solu_title h3 {*/
    /*    color: #212121;*/
    /*    font-size: 1.3rem;*/
    /*    margin-top: 13px;*/
    /*    margin-bottom: 13px;*/
    /*}*/

    /*.solution_card .solu_description p {*/
    /*    font-size: 15px;*/
    /*    margin-bottom: 15px;*/
    /*}*/

    /*.solution_card .solu_description button {*/
    /*    border: 0;*/
    /*    border-radius: 15px;*/
    /*    background: #3750eb;*/
    /*    color: #fff;*/
    /*    font-weight: 500;*/
    /*    font-size: 1rem;*/
    /*    padding: 5px 16px;*/
    /*}*/

    /*.our_solution_content h1 {*/
    /*    text-transform: capitalize;*/
    /*    margin-bottom: 1rem;*/
    /*    font-size: 2.5rem;*/
    /*}*/
    /*.our_solution_content p {*/
    /*}*/

    /*.hover_color_bubble {*/
    /*    position: absolute;*/
    /*    background: rgb(54 81 207 / 15%);*/
    /*    width: 100rem;*/
    /*    height: 100rem;*/
    /*    left: 0;*/
    /*    right: 0;*/
    /*    z-index: -1;*/
    /*    top: 4rem;*/
    /*    border-radius: 50%;*/
    /*    transform: rotate(-36deg);*/
    /*    left: -18rem;*/
    /*    transition: 0.7s;*/
    /*}*/

    /*.solution_cards_box .solution_card:hover .hover_color_bubble {*/
    /*    top: 0rem;*/
    /*}*/

    /*.solution_cards_box .solution_card .so_top_icon {*/
    /*    width: 60px;*/
    /*    height: 60px;*/
    /*    border-radius: 50%;*/
    /*    background: #fff;*/
    /*    overflow: hidden;*/
    /*    display: flex;*/
    /*    align-items: center;*/
    /*    justify-content: center;*/
    /*}*/

    /*.solution_cards_box .solution_card .so_top_icon img {*/
    /*    width: 40px;*/
    /*    height: 50px;*/
    /*    object-fit: contain;*/
    /*}*/

    /*!*start media query*!*/

    /*@media screen and (max-width: 1504px){*/
    /*    .responsive-card .validity-data-flex{*/
    /*        gap: 30px;*/
    /*    }*/

    /*    .recharge-container{*/
    /*        width: 90%;*/
    /*    }*/

    /*    .slide-container{*/
    /*        width: 97%;*/
    /*    }*/
    /*}*/

    /*@media screen and (max-width: 1146px){*/
    /*    .responsive-card .validity-data-flex{*/
    /*        gap: 20px;*/
    /*    }*/

    /*    .recharge-container{*/
    /*        width: 100%;*/
    /*    }*/

    /*    .slide-container{*/
    /*        width: 100%;*/
    /*    }*/

    /*    .slide-content{*/
    /*        margin: 0px;*/
    /*    }*/
    /*}*/

    /*@media screen and (max-width: 1024px) {*/
    /*    .sol_card_top_3 {*/
    /*        position: relative;*/
    /*        top: -3rem;*/
    /*    }*/
    /*    .our_solution_category {*/
    /*        margin: 0 auto;*/
    /*    }*/

    /*    .filter-btn{*/
    /*        right: 25px;*/
    /*    }*/

    /*    .recharge-text{*/
    /*        left: 25px;*/
    /*    }*/
    /*}*/

    /*@media screen and (max-width: 768px) {*/
    /*    .our_solution_category .solution_cards_box {*/
    /*        flex: 1;*/
    /*    }*/

    /*    .recharge-container{*/
    /*        width: 100%;*/
    /*    }*/

    /*    .responsive-card .validity-data-flex{*/
    /*        gap: 29px;*/
    /*    }*/

    /*    .recharge-cards-row .col-12{*/
    /*        margin-top: 10px;*/
    /*    }*/

    /*    .filter-btn{*/
    /*        right: 10px;*/
    /*    }*/

    /*    .recharge-text{*/
    /*        left: 12px;*/
    /*    }*/

    /*}*/

    /*@media only screen and (min-width: 768px) and (max-width: 768px){*/
    /*    .recharge-cards-row .col-12{*/
    /*        padding-left: 0px;*/
    /*        padding-right: 0px;*/
    /*    }*/

    /*    .swiper-slide .col-12{*/
    /*        padding-left: 0px;*/
    /*        padding-right: 0px;*/
    /*    }*/
    /*}*/

    /*@media screen and (max-width: 575px) {*/
    /*    .responsive-card .validity-data-flex{*/
    /*        gap: 30%*/
    /*    }*/
    /*}*/


    /*@media screen and (min-width: 576px) {*/
    /*    .recharge-plan-modal-dialog{*/
    /*        max-width: 600px;*/
    /*    }*/
    /*}*/

    /*@media screen and (max-width: 515px) {*/
    /*    .recharge-text{*/
    /*        font-size: 25px;*/
    /*        top: 15px;*/
    /*        left: 25px*/
    /*    }*/

    /*    .filter-btn{*/
    /*        transform: scale(0.85);*/
    /*        top: 16px;*/
    /*        right: 20px;*/
    /*    }*/

    /*    .category-name-view-flex{*/
    /*        flex-direction: column;*/
    /*        align-items: baseline;*/
    /*        row-gap: 10px;*/
    /*        padding-left: 15px;*/
    /*    }*/

    /*    .category-name-card{*/
    /*        font-size: 25px;*/
    /*    }*/

    /*    .plan-data-items .accordion-body{*/
    /*        padding-top: 0px;*/
    /*    }*/

    /*    .accordion-button-custom{*/
    /*        padding-bottom: 0px;*/
    /*    }*/
    /*}*/

    /*@media screen and (max-width: 425px) {*/
    /*    .slide-container{*/
    /*        padding-top: 40px;*/
    /*    }*/

    /*    .recharge-text {*/
    /*        left: 28px;*/
    /*        top: -1px;*/
    /*        font-size: 26px;*/
    /*    }*/

    /*    .filter-text{*/
    /*        display: none;*/
    /*    }*/

    /*    .filter-btn{*/
    /*        padding: 5px;*/
    /*        display: flex;*/
    /*        justify-content: center;*/
    /*        align-items: center;*/
    /*        top: 2px;*/
    /*    }*/

    /*    .filter-count{*/
    /*        right: -10px;*/
    /*    }*/

    /*    .filter-icon{*/
    /*        height: 24px;*/
    /*    }*/

    /*    .category-name-card{*/
    /*        font-size: 20px;*/
    /*    }*/
    /*}*/


    /*@media screen and (max-width: 376px) {*/
    /*    .table-view-plan td{*/
    /*        font-size: 15px;*/
    /*    }*/

    /*    .view-plan-body{*/
    /*        padding: 25px;*/
    /*    }*/

    /*    .modal-amount-icon-flex {*/
    /*        padding-top: 25px;*/
    /*        padding-left: 7px;*/
    /*        transform: scale(1.70);*/
    /*    }*/
    /*}*/

    /*@media screen and (min-width: 320px) {*/
    /*    .sol_card_top_3 {*/
    /*        position: relative;*/
    /*        top: 0;*/
    /*    }*/

    /*    .our_solution_category {*/
    /*        width: 100%;*/
    /*        margin: 0 auto;*/
    /*    }*/

    /*    .our_solution_category .solution_cards_box {*/
    /*        flex: auto;*/
    /*    }*/
    /*}*/

</style>


</html>
