//Interface for 'Sudoku' class
//initializes members and methods

#ifndef SUDOKU_H
#define SUDOKU_H

#include <fstream>
#include <iostream>
#include <vector>
#include <string>
#include "Square.h"

//Sudoku class definition
class Sudoku {
    public:
        explicit Sudoku(const std::vector<std::vector<int>>& puzzle);

        //milestone 1
        void setSquareValue(int row, int col, int num, bool change, bool force); //setter for sudoku square value
        int getSquareValue(int row, int col) const; //getter for sudoku square value
        bool getSquareChangeable(int row, int col) const; //getter for if square is changeable

        //milestone 2
        void display() const; //display text sudoku with styling

        //milestone 3
        bool isValid(int row, int col, int num); //returns whether the square is changeable and requested position does not violate rules of sudoku game
        void saveToFile(const std::string& fileName) const; //function for writing to file
        void loadFromFile(const std::string& fileName) const; //loads information inside the file created by saveToFile()

        //milestone 4
        bool isSolved(); //sets boolean for if the sudoku is filled (no more 0s)
        void menu(const Sudoku &obj); //menu template until user decision (removes a lot of code)

    private:
        Square grid[9][9]; //puzzle vector of class Square
};

#endif

