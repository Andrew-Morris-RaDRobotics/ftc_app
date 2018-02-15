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

@Autonomous (name= "BlueAutoCompv2", group= "competition")

public class BlueAutoCOMPV2 extends LinearOpMode {

    public Servo jewelStick;
    public Servo conveyorTop;
    public ColorSensor leftJewel;
    public ColorSensor rightJewel;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    public DcMotor intakeDrive;
    public motorDeclaration Motors;
    public gyroCompass testGyro;
    public DcMotor fwopperDrive;
public DcMotor conveyor;
    public Servo intakeBucket;

    public turnTo turn;


    @Override
    public void runOpMode() throws InterruptedException {
        intakeDrive = hardwareMap.dcMotor.get("intakeDrive");
        intakeDrive.setDirection(DcMotor.Direction.REVERSE);
        
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
        //glyph = new GlyphVision(hardwareMap);
        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap, testGyro);
        Motors = new motorDeclaration(hardwareMap);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Path0", "Starting at %7d :%7d",
                fr.getCurrentPosition(),
                bl.getCurrentPosition());
        telemetry.update();


        jewelStick = hardwareMap.servo.get("jewelStick");
        leftJewel = hardwareMap.colorSensor.get("leftJewel");
        rightJewel = hardwareMap.colorSensor.get("rightJewel");
        fwopperDrive = hardwareMap.dcMotor.get("fwopperDrive");
        intakeBucket = hardwareMap.servo.get("intakeBucket");


        int newLeftTarget = 0;
        int newRightTarget = 0;
        double target = 0;
        waitForStart();


        int completed = 1;
        int color = 0; //1 is red 2 is blu (left side)
        double curr = 0.0;
        int stage = 0;
        double isComplete = 1;
        double p = .0019;
        double i = .0003;
        int updatin = 0;
        int Timer = 0;
        ElapsedTime     runtime = new ElapsedTime();
        runtime.reset();
       // RelicRecoveryVuMark matchGlyph = null;
        while (opModeIsActive()) {

//            if (completed == 0) {
//
//                matchGlyph = glyph.getGlyph();
//
//                if (matchGlyph != null) {
//                    completed++;
//                }
//            }
//            telemetry.addData("glyph: ", matchGlyph);
            telemetry.addData("Completedvar", completed);

            if (completed == 1) {
                if (color == 0) {

                        jewelStick.setPosition(.27);

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

                    if(runtime.seconds()>7.0) {
                        target = 0;
                        color = 3;
                    }
                    else if (leftJewel.red() - 1 > rightJewel.red() && rightJewel.blue() - 1 > leftJewel.blue()&& runtime.seconds()>2) {
                        telemetry.addData("left side is red", "right side is blue");
                        color = 2;
                        curr = testGyro.getHeading();
                        target = -20;
                        // sleep(1000);
                    } else if (leftJewel.red() + 1 < rightJewel.red() && rightJewel.blue() + 1 < leftJewel.blue()&& runtime.seconds()>2) {
                        telemetry.addData("right side is red", "left side is blue");
                        color = 1;
                        curr = testGyro.getHeading();
                        target = 20;
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
                        jewelStick.setPosition(1);
                        isComplete = 1;
                        //sleep(1000);
                        stage++;
                    }
                    if (stage == 1) {
                        telemetry.addData("turning to 0", "ye");
                        isComplete = turn.turnT(0, 0.0075, .0003, 0.0, 1);

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
                telemetry.addData("Path0",fr.getCurrentPosition());
                telemetry.addData("Path0",fl.getCurrentPosition());
                telemetry.addData("Path0",br.getCurrentPosition());
                telemetry.addData("Path0",bl.getCurrentPosition());
//                if (fr.getCurrentPosition() < 1000) {
//                    Motors.setP(-.2, 0, 0);
//                }
//                else{
//                    Motors.setP(0,0,0);
//                }
                fr.setTargetPosition(-1000);
                fr.setPower(0.2);

                fl.setTargetPosition(1000);
                fl.setPower(0.2);

                br.setTargetPosition(-1000);
                br.setPower(0.2);

                bl.setTargetPosition(1000);
                bl.setPower(0.2);
                if(runtime.seconds()>3){
                    //completed++;
                    runtime.reset();
telemetry.addData("yeet","we won");
//                    br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                    bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                    fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                    fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                }
            }
            /*
            if(completed==3){

                isComplete = turn.turnT(90, 0.008, 0.0004, 0.0, 3);
                telemetry.addData("i", isComplete);
                if(isComplete==0 || runtime.seconds()>3){
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
            if(completed==4){

                fr.setTargetPosition(400);
                fr.setPower(0.2);

                fl.setTargetPosition(-400);
                fl.setPower(0.2);

                br.setTargetPosition(400);
                br.setPower(0.2);

                bl.setTargetPosition(-400);
                bl.setPower(0.2);
                if(runtime.seconds()>3){
                    completed++;
                    runtime.reset();
                }

            }
            if(completed==5){
                telemetry.addData("flippin it","flipity dip");
                conveyor.setPower(-1);
                conveyor.setPower(-1);
                conveyorTop.setPosition(1);
                if(runtime.seconds()>5){
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

            if(completed==6){

                fr.setTargetPosition(-200);
                fr.setPower(0.2);

                fl.setTargetPosition(200);
                fl.setPower(0.2);

                br.setTargetPosition(-200);
                br.setPower(0.2);

                bl.setTargetPosition(200);
                bl.setPower(0.2);
                if(runtime.seconds()>3){
                    completed++;
                    conveyorTop.setPosition(.45);
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
            if(completed==7){
                fr.setTargetPosition(0);
                fr.setPower(0.2);

                fl.setTargetPosition(0);
                fl.setPower(0.2);

                br.setTargetPosition(0);
                br.setPower(0.2);

                bl.setTargetPosition(0);
                bl.setPower(0.2);
                conveyor.setPower(-.45);
            }
            */
            telemetry.update();
        }
    }
}