package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;
import java.util.Locale;

/**
 * Created by DeathChicken on 11/18/2017.
 */

@TeleOp(name = "GyroTilt", group = "Testing")
public class GyroTilt extends LinearOpMode
{
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    BNO055IMU imu;

     Orientation angles;

    @Override public void runOpMode() {

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        composeTelemetry();
        double pitch=formatAngle(angles.angleUnit, angles.thirdAngle);
        double roll=formatAngle(angles.angleUnit, angles.secondAngle);

        while (!(roll < 3 && roll > -3 || pitch < 3 && pitch > -3)) {
            fr.setPower(-0.5);
            br.setPower(0.5);
            telemetry.addData("roll", roll);
            telemetry.addData("pitch", pitch);
            telemetry.update();

            pitch=formatAngle(angles.angleUnit, angles.thirdAngle);
            roll=formatAngle(angles.angleUnit, angles.secondAngle);
        }
         telemetry.addData("Level", "");
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void composeTelemetry() {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double roll = formatAngle(angles.angleUnit, angles.secondAngle);
        double pitch = formatAngle(angles.angleUnit, angles.thirdAngle);

        telemetry.addData("roll", roll);
        telemetry.addData("pitch", pitch);
    }
    double formatAngle(AngleUnit angleUnit, double angle) {
        return AngleUnit.DEGREES.fromUnit(angleUnit, angle);
    }
}


