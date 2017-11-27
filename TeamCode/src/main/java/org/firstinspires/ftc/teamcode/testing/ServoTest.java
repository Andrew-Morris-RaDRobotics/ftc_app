package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Servo test", group = "testing")
public class ServoTest extends OpMode{
    Servo theServo;

    @Override
    public void init() {
        theServo = hardwareMap.servo.get("servo1");
    }


    @Override
    public void loop() {
        theServo.setPosition((-gamepad1.left_trigger+gamepad1.right_trigger)*(0.5)+0.5);
       // theServo.setPosition(gamepad1.right_trigger*90*(1/190.5));
        //theServo.setPosition(gamepad1.left_trigger*(-90)*(1/190.5));
    }
}