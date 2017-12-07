package org.firstinspires.ftc.teamcode.ChallengeFile;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "ChallengeFile")
public class TeleOp extends OpMode {

    public DcMotor stanley;

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    private Servo leftIntakeFlipper;
    private Servo rightIntakeFlipper;
    private DcMotor intakeDrive;
    private DcMotor fwopperDrive;
    private Servo intakeBucket;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        leftIntakeFlipper = hardwareMap.servo.get("leftIntakeFlipper");
        rightIntakeFlipper = hardwareMap.servo.get("rightIntakeFlipper");
        intakeBucket = hardwareMap.servo.get("intakeBucket");
        intakeDrive = hardwareMap.dcMotor.get("intakeDrive");
        fwopperDrive = hardwareMap.dcMotor.get("fwopperDrive");
    }
    @Override
    public void loop() {
        double speed = 0.25;
        speed = speed + gamepad1.right_trigger*0.5;


        System.out.println(gamepad1.right_bumper);
        double frSpeed = (speed)*(-gamepad1.left_stick_y +gamepad1.left_stick_x -gamepad1.right_stick_x);
        double flSpeed = (speed)*(+gamepad1.left_stick_y +gamepad1.left_stick_x -gamepad1.right_stick_x);
        double brSpeed = (speed)*(-gamepad1.left_stick_y -gamepad1.left_stick_x -gamepad1.right_stick_x);
        double blSpeed = (speed)*(+gamepad1.left_stick_y -gamepad1.left_stick_x -gamepad1.right_stick_x);

        fr.setPower(frSpeed);
        fl.setPower(flSpeed);
        br.setPower(brSpeed);
        bl.setPower(blSpeed);

        boolean fwopperSpeedForward = gamepad1.dpad_up;
        boolean fwopperSpeedBack = gamepad1.dpad_down;
        if (fwopperSpeedForward) {
            fwopperDrive.setPower(0.2);
        } else if (fwopperSpeedBack) {
            fwopperDrive.setPower(-0.2);
        }

        double intakeSpeed = gamepad1.left_trigger;
        intakeDrive.setPower(intakeSpeed);

        // Code using the second gamepad (for the operator/robot controller).
//        double fwopperSpeed = gamepad2.left_stick_y;
//        fwopperDrive.setPower(fwopperSpeed);
//
//        double intakeSpeed = gamepad2.right_stick_y;
//        intakeDrive.setPower(intakeSpeed);

    }
}
