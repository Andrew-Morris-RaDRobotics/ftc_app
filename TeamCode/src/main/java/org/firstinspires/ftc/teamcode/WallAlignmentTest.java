package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

import java.util.Locale;

@Autonomous(name = "WallAlignment", group = "testing")
public class WallAlignmentTest extends LinearOpMode {
    public motorDeclaration motors;
    DistanceSensor sensorDistance;
    gyroCompass imu;

    @Override
    public void runOpMode() {
        motors = new motorDeclaration(hardwareMap);
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colordistance1");
        imu = new gyroCompass(hardwareMap);

        double distance = sensorDistance.getDistance(DistanceUnit.INCH);
        double heading = imu.getHeading();

        while (distance > 16) {
            motors.setP(0, 0, 0.2);
        }

        double targetDistance = 10;
        double distanceDeadzone = 5;
        double distanceError = targetDistance - distance;

        while (distanceError > distanceDeadzone || distanceError < -distanceDeadzone) {
            if (distanceError > distanceDeadzone) {
                motors.setP(-0.2, 0, 0);
            } else {
                motors.setP(0.2, 0, 0);
            }
        }

        motors.setP(0, 0, 0);
    }
}

