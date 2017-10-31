package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.Func;

import java.util.prefs.Preferences;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "turnTest3", group = "testing")
public class turnTest3 extends OpMode {

    public DcMotor stanley;

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    gyroCompass testGyro;
    Servo theServo;
    @Override
    public void init() {
        testGyro = new gyroCompass(hardwareMap);
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        theServo = hardwareMap.servo.get("servo1");
        br = hardwareMap.dcMotor.get("br");




    }

    //robot goes forward to the right
    @Override
    public void loop() {
        double heading = testGyro.getHeading();
        double targetDegrees = 90;
        double deadzone = 10;
        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override
                    public String value() {
                        return testGyro.getHeading()+"";
                    }
                });
        telemetry.update();
        if(gamepad1.a) {
            if (heading > targetDegrees - deadzone && heading < targetDegrees + deadzone) {
                fl.setPower(0);
                br.setPower(0);
            } else if (heading > 100) {
                fl.setPower(.25);
                br.setPower(.25);
            } else if (heading < 80) {
                fl.setPower(-.25);
                br.setPower(-.25);
            }
        }
        theServo.setPosition((gamepad1.left_trigger+gamepad1.right_trigger)*(0.5)+0.5);

        double speed = 0.25;
        speed = speed + gamepad1.right_trigger*0.5;


        System.out.println(gamepad1.right_bumper);
        if(!gamepad1.a) {
            double frSpeed = (speed) * (-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            double flSpeed = (speed) * (+gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            double brSpeed = (speed) * (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            double blSpeed = (speed) * (+gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);

            fr.setPower(frSpeed);
            fl.setPower(flSpeed);
            br.setPower(brSpeed);
            bl.setPower(blSpeed);
        }
//        frSpeed = frSpeed/1.1*5;
//        flSpeed = flSpeed/-0.1*5;
//        brSpeed = brSpeed/1.15*5;
//        blSpeed = blSpeed/-0.8*5;



//
//        fr.setPower(gamepad1.left_stick_x);
//        fl.setPower(gamepad1.left_stick_y);
//        br.setPower(gamepad1.right_stick_x);
//        bl.setPower(gamepad1.right_stick_y);


    }
}
