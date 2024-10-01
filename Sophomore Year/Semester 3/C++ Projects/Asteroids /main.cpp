/*-------------------------------------------------
 * Name: Matthew Krueger
 * Assignment: Homework #7 - Asteroids
 * Description: Asteroids game
 *
 * Changes made for extra credit:
 * 1. Starting screen
 * 2. Restart function
 * 3. Life counter
 * 4. High score & end screen
 * 5. Background Music, and sounds for bullets and explosions
 *------------------------------------------------*/

#include <list>
#include <cmath>
#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>
#include <iostream>
#include <string>
#include <ctime>

using namespace sf;

//initialize height and widths of screen
const int W = 1200;
const int H = 800;

//initialize value for number of lives
const int startingLives = 3;

//conversion between degree to radians
float DEGTORAD = 0.017453f;

//[][][][][][][][][][][][][][]//
//      Animation Class       //
//[][][][][][][][][][][][][][]//
class Animation
{
public:
    //float values to represent frame and speed
	float Frame, speed;

    //sprite value for a sprite (animation)
	Sprite sprite;

    //vector of rectangle values representing frames
    std::vector<IntRect> frames;

    /*
     * Function: Default constructor
     * Description: nothing
     * Input: none
     * Output: none
     */
    Animation(){}

    /*
     * Function: Constructor for Animation
     * Description: initialize important values relating animations of elements
     * Input: texture (by reference Texture), speed (float), and x pos, y pos, width, height, and count (integer)
     * Output: none
     */

    Animation (Texture &t, int x, int y, int w, int h, int count, float Speed)
    {
        //set frame to 0
        Frame = 0;

        //set speed to speed value passed into constructor
        speed = Speed;

        //loop through count and add rectangles to frames vector
        for (int i = 0; i < count; i++) {
            frames.push_back(IntRect(x + i * w, y, w, h));
        }

        //set the texture to the texture passed
        sprite.setTexture(t);

        //set the origin to width/2, height/2
        sprite.setOrigin(w/2,h/2);

        //set the texture for the rectangle to frames[0]
        sprite.setTextureRect(frames[0]);
    }

    /*
     * Function: update
     * Description: update the frame, number of elements, and set textures of rectangle
     * Input: none
     * Output: none
     */
	void update()
	{
    	Frame += speed;
		int n = frames.size();
        if (Frame >= n) { Frame -= n; }
        if (n > 0) { sprite.setTextureRect(frames[int(Frame)]); }
	}

    /*
     * Function: isEnd
     * Description: boolean value representing if the frames + speed is greater or equal to the size of the frames vector
     * Input: none
     * Output: boolean value
     */
	bool isEnd()
	{
	  return Frame+speed>=frames.size();
	}

};

//[][][][][][][][][][][][][][]//
//        Entity Class        //
//[][][][][][][][][][][][][][]//
class Entity
{
public:
    //float values for x pos, y pos, change in x and y, radius, and angle
    float x,y,dx,dy,R,angle;

    //boolean for if on screen
    bool life;

    //string representing name
    std::string name;

    //Animation for type of animation
    Animation anim;

    /*
     * Function: Default constructor
     * Description: set life of entity to 1
     * Input: none
     * Output: none
     */
    Entity() {
      life=1;
    }

    /*
     * Function: settings
     * Description: set attributes
     * Input: animation (by reference Animation), x pos, y pos, radius (as integers), angle (float). angle will be set to 0 and radius will be set to 0 by default
     * Output: none
     */
    void settings(Animation &a,int X,int Y,float Angle=0,int radius=1)
    {
      //set each member attribute
      anim = a;
      x=X; y=Y;
      angle = Angle;
      R = radius;
    }

    //virtual method update (will be overriden by the type of entity, signalled by this one doing nothing)
    virtual void update(){};

