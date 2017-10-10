package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "STANLEY test", group = "testing")
public class OmnidriveTest extends OpMode {

    public DcMotor stanley;

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");
    }
        //front left motor is front right motor.
        //robot goes forward to the right
    @Override
    public void loop() {
        double frSpeed = gamepad1.left_stick_y  -gamepad1.left_stick_x -gamepad1.right_stick_x;
        double flSpeed = gamepad1.left_stick_y +gamepad1.right_stick_x -gamepad1.left_stick_x;
        double brSpeed = -gamepad1.left_stick_y +gamepad1.left_stick_x -gamepad1.right_stick_x;
        double blSpeed = -gamepad1.left_stick_y +gamepad1.left_stick_x +gamepad1.right_stick_x;

        frSpeed = frSpeed/1.1*5;
        flSpeed = flSpeed/-0.1*5;
        brSpeed = brSpeed/1.15*5;
        blSpeed = blSpeed/-0.8*5;


        fr.setPower(frSpeed);
        fl.setPower(flSpeed);
        br.setPower(brSpeed);
        bl.setPower(blSpeed);


    }
}
