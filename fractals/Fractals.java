import drawing.*;
import java.awt.Color;

class Fractals
{
	static final double TAU = 6.283185;
	static final double PHI = 1.618033;

	public static void triangle(Turtle turtle, double[] leftVertex, double sideLength)
	{
		turtle.penUp();
		turtle.goTo(leftVertex[0], leftVertex[1]);
		turtle.penDown();
		for (int i = 0; i < 3; i++)
		{
			turtle.forward(sideLength);
			turtle.counterclockwise(120.0);
		}
	}


	static void sierpinskiTriRecurs(Turtle turtle, double[] leftCorner, double sideLength, int depth)
	{
		if (depth == 0) 
		{ // base case
			triangle(turtle, leftCorner, sideLength);
			return;
		}

		double[] midpointLeft = new double[] { leftCorner[0] + sideLength / 4.0,	// x
			leftCorner[1] + sideLength * Math.sqrt(3) / 4.0 };			// y
		double[] midpointBottom = new double[] { leftCorner[0] + sideLength / 2.0, 	// x
			leftCorner[1] };							// y

		sierpinskiTriRecurs(turtle, leftCorner, sideLength / 2.0, depth - 1);
		sierpinskiTriRecurs(turtle, midpointLeft, sideLength / 2.0, depth - 1);
		sierpinskiTriRecurs(turtle, midpointBottom, sideLength / 2.0, depth - 1);
	}

	public static void sierpinskiTri(Turtle turtle, double[] leftCorner, double sideLength)
	{
		sierpinskiTriRecurs(turtle, leftCorner, sideLength, 6);
	}

	public static void sierpinskiTri(Turtle turtle, double[] leftCorner, double sideLength, int depth)
	{
		sierpinskiTriRecurs(turtle, leftCorner, sideLength, depth);
	}


	public static void regPoly(Turtle turtle, int n, double[] center, double radius)
	{
		double oneAngle = TAU / n;
		double angle = 0.0;

		double startX = center[0] + radius * Math.cos(angle);
		double startY = center[1] + radius * Math.sin(angle);

		turtle.penUp();
		turtle.goTo(startX, startY);
		turtle.penDown();

		for (int i = 0; i < n; i++)
		{
			angle += oneAngle;
			double x = center[0] + radius * Math.cos(angle);
			double y = center[1] + radius * Math.sin(angle);
			turtle.goTo(x, y);
		}
	}

	public static double[][] invisRegPoly(int n, double[] center, double radius)
	{
		double oneAngle = TAU / n;
		double angle = 0.0;

		double[][] verticies = new double[n][];
		double startX = center[0] + radius * Math.cos(angle);
		double startY = center[1] + radius * Math.sin(angle);
		verticies[0] = new double[] { startX, startY };

		for (int i = 1; i < n; i++)
		{
			angle += oneAngle;
			double x = center[0] + radius * Math.cos(angle);
			double y = center[1] + radius * Math.cos(angle);
			verticies[i] = new double[] { x, y }; 
		}

		return verticies;
	}


	public static void nFlakeRecurs(Turtle turtle, int n, double[] center, double radius, double scaleFactor, int depth)
	{
		if (depth == 0)
		{
			regPoly(turtle, n, center, radius);
			return;
		}

		double newRadius = radius * scaleFactor;
		double[][] newCenters = invisRegPoly(n, center, radius - newRadius);

		for (double[] newCenter : newCenters)
			nFlakeRecurs(turtle, n, newCenter, newRadius, scaleFactor, depth - 1);
	}

	public static void nFlake(Turtle turtle, int n, double[] center, double radius)
	{
		double sum = 1.0;
		for (int i = 1; i < n / 4 + 1; i++)
		{
			sum += Math.cos(TAU * i / n);
		}
		double scaleFactor = 1.0 / (2.0 * sum);
		nFlakeRecurs(turtle, n, center, radius, scaleFactor, 6);	
	}

	public static void nFlake(Turtle turtle, int n, double[] center, double radius, int depth)
	{
		double sum = 1.0;
		for (int i = 1; i < n / 4 + 1; i++)
		{
			sum += Math.cos(TAU * i / n);
		}
		double scaleFactor = 1.0 / (2.0 * sum);
		nFlakeRecurs(turtle, n, center, radius, scaleFactor, depth);	
	}

	public static void main(String[] args)
	{
		Canvas canvas = new Canvas(10000000);
		canvas.setXscale(-1.0, 1.0);
		canvas.setYscale(-1.0, 1.0);
		canvas.clear();

		Turtle oogway = new Turtle(canvas);
		oogway.setPenRadius(0.002);

		// triangle(oogway, new double[] { 0.0, 0.0 }, 0.25);
		// oogway.pause(1);
		// canvas.clear();

		sierpinskiTri(oogway, new double[] { -0.87, -0.75 }, 1.75, 11);
		oogway.pause(1);
		// canvas.clear();

		// regPoly(oogway, 6, new double[] { 0.0, 0.0 }, 0.5);
		// oogway.pause(1);
		// canvas.clear();

		// nFlake(oogway, 3, new double[] { 0.0, 0.0 }, 0.5, 4);
	}
}
