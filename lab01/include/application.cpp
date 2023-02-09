#include "application.h"

Line Application::calculateTangent()
{
	return Line();
}

void Application::Run()
{
	point = menu.GetPointPosition();
	circle = Circle(menu.GetCirclePosition(), menu.GetCircleDiameter());
	tangent = calculateTangent();
	graphics.DrawTangent(circle, point);

	char choice = 0;
	while (true)
	{
		auto choice = menu.GetMenuChoice();
		switch (choice)
		{
		case Menu::Choice::DrawTangent:
		{
			point = menu.GetPointPosition();
			Point circleCenter = menu.GetCirclePosition();
			int diameter = menu.GetCircleDiameter();
			circle = Circle(circleCenter, diameter);
			break;
		}

		case Menu::Choice::ChangeCirclePosition:
		{
			Point circleCenter = menu.GetCirclePosition();
			circle = Circle(circleCenter, circle.diameter);
			break;
		}

		case Menu::Choice::ChangeCircleDiameter:
		{
			int diameter = menu.GetCircleDiameter();
			circle = Circle(circle.center, diameter);
			break;
		}
		case Menu::Choice::ChangePointPosition:
		{
			point = menu.GetPointPosition();
			break;
		}
		case Menu::Choice::CloseApplication:
		{
			return;
		}
		}
		graphics.DrawTangent(circle, point);
	}
}
