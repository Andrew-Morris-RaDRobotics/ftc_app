package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by DeathChicken on 11/25/2017.
 */
@TeleOp(name = "conveyerSpeedVariation", group = "Mechanical")
public class conveyerSpeedVariation extends OpMode {
    public DcMotor rightConveyer;
    @Override
    public void init() {
        rightConveyer = hardwareMap.dcMotor.get("rightConveyer");
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            rightConveyer.setPower(0.8);
        }
        else if (gamepad1.x) {
            rightConveyer.setPower(0.6);
        }
        else if (gamepad1.y) {
            rightConveyer.setPower(0.4);
        }
        else  if (gamepad1.b) {
            rightConveyer.setPower(0.2);
        }
        else if (gamepad1.left_bumper) {
            rightConveyer.setPower(-0.5);
        }
        else if (gamepad1.right_bumper) {
            rightConveyer.setPower(0.5);
        }
        else {
            telemetry.addData("Press the A button for 80% power", "");
            telemetry.addData("Press the X button for 60% power", "");
            telemetry.addData("Press the Y button for 40% power", "");
            telemetry.addData("Press the B button for 20% power", "");
            telemetry.addData("Press the left bumper for reverse power", "");
            telemetry.addData("Press the right bumper for forward power", "");
        }
    }
}
