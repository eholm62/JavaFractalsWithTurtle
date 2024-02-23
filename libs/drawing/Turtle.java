package drawing;

import java.awt.Color;

public class Turtle
{
	double x;
	double y;
	double angle;
	boolean isUp;

	Canvas canvas;

	public Turtle(Canvas canvas)
	{
		this.x = 0.0;
		this.y = 0.0;
		this.angle = 0.0;
		this.isUp = false;
		canvas.setPenColor(Color.BLACK);
		canvas.setPenRadius(0.0025);

		this.canvas = canvas;
	}

	public void setPenColor(Color color)
	{
		canvas.setPenColor(color);
	}

	public void setPenRadius(double penRadius)
	{
		canvas.setPenRadius(penRadius);
	}

	public void clockwise(double degrees)
	{
		angle = (angle - degrees) % 360.0;
	}

	public void counterclockwise(double degrees)
	{
		angle = (angle + degrees) % 360.0;
	}

	public void penUp()
	{
		isUp = true;
	}

	public void penDown()
	{
		isUp = false;
	}

	public void goTo(double x, double y)
	{
		if (!isUp) canvas.line(x, y, this.x, this.y);
		this.x = x;
		this.y = y;
	}

	public void forward(double distance)
	{
		double deltaX = Math.cos(Math.toRadians(this.angle)) * distance;
		double deltaY = Math.sin(Math.toRadians(this.angle)) * distance;
		if (!isUp) canvas.line(this.x, this.y, this.x + deltaX, this.y + deltaY);
		this.x += deltaX;
		this.y += deltaY;
	}

	public void pause(double seconds)
	{
		canvas.pause((int)(seconds * 1000));
	}
}
