#include "Practice.h"
#include <iostream>;
#include <conio.h>;
#include <string>;
#include <cstdlib>
#include <ctime>
#include <Windows.h>
using namespace std;


	//Snake game
	bool quitGame;
	bool quitProgram;
	const int width = 40;
	const int height = 20;
	int x, y, fruitX, fruitY, highscore, score = 0;
	enum eDirection { STOP = 0, LEFT, RIGHT, DOWN, UP };
	eDirection dir;

	//tail variables
	int tailX[100];
	int tailY[100];
	int nTail;
	


	void Setup() {
		score = 0;
		nTail = 0;
		quitGame = false;
		quitProgram = false;

		x = width / 2;
		y = height / 2;
		fruitX = (time(NULL) % (width / 2)) + (width / 4);
		fruitY = (time(NULL) % (height / 2)) + (height / 4);
		dir = STOP;

	}

	void Draw() {

		system("cls");

		//print top border
		for (int i = 0; i < width + 2; i++) cout << "#";

		cout << endl;

		//print middle grid

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width + 2; j++) {
				if (j == 0 || j == width + 1) cout << "#";
				else if (i == y && j == x) cout << "O";
				else if (i == fruitY && j == fruitX) cout << "F";
				else {
					bool print = false;
					for (int k = 0; k < nTail; k++) {
						if (tailX[k] == j && tailY[k] == i) {
							cout << "o";
								print = true;
						}
					}
					if (!print)
					cout << " ";
				}
				

			}
			cout << endl;
		}

		//print bottom border

		for (int i = 0; i < width + 2; i++) cout << "#";
		cout << endl << "SCORE: " << score << endl;
		cout << "HIGH SCORE " << highscore;

		

	}

	void Input() {
		
		if (_kbhit()) {

			switch (_getch()) {
			case 'w':
				dir = UP;
				break;
			case 'a':
				dir = LEFT;
				break;
			case 's':
				dir = DOWN;
				break;
			case 'd':
				dir = RIGHT;
				break;
			case 'x':
				quitGame = true;
				quitProgram = true;
				break;
			default:
				break;
			}


		}


	}

	void Logic() {
		
		int prevX = tailX[0];
		int prevY = tailY[0];
		int prev2X, prev2Y;
		tailX[0] = x;
		tailY[0] = y;
		for (int i = 1; i < nTail; i++) {
			prev2X = tailX[i];
			prev2Y = tailY[i];
			tailX[i] = prevX;
			tailY[i] = prevY;
			prevX = prev2X;
			prevY = prev2Y;
		}

		switch (dir) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case RIGHT:
			x++;
			break;
		case LEFT:
			x--;
			break;
		default:
			break;
		}


		if (fruitX == x && fruitY == y) {
			fruitX = (time(NULL) % (width / 2))+ (width / 4);
			fruitY = (time(NULL) % (height / 2)) + (height / 4);
			score += 10;
			if (score > highscore) highscore = score;
			nTail++;
		}

		if (x == 0 || x == width + 1 || y < 0 || y == height) quitGame = true;
		for (int i = 0; i < nTail; i++) {
			if (x == tailX[i] && y == tailY[i]) {
				quitGame = true;
				break;
			}
		}
	}

	int main() {

		while (!quitProgram) {

			Setup();

			while (!quitGame) {

				Draw();
				Input();
				Logic();
				Sleep(15);
			}
		}

		return 0;
	}
