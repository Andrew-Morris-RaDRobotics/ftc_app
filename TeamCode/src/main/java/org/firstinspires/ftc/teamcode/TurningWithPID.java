package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.Func;

import java.util.prefs.Preferences;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "turningWithPID", group = "testing")
public class TurningWithPID extends OpMode {

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
        theServo.setPosition(45);

//        telemetry.addLine()
//                .addData("heading", new Func<String>() {
//                    @Override
//                    public String value() {
//                        return String.valueOf(testGyro.getHeading());
//                    }
//                });
    }

    //robot goes forward to the right
    @Override
    public void loop() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double heading = testGyro.getHeading();
        double targetDegrees = 90;
        double deadzone = 10;
        double error = targetDegrees - heading;
        double Kp = 1.0 / 50*2;
        double power = -Kp * Math.pow(error,2.0);

        telemetry.addData("heading", String.valueOf(testGyro.getHeading()));
        telemetry.addData("Error = ", error);
        telemetry.addData("Power = ", power);
        telemetry.update();

        if (gamepad1.a) {

            fl.setPower(power);
            br.setPower(power);
        }
        if (gamepad1.left_bumper == true) {
            theServo.setPosition(90);
        }
        else if (gamepad1.right_bumper == true) {
            theServo.setPosition(-90);
        }
        else {
            theServo.setPosition(0);
        }
        double speed = 0.25;
        speed = speed + gamepad1.right_trigger * 0.5;
        System.out.println(gamepad1.right_bumper);
        if (!gamepad1.a) {
            double frSpeed = (speed) * (-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            double flSpeed = (speed) * (+gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            double brSpeed = (speed) * (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            double blSpeed = (speed) * (+gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);

            fr.setPower(frSpeed);
            fl.setPower(flSpeed);
            br.setPower(brSpeed);
            bl.setPower(blSpeed);
        }


    }
}