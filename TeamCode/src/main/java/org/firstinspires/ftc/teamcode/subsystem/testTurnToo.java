package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.turnTo;

@Autonomous (name= "testTurnToo", group= "testing")

public class testTurnToo extends LinearOpMode {

    public DcMotor fr;
    public DcMotor fl;
    public DcMotor bl;
    public DcMotor br;
    public drive_at_angle_psudo testGyro;
    public turnTo turn;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        testGyro = new drive_at_angle_psudo(hardwareMap);


        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        while (opModeIsActive()) {
       testGyro.angle(10.0,.3);

        }

    }
}