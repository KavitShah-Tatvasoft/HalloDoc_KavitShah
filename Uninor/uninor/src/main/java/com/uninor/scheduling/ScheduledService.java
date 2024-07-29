package com.uninor.scheduling;


import com.uninor.enumeration.NotificationTypeEnum;
import com.uninor.helper.Helper;
import com.uninor.model.*;
import com.uninor.repository.*;
import com.uninor.service.ClientService;
import com.uninor.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Component
public class ScheduledService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PlanActivationRepository planActivationRepository;

    @Autowired
    private SimCardRepository simCardRepository;

    @Autowired
    private RoamingActivationRepository roamingActivationRepository;

    @Async("taskExecutor")
    @Scheduled(cron = "0 0 0 * * ?")
    public void midnightScheduler() {
        updateExpiredPlan();
        activateUpcomingPlan();
        refreshDailyData();
    }

    @Async("taskExecutor")
    @Scheduled(cron = "0 */30 * * * *")
    public void halfHourScheduler() {
        dataUsageNotification();
    }

    @Async("taskExecutor")
    @Scheduled(cron = "0 0 */6 * * *")
    public void sendExpirationNotification() {
        sendClientPlanExpirationNotifications();
    }

    private void dataUsageNotification(){
        List<PlanActivation> planActivationList = this.planActivationRepository.getAllActivePlanActivation();
        List<RoamingActivation> roamingActivationList = this.roamingActivationRepository.getAllActiveRoamingPlans();
        List<Notification> notificationList = new ArrayList<>();
        NotificationType notificationType = this.notificationRepository.getNotificationTypeObj(NotificationTypeEnum.INTERNET_USAGE.getNotificationTypeId());
        for(RoamingActivation roamingActivation : roamingActivationList){
            double dataUsedPercentage = (roamingActivation.getRoamingDataUsage() / roamingActivation.getRoamingPlan().getDataAllowance()) * 100;
            if(20.00 < dataUsedPercentage && dataUsedPercentage < 30.00 ){
                Notification notification = sendDataUsageNotification(roamingActivation.getSimCard().getClient(),25,notificationType);
                this.smsService.sendDataUsageMessage(roamingActivation.getSimCard().getPhoneNumber(),25);
                notificationList.add(notification);
            }

            if(45.00 < dataUsedPercentage && dataUsedPercentage < 55.00 ){
                Notification notification = sendDataUsageNotification(roamingActivation.getSimCard().getClient(),50,notificationType);
                this.smsService.sendDataUsageMessage(roamingActivation.getSimCard().getPhoneNumber(),50);
                notificationList.add(notification);
            }

            if(99 < dataUsedPercentage && dataUsedPercentage < 100){
                Notification notification = sendDataUsageNotification(roamingActivation.getSimCard().getClient(),100,notificationType);
                this.smsService.sendDataUsageMessage(roamingActivation.getSimCard().getPhoneNumber(),100);
                notificationList.add(notification);
            }
        }

        for(PlanActivation planActivation : planActivationList){
            double dataUsedPercentage = (planActivation.getPlanUsage().getDataUsage() / planActivation.getPlan().getDataAllowance()) * 100;
            if(20.00 < dataUsedPercentage && dataUsedPercentage < 30.00 ){
                Notification notification = sendDataUsageNotification(planActivation.getSimCard().getClient(),25,notificationType);
                this.smsService.sendDataUsageMessage(planActivation.getSimCard().getPhoneNumber(),25);
                notificationList.add(notification);
            }

            if(45.00 < dataUsedPercentage && dataUsedPercentage < 55.00 ){
                Notification notification = sendDataUsageNotification(planActivation.getSimCard().getClient(),50,notificationType);
                this.smsService.sendDataUsageMessage(planActivation.getSimCard().getPhoneNumber(),50);
                notificationList.add(notification);
            }

            if(99 < dataUsedPercentage && dataUsedPercentage < 100){
                Notification notification = sendDataUsageNotification(planActivation.getSimCard().getClient(),100,notificationType);
                this.smsService.sendDataUsageMessage(planActivation.getSimCard().getPhoneNumber(),100);
                notificationList.add(notification);
            }
        }

        if(!notificationList.isEmpty()){
            this.notificationRepository.saveAllNotification(notificationList);
        }
    }

    private Notification sendDataUsageNotification(Client client, int percentage, NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setMessage(Helper.getDataUsageMessage(percentage, Helper.formatLocalDateTime(LocalDateTime.now())));
        notification.setNotificationType(notificationType);
        notification.setDeleted(false);
        notification.setClient(client);
        notification.setNotificationHeader(Helper.getDataUsageHeader(percentage));
        notification.setRead(false);
        return notification;
    }

    private void sendClientPlanExpirationNotifications() {
        LocalDate currentDate = LocalDate.now();
        List<PlanActivation> planActivationList = this.planRepository.getExpiringPlans(currentDate);
        List<Notification> notificationList = new ArrayList<>();
        if(!planActivationList.isEmpty()) {
            NotificationType notificationType = this.notificationRepository.getNotificationTypeObj(NotificationTypeEnum.PLAN_EXPIRATION.getNotificationTypeId());
            for (PlanActivation planActivation : planActivationList) {
                long difference =  ChronoUnit.DAYS.between(planActivation.getExpirationDate().toLocalDate(), currentDate);
                this.smsService.sendPlanExpirationNotice(planActivation.getSimCard().getPhoneNumber(), difference);
                Notification notification = createAndSaveExpirationNotification(currentDate,planActivation,difference,notificationType);
                notificationList.add(notification);
            }
            this.notificationRepository.saveAllNotification(notificationList);
        }
    }

    private Notification createAndSaveExpirationNotification(LocalDate currentDate, PlanActivation planActivation, long difference, NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setClient(planActivation.getSimCard().getClient());
        notification.setNotificationHeader(Helper.PLAN_EXPIRATION_HEADER);
        notification.setMessage(Helper.getPlanExpirationNotificationMessage(planActivation.getSimCard().getPhoneNumber(), difference));
        notification.setNotificationType(notificationType);
        notification.setDeleted(false);
        notification.setCreatedDate(LocalDateTime.now());
        notification.setRead(false);
        return notification;
    }

    private Notification getExpiredPlanNotification(NotificationType notificationType, String mobileNumber){
        Notification notification = new Notification();
        notification.setNotificationHeader(Helper.PLAN_EXPIRATION_HEADER);
        notification.setDeleted(false);
        notification.setRead(false);
        notification.setNotificationType(notificationType);
        notification.setMessage(Helper.getPlanExpiredMessage(mobileNumber));
        return notification;
    }

    private void updateExpiredPlan(){
        List<PlanActivation> planActivationList = this.planRepository.getExpiredPlans();
        List<PlanActivation> expiredPlanActivationList = new ArrayList<>();
        List<Notification> notificationList = new ArrayList<>();
        LocalDateTime expirationDateTime = LocalDateTime.now();
        NotificationType notificationType = this.notificationRepository.getNotificationTypeObj(NotificationTypeEnum.PLAN_EXPIRATION.getNotificationTypeId());
        for (PlanActivation planActivation : planActivationList) {
            planActivation.setExpired(true);
            planActivation.setActive(false);
            planActivation.setExpirationDate(expirationDateTime);
            expiredPlanActivationList.add(planActivation);
            Notification notification = getExpiredPlanNotification(notificationType,planActivation.getSimCard().getPhoneNumber());
            notificationList.add(notification);
            this.smsService.sendPlanExpiredSms(planActivation.getSimCard().getPhoneNumber());
        }

        this.notificationRepository.saveAllNotification(notificationList);
        this.planActivationRepository.updateAllPlans(expiredPlanActivationList);

    }

    private void activateUpcomingPlan(){
        List<SimCard> simCardList = this.simCardRepository.getAllActiveSimDetails();
        List<PlanActivation> planActivationList = new ArrayList<>();
        List<RoamingActivation> roamingActivationList = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        for (SimCard simCard : simCardList) {
            if(simCard.isRoamingActive()){
                List<RoamingActivation> roamingActivations = this.roamingActivationRepository.getNextActivablePlan(simCard.getPhoneNumber());
                if(!roamingActivations.isEmpty()){
                    RoamingActivation roamingActivation = getRoamingActivation(roamingActivations, currentDateTime);
                    roamingActivationList.add(roamingActivation);
                }
            }else {
                List<PlanActivation> planActivations = this.planActivationRepository.getNextAvailablePlan(simCard);
                if(!planActivations.isEmpty()){
                    PlanActivation planActivation = getPlanActivation(planActivations,currentDateTime);
                    planActivationList.add(planActivation);
                }
            }

            if(!roamingActivationList.isEmpty()){
                this.roamingActivationRepository.saveRoamingActivationList(roamingActivationList);
            }

            if(!planActivationList.isEmpty()){
                this.planActivationRepository.updateAllPlans(planActivationList);
            }
        }
    }

    private static PlanActivation getPlanActivation(List<PlanActivation> planActivations, LocalDateTime currentDate) {
        PlanActivation planActivation = planActivations.get(0);
        planActivation.setActive(true);
        planActivation.setActivationDate(currentDate);
        planActivation.setExpirationDate(currentDate.plusDays(planActivation.getPlan().getValidity()));
        planActivation.setExpired(false);
        return planActivation;
    }

    private static RoamingActivation getRoamingActivation(List<RoamingActivation> roamingActivations, LocalDateTime currentDateTime) {
        RoamingActivation roamingActivation = roamingActivations.get(0);
        roamingActivation.setActive(true);
        roamingActivation.setActivationDate(currentDateTime);
        roamingActivation.setSmsUsage(0);
        roamingActivation.setExpired(false);
        roamingActivation.setRoamingDataUsage(0d);
        roamingActivation.setExpirationDate(currentDateTime.plusDays(roamingActivation.getRoamingPlan().getValidity()));
        roamingActivation.setVoiceUsage(0);
        roamingActivation.setReactiveAvailable(false);
        roamingActivation.setServiceChange(false);
        return roamingActivation;
    }

    public void refreshDailyData(){
        List<PlanActivation> planActivationList = this.planActivationRepository.getAllActivePlanActivation();
        List<PlanActivation> updatedPlanActivationList = new ArrayList<>();
        for (PlanActivation planActivation : planActivationList) {
            PlanUsage planUsage = planActivation.getPlanUsage();
            if(planActivation.getPlan().getIsRefreshing()){
                planUsage.setDataUsage(0);
                planUsage.setSmsUsage(0);
                planActivation.setPlanUsage(planUsage);
                updatedPlanActivationList.add(planActivation);
            }
        }

        if(!updatedPlanActivationList.isEmpty()){
            this.planActivationRepository.updateAllPlans(updatedPlanActivationList);
        }
    }

}
