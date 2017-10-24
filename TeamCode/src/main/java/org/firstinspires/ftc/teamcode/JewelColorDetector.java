package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Jewel_Detector", group = "testing")


public class JewelColorDetector extends OpMode{


    ColorSensor rev_color_sensor;

    @Override
    public void init() {

        rev_color_sensor = hardwareMap.colorSensor.get("color2");

    }

    @Override
    public void loop() {

        telemetry.addData("Blue", rev_color_sensor.blue());
        telemetry.addData("Red", rev_color_sensor.red());

        if(rev_color_sensor.red() > rev_color_sensor.blue()){
            telemetry.addData("Red","");
        }
        else{
            telemetry.addData("Blue","");
        }
    }
    }