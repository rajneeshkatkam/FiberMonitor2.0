package com.raj.fibermonitorapp;

import java.sql.Timestamp;

/**
 * Created by RAJ on 29-03-2018.
 */

public class Notifications {

    Number adjustmentValue,distance;
    Timestamp damageTime,repairTime;
    String msg;
    Boolean status;

    public Notifications() {
    }

    public Notifications(Number adjustmentValue,Timestamp damageTime,Number distance,String msg,Timestamp repairTime,Boolean status){

        this.adjustmentValue=adjustmentValue;
        this.damageTime=damageTime;
        this.distance=distance;
        this.msg=msg;
        this.repairTime=repairTime;
        this.status=status;

    }

}
