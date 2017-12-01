package org.firstinspires.ftc.teamcode.testing;

import java.lang.Math;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Sensor_Test", group = "testing")


public class GlyphTest extends OpMode {

    private ColorSensor rev_color_sensor;
    private DistanceSensor distanceSensor;
    private DigitalChannel MRLimitSwitch;

    @Override
    public void init() {
        rev_color_sensor = hardwareMap.colorSensor.get("colordistance1");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "colordistance1");
        MRLimitSwitch = hardwareMap.digitalChannel.get("LimitSwitch1");
        MRLimitSwitch.setMode(DigitalChannel.Mode.INPUT);
//        float hsvValues[] = {0F, 0F, 0F};
//        Values = hsvValues;
    }


    @Override
    public void loop() {
        float red = rev_color_sensor.red();
        float blue = rev_color_sensor.blue();
        double ratio = blue / red;
        double rotations = distanceSensor.getDistance(DistanceUnit.INCH)/3.25*Math.PI;
        telemetry.addData("Red", rev_color_sensor.red());
        telemetry.addData("Blue", rev_color_sensor.blue());
        telemetry.addData("ratio", ratio);
        telemetry.addData("distance", distanceSensor.getDistance(DistanceUnit.INCH));
        telemetry.addData("Estimated Rotations", rotations);
        if (MRLimitSwitch.getState()) {
            if (ratio > 0.8) {
                telemetry.addData("Gray", "");
            } else if (ratio > 0.5 && ratio < 0.8) {
                telemetry.addData("Brown", "");
            } else {
                telemetry.addData("","");
            }
        } else {
            telemetry.addData("","");
        }
    }
}
