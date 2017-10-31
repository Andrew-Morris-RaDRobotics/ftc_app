package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import java.util.prefs.Preferences;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "driveWithControllers", group = "testing")
public class driveWithControllers extends OpMode {

    public DcMotor stanley;

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    Servo theServo;
    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        theServo = hardwareMap.servo.get("servo1");
        br = hardwareMap.dcMotor.get("br");



    }

        //robot goes forward to the right
    @Override
    public void loop() {
        theServo.setPosition((gamepad1.left_trigger+gamepad1.right_trigger)*(0.5)+0.5);

        double speed = 0.25;
        speed = speed + gamepad1.right_trigger*0.5;


        System.out.println(gamepad1.right_bumper);
        double frSpeed = (speed)*(-gamepad1.left_stick_y +gamepad1.left_stick_x +gamepad1.right_stick_x);
        double flSpeed = (speed)*(+gamepad1.left_stick_y +gamepad1.left_stick_x +gamepad1.right_stick_x);
        double brSpeed = (speed)*(-gamepad1.left_stick_y -gamepad1.left_stick_x +gamepad1.right_stick_x);
        double blSpeed = (speed)*(+gamepad1.left_stick_y -gamepad1.left_stick_x +gamepad1.right_stick_x);

//        frSpeed = frSpeed/1.1*5;
//        flSpeed = flSpeed/-0.1*5;
//        brSpeed = brSpeed/1.15*5;
//        blSpeed = blSpeed/-0.8*5;


        fr.setPower(frSpeed);
        fl.setPower(flSpeed);
        br.setPower(brSpeed);
        bl.setPower(blSpeed);
//
//        fr.setPower(gamepad1.left_stick_x);
//        fl.setPower(gamepad1.left_stick_y);
//        br.setPower(gamepad1.right_stick_x);
//        bl.setPower(gamepad1.right_stick_y);


    }
}
