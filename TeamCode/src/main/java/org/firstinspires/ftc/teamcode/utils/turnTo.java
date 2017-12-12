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
    public turnTo(HardwareMap hardwareMap, gyroCompass gyroscope) {

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        gyro=gyroscope;
        sumError=0;
    }

    public double turnT(double target,double p,double i, double deadZone){
        double curr = gyro.getHeading();
        double error = curr-target;

        if(error>180){
            error = (360-error);
        }
        if(error<-180){
            error = -(360+error);
        }

        if(Math.abs(error)<8){
            sumError+=error;
        }

        double power = error*p +sumError*i;
        setP(power);
        if(Math.abs(error)<deadZone){
            return power;
        }
        else{
            return error;
        }

    }
    //    public void setP(double powerF, double powerS){
//        fr.setPower(-powerF + powerS);
//        fl.setPower(powerF+powerS);
//        br.setPower(-powerF -powerS);
//        bl.setPower(powerF-powerS);
//    }
    public void setP(double turn){
        fr.setPower(turn);
        fl.setPower(turn);
        br.setPower(turn);
        bl.setPower(turn);
    }



}
