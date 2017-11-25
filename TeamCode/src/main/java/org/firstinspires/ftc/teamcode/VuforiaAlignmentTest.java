package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "VuforiaAlignment", group = "Testing")

public class VuforiaAlignmentTest extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;

    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AVkCYrr/////AAAAGTRp5SBV1U/KkkGEuhLyoQx9NgIdVEvNCrVlFCGRMMJW9KCAhjIPmRVgTf94ROoc0oQ8rZHHultjPEsB65/JEbfsHiygNqQmp0A5BartN69YNuLO6sTXbpusJprKw3jYerTorJlyG+K5ZE918dkXxAAIuGSMbstujn9JIzzobjZbX76TCKl923Mqaeq4Gb/07Xi+0gg8l2+uC2L+4Nroywh4Lw/v0Al8GmRQk+arqmd+OYPfSYpGvkQ1QHauMrjUuEiBRTt7EI17Y2T9gmShWof0Eo6nMT8tx9LPLjvioc7NV7Q9MUP4jSwqjMDa5IZpVyynvYVvD7QK3cLnfrxCgaDp5FBaTeM1jJfuoyY2hpYs";


        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("VuMark", "%s visible", vuMark);

                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                }
                VectorF trans = pose.getTranslation();
                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
                double tX = trans.get(0);
                double tY = trans.get(1);
                double rX = rot.firstAngle;

//                double targetX = 0.0;
//                double targetY = 20.0;
//                double targetRotation = 0;
//                double deadzone = 10;
//                double errorX = targetX - tX;
//                double errorY = targetY - tY;
//                double rotationError = targetRotation - rX;

                if (tX > 10) {
                    fr.setPower(fr.getPower() - 0.5);
                    br.setPower(br.getPower() + 0.5);
                } else if (tX < -10) {
                    fr.setPower(fr.getPower() + 0.5);
                    br.setPower(br.getPower() - 0.5);
                } else {
                    fr.setPower(fr.getPower());
                    br.setPower(br.getPower());
                }

                if (tY <= 10) {
                    fr.setPower(fr.getPower() - 0.5);
                    fl.setPower(fl.getPower() - 0.5);
                } else if (tY >= 30) {
                    fr.setPower(fr.getPower() + 0.5);
                    fl.setPower(fr.getPower() + 0.5);
                } else {
                    fr.setPower(fr.getPower());
                    fl.setPower(fl.getPower());
                }

                if (rX > 10) {
                    fr.setPower(fr.getPower() - 0.5);
                    fl.setPower(fl.getPower() - 0.5);
                } else if (rX < -10) {
                    fr.setPower(fr.getPower() + 0.5);
                    fl.setPower(fl.getPower() + 0.5);
                } else {
                    fr.setPower(fr.getPower());
                    fl.setPower(fl.getPower());
                }
            } else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
