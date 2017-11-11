package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "LimitSwitchTest", group = "testing")

public class LimitSwitchTest extends OpMode {


    DigitalChannel LimitSwitch;

    @Override
    public void init() {
        LimitSwitch = (DigitalChannel) hardwareMap.get("LimitSwitch1");
        telemetry.addData("", LimitSwitch.getState());
    }

    @Override
    public void loop() {
        telemetry.update();

    }
}
