package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by DeathChicken on 11/24/2017.
 */

public class motorDeclaration {

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;

    public motorDeclaration(HardwareMap hardwareMap) {

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");



    }

//    public void setP(double powerF, double powerS){
//        fr.setPower(-powerF + powerS);
//        fl.setPower(powerF+powerS);
//        br.setPower(-powerF -powerS);
//        bl.setPower(powerF-powerS);
//    }
    public void setP(double powerF, double powerS, double turn){
        fr.setPower(-(-powerF + powerS+ turn));
        fl.setPower(-(powerF+powerS+turn));
        br.setPower(-(-powerF -powerS+turn));
        bl.setPower(-(powerF-powerS+turn));
    }




}
