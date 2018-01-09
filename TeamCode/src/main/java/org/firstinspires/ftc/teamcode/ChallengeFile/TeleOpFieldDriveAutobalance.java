package org.firstinspires.ftc.teamcode.ChallengeFile;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystem.drive_at_angle_psudo;
import org.firstinspires.ftc.teamcode.utils.gyroCompass;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOpFieldDriveAutobalance", group = "ChallengeFile")
public class TeleOpFieldDriveAutobalance extends OpMode {

    public DcMotor stanley;

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    Servo leftIntakeFlipper;
    Servo rightIntakeFlipper;
    DcMotor intakeDrive;
    DcMotor fwopperDrive;
    DcMotor conveyor;
    gyroCompass testGyro;
    drive_at_angle_psudo thing;

    Servo intakeBucket;
    Servo jewelStick;
    int floppers;
    double position;
    int conveyorP;
    boolean balanceEnabled;
    boolean offBalance;
    double angle2 = 0;
    boolean tankdrive = false;
    boolean startPressed = false;

    //    Servo leftSorter;
//    Servo rightSorter;
    @Override
    public void init() {
        jewelStick = hardwareMap.servo.get("jewelStick");
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        fr.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
        leftIntakeFlipper = hardwareMap.servo.get("leftIntakeFlipper");
        rightIntakeFlipper = hardwareMap.servo.get("rightIntakeFlipper");
        intakeBucket = hardwareMap.servo.get("intakeBucket");
        intakeDrive = hardwareMap.dcMotor.get("intakeDrive");
        fwopperDrive = hardwareMap.dcMotor.get("fwopperDrive");
        conveyor = hardwareMap.dcMotor.get("conveyer");
//        leftSorter = hardwareMap.servo.get("leftSorter");
//        rightSorter = hardwareMap.servo.get("rightSorter");
        conveyorP = 0;
        floppers = 0;
        position = 0.0;
        testGyro = new gyroCompass(hardwareMap);
        balanceEnabled = true;
    }

    @Override
    public void loop() {
        double speed = 0.35;
        double speed2 = 0.7;
        speed = speed + gamepad1.right_trigger * 0.65;
        speed2 = speed2 + gamepad2.right_trigger / 1.65;

        if (gamepad1.start && startPressed == false) {
            startPressed = true;
        } else if (!gamepad1.start) {
            startPressed = false;
        }

        if (startPressed = true) {
            if (tankdrive) {
                tankdrive = false;
            } else if (tankdrive = false) {
                tankdrive = true;
            }
        }

        telemetry.addData("Tankdrive",tankdrive);


        //System.out.println(gamepad1.right_bumper);
        if (tankdrive == false) {
            double x2 = gamepad1.left_stick_x;
            double y2 = -1 * gamepad1.left_stick_y;
            if (x2 > 0 && y2 > 0) {
                angle2 = Math.toDegrees(Math.atan(x2 / y2));
            } else if (x2 > 0 && y2 <= 0) {
                angle2 = 90 + -1 * Math.toDegrees(Math.atan(y2 / x2));
            } else if (x2 < 0 && y2 <= 0) {
                angle2 = -90 + -1 * Math.toDegrees(Math.atan(y2 / x2));
            } else if (x2 < 0 && y2 > 0) {
                angle2 = Math.toDegrees(Math.atan(x2 / y2));
            } else if (x2 == 0 && y2 < 0.15) {
                angle2 = 180.0;
            } else if (x2 == 0 && y2 > 0.15) {
                angle2 = 0.0;
            }

            telemetry.addData("gyro", testGyro.getHeading());
//        if(((Math.abs(x)>.08)||(Math.abs(y)>.08))){
//                turn.turnT(angle, p, i, 0.0, 1);
//            dontDoIt=true;
//            }

            if (((Math.abs(x2) > .08) || (Math.abs(y2) > .08)) &&offBalance&&balanceEnabled) {
                thing.angle(angle2 - (-1 * testGyro.getHeading()), speed, -1 * gamepad1.right_stick_x * (speed + .03));
                telemetry.addData("angle", angle2);
                telemetry.addData("target angle", angle2 - (-1 * testGyro.getHeading()));
            } else if (Math.abs(gamepad1.right_stick_x) > .05 && offBalance&&balanceEnabled) {
                fr.setPower(-1 * gamepad1.right_stick_x * speed);
                fl.setPower(-1 * gamepad1.right_stick_x * speed);
                br.setPower(-1 * gamepad1.right_stick_x * speed);
                bl.setPower(-1 * gamepad1.right_stick_x * speed);
            }
        } else {
            double frSpeed = (speed) * -(-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
            double flSpeed = (speed) * -(+gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
            double brSpeed = (speed) * -(-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
            double blSpeed = (speed) * -(+gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
            if(offBalance&&balanceEnabled) {
            fr.setPower(frSpeed);
            fl.setPower(flSpeed);
            br.setPower(brSpeed);
            bl.setPower(blSpeed);
        }
        }
        // }

//conveyor
        if (gamepad2.dpad_down) {
            conveyorP = 1;
        } else if (gamepad2.dpad_up) {
            conveyorP = -1;
        } else {
            conveyorP = 0;
        }

        if (conveyorP == 1) {
            conveyor.setPower(speed2 - .1);
        } else if (conveyorP == -1) {
            conveyor.setPower(-speed2 + .1);
        } else {
            conveyor.setPower(0);
        }

        //floppers
        if (gamepad2.a) {
            floppers = 1;
        } else if (gamepad2.x) {
            floppers = -1;
        } else if (gamepad2.b) {
            floppers = 0;
        }
        if (floppers == 1) {
            intakeDrive.setPower(speed2);
        } else if (floppers == -1) {
            intakeDrive.setPower(-speed2);
        } else {
            intakeDrive.setPower(0);
        }

        if (gamepad2.left_bumper) {
            fwopperDrive.setPower(speed2);
        } else if (gamepad2.right_bumper) {
            fwopperDrive.setPower(-(speed2));
        } else {
            fwopperDrive.setPower(0);
        }
        position = position + (gamepad2.right_stick_y * .1);
        if (position < 0) {
            position = 0;
        }
        if (position > 1) {
            position = 1;
        }
        leftIntakeFlipper.setPosition(position);
        rightIntakeFlipper.setPosition(position);
        telemetry.addData("pos", position);
        telemetry.update();
        intakeBucket.setPosition(.68 - (gamepad2.left_trigger * .5));
        jewelStick.setPosition(.1 + gamepad1.left_trigger);

        if(gamepad1.a){
            balanceEnabled=true;
        }
        if(gamepad1.b){
            balanceEnabled=false;
        }

        double pitch = testGyro.getPitch();
        double roll = -1 * (testGyro.getRoll());
       // switched them because im lazy
         double asdf = testGyro.getHeading();
        telemetry.addData("roll", roll);
         telemetry.addData("pitch", pitch);
         telemetry.addData("heading", asdf);
        // telemetry.addData("test: ", xp);
         //xp++;
          telemetry.update();

        if ( (roll > 2) || (roll < -2) || (pitch > 2) || (pitch < -2) ) {
            offBalance=true;

            if(roll<3 && roll>-3){
                roll = 0;
            }
            if(pitch<3 && pitch>-3){
                pitch = 0;
            }


            if(balanceEnabled) {
                fr.setPower(-(.032) * (-roll + pitch));
                fl.setPower(-(.032) * (+roll + pitch));
                br.setPower(-(.032) * (-roll - pitch));
                bl.setPower(-(.032) * (+roll - pitch));
            }


        }
        else {
            telemetry.addData("Level", "");
offBalance=false;

        }








    }
}
