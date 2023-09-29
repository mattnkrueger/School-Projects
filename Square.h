//Interface for 'Square' class
//initializes members and methods

#ifndef SQUARE_H
#define SQUARE_H

#include <iostream>
#include <fstream>

//Square class definition
class Square { //milestone 1
    //public methods
    public:
        //overload function for << to write to file
        friend std::ostream& operator<<(std::ostream& out, const Square& squareValue){
            out << squareValue.getNum(); //fixes bug of "invalid operands to binary expression ('std::ofstream' (aka 'basic_ofstream<char>') and 'const Square')"
            return out;
        }

        //constructor
        explicit Square(int number = 0, bool change = true): num(number), changeable(change){} //called upon object instantiation and sets values using constructor with parameter and default values

        //setter and getter methods
        void setNum(int val); //sets integer value of num. Valid values from 0-9. 0 signifies empty square
        void setChangeable(bool change); //sets boolean value of changeable. Valid values of true and false. Default initialization of true
        int getNum() const; //returns integer value of num
        bool getChangeable() const; //returns boolean value of changeable

    private:
            int num; //holds the value of the square number as an integer
            bool changeable; //holds the value of the boolean for if the square is able to be changed
};

#endif