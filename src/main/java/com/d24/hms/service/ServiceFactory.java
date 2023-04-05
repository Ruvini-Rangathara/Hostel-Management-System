package com.d24.hms.service;

import com.d24.hms.service.custom.impl.ReservationServiceImpl;
import com.d24.hms.service.custom.impl.RoomServiceImpl;
import com.d24.hms.service.custom.impl.StudentServiceImpl;
import com.d24.hms.service.custom.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceType serviceTypes){
        switch (serviceTypes){
            case STUDENT_SERVICE:
                return (T)new StudentServiceImpl();

            case ROOM_SERVICE:
                return (T)new RoomServiceImpl();

            case RESERVATION_SERVICE:
                return (T)new ReservationServiceImpl();

            case USER_SERVICE:
                return (T)new UserServiceImpl();

            default:
                return null;
        }
    }
}
