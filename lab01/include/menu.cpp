#include "menu.h"

Menu::Choice Menu::GetMenuChoice()
{
	int choice;
	while (true)
	{
		std::cout
			<< "1. Draw tangent\n"
			<< "2. Change circle position\n"
			<< "3. Change circle diameter\n"
			<< "4. Change point position\n"
			<< "5. Close application\n";
		std::cin >> choice;
		if (IsCorrectChoiceInput(choice))
			return (Menu::Choice)choice;
		else
			std::cout << "Error. Wrong input.\n";
	}
}

Point Menu::GetPointPosition()
{
	int x, y;
	std::cout << "Enter point coordinates (x and y): ";
	std::cin >> x >> y;
	return Point(x, y);
}

Point Menu::GetCirclePosition()
{
	int x, y;
	std::cout << "Enter circle coordinates (x and y): ";
	std::cin >> x >> y;
	return Point(x, y);
}

int Menu::GetCircleDiameter()
{
	int l;
	std::cout << "Enter circle diameter: ";
	std::cin >> l;
	return l;
}

inline bool Menu::IsCorrectChoiceInput(const int& choice)
{
	return choice >= (int)Menu::Choice::DrawTangent and choice <= (int)Menu::Choice::CloseApplication;
}