    /*
     * Function: draw
     * Description: draw the game by setting sprite position, rotation, and drawing sprite.
     * Input: app window passed by reference
     * Output: none
     */
    void draw(RenderWindow &app)
    {
      //this block sets sprite position on screen
      anim.sprite.setPosition(x,y);
      anim.sprite.setRotation(angle+90);
      app.draw(anim.sprite);

      //this block creates a circle which inscribes respective entity (good for highlighting entities on screen)
      CircleShape circle(R);
      circle.setFillColor(Color(255,0,0,170));
      circle.setPosition(x,y);
      circle.setOrigin(R,R);
      //app.draw(circle); //uncomment this for testing
    }

    /*
     * Function: Entity destructor
     * Description: free memory
     * Input: app window
     * Output: none
     */
    virtual ~Entity(){};
};

//[][][][][][][][][][][][][][]//
//       Asteroid Class       //
//[][][][][][][][][][][][][][]//
class asteroid: public Entity
{
public:

    /*
     * Function: Asteroid default constructor
     * Description: call entity default constructor and then set dx and dy of asteroid
     * Input: none
     * Output: none
     */
    asteroid()
  {
    //set dx and dy to a random numbers between -4 and 3 (this is the movement speed of the specific asteroid)
    dx=rand()%8-4;
    dy=rand()%8-4;

    //set name
    name="asteroid";
  }

/*
 * Function: update
 * Description: update the position of the asteroid on screen by incrementing x and y by dx and dy. If asteroid goes "off the screen", then place on opposite side (similar to pac man effect)
 * Input: none
 * Output: none
 */
void update()
  {
    //increment x and y poisition by dx and dy
    x+=dx;
    y+=dy;

    //if asteroid exits positive x side, set x position to 0
    if (x > W) { x = 0; }

    //if asteroid exits positive x side, set x position to  max width
    if (x < 0) { x = W; }

    //if asteroid exits positive y side, set y position to 0
    if (y > H) { y = 0; }

    //if asteroid exits negative y side, set y position to max height
    if (y < 0) { y = H; }
  }

  /*
   * Function: getAsteroidCount
   * Description: return the number of asteroids in game
   * Input: none
   * Output: integer value representing number of asteroids on screen
   */
  int getAsteroidCount () {
      return asteroidCount;
  }

private:
    static int asteroidCount;
};

//initialize asteroid count to 0
int asteroid::asteroidCount = 0;

//[][][][][][][][][][][][][][]//
//        Bullet Class        //
//[][][][][][][][][][][][][][]//
class bullet: public Entity
{
public:
 /*
  * Function: Bullet Default Constructor
  * Description: call entity constructor and then set name to bullet
  * Input: none
  * Output: none
  */
  bullet()
  {
    name="bullet";
  }

   /*
    * Function: update
    * Description: update the position of the bullet on screen by incrementing x and y by dx and dy. If asteroid goes "off the screen", then set bullet life to 0
    * Input: none
    * Output: none
    */
    void update()
      {

       //set bullet dx and dy based off of angle
       dx=cos(angle*DEGTORAD)*6;
       dy=sin(angle*DEGTORAD)*6;

       //set random path of bullet
       //angle+=rand()%7-3;

       //increment x and y positions on screen
       x+=dx;
       y+=dy;

       //if the bullets leave screen, set life to 0
       if (x > W || x < 0 || y > H || y < 0) { life = 0; }
      }

};

//[][][][][][][][][][][][][][]//
//        Player Class        //
//[][][][][][][][][][][][][][]//
class player: public Entity
{
public:
   //boolean variable for if player is accelerating
   bool thrust;

  /*
   * Function: player Default Constructor
   * Description: call entity default constructor and then set the player name
   * Input: none
   * Output: none
   */
   player()
   {
     name="player";
   }

