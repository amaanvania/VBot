package loader.modifiers.replacements;

import client.ui.AppletController;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BotMouseListener implements MouseListener, MouseMotionListener
{
	private boolean mouseEnabled;
	private Applet targetApplet;
	private MouseListener[] gameMouseListeners;
	private MouseMotionListener[] gameMouseMotionListeners;
	private Point position;
	private boolean onScreen;

	public BotMouseListener(AppletController target)
	{
		this.mouseEnabled = true;
		this.onScreen = false;
		this.targetApplet = target.getApplet();
		this.gameMouseListeners = targetApplet.getMouseListeners();
		this.gameMouseMotionListeners = targetApplet.getMouseMotionListeners();
		for (MouseListener listener : gameMouseListeners)
		{
			targetApplet.removeMouseListener(listener);
		}
		for (MouseMotionListener listener : gameMouseMotionListeners)
		{
			targetApplet.removeMouseMotionListener(listener);
		}
		targetApplet.addMouseListener(this);
		targetApplet.addMouseMotionListener(this);
		position = new Point(-1, -1);
	}

	public boolean isMouseEnabled()
	{
		return mouseEnabled;
	}

	public boolean isMouseOnScreen()
	{
		return onScreen;
	}

	public void toggleMouse()
	{
		mouseEnabled = !mouseEnabled;
	}

	public Point getMousePosition()
	{
		return position;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (mouseEnabled)
		{
			for (MouseListener listener : gameMouseListeners)
			{
				listener.mouseClicked(e);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if (mouseEnabled)
		{
			for (MouseListener listener : gameMouseListeners)
			{
				listener.mousePressed(e);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (mouseEnabled)
		{
			for (MouseListener listener : gameMouseListeners)
			{
				listener.mouseReleased(e);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (mouseEnabled)
		{
			onScreen = true;
			for (MouseListener listener : gameMouseListeners)
			{
				listener.mouseEntered(e);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		if (mouseEnabled)
		{
			onScreen = false;
			for (MouseListener listener : gameMouseListeners)
			{
				listener.mouseExited(e);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (mouseEnabled)
		{
			for (MouseMotionListener listener : gameMouseMotionListeners)
			{
				listener.mouseDragged(e);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		if (mouseEnabled)
		{
			position = new Point(e.getX(), e.getY());
			for (MouseMotionListener listener : gameMouseMotionListeners)
			{
				listener.mouseMoved(e);
			}
		}
	}
}