package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

@TeleOp(name="ColinWillBeMad", group="Test")

public class ColinWillBeMad extends LinearOpMode {

    //Connect Hardware Maps
    BasicHardwareMap2021 robot = new BasicHardwareMap2021();

    private ElapsedTime runtime = new ElapsedTime();

    //Run LinearOpMode
    @Override
    public void runOpMode() {

        //Initiate Hardware Map
        robot.init(hardwareMap);


        //Add Telemetry to confirm Robot Start. Recommended to name her here
        double motorPower = 1;
        double strafePower = 1;


        // Wait for the game to start (driver presses PLAY)

        waitForStart();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()){

            //---------------------
            //------GAMEPAD 1------
            //---------------------

            //-------WHEELS--------
            double yLValue = gamepad1.left_stick_y;
            double yRValue = gamepad1.right_stick_y;
            double xLValue = gamepad1.left_stick_x;
            double xRValue = gamepad1.right_stick_x;


            boolean changed = false;

            if(gamepad1.b && !changed){
                if(motorPower == 1){
                    while (gamepad1.b) {
                        motorPower = .55;
                        strafePower = .65;
                        changed = true;
                        telemetry.addData("Current Speed:", " slow");
                        telemetry.addData("Motor Power", motorPower);
                        telemetry.update();
                    }
                }
                else if (motorPower == .55){
                    while (gamepad1.b) {
                        motorPower = 1;
                        strafePower = 1;
                        changed = true;
                        telemetry.addData("Current Speed:", " fast");
                        telemetry.addData("Motor Power", motorPower);
                        telemetry.update();
                    }
                }
            }
            else {
                changed = true;
            }

            double radL = Math.sqrt(Math.pow(xLValue,2)+Math.pow(yLValue,2));

            double radR = Math.sqrt(Math.pow(xRValue,2)+Math.pow(yRValue,2));




            //Movement
            yLValue = Range.clip(yLValue, -1, 1);
            yRValue = Range.clip(yRValue, -1, 1);
            xLValue = Range.clip(xLValue, -1, 1);
            xRValue = Range.clip(xRValue, -1, 1);

            if (yLValue > 0 && radL > .1) {
                robot.motorFL.setPower(motorPower);
                robot.motorBL.setPower(motorPower);
            }   if (yLValue < 0 && radL > .1) {
                robot.motorFL.setPower(-motorPower);
                robot.motorBL.setPower(-motorPower);
            }   if (yRValue > 0 && radR > .1){
                robot.motorFR.setPower(motorPower);
                robot.motorBR.setPower(motorPower);
            }   if (yRValue < 0 && radR > .1){
                robot.motorFR.setPower(-motorPower);
                robot.motorBR.setPower(-motorPower);
            }


            if (gamepad1.dpad_down &&gamepad1.dpad_left){

                robot.motorFL.setPower(strafePower/1.5);
                robot.motorBR.setPower(strafePower/1.5);
                robot.motorBL.setPower(0);
                robot.motorFR.setPower(0);

            }
            else if(gamepad1.dpad_up &&gamepad1.dpad_right){

                robot.motorFL.setPower(-strafePower/1.8);
                robot.motorBR.setPower(-strafePower/1.8);
                robot.motorBL.setPower(0);
                robot.motorFR.setPower(0);

            }else if (gamepad1.dpad_down &&gamepad1.dpad_right){

                robot.motorFR.setPower(strafePower/1.5);
                robot.motorBL.setPower(strafePower/1.5);
                robot.motorFL.setPower(0);
                robot.motorBR.setPower(0);

            }else if (gamepad1.dpad_up &&gamepad1.dpad_left){

                robot.motorFR.setPower(-strafePower/1.5);
                robot.motorBL.setPower(-strafePower/1.5);
                robot.motorFL.setPower(0);
                robot.motorBR.setPower(0);

            }else if (gamepad1.dpad_left){

                robot.motorFL.setPower(strafePower/1.8);
                robot.motorBR.setPower(strafePower/1.8);
                robot.motorBL.setPower(-strafePower/1.8);
                robot.motorFR.setPower(-strafePower/1.8);

            }else if (gamepad1.dpad_right){

                robot.motorFL.setPower(-strafePower/1.8);
                robot.motorBR.setPower(-strafePower/1.8);
                robot.motorBL.setPower(strafePower/1.8);
                robot.motorFR.setPower(strafePower/1.8);

            }else if (gamepad1.dpad_up){

                robot.motorFL.setPower(-motorPower/2.5);
                robot.motorBR.setPower(-motorPower/2.5);
                robot.motorBL.setPower(-motorPower/2.5);
                robot.motorFR.setPower(-motorPower/2.5);

            }else if (gamepad1.dpad_down){

                robot.motorFL.setPower(motorPower/2.5);
                robot.motorBR.setPower(motorPower/2.5);
                robot.motorBL.setPower(motorPower/2.5);
                robot.motorFR.setPower(motorPower/2.5);

            } else {
                robot.motorFL.setPower(0);
                robot.motorBL.setPower(0);
                robot.motorBR.setPower(0);
                robot.motorFR.setPower(0);
            }


        }

    }
}

