package jena.examples.ontology.describeClass;

import java.util.List;

import org.apache.jena.reasoner.rulesys.ClauseEntry;
import org.apache.jena.reasoner.rulesys.Rule;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			List<Rule> rules=Rule.rulesFromURL("file:d://rule.txt");
			System.out.println(rules.get(0));
			ClauseEntry[] body=rules.get(0).getBody();
			int j=rules.get(0).bodyLength();
			
			System.out.println(j);
			
			for (int i=0;i<j;i++){
				System.out.print(body[i]+",");
			}
			System.out.println("/n-------------------/n");
			
			ClauseEntry[] head=rules.get(0).getHead();
			int k=rules.get(0).headLength();
			for (int i=0;i<k;i++){
				System.out.print(head[i]+",");
			}
			System.out.println("/n-------------------/n");
			
			System.out.println(rules.get(0).getName());
		
	}

}
