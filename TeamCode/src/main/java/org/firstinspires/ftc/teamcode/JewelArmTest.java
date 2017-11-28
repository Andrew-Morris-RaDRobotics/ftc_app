package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

@Autonomous (name= "JewelArmTest", group= "testing")

public class JewelArmTest extends LinearOpMode {

    public Servo jewelStick;
    public ColorSensor leftJewel;
    public ColorSensor rightJewel;
    public motorDeclaration Motors;

    @Override
    public void runOpMode() throws InterruptedException {

        jewelStick = hardwareMap.servo.get("jewelStick");
        leftJewel = hardwareMap.colorSensor.get("leftJewel");
        rightJewel = hardwareMap.colorSensor.get("rightJewel");

        jewelStick.setPosition(0);
        //runOpMode();
        telemetry.addData("Left Red", leftJewel.red());
        telemetry.addData("Left Blue", leftJewel.blue());
        telemetry.addData("Right Red", rightJewel.red());
        telemetry.addData("Right Blue", rightJewel.blue());
        telemetry.update();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            if (leftJewel.red() > rightJewel.red() && rightJewel.blue() > leftJewel.blue()) {
                telemetry.addData("left side is red", "right side is blue");
            } else if (leftJewel.red() < rightJewel.red() && rightJewel.blue() < leftJewel.blue()) {
                telemetry.addData("right side is red", "left side is blue");
            } else {
                telemetry.addData("No reading", "");
            }
            jewelStick.setPosition(95.75);
            //jewelStick.setPosition(180/191.5);
        }

    }/*
    ColorSensor revColorSensor;
    Servo servo;
    public void init () {

    }
    public void loop (){
        float red=revColorSensor.red();
        float blue=revColorSensor.blue();
        double ratio=blue/red;
        telemetry.addData("Red", revColorSensor.red());
        telemetry.addData("Blue", revColorSensor.blue());
        telemetry.addData("ratio",ratio);
        if (ratio>1){
            telemetry.addData("Blue", "");
            servo.setPosition(0);
        } else if (ratio<0.5){
            telemetry.addData("Red", "");
            servo.setPosition(90/191.5);
        } else {
            telemetry.addData("Nothing", "");
            servo.setPosition(45/191.5);
        }
        telemetry.update();
    }
}*/
}