package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BasicHardwareMap2021 {

    //Define Motors
    public DcMotor motorFL     = null;
    public DcMotor motorFR     = null;
    public DcMotor motorBL     = null;
    public DcMotor motorBR     = null;
    public CRServo fastBoi     = null;
    public CRServo hoppy       = null;
    public Servo   finger      = null;
    public CRServo lifty       = null;
    public Servo grabby      = null;

    //Define Servos

    // Local OpMode members
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    // Constructor
    public BasicHardwareMap2021() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {

        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors/Servos
        motorFL = hwMap.get(DcMotor.class, "motorFL");
        motorFR = hwMap.get(DcMotor.class, "motorFR");
        motorBL = hwMap.get(DcMotor.class, "motorBL");
        motorBR = hwMap.get(DcMotor.class, "motorBR");

        hoppy   = hwMap.get(CRServo.class, "hoppy");
        fastBoi = hwMap.get(CRServo.class, "fastBoi");
        lifty   = hwMap.get(CRServo.class, "lifty");
        grabby  = hwMap.get(Servo.class, "grabby");

        finger  = hwMap.get(Servo.class, "finger");

        // Set all motors to zero power

        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
        fastBoi.setPower(0);
        hoppy.setPower(0);
        lifty.setPower(0);



        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.

        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fastBoi.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        // Define and initialize ALL installed servos

        finger.scaleRange(0,1);
        grabby.scaleRange(0,1);


    }
}
