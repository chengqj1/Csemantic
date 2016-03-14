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
public class my4 extends Object {
	public static final String uri ="http://www.w3.org/2001/my-rdf/3.0#";
    public static void main (String args[]) {
    
        // some definitions
        String personURI    = "http://somewhere/��";
        String givenName    = "�佭";
        String familyName   = "��";
        String fullName     = givenName + " " + familyName;
        // create an empty model
        Model model = ModelFactory.createDefaultModel();
        
        // create the resource
        //   and add the properties cascading style
        Resource mobile 
          = model.createResource(personURI)
                 .addProperty(model.createProperty(uri,"����"), fullName)
                 .addProperty(model.createProperty(uri,"ȫ��"), 
                              model.createResource()
                                   .addProperty(model.createProperty(uri,"��"),model.createLiteral(givenName, "cn") )
                                   .addProperty(model.createProperty(uri,"��"), familyName));
        model.setNsPrefix( "nsA", uri );

        

        
        //model.write(System.out);
        try{
        	model.write(new FileOutputStream(new File("d:\\cheng.txt")));
        }catch(Exception o){
        	
        }
    }
}
