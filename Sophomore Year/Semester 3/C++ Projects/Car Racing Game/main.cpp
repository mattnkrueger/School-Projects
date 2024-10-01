//
// Programmer: Fam Trinli
// Fam Trinli's Source Code from: https://www.youtube.com/watch?v=YzhhVHb0WVY
// Description: This program is a car racing game
//
// ------- HW #5 -------
// Modified by: Matthew Krueger
// Date: 10/27/23
// ---------------------

#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>


#include <cmath>
#include <iostream>
#include <string>

using namespace sf;

const int num = 8;
int points[num][2] = {
        300, 610,    // coordinates for turn 1 checkpoint (x, y)
        1270, 430,   // coordinates for turn 2 checkpoint (x, y)
        1380, 2380,  // coordinates for turn 3 checkpoint (x, y)
        1900, 2460,  // coordinates for turn 4 checkpoint (x, y)
        1970, 1700,  // coordinates for turn 5 checkpoint (x, y)
        2550, 1680,  // coordinates for turn 6 checkpoint (x, y)
        2560, 3150,  // coordinates for turn 7 checkpoint (x, y)
        500, 3300    // coordinates for turn 8 checkpoint (x, y)
};

//CAR CLASS
class Car {
public:
    explicit Car(); //constructor
    void move(); //move
    void findTarget(); //findTarget
    void findTarget(int cNum); //findTarget for car[0]

    //setter & getter functions
    void setX(float xVal);
    float getX() const;

    void setY(float yVal);
    float getY() const;

    void setSpeed(float speedVal);
    float getSpeed() const;

    void setAngle(float angleVal);
    float getAngle() const;

    void setCheckpoint(int checkpointVal);
    int getCheckpoint() const;

    void setBackgroundScaleFactor(float s);
    void setBackgroundHeight(float h);
    void setBackgroundWidth(float w);

    float getBackgroundScaleFactor();
    float getBackgroundHeight();
    float getBackgroundWidth();

    int currTarget;

private:
    float x;
    float y;
    float speed;
    float angle;
    int checkpointNum;

    float backgroundScaleFactor;
    float backgroundImgHeight = 1440;
    float backgroundImgWidth = 1824;
};

///function: Car (constructor)
///purpose: initializes the Car member variables if nothing is passed... default constructor for the Car class
///input: none - accesses variables
///output: none - no return type. Only sets speed, angle, and current point of the car
Car::Car() {
    speed = 2.5;  // initial speed of the car (in units per frame)
    angle = 0; // initial angle of the car, pointing upwards (in radians)
    checkpointNum = 0; // index of the car's current checkpoint (starts at 0)
    x = 0; //initial x location
    y = 0; //initial y location
    backgroundScaleFactor = 2;

    //initialize using defaulted values when constructed
    setBackgroundHeight(backgroundScaleFactor);
    setBackgroundWidth(backgroundScaleFactor);
}

///function: move
///purpose: calculate the car x and y (moving the car). Uses member variables of speed and angle to move car horizontally and vertically on background sprite
///input: none - accesses variables
///output: none - only alters variables
void Car::move() {
    x += sin(angle) * speed; // move the car horizontally based on the current angle and speed
    y -= cos(angle) * speed; // move the car vertically based on the current angle and speed
}

///function: findTarget
///purpose: Locate the (x,y) position of the next target and change the angle difference between the car and point to ensure car is on course. While loop will continue to run, recalculating beta to point car towards checkpoint
///input: none - accesses variables
///output: none; void return type. Only updates the angle of the NPC cars to and checkpoint car is approaching
void Car::findTarget() {
    float nextCheckpointX = points[checkpointNum][0]; // x coordinate of the next checkpoint
    float nextCheckpointY = points[checkpointNum][1]; // y coordinate of the next checkpoint
    float angleDifference = angle - atan2(nextCheckpointX - x, -nextCheckpointY + y); // angle difference between car and checkpoint
    if (sin(angleDifference) < 0) { angle += 0.005 * speed; } else { angle -= 0.005 * speed; } // adjust angle if the car is off course
    if ((x - nextCheckpointX) * (x - nextCheckpointX) + (y - nextCheckpointY) * (y - nextCheckpointY) < 25 * 25) { checkpointNum = (checkpointNum + 1) % num; } // move to the next checkpoint if the car is close enough
}

