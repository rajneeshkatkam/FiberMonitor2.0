package com.raj.fibermonitorapp;

import java.sql.Timestamp;

/**
 * Created by RAJ on 29-03-2018.
 */

public class Notifications {

    Number adjustmentValue,distance;
    Timestamp issueTime,repairTime,predictedFailureTime;
    String msg,linkName;
    Boolean status;

    public Notifications() {
    }

    public Notifications(Number adjustmentValue,Timestamp damageTime,Number distance,String msg,Timestamp repairTime,Boolean status,String linkname,Timestamp predictedFailureTime){

        this.predictedFailureTime=predictedFailureTime;
        this.adjustmentValue=adjustmentValue;
        this.issueTime=damageTime;
        this.linkName=linkname;
        this.distance=distance;
        this.msg=msg;
        this.repairTime=repairTime;
        this.status=status;

    }

}
