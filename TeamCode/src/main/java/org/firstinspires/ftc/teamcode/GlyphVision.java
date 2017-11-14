package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.ClassFactory;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by stealthwhale on 11/13/2017.
 */

public class GlyphVision {
    private static final String LICENSE_KEY = "AVkCYrr/////AAAAGTRp5SBV1U/KkkGEuhLyoQx9NgIdVEvNCrVlFCGRMMJW9KCAhjIPmRVgTf94ROoc0oQ8rZHHultjPEsB65/JEbfsHiygNqQmp0A5BartN69YNuLO6sTXbpusJprKw3jYerTorJlyG+K5ZE918dkXxAAIuGSMbstujn9JIzzobjZbX76TCKl923Mqaeq4Gb/07Xi+0gg8l2+uC2L+4Nroywh4Lw/v0Al8GmRQk+arqmd+OYPfSYpGvkQ1QHauMrjUuEiBRTt7EI17Y2T9gmShWof0Eo6nMT8tx9LPLjvioc7NV7Q9MUP4jSwqjMDa5IZpVyynvYVvD7QK3cLnfrxCgaDp5FBaTeM1jJfuoyY2hpYs";

    private final HardwareMap hardwareMap;
    private final int cameraMonitorViewId;
    private final VuforiaLocalizer.Parameters parameters;
    private final VuforiaLocalizer vuforia;
    private final VuforiaTrackables relicTrackables;
    private final VuforiaTrackable relicTemplate;

    public GlyphVision(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = LICENSE_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();

    }
    public RelicRecoveryVuMark getGlyph(){
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

        return vuMark;
    }

    public String getPos(){
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
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
        return (pose != null) ? pose.formatAsTransform() : "null";
    }
}
