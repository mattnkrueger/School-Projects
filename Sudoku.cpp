#include "Sudoku.h"
#include <fstream>
#include <iostream>
#include <vector>
#include <format>
#include <string>

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: CONSTRUCTOR                                                                                           //
//description: set the value of the member attributes row, col, num, change, & force                            //
//input: 2d array (of sudoku values)                                                                            //
//output: no output; only sets values for member attributes                                                     //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Sudoku::Sudoku(const std::vector<std::vector<int>> &puzzle) {
    //r = row iterator... c = column iterator
    for (int r = 0; r < 9; r++) { //loops through rows first
        for (int c = 0; c < 9; c++) { //loops through columns second
            int numberP = puzzle[r][c];
            bool changeP = (numberP == 0); //sets boolean by using expression to check if numberVal is a changeable value
            bool forceP = (numberP != 0); //sets boolean by using expression for if num is not equal to 0

            //set square value using values passed by array into constructor
            setSquareValue(r, c, numberP, changeP, forceP);
        }
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: setSquareValue                                                                                        //
//description: set the value of the member attributes row, col, num, change, & force                            //
//input: row, col, num, change and force                                                                        //
//output: sets the number value of squares based off of logic: If force is true, this function should set       //
//the square to the values num and changeable at the location specified by row and col. If force is false, then //
//the square should only be set to num if changeable is true                                                    //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void Sudoku::setSquareValue(int row, int col, int num, bool change, bool force) {
    if (row<0 || row >= 9 || col < 0 || col >= 9) {
        return; //ignore if out of range
    }
    if (force) {
        //set square to specified num and changeable if force is true
        if (num >=1 && num <= 9) {
            grid[row][col].setNum(num); //set num at square location
            grid[row][col].setChangeable(change); //set changeable at square location
        }
    }
    else {
        //if force is false, and change is true, set to num
        if (change && grid[row][col].getChangeable()) {
            if (num >= 1 && num <= 9) {
                grid[row][col].setNum(num); //set num only at square location
            }
        }

    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Getter functions (optional)    -> getSquareValue returns num @ row/col ... getSquareChangeable returns changeable at row/col //                                                                      //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
int Sudoku::getSquareValue(int row, int col) const {return grid[row][col].getNum();}

bool Sudoku::getSquareChangeable(int row, int col) const {return grid[row][col].getChangeable();}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: display                                                                                               //
//description: create visual display for the sudoku game                                                        //
//input: member attributes                                                                                      //
//output: returns a formatted cout statement by looping through all squares                                     //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void Sudoku::display() const {
    for (int rowD = 0; rowD < 9; rowD++) { //loops through rows first
        for (int colD = 0; colD < 9; colD++) { //loops through columns second
            std::cout << grid[rowD][colD].getNum(); //get square

            //formatting for 3rd and 6th columns and rows

            //add space between
            if (colD != 8) {
                std::cout << " ";
            }

            //vertical separator "|"
            if (colD == 2 || colD == 5) {
                std::cout << " | ";
            }
        }

        //horizontal separator "-"
        std::cout << std::endl;
        if (rowD ==2 || rowD ==5) {
            std::cout << "-----------------------" << std::endl;
        }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: isValid                                                                                               //
//description: returns whether square is able to alterable (changeable and if it abides by the rules)           //
//input: row, col, num                                                                                          //
//output: returns a boolean for if the square is changeable and follows sudoku rules                            //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
bool Sudoku::isValid(int row, int col, int num){
    // Rules:
    // 0. Must be a valid number - 0-9
    // 1. Must be changeable
    // 2. Must not equal any of the numbers in the same row and column
    // 3. Must not equal any of the numbers in the same 3x3 grid

    //test 0
    if (!((num >= 0) && (num < 9))) { //if it is not in the range -> invalid
        return false;
    }

    // Figure out which 3x3 grid the square is in:
    int gridRow = row / 3; // Floor division to get the number for which grid y value it is in (0, 1, or 2)
    int gridCol = col / 3; // Floor division to get the number for which grid x value it is in (0, 1, or 2)

    // Starting values for the 3x3 grids:
    int startGridRow = gridRow * 3; // Multiply by 3 to get the starting value
    int startGridCol = gridCol * 3; // Multiply by 3 to get the starting value

    if (grid[row][col].getChangeable()) {
        int potentialVal = num; //set potential value to check against cases (if changeable)

        // Check test 2 - Row and Column
        for (int i = 0; i < 9; i++) {
            if ((i != row && potentialVal == grid[i][col].getNum()) || (i != col && potentialVal == grid[row][i].getNum())) {
                return false; // Fails because there is the same value in the row or column
            }
        }

        // Check test 3 - 3x3 Grid
        for (int k = startGridRow; k < startGridRow + 3; k++) {
            for (int h = startGridCol; h < startGridCol + 3; h++) {
                if (potentialVal == grid[k][h].getNum()) {
                    return false; // Fails because there is the same value in the 3x3 grid
                }
            }
        }
    } else {
        return false; // Fails because it is not a changeable value
    }

    // If none of these trigger a false return statement, true is returned, thus it is a valid value
    return true;
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: saveToFile                                                                                            //
//description: write to file to save the sudoku in text form                                                    //
//input: const string of fileName                                                                               //
//output: no return; void return type                                                                           //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void Sudoku::saveToFile(const std::string &fileName) const {
    std::ofstream write; //create output stream
    write.open(fileName); //open file

    if (!write.is_open()) {
        //std::cout << std::format("error when opening file {}.", fileName) << std::endl; //error message if file does not open
        std::cout << "error opening file to write to." << std::endl;
        return; //end of function if nothing opens
    }

    for (int i = 0; i < 9; i++) {
        if (i != 0) {
            write << std::endl; //new line for formatting if the row is not the first (after each new row, a new line is added)
        }
        for (int j = 0; j < 9; j++) {
            write << grid[i][j] << " "; //read in the square values
        }
    }
    write.close(); // close the file after writing to it
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: loadFromFile                                                                                          //
//description: obtain text sudoku puzzle from file created by saveToFile                                        //
//input: const string of fileName                                                                               //
//output: no return; void return type                                                                           //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void Sudoku::loadFromFile(const std::string &fileName) const {
    std::ifstream loadTest; //declare filestream
    loadTest.open(fileName); //open file

    //unit test for loadFromFile
    int nextVal; //value to hold each new line

    if (loadTest.is_open()) {
        for (int row = 0; row < 9; row++) { //row traverse
            for (int col = 0; col < 9; col++) { //column traverse
                if (loadTest >> nextVal) { //if there is a next value
                    if (nextVal != grid[row][col].getNum()) { //if these values are not equal
                        std::cout << "saveToFile / loadFromFile test Failed: 1 or more values are not equal" << std::endl;
                        return; //end loop if there is an error
                    }
                }
            }
        }
        std::cout << "saveToFile / loadFromFile test Passed; Correct puzzle loaded. Puzzle in raw form:" << std::endl; //test passed message
        loadTest.close(); //close file after verifying that all values are equal

        //create new stream to load the correct values
        std::ifstream loadPuzzle;
        loadPuzzle.open(fileName);

        if (loadPuzzle.is_open()) {
            std::string line; //value to hold each new line
            while (getline(loadPuzzle, line)) { //while there is a new line
                std::cout << line;
                if (!loadPuzzle.eof()) {
                    std::cout << std::endl; //add a newline if not the last line (without the if statement, an extra line is added at the end)
                }
            }
        }
        else {
            std::cout << "error opening file to LOAD loadFromFile." << std::endl; //error message
            return; //end of function if there is an error
        }
        loadPuzzle.close(); //close the file
    }
    else {
        std::cout << "error opening file to TEST loadFromFile." << std::endl; //error message
        return; //end of function if there is an error
    }
    loadTest.close(); //close file
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: isSolved                                                                                              //
//description: checks if sudoku has been solved (all values are 0)                                              //
//input: none... analyses grid values                                                                           //
//output: boolean value                                                                                         //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
bool Sudoku::isSolved() {
    // IMPORTANT NOTE - this assumes that a valid starting sudoku puzzle is passed
    for (int r = 0; r < 9; r++) { //loops through rows first
        for (int c = 0; c < 9; c++) { //loops through columns second
            if (grid[r][c].getNum() != 0) { //sudoku not finished
                return false;
            }
        }
    }
    return true; //if all values are not 0, based off of the isValid function, all values should be valid and the sudoku puzzle is solved
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//method: menu                                                                                                  //
//description: print the menu for the user to see (better for readability and code slimming)                    //
//input: none                                                                                                   //
//output: no return; void return type                                                                           //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void Sudoku::menu(const Sudoku &obj) {
    obj.display();
    std::cout << std::endl
              << "Menu:" << std::endl
              << "1. Set a value in the puzzle (row, col, num)" << std::endl
              << "2. Solve the puzzle" << std::endl
              << "3. Restart with a new puzzle" << std::endl
              << "4. Exit" << std::endl
              << "Enter your choice: ";
}