///function: findTarget
///purpose: Locate the (x,y) position of the next target and change the angle difference between the car and point to ensure car is on course. While loop will continue to run, recalculating beta to point car towards checkpoint
///input: none - accesses variables
///output: none; void return type. Only updates the angle of the NPC cars to and checkpoint car is approaching
void Car::findTarget(int cNum) {
    float nextCheckpointX = points[cNum][0]; // x coordinate of the next checkpoint
    float nextCheckpointY = points[cNum][1]; // y coordinate of the next checkpoint
    if ((x - nextCheckpointX) * (x - nextCheckpointX) + (y - nextCheckpointY) * (y - nextCheckpointY) < 25 * 40) { checkpointNum = (checkpointNum + 1) % num; } // move to the next checkpoint if the car is close enough
}

///Setter and Getter Functions
///For each, there is a valid value check to ensure that if an invalid value is passed, it will default to a valid value.

///X
void Car::setX(float xVal) {
    if (xVal > 0 && xVal < backgroundImgWidth) {
        x = xVal;
    } else if (xVal < 0) {
        x = 0;
    } else {
        x = backgroundImgWidth;
    }
}

float Car::getX() const {return x;}

///Y
void Car::setY(float yVal) {
    if (yVal > 0 && yVal < backgroundImgHeight) {
        y = yVal;
    } else if (yVal < 0) {
        y = 0;
    } else {
        y = backgroundImgHeight;
    }
}

float Car::getY() const {return y;}

///Speed
void Car::setSpeed(float speedVal) {
    if (speedVal >= 0) {
        speed = speedVal;
    } else {
        speed = 0; //if value is negative set to 0
    }
}

float Car::getSpeed() const {return speed;}

///Angle
void Car::setAngle(float angleVal) {
    // Ensure the angle is within the range [0, 2*pi]
    while (angleVal < 0) {
        angleVal += 2 * M_PI;
    }
    while (angleVal >= 2 * M_PI) {
        angleVal -= 2 * M_PI;
    }
    angle = angleVal;
}

float Car::getAngle() const {return angle;}

///Checkpoint
void Car::setCheckpoint(int checkpointVal)  {
    if (checkpointVal >= 0 && checkpointVal < num) {
        checkpointNum = checkpointVal;
    } else {
        checkpointNum = 0; //if not in range of checkpoints, set to 0
    }
}

int Car::getCheckpoint() const {return checkpointNum;}

///Setters and getters added for hw5 - to set and get background height and width

void Car::setBackgroundScaleFactor(float s) {
    if (s < 0) {
        backgroundScaleFactor = 2; //default
    }
    else {
        backgroundScaleFactor = s;
    }
}

void Car::setBackgroundHeight(float h) {
    if (h < 0) {
        backgroundImgHeight = 1000 * backgroundScaleFactor;
    }
    else{
        backgroundImgHeight = backgroundScaleFactor*h;
    }
}

void Car::setBackgroundWidth(float w) {
    if (w < 0) {
        backgroundImgWidth = 1000 * backgroundScaleFactor;
    } else {
        backgroundImgWidth = backgroundScaleFactor * w;
    }
}

float Car::getBackgroundScaleFactor() {
    return backgroundScaleFactor;
}

float Car::getBackgroundHeight() {
    return backgroundImgHeight;
}

float Car::getBackgroundWidth() {
    return backgroundImgWidth;
}

