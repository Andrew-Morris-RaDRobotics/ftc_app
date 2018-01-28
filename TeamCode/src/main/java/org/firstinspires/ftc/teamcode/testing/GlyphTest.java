package org.firstinspires.ftc.teamcode.testing;

import java.lang.Math;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Sensor_Test", group = "testing")


public class GlyphTest extends OpMode {

    private ColorSensor glyphColor;
    private DistanceSensor glyphDistance;
    private DigitalChannel MRLimitSwitch;

    @Override
    public void init() {
        glyphColor = hardwareMap.colorSensor.get("glyphColor1");
        glyphDistance = hardwareMap.get(DistanceSensor.class, "glyphColor1");
        MRLimitSwitch = hardwareMap.digitalChannel.get("LimitSwitch1");
        MRLimitSwitch.setMode(DigitalChannel.Mode.INPUT);
//        float hsvValues[] = {0F, 0F, 0F};
//        Values = hsvValues;
    }


    @Override
    public void loop() {
        float red = glyphColor.red();
        float blue = glyphColor.blue();
        double ratio = blue / red;
        double rotations = glyphDistance.getDistance(DistanceUnit.INCH)/3.25*Math.PI;
        telemetry.addData("Red", glyphColor.red());
        telemetry.addData("Blue", glyphColor.blue());
        telemetry.addData("ratio", ratio);
        telemetry.addData("distance", glyphDistance.getDistance(DistanceUnit.INCH));
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
