package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Tgodden on 9/30/2017.
 */
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

    @Override
    public void loop() {
        fr.setPower(-gamepad1.left_stick_y  -gamepad1.left_stick_x -gamepad1.right_stick_x);
        fl.setPower(gamepad1.left_stick_y -gamepad1.right_stick_x -gamepad1.left_stick_x);
        br.setPower(gamepad1.left_stick_y +gamepad1.left_stick_x -gamepad1.right_stick_x);
        bl.setPower(-gamepad1.left_stick_y +gamepad1.left_stick_x -gamepad1.right_stick_x);
    }
}
