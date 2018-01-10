package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rick on 11/24/2017.
 */

public class turnTo {
    private ArrayList<Double> history;
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor br;
    private DcMotor bl;
    private gyroCompass gyro;
    private double sumError;
    private double prev;
    public turnTo(HardwareMap hardwareMap, gyroCompass gyroscope) {
        history = new ArrayList<Double>();
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        gyro=gyroscope;
        sumError=0;
    }

    public double turnT(double target,double p,double i, double d,double deadZone){

       // double target = -target1;
        double curr = gyro.getHeading();
        double error = target-curr;
        history.add(error);
        if(history.size()>5){
            history.remove(0);
        }

        if(error>180){
            error = -(360-error);
        }
        if(error<-180){
            error = (360+error);
        }


        sumError+=error;

        double end = history.get(history.size()-1);
        double beginning = history.get(0);
        double diff = beginning-end;
        double power = error*p;
        if(Math.abs(diff)<5){
            sumError+=error;
            power+= sumError*i;
        }
        else{
            sumError=0;
        }



        // + -predictedError*i;// + sumError*i + d*(error-prev);//-10 - -11

        prev=error;
        if(Math.abs(error)<deadZone){
            sumError=0;
            return 0.0;

        }
        else{
            setP(power);
            return power*i;
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
    public void reset(){
        sumError=0;
    }



}
