package bounce;

import java.util.ArrayList;
import java.util.LinkedList;


public class NestingShape extends Shape {
	
	private LinkedList<Shape> children;
	
	/**   
	* Creates a NestingShape object with default values.
	*/ 
	public NestingShape() {
		super(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
		
	/**
	* Creates a NestingShape object with specified location values, default values for others .   
	*/ 
	public NestingShape (int x, int y) {
		super(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
		
	/**
	* Creates a NestingShape with specified values for location, velocity and direction. Nonspecified   
	* attributes take on default values.   
	*/ 
	public NestingShape (int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 *  Creates a NestingShape with specified values for location, velocity, direction, width and height.    
	 */  
	public NestingShape (int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
		children = new LinkedList<Shape>();
	}
	
	/**
	 *  Creates a NestingShape with specified values for location, velocity, direction, width, height and text.    
	 */  
	public NestingShape (int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
		children = new LinkedList<Shape>();
	}
	
	/**    
	* Moves a NestingShape object (including its children) within the bounds specified by arguments    
	* width and height. A NestingShape first moves itself, and then moves its children.   
	*/ 
	public void move (int width, int height) {
		super.move(width, height);
		for(Shape child : children) {
			child.move(_width, _height);
		}
	}
	
	/**   
	* Paints a NestingShape object by drawing a rectangle around the edge of its bounding box. Once   
	* the NestingShape's border has been painted, a NestingShape paints its children.    
	*/ 
	@Override
	protected void doPaint(Painter painter) {
		painter.drawRect(_x,_y,_width,_height);
		for(Shape child : children){
			painter.translate(_x, _y);
			child.paint(painter);
			painter.translate(-_x, -_y);
		}
	}
	
	/**   
	* Attempts to add a Shape to a NestingShape object. If successful, a two-way link is established   
	* between the NestingShape and the newly added Shape. Note that this method has package    
	* visibility for reasons that will become apparent in Bounce III.    
	* @param shape the shape to be added.    
	* @throws IllegalArgumentException if an attempt is made to add a Shape to a NestingShape   
	* instance where the Shape argument is already a child within a NestingShape instance. An   
	* IllegalArgumentException is also thrown when an attempt is made to add a Shape that will not fit   
	* within the bounds of the proposed NestingShape object.    
	*/  
	void add (Shape shape) throws IllegalArgumentException{
		if (shape._parent != null) {
			throw new IllegalArgumentException();
		}
		if(shape._width + shape._x > _x + _width || shape._y + shape._height > _y + _height) {
			throw new IllegalArgumentException();
		}
		
		children.add(shape);
		shape.setParent(this);
	}
	
	/**  
	* Removes a particular Shape from a NestingShape instance. Once removed, the two-way link      
	* between the NestingShape and its former child is destroyed. This method has no effect if the      
	* Shape specified to remove is not a child of the NestingShape . Note that this method has package      
	*visibility  for reasons that will become apparent in Bounce III .    
	* @param shape the shape to be removed.    
	*/ 
	void remove (Shape shape) {
		int idx = children.indexOf(shape);
		if (idx >= 0) {
			children.remove(shape);
			shape.setParent(null);
		}
	}
	
	/**   
	* Returns the Shape at a specified position within a NestingShape. If the position specified is less   
	* than zero or greater than the number of children stored in the NestingShape less one this method   
	* throws an IndexOutOfBoundsException.    
	* @param index the specified index position .    
	*/  
	public Shape shapeAt (int index) throws IndexOutOfBoundsException{
		if (index < 0 || index > children.size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		return children.get(index);
	}
	
	/**   
	* Returns the number of children contained within a NestingShape object. Note this method is not   
	* recursive  it simply returns the number of children at the top level within the callee    
	* NestingShape object.    
	*/ 
	public int shapeCount () {
		return children.size();
	}
	
	/**   
	* Returns the index of a specified child within a NestingShape object. If the Shape specified is not   
	* actually a child of the NestingShape this method returns -1; otherwise the value returned is in the   
	* range 0 .. shapeCount() - 1.    
	* @param the shape whose index position within the NestingShape is requested.    
	*/  
	public int indexOf (Shape shape) {
		return children.indexOf(shape);
	}
	
	/**   
	* Returns true if the Shape argument is a child of the NestingShape object on which this method is   
	* called , false otherwise .    
	*/ 
	public boolean contains (Shape shape){
		return children.contains(shape);
	}

}