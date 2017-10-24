package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "PortTest", group = "testing")
public class PortTest extends OpMode {

    public DcMotor stanley;

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fl");
        fl = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("br");
        br = hardwareMap.dcMotor.get("bl");
    }
    //front left motor is front right motor.
    //robot goes forward to the right
    @Override
    public void loop() {


        fr.setPower(gamepad1.left_stick_x);
        fl.setPower(gamepad1.left_stick_y);
        br.setPower(gamepad1.right_stick_x);
        bl.setPower(gamepad1.right_stick_y);


    }
}
