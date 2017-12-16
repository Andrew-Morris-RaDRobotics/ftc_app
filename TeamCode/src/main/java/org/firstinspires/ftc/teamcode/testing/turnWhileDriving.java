package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utils.motorDeclaration;
/**
 * Created by Andrew on 12/11/2017.
 */
@TeleOp(name = "TurnWhileDriving", group = "testing")
public class turnWhileDriving extends OpMode {
    public motorDeclaration motors;
    public DcMotor fr;
    public DcMotor fl;
    public DcMotor bl;
    public DcMotor br;
    @Override
    public void init() {
        motors = new motorDeclaration(hardwareMap);
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
    }

    @Override
    public void loop() {
        double speed = 0.25;
        speed = speed + gamepad1.right_trigger*0.5;

        double frSpeed = (speed)*(-gamepad1.left_stick_y -gamepad1.left_stick_x -gamepad1.right_stick_x);
        double flSpeed = (speed)*(+gamepad1.left_stick_y -gamepad1.left_stick_x -gamepad1.right_stick_x);
        double brSpeed = (speed)*(-gamepad1.left_stick_y +gamepad1.left_stick_x -gamepad1.right_stick_x);
        double blSpeed = (speed)*(+gamepad1.left_stick_y +gamepad1.left_stick_x -gamepad1.right_stick_x);


        fr.setPower(frSpeed);
        fl.setPower(flSpeed);
        br.setPower(brSpeed);
        bl.setPower(blSpeed);





        if (gamepad1.a) {
            motors.setP(1,0,0.5);
            telemetry.addData("Motors", "1, 0, 0.5");
        }
        else if (gamepad1.x) {
            motors.setP(1,0,2);
            telemetry.addData("Motors","1,0,2");
        }
        else if (gamepad1.y) {
            motors.setP(1,1,1);
        }
    }
}