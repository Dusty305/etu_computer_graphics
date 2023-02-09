#include "menu.h"
#include "graphics/appgraphics.h"
#include "graphics/point.h"
#include "graphics/line.h"
#include "graphics/circle.h"

class Application
{
private:
	Menu menu;
	Graphics graphics;

	Circle circle;
	Point point;
	Line tangent;

private:
	Line calculateTangent();
public:
	Application() = default;

	void Run();
};