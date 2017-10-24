package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Distance Sensor", group = "testing")
public class DistanceSensorTest extends OpMode{
    DistanceSensor sensorDistance;

    @Override
    public void init() {
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colordistance1");
    }

    @Override
    public void loop() {
        telemetry.addData("Distance", String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.INCH)));
    }
}
