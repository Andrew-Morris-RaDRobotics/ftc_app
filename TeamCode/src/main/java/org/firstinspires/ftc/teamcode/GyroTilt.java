package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import java.io.File;
import java.util.Locale;

/**
 * Created by DeathChicken & StealthWhale on 11/18/2017.
 */

@TeleOp(name = "GyroTilt", group = "Testing")
public class GyroTilt extends OpMode
{
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    gyroCompass testGyro;

    double pitch;
    double roll;
    double asdf;
    int xp;

     public void init() {
         telemetry.addData("Status", "Initialized");

         telemetry.update();
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
       // pitch = 0;
         //roll= 0;
         testGyro = new gyroCompass(hardwareMap);
         xp=0;
      }


    @Override
    public void loop() {


         roll = testGyro.getPitch();
        pitch = testGyro.getRoll();
        //switched them because im lazy
        asdf = testGyro.getHeading();
        telemetry.addData("roll", roll);
        telemetry.addData("pitch", pitch);
        telemetry.addData("heading", asdf);
        telemetry.addData("test: ", xp);
        xp++;
        telemetry.update();

        if ( (roll > 3) || (roll < -3) || (pitch > 3) || (pitch < -3) ) {
            //ls y = roll
            //ls r = pitch
//                    try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            if(roll<3 && roll>-3){
                roll = 0;
            }
            if(pitch<3 && pitch>-3){
                pitch = 0;
            }
            double frSpeed = -(.03) * (-roll + pitch);
            double flSpeed = -(.03) * (+roll + pitch);
            double brSpeed = -(.03) * (-roll - pitch);
            double blSpeed = -(.03) * (+roll - pitch);

            fr.setPower(frSpeed);
            fl.setPower(flSpeed);
            br.setPower(brSpeed);
            bl.setPower(blSpeed);
//            br.setPower(0.5);
//            telemetry.addData("roll", roll);
//            telemetry.addData("pitch", pitch);
//            telemetry.update();
//            pitch = testGyro.getPitch();
//            roll = testGyro.getRoll();
            // pitch=formatAngle(angles.angleUnit, angles.thirdAngle);
            //roll=formatAngle(angles.angleUnit, angles.secondAngle);
        }
        else {
            telemetry.addData("Level", "");

            fr.setPower(0);
            fl.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        }



        //telemetry.addData("Level", "");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

//    void composeTelemetry() {
//
//
//        telemetry.addData("roll", roll);
//        telemetry.addData("pitch", pitch);
//    }

}


