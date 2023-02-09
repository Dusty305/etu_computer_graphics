#include <iostream>
#include "graphics/point.h"

class Menu
{
private:

public:
	// Menu options from which users choose
	enum class Choice
	{
		DrawTangent = 1, 
		ChangeCirclePosition, 
		ChangeCircleDiameter,
		ChangePointPosition,
		CloseApplication
	};

private:
	// Check if menu option input is within given options
	inline bool IsCorrectChoiceInput(const int& choice);

public:
	Menu() = default;

	// Shows menu and returns choosen menu option
	Menu::Choice GetMenuChoice();

	Point GetPointPosition();
	Point GetCirclePosition();
	int GetCircleDiameter();
};