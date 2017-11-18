package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by PhantomWolf on 11/18/2017.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "JewelColorTest", group = "testing")

public class JewelColorTest extends OpMode{

    ColorSensor revColorSensor;
    Servo servo;
    public void init () {
        servo=hardwareMap.servo.get("servo1");
        revColorSensor=hardwareMap.colorSensor.get("colordistance1");
        telemetry.addData("Red", revColorSensor.red());
        telemetry.addData("Blue", revColorSensor.blue());
        telemetry.addData("Green",revColorSensor.green());
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
}
