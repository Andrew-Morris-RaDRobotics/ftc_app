package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by rick on 11/24/2017.
 */

public class turnTo {

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor br;
    private DcMotor bl;
    private gyroCompass gyro;
    private double sumError;
    private double prev;
    public turnTo(HardwareMap hardwareMap, gyroCompass gyroscope) {

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        gyro=gyroscope;
        sumError=0;
    }

    public boolean turnT(double target,double p,double i, double d,double deadZone){
       // double target = -target1;
        double curr = gyro.getHeading();
        double error = target-curr;

        if(error>180){
            error = -(360-error);
        }
        if(error<-180){
            error = (360+error);
        }

        if(Math.abs(error)<8){
            sumError+=error/50;
        }

        double power = error*p  + sumError*i + d*(error-prev);//-10 - -11

        prev=error;
        if(Math.abs(error)<deadZone){
            return true;
        }
        else{
            setP(power);
            return false;
        }

    }
    //    public void setP(double powerF, double powerS){
//        fr.setPower(-powerF + powerS);
//        fl.setPower(powerF+powerS);
//        br.setPower(-powerF -powerS);
//        bl.setPower(powerF-powerS);
//    }
    public void setP(double test){
        fr.setPower(test);
        fl.setPower(test);
        br.setPower(test);
        bl.setPower(test);

    }



}
