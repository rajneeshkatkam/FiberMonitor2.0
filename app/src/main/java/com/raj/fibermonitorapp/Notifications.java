package com.raj.fibermonitorapp;

import com.google.firebase.Timestamp;

/**
 * Created by RAJ on 29-03-2018.
 */

public class Notifications {

    Double adjustmentValue,distance;
    String msg,linkName,issueTime,repairTime,predictedFailureTime;
    Boolean status;


    public Notifications() {
    }

    public Notifications(Double adjustmentValue,String issueTime,Double distance,String msg,String repairTime,Boolean status,String linkname,String predictedFailureTime){

        this.predictedFailureTime=predictedFailureTime;
        this.adjustmentValue=adjustmentValue;
        this.issueTime=issueTime;
        this.linkName=linkname;
        this.distance=distance;
        this.msg=msg;
        this.repairTime=repairTime;
        this.status=status;

    }

}
