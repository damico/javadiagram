package org.jdamico.javadiagram.test;

import java.util.ArrayList;

import org.jdamico.javadiagram.creator.DiagramCreator;
import org.jdamico.javadiagram.creator.StructureCreator;
import org.jdamico.javadiagram.dataobject.RetanguleBox;

import junit.framework.TestCase;

public class TestStructureCreator extends TestCase {

	/* First, declare your universe set as a common String.*/
	private String universeSet =    "a = {b,c}      " +
	"b = {d,e}      " +
	"c = {p}        " +
	"d = {}         " +
	"e = {f,g,h}    " +
	"f = {k,l,m,n,o}" +
	"g = {q,r}      " +
	"h = {i,j}      " +
	"i = {}         " +
	"j = {t,u,v}    " +
	"k = {w,z}      " +
	"l = {s}        " +
	"m = {}         " +
	"n = {}         " +
	"o = {}         " +
	"p = {}         " +
	"q = {x,y}      " +
	"r = {}         " +
	"s = {}         " +
	"t = {}         " +
	"u = {}         " +
	"v = {}         " +
	"x = {}         " +
	"y = {}         " +
	"w = {}         " +
	"z = {}         ";

	public void testConvert(){

		/* Second, instantiate the StructureCreator */
		StructureCreator sc = new StructureCreator();

		/* Third, convert your U set into an array of Retagules */
		ArrayList<RetanguleBox> retArray = sc.convert(universeSet);

		/* Forth, instantiate the DiagramCreator passing a png file name/path */
		DiagramCreator dc = new DiagramCreator("/tmp/test2.png");

		/* Fiftieth and finally, call the design method, passing the converted array */ 
		dc.design(retArray);
	}
}
