var stompClient = null;

function connect() {
    var socket = new SockJS('/uninor/notifications');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        alert('Connected: ' + frame);
        stompClient.subscribe('/topic/notifications', function (notification) {
            handleNotification(notification.body);
        });
    });
}

function handleNotification(message) {
    // Call your custom function here
    getNotificationDetails()
}

document.addEventListener('DOMContentLoaded', function (){

    if(stompClient == null){
        connect();
    }


})

function getNotificationDetails() {
    $.ajax({
        url: CONTEXT_PATH + '/client/get-client-notifications',
        type: 'GET',
        success: function (xhr, status, success) {
            console.log(xhr)
            var body = $(".noti-main-body")
            if (xhr['notificationDetails'] != null) {
                $(".no-notification").addClass("d-none")
                body.empty()
                $(".count-span-noti").text(xhr['notificationDetails'][0].newCount)
                $.each(xhr.notificationDetails, function (index, item) {
                    var singleNotification = $(".clone-notification-class").clone(true).removeClass("d-none").removeClass("clone-notification-class")
                    singleNotification.find(".noti-header-text").text(item.header)
                    singleNotification.find(".noti-description-text").text(item.message)
                    singleNotification.find(".noti-time").text(item.sendDate)
                    singleNotification.find(".close-btn-noti").attr("onclick","removeNotification(" + item.notificationId + ")")
                    body.append(singleNotification)
                })
            }else {
                body.empty()
                $(".no-notification").removeClass("d-none")
                $(".count-span-noti").text('0')
            }

        },
        error: function (xhr, status, error) {
            debugger
            if (xhr.status === 400 || xhr.status === 401 || xhr.status === 409) {
                let errorResponse;
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }
                console.log(errorResponse)
                if (errorResponse.errors) {
                    let errorMessage = errorResponse.errors
                    showAlert(true, errorMessage, "faliure")
                }
            } else {
                showAlert(true, "Server Side Error", "faliure")
            }
            console.log(xhr)
        }
    })
}

function removeNotification(notificationId){

    const regex = /^\d+$/;
    if(!regex.test(notificationId)){
        showAlert(true,"Error deleting notification", "faliure")
    }else{
        $.ajax({
            url: CONTEXT_PATH + '/client/remove-notification',
            type: 'POST',
            data : {
                notificationId : notificationId
            },
            success: function (xhr, status, success) {
                getNotificationDetails()
            },
            error: function (xhr, status, error) {
                debugger
                if (xhr.status === 400 || xhr.status === 401 || xhr.status === 409) {
                    let errorResponse;
                    try {
                        errorResponse = JSON.parse(xhr.responseText);
                    } catch (e) {
                        errorResponse = xhr.responseText;
                    }
                    console.log(errorResponse)
                    if (errorResponse.errors) {
                        let errorMessage = errorResponse.errors
                        showAlert(true, errorMessage, "faliure")
                    }
                } else {
                    showAlert(true, "Server Side Error", "faliure")
                }
                console.log(xhr)
            }
        })
    }

}

function clearAllNotification(){
    $.ajax({
        url: CONTEXT_PATH + '/client/remove-all-notification',
        type: 'GET',
        success: function (xhr, status, success) {
            getNotificationDetails()
            updateReadReciepts()
        },
        error: function (xhr, status, error) {
            debugger
            if (xhr.status === 400 || xhr.status === 401 || xhr.status === 409) {
                let errorResponse;
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }
                console.log(errorResponse)
                if (errorResponse.errors) {
                    let errorMessage = errorResponse.errors
                    showAlert(true, errorMessage, "faliure")
                }
            } else {
                showAlert(true, "Server Side Error", "faliure")
            }
            console.log(xhr)
        }
    })
}

document.getElementById('notification-tab-id').addEventListener('hidden.bs.offcanvas', function () {
    updateReadReciepts()
});

function updateReadReciepts(){
    $.ajax({
        url: CONTEXT_PATH + '/client/update-read-receipts',
        type: 'GET',
        success: function (xhr, status, success) {
            $(".count-span-noti").text("0")
        },
        error: function (xhr, status, error) {
            debugger
            if (xhr.status === 400 || xhr.status === 401 || xhr.status === 409) {
                let errorResponse;
                try {
                    errorResponse = JSON.parse(xhr.responseText);
                } catch (e) {
                    errorResponse = xhr.responseText;
                }
                console.log(errorResponse)
                if (errorResponse.errors) {
                    let errorMessage = errorResponse.errors
                    showAlert(true, errorMessage, "faliure")
                }
            } else {
                showAlert(true, "Server Side Error", "faliure")
            }
            console.log(xhr)
        }
    })
}