int main() {
    //INITIALIZE RACE STATUS
    bool raceCompleted = true;

    //BEST RUN
    float bestTime = 99999;

    //INITIALIZE SCREEN VARIABLES
    const int screenWidth = 640;
    const int screenHeight = 480;
    const int backgroundScale = 2;

    //CREATE WINDOW
    RenderWindow app(VideoMode(screenWidth, screenHeight), "mnkrueger - Car Racing Game!");

    //SET FRAME LIMIT
    app.setFramerateLimit(60);

    //CREATE TEXTURES
    Texture backgroundTexture, carTexture;

    //SET BACKGROUND TEXTURE AND CHECK IF LOADED
    backgroundTexture.loadFromFile("images/background.png");
    if (!backgroundTexture.loadFromFile("images/background.png")) { return EXIT_FAILURE;}

    //SET CAR TEXTURE AND CHECK IF LOADED
    carTexture.loadFromFile("images/car.png"); // car texture
    if (!carTexture.loadFromFile("images/car.png")) { return EXIT_FAILURE; }

    //SET TEXTURES TO SMOOTH
    backgroundTexture.setSmooth(true);
    carTexture.setSmooth(true);

    //CREATE SPRITES FOR BACKGROUND AND CAR
    Sprite sBackground(backgroundTexture);
    Sprite sCar(carTexture);

    //SET BACKGROUND SCALE
    sBackground.scale(backgroundScale, backgroundScale);

    //SET CAR ORIGIN
    sCar.setOrigin(22, 22);

    //SET CAR RADIUS (this acts like a circle)
    float carRadius= 22;

    //SET CARS
    const int NumberOfCars = 5;
    Car car[NumberOfCars];

    //SET STARTING POINT ON TRACK
    for (int i = 0; i < NumberOfCars; i++) {
        car[i].setBackgroundWidth(1440);
        car[i].setBackgroundHeight(1824);
        car[i].setX(300 + i * 50);
        car[i].setY(1700 + i * 80);
        car[i].setSpeed(7 + i);
    }

    //SET USER CAR INITIAL MOVEMENT VALUES
    float speed = 0;
    float angle = 0;
    float maxSpeed = 5.2;
    float acc = 0.2;
    float dec = 0.3;
    float turnSpeed = 0.08;

    //SET OFFSET
    int offsetX = 0;
    int offsetY = 0;

    //CHECKPOINTS
    sf::CircleShape checkpointMarker(50.f);
    checkpointMarker.setOutlineThickness(5.f);
    checkpointMarker.setFillColor(sf::Color::Green);

    //STARTING CHECKPOINT
    int userCheckpoint = 0;

    //INST SCREEN
    bool instScreen = false;

    //MUSIC
    ///Music attribution:
    ///Music by: bensound.com (Benjamin Tissot) Chill Out Copyright Free Music music track with a laid-back mood. Great for videos about travel, vlogs, YouTube videos, advertising, and much more. Features synthesizers, snaps, drums, electric bass, piano, and voice samples.
    ///License code: RF9C9CBAHYAWVGNK
    sf::Music backgroundMusic;
    if (!backgroundMusic.openFromFile("audio/dreams.ogg")) { // audio file - Dreams by Benjamin Tissot
        std::cout << "Failed to load background music" << std::endl; //error msg
        return EXIT_FAILURE;
    }

    //MUSIC ATTRIBUTES
    backgroundMusic.setPitch(1.2f);
    backgroundMusic.setVolume(50.f);
    backgroundMusic.play();
    backgroundMusic.setLoop(true);

    //TIMER
    float timer=0;
    float elapsed = 0;
    Clock clock;

    //TIME FONT
    sf::Font font;
    if (!font.loadFromFile("fonts/8-bit-operator/8bitOperatorPlus-Bold.ttf")) { ///Attribution: 8 Bit Operator - Grandoplex Productions (https://www.1001freefonts.com/8-bit-operator.font)
        std::cerr << "Failed to load font." << std::endl;
        return EXIT_FAILURE;
    }

    //END SCREEN
    bool finished = false;
    float finishedTime = 0;

    //END SCREEN TEXT
    sf::Text endText;
    endText.setFont(font);
    endText.setCharacterSize(20);
    endText.setFillColor(sf::Color::White);
    endText.setStyle(sf::Text::Bold);

    //Instructions
    //THIS WAS MY ADDITION TO THE GAME
    sf::Text howToInstructions;
    howToInstructions.setFont(font);
    howToInstructions.setCharacterSize(15);
    howToInstructions.setFillColor(sf::Color::White);
    howToInstructions.setStyle(sf::Text::Regular);
    std::string howToInst = "To Play: \nUse the arrow dpad keys on computer to move car.\nup arrow = up\nleft arrow = left\ndown arrow = down\nright arrow = right. \nPress R to restart";
    howToInstructions.setString(howToInst);


    //GAME LOOP
    while (app.isOpen()) {

        //////////////
        /// EVENTS ///
        //////////////

        //CREATE EVENT
        Event e;
        while (app.pollEvent(e)) {
            if (e.type == Event::Closed) { app.close(); }
            if (e.type == Event::KeyPressed) {
                if (e.key.code == Keyboard::R && (instScreen || finished)) {
                    instScreen = false;
                    car[0].setX(300);
                    car[0].setY(1700);
                    car[0].setSpeed(0);
                    car[0].setAngle(0);

                    userCheckpoint = 0;

                    elapsed = 0;

                    for (int i = 1; i < NumberOfCars; i++) {
                        car[i].setX(300 + i * 50);
                        car[i].setY(1700 + i * 80);
                        car[i].setSpeed(7 + i);
                        car[i].setAngle(0);
                        car[i].setCheckpoint(0);
                    }

                    offsetX = 0;
                    offsetY =0;
                    finished = false;
                }
                if (e.key.code == Keyboard::H) {
                    instScreen = true;

                }
            }
        }


        //////////////////////////////
        /// GAME LOOP CALCULATIONS ///
        //////////////////////////////

        //ELAPSED TIME
        float time = clock.getElapsedTime().asSeconds();
        clock.restart();
        elapsed += time;

        std::cout << car[0].getX() << ", " << car[0].getY() << std::endl;

        //TIME
        sf::Text text;
        text.setFont(font);
        int intElapsedTime = static_cast<int>(elapsed);
        std::string timeString = std::to_string(intElapsedTime);
        text.setString(timeString);

        //TEXT ATTRIBUTES
        text.setCharacterSize(24);
        text.setFillColor(sf::Color::White);
        text.setStyle(sf::Text::Bold | sf::Text::Underlined);
        text.setPosition(0, screenHeight-70);

        //USER CAR MOVEMENT (U,D,L,R)
        bool Up = 0, Right = 0, Down = 0, Left = 0;
        if (Keyboard::isKeyPressed(Keyboard::Up)) { Up = 1; }
        if (Keyboard::isKeyPressed(Keyboard::Right)) { Right = 1; }
        if (Keyboard::isKeyPressed(Keyboard::Down)) { Down = 1; }
        if (Keyboard::isKeyPressed(Keyboard::Left)) { Left = 1; }

        //USER CAR MOVEMENT CALCULATION
        if (Up && speed < maxSpeed) {
            if (speed < 0) { speed += dec; }
            else { speed += acc; }
        }

        if (Down && speed > -maxSpeed) {
            if (speed > 0) { speed -= dec; }
            else { speed -= acc; }
        }

        if (!Up && !Down) {
            if (speed - dec > 0) { speed -= dec; }
            else if (speed + dec < 0) { speed += dec; }
            else { speed = 0; }
        }

        //USER CAR ANGLE CALCULATION
        if (Right && speed != 0) { angle += turnSpeed * speed / maxSpeed; }
        if (Left && speed != 0) { angle -= turnSpeed * speed / maxSpeed; }

        //SET USER CAR X & Y
        if (speed != 0) {
            car[0].setX(car[0].getX() + sin(angle) * speed);
            car[0].setY(car[0].getY() - cos(angle) * speed);
        }

        //SET USER CAR SPEED
        car[0].setSpeed(speed);

        //SET USER CAR ANGLE
        car[0].setAngle(angle);

        //NPC CAR MOVEMENT
        for (int i = 0; i < NumberOfCars; i++) { car[i].move(); }

        //NPC FIND TARGET
        for (int i = 1; i < NumberOfCars; i++) { car[i].findTarget(); }

        //CAR COLLISION
        for (auto &i : car) {
            for (auto &j : car) {
                if (&i != &j) {
                    float dx = j.getX() - i.getX();
                    float dy = j.getY() - i.getY();
                    float distance = sqrt(dx * dx + dy * dy);

                    if (distance < 2 * carRadius) {
                        float overlap = 2 * carRadius - distance;
                        float angleC = atan2(dy, dx);
                        float moveX = (overlap / 2) * cos(angleC);
                        float moveY = (overlap / 2) * sin(angleC);
                        i.setX(i.getX() - moveX);
                        i.setY(i.getY() - moveY);
                        j.setX(j.getX() + moveX);
                        j.setY(j.getY() + moveY);
                    }
                }
            }
        }

        //USER CAR CHECKPOINT
        float checkpointX = car[0].getX() - points[userCheckpoint][0];
        float checkpointY = car[0].getY() - points[userCheckpoint][1];
        float distCheckpoint = sqrt((checkpointX * checkpointX) + (checkpointY * checkpointY));

        //DISTANCE TO HIT CHECKPOINT
        if (distCheckpoint < 100) {
            userCheckpoint++;

            // Check if the last checkpoint is reached
            if (userCheckpoint > num-1) {
                finishedTime = elapsed;
                finished = true;

                if (finishedTime < bestTime) {
                    bestTime = finishedTime;
                }
            }
        }

        //GET NEW CAMERA POSITION
        if ((car[0].getX() > 320) && (car[0].getX() < car[0].getBackgroundWidth()-320)) { //BORDER FIX?
            offsetX = car[0].getX() - 320;
        }
        if ((car[0].getY() > 240) && (car[0].getY() < car[0].getBackgroundHeight()-240)) {
            offsetY = car[0].getY() - 240;
        }

        ///////////////
        /// DRAWING ///
        ///////////////



        if (!finished && !instScreen) {

            //////////////////
            /// SET SCREEN ///
            //////////////////

            //CLEAR SCREEN
            app.clear(Color::White);

            //SET BACKGROUND TO CAMERA POSITION
            sBackground.setPosition(-offsetX, -offsetY);

            //SET MARKER POSITION ON SCREEN
            checkpointMarker.setPosition(points[userCheckpoint][0] - offsetX, points[userCheckpoint][1] - offsetY);

            //DRAW BACKGROUND
            app.draw(sBackground);

            //DRAW TIME
            app.draw(text);

            //DRAW CHECKPOINT NUM
            std::string checkpointNumString = std::to_string(userCheckpoint);
            std::string underTime = "CHECKPOINT #" + checkpointNumString;
            text.setString(underTime);
            text.setPosition(0, screenHeight - 36);
            app.draw(text);

            text.setPosition(0, 0);

            //DRAW CHECKPOINT MARKER
            app.draw(checkpointMarker);

            //CAR COLORS
            Color colors[10] = {Color::Red, Color::Green, Color::Magenta, Color::Blue, Color::White};

            //DRAW EACH CAR
            for (int i = 0; i < NumberOfCars; i++) {
                sCar.setPosition(car[i].getX() - offsetX, car[i].getY() - offsetY);
                sCar.setRotation(car[i].getAngle() * 180 / 3.141593);
                sCar.setColor(colors[i]);
                app.draw(sCar);
            }
        } else if(finished && !instScreen) {
            car[0].setAngle(0);
            app.clear(sf::Color::Black);
            std::string finishedTimeString = std::to_string(finishedTime);
            std::string bestTimeString = std::to_string(bestTime);
            std::string endMessage = "Congrats! You finished the race :) \n\nYour time: " + finishedTimeString +
                                     " s.\n\nSession fastest time: " + bestTimeString + " s.\n\nPress R to restart.\n\nPress H if you need directions.";
            endText.setString(endMessage);
            app.draw(endText);
        }
        else {
            app.clear(sf::Color::Black);
            app.draw(howToInstructions);
            if (finished) {
                finished = false;
            }
        }

        //DISPLAY
        app.display();
    }

    return 0;
}