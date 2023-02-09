#pragma once
#include "point.h"

struct Circle
{
	Point center;
	int diameter;

	Circle() : diameter(-1)
	{ }
	Circle(Point p, int d) : center(p), diameter(d)
	{ }
};