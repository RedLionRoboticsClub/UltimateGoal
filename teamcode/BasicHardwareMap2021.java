//import all of your ftc specific files so that the robot phone and revhub or whatever ur using now knows what to do
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//make a class or something so that the code works, I wish I could explain better but I actually have never taken a coding class so i have no idea what this means

public class BasicHardwareMap2021 {

    //define each motor and servo, making sure you use the right label (i.e. CRServo for a continuous rotation servo, Servo for normal Servo) 
    //ALSO MAKE SURE YOU DEFINE STUFF IN THE ROBOT CONTROLLER PHONE THE SAME OR ELSE IT WONT WORK
    public DcMotor motorFL     = null;
    public DcMotor motorFR     = null;
    public DcMotor motorBL     = null;
    public DcMotor motorBR     = null;
    public CRServo fastBoi     = null;
    public CRServo hoppy       = null;
    public Servo   finger      = null;
    public CRServo lifty       = null;
    public Servo grabby      = null;


    // Local OpMode members (this was already here I dont know what it means)
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    // Constructor (when you write your own hw map you'll put it here)
    public BasicHardwareMap2021() {
    }

    //Initialize standard Hardware interfaces (start the boi)
    public void init(HardwareMap ahwMap) {

        // save reference to HW Map (dont know why this is here but put it probably)
        hwMap = ahwMap;

        // Define and Initialize Motors/Servos (once again, make sure the names are right in the phone)
        motorFL = hwMap.get(DcMotor.class, "motorFL");
        motorFR = hwMap.get(DcMotor.class, "motorFR");
        motorBL = hwMap.get(DcMotor.class, "motorBL");
        motorBR = hwMap.get(DcMotor.class, "motorBR");

        hoppy   = hwMap.get(CRServo.class, "hoppy");
        fastBoi = hwMap.get(CRServo.class, "fastBoi");
        lifty   = hwMap.get(CRServo.class, "lifty");
        grabby  = hwMap.get(Servo.class, "grabby");

        finger  = hwMap.get(Servo.class, "finger");

        // Set all motors to zero power (yeah that)

        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
        fastBoi.setPower(0);
        hoppy.setPower(0);
        lifty.setPower(0);



        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        // this has been a point of contention for a while, im not really sure if this matters outside of if ur code doesnt work and you dont know why just flip from
        // encoder to no encoder or vice versa

        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fastBoi.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        // Define and initialize ALL installed servos (this is straightforward I think)

        finger.scaleRange(0,1);
        grabby.scaleRange(0,1);


    }
}

// congratulations, you now have seen robot code!! probably bother mr barrett to teach you how to run android studio and if he doesnt know then I can try to help. 
// but for those of you who have no coding experience, i did not when i started either so dont worry too much about learning all the formatting and syntax because
// I have no idea what it means either. Basically as long as you know what to replace in the code that is robot specific to this years game, you'll be fiiiine
// the real work comes when we get to auto but it will be okay because you wont need that for a while probably and when you will need it i will help you learn
