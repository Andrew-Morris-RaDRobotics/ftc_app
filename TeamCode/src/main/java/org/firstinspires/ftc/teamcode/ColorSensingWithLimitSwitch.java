package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by DeathChicken on 11/18/2017.
 */

@TeleOp(name = "ColorLimitSensing", group = "Testing")

public class ColorSensingWithLimitSwitch extends OpMode {

    DigitalChannel MRLimitSwitch;
    int switchCount = 0;
    boolean switchState = false;

    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        MRLimitSwitch = hardwareMap.digitalChannel.get("LimitSwitch1");
        MRLimitSwitch.setMode(DigitalChannel.Mode.INPUT);
    }

    public void loop() {

        telemetry.addData("Switch State", MRLimitSwitch.getState());
        telemetry.addData("Count", switchCount);

        telemetry.update();
        if (MRLimitSwitch.getState())
        {
            switchState = true;

        }
        else {
            if (switchState){
                switchCount++;
            }
            switchState=false;
        if (switchState) {
            //color code here
        }
        }
    }
}

