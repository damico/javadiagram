package org.jdamico.javadiagram.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.jdamico.javadiagram.config.Constants;
import org.jdamico.javadiagram.creator.DiagramCreator;
import org.jdamico.javadiagram.dataobject.RetanguleBox;


public class TestDiagramCreator extends TestCase {
	public void testDesign(){
		
		ArrayList<RetanguleBox> retArray = new ArrayList<RetanguleBox>();
		
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "a", 0, -1, 0, 0));
		
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "b", 1,  0, 1, 0));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "c", 2,  0, 1, 1));
		
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "d", 3,  1, 2, 0));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "e", 4,  1, 2, 1));
		
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "f", 5,  4, 3, 0));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "g", 6,  4, 3, 1));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "h", 7,  4, 3, 2));
		
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "i", 8,  7, 4, 0));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "j", 9,  7, 4, 1));

		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "k", 10, 5, 5, 0));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "l", 11, 5, 5, 1));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "m", 12, 5, 5, 2));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "n", 13, 5, 5, 3));
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "o", 14, 5, 5, 4));
		
		retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, "p", 15, 2, 6, 0));

		DiagramCreator dc = new DiagramCreator("/tmp/jdiagram-test.png");
		dc.design(retArray);
	}
}