  /*
   * Function: update
   * Description: update the position of the asteroid on screen by incrementing x and y by dx and dy. If player goes "off the screen", then place on opposite side
   * Input: none
   * Output: none
   */
   void update()
   {
     //if the player is accelerating change the speed of the player
     if (thrust)
      { dx+=cos(angle*DEGTORAD)*0.15;
        dy+=sin(angle*DEGTORAD)*0.15; }

     //if the player is not accelerating, decelerate speed
     else
      { dx*=0.99;
        dy*=0.99; }

    //initiate the maxSpeed to 15
    int maxSpeed=15;

    //initiate speed to magnitude of x and y speeds
    float speed = sqrt(dx*dx+dy*dy);

    //if speed is greater than maxSpeed, set the x and y speeds to maxSpeed/speed
    if (speed>maxSpeed)
     { dx *= maxSpeed/speed;
       dy *= maxSpeed/speed; }

    //update x and y position on screen
    x+=dx;
    y+=dy;

   //if player exits positive x side, set x position to 0
   if (x > W) { x = 0; }

   //if player exits positive x side, set x position to  max width
   if (x < 0) { x = W; }

   //if player exits positive y side, set y position to 0
   if (y > H) { y = 0; }

   //if player exits negative y side, set y position to max height
   if (y < 0) { y = H; }
   }

};

/*
 * Function: isCollide
 * Description: check to see if a bullet collided with another entity. Compute a conditional to check if the square diff of x and y is less than the square diff of radii (of the two entities)
 * Input: Two entities passed by pointer
 * Output: boolean representing if a collision occurred
 */
bool isCollide(Entity *a,Entity *b)
{
  return (b->x - a->x)*(b->x - a->x)+
         (b->y - a->y)*(b->y - a->y)<
         (a->R + b->R)*(a->R + b->R);
}
//[][][][][][][][][][][][][][]//
//    Game Variables Class    //
//[][][][][][][][][][][][][][]//
class GameVariables {
public:
    //stores number of ufos in game
    static int numUfos;

    //store number of asteroids
    static int numAsteroids;

    //stores the current score
    static int score;

    //stores the high score
    static int highScore;

    //boolean for game in play
    static bool inPlay;

    //boolean for start screen open
    static bool startScreen;

    //boolean for end screen opened
    static bool endScreen;

    //boolean for restarting game
    static bool start;
};
//set number of asteroids in game to 0 initially
int GameVariables::numAsteroids = 0;

//set initial ufoInGame to 0
int GameVariables::numUfos = 0;

//set initial score
int GameVariables::score = 0;

//set initial high score
int GameVariables::highScore = 0;

//init to false -> start screen should be pulled up
bool GameVariables::inPlay = false;

//set start screen to opened when played
bool GameVariables::startScreen = true;

//initialize to false. dying will pull up the endScreen
bool GameVariables::endScreen = false;

//set initially to 0
bool GameVariables::start = false;

/*
 * Function: CreateAsteroids
 * Description: add asteroids to the game
 * Inputs: reference to entity list, animation anim
 * Outputs: None
 */
void createAsteroids(std::list<Entity*> &entities, Animation &anim)
{
    //if no asteroids (starting)
    if (GameVariables::numAsteroids <= 0) {
        //loop through 15 times to make 15 new asteroids
        for (int i = 0; i < 20; i++) {
            //create new asteroid
            asteroid *a = new asteroid();

            //random location for new asteroid
            int randX = rand()%W;
            int randY = rand()%H;

            //get distance from middle
            int xDistFromMiddle = W - randX;
            int yDistFromMiddle = H - randY;

            //if close
            if (xDistFromMiddle < 30 && yDistFromMiddle < 30) {
                //add distance between so spawn is further away from center
                randX += 30;
                randY += 30;
            }

            //set asteroid attributes
            a->settings(anim, randX, randY, rand() % 360, 25);

            //add to list
            entities.push_back(a);

            //increment asteroid count (3 because the rock breaks into 2)
            GameVariables::numAsteroids += 1;
        }
    }
}

