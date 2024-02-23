package drawing;

import stdlib.*;
import java.awt.Color;

public class Canvas
{
	private final int tracerNanoSeconds;
	private final Draw draw;
	private final Thread update;

	public Canvas(int tracerNanoSeconds)
	{
		this.tracerNanoSeconds = tracerNanoSeconds;
		draw = new Draw();

		draw.enableDoubleBuffering();
		update = new Thread(() -> 
		{
			long startNanoSeconds = System.nanoTime();
			while (true)
			{
				if (System.nanoTime() - startNanoSeconds > tracerNanoSeconds)
				{
					draw.show();
					startNanoSeconds = System.nanoTime();
				}
			}
		});
		update.start();
	}

	public void clear() 
	{ 
		draw.clear();
	}

	public void clear(Color color)
	{
		draw.clear(color);
	}

	protected void setPenColor(Color color)
	{
		draw.setPenColor(color);
	}

	protected void setPenRadius(double penRadius)
	{
		draw.setPenRadius(penRadius);
	}

	public void line(double x1, double y1, double x2, double y2)
	{
		draw.line(x1, y1, x2, y2);
	}

	public void pause(int milliSeconds)
	{
		draw.pause(milliSeconds);
	}

	public void setXscale(double min, double max)
	{
		draw.setXscale(min, max);
	}

	public void setYscale(double min, double max)
	{
		draw.setYscale(min, max);
	}
}
