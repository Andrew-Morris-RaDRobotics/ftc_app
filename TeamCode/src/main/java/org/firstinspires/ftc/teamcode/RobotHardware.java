package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotHardware {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public RobotHardware() {
    }

    public void initialize(HardwareMap hardwareMap) {
        leftMotor = new SyncedDcMotors(hardwareMap, DcMotorSimple.Direction.REVERSE, SyncedDcMotors.ALL_SAME, "left1", "left2");
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor = new SyncedDcMotors(hardwareMap, DcMotorSimple.Direction.FORWARD, SyncedDcMotors.ALL_SAME, "right1", "right2");
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public DcMotor getLeftMotor() {
        return leftMotor;
    }

    public DcMotor getRightMotor() {
        return rightMotor;
    }
}
