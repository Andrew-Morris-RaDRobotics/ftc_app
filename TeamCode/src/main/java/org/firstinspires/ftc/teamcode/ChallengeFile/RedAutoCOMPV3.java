package org.firstinspires.ftc.teamcode.ChallengeFile;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.utils.GlyphVision;
import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.motorDeclaration;
import org.firstinspires.ftc.teamcode.utils.turnTo;

@Autonomous (name= "RedAutoCompV3", group= "competition")

public class RedAutoCOMPV3 extends LinearOpMode {
    public GlyphVision glyph;
    public Servo jewelStick;
    public Servo leftIntakeFlipper;
    public Servo rightIntakeFlipper;
    public Servo conveyorTop;
    public ColorSensor leftJewel;
    public ColorSensor rightJewel;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    public DcMotor fwoppers;
    public DcMotor intakeDrive;
    public motorDeclaration Motors;
    public gyroCompass testGyro;
    public DcMotor conveyor;
    public Servo intakeBucket;
    public ColorSensor glyphColor;
    public DistanceSensor glyphDistance;

    public turnTo turn;


    @Override
    public void runOpMode() throws InterruptedException {
        intakeDrive = hardwareMap.dcMotor.get("intakeDrive");

        double autoTime = 0;
        double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
        double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
        double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
        double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);
        conveyor = hardwareMap.dcMotor.get("conveyer");
        conveyorTop = hardwareMap.servo.get("conveyerTop");
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        fr.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
        glyph = new GlyphVision(hardwareMap);
        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap, testGyro);
        Motors = new motorDeclaration(hardwareMap);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        jewelStick = hardwareMap.servo.get("jewelStick");
        leftJewel = hardwareMap.colorSensor.get("leftJewel");
        rightJewel = hardwareMap.colorSensor.get("rightJewel");
        fwoppers = hardwareMap.dcMotor.get("fwopperDrive");
        intakeBucket = hardwareMap.servo.get("intakeBucket");
        leftIntakeFlipper = hardwareMap.servo.get("leftIntakeFlipper");
        rightIntakeFlipper = hardwareMap.servo.get("rightIntakeFlipper");
        glyphColor= hardwareMap.colorSensor.get("glyphColor");
        glyphDistance=hardwareMap.get(DistanceSensor.class,"glyphColor");

        int newLeftTarget = 0;
        int newRightTarget = 0;
        double target = 0;


        waitForStart();


        double completed = 0;
        int color = 0; //1 is red 2 is blu (left side)
        double curr = 0.0;
        int stage = 0;
        double isComplete = 1;
        double p = .0019;
        double i = .0003;
        int updatin = 0;
        int Timer = 0;
        ElapsedTime runtime = new ElapsedTime();
        ElapsedTime totalTime = new ElapsedTime();
        totalTime.reset();
        runtime.reset();
        RelicRecoveryVuMark matchGlyph = null;

        int flPosition=0;

        telemetry.update();
        while (opModeIsActive()) {

            telemetry.addData("Completedvar", completed);
            telemetry.addData("heading: ",testGyro.getHeading());
            if (completed == 0) {

                matchGlyph = glyph.getGlyph();

                if (matchGlyph != null || runtime.seconds() > 1.2) {
                    completed++;
                    runtime.reset();
                }
            }
            telemetry.addData("glyph: ", matchGlyph);


            if (completed == 1) {
                if (color == 0) {
                    if (runtime.seconds() < 1) {
                        jewelStick.setPosition(.29);
                    } else if (runtime.seconds() < 2) {
                        jewelStick.setPosition(.3);
                    } else if (runtime.seconds() < 3) {
                        jewelStick.setPosition(.28);
                    } else if (runtime.seconds() < 4) {
                        jewelStick.setPosition(.275);
                    }
                    //jewelStick.setPosition(.27);

//                    if(runtime.seconds()<1){
//                        intakeBucket.setPosition(.7);
//
//                    }
//                    if(runtime.seconds()>2 && runtime.seconds()<3){
//                        fwopperDrive.setPower(1);
//                    }
//                    else{
//                        fwopperDrive.setPower(0);
//                    }
//                    if(runtime.seconds()>3) {
//                        intakeBucket.setPosition(.3);
//                    }

                    int leftRed=leftJewel.red();
                    int leftBlue=leftJewel.blue();
                    int leftGreen=leftJewel.green();

                    int rightRed=rightJewel.red();
                    int rightBlue=rightJewel.blue();
                    int rightGreen=rightJewel.green();

                    if (runtime.seconds() > 4.5) {
                        target = 0;
                        color = 3;
                    } else if (leftBlue/leftGreen>0.74 && leftBlue/leftGreen<0.81 && rightRed/rightGreen>0.4 && rightRed/rightGreen<0.45) {
                        telemetry.addData("left side is red", "right side is blue");
                        color = 2;
                        curr = testGyro.getHeading();
                        target = 16;
                        // sleep(1000);
                    } else if (rightBlue/rightGreen>0.74 && rightBlue/rightGreen<0.81 && leftRed/leftGreen>0.4 && leftRed/leftGreen<0.45) {
                        telemetry.addData("right side is red", "left side is blue");
                        color = 1;
                        curr = testGyro.getHeading();
                        target = -16;
                        //sleep(1000);

                    } else {
                        telemetry.addData("No reading", "");
                    }

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
                        // telemetry.addData("i", turn.turnT(target, 0.004, 0.001, 0.0, 1));
                        // telemetry.addData("i", turn.turnT(0, 0.0052, 0.002, 0.0, 1));
                        isComplete = turn.turnT(target, 0.008, 0.0004, 0.0, 3);
                        telemetry.addData("i", isComplete);
                        //isComplete = turn.turnT(target, p, 0, 0.0, 1);
                    }
                    if (isComplete == 0.0) {
                        jewelStick.setPosition(.5);
                        isComplete = 1;
                        //sleep(1000);
                        stage++;
                    }
                    if (stage == 1) {
                        telemetry.addData("turning to 0", "ye");
                        isComplete = turn.turnT(0, 0.007, .0003, 0.0, .5);

                        // completed++;
                    }

                    if (stage >= 2) {
                        telemetry.addData("completed", "yay");
                        Motors.setP(0, 0, 0);
                        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


                        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                        completed++;
                        runtime.reset();
                    }
                    //telemetry.addData("comp:", completed);
                }
            }
