package org.firstinspires.ftc.teamcode;
//Imports
//import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.ArrayList;

/// @version 3
/// changes for the third match
@TeleOp(name = "Clark 10518 TeleOp 3.0")


public class Clark10518TeleOpv1 extends LinearOpMode {

    //Initialized Hardware map instance variable assigned to "robot"
    Clark10518HWMap robot = new Clark10518HWMap();

    // -------------Create all the variables necessary for Robot movement-------------//

    // For toggle button
    ArrayList<Boolean> booleanArray = new ArrayList<Boolean>();
    int booleanIncrementer = 0;

    //Local boolean variables for one-button-two-functions operations
    Boolean gamepad1AState = false;
    Boolean gamepad1BState = false;
    Boolean gamepad1XState = false;
    Boolean gamepad1YState = false;

    // The values for the claw
    double clawOpenPos = 0.0;
    double clawClosePos = 1;
    double currentClawPos = 0.0;

    // The values for the WristY
    double clawWristYUp = 0.0;
    double clawWristYDown = 1.0;
    double currentWristYPos = 0.0;

    // The values for the WristX
    double clawWristXVertical = 1.0;
    double clawWristXHorizontal = 0.5;
    double currentWristXPos = 1.0;



    @Override
    public void runOpMode(){
        //Initiates the Map function, assigning items to instance variables in the hardware map
        robot.Map(hardwareMap);
        // Initialization of hardware
        easterEggs();
        innitHarware();

        //Waits for the button to start on the driver hub to be pressed
        waitForStart();

        //while loop starts once the start button is pressed
        while(opModeIsActive()) {

            // moves the Chassis according to inout values
            moveChassis();

            // moves the Lifts according to the pressure on the trigger
            moveLifts();

            // Extends the arm (DPAD-left,right)
            extendArm();

            // Rotates the arm (DPAD-up,down)
            rotateArm();

            // moves the entire "hand" of the robot
            moveHand();

            // sets the boolean incrementer (required for the claw toggle functionality) to 0
            booleanIncrementer = 0;



        }
    }
    public void moveChassis(){
        //Slow mode whenever you need to go slower to get precise blocks
        //TODO change controller input if needed
        double chassisPower = gamepad1.right_bumper ? 0.5 : 1.0;

        //Instances variables assigned to double or decimal values to the different gamepad
        //Set the vertical as a negative because of the different values needed for the right side as they are in reverse
        //TODO change negative signs of all variables if robot not moving or working as the values for the robot could need to be switched
        double vertical = gamepad1.left_stick_y;
        double horizontal = gamepad1.left_stick_x * 1.1; //  Multiply by 1.1 to negate imperfect strafing
        double pivot = gamepad1.right_stick_x;

        //Obtains values for each motor through the positions through values
        //from  left joystick which has up/down(vertical) and left/right values(horizontal), and right joystick which has left/right values(pivot)
        double denominator = Math.max(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(pivot), 1);
        double rightFrontPower = (horizontal - vertical + pivot) / denominator;
        double rightBackPower = (horizontal + vertical + pivot) / denominator;
        double LeftFrontPower = (horizontal + vertical - pivot) / denominator;
        double LeftBackPower = (horizontal - vertical - pivot) / denominator;

        //Sets Power to the motors and changed the signed of the math in order to proportion the wheels right to move
        robot.frontRightChassis.setPower(rightFrontPower * chassisPower);
        robot.backRightChassis.setPower(rightBackPower * chassisPower);
        robot.backLeftChassis.setPower(LeftBackPower * chassisPower);
        robot.frontLeftChassis.setPower(LeftFrontPower * chassisPower);
    }

