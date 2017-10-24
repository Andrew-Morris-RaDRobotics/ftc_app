package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Sensor_Test", group = "testing")


public class Sensor_Test extends OpMode{


    ModernRoboticsI2cColorSensor color_sensor;
    ColorSensor rev_color_sensor;

    @Override
    public void init() {
        color_sensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class,"color1");
        rev_color_sensor = hardwareMap.colorSensor.get("color2");
    }

    @Override
    public void loop() {
        telemetry.addData("Red", color_sensor.red());
        telemetry.addData("Blue", color_sensor.blue());
        //telemetry.addData("Green", color_sensor.green());
        telemetry.addData("Rev Blue", rev_color_sensor.blue());
        telemetry.addData("Rev Red", rev_color_sensor.red());
        //telemetry.addData("Rev Green", rev_color_sensor.green());
        rev_color_sensor.enableLed(true);
        if(rev_color_sensor.red() > rev_color_sensor.blue()){
            telemetry.addData("Red","");
        }
        else{
            telemetry.addData("Blue","");
        }
    }
    }

/*color_sensor.red();   // Red channel value
color_sensor.green(); // Green channel value
color_sensor.blue();  // Blue channel value

color_sensor.alpha(); // Total luminosity
color_sensor.argb();  // Combined color value*/