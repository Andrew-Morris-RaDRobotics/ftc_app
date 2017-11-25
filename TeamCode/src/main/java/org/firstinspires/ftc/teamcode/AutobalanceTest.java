package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


import java.util.Locale;


@TeleOp(name = "AutobalanceTest", group = "Testing")
public class AutobalanceTest extends LinearOpMode {

    BNO055IMU imu;

    Orientation angles;

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;


    @Override
    public void runOpMode() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        composeTelemetry();
        telemetry.log().add("Waiting for start...");

        // Wait until we're told to go
        while (!isStarted()) {
            telemetry.update();
            idle();
        }

        while (opModeIsActive()) {

            if (gamepad1.a) {

                // Get the calibration data
                BNO055IMU.CalibrationData calibrationData = imu.readCalibrationData();


                telemetry.update();
            }
        }
    }

    void composeTelemetry() {


        double roll = formatAngle(angles.angleUnit, angles.secondAngle);
        double pitch = formatAngle(angles.angleUnit, angles.thirdAngle);


        telemetry.addAction(new Runnable() {
            @Override
            public void run() {


            }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override
                    public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override
                    public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addData("roll", roll);
        telemetry.addData("pitch", pitch);

        if (pitch > 3) {
            fl.setPower(.5);
            fr.setPower(-.5);
            bl.setPower(.5);
            br.setPower(-.5);
        } else if (roll < 0) {
            fl.setPower(-.5);
            fr.setPower(.5);
            bl.setPower(-.5);
            br.setPower(.5);
        } else {
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }

        if (pitch > 0) {
            fl.setPower(fl.getPower());
        }

    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    double formatAngle(AngleUnit angleUnit, double angle) {
        return AngleUnit.DEGREES.fromUnit(angleUnit, angle);
    }
}