//
//
//
//
            if (completed == 2) {
                flPosition=fl.getCurrentPosition();
                telemetry.addData("Path0", fr.getCurrentPosition());
                telemetry.addData("Path0", fl.getCurrentPosition());
                telemetry.addData("Path0", br.getCurrentPosition());
                telemetry.addData("Path0", bl.getCurrentPosition());

                if (matchGlyph == RelicRecoveryVuMark.RIGHT) {
                    fr.setTargetPosition(875);
                    bl.setTargetPosition(-875);
                    fl.setTargetPosition(-875);
                    br.setTargetPosition(875);
                } else if (matchGlyph == RelicRecoveryVuMark.LEFT) {
                    fr.setTargetPosition(1025);
                    bl.setTargetPosition(-1025);
                    fl.setTargetPosition(-1025);
                    br.setTargetPosition(1025);
                } else {
                    fr.setTargetPosition(950);
                    bl.setTargetPosition(-950);
                    fl.setTargetPosition(-950);
                    br.setTargetPosition(950);
                }

                fr.setPower(0.2);
                fl.setPower(0.2);
                br.setPower(0.2);
                bl.setPower(0.2);
                if (fl.getCurrentPosition()<flPosition || runtime.time()>3) {
                    completed++;
                    runtime.reset();
                    telemetry.addData("yeet", "we won");
                    br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                }
            }

            if (completed == 3) {
                isComplete = turn.turnT(90, 0.009, 0.0003, 0.0, .5);
                telemetry.addData("i", isComplete);
                if (isComplete == 0 || runtime.seconds() > 3) {
                    completed++;
                    br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                    fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                    runtime.reset();
                }
            }
            if (completed == 4) {
                flPosition=fl.getCurrentPosition();

                fr.setTargetPosition(400);
                fr.setPower(0.2);

                fl.setTargetPosition(-400);
                fl.setPower(0.2);

                br.setTargetPosition(400);
                br.setPower(0.2);

                bl.setTargetPosition(-400);
                bl.setPower(0.2);
                if (runtime.seconds() > 2 || flPosition<fl.getCurrentPosition()) {
                    completed++;
                    runtime.reset();
                }

            }
            if (completed == 5) {
                telemetry.addData("flippin it", "flipity dip");
                conveyor.setPower(-1);
                conveyor.setPower(-1);
                intakeBucket.setPosition(.62);
                if (runtime.seconds() > 1.6) {

                    runtime.reset();
                    completed++;
                    conveyor.setPower(0);
                    fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }

            if (completed == 6) {
                conveyorTop.setPosition(1);
                fr.setTargetPosition(-420);


                fl.setTargetPosition(420);


                br.setTargetPosition(-420);


                bl.setTargetPosition(420);
                if (runtime.seconds() > .5) {
                    fl.setPower(0.2);
                    br.setPower(0.2);
                    fr.setPower(0.2);
                    bl.setPower(0.2);
                } else {
                    fl.setPower(0);
                    br.setPower(0);
                    fr.setPower(0);
                    bl.setPower(0);
                }
                if (runtime.seconds() > 2.2) {
                    completed += .5;

                    fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


                    br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                    runtime.reset();
                }

            }
            if (completed == 6.5) {
                fr.setTargetPosition(150);
                fr.setPower(0.2);

                fl.setTargetPosition(-150);
                fl.setPower(0.2);

                br.setTargetPosition(150);
                br.setPower(0.2);

                bl.setTargetPosition(-150);
                bl.setPower(0.2);
                if (runtime.seconds() > 1.5) {
                    completed += .5;

                    fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


                    br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                    runtime.reset();
                }
            }
            if (completed == 7) {
                leftIntakeFlipper.setPosition(1);
                rightIntakeFlipper.setPosition(1);
                conveyorTop.setPosition(.45);
                fr.setTargetPosition(0);
                fl.setTargetPosition(0);
                br.setTargetPosition(0);
                bl.setTargetPosition(0);

                br.setPower(1);
                bl.setPower(1);
                fl.setPower(1);
                fr.setPower(1);
                intakeBucket.setPosition(0.15);
                conveyor.setPower(-.45);
                if (runtime.seconds() > 2.5) {
                    completed++;

//                    fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                    bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                    fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                    br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//
//                    br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                    runtime.reset();
                }
            }
            if (completed == 8) {

                leftIntakeFlipper.setPosition(1);
                rightIntakeFlipper.setPosition(1);
                intakeBucket.setPosition(.62);
                br.setPower(1);
                bl.setPower(1);
                fl.setPower(1);
                fr.setPower(1);
                intakeDrive.setPower(.8);
                fwoppers.setPower(1);
                if (runtime.seconds() > 2) {
                    runtime.reset();
                    completed++;
                }
            }
            if (completed == 9) {
                intakeBucket.setPosition(.62);
                br.setPower(1);
                bl.setPower(1);
                fl.setPower(1);
                fr.setPower(1);
                intakeDrive.setPower(-.8);
                fwoppers.setPower(1);
                if (runtime.seconds() > 1.5) {
                    runtime.reset();
                    completed++;
                }
            }
            if (completed == 10) {
                br.setPower(1);
                bl.setPower(1);
                fl.setPower(1);
                fr.setPower(1);
                intakeDrive.setPower(0);
                fwoppers.setPower(1);
                intakeBucket.setPosition(0);
                conveyor.setPower(-.45);
                if (runtime.seconds() > .8) {
                    runtime.reset();
                    completed = 8;
                    //g completed++;
                }
            }
            if (completed == 42) {
                fr.setTargetPosition(100);
                fr.setPower(0.2);

                fl.setTargetPosition(-100);
                fl.setPower(0.2);

                br.setTargetPosition(100);
                br.setPower(0.2);

                bl.setTargetPosition(-100);
                bl.setPower(0.2);
            }
            if (totalTime.seconds() > 29.5 && completed < 20) {

                fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


                br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                completed = 42;
            }
//            if(completed==11){
////                br.setPower(1);
////                bl.setPower(1);
////                fl.setPower(1);
////                fr.setPower(1);
//                intakeDrive.setPower(0);
//                fwoppers.setPower(1);
//                intakeBucket.setPosition(0);
//                if(runtime.seconds()>.8){
//                    runtime.reset();
//                    completed++;
//                }
//            }
///**/        if(completed==12) {
//                fl.setTargetPosition(-200);
//                fr.setTargetPosition(200);
//                bl.setTargetPosition(-200);
//                br.setTargetPosition(200);
//                completed++;
//            }
/**/
            telemetry.update();

        }
    }
}