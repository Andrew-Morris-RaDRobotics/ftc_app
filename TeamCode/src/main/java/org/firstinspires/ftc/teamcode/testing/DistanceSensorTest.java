package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Distance Sensor", group = "testing")
public class DistanceSensorTest extends OpMode{
    DistanceSensor sensorDistance;
    DistanceSensor sensorDistance2;

    @Override
    public void init() {
        sensorDistance = hardwareMap.get(DistanceSensor.class, "glyphColor1");
        sensorDistance2 = hardwareMap.get(DistanceSensor.class, "glyphColor2");
    }

    @Override
    public void loop() {
        telemetry.addData("Distance2", String.format(Locale.US, "%.02f",sensorDistance2.getDistance(DistanceUnit.CM)));
        telemetry.addData("Distance1", String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
    }
}
