package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by DeathChicken on 11/25/2017.
 */
@TeleOp(name = "intakeTest", group = "testing")
public class intakeTest extends OpMode{
    
    public DcMotor intakeDrive;
    public Servo leftIntakeFlipper;
    public Servo rightIntakeFlipper;
    public Servo intakeBucket;
    public DcMotor fwopperDrive;
    
    @Override
    public void init() {
        leftIntakeFlipper = hardwareMap.servo.get("leftIntakeFlipper");
        rightIntakeFlipper = hardwareMap.servo.get("rightIntakeFlipper");
        intakeBucket = hardwareMap.servo.get("intakeBucket");
        intakeDrive = hardwareMap.dcMotor.get("intakeDrive");
        fwopperDrive = hardwareMap.dcMotor.get("fwopperDrive");
    }

    @Override
    public void loop() {
        if (gamepad1.x){
            leftIntakeFlipper.setPosition(0.8);
            rightIntakeFlipper.setPosition(0.8);
        }
        else if (gamepad1.b) {
            leftIntakeFlipper.setPosition(0.2);
            rightIntakeFlipper.setPosition(0.2);
        }
        else if (gamepad1.y) {
            intakeBucket.setPosition(1);
        }
        else if (gamepad1.a) {
            intakeBucket.setPosition(0);
        }
        else if (gamepad1.dpad_up) {
            intakeDrive.setPower(0.5);
        }
        else if (gamepad1.dpad_down) {
            intakeDrive.setPower(-0.5);
        }
        else if (gamepad1.dpad_left) {
            fwopperDrive.setPower(0.5);
        }
        else if (gamepad1.dpad_right) {
            fwopperDrive.setPower(-0.5);
        }
        else {
            fwopperDrive.setPower(0);
            intakeDrive.setPower(0);
        }


    }
}
