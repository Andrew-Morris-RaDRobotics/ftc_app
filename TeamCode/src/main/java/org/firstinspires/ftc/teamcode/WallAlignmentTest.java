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
import org.firstinspires.ftc.teamcode.gyroCompass;

import java.util.Locale;

@Autonomous (name = "WallAlignment", group = "testing")
public class WallAlignmentTest extends LinearOpMode {
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    DistanceSensor sensorDistance;
    gyroCompass imu;


    @Override
    public void runOpMode () {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colordistance1");
        imu = new gyroCompass(hardwareMap);

        double distance = sensorDistance.getDistance(DistanceUnit.INCH);
        double heading = imu.getHeading();

//        double targetDegrees = 90;
//        double deadzone = 10;
//        double error = targetDegrees - heading;
//        double Kp = 1.0 / 150;
//        double power = -Kp * error;


        while (distance>16) {
            fl.setPower(0.25);
            fr.setPower(0.25);
            bl.setPower(0.25);
            br.setPower(0.25);
        }

        double targetDistance = 10;
        double distanceDeadzone = 5;
        double distanceError = targetDistance - distance;
        double distanceKp = 1.0/50;
        double power2 = -distanceKp * distanceError;

        while (distanceError>distanceDeadzone||distanceError<-distanceDeadzone){
            fl.setPower(power2);
            fr.setPower(-power2);
            bl.setPower(power2);
            br.setPower(-power2);
        }

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

//        if (distance>30 || distance<10) {
//        fl.setPower(power+power2);
//        fr.setPower(power-power2);
//        bl.setPower(power+power2);
//        br.setPower(power-power2);


    }
}

