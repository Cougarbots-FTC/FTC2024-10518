package org.firstinspires.ftc.teamcode;
//Imports
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


//Clark Robotics 15024 Hardware Map
//Currently only have the motors from the robot powering the wheels
//frontRightChassis, backRightChassis, frontLeftChassis, backRightChassis
public class Clark10518HWMap {
    //Instances variables which makes the variables we can use or assign later
    // null - placeholder of a variable and basically has nothing in the variable currently
    public DcMotor frontRightChassis = null;
    public DcMotor frontLeftChassis = null;
    public DcMotor backRightChassis = null;
    public DcMotor backLeftChassis = null;

    public DcMotor leftLift = null;
    public DcMotor rightLift = null;

    public DcMotor armExtender = null;
    public DcMotor armRotator = null;

    public Servo claw = null;
    public Servo wristX = null;
    public Servo wristY = null;

    //public SparkFunOTOS odom = null;
    //public SparkFunOTOS.Pose2D pos = null;
    //public SensorSparkFunOTOS odom = null;
    //public Servo drop = null;



    HardwareMap hM = null;
    //Map function which we can use to assign values to the instance variables
    //HWM - HardwareMap parameter which we can use to pull values from which are mostly used to assign to values
    public void Map(HardwareMap HWM){
        //Assigns values to instance variables using the parameter which the .get functions to pull the information from the control hub
        hM = HWM;
        //The second parameter of the get function is used to obtain the specific motor from the config file in the robot
        backLeftChassis = HWM.get(DcMotor.class, "backLeftChassis");
        frontLeftChassis = HWM.get(DcMotor.class, "frontLeftChassis");
        frontRightChassis = HWM.get(DcMotor.class, "frontRightChassis");
        backRightChassis = HWM.get(DcMotor.class, "backRightChassis");

        leftLift = HWM.get(DcMotor.class, "leftLift");
        rightLift = HWM.get(DcMotor.class, "rightLift");
        armExtender = HWM.get(DcMotor.class, "armExtender");
        armRotator = HWM.get(DcMotor.class, "armRotator");

        /*linearMotionRight1 = HWM.get(DcMotor.class, "linearMotionRight1");
        intakeHD = HWM.get(DcMotor.class, "Intake");
        //odom = HWM.get(SensorSparkFunOTOS.class, "sensor_otos");
        odom = HWM.get(SparkFunOTOS.class, "sensor_otos");*/

        //Servos for delivery
        claw = HWM.get(Servo.class, "claw");
        wristX = HWM.get(Servo.class, "wristX");
        wristY = HWM.get(Servo.class, "wristY");

        //Using functions from the DcMotor class, this changes the direction of the motor, sets the power to 0, and makes the runmode to run without encoder as the robot is not moving
        //The direction of the some variables are different because they are needed to offset each other to move

        //setDirection function sets the direction of the wheels which it has to go. Some say forward and some say reverse because that is how you are also to move the robot back and forth
        backLeftChassis.setDirection(DcMotorSimple.Direction.REVERSE);
        //setZeroPowerBehavior function indicates to us that the power of the motor is at zero or not changing
        backLeftChassis.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //setMode with Running without encoder means that the motor is on by the encoders on the motors are not activated yet
        backLeftChassis.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftChassis.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftChassis.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftChassis.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backRightChassis.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightChassis.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightChassis.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontRightChassis.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightChassis.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightChassis.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftLift.setDirection(DcMotorSimple.Direction.FORWARD);
        leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightLift.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //TODO Change direction of motor according to the plans of hardware team
        armExtender.setDirection(DcMotorSimple.Direction.FORWARD);
        armExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armExtender.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armRotator.setDirection(DcMotorSimple.Direction.FORWARD);
        armRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        claw.setDirection(Servo.Direction.FORWARD);
        //claw.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wristX.setDirection(Servo.Direction.FORWARD);
        //wristX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wristY.setDirection(Servo.Direction.REVERSE);
        //wristY.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //pos = odom.getPosition();





    }

}