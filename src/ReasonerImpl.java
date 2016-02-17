import java.util.List;

import org.apache.jena.graph.Capabilities;
import org.apache.jena.graph.Graph;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.InfGraph;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerException;
import org.apache.jena.reasoner.ReasonerFactory;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.shared.RulesetNotFoundException;
import org.apache.jena.vocabulary.ReasonerVocabulary;

//public class ReasonerImpl implements Reasoner {
public class ReasonerImpl  {	
	private InfModel inf = null;

	/**
	 * 获取一个推理接口
	 * @param path
	 * @return
	 * @throws RulesetNotFoundException
	 */
	private GenericRuleReasoner getReasoner(String path) throws RulesetNotFoundException {
		
		List<Rule> rules = Rule.rulesFromURL(path);  //"file:./family/family.rules"
		GenericRuleReasoner reasoner = new GenericRuleReasoner(rules);
		reasoner.setOWLTranslation(true);
		reasoner.setDerivationLogging(true);
		reasoner.setTransitiveClosureCaching(true);
		return reasoner;
		
	}
	
	/**
	 * 获取推理的本体
	 * @param path
	 * @return
	 */
	private OntModel getOntModel(String path) {
		
		Model model = ModelFactory.createDefaultModel();
		model.read(path);  //"file:./family/family.owl"
		OntModel ont = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,
				model);
		Resource configuration = ont.createResource(); //a new anonymous resource linked to this model
		configuration.addProperty(ReasonerVocabulary.PROPruleMode
				, "hybrid");
		return ont;
		
	}
	
	/**
	 * InfModel是对常规Model的扩展，支持任何相关的推理能力
	 * @param ontPath
	 * @param rulePath
	 * @return
	 */
	public InfModel getInfModel(String rulePath, String ontPath) {
		
		this.inf = ModelFactory.createInfModel(getReasoner(rulePath), getOntModel(ontPath));
		return this.inf;
		
	}
	
	/**
	 * InfModel是对常规Model的扩展，支持任何相关的推理能力
	 * @param model
	 * @param rulePath
	 * @return
	 */
	public InfModel getInfModel(String rulePath, OntModel model) {
		this.inf = ModelFactory.createInfModel(getReasoner(rulePath), model);
		return this.inf;
	}
	
	/**
	 * 打印推理结果
	 * @param a
	 * @param b
	 */
	public void printInferResult(Resource a, Resource b) {
		
		StmtIterator stmtIter = this.inf.listStatements(a, null, b);
		if (!stmtIter.hasNext()) {
			System.out.println("there is no relation between "
				      + a.getLocalName() + " and " + b.getLocalName());
			System.out.println("\n-------------------\n");
		}
		while (stmtIter.hasNext()) {
			Statement s = stmtIter.nextStatement();
			System.out.println("Relation between " + a.getLocalName() + " and "
				      + b.getLocalName() + " is :");
			System.out.println(a.getLocalName() + " "
				      + s.getPredicate().getLocalName() + " " + b.getLocalName());
			System.out.println("\n-------------------\n");
		}
		
	}
	
	public void searchOnto(String queryString) {
		
		Query query = QueryFactory.create(queryString);	  
	    QueryExecution qe = QueryExecutionFactory.create(query, this.inf);
	    ResultSet results = qe.execSelect();
	    
	    ResultSetFormatter.out(System.out, results, query);
	    qe.close();
	    
	}
	public  void testQuery() {
		String ruleFile = "file:./expert/Expert.rules";
		String ontoFile = "file:./expert/Expert.owl";
		String queryString = "PREFIX Expert:<http://www.owl-ontologies.com/Expert.owl#> " +
	    	"SELECT ?expert ?subject " +
	    	"WHERE {?expert Expert:familiar_with ?subject} ";
	
		
		//Reasoner famRea = ReasonerRegistry.getRDFSReasoner();
		
		getInfModel(ruleFile, ontoFile);
		searchOnto(queryString);
	}
    public static void main( String[] args ) {
    	ReasonerImpl  re=new ReasonerImpl();
    	re.testQuery();
    }


}