package bounce.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.sun.org.apache.xpath.internal.operations.And;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;
import bounce.ShapeModelEvent;
import bounce.ShapeModelListener;

public class TreeModelAdapter implements TreeModel {
	
	protected ShapeModel _adaptee;
	


	// List of TreeModelListeners.
	protected List<TreeModelListener> _listeners;
	
	public TreeModelAdapter(ShapeModel shape) {
		_adaptee = shape;
		_listeners = new ArrayList<TreeModelListener>();
	}

	@Override
	public Object getRoot() {
		return _adaptee.root();
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof NestingShape) {
			NestingShape nestingShape = (NestingShape)parent;
			if(index < 0 || index >= nestingShape.shapeCount()) {
				return null;
			}
			else {
				return nestingShape.shapeAt(index);
			}
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof NestingShape) {
			NestingShape nestingShape = (NestingShape)parent;
			return nestingShape.shapeCount();
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof NestingShape) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent == null || child == null) {
			return -1;
		}
		
		else if (parent instanceof NestingShape && child instanceof Shape) {
			NestingShape nestingShape = (NestingShape)parent;
			Shape childShape = (Shape)child;
			return nestingShape.indexOf(childShape);
		}
		
		else {
			return -1;
		}
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		_listeners.add(l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		_listeners.remove(l);
	}

}
