package org.firstinspires.ftc.teamcode.ChallengeFile;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.gyroCompass;

/**
 * Created by DeathChicken on 11/22/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "DrivingAndServo", group = "Challenge")

public class DrivingAndServo extends OpMode {


    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    gyroCompass testGyro;
    Servo jewelServo;

    @Override
    public void init() {
        // testGyro = new gyroCompass(hardwareMap);
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        jewelServo = hardwareMap.servo.get("jewelStick");

    }

    //robot goes forward to the right
    @Override
    public void loop() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //jewelServo.setPosition(.5);
        //      double heading = testGyro.getHeading();
        double targetDegrees = 90;
        double deadzone = 10;
        //     double error = targetDegrees - heading;
        double Kp = 1.0 / 150;
        // double power = -Kp * error;

        //       telemetry.addData("heading", String.valueOf(testGyro.getHeading()));
        //   telemetry.addData("Error = ", error);
        //   telemetry.addData("Power = ", power);
        telemetry.update();
        double speed = 0.25;
        speed = speed + gamepad1.right_trigger * 0.5;
       // System.out.println(gamepad1.right_bumper);

 //       if (gamepad1.a) {

//            fl.setPower((-1.0/150) * (0-heading));
//            br.setPower((-1.0/150) * (0-heading));


 //       }
/*        else { */
            double frSpeed = (speed) * (-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            double flSpeed = (speed) * (+gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            double brSpeed = (speed) * (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            double blSpeed = (speed) * (+gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);

            fr.setPower(frSpeed);
            fl.setPower(flSpeed);
            br.setPower(brSpeed);
            bl.setPower(blSpeed);
  //      }

        if (gamepad1.left_bumper) {
            jewelServo.setPosition(0);
            //jewelStick.setPosition(170 / 190.5);
        } else if (gamepad1.right_bumper) {
            jewelServo.setPosition(1);
            //jewelStick.setPosition(0);
        } else {
            jewelServo.setPosition(.5);
            //jewelStick.setPosition(90 / 190.5);
        }

        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
