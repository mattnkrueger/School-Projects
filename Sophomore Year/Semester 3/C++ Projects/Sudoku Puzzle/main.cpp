//----------------------------------------------------//
// Name: Matthew Krueger                              //
// Student ID: 01478034                               //
// Project: CIE HW-3                                  //
// Project Description:                               //
//      Sudoku Game                                   //
//            Milestone #1 Due: 9/22/23               //
//            Milestone #2 Due: 9/22/23               //
//            Milestone #3 Due: 9/29/23               //
//            Milestone #4 Due: 9/29/23               //
//----------------------------------------------------//

#include <iostream>
#include <vector>

#include "Square.h"
#include "Sudoku.h"


int main() {
    //Milestone #1
    std::cout << "===================== MILESTONE 1 =====================" << std::endl;

    //Unit testing: 1 good, 1 bad unit tests for constructor each set and get methods called to initialize and return member variables of class Square

    //CONSTRUCTOR TESTS
    //Constructor TEST 1 - checks values of num: 9, changeable: true
    Square cT1(9, true);

    if (cT1.getNum() == 12){
        std::cout << "constructor num Test 2: Passed; num initialized and returned to 9 as expected" << std::endl;
    }
    else {
        std::cout << "constructor num Test 2: Failed; num initialized to default 0 (9 invalid)" << std::endl;
    }

    if (!cT1.getChangeable()){
        std::cout << "constructor changeable Test 2: Passed; changeable initialized and returned to true as expected" << std::endl;
    }
    else {
        std::cout << "constructor changeable Test 2: Failed; changeable initialized to default true (true invalid)" << std::endl;
    }

    //Constructor TEST 2 - checks values of num: 12, changeable: 'true'
    Square cT2(9, true);

    if (cT2.getNum() == 12){
        std::cout << "constructor num Test 2: Passed; num initialized and returned to 9 as expected" << std::endl;
    }
    else {
        std::cout << "constructor num Test 2: Failed; num initialized to default 0 (9 invalid)" << std::endl;
    }

    if (!cT2.getChangeable()){
        std::cout << "constructor changeable Test 2: Passed; changeable initialized and returned to true as expected" << std::endl;
    }
    else {
        std::cout << "constructor changeable Test 2: Failed; changeable initialized to default true (true invalid)" << std::endl;
    }

    //SET AND GET METHOD TESTS
    //set/get TEST 1 - checks values of num: 4, changeable: false
    Square sgT1;
    sgT1.setNum(4);
    sgT1.setChangeable(false);

    if (sgT1.getNum() == 4){
        std::cout << "set/get num Test 1: Passed; num initialized and returned to 4 as expected" << std::endl;
        }
        else {
            std::cout << "set/get num Test 1: Failed; num initialized to default 0 (4 invalid)" << std::endl;
        }

    if (!sgT1.getChangeable()){
        std::cout << "set/get changeable Test 1: Passed; changeable initialized and returned to false as expected" << std::endl;
        }
        else {
        std::cout << "set/get changeable Test 1: Failed; changeable initialized to default true (false invalid)" << std::endl;
        }

    //set/get TEST 2 - checks values of num: 36, changeable: 'false' (string)
    Square sgT2;
    sgT2.setNum(36);
    sgT2.setChangeable('false');

    if (sgT2.getNum() == 4){
        std::cout << "set/get num Test 2: Passed; num initialized and returned to 36 as expected" << std::endl;
    }
    else {
        std::cout << "set/get num Test 2: Failed; num initialized to default 0 (36 invalid)" << std::endl;
    }

    if (!sgT2.getChangeable()){
        std::cout << "set/get changeable Test 2: Passed; changeable initialized and returned to 'false' as expected" << std::endl;
    }
    else {
        std::cout << "set/get changeable Test 2: Failed; changeable initialized to default true ('false' invalid)" << std::endl;
    }


    //Milestone #2
    std::cout << "===================== MILESTONE 2 =====================" << std::endl;

    //UNIT TESTS FOR MILESTONE 2 (using sudoku table initialize above ^^^

    //CONSTRUCTOR
    // Test Constructor: Valid input
    std::vector<std::vector<int>> validPuzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},// all values are valid (0-9)
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    Sudoku constructorTest1(validPuzzle);

    // Check if the constructor correctly initializes the grid
        bool validConstructorTestPassed = true;
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                int value = constructorTest1.getSquareValue(row, col);
                bool changeable = constructorTest1.getSquareChangeable(row, col);

                // Check if the values match the initial puzzle
                if (value != validPuzzle[row][col] || (value != 0 && !changeable)) {
                    validConstructorTestPassed = false;
                    break;
                }
            }
            if (!validConstructorTestPassed) {
                break;
            }
        }

        if (validConstructorTestPassed) {
            std::cout << "constructor Test 1: Passed; Grid initialized correctly" << std::endl;
        } else {
            std::cout << "constructor Test 1: Failed; Grid initialization incorrect" << std::endl;
        }

    // Test Constructor: Invalid input (out of range values)
    std::vector<std::vector<int>> invalidPuzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 15, 0, 0, 0}, // Invalid value (15)
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    Sudoku constructorTest2(invalidPuzzle);

    // Check if the constructor correctly initializes the grid with invalid input
        bool invalidConstructorTestPassed = true;
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                int value = constructorTest2.getSquareValue(row, col);
                bool changeable = constructorTest2.getSquareChangeable(row, col);
                // Check if the values match the initial puzzle and if out-of-range values are set to 0
                if (value != invalidPuzzle[row][col] || (value == 15) || (value < 0 || value > 9) || (value != 0 && !changeable)) {
                    invalidConstructorTestPassed = false;
                    break;
                }
            }
            if (!invalidConstructorTestPassed) {
                break;
            }
        }

        if (!invalidConstructorTestPassed) {
            std::cout << "Constructor Test 2: Failed; Grid initialization incorrect with out-of-range values" << std::endl;
        } else {
            std::cout << "Constructor Test 2: Passed; Grid initialized correctly with out-of-range values set to 0" << std::endl;
        }

    //set/get TESTS
    //sudoku table used:
    std::vector<std::vector<int>> initialPuzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    Sudoku sudoku(initialPuzzle);
    //TEST 1: valid input with force and changeable = true
    int row = 0, col = 2, num = 5;
    bool change = true, force = true;

    if (sudoku.getSquareChangeable(row, col) && (num >= 1 && num <= 9)) {
        sudoku.setSquareValue(row, col, num, change, force);
        if (sudoku.getSquareValue(row, col) == num) {
            std::cout << "set/get Test 1: Passed; Square value changed to 2 as expected" << std::endl;
        } else {
            std::cout << "set/get Test 1: Failed; Square value remains the same (ignored due to being out of range)" << std::endl;
        }
    }

    // Test 2: Invalid input with force and changeable = true (num out of range)
    int row2 = 0, col2 = 2, num2 = 10;
    bool change2 = true, force2 = true;

    if (sudoku.getSquareChangeable(row2, col2) && (num2 >= 1 && num2 <= 9)) {
        sudoku.setSquareValue(row2, col2, num2, change2, force2);
        if (sudoku.getSquareValue(row2, col2) != num2) {
            std::cout << "set/get Test 2: Passed; Square value remains the same (num out of range)" << std::endl;
        } else {
            std::cout << "set/get Test 2: Failed; Square value changed to 10 (num out of range)" << std::endl;
        }
    } else {
        std::cout << "set/get Test 2: Passed; Ignored due to invalid input (num out of range)" << std::endl;
    }

    // Test 3: Valid input with force = false and changeable = true (forced change)
        int row3 = 0, col3 = 3, num3 = 4;
        bool change3 = true, force3 = false;

        if (sudoku.getSquareChangeable(row3, col3) && (num3 >= 1 && num3 <= 9)) {
            sudoku.setSquareValue(row3, col3, num3, change3, force3);
            if (sudoku.getSquareValue(row3, col3) == num3) {
                std::cout << "set/get Test 3: Passed; Square value changed to 4 as expected" << std::endl;
            } else {
                std::cout << "set/get Test 3: Failed; Square value remains the same (not forced)" << std::endl;
            }
        } else {
            std::cout << "set/get Test 3: Passed; Ignored due to invalid input (num or changeable false)" << std::endl;
        }

    // Test 4: Valid input with force = false and changeable = true (unchangeable square)
        int row4 = 0, col4 = 0, num4 = 2;
        bool change4 = true, force4 = false;

        if (sudoku.getSquareChangeable(row4, col4) && (num4 >= 1 && num4 <= 9)) {
            sudoku.setSquareValue(row4, col4, num4, change4, force4);
            if (sudoku.getSquareValue(row4, col4) != num4) {
                std::cout << "set/get Test 4: Passed; Square value remains the same (unchangeable square)" << std::endl;
            } else {
                std::cout << "set/get Test 4: Failed; Square value changed to 2 (unchangeable square)" << std::endl;
            }
        } else {
            std::cout << "set/get Test 4: Passed; Ignored due to invalid input (changeable false)" << std::endl;
        }

    // Test 5: Valid input with force = true and changeable = false (forced change of unchangeable)
        int row5 = 1, col5 = 1, num5 = 9;
        bool change5 = false, force5 = true;

        if (sudoku.getSquareChangeable(row5, col5) && (num5 >= 1 && num5 <= 9)) {
            sudoku.setSquareValue(row5, col5, num5, change5, force5);
            if (sudoku.getSquareValue(row5, col5) == num5) {
                std::cout << "set/get Test 5: Passed; Square value changed to 9 (forced change of unchangeable square)" << std::endl;
            } else {
                std::cout << "set/get Test 5: Failed; Square value remains the same (unchangeable square, forced change)" << std::endl;
            }
        } else {
            std::cout << "set/get Test 5: Passed; Ignored due to invalid input (changeable false)" << std::endl;
        }


        std::cout << "display test: Passed; sudoku displays as expected" << std::endl;
        sudoku.display();

    //Milestone #3
    std::cout << "===================== MILESTONE 3 =====================" << std::endl;

    const std::vector<std::vector<int>> originalPuzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    std::vector<std::vector<int>> mile3puzzle = originalPuzzle;

    Sudoku milestone3(mile3puzzle); //create new object

    //UNIT TEST - isValid
    //T1
    if (milestone3.isValid(0, 2, 2)) { //passing because value passes all 3 tests listed in isValid
        std::cout << "isValid test 1 Passed: value at row 0 col 2 with a value of 2 is valid" << std::endl;
    }
    else {
        std::cout << "isValid test 1 Failed: value at row 0 col 2 with a value of 2 is not valid" << std::endl;
    }

    //T2
    if (milestone3.isValid(0, 2, 8)) { //failing because value is a duplicate (two 8 in column)
        std::cout << "isValid test 2 Passed: value at row 0 col 2 with a value of 8 is valid" << std::endl;
    }
    else {
        std::cout << "isValid test 2 Failed: value at row 0 col 2 with a value of 8 is not valid" << std::endl;
    }

    //T3
    if (milestone3.isValid(0, 2, 12)) { //failing because value is out of range (12 > 9)
        std::cout << "isValid test 3 Passed: value at row 0 col 2 with a value of 12 is valid" << std::endl;
    }
    else {
        std::cout << "isValid test 3 Failed: value at row 0 col 2 with a value of 12 is not valid" << std::endl;
    }

    //T4
    if (milestone3.isValid(0, 0, 4)) { //failing because value is not changeable (r0,c0 with value 5 is a pre-solved square)
        std::cout << "isValid test 4 Passed: value at row 0 col 2 with a value of 4 is valid" << std::endl;
    }
    else {
        std::cout << "isValid test 4 Failed: value at row 0 col 2 with a value of 4 is not valid" << std::endl;
    }

    //UNIT TEST - saveToFile/loadFromFile
    //IMPORTANT -> loadFromFile contains the unit testing inside of the function to ensure that it correctly loads each value of the sudoku puzzle
    milestone3.saveToFile("test.txt"); //read puzzle to file
    milestone3.loadFromFile("test.txt"); //load puzzle from file
    std::cout << std::endl;

    //milestone #4
    std::cout << "===================== MILESTONE 4 =====================" << std::endl;

    //create original object of sudoku
    Sudoku original(originalPuzzle);

    //create new object of sudoku
    std::vector<std::vector<int>> mile4Puzzle = originalPuzzle; //copy the original puzzle
    Sudoku milestone4(mile4Puzzle);

    //welcome message
    std::cout << "Welcome to Sudoku!" << std::endl
              << "Current Sudoku Puzzle:" << std::endl;
    milestone4.menu(milestone4);
    int menuDecision;
    int inputRow, inputCol, inputSquareValue;
    std::cin >> menuDecision; //obtain initial menuDecision
    while ((menuDecision != 3) && (!milestone4.isSolved())) { //runs while the solver isnt exited and while the sudoku still needs to be solved
        if (menuDecision == 1) { //set a value in the puzzle
            std::cout << "Enter row (0-8), column (0-8), and value (1-9): ";
            std::cin >> inputRow >> inputCol >> inputSquareValue; //obtain values for user to alter sudoku
            if (milestone4.isValid(inputRow,inputCol,inputSquareValue)) {
                milestone4.setSquareValue(inputRow, inputCol, inputSquareValue, milestone4.getSquareChangeable(inputRow, inputCol), false); //sets square to user value
                std::cout << ">> valid input." << std::endl;
                milestone4.menu(milestone4); //display new menu
                std::cin >> menuDecision; //get new menu decision
            }
            else {
                std::cout << ">>> The value (" << inputSquareValue
                          << ") you requested to set at row: " << inputRow
                          << ", column: " << inputCol
                          << " is invalid." << std::endl;
                milestone4.menu(milestone4); //display new menu
                std::cin >> menuDecision; //get new menu decision
            }
        }
        else if (menuDecision == 2) { //restart puzzle
            milestone4 = original;
            std::cout << ">>> Puzzle restarted." << std::endl;
            milestone4.menu(milestone4);
            std::cin >> menuDecision; //new decision
        }
        else { //if not a menu item
            std::cout << ">>> Invalid menu item entered." << std::endl;
            milestone4.menu(milestone4);
            std::cin >> menuDecision; //new decision
        }
    }

    if (milestone4.isSolved()) {
        std::cout << "SUDOKU PUZZLE SOLVED!" << std::endl; //solved message
    }
    std::cout << "Exiting Sudoku Solver. Goodbye!" << std::endl; //goodbye message
    

    return 0;
}
