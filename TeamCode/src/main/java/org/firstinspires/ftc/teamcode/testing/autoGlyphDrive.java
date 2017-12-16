package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

/**
 * Created by Andrew on 12/16/2017.
 */
@Autonomous(name = "autoGlyphDrive", group = "testing")
public class autoGlyphDrive extends LinearOpMode {
    //motorDeclaration motors = new motorDeclaration(hardwareMap);
    int OpCount = 0;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor fr = hardwareMap.dcMotor.get("fr");
        DcMotor fl = hardwareMap.dcMotor.get("fl");
        DcMotor bl = hardwareMap.dcMotor.get("bl");
        DcMotor br = hardwareMap.dcMotor.get("br");

        waitForStart();
        telemetry.addData("OpCount =", OpCount);

        while (opModeIsActive()) {
            if (OpCount <= 2) {
                telemetry.addData("OpCount =", OpCount);
                sleep(1000);
                fl.setPower(-.5);
                fr.setPower(.5);

            } else if (OpCount == 3) {
                br.setPower(.5);
                fr.setPower(-.5);
            }
            else {
                telemetry.addData("count > 5", "");
                fl.setPower(0);
                fr.setPower(0);
            }
            OpCount++;
            telemetry.update();
        }
    }
}