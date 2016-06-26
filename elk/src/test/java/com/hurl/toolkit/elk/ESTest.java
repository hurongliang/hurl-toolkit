package com.hurl.toolkit.elk;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hurongliang on 16/6/25.
 */
public class ESTest {
    private static final String host = "localhost:9200";
//    @Test
    public void test(){
//        System.out.println(ES.clusterHealth("localhost:9200"));
//       System.out.println(ES.deleteIndex("localhost:9200", "cars"));
//        System.out.println(ES.createIndex(host, "mrhq"));
        System.out.println(ES.status(host));
    }
//    @Test
    public void setFieldMappings(){
//        ES.closeIndex(host, "mrhq");
        List<FieldMapping> mappings = new ArrayList<>();
        mappings.add(FieldMapping.stringField("gpdm", true));
        mappings.add(FieldMapping.intField("gpdmJ"));
        mappings.add(FieldMapping.dateField("rq"));
        mappings.add(FieldMapping.floatField("open"));
        mappings.add(FieldMapping.floatField("close"));
        mappings.add(FieldMapping.floatField("high"));
        mappings.add(FieldMapping.floatField("low"));
        mappings.add(FieldMapping.longField("closePreDay"));
        mappings.add(FieldMapping.longField("volume"));
        mappings.add(FieldMapping.longField("turnover"));
//        System.out.println(ES.setFieldMapping(host, "mrhq", "mrhq", mappings));
//        ES.openIndex(host, "mrhq");
    }
    @Test
    public void bulk(){
        List<MrhqDocument> docs = new ArrayList<>();
        MrhqDocument doc = new MrhqDocument();
        doc.setGpdm("SH600000");
        doc.setGpdmJ("600000");
        doc.setRq(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        docs.add(doc);
        ES.bulk(host, "mrhq", "mrhq", docs);
    }
}
