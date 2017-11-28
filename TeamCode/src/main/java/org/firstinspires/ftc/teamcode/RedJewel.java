package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

import java.util.concurrent.TimeUnit;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "RedJewel", group = "testing")


public class RedJewel extends LinearOpMode{


    ColorSensor colorSensor;
    ColorSensor colorSensor2;
    public motorDeclaration motors;
    Servo servo;


    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor2 = hardwareMap.colorSensor.get("leftJewe");
        colorSensor = hardwareMap.colorSensor.get("rightJewel");
        motors = new motorDeclaration(hardwareMap);
        servo = hardwareMap.servo.get("jewelStick");

        servo.setPosition(0.45);
        if (colorSensor.red() > colorSensor.blue()) {
            motors.setP(0, 0, 0.2);
            TimeUnit.SECONDS.sleep(2);
            motors.setP(0,0,-0.2);
        } else if (colorSensor2.red()>colorSensor2.blue()) {
            motors.setP(0, 0, -0.2);
            TimeUnit.SECONDS.sleep(2);
            motors.setP(0,0,0.2);
        } else {
            motors.setP(0,0,0);
        }

        servo.setPosition(0);
    }
}
