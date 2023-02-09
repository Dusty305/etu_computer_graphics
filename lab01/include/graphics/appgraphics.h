#include "circle.h"
#include "line.h"
#include "point.h"

class Graphics
{
private:

private:
	Line calculateTangent(const Circle& circle, const Point& point);
public:
	Graphics() = default;

	void DrawTangent(const Circle& circle, const Point& point);
};