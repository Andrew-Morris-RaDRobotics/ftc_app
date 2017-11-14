package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Sensor_Test", group = "testing")


public class Sensor_Test extends OpMode {

    ColorSensor rev_color_sensor;
    float[] Values;
    int colorTotal = rev_color_sensor.red() + rev_color_sensor.blue() + rev_color_sensor.green();
    @Override
    public void init() {
        rev_color_sensor = hardwareMap.colorSensor.get("colordistance1");
        float hsvValues[] = {0F,0F,0F};
        Values=hsvValues;
    }


    @Override
    public void loop() {
/*        telemetry.addData("Rev Blue", rev_color_sensor.blue());
        telemetry.addData("Rev Red", rev_color_sensor.red());
        telemetry.addData("Rev Green", rev_color_sensor.green());
        rev_color_sensor.enableLed(true);
        if (colorTotal > 120 & colorTotal < 190) {
            telemetry.addData("Gray", "");
        } else if (colorTotal < 120 & colorTotal > 40) {
            telemetry.addData("Brown", "");
        } else ;
        telemetry.addData("L", ""); */

        Color.RGBToHSV(rev_color_sensor.red(), rev_color_sensor.green(), rev_color_sensor.blue(), Values);
       //telemetry.addData();
    }
}



// brown block is from 50 red, 25 green, 0 blue, to red 150, green 75, blue 0.
// grey block is from  red 96, green 96, blue 96, to red 160, green 160, blue 160.


/*Nothing is Blue 54, red 63, green 56











/*color_sensor.red();   // Red channel value
color_sensor.green(); // Green channel value
color_sensor.blue();  // Blue channel value

color_sensor.alpha(); // Total luminosity
color_sensor.argb();  // Combined color value*/