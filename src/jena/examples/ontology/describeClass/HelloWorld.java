package jena.examples.ontology.describeClass;

import java.util.Iterator;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create the base model
				//String SOURCE = "http://www.semanticweb.org/ziv/ontologies/2014/3/untitled-ontology-19";
				String SOURCE ="http://www.w3.org/2001/sw/WebOnt/guide-src/wine";
		        String NS = SOURCE + "#";
				OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
				OntDocumentManager dm = m.getDocumentManager();
				dm.addAltEntry( SOURCE,"file:" + "testing/reasoners/bugs/wine.owl"    );
				m.read( SOURCE, "RDF/XML" );
				//get root node TRAVEL
				OntClass rootClass=m.getOntClass(NS+"Wine");
				int depth=0;
				printOntClassNode(rootClass,depth);
				
				//列出model中所包含的所有的类
				for(Iterator<OntClass> i = m.listClasses(); i.hasNext();){
				 OntClass c = i.next();
				 System.out.println("ontClass:" + c.getLocalName());
				}
				//理出model中所包含的所有的实例
				for(Iterator<Individual> i = m.listIndividuals(); i.hasNext();){
				 Individual j = i.next();
				 System.out.println("individual:" + j.getLocalName());
				}
				//列出model中所有的物体的属性
				for(ExtendedIterator<ObjectProperty> i = m.listObjectProperties(); i.hasNext();){
				 System.out.println("ObjectProperty:" + i.next().getLocalName());   
				}
				//列出model中所有的数据类型属性
				for(ExtendedIterator<DatatypeProperty> i = m.listDatatypeProperties(); i.hasNext();){
				 System.out.println("DatatypeProperty:" + i.next().getLocalName());
				}
				
			}
			private static void printOntClassNode(OntClass oc,int depth)
			{
				System.out.println("OntClass:"+oc.getLocalName()+" Depth:"+depth);
				//print all subclass
				if(oc.hasSubClass())
				{
					for(Iterator<OntClass> it = oc.listSubClasses(true); it.hasNext();){
						OntClass c = it.next();
						printOntClassNode(c, depth+1);
					}
				}
				//print all Individual
				for(Iterator<Individual> i = (Iterator<Individual>) oc.listInstances(true); i.hasNext();){
					 Individual ind = i.next();
					 int deeper=depth+1;
					 System.out.println("Individual:"+ind.getLocalName()+" Depth:"+deeper);
				}
				return ;
			}
}
