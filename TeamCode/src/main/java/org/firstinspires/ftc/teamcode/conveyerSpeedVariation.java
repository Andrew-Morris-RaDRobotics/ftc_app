package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by DeathChicken on 11/25/2017.
 */
@TeleOp(name = "conveyerSpeedVariation", group = "Mechanical")
public class conveyerSpeedVariation extends OpMode {
    private DcMotor topConveyer;
    private DcMotor bottomConveyer;
    @Override
    public void init() {
        topConveyer = hardwareMap.dcMotor.get("topConveyer");
        bottomConveyer = hardwareMap.dcMotor.get("bottomConveyer");
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            topConveyer.setPower(-0.8);
            bottomConveyer.setPower(-0.8);
        }
        else if (gamepad1.x) {
            topConveyer.setPower(-0.6);
            bottomConveyer.setPower(-0.6);
        }
        else if (gamepad1.y) {
            topConveyer.setPower(-0.4);
            bottomConveyer.setPower(-0.4);
        }
        else  if (gamepad1.b) {
            topConveyer.setPower(-0.2);
            bottomConveyer.setPower(-0.2);
        }
        else if (gamepad1.left_bumper) {
            topConveyer.setPower(1);
            bottomConveyer.setPower(1);
        }
        else if (gamepad1.right_bumper) {
            topConveyer.setPower(-1);
            bottomConveyer.setPower(-1);
        }
        else {
            topConveyer.setPower(0);
            bottomConveyer.setPower(0);
            telemetry.addData("Press the B button for 20% power", "");
            telemetry.addData("Press the Y button for 40% power", "");
            telemetry.addData("Press the X button for 60% power", "");
            telemetry.addData("Press the A button for 80% power", "");
            telemetry.addData("Press the left bumper for reverse power", "");
            telemetry.addData("Press the right bumper for forward power", "");
        }
    }
}
