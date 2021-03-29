package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

/**
 * This 2020-2021 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the Ultimate Goal game elements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "real auto tm", group = "Test")

public class angerynoisies extends LinearOpMode {

    int i = 0;

    private ElapsedTime runtime = new ElapsedTime();

    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "ATaU6gH/////AAABmQAPTavPq0n+tszcjRF2rU5egY5UskO4ohlkLp0vptcz+EXnxgL/VJ7JN7mY7IYCBGho/YDMkyV4cxRggzFVaXCN7HPBAW/mUi+AcYWdH66Vgu2gHf5UPzyERfU/XUzYsRpNCwXQTJ0VhogS7cLEQQl3A7m9Mn71ShZgHEkq8k/Bwoasf8OqBwqYTD4yJkocduEcL/ZtEKr5r6ZAE0SbRfKogp0O6I5CI2ktkNL48XoVCHeOrMh/olqzgk9Jbh7Hn+zyrPqWnYKy2t3RFewo/RU28s9nqEq0XOmUrT2Enqix+5i/cVdyuQvqkOEYL5LZdFH7Ibu7T5Uv9S0hTGpokof1lxp+a4QqRj3iPDQ/wrfg";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    BasicHardwareMap2021 robot = new BasicHardwareMap2021();

    @Override
    public void runOpMode() throws InterruptedException {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.


        robot.init(hardwareMap);
        initVuforia();
        initTfod();


        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 1.78 or 16/9).

            // Uncomment the following line if you want to adjust the magnification and/or the aspect ratio of the input images.
            //tfod.setZoom(2.5, 1.78);

        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            chomp();
            lift();
            moveForward(500, .5, 10000);
            dothingsplz();
            strafeLeft(500, .5, 10000);
            moveForward(1500, .5, 10000);
            strafeRight(750, .5, 10000);
            Dreinion(500);

            if(i == 1) {
                nonyong();
            } else if (i == 2){
                einyong();
            } else {
                vieryong();
            }

        }


        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.6f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    public void nonyong() throws InterruptedException{

        strafeRight(1000,.5, 10000);
        moveForward(1500, .5, 10000);
        dropit();
        moveForward(500, .5, 10000);
        strafeLeft(1000, .5, 100000);
        moveBackward(750, .5, 10000);

    }

    public void einyong() throws InterruptedException{

        moveForward(2500,.5,10000);
        dropit();
        moveForward(700, .5, 10000);
        strafeLeft(1000, .5, 10000);
        moveBackward(2000, .5, 10000);
    }

    public void vieryong() throws InterruptedException{

        strafeRight(1000, .5, 10000);
        moveForward(2250, .5, 10000);
        turnLeft(2250,.5,10000);
        dropit();
        moveForward(1250, .5, 10000);

    }



    public void dothingsplz() throws InterruptedException {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 0) {
                    // empty list.  no objects recognized.
                    i = 1;
                    telemetry.addData("TFOD", "No items detected.");
                    telemetry.addData("Target Zone", "A");
                } else {
                    // list is not empty.
                    // step through the list of recognitions and display boundary info.
                    for (Recognition recognition : updatedRecognitions) {

                        // check label to see which target zone to go after.
                        if (recognition.getLabel().equals("Single")) {
                            telemetry.addData("Target Zone", "B");
                            i = 2;
                        } else if (recognition.getLabel().equals("Quad")) {
                            telemetry.addData("Target Zone", "C");
                            i = 3;
                        } else {
                            telemetry.addData("Target Zone", "UNKNOWN");
                        }
                    }
                }

                telemetry.update();


            }

        }
    }

    public void moveBackward(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(tick);
        robot.motorBR.setTargetPosition(tick);
        robot.motorFL.setTargetPosition(tick);
        robot.motorBL.setTargetPosition(tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    public void moveForward(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(-tick);
        robot.motorBR.setTargetPosition(-tick);
        robot.motorFL.setTargetPosition(-tick);
        robot.motorBL.setTargetPosition(-tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    public void turnRight(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(-tick);
        robot.motorBR.setTargetPosition(-tick);
        robot.motorFL.setTargetPosition(tick);
        robot.motorBL.setTargetPosition(tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    public void turnLeft(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(tick);
        robot.motorBR.setTargetPosition(tick);
        robot.motorFL.setTargetPosition(-tick);
        robot.motorBL.setTargetPosition(-tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    public void strafeLeft(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(-tick);
        robot.motorBR.setTargetPosition(tick);
        robot.motorFL.setTargetPosition(tick);
        robot.motorBL.setTargetPosition(-tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    public void strafeRight(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(tick);
        robot.motorBR.setTargetPosition(-tick);
        robot.motorFL.setTargetPosition(-tick);
        robot.motorBL.setTargetPosition(tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }


    public void stopMotors() throws InterruptedException {
        robot.motorBR.setPower(0);
        robot.motorBL.setPower(0);
        robot.motorFR.setPower(0);
        robot.motorFL.setPower(0);
    }

    public void ShootOnion(int time) throws InterruptedException{
        robot.fastBoi.setPower(.85);
        sleep(time);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(0.5);
        sleep(800);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.9);
        sleep(time);
        robot.fastBoi.setPower(0);


    }

    public void Dreinion(int time) throws InterruptedException{
        robot.fastBoi.setPower(.8);
        sleep(time);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(.49);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.25);
        robot.hoppy.setPower(1);
        sleep(750);
        robot.hoppy.setPower(0);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(.49);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.25);
        sleep(1000);
        robot.hoppy.setPower(1);
        sleep(750);
        robot.hoppy.setPower(0);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(.49);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.25);
        sleep(1000);
        robot.fastBoi.setPower(0);
    }
    public void OnionEat(int time) throws InterruptedException{
        robot.hoppy.setPower(1);
        sleep(time);
    }

    public void OnionStopEating(int time) throws InterruptedException{
        robot.hoppy.setPower(0);
    }

    public void chomp() throws InterruptedException{

        robot.grabby.setDirection(Servo.Direction.REVERSE);
        robot.grabby.setPosition(.2);

    }

    public void lift() throws InterruptedException{

        robot.lifty.setPower(.5);
        sleep(500);
        robot.lifty.setPower(0);

    }

    public void dropit() throws InterruptedException{

        robot.grabby.setDirection(Servo.Direction.FORWARD);
        robot.grabby.setPosition(.7);
    }
}









