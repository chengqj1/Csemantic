package jena.examples.ontology.describeClass;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.iterator.ExtendedIterator;

public class OW2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create the base model
		//String SOURCE = "http://www.eswc2006.org/technologies/ontology";
		//String SOURCE ="http://www.eswc2006.org/www.eswc2006.org/technologies/ontology-content";
		String  SOURCE="d:\\cuishou.owl";
		String NS = "http://www.kaolazhengxin.com/cuishou.owl#";
		             
		OntModel base = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		base.read( SOURCE, "RDF/XML" );
		//base.write(System.out);
		// create the reasoning model using the base
		//OntModel inf = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, base );

		// create a dummy paper for this example
	    OntClass paper = base.getOntClass( NS + "mobile" );
		Individual p1 = base.createIndividual( NS + "15011020865", paper );
		
		
		OntClass idno = base.getOntClass( NS + "idno" );
		Individual p2 = base.createIndividual( NS + "510228197409113334", idno );	
		p2.addProperty(base.createProperty(NS,"age"), "26");
		p2.addProperty(base.createProperty(NS,"sex"), "ÄÐ");
		
		
	
	
		p2.addProperty(base.createObjectProperty(NS +"hasMobile"),"15011020865");
		
		Map map=new HashMap();
		map.put("rdf","http://www.w3.org/1999/02/22-rdf-syntax-ns#");
	
		map.put("owl","http://www.w3.org/2002/07/owl#");
		map.put("","http://www.kaolazhengxin.com/cuishou.owl#");
		map.put("rdfs","http://www.w3.org/2000/01/rdf-schema#");
		map.put("owl2xml","http://www.w3.org/2006/12/owl2-xml#");
		map.put("xsd","http://www.w3.org/2001/XMLSchema#");
		
		//base.set
		
		base.setNsPrefixes(map);
		//model.setNsPrefix( "nsA", uri );
		try{
			base.write(new FileOutputStream(new File("d:\\cheng2.txt")));
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
