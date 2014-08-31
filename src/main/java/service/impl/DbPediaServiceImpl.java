package service.impl;

import com.hp.hpl.jena.query.*;
import service.DbPediaService;

/**
 * Created by Silvia on 31. 8. 2014.
 * @author Silvia
 */
public class DbPediaServiceImpl implements DbPediaService{

    @Override
    public void getMetadata(String name){
        String service = "http://dbpedia.org/sparql";
        String sparqlQueryString1 = "PREFIX dbpprop:<http://dbpedia.org/property/> " +
                "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> " +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT * WHERE {\n" +
                "  ?x rdfs:label \""+name+"\"@en.\n" +
                "  ?x dbpedia-owl:abstract ?abstract.\n" +
                "  OPTIONAL { ?x dbpprop:category ?category. }\n" +
                "  OPTIONAL { ?x dbpprop:country ?country. }\n" +
                "  OPTIONAL { ?x dbpprop:commons ?commons. }\n" +
                "  FILTER (LANG(?abstract) = 'en')\n" +
                "}";

        Query query = QueryFactory.create(sparqlQueryString1);
        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        qe.close();


    }
}
