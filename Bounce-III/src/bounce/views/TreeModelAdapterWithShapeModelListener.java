package bounce.views;

import java.util.ArrayList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;
import bounce.ShapeModelEvent;
import bounce.ShapeModelListener;
import sun.print.PSPrinterJob.PluginPrinter;

public class TreeModelAdapterWithShapeModelListener extends TreeModelAdapter implements ShapeModelListener{

	public TreeModelAdapterWithShapeModelListener(ShapeModel shape) {
		super(shape);
	}

	@Override
	public void update(ShapeModelEvent event) {
		ShapeModelEvent.EventType eventType = event.eventType();
		Shape shape = event.operand();
		NestingShape parent = event.parent();
		if (parent != null) {
			ArrayList<Shape> path = new ArrayList<Shape>();
			while (parent != getRoot()) {
				path.add(0, parent);
				parent = parent.parent();
			}
			path.add(0, parent);
			Object[] pathObjects = new Object[path.size()];
			for (int i = 0; i < path.size(); i++) {
				pathObjects[i] = path.get(i);
			}
			Object source = event.source();
			int[] childIndices = new int[1];
			childIndices[0] = event.index();
			Object[] children = new Object[1];
			children[0] = shape;
			TreeModelEvent treeEvent = new TreeModelEvent(source, pathObjects, childIndices, children);
			for (TreeModelListener listener : _listeners) {
				if(eventType == ShapeModelEvent.EventType.ShapeAdded) {
					listener.treeNodesInserted(treeEvent);
				}
				else if(eventType == ShapeModelEvent.EventType.ShapeRemoved) {
					listener.treeNodesRemoved(treeEvent);
				} 
			}
		}
		
	}

}
