package org.firstinspires.ftc.teamcode.ChallengeFile;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.motorDeclaration;
import org.firstinspires.ftc.teamcode.utils.turnTo;

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
    public turnTo turn;

    @Override
    public void runOpMode() throws InterruptedException {

        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap, testGyro);
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
        double isComplete=1;
        double p = .0019;
        double i = .0003;
        int updatin = 0;
        int Timer = 0;
        while (opModeIsActive()) {
            if (completed == 0) {
                if (color == 0) {


                    if (leftJewel.red() - 1 > rightJewel.red() && rightJewel.blue() - 1 > leftJewel.blue()) {
                        telemetry.addData("left side is red", "right side is blue");
                        color = 2;
                        curr = testGyro.getHeading();
                        target = curr - 10;
                        // sleep(1000);
                    } else if (leftJewel.red() + 1 < rightJewel.red() && rightJewel.blue() + 1 < leftJewel.blue()) {
                        telemetry.addData("right side is red", "left side is blue");
                        color = 1;
                        curr = testGyro.getHeading();
                        target = curr + 10;
                        //sleep(1000);

                    } else {
                        telemetry.addData("No reading", "");
                    }
                    telemetry.addData("Completedvar", completed);
                    telemetry.addData("Left Red", leftJewel.red());
                    telemetry.addData("Left Blue", leftJewel.blue());
                    telemetry.addData("Right Red", rightJewel.red());
                    telemetry.addData("Right Blue", rightJewel.blue());
                    telemetry.addData("color:", color);
                    telemetry.addData("update:", updatin);
                    updatin++;

                }
                if (color >= 1) {
                    telemetry.addData("color", color);
                    telemetry.addData("gyroC1", testGyro.getHeading());
                    telemetry.addData("target", target);
                    telemetry.addData("curr", curr);
                    telemetry.addData("POWer", isComplete);
                    telemetry.addData("stage", stage);


                    // if(testGyro.getHeading()<target && stage==0) {
                    if (stage == 0) {
                        //Motors.setP(0, 0, target/50);
                        telemetry.addData("turning to", target);
                        isComplete = turn.turnT(target, 0.005, 0.0005, 0.0, 1);
                        //isComplete = turn.turnT(target, p, 0, 0.0, 1);
                    }
                    if (isComplete == 0.0) {
                        jewelStick.setPosition(0);
                        isComplete = 1;
                        //sleep(1000);
                        stage++;
                    }
                    if (stage == 1) {
                        telemetry.addData("turning to 0", "ye");
                        isComplete = turn.turnT(0, p, 0, 0.0, 1);

                        // completed++;
                    }

                    if (stage >= 2) {
                        telemetry.addData("completed", "yay");
                        Motors.setP(0, 0, 0);
                        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        completed++;
                    }
                    telemetry.addData("comp:", completed);
                }
            }
//
//
//            if (completed == 2) {
//
//                fr.setTargetPosition(newLeftTarget);
//                bl.setTargetPosition(newRightTarget);
//                fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d",
//                        fr.getCurrentPosition(),
//                        bl.getCurrentPosition());
//                telemetry.update();
//                fr.setPower(Math.abs(.3));
//                bl.setPower(Math.abs(.3));
//                if (completed == 0 && newLeftTarget >= fr.getCurrentPosition() - 4 && newLeftTarget <= fr.getCurrentPosition() + 4 && newRightTarget >= bl.getCurrentPosition() - 4 && newRightTarget <= bl.getCurrentPosition() + 4) {
//                    completed++;
//                    telemetry.addData("Completed1", "");
//                    telemetry.update();
//                }
//            }
//
            if (completed == 1) {
                telemetry.addData("Path0", "Starting at %7d :%7d",
                        fr.getCurrentPosition(),
                        bl.getCurrentPosition());
                if(fr.getCurrentPosition()<500) {
                    Motors.setP(0, -.3, 0);
                }
                else{
                    Motors.setP(0,0,0);
                }

            }

telemetry.update();
        }

    }
}