package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;


/*
Modern Robotics Digital In Example
Created 7/25/2017 by Colton Mehlhoff of Modern Robotics using FTC SDK 3.10
Modified 11/16/17 by PhantomWolf and DeathChicken of GreenGriffins10092
Reuse permitted with credit where credit is due

Configuration: Digital Device named "limit"
In this example, we are using a limit switch but you could use any digital input like a touch sensor.

Core Device Interface named "Device Interface Module 1"

Support is available by emailing support@modernroboticsinc.com
*/

@TeleOp(name = "LimitSwitchExample", group = "Testing")
//@Disabled
public class LimitSwitchExample extends OpMode {

    //A Digital Input.
    DigitalChannel MRLimitSwitch;
    int switchCount = 0;
    boolean switchState = false;
    //CDI. Using this, we can read any digital sensor on this CDI without creating an instance for each sensor.
    DeviceInterfaceModule cdi;

    @Override
    public void init() {


        telemetry.addData("Status", "Initialized");

        telemetry.update();

        //Link objects to configuration file
        MRLimitSwitch = hardwareMap.digitalChannel.get("LimitSwitch1");
        MRLimitSwitch.setMode(DigitalChannel.Mode.INPUT);
        //cdi = hardwareMap.deviceInterfaceModule.get("Expansion Hub 1");

    }

    public void loop() {
        //Read the limit switch using the instance of DigitalChannel.
        //Value will be true or false
        telemetry.addData("Switch State", MRLimitSwitch.getState());
        telemetry.addData("Count", switchCount);
        //Read each Digital Port of the CDI. 0-7
        //for (int i = 0; i < 8; i++) {
        //    telemetry.addData("Digital " + i, cdi.getDigitalChannelState(i));
        //}
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
        }
    }
}



