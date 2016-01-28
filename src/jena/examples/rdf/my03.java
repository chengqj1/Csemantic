/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jena.examples.rdf ;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;


/** Tutorial 3 Statement attribute accessor methods
 */
public class my03 extends Object {
	public static final String uri ="http://www.w3.org/2001/my-rdf/3.0#";
    public static void main (String args[]) {
    
        // some definitions
        String personURI    = "http://somewhere/我";
        String givenName    = "其江";
        String familyName   = "程";
        String fullName     = givenName + " " + familyName;
        // create an empty model
        Model model = ModelFactory.createDefaultModel();
        
        // create the resource
        //   and add the properties cascading style
        Resource johnSmith 
          = model.createResource(personURI)
                 .addProperty(model.createProperty(uri,"姓名"), fullName)
                 .addProperty(model.createProperty(uri,"全称"), 
                              model.createResource()
                                   .addProperty(model.createProperty(uri,"名"),model.createLiteral(givenName, "cn") )
                                   .addProperty(model.createProperty(uri,"姓"), familyName));
        model.setNsPrefix( "nsA", uri );
        // list the statements in the graph
        StmtIterator iter = model.listStatements();
        
        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt      = iter.nextStatement();         // get next statement
            Resource  subject   = stmt.getSubject();   // get the subject
            Property  predicate = stmt.getPredicate(); // get the predicate
            RDFNode   object    = stmt.getObject();    // get the object
            
            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
        System.out.println(model.getGraph().size());
        //model.write(System.out);
        try{
        	model.write(new FileOutputStream(new File("d:\\cheng.txt")));
        }catch(Exception o){
        	
        }
    }
}