    public void moveLifts(){
        //left_trigger - leftLift and rightLift up
        //right_trigger - leftLift and rightLift down
        float liftPowerUp = gamepad1.left_trigger;
        float liftPowerDown = gamepad1.right_trigger;
        if (gamepad1.left_trigger > 0.1) {
            robot.leftLift.setPower(liftPowerUp);
            robot.rightLift.setPower(liftPowerUp);
        } else if (gamepad1.right_trigger > 0.1) {
            robot.leftLift.setPower(-1 * liftPowerDown);
            robot.rightLift.setPower(-1 * liftPowerDown);
        } else {
            robot.leftLift.setPower(0);
            robot.rightLift.setPower(0);
        }

    }

    public void easterEggs(){
        //Launch easter eggs
        telemetry.addData("Say", "Starting 10518 TeleOp 2.0");
        telemetry.addData("Say", "Initializing 10518 TeleOp 2.0");
        telemetry.addData("Say", "Locking hardware people out of the code...");
        telemetry.addData("Say", "Eating s'mores...");
        telemetry.addData("Say", "Annihilating positrons...");
        telemetry.addData("Say", "Downloading RAM...");
        telemetry.addData("Say", "Done!");
        telemetry.update();
    }

    public void  initLifts(){
        robot.leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void initRotator(){
        robot.armRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void initExtender(){
        robot.armExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void innitHarware(){
        initExtender();
        initRotator();
        initLifts();
    }

    public void extendArm(){
        //armExtender on DPad up and down
        double armExtenderPower = gamepad1.right_bumper ? 0.5 : 1;
        if (gamepad1.dpad_down) {
            robot.armExtender.setPower(armExtenderPower);
        } else if (gamepad1.dpad_up) {
            robot.armExtender.setPower(-1 * armExtenderPower);
        } else {
            robot.armExtender.setPower(0);
        }
    }

    public void rotateArm(){
        if (gamepad1.dpad_left) {
            robot.armRotator.setPower(-1);

        } else if (gamepad1.dpad_right) {
            //down
            robot.armRotator.setPower(1);
        } else {
            robot.armRotator.setPower(0.2);
        }
    }


    public void moveHand(){
        moveClaw();
        moveWristX();
        moveWristY();
    }
    public void moveClaw(){
        // initializing some variables and checking if pressed
        boolean G1b = gamepad1.b;
        boolean G1bPressed = ifPressed(G1b);

        // if pressed switches toggle
        if(G1bPressed && currentClawPos == clawClosePos){
            currentClawPos = clawOpenPos;
        }
        else if(G1bPressed && currentClawPos == clawOpenPos){
            currentClawPos = clawClosePos;
        }
        // setting the servo position to whatever current position is
        robot.claw.setPosition(currentClawPos);
    }


    public void moveWristX() {
        // initializing some variables and checking if pressed
        boolean G1x = gamepad1.x;
        boolean G1xPressed = ifPressed(G1x);

        // if pressed switches toggle
        if(G1xPressed && currentWristXPos == clawWristXHorizontal){
            currentWristXPos = clawWristXVertical;
        }
        else if(G1xPressed && currentWristXPos == clawWristXVertical){
            currentWristXPos = clawWristXHorizontal;
        }
        // setting the servo position to whatever current position is
        robot.wristX.setPosition(currentWristXPos);

    }

    public void moveWristY() {
        // initializing some variables and checking if pressed
        boolean G1y = gamepad1.y;
        boolean G1yPressed = ifPressed(G1y);

        // if pressed switches toggle
        if(G1yPressed && currentWristYPos == clawWristYDown){
            currentWristYPos = clawWristYUp;
        }
        else if(G1yPressed && currentWristYPos == clawWristYUp){
            currentWristYPos = clawWristYDown;
        }
        // setting the servo position to whatever current position is
        robot.wristY.setPosition(currentWristYPos);




    }

    public boolean ifPressed(boolean button){
        boolean output = false;
        if(booleanArray.size() == booleanIncrementer){
            booleanArray.add(false);
        }
        boolean buttonWas = booleanArray.get(booleanIncrementer);
        if(button != buttonWas && button == true){
            output = true;
        }

        booleanArray.set(booleanIncrementer,button);

        booleanIncrementer = booleanIncrementer + 1;
        return output;
    }
}