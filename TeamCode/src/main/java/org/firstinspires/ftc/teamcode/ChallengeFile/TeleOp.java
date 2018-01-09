package org.firstinspires.ftc.teamcode.ChallengeFile;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.gyroCompass;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "ChallengeFile")
public class TeleOp extends OpMode {

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

    Servo intakeBucket;
    Servo jewelStick;
    int floppers;
    double position;
    int conveyorP;
    boolean balanceEnabled;
    boolean offBalance;
    @Override
    public void init() {
        jewelStick = hardwareMap.servo.get("jewelStick");
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
      leftIntakeFlipper = hardwareMap.servo.get("leftIntakeFlipper");
      rightIntakeFlipper = hardwareMap.servo.get("rightIntakeFlipper");
        intakeBucket = hardwareMap.servo.get("intakeBucket");
        intakeDrive = hardwareMap.dcMotor.get("intakeDrive");
        fwopperDrive = hardwareMap.dcMotor.get("fwopperDrive");
        conveyor = hardwareMap.dcMotor.get("conveyer");
        conveyorP=0;
        floppers = 0;
        position=0.0;
        testGyro = new gyroCompass(hardwareMap);
        balanceEnabled=true;
    }

    @Override
    public void loop() {
        double speed = 0.35;
        double speed2 = 0.35;
        speed = speed + gamepad1.right_trigger * 0.65;
        speed2 = speed2 + gamepad2.right_trigger * 0.65;

        //System.out.println(gamepad1.right_bumper);
        double frSpeed = (speed) * -(-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
        double flSpeed = (speed) * -(+gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
        double brSpeed = (speed) * -(-gamepad1.left_stick_y  + gamepad1.left_stick_x - gamepad1.right_stick_x);
        double blSpeed = (speed) * -(+gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
       //if(offBalance&&balanceEnabled) {
           fr.setPower(frSpeed);
           fl.setPower(flSpeed);
           br.setPower(brSpeed);
           bl.setPower(blSpeed);
      // }

//conveyor
        if (gamepad2.dpad_down) {
            conveyorP=1;
        } else if (gamepad2.dpad_up) {
            conveyorP=-1;
        }
        else if(gamepad2.dpad_left||gamepad2.dpad_right){
            conveyorP=0;
        }

        if(conveyorP==1){
            conveyor.setPower(speed2-.1);
        }
        else if(conveyorP==-1){
            conveyor.setPower(-speed2+.1);
        }
        else{
            conveyor.setPower(0);
        }

        //floppers
        if(gamepad2.a){
            floppers = 1;
        }
        else if(gamepad2.b){
            floppers=0;
        }
        else if(gamepad2.x){
            floppers=-1;
        }
        if(floppers==1){
            intakeDrive.setPower(speed2*2);
        }
        else if(floppers==-1){
            intakeDrive.setPower(-speed2*2);
        }
        else{
            intakeDrive.setPower(0);
        }


        fwopperDrive.setPower(speed2*gamepad2.left_stick_y);

        position=position+(gamepad2.right_stick_y*.01);
        if(position<0){
            position=0;
        }
        if(position>1){
            position=1;
        }
        leftIntakeFlipper.setPosition(position);
        rightIntakeFlipper.setPosition(position);
        telemetry.addData("pos",position);
        telemetry.update();
        intakeBucket.setPosition(.68-(gamepad2.left_trigger*.5));
        jewelStick.setPosition(.5+gamepad1.left_trigger);




       double pitch = testGyro.getPitch();
       double roll = -1*(testGyro.getRoll());
        //switched them because im lazy
       // asdf = testGyro.getHeading();
        //telemetry.addData("roll", roll);
       // telemetry.addData("pitch", pitch);
       // telemetry.addData("heading", asdf);
       // telemetry.addData("test: ", xp);
       // xp++;
      //  telemetry.update();
/*
        if ( (roll > 2) || (roll < -2) || (pitch > 2) || (pitch < -2) ) {
            offBalance=true;
            //ls y = roll
            //ls r = pitch
//                    try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            if(roll<3 && roll>-3){
                roll = 0;
            }
            if(pitch<3 && pitch>-3){
                pitch = 0;
            }

            if(gamepad1.a){
                balanceEnabled=true;
            }
            if(gamepad1.b){
                balanceEnabled=false;
            }
            if(balanceEnabled) {
                fr.setPower(-(.032) * (-roll + pitch));
                fl.setPower(-(.032) * (+roll + pitch));
                br.setPower(-(.032) * (-roll - pitch));
                bl.setPower(-(.032) * (+roll - pitch));
            }

//            br.setPower(0.5);
//            telemetry.addData("roll", roll);
//            telemetry.addData("pitch", pitch);
//            telemetry.update();
//            pitch = testGyro.getPitch();
//            roll = testGyro.getRoll();
            // pitch=formatAngle(angles.angleUnit, angles.thirdAngle);
            //roll=formatAngle(angles.angleUnit, angles.secondAngle);
        }
        else {
            telemetry.addData("Level", "");
offBalance=false;

        }
*/


        //telemetry.addData("Level", "");
//        dsadddddddddddddddddddddd

//        double intakeSpeed = gamepad1.left_trigger-gamepad1.right_trigger;
//        intakeDrive.setPower(intakeSpeed);

        // Code using the second gamepad (for the operator/robot controller).
//        double fwopperSpeed = gamepad2.left_stick_y;
//        fwopperDrive.setPower(fwopperSpeed);
//
//        double intakeSpeed = gamepad2.right_stick_y;
//        intakeDrive.setPower(intakeSpeed);

    }
}
