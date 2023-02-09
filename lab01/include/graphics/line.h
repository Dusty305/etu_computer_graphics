#pragma once
#include "point.h"
#include "circle.h"

struct Line
{
	Point a, b;

	Line() = default;
	Line(Point a, Point b) : a(a), b(b)
	{ }
};