/*
 * Function: clearAsteroids
 * Description: restart the game
 * Inputs:list of entities
 * Outputs: None
 */
void clearAsteroids(std::list<Entity*> &entities)
{
    //if there are still more asteroids to clear
    if (GameVariables::numAsteroids > 0) {
        //loop through each entity in entities
        for (auto entity: entities) {
            //if the entity is an asteroid
            if (entity->name == "asteroid") {
                //deallocate memory
                entity->life = 0;
            }
        }
    }
}

//[][][][][][][][][][][][][][]//
//          Ufo Class         //
//[][][][][][][][][][][][][][]//
class Ufo : public Entity
{
public:
    /*
     * Function: Ufo Constructor
     * Description: create a ufo
     * Inputs: call entity constructor and then set name to ufo
     * Outputs: None
     */
    Ufo()
    {
        //set name
        name = "ufo";

        //set dx to random speed
        dx = rand()%4 +2;
    }

    /*
     * Function: Ufo Update
     * Description: change the position of the ufo on the screen
     * Inputs: None
     * Outputs: None
     */
    void update()
    {
        //increment dx
        x += dx;
    }
};



//[][][][][][][][][][][][][][]//
//         MAIN CODE          //
//[][][][][][][][][][][][][][]//
int main()
{
    //set seed time to random to ensure different sequence of random numbers upon each play
    srand(time(0));

    //time variable to store the time between asteroid spawns
    int ufoTime = 0;

    //initialize clock
    Clock timer;

    //current life count
    int currentLives = startingLives;

    //create window with values for W & H
    RenderWindow app(VideoMode(W, H), "Matthew Krueger's Asteroids!");

    //framerate of game
    app.setFramerateLimit(60);

    //load textures for spaceship, backround, explosion types C & B, rock, bullet, small rock.
    Texture t1,t2,t3,t4,t5,t6,t7,t8,t9;
    t1.loadFromFile("images/spaceship.png");
    t2.loadFromFile("images/background.jpg");
    t3.loadFromFile("images/explosions/type_C.png");
    t4.loadFromFile("images/rock.png");
    t5.loadFromFile("images/fire_blue.png");
    t6.loadFromFile("images/rock_small.png");
    t7.loadFromFile("images/explosions/type_B.png");


    /*
     * Ufo Icon Attribution
     * icon from: Flaticon
     * https://www.flaticon.com/free-icon/ufo_1888064?related_id=1888101&origin=search
     * Name: Ufo - Free transport icons
     */
    t8.loadFromFile("images/ufoIcon.png");

    /*
     * Start Background Attribution
     * Background from: Javier Miranda (nuvaproductions)
     * https://unsplash.com/photos/an-artists-rendering-of-a-space-ship-approaching-a-planet-SYRNx7SLHCk
     * Name: Alien planet and Asteroids
     */
    t9.loadFromFile("images/startBackground.jpg");

    //set everything to smooth
    t1.setSmooth(true);
    t2.setSmooth(true);
    t3.setSmooth(true);
    t4.setSmooth(true);
    t5.setSmooth(true);
    t6.setSmooth(true);
    t7.setSmooth(true);
    t8.setSmooth(true);
    t9.setSmooth(true);


    /*
     * Ufo Crossing Sound Attribution
     * sound from: Mattias "Matrix" Lahoud
     * https://freesound.org/people/MATRIXXX_/sounds/459150/
     * UFO ACTIVITY 01 05 SEC
     */
    sf::Music backgroundMusic;
    if (!backgroundMusic.openFromFile("sounds/asteroidsBackgroundMusic.ogg")) {
        std::cout << "Failed to load ufo_crossing music" << std::endl;
        return EXIT_FAILURE;
    }

    //MUSIC ATTRIBUTES
    backgroundMusic.setPitch(1.2f);
    backgroundMusic.setVolume(50.f);
    backgroundMusic.setLoop(true);

    /*
     * Bullet Sound Attribution
     * sound from: Mattias "Matrix" Lahoud
     * https://freesound.org/people/MATRIXXX_/sounds/459150/
     * RETRO, UNDERWATER EXPLOSION
     */
    sf::SoundBuffer bulletSoundBuffer;
    if(!bulletSoundBuffer.loadFromFile("sounds/bulletFire.ogg"))
    {
        return EXIT_FAILURE;
    }

    //load bullet sound
    sf::Sound bulletSound;
    bulletSound.setBuffer(bulletSoundBuffer);

    /*
     * Death Sound Attribution
     * sound from: Flying Deer
     * https://freesound.org/people/Flying_Deer_Fx/sounds/369005/
     * MOUTH DEATH 01 (MOUTH FX) MAN VOICE
     */
    sf::SoundBuffer deathSoundBuffer;
    if(!deathSoundBuffer.loadFromFile("sounds/deathNoise.ogg"))
    {
        return EXIT_FAILURE;
    }

    //load bullet sound
    sf::Sound deathSound;
    deathSound.setBuffer(deathSoundBuffer);

    /*
     * Asteroid Explosion Sound Attribution
     * sound from: Mattias Michael Lahoud
     * https://freesound.org/people/MATRIXXX_/sounds/403298/
     * RETRO EXPLOSION 02
     */
    sf::SoundBuffer asteroidExplosion;
    if(!asteroidExplosion.loadFromFile("sounds/asteroidExplosion.ogg"))
    {
        return EXIT_FAILURE;
    }

    // load explosion sound
    sf::Sound explosion1;
    explosion1.setBuffer(asteroidExplosion);

    /*
     * Ufo Crossing Sound Attribution
     * sound from: Mattias "Matrix" Lahoud
     * https://freesound.org/people/MATRIXXX_/sounds/459150/
     * UFO ACTIVITY 01 05 SEC
     */
    sf::Music ufoCrossing;
    if (!ufoCrossing.openFromFile("sounds/ufo_crossing.ogg")) {
        std::cout << "Failed to load ufo_crossing music" << std::endl;
        return EXIT_FAILURE;
    }

    //MUSIC ATTRIBUTES
    ufoCrossing.setPitch(1.2f);
    ufoCrossing.setVolume(50.f);
    ufoCrossing.setLoop(true);

    /*
     * Ufo Explosion Sound Attribution
     * sound from: TV_LING
     * https://freesound.org/people/TV_LING/sounds/523467/
     * perfect-fart
     */
    sf::SoundBuffer ufoExplosion;
    if(!bulletSoundBuffer.loadFromFile("sounds/bulletFire.ogg"))
    {
        return EXIT_FAILURE;
    }

    //load explosion sound
    sf::Sound explosion2;
    explosion2.setBuffer(ufoExplosion);

    //set background to t2 (background.jpg)
    Sprite background(t2);

    //set start background to t9 (startBackground.jpg)
    Sprite startBackground(t9);

    //end screen
    Sprite endBackground(t6);

    //create animations for all textures
    Animation sExplosion(t3, 0,0,256,256, 48, 0.5);
    Animation sRock (t4, 0,0,64,64, 16, 0.2);
    Animation sBullet(t5, 0,0,32,64, 16, 0.8);
    Animation sPlayer(t1, 40,0,40,40, 1, 0);
    Animation sPlayer_go(t1, 40,40,40,40, 1, 0);
    Animation sExplosion_ship(t7, 0,0,192,192, 64, 0.5);

    //ufo animation
    Animation sUfos(t8, 0,0,30,30, 1, 0);


    //initialize list oto hold entities
    std::list<Entity*> entities;

    createAsteroids(entities, sRock);

    //create a new player in memory
    player *p = new player();

    //update player settings with desired values
    p->settings(sPlayer,W/2,H/2,0,20);

    //add player to entity list
    entities.push_back(p);

    /*
     * Font Attribution:
     * Name: 8 Bit Operator
     * By: Grandoplex Productions
     * https://www.1001freefonts.com/8-bit-operator.font
     */
    sf::Font font;
    if (!font.loadFromFile("fonts/8-bit-operator/8bitOperatorPlus-Regular.ttf")) {
        std::cerr << "Failed to load font." << std::endl;
        return EXIT_FAILURE;
    }

    //Start screen text:
    sf::Text text;
    text.setFont(font);
    text.setCharacterSize(50);
    text.setFillColor(sf::Color::White);
    text.setStyle(sf::Text::Regular);

    //set scale of background image
    startBackground.setScale(0.5, 0.5);

    /////main loop/////
    while (app.isOpen())
    {
        //create event
        Event event;

        //if not in play, display start or end screen based off of if game is starting or ended
        if (!GameVariables::inPlay) {

            if (event.type == Event::Closed) {
                //close app
                app.close();
            }

            app.clear(sf::Color::Black);

            //if starting & not on end screen
            if (GameVariables::startScreen && !GameVariables::endScreen) {
                //create string to be displayed
                std::string instructions = "Asteroids\nPress 'S' to play";

                //set string to be displayed
                text.setString(instructions);

                //draw the start background
                app.draw(startBackground);

                //draw the text
                app.draw(text);

                //get input
                app.pollEvent(event);

                //if s pressed, start the game
                if (event.key.code == Keyboard::S) {
                    //play a bullet sound
                    bulletSound.play();

                    //start the game
                    GameVariables::inPlay = true;

                    //remove the start screen
                    GameVariables::startScreen = false;

                    //ensure end screen off
                    GameVariables::endScreen = false;

                    //set life to true
                    p->life = true;

                    //set start to true
                    GameVariables::start = true;

                    //start background music
                    backgroundMusic.play();
                }
            }

            //ending screen & not starting screen
            if (GameVariables::endScreen && !GameVariables::startScreen) {
                //set background scale & position
                endBackground.setScale(2,2);
                endBackground.setPosition(0,H *3/4);

                //draw end background
                app.draw(endBackground);

                //convert score to string
                std::string finalScore = std::to_string(GameVariables::score);

                //convert best score to string
                std::string bestScore = std::to_string(GameVariables::highScore);

                //new string variable
                std::string scoresString;

                //create new string
                scoresString = "\n\t\t\t\t\tYour Score: " + finalScore + "\n\t\t\t\t\tHigh Score: " + bestScore;

                //change text
                std::string restartInstructions = "Game Over!\nPress 'R' to restart\n\n" + scoresString;

                //set updated text
                text.setString(restartInstructions);

                //set text size
                text.setCharacterSize(50);

                //draw end text
                app.draw(text);

                //get input
                app.pollEvent(event);


                //if r pressed, restart the game
                if (event.key.code == Keyboard::R) {
                    //clear screen
                    app.clear(sf::Color::Black);

                    //play a bullet sound
                    bulletSound.play();

                    //turn off end screen
                    GameVariables::endScreen = false;

                    //turn on start screen
                    GameVariables::startScreen = true;

                    p->life = false;

                    GameVariables::inPlay = false;

                }
            }
        }

        //if in play:
        else if(GameVariables::inPlay) {

            //if restarted (player life = 1, restart = true, num asteroids = 0
            if (GameVariables::start) {
                //clear all asteroids
                clearAsteroids(entities);

                //set start to false
                GameVariables::start = false;

                //clear app
                app.clear(sf::Color::White);

                //reset score
                GameVariables::score = 0;

                //reset lives
                currentLives = startingLives;

                //set life to true
                p->life = true;

                //spawn player
                p->settings(sPlayer, W/2, H/2, 0, 20);

                //set start to false
                GameVariables::start = false;

                //reset asteroids
                GameVariables::numAsteroids = 0;

                //reset ufo
                GameVariables::numUfos = 0;

                //reset clocks
                ufoTime = 0;
                timer.restart();
            }

            //while running, poll for events
            while (app.pollEvent(event)) {
                if (event.type == Event::Closed) {
                    //close app
                    app.close();
                }

                //if key pressed
                if (event.type == Event::KeyPressed) {

                    //if space key pressed
                    if ((event.key.code == Keyboard::Space) && (p->life)) {
                        //create new bullet
                        bullet *b = new bullet();

                        //play bullet sound
                        bulletSound.play();

                        //set bullet with a radius of 10
                        b->settings(sBullet, p->x, p->y, p->angle, 10);

                        //add bullet to entity list
                        entities.push_back(b);
                    }
                }
            }

            //right arrow pressed, alter angle +
            if (Keyboard::isKeyPressed(Keyboard::Right)) { p->angle += 3; }

            //left arrow pressed, alter angle -
            if (Keyboard::isKeyPressed(Keyboard::Left)) { p->angle -= 3; }

            //up arrow pressed, no angle change, set thrust to true
            if (Keyboard::isKeyPressed(Keyboard::Up)) {
                p->thrust = true;
            }

                //if up arrow not pressed, set thrust to false
            else { p->thrust = false; }

            for (auto a: entities) {
                for (auto b: entities) {
                    if ((a->name == "asteroid" && b->name == "bullet") || (a->name == "ufo" && b->name == "bullet")) {
                        if (isCollide(a, b)) {

                            //if bullet x asteroid, play asteroid explosion
                            if (a->name == "asteroid") {
                                //play explosion sound
                                explosion1.play();

                                GameVariables::score += 100;

                                //decrement asteroid count
                                GameVariables::numAsteroids -= 1;
                            }

                            //if bullet x ufo, play ufo explosion
                            if (a->name == "ufo") {
                                //restart clocks
                                ufoTime = 0;
                                timer.restart();

                                //play ufo warning sound
                                ufoCrossing.stop();

                                //play explosion sound
                                explosion1.play();

                                //increment score
                                GameVariables::score += 500;

                                //ufo life decrement
                                a->life = 0;

                                //decrement ufo count
                                GameVariables::numUfos = 0;
                            }

                            //set both of the entities lives to false
                            a->life = false;
                            b->life = false;

                            //create a new entity for explosion
                            Entity *e = new Entity();

                            //set attributes
                            e->settings(sExplosion, a->x, a->y);

                            //set name
                            e->name = "explosion";

                            //add to entities
                            entities.push_back(e);
                        }
                    }

                    if ((a->name == "player" && b->name == "asteroid") || (a->name == "player" && b->name == "ufo")) {
                        // check collision between a and b
                        if (isCollide(a, b)) {
                            //if asteroid
                            if (b->name == "asteroid") {
                                //increment score
                                GameVariables::score += 100;

                                //decrement number of asteroids
                                GameVariables::numAsteroids -= 1;
                            }
                            //if ufo
                            if (b->name == "ufo") {
                                //restart clocks
                                ufoTime = 0;
                                timer.restart();

                                //increment score
                                GameVariables::score += 500;

                                // stop ufo sound
                                ufoCrossing.stop();

                                //ufo life
                                b->life = 0;

                                //decrement number of ufos
                                GameVariables::numUfos = 0;
                            }

                            //destroy b
                            b->life = false;

                            //play death sound
                            deathSound.play();

                            //create new explosion entity
                            Entity *e = new Entity();

                            //set attributes
                            e->settings(sExplosion_ship, a->x, a->y);

                            //set name
                            e->name = "explosion";

                            //add to entities
                            entities.push_back(e);

                            //decrement current lives
                            currentLives -= 1;

                            //respawn player at
                            if (currentLives > 0) {
                                //set spawn at same location
                                p->settings(sPlayer, W/2, H/2, 0, 20);

                                //reset speed to 0
                                p->dx = 0;
                                p->dy = 0;
                            } else {
                                //check if the new score is greater than the high score
                                if (GameVariables::score > GameVariables::highScore) {
                                    //if true, set high score to current score
                                    GameVariables::highScore = GameVariables::score;
                                }
                            }
                        }
                    }
                }
            }

            //if player is thrusting
            if (p->thrust) {

                //set animation sPlayer_go
                p->anim = sPlayer_go;
            }

                //else set animation to sPlayer
            else { p->anim = sPlayer; }


            //for each entity in entity list
            for (auto e: entities) {

                //if the entity is an explosion
                if (e->name == "explosion") {

                    //if anim.isEnd, set the entities life to 0
                    if (e->anim.isEnd()) { e->life = 0; }
                }
            }

            if (GameVariables::numUfos == 0) {ufoCrossing.stop();}

            //create new asteroids if needed (rounds)
            createAsteroids(entities, sRock);

            //hold time elapsed
            int elapsedTime = timer.getElapsedTime().asSeconds();

            //increment the ufoTime by each second passed
            if (elapsedTime >= 1) {
                //add one second to ufo time
                ufoTime += 1;

                //resets elapsed every second (counts by seconds)
                timer.restart();
            }

            //spawn new asteroid every 6 seconds
            if (ufoTime == 8 && GameVariables::numUfos == 0) {
                //restart clock
                ufoTime = 0;
                timer.restart();

                std::cout << "ufo created" << std::endl;
                //create new ufo
                Ufo *u = new Ufo();

                u->life = true;
                //set ufo settings (random y height on screen)
                u->settings(sUfos, 0, rand()%H, 0, 30);

                //add to entities
                entities.push_back(u);

                //play ufo warning
                ufoCrossing.play();

                //increment number of ufos
                GameVariables::numUfos += 1;
            }

            //for each iterable in entity list
            for (auto i = entities.begin(); i != entities.end();) {

                //set the iterable to the entity
                Entity *e = *i;

                if (e->name == "ufo" && (e->x > W || e->x < 0)) {
                    std::cout << "here" << std::endl;
                    e->life = false;
                    GameVariables::numUfos = 0;

                    //reset times
                    ufoTime=0;
                    timer.restart();
                }

                //update the entity
                e->update();

                //update the animation of the entity
                e->anim.update();

                //if the life of the entity is 0,  remove the entity from the list and deallocate the memory
                if (e->life == false) {
                    i = entities.erase(i);
                    delete e;
                }

                    //if entity life is 1, move to the next entity
                else i++;
            }

            //////draw//////
            if (currentLives != 0) {
                app.draw(background);

                //for each entity
                for (auto i: entities) {
                    //draw the entity on the app
                    i->draw(app);
                }

                //output text for new score
                std::string scoreTxt = std::to_string(GameVariables::score);

                //output text for number of lives
                std::string livesTxt = std::to_string(currentLives);

                std::string gameTxt = "SCORE: " + scoreTxt + "\nNUMBER OF LIVES: " + livesTxt;

                //decrease font size
                text.setCharacterSize(20);

                //set new text
                text.setString(gameTxt);

                //set position of text on screen
                text.setPosition(0,0);

                //put text on screen
                app.draw(text);


            } else {
                //erase bullets
                for (auto i: entities) {
                    if (i->name != "player") {
                        i->life = 0;
                    }
                }

                //stop sound
                ufoCrossing.stop();

                //if there are remaining asteroids
                if (GameVariables::numAsteroids > 0) {
                    //delete all remaining asteroids
                    clearAsteroids(entities);
                }

                //end background music
                backgroundMusic.stop();

                //set in play to false
                GameVariables::inPlay = false;

                //set end screen to true
                GameVariables::endScreen = true;

                //set restart to true
                GameVariables::start = false;
            }
        }

    //display
    app.display();

    }

    return 0;
}
