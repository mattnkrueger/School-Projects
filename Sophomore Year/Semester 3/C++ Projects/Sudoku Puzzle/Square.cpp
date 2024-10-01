//Method definitions for 'Square' class
//defines set and get methods for members num and changeable

#include "Square.h"
#include <iostream>
#include <stdexcept>
// '#include <stdexcept>' not important to the hw guidelines, included for functionality when testing input values
using namespace std;


//setters

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: setNum                                                                                                //
//description: set the value of the member attribute num                                                        //
//input: integer value                                                                                          //
//output: no output; void function... sets the value of the integer (or defualt 0 if invalid value inputted)    //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

void Square::setNum(int val) {
    //check if valid and throw error if not
    if ( (val >= 0) && (val <=9) ){
        num = val;
    }
    else {
        num = 0; //defualt value
        //throw std::invalid_argument("integer value out of range.");
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: setNum                                                                                                //
//description: set the value of the member attribute changeable                                                 //
//input: boolean value                                                                                          //
//output: no output; void function... sets the value of the integer (or defualt true if invalid value inputted) //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

void Square::setChangeable(bool change) {
    if ( (change == true) || (change==false)) {
        changeable = change;
    }
    else {
        changeable = true; //default value
        //throw std::invalid_argument("boolean value not inputted.");
    }
}

//getters
int Square::getNum() const {return num;}
bool Square::getChangeable() const {return changeable;}