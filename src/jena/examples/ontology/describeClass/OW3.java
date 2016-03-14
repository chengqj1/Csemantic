package jena.examples.ontology.describeClass;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.VCARD;

public class OW3 {

	public static void main(String[] args) {
		String  SOURCE="d:\\cuishou.owl";
		String NS = "http://www.kaolazhengxin.com/cuishou.owl#";
		             
		OntModel base = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		base.read( SOURCE, "RDF/XML" );
		
//		
//		// create a dummy paper for this example
	    OntClass paper = base.getOntClass( NS + "mobile" );
		Individual p1 = base.createIndividual( NS + "15011020865", paper );
//		
//		
//		OntClass idno = base.getOntClass( NS + "idno" );
//		Individual p2 = base.createIndividual( NS + "510228197409113334", idno );	
//		p2.addProperty(base.createProperty(NS,"age"), "26");
//		p2.addProperty(base.createProperty(NS,"sex"), "ÄÐ");
		
		
	
		base.setNsPrefix("", NS);
		
		OntClass idno     = base.createClass( NS + "idno" );
		DatatypeProperty  name = base.createDatatypeProperty( NS + "name" );
		DatatypeProperty  sex = base.createDatatypeProperty( NS + "sex" );
		DatatypeProperty  age = base.createDatatypeProperty( NS + "age" );
		
		ObjectProperty hasMobile = base.createObjectProperty( NS + "hasMobile" );
		
		//ObjectProperty	bookMadeMovie = base.createObjectProperty(SOURCE + "bookMadeMovie");
		//base.setNsPrefix("BookMadeIntoMovie", SOURCE+"bookMadeMovie");
		//director.addLabel("Director",SOURCE);
		
		name.addDomain(idno);
		sex.addDomain(idno);
		age.addDomain(idno);
		//hasMobile.addDomain(idno);
		

		//director.addProperty(VCARD.NAME, "Stanley Kubrick");
		//director.addProperty(VCARD.ROLE, "Director");
		//bookMadeMovie.addDomain(film);
		//bookMadeMovie.addProperty(DCTerms.creator, m.createResource().addProperty(VCARD.NAME, "Peter George").addProperty(VCARD.ROLE, "Author"));
		//bookMadeMovie.addProperty(DCTerms.title, "Red Alert");
		
		//InputStream in = 
		Individual instance1 = idno.createIndividual( NS + "510228197409113334");
		instance1.addLiteral(name, "³ÌÆä½­");
		instance1.addLiteral(sex, "ÄÐ");
		instance1.addLiteral(age, "40");
		
		//instance1.addProperty(hasMobile,base.createResource().addProperty(base.createObjectProperty("rdf:resource"), "#15911020865"));
		instance1.addProperty(hasMobile,p1);
		
		
		//instance1.addProperty(bookMadeMovie, m.createResource().addProperty(DCTerms.creator, m.createResource().addProperty(VCARD.NAME, "Peter George").addProperty(VCARD.ROLE, "Author")).addProperty(DCTerms.title, "Red Alert"));
		
		//instance1.addProperty(instance1.getProperty(director);
//		Individual instance2 = film.createIndividual( SOURCE + "Dr. Strangelove");
//		instance2.addLiteral(name, "Dr. Strangelove");
//		instance2.addLiteral(year, 1964);
//		instance2.addProperty(director,m.createResource().addProperty(VCARD.NAME, "Stanley Kubrick").addProperty(VCARD.ROLE, "Director"));
//		instance2.addProperty(bookMadeMovie, m.createResource().addProperty(DCTerms.creator, m.createResource().addProperty(VCARD.NAME, "Peter George").addProperty(VCARD.ROLE, "Author")).addProperty(DCTerms.title, "Red Alert"));
		
//		try {
//			fop = new FileOutputStream(Lab2);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		
		try{
			//base.write(new FileOutputStream(new File("d:\\cheng2.txt")));
			base.write(new FileOutputStream(new File("d:\\cheng2.txt")),"RDF/XML-ABBREV");
			base.write(System.out,"RDF/XML-ABBREV");	
		}catch(Exception o){
			
		}
		
/*		
		for (ExtendedIterator<Resource> i = paper.listInstances(); i.hasNext(); ) {
			i.next();
		   // System.out.println( p1.getURI() + " is asserted in class " + i.next() );
		}
*/
		/*			// list the asserted types
		for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
			i.next();
		   // System.out.println( p1.getURI() + " is asserted in class " + i.next() );
		}

		// list the inferred types
		p1 = inf.getIndividual( NS + "paper1" );
		for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
			//i.next();
		    System.out.println( p1.getURI() + " is inferred to be in class " + i.next() );
		}*/
		//OntClass artefact = base.getOntClass( NS + "Artefact" );
/*		for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext(); ) {
		  OntClass c = i.next();
		  System.out.println( c.getURI() );
		}*/
//		for (Iterator<OntClass> i = artefact.listSuperClasses(); i.hasNext(); ) {
//			  OntClass c = i.next();
//			  System.out.println( c.getModel().shortForm(c.getURI()) );
//			}
		//System.out.println(artefact.getLocalName());
		//System.out.println(artefact.getLabel(""));
		// get the class references
//		OntClass place = m.getOntClass( NS + "Place" );
//		OntClass indTrack = m.getOntClass( NS + "IndustryTrack" );
//
//		// get the property references
//		ObjectProperty hasPart = m.getObjectProperty( NS + "hasPart" );
//		ObjectProperty hasLoc = m.getObjectProperty( NS + "hasLocation" );
//
//		// create the UK instance
//		Individual uk = place.createIndividual( NS + "united_kingdom" );
//
//		// now the anonymous restrictions
//		HasValueRestriction ukLocation =
//		    m.createHasValueRestriction( null, hasLoc, uk );
//		SomeValuesFromRestriction hasIndTrack =
//		    m.createHasValueRestriction( null, hasPart, indTrack );
//
//		// finally create the intersection class
//		IntersectionClass ukIndustrialConf =
//		    m.createIntersectionClass( NS + "UKIndustrialConference",
//		                               m.createList( new RDFNode[] {ukLocation, hasIndTrack} ) );
	}

}
