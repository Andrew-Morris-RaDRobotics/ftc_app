package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class intakeDeclaration {
    Servo leftIntakeFlipper;
    Servo rightIntakeFlipper;
    DcMotor intakeDrive;
    DcMotor fwopperDrive;
    DcMotor conveyor;
    Servo intakeBucket;

    public intakeDeclaration(HardwareMap hardwareMap) {
        leftIntakeFlipper = hardwareMap.servo.get("leftIntakeFlipper");
        rightIntakeFlipper = hardwareMap.servo.get("rightIntakeFlipper");
        intakeBucket = hardwareMap.servo.get("intakeBucket");
        intakeDrive = hardwareMap.dcMotor.get("intakeDrive");
        fwopperDrive = hardwareMap.dcMotor.get("fwopperDrive");
        conveyor = hardwareMap.dcMotor.get("conveyer");
    }
     public void runIntake (double intakePower) {
         intakeDrive.setPower(intakePower);
     }

    public void driveIntake (double drivePower) {
        fwopperDrive.setPower(drivePower);
    }

    public void runConveyor (double conveyorPower) {
        conveyor.setPower(conveyorPower);
    }

    public void bucketRaise (boolean raiseUp) {
        if (raiseUp) {
            intakeBucket.setPosition(0.18);
        } else {
            intakeBucket.setPosition(0.68);
        }
    }

    public void runAll (double intakePower, double conveyorPower) {
        intakeDrive.setPower(intakePower);
        conveyor.setPower(conveyorPower);
    }
}
