package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Andrew on 12/23/2017.
 */

public class drive_at_angle_psudo {
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;


    public drive_at_angle_psudo(HardwareMap hardwareMap) {

            fr = hardwareMap.dcMotor.get("fr");
            fl = hardwareMap.dcMotor.get("fl");
            bl = hardwareMap.dcMotor.get("bl");
            br = hardwareMap.dcMotor.get("br");
        }
    public void angle (double angle1, double speed) {
        double cos = Math.cos(angle1);
        double sin = Math.sin(angle1);


        double frSpeed = (speed) * (-Math.sin(angle1) - Math.cos(angle1) - Math.cos(angle1));
        double flSpeed = (speed) * (+Math.sin(angle1) - Math.cos(angle1) - Math.cos(angle1));
        double brSpeed = (speed) * (-Math.sin(angle1) + Math.cos(angle1) - Math.cos(angle1));
        double blSpeed = (speed) * (+Math.sin(angle1) + Math.cos(angle1) - Math.cos(angle1));

        fr.setPower(frSpeed);
        fl.setPower(flSpeed);
        br.setPower(brSpeed);
        bl.setPower(blSpeed);

    }}






