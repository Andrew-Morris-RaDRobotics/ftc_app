package org.firstinspires.ftc.teamcode.ChallengeFile;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

@Autonomous (name= "BlueJewelRight", group= "competition")

public class BlueJewelRight extends LinearOpMode {

    public Servo jewelStick;
    public ColorSensor leftJewel;
    public ColorSensor rightJewel;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    public motorDeclaration Motors;
    public gyroCompass testGyro;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        testGyro = new gyroCompass(hardwareMap);
        Motors = new motorDeclaration(hardwareMap);
        double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
        double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
        double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
        double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Path0", "Starting at %7d :%7d",
                fr.getCurrentPosition(),
                bl.getCurrentPosition());
        telemetry.update();


        jewelStick = hardwareMap.servo.get("jewelStick");
        leftJewel = hardwareMap.colorSensor.get("leftJewel");
        rightJewel = hardwareMap.colorSensor.get("rightJewel");
        int newLeftTarget = 0;
        int newRightTarget = 0;
        double target = 0;
        waitForStart();

        jewelStick.setPosition(1);
        int completed = 0;
        int color = 0; //1 is red 2 is blu (left side)
        double curr = 0.0;
        int stage = 0;

        while (opModeIsActive()) {
            if (completed == 0 && color == 0) {
                telemetry.addData("Completed2", completed);
                telemetry.addData("Left Red", leftJewel.red());
                telemetry.addData("Left Blue", leftJewel.blue());
                telemetry.addData("Right Red", rightJewel.red());
                telemetry.addData("Right Blue", rightJewel.blue());
                telemetry.addData("color:", color);
                telemetry.update();

                if (leftJewel.red() - 1
                        > rightJewel.red() && rightJewel.blue() - 1 > leftJewel.blue()) {
                    telemetry.addData("left side is red", "right side is blue");
                    color = 1;
                    curr = testGyro.getHeading();
                    target = curr - 10;
                    sleep(1000);
                } else if (leftJewel.red() + 1 < rightJewel.red() && rightJewel.blue() + 1 < leftJewel.blue()) {
                    telemetry.addData("right side is red", "left side is blue");
                    color = 2;
                    curr = testGyro.getHeading();
                    target = curr + 10;
                    sleep(1000);

                } else {
                    telemetry.addData("No reading", "");
                }

            }
            if (color == 1) {

                telemetry.addData("gyroC1", testGyro.getHeading());
                telemetry.addData("target", target);
                telemetry.addData("stage", stage);
                telemetry.update();

                // if(testGyro.getHeading()<target && stage==0) {
                if (stage < 1) {
                    Motors.setP(0, 0, target/50);
                }
                if (testGyro.getHeading() <= target && stage == 0) {
                    jewelStick.setPosition(0);
                    Motors.setP(0,0,-target/50);
                    stage++;
                }
                if (stage == 1 && testGyro.getHeading() <= target + 1 && testGyro.getHeading() >= target - 1) {
                    stage++;
                    Motors.setP(0, 0, 0);
                    sleep(1000);
                    // completed++;
                }

            }
            if (color == 2) {
                telemetry.addData("gyroC2 ", testGyro.getHeading());
                telemetry.addData("target", target);
                telemetry.addData("stage", stage);
                telemetry.update();
                //if(testGyro.getHeading()<target && stage==0) {
                if (stage < 1) {
                    Motors.setP(0, 0,target/50);
                } //}
                if (testGyro.getHeading() > target && stage == 0) {
                    jewelStick.setPosition(0);
                    Motors.setP(0,0,-target/50);
                    stage++;
                }
                if (stage == 1 && testGyro.getHeading() >= target - 1 && testGyro.getHeading() <= target + 1) {
                    stage++;
                    Motors.setP(0, 0, 0);
                    sleep(1000);
                    // completed++;
                }

            }

            if (completed == 2) {

                fr.setTargetPosition(newLeftTarget);
                bl.setTargetPosition(newRightTarget);
                fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        fr.getCurrentPosition(),
                        bl.getCurrentPosition());
                telemetry.update();
                fr.setPower(Math.abs(.3));
                bl.setPower(Math.abs(.3));
                if (completed == 0 && newLeftTarget >= fr.getCurrentPosition() - 4 && newLeftTarget <= fr.getCurrentPosition() + 4 && newRightTarget >= bl.getCurrentPosition() - 4 && newRightTarget <= bl.getCurrentPosition() + 4) {
                    completed++;
                    telemetry.addData("Completed1", "");
                    telemetry.update();
                }
            }

            if (completed == 1) {

                fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                fr.setPower(0);
                bl.setPower(0);
                newLeftTarget = fr.getCurrentPosition() + (int) (12 * COUNTS_PER_INCH);
                newRightTarget = fr.getCurrentPosition() + (int) (12 * COUNTS_PER_INCH);
                runtime.reset();
                while (runtime.seconds()<2.5) {
                    fl.setPower(3.5);
                    fr.setPower(-3.5);
                    bl.setPower(3.5);
                    br.setPower(-3.5);
                }
                completed++;
                telemetry.addData("Completed1", completed);
                telemetry.update();
            }


        }

    }
}