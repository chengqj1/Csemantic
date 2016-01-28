package jena.examples.rdf;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
public class Preix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model m = ModelFactory.createDefaultModel();
		String nsA = "http://somewhere/else#";
		String nsB = "http://nowhere/else#";
		Resource root = m.createResource( nsA + "root" );
		Property P = m.createProperty( nsA + "P" );
		Property Q = m.createProperty( nsB + "Q" );
		Resource x = m.createResource( nsA + "x" );
		Resource y = m.createResource( nsA + "y" );
		Resource z = m.createResource( nsA + "z" );
		m.add( root, P, x ).add( root, P, y ).add( y, Q, z );//add(主体,谓语,客体);
		System.out.println( "# -- no special prefixes defined" );
		m.write( System.out );
		System.out.println( "# -- nsA defined" );
		m.setNsPrefix( "nsA", nsA );
		m.write( System.out );
		System.out.println( "# -- nsA and cat defined" );
		m.setNsPrefix( "cat", nsB );
		m.write( System.out );
	}

}
