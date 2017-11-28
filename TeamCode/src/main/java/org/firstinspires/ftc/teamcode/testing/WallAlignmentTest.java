package org.firstinspires.ftc.teamcode.testing;

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

//Note-all of the commented out sections of the code below are functional, but must utilize two distance sensors (not one)
@Autonomous(name = "WallAlignment", group = "testing")
public class WallAlignmentTest extends LinearOpMode {
    public motorDeclaration motors;
    DistanceSensor sensorDistanceLeft;
//    DistanceSensor sensorDistanceRight;
    gyroCompass imu;

    @Override
    public void runOpMode() {
        motors = new motorDeclaration(hardwareMap);
        sensorDistanceLeft = hardwareMap.get(DistanceSensor.class, "colordistance1");
//        sensorDistance = hardwareMap.get(DistanceSensor.class, "colordistance2");
        imu = new gyroCompass(hardwareMap);

        double distanceLeft = sensorDistanceLeft.getDistance(DistanceUnit.INCH);
//        double distanceRight = sensorDistanceRight.getDistance(DistanceUnit.INCH);
        double heading = imu.getHeading();

        while (distanceLeft > 16) {
            motors.setP(0, 0, 0.2);
        }

        double targetDistance = 10;
        double distanceDeadzone = 5;
        double distanceError = targetDistance - distanceLeft;

//        while (distanceLeft!=distanceRight){
//            if (distanceLeft>distanceRight){
//                motors.setP(0,0,0.2);
//            } else {
//                motors.setP(0,0,-0.2);
//            }
//        }

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

